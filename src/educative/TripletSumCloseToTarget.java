import java.util.*;

class TripletSumCloseToTarget {

    public static int searchTriplets(int[] arr, int targetSum) {
        int sumReturnValue = Integer.MIN_VALUE;

        Arrays.sort(arr);
        if (arr.length < 1 || arr[0] >= 0 || arr[arr.length - 1] <= 0) {
            // no elements || no opposite sign numbers
            return sumReturnValue;
        }

        int lastKnownDistanceFromZero = -1;
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                int sum = -1 * (arr[i] + arr[j] - targetSum);

                // binary search
                int numberFound = findValue(arr, i + 1, j - 1, sum);
                int newDistanceFromZero = Math.abs(arr[i] + arr[j] + numberFound - targetSum);
                if (newDistanceFromZero < lastKnownDistanceFromZero) {
                    sumReturnValue = arr[i] + arr[j] + numberFound;
                }
            }
        }

        return sumReturnValue;
    }

    public static int findValue(int[] arr, int leftIndex, int rightIndex, int valueToSearch) {
        int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

        if (arr[midIndex] == valueToSearch) {
            return arr[midIndex];
        }
        if (leftIndex >= rightIndex) {
            int index = getMin(arr, midIndex-1, midIndex, midIndex+1, valueToSearch);
            return arr[index];
        }
        if (valueToSearch >= arr[midIndex]) {
            leftIndex = midIndex + 1;
        } else {
            rightIndex = midIndex - 1;
        }
        System.out.println(leftIndex + "_" + rightIndex + "_" + valueToSearch);

        return findValue(arr, leftIndex, rightIndex, valueToSearch);
    }

    public static int getMin(int[] arr, int l, int m, int r, int valueToSearch){
        int minDiff = Integer.MAX_VALUE;
        int tmp, index=m;
        if(l > 0 && minDiff > (tmp=Math.abs(valueToSearch - arr[l]))){
            minDiff = tmp;
            index = l;
        }
        if(minDiff > (tmp=Math.abs(valueToSearch - arr[m]))){
            minDiff = tmp;
            index = m;
        }
        if(r < arr.length && minDiff > (tmp=Math.abs(valueToSearch - arr[r]))){
            minDiff = tmp;
            index = r;
        }
        return index;
    }

    public static final Map<List<Integer>, Integer> INPUT_DATA = Collections.unmodifiableMap(new HashMap<List<Integer>, Integer>() {
        {
            put(Arrays.asList(-2, 0, 1, 2), 2); // output: 1
            put(Arrays.asList( -3, -1, 1, 2 ), 1); // output: 0
            put(Arrays.asList( 1, 0, 1, 1 ), 100); // output: 3
        }
    });

    public static void main(String[] args) {
        // JDK 10 onwards, can use var as local variable types
        for (Map.Entry<List<Integer>, Integer> rowData : INPUT_DATA.entrySet()) {
            var result = searchTriplets(rowData.getKey().stream().mapToInt(i -> i).toArray(), rowData.getValue());
            System.out.println(String.format("Output: %s for input: %s", result, rowData.getKey()));
        }

    }

}

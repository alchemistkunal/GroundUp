import java.util.*;

class TripletSumToZero {

  public static List<List<Integer>> searchTriplets(int[] arr) {
    List<List<Integer>> triplets = new ArrayList<>();
    
    Arrays.sort(arr);
    if (arr.length < 1 || arr[0] >=0 || arr[arr.length-1] <=0){
      // no elements || no opposite sign numbers
      return triplets;
    }

    // Store combination as String
    Set<String> trackUniqueCombination = new HashSet<>();
    
    for (int i = 0 ; i < arr.length; i++){
      for (int j = arr.length - 1 ; j > i ; j--){
        int sum = -1 * (arr[i] + arr[j]);

        // binary search
        boolean flagSearch = findValue(arr, i+1,j-1, sum);
        if (flagSearch) {
          String tmp = arr[i] + "_" + sum + "_" + arr[j];
          if (!trackUniqueCombination.contains(tmp)){
            trackUniqueCombination.add(tmp);
            List<Integer> triplet = new ArrayList<>();
            triplet.add(arr[i]);
            triplet.add(sum);
            triplet.add(arr[j]);
            triplets.add(triplet);
          }
        }
      }
    }
    
    return triplets;
  }

  public static boolean findValue(int[] arr, int leftIndex, int rightIndex, int valueToSearch){
    int midIndex = leftIndex + (rightIndex - leftIndex)/2;
    
    if (arr[midIndex] == valueToSearch){
      return true;
    }
    if (leftIndex >= rightIndex){
      return false;
    }
    if (valueToSearch >= arr[midIndex]){
      leftIndex = midIndex+1;
    } else{
      rightIndex = midIndex-1;
    }
    System.out.println(leftIndex +"_" +rightIndex +"_"+ valueToSearch);

    return findValue(arr, leftIndex, rightIndex, valueToSearch);
  }

  public static final List<int[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<int[]>() {
    {
      add(new int[] { -3, 0, 1, 2, -1, 1, -2}); // output: [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]
      add(new int[] { -5, 2, -1, -2, 3 }); // output: [[-5, 2, 3], [-2, -1, 3]]
    }
  });

  public static void main(String[] args) {
    // JDK 10 onwards, can use var as local variable types
    for (var rowData : INPUT_DATA) {
      var result = searchTriplets(rowData);
      System.out.println(String.format("Output: %s for input: %s", result, Arrays.toString(rowData)));
    }

  }

}

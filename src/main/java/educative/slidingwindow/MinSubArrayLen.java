package educative.slidingwindow;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MinSubArrayLen {

    public static int minSubArrayLen(int target, int[] arr) {
        int minWindowSize = Integer.MAX_VALUE;

        int sum = 0;
        int lp = 0;
        for(int rp =0 ; rp < arr.length ; rp++){
            sum += arr[rp];
            while(sum>=target) {
//                System.out.println(sum);
                minWindowSize = Math.min(minWindowSize, rp - lp + 1);
                sum -= arr[lp];
                lp++;
            }
        }

        return minWindowSize==Integer.MAX_VALUE?0:minWindowSize;
    }

    public static final Map<Integer[], Integer> INPUT_DATA = Collections.unmodifiableMap(new HashMap<>(){{
        put(new Integer[]{2,3,1,2,4,3}, 7); // output: 2
        put(new Integer[]{1,4,4}, 4);   // output: 1
        put(new Integer[]{1,1,1,1,1,1,1,1}, 11); // output: 0
        put(new Integer[]{1,2,3,4}, 10); // output: 4
        put(new Integer[]{1,2,1,3}, 5); // output: 3

    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        for (var rowData : INPUT_DATA.entrySet()) {

            var result = minSubArrayLen(rowData.getValue(), Arrays.stream(rowData.getKey()).mapToInt(Integer::intValue).toArray());
            System.out.println(String.format("For Input v1: %s op: %s , result is %s ", Arrays.toString(rowData.getKey()), rowData.getValue(), result));
        }
    }
}

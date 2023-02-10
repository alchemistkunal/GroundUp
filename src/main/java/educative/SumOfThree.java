package educative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumOfThree {
    public static boolean findSumOfThree(int[] nums, int target) {
        if(null == nums || nums.length < 3){
            return false;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2 ; i++){

            for(int l = i+1, h = nums.length - 1; l < h ; ) {
                if (target == nums[i] + nums[l] + nums[h]) {
                    return true;
                }
                else if (target < nums[i] + nums[l] + nums[h]) {
                    h--;
                } else{
                    l++;
                }
            }
        }

        return false;
    }

    public static final Map<Integer[], Integer> INPUT_DATA = Collections.unmodifiableMap(new HashMap<>(){{
        put(new Integer[]{1,-1,0}, -1);
        put(new Integer[]{3,7,1,2,8,4,5}, 10);
        put(new Integer[]{3,7,1,2,8,4,5}, 20);
        put(new Integer[]{3,7,1,2,8,4,5}, 21);
        put(new Integer[]{-1,2,1,-4,5,-3}, -8);
        put(new Integer[]{-1,2,1,-4,5,-3}, 0);
        put(new Integer[]{-1,2,1,-4,5,-3}, 7);
    }});

    public static void main(String[] args) {
        // Int to Integer arr
//        int[] data = {1,2,3,4,5,6,7,8,9,10};
        // To boxed array
//        Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );

        // JDK 10 onwards, can use var as local variable types
        System.out.println(INPUT_DATA.size());
        // INPUT_DATA.add(new String[]{"2", "2", "EQUAL_TO"}); -- Collections.unmodifiablelist prohibits this line
        for (var rowData : INPUT_DATA.entrySet()) {

            boolean result = SumOfThree.findSumOfThree(Arrays.stream(rowData.getKey()).mapToInt(Integer::intValue).toArray(), rowData.getValue());
            System.out.println(String.format("For Input v1: %s op: %s , result is %s ", Arrays.toString(rowData.getKey()), rowData.getValue(), result));
        }
//        System.out.println(findSumOfThree(new int[]{3,7,1,2,8,4,5}, 10));
    }
}

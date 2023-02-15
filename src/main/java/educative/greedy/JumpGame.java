package educative.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JumpGame {

    private static boolean recurJump(int[] nums, int jumpFromIndex){
        if(jumpFromIndex >= nums.length)
            return false;
        if (jumpFromIndex == nums.length-1)
            return true;
        int x = nums[jumpFromIndex];
        boolean endReached = false;
//        System.out.println(x);
        while(x>=1 && !endReached ) {
            endReached = recurJump(nums, jumpFromIndex + x--);
        }
        return endReached;
    }
    public static boolean jumpGame(int[] nums) {
        if(nums.length <=1)
            return true;
        return recurJump(nums, 0);
    }


    public static final List<Integer[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>(){{
        add(new Integer[]{2,3,1,1,9});      // output: T
        add(new Integer[]{3,2,1,0,4});      // output: F
        add(new Integer[]{0});      // output: T
        add(new Integer[]{4, 3, 2, 1, 0});      // output: T


    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        for (var rowData : INPUT_DATA.subList(0,4)) {

            var result = jumpGame(Arrays.stream(rowData).mapToInt(Integer::intValue).toArray());
            System.out.println(String.format("For Input v1: %s , result is %s ", Arrays.toString(rowData), result));
        }

    }
}

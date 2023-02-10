package educative.slowpointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircularArrayLoop {

    private static int updateIndexMovement(int index, int[] nums){
        index = (index + nums[index]) % nums.length;
        index = (index < 0) ? nums.length - (-1*index) : index;
        return index;
    }
    public static boolean circularArrayLoop(int[] nums) {

        // Your code will replace this placeholder return statement
        int spIndex, fpIndex;
        for(int i = 0; i < nums.length ; i++){
            // to stop looping of a pointer
            if(Math.abs(nums[i]) == nums.length)
                continue;

            spIndex = i; fpIndex = i;
//            System.out.println(i + " starts");
            int initialDirection = (int) Math.signum(nums[i]);
            boolean foundOppMove;
            do{
                foundOppMove = false;
                spIndex = updateIndexMovement(spIndex, nums);
                foundOppMove = ((int)Math.signum(nums[spIndex]) != initialDirection);
                fpIndex = updateIndexMovement(fpIndex, nums);
                foundOppMove |= ((int)Math.signum(nums[fpIndex]) != initialDirection);
                fpIndex = updateIndexMovement(fpIndex, nums);
                foundOppMove |= ((int)Math.signum(nums[fpIndex]) != initialDirection);
//                System.out.println(spIndex + " " +  fpIndex);
                if(fpIndex == spIndex){
                    return true;
                }
            } while(!foundOppMove && spIndex != i);
        }

        return false;
    }


    public static final List<Integer[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>(){{
        add(new Integer[]{1,3,-2,-4,1});
        add(new Integer[]{2,1,-1,-2});
        add(new Integer[]{3,2,1,1,-4,1});
        add(new Integer[]{1,2,-3,3,4,7,1});
        add(new Integer[]{3,3,1,-1,2});
        add(new Integer[]{-1, -1, -1, -1, -1, -1});
    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        System.out.println(INPUT_DATA.size());

        for (var rowData : INPUT_DATA) {

            boolean result = CircularArrayLoop.circularArrayLoop(Arrays.stream(rowData).mapToInt(Integer::intValue).toArray());
            System.out.println(String.format("For Input v1: %s , result is %s ", Arrays.toString(rowData), result));
        }

    }
}

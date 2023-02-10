package educative.slowpointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HappyNumber {

    private static int sumDigits(int number) {
        int totalSum = 0;
        while (number != 0) {
            int digit = number % 10;
            number = number / 10;
            totalSum += (Math.pow(digit, 2));
        }
        return totalSum;
    }
    /**
     * Using no space -> O(1)
     * Time complexity -> O(log n)
     * @param n
     * @return
     */
    public static boolean isHappyNumberPointers(int n) {
        int slowPointer = n;
        int fastPointer = sumDigits(n);

        while (fastPointer != 1 && slowPointer != fastPointer) {
            slowPointer = sumDigits(slowPointer);
            fastPointer = sumDigits(sumDigits(fastPointer));
        }
        return fastPointer == 1;
    }

    /**
     * Using extra space -> O(n)
     * Time complexity -> O(log n)
     * @param n
     * @return
     */
    public static boolean isHappyNumber(int n) {
        Set<Integer> encounteredVals = new HashSet<>();
        int sumSq= 0;
        do {
            encounteredVals.add(sumSq);
            sumSq = 0;
            do {
                int r = n % 10;
                sumSq += r*r;

                n = n / 10;
            } while (n > 0);
            if (sumSq == 1)
                return true;
            n = sumSq;
        }   while(!encounteredVals.contains(sumSq));
        return false;
    }


    public static final List<Integer> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>(){{
        add(2147483646);
        add(1);
        add(19);
        add(8);
        add(7);
    }});
    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        System.out.println(INPUT_DATA.size());
        // INPUT_DATA.add(new String[]{"2", "2", "EQUAL_TO"}); -- Collections.unmodifiablelist prohibits this line
        for (var rowData : INPUT_DATA) {
            var result = HappyNumber.isHappyNumber(rowData);
            System.out.println(String.format("For Input v1: %s :: result is: %s ", rowData,  result));
        }
    }
}

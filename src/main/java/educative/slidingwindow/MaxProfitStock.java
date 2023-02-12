package educative.slidingwindow;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxProfitStock {

    public static int maxProfit(int[] stockPrices) {
        int maxNum = Integer.MIN_VALUE, maxIndex = 0;
        int minNum = Integer.MAX_VALUE, minIndex = 0;

        for(int i = 0 ; i < stockPrices.length ; i++){
            if(minNum > stockPrices[i]){
                minIndex = i;
                minNum = stockPrices[i];
            }
        }
        for(int i = minIndex + 1 ; i < stockPrices.length ; i++){
            if(maxNum < stockPrices[i]){
                maxIndex = i;
                maxNum = stockPrices[i];
            }
        }
        return (maxNum == Integer.MIN_VALUE || minNum == Integer.MAX_VALUE)? 0 : maxNum - minNum;
    }

    public static final List<Integer[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>(){{
        add(new Integer[]{7,1,5,3,6,4});
//        add(new Integer[]{1,2,4,2,5,7,2,4,9,0,9});
//        add(new Integer[]{7,6,4,3,1});
//        add(new Integer[]{2,6,8,7,8,7,9,4,1,2,4,5,8});
//        add(new Integer[]{1,2});
    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        for (var rowData : INPUT_DATA) {

            var result = maxProfit(Arrays.stream(rowData).mapToInt(Integer::intValue).toArray());
            System.out.println(String.format("For Input v1: %s , result is %s ", Arrays.toString(rowData), result));
        }

    }
}

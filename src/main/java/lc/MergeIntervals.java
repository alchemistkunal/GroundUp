package lc;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        System.out.println(Arrays.deepToString(intervals));
        for(int i = 1 ; i < intervals.length ; i++){
            ValueRange range = ValueRange.of(intervals[i-1][0], intervals[i-1][1]);
            if(range.isValidIntValue(intervals[i][0]) || range.isValidIntValue(intervals[i][1])){
                intervals[i][0] = Math.min(intervals[i-1][0], intervals[i][0]);
                intervals[i][1] = Math.max(intervals[i-1][1], intervals[i][1]);
                // pushing forward the range
                intervals[i-1] = null;
            }
        }

        return Arrays.stream(intervals).filter(Objects::nonNull).toArray(int[][]::new);
    }

    /**
     * [[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]]
     * 86 / 170 testcases passed
     * Output
     * [[4,7]]
     * Expected
     * [[1,3],[4,7]]
     */

    public static final List<Integer[][]> INPUT_DATA =
            Collections.unmodifiableList(new ArrayList<>(){{
                add(
                        new Integer[][]{{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}}
                );  //Output: [[1,3],[4,7]]


            }});

    public static void main(String[] args) {

        MergeIntervals mi = new MergeIntervals();

        for (var rowData : INPUT_DATA) {
            int[][] inputArr = Arrays.stream(rowData)
                    .map(e -> Arrays.stream(e)
                            .mapToInt(Integer::intValue)
                            .toArray())
                    .toArray(int[][]::new);

            var result = mi.merge(inputArr);

            System.out.println(String.format("For Input v1: %s :: result is: %s ", Arrays.deepToString(inputArr),  Arrays.deepToString(result)));
        }

    }
}

package educative.slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindowMaximum {

    // Space O(w), time O(n)
    public static List<Integer> findMaxSlidingWindow(List<Integer> nums, int windowSize) {
        List<Integer> result = new ArrayList<>();
        // Initializing doubly ended queue for storing indices
        Deque<Integer> maxValsIndexInWindowQueue = new ArrayDeque<>();

        if(windowSize > nums.size())
            windowSize = nums.size();

        // Find max of 1st window
        for(int i = 0 ; i < windowSize ; i++){
            // For every element, remove the previous smaller elements from window
            while(!maxValsIndexInWindowQueue.isEmpty() && nums.get(i) >= nums.get(maxValsIndexInWindowQueue.peekLast())) {
                maxValsIndexInWindowQueue.removeLast();
            }
            // store index
            maxValsIndexInWindowQueue.addLast(i);
        }
        // 1st max
        result.add(nums.get(maxValsIndexInWindowQueue.peek()));

        for (int i = windowSize; i<nums.size(); i++) {
            while (!maxValsIndexInWindowQueue.isEmpty() && nums.get(i) >= nums.get(maxValsIndexInWindowQueue.peekLast())) {
                maxValsIndexInWindowQueue.removeLast();
            }
            // Remove first index from the window deque if
            // it doesn't fall in the current window anymore
            if ((!maxValsIndexInWindowQueue.isEmpty()) && maxValsIndexInWindowQueue.peek()<= (i - windowSize))
                maxValsIndexInWindowQueue.removeFirst();
            // Add current element at the back of the queue
            maxValsIndexInWindowQueue.addLast(i);
            result.add(nums.get(maxValsIndexInWindowQueue.peek()));
        }
        return result;
    }

    public static final Map<Integer[], Integer> INPUT_DATA = Collections.unmodifiableMap(new HashMap<>(){{
        put(new Integer[]{1,2,3,4,5,6,7,8,9,10}, 3);
        put(new Integer[]{3,3,3,3,3,3,3,3,3,3}, 4);
        put(new Integer[]{10,6,9,-3,23,-1,34,56,67,-1,-4,-8,-2,9,10,34,67}, 2);
        put(new Integer[]{4,5,6,1,2,3}, 1);
        put(new Integer[]{9,5,3,1,6,3}, 2);
        put(new Integer[]{1,2}, 2);
    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        for (var rowData : INPUT_DATA.entrySet()) {

            var result = SlidingWindowMaximum.findMaxSlidingWindow(Arrays.stream(rowData.getKey()).toList(), rowData.getValue());
            System.out.println(String.format("For Input v1: %s op: %s , result is %s ", Arrays.toString(rowData.getKey()), rowData.getValue(), result));
        }
    }

    /**
     * The naive approach is to iterate over all possible windows and calculate the maximum of each window.
     * For an array of size n and a window of size w, there will be a total of n−w+1 windows.
     * Traversing any given window to find its maximum will cost O(w).
     * This naive approach has a total time complexity of O(n∗w) since we need to traverse through all possible windows.
     * Extra space in terms of creating temporary sublist of size w is used. O(w)
     * @param nums
     * @param windowSize
     * @return
     */
    public static List<Integer> findMaxSlidingWindowNaive(List<Integer> nums, int windowSize) {
        List<Integer> result = new ArrayList<>();
        for(int i = windowSize; i <= nums.size() ; i++){
            result.add(Collections.max(nums.subList(i-windowSize, i)));
        }
        return result;
    }
}

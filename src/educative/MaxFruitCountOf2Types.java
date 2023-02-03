

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxFruitCountOf2Types {

  public static final List<Character[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<Character[]>() {
    {
      add(new Character[] { 'A', 'B', 'C', 'A', 'C' }); // output: 3
      add(new Character[] { 'A', 'B', 'C', 'B', 'B', 'C' }); // output: 5
      add(new Character[] { 'A', 'A', 'B', 'A', 'C', 'C', 'A', 'C', 'B' }); // output: 5

    }
  });

  public static int findLength(Character[] arr) {
    var currentVar1 = arr[0];
    var previousChar = currentVar1;
    int currentCount = 1;

    var currentVar2 = '_';
    int maxCount = -1;

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] != currentVar1 && arr[i] != currentVar2) {
        if (currentVar2 != '_') {
          currentVar1 = previousChar;
          if (maxCount < currentCount) {
            maxCount = currentCount;
          }
          currentCount = 1; // ++ below on changing v2
        }
        currentVar2 = arr[i];
      }
      previousChar = arr[i];
      currentCount++;
    }

    maxCount = maxCount == -1 || maxCount < currentCount ? currentCount : maxCount;

    return maxCount;
  }

  public static void main(String[] args) {
    // JDK 10 onwards, can use var as local variable types
    for (var rowData : INPUT_DATA) {
      int result = findLength(rowData);
      System.out.println(String.format("Output: %s for input: %s", result, Arrays.toString(rowData)));
    }

  }

}

package educative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Permutation in a String (hard)
 * 
 * Given a string and a pattern, find out if the string contains any permutation
 * of the pattern.
 * 
 * Permutation is defined as the re-arranging of the characters of the string.
 * For example, “abc” has the following six permutations:
 * 
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * If a string has ‘n’ distinct characters, it will have n!
 * n!
 * permutations.
 * 
 * Example 1:
 * 
 * Input: String="oidbcaf", Pattern="abc"
 * Output: true
 * Explanation: The string contains "bca" which is a permutation of the given
 * pattern.
 * Example 2:
 * 
 * Input: String="odicf", Pattern="dc"
 * Output: false
 * Explanation: No permutation of the pattern is present in the given string as
 * a substring.
 * Example 3:
 * 
 * Input: String="bcdxabcdy", Pattern="bcdyabcdx"
 * Output: true
 * Explanation: Both the string and the pattern are a permutation of each other.
 * Example 4:
 * 
 * Input: String="aaacb", Pattern="abc"
 * Output: true
 * Explanation: The string contains "acb" which is a permutation of the given
 * pattern.
 * 
 */

public class StringPermutation {

    public static final List<String[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<String[]>() {
        {
          add(new String[] { "oidbcaf","abc" }); // output: true
          add(new String[] { "odicf","dc" }); // output: false
          add(new String[] { "bcdxabcdy","bcdyabcdx" }); // output: true
    
        }
      });
    
    public static boolean findPermutation(String str, String pattern) {
        // char[] inputArr = str.toCharArray();
        // char[] patternArr = pattern.toCharArray();

        // Arrays.sort(inputArr);
        // Arrays.sort(patternArr);
        // System.out.println(Arrays.toString(inputArr));
        // System.out.println(Arrays.toString(patternArr));
        // System.out.println( new String(inputArr).indexOf(new String(pattern)));
        // return new String(inputArr).indexOf(new String(pattern)) != -1;

        var patternSet = createArraylist(pattern);
        var flagFoundOne = false;
        for (char inputChar: str.toCharArray()) {
            if (patternSet.contains(inputChar)){
                // System.out.print("found " + inputChar);
                flagFoundOne = true;
                patternSet.remove((Character)inputChar);
                // System.out.println(", remaining " + patternSet);
                if (patternSet.size() == 0){
                    return true;
                }
            } else {
                if(flagFoundOne){
                    // reset
                    flagFoundOne = false;
                    patternSet = createArraylist(pattern);
                }

            }
        }

        return false;
    }

    private static ArrayList<Character> createArraylist(String pattern){
        return new ArrayList<>(pattern.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        // JDK 10 onwards, can use var as local variable types
        for (var rowData : INPUT_DATA) {
          boolean result = findPermutation(rowData[0], rowData[1]);
          System.out.println(String.format("Output: %s for input: %s", result, Arrays.toString(rowData)));
        }
    
      }

}

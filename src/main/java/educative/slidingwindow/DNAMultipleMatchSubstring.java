package educative.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string s that represents a DNA sequence, and a number k, return all the contiguous sequences (substrings)
 * of length k that occur more than once in the string.
 * The order of the returned subsequences does not matter.
 * If no repeated subsequence is found, the function should return an empty set.
 */
public class DNAMultipleMatchSubstring {

    public static List<String> findRepeatedSequences(String s, int k) {
        Map<String, Integer> subseqCount = new HashMap<>();
        if(k > s.length()){
            k = s.length();
        }
        for(int i = 0 ; i < s.length() - k; i++){
            String subSeq = s.substring(i, i+k);
            subseqCount.put(subSeq, subseqCount.getOrDefault(subSeq,0) + 1);
        }

        return subseqCount.entrySet().stream().filter(e -> e.getValue()>1).map(e -> e.getKey()).collect(Collectors.toList());
    }

    public static final Map<String, Integer> INPUT_DATA = Collections.unmodifiableMap(new HashMap<>(){{
        put("AAAAACCCCCAAAAACCCCCC", 8);
        put("GGGGGGGGGGGGGGGGGGGGGGGGG", 12);
        put("TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT", 10);
        put("AAAAAACCCCCCCAAAAAAAACCCCCCCTG", 10);
        put("ATATATATATATATAT", 6);
    }});

    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        for (var rowData : INPUT_DATA.entrySet()) {

            var result = DNAMultipleMatchSubstring.findRepeatedSequences(rowData.getKey(), rowData.getValue());
            System.out.println(String.format("For Input v1: %s op: %s , result is %s ", rowData.getKey(), rowData.getValue(), result));
        }
    }

}

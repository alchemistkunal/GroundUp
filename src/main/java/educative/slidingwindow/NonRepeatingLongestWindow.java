package educative.slidingwindow;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonRepeatingLongestWindow {
    public static int findLongestSubstring(String inputString) {
        if(StringUtils.isEmpty(inputString)){
            return 0;
        }
        Set<Character> seenChar = new HashSet<>();
        int lp = 0, rp = 0;
        int start = 0, end = 0, resultLen = -1;
        while(rp < inputString.length()){
            char currChar = inputString.charAt(rp);
            if(seenChar.contains(currChar)){
                resultLen = Math.max(resultLen, seenChar.size());

                char encounteredChar;
                while(start < rp && (encounteredChar=inputString.charAt(start++)) != currChar){
                    seenChar.remove(encounteredChar);
                    System.out.println("remove " + encounteredChar);
                }
                System.out.println("clear, max= "+resultLen);
            } else {
                seenChar.add(currChar);
                System.out.println("add " + currChar);
            }
            rp++;
        }
        // last
        resultLen = Math.max(resultLen, seenChar.size());

        return resultLen==-1 ? inputString.length() : resultLen;
    }



    public static final List<String[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>() {
        {
            add(new String[] { "abcdbea"}); // output: 5
            add(new String[] { "aba" }); // output: 2
            add(new String[] { "abccabcabcc" }); // output: 3
            add(new String[] { "" }); // output: 0
//            add(new String[] { "ABDFGDCKAB","ABCD" }); // output:
        }
    });

    public static void main(String[] args) {
        for (var rowData : INPUT_DATA) {
            var result = findLongestSubstring(rowData[0]);
            System.out.println(String.format("Output: %s for input: %s", result, Arrays.toString(rowData)));
        }

    }
}

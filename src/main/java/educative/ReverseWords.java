package educative;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseWords {


    public static String reverseWords(String sentence) {
        if(StringUtils.isEmpty(sentence))
            return sentence;

        sentence = StringUtils.reverse(sentence.trim());
        sentence = sentence.replaceAll(" +", " ");
        char[] sentenceArr = sentence.toCharArray();
        int l = 0;

        for (int i = 0 ; i < sentenceArr.length ; i++){
            if(i == sentenceArr.length - 1 || sentenceArr[i + 1] == ' '){
                ArrayUtils.reverse(sentenceArr, l, i + 1);
                l = i + 2;
            }
        }

        return String.valueOf(sentenceArr);
    }

    public static final List<String> INPUT_DATA = Collections.unmodifiableList(new ArrayList<>(){{
        add("We love Python");
        add("To be or not to be");
        add("You are amazing");
        add("Hello     World");
        add("Hey");
    }});
    public static void main(String[] args) {

        // JDK 10 onwards, can use var as local variable types
        System.out.println(INPUT_DATA.size());
        // INPUT_DATA.add(new String[]{"2", "2", "EQUAL_TO"}); -- Collections.unmodifiablelist prohibits this line
        for (var rowData : INPUT_DATA) {

            var result = ReverseWords.reverseWords(rowData);
            System.out.println(String.format("For Input v1: %s :: result is: %s ", rowData,  result));
        }
    }
}

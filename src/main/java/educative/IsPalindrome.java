package educative;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IsPalindrome{

    public static final List<String[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<String[]>(){{
        add(new String[]{"kayak"});
        add(new String[]{"hello"});
        add(new String[]{"RACEACAR"});
        add(new String[]{"A"});
        add(new String[]{"ABCDABCD"});
        add(new String[]{"DCBAABCD"});
        add(new String[]{"ABCBA"});

    }});

    public static boolean isPalindrome(String s) {
        if(null == s)
            return false;
        int lp, rp;
        for (lp=0, rp = s.length() - 1 ; lp < rp ; lp++,rp--){
            if (s.charAt(lp) != s.charAt(rp)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("DCBAABCD"));

        // JDK 10 onwards, can use var as local variable types
        System.out.println(INPUT_DATA.size());
        // INPUT_DATA.add(new String[]{"2", "2", "EQUAL_TO"}); -- Collections.unmodifiablelist prohibits this line
        for (var rowData : INPUT_DATA) {
            IsPalindrome obj = new IsPalindrome();
            boolean result = obj.isPalindrome(rowData[0]);
            System.out.println(String.format("For Input v1: %s  result is %s ", rowData[0],  result));
        }
    }

}
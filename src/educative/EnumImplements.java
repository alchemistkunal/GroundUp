// package educative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumImplements {
    
    public static final List<String[]> INPUT_DATA = Collections.unmodifiableList(new ArrayList<String[]>(){{
        add(new String[]{"12", "52", "GREATER_THAN"});
        add(new String[]{"100", "52", "GREATER_THAN"});
        add(new String[]{"2", "2", "EQUAL_TO"});

    }});

    interface ComparatorFunctionality {
        boolean calculate(int a, int b);
    }
    
    // enums can implement interfaces, but not classes as enum itself calls ENUM super class behind
    // Would lead to Diamond Problem
    enum ComparatorOperator implements ComparatorFunctionality {
        GREATER_THAN {
            @Override
            public boolean calculate(int a, int b){
                return a > b;
            }
        },
        LESS_THAN {
            @Override
            public boolean calculate(int a, int b){
                return a < b;
            }
        },
        EQUAL_TO {
            @Override
            public boolean calculate(int a, int b){
                return a == b;
            }
        }
    }

    public static void main(String[] args) {
        // JDK 10 onwards, can use var as local variable types
        System.out.println(INPUT_DATA.size());
        // INPUT_DATA.add(new String[]{"2", "2", "EQUAL_TO"}); -- Collections.unmodifiablelist prohibits this line
        for (var rowData : INPUT_DATA) {
            ComparatorOperator opeerator = ComparatorOperator.valueOf(rowData[2]);
            boolean result = opeerator.calculate(Integer.parseInt(rowData[0]), Integer.parseInt(rowData[1]));
            System.out.println(String.format("For Input v1: %s op: %s v2: %s, result is %s ", rowData[0], rowData[2], rowData[1], result));
        }
    }


}

import java.util.*;
import java.util.stream.*;

/**
 * You are given a sorted list of distinct integers from 0 to 99, for instance [0, 1, 2, 50, 52, 75].
 * Your task is to produce a string that describes numbers missing from the list; in this case "3-49,51,53-74,76-99".
 * The items should be sorted in ascending order and separated by commas.
 * When a gap spans only one number, the item is the number itself;
 * when a gap is longer, the item comprises the start and the end of the gap, joined with a minus sign. * 
 */
public class MissingNumbersFromList {

    static final int LIMIT = 100;
    public static void main(String[] args) {        
        test();
    }

    static void test() {
        String missing = compute(new int[]{5});
        assert(missing.equals("0-4,6-99"));
 
        missing = compute(new int[]{0});
        assert(missing.equals("1-99"));
 
        missing = compute(new int[]{99});
        assert(missing.equals("0-98"));
 
        missing = compute(new int[]{3, 5});
        assert(missing.equals("0-2,4,6-99"));
 
        missing = compute(new int[]{3, 5, 10, 11, 13, 17});
        assert(missing.equals("0-2,4,6-9,12,14-16,18-99"));
        
        missing = compute(new int[]{0, 1, 2, 50, 52, 75, 91, 93});
        assert(missing.equals("3-49,51,53-74,76-90,92,94-99"));

        missing = compute(new int[]{0, 1, 2, 50, 52, 75});
        assert(missing.equals("3-49,51,53-74,76-99"));
    }

    static int getEndIdx(int[] array, int idx) {
        for (int i = idx; i < array.length; i++) {
            if (array[i] == 1) {
                return i-1;
            } 
        }

        return array.length-1;
    }
    
    static String compute(int[] array) {
        
        int[] computeArray = new int[LIMIT];
        
        for (int elem : array) {
            computeArray[elem] = 1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < LIMIT; i++) {
            
            if (computeArray[i] == 0) {
                int startIdx = i;
                int endIdx = getEndIdx(computeArray, startIdx+1);
                sb.append(startIdx);
                if (startIdx != endIdx) {
                    sb.append("-");
                    sb.append(endIdx);
                }

                if (endIdx < LIMIT-2) 
                    sb.append(",");

                i = endIdx;
            }
        }
        
        return sb.toString();
    }
}

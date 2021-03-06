import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MakingAnagrams {
    
    static int computeLeastNumberOfRemovals(String first, String second) {
        Map<Character, Integer> firstMap = createFrequencyMap(first);
        Map<Character, Integer> secondMap = createFrequencyMap(second);

        int firstNum = firstMap.entrySet()
            .stream()
            .filter(x -> !secondMap.containsKey(x.getKey()))
            .mapToInt(x -> x.getValue())
            .sum();

        int secondNum = secondMap.entrySet()
            .stream()
            .filter(x -> !firstMap.containsKey(x.getKey()))
            .mapToInt(x -> x.getValue())
            .sum();

        int commonNum = firstMap.entrySet()
            .stream()
            .filter(x -> secondMap.containsKey(x.getKey()))
            .mapToInt(x -> Math.abs(x.getValue() - secondMap.get(x.getKey())))
            .sum();
        
        return firstNum + secondNum + commonNum;
    }

    private static Map<Character, Integer> createFrequencyMap(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.compute(c, (k, v) -> v == null ? 1 : v+1);
        }

        return freqMap;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(computeLeastNumberOfRemovals(a, b));
    }
}

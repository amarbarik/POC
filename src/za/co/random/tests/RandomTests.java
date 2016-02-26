package za.co.random.tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by F4742443 on 2015/09/22.
 */
public class RandomTests {

    public static int indexOfLongestRun(String str) {
        char[] chars = str.toCharArray();
        int sq = 0;
        for(int i =0;  i < chars.length ; i++) {
            if(chars[i] == chars[i + 1]) {
                sq = sq + 1;
            } else {
                sq = 0;
            }
        }

        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static int numberOfWays(int n) {
        int one = 1;
        int two = 2;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return numberOfWays(n - 1) + numberOfWays(n - 2);
        }
    }


    public static boolean areAnagrams(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        return Arrays.equals(aChars, bChars);
    }

    public static int[] findTwoSum1(int[] list, int sum) {
        if (list == null || list.length < 2) return null;
        //map values to indexes
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            int needed = sum - list[i];
            Integer integer = indexMap.get(needed);
            System.out.println(integer);
            if (integer != null) {
                return new int[]{i, integer};
            }

            indexMap.put(list[i], i);
        }

        //none found
        return null;
    }

    public static int[] findTwoSum3(int[] list, int sum) {
        if (list == null || list.length < 2) return null;
        //map values to indexes
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            indexMap.put(list[i], i);
        }

        for (int i = 0; i < list.length; i++) {
            int needed = sum - list[i];
            if (indexMap.get(needed) != null) {
                return new int[]{i, indexMap.get(needed)};
            }
        }

        //none found
        return null;
    }

    public static int[] findTwoSum(int[] list, int sum) {
        if (list == null || list.length < 2) return null;
        int k = 0;
        int[] indices = new int[022];
        if (list.length > 1) {
            for (int i = 0; i < list.length; i++) {
                for (int j = 0; j < list.length; j++) {
                    if ((list[i] + list[j]) == sum) {
                        indices[k] = i;
                        indices[k + 1] = j;
                        k = k + 2;
                    }
                }
            }
        }
        return indices;
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static boolean isPalindrome(String str) {
        String reverse = "";
        String replace = str.replace(" ", "");
        replace = replace.replace(".", "");
        System.out.println(replace);
        char[] chars = replace.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse = reverse + chars[i];
        }

        reverse = "";
        char[] chR = str.toCharArray();
        for (int i = chR.length - 1; i >= 0; i--) {
            if (Character.isLetter(chR[i])) {
                reverse = reverse + chR[i];
            }
        }
        System.out.println("---" + reverse);
        return replace.equalsIgnoreCase(reverse);

        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }


    public static void main(String[] args) {
        //System.out.println(areAnagrams("momdad", "dadmom"));
        //System.out.println(isPalindrome("Noel sees Leon."));
        // int[] indices = findTwoSum(new int[] { 1, 3, 5, 7, 9 }, 12);
        //System.out.println(indices[0] + " " + indices[1]);
        //System.out.println(indices[2] + " " + indices[3]);
        System.out.println(numberOfWays(3));
    }
}

package za.co.solidtech;

import java.util.*;

/**
 * Created by F4742443 on 2016/02/16.
 */
public class AssignmentThree {

    public static List<Integer> findNumbersWithCount(int[] listOfNumbers, int countOfOccurrenceThreshold)
    {
        List<Integer> occurrenceList = new ArrayList<>();
        if(listOfNumbers.length > 0 && countOfOccurrenceThreshold > 0) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int number : listOfNumbers) {
                if (map.containsKey(number)) {
                    int occurrence = map.get(number);
                    occurrence++;
                    map.put(number, occurrence);
                } else {
                    map.put(number, 1);
                }
            }

            for (Integer key : map.keySet()) {
                int occurrence = map.get(key);
                if (occurrence >= countOfOccurrenceThreshold) {
                    occurrenceList.add(occurrence);
                }
            }
        }
        return occurrenceList;
    }

    public static void main(String[] args)
    {
        List<Integer> x = findNumbersWithCount(new int[] { 5, 4, 3, 2, 1, 5, 4, 3, 2, 5, 4, 3, 5, 4, 5 }, 2);
        System.out.println(x);

        System.out.println(findNumbersWithCount(new int[]{}, 2));
    }
}

package za.co.solidtech;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by F4742443 on 2016/02/16.
 */
public class AssignmentOne {

    public static void main(String[] args) {
        String items[] = { "fig",  "apple", "date", "banana", "egg", "carrot" };
        System.out.println("Original List: " + Arrays.toString(items));

        items = sortItems(items, false);
        System.out.println("Ascending List: " + Arrays.toString(items));

        items = sortItems(items, true);
        System.out.println("Descending List: " + Arrays.toString(items));
    }

    public static String[] sortItems(String items[], boolean inReverse) {

        // DO SOMETHING TO SORT THE LIST
        if(items.length > 0) {
            if (inReverse) {
                Arrays.sort(items, Collections.reverseOrder());
                return items;
            } else {
                Arrays.sort(items);
                return items;
            }
        } else {
            return new String[]{""};
        }
    }
}

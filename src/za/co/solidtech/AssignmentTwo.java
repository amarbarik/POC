package za.co.solidtech;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by F4742443 on 2016/02/16.
 */
public class AssignmentTwo {

    public static void main(String[] args) {
        System.out.println(wordCounter("The big word we need to find Big." +
                " with Big values", "Big"));
    }

    public static int wordCounter(String inputString, String wordToCount) {

        // To be implemented by you!
        inputString = inputString.toLowerCase();
        wordToCount = wordToCount.toLowerCase();
        int count = 0;
        Pattern p = Pattern.compile(wordToCount);
        Matcher m = p.matcher(inputString);
        while (m.find()) {
            count++;
        }
        System.out.println(count);
        return count;
    }
}

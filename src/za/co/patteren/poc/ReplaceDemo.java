package za.co.patteren.poc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class ReplaceDemo {

    private static String REGEX = "dog";
    private static String INPUT = "The dog says meow. All dogs say meow.";
    private static String REPLACE = "cat";

    public static void main(String[] args) {

        System.out.println("Input: " + INPUT);
        System.out.println("Replaceable word \"" + REGEX + "\" with \"" + REPLACE + "\"");
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        INPUT = m.replaceAll(REPLACE);
        System.out.println("Result after replacement: " +INPUT);
    }
}

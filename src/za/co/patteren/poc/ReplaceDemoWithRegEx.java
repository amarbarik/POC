package za.co.patteren.poc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class ReplaceDemoWithRegEx {

    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoob";
    private static String REPLACE = "-";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);
    }

}

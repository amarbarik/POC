package za.co.test.java7;

import java.io.Console;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class ConsoleTest {
    public static void main(String[] args) {
        Console cons =  System.console();

        Set<String> set =  new LinkedHashSet<>();
        set.add("3");
        set.add("1");
        set.add("3");
        set.add("2");
        set.add("3");
        set.add("1");
        for (String str : set) {
            System.out.println(str + "-");
        }
    }
}

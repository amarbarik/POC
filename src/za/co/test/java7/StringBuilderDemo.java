package za.co.test.java7;

import java.util.Scanner;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder str =  new StringBuilder();
        for(String arg : args) {
            if(str.indexOf(arg) < 1 )
                str.append(arg + " ");
        }
        System.out.println(str.toString());
        Scanner sc =  new Scanner(str.toString());
        while (sc.hasNext()) {
            if(sc.hasNext())
                System.out.println(sc.nextInt() + " ");
            else {
                sc.next();
            }
        }
    }
}

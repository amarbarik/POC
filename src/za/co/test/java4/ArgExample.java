package za.co.test.java4;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class ArgExample {
    static String method(boolean b) {
        return b ? "True" : "False";
    }
    public static void main (String[] s) {
        boolean b = ((false ? false : true) ? false : true) ? false : true;
        System.out.print(method(b));
    }
}

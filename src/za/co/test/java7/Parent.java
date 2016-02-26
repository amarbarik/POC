package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class Parent {
    protected static int count =0;
    public Parent() {
        count++;
    }
    static int getCount() {
        return count;
    }
}

package za.co.test.java4;

/**
 * Created by F4742443 on 2016/02/22.
 */
interface A {
    int x= 5;
}
interface B {
    int x= 10, y = 15;
}
public class C implements A,B {
    public static void main(String[] args) {
        //System.out.println( x + " " + y);
    }
}

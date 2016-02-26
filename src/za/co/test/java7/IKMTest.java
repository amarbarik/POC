package za.co.test.java7;

import java.text.DecimalFormat;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class IKMTest {
    void display() {
        System.out.println("data " + data);
    }
    int data;

    public static void main(String[] args) {
        int x = 0;
        IKMTest k = new IKMTest();
        k.display();

        double n = 98765.4321;
        System.out.println(new DecimalFormat().format(n));
        System.out.println(new DecimalFormat("0.0").format(n));
        System.out.println(n);
        System.out.println(n/98765.00);

    }
}

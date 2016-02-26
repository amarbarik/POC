package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class CollectionTest {

    static int n2 = getValue();
    static int n1 = 10;
    static int getValue() {
        return n1;
    }
    static int doSum() {
        return n1 + n2;
    }
    static int doMinus() {
        return n1-n2;
    }

    public static void main(String[] args) {
        System.out.println("n1 + n2 " + doSum());
        System.out.println("n1-n2 " + doMinus());

    }
}

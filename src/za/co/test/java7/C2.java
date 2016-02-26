package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
interface I1 {
    String name = "N1";
    String s1= "S1";
}

interface I2 extends I1 {
    String name = "N2";
}
public class C2 implements I2{
    public static void main(String[] args) {
        System.out.println(I2.name + " ");
        System.out.println(I2.s1 + "");
        System.out.println(((I1)new C2()).name);
    }
}

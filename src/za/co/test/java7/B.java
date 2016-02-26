package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
class A {
    public void m1() {
        System.out.println("A.m1, ");
    }
    protected void m2() {
        System.out.println("A.m2, ");
    }
    private void m3() {
        System.out.println("A.m3, ");
    }
    void m4() {
        System.out.println("A.m4, ");
    }
}
public class B {
    public static void main(String[] args) {
        A a = new A();
        a.m1();
        a.m2();
       // a.m3();
        a.m4();
    }
}

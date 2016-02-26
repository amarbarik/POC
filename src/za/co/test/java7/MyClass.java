package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class MyClass {

    String objId;
    MyClass conIntant;
    public MyClass(String idValue) {
        objId = idValue;
    }
    public MyClass(String idValue, MyClass in) {
        objId = idValue;
        conIntant = in;
    }

    public static void main(String[] args) {
        MyClass m1 = new MyClass("One");
        MyClass m2 = new MyClass("2", m1);
        MyClass m3 = new MyClass("3", m2);
        MyClass m4 = new MyClass("4", m2);
        m1=null;
        m2=null;
        m3=null;
        m4=null;

        String s = new Integer(1).toString();


    }

}

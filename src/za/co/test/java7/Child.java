package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
class Parent2 {
    protected static int count = 0;
    public Parent2(){
        count++;
    }
    static int getCount(){
        return count;
    }
}
public class Child extends Parent2 {
    public Child() {
        count++;
    }

    public static void main(String[] args) {
        System.out.println("Count =" + getCount());
        Child c = new Child();
        System.out.println("Count = " + getCount());
        Parent2 p = new Child();
    }
}

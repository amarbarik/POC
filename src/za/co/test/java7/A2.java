package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
class A2 {
    static int to = 10;
    public void call() {
        int to = 5;
        System.out.println(this.to);
    }

    public static void main(String[] args) {
        A2 a = new A2();
        a.call();
    }
}

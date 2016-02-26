package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class DBDemo {
    public static void main(String[] args) {
        Integer before = new Integer(25);
        Integer after = ++before == 26 ? 5 : new Integer(10);
       // System.out.println(after.intValue() - before.intValue());
        int x = -1;
        x = x >>> 32;
        System.out.println(x);
        DBDemo d1 = new DBDemo();
        DBDemo d2 = new DBDemo();
        System.out.println(d1.equals(d2));

    }

   // @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

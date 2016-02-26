package za.co.random.tests;

import java.util.TreeSet;

/**
 * Created by F4742443 on 2016/01/12.
 */
public class MyClass implements Comparable {
    public static void main(String arguments[]) {
        new MyClass().amethod(arguments);
        //byte a;
        System.out.println(Byte.MAX_VALUE + "" + Byte.MIN_VALUE);
    }
    public void amethod(String[] arguments) {
        System.out.println(arguments);
        //System.out.println(arguments[1]);
        TreeSet<MyClass> st =  new TreeSet<MyClass>();
        st.add(new MyClass());st.add(new MyClass());st.add(new MyClass());
        System.out.println(st.first());
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

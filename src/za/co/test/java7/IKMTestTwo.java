package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class IKMTestTwo {

    public static void main(String[] args) {
        Double d = 1.0;
        if(d instanceof Number)
            d = d++;
        boolean cond = (d==2) ? true : false;
        if(cond)
            System.out.println("ac1");
        double e = 1.0;
        if((Double)e instanceof Double | d++ == e++)
            d+=d;
        boolean cond2 = (d>2) ? true : false;
        if(cond2)
            System.out.println("ac2");
        boolean cond3 = cond ^ cond2;
        if(cond3)
            System.out.println("act3");
    }
}

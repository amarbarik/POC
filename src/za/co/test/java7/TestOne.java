package za.co.test.java7;

import java.util.Calendar;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class TestOne {

    public static void main(String[] args) {
        System.out.println("Test One Two");

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.AUGUST, 30);
        cal.roll(Calendar.MONTH, 7);
        System.out.println(" " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.YEAR)) ;
        cal.add(Calendar.MONTH, 11);
        System.out.println(" " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.YEAR)) ;


    }
}

package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */


public class TestSeven {
    public static void main(String[] args) {
        int x=5, y=0;
        try{
            try{
                System.out.println(x);
                System.out.println(x/y);
                System.out.println(y);
            }catch (ArithmeticException ex) {
                System.out.println("Inner catch1");
                throw  ex;
            } catch (RuntimeException ex) {
                System.out.println("catch 2");
                throw ex;
            } finally {
                System.out.println("Finnally inner");
            }
        }catch (Exception e) {
            System.out.println("outer catch");
        }
    }
}



package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class TestNine {

    public static void main(String[] args) {
        try{
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            System.out.println("Answer " + (n1/n2));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(e.getClass());
        } catch (NullPointerException e) {
            System.out.println(e.getClass());
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}

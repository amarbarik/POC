package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class TestTwo {
    private static int count;
    static {
        System.out.println("1 ");
        count = 10;
    }
    private int[] data;
    {
        System.out.println("2");
        data = new int[count];
        for(int i =0; i<count; i++){
            data[i] =  i;
        }
    }

    public static void main(String[] args) {
        System.out.println("count " + count);
        System.out.println("before 1st");
        TestTwo t =  new TestTwo();
        System.out.println("before 2");
        TestTwo t2 =  new TestTwo();
    }
}

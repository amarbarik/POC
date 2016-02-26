package za.co.test.java7;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class TestEight {
    public static void main(String[] args) {
        int x = 0;
        if(x>0)
            System.out.println("one");
            System.out.println("two");
        System.out.println("---------------------");
        int a=0,b=0;
        b= a+1;
        System.out.println("a= "+ a + "b= "+ b);
        b= a++;
        System.out.println("a= "+ a + "b= "+ b);
        b= ++a;
        System.out.println("a= "+ a + "b= "+ b);
        System.out.println("---------------------");

        int j=0;
        int vv[] = {2,4};
        do for(int i:vv)
            System.out.println(i+" ");
        while (j++ < 1);

    }
}

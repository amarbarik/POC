package za.co.format.poc;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class RootWithFormat {
    public static void main(String[] args) {
        int i = 2;
        double r = Math.sqrt(i);

        System.out.format("The square root of %d is %f.%n", i, r);
    }
}

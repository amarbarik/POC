package za.co.comparable.poc;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class Company {
    private int num_of_employess;
    private String name;
    public Company(String name, int num_of_employess) {
        this.name = name;
        this.num_of_employess = num_of_employess;
    }

    public int getNumOfEmployess() {
        return this.num_of_employess;
    }

    public String getName() {
        return this.name;
    }
}

package za.co.comparable.poc;

import java.util.Comparator;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class SortCompanies implements Comparator {

    public int compare(Company comp1, Company comp2) {
        if(comp1.getNumOfEmployess()== comp2.getNumOfEmployess())
            return 0;
        else
            return comp1.getNumOfEmployess() > comp2.getNumOfEmployess() ? 1 : -1;
    }

    public static void main(String[] args) {
        Company comp1 = new Company("Company1", 20);
        Company comp2 = new Company("Company2", 15);


        SortCompanies sortCmp = new SortCompanies();

        int retval = sortCmp.compare(comp1, comp2);
        switch(retval) {
            case -1: {
                System.out.println("The " + comp2.getName() + " is bigger!");
                break;
            }
            case 1: {
                System.out.println("The " + comp1.getName() + " is bigger!");
                break;
            }
            default:
                System.out.println("The two companies are of the same size!");
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        return compare((Company)o1, (Company)o2);
    }
}

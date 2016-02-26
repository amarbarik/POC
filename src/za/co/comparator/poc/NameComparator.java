package za.co.comparator.poc;

import java.util.Comparator;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class NameComparator implements Comparator {

    public int compare(Student o1, Student o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();

        // ascending order (descending order would be: name2.compareTo(name1))
        return name1.compareTo(name2);
    }

    @Override
    public int compare(Object o1, Object o2) {
        return compare((Student)o1,(Student)o2);
    }
}

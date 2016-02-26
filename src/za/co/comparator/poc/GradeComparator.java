package za.co.comparator.poc;

import java.util.Comparator;

/**
 * Created by F4742443 on 2016/02/25.
 */

public class GradeComparator implements Comparator {

    public int compare(Student o1, Student o2) {

        // descending order (ascending order would be:
        // o1.getGrade()-o2.getGrade())
        return o2.getGrade() - o1.getGrade();
    }

    @Override
    public int compare(Object o1, Object o2) {
        return compare((Student) o1, (Student)o2);
    }
}

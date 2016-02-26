package za.co.comparator.poc;

import java.util.Arrays;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class MainComparatorExample {

    public static void main(String args[]) {
        Student student[] = new Student[3];

        student[0] = new Student();
        student[0].setName("Nick");
        student[0].setGrade(19);

        student[1] = new Student();
        student[1].setName("Helen");
        student[1].setGrade(12);

        student[2] = new Student();
        student[2].setName("Ross");
        student[2].setGrade(16);

        System.out.println("Order of students before sorting is: ");

        for (Student aStudent : student) {
            System.out.println(aStudent.getName() + "\t"
                    + aStudent.getGrade());
        }

        Arrays.sort(student, new GradeComparator());
        System.out
                .println("Order of students after sorting by student grade is");

        for (Student aStudent : student) {
            System.out.println(aStudent.getName() + "\t"
                    + aStudent.getGrade());
        }

        Arrays.sort(student, new NameComparator());
        System.out
                .println("Order of students after sorting by student name is");

        for (Student aStudent : student) {
            System.out.println(aStudent.getName() + "\t"
                    + aStudent.getGrade());
        }
    }

}

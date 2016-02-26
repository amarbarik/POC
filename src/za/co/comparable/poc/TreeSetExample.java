package za.co.comparable.poc;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class TreeSetExample {

    private static final int SIZE = 20;

    public static void main(String[] args) {
        TreeSet persons = new TreeSet();

        // Create a number of persons with random age.
        Random rand = new Random();

        for (int i = 1; i <= SIZE; ++i) {
            int randAge = rand.nextInt(50) + 1;
            persons.add(new Person("Person" + i, randAge));
        }

        // Print all persons.
        Iterator iter = persons.iterator();
        while(iter.hasNext())
            System.out.println(iter.next().toString());
    }
}

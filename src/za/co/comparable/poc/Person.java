package za.co.comparable.poc;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class Person implements Comparable {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                +" Age: " + age;
    }

    //first >  second ? 1 (ascending)
    //second > first ? 1 (descending)
    public int compareTo(Person per) {
        if(this.age == per.age)
            return 0;
        else
            return this.age > per.age ? 1 : -1;
    }

    public static void main(String[] args) {
        Person e1 = new Person("Adam", 45);
        Person e2 = new Person("Steve", 60);

        int retval = e1.compareTo(e2);
        switch(retval) {
            case -1: {
                System.out.println("The " + e2.getName() + " is older!");
                break;
            }
            case 1: {
                System.out.println("The " + e1.getName() + " is older!");
                break;
            }
            default:
                System.out.println("The two persons are of the same age!");
        }
    }

    @Override
    public int compareTo(Object o) {
        return compareTo((Person)o);
    }
}

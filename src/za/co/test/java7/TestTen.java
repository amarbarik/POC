package za.co.test.java7;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class TestTen {

    public static void main(String[] args) {
        SortedSet<Element> s = new TreeSet<>();
        s.add(new Element(15));
        s.add(new Element(10));
        s.add(new Element(25));
        s.add(new Element(10));
        System.out.println(s.first() + " " + s.size());


    }
}
class Element implements Comparable {
    int id;
    Element(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        Element e =  (Element)o;
        return this.id - e.id;
    }
    public String toString() {
        return " " + this.id;
    }
}

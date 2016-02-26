package za.co.random.poc;

public class InnerExample {

    public static void main(String[] args) {
        Outer o1 = new Outer();

        // Inner cannot be resolved to a type
        // Inner i1=new Inner();//Compile time error

        // Type mismatch: cannot convert from Outer.Inner to Outer
        // Outer i1=o1.new Inner();//Compile time error

        Object obj1 = o1.new Inner();
        Object obj2 = new Outer().new Inner();

        System.out.println("o1 class	" + o1.getClass());
        System.out.println("obj1 class	" + obj1.getClass());
        System.out.println("obj2 class	" + obj2.getClass());

        Outer.Inner i2 = o1.new Inner();
        Outer.Inner i3 = new Outer().new Inner();

        Outer.InnerStatic innerStatic = new Outer.InnerStatic();
        innerStatic.checkMessage();
    }

    public void testMethod() {

    }

}

class Outer {
    static String test;
    String str = "Outer class field";

    Outer() {
        new Inner();
    }

    class Inner {// class defined within the body of Outer
        // The field str cannot be declared static;
        // static fields can only be declared in static or top level types
        // static String str;//Compile time error

        // The blank final field str may not have been initialized
        // static final String str;//Compile time error

        static final String str = "Constant is Okay";

        Inner() {
            System.out.println(Outer.this.str);
            test = "Inherits static Member";
            System.out.println(test);
        }

        // The member interface NeverInner can only be defined inside a
        // top-level class or interface
        // interface NeverInner;//Compile error
    }

    static class InnerStatic {
        static int value = 100;

        public InnerStatic() {
            System.out.println("New Static Inner Class");
            System.out.println(test);// access outer class field

            // System.out.println(str);//Cannot make a static reference to the
            // non-static field str

            Outer o2 = new Outer();// can create an instance of outer class
            System.out.println(o2.str);
        }

        public void checkMessage() {
            System.out.println("Inside Inner Static Class");
        }

    }
}

public class TestBox {
    public static void main(String[] args) {
       Box<Integer> b1 = new Box();
        b1.fill(new Integer(5));

        Box<Double> b2 = new Box();
        b2.fill(new Double(4.2));

        Box<String> b3 = new Box();
        b3.fill("Hello Box");

        Box<Integer> b4 = new Box();
        b4.fill(new Integer(82));

        System.out.println("b1: " + b1.look());
        System.out.println("b2: " + b2.look().toString());
        System.out.println("b3: " + b3.look());

        System.out.println("b1 again : " + b1.toString());


        max(b1.look(), b4.look());

    }

    //add the generic method max that displays "larger" of two things
    public static <E extends Comparable<E>> void max(E o1, E o2){
        if (o1.compareTo(o2) > 1)
            System.out.println(o1.toString() + " is bigger than " + o2.toString());
        else if(o1.compareTo(o2) < 1)
            System.out.println(o2.toString() + " is bigger than " + o1.toString());
        else
            System.out.println(o1.toString() + " and " + o2.toString() + " are equivalent");
    }
}

public class StringCount {
    public static void main(String[] args) {
        Integer object1 = new Integer(555);
        Double object2 = new Double(123.456);
        String object3 = "Generics are quite useful!";

        System.out.println(object1 + " has " + toStringCount(object1) + " characters.");
        System.out.println(object2 + " has " + toStringCount(object2) + " characters.");
        System.out.println(object3 + " has " + toStringCount(object3) + " characters.");
    }

    //add generic method toStringCount
    public static <E> int toStringCount(E o){
        String s = o.toString();
        return s.length();
    }
}

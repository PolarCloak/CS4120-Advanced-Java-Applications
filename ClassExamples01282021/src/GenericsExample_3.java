//Creating a generic method
//Example credited to
//http://www.java2s.com/Tutorials/Java/Java_Language/
//8010__Java_generic_method.htm

public class GenericsExample_3 {
    //Method using generics
    static <T, V extends T> boolean isIn(T x, V[] y) {
        for (int i = 0; i < y.length; i++) {
            if (x.equals(y[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Integer nums[] = { 1, 2, 3, 4, 5 };
        if (isIn(2, nums)){
            System.out.println("2 is in nums");
        }
        String strs[] = { "one", "two", "three", "four", "five" };
        if (isIn("two", strs)){
            System.out.println("two is in strs");
        }
    }//end main
}

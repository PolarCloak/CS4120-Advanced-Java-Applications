// implement the method
// public static <E extends Comparable<E>> int linearSeach(E[] list, E key)

public class GenericLinearSearch {
    public static void main(String[] args) {
        Integer[] list = {3, 4, 5, 1, -3, -5, -1};
        int searchValue = 18;
        int result = linearSearch(list, 10);
        if (result > -1)
            System.out.println(searchValue + " was found at " + result);
        else
            System.out.println(searchValue + " was not in the list");

        Character[] charList = {'a', 'b', 'c'};
        result = linearSearch(charList, 'c');
        if (result > -1)
            System.out.println("c" + " was found at index " + result);
        else
            System.out.println("c was not in the list");

    }

    //add Generic Method linearSearch
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key){
        for(int i=0; i<list.length; i++){
            if(list[i].equals(key))
                return i;
        }
        return -1;
    }


}

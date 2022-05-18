public class Heaping {

    public static String heap(int[] array){
        boolean heap = isHeap(array);
        if(heap){
            return "Yes, heap.";
        }
        else{
            return "No, not a heap.";
        }
    }

    private static boolean isHeap(int[] array) {

        // running time for this algorithm will be 1n + 1, since
        // we will loop through the array a single time, using only
        // variable checks at each index, +1 for closing the for loop.

        for(int childIndex = array.length-1; childIndex>=0; childIndex--){
            int parentIndex = childIndex/2;
            if(array[parentIndex]<array[childIndex]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] A={16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        int[] B={10, 3, 9, 7, 2, 11, 5, 1, 6};
        System.out.println(Heaping.heap(A));
        System.out.println(Heaping.heap(B));
    }
}

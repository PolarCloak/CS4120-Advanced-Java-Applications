public class Box <E extends Comparable<E>>{

    private E o;

    public void fill(E o){
        this.o = o;
    }

    public E look(){
        return o;
    }

    public void empty(){
        o = null;
    }

    public String toString(){
        return o.toString();
    }

    public int compareTo(E o){
        if(o instanceof Box)
            return this.o.compareTo(o);
        return 0;
    }

}

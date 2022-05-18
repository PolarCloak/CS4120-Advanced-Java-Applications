public class ThreadFun {

    public static void main(String[] args){
        Task task1 = new Task("sunshine", 25);
        Task task2 = new Task("clouds", 25);
        Task task3 = new Task("mules", 50);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class Task implements Runnable{

    String word;
    int number;

    public Task(String word, int number){
        this.word=word;
        this.number=number;
    }

    @Override
    public void run() {
        for(int i=0; i<number; i++){
            System.out.println(word);
        }
        System.out.println("Task " + word + " is finished.");
    }
}

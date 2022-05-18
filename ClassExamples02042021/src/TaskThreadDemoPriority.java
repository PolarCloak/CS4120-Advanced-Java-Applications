public class TaskThreadDemoPriority {
    public static void main(String[] args) {
        // Create tasks
        Runnable print100 = new PrintNum2(100);
        Runnable printA = new PrintChar2('a', 1000);
        Runnable printB = new PrintChar2('b', 1000);


        // Create threads
        Thread thread3 = new Thread(print100);
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);


        // Start threads
        thread1.start();
        thread2.start();
        thread3.setPriority(Thread.MAX_PRIORITY);
        thread3.start();
    }
}

// The task for printing a specified character in specified times
class PrintChar2 implements Runnable {
    private char charToPrint; // The character to print
    private int times; // The times to repeat

    /** Construct a task with specified character and number of
     *  times to print the character
     */
    public PrintChar2(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override /** Override the run() method to tell the system
     *  what the task to perform
     */
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint);
        }
    }
}

// The task class for printing number from 1 to n for a given n
class PrintNum2 implements Runnable {
    private int lastNum;

    /** Construct a task for printing 1, 2, ... i */
    public PrintNum2(int n) {
        lastNum = n;
    }

    @Override /** Tell the thread how to run */
    public void run() {
        for (int i = 1; i <= lastNum; i++) {
            System.out.print(" " + i);
           // if (i ==50) {
           //     try {
                   // Thread.sleep(3);
          //      } catch (InterruptedException e) {
          //          e.printStackTrace();
         //       }
         //   }
        }
    }
}

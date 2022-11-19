//producer-consumer concept in java

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> sharedList = new ArrayList<Integer>();
        Thread thread1 = new Thread(new Reader(sharedList));
        Thread thread2 = new Thread(new Writer(sharedList));
        thread1.start();
        thread2.start();
        // thread1.join();
        // thread2.join();
    }
}
// producer class
class Reader implements Runnable {
    List<Integer> sharedList = null;
    //set a size of sharedlist which means producer can produce atmost 5 element ata atime
    final int MAX_SIZE = 5;
    private int i = 0;

    // constructor or producer class
    public Reader(List<Integer> sharedList) {
        super();
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //calling produce function in run method
                reading(i++);
            } catch (InterruptedException exception) {

            }
        }
    }

    public void reading(int i) throws InterruptedException {
        synchronized (sharedList) {
            //if shared list is full then it is waiting for consumer
            while (sharedList.size() == MAX_SIZE) {
                System.out.println("Sharedlist is Full...waiting for the Writer to write");
                sharedList.wait();
            }
        }
        synchronized (sharedList) {
            System.out.println("Reader reading element" + i);
            //if not full then add in sharedlist and notify to consumer
            sharedList.add(i);
            Thread.sleep(500);
            sharedList.notify();
        }
    }
}

// consumer class
class Writer implements Runnable {
    List<Integer> sharedList = null;

    // constructor or consumer class
    public Writer(List<Integer> sharedList) {
        super();
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                writing();
            } catch (InterruptedException exception) {

            }
        }
    }

    public void writing() throws InterruptedException {
        synchronized (sharedList) {
            while (sharedList.isEmpty()) {
                //if sharedlist is empty waiting for producer
                System.out.println("Sharedlist is empty...waiting for the Reader  to read");
                sharedList.wait();
            }
        }
        synchronized (sharedList) {

            Thread.sleep(1000);
            System.out.println("writing the element" + sharedList.remove(0));
            sharedList.notify();
        }
    }

}

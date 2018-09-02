package task06;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();
        Chronometr chronometr = new Chronometr(monitor);
        Thread threadChr = new Thread(chronometr);
        threadChr.start();

        Thread thread1 = new Thread(new MessageRunnable(monitor, 5));
        Thread thread2 = new Thread(new MessageRunnable(monitor, 7));

        thread1.start();
        thread2.start();

        System.out.println("Started");
    }
}

package task06;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Chronometr chronometr = new Chronometr();

        Message message1 = new Message(chronometr, 5);
        Thread thread1 = new Thread(message1);
        Message message2 = new Message(chronometr, 7);
        Thread thread2 = new Thread(message2);

        Thread threadChr = new Thread(chronometr);
        threadChr.start();

        thread1.start();
        thread2.start();

        System.out.println("Started");
    }
}

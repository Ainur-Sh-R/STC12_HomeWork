package task06;

public class Message implements Runnable {
    Chronometr monitor;
    private int count;

    public Message(Chronometr monitor, int count) {
        this.monitor = monitor;
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.monitor) {

                try {
                    this.monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (monitor.getSec() % count == 0) {
                        System.out.println(Thread.currentThread().getName() + " : прошло " + count + " сек");
                    }

            }
        }
    }
}

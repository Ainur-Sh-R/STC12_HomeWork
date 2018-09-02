package task06;

import java.util.concurrent.atomic.AtomicInteger;

public class Chronometr implements Runnable {
    private static final AtomicInteger sec = new AtomicInteger(0);
    private Object monitor;

    public Chronometr(Object monitor) {
        this.monitor = monitor;
    }

    static public int getSec() {
        return sec.intValue();
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            synchronized (monitor) {
            try {
                monitor.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                sec.incrementAndGet();
                this.monitor.notifyAll();
                System.out.println("Прошло " + sec.intValue() + " сек");
            }

        }
    }
}

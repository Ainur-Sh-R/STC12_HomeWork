package task06;

import java.util.concurrent.atomic.AtomicInteger;

public class Chronometr implements Runnable {
    private final AtomicInteger sec = new AtomicInteger(0);

    public int getSec() {
        return sec.get();
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                sec.incrementAndGet();
                this.notifyAll();
                System.out.println("Прошло " + this.getSec() + " сек");
            }

        }
    }
}

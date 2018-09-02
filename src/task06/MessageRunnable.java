package task06;

/**
 * Выводит в консоль сообщение каждые #count секунд
 */
public class MessageRunnable implements Runnable {
    /** Общий монитор для всех объектов*/
    private Object monitor;
    /** значение делителя*/
    private int count;

    /**
     * @param monitor Общий монитор для всех объектов
     * @param count значение делителя
     */
    public MessageRunnable(Object monitor, int count) {
        this.monitor = monitor;
        this.count = count;
    }


    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            synchronized (this.monitor) {
                try {
                    this.monitor.wait();
                    if ((Chronometr.getSec() % count) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : прошло " + count + " сек");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

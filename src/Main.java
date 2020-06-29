import java.util.concurrent.locks.LockSupport;

public class Main {

    public static void main(String[] args) {
        Object forever = new Object();
        synchronized (forever) {
            try {
                LockSupport.park();
                forever.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
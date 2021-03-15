import entity.Atom;
import org.junit.jupiter.api.Test;

public class AtomTest {

    @Test
    public void test1() throws InterruptedException {
        Atom atom = new Atom();
        Thread thread = new Thread(){
            @Override
            public void run() {
                atom.service((int) currentThread().getId());
            }
        };
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                atom.service((int) currentThread().getId());
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                atom.service(2);
            }
        };

        thread.start();

        thread1.start();

//        runnable.run();
        for(int i=0;i<100;i++) {
            Thread.sleep(1);
            System.out.println(i);
        }
    }
}

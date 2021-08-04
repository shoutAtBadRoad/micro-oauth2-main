package mashibing;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.*;

/**
 * @author JYP
 * @date 2021/6/25
 **/

public class TaskTest {

    final static Callable<Integer> c = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("success");
            return 1;
        }
    };



    public static void main(String[] args) throws Exception{
//        future.get();
        TimeUnit.MILLISECONDS.sleep(1000);
        Integer call = c.call();
    }
}

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final ExecutorService executor = new ExecutorBuilder().build();

        Callable<List<Integer>> task = new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                List<Integer> result = new ArrayList<>();
                for(int i=0;i<100;++i) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Thread.currentThread());
                    System.out.println(stringBuilder.append(i).toString());
                    result.add(i);
                }
                return result;
            }
        };

        Future<List<Integer>> future = executor.submit(task);
        System.out.println(future.get());

    }
}

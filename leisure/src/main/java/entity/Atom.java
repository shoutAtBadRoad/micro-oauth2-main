package entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Data
@Slf4j
public class Atom {
    private final AtomicLong count = new AtomicLong(0);

//    private long count = 0;

    public void service(int id) {
        log.info("Thread "+id +"执行此方法***************");
        count.incrementAndGet();
        System.out.println("now count is " + count);
//        count++;
    }

}

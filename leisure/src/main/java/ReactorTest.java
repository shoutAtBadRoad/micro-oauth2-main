import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactorTest {

    @Test
    public void test(){
        Flux.just("hello","world").subscribe(System.out::println);
        System.out.println("----------------");
        Flux.fromArray(new Integer[]{1,2,3}).subscribe(System.out::println);
        System.out.println("----------------");
        Flux.range(1,5).subscribe(System.out::println);
        Flux.create(fluxSink -> {
            for(int i=0;i<10;i++)
                fluxSink.next(i);
            fluxSink.complete();
        }).subscribe(System.out::println);
        System.out.println("-----------------");
        Mono.delay(Duration.ofSeconds(1)).subscribe(System.out::println);
    }

    @Test
    public void test1(){
//        Flux.range(1,5).window(3).toIterable().forEach(w ->{
//            w.subscribe(System.out::println);
//            System.out.println("--------------");
//        });
//        Flux.range(1,10).filter(integer -> integer%2==0).take(20)
//                .subscribe(System.out::println);
//        Flux.merge(Flux.interval(Duration.ofMillis(0),Duration.ofMillis(10)).take(3),
//                Flux.interval(Duration.ofMillis(5),Duration.ofMillis(10)).take(3)).toStream().forEach(System.out::println);
        System.out.println("-------------------");
        Flux.mergeSequential(Flux.interval(Duration.ofMillis(10),Duration.ofMillis(0)).take(3),
                Flux.interval(Duration.ofMillis(10),Duration.ofMillis(5)).take(3)).toStream().forEach(System.out::println);
        System.out.println("-------------------");
        Flux.just("a","b").zipWith(Flux.just("c","d")).subscribe(System.out::println);
        System.out.println("-------------------");
        Flux.just("a","b").zipWith(Flux.just("c","d"), (s1,s2) ->
            String.format("%s+%s",s1,s2)).subscribe(System.out::println);

    }

    @Test
    public void test2(){
//        Hooks.onOperatorDebug();
        Mono.just(0)
                .map(x-> 1+x)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .onErrorReturn(0)
//                .log()
                .checkpoint("zero")
                .subscribe(System.out::println);
    }
}

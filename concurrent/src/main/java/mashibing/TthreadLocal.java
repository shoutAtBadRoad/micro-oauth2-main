package mashibing;

/**
 * @author JYP
 * @date 2021/6/16
 **/

public class TthreadLocal {

    public static void  test(){
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        threadLocal.set(10);
        threadLocal.remove();
    }
}

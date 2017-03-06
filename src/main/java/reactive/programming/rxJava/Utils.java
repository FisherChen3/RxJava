package reactive.programming.rxJava;

/**
 * Created by Fisher on 2/25/2017.
 */
public class Utils {

    public static void log(Object message) {

        System.out.println('[' + Thread.currentThread().getName() + "] " + message.toString());

    }

    public static void pend(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

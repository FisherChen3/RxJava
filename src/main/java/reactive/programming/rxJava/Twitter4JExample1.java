package reactive.programming.rxJava;

import rx.Observable;
import rx.subscriptions.Subscriptions;
import twitter4j.*;
import twitter4j.util.function.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Fisher on 2/7/2017.
 */
public class Twitter4JExample1 {
    // 1 initial callback
    public static void main(String[] args) {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new twitter4j.StatusListener() {
            @Override
            public void onStatus(Status status) {
                log("Status: {}" + status);
            }

            @Override
            public void onException(Exception ex) {
                log("Error callback" + ex);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {

            }

            @Override
            public void onScrubGeo(long l, long l1) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }


//other callbacks
        });
        /**
         * Calling twitterStream.sample() starts a background thread that logs in to Twitter
         and awaits new messages. Every time a tweet appears, the onStatus callback is executed.
         */
        twitterStream.sample();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        twitterStream.shutdown();
    }

    private static void log(Object msg) {
        System.out.println(
                Thread.currentThread().getName() +
                        ": " + msg);
    }

    // 2. use a generic call back to add another abstraction
    void consume(
            Consumer<Status> onStatus,
            Consumer<Exception> onException) {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                onStatus.accept(status);
            }

            @Override
            public void onException(Exception ex) {
                onException.accept(ex);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {

            }

            @Override
            public void onScrubGeo(long l, long l1) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }

        });
        twitterStream.sample();
    }

    /* Imagine that instead of logging you have persistence, analytics, or fraud detection
    we just need to change the following callback behavior
    consume(
        status -> log.info("Status: {}", status),
        ex -> log.error("Error callback", ex)
    );
     */


    /** 3. return an Observable<Status>
        subscriber can unsubscriber Observable
     */
    Observable<Status> observe(){

        return Observable.create(subscriber->{
            TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            twitterStream.addListener(new StatusListener() {
                @Override
                public void onStatus(Status status) {
                    subscriber.onNext(status);
                }

                @Override
                public void onException(Exception ex) {
                    subscriber.onError(ex);
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

                }

                @Override
                public void onTrackLimitationNotice(int i) {

                }

                @Override
                public void onScrubGeo(long l, long l1) {

                }

                @Override
                public void onStallWarning(StallWarning stallWarning) {

                }

            });
            subscriber.add(Subscriptions.create(twitterStream::shutdown));
        });

    }


}

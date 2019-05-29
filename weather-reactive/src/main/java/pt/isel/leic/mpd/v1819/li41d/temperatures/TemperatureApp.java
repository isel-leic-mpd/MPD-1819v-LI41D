package pt.isel.leic.mpd.v1819.li41d.temperatures;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TemperatureApp {
    public static void main(String[] args) {
        //CompletableFuture cf = new CompletableFuture();

        //final Observable<Long> onePerSecond = Observable.interval(200, TimeUnit.MILLISECONDS).take(40);
        Observable<Integer> onePerSecond = getTemperature( "New York", 50)
                .filter(ti -> ti.getTemp() > 20)
                .map(TempInfo::getTemp);


        onePerSecond
                .blockingSubscribe(
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer tempInfo) {
                        System.out.println(tempInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                        //cf.complete(null);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("###################");
                        //cf.complete(null);
                    }
                }
        );



        //cf.join();




    }


    public static Observable<TempInfo> getTemperature(String town, int numMeasures) {
        return Observable.create(emitter ->
                Observable.interval(200, TimeUnit.MILLISECONDS)
                        .subscribe(l -> {
                            if (!emitter.isDisposed()) {
                                if (l >= numMeasures) {
                                    emitter.onComplete();
                                } else {
                                    try {
                                        final TempInfo tempInfo = TempInfo.fetch(town);
                                        System.out.println("####" + tempInfo);
                                        emitter.onNext(tempInfo);
                                    } catch (Exception e) {
                                        emitter.onError(e);
                                    }
                                }
                            }
                        }));
    }
}

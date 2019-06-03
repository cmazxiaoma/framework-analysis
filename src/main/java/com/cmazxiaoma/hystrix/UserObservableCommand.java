package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 19:41
 */
public class UserObservableCommand extends HystrixObservableCommand<HystrixTest.User> {

    public UserObservableCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Observable<HystrixTest.User> construct() {
        return Observable.create(
                new Observable.OnSubscribe<HystrixTest.User>() {
                    @Override
                    public void call(
                            Subscriber<? super HystrixTest.User>
                                    subscriber) {
                        try {
                            if (!subscriber.isUnsubscribed()) {
                                HystrixTest.User user = new HystrixTest.User().setId(4L);
                                subscriber.onNext(user);
                                subscriber.onCompleted();
                            }
                        } catch (Exception ex) {
                            subscriber.onError(ex);
                        }
                    }
                }
        );
    }

    @Override
    protected Observable<HystrixTest.User> resumeWithFallback() {
        return Observable.create(
                new Observable.OnSubscribe<HystrixTest.User>() {
                    @Override
                    public void call(Subscriber<? super HystrixTest.User> subscriber) {
                        try {
                            if (!subscriber.isUnsubscribed()) {
                                HystrixTest.User user = new HystrixTest.User().setId(4L).setMsg("fallback");
                                subscriber.onNext(user);
                                subscriber.onCompleted();
                            }
                        } catch (Exception ex) {
                            subscriber.onError(ex);
                        }
                    }
                }
        );
    }
}

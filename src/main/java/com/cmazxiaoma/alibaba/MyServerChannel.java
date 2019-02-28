package com.cmazxiaoma.alibaba;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/28 14:10
 */
public class MyServerChannel {

    public <A> void accept(A attachment, Handler<MyClientChannel, ? super A> handler) {
        System.out.println("accept");
        MyClientChannel myClientChannel = new MyClientChannel();
        handler.completed(myClientChannel, attachment);
    }
}

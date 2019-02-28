package com.cmazxiaoma.alibaba;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/28 14:19
 */
public class MyClientChannel {

    public final <A> void read(ByteBuffer dst,
                               A attachment,
                               Handler<Integer,? super A> handler)
    {
        System.out.println("read");

        handler.completed(1, attachment);
    }

}

package com.cmazxiaoma.netty.demo.aio.hard;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/27 22:54
 */
public class AioClient {

    AsynchronousSocketChannel asynchronousSocketChannel = null;
    static final int port = 8889;
    Charset charset = Charset.forName("UTF-8");

    public void connect() throws IOException, ExecutionException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ExecutorService executorService = Executors.newFixedThreadPool(80);
        AsynchronousChannelGroup channelGroup =
                AsynchronousChannelGroup.withThreadPool(executorService);
        asynchronousSocketChannel = AsynchronousSocketChannel.open(channelGroup);
        asynchronousSocketChannel.connect(new InetSocketAddress("127.0.0.1", port)).get();

        asynchronousSocketChannel.read(buffer, null, new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer result, Object attachment) {
                buffer.flip();
                String content = charset.decode(buffer).toString();
                System.out.println("从Server收到消息：" + content);
                buffer.clear();
                asynchronousSocketChannel.read(buffer, null, this);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败：" + exc);
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        AioClient aioClient = new AioClient();
        aioClient.connect();

        aioClient.asynchronousSocketChannel.write(ByteBuffer.wrap("hello!".getBytes("UTF-8")))
                .get();

        TimeUnit.SECONDS.sleep(5);
    }

}
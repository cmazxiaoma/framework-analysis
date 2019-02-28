package com.cmazxiaoma.netty.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/27 22:39
 */
public class SimpleAioClient {

    static final int port = 8887;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Charset charset = Charset.forName("UTF-8");

        AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();
        clientChannel.connect(new InetSocketAddress("127.0.0.1", port)).get();

        clientChannel.read(buffer).get();
        buffer.flip();

        String content = charset.decode(buffer).toString();
        System.out.println("服务器消息：" + content);
    }
}

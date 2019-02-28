package com.cmazxiaoma.netty.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/27 22:38
 */
public class SimpleAioServer {

    static final int port = 8887;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));

        while (true) {
            Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
            AsynchronousSocketChannel socketChannel = future.get();
            socketChannel.write(ByteBuffer.wrap("欢迎来到AIO的世界".getBytes("UTF-8"))).get();
        }
    }
}

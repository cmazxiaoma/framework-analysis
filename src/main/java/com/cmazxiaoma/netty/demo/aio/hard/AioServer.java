package com.cmazxiaoma.netty.demo.aio.hard;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/27 22:54
 */
public class AioServer {

    static final int port = 8889;
    static List<AsynchronousSocketChannel> channelList = new ArrayList<>();
    Charset charset = Charset.forName("UTF-8");

    public void startListen() throws IOException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        // 以指定线程池来创建一个AsynchronousChannelGroup
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executorService);

        // 以指定线程池来创建一个AsynchronousServerSocketChannel
        AsynchronousServerSocketChannel serverSocketChannel =
                AsynchronousServerSocketChannel.open(channelGroup)
                        .bind(new InetSocketAddress("127.0.0.1", port));

        // 使用CompletionHandler接收来自客户端的连接方式
        serverSocketChannel.accept(null, new AcceptHandler(serverSocketChannel));
    }

    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

        public AcceptHandler(AsynchronousServerSocketChannel asynchronousServerSocketChannel) {
            this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
        }

        // 定义一个ByteBuffer准备读取数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);


        // 实际IO操作完成时触发该方法
        @Override
        public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
            // 记录新连接进来的Channel
            AioServer.channelList.add(socketChannel);

            System.out.println("有客户端连接...");

            // 准备接收客户端的下一次请求
            asynchronousServerSocketChannel.accept(null, this);

            socketChannel.read(buffer, null, new CompletionHandler<Integer, Object>() {

                @Override
                public void completed(Integer result, Object attachment) {
                    buffer.flip();
                    String content = charset.decode(buffer).toString();
                    System.out.println("从client收到：" + content);

                    // 遍历整个Channel
                    for (AsynchronousSocketChannel channel : AioServer.channelList) {
                        try {
                            channel.write(ByteBuffer.wrap(content.getBytes("UTF-8"))).get();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    buffer.clear();
                    // 读取下一次数据
                    socketChannel.read(buffer, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("读取数据失败：" + exc);
                    AioServer.channelList.remove(socketChannel);
                }
            });
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            System.out.println("连接失败:" + exc);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AioServer aioServer = new AioServer();
        aioServer.startListen();

        TimeUnit.SECONDS.sleep(5);
    }
}

package com.cmazxiaoma.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/27 19:14
 */
public class PlainNioClient {

    private Charset charset = Charset.forName("UTF-8");
    private Selector selector = null;

    public void initClient(int port) throws IOException {
        InetSocketAddress address = new InetSocketAddress(port);
        // 调用open静态方法创建连接到指定主机的SocketChannel
        SocketChannel socketChannel = SocketChannel.open(address);

        socketChannel.configureBlocking(false);
        selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_READ);
        new ClientThread().start();

        for (int i = 0; i < 100; i++) {
            socketChannel.write(charset.encode(i + ""));
        }
    }

    private class ClientThread extends Thread {

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();

                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (selectionKey.isReadable()) {
                            try {
                                SocketChannel channel = (SocketChannel) selectionKey.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                StringBuffer stringBuffer = new StringBuffer();

                                while (channel.read(buffer) > 0) {
                                    buffer.flip();
                                    stringBuffer.append(charset.decode(buffer));
                                }
                                System.out.println("从Server接收消息：" + stringBuffer.toString());
                            } finally {
                                iterator.remove();
                                // 将selectionKey对应的channel设置成准备下一次读取
                                selectionKey.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainNioClient().initClient(8886);
    }
}

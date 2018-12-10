package com.cmazxiaoma.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/6 17:53
 */
public class PlainNioServer {

    public void serve(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);

        // 将服务器绑定到选定的端口
        serverSocket.bind(address);

        // 打开Selector来处理Channel
        Selector selector = Selector.open();
        // 将ServerSocket注册到Selector以接受连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi\r\n".getBytes("UTF-8"));

        for(;;) {
            try {
                // 等待需要处理的新事件，阻塞将一直持续到下一个传入事件
                selector.select();

                // 获取所有接收事件的SelectionKey实例
                Set<SelectionKey> readKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = readKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    try {

                        //检查事件是否是一个新的并且已经就绪可以被接受的连接
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel client = server.accept();
                            client.configureBlocking(false);

                            // 接受客户端，并将它注册到选择器
                            client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ,
                                    msg.duplicate());

                            System.out.println("accepted connection from " + client);
                        }

                        // 检查套接字是否已经准备好写数据
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();

                            while (buffer.hasRemaining()) {
                                // 将数据写入到已连接的客户端
                                if (client.write(buffer) == 0) {
                                    break;
                                }
                            }
                            client.close();
                        }

                    } catch (IOException ex) {
                        key.cancel();

                        try {
                            key.channel().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
        }




    }
}

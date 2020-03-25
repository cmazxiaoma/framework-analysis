package com.cmazxiaoma.netty.demo.nio;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/6 17:53
 */
public class PlainNioServer {

    private Charset charset = Charset.forName("UTF-8");

    public void initServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 非阻塞
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);

        // 将服务器绑定到选定的端口
        serverSocket.bind(address);

        // 打开Selector来处理Channel
        Selector selector = Selector.open();
        // 将ServerSocket注册到Selector以接受连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("hi,client\r\n".getBytes("UTF-8"));

        // 等待需要处理的新事件，阻塞将一直持续到下一个传入事件
        while (selector.select() > 0) {
            try {
                // 获取所有接收事件的SelectionKey实例
                Set<SelectionKey> readKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = readKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // 从selector上的已选择Key集中删除正在处理的SelectionKey
                    iterator.remove();

                    try {

                        //检查事件是否是一个新的并且已经就绪可以被接受的连接
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel client = server.accept();

                            // 以非阻塞方式工作
                            client.configureBlocking(false);

                            // 接受客户端，并将它注册到选择器
                            client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());

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
                        }

                        // 如果key对应的Channel有数据要读取
                        if (key.isReadable()) {

                            // 获取该SelectionKey对应的Channel，该Channel中有可读的数据
                            SocketChannel client = (SocketChannel) key.channel();
                            // 定义准备执行读取数据的ByteBuffer
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            StringBuffer stringBuffer = new StringBuffer();

                            while (client.read(buffer) > 0) {
                                buffer.flip();
                                stringBuffer.append(charset.decode(buffer));
                            }

                            // 打印从selectionKey对应的Channel里读取的数据
                            System.out.println("读取消息：" + stringBuffer.toString());
                            // 将selectionKey对应的Channel设置成准备下一次读取
                            key.interestOps(SelectionKey.OP_READ);

                            if (!StringUtils.isEmpty(stringBuffer.toString())) {
                                for (SelectionKey selectionKey : selector.keys()) {
                                    Channel channel = selectionKey.channel();
                                    if (channel instanceof SocketChannel) {
                                        SocketChannel dest = (SocketChannel) channel;
                                        dest.write(charset.encode(stringBuffer.toString()));
                                    }
                                }
                            }
                        }

                    } catch (IOException ex) {
                        // 从selection中删除指定的selectionKey
                        key.cancel();

                        try {
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainNioServer().initServer(8886);
    }
}

package com.cmazxiaoma.netty.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/28 21:55
 */
public class NettyNioServer {

    public void server(int port) throws InterruptedException {
        final ByteBuf buf = Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8"));

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    // 将消息写客户端，并添加ChannelFutureListener,以便消息一被写完就关闭连接
                                    ctx.writeAndFlush(buf.duplicate())
                                            .addListener(ChannelFutureListener.CLOSE);
                                    /**
                                     * 在Netty中，有2种发送消息的方式，你可以直接写到Channel中，
                                     * 也可以写到和ChannelHandler相关联的ChannelHandlerContext对象中。
                                     *
                                     * 前一种方式 可以导致消息从ChannelPipeline的尾部开始流动。
                                     * 后者将导致消息从ChannelPipeLine中的下一个ChannelHandler开始流动。
                                     */
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyNioServer nettyNioServer = new NettyNioServer();
        nettyNioServer.server(9998);
    }
}

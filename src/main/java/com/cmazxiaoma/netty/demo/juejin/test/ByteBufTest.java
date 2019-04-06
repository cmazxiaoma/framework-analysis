package com.cmazxiaoma.netty.demo.juejin.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/18 22:01
 */
public class ByteBufTest {

    public static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hi", charset);
        System.out.println("=======================================");
        System.out.println(byteBuf.toString(charset));
        System.out.println(byteBuf.maxCapacity());
        System.out.println(byteBuf.readableBytes());


        ByteBuf byteBuf1 = byteBuf.slice();
        System.out.println("=======================================");
        System.out.println(byteBuf1.toString(charset));
        System.out.println(byteBuf1.maxCapacity());
        System.out.println(byteBuf1.readableBytes());


        ByteBuf byteBuf2 = byteBuf.duplicate();
        System.out.println("=======================================");
        System.out.println(byteBuf2.toString(charset));
        System.out.println(byteBuf2.maxCapacity());
        System.out.println(byteBuf2.readableBytes());
    }
}

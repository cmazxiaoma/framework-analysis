package com.cmazxiaoma.alibaba;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/28 14:16
 */
public class Test {
    public static void main(String[] args) {
        MyServerChannel myServerChannel = new MyServerChannel();
        myServerChannel.accept(null, new MyHandler(myServerChannel));
    }

    private static class MyHandler implements Handler<MyClientChannel, Object> {
        public MyServerChannel myServerChannel;

        public MyHandler(MyServerChannel myServerChannel) {
            this.myServerChannel = myServerChannel;
        }

        @Override
        public void completed(MyClientChannel myClientChannel, Object attachment) {
            System.out.println("新连接进入");

            myServerChannel.accept(null, this);

            myClientChannel.read(null, null, new Handler<Integer, Object>() {

                @Override
                public void completed(Integer result, Object attachment) {
                    System.out.println("客户端读完成");
                    myClientChannel.read(null, null, this);
                }

                @Override
                public void failed(Throwable ex, Object attachment) {

                }

            });

        }

        @Override
        public void failed(Throwable ex, Object attachment) {

        }
    }
}




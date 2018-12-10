package com.cmazxiaoma.netty.demo.oio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/6 17:26
 */
public class PlainOioServer {

    public void serve(int port) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            for (;;) {
                final Socket clientSocket = serverSocket.accept();
                System.out.println("accepted connection from " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("Hi\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (clientSocket != null) {
                                    clientSocket.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception e) {

        } finally {
            // todo
        }
    }

    public static void main(String[] args) throws Exception {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.serve(9998);
    }
}

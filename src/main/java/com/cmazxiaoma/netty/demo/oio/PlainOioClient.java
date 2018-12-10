package com.cmazxiaoma.netty.demo.oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/6 17:37
 */
public class PlainOioClient {

    public static void main(String[] args) throws IOException {
        PlainOioClient plainOioClient = new PlainOioClient();
        plainOioClient.connect();
    }

    public void connect() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 9998), 5000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        socket.close();
    }
}

package com.cmazxiaoma.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/24 17:37
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        String readPath = "C:\\Users\\cmazxiaoma\\Desktop\\read.txt";
        String writePath = "C:\\Users\\cmazxiaoma\\Desktop\\write.txt";

        FileInputStream fileInputStream = new FileInputStream(readPath);
        FileOutputStream fileOutputStream = new FileOutputStream(writePath);

        int hasRead = 0;
        byte[] bytes = new byte[2];

        while ((hasRead = fileInputStream.read(bytes)) != -1) {
            System.out.println("hasRead=" + hasRead);
            System.out.println("bytes=" + bytes);

            // offset,len 分别指的是bytes的偏移量、bytes的大小。
            fileOutputStream.write(bytes, 0, hasRead);
        }
    }
}

package com.cmazxiaoma.Javaoffer.classLoader;

import java.io.UnsupportedEncodingException;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/18 22:19
 */
public class ClassLoaderChecker {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedEncodingException {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\dev\\intellij-workspace\\hibernate_demo\\src\\main\\java\\com\\cmazxiaoma\\Javaoffer\\classLoader\\", "MyClassLoader");
        Class myClass = myClassLoader.loadClass("Data");

        System.out.println(myClassLoader);
        System.out.println(myClassLoader.getParent());
        System.out.println(myClassLoader.getParent().getParent());
        System.out.println(myClassLoader.getParent().getParent().getParent());

        Object object = myClass.newInstance();

    }
}

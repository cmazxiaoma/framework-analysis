package com.cmazxiaoma.cpucache;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/22 14:31
 */
public class Test {

    public static void main(String[] args) {

        String[] stringArray1 = new String[1];

        String[] stringArray2 = stringArray1;

        stringArray2[0] = "mayday";


        for (String s : stringArray1) {
            System.out.println(s);
        }

        System.out.println("===============");

        for (String s : stringArray2) {
            System.out.println(s);
        }

        for (String s : args) {
            System.out.println("arg=" + s);
        }
    }
}

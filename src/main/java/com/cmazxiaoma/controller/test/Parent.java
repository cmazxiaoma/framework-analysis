package com.cmazxiaoma.controller.test;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/17 23:19
 */
public class Parent {

    public String publicField  = "1";

    String defaultField = "2";

    protected String protectedField = "3";

    private String privateField = "4" ;

    public void publicMethod() {
        System.out.println("publicMethod...");
    }

    void defaultMethod() {
        System.out.println("defaultMethod...");
    }

    protected void protectedMethod() {
        System.out.println("protectedMethod...");
    }

    private void privateMethod() {
        System.out.println("privateMethod...");
    }

    static class LocalMap {

        private static String table = "table";

        static class Entry {
            private static String name = "entry";
        }
    }

}

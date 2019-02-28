package com.cmazxiaoma.gc;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/28 13:18
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();

        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable ex) {
            System.out.println("stack length:" + javaVMStackSOF.stackLength);
            throw ex;
        }
    }
}

package com.cmazxiaoma.慕课网并发学习.安全发布对象;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/14 4:12
 */
public class Publish {

    private String[] state = {"c", "m", "a", "z"};


    public String[] getStates() {
        return state;
    }

    public static void main(String[] args) {
        Publish publish = new Publish();
        publish.getStates()[0] = "a";
        publish.getStates()[0] = "b";
    }
}

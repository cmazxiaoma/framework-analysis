package cmazxiaoma.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/4 18:17
 */
public class SonTest {

    @Test
    public void test() {
        Son son1 = new Son();
        son1.setSonName("son1");
        son1.setFatherName("baseFather");

        Son son2 = new Son();
        son2.setSonName("son1");
        son2.setFatherName("baseFather2");

        System.out.println(son1.equals(son2));

    }
}
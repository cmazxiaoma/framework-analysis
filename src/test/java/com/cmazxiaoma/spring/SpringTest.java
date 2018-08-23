package com.cmazxiaoma.spring;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.spring.bean.SpringTest2;
import com.cmazxiaoma.spring.bean.SpringTest3;
import com.cmazxiaoma.spring.bean.SpringTestBean;
import com.cmazxiaoma.spring.compoent.SpringTest4;
import com.cmazxiaoma.spring.lazy.Hero;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/8 16:16
 */
public class SpringTest extends InitSpringTest {

    @Autowired
    private SpringTest3 springTest3;

    @Autowired
    private SpringTestBean springTestBean;

    private Hero hero;

    @Autowired
    private SpringTest4 springTest4;

    @Autowired
    private SpringTest2 springTest2;

    @Test
    public void test() {
        System.out.println("springTest3=" + springTest3);
    }

    @Test
    public void test1() {
        System.out.println("springTestBean=" + springTestBean);
    }

    @Test
    public void testHero() {
        hero = SpringApplicationContext.getBeanByType(Hero.class);
    }

    @Test
    public void test4() {
        System.out.println("springTest4=" + springTest4);
    }

    @Test
    public void test5() {
        System.out.println(springTest2.toString());
    }

}

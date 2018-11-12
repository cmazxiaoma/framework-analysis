package com.cmazxiaoma.thread;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.hibernate.ISchoolDao;
import com.cmazxiaoma.hibernate.IStudentDao;
import com.cmazxiaoma.mybatis.ISchoolMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/12 9:43
 */
public class ThreadTest extends InitSpringTest {

    @Autowired
    private ISchoolMapper schoolMapper;

    @Autowired
    private IStudentDao studentDao;

    @Test
    public void threadTest() throws InterruptedException {
        System.out.println("==================start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=================thread");
                schoolMapper.findOneByV1("1");
                studentDao.findOne("1");
            }
        });
        thread.start();
        thread.join();
        System.out.println("==================end");
    }
}

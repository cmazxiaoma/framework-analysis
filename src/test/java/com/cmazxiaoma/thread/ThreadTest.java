package com.cmazxiaoma.thread;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.hibernate.ISchoolDao;
import com.cmazxiaoma.hibernate.IStudentDao;
import com.cmazxiaoma.mybatis.ISchoolMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void threadTest() {
        System.out.println("test");
        new Thread(new Runnable() {
            @Override
            public void run() {
                studentDao.findAll();
            }
        }).start();
    }
}

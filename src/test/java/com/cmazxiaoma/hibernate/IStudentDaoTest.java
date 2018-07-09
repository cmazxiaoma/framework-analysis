package com.cmazxiaoma.hibernate;

import com.cmazxiaoma.BaseTest;
import com.cmazxiaoma.model.School;
import com.cmazxiaoma.model.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 18:00
 */
public class IStudentDaoTest extends BaseTest {

    @Resource
    private IStudentDao studentDao;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void query() {
        Student student = studentDao.findOne("1");
        System.out.println("student=" + student);
        System.out.println("student.id=" + student.getId());

        School school = student.getSchool();
        System.out.println("school=" + school);

        System.out.println("=======================");
        Student student1 = studentDao.findOne("1");
        System.out.println("student=" + student1);
        System.out.println("student.id=" + student1);
        School school1 = student1.getSchool();
        System.out.println("school1=" + school1);
    }
}
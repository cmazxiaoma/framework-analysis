package cmazxiaoma.dao;

import cmazxiaoma.BaseTest;
import cmazxiaoma.model.School;
import cmazxiaoma.model.Student;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.*;

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
    }
}
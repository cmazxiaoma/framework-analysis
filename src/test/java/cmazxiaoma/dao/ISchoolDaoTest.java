package cmazxiaoma.dao;

import cmazxiaoma.BaseTest;
import cmazxiaoma.model.School;
import cmazxiaoma.model.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:59
 */

public class ISchoolDaoTest extends BaseTest {

    @Autowired
    private ISchoolDao schoolDao;

    @Test
    public void query() throws Exception {
        School school = schoolDao.findOne("1");
        System.out.println(school);

        Set<Student> studentList = school.getStudentList();
        System.out.println("studentList=" + studentList);
    }
}
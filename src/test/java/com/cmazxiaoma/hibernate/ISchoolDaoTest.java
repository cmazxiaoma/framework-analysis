package com.cmazxiaoma.hibernate;

import com.cmazxiaoma.BaseTest;
import com.cmazxiaoma.model.School;
import com.cmazxiaoma.model.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:59
 */

public class ISchoolDaoTest extends BaseTest {

    @Autowired
    private ISchoolDao schoolDao;

    @Autowired
    private IStudentDao studentDao;

    @Test
    public void query() throws Exception {
        School school = schoolDao.findOne("1");
        System.out.println(school);

        Set<Student> studentList = school.getStudentList();
        System.out.println("studentList=" + studentList);
    }

    @Test
    public void persist() {
        School school = schoolDao.findOne("1");
        Set<Student> studentList = school.getStudentList();

        school.setSchoolName(school.getSchoolName() + "v1.1");

        for (Student item : studentList) {
            item.setStudentName(item.getStudentName() + "v1.1");
        }

        schoolDao.save(school);
    }

    @Test
    public void merge() {
        Student student =  studentDao.findOne("1");

        student.setStudentName(student.getStudentName() + "v1.2");
        studentDao.save(student);

        School school = schoolDao.findOne("1");
        Set<Student> studentSet = school.getStudentList();
        System.out.println("studentSet=" + studentSet);
    }

    @Test
    public void remove() {
        School school = schoolDao.findOne("1");
        Set<Student> studentSet = school.getStudentList();
        System.out.println("studentSet=" + studentSet);

        schoolDao.delete(school);

        System.out.println("==================");
        School school1 = schoolDao.findOne("1");
        System.out.println("school1=" + school1);
        Set<Student> studentSet1 = school1.getStudentList();
        System.out.println("studentSet1=" + studentSet1);
    }

    /**
     *
     */
    @Test
    public void refresh() {

    }

    @Test
    public void detach() {
        Student student = studentDao.findOne("1");
        System.out.println("student=" + student);
        School school = student.getSchool();
        System.out.println("school=" + school);

        System.out.println("=====================");

        studentDao.delete(student);

        Student student1 = studentDao.findOne("1");
        System.out.println("student1=" + student1);

    }
}
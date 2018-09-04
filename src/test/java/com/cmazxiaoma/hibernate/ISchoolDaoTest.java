package com.cmazxiaoma.hibernate;

import com.cmazxiaoma.InitSpringTest;
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

public class ISchoolDaoTest extends InitSpringTest {

    @Autowired
    private ISchoolDao schoolDao;

    @Autowired
    private IStudentDao studentDao;

    /**
     * 查询school操作是否启动了懒加载策略
     * @throws Exception
     */
    @Test
    public void query() throws Exception {
        School school = schoolDao.findOne("1");
        System.out.println(school);

        Set<Student> studentList = school.getStudentList();
        System.out.println("studentList=" + studentList);
    }

    /**
     * 级联保存操作
     */
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

    /**
     * 级联合并操作
     */
    @Test
    public void merge() {
        Student student =  studentDao.findOne("1");

        student.setStudentName(student.getStudentName() + "v1.2");
        studentDao.save(student);

        School school = schoolDao.findOne("1");
        Set<Student> studentSet = school.getStudentList();
        System.out.println("studentSet=" + studentSet);
    }

    /**
     * 级联删除操作，全部删除
     */
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
     * 级联删除操作，部分删除
     */
    @Test
    public void remove1() {
        School school = schoolDao.findOne("1");

        Set<Student> studentSet = school.getStudentList();
        System.out.println("studentSet=" + studentSet);

        school.setStudentList(null);

        Set<Student> studentSet1 = school.getStudentList();
        System.out.println("studentSet1=" + studentSet1);

        schoolDao.delete(school);

        System.out.println("==================");

        School school1 = schoolDao.findOne("1");
        System.out.println("school1=" + school1);

        Set<Student> studentSet2 = school1.getStudentList();
        System.out.println("studentSet2=" + studentSet1);
    }


    /**
     * 级联刷新操作
     */
    @Test
    public void refresh() {

    }

    /**
     * 级联游离操作,如果你要删除一个实体，但是它有外键无法删除，
     * 这时候你就需要这个级联权限了。它会撤销所有相关的外键关联。
     */
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

    @Test
    public void updateStudent() {
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            student.setId("4");
            student.setStudentName("大哥4");
            studentDao.save(student);
        }
    }

}
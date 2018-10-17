package com.cmazxiaoma.service.impl;

import com.cmazxiaoma.InitSpringTest;
import com.cmazxiaoma.model.Student;
import com.cmazxiaoma.service.StudentTestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/10/17 14:35
 */
public class StudentTestServiceTest extends InitSpringTest {

    @Autowired
    private StudentTestService studentTestService;

    @Test
    public void testSave() {
        Student student = new Student();
        student.setStudentName("pengsai123");
        student.setIsDel("0");
        student.setCreatedDt(new Date());
        student.setUpdatedDt(new Date());

        studentTestService.save(student);
    }
}

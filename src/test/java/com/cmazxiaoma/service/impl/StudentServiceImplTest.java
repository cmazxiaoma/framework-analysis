package com.cmazxiaoma.service.impl;

import com.cmazxiaoma.BaseTest;
import com.cmazxiaoma.model.Student;
import com.cmazxiaoma.service.IStudentService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/6 15:49
 */
public class StudentServiceImplTest extends BaseTest {

    @Resource
    private IStudentService studentService;

    @Test
    public void findOneByV2() throws Exception {
        Student student1 = studentService.findOneByV2("1");
        System.out.println("student1=" + student1);

        Student student2 = studentService.findOneByV2("1");
        System.out.println("student2=" + student2);
    }

}
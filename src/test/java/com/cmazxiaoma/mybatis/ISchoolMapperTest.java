package com.cmazxiaoma.mybatis;

import com.cmazxiaoma.BaseTest;
import com.cmazxiaoma.model.School;
import com.cmazxiaoma.model.Student;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/5 17:14
 */
public class ISchoolMapperTest extends BaseTest {

    @Resource
    private ISchoolMapper schoolMapper;

    @Test
    public void findOneByV1() throws Exception {
        List<School> schoolList = schoolMapper.findOneByV1("1");
        System.out.println("schoolList=" + schoolList);
        System.out.println("school=" + schoolList.get(0));
        System.out.println("studentSet=" + schoolList.get(0).getStudentList());
    }

    @Test
    public void findOneByV2() throws Exception {
        School school = schoolMapper.findOneByV2("1");
        System.out.println("school=" + school);
        Set<Student> studentSet = school.getStudentList();
        System.out.println("studentSet=" + studentSet);
    }

}
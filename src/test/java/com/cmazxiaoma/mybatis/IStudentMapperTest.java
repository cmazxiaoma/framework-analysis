package com.cmazxiaoma.mybatis;

import com.cmazxiaoma.BaseTest;
import com.cmazxiaoma.model.School;
import com.cmazxiaoma.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import javax.xml.transform.Result;

import static org.junit.Assert.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/5 14:22
 */
public class IStudentMapperTest extends BaseTest {

    @Resource
    private IStudentMapper studentMapper;

    @Resource
    private SqlSession sqlSession;

    @Test
    public void findOneByV1() throws Exception {
        Student student = studentMapper.findOneByV1("1");
        System.out.println("student=" + student);

        School school = student.getSchool();
        System.out.println("school=" + school);

        Student student1 = studentMapper.findOneByV1("1");
        System.out.println("student1=" + student1);
    }

    @Test
    public void findOneByV2() {
        Student student = studentMapper.findOneByV2("1");
        System.out.println("student=" + student);
        System.out.println("=====================");
        School school = student.getSchool();
        System.out.println("school=" + school);
    }

    @Test
    public void findOneByV3() {
        Student student = studentMapper.findOneByV3("1");
        System.out.println("student=" + student);
        School school = student.getSchool();
        System.out.println("school=" + school);

        System.out.println("======================");
        Student student1 = studentMapper.findOneByV3("1");
        System.out.println("student1=" + student1);
    }

    /**
     * 深度延迟加载
     */
    @Test
    public void lazy() {
        Student student = studentMapper.findOneByV2("2");
        System.out.println("student=" + student);
        System.out.println("===================");
        System.out.println("student.id=" + student.getId());
        System.out.println("===================");

        School school = student.getSchool();
        System.out.println("school=" + school);
    }


    /**
     * 侵入式延迟加载
     */
    @Test
    public void aggressiveLazy() {
        Student student = studentMapper.findOneByV2("2");
        System.out.println("============================");
        System.out.println("student=" + student);
    }

    private static final String SCHOOLMAPPER = "com.cmazxiaoma.mybatis.ISchoolMapper.";

    private static final String STUDENTMAPPER = "com.cmazxiaoma.mybatis.IStudentMapper.";

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 一级缓存，默认开启
     */
    @Test
    public void oneLevelCache() {
        SqlSession studentSqlSession = sqlSessionFactory.openSession();

        try {
            Student student1 = studentSqlSession.selectOne(
                    STUDENTMAPPER + "findOneByV2", "1"
            );
            System.out.println("student1=" + student1);

//            studentSqlSession.update(STUDENTMAPPER + "updateIsDel", "1");

            Student student2 = studentSqlSession.selectOne(
                    STUDENTMAPPER + "findOneByV2", "1"
            );
            System.out.println("student2=" + student2);
        } finally {
            if (studentSqlSession != null) {
                studentSqlSession.close();
            }
        }
    }


    /**
     * 二级缓存，手动开启
     */
    @Test
    public void twoLevelCache() {

        SqlSession studentSqlSession1 = sqlSessionFactory.openSession();

        try {
            Student student1 = studentSqlSession1.selectOne(
                    STUDENTMAPPER + "findOneByV2", "1");
            System.out.println("student1=" + student1);
        } finally {
            if (studentSqlSession1 != null) {
                studentSqlSession1.close();
            }
        }

        SqlSession studentSqlSession2 = sqlSessionFactory.openSession();

        try {
            Student student2 = studentSqlSession2.selectOne(STUDENTMAPPER + "findOneByV2", "1");
            System.out.println("student2=" + student2);
        } finally {
            if (studentSqlSession2 != null) {
                studentSqlSession2.close();
            }
        }
    }

    @Test
    public void twoLevelCache1() {
        Student student1 = studentMapper.findOneByV2("1");
        System.out.println("student1=" + student1);

        Student student2 = studentMapper.findOneByV2("1");
        System.out.println("student2=" + student2);

        Student student3 = studentMapper.findOneByV2("1");
        System.out.println("student3=" + student3);
    }

    /**
     * Executor.query()有参数ResultHandler作为回调，可以对ResultContext做处理
     */
    @Test
    public void testResultHandler() {
        SqlSession studentSqlSession = sqlSessionFactory.openSession();

        try {
            studentSqlSession.select(
                    STUDENTMAPPER + "findOneByV2",
                    "1",
                    new ResultHandler() {
                        @Override
                        public void handleResult(ResultContext resultContext) {
                            System.out.println("=======>resultContext=" + resultContext);
                            System.out.println("========>stopped=" + resultContext.isStopped());
                            System.out.println("========>resultObject=" + resultContext.getResultObject());
                            System.out.println("========>resultCount=" + resultContext.getResultCount());
                        }
                    }
            );
        } finally {
            if (studentSqlSession != null) {
                studentSqlSession.close();
            }
        }
    }

    /**
     * 通过SqlSession获取Mapper
     */
    @Test
    public void testDynamicMapper() {
        SqlSession defaultSqlSession = sqlSessionFactory.openSession();
        IStudentMapper mapper = defaultSqlSession.getMapper(IStudentMapper.class);
        Student student = mapper.findOneByV1("1");
        System.out.println("student=" + student);
    }
}
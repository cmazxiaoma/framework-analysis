package com.cmazxiaoma.mybatis;

import com.cmazxiaoma.model.School;
import com.cmazxiaoma.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/5 13:49
 */
@Mapper
public interface IStudentMapper {

    Student findOneByV1(@Param("studentId") String studentId);

    Student findOneByV2(@Param("studentId") String studentId);

    Student findOneByV3(@Param("studentId") String studentId);

    int updateIsDel(@Param("studentId") String studentId);
}

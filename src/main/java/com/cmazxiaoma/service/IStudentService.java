package com.cmazxiaoma.service;

import com.cmazxiaoma.model.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/6 15:47
 */
public interface IStudentService {

    Student findOneByV1(@Param("studentId") String studentId);

    Student findOneByV2(@Param("studentId") String studentId);

    Student findOneByV3(@Param("studentId") String studentId);

    int updateIsDel(@Param("studentId") String studentId);

}

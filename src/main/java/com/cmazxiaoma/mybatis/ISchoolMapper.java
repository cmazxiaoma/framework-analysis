package com.cmazxiaoma.mybatis;

import com.cmazxiaoma.model.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/5 13:49
 */
@Mapper
public interface ISchoolMapper {

    List<School> findOneByV1(@Param("schoolId") String schoolId);

    School findOneByV2(@Param("schoolId") String schoolId);
}

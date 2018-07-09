package com.cmazxiaoma.service.impl;

import com.cmazxiaoma.model.Student;
import com.cmazxiaoma.mybatis.IStudentMapper;
import com.cmazxiaoma.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/6 15:46
 */
@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    @Resource
    private IStudentMapper studentMapper;

    @Override
    public Student findOneByV1(String studentId) {
        return studentMapper.findOneByV1(studentId);
    }

    @Override
    public Student findOneByV2(String studentId) {
        return studentMapper.findOneByV2(studentId);
    }

    @Override
    public Student findOneByV3(String studentId) {
        return studentMapper.findOneByV3(studentId);
    }

    @Override
    public int updateIsDel(String studentId) {
        return studentMapper.updateIsDel(studentId);
    }
}

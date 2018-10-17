package com.cmazxiaoma.service;

import com.cmazxiaoma.hibernate.IStudentDao;
import com.cmazxiaoma.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/10/17 14:33
 */
@Service
@Transactional
public class StudentTestService {

    @Autowired
    private IStudentDao studentDao;

    public void save(Student student) {
        studentDao.save(student);
        if (true) {
            throw new RuntimeException("回滚");
        }
//        Integer pengsai = Integer.parseInt(null);
    }
}

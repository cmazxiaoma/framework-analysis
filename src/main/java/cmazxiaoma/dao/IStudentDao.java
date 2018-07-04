package cmazxiaoma.dao;

import cmazxiaoma.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:56
 */
public interface IStudentDao extends JpaRepository<Student, String> {

}

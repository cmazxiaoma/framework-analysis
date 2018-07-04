package cmazxiaoma.dao;

import cmazxiaoma.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:56
 */
public interface ISchoolDao extends JpaRepository<School, String> {

}

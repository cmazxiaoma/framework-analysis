package com.cmazxiaoma.hibernate;

import com.cmazxiaoma.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:56
 */
public interface ISchoolDao extends JpaRepository<School, String> {

}

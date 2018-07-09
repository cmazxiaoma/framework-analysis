package com.cmazxiaoma.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:25
 */
@Entity
@Table(name = "tbl_school")
@Setter
@Getter
@DynamicUpdate
@DynamicInsert
public class School implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "school_name")
    private String schoolName;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
                    CascadeType.REMOVE})
    private Set<Student> studentList = new HashSet<>();

    @Column(name = "created_dt")
    private Date createdDt;

    @Column(name = "updated_dt")
    private Date updatedDt;

    @Column(name = "is_del")
    private String isDel;

    @Override
    public String toString() {
        return "School{" +
                "id='" + id + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        if (!super.equals(o)) return false;

        School school = (School) o;

        if (!getId().equals(school.getId())) return false;
        if (!getSchoolName().equals(school.getSchoolName())) return false;
        if (!getCreatedDt().equals(school.getCreatedDt())) return false;
        if (!getUpdatedDt().equals(school.getUpdatedDt())) return false;
        return getIsDel().equals(school.getIsDel());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getSchoolName().hashCode();
        result = 31 * result + getCreatedDt().hashCode();
        result = 31 * result + getUpdatedDt().hashCode();
        result = 31 * result + getIsDel().hashCode();
        return result;
    }
}

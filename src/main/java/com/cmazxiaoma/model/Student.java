package com.cmazxiaoma.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/3 17:24
 */
@Entity
@Table(name = "tbl_student")
@Setter
@Getter
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
public class Student implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "school_id", insertable = false, updatable = false)
    private String schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    // 会使懒加载失效
    @NotFound(action = NotFoundAction.IGNORE)
    private School school;

    @Column(name = "created_dt")
    private Date createdDt;

    @Column(name = "updated_dt")
    private Date updatedDt;

    @Column(name = "is_del")
    private String isDel;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (getId() != null ? !getId().equals(student.getId()) : student.getId() != null) return false;
        if (getStudentName() != null ? !getStudentName().equals(student.getStudentName()) : student.getStudentName() != null)
            return false;
        if (getSchoolId() != null ? !getSchoolId().equals(student.getSchoolId()) : student.getSchoolId() != null)
            return false;
        if (getSchool() != null ? !getSchool().equals(student.getSchool()) : student.getSchool() != null) return false;
        if (getCreatedDt() != null ? !getCreatedDt().equals(student.getCreatedDt()) : student.getCreatedDt() != null)
            return false;
        if (getUpdatedDt() != null ? !getUpdatedDt().equals(student.getUpdatedDt()) : student.getUpdatedDt() != null)
            return false;
        return getIsDel() != null ? getIsDel().equals(student.getIsDel()) : student.getIsDel() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getStudentName() != null ? getStudentName().hashCode() : 0);
        result = 31 * result + (getSchoolId() != null ? getSchoolId().hashCode() : 0);
        result = 31 * result + (getSchool() != null ? getSchool().hashCode() : 0);
        result = 31 * result + (getCreatedDt() != null ? getCreatedDt().hashCode() : 0);
        result = 31 * result + (getUpdatedDt() != null ? getUpdatedDt().hashCode() : 0);
        result = 31 * result + (getIsDel() != null ? getIsDel().hashCode() : 0);
        return result;
    }
}

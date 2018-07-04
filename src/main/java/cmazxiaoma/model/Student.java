package cmazxiaoma.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.InitializingBean;

import javax.persistence.*;
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
public class Student {

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

        if (!getId().equals(student.getId())) return false;
        if (!getStudentName().equals(student.getStudentName())) return false;
        if (!getSchoolId().equals(student.getSchoolId())) return false;
        if (!getCreatedDt().equals(student.getCreatedDt())) return false;
        if (!getUpdatedDt().equals(student.getUpdatedDt())) return false;
        return getIsDel().equals(student.getIsDel());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getStudentName().hashCode();
        result = 31 * result + getSchoolId().hashCode();
        result = 31 * result + getCreatedDt().hashCode();
        result = 31 * result + getUpdatedDt().hashCode();
        result = 31 * result + getIsDel().hashCode();
        return result;
    }
}

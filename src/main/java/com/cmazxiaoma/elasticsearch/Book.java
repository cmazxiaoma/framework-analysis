package com.cmazxiaoma.elasticsearch;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Update;
import org.hibernate.exception.DataException;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/10/24 16:09
 */
@Setter
@Getter
public class Book {

    @NotBlank(message = "id required", groups = Updated.class)
    public String id;

    @NotBlank(message = "title required", groups = Added.class)
    public String title;

    @NotBlank(message = "author required", groups = Added.class)
    public String author;

    @NotBlank(message = "wordCount required", groups = Added.class)
    public String wordCount;

    @NotNull(message = "publishDate required", groups = Added.class)
    public Date publishDate;
}

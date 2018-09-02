package com.cmazxiaoma.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/4 18:13
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Son extends Father {

    private String sonName;

}

package com.cmazxiaoma.hystrix;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/6/3 16:01
 */
@Data
@Accessors(chain = true)
public class Admin {

    private Long id;
    private String name;
}

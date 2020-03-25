package com.cmazxiaoma.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/7 17:55
 */
@Data
@AllArgsConstructor
public class PhonePrdDto implements Serializable {

    // 设备号
    private String phoneId;
    // 产品号
    private String prdId;

    private Long userId;

}

package com.cmazxiaoma.caiji;

import lombok.Data;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.omg.CORBA.StringHolder;

import java.io.Serializable;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/24 0:08
 */
@Data
public class Product implements Serializable {

    // 商品标题
    private String title;

    // 商品图片
    private String imgUrl;

    // 价格
    private String price;

    // 促销字段
    private String promoWords;

    // 评论
    private String commit;

    // 店铺名称
    private String shopName;

}

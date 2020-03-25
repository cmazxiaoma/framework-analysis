package com.cmazxiaoma.coupon;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/8 20:06
 */
@Data
public class CouponTextVo implements Serializable {

    /**
     *   -- couponId 优惠券ID
         -- couponExpireStartTime 优惠券有效期开始时间
         -- couponExpireEndTime 优惠券有效期结束时间
         -- couponRedirectType  优惠券跳转类型, 0是点击券面直接领取, 1是跳转链接, 2是跳转商详
         -- couponRedirectUrl 优惠券跳转路由
         -- couponRedirectSourceId 优惠券跳转商详, 商品ID
     */

    private Long couponId;
    private Date couponExpireStartTime;
    private Date couponExpireEndTime;
    private Integer couponRedirectType;
    private String couponRedirectUrl;
    private String couponRedirectSourceId;


}

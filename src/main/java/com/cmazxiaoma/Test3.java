package com.cmazxiaoma;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/7 17:48
 */
public class Test3 {

    public static void main(String[] args) {
        double str = 0.10;
        System.out.println(str > 0.00D);


        double addMoneyCount = 10000 * 0.23D;
        System.out.println(new BigDecimal(addMoneyCount).intValue());

        System.out.println(new BigDecimal(100000).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println(0.00D/10000);

        BigDecimal rate = new BigDecimal(1).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(rate);

        System.out.println(System.nanoTime());

        long key = 0;
        System.out.println(Objects.equals(key, 0L));

        String defaultAction = "vipgift://com.xmiles.vipgift/mall/ProductDetailActivity?shopType=0&shopParams={\"pageType\":\"2\",\"id\":\"SOURCE_ID_TEMPLATE\"}";
        defaultAction = defaultAction.replace("SOURCE_ID_TEMPLATE", "12345");

        System.out.println(defaultAction);

    }
}

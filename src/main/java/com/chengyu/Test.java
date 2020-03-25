package com.chengyu;

import com.cmazxiaoma.quzhuanxiang.DateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/14 15:07
 */
public class Test {

    public static void main(String[] args) {
        // 1个小时 3个体力 10个小时 30个体力 100个小时 300个体力
        System.out.println(DateUtils.addHours(new Date(), -100).getTime());

        // {"data":[{"key":"key_tilitime","value":"1578982820745"}],"session_id":"u11XckV6RQRwM531v6Jqd"}

        System.out.println(DateFormatUtils.format(new Date(1578986420745L), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateFormatUtils.format(new Date(1578982820745L), "yyyy-MM-dd HH:mm:ss"));


        List<Integer> list = Lists.newArrayList(7,2,1);
        Collections.sort(list);
        System.out.println(list);
    }
}

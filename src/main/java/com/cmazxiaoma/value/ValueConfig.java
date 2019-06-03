package com.cmazxiaoma.value;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/16 10:37
 */
@Component
@Data
public class ValueConfig {

    @Value("#{'${my.mail}'.split(',')}")
    private Collection<String> list;

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()).toString());

        System.out.println("=============================");
        List<Integer> integerList = Lists.newArrayList();

        // 学习lambda
        Integer integer = Optional.ofNullable(integerList)
                .map(Collection::stream).orElseGet(Stream::empty)
                .findFirst()
                .map(Integer::intValue).orElseGet(() -> null);
        System.out.println("integer=" + integer);

    }
}

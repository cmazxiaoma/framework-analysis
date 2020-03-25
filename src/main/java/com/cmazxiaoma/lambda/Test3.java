package com.cmazxiaoma.lambda;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/7 17:55
 */
public class Test3 {

    public static void main(String[] args) {
        List<PhonePrdDto> list = Lists.newArrayList();
        list.add(new PhonePrdDto("dc03debda990e301", "14000", -1L));
        list.add(new PhonePrdDto("dc03debda990e301", "14300", -1L));
        list.add(new PhonePrdDto("dc03debda990e301", "14400", -1L));

        list.add(new PhonePrdDto("dc03debda990e301", "14000", -1L));
        list.add(new PhonePrdDto("dc03debda990e301", "14000", 1L));
        list.add(new PhonePrdDto("dc03debda990e301", "14000", 2L));
        list.add(new PhonePrdDto("dc03debda990e301", "14000", null));

        Map<String, Set<String>> prdMap = list.stream().collect(Collectors.groupingBy(PhonePrdDto::getPrdId, Collectors.mapping(PhonePrdDto::getPhoneId, Collectors.toSet())));


        SetMultimap<String, Long> setMultimap = HashMultimap.create();

        list.forEach(fruit -> {
            if (!Objects.isNull(fruit.getUserId()) && fruit.getUserId() > 0L) {
                setMultimap.put(fruit.getPhoneId() +  "_" + fruit.getPrdId(), fruit.getUserId());
            }
        });

        System.out.println("prdMap:" + prdMap);
        System.out.println("multiMap:" + setMultimap);

        Set<Long> userIdList = setMultimap.get("dc03debda990e301_14300");
        System.out.println("userIdList:" + userIdList);
    }
}

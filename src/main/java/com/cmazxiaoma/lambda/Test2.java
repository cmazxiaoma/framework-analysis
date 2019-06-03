package com.cmazxiaoma.lambda;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dyuproject.protostuff.MapSchema.MessageFactories.HashMap;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/23 21:03
 */
public class Test2 {

    public static void main(String[] args) {
        List<Product> productList = Lists.newArrayList();
        productList.add(new Product(1, 1));
        productList.add(new Product(1, 2));
        productList.add(new Product(2, 2));
        productList.add(new Product(3, 3));

        Map<Integer, Integer> map = productList.stream()
                .collect(Collectors.toMap(Product::getId, Product::getCount,
                        (old, newBaby) -> newBaby));
        System.out.println(map);

        Map<Integer, Long> productMap =
                productList.stream().collect(Collectors.groupingBy(Product::getId,
                        Collectors.counting()));
        System.out.println(productMap);

        Map<Integer, List<Product>> map2 = productList.stream().collect(Collectors
                .groupingBy(Product::getId));
        System.out.println(map2);

        List<Integer> list = Lists.newArrayList();
        map2.entrySet().forEach(
                integerListEntry -> {
                    list.add(integerListEntry.getKey());
                }
        );
        System.out.println(list);

        Stream.iterate(0 , i -> i + 1).limit(list.size())
                .forEach(i -> {
                    Integer integer = list.get(i);
                    System.out.println(integer);
                });
    }

    @Data
    @AllArgsConstructor
    public static class Product {
        private Integer id;
        private Integer count;
    }
}

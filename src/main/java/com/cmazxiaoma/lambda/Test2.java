package com.cmazxiaoma.lambda;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
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

        System.out.println("=================================");


        String str = productList.stream().map(product -> {
            return String.valueOf(product.getId());
        }).collect(Collectors.joining(",", "{", "}"));

        System.out.println("str=" + str);

        List<Employee> employeeList = Lists.newArrayList();
        employeeList.add(new Employee("name1", "后端1组"));
        employeeList.add(new Employee("name2", "后端1组"));

        employeeList.add(new Employee("name3", "后端2组"));
        employeeList.add(new Employee("name4", "后端2组"));


        Map<String, List<String>> resultMap = employeeList.stream().collect(
                Collectors.groupingBy(Employee::getDepartmentName,
                        Collectors.mapping(Employee::getUsername,
                                Collectors.toList()))
        );

        System.out.println(resultMap);
    }

    @Data
    @AllArgsConstructor
    public static class Product {
        private Integer id;
        private Integer count;
    }

    @Data
    @AllArgsConstructor
    public static class Employee {
        private String username;
        private String departmentName;
    }
}

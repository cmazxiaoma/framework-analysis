package com.cmazxiaoma.hystrix;


import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Collection;
import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/6/3 16:07
 */
public class BeanCopierTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Admin admin = new Admin().setId(1L).setName("cmazxiaoma");
        List<Admin> adminList = Lists.newArrayList();
        adminList.add(admin);
        adminList.add(new Admin().setId(2L).setName("cmazxiaoma1"));

        List<AdminDto> adminDtoList = Lists.newArrayList();

        adminDtoList = convertDTO(adminList, Admin.class, AdminDto.class);
        System.out.println(adminDtoList);
    }


    public static <D> List<D> convertDTO(Collection sourceList,
                                         Class sourceClass,
                                         Class<D> destinationClass)
            throws IllegalAccessException, InstantiationException {

        BeanCopier beanCopier = BeanCopier.create(
                sourceClass,
                destinationClass,
                false
        );

        List<D> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            D instance = destinationClass.newInstance();
            beanCopier.copy(sourceObject, instance, null);
            destinationList.add(instance);
        }
        return destinationList;
    }
}

package com.cmazxiaoma.hystrix;


import org.springframework.cglib.beans.BeanCopier;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/6/3 16:07
 */
public class BeanCopierTest {

    public static void main(String[] args) {
        Admin admin = new Admin().setId(1L).setName("cmazxiaoma");
        AdminDto adminDto = new AdminDto();
        BeanCopier beanCopier = BeanCopier.create(
                Admin.class,
                AdminDto.class,
                false
        );
        beanCopier.copy(admin, adminDto, null);
        System.out.println(adminDto);
    }
}

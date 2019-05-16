package com.cmazxiaoma.controller.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/17 23:21
 */
public class MyReflectionUtilsTest {
    
    public static void testGetDeclaredMethod() {

        Object obj = new Son() ;

        Method publicMethod = MyReflectionUtils.getDeclaredMethod(obj, "publicMethod") ;
        System.out.println(publicMethod.getName());

        Method defaultMethod = MyReflectionUtils.getDeclaredMethod(obj, "defaultMethod") ;
        System.out.println(defaultMethod.getName());

        Method protectedMethod = MyReflectionUtils.getDeclaredMethod(obj, "protectedMethod") ;
        System.out.println(protectedMethod.getName());

        Method privateMethod = MyReflectionUtils.getDeclaredMethod(obj, "privateMethod") ;
        System.out.println(privateMethod.getName());
    }

    /**
     * 测试调用     * @throws Exception 
     */
    
    public static void testInvokeMethod() throws Exception {
        Object obj = new Son() ;

        //调用        MyReflectionUtils.invokeMethod(obj, "publicMethod", null , null) ;  

        //调用        MyReflectionUtils.invokeMethod(obj, "defaultMethod", null , null) ;  

        //调用        MyReflectionUtils.invokeMethod(obj, "protectedMethod", null , null) ;  

        //调用        MyReflectionUtils.invokeMethod(obj, "privateMethod", null , null) ;  
    }

    /**
     * 测试获父类的各个属性名 
     */
    
    public static void testGetDeclaredField() {

        Object obj = new Son() ;

        Field publicField = MyReflectionUtils.getDeclaredField(obj, "publicField") ;
        System.out.println(publicField.getName());

        Field defaultField = MyReflectionUtils.getDeclaredField(obj, "defaultField") ;
        System.out.println(defaultField.getName());

        Field protectedField = MyReflectionUtils.getDeclaredField(obj, "protectedField") ;
        System.out.println(protectedField.getName());

        Field privateField = MyReflectionUtils.getDeclaredField(obj, "privateField") ;
        System.out.println(privateField.getName());

    }
    
    public static void testSetFieldValue() {

        Object obj = new Son() ;


        System.out.println("原来的各个属性的值: ");
        System.out.println("publicField = " + MyReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + MyReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + MyReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + MyReflectionUtils.getFieldValue(obj, "privateField"));

        MyReflectionUtils.setFieldValue(obj, "publicField", "a") ;
        MyReflectionUtils.setFieldValue(obj, "defaultField", "b") ;
        MyReflectionUtils.setFieldValue(obj, "protectedField", "c") ;
        MyReflectionUtils.setFieldValue(obj, "privateField", "d") ;

        System.out.println("***********************************************************");

        System.out.println("将属性值改变后的各个属性值: ");
        System.out.println("publicField = " + MyReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + MyReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + MyReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + MyReflectionUtils.getFieldValue(obj, "privateField"));

    }
    
    public static void testGetFieldValue() {

        Parent obj = new Son() ;

        System.out.println("publicField = " + MyReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + MyReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + MyReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + MyReflectionUtils.getFieldValue(obj, "privateField"));
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        testSuperClassInnerClass();
    }

    public static void testSuperClassInnerClass() throws NoSuchFieldException, IllegalAccessException {
        Parent obj = new Parent();

        Class[] innerClazzs = obj.getClass().getDeclaredClasses();

        for (Class innerClazz : innerClazzs) {
            int modifier = innerClazz.getModifiers();
            String modifierName = Modifier.toString(modifier);

            if ("static".equals(modifierName) &&
                    "LocalMap".equals(innerClazz.getSimpleName())) {
                Field field = innerClazz.getDeclaredField("table");
                field.setAccessible(true);
                System.out.println(field.get(obj));
            }
        }
    }
}

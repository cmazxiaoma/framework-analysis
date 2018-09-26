/**
 * 文件名：MyHibernateDaoSupport.java</br>
 * 描述： </br>
 * 开发人员：wutao </br>
 * 创建时间： 2018-9-26
 */

package com.cmazxiaoma.spring.inject;


/**
 * 类名: MyHibernateDaoSupport</br>
 * 描述: </br>
 * 开发人员： wutao</br>
 * 创建时间： 2018-9-26
 */
public class MyHibernateDaoSupport {

    private String template;

    /**
     * 描述： 设置 mySessionFactory</br>
     * @param mySessionFactory
     */

    public void setMySessionFactory(MySessionFactory mySessionFactory) {
        createTemplate(mySessionFactory);
    }

    public void createTemplate(MySessionFactory mySessionFactory) {
        this.template = mySessionFactory.getName();
    }

    public String getTemplate() {
        return this.template;
    }
}

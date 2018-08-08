package com.cmazxiaoma.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 13:30
 */
public abstract class BaseController {

    @Autowired
    protected HttpSession httpSession;

    @Autowired
    protected HttpServletRequest request;

}

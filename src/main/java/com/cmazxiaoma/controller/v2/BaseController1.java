package com.cmazxiaoma.controller.v2;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 16:15
 */
public abstract class BaseController1 {

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession httpSession;

    @ModelAttribute
    public void init(HttpServletRequest request,
                     HttpServletResponse response,
                     HttpSession httpSession) {
        this.request = request;
        this.response = response;
        this.httpSession = httpSession;
    }
}

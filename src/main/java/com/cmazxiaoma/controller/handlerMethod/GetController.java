package com.cmazxiaoma.controller.handlerMethod;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/7 0:22
 */
@RestController
@RequestMapping("/test")
public class GetController {

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name = "id") String id) {
        return id;
    }

    @InitBinder("user")
    public void initUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");

    }

    @InitBinder("admin")
    public void initAdmin(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("admin.");
    }

}

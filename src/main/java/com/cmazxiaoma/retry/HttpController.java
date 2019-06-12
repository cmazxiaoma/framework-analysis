package com.cmazxiaoma.retry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/6/12 12:26
 */
@RestController
@RequestMapping("/http")
public class HttpController {

    private LongAdder longAdder = new LongAdder();

    @GetMapping("/demo")
    public String demo() {
        longAdder.increment();

        if (longAdder.intValue() % 4 == 0) {
            System.out.println(longAdder.intValue() + ":success");
            return "success";
        } else {
            System.out.println(longAdder.intValue() + ":fail");
//            throw new RuntimeException("fail");
            return "fail";
        }
    }
}

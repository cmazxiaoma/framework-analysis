package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 11:01
 */
public class UserBatchCommand extends HystrixCommand<List<HystrixTest.User>> {

    private UserService userService;

    private List<Long> userIds;

    public UserBatchCommand(UserService userService,
                            List<Long> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
                .asKey("userBatchCommand")));
        this.userIds = userIds;
        this.userService = userService;
    }

    @Override
    protected List<HystrixTest.User> run() throws Exception {
        System.out.println("=============Hystrix批量处理请求。。。================");
        return userService.findAll(userIds);
    }
}

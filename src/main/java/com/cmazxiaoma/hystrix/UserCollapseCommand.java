package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 10:44
 */
public class UserCollapseCommand extends HystrixCollapser<List<HystrixTest.User>, HystrixTest.User, Long> {

    private UserService userService;

    private Long userId;

    public UserCollapseCommand(UserService userService, Long userId) {
        super(Setter.withCollapserKey(
                HystrixCollapserKey.Factory.asKey("userCollapseCommand")
        ).andCollapserPropertiesDefaults(
                HystrixCollapserProperties.Setter()
                .withTimerDelayInMilliseconds(3000)
        ));
        this.userId = userId;
        this.userService = userService;
    }

    @Override
    public Long getRequestArgument() {
        return userId;
    }

    @Override
    protected HystrixCommand<List<HystrixTest.User>> createCommand(Collection<CollapsedRequest<HystrixTest.User, Long>> collapsedRequests) {
        List<Long> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream()
        .map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService, userIds);
    }

    @Override
    protected void mapResponseToRequests(List<HystrixTest.User> batchResponse, Collection<CollapsedRequest<HystrixTest.User, Long>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<HystrixTest.User, Long> collapsedRequest : collapsedRequests) {
            HystrixTest.User user = batchResponse.get(count++);
            collapsedRequest.setResponse(user);
        }
    }
}

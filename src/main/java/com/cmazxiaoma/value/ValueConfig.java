package com.cmazxiaoma.value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/16 10:37
 */
@Component
@Data
public class ValueConfig {

    @Value("#{'${my.mail}'.split(',')}")
    private Collection<String> list;
}

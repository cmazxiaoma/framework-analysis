package com.cmazxiaoma.concurrent.blockingqueue;

import org.springframework.web.util.HtmlUtils;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/27 18:05
 */
public class PriorityTask implements Comparable<PriorityTask> {

    private Integer num;

    public PriorityTask(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public int compareTo(PriorityTask item) {
        if (this.getNum() > item.getNum()) {
            return 1;
        } else if (this.getNum() < item.getNum()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {

        return "PriorityTask{" +
                "num=" + num +
                '}';
    }
}

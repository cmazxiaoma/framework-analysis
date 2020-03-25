package com.cmazxiaoma.lotteryTest;

import java.io.Serializable;

public class Mall11LotteryResultVo implements Serializable {

    private Long goodId;

    private String goodName;

    private String goodImgUrl;


    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodImgUrl() {
        return goodImgUrl;
    }

    public void setGoodImgUrl(String goodImgUrl) {
        this.goodImgUrl = goodImgUrl;
    }
}
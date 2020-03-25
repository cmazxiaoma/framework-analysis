package com.cmazxiaoma.lotteryTest;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/24 15:53
 */
public class Payload {
    private long costTime;
    private Result result ;
    private Mall11LotteryResultVo data;

    public Payload() {
        this.result = new Result(1);
    }

    public Payload(Mall11LotteryResultVo data) {
        this();
        this.data = data;
    }

    public Payload(long costTime){
        this();
        this.costTime = costTime;
    }

    public static Payload emptyPayload(){
        return new Payload(null);
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Mall11LotteryResultVo getData() {
        return data;
    }

    public void setData(Mall11LotteryResultVo data) {
        this.data = data;
    }



    public static class Result{
        private int status;
        private int errorcode;
        private String msg;

        public Result(int status) {
            this.status = status;
        }

        public Result(int status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Result() {
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getErrorcode() {
            return errorcode;
        }

        public void setErrorcode(int errorcode) {
            this.errorcode = errorcode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}

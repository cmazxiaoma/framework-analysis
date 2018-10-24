package com.cmazxiaoma.elasticsearch;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    //成功
    SUCCESS(200, "success"),

    //失败
    FAIL(400, "fail"),

    //未认证（签名错误）
    UNAUTHORIZED(401, "unauthorized"),

    //接口不存在
    NOT_FOUND(404, "not found"),

    //服务器内部错误
    INTERNAL_SERVER_ERROR(500, "internal_server_error"),

    //拒接访问
    FORBIDDEN(401, "您没有权限访问该资源"),

    //账号错误
    USERNAME_ERROR(1, "对不起，您的账号错误，请检查后再登陆！"),

    //账号被禁用，请联系管理员
    ACCOUNT_DISABLED(2, "账号被禁用，请联系管理员"),

    //登录失败
    LOGIN_FAILED(3, "登录失败"),

    //非法参数
    ILLEGAL_PARAMETERS(4, "非法参数"),

    //查询无记录
    QUERY_NO_DATA(5, "查询无信息"),

    PASSWORD_ERROR(6, "对不起，您的密码错误，请检查后再登陆！"),

    ACCOUNT_LOCAKED(7, "账号被锁定, 请联系管理员"),

    //未知错误
    UNKNOWN_ERROR(8, "未知错误"),

    //Session失效
    INVALID_SESSION(9, "session已失效"),

    ACCOUNT_LOGIN_ELSEWHERE(10, "账号已在其他地方登录，被挤下线！"),

    REQUEST_METHOD_ERROR(11, "请求方式错误"),

    ARGUMENT_TYPE_MISMATCH_ERROR(12, "参数类型不匹配！"),

    REQUIRED_PARAM_EMPTY(13, "必选参数为空！"),

    PARAM_FORMAT_ERROR(14, "参数格式错误！");


    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

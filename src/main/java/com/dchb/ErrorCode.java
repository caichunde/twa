package com.dchb;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

public enum ErrorCode implements IErrorCode {
	OK("200", "请求成功"),Reset("205","重置表单"),BadReq("400","请求参数错误"),Unauthorized("401","当前请求需要用户验证"),
	NotFound("404","请求失败"),ReqTimeOut("408","请求超时"),ReqUrl("414","请求地址长度超长"),InternalErr("500","服务器逻辑错误"),
	SignErr("401","签名错误");
	
    private String code;
    private String msg;

    ErrorCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

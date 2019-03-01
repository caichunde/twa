package com.dchb.config;

import com.dchb.util.IReturn;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public IReturn errorHandler(Exception ex) {
        return new IReturn(false, ex.getMessage());
    }
}

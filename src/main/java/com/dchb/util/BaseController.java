package com.dchb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
	public void initBinder(WebDataBinder binder){ 	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");    
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,true));
	}
	
	/**
	 * 返回成功信息
	 * @param data 传递给前端的数据
	 * @return msg
	 */
	protected IReturn success() {
		return new IReturn(true,"0","操作成功");
	}
	/**
	 * 返回成功信息
	 * @param data 传递给前端的数据
	 * @return msg
	 */
	protected IReturn success(Object data) {
		return new IReturn(true,"0",data);
	}
	/**
	 * 返回错误提示信息
	 * @param data 传递给前端的数据
	 * @return msg
	 */
	protected IReturn fail(Object data) {
		
		return new IReturn(false,"2",data);
	}
	/**
	 * 判断http请求是否是ajax请求
	 * @param request
	 * @param model
	 */
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	//获取request
	public static HttpServletRequest getRequest() { 
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		return attrs.getRequest(); 
	}
}

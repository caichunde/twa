package com.dchb.util;

public class ReturnMsg {

	private boolean success;
	private String code;  
	private String message;
	private Object data="执行成功";
	public ReturnMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public ReturnMsg(boolean success,Object result){
		this.success=success;
		if(result==null)
			this.setData("[]");
		else
		  this.setData(result);
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public ReturnMsg setData(Object data) {
		this.data = data;
		return this;
	}

	public String getCode() {
		return code;
	}

	public ReturnMsg setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ReturnMsg setMessage(String message) {
		this.message = message;
		return this;
	}

}

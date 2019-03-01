package com.dchb.util;

public class IReturn {

    private boolean success;
    private String iReturn;
    private Object sReturnData;

    public IReturn() {
        // TODO Auto-generated constructor stub
    }

    public IReturn(boolean success, String iReturn, Object sReturnData) {
        this.success = success;
        this.iReturn = iReturn;
        if (sReturnData == null) {
            this.setsReturnData("[]");
        } else {
            this.setsReturnData(sReturnData);
        }
    }

    public IReturn(boolean success, String iReturn) {
        this.success = success;
        this.iReturn = iReturn;
    }


    public IReturn(boolean success, Object sReturnData) {
        this.success = success;
        this.sReturnData = sReturnData;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getiReturn() {
        return iReturn;
    }

    public void setiReturn(String iReturn) {
        this.iReturn = iReturn;
    }

    public Object getsReturnData() {
        return sReturnData;
    }

    public void setsReturnData(Object sReturnData) {
        this.sReturnData = sReturnData;
    }

}

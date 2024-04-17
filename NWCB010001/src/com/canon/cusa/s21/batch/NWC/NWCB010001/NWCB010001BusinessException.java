package com.canon.cusa.s21.batch.NWC.NWCB010001;

import java.io.Serializable;

public class NWCB010001BusinessException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;
    //Number
    private String number = null;
    //Message ID
    private String msgId = null;
    //Message Parameter
    private String[] msgParam = null;
    
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getMsgId() {
        return this.msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String[] getMsgParam() {
        return this.msgParam;
    }
    public void setMsgParam(String[] msgParam) {
        this.msgParam = msgParam;
    }
}

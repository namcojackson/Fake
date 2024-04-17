package com.canon.cusa.s21.batch.NFD.NFDB002001;

/**
 * ErroMessage Class
 * @author h00180
 * @memo From ErrorMessage to NFDB002001_ErrorMessage
 */
public class NFDB002001_ErrorMessage {

    /** msgId */
    private String msgId;

    /** msgTxt */
    private String[] msgTxt;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String[] getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String[] msgTxt) {
        this.msgTxt = msgTxt;
    }
}

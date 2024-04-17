package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.List;

/**
 * NWXC100001CalcSchdData
 */
public class NWXC100001CalcSchdData {

    /** Message Id */
    private String msgId;

    /** Delivery Date List */
    private List<String> delyDtList;

    /**
     * Get Message Id
     * @return msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Set Message Id
     * @param msgId String
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * Get Delivery Date List
     * @return msgId
     */
    public List<String> getDelyDtList() {
        return delyDtList;
    }

    /**
     * Set Delivery Date List
     * @param delyDtList List<String>
     */
    public void setDelyDtList(List<String> delyDtList) {
        this.delyDtList = delyDtList;
    }

    /**
     * Add Delivery Date List
     * @param delyDt String
     */
    public void addDelyDtList(String delyDt) {
        this.delyDtList.add(delyDt);
    }
}

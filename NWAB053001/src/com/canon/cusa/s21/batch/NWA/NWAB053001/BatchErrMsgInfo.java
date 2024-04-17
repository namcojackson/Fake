/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB053001;
import java.io.Serializable;

/**
 * <pre>
 * Create All Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 12/14/2018   FUJITSU         Mz.Takahashi    CREATE          QC#29024
 *</pre>
 */
public class BatchErrMsgInfo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** messageId */
    private String messageId;

    /** insertStr */
    private String[] insertStr;

    /** BatchErrMsgInfo */
    public BatchErrMsgInfo() {

    }

    /**
     * getInsertStr
     * @return String[]
     */
    public String[] getInsertStr() {
        return insertStr;
    }

    /**
     * setInsertStr
     * @param insertStr String[]
     */
    public void setInsertStr(String[] insertStr) {
        this.insertStr = insertStr;
    }

    /**
     * getMessageId
     * @return String
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * setMessageId
     * @param messageId String
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}

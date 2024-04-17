/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB125001;

import java.util.Map;

import business.db.AR_RCPTTMsg;
import business.db.CUST_CR_PRFLTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * QueryClass.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009   Canon           H.Tokunaga      Create          N/A
 * 07/31/2010   Canon           I.Kondo         Update          Merge.
 *</pre>
 */
public final class NFCB125001Query extends S21SsmEZDQuerySupport {

    /** MYINSTANCE */
    private static final NFCB125001Query MYINSTANCE = new NFCB125001Query();

    /**
     * Construction
     */
    private NFCB125001Query() {
    }

    /**
     * get MYINSTANCE.
     * @return MYINSTANCE Instance
     */
    public static NFCB125001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_RCPTTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFinalRcptDt(Map<String, Object> param, AR_RCPTTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getFinalRcptDt", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg CUST_CR_PRFLTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArBalAmt(Map<String, Object> param, CUST_CR_PRFLTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArBalAmt", param, tMsg);
    }

}

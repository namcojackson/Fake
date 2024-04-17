/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Daily Option Information Entry Batch QUERY class.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/25/2009   Canon           H.Tokunaga        Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB271002;

import java.util.Map;

import business.db.AR_TRX_BALTMsg;
import business.db.AR_DLY_OP_INFOTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * QUERY class.
 * @author Administrator
 */
public final class NFCB271002Query extends S21SsmEZDQuerySupport {

    /** MYINSTANCE. */
    private static final NFCB271002Query MYINSTANCE = new NFCB271002Query();

    /**
     * construction.
     */
    private NFCB271002Query() {
    }

    /**
     * get MYINSTANCE.
     */
    public static NFCB271002Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param param String
     * @param tMsg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getThruArTrxBalPkTMsgList(final Map<String, Object> param, final AR_DLY_OP_INFOTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getThruArTrxBalPk", param, tMsg);
    }
    
    /**
     * @param param String
     * @param tMsg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArTrxBalPkTMsgList(final Map<String, Object> param, final AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArTrxBalPk", param, tMsg);
    }

}

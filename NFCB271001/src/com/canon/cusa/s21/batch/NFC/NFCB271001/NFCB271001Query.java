/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB271001;

import java.util.Map;

import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Account Period Update QUERY class.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   Canon           H.Tokunaga        Create          N/A
 *</pre>
 */
public final class NFCB271001Query extends S21SsmEZDQuerySupport {

    /** MYINSTANCE. */
    private static final NFCB271001Query MYINSTANCE = new NFCB271001Query();

    /**
     * construction.
     */
    private NFCB271001Query() {
    }

    /**
     * get MYINSTANCE.
     */
    public static NFCB271001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param param String
     * @param tMsg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArAcctDtTMsgList(final Map<String, Object> param, final AR_ACCT_DTTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArAcctDt", param, tMsg);
    }

}

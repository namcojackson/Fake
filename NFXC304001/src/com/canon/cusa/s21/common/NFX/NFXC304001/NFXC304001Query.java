/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/18/2009   Canon           Y.Kondo         Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC304001;

import java.util.Map;

import business.db.AR_RCPT_IN_PROC_WRKTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 */
public final class NFXC304001Query extends S21SsmEZDQuerySupport {

    /* */
    private static final NFXC304001Query MYINSTANCE = new NFXC304001Query();

    /**
     */
    private NFXC304001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NFXC304001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * <pre>
     * </pre>

     * @param param Map<String, Object>
     * @param tMsg AR_RCPT_RCV_WRKTMsg
     * @return AR_RCPT_RCV_WRKTMsg
     */
    public S21SsmEZDResult getRcvFuncId(Map<String, Object> param, AR_RCPT_RCV_WRKTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getRcvFuncId", param, tMsg);
    }

    /**
     * <pre>
     * </pre>

     * @param param Map<String, Object>
     * @param tMsg AR_RCPT_IN_PROC_WRKTMsg
     * @return AR_RCPT_IN_PROC_WRKTMsg
     */
    public S21SsmEZDResult getEdiSendBankCd(Map<String, Object> param, AR_RCPT_IN_PROC_WRKTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getEdiSendBankCd", param, tMsg);
    }
}

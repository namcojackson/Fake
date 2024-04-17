/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/17/2009   Canon      M.Moriyama   Create          N/A
 * 09/09/2010   Fujitsu    T.Tanaka     Update          DefID:8075
 * 09/28/2010   Fujitsu    T.Tanaka     Update          DefID:8053
 * 10/08/2010   Fujitsu    T.Tanaka     Update          Merge R2->R3
 * 10/28/2010   Canon      T.Tanaka     Update          DefID M57
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC302001;

import java.util.Map;

import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.CUST_CR_PRFLTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 */
public final class NFXC302001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NFXC302001Query MYINSTANCE = new NFXC302001Query();

    /**
     */
    private NFXC302001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NFXC302001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_RCPTTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcptEzdData(Map<String, Object> param, AR_RCPTTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArRcptEzdData", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArTrxBalEzdData(Map<String, Object> param, AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArTrxBalEzdData", param, tMsg);
    }

    /**
     * Don't Use this Method
     * @param tMsg CUST_CR_PRFLTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCustCrPrfl(CUST_CR_PRFLTMsg tMsg) {
        return new S21SsmEZDResult();
        //return getSsmEZDClient().queryObject("findCustCrPrfl", tMsg);
    }
}

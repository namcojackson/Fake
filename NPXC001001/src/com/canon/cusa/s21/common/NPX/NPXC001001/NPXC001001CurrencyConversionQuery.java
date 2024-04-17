/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.Map;

import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            Hisashi         Create          N/A
 *</pre>
 */
public final class NPXC001001CurrencyConversionQuery extends S21SsmEZDQuerySupport {

    /** */
    private static final NPXC001001CurrencyConversionQuery MYINSTANCE = new NPXC001001CurrencyConversionQuery();

    /**
     */
    private NPXC001001CurrencyConversionQuery() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NPXC001001CurrencyConversionQuery getInstance() {
        return MYINSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * @param param Map<String, Object>
     * @param tMsg ACCT_DLY_ACTL_EXCH_RATESTMsg
     * @return ACCT_DLY_ACTL_EXCH_RATESTMsg
     */
    public S21SsmEZDResult getActlExchRate(Map<String, Object> param, ACCT_DLY_ACTL_EXCH_RATESTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getActlExchRate", param, tMsg);
    }
}

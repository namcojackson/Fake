/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/18/2009   Canon           Y.Kondo         Create          N/A
 * 06/18/2010   Canon           Y.Aikawa        Update          N/A
 * 07/16/2010   Canon           Y.Aikawa        Update          N/A
 * 10/26/2010   Canon           I.Kondo         Update          DefID:M57
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC307001;

import java.util.Map;

import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 */
public final class NFXC307001Query extends S21SsmEZDQuerySupport {

    /* */
    private static final NFXC307001Query MYINSTANCE = new NFXC307001Query();

    /**
     */
    private NFXC307001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NFXC307001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param param Map<String, Object>
     * @param tMsg ACCT_DLY_ACTL_EXCH_RATESTMsg
     * @return ACCT_DLY_ACTL_EXCH_RATESTMsg
     */
    public S21SsmEZDResult getActlExchRate(Map<String, Object> param, ACCT_DLY_ACTL_EXCH_RATESTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getActlExchRate", param, tMsg);
    }
}

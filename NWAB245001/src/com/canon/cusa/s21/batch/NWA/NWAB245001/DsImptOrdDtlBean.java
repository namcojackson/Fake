/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB245001;

import business.db.DS_IMPT_ORD_DTLTMsg;

/**
 * <pre>
 * CUSA Retail Order Import Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/16/2016   CITS            S.Tanikawa      Create          N/A
 * </pre>
 */
public class DsImptOrdDtlBean extends DS_IMPT_ORD_DTLTMsg {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** trxLineNum */
    private String trxLineNum;

    /** trxLineSubNum */
    private String trxLineSubNum;

    /**
     * getTrxLineNum
     * @return String
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * setTrxLineNum
     * @param trxLineNum String
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * getTrxLineSubNum
     * @return String
     */
    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    /**
     * setTrxLineSubNum
     * @param trxLineSubNum String
     */
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.HashMap;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Create the PO History.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   CITS            T.Tokuomi       Create
 * 2016/05/06   CSAI            K.Lee           Update          QC#5762
 *</pre>
 */
public class NPXC001001CreatePOHistoryQuery extends S21SsmEZDQuerySupport {

    /**
     * instance
     */
    private static final NPXC001001CreatePOHistoryQuery MYINSTANCE = new NPXC001001CreatePOHistoryQuery();

    /**
     * PO Entry Function ID
     */
    private static final String PO_ENTRY_FUNC_ID = "NPAL1500";

    /**
     * CONST
     */
    public NPXC001001CreatePOHistoryQuery() {
        super();
    }

    /**
     * @return MYINSTANCE
     */
    public static NPXC001001CreatePOHistoryQuery getInstance() {
        return MYINSTANCE;
    }

    /**
     * getPoHeader
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoHeader(String glblCmpyCd, String poOrdNum) {
        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("poOrdNum", poOrdNum);

        return getSsmEZDClient().queryObject("getPoHeader", ssmParam);
    }

    /**
     * getPoDetail
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoDetail(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        HashMap<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("poOrdNum", poOrdNum);
        if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
            ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        }
        ssmParam.put("PoEntryAppFuncId", PO_ENTRY_FUNC_ID);
        ssmParam.put("poAcctTpCd01", PO_ACCT_TP.CHARGE);
        ssmParam.put("poAcctTpCd02", PO_ACCT_TP.ACCRUAL);
        ssmParam.put("poAcctTpCd03", PO_ACCT_TP.VARIANCE);

        return getSsmEZDClient().queryObjectList("getPoDetail", ssmParam);
    }
}

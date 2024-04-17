/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package com.canon.cusa.s21.batch.NFC.NFCB127001;

import java.util.Map;

import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Dunning Current Month Updated Balance Extraction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2009   Canon           U.Usui          Create          N/A
 * 03/25/2016   Hitachi         T.Tsuchida      Update          S21 NA Unit Test #164 Change Update Data
 * 04/11/2016   Fujitsu         S.Fujita        Create          QC#6722
 *</pre>
 */
public final class NFCB127001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NFCB127001Query MYINSTANCE = new NFCB127001Query();

    /**
     */
    private NFCB127001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NFCB127001Query getInstance() {
        return MYINSTANCE;
    }

// START 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]
// START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
    /**
     * @param param Map Map<String, Object>
     * @param tMsg AR_ACCT_DTTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArAcctDt(Map<String, Object> param, AR_ACCT_DTTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArAcctDt", param, tMsg);
    }
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
// END 2016/04/11 S.Fujita [S21 NA Unit Test QC#6722,MOD]

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_ADJTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArAdj(Map<String, Object> param, AR_ADJTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getArAdj", param, tMsg);
    }

 // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
//    /**
//     * @param param Map<String, Object>
//     * @param tMsg AR_CASH_APPTMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult getArCashAppByCreditMemo(Map<String, Object> param, AR_CASH_APPTMsg tMsg) {
//        return getSsmEZDClient().queryEZDMsg("getArCashAppByCreditMemo", param, tMsg);
//    }
// END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0860;

import static business.blap.NFCL0860.constant.NFCL0860Constant.AR_RCPT_CHK_NUM;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SUB_SYS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFCL0860Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/22    Fujitsu         S.Fujita        Create          N/A
 * 2016/04/08   CSAI            K.Uramori       Update          QC#6750
 * 2016/05/13   CSAI            K.Uramori       Update          QC#7983
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 * 2021/11/11   CITS            G.Delgado       Update          QC#55788
 * 2022/01/08   CITS            K.Suzuki        Update          QC#55788-1
 * 2022/04/22   CITS            K.Suzuki        Update          QC#59333
 * 2022/06/09   CITS            D.Mamaril       Update          QC#59333-2
 *</pre>
 */
public final class NFCL0860Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL0860Query MY_INSTANCE = new NFCL0860Query();

    /**
     * Private constructor
     */
    private NFCL0860Query() {
        super();
    }

    /**k
     * Get the NFCL0860Query instance.
     * @return NFCL0860Query instance
     */
    public static NFCL0860Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search Header
     * @param bizMsg NFCL0860CMsg
     * @param glblMsg NFCL0860SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchHeader(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invNum", bizMsg.invNum_H.getValue());

        return getSsmEZDClient().queryEZDMsg("getInvHead", params, glblMsg);
    }

    /**
     * Search Detail
     * @param bizMsg NFCL0860CMsg
     * @param glblMsg NFCL0860SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchDetail(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("invNum", bizMsg.invNum_H.getValue());
        params.put("offset", AR_RCPT_TRX_TP.OFFSET);
        params.put("nfc", SUB_SYS.NFC);
        params.put("inv", AR_TRX_TP.INVOICE);
        params.put("crm", AR_TRX_TP.CREDIT_MEMO);
        params.put("dem", AR_TRX_TP.DEBIT_MEMO);
        params.put("acc", AR_TRX_TP.ON_ACCOUNT);
        params.put("ded", AR_TRX_TP.DEDUCTION);
        params.put("adj", AR_APPLY_TP.ADJUSTMENT);
        //---- start 2016/04/08 QC#6750
        params.put("offset", AR_RCPT_TRX_TP.OFFSET);
        //---- end 2016/04/08
      //---- start 2016/05/13 QC#7983
        params.put("wrtOffScrn", AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN);
        params.put("cmpltStrgy", AR_WRT_OFF_RQST_TP.COMPLETE_STRATEGY);
        params.put("unapply", AR_CASH_APPLY_STS.UNAPPLIED);
        params.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        //---- end 2016/05/13
        // START 2018/03/02 J.Kim [QC#21143,ADD]
        params.put("wfStsCdPending", AR_DS_WF_STS.PENDING);
        params.put("wfStsCdApproved", AR_DS_WF_STS.APPROVED);
        // END 2018/03/02 J.Kim [QC#21143,ADD]

        // START 2022/04/22 K.Suzuki [QC#59333,ADD]
        params.put("arAdjTpCd", AR_ADJ_TP.OPEN_A_OR_R_REFUND);
        // END   2022/04/22 K.Suzuki [QC#59333,ADD]
        // START 2022/06/04 K.Suzuki [QC#59333-1,ADD]
        params.put("arCustRefNum", AR_RCPT_CHK_NUM);
        params.put("upperCustIssPoNum", AR_RCPT_CHK_NUM);
        // END   2022/06/04 K.Suzuki [QC#59333-1,ADD]

        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getInvList", params, glblMsg.A);
    }

    // START 2022/01/08 K.Suzuki [QC#55788-1, DEL]
    // START 2021/11/11 G.Delgado [QC#55788, ADD]
    /**
     * Search Detail For Receipt
     * @param bizMsg NFCL0860CMsg
     * @param glblMsg NFCL0860SMsg
     * @return S21SsmEZDResult
     */
    //public S21SsmEZDResult searchDetailForReceipt(NFCL0860CMsg bizMsg, NFCL0860SMsg glblMsg) {

    //    // START 2022/01/08 K.Suzuki [QC#55788-1, DEL]
    //    Map<String, Object> params = new HashMap<String, Object>();
    //    params.put("glblCmpyCd", getGlobalCompanyCode());
    //    params.put("receiptNum", bizMsg.invNum_H.getValue());
    //    params.put("nfc", SUB_SYS.NFC);
    //    params.put("onlBatTpCd", NFCConst.CST_ONL_BAT_TP_CD_ONL);
    //    params.put("inv", AR_TRX_TP.INVOICE);
    //    params.put("crm", AR_TRX_TP.CREDIT_MEMO);
    //    params.put("dem", AR_TRX_TP.DEBIT_MEMO);
    //    params.put("acc", AR_TRX_TP.ON_ACCOUNT);
    //    params.put("ded", AR_TRX_TP.DEDUCTION);

    //    params.put("rowNum", glblMsg.A.length() + 1);

    //    return getSsmEZDClient().queryEZDMsgArray("getInvListForRcp", params, glblMsg.A);
    //}
    // END 2021/11/11 G.Delgado [QC#55788, ADD]
    // END   2022/01/08 K.Suzuki [QC#55788-1, DEL]

    // START 2022/01/08 K.Suzuki [QC#55788-1, ADD]
    /**
     * Get Receipt from OnAccount
     * @param bizMsg NFCL0860CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReceiptNumFromOnAccount(NFCL0860CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustAcctCd", bizMsg.billToCustAcctCd_AB.getValue());
        params.put("OnAccountNum", bizMsg.arTrxNum_AB.getValue());
        params.put("acc", AR_TRX_TP.ON_ACCOUNT);
        params.put("rcp", AR_TRX_TP.RECEIPT);

        return getSsmEZDClient().queryObject("getReceiptNumFromOnAccount", params);
    }
    // END   2022/01/08 K.Suzuki [QC#55788-1, ADD]

    // START 2022/04/22 K.Suzuki [QC#59333,ADD]
    /**
     * findCancelArApplyIntfcWrk
     * 
     * @param bizMsg  NFCL0860CMsg
     * @param rcptNum NFCL0860_ACMsg
     * @return        S21SsmEZDResult
     */
    public S21SsmEZDResult findCancelArApplyIntfcWrk(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("rcptNum", bizMsgA.rcptNum_R1.getValue());
        ssmParam.put("invNum", bizMsg.arTrxNum_AB.getValue());

        return getSsmEZDClient().queryObject("findCancelArApplyIntfcWrk", ssmParam);
    }

    /**
     * findArRfRqst
     * 
     * @param bizMsg  NFCL0860CMsg
     * @return        S21SsmEZDResult
     */
    public S21SsmEZDResult findArRfRqst(NFCL0860CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("arTrxNum", bizMsg.arTrxNum_AB.getValue());

        return getSsmEZDClient().queryObject("findArRfRqst", ssmParam);
    }
    // END   2022/04/22 K.Suzuki [QC#59333,ADD]
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3050;

import static business.blap.NFCL3050.constant.NFCL3050Constant.BIZ_ID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NFCL3050.common.NFCL3050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NFCL3050Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/17   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/23   Hitachi         T.Tsuchida      Update          QC#5859
 * 2016/04/25   Fujitsu         S.Fujita        Update          QC#5047
 * 2016/06/17   Hitachi         T.Tsuchida      Update          QC#10141
 * 2016/06/24   Hitachi         K.Kojima        Update          QC#8026
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#11024
 * 2016/08/31   Hitachi         K.Kojima        Update          QC#10786
 * 2018/01/15   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/02/20   Fujitsu         H.Ikeda         Update          QC#23843
 * 2018/07/30   CITS            K.Ogino         Update          QC#26680
 * 2024/03/05   Hitachi         TZ.Win          Update          QC#63665
 *</pre>
 */
public final class NFCL3050Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NFCL3050Query MY_INSTANCE = new NFCL3050Query();

    /**
     * Private constructor
     */
    private NFCL3050Query() {
        super();
    }

    /**
     * Get the NFCL3050Query instance.
     * @return NFCL3050Query instance
     */
    public static NFCL3050Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * Search
     * @param bizMsg NFCL3050CMsg
     * @param glblMsg NFCL3050SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NFCL3050CMsg bizMsg, NFCL3050SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cMsg", bizMsg);
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustAcctNm_H", bizMsg.billToCustAcctNm_H.getValue());
        params.put("billToCustAcctCd_H", bizMsg.billToCustAcctCd_H.getValue());
        params.put("shipToLocNm_H", bizMsg.shipToLocNm_H.getValue());
        params.put("shipToCustCd_H", bizMsg.shipToCustCd_H.getValue());
        params.put("invNum_FR", bizMsg.invNum_FR.getValue());

        // START 2016/04/25 S.Fujita [QC#5047,MOD]
        String searchFlg = NFCL3050CommonLogic.setSearchFlg( bizMsg.invNum_FR.getValue());

        params.put("searchFlg", searchFlg);

        params.put("invNum_TO", bizMsg.invNum_TO.getValue());
//        if (!ZYPCommonFunc.hasValue(bizMsg.invNum_TO)) {
//            params.put("invNum_TO", bizMsg.invNum_FR.getValue());
//        } else {
//            params.put("invNum_TO", bizMsg.invNum_TO.getValue());
//        }
        // END 2016/04/25 S.Fujita [QC#5047,MOD]

        params.put("xxFromDt_FR", bizMsg.xxFromDt_FR.getValue());

        // START 2016/04/25 S.Fujita [QC#5047,MOD]
        params.put("xxToDt_TO", bizMsg.xxToDt_TO.getValue());
        // END 2016/04/25 S.Fujita [QC#5047,MOD]

        params.put("dueDt_FR", bizMsg.dueDt_FR.getValue());

        // START 2016/04/25 S.Fujita [QC#5047,MOD]
        params.put("dueDt_TO", bizMsg.dueDt_TO.getValue());
//        if (!ZYPCommonFunc.hasValue(bizMsg.dueDt_TO)) {
//            params.put("dueDt_TO", bizMsg.dueDt_FR.getValue());
//        } else {
//            params.put("dueDt_TO", bizMsg.dueDt_TO.getValue());
//        }
        // END 2016/04/25 S.Fujita [QC#5047,MOD]

        params.put("dealRmngBalGrsAmt_LO", bizMsg.dealRmngBalGrsAmt_LO.getValue());

        // START 2016/04/25 S.Fujita [QC#5047,MOD]
        params.put("dealRmngBalGrsAmt_HI", bizMsg.dealRmngBalGrsAmt_HI.getValue());
//        if (!ZYPCommonFunc.hasValue(bizMsg.dealRmngBalGrsAmt_HI)) {
//            params.put("dealRmngBalGrsAmt_HI", bizMsg.dealRmngBalGrsAmt_LO.getValue());
//        } else {
//            params.put("dealRmngBalGrsAmt_HI", bizMsg.dealRmngBalGrsAmt_HI.getValue());
//        }
        // END 2016/04/25 S.Fujita [QC#5047,MOD]

        params.put("invTotDealNetAmt_LO", bizMsg.invTotDealNetAmt_LO.getValue());

        // START 2016/04/25 S.Fujita [QC#5047,MOD]
        params.put("invTotDealNetAmt_HI", bizMsg.invTotDealNetAmt_HI.getValue());
//        if (!ZYPCommonFunc.hasValue(bizMsg.invTotDealNetAmt_HI)) {
//            params.put("invTotDealNetAmt_HI", bizMsg.invTotDealNetAmt_LO.getValue());
//        } else {
//            params.put("invTotDealNetAmt_HI", bizMsg.invTotDealNetAmt_HI.getValue());
//        }
        // END 2016/04/25 S.Fujita [QC#5047,MOD]

        params.put("dsInvTpCd_L", bizMsg.dsInvTpCd_L.getValue());
        params.put("invTpCd_L", bizMsg.invTpCd_L.getValue());
        // START 2016/06/24 K.Kojima [QC#8026,ADD]
        params.put("sysSrcCd_L", bizMsg.sysSrcCd_L);
        // END 2016/06/24 K.Kojima [QC#8026,ADD]
        params.put("mdlNm_H", bizMsg.mdlNm_H.getValue());
        params.put("serNum_H", bizMsg.serNum_H.getValue());
        // START 2018/02/19 H.Ikeda [QC#22843,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.mdlNm_H.getValue()) || ZYPCommonFunc.hasValue(bizMsg.serNum_H.getValue())) {
            params.put("svcInvLineFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcInvLineFlg", ZYPConstant.FLG_OFF_N);
        }
        // END   2018/02/19 H.Ikeda [QC#22843,ADD]
        // START 2018/01/15 H.Ikeda [QC#22759,ADD]
        params.put("grpInvNum_H", bizMsg.grpInvNum_H.getValue());
        // END   2018/01/15 H.Ikeda [QC#22759,ADD]

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_CL.getValue())) {
            List<String> inCloInv = new ArrayList<String>();
            // START 2016/03/23 T.Tsuchida [QC#5859,MOD]
//            inCloInv.add(UNAPPLIED);
//            inCloInv.add(PARTIAL);
            inCloInv.add(AR_CASH_APPLY_STS.APPLIED);
            inCloInv.add(AR_CASH_APPLY_STS.VOID);
            // END 2016/03/23 T.Tsuchida [QC#5859,MOD]
            params.put("inCloInv", inCloInv);
        }

        // START 2016/10/31 J.Kim [QC#15571,MOD]
        //// START 2016/06/17 T.Tsuchida [QC#10141,ADD]
        // params.put("arTrxTpIsCRM", AR_TRX_TP.CREDIT_MEMO);
        //// END 2016/06/17 T.Tsuchida [QC#10141,ADD]
        params.put("invTpCRM", INV_TP.CREDIT_MEMO);
        // END 2016/10/31 J.Kim [QC#15571,MOD]

        // START 2016/08/23 S.Fujita [QC#11024,ADD]
        params.put("Applied", AR_CASH_APPLY_STS.APPLIED);
        params.put("Unpplied", AR_CASH_APPLY_STS.UNAPPLIED);
        params.put("Partial", AR_CASH_APPLY_STS.PARTIAL);
        params.put("Void", AR_CASH_APPLY_STS.VOID);
        // END   2016/08/23 S.Fujita [QC#11024,ADD]

        // START 2016/08/31 K.Kojima [QC#10786,ADD]
        params.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/08/31 K.Kojima [QC#10786,ADD]
        // QC#26680
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        params.put("custIncdtId", bizMsg.custIncdtId.getValue());
        params.put("xxCratDt_FR", bizMsg.xxCratDt_FR.getValue());
        params.put("xxCratDt_TO", bizMsg.xxCratDt_TO.getValue());
        params.put("ezInUserID", bizMsg.ezInUserID.getValue());
        // END 2024/03/05 TZ.Win [QC#63665, ADD]

        params.put("rowNum", glblMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getInvList", params, glblMsg.A);
    }

    /**
     * getInvoiceTpList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceTpList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getInvoiceTpList", params);
    }

    // START 2016/07/14 K.Kojima [QC#11507,ADD]
    /**
     * getInvoiceClassList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvoiceClassList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getInvoiceClassList", params);
    }
    /**
     * getSourceList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSourceList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getSourceList", params);
    }
    // END 2016/07/14 K.Kojima [QC#11507,ADD]

}

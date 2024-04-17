package business.blap.NFCL3030;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NFCL3030.common.NFCL3030CommonLogic;
import business.blap.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_RVRS_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_VOID_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 10/05/2015   Fujitsu         T.Tanaka        Update          Bank Setting
 * 10/06/2015   Fujitsu         T.Tanaka        Update          Add NFCL3030Scrn00_Click_SetLocationName
 * 02/10/2016   Fujitsu         T.Tanaka        Update          Def#3596
 * 02/15/2016   Fujitsu         T.Tanaka        Update          Def#2601
 * 03/14/2016   CSAI            Y.Miyauchi      Update          Def#5385
 * 2016/07/25   Hitachi         J.Kim           Update          QC#9476
 * 2016/08/05   Hitachi         J.Kim           Update          QC#9476
 * 2016/11/11   Hitachi         J.Kim           Update          QC#15756
 * 2017/03/17   Fujitsu         T.Murai         Update          QC#14205
 * 2018/02/07   Fujitsu         T.Murai         Update          QC#21372
 * 2018/04/04   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 2018/09/26   Fujitsu         S.Ohki          Update          QC#28097
 * 2018/10/09   Fujitsu         T.Ogura         Update          QC#28166
 * 2018/11/29   Fujitsu         Y.Matsui        Update          QC#29479
 * 2019/08/13   Fujitsu         H.Ikeda         Update          QC#52593
 * 2020/06/05   CITS            R.Kurahashi     Update          QC#56012
 * 2022/08/31   CITS            K.Suzuki        Update          QC#60510
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3030BL02 extends S21BusinessHandler implements NFCL3030Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3030_INIT".equals(screenAplID)) {
                doProcess_NFCL3030_INIT(cMsg, sMsg);

            } else if ("NFCL3030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("NFCL3030Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Reset(cMsg, sMsg);

            } else if ("NFCL3030Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NFCL3030Scrn00_Click_CreditCardRefund".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_Click_CreditCardRefund(cMsg, sMsg);

            } else if ("NFCL3030Scrn00_Click_Reverse".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NFCL3030_NWAL1130".equals(screenAplID)) {
                doProcess_NFCL3030_NWAL1130(cMsg, sMsg);

            } else if ("NFCL3030_NMAL6760".equals(screenAplID)) {
                doProcess_NFCL3030_NMAL6760(cMsg, sMsg);

            } else if ("NFCL3030_NFCL2660".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NFCL3030_NFCL2760".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Submit(cMsg, sMsg);

            // START 2016/11/11 J.Kim [QC#15756,ADD]
            } else if ("NFCL3030Scrn00_Click_CashApplication".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_Click_CashApplication(cMsg, sMsg);
                // END 2016/11/11 J.Kim [QC#15756,ADD]
                // START 2018/02/07 [QC#21372,ADD]
            } else if ("NFCL3030Scrn00_CustomerName".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CustomerName(cMsg, sMsg);
                // END   2018/02/07 [QC#21372,ADD]
            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030_INIT================================START", this);

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;

        String arAcctDt = NFCL3030CommonLogic.getArAcctDt(getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(arAcctDt)) {
            bizMsg.setMessageInfo("NFCM0045E", null);
            return;
        }
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        bizMsg.dsBankAcctTpCd.setValue(DS_BANK_ACCT_TP.INTERNAL);

        NFCL3030CommonLogic.clearScreen(bizMsg);

        ZYPCodeDataUtil.createPulldownList(AR_RCPT_STS.class, bizMsg.arRcptStsCd_LC, bizMsg.arRcptStsNm_LD);
        NFCL3030CommonLogic.createPulldownListArRcptTrxTp(getGlobalCompanyCode(), bizMsg);
        ZYPCodeDataUtil.createPulldownList(AR_RCPT_VOID_RSN.class, bizMsg.arRcptVoidRsnCd_LC, bizMsg.arRcptVoidRsnNm_LD);
        ZYPCodeDataUtil.createPulldownList(AR_RCPT_RVRS_RSN.class, bizMsg.arRcptRvrsRsnCd_LC, bizMsg.arRcptRvrsRsnNm_LD);
        // START 2018/04/03 H.Ikeda [QC#21737-1, MOD]
        String arRcptManCrftFlg = ZYPConstant.FLG_ON_Y;
        if (ZYPCommonFunc.hasValue(bizMsg.rcptNum_H)) {
            arRcptManCrftFlg = ZYPConstant.FLG_OFF_N;
        }
        NFCL3030CommonLogic.createPulldownListArRcptSrc(bizMsg.glblCmpyCd.getValue(), bizMsg, arRcptManCrftFlg);
        // END    2018/04/03 H.Ikeda [QC#21737-1, MOD]

        bizMsg.xxTotAmt_BK.setValue(BigDecimal.ZERO);
        if (ZYPCommonFunc.hasValue(bizMsg.rcptNum_H)) {
            getRcpt(bizMsg);

            if (!ZYPCommonFunc.hasValue(bizMsg.glDt_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H, arAcctDt);
            }
            // START 2018/09/26 S.Ohki [QC#28097,ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.voidDt_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.voidDt_H, arAcctDt);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.voidGlDt_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.voidGlDt_H, arAcctDt);
            }
            // END 2018/09/26 S.Ohki [QC#28097,ADD]

            // START 2016/08/05 J.Kim [QC#9476,ADD]
            NFCL3030CommonLogic.getArRfRqstPk(bizMsg);
            // END 2016/08/05 J.Kim [QC#9476,ADD]

            // START 2022/08/31 [QC#60510,ADD]
            // CreditardRefund button control
            NFCL3030CommonLogic.getCreditCardRefundArRfRqstPk(bizMsg);

            // Submit button control
            NFCL3030CommonLogic.getCreditCardRefundArRfRqst(bizMsg);
            // END 2022/08/31 [QC#60510,ADD]

            // START 2018/10/09 T.Ogura [QC#28166,ADD]
            NFCL3030CommonLogic.setAppBtnInactiveFlg(bizMsg);
            // END   2018/10/09 T.Ogura [QC#28166,ADD]

            // START 2018/11/29 Y.Matsui [QC#29479,ADD]
            NFCL3030CommonLogic.addtPulldownListArRcptTrxTp(bizMsg);
            // END 2018/11/29 Y.Matsui [QC#29479,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptDt_H, arAcctDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H, arAcctDt);

            bizMsg.ajeCratCpltFlg_H.setValue(ZYPConstant.FLG_OFF_N);

        }

        // START 2019/08/13 H.Ikeda [QC#52593, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.rcptDt_H)) {
            bizMsg.rcptDt_BK.setValue(bizMsg.rcptDt_H.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.arRcptRemDt_H)) {
            bizMsg.arRcptRemDt_BK.setValue(bizMsg.arRcptRemDt_H.getValue());
        }
        // END   2019/08/13 H.Ikeda [QC#52593, ADD]

        EZDDebugOutput.println(1, "doProcess_NFCL3030_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Clear================================START", this);
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030SMsg globalMsg = (NFCL3030SMsg) sMsg;

        bizMsg.rcptChkNum_H.clear();
        bizMsg.rcptNum_H.clear();
        NFCL3030CommonLogic.clearScreen(bizMsg);
        bizMsg.rcptDt_H.setValue(bizMsg.procDt.getValue());
        bizMsg.glDt_H.setValue(bizMsg.procDt.getValue());

        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Clear================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030SMsg globalMsg = (NFCL3030SMsg) sMsg;

        doProcess_NFCL3030_INIT(bizMsg, globalMsg);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Submit================================START", this);
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030SMsg globalMsg = (NFCL3030SMsg) sMsg;
        doProcess_NFCL3030_INIT(bizMsg, globalMsg);
        // START 2020/06/05 R.Kurahashi [QC#56012 ,MOD]
        String screenAplID = cMsg.getScreenAplID();
        if ("NFCL3030Scrn00_Click_Reverse".equals(screenAplID) || "NFCL3030_NFCL2660".equals(screenAplID) || "NFCL3030_NFCL2760".equals(screenAplID)) {
            bizMsg.setMessageInfo("NZZM0002I");
        }
        // END 2020/06/05 R.Kurahashi [QC#56012 ,MOD]
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Submit================================END", this);
    }

    // START 2016/07/25 J.Kim [QC#9476,ADD]
    private void doProcess_NFCL3030Scrn00_Click_CreditCardRefund(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030SMsg globalMsg = (NFCL3030SMsg) sMsg;
        globalMsg.clear();
        doProcess_NFCL3030_INIT(bizMsg, globalMsg);
    }
    // END 2016/07/25 J.Kim [QC#9476,ADD]

    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getRcpt(NFCL3030CMsg bizMsg) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("arCashApplySts_Unidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
        ssmMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        ssmMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);

        S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().getRcpt(bizMsg, ssmMap);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo("NZZM0000E");
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_BK, bizMsg.xxTotAmt_H1.getValue());

        // START 2017/03/16 [QC#14205,ADD]
        String a = ZYPRecHistUtil.getFullNameForRecHist(bizMsg.xxRecHistCratByNm_RC.getValue());
        bizMsg.xxRecHistCratByNm_RC.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.xxRecHistCratByNm_RC.getValue()));
        bizMsg.xxRecHistUpdByNm_RC.setValue(ZYPRecHistUtil.getFullNameForRecHist(bizMsg.xxRecHistUpdByNm_RC.getValue()));
        // END   2017/03/16 [QC#14205,ADD]
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030_NWAL1130================================START", this);
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;

        if (bizMsg.xxScrEventNm.getValue().equals("Click_LinkCustomer")) {
            NFCL3030CommonLogic.setDsAcctNm(getGlobalCompanyCode(), bizMsg);
        }
        EZDDebugOutput.println(1, "doProcess_NFCL3030_NWAL1130================================END", this);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3030_NMAL6760(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030CommonLogic.setLocNm(getGlobalCompanyCode(), bizMsg);
    }

    // START 2016/11/11 J.Kim [QC#15756,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_Click_CashApplication(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030CommonLogic.getCreditCardRefundArRfRqstInfo(bizMsg);
    }
    // END 2016/11/11 J.Kim [QC#15756,ADD]

    // START 2018/02/07 [QC#21372,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_CustomerName(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030CommonLogic.getDsAcctNm_Like(getGlobalCompanyCode(), bizMsg);
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)) {
            bizMsg.invNum_H.clear();
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    }
    // END   2018/02/07 [QC#21372,ADD]
}

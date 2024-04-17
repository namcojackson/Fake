/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2300.common;

import static business.blap.NWAL2300.constant.NWAL2300Constant.AUTHORITY_NWAL2300T010;
import static business.blap.NWAL2300.constant.NWAL2300Constant.AUTHORITY_NWAL2300T020;
import static business.blap.NWAL2300.constant.NWAL2300Constant.AUTHORITY_UPDATE_CD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.BIZ_ID;
import static business.blap.NWAL2300.constant.NWAL2300Constant.COMMENT_MAX_SIZE;
import static business.blap.NWAL2300.constant.NWAL2300Constant.DEF_LINE_SUB_NUM;
import static business.blap.NWAL2300.constant.NWAL2300Constant.FLG_TYPE_N;
import static business.blap.NWAL2300.constant.NWAL2300Constant.FLG_TYPE_Y;
import static business.blap.NWAL2300.constant.NWAL2300Constant.MODE_SAVE;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0842E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0843E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0844E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0845E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0846E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0847E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0848E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0849E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0850W;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0851W;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0866E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0873E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM0961W;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NWAM8453W;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NZZM0000E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NZZM0001W;
import static business.blap.NWAL2300.constant.NWAL2300Constant.NZZM0003E;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PERIOD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_CD_C_R;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_CD_RMA_RB;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_TXT_C_R;
import static business.blap.NWAL2300.constant.NWAL2300Constant.PULLDOWN_TXT_RMA_RB;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RADIO_BUTTON_DEF;
import static business.blap.NWAL2300.constant.NWAL2300Constant.RADIO_BUTTON_ORD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_CSMP_CLAIM_DS_INV_TP_CD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.VARCHAR_CONST_NWAL2300_EXCEPT_RMA_WH_CD;
import static business.blap.NWAL2300.constant.NWAL2300Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2300.NWAL2300CMsg;
import business.blap.NWAL2300.NWAL2300Query;
import business.blap.NWAL2300.NWAL2300_CCMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.INVTMsg;
import business.db.INVTMsgArray;
import business.db.INV_BOLTMsgArray;
import business.db.MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_APMsgArray;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnDtlPMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001NumberingUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMA_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NWAL2300CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/10/27   Fujitsu         R.Nakamura      Update          S21_NA#22125
 * 2018/03/30   Fujitsu         Y.Kanefusa      Update          S21_NA#22106
 * 2018/04/06   Fujitsu         T.Aoi           Update          S21_NA#22122
 * 2018/07/04   Fujitsu         T.Aoi           Update          S21_NA#26917
 * 2018/08/03   Fujitsu         M.Ishii         Update          S21_NA#27481
 * 2019/06/05   Fujitsu         R.Nakamura      Update          S21_NA#50134
 * 2021/03/23   CITS            K.Ogino         Update          S21_NA#57812
 *</pre>
 */
public class NWAL2300CommonLogic {

    /**
     * Set Authority
     * @param bizMsg NWAL2300CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL2300CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrEdtTpCd, AUTHORITY_NWAL2300T010);
        for (String functionId : functionIds) {
            if (functionId.equals(AUTHORITY_UPDATE_CD)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxScrEdtTpCd, AUTHORITY_NWAL2300T020);
                break;
            }
        }
    }

    /**
     * errChkSearch
     * @param bizMsg NWAL2300CMsg
     * @return boolean(true:err)
     */
    public static boolean errChkSearch(NWAL2300CMsg bizMsg) {

        // a)order Number blank
        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H1)) {
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, ZZM9000E, new String[] {"Order Number" });
            return true;
        }

        // b)Check ineligible order Category
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getIneligibleOrder(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // It is ineligible order Category
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0842E, new String[] {"Order #" });
            return true;
        }

        // g)get closed data
        if (getINVTMsgList(bizMsg) == null) {
            // not exist closed data.
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0847E, new String[] {"Order #" });
            return true;
        }

        // c)get credit data
        ssmResult = NWAL2300Query.getInstance().getCreditData(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // It is credit data.
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0843E, new String[] {"Order #" });
            return true;
        }

        // d)get outbound Data
        ssmResult = NWAL2300Query.getInstance().getOutboundData(bizMsg);
        if (ssmResult.isCodeNotFound() && ssmResult.isCodeNotFound()) {
            // Not exist outbound data.
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0844E, new String[] {"Order #" });
            return true;
        }

        // e)get open data with RWS
        ssmResult = NWAL2300Query.getInstance().getRwsData(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // exist open data with RWS
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0845E, new String[] {"Order #" });
            return true;
        }

        // 2018/07/04 QC#26917 Del Start
        //// f) get allocated Data
        //ssmResult = NWAL2300Query.getInstance().getAllocatedData(bizMsg);
        //if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
        //    // exist allocated data.
        //    bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0846E, new String[] {"Order #" });
        //    return true;
        //}
        // 2018/07/04 QC#26917 Del End

        // get non finlized invoice Data
        ssmResult = NWAL2300Query.getInstance().getNonFinaliedInvoice(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // exist non finlized invoice Data.
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0866E, new String[] {"Order #" });
            return true;
        }

        return false;
    }

    /**
     * createComment
     * @param ssmResult S21SsmEZDResult
     * @return String
     */
    public static String createComment(S21SsmEZDResult ssmResult) {

        StringBuilder s = new StringBuilder();
        List<Map<String, String>> listMap = (List<Map<String, String>>) ssmResult.getResultObject();
        for (int i = 0; i < listMap.size(); i++) {
            s.append((String) listMap.get(i).get("MSG_TXT_INFO_TXT"));
        }
        if (s.length() >= COMMENT_MAX_SIZE) {
            return s.toString().substring(0, COMMENT_MAX_SIZE);
        } else {
            return s.toString();
        }
    }

    /**
     * creditRebillInformationSearch
     * @param bizMsg NWAL2300CMsg
     * @return boolean
     */
    public static boolean creditRebillInformationSearch(NWAL2300CMsg bizMsg) {

        // Credit & Rebill Info search
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().searchCreditRebillInfo(bizMsg);
        if (ssmResult.isCodeNotFound() || ssmResult.getQueryResultCount() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return false;
        // 2018/04/06 QC#22122 Del Start
        //} else {
        //    if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H2) || ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H3)) {
        //        // Exist Credit/Rebill Order
        //        bizMsg.setMessageInfo(NWAM8453W, new String[] {bizMsg.cpoOrdNum_H1.getValue() });
        //    }
        }
        // comment search
        ssmResult = NWAL2300Query.getInstance().searchComment(bizMsg, TXT_TP.CREDIT_REBILL_COMMENT);
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdMemoTxt_H1, NWAL2300CommonLogic.createComment(ssmResult));
        }
        return true;
    }

    /**
     * optionalOrderDetailSearch
     * @param bizMsg NWAL2300CMsg
     */
    public static void optionalOrderDetailSearch(NWAL2300CMsg bizMsg) {

        // Total Invoiced search
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().searchTotalInvoiced(bizMsg);
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invTotDealNetAmt_H1, (BigDecimal) ssmResult.getResultObject());
        }

        // Open Line search
        String flg = FLG_TYPE_N;
        ssmResult = NWAL2300Query.getInstance().isOpenOrder(bizMsg);
        if (ssmResult.isCodeNormal()) {
            flg = FLG_TYPE_Y;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoNm_H1, flg);

        // Open RMAs search
        flg = FLG_TYPE_N;
        ssmResult = NWAL2300Query.getInstance().isOpenRma(bizMsg);
        if (ssmResult.isCodeNormal()) {
            flg = FLG_TYPE_Y;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoNm_H2, flg);
    }

    /**
     * originalOrderProfitabilityDetailsSearch
     * @param bizMsg NWAL2300CMsg
     */
    public static void originalOrderProfitabilityDetailsSearch(NWAL2300CMsg bizMsg) {

        // Original Order Profitability Details Search
        NWAL2300Query.getInstance().searchOrdPrft(bizMsg);
    }

    /**
     * invoiceSummarySearch
     * @param bizMsg NWAL2300CMsg
     */
    public static void invoiceSummarySearch(NWAL2300CMsg bizMsg) {

        // 2018/04/06 QC#22122 Add Start
        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H2) && !ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H3)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn, RADIO_BUTTON_ORD);
        }
        // 2018/04/06 QC#22122 Add End
        // invoice Summary Search
        NWAL2300Query.getInstance().searchInvSummary(bizMsg);

        // 2018/04/06 QC#22122 Add Start
        int crCpoOrdNumCnt = 0;
        if (bizMsg.B.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).cpoOrdNum_B1)) {
                    crCpoOrdNumCnt++;
                }
            }
            if (bizMsg.B.getValidCount() == crCpoOrdNumCnt) {
                bizMsg.setMessageInfo(NWAM8453W, new String[] {bizMsg.cpoOrdNum_H1.getValue() });
            }
        }
        // 2018/04/06 QC#22122 Add End
    }

    /**
     * invoiceLineSearch
     * @param bizMsg NWAL2300CMsg
     */
    public static void invoiceLineSearch(NWAL2300CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H2) && !ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H3)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_H1, RADIO_BUTTON_DEF);
        }
        // invoice Summary Search
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().searchInvLine(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;

        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.C.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.C.setValidCount(bizMsg.C.length());
            } else {
                bizMsg.C.setValidCount(ssmResult.getQueryResultCount());
            }
        }
    }
    // QC#22106 2018/03/30 Add Start
    public static void invoiceLineReNumbering(NWAL2300CMsg bizMsg) {
        String lineNum = "000";
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg line = bizMsg.C.no(i);
            if (!ZYPCommonFunc.hasValue(line.cpoDtlLineSubNum_C1)) {
                // bill with equipment
                ZYPEZDItemValueSetter.setValue(line.cpoDtlLineSubNum_C1, DEF_LINE_SUB_NUM);
            }
            if (ZYPCommonFunc.hasValue(line.dsCpoLineSubNum_C1)) {
                ZYPEZDItemValueSetter.setValue(line.cpoDtlLineNum_T, lineNum);
            } else {
                lineNum = getNextCpoDtlLineNum(lineNum);
                ZYPEZDItemValueSetter.setValue(line.cpoDtlLineNum_T, lineNum);
            }
        }
    }
    // QC#22106 2018/03/30 Add End

    /**
     * setReasonCode
     * @param bizMsg NWAL2300CMsg
     */
    public static void setReasonCode(NWAL2300CMsg bizMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_H1.getValue()) || ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_H2.getValue()) || ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg_H3.getValue())) {
            return;
        }
        resetReasonCodeList(bizMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H2)) {
            // Reason Code
            S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().searchReasonCode(bizMsg);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, String>> listMap = (List<Map<String, String>>) ssmResult.getResultObject();
                for (int i = 0; i < listMap.size(); i++) {
                    String crrc = (String) listMap.get(i).get("CR_REBIL_RSN_CD");
                    for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                        if (crrc.equals(bizMsg.A.no(j).crRebilRsnCd_A1.getValue())) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(j).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
                        }
                    }
                }
            }
        }
    }

    /**
     * setPmtTermCashDiscCdHder
     * @param bizMsg NWAL2300CMsg
     */
    public static void setPmtTermCashDiscCdHder(NWAL2300CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getPmtTermCashDiscCdHder(bizMsg);
        if (ssmResult.isCodeNormal()) {
            String pmtTermCashDiscCd = (String) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd_H1, pmtTermCashDiscCd);
        }
    }

    /**
     * errChkSubmit
     * @param bizMsg NWAL2300CMsg
     * @return boolean
     */
    public static boolean errChkSubmit(NWAL2300CMsg bizMsg) {

        // a)Check exist credit & Rebill Order
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().existCreditRebill(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // Already entried credit/Rebill with the order.
            bizMsg.setMessageInfo(NWAM0848E, new String[] {"Order #" + bizMsg.cpoOrdNum_H1.getValue() });
            return true;
        }
        // b) RWS
        ssmResult = NWAL2300Query.getInstance().getRwsData(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // It has open RMA line wtih created RWS.
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0845E, new String[] {"Order #" + bizMsg.cpoOrdNum_H1.getValue() });
            return true;
        }

        // c)Allocation
        ssmResult = NWAL2300Query.getInstance().getAllocatedData(bizMsg);
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
            // It has open outbound line with allocated inventory.
            bizMsg.cpoOrdNum_H1.setErrorInfo(1, NWAM0846E, new String[] {"Order #" + bizMsg.cpoOrdNum_H1.getValue() });
            return true;
        }

        // Check Action combination
        if (chkMandatoryAction(bizMsg)) {
            return true;
        }

        // Check Action Bill Only Order
        if (chkActionBillOnly(bizMsg)) {
            return true;
        }

        // Check Action combination
        if (chkActionRmaWithCredit(bizMsg)) {
            bizMsg.setMessageInfo(NWAM0849E);
            return true;
        }

        // Check checkBox CancelOpenRMA
        if (chkCancelOpenRMA(bizMsg)) {
            bizMsg.setMessageInfo(NWAM0849E);
            return true;
        }

        // Check Timestamp
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                continue;
            }
            // Outbound
            if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                SHPG_PLNTMsg shpgPln = new SHPG_PLNTMsg();
                ZYPEZDItemValueSetter.setValue(shpgPln.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgPln.shpgPlnNum, invLine.shpgPlnNum_C1);
                shpgPln = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(shpgPln);
                if (shpgPln == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return true;
                }
                String curUpdtDt = shpgPln.ezUpTime.getValue();
                String curTimeZone = shpgPln.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(invLine.ezUpTime_C1.getValue(), invLine.ezUpTimeZone_C1.getValue(), curUpdtDt, curTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return true;
                }
            }
            // Inbound
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                DS_CPO_RTRN_DTLTMsg rtrnDtl = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rtrnDtl.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rtrnDtl.cpoOrdNum, bizMsg.cpoOrdNum_H1);
                ZYPEZDItemValueSetter.setValue(rtrnDtl.dsCpoRtrnLineNum, invLine.cpoDtlLineNum_C1);
                ZYPEZDItemValueSetter.setValue(rtrnDtl.dsCpoRtrnLineSubNum, invLine.cpoDtlLineSubNum_C1);
                rtrnDtl = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(rtrnDtl);
                if (rtrnDtl == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return true;
                }
                String curUpdtDt = rtrnDtl.ezUpTime.getValue();
                String curTimeZone = rtrnDtl.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(invLine.ezUpTime_C1.getValue(), invLine.ezUpTimeZone_C1.getValue(), curUpdtDt, curTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return true;
                }
            }
        }

        // e)Open Order(warning)
        /***************************************************************************
         * Warning Message 1
         ***************************************************************************/
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_H1.getValue())) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue()) && ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                    // The order# @ selected for Credit & Rebill
                    // contains opn lines. These lines will be
                    // automatically canceled and created on the
                    // Rebill
                    bizMsg.setMessageInfo(NWAM0850W, new String[] {"Order #" + bizMsg.cpoOrdNum_H1.getValue() });
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_ON_Y);
                    return true;
                }
            }
        }

        // f)Open RMA(warning)
        /***************************************************************************
         * Warning Message 2
         ***************************************************************************/
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_H2.getValue())) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue()) && ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                    // There are some open RMA line. If you would like
                    // to cancel open RMA, Please check
                    // "Cancel Open RMA"
                    bizMsg.setMessageInfo(NWAM0851W);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_ON_Y);
                    return true;
                }
            }
        }

        /***************************************************************************
         * Warning Message 3
         ***************************************************************************/
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_ON_Y);
            // 2018/08/03 QC#27481 Mod Start
//            bizMsg.setMessageInfo(NWAM0852W, new String[] {"Order #" });
            bizMsg.setMessageInfo(NWAM0961W);
            // 2018/08/03 QC#27481 Mod End
            return true;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_OFF_N);
        }

        return false;
    }

    private static boolean chkMandatoryAction(NWAL2300CMsg bizMsg) {
        boolean rc = false;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(invLine.xxTpCd_C1)) {
                invLine.xxTpCd_C1.setErrorInfo(1, ZZM9000E, new String[] {"Action" });
                rc = true;
            }
        }
        return rc;
    }

    private static boolean chkActionBillOnly(NWAL2300CMsg bizMsg) {
        boolean rc = false;
        String excRmaWhCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NWAL2300_EXCEPT_RMA_WH_CD, bizMsg.glblCmpyCd.getValue());
        List<String> excRmaWhCdList = null;
        if (S21StringUtil.isNotEmpty(excRmaWhCd)) {
            excRmaWhCdList = S21StringUtil.toList(excRmaWhCd);
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2300_CCMsg invLine = bizMsg.C.no(i);
            if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                for (int j = 0; j < excRmaWhCdList.size(); j++) {
                    // QC#57812 Mod
                    if (excRmaWhCdList.get(j).equals(invLine.rtlWhCd_C1.getValue()) //
                            && !isIntangibleLoanToSales(bizMsg.glblCmpyCd.getValue(), invLine.dsCpoConfigPk_C1.getValue(), invLine.mdseCd_C1.getValue())) {
                        invLine.xxTpCd_C1.setErrorInfo(1, NWAM0873E);
                        return true;
                    }
                }
            }
        }
        return rc;
    }

    // Action Check(C/R)
    /**
     * chkActionOutbound
     * @param bizMsg NWAL2300CMsg
     * @return boolean
     */
    private static boolean chkActionRmaWithCredit(NWAL2300CMsg bizMsg) {

        // value set check
        // Get RETAIL_EQUIPMENT_ORDERS
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().checkDetail(bizMsg);
        if (ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound()) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Skip
                    continue;
                }
                // Get Main Machine
                ssmResult = NWAL2300Query.getInstance().checkMainMachine(bizMsg.C.no(i), bizMsg.glblCmpyCd.getValue());
                if (ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound()) {
                    if (PULLDOWN_CD_RMA_RB.equals(bizMsg.C.no(i).xxTpCd_C1.getValue())) {
                        String posnNum = bizMsg.C.no(i).dsOrdPosnNum_C1.getValue();
                        for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                            String dsOrdLineDrctnCd = bizMsg.C.no(j).dsOrdLineDrctnCd_C1.getValue();
                            if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(dsOrdLineDrctnCd) && posnNum.equals(bizMsg.C.no(j).dsOrdPosnNum_C1.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.C.no(j).openFlg_C1.getValue())) {
                                // Same Position#
                                if (!PULLDOWN_CD_RMA_RB.equals(bizMsg.C.no(j).xxTpCd_C1.getValue())) {
                                    // Main Machine: RMA w/Credit,
                                    // Accessary/Supply: Not RMA
                                    // w/Credit
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check CancelOpenRMA
     * @param bizMsg
     * @return boolean
     */
    private static boolean chkCancelOpenRMA(NWAL2300CMsg bizMsg) {

        // Get RETAIL_EQUIPMENT_ORDERS
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().checkDetail(bizMsg);
        if (ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound()) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    // Skip
                    continue;
                }
                // Get Main Machine
                ssmResult = NWAL2300Query.getInstance().checkMainMachine(bizMsg.C.no(i), bizMsg.glblCmpyCd.getValue());
                if (ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound()) {
                    if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.C.no(i).xxChkBox_C1.getValue())) {
                        String posnNum = bizMsg.C.no(i).dsOrdPosnNum_C1.getValue();
                        for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                            if (posnNum.equals(bizMsg.C.no(j).dsOrdPosnNum_C1.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.C.no(j).openFlg_C1.getValue())) {
                                // Same Position#
                                if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.C.no(j).xxChkBox_C1.getValue())) {
                                    // Main Machine: Cancel RMA,
                                    // Accessary/Supply: Not cancel
                                    // RMA
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * getCrRebilRsnCatg
     * @param bizMsg NWAL2300CMsg
     * @return String
     */
    public static String getCrRebilRsnCatg(NWAL2300CMsg bizMsg) {

        String category = CR_REBIL_RSN_CATG.INTERNAL;
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().getCrRebilRsnCatgCd(bizMsg);
        if (ssmResult.isCodeNormal()) {
            List<String> listMap = (List<String>) ssmResult.getResultObject();
            for (int i = 0; i < listMap.size(); i++) {
                category = listMap.get(i);
                if (CR_REBIL_RSN_CATG.EXTERNAL.equals(category)) {
                    break;
                }
            }
        }
        return category;
    }

    /**
     * getRequestType
     * @param bizMsg NWAL2300CMsg
     * @return String
     */
    public static String getRequestType(NWAL2300CMsg bizMsg) {

        // 2017/10/13 S21_NA#21267 Mod Start
//        String req = MODE_SUBMIT;
//        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//            NWAL2300_CCMsg detailDataMsg = bizMsg.C.no(i);
//            String posnNum = detailDataMsg.dsOrdPosnNum_C1.getValue();
//            if (posnNum.indexOf("RMA") != -1) {
//                req = MODE_SAVE;
//                break;
//            }
//        }
        String req = MODE_SAVE;
        // 2017/10/13 S21_NA#21267 Mod End
        return req;
    }

    /**
     * initField
     * @param bizMsg NWAL2300CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initField(NWAL2300CMsg bizMsg, S21UserProfileService userProfileService) {

        String cpoOrdNum = bizMsg.cpoOrdNum_BK.getValue();
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        BigDecimal aftDeclPntDigitNum = bizMsg.aftDeclPntDigitNum.getValue();
        allClear(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_BK, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, aftDeclPntDigitNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());
        NWAL2300Query.getInstance().getReasonCodeList(bizMsg);
        NWAL2300CommonLogic.createActionList(bizMsg);
        setAuthority(bizMsg, userProfileService);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_OFF_N);
        // Get except DS_INV_TP
        String exDsInvTpStr = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_CSMP_CLAIM_DS_INV_TP_CD, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem130Txt, exDsInvTpStr);
    }

    /**
     * allClear
     * @param bizMsg NWAL2300CMsg
     */
    public static void allClear(NWAL2300CMsg bizMsg) {

        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.A);
    }

    /**
     * createActionList
     * @param bizMsg NWAL2300CMsg
     */
    public static void createActionList(NWAL2300CMsg bizMsg) {

        // PulldownList
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_L1.no(0), PULLDOWN_CD_C_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpNm_L1.no(0), PULLDOWN_TXT_C_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpCd_L1.no(1), PULLDOWN_CD_RMA_RB);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTpNm_L1.no(1), PULLDOWN_TXT_RMA_RB);
    }

    /**
     * getNextCpoDtlLineNum
     * @param lineNum String
     * @return String
     */
    public static String getNextCpoDtlLineNum(String lineNum) {

        // QC#22106 2018/03/30 Mod Start
        // char[] digit1 = S21StringUtil.subStringByLength(lineNum, 0, 1).toCharArray();
        // int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(lineNum, 1, 2));
        //
        // increment line number
        // digit23++;
        // digit23 = digit23 % 100;
        // if (digit23 == 0) {
        //     if (digit1[0] == 0x0039) {
        //         digit1[0] = 0x0041;
        //     } else {
        //         digit1[0]++;
        //     }
        // }
        // return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
        return NWXC150001NumberingUtil.getNextCpoDtlLineNum(lineNum);
        // QC#22106 2018/03/30 Mod End
    }

    /**
     * negate
     * @param val BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal negate(BigDecimal val) {

        if (val == null) {
            return val;
        }
        return val.negate();
    }

    /**
     * resetReasonCodeList
     * @param bizMsg NWAL2300CMsg
     */
    public static void resetReasonCodeList(NWAL2300CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).xxChkBox_A1.clear();
        }
    }

    private static INVTMsgArray getINVTMsgList(NWAL2300CMsg bizMsg) {

        INVTMsg tMsg = new INVTMsg();
        tMsg.setSQLID("006");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum_H1.getValue());

        INVTMsgArray tMsgArray = (INVTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        return tMsgArray;
    }
    /**
     * getDplyLineRefNum
     * @param dtlPMsg NWZC150001_APMsg
     * @return String
     */
    public static String getDplyLineRefNum(NWZC150001_APMsg dtlPMsg) {

        String dsOrdPosnNum = dtlPMsg.dsOrdPosnNum_A1.getValue();
        BigDecimal dsCpoLineNum = dtlPMsg.dsCpoLineNum_A1.getValue();
        BigDecimal dsCpoLineSubNum = dtlPMsg.dsCpoLineSubNum_A1.getValue();
        StringBuilder xxLineNum = new StringBuilder();
        xxLineNum.append(dsOrdPosnNum);
        xxLineNum.append(PERIOD);
        xxLineNum.append(dsCpoLineNum);
        if (dsCpoLineSubNum != null) {
            xxLineNum.append(PERIOD);
            xxLineNum.append(dsCpoLineSubNum);
        }
        return xxLineNum.toString();
    }

    /**
     * getDplyLineRefNumRtrn
     * @param dtlPMsg NWZC150001_rtnDtlPMsg
     * @return String
     */
    public static String getDplyLineRefNumRtrn(NWZC150001_rtnDtlPMsg dtlPMsg) {

        String dsOrdPosnNum = dtlPMsg.dsOrdPosnNum_B1.getValue();
        BigDecimal dsCpoLineNum = dtlPMsg.dsCpoLineNum_B1.getValue();
        BigDecimal dsCpoLineSubNum = dtlPMsg.dsCpoLineSubNum_B1.getValue();
        StringBuilder xxLineNum = new StringBuilder();
        xxLineNum.append(dsOrdPosnNum);
        xxLineNum.append(PERIOD);
        xxLineNum.append(dsCpoLineNum);
        if (dsCpoLineSubNum != null) {
            xxLineNum.append(PERIOD);
            xxLineNum.append(dsCpoLineSubNum);
        }
        return xxLineNum.toString();
    }

    /**
     * getBaseCompList
     * @param msgArray NWZC150001_APMsgArray
     * @return List<NWZC150001_APMsg>
     */
    public static List<NWZC150001_APMsg> getBaseCompList(NWZC150001_APMsgArray msgArray) {
        List<NWZC150001_APMsg> baseCompList = new ArrayList<NWZC150001_APMsg>();

            for (int i = 0; i < msgArray.getValidCount(); i++) {
                NWZC150001_APMsg line = msgArray.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(line.baseCmptFlg_A1.getValue())) {
                    baseCompList.add(line);
                }
            }
        return baseCompList;
    }

    /**
     * getBaseCompListRtrn
     * @param msgArray NWZC150001_rtnDtlPMsgArray
     * @return List<NWZC150001_rtnDtlPMsg>
     */
    public static List<NWZC150001_rtnDtlPMsg> getBaseCompListRtrn(NWZC150001_rtnDtlPMsgArray msgArray) {
        List<NWZC150001_rtnDtlPMsg> baseCompList = new ArrayList<NWZC150001_rtnDtlPMsg>();

            for (int i = 0; i < msgArray.getValidCount(); i++) {
                NWZC150001_rtnDtlPMsg line = msgArray.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(line.baseCmptFlg_B1.getValue())) {
                    baseCompList.add(line);
                }
            }
        return baseCompList;
    }

    /**
     * setRefCpoDtlLineNum
     * @param baseCompList List<NWZC150001_APMsg>
     * @param dtlPMsg NWZC150001_APMsg
     */
    public static void setRefCpoDtlLineNum(List<NWZC150001_APMsg> baseCompList, NWZC150001_APMsg dtlPMsg) {

        dtlPMsg.refCpoDtlLineNum_A1.clear();
        dtlPMsg.refCpoDtlLineSubNum_A1.clear();
        dtlPMsg.dplyLineRefNum_A1.clear();

        for (NWZC150001_APMsg baseComp : baseCompList) {
            if (dtlPMsg.dsOrdPosnNum_A1.getValue().equals(baseComp.dsOrdPosnNum_A1.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(dtlPMsg.baseCmptFlg_A1.getValue())) {
                    //                    dtlPMsg.refCpoDtlLineNum_A1.clear();
                    //                    dtlPMsg.refCpoDtlLineSubNum_A1.clear();
                    //                    dtlPMsg.dplyLineRefNum_A1.clear();
                } else {
                    //REF_CPO_DTL_LINE_NUM
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_A1, baseComp.cpoDtlLineNum_A1);
                    //REF_CPO_DTL_LINE_SUB_NUM
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_A1, baseComp.cpoDtlLineSubNum_A1);
                    //DPLY_LINE_REF_NUM
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, getDplyLineRefNum(baseComp));
                }
                break;
            }

        }
    }

    /**
     * setRefCpoDtlLineNumRtrn
     * @param baseCompListRtrn List<NWZC150001_rtnDtlPMsg>
     * @param rtrnPMsg NWZC150001_rtnDtlPMsg
     */
    public static void setRefCpoDtlLineNumRtrn(List<NWZC150001_rtnDtlPMsg> baseCompListRtrn, NWZC150001_rtnDtlPMsg rtrnPMsg) {

        rtrnPMsg.refCpoDtlLineNum_B1.clear();
        rtrnPMsg.refCpoDtlLineSubNum_B1.clear();
        rtrnPMsg.dplyLineRefNum_B1.clear();

        for (NWZC150001_rtnDtlPMsg baseComp : baseCompListRtrn) {
            if (rtrnPMsg.dsOrdPosnNum_B1.getValue().equals(baseComp.dsOrdPosnNum_B1.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(rtrnPMsg.baseCmptFlg_B1.getValue())) {
                    //                    rtrnPMsg.refCpoDtlLineNum_B1.clear();
                    //                    rtrnPMsg.refCpoDtlLineSubNum_B1.clear();
                    //                    rtrnPMsg.dplyLineRefNum_B1.clear();

                } else {
                    //REF_CPO_DTL_LINE_NUM
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.refCpoDtlLineNum_B1, baseComp.cpoDtlLineNum_B1);
                    //REF_CPO_DTL_LINE_SUB_NUM
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.refCpoDtlLineSubNum_B1, baseComp.cpoDtlLineSubNum_B1);
                    //DPLY_LINE_REF_NUM
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.dplyLineRefNum_B1, getDplyLineRefNumRtrn(baseComp));
                }
                break;
            }

        }
    }

    /**
     * getShpgSvcLvlCdFromInvBol
     * @param invBolList INV_BOLTMsgArray
     * @return String
     */
    public static String getShpgSvcLvlCdFromInvBol(INV_BOLTMsgArray invBolList) {

        if (invBolList == null) {
            return null;
        }

        for (int i = 0; i < invBolList.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(invBolList.no(i).shpgSvcLvlCd)) {
                return invBolList.no(i).shpgSvcLvlCd.getValue();
            }
        }
        return null;
    }

    /**
     * getShipToCustCdFromInvBol
     * @param invBolList INV_BOLTMsgArray
     * @return String
     */
    public static String getShipToCustCdFromInvBol(INV_BOLTMsgArray invBolList) {

        if (invBolList == null) {
            return null;
        }

        for (int i = 0; i < invBolList.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(invBolList.no(i).shipToCustCd)) {
                return invBolList.no(i).shipToCustCd.getValue();
            }
        }
        return null;
    }
    /**
     * DS_CONTRTMsg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public static  DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, dsContrPk);

        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    public static boolean isVendor(String shipFromInvtyLocCd) {
        Integer cnt = NWAL2300Query.getInstance().getCountVendor(shipFromInvtyLocCd);
        return cnt > 0;
    }

    // Add Start 2017/10/27 QC#22125
    public static boolean isSetCompornentChild(String glblCmpyCd, NWAL2300_CCMsg invLineMsg) {

        String setMdseCd = (String) NWAL2300Query.getInstance().getSetCompornentChild(glblCmpyCd ,invLineMsg);

        if (ZYPCommonFunc.hasValue(setMdseCd)) {
            return true;
        } else {
            return false;
        }
    }
    // Add End 2017/10/27 QC#22125

    // 2018/04/06 QC#22122 Add Start
    /**
     * errChkSubmitForPartial
     * @param bizMsg NWAL2300CMsg
     * @return boolean
     */
    public static boolean errChkSubmitForPartialInvoice(NWAL2300CMsg bizMsg, List<String> invNumList) {

        // Check Action combination
        if (chkMandatoryActionForPartialInvoice(bizMsg, invNumList)) {
            return true;
        }

        // Check Action Bill Only Order
        if (chkActionBillOnlyForPartialInvoice(bizMsg, invNumList)) {
            return true;
        }

        // Check Action combination
        if (chkActionRmaWithCreditForPartialInvoice(bizMsg, invNumList)) {
            bizMsg.setMessageInfo(NWAM0849E);
            return true;
        }

        /***************************************************************************
         * Warning Message 3
         ***************************************************************************/
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_ON_Y);
            // 2018/08/03 QC#27481 Mod Start
//            bizMsg.setMessageInfo(NWAM0852W, new String[] {"Invoice #" });
            bizMsg.setMessageInfo(NWAM0961W);
            // 2018/08/03 QC#27481 Mod End
            return true;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_OFF_N);
        }

        return false;
    }

    private static boolean chkMandatoryActionForPartialInvoice(NWAL2300CMsg bizMsg, List<String> invNumList) {
        boolean rc = false;
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            for (String invNum : invNumList) {
                if (!invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                    continue;
                }
                NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                    continue;
                }
                if (ZYPConstant.FLG_ON_Y.equals(invLine.openFlg_C1.getValue())) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(invLine.xxTpCd_C1)) {
                    invLine.xxTpCd_C1.setErrorInfo(1, ZZM9000E, new String[] {"Action" });
                    rc = true;
                }
            }
        }
        return rc;
    }

    private static boolean chkActionBillOnlyForPartialInvoice(NWAL2300CMsg bizMsg, List<String> invNumList) {
        boolean rc = false;
        String excRmaWhCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NWAL2300_EXCEPT_RMA_WH_CD, bizMsg.glblCmpyCd.getValue());
        List<String> excRmaWhCdList = null;
        if (S21StringUtil.isNotEmpty(excRmaWhCd)) {
            excRmaWhCdList = S21StringUtil.toList(excRmaWhCd);
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            for (String invNum : invNumList) {
                if (!invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                    continue;
                }
                NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                if (PULLDOWN_CD_RMA_RB.equals(invLine.xxTpCd_C1.getValue())) {
                    for (int j = 0; j < excRmaWhCdList.size(); j++) {
                        // QC#57812 Mod
                        if (excRmaWhCdList.get(j).equals(invLine.rtlWhCd_C1.getValue()) //
                                && !isIntangibleLoanToSales(bizMsg.glblCmpyCd.getValue(), invLine.dsCpoConfigPk_C1.getValue(), invLine.mdseCd_C1.getValue())) {
                            invLine.xxTpCd_C1.setErrorInfo(1, NWAM0873E);
                            return true;
                        }
                    }
                }
            }
        }
        return rc;
    }

    private static boolean chkActionRmaWithCreditForPartialInvoice(NWAL2300CMsg bizMsg, List<String> invNumList) {

        // value set check
        // Get RETAIL_EQUIPMENT_ORDERS
        S21SsmEZDResult ssmResult = NWAL2300Query.getInstance().checkDetail(bizMsg);
        if (ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound()) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                for (String invNum : invNumList) {
                    if (!invNum.equals(bizMsg.C.no(i).invNum_C1.getValue())) {
                        continue;
                    }
                    NWAL2300_CCMsg invLine = bizMsg.C.no(i);
                    if (DS_ORD_LINE_DRCTN.INBOUND.equals(invLine.dsOrdLineDrctnCd_C1.getValue())) {
                        // Skip
                        continue;
                    }
                    // Get Main Machine
                    ssmResult = NWAL2300Query.getInstance().checkMainMachine(bizMsg.C.no(i), bizMsg.glblCmpyCd.getValue());
                    if (ssmResult.isCodeNormal() && !ssmResult.isCodeNotFound()) {
                        if (PULLDOWN_CD_RMA_RB.equals(bizMsg.C.no(i).xxTpCd_C1.getValue())) {
                            String posnNum = bizMsg.C.no(i).dsOrdPosnNum_C1.getValue();
                            for (int j = 0; j < bizMsg.C.getValidCount(); j++) {
                                String dsOrdLineDrctnCd = bizMsg.C.no(j).dsOrdLineDrctnCd_C1.getValue();
                                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(dsOrdLineDrctnCd) && posnNum.equals(bizMsg.C.no(j).dsOrdPosnNum_C1.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.C.no(j).openFlg_C1.getValue())) {
                                    // Same Position#
                                    if (!PULLDOWN_CD_RMA_RB.equals(bizMsg.C.no(j).xxTpCd_C1.getValue())) {
                                        // Main Machine: RMA w/Credit,
                                        // Accessary/Supply: Not RMA
                                        // w/Credit
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    // 2018/04/06 QC#22122 Add End

    // Add Start 2019/06/05 QC#50134
    /**
     * check Merchandise RMA Not Allowance
     * @param invLineMsg NWAL2300_CCMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkMdseRmaNotAllow(NWAL2300_CCMsg invLineMsg, String glblCmpyCd) {

        if (!PULLDOWN_CD_RMA_RB.equals(invLineMsg.xxTpCd_C1.getValue())) {
            return false;
        }

        MDSETMsg mdseTMsg = (MDSETMsg) getMdseTMsg(glblCmpyCd, invLineMsg.mdseCd_C1.getValue());
        if (mdseTMsg != null) {
            if (!RMA_REQ_TP.RMA_REQUIRED.equals(mdseTMsg.rmaReqTpCd.getValue())) {
                return true;
            }
        }

        return false;
    }

    /**
     * getMdseTMsg
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
    private static EZDTMsg getMdseTMsg(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final MDSETMsg tMsg = new MDSETMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.mdseCd.setValue(cd);

        return findByKeyWithCache(tMsg);
    }

    /**
     * findByKeyWithCache
     * @param reqTMsg EZDTMsg
     * @return EZDTMsg
     */
    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {
        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }
    // Add End 2019/06/05 QC#50134

    // QC#57812 Add Method.
    private static boolean isIntangibleLoanToSales(String glblCmpyCd, BigDecimal dsCpoConfigPk, String mdseCd) {

        boolean ret = false;

        if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) || !ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(mdseCd)) {
            return ret;
        }

        MDSETMsg mdseTMsg = (MDSETMsg) getMdseTMsg(glblCmpyCd, mdseCd);
        if (mdseTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
                return ret;
            }
        }

        DS_CPO_CONFIGTMsg tMsg = new DS_CPO_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsCpoConfigPk, dsCpoConfigPk);

        tMsg = (DS_CPO_CONFIGTMsg)findByKeyWithCache(tMsg);
        if (tMsg != null) {
            if (CONFIG_TP.TO_SALES_CONVERSION.equals(tMsg.configTpCd.getValue())) {
                ret = true;
            }
        }

        return ret;

    }
}

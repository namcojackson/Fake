/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1540.common;

import static business.blap.NWAL1540.constant.NWAL1540Constant.BIG_DECIMAL_100;
import static business.blap.NWAL1540.constant.NWAL1540Constant.CSV_FILE_NAME;
import static business.blap.NWAL1540.constant.NWAL1540Constant.CSV_HEADER_INFO;
import static business.blap.NWAL1540.constant.NWAL1540Constant.EXTN_CSV;
import static business.blap.NWAL1540.constant.NWAL1540Constant.NZZM0007E;
import static business.blap.NWAL1540.constant.NWAL1540Constant.ORD_PROC_NODE_PRFL_CD_33;
import static business.blap.NWAL1540.constant.NWAL1540Constant.ZZM8100I;
import static business.blap.NWAL1540.constant.NWAL1540Constant.ZZZM9001E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1540.NWAL1540CMsg;
import business.blap.NWAL1540.NWAL1540Query;
import business.blap.NWAL1540.NWAL1540SMsg;
import business.blap.NWAL1540.NWAL1540_ASMsg;
import business.blap.NWAL1540.constant.NWAL1540Constant.MODE;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.file.NWAL1540F00FMsg;
import business.parts.NWZC156001PMsg;
import business.parts.NWZC156001_svcConfigRefPMsg;
import business.parts.NWZC156002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC156001.NWZC156001;
import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL1540CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 * 2016/03/30   Fujitsu         S.Yamamoto      Update          S21_NA#6228
 * 2016/04/27   SRA             E.Inada         Update          QC#7285
 * 2016/05/31   SRA             E.Inada         Update          QC#9123
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/09/28   Fujitsu         N.Sugiura       Update          QC#12187
 * 2016/10/18   Fujitsu         W.Honda         Update          S21_NA#15193
 * 2017/10/11   Fujitsu         R.Nakamura      Update          S21_NA#21664
 * 2018/01/10   Fujitsu         Y.Kanefusa      Update          S21_NA#22372
 * 2018/06/05   Fujitsu         T.Aoi           Update          S21_NA#26165
 *</pre>
 */
public class NWAL1540CommonLogic {

    /** S21UserProfileService profileService */
    private static S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** String globalCompanyCode */
    private static String glblCmpyCd = profileService.getGlobalCompanyCode();

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL1540CMsg bizMsg = (NWAL1540CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    public static void createPulldownListForVrsnNum(NWAL1540CMsg bizMsg) {
        bizMsg.ordPrftVrsnNum_CD.clear();
        bizMsg.xxScrItem13Txt_NM.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.ordPrftVrsnNum_CD.no(0), BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem13Txt_NM.no(0), "Online");
        //        ZYPEZDItemValueSetter.setValue(bizMsg.ordPrftVrsnNum_NM.no(i), BigDecimal.ZERO);
        if (!ZYPCommonFunc.hasValue(bizMsg.trxHdrNum)) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1540Query.getInstance().getVrsnNumList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<BigDecimal> vrsnNumList = (List) ssmResult.getResultObject();

        for (int i = 0; i < vrsnNumList.size(); i++) {
            bizMsg.ordPrftVrsnNum_CD.no(i + 1).setValue(vrsnNumList.get(i));// 2016/03/10 S21_NA#2939
            bizMsg.xxScrItem13Txt_NM.no(i + 1).setValue(vrsnNumList.get(i).toString());// 2016/03/10 S21_NA#2939
        }
    }

    public static void createPulldownListForMode(NWAL1540CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_CD.no(0), MODE.ORDER.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem8Txt_NM.no(0), MODE.ORDER.toString());

        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_CD.no(1), MODE.QUOTE.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem8Txt_NM.no(1), MODE.QUOTE.toString());
    }

    /**
     * <pre>
     * @param bizMsg    NWAL1540CMsg
     * @param glblMsg   NWAL1540SMsg
     * </pre>
     */
    public static void getProfitability(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.trxHdrNum)) {
            return;
        }

        NWZC156001PMsg prftApiPMsg = getProfitabilityCalculateApiPMsg(bizMsg, glblMsg, getPrftCalcMode(bizMsg));
        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }
        List<NWZC156002PMsg> prftApiPMsg2List = new ArrayList<NWZC156002PMsg>();
        NWZC156001 prftApi = new NWZC156001();
        prftApi.execute(prftApiPMsg, prftApiPMsg2List, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prftApiPMsg)) {
            bizMsg.setMessageInfo(S21ApiUtil.getXxMsgIdList(prftApiPMsg).get(0));
            return;
        }
        for (NWZC156002PMsg prftApiPMsg2 : prftApiPMsg2List) {
            if (S21ApiUtil.isXxMsgId(prftApiPMsg2)) {
                bizMsg.setMessageInfo(S21ApiUtil.getXxMsgIdList(prftApiPMsg2).get(0));
                return;
            }
        }

        //        if (MODE.QUOTE.getValue().equals(bizMsg.xxModeCd.getValue()) //
        //                || !ZYPCommonFunc.hasValue(bizMsg.ordPrftVrsnNum) //
        //                || BigDecimal.ZERO.compareTo(bizMsg.ordPrftVrsnNum.getValue()) == 0) {
        //            setProfitabilityToGlblMsg(glblMsg, prftApiPMsg);
        //        }
        setProfitabilityToGlblMsg(glblMsg, prftApiPMsg);
        setProfitabilityToBizMsg(bizMsg, prftApiPMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue(1);
        NWAL1540CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    private static void setProfitabilityToBizMsg(NWAL1540CMsg bizMsg, NWZC156001PMsg pMsg, NWAL1540SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.funcNegoDealAmt, pMsg.funcNegoDealAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncRepRevAmt, pMsg.totFuncRepRevAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncFinalFlAmt, pMsg.totFuncFinalFlAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncRepRevAdjAmt, pMsg.totFuncRepRevAdjAmt); // QC#7707
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncDlrCrAmt, pMsg.totFuncDlrCrAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.lastPrftCalcUsrId, pMsg.lastPrftCalcUsrId);
        ZYPEZDItemValueSetter.setValue(bizMsg.lastPrftCalcTs, pMsg.lastPrftCalcTs);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem81Txt //
                , editUserId(pMsg.lastPrftCalcUsrId.getValue()));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem19Txt //
                , editTs(pMsg.lastPrftCalcTs.getValue()));
        // Mod Start 2017/10/11 QC#21664
        if (!checkOrdProcNodePrflCd(pMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.grsPrftPct, pMsg.grsPrftPct);
            ZYPEZDItemValueSetter.setValue(bizMsg.funcGrsPrftAmt, pMsg.funcGrsPrftAmt);// 2016/03/10 S21_NA#2939
        }
        // Mod End 2017/10/11 QC#21664
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdPrftFndrFeeAmt, pMsg.xxOrdPrftFndrFeeAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdPrftIstlCrAmt, pMsg.xxOrdPrftIstlCrAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.funcAltGrsPrftAmt, pMsg.funcAltGrsPrftAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.altGrsPrftPct, pMsg.altGrsPrftPct);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncMsrpAmt, pMsg.totFuncMsrpAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncStdFlAmt, pMsg.totFuncStdFlAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncFlAdjAmt, pMsg.totFuncFlAdjAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpOrdFlg, pMsg.csmpOrdFlg);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncCsmpCrAmt, pMsg.totFuncCsmpCrAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpContrNum, pMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncByotAmt, pMsg.totFuncByotAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncSvcRevTrnsfAmt, pMsg.totFuncSvcRevTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncSvcCostTrnsfAmt, pMsg.totFuncSvcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.totFuncProSvcAmt, pMsg.totFuncProSvcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdPrftSvcAmt, pMsg.xxOrdPrftSvcAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdPrftSplyAmt, pMsg.xxOrdPrftSplyAmt);

        // 2016/03/10 S21_NA#2939
        if (MODE.ORDER.getValue().equals(bizMsg.xxModeCd.getValue())) {


            // 2016/09/28 S21_NA#12187 Mod Start
            if (pMsg.altGrsPrftPct.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.altGrsPrftPct_MV, pMsg.altGrsPrftPct);
                ZYPEZDItemValueSetter.setValue(bizMsg.funcAltGrsPrftAmt_MV, pMsg.funcAltGrsPrftAmt);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.altGrsPrftPct_MV, pMsg.grsPrftPct);
                ZYPEZDItemValueSetter.setValue(bizMsg.funcAltGrsPrftAmt_MV, pMsg.funcGrsPrftAmt);
            }

            S21SsmEZDResult ssmResult = NWAL1540Query.getInstance().getMaxVerGrossProfit(bizMsg);

            // Set GP and GP(%) for Profit Recalculate
            if (ssmResult.isCodeNormal()) {
                if (bizMsg.ordPrftVrsnNum.getValueInt() == 0) {
                    Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
                    BigDecimal grsPrftPct = (BigDecimal) resultMap.get("GRS_PRFT_PCT");
                    BigDecimal funcGrsPrftAmt = (BigDecimal) resultMap.get("FUNC_GRS_PRFT_AMT");
                    
                    ZYPEZDItemValueSetter.setValue(bizMsg.altGrsPrftPct_RE, grsPrftPct);
                    ZYPEZDItemValueSetter.setValue(bizMsg.funcAltGrsPrftAmt_RE, funcGrsPrftAmt);

                } else {
                    bizMsg.altGrsPrftPct_RE.clear();
                    bizMsg.funcAltGrsPrftAmt_RE.clear();
                }
            } else {
                bizMsg.altGrsPrftPct_RE.clear();
                bizMsg.funcAltGrsPrftAmt_RE.clear();
            }
            // 2016/09/28 S21_NA#12187 Mod End

        }

        // 2016/03/30 S21_NA#6228 Delete
//        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
//            NWAL1540_ACMsg aBizMsg = bizMsg.A.no(i);
//            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
//            EZDMsg.copy(scrPMsg, "", aBizMsg, "A");
//            ZYPEZDItemValueSetter.setValue(//
//                    aBizMsg.xxLineNum_A //
//                    , NWXC150001DsCheck.editDtlLineNum(//
//                            scrPMsg.dsOrdPosnNum.getValue() //
//                            , scrPMsg.dsCpoLineNum.getValue() //
//                            , scrPMsg.dsCpoLineSubNum.getValue()));
//            //            ZYPEZDItemValueSetter.setValue(aBizMsg.ordPrftTrxCatgCd_A, scrPMsg.ordPrftRuleTpCd);
//        }
//        bizMsg.A.setValidCount(pMsg.svcConfigRef.getValidCount());

        // QC#9123 Add Start
        // Total Invoice Amount
        int asSize = glblMsg.A.getValidCount();
        BigDecimal totInvAmt = BigDecimal.ZERO;
        for (int i = 0; i < asSize; i++) {
            totInvAmt = totInvAmt.add(getValue(glblMsg.A.no(i).funcNetSellPrcAmt_A.getValue()));
        }
        bizMsg.totInvAmt.setValue(totInvAmt);

        // Total Cost Amount
        if (asSize > 0 && !ORD_PRFT_RULE_TP.EQUIPMENT.equals(glblMsg.A.no(0).ordPrftRuleTpCd_A.getValue())) {
            BigDecimal totCostAmt = BigDecimal.ZERO;
            for (int i = 0; i < asSize; i++) {
                totCostAmt = totCostAmt.add(getValue(glblMsg.A.no(i).funcUnitStdCostAmt_A.getValue()).multiply(getValue(glblMsg.A.no(i).ordQty_A.getValue())));
            }
            bizMsg.totCostAmt.setValue(totCostAmt);
        } else {
            bizMsg.totCostAmt.clear();
        }
        // QC#9123 End

        // 2018/06/05 QC#26165 Add Start
        if (BigDecimal.ZERO.equals(bizMsg.totInvAmt.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.grsPrftPct, ZYPCodeDataUtil.getNumConstValue("NWAL1540_GRS_PRFT_PERCENT", glblCmpyCd));
        }
        // 2018/06/05 QC#26165 Add End
    }

    private static BigDecimal getValue(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    public static String editTs(String ts) {
        if (!ZYPCommonFunc.hasValue(ts)) {
            return "";
        }
        return ZYPDateUtil.formatEzd14ToDisp(ts.substring(0, 14));
    }

    public static String editUserId(String userId) {
        if (!ZYPCommonFunc.hasValue(userId)) {
            return "";
        }
        // QC#7285 Delete
//        S21_PSNTMsg tMsg = new S21_PSNTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, userId);
//
//        tMsg = (S21_PSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//        if (tMsg == null) {
//            return S21StringUtil.concatStrings(userId, ":N/A");
//        }
//        return S21StringUtil.concatStrings(//
//                userId //
//                , ":" //
//                , tMsg.psnFirstNm.getValue() //
//                , ", " //
//                , tMsg.psnLastNm.getValue());

        // QC#7285 Add
        AUTH_PSNTMsg tMsg = new AUTH_PSNTMsg();
        tMsg.setSQLID("053");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("usrNm01", userId);

        AUTH_PSNTMsgArray tMsgArray = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray == null || tMsgArray.length() < 1) {
            return S21StringUtil.concatStrings(userId, ":N/A");
        }

        return S21StringUtil.concatStrings(userId, ":", tMsgArray.no(0).firstNm.getValue(), ", ", tMsgArray.no(0).lastNm.getValue());
    }

    private static void setProfitabilityToGlblMsg(NWAL1540SMsg glblMsg, NWZC156001PMsg pMsg) {
        for (int i = 0; i < pMsg.svcConfigRef.getValidCount(); i++) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i);
            NWAL1540_ASMsg asMsg = glblMsg.A.no(i);

            EZDMsg.copy(scrPMsg, "", asMsg, "A");
            ZYPEZDItemValueSetter.setValue(asMsg.xxLineNum_A, editLineNum(scrPMsg));
            ZYPEZDItemValueSetter.setValue(asMsg.lineWtAmtRate_A, asMsg.lineWtAmtRate_A.getValue().multiply(BIG_DECIMAL_100));// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(asMsg.prcCatgNm_A2, scrPMsg.prcCatgNm_P2.getValue()); // QC#7707
        }
        glblMsg.A.setValidCount(pMsg.svcConfigRef.getValidCount());
    }

    private static String editLineNum(NWZC156001_svcConfigRefPMsg scrPMsg) {
        if (!ZYPCommonFunc.hasValue(scrPMsg.dsCpoLineNum)) {
            return scrPMsg.dsOrdPosnNum.getValue();
        }
        String lineNum = S21StringUtil.concatStrings(//
                scrPMsg.dsOrdPosnNum.getValue() //
                , "." //
                , scrPMsg.dsCpoLineNum.getValue());
        if (ZYPCommonFunc.hasValue(scrPMsg.dsCpoLineSubNum) //
                && BigDecimal.ZERO.compareTo(scrPMsg.dsCpoLineSubNum.getValue()) != 0) {
            lineNum = S21StringUtil.concatStrings(lineNum, ".", scrPMsg.dsCpoLineSubNum.getValue());
        }
        return lineNum;
    }

    private static NWZC156001PMsg getProfitabilityCalculateApiPMsg(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg, String mode) {

        NWZC156001PMsg pMsg = new NWZC156001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, bizMsg.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);

        S21SsmEZDResult ssmResult = null;
        if (MODE.ORDER.getValue().equals(bizMsg.xxModeCd.getValue())) {
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC156001Constant.MODE_ONLINE);// 2016/03/10 S21_NA2939
            ssmResult = NWAL1540Query.getInstance().getPrftCalcInfoForOrder(bizMsg);
        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC156001Constant.MODE_QUOTE);// 2016/03/10 S21_NA2939
            ssmResult = NWAL1540Query.getInstance().getPrftCalcInfoForQuote(bizMsg);
        }
        if (ssmResult.isCodeNotFound()) {
            bizMsg.trxHdrNum.setErrorInfo(1, ZZZM9001E);
            return pMsg;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

        if (resultMapList.size() > pMsg.svcConfigRef.length()) {
            bizMsg.setMessageInfo(NZZM0007E);
            return pMsg;
        }
        int i = 0;
        Map<String, Object> resultMap0 = resultMapList.get(i);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, (String) resultMap0.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, (String) resultMap0.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, (String) resultMap0.get("DS_ORD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, (String) resultMap0.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.funcNegoDealAmt, (BigDecimal) resultMap0.get("NEGO_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, (String) resultMap0.get("CSMP_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, (String) resultMap0.get("DLR_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, (String) resultMap0.get("SELL_TO_CUST_CD"));

        for (Map<String, Object> resultMap : resultMapList) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i++);
            ZYPEZDItemValueSetter.setValue(scrPMsg.ordPrftTrxCatgCd, (String) resultMap.get("CONFIG_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxLineNum, (String) resultMap.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxLineSubNum, (String) resultMap.get("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsOrdPosnNum, (String) resultMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsCpoLineNum, (BigDecimal) resultMap.get("DS_CPO_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsCpoLineSubNum, (BigDecimal) resultMap.get("DS_CPO_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.mdseCd, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.rtlWhCd, (String) resultMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.rtlSwhCd, (String) resultMap.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsOrdLineCatgCd, (String) resultMap.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.ordQty, (BigDecimal) resultMap.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.custUomCd, (String) resultMap.get("CUST_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxRefLineNum, (String) resultMap.get("REF_CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxRefLineSubNum, (String) resultMap.get("REF_CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.flPrcListCd, (String) resultMap.get("FL_PRC_LIST_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.csmpPrcListCd, (String) resultMap.get("CSMP_PRC_LIST_CD"));// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.prcCatgCd, (String) resultMap.get("PRC_CATG_CD")); // QC#7707
            // 2016/10/18 S21_NA#15193 Mod Start
//            ZYPEZDItemValueSetter.setValue(scrPMsg.coaMdseTpNm, (String) resultMap.get("COA_MDSE_TP_NM")); // QC#7707
            ZYPEZDItemValueSetter.setValue(scrPMsg.coaProjNm, (String) resultMap.get("COA_PROJ_NM")); // QC#7707
            // 2016/10/18 S21_NA#15193 Mod End
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcManFlAdjAmt, (BigDecimal) resultMap.get("FUNC_MAN_FL_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitListPrcAmt, (BigDecimal) resultMap.get("FUNC_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcNetUnitPrcAmt, (BigDecimal) resultMap.get("FUNC_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcNetSellPrcAmt, (BigDecimal) resultMap.get("FUNC_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcSvcCostTrnsfAmt, (BigDecimal) resultMap.get("FUNC_SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcManRepRevAdjAmt, (BigDecimal) resultMap.get("FUNC_MAN_REP_REV_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcSvcRevTrnsfAmt, (BigDecimal) resultMap.get("FUNC_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.cpoDtlFuncSlsAmt, (BigDecimal) resultMap.get("CPO_DTL_FUNC_SLS_AMT"));// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.ordCustUomQty, (BigDecimal) resultMap.get("ORD_CUST_UOM_QTY"));// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.csmpContrNum, (String) resultMap.get("DTL_CSMP_CONTR_NUM"));// 2016/03/10 S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.dlrRefNum, (String) resultMap.get("DTL_DLR_REF_NUM"));// 2016/03/10 S21_NA#2939

            ZYPEZDItemValueSetter.setValue(scrPMsg.ordLineStsCd, (String) resultMap.get("LINE_STS_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.billToCustCd, (String) resultMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.shipToCustCd, (String) resultMap.get("SHIP_TO_CUST_CD"));

            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitStdCostAmt, (BigDecimal) resultMap.get("THIS_MTH_TOT_STD_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.prcBaseDt, (String) resultMap.get("LINE_PRC_BASE_DT"));  // QC#9437 2016/06/21 Add
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitFlPrcAmt, (BigDecimal) resultMap.get("FUNC_UNIT_FL_PRC_AMT"));  // QC#22372 2018/01/10 Add
        }
        pMsg.svcConfigRef.setValidCount(i);
        return pMsg;
    }

    /**
     * <pre>
     * @param bizMsg    NWAL1540CMsg
     * @return          if Mode is Order then return Online
     * </pre>
     */
    private static String getPrftCalcMode(NWAL1540CMsg bizMsg) {
        if (MODE.ORDER.getValue().equals(bizMsg.xxModeCd.getValue())) {
            return NWZC156001Constant.MODE_ONLINE;
        }
        return NWZC156001Constant.MODE_QUOTE;
    }

    public static void recalculateProfitability(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {
        NWZC156001PMsg prftApiPMsg //
        = NWAL1540CommonLogic.getProfitabilityCalculateApiPMsg(bizMsg, glblMsg, NWZC156001Constant.MODE_UPDATE);
        List<NWZC156002PMsg> prftApiPMsg2List = new ArrayList<NWZC156002PMsg>();
        NWZC156001 prcApi = new NWZC156001();
        prcApi.execute(prftApiPMsg, prftApiPMsg2List, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(prftApiPMsg)) {
            bizMsg.setMessageInfo(S21ApiUtil.getXxMsgIdList(prftApiPMsg).get(0));
            return;
        }
        for (NWZC156002PMsg prftApiPMsg2 : prftApiPMsg2List) {
            if (S21ApiUtil.isXxMsgId(prftApiPMsg2)) {
                bizMsg.setMessageInfo(S21ApiUtil.getXxMsgIdList(prftApiPMsg2).get(0));
                return;
            }
        }

        setProfitabilityToGlblMsg(glblMsg, prftApiPMsg);
        bizMsg.ordPrftVrsnNum.setValue(0); // 2016/09/28 S21_NA#12187 Add
        setProfitabilityToBizMsg(bizMsg, prftApiPMsg, glblMsg);

        bizMsg.setMessageInfo(ZZM8100I);
    }

//    /**
//     * <pre>
//     * Write csv file Contract Info
//     * @param bizMsg NWAL1540CMsg
//     * @param rs     ResultSet
//     * @throws SQLException 
//     * @throws EZDAbendException 
//     * @throws SQLException
//     * </pre>
//     */
//    public static void writeCsvFileInfo(NWAL1540CMsg bizMsg, ResultSet rs) throws EZDAbendException, SQLException {
//
//        NWAL1540F00FMsg fMsg = new NWAL1540F00FMsg();
//        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
//        csvOutFile.writeHeader(CSV_HEADER_INFO);
//
//        do {
//            if (rs.getRow() > MAX_DOWNLOAD_CNT) {
//                bizMsg.setMessageInfo(NZZM0007E, null);
//                break;
//            }
//
//            ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum, rs.getString("TRX_HDR_NUM_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.ordPrftVrsnNum, rs.getBigDecimal("ORD_PRFT_VRSN_NUM_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcNegoDealAmt_H, rs.getBigDecimal("FUNC_NEGO_DEAL_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncRepRevAdjAmt_H, rs.getBigDecimal("TOT_FUNC_REP_REV_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncRepRevAdjAmt_H, rs.getBigDecimal("TOT_FUNC_REP_REV_ADJ_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncFinalFlAmt_H, rs.getBigDecimal("TOT_FUNC_FINAL_FL_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcGrsPrftAmt_H, rs.getBigDecimal("FUNC_GRS_PRFT_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.grsPrftPct_H, rs.getBigDecimal("GRS_PRFT_PCT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncMsrpAmt_H, rs.getBigDecimal("TOT_FUNC_MSRP_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncStdFlAmt_H, rs.getBigDecimal("TOT_FUNC_STD_FL_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncFlAdjAmt_H, rs.getBigDecimal("TOT_FUNC_FL_ADJ_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.csmpOrdFlg_H, rs.getString("CSMP_ORD_FLG_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncCsmpCrAmt_H, rs.getBigDecimal("TOT_FUNC_CSMP_CR_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncCsmpFlAmt_H, rs.getBigDecimal("TOT_FUNC_CSMP_FL_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.csmpContrNum_H, rs.getString("CSMP_CONTR_NUM_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dlrRefNum_H, rs.getString("DLR_REF_NUM_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_HS, ZYPDateUtil.formatDisp8ToEzd(rs.getString("CSMP_CONTR_START_DT_H")));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_HE, ZYPDateUtil.formatDisp8ToEzd(rs.getString("CSMP_CONTR_END_DT_H")));
//            ZYPEZDItemValueSetter.setValue(fMsg.lastPrftCalcUsrId_H, rs.getString("LAST_PRFT_CALC_USR_ID_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem23Txt_H, ZYPDateUtil.formatEzd17ToDisp(rs.getString("LAST_PRFT_CALC_TS_H")));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncByotAmt_H, rs.getBigDecimal("TOT_FUNC_BYOT_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncSvcRevTrnsfAmt_H, rs.getBigDecimal("TOT_FUNC_SVC_REV_TRNSF_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncSvcCostTrnsfAmt_H, rs.getBigDecimal("TOT_FUNC_SVC_COST_TRNSF_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncProSvcAmt_H, rs.getBigDecimal("TOT_FUNC_PRO_SVC_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncOmMaintBllblAmt_H, rs.getBigDecimal("TOT_FUNC_OM_MAINT_BLLBL_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcAltGrsPrftAmt_H, rs.getBigDecimal("FUNC_ALT_GRS_PRFT_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.altGrsPrftPct_H, rs.getBigDecimal("ALT_GRS_PRFT_PCT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncDlrCrAmt_H, rs.getBigDecimal("TOT_FUNC_DLR_CR_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncDlrInvAmt_H, rs.getBigDecimal("TOT_FUNC_DLR_INV_AMT_H"));
//            ZYPEZDItemValueSetter.setValue(fMsg.totFuncRedCompAmt_H, rs.getBigDecimal("TOT_FUNC_RED_COMP_AMT_H"));
//
//            ZYPEZDItemValueSetter.setValue(fMsg.ordPrftTrxCatgCd_D, rs.getString("ORD_PRFT_TRX_CATG_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.trxLineNum_D, rs.getString("TRX_LINE_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.trxLineSubNum_D, rs.getString("TRX_LINE_SUB_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdPosnNum_D, rs.getString("DS_ORD_POSN_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsCpoLineNum_D, rs.getBigDecimal("DS_CPO_LINE_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsCpoLineSubNum_D, rs.getBigDecimal("DS_CPO_LINE_SUB_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_D, rs.getString("MDSE_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.mdseNm_D, rs.getString("MDSE_NM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_D, rs.getString("RTL_WH_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_D, rs.getString("RTL_SWH_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.mdseInvtyCostPct_D, rs.getBigDecimal("MDSE_INVTY_COST_PCT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdLineCatgCd_D, rs.getString("DS_ORD_LINE_CATG_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.ordQty_D, rs.getBigDecimal("ORD_QTY_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.custUomCd_D, rs.getString("CUST_UOM_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.flPrcListCd_D, rs.getString("FL_PRC_LIST_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.csmpPrcListCd_D, rs.getString("CSMP_PRC_LIST_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcDlrCrAmt_D, rs.getBigDecimal("FUNC_DLR_CR_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcRedCompAmt_D, rs.getBigDecimal("FUNC_RED_COMP_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitFlPrcAmt_D, rs.getBigDecimal("FUNC_UNIT_FL_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcInitFlPrcAmt_D, rs.getBigDecimal("FUNC_INIT_FL_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.lineWtAmtRate_D, rs.getBigDecimal("LINE_WT_AMT_RATE_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcGenlFlAdjAmt_D, rs.getBigDecimal("FUNC_GENL_FL_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcSpecFlAdjAmt_D, rs.getBigDecimal("FUNC_SPEC_FL_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcManFlAdjAmt_D, rs.getBigDecimal("FUNC_MAN_FL_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcFlAdjAmt_D, rs.getBigDecimal("FUNC_FL_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcCsmpUnitCrAmt_D, rs.getBigDecimal("FUNC_CSMP_UNIT_CR_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcCsmpCrAmt_D, rs.getBigDecimal("FUNC_CSMP_CR_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcCsmpFlPrcAmt_D, rs.getBigDecimal("FUNC_CSMP_FL_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcSvcCostTrnsfAmt_D, rs.getBigDecimal("FUNC_SVC_COST_TRNSF_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcWtSvcCostTrnsfAmt_D, rs.getBigDecimal("FUNC_WT_SVC_COST_TRNSF_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalFlPrcAmt_D, rs.getBigDecimal("FUNC_FINAL_FL_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitListPrcAmt_D, rs.getBigDecimal("FUNC_UNIT_LIST_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcNetUnitPrcAmt_D, rs.getBigDecimal("FUNC_NET_UNIT_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcNetSellPrcAmt_D, rs.getBigDecimal("FUNC_NET_SELL_PRC_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcInitRepRevAmt_D, rs.getBigDecimal("FUNC_INIT_REP_REV_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcGenlRepRevAdjAmt_D, rs.getBigDecimal("FUNC_GENL_REP_REV_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcSpecRepRevAdjAmt_D, rs.getBigDecimal("FUNC_SPEC_REP_REV_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcManRepRevAdjAmt_D, rs.getBigDecimal("FUNC_MAN_REP_REV_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcRepRevAdjAmt_D, rs.getBigDecimal("FUNC_REP_REV_ADJ_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcSvcRevTrnsfAmt_D, rs.getBigDecimal("FUNC_SVC_REV_TRNSF_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcWtSvcRevTrnsfAmt_D, rs.getBigDecimal("FUNC_WT_SVC_REV_TRNSF_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalRepRevAmt_D, rs.getBigDecimal("FUNC_FINAL_REP_REV_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitMsrpAmt_D, rs.getBigDecimal("FUNC_UNIT_MSRP_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitStdCostAmt_D, rs.getBigDecimal("FUNC_UNIT_STD_COST_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalStdCostAmt_D, rs.getBigDecimal("FUNC_FINAL_STD_COST_AMT_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.csmpContrNum_D, rs.getString("CSMP_CONTR_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dlrRefNum_D, rs.getString("DLR_REF_NUM_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_DS, ZYPDateUtil.formatDisp8ToEzd(rs.getString("CSMP_CONTR_START_DT_D")));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem10Txt_DE, ZYPDateUtil.formatDisp8ToEzd(rs.getString("CSMP_CONTR_END_DT_D")));
//            ZYPEZDItemValueSetter.setValue(fMsg.chngOrdFlg_D, rs.getString("CHNG_ORD_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_D, rs.getString("COA_MDSE_TP_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_D, rs.getString("COA_PROD_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.mdseItemTpCd_D, rs.getString("MDSE_ITEM_TP_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.ordPrftRuleTpCd_D, rs.getString("ORD_PRFT_RULE_TP_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.flPrcCalcInclFlg_D, rs.getString("FL_PRC_CALC_INCL_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.repRevCalcInclFlg_D, rs.getString("REP_REV_CALC_INCL_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.discMdseTpFlg_D, rs.getString("DISC_MDSE_TP_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.redFlPrcFlg_D, rs.getString("RED_FL_PRC_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.redRepRevFlg_D, rs.getString("RED_REP_REV_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.discAllocMethCd_D, rs.getString("DISC_ALLOC_METH_CD_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dlrCrPrftInclFlg_D, rs.getString("DLR_CR_PRFT_INCL_FLG_D"));
//            ZYPEZDItemValueSetter.setValue(fMsg.redCompAmtFlg_D, rs.getString("RED_COMP_AMT_FLG_D"));
//
//            csvOutFile.write();
//        } while (rs.next());
//        csvOutFile.close();
//    }

    public static void writeCsvFileInfo(NWAL1540CMsg bizMsg, NWAL1540SMsg glblMsg) {

        NWAL1540F00FMsg fMsg = new NWAL1540F00FMsg();

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HEADER_INFO);

        // Header copy
        ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum_H, bizMsg.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.ordPrftVrsnNum_H, bizMsg.ordPrftVrsnNum.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.funcNegoDealAmt_H, bizMsg.funcNegoDealAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncRepRevAmt_H, bizMsg.totFuncRepRevAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncFinalFlAmt_H, bizMsg.totFuncFinalFlAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncRepRevAdjAmt_H, bizMsg.totFuncRepRevAdjAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.funcGrsPrftAmt_H, bizMsg.funcGrsPrftAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.grsPrftPct_H, bizMsg.grsPrftPct.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.altGrsPrftPct_H, bizMsg.altGrsPrftPct.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.xxOrdPrftFndrFeeAmt_H, bizMsg.xxOrdPrftFndrFeeAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.xxOrdPrftIstlCrAmt_H, bizMsg.xxOrdPrftIstlCrAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncDlrCrAmt_H, bizMsg.totFuncDlrCrAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncMsrpAmt_H, bizMsg.totFuncMsrpAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncStdFlAmt_H, bizMsg.totFuncStdFlAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncFlAdjAmt_H, bizMsg.totFuncFlAdjAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.csmpOrdFlg_H, bizMsg.csmpOrdFlg.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncCsmpCrAmt_H, bizMsg.totFuncCsmpCrAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.csmpContrNum_H, bizMsg.csmpContrNum.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncByotAmt_H, bizMsg.totFuncByotAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncSvcRevTrnsfAmt_H, bizMsg.totFuncSvcRevTrnsfAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncSvcCostTrnsfAmt_H, bizMsg.totFuncSvcCostTrnsfAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.totFuncProSvcAmt_H, bizMsg.totFuncProSvcAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.xxOrdPrftSvcAmt_H, bizMsg.xxOrdPrftSvcAmt.getValue());
        ZYPEZDItemValueSetter.setValue(fMsg.xxOrdPrftSplyAmt_H, bizMsg.xxOrdPrftSplyAmt.getValue());

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            // Detail copy
            ZYPEZDItemValueSetter.setValue(fMsg.ordPrftTrxCatgCd_D, glblMsg.A.no(i).ordPrftTrxCatgCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.xxLineNum_D, glblMsg.A.no(i).xxLineNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdPosnNum_D, glblMsg.A.no(i).dsOrdPosnNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_D, glblMsg.A.no(i).mdseCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_D, glblMsg.A.no(i).mdseDescShortTxt_A.getValue());
            // 2016/10/18 S21_NA#15193 Mod Start
//            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpNm_D, glblMsg.A.no(i).coaMdseTpNm_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.coaProjNm_D, glblMsg.A.no(i).coaProjNm_A.getValue());
            // 2016/10/18 S21_NA#15193 Mod End
            ZYPEZDItemValueSetter.setValue(fMsg.ordQty_D, glblMsg.A.no(i).ordQty_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitMsrpAmt_D, glblMsg.A.no(i).funcUnitMsrpAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcCsmpFlPrcAmt_D, glblMsg.A.no(i).funcCsmpFlPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcNetUnitPrcAmt_D, glblMsg.A.no(i).funcNetUnitPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcNetSellPrcAmt_D, glblMsg.A.no(i).funcNetSellPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalUnitFlPrcAmt_D, glblMsg.A.no(i).funcFinalUnitFlPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalUnitRevAmt_D, glblMsg.A.no(i).funcFinalUnitRevAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalFlPrcAmt_D, glblMsg.A.no(i).funcFinalFlPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcFinalRepRevAmt_D, glblMsg.A.no(i).funcFinalRepRevAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcCsmpUnitCrAmt_D, glblMsg.A.no(i).funcCsmpUnitCrAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.csmpContrNum_D, glblMsg.A.no(i).csmpContrNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.dlrRefNum_D, glblMsg.A.no(i).dlrRefNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm_D, glblMsg.A.no(i).prcCatgNm_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitListPrcAmt_D, glblMsg.A.no(i).funcUnitListPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm_D2, glblMsg.A.no(i).prcCatgNm_A2.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitFlPrcAmt_D, glblMsg.A.no(i).funcUnitFlPrcAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.flPrcListNm_D, glblMsg.A.no(i).flPrcListNm_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.lineWtAmtRate_D, glblMsg.A.no(i).lineWtAmtRate_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcFlAdjAmt_D, glblMsg.A.no(i).funcFlAdjAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcRepRevAdjAmt_D, glblMsg.A.no(i).funcRepRevAdjAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcUnitStdCostAmt_D, glblMsg.A.no(i).funcUnitStdCostAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcWtSvcRevTrnsfAmt_D, glblMsg.A.no(i).funcWtSvcRevTrnsfAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcWtSvcCostTrnsfAmt_D, glblMsg.A.no(i).funcWtSvcCostTrnsfAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcWtManFlAdjAmt_D, glblMsg.A.no(i).funcWtManFlAdjAmt_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.funcWtManRevAdjAmt_D, glblMsg.A.no(i).funcWtManRevAdjAmt_A.getValue());

            ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm_D2, glblMsg.A.no(i).prcCatgNm_A2.getValue());

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    //    private static String getEditDate(String val, String inFormat, String outFormat) {
    //        if (val == null) {
    //            return "";
    //        }
    //        return ZYPDateUtil.DateFormatter(val, inFormat, outFormat);
    //    }

    // 2016/07/25 QC#11636
    public static void getCreditRebillCd(NWAL1540CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1540Query.getInstance().getCreditRebillCd(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.crRebilCd.clear();
        }
        @SuppressWarnings("unchecked")
        String crRebilCd = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.crRebilCd, crRebilCd);
    }

    // Add Start 2017/10/11 QC#21664
    private static boolean checkOrdProcNodePrflCd(NWZC156001PMsg pMsg) {

        S21SsmEZDResult ssmResult = NWAL1540Query.getInstance().getPrftRuleTp(pMsg);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            String ordProcNodePrflCd = (String) resultMap.get("ORD_PROC_NODE_PRFL_CD");
            if (ORD_PROC_NODE_PRFL_CD_33.equals(ordProcNodePrflCd)) {
                return true;
            }
        }

        return false;
    }
    // Add End 2017/10/11 QC#21664
}

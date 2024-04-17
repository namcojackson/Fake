/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2020.common;

import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.blap.NLAL2020.NLAL2020Query;
import business.blap.NLAL2020.NLAL2020SMsg;
import business.blap.NLAL2020.NLAL2020_ACMsg;
import business.blap.NLAL2020.NLAL2020_ASMsg;
import business.blap.NLAL2020.NLAL2020_ASMsgArray;
import business.blap.NLAL2020.constant.NLAL2020Constant;
import business.db.INVTY_ACCTTMsg;
import business.db.LOC_TPTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NLAL2020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 2016/04/25   Fujitsu         Y.Taoka         Update          NA_QC#7482
 * 05/23/2016   CSAI            Y.Imazu         Update          QC#7759
 * 05/23/2016   CSAI            Y.Imazu         Update          QC#8530
 * 06/02/2016   CSAI            Y.Imazu         Update          QC#8663
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7981
 * 06/06/2016   CSAI            Y.Imazu         Update          QC#5061
 * 08/19/2016   CSAI            Y.Imazu         Update          QC#7981
 * 08/25/2016   CITS            S.Endo          Update          QC#13142
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 10/17/2016   CITS            K.Ogino         Update          QC#10405
 * 04/13/2017   CITS            T.Tokutomi      Update          Merge DS
 * 05/08/2017   CITS            R.Shimamoto     Update          QC#18427
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 * 08/31/2023   Hitachi         TZ.Win          Update          QC#61792
 *</pre>
 */
public class NLAL2020CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NLAL2020CMsg
     */
    public static void createSavedSearchOptionsPullDown(NLAL2020CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getSavedSearchOptionList(bizMsg.glblCmpyCd.getValue(), bizMsg.usrId.getValue());

        if (!ssmResult.isCodeNormal()) {

            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     */
    public static void callNszc0330forDeleteSearch(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL2020Constant.BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {

            createSavedSearchOptionsPullDown(bizMsg);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLAL2020Constant.SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     */
    public static void callNszc0330forSaveSearch(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) || isSameSaveSearchName(bizMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S.getValue());

        } else {

            setSelectSaveSearchName(pMsg, bizMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL2020Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, bizMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.rwsRefNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.bolNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.rwsNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.rwsStsCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.srcOrdNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.sceOrdTpCd_H.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_H)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.svcConfigMstrPk_H.getValue().toString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.shipToRtlWhCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.shipLocCd_H.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.whInEtaDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.whInEtaDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.whInEtaDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.whInEtaDt_TO.getValue());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.serNum_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.rtlWhCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.xxChkBox_CL.getValue());
        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.xxChkBox_WW.getValue());
        // END 2023/08/31 TZ.Win [QC#61792,ADD]

        if (callNszc0330(bizMsg, pMsg)) {

            createSavedSearchOptionsPullDown(bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_S, pMsg.srchOptNm);
            bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLAL2020Constant.SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLAL2020CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            return false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {

                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {

                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLAL2020CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NLAL2020CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLAL2020CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {

                return;
            }

            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }

        return;
    }

    /**
     * callNszc0330
     * @param bizMsg NLAL2020CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NLAL2020CMsg bizMsg, NSZC033001PMsg pMsg) {

        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {

                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     */
    public static void callNszc0330forSearchOption(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, bizMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL2020Constant.BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {

            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_S, pMsg.srchOptNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsRefNum_H, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.bolNum_H, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_H, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsStsCd_H, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.srcOrdNum_H, pMsg.srchOptTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd_H, pMsg.srchOptTxt_06.getValue());

        if (isNumberCheck(pMsg.srchOptTxt_07.getValue())) {

            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_H, new BigDecimal(pMsg.srchOptTxt_07.getValue()));

        } else {

            bizMsg.svcConfigMstrPk_H.clear();
        }

        bizMsg.rtlWhNm_H.clear();
        bizMsg.rmaSlsWhNm_H.clear();
        bizMsg.dsAcctNm_H.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToRtlWhCd_H, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.shipLocCd_H, pMsg.srchOptTxt_09.getValue());

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_10.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(bizMsg.whInEtaDt_FR, pMsg.srchOptTxt_10.getValue());

        } else {

            bizMsg.whInEtaDt_FR.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(bizMsg.whInEtaDt_TO, pMsg.srchOptTxt_11.getValue());

        } else {

            bizMsg.whInEtaDt_TO.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.serNum_H, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CL, pMsg.srchOptTxt_14.getValue());
        // START 2023/08/31 TZ.Win [QC#61792,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_WW, pMsg.srchOptTxt_15.getValue());
        // END 2023/08/31 TZ.Win [QC#61792,ADD]

    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);

                if (sLineMsg instanceof NLAL2020_ASMsg) {
                    // Who Column
                    NLAL2020_ASMsg glblMsgLine = (NLAL2020_ASMsg) sLineMsg;
                    ZYPEZDItemValueSetter.setValue(glblMsgLine.xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(glblMsgLine.xxRecHistCratByNm_A1.getValue()));
                    ZYPEZDItemValueSetter.setValue(glblMsgLine.xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(glblMsgLine.xxRecHistUpdByNm_A1.getValue()));
                }
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

    /**
     * setSsearchResultToGlblMsg
     * @param ssmResult S21SsmEZDResult
     * @param glblMsg NLAL2020SMsg
     * @param glblCmpyCd String
     */
    public static void setSsearchResultToGlblMsg(S21SsmEZDResult ssmResult, NLAL2020SMsg glblMsg, String glblCmpyCd) {

        Map<String, String> rwsHdrOpenStsMap = new HashMap<String, String>();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            String rwsHdrOpenFlg = "";
            String rwsNum = glblMsgLine.rwsNum_A1.getValue();

            if (rwsHdrOpenStsMap.isEmpty() || !rwsHdrOpenStsMap.containsKey(rwsNum)) {
                rwsHdrOpenFlg = getRwsHdrOpenSts(glblMsg, glblMsgLine);
                rwsHdrOpenStsMap.put(rwsNum, rwsHdrOpenFlg);
            } else {
                rwsHdrOpenFlg = rwsHdrOpenStsMap.get(rwsNum);
            }

            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxNum_A1, new BigDecimal(i));
            // Hidden
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsOpenFlg_A1, rwsHdrOpenFlg);

            // Serial Number Edit Control for Return
            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxEdtModeFlg_A1, ZYPConstant.FLG_ON_Y);

            if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.serNumTakeFlg_A1.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.openFlg_A1.getValue())) {

                    if (ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1) && glblMsgLine.poBalQty_A1.getValueInt() == 1) {

                        ZYPEZDItemValueSetter.setValue(glblMsgLine.xxSerNumSrchTxt_A1, ZYPConstant.FLG_OFF_N);

                        if (SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                                && ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxEdtModeFlg_A1, ZYPConstant.FLG_OFF_N);
                        }

                    } else if (ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1) && glblMsgLine.poBalQty_A1.getValueInt() > 1) {

                        if (SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                                && ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxEdtModeFlg_A1, ZYPConstant.FLG_OFF_N);
                        }
                    }

                } else {

                    if (ZYPCommonFunc.hasValue(glblMsgLine.rwsStkQty_A1) && glblMsgLine.rwsStkQty_A1.getValueInt() == 1) {

                        ZYPEZDItemValueSetter.setValue(glblMsgLine.xxSerNumSrchTxt_A1, ZYPConstant.FLG_OFF_N);

                    } else if (ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1) && glblMsgLine.poBalQty_A1.getValueInt() > 1) {

                        if (SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                                && ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxEdtModeFlg_A1, ZYPConstant.FLG_OFF_N);
                        }

                    } else if (ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1) && glblMsgLine.poBalQty_A1.getValueInt() == 0) {

                        if (SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                                && ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1)) {
                            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxEdtModeFlg_A1, ZYPConstant.FLG_OFF_N);
                        }
                    }

                    if (!ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1)) {

                        ZYPEZDItemValueSetter.setValue(glblMsgLine.serNumTakeFlg_A1, ZYPConstant.FLG_OFF_N);
                    }
                }
            }
        }
    }

    /**
     * getRwsHdrOpenSts
     * @param resultMap Map<String, Object>
     * @param resultList List<Map<String, Object>>
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private static String getRwsHdrOpenSts(NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgALine) {

        String rwsNum = glblMsgALine.rwsNum_A1.getValue();
        String rwsHdrOpenFlg = glblMsgALine.rwsOpenFlg_A1.getValue();
        String rwsDtlOpenFlg = glblMsgALine.openFlg_A1.getValue();

        // Header close
        if (!ZYPConstant.FLG_ON_Y.equals(rwsHdrOpenFlg)) {
            return rwsHdrOpenFlg;
        }

        // Detail open
        if (ZYPConstant.FLG_ON_Y.equals(rwsDtlOpenFlg)) {
            return rwsDtlOpenFlg;
        }

        // Check All Detail Status
        for (int i = 0; i < glblMsg.A.length(); i++) {

            String chkRwsNum = glblMsg.A.no(i).rwsNum_A1.getValue();
            String chkRwsDtlOpenFlg = glblMsg.A.no(i).openFlg_A1.getValue();
            if (rwsNum.equals(chkRwsNum)) {
                if (ZYPConstant.FLG_ON_Y.equals(chkRwsDtlOpenFlg)) {
                    return chkRwsDtlOpenFlg;
                }
            }
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * Update the global Message.
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     */
    public static void updateGlblMsg(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * getCancelDate
     * @return String
     */
    public static String getRcvDateTime() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    /**
     * chkPutAwayApiResult
     * @param putAwayPMsgList List<NLZC206001PMsg>
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    public static boolean chkPutAwayApiResult(List<NLZC206001PMsg> putAwayPMsgList, NLAL2020CMsg bizMsg) {

        for (NLZC206001PMsg putAwayPMsg : putAwayPMsgList) {

            if (!chkApiRslt(bizMsg, S21ApiUtil.getXxMsgIdList(putAwayPMsg))) {

                return false;
            }
        }

        return true;
    }

    /**
     * chkApiRslt
     * @param bizMsg NLAL2020CMsg
     * @param msgIdList List<String>
     * @return boolean true : OK, false : NG
     */
    public static boolean chkApiRslt(NLAL2020CMsg bizMsg, List<String> msgIdList) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    bizMsg.setMessageInfo(msgId, null);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * getRtlWhNm
     * @param glblCmpyCd String
     * @param rtlWhCdItem EZDCStringItem
     * @param rtlWhNmItem EZDCStringItem
     * @param errParamTxt String
     * @return boolean
     */
    public static boolean getRtlWhNm(String glblCmpyCd, EZDCStringItem rtlWhCdItem, EZDCStringItem rtlWhNmItem, String errParamTxt) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCdItem.getValue());
        rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {

            rtlWhCdItem.setErrorInfo(1, NLAL2020Constant.NLZM2278E, new String[] {errParamTxt });
            rtlWhNmItem.clear();
            return false;
        }

        ZYPEZDItemValueSetter.setValue(rtlWhNmItem, rtlWhTMsg.rtlWhNm.getValue());
        return true;
    }

    /**
     * getRcvByNm
     * @param glblCmpyCd String
     * @param bizMsgLine NLAL2020_ACMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return boolean true : OK, false : NG
     */
    public static boolean getRcvByNm(String glblCmpyCd, NLAL2020_ACMsg bizMsgLine, NLAL2020_ASMsg glblMsgLine) {

        String rcvRtlWhCd = "";
        String rwsRtlWhCd = "";
        String rwsRtlSwhCd = "";
        String rwsLocTpCd = "";
        String rwsInvtyAcctCd = "";

        if (bizMsgLine != null) {

            rcvRtlWhCd = bizMsgLine.rtlWhCd_A1.getValue();
            rwsRtlWhCd = bizMsgLine.shipToRtlWhCd_A1.getValue();
            rwsRtlSwhCd = bizMsgLine.rtlSwhCd_A1.getValue();
            rwsLocTpCd = bizMsgLine.toLocTpCd_A1.getValue();
            rwsInvtyAcctCd = bizMsgLine.invtyAcctCd_A1.getValue();
            bizMsgLine.rmaSlsWhNm_A1.clear();

        } else if (glblMsgLine != null) {

            rcvRtlWhCd = glblMsgLine.rtlWhCd_A1.getValue();
            rwsRtlWhCd = glblMsgLine.shipToRtlWhCd_A1.getValue();
            rwsRtlSwhCd = glblMsgLine.rtlSwhCd_A1.getValue();
            rwsLocTpCd = glblMsgLine.toLocTpCd_A1.getValue();
            rwsInvtyAcctCd = glblMsgLine.invtyAcctCd_A1.getValue();
            glblMsgLine.rmaSlsWhNm_A1.clear();
        }

        if (ZYPCommonFunc.hasValue(rcvRtlWhCd) && !rcvRtlWhCd.equals(rwsRtlWhCd)) {

            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rcvRtlWhCd);
            rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

            if (rtlWhTMsg == null) {

                setRcvByErrMsg(bizMsgLine, glblMsgLine, NLAL2020Constant.NLZM2278E, new String[] {"Received WH" });
                return false;
            }

            // Location Type Check
            if (!rtlWhTMsg.locTpCd.getValue().equals(rwsLocTpCd)) {

                String locTpDescTxt = getLosTpDescTxt(glblCmpyCd, rwsLocTpCd);

                if (ZYPCommonFunc.hasValue(locTpDescTxt)) {

                    setRcvByErrMsg(bizMsgLine, glblMsgLine, NLAL2020Constant.NLAM0173E, new String[] {locTpDescTxt });

                } else {

                    setRcvByErrMsg(bizMsgLine, glblMsgLine, NLAL2020Constant.NLZM2278E, new String[] {"Received WH" });
                }

                return false;
            }

            // Inventory Account Check
            if (!rtlWhTMsg.invtyAcctCd.getValue().equals(rwsInvtyAcctCd)) {

                String invtyAcctDescTxt = getInvtyAcctDescTxt(glblCmpyCd, rwsInvtyAcctCd);

                if (ZYPCommonFunc.hasValue(invtyAcctDescTxt)) {

                    setRcvByErrMsg(bizMsgLine, glblMsgLine, NLAL2020Constant.NLAM0173E, new String[] {invtyAcctDescTxt });

                } else {

                    setRcvByErrMsg(bizMsgLine, glblMsgLine, NLAL2020Constant.NLZM2278E, new String[] {"Received WH" });
                }

                return false;
            }

            // Inventory Location
            S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getInvtyLoc(glblCmpyCd, rcvRtlWhCd, rwsRtlSwhCd);

            if (ssmResult.isCodeNotFound()) {

                setRcvByErrMsg(bizMsgLine, glblMsgLine, NLAL2020Constant.NLZM2279E, new String[] {"Received WH", "Sub Warehouse" });
                return false;
            }

            Map<String, String> invtyLocMap = (Map<String, String>) ssmResult.getResultObject();

            if (bizMsgLine != null) {

                ZYPEZDItemValueSetter.setValue(bizMsgLine.rmaSlsWhNm_A1, rtlWhTMsg.rtlWhNm.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsgLine.destInvtyLocCd_A1, invtyLocMap.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsgLine.xxToInvtyLocNm_A1, invtyLocMap.get("INVTY_LOC_NM"));

            } else if (glblMsgLine != null) {

                ZYPEZDItemValueSetter.setValue(glblMsgLine.rmaSlsWhNm_A1, rtlWhTMsg.rtlWhNm.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsgLine.destInvtyLocCd_A1, invtyLocMap.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxToInvtyLocNm_A1, invtyLocMap.get("INVTY_LOC_NM"));
            }

        } else {

            if (bizMsgLine != null) {

                ZYPEZDItemValueSetter.setValue(bizMsgLine.rtlWhCd_A1, bizMsgLine.shipToRtlWhCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsgLine.rmaSlsWhNm_A1, bizMsgLine.rtlWhNm_A1.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsgLine.destInvtyLocCd_A1, bizMsgLine.rcvInvtyLocCd_A1.getValue());

            } else if (glblMsgLine != null) {

                ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlWhCd_A1, glblMsgLine.shipToRtlWhCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsgLine.rmaSlsWhNm_A1, glblMsgLine.rtlWhNm_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsgLine.destInvtyLocCd_A1, glblMsgLine.rcvInvtyLocCd_A1.getValue());
            }
        }

        return true;
    }

    /**
     * setRcvByErrMsg
     * @param bizMsgLine NLAL2020_ACMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @param errMsgId String
     * @param errMsgTxt String[]
     */
    private static void setRcvByErrMsg(NLAL2020_ACMsg bizMsgLine, NLAL2020_ASMsg glblMsgLine, String errMsgId, String[] errMsgTxt) {

        if (bizMsgLine != null) {

            setErrMsg(bizMsgLine.rtlWhCd_A1, null, errMsgId, errMsgTxt);

        } else if (glblMsgLine != null) {

            setErrMsg(null, glblMsgLine.rtlWhCd_A1, errMsgId, errMsgTxt);
        }
    }

    /**
     * setErrMsg
     * @param ezCMsgItem EZDCStringItem
     * @param ezSMsgItem EZDSStringItem
     * @param errMsgId String
     * @param errMsgTxt String[]
     */
    private static void setErrMsg(EZDCStringItem ezCMsgItem, EZDSStringItem ezSMsgItem, String errMsgId, String[] errMsgTxt) {

        if (ezCMsgItem != null) {

            if (errMsgTxt != null) {

                ezCMsgItem.setErrorInfo(1, errMsgId, errMsgTxt);

            } else {

                ezCMsgItem.setErrorInfo(1, errMsgId);
            }

        } else if (ezSMsgItem != null) {

            if (errMsgTxt != null) {

                ezSMsgItem.setErrorInfo(1, errMsgId, errMsgTxt);

            } else {

                ezSMsgItem.setErrorInfo(1, errMsgId);
            }
        }
    }

    /**
     * getLosTpDescTxt
     * @param glblCmpyCd String
     * @param locTpCd String
     * @return String
     */
    private static String getLosTpDescTxt(String glblCmpyCd, String locTpCd) {

        LOC_TPTMsg locTpTMsg = new LOC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(locTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(locTpTMsg.locTpCd, locTpCd);
        locTpTMsg = (LOC_TPTMsg) EZDFastTBLAccessor.findByKey(locTpTMsg);

        if (locTpTMsg != null) {

            return locTpTMsg.locTpDescTxt.getValue();
        }

        return null;
    }

    /**
     * getInvtyAcctDescTxt
     * @param glblCmpyCd String
     * @param invtyAcctCd String
     * @return String
     */
    private static String getInvtyAcctDescTxt(String glblCmpyCd, String invtyAcctCd) {

        INVTY_ACCTTMsg invtyAcctTMsg = new INVTY_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(invtyAcctTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invtyAcctTMsg.invtyAcctCd, invtyAcctCd);
        invtyAcctTMsg = (INVTY_ACCTTMsg) EZDFastTBLAccessor.findByKey(invtyAcctTMsg);

        if (invtyAcctTMsg != null) {

            return invtyAcctTMsg.invtyAcctDescTxt.getValue();
        }

        return null;
    }

    /**
     * getShipFromLocNm
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    public static boolean getShipFromLocNm(NLAL2020CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sqlTxt", getPartyCdSelectSql(bizMsg, bizMsg.shipLocCd_H.getValue()));

        S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getParty(params);
        Map<String, Object> outMsg = (Map<String, Object>) ssmResult.getResultObject();

        if (ssmResult.isCodeNotFound() || outMsg == null) {

            bizMsg.shipLocCd_H.setErrorInfo(1, NLAL2020Constant.NLZM2278E, new String[] {"Party" });
            bizMsg.dsAcctNm_H.clear();
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H, (String) outMsg.get("RTL_WH_NM"));
        return true;
    }

    /**
     * getPartyCdSelectSql
     * @param bizMsg NLAL2020CMsg
     * @param fromLocCd String
     * @return String
     */
    public static String getPartyCdSelectSql(NLAL2020CMsg bizMsg, String fromLocCd) {

        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD").append("\n");
        sb.append(",RW.RTL_WH_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR   AS CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD      AS ST_CD").append("\n");
        sb.append(",RW.POST_CD    AS POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH  RW").append("\n");
        sb.append(",RTL_SWH RSW").append("\n");
        sb.append(",WH      W").append("\n");
        sb.append(",LOC_TP  LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("RW.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND RW.RTL_WH_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND RW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND RSW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND RSW.RTL_WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND RSW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND W.GLBL_CMPY_CD = RSW.GLBL_CMPY_CD").append("\n");
        sb.append("AND W.WH_CD = RSW.INVTY_LOC_CD").append("\n");
        sb.append("AND W.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = W.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = W.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = W.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // WAREHOUSE (DUMMY)
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD").append("\n");
        sb.append(",RW.RTL_WH_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR   AS CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD      AS ST_CD").append("\n");
        sb.append(",RW.POST_CD    AS POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH  RW").append("\n");
        sb.append(",WH      W").append("\n");
        sb.append(",LOC_TP  LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD= '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND RW.RTL_WH_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND RW.EZCANCELFLAG= '0'").append("\n");
        sb.append("AND W.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND W.WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND W.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD= W.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = W.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD= W.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Technician
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD").append("\n");
        sb.append(",RW.RTL_WH_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR   AS CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD      AS ST_CD").append("\n");
        sb.append(",RW.POST_CD    AS POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH   RW").append("\n");
        sb.append(",RTL_SWH  RSW").append("\n");
        sb.append(",TECH_LOC TL").append("\n");
        sb.append(",LOC_TP   LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND RW.RTL_WH_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND RW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND RSW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND RSW.RTL_WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND RSW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND TL.GLBL_CMPY_CD = RSW.GLBL_CMPY_CD").append("\n");
        sb.append("AND TL.TECH_LOC_CD  = RSW.INVTY_LOC_CD").append("\n");
        sb.append("AND TL.EZCANCELFLAG  = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = TL.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = TL.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = TL.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Vendor
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" V.VND_CD").append("\n");
        sb.append(",PV.DPLY_VND_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",V.FIRST_LINE_ADDR || ' ' || V.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",V.CTY_ADDR   AS CTY_ADDR").append("\n");
        sb.append(",V.ST_CD      AS ST_CD").append("\n");
        sb.append(",V.POST_CD    AS POST_CD").append("\n");
        sb.append(",V.GLBL_CMPY_CD").append("\n");
        sb.append(",V.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" VND  V").append("\n");
        sb.append(",PO_VND_V PV").append("\n");
        sb.append(",LOC_TP LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    V.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND V.VND_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND V.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD").append("\n");
        sb.append("AND V.VND_CD = PV.VND_CD").append("\n");
        sb.append("AND PV.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = V.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = V.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = V.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Ship To Customer
        sb.append("UNION ALL").append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" ST.SHIP_TO_CUST_CD").append("\n");
        sb.append(",DAC.DS_ACCT_NM").append("\n");
        sb.append(",LT.LOC_TP_CD").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",ST.FIRST_LINE_ADDR || ' ' || ST.SCD_LINE_ADDR AS LINE_ADDR").append("\n");
        sb.append(",ST.CTY_ADDR   AS CTY_ADDR").append("\n");
        sb.append(",ST.ST_CD      AS ST_CD").append("\n");
        sb.append(",ST.POST_CD    AS POST_CD").append("\n");
        sb.append(",ST.GLBL_CMPY_CD").append("\n");
        sb.append(",ST.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" SHIP_TO_CUST ST").append("\n");
        sb.append(",SELL_TO_CUST DAC").append("\n");
        sb.append(",LOC_TP       LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    ST.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        if (ZYPCommonFunc.hasValue(fromLocCd)) {
            sb.append("AND ST.SHIP_TO_CUST_CD = '#SHIP_FROM_LOC_CD#'").append("\n");
        }
        sb.append("AND ST.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND DAC.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND DAC.SELL_TO_CUST_CD = ST.SELL_TO_CUST_CD").append("\n");
        sb.append("AND DAC.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = ST.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = ST.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    RTL_WH NR").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NR.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NR.RTL_WH_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NR.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    WH NW").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NW.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NW.WH_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NW.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    TECH_LOC NT").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NT.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NT.TECH_LOC_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NT.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    VND NV").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NV.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NV.VND_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NV.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    BR B").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    B.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND B.BR_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND B.EZCANCELFLAG = '0')").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", bizMsg.glblCmpyCd.getValue());
        sql = sql.replaceAll("#SHIP_FROM_LOC_CD#", fromLocCd);
        return sql;
    }

    /**
     * check PO Detail
     * @param putAwayPMsg NLZC206001PMsg
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return true(=no Error)/false(=hasError)
     */
    public static boolean checkPurchaseOrderDetail(NLZC206001PMsg putAwayPMsg, NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {
        boolean result = true;
        for (int i = 0; i < putAwayPMsg.RWSPutAwayList.getValidCount(); i++) {

            S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getPODetailInfo(putAwayPMsg.glblCmpyCd.getValue(), putAwayPMsg.RWSPutAwayList.no(i).rwsNum.getValue(), putAwayPMsg.RWSPutAwayList.no(i).rwsDtlLineNum.getValue());
            Map<String, String> dgDpDnInfoMap = (Map<String, String>) ssmResult.getResultObject();
            if (ssmResult.isCodeNotFound() || dgDpDnInfoMap == null) {
                continue;
            }

            ssmResult = NLAL2020Query.getInstance().getAllPODtlKeyList(putAwayPMsg.glblCmpyCd.getValue(), dgDpDnInfoMap.get("PO_ORD_NUM"), putAwayPMsg.RWSPutAwayList.no(i).mdseCd.getValue(), dgDpDnInfoMap.get("PO_ORD_DTL_LINE_NUM"));
            if (ssmResult.isCodeNotFound() || ssmResult.getResultObject() == null) {
                continue;
            }

            ssmResult = NLAL2020Query.getInstance().getActivePODtlKeyList(putAwayPMsg.glblCmpyCd.getValue(), dgDpDnInfoMap.get("PO_ORD_NUM"), putAwayPMsg.RWSPutAwayList.no(i).mdseCd.getValue(), dgDpDnInfoMap.get("PO_ORD_DTL_LINE_NUM"));
            if (ssmResult.isCodeNotFound() || ssmResult.getResultObject() == null) {
                for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                    NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(j);
                    if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A2.getValue())) {
                        if (glblMsgLine.rwsNum_A1.getValue().equals(putAwayPMsg.RWSPutAwayList.no(i).rwsNum.getValue()) && glblMsgLine.rwsDtlLineNum_A1.getValue().equals(putAwayPMsg.RWSPutAwayList.no(i).rwsDtlLineNum.getValue())) {
                            glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLAM1341E);
                            break;
                        }
                    }
                }
                result = false;
            }
        }
        return result;
    }

    /**
     * chkWarehousePermission
     * @param cMsg NLBL2020CMsg
     * @param rtlWhCd String
     * @param functionList List<String>
     * @return true:OK false:NG
     */
    public static boolean isWarehousePermission(NLAL2020CMsg cMsg, String rtlWhCd, List<String> functionList) {

        if (chkWarehousePermtReq(functionList)) {
            // All Wh Permission User
            return true;
        }

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("schdCoordPsnCd", cMsg.usrId.getValue());
        param.put("salesDate", cMsg.slsDt.getValue());
        param.put("maxThruDt", "99991231");

        S21SsmEZDResult result = NLAL2020Query.getInstance().getPermissionWhList(param);

        if (result.isCodeNormal()) {

            List<String> permissionWhList = (List<String>) result.getResultObject();

            if (permissionWhList.isEmpty()) {
                return false;
            }

            if (permissionWhList.contains("*")) {
                // All Wh permission
                return true;
            }

            if (permissionWhList.contains(rtlWhCd)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks for Warehouse Permission Required.
     * @param functionList List<String>
     * @return true, if successful
     */
    public static boolean chkWarehousePermtReq(List<String> functionList) {
        return functionList.contains(NLAL2020Constant.FUNC_ID_ALL_WH_PERMISSION);
    }

    /** QC#18427 Add.
     * checkDuplicateRwsPutAwaySer
     * @param bizMsg        NLAL2020CMsg
     * @param glblMsgLine   NLAL2020_ASMsg
     * @param serNum        String
     * @return boolean
     */
    public static boolean checkDuplicateRwsPutAwaySer(NLAL2020CMsg bizMsg, NLAL2020_ASMsg glblMsgLine, String serNum) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("rwsNum", glblMsgLine.rwsNum_A1.getValue());
        param.put("rwsLineNum", glblMsgLine.rwsDtlLineNum_A1.getValue());
        param.put("mdseCd", glblMsgLine.mdseCd_A1.getValue());
        param.put("serNum", serNum);

        String result = NLAL2020Query.getInstance().checkDuplicateRwsPutAwaySer(param);

        if (ZYPCommonFunc.hasValue(result)) {
            // Duplicate Error
            return false;
        }

        return true;
    }

    /**
    * chkSvcConfigPk
    * @param bizMsg        NLAL2020CMsg
    * @param glblMsgLine   NLAL2020_ASMsg
    * @param serNum        String
    * @return boolean
    */
   public static boolean chkSvcConfigPk(NLAL2020CMsg bizMsg, NLAL2020_ASMsg glblMsgLine, String serNum) {

       HashMap<String, Object> param = new HashMap<String, Object>();
       param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
       param.put("svcConfigMstrPk", glblMsgLine.svcConfigMstrPk_RT.getValue());
       param.put("mdseCd", glblMsgLine.mdseCd_A1.getValue());
       param.put("serNum", serNum);

       boolean result = NLAL2020Query.getInstance().chkSvcConfigPk(param);

       return result;
   }

   // START 2022/10/26 M.Kikushima [QC#60413,ADD]
   /**
    * validatePO for cancel
    * @param bizMsg NLAL2020CMsg
    * @param glblMsg NLAL2020SMsg
    * @return boolean
    */
   public static boolean validateForCancel(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, boolean pageFlg) {

       NLAL2020Query query = NLAL2020Query.getInstance();
       boolean chkWrn = false;
       int firstErrIdx = -1;

       for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

           if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {

               BigDecimal APInvCnt = query.countValidAPInvoiceForCancel(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).srcOrdNum_A1.getValue());

               if (BigDecimal.ZERO.compareTo(APInvCnt) < 0) {
                   chkWrn = true;
                   if(firstErrIdx == -1) {
                       firstErrIdx = i;
                   }
               } else {
                   chkWrn = false;
               }

           }

           if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A2.getValue())) {
               if(chkWrn) {
                   glblMsg.A.no(i).xxChkBox_A2.setErrorInfo(2, NLAL2020Constant.NLAM1358W);
               }
           }

       }

       if (chkWrn && !pageFlg) {
           bizMsg.xxPageShowFromNum.setValue(firstErrIdx);
           NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
       }

       return chkWrn;
   }
   // END 2022/10/26 M.Kikushima [QC#60413,ADD]
}

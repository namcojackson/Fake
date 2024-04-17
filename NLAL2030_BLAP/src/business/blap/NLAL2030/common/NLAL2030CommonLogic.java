/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2030.common;

import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.blap.NLAL2030.NLAL2030Query;
import business.blap.NLAL2030.NLAL2030SMsg;
import business.blap.NLAL2030.NLAL2030_ASMsg;
import business.blap.NLAL2030.NLAL2030_BSMsg;
import business.blap.NLAL2030.constant.NLAL2030Constant;
import business.db.RTL_SWHTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * NLAL2030CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7981
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/09/2016   CSAI            Y.Imazu         Update          QC#7981
 * 2016/07/25   CITS            Y.Nomura        Update          QC#11762
 * 06/08/2017   CITS            M.Naito         Update          QC#18382
 * 02/06/2018   CITS            T.Tokutomi      Update          QC#18461-Sol#085
 * 03/07/2018   CITS            K.Ogino         Update          QC#24413
 * 05/21/2018   CITS            S.Katsuma       Update          QC#25929
 * 01/27/2020   Fujitsu         T.Ogura         Update          QC#55497
 * 11/11/2021   CITS            A.Marte         Update          QC#59350
 * 11/23/2022   CITS            R.Azucena       Update          QC#60835
 * 02/22/2023   Hitachi         TZ.Win          Update          QC#61161
 *</pre>
 */
public class NLAL2030CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NLAL2030CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NLAL2030CMsg bizMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSavedSearchOptionList(bizMsg.glblCmpyCd.getValue(), srchOptUsrId);

        if (!ssmResult.isCodeNormal()) {

            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_L.no(i), (BigDecimal) resultMap.get("SRCH_OPT_PK"));
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_L.no(i), (String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, String srchOptUsrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL2030Constant.BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {

            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLAL2030Constant.SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, String srchOptUsrId) {

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

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL2030Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.trxOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.fromLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.sceOrdTpCd.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.svcConfigMstrPk.getValue().toString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.serNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.rtlSwhCd.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.whInEtaDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.whInEtaDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.whInEtaDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.whInEtaDt_TO.getValue());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.imptInvBolNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.carrCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.rwsNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.rwsStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.flipMdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.whCd.getValue());

        if (callNszc0330(bizMsg, pMsg)) {

            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_S, pMsg.srchOptNm.getValue());
            bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLAL2030Constant.SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NLAL2030CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLAL2030CMsg bizMsg) {

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
     * @param bizMsg NLAL2030CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLAL2030CMsg bizMsg) {

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
     * @param bizMsg NLAL2030CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLAL2030CMsg bizMsg) {

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
     * @param bizMsg NLAL2030CMsg
     * @param pMsg NSZC033001PMsg
     */
    private static boolean callNszc0330(NLAL2030CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (String msgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (ZYPCommonFunc.hasValue(msgId)) {

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
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL2030Constant.BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {

            return;
        }

        bizMsg.dsAcctNm.clear();
        bizMsg.rtlWhNm.clear();
        bizMsg.rtlSwhNm.clear();
        bizMsg.carrNm.clear();
        bizMsg.whNm.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_S, pMsg.srchOptNm);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxOrdNum, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.fromLocCd, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.sceOrdTpCd, pMsg.srchOptTxt_04.getValue());

        if (isNumberCheck(pMsg.srchOptTxt_05.getValue())) {

            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk, new BigDecimal(pMsg.srchOptTxt_05.getValue()));

        } else {

            bizMsg.svcConfigMstrPk.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsRefNum, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.serNum, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd, pMsg.srchOptTxt_09.getValue());

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

        ZYPEZDItemValueSetter.setValue(bizMsg.imptInvBolNum, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.carrCd, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsStsCd, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.flipMdseCd, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.whCd, pMsg.srchOptTxt_17.getValue());
    }


    /**
     * search
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg glblMsg
     * @param searchMode int
     */
    public static void search(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int searchMode) {

        List<String> sceOrdTpList = new  ArrayList<String>();

        if (ZYPCommonFunc.hasValue(bizMsg.sceOrdTpCd)) {

            sceOrdTpList = getSceOrdTpListBefMarg(bizMsg);
        }

        if (NLAL2030Constant.TAB_ID_ORD.equals(bizMsg.xxDplyTab.getValue())) {

//            searchOrd(bizMsg, glblMsg, searchMode, sceOrdTpList);
            searchOrd(bizMsg, glblMsg, sceOrdTpList);

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(bizMsg.xxDplyTab.getValue())) {

            searchRws(bizMsg, glblMsg, searchMode, sceOrdTpList);
        }
    }

    /**
     * searchOrd
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param searchMode int
     * @param sceOrdTpList List<String>
     */
    private static void searchOrd(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int searchMode, List<String> sceOrdTpList) {

        List<String> querySsmIdList = new ArrayList<String>();
        List<String> ordNumList = new ArrayList<String>();
        List<String> ordNumLineNumList = new ArrayList<String>();

        if (searchMode != NLAL2030Constant.SEARCH_MODE_RESEARCH) {

            querySsmIdList = getSsmIdOrTpList(bizMsg, sceOrdTpList, NLAL2030Constant.TAB_ID_ORD);

        } else {

            sceOrdTpList = new ArrayList<String>();

            // For Re-Search Order Number & Line Number
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                if (!ordNumList.contains(glblMsg.A.no(i).trxOrdNum_A1.getValue())) {

                    ordNumList.add(glblMsg.A.no(i).trxOrdNum_A1.getValue());

                    if (!sceOrdTpList.contains(glblMsg.A.no(i).sceOrdTpCd_A1.getValue())) {

                        sceOrdTpList.add(glblMsg.A.no(i).sceOrdTpCd_A1.getValue());
                    }
                }

                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).trxLineSubNum_A1)) {

                    ordNumLineNumList.add(glblMsg.A.no(i).trxOrdNum_A1.getValue().concat(glblMsg.A.no(i).trxLineNum_A1.getValue()).concat(glblMsg.A.no(i).trxLineSubNum_A1.getValue()));

                } else {

                    ordNumLineNumList.add(glblMsg.A.no(i).trxOrdNum_A1.getValue().concat(glblMsg.A.no(i).trxLineNum_A1.getValue()));
                }
            }

            querySsmIdList = getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(), sceOrdTpList, null, NLAL2030Constant.TAB_ID_ORD);
        }

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        bizMsg.rwsRefNum_AP.clear();
        bizMsg.imptInvBolNum_AP.clear();

        if (querySsmIdList == null || querySsmIdList.isEmpty()) {

            bizMsg.xxPageShowFromNum_A.clear();
            bizMsg.xxPageShowToNum_A.clear();
            bizMsg.xxPageShowOfNum_A.clear();
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM0049E);
            return;
        }

        int queryResultCount = 0;

        for (String querySsmId : querySsmIdList) {

            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().searchOrd(bizMsg, glblMsg, searchMode, querySsmId, sceOrdTpList, ordNumList, ordNumLineNumList);

            if (!ssmResult.isCodeNotFound()) {

                if (queryResultCount == glblMsg.A.length()) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NZZM0001W, new String[]{Integer.toString(glblMsg.A.length())});
                    break;
                }

                setSearchOrdResultToGlblMsg(ssmResult, glblMsg);

                queryResultCount = queryResultCount + ssmResult.getQueryResultCount();

                if (queryResultCount > glblMsg.A.length()) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NZZM0001W, new String[]{Integer.toString(glblMsg.A.length())});
                    break;
                }
            }
        }

        if (glblMsg.A.getValidCount() > 0) {

            bizMsg.xxPageShowFromNum_A.setValue(1);
            loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[]{"Search"});

        } else {

            bizMsg.xxPageShowFromNum_A.clear();
            bizMsg.xxPageShowToNum_A.clear();
            bizMsg.xxPageShowOfNum_A.clear();
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM0049E);
        }
    }

    /**
     * searchOrd
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private static void searchOrd(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, List<String> sceOrdTpList) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        bizMsg.rwsRefNum_AP.clear();
        bizMsg.imptInvBolNum_AP.clear();

        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().searchOrd(bizMsg, glblMsg, sceOrdTpList);

        if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NLAL2030Constant.NZZM0001W, new String[] {Integer.toString(glblMsg.A.length()) });
        }

        // add QC#18382
        // check Kitting Cancel
        NLAL2030SMsg newGlblMsg = new NLAL2030SMsg();
        int j = 0;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            S21SsmEZDResult sceOrdTp = null;
            if (ZYPCommonFunc.hasValue(bizMsg.sceOrdTpCd.getValue()) && !bizMsg.sceOrdTpCd.getValue().equals(glblMsg.A.no(i).sceOrdTpCd_A1.getValue())) {
                continue;
            }
            if (NLAL2030Constant.DCC_UNKITTING_CANCEL.equals(glblMsg.A.no(i).sceOrdTpCd_A1.getValue())) {
                sceOrdTp = NLAL2030Query.getInstance().checkKittingCancel(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).rwsNum_A1.getValue());
                if (!sceOrdTp.isCodeNotFound() && !NLAL2030Constant.DCC_UNKITTING_CANCEL.equals(sceOrdTp.getResultObject().toString())) {
                    continue;
                }
            } else if (NLAL2030Constant.DCC_KITTING_CANCEL.equals(glblMsg.A.no(i).sceOrdTpCd_A1.getValue())) {
                sceOrdTp = NLAL2030Query.getInstance().checkKittingCancel(bizMsg.glblCmpyCd.getValue(), glblMsg.A.no(i).rwsNum_A1.getValue());
                if (!sceOrdTp.isCodeNotFound() && !NLAL2030Constant.DCC_KITTING_CANCEL.equals(sceOrdTp.getResultObject().toString())) {
                    continue;
                }
            }
            EZDMsg.copy(glblMsg.A.no(i), null, newGlblMsg.A.no(j), null);
            j++;
        }
        newGlblMsg.A.setValidCount(j);
        glblMsg.A.clear();
        EZDMsg.copy(newGlblMsg.A, null, glblMsg.A, null);

        if (glblMsg.A.getValidCount() > 0) {
            bizMsg.xxPageShowFromNum_A.setValue(1);
            loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[] {"Search" });
        } else {
            bizMsg.xxPageShowFromNum_A.clear();
            bizMsg.xxPageShowToNum_A.clear();
            bizMsg.xxPageShowOfNum_A.clear();
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM0049E);
        }
    }

    /**
     * searchRws
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     * @param searchMode int
     * @param sceOrdTpList List<String>
     */
    public static void searchRws(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg, int searchMode, List<String> sceOrdTpList) {

        List<String> querySsmTpList = new ArrayList<String>();
        List<String> rwsNumList = new  ArrayList<String>();

        if (searchMode == NLAL2030Constant.SEARCH_MODE_RESEARCH) {

            sceOrdTpList = new ArrayList<String>();

            // For Re-Search RWS Number
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

                if (!rwsNumList.contains(glblMsg.B.no(i).rwsNum_B1.getValue())) {

                    rwsNumList.add(glblMsg.B.no(i).rwsNum_B1.getValue());

                    if (!sceOrdTpList.contains(glblMsg.B.no(i).sceOrdTpCd_B1.getValue())) {

                        sceOrdTpList.add(glblMsg.B.no(i).sceOrdTpCd_B1.getValue());
                    }
                }
            }

            querySsmTpList = getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(), sceOrdTpList, null, NLAL2030Constant.TAB_ID_RWS);

         } else if (searchMode == NLAL2030Constant.SEARCH_MODE_TAB) {

             sceOrdTpList = new ArrayList<String>();

             for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                 if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {

                     if (!sceOrdTpList.contains(glblMsg.A.no(i).sceOrdTpCd_A1.getValue())) {

                         sceOrdTpList.add(glblMsg.A.no(i).sceOrdTpCd_A1.getValue());
                     }
                 }
             }

             querySsmTpList = getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(), sceOrdTpList, null, NLAL2030Constant.TAB_ID_RWS);

         } else {

             querySsmTpList = getSsmIdOrTpList(bizMsg, sceOrdTpList, NLAL2030Constant.TAB_ID_RWS);
         }

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.D);

        if (querySsmTpList == null || querySsmTpList.isEmpty()) {

            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM0049E);
            return;
        }

        int queryResultCount = 0;

        for (String querySsmTp : querySsmTpList) {

            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().searchRws(bizMsg, glblMsg, searchMode, querySsmTp, sceOrdTpList, rwsNumList);

            if (!ssmResult.isCodeNotFound()) {

                // START 2018/05/21 S.Katsuma [QC#25929,MOD]
                if (queryResultCount >= glblMsg.B.length()) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NZZM0001W, new String[]{Integer.toString(glblMsg.B.length())});
                    break;
                }
                // END 2018/05/21 S.Katsuma [QC#25929,MOD]
                // QC#18461-Sol#085 modify method.
                setSearchRwsResultToGlblMsg(ssmResult, glblMsg, bizMsg);

                queryResultCount = queryResultCount + ssmResult.getQueryResultCount();

                if (ssmResult.getQueryResultCount() > glblMsg.B.length()) {

                    bizMsg.setMessageInfo(NLAL2030Constant.NZZM0001W, new String[]{Integer.toString(glblMsg.B.length())});
                    break;
                }
            }
        }

        if (glblMsg.B.getValidCount() > 0) {

            bizMsg.xxPageShowFromNum_B.setValue(1);
            loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
            bizMsg.setMessageInfo(NLAL2030Constant.ZZZM9003I, new String[]{"Search"});

        } else {

            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM0049E);
        }
    }

    /**
     * getSceOrdTpListBefMarg
     * @param bizMsg NLAL2030CMsg
     * @return List<String>
     */
    public static List<String> getSceOrdTpListBefMarg(NLAL2030CMsg bizMsg) {

        List<String> sceOrdTpList = new ArrayList<String>();
        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSceOrdTpListBefMarg(bizMsg.glblCmpyCd.getValue(), bizMsg.sceOrdTpCd.getValue());

        if (!ssmResult.isCodeNotFound()) {

            sceOrdTpList = (List<String>) ssmResult.getResultObject();

        } else {

            sceOrdTpList.add(bizMsg.sceOrdTpCd.getValue());
        }

        return sceOrdTpList;
    }

    /**
     * getSsmIdOrTpList
     * @param bizMsg NLAL2030CMsg
     * @param sceOrdTpList List<String>
     * @param dplyTab String
     * @return List<String>
     */
    public static List<String> getSsmIdOrTpList(NLAL2030CMsg bizMsg, List<String> sceOrdTpList, String dplyTab) {

        List<String> querySsmIdOrTpList = new ArrayList<String>();

        // Search By Source Document Number
        if (ZYPCommonFunc.hasValue(bizMsg.trxOrdNum)) {

            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getSsmIdBySrcOrd(bizMsg.glblCmpyCd.getValue(), bizMsg.trxOrdNum.getValue());

            if (!ssmResult.isCodeNotFound()) {

                List<Map<String, String>> resultMapList = (List<Map<String, String>>) ssmResult.getResultObject();
                List<String> ssmIdOrTpListByOrd = new ArrayList<String>();

                for (Map<String, String> resultMap : resultMapList) {

                    // PO
                    if (ZYPCommonFunc.hasValue(resultMap.get("PO_ORD_NUM"))) {

                        ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForPo", dplyTab));

                    // CPO
                    } else if (ZYPCommonFunc.hasValue(resultMap.get("CPO_ORD_NUM"))) {

                        String dsOrdCatgCdForSo = ZYPCodeDataUtil.getVarCharConstValue("CPO_WH_TRANSFER", bizMsg.glblCmpyCd.getValue());

                        if (resultMap.get("DS_ORD_CATG_CD").equals(dsOrdCatgCdForSo)) {

                            ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForSo", dplyTab));

                        } else {

                            ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForCpo", dplyTab));
                        }

                    // PR
                    } else if (ZYPCommonFunc.hasValue(resultMap.get("PRCH_REQ_NUM"))) {

                        ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForPr", dplyTab));

                    // Work Order
                    }  else if (ZYPCommonFunc.hasValue(resultMap.get("WRK_ORD_NUM"))) {

                        if (DS_WRK_ORD_TP.KIT.equals(resultMap.get("DS_WRK_ORD_TP_CD"))) {

                            ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForWoKt", dplyTab));
                            ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForWoKc", dplyTab));

                        } else if (DS_WRK_ORD_TP.UN_KIT.equals(resultMap.get("DS_WRK_ORD_TP_CD"))) {

                            ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForWoKu", dplyTab));
                            ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForWoUc", dplyTab));
                        }

                    // Reman Order
                    } else if (ZYPCommonFunc.hasValue(resultMap.get("RMNF_ORD_NUM"))) {

                        ssmIdOrTpListByOrd.add(getQueryByTab("searchOrdForRu", dplyTab));
                    }
                }

                // Check Source Document Type
                if (ssmIdOrTpListByOrd != null && !ssmIdOrTpListByOrd.isEmpty() && sceOrdTpList != null && !sceOrdTpList.isEmpty()) {

                    List<String> ssmIdOrTpListBySceTp = getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(), sceOrdTpList, bizMsg.sceOrdTpCd.getValue(), dplyTab);

                    for (String ssmIdOrTpBySceTp : ssmIdOrTpListBySceTp) {

                        if (ssmIdOrTpListByOrd.contains(ssmIdOrTpBySceTp)) {

                            querySsmIdOrTpList.add(ssmIdOrTpBySceTp);
                        }
                    }

                } else {

                    querySsmIdOrTpList = ssmIdOrTpListByOrd;
                }
            }

        // Search By RWS Number or Shipment Number or Tracking Number
        } else if (ZYPCommonFunc.hasValue(bizMsg.rwsNum) || ZYPCommonFunc.hasValue(bizMsg.rwsRefNum) || ZYPCommonFunc.hasValue(bizMsg.imptInvBolNum)) {

            S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getRwsSceOrdTp(bizMsg, sceOrdTpList);

            if (!ssmResult.isCodeNotFound()) {

                List<String> rwsSceOrdTpList = (List<String>) ssmResult.getResultObject();

                if (rwsSceOrdTpList != null && !rwsSceOrdTpList.isEmpty()) {

                    querySsmIdOrTpList = getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(), rwsSceOrdTpList, bizMsg.sceOrdTpCd.getValue(), dplyTab);
                }
            }

        // Search By Source Document Type
        } else if (sceOrdTpList != null && !sceOrdTpList.isEmpty()) {

            querySsmIdOrTpList = getSsmIdOrTpBySceOrdTp(bizMsg.glblCmpyCd.getValue(), sceOrdTpList, bizMsg.sceOrdTpCd.getValue(), dplyTab);

        } else {

            querySsmIdOrTpList.add(getQueryByTab("searchOrdForPo", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForCpo", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForSo", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForPr", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoKt", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoKc", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoKu", dplyTab));
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoUc", dplyTab));
            // add QC#11762
            querySsmIdOrTpList.add(getQueryByTab("searchOrdForRu", dplyTab));
        }

        // Check Duplicate
        if (!querySsmIdOrTpList.isEmpty()) {

            List<String> ssmIdForDupChkList = new ArrayList<String>();

            for (String querySsmIdOrTp : querySsmIdOrTpList) {

                if (ssmIdForDupChkList.isEmpty() || !ssmIdForDupChkList.contains(querySsmIdOrTp)) {

                    ssmIdForDupChkList.add(querySsmIdOrTp);
                }
            }

            querySsmIdOrTpList = ssmIdForDupChkList;
        }

        // Query for Flip Mdse Check
        if (!querySsmIdOrTpList.isEmpty() && ZYPCommonFunc.hasValue(bizMsg.flipMdseCd)) {

            List<String> ssmIdForFlipMdseList = new ArrayList<String>();

            for (String querySsmIdOrTp : querySsmIdOrTpList) {

                if (isQueryForFlipMdse(querySsmIdOrTp, dplyTab)) {

                    ssmIdForFlipMdseList.add(querySsmIdOrTp);
                }
            }

            return ssmIdForFlipMdseList;
        }

        return querySsmIdOrTpList;
    }

    /**
     * getSsmIdOrTpBySceOrdTp
     * @param glblCmpyCd String
     * @param sceOrdTpList List<String>
     * @param dplySceOrdTpCd String
     * @param dplyTab String
     * @return List<String> querySsmIdList
     */
    public static List<String> getSsmIdOrTpBySceOrdTp(String glblCmpyCd, List<String> sceOrdTpList, String dplySceOrdTpCd, String dplyTab) {

        List<String> querySsmIdOrTpList = new ArrayList<String>();
        S21SsmEZDResult ssmResult = NLAL2030Query.getInstance().getDsCondConstForRwsCtrl(glblCmpyCd, sceOrdTpList, dplySceOrdTpCd);

        if (!ssmResult.isCodeNotFound()) {

            List<String> dsCondConstValTxt03List = (List<String>) ssmResult.getResultObject();

            for (String dsCondConstValTxt03 : dsCondConstValTxt03List) {

                if (!ZYPCommonFunc.hasValue(dsCondConstValTxt03)) {

                    continue;
                }

                if (dsCondConstValTxt03.equals("PO")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForPo", dplyTab));

                } else if (dsCondConstValTxt03.equals("CPO")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForCpo", dplyTab));

                } else if (dsCondConstValTxt03.equals("SO")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForSo", dplyTab));

                } else if (dsCondConstValTxt03.equals("PR")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForPr", dplyTab));

                } else if (dsCondConstValTxt03.equals("KT")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoKt", dplyTab));

                } else if (dsCondConstValTxt03.equals("KC")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoKc", dplyTab));

                } else if (dsCondConstValTxt03.equals("KU")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoKu", dplyTab));

                } else if (dsCondConstValTxt03.equals("UC")) {

                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForWoUc", dplyTab));

                } else if (dsCondConstValTxt03.equals("RMN")) {
                    // add QC#11762
                    querySsmIdOrTpList.add(getQueryByTab("searchOrdForRu", dplyTab));
                }
            }
        }

        // Check Duplicate
        if (!querySsmIdOrTpList.isEmpty()) {

            List<String> ssmIdForDupChkList = new ArrayList<String>();

            for (String querySsmIdOrTp : querySsmIdOrTpList) {

                if (ssmIdForDupChkList.isEmpty() || !ssmIdForDupChkList.contains(querySsmIdOrTp)) {

                    ssmIdForDupChkList.add(querySsmIdOrTp);
                }
            }

            querySsmIdOrTpList = ssmIdForDupChkList;
        }

        return querySsmIdOrTpList;
    }

    /**
     * getQueryByTab
     * @param querySsmId String
     * @param dplyTab String
     * @return String
     */
    private static String getQueryByTab(String querySsmId, String dplyTab) {

        if (NLAL2030Constant.TAB_ID_ORD.equals(dplyTab)) {

            return querySsmId;

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(dplyTab)) {

            if (querySsmId.equals("searchOrdForPo")) {

                return "PO";

            } else if (querySsmId.equals("searchOrdForCpo")) {

                return "CPO";

            } else if (querySsmId.equals("searchOrdForSo")) {

                return "DT";

            } else if (querySsmId.equals("searchOrdForPr")) {

                return "PR";

            } else if (querySsmId.equals("searchOrdForWoKt")) {

                return "KT";

            } else if (querySsmId.equals("searchOrdForWoKc")) {

                return "KC";

            } else if (querySsmId.equals("searchOrdForWoKu")) {

                return "KU";

            } else if (querySsmId.equals("searchOrdForWoUc")) {

                return "UC";
            } else if (querySsmId.equals("searchOrdForRu")) {
                // add QC#11762
                return "RU";
            }
        }

        return "SO";
    }

    /**
     * isQueryForFlipMdse
     * @param querySsmIdOrTp String
     * @param dplyTab String
     * @return boolean
     */
    private static boolean isQueryForFlipMdse(String querySsmIdOrTp, String dplyTab) {

        if (NLAL2030Constant.TAB_ID_ORD.equals(dplyTab)) {

            if (querySsmIdOrTp.equals("searchOrdForPo")) {

                return true;

            } else if (querySsmIdOrTp.equals("searchOrdForPr")) {

                return true;
            }

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(dplyTab)) {

            if (querySsmIdOrTp.equals("PO")) {

                return true;

            } else if (querySsmIdOrTp.equals("PR")) {

                return true;
            }
        }

        return false;
    }

    /**
     * setSearchOrdResultToGlblMsg
     * @param ssmResult S21SsmEZDResult
     * @param glblMsg NLAL2030SMsg
     */
    private static void setSearchOrdResultToGlblMsg(S21SsmEZDResult ssmResult, NLAL2030SMsg glblMsg) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int glblMsgLineIndex = glblMsg.A.getValidCount();

        for (Map<String, Object> resultMap : resultList) {

            NLAL2030_ASMsg glblMsgLine = glblMsg.A.no(glblMsgLineIndex);
            ZYPEZDItemValueSetter.setValue(glblMsgLine.sceOrdTpNm_A1, (String) resultMap.get("SCE_ORD_TP_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.sceOrdTpCd_A1, (String) resultMap.get("SCE_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxOrdNum_A1, (String) resultMap.get("SRC_DOC_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxLineNum_A1, (String) resultMap.get("SRC_DOC_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxLineSubNum_A1, (String) resultMap.get("SRC_DOC_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.dplyLineNum_A1, (String) resultMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.fromLocCd_A1, (String) resultMap.get("PTY_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.dsAcctNm_A1, (String) resultMap.get("PTY_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlWhCd_A1, (String) resultMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlWhNm_A1, (String) resultMap.get("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.svcConfigMstrPk_A1, (BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsOpenFlg_A1, (String) resultMap.get("RWS_OPEN_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.mdseCd_A1, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.flipMdseCd_A1, (String) resultMap.get("FLIP_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsQty_A1, (BigDecimal) resultMap.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxQty10Num_A1, (BigDecimal) resultMap.get("BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.serNum_A1, (String) resultMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.coaMdseTpCd_A1, (String) resultMap.get("MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.aslMdseCd_A1, (String) resultMap.get("SPLY_ITEM_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlSwhCd_A1, (String) resultMap.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlSwhNm_A1, (String) resultMap.get("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.dsOrdPosnNum_A1, (String) resultMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.serNumTakeFlg_A1, (String) resultMap.get("SER_NUM_TAKE_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsFlg_A1, (String) resultMap.get("RWS_CRT_CTRL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.procModeCd_A1, (String) resultMap.get("RWS_CRT_CTRL_MODE"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTime_A1, (String) resultMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTimeZone_A1, (String) resultMap.get("EZUPTIMEZONE"));

            if (!ZYPConstant.FLG_ON_Y.equals((String) resultMap.get("SRC_DOC_LINE_OPEN_FLG"))) {

                ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsFlg_A1, ZYPConstant.FLG_OFF_N);
            }

            glblMsgLineIndex++;
            glblMsg.A.setValidCount(glblMsgLineIndex);

            if (glblMsgLineIndex == glblMsg.A.length()) {

                break;
            }
        }
    }

    /**
     * setSearchRwsResultToGlblMsg
     * QC#18461-Sol#085 Modify method.
     * @param ssmResult S21SsmEZDResult
     * @param glblMsg NLAL2030SMsg
     * @param bizMsg NLAL2030CMsg
     */
    private static void setSearchRwsResultToGlblMsg(S21SsmEZDResult ssmResult, NLAL2030SMsg glblMsg, NLAL2030CMsg bizMsg) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int glblMsgLineIndex = glblMsg.B.getValidCount();

        // QC#18461-Sol#085 Add.
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String editWhTp = ZYPCodeDataUtil.getVarCharConstValue("NLAL2030_EDIT_WH_ORD_TP_LIST", glblCmpyCd);
        String editRmaOrdTp = ZYPCodeDataUtil.getVarCharConstValue("NLAL2030_EDIT_RMA_ORD_TP_LIST", glblCmpyCd);

        if(!ZYPCommonFunc.hasValue(editWhTp)){
            // Error Message.
            bizMsg.setMessageInfo("NPAM1010E", new String[]{"NLAL2030_EDIT_WH_ORD_TP_LIST"});
            editWhTp = "_";
        }
        if(!ZYPCommonFunc.hasValue(editRmaOrdTp)){
            // Error Message.
            bizMsg.setMessageInfo("NPAM1010E", new String[]{"NLAL2030_EDIT_RMA_ORD_TP_LIST"});
            editRmaOrdTp = "_";
        }

        List<String> editWhOrdTpList = Arrays.asList(editWhTp.split(","));
        List<String> editRmaOrdTpList = Arrays.asList(editRmaOrdTp.split(","));

        for (Map<String, Object> resultMap : resultList) {

            NLAL2030_BSMsg glblMsgLine = glblMsg.B.no(glblMsgLineIndex);
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsStsDescTxt_B1, (String) resultMap.get("RWS_HDR_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsStsCd_B1, (String) resultMap.get("RWS_HDR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsNum_B1, (String) resultMap.get("RWS_NUM"));
            // START 2023/02/22 TZ.Win [QC#61161, ADD]
            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxCratDt_B1, (String) resultMap.get("ASN_CRAT_DT"));
            // END 2023/02/22 TZ.Win [QC#61161, ADD]
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsDtlLineNum_B1, (String) resultMap.get("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlWhCd_B1, (String) resultMap.get("RCV_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlWhNm_B1, (String) resultMap.get("RCV_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsRefNum_B1, (String) resultMap.get("RWS_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.imptInvBolNum_B1, (String) resultMap.get("BOL_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.sceOrdTpNm_B1, (String) resultMap.get("SCE_ORD_TP_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.sceOrdTpCd_B1, (String) resultMap.get("SCE_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxOrdNum_B1, (String) resultMap.get("SRC_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxLineNum_B1, (String) resultMap.get("SRC_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxLineSubNum_B1, (String) resultMap.get("SRC_ORD_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.dplyLineNum_B1, (String) resultMap.get("DPLY_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.fromLocCd_B1, (String) resultMap.get("SHIP_FROM_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.dsAcctNm_B1, (String) resultMap.get("SHIP_FROM_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.svcConfigMstrPk_B1, (BigDecimal) resultMap.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.mdseCd_B1, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.flipMdseCd_B1, (String) resultMap.get("FLIP_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.mdseDescShortTxt_B1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsQty_B1, (BigDecimal) resultMap.get("RWS_QTY"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxQty10Num_B1, (BigDecimal) resultMap.get("RWS_BAL_QTY"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsPutAwayQty_B1, (BigDecimal) resultMap.get("RWS_PUT_AWAY_QTY"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlSwhCd_B1, (String) resultMap.get("RCV_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlSwhNm_B1, (String) resultMap.get("RCV_RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxRtlWhSrchTxt_B1, (String) resultMap.get("ACTL_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.serNum_B1, (String) resultMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.serNumTakeFlg_B1, (String) resultMap.get("SER_NUM_TAKE_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rwsScrCancAvalFlg_B1, (String) resultMap.get("RWS_SCR_CANC_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.xxStsCd10Txt_B1, (String) resultMap.get("RWS_DTL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.openFlg_BH, (String) resultMap.get("RWS_HDR_OPEN_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.openFlg_BD, (String) resultMap.get("RWS_DTL_OPEN_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rcvFlg_BH, (String) resultMap.get("RWS_HDR_RCV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rcvFlg_BD, (String) resultMap.get("RWS_DTL_RCV_FLG"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.poOrdNum_B1, (String) resultMap.get("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.poOrdDtlLineNum_B1, (String) resultMap.get("PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxOrdNum_B2, (String) resultMap.get("TRX_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.trxLineNum_B2, (String) resultMap.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.s80StkStsCd_B1, (String) resultMap.get("RWS_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rcvInvtyLocCd_B1, (String) resultMap.get("RCV_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTime_RH, (String) resultMap.get("EZUPTIME_RWSH"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTimeZone_RH, (String) resultMap.get("EZUPTIMEZONE_RWSH"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTime_RD, (String) resultMap.get("EZUPTIME_RWSD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTimeZone_RD, (String) resultMap.get("EZUPTIMEZONE_RWSD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTime_PH, (String) resultMap.get("EZUPTIME_PORH"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTimeZone_PH, (String) resultMap.get("EZUPTIMEZONE_PORH"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTime_PD, (String) resultMap.get("EZUPTIME_PORD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.ezUpTimeZone_PD, (String) resultMap.get("EZUPTIMEZONE_PORD"));

            // QC#18461-Sol#085 Add.
            ZYPEZDItemValueSetter.setValue(glblMsgLine.rtlWhCd_B2, (String) resultMap.get("RCV_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.thirdPtyDspTpCd_B1, (String) resultMap.get("THIRD_PTY_DSP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.thirdPtyDspTpCd_B2, (String) resultMap.get("THIRD_PTY_DSP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.invtyOwnrCd_B1, (String) resultMap.get("INVTY_OWNR_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsgLine.dsOrdPosnNum_B1, (String) resultMap.get("DS_ORD_POSN_NUM"));

            if (editWhOrdTpList.contains(glblMsgLine.sceOrdTpCd_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxDplyCtrlFlg_WH, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxDplyCtrlFlg_WH, ZYPConstant.FLG_OFF_N);
            }
            if (editRmaOrdTpList.contains(glblMsgLine.sceOrdTpCd_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxDplyCtrlFlg_TP, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxDplyCtrlFlg_TP, ZYPConstant.FLG_OFF_N);
            }

            // Line Close
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.openFlg_BD.getValue())) {

                // Not Received
                if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.rcvFlg_BD.getValue())) {

                    ZYPEZDItemValueSetter.setValue(glblMsgLine.xxQty10Num_B1, BigDecimal.ZERO);

                // 0qty Received
                } else if (glblMsgLine.rwsQty_B1.getValue().compareTo(glblMsgLine.xxQty10Num_B1.getValue()) == 0) {

                    ZYPEZDItemValueSetter.setValue(glblMsgLine.xxQty10Num_B1, BigDecimal.ZERO);
                }
            }

            glblMsgLineIndex++;
            glblMsg.B.setValidCount(glblMsgLineIndex);

            if (glblMsgLineIndex == glblMsg.B.length()) {

                break;
            }
        }
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        int maxDisplayRows = cMsgArray.length();
        int startIndex = 0;

        if (cMsgArray.getClass().equals(bizMsg.A.getClass())) {

            bizMsg.xxPageShowToNum_A.clear();
            bizMsg.xxPageShowOfNum_A.clear();
            startIndex = (bizMsg.xxPageShowFromNum_A.getValueInt() / maxDisplayRows) * maxDisplayRows;

        } else {

            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
            startIndex = (bizMsg.xxPageShowFromNum_B.getValueInt() / maxDisplayRows) * maxDisplayRows;
        }

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

        if (cMsgArray.getClass().equals(bizMsg.A.getClass())) {

            bizMsg.xxPageShowFromNum_A.setValue(startIndex + 1);
            bizMsg.xxPageShowToNum_A.setValue(startIndex + cMsgArray.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(sMsgArray.getValidCount());

        } else {

            bizMsg.xxPageShowFromNum_B.setValue(startIndex + 1);
            bizMsg.xxPageShowToNum_B.setValue(startIndex + cMsgArray.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(sMsgArray.getValidCount());
        }
    }

    /**
     * Update the global Message.
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    public static void updateGlblMsg(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        if (NLAL2030Constant.TAB_ID_ORD.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            int ixG = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
            }

        } else if (NLAL2030Constant.TAB_ID_RWS.equalsIgnoreCase(bizMsg.xxDplyTab.getValue())) {

            int ixG = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;

            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

                EZDMsg.copy(bizMsg.B.no(i), null, glblMsg.B.no(ixG + i), null);
            }

            // Update CheckBox
            String preRwsNum = "";
            String val = "";

            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

                if (preRwsNum.equals(glblMsg.B.no(i).rwsNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxChkBox_B1, val);

                } else {
                    val = glblMsg.B.no(i).xxChkBox_B1.getValue();
                }
            }
        }
    }

    /**
     * getInvtyLocCd
     * @param bizMsg NLAL2030CMsg
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @return String
     */
    public static String getInvtyLocCd(NLAL2030CMsg bizMsg, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwh.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlSwhCd, rtlSwhCd);
        rtlSwh = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwh);

        if (rtlSwh == null) {

            return null;

        } else {

            return rtlSwh.invtyLocCd.getValue();
        }
    }

    /**
     * getDateTime14
     * @return String
     */
    public static  String getDateTime14() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    /**
     * getDateTime17
     * @return String
     */
    public static  String getDateTime17() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmssSSS");

        return yyyymmdd + hhmmss;
    }

    /**
     * checkInputRWSPrint
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsgS
     * @return boolean
     */
    public static boolean checkInputRWSPrint(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        int cntChkOn = 0;

        // Count CheckBox-On
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue())) {

                cntChkOn++;
            }
        }

        if (cntChkOn == 0) {

            // All Un-Select
            bizMsg.setMessageInfo(NLAL2030Constant.NLAM1288E);
            return false;
        }

        return true;
    }

    /**
     * chkApiExecRslt
     * @param bizMsg NLAL2030CMsg
     * @param msgIdList List<String>
     * @return boolean true : OK, false : NG
     */
    public static boolean chkApiExecRslt(NLAL2030CMsg bizMsg, List<String> msgIdList) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    bizMsg.setMessageInfo(msgId, null);

                    if (msgId.endsWith("E")) {

                        // START 01/27/2020 T.Ogura [QC#55497,ADD]
                        S21InfoLogOutput.println(msgId);
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgId, msgId);
                        // END   01/27/2020 T.Ogura [QC#55497,ADD]
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * getRwsRefNum Add QC#24413
     * @param rwsRefNum String
     * @return String
     */
    /* START 2022/11/23 R.Azucena [QC#60835, DEL]
    public static String getRwsRefNum(String rwsRefNum) {

        String retVal = "";
        String strRevision = "1";
        int index = rwsRefNum.lastIndexOf("-");

        if (index != -1) {
            // Number Check
            String suffix = rwsRefNum.substring(index + 1);
            if (ZYPCommonFunc.hasValue(suffix) && ZYPCommonFunc.isNumeric(suffix)) {
                try {
                    strRevision = String.valueOf(Integer.parseInt(suffix) + 1);
                } catch (NumberFormatException e) {
                    retVal = ZYPCommonFunc.concatString(rwsRefNum, "-", strRevision);
                    return retVal;
                }

                if (suffix.substring(0, 1).equals("0")) {
                    retVal = ZYPCommonFunc.concatString(rwsRefNum.substring(0, index), "-", ZYPCommonFunc.leftPad(strRevision, 2, "0"));
                } else {
                    retVal = ZYPCommonFunc.concatString(rwsRefNum.substring(0, index), "-", strRevision);
                }
                return retVal;
            }
        }

        retVal = ZYPCommonFunc.concatString(rwsRefNum, "-", strRevision);

        return retVal;
    } */
    // END 2022/11/23 R.Azucena [QC#60835, DEL]

    // START 2022/11/23 R.Azucena [QC#60835, ADD]
    /**
     * getRwsRefNum
     * @param glblCmpyCd String
     * @param whCd String
     * @param rwsRefNum String
     * @return String
     */
    public static String getRwsRefNum(String glblCmpyCd, String whCd, String rwsRefNum) {

        String retVal = "";
        String strRevision = "1";
        String baseRwsRefNum = rwsRefNum;
        String suffix = null;
        boolean padFlg = false;
        boolean firstPassFlg = false;

        if (rwsRefNum.matches("^.*-[0-9]+$")) {
            // With Revision Info
            int index = rwsRefNum.lastIndexOf("-");
            baseRwsRefNum = rwsRefNum.substring(0, index);
            suffix = rwsRefNum.substring(index + 1);
            strRevision = String.valueOf(Integer.parseInt(suffix) + 1);

            if (suffix.substring(0, 1).equals("0")) {
                padFlg = true;
            }

            if (strRevision.length() > NLAL2030Constant.MAX_REVISION_DIGITS_NUM) {
                // More than 2 digits (100 onwards)
                strRevision = "1";
                firstPassFlg = true;
            }
        }

        List<String> existRwsRefNumList = getExistRwsRefNumList(glblCmpyCd, whCd, baseRwsRefNum);

        while (true) {
            if (padFlg) {
                retVal = ZYPCommonFunc.concatString(baseRwsRefNum, "-", ZYPCommonFunc.leftPad(strRevision, 2, "0"));
            } else {
                retVal = ZYPCommonFunc.concatString(baseRwsRefNum, "-", strRevision);
            }

            if (!existRwsRefNumList.contains(retVal)) {
                // Found unused rwsRefNum
                return retVal;
            }

            strRevision = String.valueOf(Integer.parseInt(strRevision) + 1);

            if (ZYPCommonFunc.hasValue(suffix)) {
                if (retVal.equals(rwsRefNum)) {
                    // Already checked all possible revision values (1-99)
                    return null;
                }

                if (strRevision.length() > NLAL2030Constant.MAX_REVISION_DIGITS_NUM) {
                    if (!firstPassFlg) {
                        strRevision = "1";
                        firstPassFlg = true;
                    } else {
                        // Already checked all possible revision values (1-99)
                        // Handling for 0 or >99 initial revision.
                        return null;
                    }
                }
            } else {
                if (strRevision.length() > NLAL2030Constant.MAX_REVISION_DIGITS_NUM) {
                    // Already checked all possible revision values (1-99)
                    return null;
                }
            }
        }
    }
    // END 2022/11/23 R.Azucena [QC#60835, ADD]

    // START 2021/11/11 A.Marte [QC#59350, ADD]
    /**
     * isValidRwhSwhCobination
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @return boolean
     */
    public static boolean isValidRwhSwhCobination(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        String targetInvtyLoccd = NLAL2030Query.getInstance().getValidInvtyLocCd(glblCmpyCd, rtlWhCd, rtlSwhCd);
        if (!ZYPCommonFunc.hasValue(targetInvtyLoccd)) {
            return false;
        }
        return true;
    }
    // END 2021/11/11 A.Marte [QC#59350, ADD]

    // START 2022/11/23 R.Azucena [QC#60835, ADD]
    /**
     * getExistRwsRefNumList
     * @param glblCmpyCd String
     * @param whCd String
     * @param baseRwsRefNum String
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    private static List<String> getExistRwsRefNumList(String glblCmpyCd, String whCd, String baseRwsRefNum) {
        List<String> outputList = new ArrayList<String>();
        S21SsmEZDResult result = NLAL2030Query.getInstance().getExistRwsRefNumList(glblCmpyCd, whCd, baseRwsRefNum);

        if (result.isCodeNormal()) {
            outputList = (List<String>) result.getResultObject();
        }

        return outputList;
    }
    // END 2022/11/23 R.Azucena [QC#60835, ADD]
}

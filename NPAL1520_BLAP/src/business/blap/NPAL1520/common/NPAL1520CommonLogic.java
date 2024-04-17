/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1520.common;

import static business.blap.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_WH_MGR_PSN_CD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.NMAM0038I;
import static business.blap.NPAL1520.constant.NPAL1520Constant.NZZM0001W;
import static business.blap.NPAL1520.constant.NPAL1520Constant.NZZM0010E;
import static business.blap.NPAL1520.constant.NPAL1520Constant.ROWNUM_SEARCH;
import static business.blap.NPAL1520.constant.NPAL1520Constant.SCRN_ID;
import static business.blap.NPAL1520.constant.NPAL1520Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1520.NPAL1520CMsg;
import business.blap.NPAL1520.NPAL1520Query;
import business.blap.NPAL1520.NPAL1520SMsg;
import business.db.RTL_WHTMsg;
import business.db.RTL_WH_CATGTMsg;
import business.db.SWHTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
*<pre>
* Business ID : NPAL1520 Min-Max Planning Inquiry
*
* Date         Company         Name            Create/Update   Defect No
* ----------------------------------------------------------------------
* 01/11/2016   CITS            Keiichi Masaki  Create          N/A
* 10/25/2018   CITS            M.Naito         Update          QC#28867
*</pre>
*/
public class NPAL1520CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    public static void createPullDownSearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {
        EZDDebugOutput.println(1, "NPAL1520 Min-Max Planning Inquiry Create Pulldown list SearchOption.", null);

        // Clear Pulldown Data
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        // Create Parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, cMsg.srchOptUsrId_U1.getValue());

        // Execute
        S21SsmEZDResult result = NPAL1520Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), (BigDecimal) recode.get(DB_COLUMN_SRCH_OPT_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), (String) recode.get(DB_COLUMN_SRCH_OPT_NM));

                if (i == cMsg.srchOptPk_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Set Item Description
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param validateFlg String
     */
    public static void setItemDescription(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String validateFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd);

        S21SsmEZDResult result = NPAL1520Query.getInstance().getItemDescription(ssmParam);

        if (result.isCodeNormal()) {
            List<String> recode = (List<String>) result.getResultObject();

            // 1 recode only
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) recode.get(0));

        } else {
            cMsg.mdseDescShortTxt.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.mdseCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.mdseCd.getValue() });
            }
        }
    }

    /**
     * Set Warehouse Name
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param validateFlg String
     */
    public static void setWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String validateFlg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W1, existTMsg.rtlWhNm.getValue());
        } else {
            cMsg.rtlWhNm_W1.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.rtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd.getValue() });
            }
        }
    }

    /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param validateFlg String
     */
    public static void setSubWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String validateFlg) {

        SWHTMsg tMsg = new SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.rtlSwhCd.getValue());

        SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S1, existTMsg.rtlSwhNm.getValue());
        } else {
            cMsg.rtlSwhNm_S1.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.rtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlSwhCd.getValue() });
            }
        }
    }

    /**
     * Set Manager Name
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param validateFlg String
     */
    public static void setManagerName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String validateFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WH_MGR_PSN_CD, cMsg.whMgrPsnCd);

        S21SsmEZDResult result = NPAL1520Query.getInstance().getManagerName(ssmParam);

        if (result.isCodeNormal()) {
            List<String> recode = (List<String>) result.getResultObject();

            // 1 recode only
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, (String) recode.get(0));

        } else {
            cMsg.fullPsnNm.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.whMgrPsnCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.whMgrPsnCd.getValue() });
            }
        }
    }

    /**
     * Set Source Warehouse Name
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param validateFlg String
     */
    public static void setSourceWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String validateFlg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.srcRtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W2, existTMsg.rtlWhNm.getValue());
        } else {
            cMsg.rtlWhNm_W2.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.srcRtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.srcRtlWhCd.getValue() });
            }
        }
    }

    /**
     * Set Source Sub Warehouse Name
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param validateFlg String
     */
    public static void setSourceSubWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String validateFlg) {

        SWHTMsg tMsg = new SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.srcRtlSwhCd.getValue());

        SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, existTMsg.rtlSwhNm.getValue());
        } else {
            cMsg.rtlSwhNm_S2.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.srcRtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.srcRtlSwhCd.getValue() });
            }
        }
    }

    /**
     * get RTL_WH_CATG
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    public static void getRtlWhCatg(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {
        RTL_WH_CATGTMsg tMsg = new RTL_WH_CATGTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCatgCd.setValue(cMsg.rtlWhCatgCd_SL.getValue());

        RTL_WH_CATGTMsg rtlWhCatgTMsg = (RTL_WH_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.locTpCd, rtlWhCatgTMsg.locTpCd);
    }

    /**
     * Search
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    public static void search(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        // START 2018/10/25 M.Naito [QC#28867,MOD]
//        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
        ssmParam.put(DB_PARAM_ROWNUM, ROWNUM_SEARCH);
        S21InfoLogOutput.println("### Set Parameters (SQLID: search) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        Map<String, String> logParams = setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());

        ssmParam.put(DB_PARAM_CMSG, cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]

        S21SsmEZDResult result = NPAL1520Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            //cMsg.xxRadioBtn.setValue(0);
            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Call API NSZC033001 (Save Search Option)
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param usrId String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String usrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) //
                || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.mrpPlnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.rtlWhCatgCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.rtlWhNm_W1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.rtlSwhNm_S1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.whMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.procrTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.rtlWhNm_W2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.rtlSwhNm_S2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.mrpEnblFlg);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SL, pMsg.srchOptPk);
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param usrId String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String usrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SL.clear();
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg, String usrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SL, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W1, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S1, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(cMsg.whMgrPsnCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SL, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W2, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.mrpEnblFlg, pMsg.srchOptTxt_16);

    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1520CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1520CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL) //
                        && cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param cMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1520CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NPAL1520CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1520CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_PD.no(i));
            }
        }
        return;
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NPAL1520CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1520CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        cMsg.srchOptPk_SL.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_TX.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // START 2018/10/25 M.Naito [QC#28867,ADD]
    private static Map<String, String> setParamsForLog(NPAL1520CMsg cMsg) {
        Map<String, String> setParams = new HashMap<String, String>();
        setMap(setParams, "mrpPlnNm", cMsg.mrpPlnNm.getValue());
        setMap(setParams, "mdseCd", cMsg.mdseCd.getValue());
        setMap(setParams, "rtlWhCatgCd_SL", cMsg.rtlWhCatgCd_SL.getValue());
        setMap(setParams, "rtlWhCd", cMsg.rtlWhCd.getValue());
        setMap(setParams, "rtlSwhCd", cMsg.rtlSwhCd.getValue());
        setMap(setParams, "whMgrPsnCd", cMsg.whMgrPsnCd.getValue());
        setMap(setParams, "procrTpCd_SL", cMsg.procrTpCd_SL.getValue());
        setMap(setParams, "srcRtlWhCd", cMsg.srcRtlWhCd.getValue());
        setMap(setParams, "srcRtlSwhCd", cMsg.srcRtlSwhCd.getValue());
        setMap(setParams, "mrpEnblFlg", cMsg.mrpEnblFlg.getValue());

        return setParams;
    }

    private static Map<String, String> setMap(Map<String, String> map, String key, String value) {
        if (ZYPCommonFunc.hasValue(value)) {
            map.put(key, value);
        }
        return map;
    }
    // END 2018/10/25 M.Naito [QC#28867,ADD]
}

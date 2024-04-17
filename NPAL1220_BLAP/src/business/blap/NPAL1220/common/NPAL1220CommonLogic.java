/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1220.common;

import static business.blap.NPAL1220.constant.NPAL1220Constant.BIZ_APP_ID;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_COLUMN_ASL_QLFY_TP_CD;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_COLUMN_ASL_QLFY_TP_DESC_TXT;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_PRNT_VND_CD;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_SALES_DATE;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPAL1220.constant.NPAL1220Constant.DB_PARAM_VND_CD;
import static business.blap.NPAL1220.constant.NPAL1220Constant.NMAM0038I;
import static business.blap.NPAL1220.constant.NPAL1220Constant.NZZM0001W;
import static business.blap.NPAL1220.constant.NPAL1220Constant.NZZM0010E;
import static business.blap.NPAL1220.constant.NPAL1220Constant.SCRN_ID;
import static business.blap.NPAL1220.constant.NPAL1220Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1220.NPAL1220CMsg;
import business.blap.NPAL1220.NPAL1220Query;
import business.blap.NPAL1220.NPAL1220SMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS      Takeshi Tokutomi      Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 * 
 *</pre>
 */
public class NPAL1220CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownSearchOption(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1220 ASL Search Create Pulldown list SearchOption.", null);

        // Clear Pulldown Data
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, cMsg.srchOptUsrId_U1.getValue());

        // Execute
        S21SsmEZDResult result = NPAL1220Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), (BigDecimal) recode.get(DB_COLUMN_SRCH_OPT_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), (String) recode.get(DB_COLUMN_SRCH_OPT_NM));

                if (i >= cMsg.srchOptPk_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Qualifier Type
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownQualifierType(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1220 ASL Search Create Pulldown list Qualifier Type.", null);

        // Clear Pulldown Data
        cMsg.aslQlfyTpCd_PD.clear();
        cMsg.aslQlfyTpDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1220Query.getInstance().getQualifierTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.aslQlfyTpCd_PD.no(i), (String) recode.get(DB_COLUMN_ASL_QLFY_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.aslQlfyTpDescTxt_PD.no(i), (String) recode.get(DB_COLUMN_ASL_QLFY_TP_DESC_TXT));

                if (i >= cMsg.aslQlfyTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Merchandise Type
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownMerchandiseType(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "NPAL1220 ASL Search Create Pulldown list Merchandise Type.", null);

        // Clear Pulldown Data
        cMsg.coaMdseTpCd_PD.clear();
        cMsg.coaProjDescTxt_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1220Query.getInstance().getMerchandiseTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd_PD.no(i), (String) recode.get("COA_PROJ_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.coaProjDescTxt_PD.no(i), (String) recode.get("COA_PROJ_DESC_TXT"));

                if (i >= cMsg.coaMdseTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Set Item Description
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void setItemDescription(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd);

        S21SsmEZDResult result = NPAL1220Query.getInstance().getItemDescription(ssmParam);

        if (result.isCodeNormal()) {
            String mdseDescShortTxt = (String) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, mdseDescShortTxt);

        } else {
            cMsg.mdseCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.mdseCd.getValue() });
        }
    }

    /**
     * Set Supplier Name
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void setSupplierName(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRNT_VND_CD, cMsg.prntVndCd);

        S21SsmEZDResult result = NPAL1220Query.getInstance().getSupplierName(ssmParam);

        if (result.isCodeNormal()) {
            String prntVndNm = (String) result.getResultObject();

            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, prntVndNm);

        } else {
            cMsg.prntVndCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.prntVndCd.getValue() });
        }
    }

    /**
     * Search
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put("rowNum", sMsg.A.length() + 1);

        S21SsmEZDResult result = NPAL1220Query.getInstance().search(ssmParam, sMsg);

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
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
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

        // START 2018/01/11 S.Katsuma [QC#12226,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.aslNm);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.prntVndCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.prntVndNm);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.splyItemNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.actvFlg);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.mdseDescShortTxt);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.aslQlfyTpCd_SL);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.aslQlfyRefCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.coaMdseTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.aslNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.locNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.splyItemNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.aslQlfyTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.aslQlfyRefCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.coaMdseTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.actvFlg);
        // END 2018/01/11 S.Katsuma [QC#12226,MOD]

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SL, pMsg.srchOptPk);
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SL.clear();
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm);

        // START 2018/01/11 S.Katsuma [QC#12226,MOD]
//        ZYPEZDItemValueSetter.setValue(cMsg.aslNm, pMsg.srchOptTxt_01);
//        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, pMsg.srchOptTxt_02);
//        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, pMsg.srchOptTxt_03);
//        ZYPEZDItemValueSetter.setValue(cMsg.splyItemNum, pMsg.srchOptTxt_04);
//        ZYPEZDItemValueSetter.setValue(cMsg.actvFlg, pMsg.srchOptTxt_05);
//        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_06);
//        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, pMsg.srchOptTxt_07);
//        ZYPEZDItemValueSetter.setValue(cMsg.aslQlfyTpCd_SL, pMsg.srchOptTxt_08);
//        ZYPEZDItemValueSetter.setValue(cMsg.aslQlfyRefCd, pMsg.srchOptTxt_09);
//        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd_SL, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.aslNm, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(cMsg.locNm, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.splyItemNum, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(cMsg.aslQlfyTpCd_SL, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.aslQlfyRefCd, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd_SL, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.actvFlg, pMsg.srchOptTxt_12);
        // END 2018/01/11 S.Katsuma [QC#12226,MOD]

    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1220CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1220CMsg cMsg) {
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
    private static boolean isSameSaveSearchName(NPAL1220CMsg cMsg) {
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
     * @param cMsg NPAL1220CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1220CMsg cMsg) {
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
     * @param bizMsg NPAL1220CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1220CMsg cMsg, NSZC033001PMsg pMsg) {
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
    

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * Set Supplier Site Name
     * @param cMsg NPAL1220CMsg
     * @param sMsg NPAL1220SMsg
     * @param glblCmpyCd String
     */
    public static void setSupplierSiteName(NPAL1220CMsg cMsg, NPAL1220SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_CD, cMsg.vndCd);

        S21SsmEZDResult result = NPAL1220Query.getInstance().getSupplierSiteName(ssmParam);

        if (result.isCodeNormal()) {
            String locNm = (String) result.getResultObject();

            ZYPEZDItemValueSetter.setValue(cMsg.locNm, locNm);

        } else {
            cMsg.vndCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.vndCd.getValue() });
        }
    }
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1400.common;

import static business.blap.NPAL1400.constant.NPAL1400Constant.BIZ_APP_ID;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPAL1400.constant.NPAL1400Constant.NMAM0038I;
import static business.blap.NPAL1400.constant.NPAL1400Constant.NMAM8181W;
import static business.blap.NPAL1400.constant.NPAL1400Constant.SCRN_ID;
import static business.blap.NPAL1400.constant.NPAL1400Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1400.NPAL1400CMsg;
import business.blap.NPAL1400.NPAL1400Query;
import business.blap.NPAL1400.NPAL1400SMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   CITS            S.Tanikawa      Create          N/A
 * 12/19/2016   CITS            Y.Fujii         Update          QC#16370
 *</pre>
 */
public class NPAL1400CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     * @param glblCmpyCd String
     * @param userId String
     */
    public static void createPullDownSearchOption(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg, String glblCmpyCd, String userId) {

        // Clear Pulldown Data
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, userId);

        // Execute
        S21SsmEZDResult result = NPAL1400Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map record = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), (BigDecimal) record.get(DB_COLUMN_SRCH_OPT_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), (String) record.get(DB_COLUMN_SRCH_OPT_NM));

                if (i >= cMsg.srchOptPk_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown Reman Order Status
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRemanOrderStatus(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.rmnfOrdStsCd_PD.clear();
        cMsg.rmnfOrdStsNm_PD.clear();

        ZYPCodeDataUtil.createPulldownList(RMNF_ORD_STS.class, cMsg.rmnfOrdStsCd_PD, cMsg.rmnfOrdStsNm_PD);
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg, String usrId, String glblCmpyCd) {
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
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdNum, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfOrdStsCd_SL, pMsg.srchOptTxt_06);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_07)) {
            cMsg.rmnfStartDt_FR.setValue(pMsg.srchOptTxt_07.getValue());
        } else {
            cMsg.rmnfStartDt_FR.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_08)) {
            cMsg.rmnfStartDt_TO.setValue(pMsg.srchOptTxt_08.getValue());
        } else {
            cMsg.rmnfStartDt_TO.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMainUnitSerNum, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.rmnfMainUnitMdseCd, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.techTocCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm, pMsg.srchOptTxt_14);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_15)) {
            cMsg.rmnfEndDt_FR.setValue(pMsg.srchOptTxt_15.getValue());
        } else {
            cMsg.rmnfEndDt_FR.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_16)) {
            cMsg.rmnfEndDt_TO.setValue(pMsg.srchOptTxt_16.getValue());
        } else {
            cMsg.rmnfEndDt_TO.clear();
        }
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NPAL1400CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1400CMsg cMsg, NSZC033001PMsg pMsg) {
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

    /**
     * check duplicate search name
     * @param cMsg NPAL1400CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1400CMsg cMsg) {
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
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.rtlSwhNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.rmnfOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.rmnfOrdStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.rmnfStartDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.rmnfStartDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.rmnfMainUnitSerNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.t_MdlNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.rmnfMainUnitMdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.techNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.rmnfEndDt_FR.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.rmnfEndDt_TO.getValue());

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg, glblCmpyCd, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SL, pMsg.srchOptPk);
            // Message ; The process [@] has been successfully
            // completed.
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1400CMsg cMsg) {
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
     * @param cMsg NPAL1400CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1400CMsg cMsg) {
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
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            createPullDownSearchOption(cMsg, sMsg, glblCmpyCd, usrId);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SL.clear();
            cMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, "Delete Search") });
        }
    }

    /**
     * search
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROW_NUM, sMsg.A.length() + 1);

        S21SsmEZDResult result = NPAL1400Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[] {"" + (sMsg.A.length()), "" + (sMsg.A.length()) });
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
     * <pre>
     * getAftDeclPnt
     * </pre>
     * @param globalCompanyCode String
     * @return AftDeclPnt
     */
    public static int getAftDeclPnt(String globalCompanyCode) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        // Execute
        S21SsmEZDResult result = NPAL1400Query.getInstance().getAftDeclPnt(ssmParam);

        if (result.isCodeNormal()) {
            return (Integer) result.getResultObject();
        }
        return 0;
    }
}

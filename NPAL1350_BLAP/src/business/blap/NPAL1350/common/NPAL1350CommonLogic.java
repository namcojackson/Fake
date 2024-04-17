/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1350.common;

import static business.blap.NPAL1350.constant.NPAL1350Constant.BIZ_APP_ID;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_DS_WRK_ORD_STS_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_SRCH_OPT_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_SRCH_OPT_PK;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_SRCH_OPT_APL_ID;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_SRCH_OPT_USR_ID;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_WRK_ORD_TP_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_WRK_ORD_NUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NMAM0038I;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NMAM8181W;
import static business.blap.NPAL1350.constant.NPAL1350Constant.SCRN_ID;
import static business.blap.NPAL1350.constant.NPAL1350Constant.ZZZM9003I;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_ACCESS_PARTS_RETURN_CODE_NORMAL;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_ATTN_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_01;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_02;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_03;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_04;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_05;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_06;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_07;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_08;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_09;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_10;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_11;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_12;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_13;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_14;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_KIT_CMNT_TXT_15;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_BOM_RPT_TS;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_CHILD_MDSE_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FIFTH_LINE_ADDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FIRST_LINE_ADDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_MDSE_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_ORD_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FRTH_LINE_ADDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_GLBL_CMPY_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_INTL_LANG_VAL_COL_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_KIT_BOM_PRINT_FLG;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_ORIG_GOODS_MDSE_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_ORIG_GOODS_ORD_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_ORIG_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RQST_RCV_DT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_SCD_LINE_ADDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_SIXTH_LINE_ADDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_SO_NUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_SPLY_RTL_SWH_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_THIRD_LINE_ADDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_USR_ID;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_WH_OWNR_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_WRK_ORD_DTL_LINE_NUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_WRK_ORD_MSG_TXT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_WRK_ORD_NUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_INVTY_LOC_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.INSERT_WRK_ORD_BOM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NLBM0024E;
import static business.blap.NPAL1350.constant.NPAL1350Constant.REPORT_EXCEPTION;
import static business.blap.NPAL1350.constant.NPAL1350Constant.REPORT_NAME;
import static business.blap.NPAL1350.constant.NPAL1350Constant.REPRINT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.WO_RPT_ID;
import static business.blap.NPAL1350.constant.NPAL1350Constant.WRK_ORD_STS_PRINT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.ZZXM0001E;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NPAM0006E;
import static business.blap.NPAL1350.constant.NPAL1350Constant.UPDATE_WRK_ORD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NPAL1350.NPAL1350CMsg;
import business.blap.NPAL1350.NPAL1350Query;
import business.blap.NPAL1350.NPAL1350SMsg;

import business.db.KIT_BOM_WRKTMsg;
import business.db.WRK_ORDTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1350 Kitting WO Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NPAL1350CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * Create Pulldown Search Option
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownSearchOption(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.srchOptPk_PD.clear();
        cMsg.srchOptNm_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SRCH_OPT_APL_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_SRCH_OPT_USR_ID, cMsg.srchOptUsrId_U1.getValue());

        // Execute
        S21SsmEZDResult result = NPAL1350Query.getInstance().getSearchOptionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> searchOptionList = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {

                if (i >= cMsg.srchOptPk_PD.length()) {
                    break;
                }

                Map<String, Object> record = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_PD.no(i), (BigDecimal) record.get(DB_COLUMN_SRCH_OPT_PK));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_PD.no(i), (String) record.get(DB_COLUMN_SRCH_OPT_NM));
            }
        }
    }

    /**
     * Create Pulldown Work Order Type
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownWorkOrderType(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.dsWrkOrdTpCd_PD.clear();
        cMsg.dsWrkOrdTpDescTxt_PD.clear();

        ZYPCodeDataUtil.createPulldownList(DS_WRK_ORD_TP.class, cMsg.dsWrkOrdTpCd_PD, cMsg.dsWrkOrdTpDescTxt_PD);

    }

    /**
     * Create Pulldown Work Order Status
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownWorkOrderStatus(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.dsWrkOrdStsNm_PC.clear();
        cMsg.dsWrkOrdStsNm_PD.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1350Query.getInstance().getWorkOrderStatusPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> workOrderStatusList = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < workOrderStatusList.size(); i++) {

                if (i >= cMsg.dsWrkOrdStsNm_PC.length()) {
                    break;
                }

                Map<String, Object> record = workOrderStatusList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdStsNm_PC.no(i), (String) record.get(DB_COLUMN_DS_WRK_ORD_STS_NM));
                ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdStsNm_PD.no(i), (String) record.get(DB_COLUMN_DS_WRK_ORD_STS_NM));
            }
        }
    }

    /**
     * Search
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_WRK_ORD_TP_CD, WRK_ORD_TP.INTERNAL_KIT);
        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.A.length() + 1);

        S21SsmEZDResult result = null;
        result = NPAL1350Query.getInstance().search(ssmParam, sMsg);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        // Max Recode Over
        int queryResCnt = result.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8181W, new String[] {String.format("%d", sMsg.A.length()), String.format("%d", sMsg.A.length())});
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

    }

    /**
     * check duplicate search name
     * @param cMsg NPAL1350CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1350CMsg cMsg) {
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
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String usrId, String glblCmpyCd) {
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

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.wrkOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.dsWrkOrdTpCd_PL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.dsWrkOrdStsNm_PL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.wrkOrdDt_FM.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.wrkOrdDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.prntMdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.rqstRcvDt_FM.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.rqstRcvDt_TO.getValue());

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
     * isSameSaveSearchName
     * @param cMsg NPAL1350CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1350CMsg cMsg) {
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
     * @param cMsg NPAL1350CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1350CMsg cMsg) {
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
     * @param bizMsg NPAL1350CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1350CMsg cMsg, NSZC033001PMsg pMsg) {
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
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String usrId, String glblCmpyCd) {
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
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String usrId, String glblCmpyCd) {
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

        ZYPEZDItemValueSetter.setValue(cMsg.wrkOrdNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdTpCd_PL, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.dsWrkOrdStsNm_PL, pMsg.srchOptTxt_03);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_04)) {
            cMsg.wrkOrdDt_FM.setValue(pMsg.srchOptTxt_04.getValue());
        } else {
            cMsg.wrkOrdDt_FM.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_05)) {
            cMsg.wrkOrdDt_TO.setValue(pMsg.srchOptTxt_05.getValue());
        } else {
            cMsg.wrkOrdDt_TO.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prntMdseCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, pMsg.srchOptTxt_08);
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_09)) {
            cMsg.rqstRcvDt_FM.setValue(pMsg.srchOptTxt_09.getValue());
        } else {
            cMsg.rqstRcvDt_FM.clear();
        }
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_10)) {
            cMsg.rqstRcvDt_TO.setValue(pMsg.srchOptTxt_10.getValue());
        } else {
            cMsg.rqstRcvDt_TO.clear();
        }
    }

    // 2018/03/14

    /**
     * Print
     * @param cMsg NPAL1350CMsg
     * @return boolean
     */
    public static boolean print(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg, String glblCmpyCd) {

        List<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();
        S21ReportRequestBean requestBean = null;
        S21InputParameter param = null;
        S21EIPPrintingService service =  new S21EIPPrintingService();
        
        String procStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        String rptId = ZYPCodeDataUtil.getVarCharConstValue(WO_RPT_ID, glblCmpyCd);
        
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = profileService.getContextUserInfo();
        
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String chkValue = sMsg.A.no(i).xxChkBox_A1.getValue();
            String wrkOrdNum = sMsg.A.no(i).wrkOrdNum_A1.getValue();
            String wrkOrdEzUpTime = sMsg.A.no(i).xxRqstTs_A1.getValue();
            String wrkOrdEzUpZone = sMsg.A.no(i).xxRqstTz_A1.getValue();
            if (ZYPConstant.CHKBOX_ON_Y.equals(chkValue)) {
                // Work Table Insert
                printProc(glblCmpyCd, wrkOrdNum, procStartTs, userInfo, cMsg, sMsg);

                // Work Order Update
                if (!updateWrkOrd(cMsg, wrkOrdNum, WRK_ORD_STS_PRINT, ZYPConstant.FLG_ON_Y, glblCmpyCd, wrkOrdEzUpTime, wrkOrdEzUpZone)) {
                    return false;
                }

                // EIP Parameter Set
                requestBean = new S21ReportRequestBean(rptId);
                requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                requestBean.setRptArcFlg(true);
                requestBean.setRptTtlNm(REPORT_NAME + wrkOrdNum + " " + procStartTs);

                param = requestBean.getInputParamBeanInstance();
                param.addReportParameter("INTL_LANG_VAL_COL_NM", param.getSystemDefaultLanguage());
                param.addReportParameter("GLBL_CMPY_CD", glblCmpyCd);
                param.addReportParameter("USR_ID", userInfo.getUserId());
                param.addReportParameter("BOM_RPT_TS", procStartTs);
                param.addReportParameter("WRK_ORD_NUM", wrkOrdNum);

                requestBean.setInputParamBean(param);
                requestList.add(requestBean);
            }
        }
        // Create PDF
        try{
            byte[] pdf = service.onlineMergeReports(requestList);
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);
                cMsg.setMessageInfo(ZZZM9003I.toString(), new String[] {"Print"});
            } else {
                throw new S21AbendException(REPORT_EXCEPTION);
            }
        } catch (S21AbendException e) {
              cMsg.setMessageInfo(ZZXM0001E.toString(), new String[] {e.getMessage() });
              return false;
        }

        return true;

    }

    // 2018/03/14 Start
    /**
     * Print
     * @param cMsg NPAL1350CMsg
     * @return boolean
     */
    protected static boolean printProc(String glblCmpyCd, String pWrkOrdNum,String pProcStartTs, S21UserInfo pUserInfo, NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {

        String salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_WRK_ORD_NUM, pWrkOrdNum);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);
        
        // Bom Comment
        Map<String, Object> cmpsnMsgMap = getCmpsnMsgMap(ssmParam);
        
        // Work Order
        S21SsmEZDResult result = NPAL1350Query.getInstance().getKitBom(ssmParam);
        
        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map record = resultList.get(i);

                KIT_BOM_WRKTMsg tMsg = new KIT_BOM_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) record.get(DB_COLUMN_GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdNum, (String) record.get(DB_COLUMN_WRK_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdDtlLineNum, (String) record.get(DB_COLUMN_WRK_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.bomRptTs, (pProcStartTs));
                ZYPEZDItemValueSetter.setValue(tMsg.usrId, (pUserInfo.getUserId()));
                ZYPEZDItemValueSetter.setValue(tMsg.attnNm, (String) record.get(DB_COLUMN_ATTN_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) record.get(DB_COLUMN_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(tMsg.fnshGoodsMdseCd, (String) record.get(DB_COLUMN_FNSH_GOODS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.fnshMdseDescShortTxt, (String) record.get(DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(tMsg.fnshGoodsOrdQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) record.get(DB_COLUMN_FNSH_GOODS_ORD_QTY)).toString());
                // QC#22324 2018/03/14 Start
                //ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdMsgTxt, (String) record.get(DB_COLUMN_DS_WRK_ORD_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(tMsg.wrkOrdMsgTxt, (String) record.get(DB_COLUMN_WRK_ORD_MSG_TXT));
                // QC#22324 2018/03/14 End
                ZYPEZDItemValueSetter.setValue(tMsg.rqstRcvDtTxt, (ZYPDateUtil.formatEzd8ToSysDisp((String) record.get(DB_COLUMN_RQST_RCV_DT))));
                ZYPEZDItemValueSetter.setValue(tMsg.origGoodsMdseCd, (String) record.get(DB_COLUMN_ORIG_GOODS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.origMdseDescShortTxt, (String) record.get(DB_COLUMN_ORIG_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(tMsg.childMdseQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) record.get(DB_COLUMN_CHILD_MDSE_QTY)).toString());
                ZYPEZDItemValueSetter.setValue(tMsg.origGoodsOrdQtyTxt, ZYPFormatUtil.formatNumToSysDisp((BigDecimal) record.get(DB_COLUMN_ORIG_GOODS_ORD_QTY)).toString());
                ZYPEZDItemValueSetter.setValue(tMsg.bomRptDtTxt, (ZYPDateUtil.formatEzd8ToSysDisp(salesDt)));

                if (ZYPConstant.FLG_ON_Y.equals((String) record.get(DB_COLUMN_KIT_BOM_PRINT_FLG))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.kitBomTxt, (String) record.get(REPRINT));
                }

                String scdLineAddr = (String) record.get(DB_COLUMN_SCD_LINE_ADDR);
                String thirdLineAddr = (String) record.get(DB_COLUMN_THIRD_LINE_ADDR);
                String frthLineAddr = (String) record.get(DB_COLUMN_FRTH_LINE_ADDR);
                String fifthLineAddr = (String) record.get(DB_COLUMN_FIFTH_LINE_ADDR);
                String sixthLineAddr = (String) record.get(DB_COLUMN_SIXTH_LINE_ADDR);

                if (scdLineAddr == null && thirdLineAddr == null && frthLineAddr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, sixthLineAddr);
                } else if (scdLineAddr != null && thirdLineAddr == null && frthLineAddr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, sixthLineAddr);
                } else if (scdLineAddr != null && thirdLineAddr != null && frthLineAddr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.fifthLineAddr, sixthLineAddr);
                } else if (scdLineAddr != null && thirdLineAddr != null && frthLineAddr != null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, frthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.fifthLineAddr, fifthLineAddr);
                    ZYPEZDItemValueSetter.setValue(tMsg.sixthLineAddr, sixthLineAddr);
                }
                // QC#22324 2018/03/14 Start
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.whOwnrCd, (String) record.get(DB_COLUMN_WH_OWNR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.splyRtlSwhCd, (String) record.get(DB_COLUMN_SPLY_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.soNum, (String) record.get(DB_COLUMN_SO_NUM));
                
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_01, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_01));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_02, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_02));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_03, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_03));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_04, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_04));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_05, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_05));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_06, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_06));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_07, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_07));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_08, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_08));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_09, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_09));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_10, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_10));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_11, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_11));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_12, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_12));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_13, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_13));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_14, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_14));
                ZYPEZDItemValueSetter.setValue(tMsg.bomKitCmntTxt_15, (String) cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_15));
                // QC#22324 2018/03/14 End
                EZDTBLAccessor.insert(tMsg);

                if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NLBM0024E, new String[] {INSERT_WRK_ORD_BOM});
                    return false;
                }
            }
        }
        return true;

    }
    /**
     * getCmpsnMsgMap
     * @param Map<String, Object> ssmParam(DB_PARAM_CMSG)
     * @return Map<String, String>
     */
    protected static Map<String, Object> getCmpsnMsgMap(Map<String, Object> ssmParam){
        
        Map<String, Object> returnCmpsnMsgMap = new HashMap<String, Object>();
        
        S21SsmEZDResult resultCmpsnMsg = NPAL1350Query.getInstance().getCmpsnMsg(ssmParam);
        if (resultCmpsnMsg.isCodeNormal() && resultCmpsnMsg.getQueryResultCount() == 1) {
            List<Map> cmpsnMsgMapList = (List<Map>) resultCmpsnMsg.getResultObject();
            Map cmpsnMsgMap = cmpsnMsgMapList.get(0);
            
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_01, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_01));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_02, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_02));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_03, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_03));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_04, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_04));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_05, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_05));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_06, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_06));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_07, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_07));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_08, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_08));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_09, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_09));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_10, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_10));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_11, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_11));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_12, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_12));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_13, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_13));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_14, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_14));
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_15, cmpsnMsgMap.get(DB_COLUMN_BOM_KIT_CMNT_TXT_15));
        } else {
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_01, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_02, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_03, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_04, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_05, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_06, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_07, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_08, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_09, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_10, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_11, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_12, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_13, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_14, "");
            returnCmpsnMsgMap.put(DB_COLUMN_BOM_KIT_CMNT_TXT_15, "");
        }
        // Get Serial#
        return returnCmpsnMsgMap;
    }

    /**
     * Get WRK_ORD
     * @param cMsg NPAL1360CMsg
     * @return WRK_ORDTMsg
     */
    public static WRK_ORDTMsg getWrkOrd(String pStrWorkOrdNum, String glblCmpyCd) {

        WRK_ORDTMsg tMsg = new WRK_ORDTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.wrkOrdNum.setValue(pStrWorkOrdNum);

        WRK_ORDTMsg wrkOrdTMsg = (WRK_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        return wrkOrdTMsg;
    }


    /**
     * Check Other Process Update
     * @param tmpTime1 String
     * @param tmpTimeZone1 String
     * @param tmpTime2 String
     * @param tmpTimeZone2 String
     * @return boolean
     */
    public static boolean checkTimeStamp(String tmpTime1, String tmpTimeZone1, String tmpTime2, String tmpTimeZone2) {

        if (!ZYPDateUtil.isSameTimeStamp(tmpTime1, tmpTimeZone1, tmpTime2, tmpTimeZone2)) {
            return false;
        }

        return true;
    }

    /**
     * Update WRK_ORD
     * @param cMsg NPAL1360CMsg
     * @param wrkOrdStsCd String
     * @param kitBomFlg String
     * @return boolean
     */
    public static boolean updateWrkOrd(NPAL1350CMsg cMsg, String pStrWorkOrdNum, String wrkOrdStsCd, String kitBomFlg, String glblCmpyCd, String pStrEzUpTime, String pStrEzUpZone) {

        WRK_ORDTMsg wrkOrdTMsg = getWrkOrd(pStrWorkOrdNum, glblCmpyCd);

        if (wrkOrdTMsg != null) {
            if (!checkTimeStamp(wrkOrdTMsg.ezUpTime.getValue(), wrkOrdTMsg.ezUpTimeZone.getValue(), pStrEzUpTime, pStrEzUpZone)) {
                cMsg.setMessageInfo(NPAM0006E);
                return false;
            }
        } else {
            cMsg.setMessageInfo(NPAM0006E);
            return false;
        }

        WRK_ORDTMsg tMsg = new WRK_ORDTMsg();

        tMsg.glblCmpyCd.setValue(wrkOrdTMsg.glblCmpyCd.getValue());
        tMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
        tMsg.wrkOrdTpCd.setValue(wrkOrdTMsg.wrkOrdTpCd.getValue());
        if (WRK_ORD_STS_PRINT.equals(wrkOrdStsCd)) {
            tMsg.wrkOrdStsCd.setValue(wrkOrdTMsg.wrkOrdStsCd.getValue());
        } else {
            tMsg.wrkOrdStsCd.setValue(wrkOrdStsCd);
        }
        tMsg.wrkOrdDt.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
        tMsg.invtyLocCd.setValue(wrkOrdTMsg.invtyLocCd.getValue());
        tMsg.vndCd.setValue(wrkOrdTMsg.vndCd.getValue());
        tMsg.vndNm.setValue(wrkOrdTMsg.vndNm.getValue());
        tMsg.tocCd.setValue(wrkOrdTMsg.tocCd.getValue());
        tMsg.fnshGoodsMdseCd.setValue(wrkOrdTMsg.fnshGoodsMdseCd.getValue());
        tMsg.fnshGoodsMdseNm.setValue(wrkOrdTMsg.fnshGoodsMdseNm.getValue());
        tMsg.fnshGoodsOrdQty.setValue(wrkOrdTMsg.fnshGoodsOrdQty.getValue());
        tMsg.fnshGoodsRcvQty.setValue(wrkOrdTMsg.fnshGoodsRcvQty.getValue());
        if (WRK_ORD_STS.CANCELLED.equals(wrkOrdStsCd)) {
            tMsg.fnshGoodsBalQty.setValue(wrkOrdTMsg.fnshGoodsCancQty.getValue());
            tMsg.fnshGoodsCancQty.setValue(wrkOrdTMsg.fnshGoodsOrdQty.getValue());  //CHANGE
        } else {
            tMsg.fnshGoodsBalQty.setValue(wrkOrdTMsg.fnshGoodsCancQty.getValue());  //CHANGE
            tMsg.fnshGoodsCancQty.setValue(wrkOrdTMsg.fnshGoodsCancQty.getValue());
        }
        tMsg.rqstRcvDt.setValue(wrkOrdTMsg.rqstRcvDt.getValue());
        tMsg.wrkOrdMsgTxt.setValue(wrkOrdTMsg.wrkOrdMsgTxt.getValue());             //CHANGE
        tMsg.stkStsCd.setValue(wrkOrdTMsg.stkStsCd.getValue());
        tMsg.rcvInvtyLocCd.setValue(wrkOrdTMsg.rcvInvtyLocCd.getValue());
        tMsg.poChrgCd.setValue(wrkOrdTMsg.poChrgCd.getValue());
        tMsg.firstProdCtrlCd.setValue(wrkOrdTMsg.firstProdCtrlCd.getValue());
        tMsg.kitBomPrintFlg.setValue(kitBomFlg);

        //Additional columns
        tMsg.wrkOrdNum.setValue(wrkOrdTMsg.wrkOrdNum.getValue());
        tMsg.dsWrkOrdTpCd.setValue(wrkOrdTMsg.dsWrkOrdTpCd.getValue());
        tMsg.rtlWhCd.setValue(wrkOrdTMsg.rtlWhCd.getValue());
        tMsg.cpltRtlSwhCd.setValue(wrkOrdTMsg.cpltRtlSwhCd.getValue());
        tMsg.oldWrkOrdNum.setValue(wrkOrdTMsg.oldWrkOrdNum.getValue());
        tMsg.fnshMdseDescShortTxt.setValue(wrkOrdTMsg.fnshMdseDescShortTxt.getValue());
        tMsg.prchReqNum.setValue(wrkOrdTMsg.prchReqNum.getValue());
        tMsg.prchReqLineNum.setValue(wrkOrdTMsg.prchReqLineNum.getValue());
        tMsg.prchReqLineSubNum.setValue(wrkOrdTMsg.prchReqLineSubNum.getValue());
        tMsg.cmpsnRevnNum.setValue(wrkOrdTMsg.cmpsnRevnNum.getValue());
        //Additional columns

        EZDTBLAccessor.update(tMsg);

        if (DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(tMsg.getReturnCode())) {
            //ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs_WH, tMsg.ezUpTime);
            //ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz_WH, tMsg.ezUpTimeZone);
            return true;
        } else {
            cMsg.setMessageInfo(NLBM0024E, new String[] {UPDATE_WRK_ORD});
            return false;
        }
    }


    // 2018/03/14 End


}

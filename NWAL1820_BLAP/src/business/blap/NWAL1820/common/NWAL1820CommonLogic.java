/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1820.common;

import static business.blap.NWAL1820.constant.NWAL1820Constant.BIZ_ID;
import static business.blap.NWAL1820.constant.NWAL1820Constant.CSV_FILE_EXT;
import static business.blap.NWAL1820.constant.NWAL1820Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NWAL1820.constant.NWAL1820Constant.CSV_MAX_ROW;
import static business.blap.NWAL1820.constant.NWAL1820Constant.SCRN_ID_00;
import static business.blap.NWAL1820.constant.NWAL1820Constant.ZZZM9001E;
import static business.blap.NWAL1820.constant.NWAL1820Constant.ZZZM9002W;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWAL1820.NWAL1820CMsg;
import business.blap.NWAL1820.NWAL1820Query;
import business.blap.NWAL1820.NWAL1820SMsg;
import business.file.NWAL1820F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1820CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/30   Fujitsu         S.Ohki          Update          S21_NA#7861
 * 2017/03/09   Fujitsu         K.Ishizuka      Update          S21_NA#13856
 *</pre>
 */
public class NWAL1820CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }
    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.shipToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.shipToCustAcctNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.addShipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.serNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.coaBrDescTxt.getValue());
        // 2016/05/30 S21_NA#7861 Mod Start
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.slsRepPsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.psnNum.getValue());
        // 2016/05/30 S21_NA#7861 Mod End
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.dsOrdRsnCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.actlShipDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.actlShipDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.actlShipDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.actlShipDt_TO.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.xxTrxDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.xxTrxDt_TO.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.aquNum.getValue());

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.addShipToCustCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.serNum, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, pMsg.srchOptTxt_06);
        // 2016/05/30 S21_NA#7861 Mod Start
//        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepPsnCd, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, pMsg.srchOptTxt_07);
        // 2016/05/30 S21_NA#7861 Mod End
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd, pMsg.srchOptTxt_10);
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_11.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.actlShipDt_FR, pMsg.srchOptTxt_11.getValue());
        } else {
            bizMsg.actlShipDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_12.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.actlShipDt_TO, pMsg.srchOptTxt_12.getValue());
        } else {
            bizMsg.actlShipDt_TO.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_13.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTrxDt_FR, pMsg.srchOptTxt_13.getValue());
        } else {
            bizMsg.xxTrxDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_14.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTrxDt_TO, pMsg.srchOptTxt_14.getValue());
        } else {
            bizMsg.xxTrxDt_TO.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.aquNum, pMsg.srchOptTxt_15);

    }
    private static boolean isSameSaveSearchName(NWAL1820CMsg bizMsg) {
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
     * @param bizMsg NWAL1820CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NWAL1820CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
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
     * @param bizMsg NWAL1820CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NWAL1820CMsg bizMsg) {
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
     * @param bizMsg NWAL1820CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NWAL1820CMsg bizMsg, NSZC033001PMsg pMsg) {

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
     * createSavedSearchOptionsPullDown
     * @param bizMsg NWAL1820CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NWAL1820CMsg bizMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NWAL1820Query.getInstance().getSavedSearchOptionList(srchOptUsrId);

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
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }
    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL1820CMsg bizMsg = (NWAL1820CMsg) cMsg;

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

    /**
     * clearAll
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     */
    public static void clearAll(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {
        // Clear
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
    }

    /**
     * Create Category Code Pulldown
     * @param bizMsg NWAL1820CMsg
     */
    public static void createCatgPullDown(NWAL1820CMsg bizMsg) {
        // Order Reason
        bizMsg.dsOrdCatgCd_CD.clear();
        bizMsg.dsOrdCatgDescTxt_NM.clear();
        bizMsg.dsOrdCatgCd.clear();
        S21SsmEZDResult ssmResult = NWAL1820Query.getInstance().getDsOrdCatgList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_CD.no(i), resultMap.get("DS_ORD_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt_NM.no(i), resultMap.get("DS_ORD_CATG_DESC_TXT"));
            }

        }
    }

    /**
     * Create Reason Code Pulldown
     * @param bizMsg NWAL1820CMsg
     */
    public static void createRsnPullDown(NWAL1820CMsg bizMsg) {
        // Order Reason
        bizMsg.dsOrdTpCd_CD.clear();
        bizMsg.dsOrdTpDescTxt_NM.clear();
        bizMsg.dsOrdTpCd.clear();
        S21SsmEZDResult ssmResult = NWAL1820Query.getInstance().getDsOrdTpList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }

        }
    }

    /**
     * Create Sub Reason Code Pulldown
     * @param bizMsg NWAL1820CMsg
     */
    public static void createSubRsnPullDown(NWAL1820CMsg bizMsg) {
        // Order Reason
        bizMsg.dsOrdRsnCd_CD.clear();
        bizMsg.dsOrdRsnDescTxt_NM.clear();
        bizMsg.dsOrdRsnCd.clear();
        S21SsmEZDResult ssmResult = NWAL1820Query.getInstance().getDsOrdTpSubReasonList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd_CD.no(i), resultMap.get("DS_ORD_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnDescTxt_NM.no(i), resultMap.get("DS_ORD_RSN_DESC_TXT"));
            }

        }
    }
    
    // ADD START S21_NA QC#13856
    /**
     * CSV DownLoad Search result
     * @param bizMsg NWAL1820CMsg
     * @param glblMsg NWAL1820SMsg
     */
    public static void csvDownload(NWAL1820CMsg bizMsg, NWAL1820SMsg glblMsg) {

        NWAL1820Query.getInstance().searchForCSV(bizMsg, glblMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * Create DownLoad Date for CSV
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {
        /** cMsg */
        private NWAL1820CMsg bizMsg;

        public CreateDownloadData(NWAL1820CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        /** CSV Header Column */
        private static final String[] CSV_HEADER = {"Order #", "Order Date", "Order Category", "Order Reason", "Sub Reason", "Order Status", "Acquisition Number", "Customer Number", "Customer Name", "Customer Location", "Salesrep", "Sales Branch", "Branch Name", "Shipped Date", "Loan Due Date", "Loan Period", "Loan Lines" };

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(ZZZM9001E);
                return false;
            }
            NWAL1820F00FMsg fMsg = new NWAL1820F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            csvOutFile.writeHeader(CSV_HEADER);

            int rowCount = 1;
            do {
                if (rowCount > CSV_MAX_ROW) {
                    bizMsg.setMessageInfo(ZZZM9002W);
                    return true;
                }

                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum_A, result.getString("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxCpoOrdDt_A, result.getString("CPO_ORD_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt_A, result.getString("DS_ORD_CATG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt_A, result.getString("DS_ORD_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdRsnDescTxt_A, result.getString("DS_ORD_RSN_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.ordHdrStsNm_A, result.getString("ORD_HDR_STS_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.aquNum_A, result.getString("AQU_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustAcctCd_A, result.getString("SHIP_TO_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustAcctNm_A, result.getString("SHIP_TO_CUST_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.addShipToCustCd_A, result.getString("ADD_SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.slsRepTocNm_A, result.getString("SLS_REP_TOC_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaBrCd_A, result.getString("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.coaBrDescTxt_A, result.getString("COA_BR_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.actlShipDt_A, result.getString("ACTL_SHIP_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxTrxDt_A, result.getString("LOAN_DUE_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.loanPerDaysAot_A, result.getBigDecimal("LOAN_PER_DAYS_AOT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtlCnt_A, result.getBigDecimal("LINE_CNT"));
                
                csvOutFile.write();
                rowCount++;
            } while (result.next());
            csvOutFile.close();

            return true;
        }
    }
    // ADD END S21_NA QC#13856

}

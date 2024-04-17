/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1850.common;

import static business.blap.NWAL1850.constant.NWAL1850Constant.BIZ_ID;
import static business.blap.NWAL1850.constant.NWAL1850Constant.CSV_FILE_EXT;
import static business.blap.NWAL1850.constant.NWAL1850Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NWAL1850.constant.NWAL1850Constant.CSV_MAX_ROW;
import static business.blap.NWAL1850.constant.NWAL1850Constant.DELY_HLD_CD_ALL;
import static business.blap.NWAL1850.constant.NWAL1850Constant.DELY_HLD_CD_NULL;
import static business.blap.NWAL1850.constant.NWAL1850Constant.DELY_HLD_TXT_ALL;
import static business.blap.NWAL1850.constant.NWAL1850Constant.DELY_HLD_TXT_NULL;
import static business.blap.NWAL1850.constant.NWAL1850Constant.NWAM0181E;
import static business.blap.NWAL1850.constant.NWAL1850Constant.SCRN_ID_00;
import static business.blap.NWAL1850.constant.NWAL1850Constant.ZZZM9001E;
import static business.blap.NWAL1850.constant.NWAL1850Constant.ZZZM9002W;

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
import business.blap.NWAL1850.NWAL1850CMsg;
import business.blap.NWAL1850.NWAL1850Query;
import business.blap.NWAL1850.NWAL1850SMsg;
import business.file.NWAL1850F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/19   Fujitsu         T.Murai         Update          QC#7670
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2019/01/14   CITS            M.Furugoori     Update          QC#55353
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 *</pre>
 */
public class NWAL1850CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg, String srchOptUsrId) {
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
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg, String srchOptUsrId) {
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

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.schdAgmtNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.sellToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.soldToCustLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.dsAcctNm_SO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.shipToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.dsAcctNm_SI.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.dsOrdCatgDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.dsOrdTpCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.schdAgmtCratDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.schdAgmtCratDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.schdAgmtCratDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.schdAgmtCratDt_TO.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.schdAgmtStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.custIssPoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.adminPsnCd.getValue());
        // Add Start QC#7670
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.cpoOrdNum.getValue());
        // Add Start QC#7670
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.serNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.xxScrItem30Txt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.dsContrNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.schdAgmtDelyHldCd.getValue());
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]

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
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg, String usrId, String glblCmpyCd) {

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

        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_SO, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_SI, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, pMsg.srchOptTxt_10);

        createRsnPullDown(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, pMsg.srchOptTxt_11);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_12.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtCratDt_FR, pMsg.srchOptTxt_12.getValue());
        } else {
            bizMsg.schdAgmtCratDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_13.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtCratDt_TO, pMsg.srchOptTxt_13.getValue());
        } else {
            bizMsg.schdAgmtCratDt_TO.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtStsCd, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.adminPsnCd, pMsg.srchOptTxt_16);

        // Add Start QC#7670
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, pMsg.srchOptTxt_17);
        // Add End QC#7670
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.serNum, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem30Txt, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldCd, pMsg.srchOptTxt_21);
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]

    }

    private static boolean isSameSaveSearchName(NWAL1850CMsg bizMsg) {
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
     * @param bizMsg NWAL1850CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NWAL1850CMsg bizMsg) {
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
     * @param bizMsg NWAL1850CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NWAL1850CMsg bizMsg) {
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
     * @param bizMsg NWAL1850CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NWAL1850CMsg bizMsg, NSZC033001PMsg pMsg) {

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
     * @param bizMsg NWAL1850CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NWAL1850CMsg bizMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NWAL1850Query.getInstance().getSavedSearchOptionList(srchOptUsrId);

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

        NWAL1850CMsg bizMsg = (NWAL1850CMsg) cMsg;

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
     * Create Reason Code Pulldown
     * @param bizMsg NWAL1850CMsg
     */
    public static void createRsnPullDown(NWAL1850CMsg bizMsg) {
        bizMsg.dsOrdTpCd_CD.clear();
        bizMsg.dsOrdTpDescTxt_NM.clear();
        bizMsg.dsOrdTpCd.clear();
        S21SsmEZDResult ssmResult = NWAL1850Query.getInstance().getDsOrdTpTxt(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }

        }
    }

    // START 2022/06/02 K.Kitachi [QC#60037, ADD]
    /**
     * Create Delivery Hold Pulldown
     * @param bizMsg NWAL1850CMsg
     */
    public static void createDelyHldPullDown(NWAL1850CMsg bizMsg) {
        bizMsg.schdAgmtDelyHldCd_CD.clear();
        bizMsg.schdAgmtDelyHldDescTxt_NM.clear();
        bizMsg.schdAgmtDelyHldCd.clear();
        int i = 0;
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldCd_CD.no(i), DELY_HLD_CD_ALL);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldDescTxt_NM.no(i), DELY_HLD_TXT_ALL);
        i++;
        S21SsmEZDResult ssmResult = NWAL1850Query.getInstance().getDelyHldTxt(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {
                ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldCd_CD.no(i), resultMap.get("SCHD_AGMT_DELY_HLD_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldDescTxt_NM.no(i), resultMap.get("SCHD_AGMT_DELY_HLD_DESC_TXT"));
                i++;
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldCd_CD.no(i), DELY_HLD_CD_NULL);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtDelyHldDescTxt_NM.no(i), DELY_HLD_TXT_NULL);
    }
    // END 2022/06/02 K.Kitachi [QC#60037, ADD]

    /**
     * Check Input Category
     * @param bizMsg NWAL1850CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static boolean checkExistCatg(NWAL1850CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1850Query.getInstance().getDsOrdCatgList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Category" });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, String>> dsOrdCatgCdList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (dsOrdCatgCdList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_DESC_TXT"));

        return true;
    }

    /**
     * clearAll
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     */
    public static void clearAll(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {
        // Clear
        bizMsg.clear();
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
    }
    // ADD START S21_NA QC#13856
    /**
     * CVS DownLoad Search result
     * @param bizMsg NWAL1850CMsg
     * @param glblMsg NWAL1850SMsg
     */
    public static void csvDownload(NWAL1850CMsg bizMsg, NWAL1850SMsg glblMsg) {

        NWAL1850Query.getInstance().searchForCSV(bizMsg, glblMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * Create DownLoad Date for CSV
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {
        /** cMsg */
        private NWAL1850CMsg bizMsg;

        public CreateDownloadData(NWAL1850CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        // MOD START S21_NA QC#55353
        // START 2022/06/02 K.Kitachi [QC#60037, MOD]
        /** CSV Header Column */
        //private static final String[] CSV_HEADER = {"Schedule #", "Order #", "Sold To Acct #", "Sold To Loc #", "Sold To Acct Name", "Sold To Address", "Ship To Acct #", "Ship To Loc #", "Ship To Acct Name", "Ship To Address", "Category", "Reason", "Schd. Create Date", "Schedule Status", "Cust PO #", "Created By" };
//        private static final String[] CSV_HEADER = {"Schedule #", "Order #", "Sold To Acct #", "Sold To Code", "Sold To Acct Name", "Sold To Address", "Ship To Acct #", "Ship To Code", "Ship To Acct Name", "Ship To Address", "Category", "Reason", "Schd. Create Date", "Schedule Status", "Cust PO #", "Created By" };
        private static final String[] CSV_HEADER = {"Schedule #", "Source Ref #", "Sold To Acct #", "Sold To Code", "Sold To Acct Name", "Sold To Address", "Ship To Acct #", "Ship To Code", "Ship To Acct Name", "Ship To Address",
                "Category", "Reason", "Serial #", "Configuration ID", "Contract #", "Delivery Hold", "Schd. Create Date", "Schedule Status", "Cust PO #", "Created By" };
        // END 2022/06/02 K.Kitachi [QC#60037, MOD]
        // MOD END S21_NA QC#55353

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(ZZZM9001E);
                return false;
            }
            NWAL1850F00FMsg fMsg = new NWAL1850F00FMsg();
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
                ZYPEZDItemValueSetter.setValue(fMsg.schdAgmtNum_A, result.getString("SCHD_AGMT_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum_A, result.getString("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.sellToCustCd_A, result.getString("SELL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.soldToCustLocCd_A, result.getString("SOLD_TO_CUST_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_AO, result.getString("SOLD_DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_AO, result.getString("SOLD_TO_CUST_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustAcctCd_A, result.getString("SHIP_TO_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, result.getString("SHIP_TO_CUST_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_AH, result.getString("SHIP_DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_AH, result.getString("SHIP_TO_CUST_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt_A, result.getString("DS_ORD_CATG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt_A, result.getString("DS_ORD_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.schdAgmtCratDt_A, result.getString("SCHD_AGMT_CRAT_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.schdAgmtStsDescTxt_A, result.getString("SCHD_AGMT_STS_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum_A, result.getString("CUST_ISS_PO_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_A, result.getString("ADMIN_PSN_NM"));
                // START 2022/06/02 K.Kitachi [QC#60037, ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.serNum_A, result.getString("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A, result.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsContrNum_A, result.getString("DS_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.schdAgmtDelyHldDescTxt_A, result.getString("SCHD_AGMT_DELY_HLD_DESC_TXT"));
                // END 2022/06/02 K.Kitachi [QC#60037, ADD]

                csvOutFile.write();
                rowCount++;
            } while (result.next());
            csvOutFile.close();

            return true;
        }
    }
    // ADD END S21_NA QC#13856
}

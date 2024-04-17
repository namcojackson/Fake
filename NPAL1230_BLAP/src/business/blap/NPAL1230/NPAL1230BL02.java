/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1230;

import static business.blap.NPAL1230.constant.NPAL1230Constant.CSV_FILE_EXTENSION;
import static business.blap.NPAL1230.constant.NPAL1230Constant.CSV_FILE_NAME;
import static business.blap.NPAL1230.constant.NPAL1230Constant.CSV_FORMAT_ERR;
import static business.blap.NPAL1230.constant.NPAL1230Constant.CSV_HDR_DOWNLOAD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.CSV_TEMP_FILE_NAME;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_COLUMN_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_COLUMN_XX_COMN_SCR_FIRST_VAL_TXT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_SALES_DATE;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DECIMAL_POINT_USD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_CMN_CLEAR;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_CMN_DOWNLOAD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_CMN_RESET;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_CMN_SUBMIT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_DELETE_ROW;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_GET_ITEM_INFO;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_GET_SUPPLIER_SITE_NAME;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_INIT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_INSERT_ROW;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_PAGE_NEXT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_PAGE_PREV;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_SEARCH;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_SET_ITEM_DESCRIPTION;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_SET_SUPPLIER_NAME;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_TEMPLATE_DOWNLOAD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_UPLOAD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.LIMIT_DL_ROWNUM;
import static business.blap.NPAL1230.constant.NPAL1230Constant.MAX_FETCH_SIZE;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NMAM0038I;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NMAM0052E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NMAM8114E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NZZM0000E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NZZM0001W;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NZZM0010E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.UPLOAD_DATA_FORMAT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.ZYEM0004E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1230.common.NPAL1230CommonLogic;
import business.file.NPAL1230F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 * 2018/04/11   CITS            K.Fukumura      Update          QC#21170
 * 2018/06/15   CITS            Y.Iwasaki       Update          QC#18390
 * 2023/01/25   Hitachi         S.Dong          Update          QC#60966
 * 2023/02/14   CITS            F.Fadriquela    Update          QC#61095 *</pre>
 */
public class NPAL1230BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1230_INIT.equals(screenAplID)) {

                doProcess_Init((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
                doProcess_Search((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_CMN_RESET.equals(screenAplID)) {

                doProcess_Reset((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
                doProcess_Init((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
                doProcess_Search((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_SET_ITEM_DESCRIPTION.equals(screenAplID)) {

                doProcess_SetItemDescription((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_SET_SUPPLIER_NAME.equals(screenAplID)) {

                doProcess_SetSupplierName((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_INSERT_ROW.equals(screenAplID)) {

                doProcess_InsertRow((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_DELETE_ROW.equals(screenAplID)) {

                doProcess_DeleteRow((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_SEARCH.equals(screenAplID)) {

                doProcess_Search((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_CMN_DOWNLOAD.equals(screenAplID)) {

                doProcess_Download((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_TEMPLATE_DOWNLOAD.equals(screenAplID)) {

                doProcess_TemplateDownload((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_UPLOAD.equals(screenAplID)) {

                doProcess_Upload((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_PAGE_NEXT.equals(screenAplID)) {

                doProcess_PageNext((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if (EVENT_NM_NPAL1230_PAGE_PREV.equals(screenAplID)) {

                doProcess_PagePrev((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if ((EVENT_NM_NPAL1230_CMN_SUBMIT.equals(screenAplID))) {

                doProcess_Submit((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if ((EVENT_NM_NPAL1230_GET_ITEM_INFO.equals(screenAplID))) {

                doProcess_NPAL1230Scrn00_GetItemInfo((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            } else if ((EVENT_NM_NPAL1230_CMN_CLEAR.equals(screenAplID))) {

                doProcess_Clear((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
                doProcess_Init((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
            } else if (EVENT_NM_NPAL1230_GET_SUPPLIER_SITE_NAME.equals(screenAplID)) {

                doProcess_GetSupplierSiteName((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            }
            // END 2018/01/12 S.Katsuma [QC#12226,ADD]

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Init(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        cMsg.glblCmpyCd.setValue(glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.aslHdrPk_S1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.aslHdrPk, cMsg.aslHdrPk_S1);

        } else {

            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(sMsg.Q);
            ZYPTableUtil.clear(cMsg.A);

        }

        if (ZYPCommonFunc.hasValue(cMsg.mdseCd_S1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, cMsg.mdseCd_S1);
        }

        NPAL1230CommonLogic.createPullDownMerchandiseType(cMsg, sMsg, glblCmpyCd);
        NPAL1230CommonLogic.createPullDownUomType(cMsg, sMsg, glblCmpyCd);

        // Default value setup
        ZYPEZDItemValueSetter.setValue(cMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.aslHdrPk_S1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.aslStartDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        }
    }

    /**
     * Reset
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Reset(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        // Save params
        if (ZYPCommonFunc.hasValue(cMsg.aslHdrPk_S1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.aslHdrPk_S1, cMsg.aslHdrPk_S1);
        }
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd_S1)) {
            ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_S1, cMsg.mdseCd_S1);
        }

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        if (ZYPCommonFunc.hasValue(sMsg.aslHdrPk_S1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.aslHdrPk_S1, sMsg.aslHdrPk_S1);
        }
        if (ZYPCommonFunc.hasValue(sMsg.mdseCd_S1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_S1, sMsg.mdseCd_S1);
        }
    }

    /**
     * Set Item Description
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_SetItemDescription(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        NPAL1230CommonLogic.setItemDescription(cMsg, sMsg, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(cMsg.mdseDescShortTxt)) {
            cMsg.mdseCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.mdseCd.getValue() });
        }
    }

    /**
     * Set Supplier Name
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_SetSupplierName(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        NPAL1230CommonLogic.setSupplierName(cMsg, sMsg, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(cMsg.prntVndNm)) {
            cMsg.prntVndCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.prntVndCd.getValue() });
        }
    }

    /**
     * getItemInfo
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NPAL1230Scrn00_GetItemInfo(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        int selectNum = cMsg.xxNum.getValueInt();

        List<Map<String, Object>> resultMapList = null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.A.no(selectNum).mdseCd_A);

        S21SsmEZDResult ssmResult = NPAL1230Query.getInstance().getItemInfo(ssmParam);

        if (ssmResult.isCodeNormal()) {

            resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (!resultMapList.isEmpty()) {

                if (resultMapList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).mdseDescShortTxt_A, (String) resultMapList.get(0).get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).xxComnScrFirstValTxt_A, (String) resultMapList.get(0).get(DB_COLUMN_XX_COMN_SCR_FIRST_VAL_TXT));
                } else {

                    cMsg.A.no(selectNum).mdseCd_A.setErrorInfo(1, NZZM0010E, new String[] {sMsg.A.no(selectNum).mdseCd_A.getValue() });
                    cMsg.setMessageInfo(NZZM0010E, new String[] {sMsg.A.no(selectNum).mdseCd_A.getValue() });

                }
            }

        } else {

            cMsg.A.no(selectNum).mdseCd_A.setErrorInfo(1, NZZM0010E, new String[] {sMsg.A.no(selectNum).mdseCd_A.getValue() });
            cMsg.setMessageInfo(NZZM0010E, new String[] {sMsg.A.no(selectNum).mdseCd_A.getValue() });

        }

    }

    /**
     * doProcess_Clear
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Clear(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.Q);

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        EZDMsg.copy(sMsg, null, cMsg, null);

    }

    /**
     * Search
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Search(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.aslHdrPk_S1)) {
            return;
        }

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Get ASL Header
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, slsDt);

        S21SsmEZDResult result = NPAL1230Query.getInstance().getAslHdr(ssmParam, sMsg);
        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.aslNm, sMsg.aslNm);
            ZYPEZDItemValueSetter.setValue(cMsg.aslStartDt, sMsg.aslStartDt);
            ZYPEZDItemValueSetter.setValue(cMsg.aslEndDt, sMsg.aslEndDt);
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, sMsg.prntVndCd);
            ZYPEZDItemValueSetter.setValue(cMsg.aslDescTxt, sMsg.aslDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, sMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, sMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, sMsg.prntVndNm);
        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();

            return;
        }

        // Get ASL Qualifier
        ssmParam.clear();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        result = NPAL1230Query.getInstance().getAslQlfyReln(ssmParam, sMsg);
        if (result.isCodeNormal()) {
            EZDMsg.copy(sMsg.Q, null, cMsg.Q, null);
        }

        // Set Item Name to search condition for ASL detail
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            NPAL1230CommonLogic.setItemDescription(cMsg, sMsg, glblCmpyCd);
        } else {
            cMsg.mdseDescShortTxt.clear();
        }

        // Get ASL Detail
        ssmParam.clear();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, slsDt);
        ssmParam.put(DB_PARAM_ROW_NUM, sMsg.A.length() + 1);

        result = NPAL1230Query.getInstance().getAslDtl(ssmParam, sMsg);
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

            if (!(EVENT_NM_NPAL1230_CMN_SUBMIT.equals(cMsg.getScreenAplID()))) {
                cMsg.setMessageInfo(NMAM0038I);
            }
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * doProcess_Submit
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Submit(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Update ASL_HDR info
        // Get ASL Header
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        S21SsmEZDResult result = NPAL1230Query.getInstance().getAslHdr(ssmParam, sMsg);
        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.aslNm, sMsg.aslNm);
            ZYPEZDItemValueSetter.setValue(cMsg.aslStartDt, sMsg.aslStartDt);
            ZYPEZDItemValueSetter.setValue(cMsg.aslEndDt, sMsg.aslEndDt);
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, sMsg.prntVndCd);
            ZYPEZDItemValueSetter.setValue(cMsg.aslDescTxt, sMsg.aslDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, sMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, sMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, sMsg.prntVndNm);
        }
        // Apply ASL_DTL_PK to sMsg
        for (int i = 0; i < sMsg.A.getValidCount(); ++i) {
            NPAL1230_ASMsg asMsg = sMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(asMsg.aslDtlPk_A)) {
                BigDecimal aslDtlPk = NPAL1230CommonLogic.getAslDtlPk(glblCmpyCd, asMsg.mdseCd_A.getValue(), asMsg.vndCd_A.getValue(), asMsg.effFromDt_A.getValue());
                ZYPEZDItemValueSetter.setValue(asMsg.aslDtlPk_A, aslDtlPk);
            }
        }
        CopySMsgToCMsgAarray(cMsg, sMsg);
    }

    /**
     * Insert Row
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_InsertRow(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        // checks if the row counts exceeds the maximum counts.
        int count = sMsg.A.getValidCount();
        if (count == sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8114E);
            return;
        }

        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);
        }

        // Insert row.
        sMsg.A.setValidCount(count + 1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).primSplyFlg_A, ZYPConstant.FLG_OFF_N);

        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).effFromDt_A, slsDt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).vndUomQty_A, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).baseOrdQty_A, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).vndIncrOrdQty_A, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).vndMinOrdQty_A, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).vndUomCd_A, VND_UOM.EACH);

        // Pagination
        paginationForInsertRow(cMsg, sMsg);
    }

    /**
     * <pre>
     * pagination for Insert New Row
     * </pre>
     * @param sMsg NPAL1230SMsg
     * @param cMsg NPAL1230CMsg
     */
    private void paginationForInsertRow(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        // Calculate last page rows
        int maxDisplayRows = cMsg.A.length();
        int startIndex = sMsg.A.getValidCount() - (sMsg.A.getValidCount() % maxDisplayRows);

        // int i = startIndex;
        if (startIndex == sMsg.A.getValidCount()) {
            startIndex = startIndex - cMsg.A.length();
        }

        int i = startIndex;

        for (; i < startIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg sLineMsg = sMsg.A.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsg.A.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - startIndex);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Delete Row
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_DeleteRow(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);
        }

        int lineCnt = sMsg.A.getValidCount();
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (!selectedRows.isEmpty()) {
            int result = ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
            sMsg.A.setValidCount(lineCnt - result);
        }

        // Pagination
        paginationForDeleteRow(cMsg, sMsg);
    }

    /**
     * <pre>
     * pagination for Delete New
     * </pre>
     * @param sMsg NPAL1230SMsg
     * @param cMsg NPAL1230CMsg
     */
    private void paginationForDeleteRow(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;

        if (startIndex > sMsg.A.getValidCount() - 1) {
            startIndex = sMsg.A.getValidCount() - (sMsg.A.getValidCount() % cMsg.A.length());
            if (startIndex == sMsg.A.getValidCount()) {
                startIndex = startIndex - cMsg.A.length();
            }
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        int i = startIndex;
        for (; i < startIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg sLineMsg = sMsg.A.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsg.A.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - startIndex);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Download
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Download(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1230Query.getInstance().getClass());
            // create csv file, parameters
            cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
            ssmParam.put(DB_PARAM_ROW_NUM, LIMIT_DL_ROWNUM);
            ps = ssmLLClient.createPreparedStatement("getAslDtl", ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void doProcess_TemplateDownload(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        NPAL1230F00FMsg fMsg = new NPAL1230F00FMsg();

        cMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_TEMP_FILE_NAME), CSV_FILE_EXTENSION);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_UP.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD);

        csvOutFile.close();
    }

    /**
     * Download Event
     * @param cMsg NPAL1230CMsg
     * @param rs ResultSet
     */
    private void writeCsvFile(NPAL1230CMsg cMsg, ResultSet rs) throws SQLException {

        NPAL1230F00FMsg fMsg = new NPAL1230F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_DL, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_DL, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.primSplyFlg_DL, rs.getString("PRIM_SPLY_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_DL, rs.getString("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndUomCd_DL, rs.getString("VND_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.splyItemNum_DL, rs.getString("SPLY_ITEM_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCd_DL, rs.getString("VND_CD"));
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.locNm_DL, rs.getString("LOC_NM"));
            // END 2018/01/12 S.Katsuma [QC#12226,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.unitPrcAmt_DL, rs.getBigDecimal("UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_DL, rs.getString("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_DL, rs.getString("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.aslItemCmntTxt_DL, rs.getString("ASL_ITEM_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndUomQty_DL, rs.getBigDecimal("VND_UOM_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.baseOrdQty_DL, rs.getBigDecimal("BASE_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndIncrOrdQty_DL, rs.getBigDecimal("VND_INCR_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndMinOrdQty_DL, rs.getBigDecimal("VND_MIN_ORD_QTY"));
            // QC#21170 Start
            ZYPEZDItemValueSetter.setValue(fMsg.vndLtDaysNum_DL, rs.getBigDecimal("VND_LT_DAYS_NUM"));
            // QC#21170 End
            // START 2023/01/25 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.vndShipLtDaysNum_DL, rs.getBigDecimal("VND_SHIP_LT_DAYS_NUM"));
            // END 2023/01/25 S.Dong [QC#60966, ADD]
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * Download
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Upload(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        cMsg.mdseCd.clear();
        cMsg.mdseDescShortTxt.clear();
        cMsg.splyItemNum.clear();
        cMsg.coaMdseTpCd_SL.clear();

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String path = cMsg.xxFileData_UP.getTempFilePath();
        NPAL1230F00FMsg fMsg = new NPAL1230F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }
            int status = -1;
            int cnt = 0;
            while ((status = mappedFile.read()) != 1) {
                cnt++;
                // format error
                if (status == CSV_FORMAT_ERR) {
                    cMsg.setMessageInfo(NMAM0052E, new String[] {UPLOAD_DATA_FORMAT });
                    return;
                }
                if(sMsg.A.getValidCount()>=sMsg.A.length()) {
                    cMsg.setMessageInfo(NMAM8114E, new String[0]);
                    return;
                }

                int newIndx = sMsg.A.getValidCount();

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).mdseCd_A, fMsg.mdseCd_DL);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).primSplyFlg_A, fMsg.primSplyFlg_DL);

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndUomCd_A, fMsg.vndUomCd_DL);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).splyItemNum_A, fMsg.splyItemNum_DL);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndCd_A, fMsg.vndCd_DL);
                // START 2023/02/14 F.Fadriquela [QC#61095,MOD]
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).unitPrcAmt_A, fMsg.unitPrcAmt_DL);
                if (ZYPCommonFunc.hasValue(fMsg.unitPrcAmt_DL)){
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).unitPrcAmt_A, fMsg.unitPrcAmt_DL.getValue().setScale(DECIMAL_POINT_USD, BigDecimal.ROUND_FLOOR));
                }
                // END 2023/02/14 F.Fadriquela [QC#61095,MOD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).effFromDt_A, fMsg.effFromDt_DL);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).effThruDt_A, fMsg.effThruDt_DL);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).aslItemCmntTxt_A, fMsg.aslItemCmntTxt_DL);

                if (ZYPCommonFunc.hasValue(fMsg.vndUomQty_DL)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndUomQty_A, fMsg.vndUomQty_DL);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndUomQty_A, BigDecimal.ONE);
                }

                if (ZYPCommonFunc.hasValue(fMsg.baseOrdQty_DL)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).baseOrdQty_A, fMsg.baseOrdQty_DL);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).baseOrdQty_A, BigDecimal.ONE);
                }

                if (ZYPCommonFunc.hasValue(fMsg.vndIncrOrdQty_DL)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndIncrOrdQty_A, fMsg.vndIncrOrdQty_DL);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndIncrOrdQty_A, BigDecimal.ONE);
                }

                if (ZYPCommonFunc.hasValue(fMsg.vndMinOrdQty_DL)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndMinOrdQty_A, fMsg.vndMinOrdQty_DL);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndMinOrdQty_A, BigDecimal.ONE);
                }
                // QC#21170 2018/04/11 Start
                BigDecimal vndLtDaysNum = fMsg.vndLtDaysNum_DL.getValue();
                if (ZYPCommonFunc.hasValue(vndLtDaysNum)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndLtDaysNum_A, vndLtDaysNum);
                } else {
                    sMsg.A.no(newIndx).vndLtDaysNum_A.clear();
                }
                // QC#21170 2018/04/11 End

                // START 2023/01/25 S.Dong [QC#60966, ADD]
                BigDecimal vndShipLtDaysNum = fMsg.vndShipLtDaysNum_DL.getValue();
                if (ZYPCommonFunc.hasValue(vndShipLtDaysNum)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).vndShipLtDaysNum_A, vndShipLtDaysNum);
                } else {
                    sMsg.A.no(newIndx).vndShipLtDaysNum_A.clear();
                }
                // END 2023/01/25 S.Dong [QC#60966, ADD]
                
                // Get ASL_DTL_PK
                BigDecimal aslDtlPk = NPAL1230CommonLogic.getAslDtlPk(glblCmpyCd, fMsg.mdseCd_DL.getValue(), fMsg.vndCd_DL.getValue(), fMsg.effFromDt_DL.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).aslDtlPk_A, aslDtlPk);

                // Get mdse name
                String mdseDescShortTxt = NPAL1230CommonLogic.getItemDescription(glblCmpyCd, fMsg.mdseCd_DL.getValue());

                if (ZYPCommonFunc.hasValue(mdseDescShortTxt)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).mdseDescShortTxt_A, mdseDescShortTxt);

                } else {
                    sMsg.A.no(newIndx).mdseDescShortTxt_A.setErrorInfo(1, NZZM0010E, new String[] {fMsg.mdseCd_DL.getValue() });
                }

                // Get COA mdse type
                String coaMdseTp = NPAL1230CommonLogic.getCoaMdseTp(glblCmpyCd, fMsg.coaMdseTpCd_DL.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).xxComnScrFirstValTxt_A, coaMdseTp);

                // START 2018/01/12 S.Katsuma [QC#12226,ADD]
                // Get Supplier Site Name
                String locNm = NPAL1230CommonLogic.getSupplierSiteName(glblCmpyCd, fMsg.vndCd_DL.getValue());
                if (ZYPCommonFunc.hasValue(locNm)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).locNm_A, locNm);
                } else {
                    sMsg.A.no(newIndx).vndCd_A.setErrorInfo(1, NZZM0010E, new String[] {fMsg.vndCd_DL.getValue() });
                }
                // END 2018/01/12 S.Katsuma [QC#12226,ADD]

                sMsg.A.setValidCount(newIndx + 1);
            }

        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();

            ZYPTableUtil.clear(cMsg.A);

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
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            cMsg.setCommitSMsg(true);
        }
    }

    /**
     * Page Next
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_PageNext(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        // copy data from CMsg onto SMsg
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);

        }

        // page next
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        cMsg.xxPageShowToNum.clear();

        // copy data from SMsg onto CMsg
        int nextPageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int k = nextPageShowFromNum;

        for (; k < nextPageShowFromNum + cMsg.A.length(); k++) {

            if (k < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(k), null, cMsg.A.no(k - nextPageShowFromNum), null);

            } else {

                break;
            }

        }

        cMsg.A.setValidCount(k - nextPageShowFromNum);
        cMsg.xxPageShowToNum.setValue(nextPageShowFromNum + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

    }

    /**
     * Page Prev
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_PagePrev(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        // copy data from CMsg onto SMsg
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);

        }

        // page next
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum.clear();

        // copy data from SMsg onto CMsg
        int prevPageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int k = prevPageShowFromNum;

        for (; k < prevPageShowFromNum + cMsg.A.length(); k++) {

            if (k < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(k), null, cMsg.A.no(k - prevPageShowFromNum), null);

            } else {

                break;
            }

        }

        cMsg.A.setValidCount(k - prevPageShowFromNum);
        cMsg.xxPageShowToNum.setValue(prevPageShowFromNum + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    // START 2018/01/12 S.Katsuma [QC#12226,ADD]
    /**
     * Set Supplier Name
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_GetSupplierSiteName(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        NPAL1230CommonLogic.getSupplierSiteName(cMsg, sMsg, getGlobalCompanyCode());
    }
    // END 2018/01/12 S.Katsuma [QC#12226,ADD]

    private void CopySMsgToCMsgAarray(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {
        // copy data from CMsg onto SMsg
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(pageShowFromNum + i), null, cMsg.A.no(i), null);
        }
    }
}

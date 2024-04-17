/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0200;

import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_MDSE_ROWNUM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_MDSE_STRU_ELMNT_TP_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_MDSE_STRU_ELMNT_TP_CD_LIST;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_PL2;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_PL3;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_PROD_CTRL_NM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.DB_PARAM_SCD_PROD_HRCH_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_ADD_LINE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_CMN_RESET;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_CMN_SUBMIT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_DELETE_LINE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_INIT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_PAGE_JUMP;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_PAGE_NEXT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_PAGE_PREV;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_SEARCH;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EVENT_NM_NMAL0200_TBL_COL_SORT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EZUPTIME;
import static business.blap.NMAL0200.constant.NMAL0200Constant.EZUPTIMEZONE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.FETCH_SIZE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.LEVEL_TXT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MDSE_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MDSE_STRU_ELMNT_TP_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.MDSE_STRU_ELMNT_TP_DESC_TXT;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM0038I;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM0050E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM0835E;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NMAM8181W;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NUM_PRODUCT_LEVEL2;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NUM_PRODUCT_LEVEL3;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NUM_PRODUCT_LEVEL4;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NUM_PRODUCT_LINE;
import static business.blap.NMAL0200.constant.NMAL0200Constant.NUM_PRODUCT_LINE_GROUP;
import static business.blap.NMAL0200.constant.NMAL0200Constant.PROD_CTRL_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.PROD_CTRL_NM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.SCD_PROD_HRCH_CD;
import static business.blap.NMAL0200.constant.NMAL0200Constant.SCD_PROD_HRCH_NM;
import static business.blap.NMAL0200.constant.NMAL0200Constant.XX_CHK_BOX_A1;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0200.common.NMAL0200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : search business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL0200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL0200_INIT.equals(screenAplID) || EVENT_NM_NMAL0200_CMN_RESET.equals(screenAplID)) {
                doProcess_NMAL0200_INIT((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_SEARCH.equals(screenAplID) || EVENT_NM_NMAL0200_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Search((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_DELETE_LINE.equals(screenAplID)) {
                doProcess_DeleteLine((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_ADD_LINE.equals(screenAplID)) {
                doProcess_AddLine((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_PAGE_JUMP.equals(screenAplID)) {
                doProcess_PageJump((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else if (EVENT_NM_NMAL0200_TBL_COL_SORT.equals(screenAplID)) {
                NMAL0200CommonLogic.updateSMsg((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
                doProcess_TBLColumnSort((NMAL0200CMsg) cMsg, (NMAL0200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_NMAL0200_INIT(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Initialize
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        // Check box initialize
        cMsg.xxChkBox_BU.setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxChkBox_PG.setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxChkBox_PF.setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxChkBox_PL.setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxChkBox_PS.setValue(ZYPConstant.FLG_ON_Y);

        // Create Level PullDown and Check Box Text
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD, MDSE_STRU_ELMNT_TP.MERCHANDISE_GROUP);
        ssmParam.put(DB_PARAM_ROWNUM, cMsg.mdseStruElmntTpCd_PD.length());

        // Execute
        S21SsmEZDResult result = NMAL0200Query.getInstance().findMultipleRecord("findMdseStruElmntTp", ssmParam);

        if (result.isCodeNormal()) {
            List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();

            for (int i = 0; i < list.size(); i++) {
                Map<String, String> record = list.get(i);

                // Detail Line PullDown
                ZYPEZDItemValueSetter.setValue(cMsg.mdseStruElmntTpCd_PD.no(i), (String) record.get(MDSE_STRU_ELMNT_TP_CD));

                // Check Box Text
                switch (i) {
                    case NUM_PRODUCT_LINE_GROUP:
                        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt_PD.no(i), String.format(LEVEL_TXT, String.valueOf(i + 1), (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT)));
                        ZYPEZDItemValueSetter.setValue(cMsg.mdseStruElmntTpDescTxt_BU, (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT));
                        break;
                    case NUM_PRODUCT_LINE:
                        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt_PD.no(i), String.format(LEVEL_TXT, String.valueOf(i + 1), (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT)));
                        ZYPEZDItemValueSetter.setValue(cMsg.mdseStruElmntTpDescTxt_PG, (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT));
                        break;
                    case NUM_PRODUCT_LEVEL2:
                        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt_PD.no(i), String.format(LEVEL_TXT, String.valueOf(i + 1), (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT)));
                        ZYPEZDItemValueSetter.setValue(cMsg.mdseStruElmntTpDescTxt_PF, (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT));
                        break;
                    case NUM_PRODUCT_LEVEL3:
                        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt_PD.no(i), String.format(LEVEL_TXT, String.valueOf(i + 1), (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT)));
                        ZYPEZDItemValueSetter.setValue(cMsg.mdseStruElmntTpDescTxt_PL, (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT));
                        break;
                    case NUM_PRODUCT_LEVEL4:
                        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem130Txt_PD.no(i), String.format(LEVEL_TXT, String.valueOf(i + 1), (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT)));
                        ZYPEZDItemValueSetter.setValue(cMsg.mdseStruElmntTpDescTxt_PS, (String) record.get(MDSE_STRU_ELMNT_TP_DESC_TXT));
                        break;
                    default:
                        break;
                }

                if (i >= cMsg.mdseStruElmntTpCd_PD.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Search
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_Search(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {

        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();

        // Table Initialize
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.prodCtrlCd)) {
            ssmParam.put(DB_PARAM_PROD_CTRL_CD, cMsg.prodCtrlCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.prodCtrlNm)) {
            ssmParam.put(DB_PARAM_PROD_CTRL_NM, cMsg.prodCtrlNm.getValue().toUpperCase());
        }
        if (ZYPCommonFunc.hasValue(cMsg.scdProdHrchCd)) {
            ssmParam.put(DB_PARAM_PL3, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
            ssmParam.put(DB_PARAM_PL2, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
            ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put(DB_PARAM_SCD_PROD_HRCH_CD, cMsg.scdProdHrchCd.getValue());
        }

        // Check box List
        List<String> mdseStruElmntTpCdList = new ArrayList<String>();
        // PLG
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_BU.getValue())) {
            mdseStruElmntTpCdList.add(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
        }
        // PL
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_PG.getValue())) {
            mdseStruElmntTpCdList.add(MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
        }
        // PL2
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_PF.getValue())) {
            mdseStruElmntTpCdList.add(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
        }
        // PL3
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_PL.getValue())) {
            mdseStruElmntTpCdList.add(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
            ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put(DB_PARAM_PL3, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
            ssmParam.put(DB_PARAM_PL2, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
        }
        // PL4
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_PS.getValue())) {
            mdseStruElmntTpCdList.add(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4);
        }

        if (mdseStruElmntTpCdList.isEmpty()) {
            ssmParam.put(DB_PARAM_PL3, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
            ssmParam.put(DB_PARAM_PL2, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
            ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        }

        ssmParam.put(DB_PARAM_MDSE_STRU_ELMNT_TP_CD_LIST, mdseStruElmntTpCdList);
        ssmParam.put(DB_PARAM_MDSE_ROWNUM, BigDecimal.ONE);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL0200Query.getInstance().getClass());

            ps = ssmLLClient.createPreparedStatement("findProdCtrlList", ssmParam, execParam);
            rs = ps.executeQuery();

            if (!rs.next()) {
                if (!EVENT_NM_NMAL0200_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                    cMsg.setMessageInfo(NMAM0038I);
                }
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowToNum.clear();
                cMsg.xxPageShowOfNum.clear();
                return;
            }

            int rowCnt = 0;
            int queryResCnt = 0;
            do {
                // Max Recode Over
                queryResCnt = rs.getRow();
                if (queryResCnt > sMsg.A.length()) {
                    if (!EVENT_NM_NMAL0200_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                        cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(queryResCnt), String.valueOf(sMsg.A.length()) });
                    }
                    break;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).mdseStruElmntTpCd_A1, rs.getString(MDSE_STRU_ELMNT_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).prodCtrlCd_A1, rs.getString(PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).prodCtrlNm_A1, rs.getString(PROD_CTRL_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).scdProdHrchCd_A1, rs.getString(SCD_PROD_HRCH_CD));
                // Relationship Code And R Link protected Flag
                if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(sMsg.A.no(rowCnt).mdseStruElmntTpCd_A1.getValue())) {
                    if (ZYPCommonFunc.hasValue(rs.getString(MDSE_CD))) {
                        // protected
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).xxBtnFlg_A1, ZYPConstant.FLG_ON_Y);
                    } else {
                        // not protected
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).xxBtnFlg_A1, ZYPConstant.FLG_OFF_N);
                    }
                } else {
                    // protected
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).xxBtnFlg_A1, ZYPConstant.FLG_ON_Y);
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).xxDplyByCtrlItemCdNm_A1, rs.getString(SCD_PROD_HRCH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).ezUpTime_A1, rs.getString(EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).ezUpTimeZone_A1, rs.getString(EZUPTIMEZONE));
                sMsg.A.no(rowCnt).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_ON_Y);

                rowCnt++;

            } while (rs.next());

            sMsg.A.setValidCount(rowCnt);

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
            cMsg.xxPageShowOfNum.setValue(rowCnt);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Add Line
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_AddLine(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(sMsg.A.length()) });
            return;
        }

        NMAL0200CommonLogic.updateSMsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(sMsg.A.getValidCount());
        sMsg.A.no(sMsg.A.getValidCount()).xxMstChkFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
        sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
        NMAL0200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * PageJump Event
     * @param cMsg NMAL0200CMsg
     * @param sMSg NMAL0200SMsg
     */
    private void doProcess_PageJump(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {
        // copy data from sMsg onto cMsg
        NMAL0200CommonLogic.updateSMsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue((cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL0200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * PageNext Event
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_PageNext(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {
        NMAL0200CommonLogic.updateSMsg(cMsg, sMsg);
        // copy data from sMsg onto cMsg
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL0200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * PagePrev Event
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_PagePrev(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {
        NMAL0200CommonLogic.updateSMsg(cMsg, sMsg);
        // copy data from sMsg onto cMsg
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NMAL0200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * DeleteLine
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_DeleteLine(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        int maxDisplayRows = cMsg.A.length();

        NMAL0200CommonLogic.updateSMsg(cMsg, sMsg);
        List<Integer> delIdx = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A1, ZYPConstant.CHKBOX_ON_Y);

        if (delIdx.size() == 0) {
            if (cMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NMAM0835E);
                return;
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM0835E);
            }
        }

        List<Integer> delIdxElList = new ArrayList<Integer>();
        try {

            for (int idx : delIdx) {
                // Used check
                if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(idx).xxMstChkFlg_A1.getValue())) {
                    delIdxElList.add(idx);
                    continue;
                }

                boolean result = NMAL0200CommonLogic.chkUsedHrchCd(cMsg, sMsg, glblCmpyCd, idx, maxDisplayRows);
                if (!result) {
                    return;
                }

                int validCnt = sMsg.B.getValidCount();
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).prodCtrlCd_DL, sMsg.A.no(idx).prodCtrlCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).mdseStruElmntTpCd_DL, sMsg.A.no(idx).mdseStruElmntTpCd_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).ezUpTime_DL, sMsg.A.no(idx).ezUpTime_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(validCnt).ezUpTimeZone_DL, sMsg.A.no(idx).ezUpTimeZone_A1);
                sMsg.B.setValidCount(validCnt + 1);
                delIdxElList.add(idx);
            }

            if (delIdxElList.size() > 0) {
                ZYPTableUtil.deleteRows(sMsg.A, delIdxElList);
            }

            if (cMsg.xxPageShowFromNum.getValueInt() > sMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(sMsg.A.getValidCount() - 1));
            }
        } finally {
            NMAL0200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
        }
    }

    /**
     * sort table column process
     * @param cMsg NMAL0200CMsg
     * @param sMsg NMAL0200SMsg
     */
    private void doProcess_TBLColumnSort(NMAL0200CMsg cMsg, NMAL0200SMsg sMsg) {

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        // copy (SMsg -> CMsg)
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
    }
}

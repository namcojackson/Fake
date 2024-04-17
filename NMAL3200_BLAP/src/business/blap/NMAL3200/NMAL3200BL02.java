/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3200;

import static business.blap.NMAL3200.constant.NMAL3200Constant.CANDI_TRTY_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CNTY_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CONTR_ASSN_TRGT_TP_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CONTR_ASSN_TRGT_TP_DESC_TXT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CRAT_TS;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CRAT_USR_ID;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CSV_FILE_NAME_UPLOAD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CSV_HDR_KEY;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CSV_HDR_KEY_PREFIX;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CSV_HDR_KEY_SUFIX;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CTRY_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CTY_ADDR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_ACTV_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_DUNS_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_EXACT_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_GLN_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_MKTG_FLD_MAP_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_MKTG_MAP_RQST_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_MKTG_MAP_RQST_PROC_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_PRTL_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_SALES_DATE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DB_PARAM_UPLD_ERR_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DS_ACCT_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DUNS_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DUNS_MATCH_LOC_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DUNS_MATCH_SF_ACCT_ID;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DUNS_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ERR_MSG_HDR_TXT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_CLEAR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_DOWNLOAD_NEW_PROS_FILE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_DOWNLOAD_REVIEW_FILE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_DOWNLOAD_SUCCESS_FILE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_DOWNLOAD_UPLD_DATA;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_CMN_SUBMIT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_INIT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_PAGE_JUMP;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_PAGE_NEXT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_PAGE_PREV;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_SEARCH_MKT_FLD_MAP;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_SEARCH_MKT_MAP_REQ;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_TBL_COL_SORT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EVENT_NM_NMAL3200_UPLOAD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EXACT_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EXACT_MATCH_LOC_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EXACT_MATCH_SF_ACCT_ID;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EXTN_CSV;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FETCH_SIZE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FIRST_LINE_ADDR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FRTH_LINE_ADDR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.GLN_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.GLN_MATCH_LOC_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.GLN_MATCH_SF_ACCT_ID;
import static business.blap.NMAL3200.constant.NMAL3200Constant.GLN_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.HIN_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MATCH_CRIT_TP_TXT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MAX_UPLOAD_CNT_NUM_CONST_KEY;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MAX_UPLOAD_SIZE_NUM_CONST_KEY;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_01;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_02;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_03;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_04;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_05;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_06;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_07;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_08;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_09;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_10;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_11;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_12;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_13;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_14;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_15;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_16;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_17;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_18;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_19;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_ATTRB_TXT_20;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_RQST_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_RQST_PROC_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM0038I;
import static business.blap.NMAL3200.constant.NMAL3200Constant.NMAM8181W;
import static business.blap.NMAL3200.constant.NMAL3200Constant.POST_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.PRTL_MATCH_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.PRTL_MATCH_LOC_NUM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.PRTL_MATCH_SF_ACCT_ID;
import static business.blap.NMAL3200.constant.NMAL3200Constant.SCD_LINE_ADDR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ST_CD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.TAB_UPLOAD;
import static business.blap.NMAL3200.constant.NMAL3200Constant.THIRD_LINE_ADDR;
import static business.blap.NMAL3200.constant.NMAL3200Constant.UPLD_CSV_RQST_CMNT_TXT;
import static business.blap.NMAL3200.constant.NMAL3200Constant.UPLD_ERR_FLG;
import static business.blap.NMAL3200.constant.NMAL3200Constant.UPLD_FILE_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.XX_FULL_NM;

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
import business.blap.NMAL3200.common.NMAL3200CommonLogic;
import business.file.NMAL3200F00FMsg;
import business.file.NMAL3200F01FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
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
 * Business ID : NMAL3200 Marketing Data Analysis
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL3200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL3200_INIT.equals(screenAplID) || EVENT_NM_NMAL3200_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NMAL3200_INIT((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_SEARCH_MKT_FLD_MAP.equals(screenAplID) || EVENT_NM_NMAL3200_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Search_MktFldMap((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_SEARCH_MKT_MAP_REQ.equals(screenAplID) || EVENT_NM_NMAL3200_UPLOAD.equals(screenAplID)) {
                doProcess_Search_MktMapReq((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_CMN_DOWNLOAD_UPLD_DATA.equals(screenAplID)) {
                doProcess_CMN_Download_UpldData((NMAL3200CMsg) cMsg);
            } else if (EVENT_NM_NMAL3200_CMN_DOWNLOAD_NEW_PROS_FILE.equals(screenAplID) //
                    || EVENT_NM_NMAL3200_CMN_DOWNLOAD_REVIEW_FILE.equals(screenAplID) //
                    || EVENT_NM_NMAL3200_CMN_DOWNLOAD_SUCCESS_FILE.equals(screenAplID)) {
                doProcess_CMN_Download_File((NMAL3200CMsg) cMsg);
            } else if (EVENT_NM_NMAL3200_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_PAGE_JUMP.equals(screenAplID)) {
                doProcess_PageJump((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else if (EVENT_NM_NMAL3200_TBL_COL_SORT.equals(screenAplID)) {
                doProcess_TBLColumnSort((NMAL3200CMsg) cMsg, (NMAL3200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_NMAL3200_INIT(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Create Y/N Pull Down
        cMsg.upldErrFlg_UE.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_UE.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.upldErrFlg_UE.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_UE.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.mktgMapRqstProcFlg_RP.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.xxFlgNm_RP.no(0).setValue(ZYPConstant.FLG_ON_Y);
        cMsg.mktgMapRqstProcFlg_RP.no(1).setValue(ZYPConstant.FLG_OFF_N);
        cMsg.xxFlgNm_RP.no(1).setValue(ZYPConstant.FLG_OFF_N);

        // NUM_CONST(Initial Data Setup)
        BigDecimal maxUploadSize = ZYPCodeDataUtil.getNumConstValue(MAX_UPLOAD_SIZE_NUM_CONST_KEY, glblCmpyCd);
        BigDecimal maxUploadCnt = ZYPCodeDataUtil.getNumConstValue(MAX_UPLOAD_CNT_NUM_CONST_KEY, glblCmpyCd);
        cMsg.numConstVal_SZ.setValue(maxUploadSize);
        cMsg.numConstVal_CU.setValue(maxUploadCnt);

        if (TAB_UPLOAD.equals(cMsg.xxDplyTab.getValue())) {
            NMAL3200CommonLogic.mktgFldMapClear(cMsg);
            cMsg.mktgFldMapNm_DB.clear();
            cMsg.mktgFldMapNm_SC.clear();
            cMsg.mktgFldMapPk.clear();
            cMsg.contrAssnTrgtTpCd_SL.clear();
            cMsg.exactCondFlg.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.dunsCondFlg.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.glnCondFlg.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.prtlCondFlg.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.ovrdVldFlg.clear();
            cMsg.xxFileData.clear();

            // Create Contract Association Target Type PullDown
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_ACTV_FLG, ZYPConstant.FLG_ON_Y);

            // Execute
            S21SsmEZDResult result = NMAL3200Query.getInstance().findMultipleRecord("findContrAssnTrgtTp", ssmParam);

            if (result.isCodeNormal()) {
                List<Map<String, String>> contractList = (List<Map<String, String>>) result.getResultObject();

                for (int i = 0; i < contractList.size(); i++) {
                    Map<String, String> record = contractList.get(i);

                    ZYPEZDItemValueSetter.setValue(cMsg.contrAssnTrgtTpCd_PD.no(i), (String) record.get(CONTR_ASSN_TRGT_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.contrAssnTrgtTpDescTxt_PD.no(i), (String) record.get(CONTR_ASSN_TRGT_TP_DESC_TXT));

                    if (i >= cMsg.contrAssnTrgtTpCd_PD.length() - 1) {
                        break;
                    }
                }
            }
        } else {
            cMsg.mktgMapRqstPk_DL.clear();
            cMsg.mktgFldMapNm_DL.clear();
            cMsg.upldErrFlg_SL.clear();
            cMsg.mktgMapRqstProcFlg_SL.clear();
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
        }
    }

    /**
     * Search_MktFldMap Event
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_Search_MktFldMap(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NMAL3200CommonLogic.mktgFldMapClear(cMsg);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cMsg.mktgFldMapNm_DB)) {
            ssmParam.put(DB_PARAM_MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_DB.getValue());
        }

        S21SsmEZDResult result = NMAL3200Query.getInstance().findSingleRecord("findMktgFldMap", ssmParam);

        if (result.isCodeNormal()) {
            Map<String, Object> map = (Map) result.getResultObject();
            NMAL3200CommonLogic.setMktgFldMap(cMsg, map);
        } else {
            cMsg.setMessageInfo(NMAM0038I);
        }

    }

    /**
     * Search_MktMapReq Event
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_Search_MktMapReq(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {

        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }

        // Table Initialize
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String glblCmpyCd = getGlobalCompanyCode();
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cMsg.mktgFldMapNm_DL)) {
            ssmParam.put(DB_PARAM_MKTG_FLD_MAP_NM, cMsg.mktgFldMapNm_DL.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mktgMapRqstPk_DL)) {
            ssmParam.put(DB_PARAM_MKTG_MAP_RQST_PK, cMsg.mktgMapRqstPk_DL.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mktgMapRqstProcFlg_SL)) {
            ssmParam.put(DB_PARAM_MKTG_MAP_RQST_PROC_FLG, cMsg.mktgMapRqstProcFlg_SL.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.upldErrFlg_SL)) {
            ssmParam.put(DB_PARAM_UPLD_ERR_FLG, cMsg.upldErrFlg_SL.getValue());
        }
        ssmParam.put(DB_PARAM_SALES_DATE, salesDate);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL3200Query.getInstance().getClass());

            ps = ssmLLClient.createPreparedStatement("findMktMapReq", ssmParam, execParam);
            rs = ps.executeQuery();

            if (!rs.next()) {
                if (!EVENT_NM_NMAL3200_UPLOAD.equals(cMsg.xxScrEventNm.getValue())) {
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
                    if (!EVENT_NM_NMAL3200_UPLOAD.equals(cMsg.xxScrEventNm.getValue())) {
                        cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(queryResCnt), String.valueOf(sMsg.A.length()) });
                    }
                    break;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).mktgMapRqstPk_A1, rs.getBigDecimal(MKTG_MAP_RQST_PK));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).mktgFldMapNm_A1, rs.getString(MKTG_FLD_MAP_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).upldErrFlg_A1, rs.getString(UPLD_ERR_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).mktgMapRqstProcFlg_A1, rs.getString(MKTG_MAP_RQST_PROC_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).upldFileNm_A1, rs.getString(UPLD_FILE_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).contrAssnTrgtTpDescTxt_A1, rs.getString(CONTR_ASSN_TRGT_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).xxFullNm_A1, rs.getString(XX_FULL_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).prtlMatchFlg_A1, rs.getString(PRTL_MATCH_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).exactMatchFlg_A1, rs.getString(EXACT_MATCH_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).glnMatchFlg_A1, rs.getString(GLN_MATCH_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).dunsMatchFlg_A1, rs.getString(DUNS_MATCH_FLG));
                if (ZYPCommonFunc.hasValue(rs.getString(CRAT_TS))) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).xxDtTm_A1, ZYPDateUtil.formatEzd14ToDisp(rs.getString(CRAT_TS)));
                }
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
     * PageJump Event
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_PageJump(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue((cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL3200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * PageNext Event
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_PageNext(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL3200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);

    }

    /**
     * PagePrev Event
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_PagePrev(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NMAL3200CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);

    }

    /**
     * sort table column process
     * @param cMsg NMAL3200CMsg
     * @param sMsg NMAL3200SMsg
     */
    private void doProcess_TBLColumnSort(NMAL3200CMsg cMsg, NMAL3200SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table A
        if ("A".equals(sortTblNm)) {
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

    /**
     * DL event for Upload Data
     * @param cMsg NMAL3200CMsg
     */
    private void doProcess_CMN_Download_UpldData(NMAL3200CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL3200Query.getInstance().getClass());

            // create csv file, parameters
            cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_UPLOAD), EXTN_CSV);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MKTG_MAP_RQST_PK, cMsg.A.no(cMsg.xxCellIdx.getValueInt()).mktgMapRqstPk_A1.getValue());
            ssmParam.put(DB_PARAM_ROWNUM, MAX_DOWNLOAD_CNT);

            ps = ssmLLClient.createPreparedStatement("downloadUpldData", ssmParam, execParam);
            rs = ps.executeQuery();
            writeUpldDataCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Upload Data Download Event
     * @param cMsg NMAL3200CMsg
     * @param ResultSet rs
     */
    private void writeUpldDataCsvFile(NMAL3200CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL3200F00FMsg fMsg = new NMAL3200F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NMAM0038I, null);
            csvOutFile.close();
            return;
        }

        // write header
        List<String> hdrList = new ArrayList<String>(CSV_HDR_KEY.length + 1);
        for (String key : CSV_HDR_KEY) {
            String txt = rs.getString(key);
            if (ZYPCommonFunc.hasValue(txt)) {
                hdrList.add(txt);
            } else {
                hdrList.add("");
            }
        }
        hdrList.add(ERR_MSG_HDR_TXT);
        String[] csvHdr = (String[]) hdrList.toArray(new String[0]);

        csvOutFile.writeHeader(csvHdr);

        // write contents
        do {
            if (rs.getRow() >= MAX_DOWNLOAD_CNT) {
                cMsg.setMessageInfo(NMAM8181W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, rs.getString(CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.cntyPk, rs.getBigDecimal(CNTY_PK));
            ZYPEZDItemValueSetter.setValue(fMsg.stCd, rs.getString(ST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd, rs.getString(POST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.ctryCd, rs.getString(CTRY_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.dunsNum, rs.getString(DUNS_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.glnNum, rs.getBigDecimal(GLN_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.hinNum, rs.getString(HIN_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_01, rs.getString(MKTG_MAP_ATTRB_TXT_01));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_02, rs.getString(MKTG_MAP_ATTRB_TXT_02));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_03, rs.getString(MKTG_MAP_ATTRB_TXT_03));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_04, rs.getString(MKTG_MAP_ATTRB_TXT_04));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_05, rs.getString(MKTG_MAP_ATTRB_TXT_05));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_06, rs.getString(MKTG_MAP_ATTRB_TXT_06));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_07, rs.getString(MKTG_MAP_ATTRB_TXT_07));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_08, rs.getString(MKTG_MAP_ATTRB_TXT_08));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_09, rs.getString(MKTG_MAP_ATTRB_TXT_09));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_10, rs.getString(MKTG_MAP_ATTRB_TXT_10));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_11, rs.getString(MKTG_MAP_ATTRB_TXT_11));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_12, rs.getString(MKTG_MAP_ATTRB_TXT_12));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_13, rs.getString(MKTG_MAP_ATTRB_TXT_13));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_14, rs.getString(MKTG_MAP_ATTRB_TXT_14));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_15, rs.getString(MKTG_MAP_ATTRB_TXT_15));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_16, rs.getString(MKTG_MAP_ATTRB_TXT_16));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_17, rs.getString(MKTG_MAP_ATTRB_TXT_17));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_18, rs.getString(MKTG_MAP_ATTRB_TXT_18));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_19, rs.getString(MKTG_MAP_ATTRB_TXT_19));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_20, rs.getString(MKTG_MAP_ATTRB_TXT_20));
            ZYPEZDItemValueSetter.setValue(fMsg.upldCsvRqstCmntTxt, rs.getString(UPLD_CSV_RQST_CMNT_TXT));
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * DL event for Detail Button
     * @param cMsg NMAL3200CMsg
     */
    private void doProcess_CMN_Download_File(NMAL3200CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL3200Query.getInstance().getClass());

            // create csv file, parameters
            cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_UPLOAD), EXTN_CSV);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MKTG_MAP_RQST_PK, cMsg.A.no(cMsg.xxCellIdx.getValueInt()).mktgMapRqstPk_A1.getValue());
            if (EVENT_NM_NMAL3200_CMN_DOWNLOAD_SUCCESS_FILE.equals(cMsg.xxScrEventNm.getValue())) {
                ssmParam.put(DB_PARAM_EXACT_MATCH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_DUNS_MATCH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_GLN_MATCH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_PRTL_MATCH_FLG, ZYPConstant.FLG_OFF_N);
            } else if (EVENT_NM_NMAL3200_CMN_DOWNLOAD_REVIEW_FILE.equals(cMsg.xxScrEventNm.getValue())) {
                ssmParam.put(DB_PARAM_EXACT_MATCH_FLG, ZYPConstant.FLG_OFF_N);
                ssmParam.put(DB_PARAM_DUNS_MATCH_FLG, ZYPConstant.FLG_OFF_N);
                ssmParam.put(DB_PARAM_GLN_MATCH_FLG, ZYPConstant.FLG_OFF_N);
                ssmParam.put(DB_PARAM_PRTL_MATCH_FLG, ZYPConstant.FLG_ON_Y);
            } else if (EVENT_NM_NMAL3200_CMN_DOWNLOAD_NEW_PROS_FILE.equals(cMsg.xxScrEventNm.getValue())) {
                ssmParam.put(DB_PARAM_EXACT_MATCH_FLG, ZYPConstant.FLG_OFF_N);
                ssmParam.put(DB_PARAM_DUNS_MATCH_FLG, ZYPConstant.FLG_OFF_N);
                ssmParam.put(DB_PARAM_GLN_MATCH_FLG, ZYPConstant.FLG_OFF_N);
                ssmParam.put(DB_PARAM_PRTL_MATCH_FLG, ZYPConstant.FLG_OFF_N);
            } else {
                ssmParam.put(DB_PARAM_EXACT_MATCH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_DUNS_MATCH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_GLN_MATCH_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(DB_PARAM_PRTL_MATCH_FLG, ZYPConstant.FLG_OFF_N);
            }
            ssmParam.put(DB_PARAM_ROWNUM, MAX_DOWNLOAD_CNT);

            ps = ssmLLClient.createPreparedStatement("downloadResultFile", ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Upload Data Download Event
     * @param cMsg NMAL3200CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NMAL3200CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL3200F01FMsg fMsg = new NMAL3200F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NMAM0038I, null);
            csvOutFile.close();
            return;
        }

        // write header
        List<String> hdrList = new ArrayList<String>(CSV_HDR_KEY_PREFIX.length + CSV_HDR_KEY.length + CSV_HDR_KEY_SUFIX.length);
        for (String key : CSV_HDR_KEY_PREFIX) {
            hdrList.add(key);
        }

        for (String key : CSV_HDR_KEY) {
            String txt = rs.getString(key);
            if (ZYPCommonFunc.hasValue(txt)) {
                hdrList.add(txt);
            } else {
                hdrList.add("");
            }
        }

        for (String key : CSV_HDR_KEY_SUFIX) {
            hdrList.add(key);
        }
        String[] csvHdr = (String[]) hdrList.toArray(new String[0]);

        csvOutFile.writeHeader(csvHdr);

        // write contents
        do {
            if (rs.getRow() >= MAX_DOWNLOAD_CNT) {
                cMsg.setMessageInfo(NMAM8181W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapRqstPk, rs.getBigDecimal(MKTG_MAP_RQST_PK));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgFldMapNm, rs.getString(MKTG_FLD_MAP_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, rs.getString(CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.cntyPk, rs.getBigDecimal(CNTY_PK));
            ZYPEZDItemValueSetter.setValue(fMsg.stCd, rs.getString(ST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd, rs.getString(POST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.ctryCd, rs.getString(CTRY_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.dunsNum, rs.getString(DUNS_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.glnNum, rs.getBigDecimal(GLN_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.hinNum, rs.getString(HIN_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_01, rs.getString(MKTG_MAP_ATTRB_TXT_01));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_02, rs.getString(MKTG_MAP_ATTRB_TXT_02));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_03, rs.getString(MKTG_MAP_ATTRB_TXT_03));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_04, rs.getString(MKTG_MAP_ATTRB_TXT_04));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_05, rs.getString(MKTG_MAP_ATTRB_TXT_05));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_06, rs.getString(MKTG_MAP_ATTRB_TXT_06));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_07, rs.getString(MKTG_MAP_ATTRB_TXT_07));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_08, rs.getString(MKTG_MAP_ATTRB_TXT_08));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_09, rs.getString(MKTG_MAP_ATTRB_TXT_09));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_10, rs.getString(MKTG_MAP_ATTRB_TXT_10));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_11, rs.getString(MKTG_MAP_ATTRB_TXT_11));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_12, rs.getString(MKTG_MAP_ATTRB_TXT_12));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_13, rs.getString(MKTG_MAP_ATTRB_TXT_13));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_14, rs.getString(MKTG_MAP_ATTRB_TXT_14));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_15, rs.getString(MKTG_MAP_ATTRB_TXT_15));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_16, rs.getString(MKTG_MAP_ATTRB_TXT_16));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_17, rs.getString(MKTG_MAP_ATTRB_TXT_17));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_18, rs.getString(MKTG_MAP_ATTRB_TXT_18));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_19, rs.getString(MKTG_MAP_ATTRB_TXT_19));
            ZYPEZDItemValueSetter.setValue(fMsg.mktgMapAttrbTxt_20, rs.getString(MKTG_MAP_ATTRB_TXT_20));
            ZYPEZDItemValueSetter.setValue(fMsg.cratUsrId, rs.getString(CRAT_USR_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.cratTs, rs.getString(CRAT_TS));
            ZYPEZDItemValueSetter.setValue(fMsg.upldFileNm, rs.getString(UPLD_FILE_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.matchCritTpTxt, rs.getString(MATCH_CRIT_TP_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.exactMatchLocNum, rs.getString(EXACT_MATCH_LOC_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.exactMatchSfAcctId, rs.getString(EXACT_MATCH_SF_ACCT_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.prtlMatchLocNum, rs.getString(PRTL_MATCH_LOC_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.prtlMatchSfAcctId, rs.getString(PRTL_MATCH_SF_ACCT_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.dunsMatchLocNum, rs.getString(DUNS_MATCH_LOC_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.dunsMatchSfAcctId, rs.getString(DUNS_MATCH_SF_ACCT_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.glnMatchLocNum, rs.getString(GLN_MATCH_LOC_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.glnMatchSfAcctId, rs.getString(GLN_MATCH_SF_ACCT_ID));
            ZYPEZDItemValueSetter.setValue(fMsg.candiTrtyNm, rs.getString(CANDI_TRTY_NM));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}

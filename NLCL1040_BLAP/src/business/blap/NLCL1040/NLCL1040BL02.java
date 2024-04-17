/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL1040;

import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ABC_ANLS_CLS_NM;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_GLBL_CMPY_CD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_ROWNUM;
import static business.blap.NLCL1040.constant.NLCL1040Constant.BIND_SMSG;
import static business.blap.NLCL1040.constant.NLCL1040Constant.CODE_SEARCH_SIZE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.CSV_FILE_NAME;
import static business.blap.NLCL1040.constant.NLCL1040Constant.CSV_HDR_DOWNLOAD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_ADDTAG;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_ANALYZE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_CLEAR;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_DOWNLOAD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_RESET;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_SUBMIT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_DELETE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_DELETE_TAG;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_EDITTAG;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_INIT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_ITEMSEARCH;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_NLCL1050;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_PAGEJUMP;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_PAGENEXT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_PAGPREV;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_REFRESH;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_SAVE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_SEARCH_HEADER;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_SEARCH_ITEM;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_SEARCH_WAREHOUSE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_TBLCOLUMNSORT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_OPEN_WIN_EDIT_CLASS;
import static business.blap.NLCL1040.constant.NLCL1040Constant.LIMIT_DL_ROWNUM;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0208E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NLCM0212I;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NMAM8114E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NPAM0049E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NZZM0000E;
import static business.blap.NLCL1040.constant.NLCL1040Constant.NZZM0001W;
import static business.blap.NLCL1040.constant.NLCL1040Constant.TABLE_A_HEAD;
import static business.blap.NLCL1040.constant.NLCL1040Constant.XX_CHECK_BOX;

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
import business.blap.NLCL1040.common.NLCL1040CommonLogic;
import business.blap.NLCL1040.constant.NLCL1040Constant;
import business.file.NLCL1040F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
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
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040BL02
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1040_INIT.equals(screenAplID)) {

                doProcess_NLCL1040_INIT((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);
                doProcess_NLCL1040Scrn00_SearchHeader((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);
                ZYPGUITableColumn.getColData((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg, TABLE_A_HEAD);

            } else if (EVENT_NM_NLCL1040_SEARCH_HEADER.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_SearchHeader((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_SEARCH_ITEM.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Search_Item((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_SEARCH_WAREHOUSE.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Search_Warehouse((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_SAVE.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_SearchHeader((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_ANALYZE.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_SearchHeader((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_DELETE.equals(screenAplID)) {

                doProcess_NLCL1040_INIT((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_REFRESH.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Refresh((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_EDITTAG.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_EditTag((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_PAGEJUMP.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_PageJump((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_PAGENEXT.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_PageNext((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_PAGPREV.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_PagePrev((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_TBLCOLUMNSORT.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_TBLColumnSort((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_CMN_CLEAR.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_CMN_Clear((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);
                doProcess_NLCL1040_INIT((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);
                ZYPGUITableColumn.getColData((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg, TABLE_A_HEAD);

            } else if (EVENT_NM_NLCL1040_CMN_RESET.equals(screenAplID)) {

                doProcess_NLCL1040_INIT((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);
                ZYPGUITableColumn.getColData((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg, TABLE_A_HEAD);
                doProcess_NLCL1040Scrn00_SearchHeader((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_ADDTAG.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_AddTag((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_OPEN_WIN_EDIT_CLASS.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_OpenWin_EditClass((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_NLCL1050.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_NLCL1040_NLCL1050((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_CMN_DOWNLOAD.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_CMN_Download((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_ITEMSEARCH.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Item_Search((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_CMN_SUBMIT.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_SearchHeader((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_DELETE_TAG.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_DeleteTag((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLCL1040Scrn00_Item_Search
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Item_Search(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // get Detail info
        NLCL1040CommonLogic.getAbcDetail(cMsg, sMsg);

    }

    /**
     * doProcess_NLCL1040Scrn00_CMN_Download
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_CMN_Download(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // get header info
        // ***************************************************
        S21SsmEZDResult ssmResultAbcHeader = NLCL1040Query.getInstance().getAbcHeader(cMsg, sMsg);

        if (!ssmResultAbcHeader.isCodeNormal()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            return;
        }

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(CODE_SEARCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLCL1040Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            params.put(BIND_SMSG, sMsg);
            params.put(BIND_ROWNUM, sMsg.A.length());

            ps = ssmLLClient.createPreparedStatement("getAbcDetail", params, execParam);

            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    /**
     * writeCsvFile
     * @param cMsg NLCL1040CMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private void writeCsvFile(NLCL1040CMsg cMsg, ResultSet rs) throws SQLException {

        NLCL1040F00FMsg fMsg = new NLCL1040F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD);

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
            ZYPEZDItemValueSetter.setValue(fMsg.abcAsgNm, cMsg.abcAsgNm);
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgDescTxt, rs.getString("RTL_WH_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd, rs.getString("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsCd, rs.getString("STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt, rs.getString("STK_STS_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.curInvtyQty, rs.getBigDecimal("CUR_INVTY_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.curInvtyCostAmt, rs.getBigDecimal("CUR_INVTY_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.histInvtyTrxQty, rs.getBigDecimal("HIST_INVTY_TRX_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.histInvtyTrxCostAmt, rs.getBigDecimal("HIST_INVTY_TRX_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.histInvtyTrxRecCnt, rs.getBigDecimal("HIST_INVTY_TRX_REC_CNT"));
            ZYPEZDItemValueSetter.setValue(fMsg.abcAnlsClsTagCd, rs.getString("ABC_ANLS_CLS_TAG_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.abcAnlsClsNm, rs.getString("ABC_ANLS_CLS_NM"));

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();

    }

    /**
     * doProcess_NLCL1040Scrn00_NLCL1040_NLCL1050
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_NLCL1040_NLCL1050(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        params.put(BIND_ABC_ANLS_CLS_NM, cMsg.abcAnlsClsNm.getValue());

        S21SsmEZDResult ssmResult = NLCL1040Query.getInstance().getPullDownList(params, "getAbcClassNameList");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> abcClassNameList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (abcClassNameList != null && !abcClassNameList.isEmpty()) {

                for (int i = 0; i < abcClassNameList.size(); i++) {

                    Map<String, Object> abcAnlsClsMap = (Map<String, Object>) abcClassNameList.get(i);

                    if (i >= cMsg.abcAnlsClsNm_L1.length()) {

                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_L1.no(i), (String) abcAnlsClsMap.get("ABC_ANLS_CLS_NUM"));
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm_L1.no(i), (String) abcAnlsClsMap.get("ABC_ANLS_CLS_NM"));
                    String abcAnlsClsNm = (String) abcAnlsClsMap.get("ABC_ANLS_CLS_NM");

                    if (!ZYPCommonFunc.hasValue(cMsg.P.no(0).srchOptTxt_P)) {
                        if (i == 0) {
                            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_H1, cMsg.abcAnlsClsNum_L1.no(i));
                        }
                    } else if (abcAnlsClsNm.equals(cMsg.P.no(0).srchOptTxt_P.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_H1, cMsg.abcAnlsClsNum_L1.no(i));
                    }
                }

                return;
            }
        }

    }

    /**
     * doProcess_NLCL1040Scrn00_OpenWin_EditClass
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_OpenWin_EditClass(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        S21SsmEZDResult result = NLCL1040Query.getInstance().getabcClassTag(cMsg, sMsg);

        List<Map<String, Object>> abcClassTagList = (List<Map<String, Object>>) result.getResultObject();

        Map<String, Object> abcClassTagMap = (Map<String, Object>) abcClassTagList.get(0);

        ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm, (String) abcClassTagMap.get("ABC_ANLS_CLS_NM"));

    }

    /**
     * doProcess_NLCL1040_INIT
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040_INIT(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        cMsg.glblCmpyCd.setValue(glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(cMsg.abcAsgNm)) {
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.S);
            ZYPTableUtil.clear(cMsg.S);
            EZDMsg.copy(cMsg, null, sMsg, null);
            EZDMsg.copy(cMsg.A, null, sMsg.A, null);
            EZDMsg.copy(cMsg.S, null, sMsg.S, null);
        }

        NLCL1040CommonLogic.createInitHeader(cMsg, getContextUserInfo().getUserId());

    }

    /**
     * doProcess_NLCL1040Scrn00_SearchHeader
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_SearchHeader(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.abcAsgNm)) {

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {
                return;
            }

            cMsg.abcAnlsRqstStsDescTxt.clear();
            cMsg.xxScrItem30Txt.clear();

            // get Header info
            NLCL1040CommonLogic.getAbcHeader(cMsg, sMsg);

            // Copy Search Result to Backup
            NLCL1040CommonLogic.setSearchResultToBackUp(cMsg, sMsg);

        }

    }

    /**
     * doProcess_NLCL1040Scrn00_Refresh
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Refresh(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // Copy Search Result to Backup
        NLCL1040CommonLogic.getLatestAnlsRequest(cMsg, sMsg);

    }

    /**
     * doProcess_NLCL1040Scrn00_EditTag
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_EditTag(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // copy message c -> S
        NLCL1040CommonLogic.copyMessage(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.mdseCd.setErrorInfo(1, NLCM0208E);
        }

    }

    /**
     * doProcess_NLCL1040Scrn00_AddTag
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_AddTag(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // checks if the row counts exceeds the maximum counts.
        int count = sMsg.A.getValidCount();
        if (count == sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8114E);
            return;
        }

        // copy message cMsg -> sMsg
        NLCL1040CommonLogic.copyMessage(cMsg, sMsg);

        // Insert row.
        sMsg.A.setValidCount(count + 1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).processedFlag_A, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).curInvtyQty_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).curInvtyCostAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).histInvtyTrxQty_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).histInvtyTrxCostAmt_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).histInvtyTrxRecCnt_A, BigDecimal.ZERO);

        cMsg.xxPageShowFromNum_A.setValue(getLastPageStartCount(sMsg.A.getValidCount(), cMsg.A.length()));
        
        // Pagination
        pagination(cMsg, sMsg);

    }

    /**
     * doProcess_NLCL1040Scrn00_PageJump
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_PageJump(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());

    }

    /**
     * doProcess_NLCL1040Scrn00_PageNext
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_PageNext(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_NLCL1040Scrn00_PagePrev
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_PagePrev(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);

    }

    /**
     * doProcess_NLCL1040Scrn00_TBLColumnSort
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_TBLColumnSort(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_A.getValue();

        if (NLCL1040Constant.TABLE_A.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // set page no
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        }

    }

    /**
     * doProcess_NLCL1040Scrn00_CMN_Clear
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_CMN_Clear(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // Clear Table
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.S);
        sMsg.clear();
        cMsg.clear();

    }

    /**
     * doProcess_NLCL1040Scrn00_Search_Item
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Search_Item(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        NLCL1040CommonLogic.getItemDescription(cMsg, sMsg);
    }

    /**
     * doProcess_NLCL1040Scrn00_Search_Warehouse
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Search_Warehouse(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        NLCL1040CommonLogic.getWarehouseName(cMsg, sMsg);
    }

    /**
     * doProcess_NLCL1040Scrn00_DeleteTag
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_DeleteTag(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // copy message c -> S
        NLCL1040CommonLogic.copyMessage(cMsg, sMsg);

        // check xxChkBox_A
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHECK_BOX, ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NPAM0049E);
            return;
        }

        // data delete from memory
        List<NLCL1040_ASMsg> recordData = new ArrayList<NLCL1040_ASMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).deleteFlagIf_A, sMsg.A.no(i).xxChkBox_A.getValue());

            if (!(ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).processedFlag_A.getValue()))) {

                recordData.add(sMsg.A.no(i));

            }

        }

        sMsg.A.setValidCount(recordData.size());

        for (int j = 0; j < sMsg.A.length(); j++) {

            if (j < recordData.size()) {

                EZDMsg.copy((EZDMsg) recordData.get(j), null, sMsg.A.no(j), null);

            } else {

                sMsg.A.no(j).clear();
            }
        }

        cMsg.setMessageInfo(NLCM0212I);

        // pagination
        pagination(cMsg, sMsg);
    }

    /**
     * <pre>
     * getLastPageStartCount
     * </pre>
     * @param allRowCount int
     * @param pageRowCount int
     */
    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {

        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }

        if (allRowCount <= pageRowCount) {
            return 1;
        }

        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }

        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }

    /**
     * paginationForInsertRow
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void pagination(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum_A.getValueInt() - 1; i < cMsg.xxPageShowToNum_A.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * setPagePos
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    public static void setPagePos(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum_A.setValue(0);
            cMsg.xxPageShowToNum_A.setValue(0);
            cMsg.xxPageShowOfNum_A.setValue(0);
            return;
        }

        int startRowCount = 0;

        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum_A)) {
            startRowCount = cMsg.xxPageShowFromNum_A.getValueInt();
        }

        int allRowCount = sMsg.A.getValidCount();

        if (startRowCount == 0) {

            cMsg.xxPageShowFromNum_A.setValue(1);

        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {

            // last page
            cMsg.xxPageShowFromNum_A.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));

        } else {

            if ((startRowCount % cMsg.A.length()) != 1) {

                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;

            }

            cMsg.xxPageShowFromNum_A.setValue(startRowCount);
        }

        if ((cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1) < allRowCount) {

            cMsg.xxPageShowToNum_A.setValue(cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1);

        } else {

            cMsg.xxPageShowToNum_A.setValue(allRowCount);
        }

        cMsg.xxPageShowOfNum_A.setValue(allRowCount);

    }

}

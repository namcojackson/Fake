/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1030;

import static business.blap.NLCL1030.constant.NLCL1030Constant.ABC_ANLS_CLS_NM_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.ABC_ANLS_CRIT_NM_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.ABC_ASG_DESC_TXT_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.ABC_ASG_NM_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.ABC_ASG_PK_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.CSV_FILE_NAME;
import static business.blap.NLCL1030.constant.NLCL1030Constant.CSV_HDR_DOWNLOAD;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_ABC_ASG_NM;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_ABC_ASG_PK;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_INVTY_LOC_SRCH_TXT_RW;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_INVTY_LOC_SRCH_TXT_SW;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_LIMIT_ROWNUM;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_LOC_NM_SRCH_TXT_RW;
import static business.blap.NLCL1030.constant.NLCL1030Constant.DB_PARAM_RTL_WH_CATG_CD;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EFF_FROM_DT_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EFF_THRU_DT_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_CMN_CLEAR;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_CMN_DOWNLOAD;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_ONCHANGE_SAVEDSEARCHOPTION;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_PAGE_JUMP;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_PAGE_NEXT;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_PAGE_PREV;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_SEARCH;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_TBLCOLUMNSORT;
import static business.blap.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030_INIT;
import static business.blap.NLCL1030.constant.NLCL1030Constant.LIMIT_DL_ROWNUM;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MAX_FETCH_SIZE;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_CD_NPAM1551W;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_CD_NPAM1552I;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_CD_NZZM0001W;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_CD_NZZM0002I;
import static business.blap.NLCL1030.constant.NLCL1030Constant.RTL_SWH_CD_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.RTL_WH_CATG_DESC_TXT_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.RTL_WH_CD_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.RTL_WH_NM_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.SRCH_OPT_NM_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.SRCH_OPT_PK_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.TABLE_A_HEAD;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL1030.common.NLCL1030CommonLogic;
import business.file.NLCL1030F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Logic for Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1030BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1030_INIT.equals(screenAplID) || EVENT_NM_NLCL1030SCRN00_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NLCL1030_INIT((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);
                ZYPGUITableColumn.getColData((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg, TABLE_A_HEAD);

            } else if (EVENT_NM_NLCL1030SCRN00_SEARCH.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_Search((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_ONCHANGE_SAVEDSEARCHOPTION.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_OnChange_SearchOption((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_PageNext((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_PAGE_PREV.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_PagePrev((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_PAGE_JUMP.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_PageJump((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_CMN_Download((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else if (EVENT_NM_NLCL1030SCRN00_TBLCOLUMNSORT.equals(screenAplID)) {
                doProcess_NLCL1030Scrn00_TBLColumnSort((NLCL1030CMsg) cMsg, (NLCL1030SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL1030_INIT(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        createPulldownList(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(sMsg.glblCmpyCd, getGlobalCompanyCode());

        EZDDebugOutput.println(1, "NLCL1030 start[doProcess_NLCL1030_INIT]", null);

    }

    private void doProcess_NLCL1030Scrn00_Search(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        // Set Query Conditions.
        Map<String, Object> ssmParam = setSsmParamForSearh(cMsg);
        ssmParam.put(DB_PARAM_LIMIT_ROWNUM, sMsg.A.length() + 1);

        // Search
        S21SsmEZDResult result = NLCL1030Query.getInstance().searchABCAssignment(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Checks if the row counts exceeds the maximum counts.
            int resultCount = result.getQueryResultCount();

            if (resultCount > sMsg.A.length()) {

                // Search results exceeds max count for display.
                resultCount = sMsg.A.length();
                cMsg.setMessageInfo(MSG_CD_NPAM1551W, new String[] {String.valueOf(resultCount), String.valueOf(resultCount) });

            } else {
                cMsg.setMessageInfo(MSG_CD_NZZM0002I);
            }

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                Map<String, Object> ssmParamSwh = new HashMap<String, Object>();
                ssmParamSwh.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
                ssmParamSwh.put(DB_PARAM_ABC_ASG_PK, sMsg.A.no(j).abcAsgPk_A.getValue());
                ssmParamSwh.put(DB_PARAM_INVTY_LOC_SRCH_TXT_RW, sMsg.A.no(j).rtlWhCdSrchTxt_AW.getValue());

                S21SsmEZDResult resultSwh = NLCL1030Query.getInstance().searchAbcAssignmentSwh(ssmParamSwh);

                if (resultSwh.isCodeNormal()) {

                    List<Map<String, Object>> subwhCdList = (List<Map<String, Object>>) resultSwh.getResultObject();

                    StringBuffer whCdBuffer = new StringBuffer();

                    for (int i = 0; i < subwhCdList.size(); i++) {

                        Map<String, Object> swhCdMap = (Map<String, Object>) subwhCdList.get(i);

                        if (i != 0) {
                            whCdBuffer.append(",");
                        }
                        whCdBuffer.append((String) swhCdMap.get(RTL_SWH_CD_DBCOLUMN));
                    }

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).rtlWhCdSrchTxt_AS, whCdBuffer.toString());

                }

            }

            // Sets the search result to the table.
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(resultCount);

        } else {
            cMsg.setMessageInfo(MSG_CD_NPAM1552I);
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
        }

    }

    /**
     * Pulldown onChange Event
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_OnChange_SearchOption(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NLCL1030CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());

        // Search Option
        S21SsmEZDResult srchOptnPulldownList = NLCL1030Query.getInstance().getSrchOptnPulldownList();

        if (srchOptnPulldownList.isCodeNormal()) {

            List<Map> srchOptnList = (List<Map>) srchOptnPulldownList.getResultObject();
            NLCL1030CommonLogic.createPulldownList(cMsg.srchOptPk_L, cMsg.srchOptNm_L, srchOptnList, new String[] {SRCH_OPT_PK_DBCOLUMN, SRCH_OPT_NM_DBCOLUMN });
        }

        // Warehouse Type
        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, cMsg.rtlWhCatgCd_L1, cMsg.rtlWhCatgDescTxt_L1);

    }

    /**
     * setSsmParamForSearh
     * @param cMsg NLCL1030CMsg
     * @return ssmParam Map<String, Object>
     */
    private Map<String, Object> setSsmParamForSearh(NLCL1030CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());

        ssmParam.put(DB_PARAM_ABC_ASG_NM, cMsg.abcAsgNm.getValue());
        ssmParam.put(DB_PARAM_RTL_WH_CATG_CD, cMsg.rtlWhCatgCd_H1.getValue());
        ssmParam.put(DB_PARAM_INVTY_LOC_SRCH_TXT_RW, NLCL1030CommonLogic.splitSrchTxt(cMsg.rtlWhCdSrchTxt_RW));
        ssmParam.put(DB_PARAM_LOC_NM_SRCH_TXT_RW, NLCL1030CommonLogic.splitSrchTxt(cMsg.rtlWhNmSrchTxt_RW));
        ssmParam.put(DB_PARAM_INVTY_LOC_SRCH_TXT_SW, NLCL1030CommonLogic.splitSrchTxt(cMsg.rtlSwhCdSrchTxt_SW));
        return ssmParam;
    }

    /**
     * Page Next
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_PageNext(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

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

        // set value to pagination items
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * Page Prev
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_PagePrev(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

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

        // set value to pagination items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Page Jump
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_PageJump(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        int pageFrom = cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1);
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        // set value to pagination items
        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum_A.setValue(pageFrom);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * CSV Download
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_CMN_Download(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLCL1030Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> ssmParam = setSsmParamForSearh(cMsg);
            ssmParam.put(DB_PARAM_LIMIT_ROWNUM, LIMIT_DL_ROWNUM + 1);

            ps = ssmLLClient.createPreparedStatement("searchABCAssignment", ssmParam, execParam);

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
     * @param cMsg NLCL1030CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private void writeCsvFile(NLCL1030CMsg cMsg, ResultSet rs) throws SQLException {

        NLCL1030F00FMsg fMsg = new NLCL1030F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(MSG_CD_NPAM1552I, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(MSG_CD_NZZM0001W, null);
                break;
            }
            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.abcAsgNm_A, rs.getString(ABC_ASG_NM_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(fMsg.abcAsgDescTxt_A, rs.getString(ABC_ASG_DESC_TXT_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgDescTxt_A, rs.getString(RTL_WH_CATG_DESC_TXT_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCdSrchTxt_AW, rs.getString(RTL_WH_CD_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNmSrchTxt_AW, rs.getString(RTL_WH_NM_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(fMsg.abcAnlsCritNm_A, rs.getString(ABC_ANLS_CRIT_NM_DBCOLUMN));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AF, NLCL1030CommonLogic.formatDt(rs.getString(EFF_FROM_DT_DBCOLUMN)));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AT, NLCL1030CommonLogic.formatDt(rs.getString(EFF_THRU_DT_DBCOLUMN)));
            ZYPEZDItemValueSetter.setValue(fMsg.abcAnlsClsNm_A, rs.getString(ABC_ANLS_CLS_NM_DBCOLUMN));

            Map<String, Object> ssmParamSwh = new HashMap<String, Object>();
            ssmParamSwh.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            ssmParamSwh.put(DB_PARAM_ABC_ASG_PK, rs.getBigDecimal(ABC_ASG_PK_DBCOLUMN));
            ssmParamSwh.put(DB_PARAM_INVTY_LOC_SRCH_TXT_RW, rs.getString(RTL_WH_CD_DBCOLUMN));

            S21SsmEZDResult resultSwh = NLCL1030Query.getInstance().searchAbcAssignmentSwh(ssmParamSwh);

            if (resultSwh.isCodeNormal()) {

                List<Map<String, Object>> subwhCdList = (List<Map<String, Object>>) resultSwh.getResultObject();

                StringBuffer whCdBuffer = new StringBuffer();

                for (int i = 0; i < subwhCdList.size(); i++) {

                    Map<String, Object> swhCdMap = (Map<String, Object>) subwhCdList.get(i);

                    if (i != 0) {
                        whCdBuffer.append(",");
                    }
                    whCdBuffer.append((String) swhCdMap.get(RTL_SWH_CD_DBCOLUMN));
                }

                ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCdSrchTxt_AS, whCdBuffer.toString());

            }

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();

    }

    /**
     * writeCsvFileHeader
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NLCL1030F00FMsg
     * @param cMsg NLCL1030CMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NLCL1030F00FMsg fMsg, NLCL1030CMsg cMsg) {

        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

    }

    /**
     * Table Column Sort
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    private void doProcess_NLCL1030Scrn00_TBLColumnSort(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_A.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum_A.setValue(BigDecimal.ONE);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        }
    }

}

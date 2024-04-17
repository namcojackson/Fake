/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6890;

import static business.blap.NMAL6890.constant.NMAL6890Constant.CSA_TO_CUSA;
import static business.blap.NMAL6890.constant.NMAL6890Constant.CSV_FILE_NAME;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_EFFFROMDT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_EFFTHRUDT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_WHOWNRCD_H1;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_FIRSTREFCMNTTXT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_FULLPSNNM_O1;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_FULLPSNNM_O2;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_INVTYACCTCD_IS;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_INVTYOWNRCD_OS;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_LIMIT_ROWNUM;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_PLNITEMINSRCCD_MS;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_PROCRTPCD_ES;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_PROCRTPCD_RS;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_PROCRTPCD_SS;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLSWHCD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLWHCATGCD_RS;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLWHCD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLWHDESCTXT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLWHNM_H2;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLWHNM_H3;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTLWHNM_H4;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_CTYADDR;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTL_WH_NM_H1;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_RTRNTOLOCNM;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_SHIPTOLOCNM;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_TELNUM;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_VNDLOCCD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_VND_XREF_TP_CD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_CMN_CLEAR;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_CMN_DOWNLOAD;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_ONCHANGE_SEARCHOPTION;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_PAGE_JUMP;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_PAGE_NEXT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_PAGE_PREV;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_SEARCH;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_TBLCOLUMNSORT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890_INIT;
import static business.blap.NMAL6890.constant.NMAL6890Constant.FLG_ON;
import static business.blap.NMAL6890.constant.NMAL6890Constant.INVTY_ACCT_CD_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.INVTY_ACCT_DESC_TXT_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.INVTY_OWNR_CD_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.INVTY_OWNR_DESC_TXT_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL6890.constant.NMAL6890Constant.LIMIT_DL_ROWNUM_SEARCH;
import static business.blap.NMAL6890.constant.NMAL6890Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL6890.constant.NMAL6890Constant.MSG_CD_NMAM0038I;
import static business.blap.NMAL6890.constant.NMAL6890Constant.MSG_CD_NMAM8181W;
import static business.blap.NMAL6890.constant.NMAL6890Constant.MSG_CD_NZZM0001W;
import static business.blap.NMAL6890.constant.NMAL6890Constant.MSG_CD_NZZM0002I;
import static business.blap.NMAL6890.constant.NMAL6890Constant.PLN_ITEM_INSRC_CD_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.PLN_ITEM_INSRC_DESC_TXT_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.PROCR_TP_CD_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.PROCR_TP_DESC_TXT_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.RTL_WH_CATG_CD_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.RTL_WH_CATG_DESC_TXT_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.SRCH_OPT_NM_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.SRCH_OPT_PK_DBCOLUMN;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL6890.common.NMAL6890CommonLogic;
import business.file.NMAL6890F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
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
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 10/21/2022   HITACHI         B.Amarsanaa     Update          QC#60609
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */
public class NMAL6890BL02 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL6890_INIT.equals(screenAplID)) {
                doProcess_NMAL6890_INIT((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);
                ZYPGUITableColumn.getColData((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_SEARCH.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_Search((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_CMN_Clear((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_ONCHANGE_SEARCHOPTION.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_OnChange_SearchOption((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_PageNext((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_PAGE_PREV.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_PagePrev((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_PAGE_JUMP.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_PageJump((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_CMN_Download((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else if (EVENT_NM_NMAL6890SCRN00_TBLCOLUMNSORT.equals(screenAplID)) {
                doProcess_NMAL6890Scrn00_TBLColumnSort((NMAL6890CMsg) cMsg, (NMAL6890SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6890_INIT(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {
        createPulldownList(cMsg, sMsg);
    }

    private void doProcess_NMAL6890Scrn00_Search(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        // Set Query Conditions.
        Map<String, Object> ssmParam = setSsmParamForSearh(cMsg);
        ssmParam.put(DB_PARAM_LIMIT_ROWNUM, LIMIT_DL_ROWNUM_SEARCH + 1);

        // Search
        S21SsmEZDResult result = NMAL6890Query.getInstance().searchWarehouses(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Checks if the row counts exceeds the maximum counts.
            int resultCount = result.getQueryResultCount();

            if (resultCount > sMsg.A.length()) {

                // Search results exceeds max count for display.
                resultCount = sMsg.A.length();
                cMsg.setMessageInfo(MSG_CD_NMAM8181W, new String[] {String.valueOf(resultCount), String.valueOf(resultCount) });

            } else {
                cMsg.setMessageInfo(MSG_CD_NZZM0002I);
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

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(resultCount);

        } else {
            cMsg.setMessageInfo(MSG_CD_NMAM0038I);
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

    }

    private void doProcess_NMAL6890Scrn00_CMN_Clear(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {
        doProcess_NMAL6890_INIT(cMsg, sMsg);
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_OnChange_SearchOption(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SS)) {
            NMAL6890CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * Page Next
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_PageNext(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * Page Prev
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_PagePrev(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Page Jump
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_PageJump(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        int pageFrom = cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        // set value to pagenation items
        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Table Column Sort
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_TBLColumnSort(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

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

            cMsg.xxPageShowFromNum.setValue(BigDecimal.ONE);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }
    }

    /**
     * CSV Download
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     */
    private void doProcess_NMAL6890Scrn00_CMN_Download(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6890Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> ssmParam = setSsmParamForSearh(cMsg);
            ssmParam.put(DB_PARAM_LIMIT_ROWNUM, LIMIT_DL_ROWNUM + 1);

            ps = ssmLLClient.createPreparedStatement("searchWarehouses", ssmParam, execParam);

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
     * @param cMsg NMAL6890CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private void writeCsvFile(NMAL6890CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL6890F00FMsg fMsg = new NMAL6890F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(MSG_CD_NMAM0038I, null);
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
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_A, rs.getString("WAREHOUSE_CODE"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, rs.getString("WAREHOUSE_NAME"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhDescTxt_A, rs.getString("WAREHOUSE_DESC"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgDescTxt_A, rs.getString("WAREHOUSE_TYPE"));
            ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_AO, rs.getString("OWNER"));
            ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_AA, rs.getString("ALT_OWNER"));
            ZYPEZDItemValueSetter.setValue(fMsg.invtyAcctDescTxt_A, rs.getString("INVENTORY_ACCOUNT"));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm_A, rs.getString("SHIP_TO_LOC"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtrnToLocNm_A, rs.getString("RETURN_LOC"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpDescTxt_AD, rs.getString("DEFAULT_SOURCE_TYPE"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A2, rs.getString("SOURCE_WH"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpDescTxt_AR, rs.getString("DEFAULT_RETURN_TYPE"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A3, rs.getString("RETURN_WH"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpDescTxt_AE, rs.getString("EMERGENCY_SOURCE_TYPE"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A4, rs.getString("EMERGENCY_SOURCE"));
            ZYPEZDItemValueSetter.setValue(fMsg.plnItemInsrcDescTxt_A, rs.getString("ALLOW_MIN_MAX_INSRC"));
            //10/26/2022 HITACHI B.Amarsanaa (QC#60608) ADD START 
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_A, rs.getString("CTY_ADDR"));
            //10/26/2022 HITACHI B.Amarsanaa (QC#60608) ADD END 
            ZYPEZDItemValueSetter.setValue(fMsg.vndLocCd_A, rs.getString("TRADING_PARTNER_ID"));
            ZYPEZDItemValueSetter.setValue(fMsg.invtyOwnrDescTxt_A, rs.getString("INVENTORY_OWNERSHIP"));
            ZYPEZDItemValueSetter.setValue(fMsg.firstRefCmntTxt_A, rs.getString("SPACE"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AF, NMAL6890CommonLogic.formatDt(rs.getString("WH_START_DATE")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AT, NMAL6890CommonLogic.formatDt(rs.getString("WH_END_DATE")));

            if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {
                ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_A, rs.getString("SUB_WH_CD"));
            }

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();

    }

    /**
     * writeCsvFileHeader
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NMAL6890F00FMsg
     * @param cMsg NMAL6890CMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NMAL6890F00FMsg fMsg, NMAL6890CMsg cMsg) {

        //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
//        final String[] csvHeader = new String[] {"Warehouse Code", "Warehouse Name", "Warehouse Description", "Warehouse Type", "Owner", "Alternate Owner", "Inventory Account", "Ship To Location", "Return Location", "Default Source Type",
//                "Source Warehouse", "Default Return Type", "Return Warehouse", "Emergency Source Type", "Emergency Source", "Allow Min-Max for Insourcing", "Trading Partner ID", "Inventory Ownership", "Space", "WH Start Date",
//                "WH End Date", "Sub WH Code" };

        //final String[] csvHeader = new String[] {"Warehouse Code", "Warehouse Name", "Warehouse Description", "Warehouse Type", "Owner", "Alternate Owner", "Inventory Account", "Ship To Location", "Return Location", "Default Source Type",
            //    "Source Warehouse", "Default Return Type", "Return Warehouse", "Tech Reference Satellite", "Tech Reference Satellite Name", "Allow Min-Max for Insourcing", "City","Trading Partner ID", "Inventory Ownership", "Space", "WH Start Date",
            //    "WH End Date", "Sub WH Code" };
        //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END
        //10/26/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
        final String[] csvHeader = new String[] {"Warehouse Code", "Warehouse Name", "Warehouse Description", "Warehouse Type", "Owner", "Alternate Owner", "Inventory Account", "Ship To Location", "Return Location", "Default Source Type",
                "Source Warehouse", "Default Return Type", "Return Warehouse", "Tech Reference Satellite", "Tech Reference Satellite Name", "Allow Min-Max for Insourcing", "City","Trading Partner ID", "Inventory Ownership", "Space", "WH Start Date",
                "WH End Date", "Sub WH Code" };
        //10/26/2022 HITACHI B.Amarsanaa (QC#60608) ADD END 
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    /**
     * setSsmParamForSearh
     * @param cMsg NMAL6890CMsg
     * @return ssmParam Map<String, Object>
     */
    private Map<String, Object> setSsmParamForSearh(NMAL6890CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());

        ssmParam.put(DB_PARAM_RTL_WH_NM_H1, cMsg.rtlWhNm_H1);
        ssmParam.put(DB_PARAM_RTLWHDESCTXT, cMsg.rtlWhDescTxt);
        ssmParam.put(DB_PARAM_RTLWHCD, NMAL6890CommonLogic.splitSrchTxt(cMsg.rtlWhCd));
        ssmParam.put(DB_PARAM_RTLWHCATGCD_RS, cMsg.rtlWhCatgCd_RS);
        ssmParam.put(DB_PARAM_INVTYACCTCD_IS, cMsg.invtyAcctCd_IS);
        ssmParam.put(DB_PARAM_TELNUM, cMsg.telNum);
        ssmParam.put(DB_PARAM_PROCRTPCD_SS, cMsg.procrTpCd_SS);
        ssmParam.put(DB_PARAM_PROCRTPCD_RS, cMsg.procrTpCd_RS);
        ssmParam.put(DB_PARAM_PROCRTPCD_ES, cMsg.procrTpCd_ES);
        ssmParam.put(DB_PARAM_PLNITEMINSRCCD_MS, cMsg.plnItemInsrcCd_MS);
        ssmParam.put(DB_PARAM_VNDLOCCD, cMsg.vndLocCd);
        ssmParam.put(DB_PARAM_INVTYOWNRCD_OS, cMsg.invtyOwnrCd_OS);
        ssmParam.put(DB_PARAM_FIRSTREFCMNTTXT, cMsg.firstRefCmntTxt);
        ssmParam.put(DB_PARAM_EFFFROMDT, cMsg.effFromDt);
        ssmParam.put(DB_PARAM_EFFTHRUDT, cMsg.effThruDt);
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ssmParam.put(DB_PARAM_WHOWNRCD_H1, cMsg.whOwnrCd_H1);
        // END 2023/03/07 S.Dong [QC#61205, ADD]
        ssmParam.put(DB_PARAM_FULLPSNNM_O1, cMsg.fullPsnNm_O1);
        ssmParam.put(DB_PARAM_FULLPSNNM_O2, cMsg.fullPsnNm_O2);
        ssmParam.put(DB_PARAM_SHIPTOLOCNM, cMsg.shipToLocNm);
        ssmParam.put(DB_PARAM_RTRNTOLOCNM, cMsg.rtrnToLocNm);
        ssmParam.put(DB_PARAM_RTLWHNM_H2, cMsg.rtlWhNm_H2);
        ssmParam.put(DB_PARAM_RTLWHNM_H3, cMsg.rtlWhNm_H3);
        ssmParam.put(DB_PARAM_RTLWHNM_H4, cMsg.rtlWhNm_H4);
        ssmParam.put(DB_PARAM_RTLSWHCD, cMsg.rtlSwhCd);
        ssmParam.put(DB_PARAM_VND_XREF_TP_CD, CSA_TO_CUSA);
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START 
        ssmParam.put(DB_PARAM_CTYADDR, cMsg.ctyAddr);
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END 
        return ssmParam;
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(FLG_ON, ZYPConstant.FLG_ON_Y);

        // Search Option
        S21SsmEZDResult srchOptnPulldownList = NMAL6890Query.getInstance().getSrchOptnPulldownList();

        if (srchOptnPulldownList.isCodeNormal()) {

            List<Map> srchOptnList = (List<Map>) srchOptnPulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.srchOptPk_SC, cMsg.srchOptNm_SC, srchOptnList, new String[] {SRCH_OPT_PK_DBCOLUMN, SRCH_OPT_NM_DBCOLUMN });
        }

        // Warehouse Type
        S21SsmEZDResult whTypePulldownList = NMAL6890Query.getInstance().getWhTypePulldownList(ssmParam);

        if (whTypePulldownList.isCodeNormal()) {

            List<Map> whTypeList = (List<Map>) whTypePulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.rtlWhCatgCd_RC, cMsg.rtlWhCatgDescTxt_RC, whTypeList, new String[] {RTL_WH_CATG_CD_DBCOLUMN, RTL_WH_CATG_DESC_TXT_DBCOLUMN });
        }

        // Inventory Account
        S21SsmEZDResult invtyAcctPulldownList = NMAL6890Query.getInstance().getInvtyAcctPulldownList(ssmParam);

        if (invtyAcctPulldownList.isCodeNormal()) {

            List<Map> invtyAcctList = (List<Map>) invtyAcctPulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.invtyAcctCd_IC, cMsg.invtyAcctDescTxt_IC, invtyAcctList, new String[] {INVTY_ACCT_CD_DBCOLUMN, INVTY_ACCT_DESC_TXT_DBCOLUMN });
        }

        // Default Source Type, Default Return Type, Tech Emergency
        // Source Type
        S21SsmEZDResult srcTypePulldownList = NMAL6890Query.getInstance().getSrcTypePulldownList(ssmParam);

        if (srcTypePulldownList.isCodeNormal()) {

            // Default Source Type
            List<Map> dfltSrcTypeList = (List<Map>) srcTypePulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.procrTpCd_SC, cMsg.procrTpDescTxt_SC, dfltSrcTypeList, new String[] {PROCR_TP_CD_DBCOLUMN, PROCR_TP_DESC_TXT_DBCOLUMN });

            // Default Return Type
            List<Map> dfltRtnTypeList = (List<Map>) srcTypePulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.procrTpCd_RC, cMsg.procrTpDescTxt_RC, dfltRtnTypeList, new String[] {PROCR_TP_CD_DBCOLUMN, PROCR_TP_DESC_TXT_DBCOLUMN });

            // Tech Emergency Source Type
            List<Map> tchEmrgySrcTypeList = (List<Map>) srcTypePulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.procrTpCd_EC, cMsg.procrTpDescTxt_EC, tchEmrgySrcTypeList, new String[] {PROCR_TP_CD_DBCOLUMN, PROCR_TP_DESC_TXT_DBCOLUMN });
        }

        // Allow MIN-MAX Parts For Insourcing
        S21SsmEZDResult plnItmInsrcPulldownList = NMAL6890Query.getInstance().getPlnItmInsrcPulldownList(ssmParam);

        if (plnItmInsrcPulldownList.isCodeNormal()) {

            List<Map> plnItmInsrcList = (List<Map>) plnItmInsrcPulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.plnItemInsrcCd_MC, cMsg.plnItemInsrcDescTxt_MC, plnItmInsrcList, new String[] {PLN_ITEM_INSRC_CD_DBCOLUMN, PLN_ITEM_INSRC_DESC_TXT_DBCOLUMN });
        }

        // Inventory Ownership
        S21SsmEZDResult invtyOwnrPulldownList = NMAL6890Query.getInstance().getInvtyOwnrPulldownList(ssmParam);

        if (invtyOwnrPulldownList.isCodeNormal()) {

            List<Map> invtyOwnrList = (List<Map>) invtyOwnrPulldownList.getResultObject();
            NMAL6890CommonLogic.createPulldownList(cMsg.invtyOwnrCd_OC, cMsg.invtyOwnrDescTxt_OC, invtyOwnrList, new String[] {INVTY_OWNR_CD_DBCOLUMN, INVTY_OWNR_DESC_TXT_DBCOLUMN });
        }
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ZYPCodeDataUtil.createPulldownList(WH_OWNR.class, cMsg.whOwnrCd_L1, cMsg.whOwnrNm_L1);
        // END 2023/03/07 S.Dong [QC#61205, ADD]
    }

}

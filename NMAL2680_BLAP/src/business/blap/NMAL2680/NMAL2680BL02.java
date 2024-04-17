/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2680;

import static business.blap.NMAL2680.constant.NMAL2680Constant.CSV_FILE_NAME;
import static business.blap.NMAL2680.constant.NMAL2680Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL2680.constant.NMAL2680Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL2680.constant.NMAL2680Constant.NZZM0000E;
import static business.blap.NMAL2680.constant.NMAL2680Constant.NZZM0001W;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2680.constant.NMAL2680Constant;
import business.db.DS_ORG_UNITTMsg;
import business.file.NMAL2680F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 * 2016/08/03   Fujitsu         C.Yokoi         Update          CSA-QC#12679
 *</pre>
 */
public class NMAL2680BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            NMAL2680CMsg bizMsg = (NMAL2680CMsg) cMsg;
            NMAL2680SMsg glblMsg = (NMAL2680SMsg) sMsg;

            if ("NMAL2680_INIT".equals(screenAplID)) {
                doProcess_NMAL2680_INIT(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_Filter".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_Filter(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_Download".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_Download(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_Search_Cust".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_Search_Cust(bizMsg, glblMsg);

            } else if ("NMAL2680Scrn00_Search_Pros".equals(screenAplID)) {
                doProcess_NMAL2680Scrn00_Search_Pros(bizMsg, glblMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680_INIT(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctTpCd_H1, DS_ACCT_TP.CUSTOMER);
        if (ZYPCommonFunc.hasValue(bizMsg.trtyRulePk_H1) || ZYPCommonFunc.hasValue(bizMsg.orgCd_P1)) {
            search(bizMsg, glblMsg);
        } else {
            bizMsg.setMessageInfo(NMAL2680Constant.NZZM0000E);
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_PagePrev(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        pagenation(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_PageNext(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        pagenation(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_CMN_Clear(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        bizMsg.xxQueryFltrTxt_H1.clear();
        doProcess_NMAL2680_INIT(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_TBLColumnSort(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_Filter(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        searchRule(glblCmpyCd, bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_Download(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2680Query.getInstance().getClass());

            // create csv file
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> queryParam = NMAL2680Query.getInstance().getTrytyRuleParam(getGlobalCompanyCode(), bizMsg, glblMsg, false);
            String sqlId = null;
            if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H1.getValue())) {
                sqlId = "getCustRule";
            } else {
                sqlId = "getProsRule";
            }
            ps = ssmLLClient.createPreparedStatement(sqlId, queryParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_Search_Cust(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctTpCd_H1, DS_ACCT_TP.CUSTOMER);
        doProcess_NMAL2680Scrn00_Filter(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2680Scrn00_Search_Pros(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctTpCd_H1, DS_ACCT_TP.PROSPECT);
        doProcess_NMAL2680Scrn00_Filter(bizMsg, glblMsg);
    }

    /**
     * writeCsvFile
     * @param bizMsg NMAL2680CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private void writeCsvFile(NMAL2680CMsg bizMsg, ResultSet rs) throws SQLException {

        NMAL2680F00FMsg fMsg = new NMAL2680F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        if (!rs.next()) {
            bizMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                bizMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.trtyRulePk_A1, rs.getBigDecimal("TRTY_RULE_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A1, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctTpNm_A1, rs.getString("DS_ACCT_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.locNum_A1, rs.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_A1, rs.getString("XX_ALL_LINE_ADDR"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NMAL2680F00FMsg fMsg, NMAL2680CMsg bizMsg) {
        final String[] csvHeader = new String[] {"Territory Rule PK", "Account Name", "Account#", "Account", "Location#", "Address" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
    }

    /**
     * Search
     * @param bizMsg NMAL2680CMsg
     * @param glblMsg NMAL2680SMsg
     */
    @SuppressWarnings("unchecked")
    private void search(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        S21SsmEZDResult ssmResult = NMAL2680Query.getInstance().getTrtyRule(glblCmpyCd, bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.orgCd_H1, (String) map.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H1, (String) map.get("ORG_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMsgEntryTxt_H1, (String) map.get("XX_DS_MSG_ENTRY_TXT"));

            searchRule(glblCmpyCd, bizMsg, glblMsg);
        // 2016/08/03 CSA-QC#12679 Add start
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.orgCd_P1)) {
                DS_ORG_UNITTMsg dsOrgUnitTMsg = new DS_ORG_UNITTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, bizMsg.orgCd_P1);

                dsOrgUnitTMsg = (DS_ORG_UNITTMsg)EZDTBLAccessor.findByKey(dsOrgUnitTMsg);

                if (EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgUnitTMsg.getReturnCode())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.orgCd_H1, dsOrgUnitTMsg.orgCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H1, dsOrgUnitTMsg.orgNm);
                    bizMsg.setMessageInfo(NMAL2680Constant.NZZM0000E);
                } else {
                    bizMsg.setMessageInfo(NMAL2680Constant.NMAM8489E, new String[] {"Territory"});
                }
            }
        }
        // 2016/08/03 CSA-QC#12679 Add End

    }

    /**
     * searchRule
     * @param glblCmpyCd String
     * @param bizMsg NMAL2680CMsg
     * @param glblMsg NMAL2680SMsg
     */
    private void searchRule(String glblCmpyCd, NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {

        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = null;
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H1.getValue())) {
            ssmResult = NMAL2680Query.getInstance().getCustRule(glblCmpyCd, bizMsg, glblMsg);
        } else {
            ssmResult = NMAL2680Query.getInstance().getProsRule(glblCmpyCd, bizMsg, glblMsg);
        }
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NMAL2680Constant.NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            }

        } else {
            bizMsg.setMessageInfo(NMAL2680Constant.NZZM0000E);
            return;
        }

        bizMsg.xxPageShowFromNum.setValue(0);
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        pagenation(bizMsg, glblMsg);
    }

    /**
     * Paging
     * @param bizMsg NMAL2680CMsg
     * @param glblMsg NMAL2680SMsg
     */
    private void pagenation(NMAL2680CMsg bizMsg, NMAL2680SMsg glblMsg) {

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to page nation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }
}

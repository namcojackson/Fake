/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2670;

import static business.blap.NMAL2670.constant.NMAL2670Constant.CSV_FILE_NAME;
import static business.blap.NMAL2670.constant.NMAL2670Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL2670.constant.NMAL2670Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL2670.constant.NMAL2670Constant.NZZM0000E;
import static business.blap.NMAL2670.constant.NMAL2670Constant.NZZM0001W;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2670.constant.NMAL2670Constant;
import business.db.DS_ORG_UNITTMsg;
import business.file.NMAL2670F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
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
 *</pre>
 */
public class NMAL2670BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            NMAL2670CMsg bizMsg = (NMAL2670CMsg) cMsg;
            NMAL2670SMsg glblMsg = (NMAL2670SMsg) sMsg;

            if ("NMAL2670_INIT".equals(screenAplID)) {
                doProcess_NMAL2670_INIT(bizMsg, glblMsg);

            } else if ("NMAL2670Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2670Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2670Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2670Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2670Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2670Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2670Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2670Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NMAL2670Scrn00_Filter".equals(screenAplID)) {
                doProcess_NMAL2670Scrn00_Filter(bizMsg, glblMsg);

            } else if ("NMAL2670Scrn00_Download".equals(screenAplID)) {
                doProcess_NMAL2670Scrn00_Download(bizMsg, glblMsg);

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
    private void doProcess_NMAL2670_INIT(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.orgCd_H1)) {
            search(bizMsg, glblMsg);
        } else {
            bizMsg.setMessageInfo(NMAL2670Constant.NZZM0000E);
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2670Scrn00_PagePrev(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {
        pagenation(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2670Scrn00_PageNext(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {
        pagenation(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2670Scrn00_CMN_Clear(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {
        bizMsg.xxQueryFltrTxt_H1.clear();
        doProcess_NMAL2670_INIT(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2670Scrn00_TBLColumnSort(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {

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
    private void doProcess_NMAL2670Scrn00_Filter(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        searchTrtyRule(glblCmpyCd, bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2670Scrn00_Download(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2670Query.getInstance().getClass());

            // create csv file
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> queryParam = NMAL2670Query.getInstance().getTrytyRuleParam(getGlobalCompanyCode(), bizMsg, glblMsg, false);
            ps = ssmLLClient.createPreparedStatement("getTrtyRule", queryParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * writeCsvFile
     * @param bizMsg NMAL2670CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public void writeCsvFile(NMAL2670CMsg bizMsg, ResultSet rs) throws SQLException {

        NMAL2670F00FMsg fMsg = new NMAL2670F00FMsg();
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
            ZYPEZDItemValueSetter.setValue(fMsg.xxDsMsgEntryTxt_A1, rs.getString("XX_DS_MSG_ENTRY_TXT"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NMAL2670F00FMsg fMsg, NMAL2670CMsg bizMsg) {
        final String[] csvHeader = new String[] {"Territory Rule PK", "Territory Rule" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
    }

    /**
     * search
     * @param bizMsg NMAL2670CMsg
     * @param glblMsg NMAL2670SMsg
     */
    private void search(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        DS_ORG_UNITTMsg dsOrgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, bizMsg.orgCd_H1);
        dsOrgUnitTMsg = (DS_ORG_UNITTMsg) S21FastTBLAccessor.findByKey(dsOrgUnitTMsg);
        if (dsOrgUnitTMsg == null) {
            bizMsg.setMessageInfo(NMAL2670Constant.NZZM0000E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H1, dsOrgUnitTMsg.orgNm);

        searchTrtyRule(glblCmpyCd, bizMsg, glblMsg);
    }

    /**
     * searchTrtyRule
     * @param bizMsg NMAL2670CMsg
     * @param glblMsg NMAL2670SMsg
     */
    private void searchTrtyRule(String glblCmpyCd, NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {

        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL2670Query.getInstance().getTrtyRule(glblCmpyCd, bizMsg, glblMsg);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NMAL2670Constant.NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            }

        } else {
            bizMsg.setMessageInfo(NMAL2670Constant.NZZM0000E);
            return;
        }

        bizMsg.xxPageShowFromNum.setValue(0);
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        pagenation(bizMsg, glblMsg);
    }

    /**
     * Paging
     * @param bizMsg NMAL2670CMsg
     * @param glblMsg NMAL2670SMsg
     */
    private void pagenation(NMAL2670CMsg bizMsg, NMAL2670SMsg glblMsg) {

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

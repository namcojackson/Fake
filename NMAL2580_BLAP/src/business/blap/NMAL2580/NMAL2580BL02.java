/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2580;

import static business.blap.NMAL2580.constant.NMAL2580Constant.FETCH_SIZE;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FILE_SFX;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2460;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2620;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2710;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2720;
import static business.blap.NMAL2580.constant.NMAL2580Constant.NZZM0000E;
import static business.blap.NMAL2580.constant.NMAL2580Constant.NZZM0001W;
import static business.blap.NMAL2580.constant.NMAL2580Constant.NZZM0002I;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2580.common.NMAL2580CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
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
 * NMAL2580BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2016/07/28   Fujitsu         R.Nakamura      Update          QC#11871
 *</pre>
 */
public class NMAL2580BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2580CMsg bizMsg = (NMAL2580CMsg) cMsg;
            NMAL2580SMsg glblMsg = (NMAL2580SMsg) sMsg;

            if ("NMAL2580_INIT".equals(screenAplID)) {
                doProcess_NMAL2580_INIT(bizMsg, glblMsg);

            } else if ("NMAL2580Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2580Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2580Scrn00_Download_DetailData".equals(screenAplID)) {
                doProcess_NMAL2580Scrn00_Download_DetailData(bizMsg, glblMsg);

            } else if ("NMAL2580Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2580Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2580Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2580Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2580Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2580Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL2580Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2580Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580_INIT(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        getPullDownList(bizMsg);

    }

    /**
     * Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580Scrn00_CMN_Clear(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        NMAL2580CommonLogic.clearHeaderData(bizMsg);

        NMAL2580CommonLogic.clearSearchData(bizMsg);

        NMAL2580CommonLogic.clearDetailData(bizMsg, glblMsg);

        getPullDownList(bizMsg);

    }

    /**
     * Download_DetailData Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580Scrn00_Download_DetailData(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            // Mod Start 2016/07/28 QC#11871
//            execParam.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            // Mod End 2016/07/28 QC#11871
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            String funcId = bizMsg.ezInAplID.getValue();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2580Query.getInstance().getClass());
            String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm(NMAL2580CommonLogic.setFileName(funcId));
            bizMsg.xxFileData.setTempFilePath(null, csvFileNm, FILE_SFX);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("rqstId", bizMsg.trtyUpdRqstHdrPk_DL.getValueInt());

            if (FUNCTION_ID_NMAL2460.equals(funcId)) {
                params.put("orgStruTp_TS", ORG_STRU_TP.TERRITORY_STRUCTURE);
                stmtSelect = ssmLLClient.createPreparedStatement("downloadNMAL2460", params, execParam);
            } else if (FUNCTION_ID_NMAL2620.equals(funcId)) {
                params.put("orgStruTp_TS", ORG_STRU_TP.TERRITORY_STRUCTURE);
                stmtSelect = ssmLLClient.createPreparedStatement("downloadNMAL2620", params, execParam);
            } else if (FUNCTION_ID_NMAL2710.equals(funcId)) {
                params.put("orgStruTp_TS", ORG_STRU_TP.TERRITORY_STRUCTURE);
                stmtSelect = ssmLLClient.createPreparedStatement("downloadNMAL2710", params, execParam);
            } else if (FUNCTION_ID_NMAL2720.equals(funcId)) {
                params.put("orgStruTp_BOS", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
                stmtSelect = ssmLLClient.createPreparedStatement("downloadNMAL2720", params, execParam);
            }

            rs = stmtSelect.executeQuery();

            if (FUNCTION_ID_NMAL2460.equals(funcId)) {
                NMAL2580CommonLogic.writeCsvFileNMAL2460(bizMsg, rs);
            } else if (FUNCTION_ID_NMAL2620.equals(funcId)) {
                NMAL2580CommonLogic.writeCsvFileNMAL2620(bizMsg, rs);
            } else if (FUNCTION_ID_NMAL2710.equals(funcId)) {
                NMAL2580CommonLogic.writeCsvFileNMAL2710(bizMsg, rs);
            } else if (FUNCTION_ID_NMAL2720.equals(funcId)) {
                NMAL2580CommonLogic.writeCsvFileNMAL2720(bizMsg, rs);
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580Scrn00_PageNext(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        // uncomment below method
        NMAL2580CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2580CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580Scrn00_PagePrev(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        // uncomment below method
        NMAL2580CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL2580CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580Scrn00_Search(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        NMAL2580CommonLogic.clearSearchData(bizMsg);

        // search
        search(bizMsg, glblMsg);

    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2580Scrn00_TBLColumnSort(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            // set pagination.
            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL2580CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        NMAL2580CommonLogic.clearDetailData(bizMsg, glblMsg);

        S21SsmEZDResult ssmResult = NMAL2580Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            } else {
                bizMsg.setMessageInfo(NZZM0002I);
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL2580CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
        }
    }

    /**
     * getPullDownRequestStatusList
     * @param cMsg NMAL2580CMsg
     */
    private void getPullDownList(NMAL2580CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(RQST_CRAT_SYS_TP.class, bizMsg.rqstCratSysTpCd_PC, bizMsg.rqstCratSysTpDescTxt_PC);

        ZYPCodeDataUtil.createPulldownList(RQST_RSLT_TP.class, bizMsg.rqstRsltTpCd_PC, bizMsg.rqstRsltTpDescTxt_PC);

    }

}

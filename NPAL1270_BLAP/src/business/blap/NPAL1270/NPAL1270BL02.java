/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1270;

import static business.blap.NPAL1270.constant.NPAL1270Constant.CSV_FILE_NAME;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PO_REQUISITION;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE_W_RECEIPT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_CMN_COL_CLEAR;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_CMN_COL_SAVE;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_CMN_DOWNLOAD;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_CMN_RESET;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_INIT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_PAGE_NEXT;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_PAGE_PREV;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EVENT_NM_NPAL1270_SEARCH;
import static business.blap.NPAL1270.constant.NPAL1270Constant.EXTN_CSV;
import static business.blap.NPAL1270.constant.NPAL1270Constant.ZZZM9001E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1270.common.NPAL1270CommonLogic;
import business.blap.NPAL1270.constant.NPAL1270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1270 PO Requisition List
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 03/01/2016   CITS            K.Ogino         Update          QC#4598
 * 11/16/2018   CITS            T.Hakodate      Update          QC#28939
 *</pre>
 */
public class NPAL1270BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1270_INIT.equals(screenAplID) || EVENT_NM_NPAL1270_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1270_INIT((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_ON_CHANGE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1270Scrn00_CMN_Download((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            } else if (EVENT_NM_NPAL1270_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPAL1270CMsg) cMsg, (NPAL1270SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     */
    private void doProcess_NPAL1270_INIT(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        cMsg.clear();
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        NPAL1270CommonLogic.createPullDownSearchOption(cMsg, glblCmpyCd, userId);

        NPAL1270CommonLogic.createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd);
        NPAL1270CommonLogic.createPullDownHeaderStatus(cMsg, sMsg, glblCmpyCd);
        NPAL1270CommonLogic.createPullDownLinelStatus(cMsg, sMsg, glblCmpyCd);
        NPAL1270CommonLogic.createPullDownApprovalStatus(cMsg, sMsg, glblCmpyCd);
        NPAL1270CommonLogic.createPullDownBusinessUnit(cMsg, sMsg, glblCmpyCd);
        NPAL1270CommonLogic.createPullDownDocumentSourceType(cMsg, sMsg, glblCmpyCd);
        NPAL1270CommonLogic.createPullDownRequestedShipMethod(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     */
    private void doProcess_Search(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1270CommonLogic.preSearchForSetName(cMsg, glblCmpyCd);

        NPAL1270CommonLogic.search(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Page Next
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     */
    private void doProcess_PageNext(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg) {
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
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     */
    private void doProcess_PagePrev(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg) {
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
     * Pulldown onChange Event
     * @param cMsg NPAL1270CMsg
     * @param sMsg NPAL1270SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            NPAL1270CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        }

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NPAL1270Scrn00_CMN_Download(NPAL1270CMsg cMsg, NPAL1270SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1270Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1270Query.getInstance().getClass());
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            String statementId = "search";
            NPAL1270CommonLogic.preSearchForSetName(cMsg, getGlobalCompanyCode());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
            ssmParam.put("prchReqRecTpCdRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
            ssmParam.put("prchReqRecTpCdReturn", PRCH_REQ_REC_TP.TECH_RETURN);
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_ROW_NUM, 65000);
            // PO_REQUISITION
            ssmParam.put(DB_PARAM_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);

            // QC#28939 add start
            ssmParam.put("mdseItemTpCd", MDSE_ITEM_TP.TEXT_ITEM);
            // QC#28939 add end
            // 2019/09/23 QC#53271 Add Start
            ssmParam.put(DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE, PRCH_REQ_LINE_TP.EXPENSE);
            ssmParam.put(DB_PARAM_PRCH_REQ_LINE_TP_CD_EXPENSE_W_RECEIPT, PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT);
            // 2019/09/23 QC#53271 Add End

            stmtSelect = ssmLLClient.createPreparedStatement(statementId, ssmParam, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(ZZZM9001E, null);
                return;
            }
            NPAL1270CommonLogic.writeCsvFileInfo(cMsg, rs, getGlobalCompanyCode());
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }
}

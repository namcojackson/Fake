/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0010;

import static business.blap.NPBL0010.constant.NPBL0010Constant.CSV_FILE_EXTENSION;
import static business.blap.NPBL0010.constant.NPBL0010Constant.CSV_FILE_NAME;
import static business.blap.NPBL0010.constant.NPBL0010Constant.CSV_HDR;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_CARR_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_CARR_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_CHARGE_ACCOUNT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_ACCT_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_AFFL_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_BR_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_CC_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_CH_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_CMPY_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_EXTN_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_MDSE_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_PROD_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_PROD_CD2;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_COA_PROJ_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_CPO_ORD_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_DEST_RTL_SWH_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_DEST_RTL_WH_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_DPLY_VND_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_DS_ACCT_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_ENT_DEAL_NET_UNIT_PRC_AMT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_ENT_PO_DTL_DEAL_NET_AMT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_FULL_PSN_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_LINE_NUMBER;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_MDSE_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_MDSE_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_MRP_PLN_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_GRP_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_CRAT_DT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_DISP_QTY;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_LINE_CMNT_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_LINE_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_LINE_SUB_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_NUM_FOR_VIEW;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_RQST_BY_PSN_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_STS_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_PRCH_REQ_TP_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_RQST_RCV_DT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_RTL_SWH_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_RTL_SWH_NM2;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_RTL_WH_NM2;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SERIAL_NUMBER;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SHIP_TO_CUST_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SHPG_SVC_LVL_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SRC_RTL_SWH_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SRC_RTL_WH_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_SVC_CONFIG_MSTR_PK;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_TRX_REF_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_COLUMN_VND_RTRN_RSN_DESC_TXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_CMSG;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_EXPENSE_ORDER_TP;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_PRCH_REQ_REC_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_VND_TP_CD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_PARAM_WH_TRANSFER_TP;
import static business.blap.NPBL0010.constant.NPBL0010Constant.DB_VALUE_MAX_BYTE_SIZE;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_CMN_DOWNLOAD;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_CMN_RESET;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_INIT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_PAGE_NEXT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_PAGE_PREV;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_TBL_COLUMN_SORT;
import static business.blap.NPBL0010.constant.NPBL0010Constant.EVENT_NM_NPBL0010_SEARCH;
import static business.blap.NPBL0010.constant.NPBL0010Constant.LIMIT_DL_ROWNUM;
import static business.blap.NPBL0010.constant.NPBL0010Constant.NZZM0000E;
import static business.blap.NPBL0010.constant.NPBL0010Constant.NZZM0001W;
import static business.blap.NPBL0010.constant.NPBL0010Constant.SCREEN_CONTROL_VALUE_SEARCH_OP_HEADER;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPBL0010.NPBL0010CMsg;
import business.blap.NPBL0010.NPBL0010SMsg;
import business.blap.NPBL0010.common.NPBL0010CommonLogic;
import business.blap.NPBL0010.constant.NPBL0010Constant;
import business.file.NPBL0010F00FMsg;

import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request Listh
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS         Makoto Okigami     Create          N/A
 * 04/05/2016   CITS         K.Ogino            Update          N/A
 * 09/05/2016   CITS         K.Ogino            Update          QC#13923
 * 08/23/2017   CITS         H.Naoi             Update          Sol#097(QC#18398)
 * 11/15/2017   CITS         K.Ogino            Update          QC#22345
 * 05/28/2019   CITS         K.Ogino            Update          QC#50203
 * 02/03/2021   CITS         F.Deola            Update          QC#58347
 * 06/22/2023   Hitachi      S.Fujita           Update          QC#61637
 *</pre>
 */
public class NPBL0010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPBL0010_INIT.equals(screenAplID) || EVENT_NM_NPBL0010_CMN_RESET.equals(screenAplID)) {
                doProcess_NPBL0010_INIT((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            } else if (EVENT_NM_NPBL0010_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            } else if (EVENT_NM_NPBL0010_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            } else if (EVENT_NM_NPBL0010_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            // START 2023/06/22 S.Fujita [QC#61637, ADD]
            } else if (EVENT_NM_NPBL0010_TBL_COLUMN_SORT.equals(screenAplID)) {
                doProcess_TBLColumnSort((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            // END 2023/06/22 S.Fujita [QC#61637, ADD]
            } else if (EVENT_NM_NPBL0010_ON_CHANGE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_OnChangeSearchOption((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            } else if (EVENT_NM_NPBL0010_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_Download((NPBL0010CMsg) cMsg, (NPBL0010SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     */
    private void doProcess_NPBL0010_INIT(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0010CommonLogic.createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
        NPBL0010CommonLogic.createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd);
        NPBL0010CommonLogic.createPullDownHeaderStatus(cMsg, sMsg, glblCmpyCd);
        NPBL0010CommonLogic.createPullDownApprovalStatus(cMsg, sMsg, glblCmpyCd);
        NPBL0010CommonLogic.createPullDownDocumentSourceType(cMsg, sMsg, glblCmpyCd);
        NPBL0010CommonLogic.createPullDownRequestedShipMethod(cMsg, sMsg, glblCmpyCd);

        // set default value
        ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn, BigDecimal.ZERO);

    }

    /**
     * Search
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     */
    private void doProcess_Search(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0010CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Page Next
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     */
    private void doProcess_PageNext(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
                // QC#50203
                NPBL0010CommonLogic.setCost(getGlobalCompanyCode(), cMsg.A.no(i - pagenationFrom));
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
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     */
    private void doProcess_PagePrev(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
                // QC#50203
                NPBL0010CommonLogic.setCost(getGlobalCompanyCode(), cMsg.A.no(i - pagenationFrom));
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

    // START 2023/06/22 S.Fujita [QC#61637, ADD]
    private void doProcess_TBLColumnSort(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {
        NPBL0010CMsg bizMsg = (NPBL0010CMsg) cMsg;
        NPBL0010SMsg globalMsg = (NPBL0010SMsg) sMsg;

        int cnt = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(cnt + i), null);
        }

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            if (bizMsg.A.getValidCount() > 0) {

                // Copy from SMsg to CMsg
                int i = 0;
                for (; i < globalMsg.A.getValidCount(); i++) {
                    if (i == bizMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                bizMsg.A.setValidCount(i);

                // Page break setting
                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            }
        }
    }

    // END 2023/06/22 S.Fujita [QC#61637, ADD]
    /**
     * Pulldown onChange Event
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     */
    private void doProcess_OnChangeSearchOption(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            NPBL0010CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * Download Event
     * @param cMsg NPBL0010CMsg
     * @param sMsg NPBL0010SMsg
     */
    private void doProcess_Download(NPBL0010CMsg cMsg, NPBL0010SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPBL0010Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPBL0010Query.getInstance().getClass());

            // create csv file, parameters
            String ssmId = "";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);
            String glblCmpyCd = getGlobalCompanyCode();

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
            ssmParam.put(DB_PARAM_ROW_NUM, LIMIT_DL_ROWNUM);
            // QC#22345 Start
            ssmParam.put(DB_PARAM_EXPENSE_ORDER_TP, PRCH_REQ_TP.EXPENSE_ORDER);
            ssmParam.put(DB_PARAM_WH_TRANSFER_TP, PRCH_REQ_TP.WH_TRANSFER);
            // QC#22345 End
            // QC#28409
            ssmParam.put(DB_PARAM_VND_TP_CD, VND_TP.OUTBOUND_CARRIER);

            if (cMsg.xxRadioBtn.getValueInt() == SCREEN_CONTROL_VALUE_SEARCH_OP_HEADER) {
                ssmId = "searchHeader";
            } else {
                ssmId = "searchDetail";
            }

            ps = ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Download Event
     * @param cMsg NPBL0010CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NPBL0010CMsg cMsg, ResultSet rs) throws SQLException {

        NPBL0010F00FMsg fMsg = new NPBL0010F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        csvOutFile.writeHeader(CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= NPBL0010Constant.LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum_A1, rs.getString(DB_COLUMN_PRCH_REQ_NUM_FOR_VIEW));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpDescTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqStsDescTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_STS_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqApvlStsDescTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCratDt_A1, rs.getString(DB_COLUMN_PRCH_REQ_CRAT_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt_A1, rs.getString(DB_COLUMN_RQST_RCV_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqSrcTpDescTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.trxRefNum_A1, rs.getString(DB_COLUMN_TRX_REF_NUM));
            // 08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            ZYPEZDItemValueSetter.setValue(fMsg.mrpPlnNm_A1, rs.getString(DB_COLUMN_MRP_PLN_NM));
            // 08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqRqstByPsnCd_A1, rs.getString(DB_COLUMN_PRCH_REQ_RQST_BY_PSN_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_A1, rs.getString(DB_COLUMN_FULL_PSN_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchGrpDescTxt_A1, rs.getString(DB_COLUMN_PRCH_GRP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.shpgSvcLvlDescTxt_A1, rs.getString(DB_COLUMN_SHPG_SVC_LVL_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.carrCd_A1, rs.getString(DB_COLUMN_CARR_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.carrNm_A1, rs.getString(DB_COLUMN_CARR_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndRtrnRsnDescTxt_A1, rs.getString(DB_COLUMN_VND_RTRN_RSN_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlWhCd_A1, rs.getString(DB_COLUMN_SRC_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, rs.getString(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlSwhCd_A1, rs.getString(DB_COLUMN_SRC_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_A1, rs.getString(DB_COLUMN_RTL_SWH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlWhCd_A1, rs.getString(DB_COLUMN_DEST_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A2, rs.getString(DB_COLUMN_RTL_WH_NM2));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd_A1, rs.getString(DB_COLUMN_DEST_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_A2, rs.getString(DB_COLUMN_RTL_SWH_NM2));
            ZYPEZDItemValueSetter.setValue(fMsg.dplyVndNm_A1, rs.getString(DB_COLUMN_DPLY_VND_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A1, rs.getString(DB_COLUMN_SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm_A1, rs.getString(DB_COLUMN_DS_ACCT_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem20Txt_A1, rs.getString(DB_COLUMN_LINE_NUMBER));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineTpDescTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, rs.getString(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, rs.getString(DB_COLUMN_MDSE_NM));
            if (rs.getString(DB_COLUMN_SVC_CONFIG_MSTR_PK) != null) {
                fMsg.svcConfigMstrPk_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_SVC_CONFIG_MSTR_PK)));
            } else {
                fMsg.svcConfigMstrPk_A1.clear();
            }
            if (rs.getString(DB_COLUMN_PRCH_REQ_DISP_QTY) != null) {
                fMsg.prchReqDispQty_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_PRCH_REQ_DISP_QTY)));
            } else {
                fMsg.prchReqDispQty_A1.clear();
            }

            // START 2021/02/03 F.Deola [QC#58347, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineStsDescTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT));
            // END 2021/02/03 F.Deola [QC#58347, ADD]

            // QC#50203
            if (!PRCH_REQ_TP.VENDOR_RETURN.equals(rs.getString("PRCH_REQ_TP_CD"))) {
                if (ZYPCommonFunc.hasValue(fMsg.mdseCd_A1)) {
                    NLXC001001GetInventoryItemCostBean nlxc001001Bean = NPBL0010CommonLogic.getInvtyItemCost(getGlobalCompanyCode(), fMsg.srcRtlWhCd_A1.getValue(), fMsg.srcRtlSwhCd_A1.getValue(), fMsg.mdseCd_A1.getValue(),
                            fMsg.prchReqDispQty_A1.getValue());
                    if (nlxc001001Bean.getErrList().isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(fMsg.entDealNetUnitPrcAmt_A1, nlxc001001Bean.getUnitPrcAmt());
                        if (ZYPCommonFunc.hasValue(fMsg.prchReqDispQty_A1) && ZYPCommonFunc.hasValue(fMsg.entDealNetUnitPrcAmt_A1)) {
                            ZYPEZDItemValueSetter.setValue(fMsg.entPoDtlDealNetAmt_A1, fMsg.entDealNetUnitPrcAmt_A1.getValue().multiply(ZYPCommonFunc.getBigDecimal(fMsg.prchReqDispQty_A1, "0")));
                        }
                    }
                }
            } else {
                if (rs.getString(DB_COLUMN_ENT_DEAL_NET_UNIT_PRC_AMT) != null) {
                    fMsg.entDealNetUnitPrcAmt_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_ENT_DEAL_NET_UNIT_PRC_AMT)));
                } else {
                    fMsg.entDealNetUnitPrcAmt_A1.clear();
                }
                if (rs.getString(DB_COLUMN_ENT_PO_DTL_DEAL_NET_AMT) != null) {
                    fMsg.entPoDtlDealNetAmt_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_ENT_PO_DTL_DEAL_NET_AMT)));
                } else {
                    fMsg.entPoDtlDealNetAmt_A1.clear();
                }
            }

            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd_A1, rs.getString(DB_COLUMN_COA_MDSE_TP_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_A2, rs.getString(DB_COLUMN_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineCmntTxt_A1, rs.getString(DB_COLUMN_PRCH_REQ_LINE_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.coaCmpyCd_A1, rs.getString(DB_COLUMN_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaAfflCd_A1, rs.getString(DB_COLUMN_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaBrCd_A1, rs.getString(DB_COLUMN_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaCcCd_A1, rs.getString(DB_COLUMN_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaAcctCd_A1, rs.getString(DB_COLUMN_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd_A1, rs.getString(DB_COLUMN_COA_PROD_CD2));
            ZYPEZDItemValueSetter.setValue(fMsg.coaChCd_A1, rs.getString(DB_COLUMN_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProjCd_A1, rs.getString(DB_COLUMN_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaExtnCd_A1, rs.getString(DB_COLUMN_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum_A2, rs.getString(DB_COLUMN_PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineNum_A1, rs.getString(DB_COLUMN_PRCH_REQ_LINE_NUM));
            if (rs.getString(DB_COLUMN_PRCH_REQ_LINE_SUB_NUM) != null) {
                fMsg.prchReqLineSubNum_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_PRCH_REQ_LINE_SUB_NUM)));
            } else {
                fMsg.prchReqLineSubNum_A1.clear();
            }
            // serial#
            String serialNumber = rs.getString(DB_COLUMN_SERIAL_NUMBER);
            if (ZYPCommonFunc.hasValue(serialNumber)) {
                int size = serialNumber.getBytes().length;
                if (size > DB_VALUE_MAX_BYTE_SIZE) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDsMultMsgDplyTxt_A1, serialNumber.substring(0, DB_VALUE_MAX_BYTE_SIZE));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDsMultMsgDplyTxt_A1, serialNumber);
                }
            }
            // charge account
            String chargeAccount = rs.getString(DB_COLUMN_CHARGE_ACCOUNT);
            if (ZYPCommonFunc.hasValue(chargeAccount)) {
                int size = chargeAccount.getBytes().length;
                if (size > DB_VALUE_MAX_BYTE_SIZE) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDsMultMsgDplyTxt_A2, chargeAccount.substring(0, DB_VALUE_MAX_BYTE_SIZE));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDsMultMsgDplyTxt_A2, chargeAccount);
                }
            }
            // QC#22345 Start
            ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum_A1, rs.getString(DB_COLUMN_CPO_ORD_NUM));
            // QC#22345 End

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}

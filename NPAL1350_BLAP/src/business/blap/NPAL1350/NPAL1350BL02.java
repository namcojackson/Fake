/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1350;

import static business.blap.NPAL1350.constant.NPAL1350Constant.CSV_FILE_EXTENSION;
import static business.blap.NPAL1350.constant.NPAL1350Constant.CSV_FILE_NAME;
import static business.blap.NPAL1350.constant.NPAL1350Constant.CSV_HDR;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_CPLT_RTL_SWH_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_DS_WRK_ORD_STS_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_DS_WRK_ORD_TP_DESC_TXT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_BAL_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_CANC_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_MDSE_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_ORD_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_GOODS_RCV_QTY;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_OLD_WRK_ORD_NUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RQST_RCV_DT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RTL_SWH_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_WRK_ORD_DT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_COLUMN_WRK_ORD_NUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.DB_PARAM_WRK_ORD_TP_CD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_CMN_DOWNLOAD;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_CMN_RESET;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_INIT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_PAGE_NEXT;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_PAGE_PREV;
import static business.blap.NPAL1350.constant.NPAL1350Constant.EVENT_NM_NPAL1350_SEARCH;
import static business.blap.NPAL1350.constant.NPAL1350Constant.LIMIT_DL_ROWNUM;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NZZM0000E;
import static business.blap.NPAL1350.constant.NPAL1350Constant.NZZM0001W;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1350.common.NPAL1350CommonLogic;
import business.blap.NPAL1350.constant.NPAL1350Constant;
import business.file.NPAL1350F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1350 Kitting WO Search
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NPAL1350BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1350_INIT.equals(screenAplID)
                    || EVENT_NM_NPAL1350_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1350_INIT((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
            } else if (EVENT_NM_NPAL1350_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
            } else if (EVENT_NM_NPAL1350_ON_CHANGE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_OnChangeSearchOption((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
            } else if (EVENT_NM_NPAL1350_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
            } else if (EVENT_NM_NPAL1350_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
            } else if (EVENT_NM_NPAL1350_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_Download((NPAL1350CMsg) cMsg, (NPAL1350SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_NPAL1350_INIT(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1350CommonLogic.createPullDownSearchOption(cMsg, sMsg, glblCmpyCd);
        NPAL1350CommonLogic.createPullDownWorkOrderType(cMsg, sMsg, glblCmpyCd);
        NPAL1350CommonLogic.createPullDownWorkOrderStatus(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_Search(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPAL1350CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_OnChangeSearchOption(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            NPAL1350CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }

    /**
     * Page Next
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_PageNext(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {

        
        // C -> S Check
        if (cMsg.A.getValidCount() != 0) {
            int nowIndex = cMsg.xxPageShowFromNum.getValueInt();
            int nowLastIndex = cMsg.xxPageShowToNum.getValueInt();
            int q = 0;

            for (int j = nowIndex - 1; j < nowLastIndex; j++) {
                EZDMsg.copy(cMsg.A.no(q), null, sMsg.A.no(j), null);
                q++;
            }
        }
        //ZYPTableUtil.clear(scrnMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        //scrnMsg.xxPageShowToNum.clear();
        
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
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_PagePrev(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {
     // copy data from CMsg onto SMsg
        if (cMsg.A.getValidCount() != 0) {
            int nowIndex = cMsg.xxPageShowFromNum.getValueInt();
            int nowLastIndex = cMsg.xxPageShowToNum.getValueInt();
            int q = 0;

            for (int j = nowIndex - 1; j < nowLastIndex; j++) {
                EZDMsg.copy(cMsg.A.no(q), null, sMsg.A.no(j), null);
                q++;
            }
        }
        
        //ZYPTableUtil.clear(scrnMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();
        
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
     * Download Event
     * @param cMsg NPAL1350CMsg
     * @param sMsg NPAL1350SMsg
     */
    private void doProcess_Download(NPAL1350CMsg cMsg, NPAL1350SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1350Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1350Query.getInstance().getClass());

            // create csv file, parameters
            String ssmId = "search";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);
            String glblCmpyCd = getGlobalCompanyCode();

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_WRK_ORD_TP_CD, WRK_ORD_TP.INTERNAL_KIT);
            ssmParam.put(DB_PARAM_MAX_ROWNUM, LIMIT_DL_ROWNUM);

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
     * @param cMsg NPAL1350CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NPAL1350CMsg cMsg, ResultSet rs) throws SQLException {

        NPAL1350F00FMsg fMsg = new NPAL1350F00FMsg();
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
            if (rs.getRow() >= NPAL1350Constant.LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.wrkOrdNum_A1, rs.getString(DB_COLUMN_WRK_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.dsWrkOrdTpDescTxt_A1, rs.getString(DB_COLUMN_DS_WRK_ORD_TP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.dsWrkOrdStsNm_A1, rs.getString(DB_COLUMN_DS_WRK_ORD_STS_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.wrkOrdDt_A1, rs.getString(DB_COLUMN_WRK_ORD_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt_A1, rs.getString(DB_COLUMN_RQST_RCV_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.fnshGoodsMdseCd_A1, rs.getString(DB_COLUMN_FNSH_GOODS_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.fnshMdseDescShortTxt_A1, rs.getString(DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_A1, rs.getString(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, rs.getString(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.cpltRtlSwhCd_A1, rs.getString(DB_COLUMN_CPLT_RTL_SWH_CD));
            if (rs.getString(DB_COLUMN_FNSH_GOODS_ORD_QTY) != null) {
                fMsg.fnshGoodsOrdQty_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_FNSH_GOODS_ORD_QTY)));
            } else {
                fMsg.fnshGoodsOrdQty_A1.clear();
            }
            if (rs.getString(DB_COLUMN_FNSH_GOODS_RCV_QTY) != null) {
                fMsg.fnshGoodsRcvQty_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_FNSH_GOODS_RCV_QTY)));
            } else {
                fMsg.fnshGoodsRcvQty_A1.clear();
            }
            if (rs.getString(DB_COLUMN_FNSH_GOODS_BAL_QTY) != null) {
                fMsg.fnshGoodsBalQty_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_FNSH_GOODS_BAL_QTY)));
            } else {
                fMsg.fnshGoodsBalQty_A1.clear();
            }
            if (rs.getString(DB_COLUMN_FNSH_GOODS_CANC_QTY) != null) {
                fMsg.fnshGoodsCancQty_A1.setValue(new BigDecimal(rs.getString(DB_COLUMN_FNSH_GOODS_CANC_QTY)));
            } else {
                fMsg.fnshGoodsCancQty_A1.clear();
            }
            ZYPEZDItemValueSetter.setValue(fMsg.oldWrkOrdNum_A1, rs.getString(DB_COLUMN_OLD_WRK_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_A1, rs.getString(DB_COLUMN_RTL_SWH_NM));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1520;

import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520SCRN00_CMN_DOWNLOAD;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520SCRN00_OPEN_SWH;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520SCRN00_OPEN_WH;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_CMN_RESET;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_INIT;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_PAGE_NEXT;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_PAGE_PREV;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SEARCH;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SET_ITEM_DESCRIPTION;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SET_MANAGERNAME;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SET_SOURCE_SUB_WAREHOUSENAME;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SET_SOURCE_WAREHOUSENAME;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SET_SUB_WAREHOUSENAME;
import static business.blap.NPAL1520.constant.NPAL1520Constant.EVENT_NM_NPAL1520_SET_WAREHOUSENAME;
import static business.blap.NPAL1520.constant.NPAL1520Constant.ROWNUM_DOWNLOAD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1520.common.NPAL1520CommonLogic;
import business.blap.NPAL1520.constant.NPAL1520Constant;
import business.file.NPAL1520F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Search Business Process
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 *</pre>
 */
public class NPAL1520BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1520_INIT.equals(screenAplID) || EVENT_NM_NPAL1520_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1520_INIT((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SET_ITEM_DESCRIPTION.equals(screenAplID)) {
                doProcess_SetItemDescription((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SET_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_SetWarehouseName((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SET_SUB_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_SetSubWarehouseName((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SET_MANAGERNAME.equals(screenAplID)) {
                doProcess_SetManagerName((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SET_SOURCE_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_SetSourceWarehouseName((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SET_SOURCE_SUB_WAREHOUSENAME.equals(screenAplID)) {
                doProcess_SetSourceSubWarehouseName((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520_ON_CHANGE_SEARCH_OPTION.equals(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1520_CMN_Download((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520SCRN00_OPEN_WH.equals(screenAplID)) {
                doProcess_NPAL1520_OpenWh((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            } else if (EVENT_NM_NPAL1520SCRN00_OPEN_SWH.equals(screenAplID)) {
                doProcess_NPAL1520_OpenSwh((NPAL1520CMsg) cMsg, (NPAL1520SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
    * Init
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_NPAL1520_INIT(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        // get GlobalCompany Code
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NPAL1520CommonLogic.createPullDownSearchOption(cMsg, sMsg);
        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, cMsg.rtlWhCatgCd_PD, cMsg.rtlWhCatgDescTxt_PD);
        ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, cMsg.procrTpCd_PD, cMsg.procrTpDescTxt_PD);

    }

    /**
    * Set Item Description
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_SetItemDescription(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.setItemDescription(cMsg, sMsg, ZYPConstant.FLG_ON_Y);

    }

    /**
    * Set Warehouse Name
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_SetWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.setWarehouseName(cMsg, sMsg, ZYPConstant.FLG_ON_Y);

 }

    /**
    * Set Sub Warehouse Name
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_SetSubWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.setSubWarehouseName(cMsg, sMsg, ZYPConstant.FLG_ON_Y);

 }

    /**
    * Set Manager Name
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_SetManagerName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.setManagerName(cMsg, sMsg, ZYPConstant.FLG_ON_Y);

    }

    /**
    * Set Source Warehouse Name
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_SetSourceWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.setSourceWarehouseName(cMsg, sMsg, ZYPConstant.FLG_ON_Y);

    }

    /**
    * Set Source Sub Warehouse Name
    * @param cMsg NPAL1520CMsg
    * @param sMsg NPAL1520SMsg
    */
    private void doProcess_SetSourceSubWarehouseName(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.setSourceSubWarehouseName(cMsg, sMsg, ZYPConstant.FLG_ON_Y);

    }

    /**
     * Open Warehouse
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_NPAL1520_OpenWh(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.getRtlWhCatg(cMsg, sMsg);

    }

    /**
     * Open Sub Warehouse
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_NPAL1520_OpenSwh(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        NPAL1520CommonLogic.getRtlWhCatg(cMsg, sMsg);

    }

    /**
     * Search
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_Search(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        // Set Item Description to header
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            NPAL1520CommonLogic.setItemDescription(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        }

        // Set Warehouse Name to header
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            NPAL1520CommonLogic.setWarehouseName(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        }

        // Set Sub Warehouse Name to header
        if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {
            NPAL1520CommonLogic.setSubWarehouseName(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        }

        // Set Manager Name to header
        if (ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd)) {
            NPAL1520CommonLogic.setManagerName(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        }

        // Set Source Warehouse Name to header
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
            NPAL1520CommonLogic.setSourceWarehouseName(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        }

        // Set Source Sub Warehouse Name to header
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
            NPAL1520CommonLogic.setSourceSubWarehouseName(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        }

        NPAL1520CommonLogic.search(cMsg, sMsg);

    }

    /**
     * Page Next
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_PageNext(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {
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

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * Page Prev
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_PagePrev(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {
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

        // set value to pagination items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            NPAL1520CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, getContextUserInfo().getUserId());

        }
    }

    /**
     * doProcess_NPAL1520_CMN_Download
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    private void doProcess_NPAL1520_CMN_Download(NPAL1520CMsg cMsg, NPAL1520SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String glblCmpyCd = getGlobalCompanyCode();

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NPAL1520Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1520Query.getInstance().getClass());

            //create csv file, parameters
            String ssmId = "";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NPAL1520Constant.CSV_FILE_NAME), ".csv");
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
            ssmParam.put(DB_PARAM_ROWNUM, ROWNUM_DOWNLOAD);
            ssmId = "search";

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
     * @param cMsg      NPAL1520CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NPAL1520CMsg cMsg, ResultSet rs) throws SQLException {

        NPAL1520F00FMsg fMsg = new NPAL1520F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(fMsg));

        //write header
        csvOutFile.writeHeader(NPAL1520Constant.CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(fMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= NPAL1520Constant.LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mrpPlnNm_A1, rs.getString("MRP_PLN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgNm_A1, rs.getString("RTL_WH_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_A1, rs.getString("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_A1, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_A1, rs.getString("RTL_SWH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.whMgrPsnCd_A1, rs.getString("WH_MGR_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.fullPsnNm_A1, rs.getString("FULL_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ropQty_A1, rs.getBigDecimal("ROP_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_A1, rs.getBigDecimal("MAX_INVTY_QTY"));
            ZYPEZDItemValueSetter.setValue(fMsg.mrpEnblFlg_A1, rs.getString("MRP_ENBL_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.procrTpNm_A1, rs.getString("PROCR_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlWhCd_A1, rs.getString("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A2, rs.getString("SRC_RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.srcRtlSwhCd_A1, rs.getString("SRC_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm_A2, rs.getString("SRC_RTL_SWH_NM"));
            // START 2022/10/05 M.Kikushima [QC#60560,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.calcOrdProcCd_A1, rs.getString("CALC_ORD_PROC_CD"));
            // END 2022/10/05 M.Kikushima [QC#60560,ADD]

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}
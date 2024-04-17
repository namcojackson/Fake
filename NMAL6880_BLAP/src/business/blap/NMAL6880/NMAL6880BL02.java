/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6880;

import static business.blap.NMAL6880.constant.NMAL6880Constant.CSV_FILE_NAME;
import static business.blap.NMAL6880.constant.NMAL6880Constant.CSV_HDR;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_CMSG;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_ROWNUM;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_SALES_DATE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.DB_PARAM_VND_XREF_TP_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EFF_FROM_DT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EFF_THRU_DT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_ADD_DETAIL_LINE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CANCEL_DETAIL_LINE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_CLEAR;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_COL_CLEAR;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_COL_SAVE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_DOWNLOAD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_CMN_SUBMIT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_INIT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_PAGE_JUMP;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_PAGE_NEXT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_PAGE_PREV;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_SEARCH;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_SEARCH_WH1;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_SEARCH_WH2;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EVENT_NM_NMAL6880_NPAL1010;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EXTN_CSV;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EZUPTIME;
import static business.blap.NMAL6880.constant.NMAL6880Constant.EZUPTIMEZONE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL6880.constant.NMAL6880Constant.MSG_RTL_WH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM0038I;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM0050E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8121E;
import static business.blap.NMAL6880.constant.NMAL6880Constant.NMAM8181W;
import static business.blap.NMAL6880.constant.NMAL6880Constant.RTL_SWH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.RTL_WH_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.RTL_WH_NM;
import static business.blap.NMAL6880.constant.NMAL6880Constant.VND_SHIP_TO_CUST_CD;
import static business.blap.NMAL6880.constant.NMAL6880Constant.VND_SHIP_TO_XREF_PK;
import static business.blap.NMAL6880.constant.NMAL6880Constant.XX_ALL_LINE_ADDR;
import static business.blap.NMAL6880.constant.NMAL6880Constant.XX_CHK_BOX;

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
import business.blap.NMAL6880.common.NMAL6880CommonLogic;
import business.file.NMAL6880F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_XREF_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : search business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 05/23/2016   CITS            K.Ogino         Create          QC#8435
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19864
 *</pre>
 */
public class NMAL6880BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL6880_INIT.equals(screenAplID)) {
                doProcess_NMAL6880_INIT((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
                ZYPGUITableColumn.getColData((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NMAL6880_CMN_Clear((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_SEARCH.equals(screenAplID) || EVENT_NM_NMAL6880_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Search((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_ADD_DETAIL_LINE.equals(screenAplID)) {
                doProcess_AddDetailLine((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_PAGE_JUMP.equals(screenAplID)) {
                doProcess_PageJump((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_CMN_Download((NMAL6880CMsg) cMsg);
            } else if (EVENT_NM_NMAL6880_SEARCH_WH1.equals(screenAplID) || EVENT_NM_NMAL6880_SEARCH_WH2.equals(screenAplID)) {
                doProcess_NMAL6880_SearchWH((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else if (EVENT_NM_NMAL6880_CMN_COL_CLEAR.equals(screenAplID) || EVENT_NM_NMAL6880_CMN_COL_SAVE.equals(screenAplID) || EVENT_NM_NMAL6880_CANCEL_DETAIL_LINE.equals(screenAplID)) {
                return;
            } else if (EVENT_NM_NMAL6880_NPAL1010.equals(screenAplID)) {
                doProcess_NMAL6880_SearchWH((NMAL6880CMsg) cMsg, (NMAL6880SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_NMAL6880_INIT(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        cMsg.clear();
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * >> Event
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_NMAL6880_SearchWH(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // find WH Master
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        int selectIdx = Integer.valueOf(cMsg.xxPopPrm_PD.getValue());
        if (selectIdx > -1) {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(selectIdx).rtlWhCd_A1.getValue());
        } else {
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        }
        ssmParam.put(DB_PARAM_SALES_DATE, salesDate);

        S21SsmEZDResult result = NMAL6880Query.getInstance().findRtlWh(ssmParam);

        if (result.isCodeNormal()) {
            Map<String, Object> map = (Map<String, Object>) result.getResultObject();
            if (selectIdx > -1) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectIdx).rtlWhNm_A1, (String) map.get(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectIdx).xxAllLineAddr_A1, (String) map.get(XX_ALL_LINE_ADDR));
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) map.get(RTL_WH_NM));
            }
        } else {
            if (selectIdx > -1) {
                cMsg.A.no(selectIdx).rtlWhCd_A1.setErrorInfo(1, NMAM8121E, new String[] {MSG_RTL_WH_CD });
            } else {
                cMsg.rtlWhCd.setErrorInfo(1, NMAM8121E, new String[] {MSG_RTL_WH_CD });
            }
        }
    }

    /**
     * Reset
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_NMAL6880_CMN_Clear(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        cMsg.clear();
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Search
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_Search(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_VND_XREF_TP_CD, VND_XREF_TP.CUSA_TO_CSA);
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);

        S21SsmEZDResult result = NMAL6880Query.getInstance().search(ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();
            for (int i = 0; i < queryResCnt; i++) {
                Map<String, Object> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).vndShipToCustCd_A1, (String) map.get(VND_SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCd_A1, (String) map.get(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, (String) map.get(RTL_WH_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxAllLineAddr_A1, (String) map.get(XX_ALL_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhCd_A1, (String) map.get(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).effFromDt_A1, (String) map.get(EFF_FROM_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).effThruDt_A1, (String) map.get(EFF_THRU_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).vndShipToXrefPk_A1, (BigDecimal) map.get(VND_SHIP_TO_XREF_PK));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_A1, (String) map.get(EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_A1, (String) map.get(EZUPTIMEZONE));
            }
            sMsg.A.setValidCount(queryResCnt);

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

    }

    /**
     * Add Line
     * @param cMsg NMAL6880CMsg
     * @param sMsg NMAL6880SMsg
     */
    private void doProcess_AddDetailLine(NMAL6880CMsg cMsg, NMAL6880SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM0050E, new String[] {String.format("%03d", sMsg.A.length()) });
            return;
        }

        NMAL6880CommonLogic.updateGlblMsg(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsg.A.getValidCount()).effFromDt_A1, salesDate);
        cMsg.xxPageShowFromNum.setValue(sMsg.A.getValidCount());
        sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
        NMAL6880CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);

    }

    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_PageJump(NMAL6880CMsg bizMsg, NMAL6880SMsg glblMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        }

        // copy data from glblMsg onto bizMsg
        NMAL6880CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        bizMsg.xxPageShowFromNum.setValue((bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NMAL6880CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_PageNext(NMAL6880CMsg bizMsg, NMAL6880SMsg glblMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        }

        NMAL6880CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL6880CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_PagePrev(NMAL6880CMsg bizMsg, NMAL6880SMsg glblMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        }

        NMAL6880CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL6880CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);

    }

    /**
     * CSV Download event
     * @param bizMsg NPAL1280CMsg
     */
    private void doProcess_CMN_Download(NMAL6880CMsg bizMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6880Query.getInstance().getClass());

            // create csv file, parameters
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_VND_XREF_TP_CD, VND_XREF_TP.CUSA_TO_CSA);
            ssmParam.put(DB_PARAM_CMSG, bizMsg);
            ssmParam.put(DB_PARAM_ROWNUM, MAX_DOWNLOAD_CNT);

            ps = ssmLLClient.createPreparedStatement("findVendorShipToXref", ssmParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Download Event
     * @param cMsg NMAL6880CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NMAL6880CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL6880F00FMsg fMsg = new NMAL6880F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        fMsg.addExclusionItem(XX_CHK_BOX);

        // write header
        csvOutFile.writeHeader(CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo(NMAM0038I, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= MAX_DOWNLOAD_CNT) {
                cMsg.setMessageInfo(NMAM8181W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.vndShipToCustCd, rs.getString(VND_SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd, rs.getString(RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString(RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr, rs.getString(XX_ALL_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd, rs.getString(RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ST, ZYPDateUtil.formatEzd8ToDisp(rs.getString(EFF_FROM_DT)));
            if (ZYPCommonFunc.hasValue(rs.getString(EFF_THRU_DT))) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_EN, ZYPDateUtil.formatEzd8ToDisp(rs.getString(EFF_THRU_DT)));
            }

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1400;

import static business.blap.NPAL1400.constant.NPAL1400Constant.CSV_FILE_EXTENSION;
import static business.blap.NPAL1400.constant.NPAL1400Constant.CSV_FILE_NAME;
import static business.blap.NPAL1400.constant.NPAL1400Constant.CSV_HDR;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_OWNR_TECH_TOC_CD;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_END_DT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_MAIN_UNIT_MDSE_CD;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_MAIN_UNIT_SER_NUM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_ORD_NUM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_ORD_STS_NM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_OTH_COST_AMT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_PRT_USG_COST_AMT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_START_DT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_TOT_COST_AMT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_RMNF_TOT_LBOR_COST_AMT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_TECH_NM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_COLUMN_T_MDL_NM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1400.constant.NPAL1400Constant.DB_PARAM_ROW_NUM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_CMN_DOWNLOAD;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_CMN_RESET;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_INIT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_ON_CHANGE_SEARCH_OPTION;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_PAGE_NEXT;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_PAGE_PREV;
import static business.blap.NPAL1400.constant.NPAL1400Constant.EVENT_NM_NPAL1400_SEARCH;
import static business.blap.NPAL1400.constant.NPAL1400Constant.LIMIT_DL_ROWNUM;
import static business.blap.NPAL1400.constant.NPAL1400Constant.MAX_FETCH_SIZE;
import static business.blap.NPAL1400.constant.NPAL1400Constant.NZZM0000E;
import static business.blap.NPAL1400.constant.NPAL1400Constant.NZZM0001W;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1400.common.NPAL1400CommonLogic;
import business.file.NPAL1400F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            S.Tanikawa      Create          N/A
 * 08/24/2016   CITS            T.Gotoda        Update          QC#13290
 * 03/06/2017   CITS            K.Kameoka       Update          QC#17826
 *</pre>
 */

public class NPAL1400BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPAL1400_INIT.equals(screenAplID) || EVENT_NM_NPAL1400_CMN_RESET.equals(screenAplID)) {
                doProcess_NPAL1400_INIT((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
                ZYPGUITableColumn.getColData((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
            } else if (EVENT_NM_NPAL1400_ON_CHANGE_SEARCH_OPTION.endsWith(screenAplID)) {
                doProcess_OnChange_SearchOption((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
            } else if (EVENT_NM_NPAL1400_SEARCH.equals(screenAplID)) {
                doProcess_Search((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
            } else if (EVENT_NM_NPAL1400_PAGE_NEXT.equals(screenAplID)) {
                doProcess_PageNext((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
            } else if (EVENT_NM_NPAL1400_PAGE_PREV.equals(screenAplID)) {
                doProcess_PagePrev((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
            } else if (EVENT_NM_NPAL1400_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_Downlaod((NPAL1400CMsg) cMsg, (NPAL1400SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     */
    private void doProcess_NPAL1400_INIT(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg) {

        // get Global Company Code
        String glblCmpyCd = getGlobalCompanyCode();

        // get User ID
        String userId = getContextUserInfo().getUserId();

        NPAL1400CommonLogic.createPullDownSearchOption(cMsg, sMsg, glblCmpyCd, userId);
        NPAL1400CommonLogic.createPullDownRemanOrderStatus(cMsg, sMsg, glblCmpyCd);

        cMsg.aftDeclPntDigitNum.setValue(NPAL1400CommonLogic.getAftDeclPnt(getGlobalCompanyCode()));
    }

    /**
     * Pulldown onChange Event
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     */
    private void doProcess_OnChange_SearchOption(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            // get Global Company Code
            String glblCmpyCd = getGlobalCompanyCode();

            // get User ID
            String userId = getContextUserInfo().getUserId();

            NPAL1400CommonLogic.callNszc0330forSearchSearchOption(cMsg, sMsg, userId, glblCmpyCd);
        }

    }

    /**
     * Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Search(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // Search
        NPAL1400CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Page Next
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     */
    private void doProcess_PageNext(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg) {
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
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     */
    private void doProcess_PagePrev(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg) {
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
     * Downlaod
     * @param cMsg NPAL1400CMsg
     * @param sMsg NPAL1400SMsg
     */
    private void doProcess_Downlaod(NPAL1400CMsg cMsg, NPAL1400SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NPAL1400Query.getInstance().getClass());
            // create csv file, parameters
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_ROW_NUM, LIMIT_DL_ROWNUM);
            ps = ssmLLClient.createPreparedStatement("search", ssmParam, execParam);
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
     * @param cMsg NPAL1400CMsg
     * @param ResultSet rs
     */
    private void writeCsvFile(NPAL1400CMsg cMsg, ResultSet rs) throws SQLException {

        NPAL1400F00FMsg fMsg = new NPAL1400F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        fMsg.addExclusionItem("rmnfOrdNum_A1");
        fMsg.addExclusionItem("ownrTechTocCd_A1");
        fMsg.addExclusionItem("techNm_A1");
        fMsg.addExclusionItem("rmnfStartDt_A1");
        fMsg.addExclusionItem("rmnfEndDt_A1");  //5
        fMsg.addExclusionItem("rmnfMainUnitSerNum_A1");
        // QC#17826 Start
        // fMsg.addExclusionItem("mktMdlCd_A1");   //7
        fMsg.addExclusionItem("t_MdlNm_A1");   //7
        // QC#17826 End
        fMsg.addExclusionItem("rmnfMainUnitMdseCd_A1");
        fMsg.addExclusionItem("mdseDescShortTxt_A1");
        fMsg.addExclusionItem("rmnfOrdStsDescTxt_A1");
        fMsg.addExclusionItem("rmnfPrtUsgCostAmt_A1");
        fMsg.addExclusionItem("rmnfTotLborCostAmt_A1");
        fMsg.addExclusionItem("rmnfOthCostAmt_A1");
        fMsg.addExclusionItem("rmnfTotCostAmt_A1");

        // write header
        csvOutFile.writeHeader(CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfOrdNum, rs.getString(DB_COLUMN_RMNF_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.ownrTechTocCd, rs.getString(DB_COLUMN_OWNR_TECH_TOC_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.techNm, rs.getString(DB_COLUMN_TECH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfStartDt, rs.getString(DB_COLUMN_RMNF_START_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfEndDt, rs.getString(DB_COLUMN_RMNF_END_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfMainUnitSerNum, rs.getString(DB_COLUMN_RMNF_MAIN_UNIT_SER_NUM));
// QC#17826 Start
            // ZYPEZDItemValueSetter.setValue(fMsg.mktMdlCd, rs.getString(DB_COLUMN_MKT_MDL_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString(DB_COLUMN_T_MDL_NM));
// QC#17826 End
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfMainUnitMdseCd, rs.getString(DB_COLUMN_RMNF_MAIN_UNIT_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfOrdStsDescTxt, rs.getString(DB_COLUMN_RMNF_ORD_STS_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfPrtUsgCostAmt, rs.getBigDecimal(DB_COLUMN_RMNF_PRT_USG_COST_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfTotLborCostAmt, rs.getBigDecimal(DB_COLUMN_RMNF_TOT_LBOR_COST_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfOthCostAmt, rs.getBigDecimal(DB_COLUMN_RMNF_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.rmnfTotCostAmt, rs.getBigDecimal(DB_COLUMN_RMNF_TOT_COST_AMT));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }
}

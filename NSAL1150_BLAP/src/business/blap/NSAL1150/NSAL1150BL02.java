/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1150;

import static business.blap.NSAL1150.constant.NSAL1150Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1150.common.NSAL1150CommonLogic;
import business.file.NSAL1150F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Watch Used / Allowed
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Kanasaka      Create          N/A
 * 2016/03/30   Hitachi         A.Kohinata      Update          QC#6066
 *</pre>
 */
public class NSAL1150BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1150CMsg cMsg = (NSAL1150CMsg) arg0;
        NSAL1150SMsg sMsg = (NSAL1150SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1150_INIT".equals(screenAplID)) {
                doProcess_NSAL1150_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL1150Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1150Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL1150Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL1150Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSAL1150Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1150Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1150Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1150Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1150Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1150Scrn00_Search(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1150_INIT(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {

        NSAL1150CommonLogic.clearMsg(cMsg, sMsg);
        String gcc = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(gcc));
        NSAL1150CommonLogic.setInitParams(cMsg, sMsg);

        if (NSAL1150CommonLogic.hasSearchCondition(cMsg)) {
            doProcess_NSAL1150Scrn00_Search(cMsg, sMsg);
        }

        NSAL1150CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL1150Scrn00_CMN_Clear(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {
        cMsg.condSqlTxt_CU.clear();
        cMsg.dsAcctNm.clear();
        cMsg.condSqlTxt_CO.clear();

        doProcess_NSAL1150_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL1150Scrn00_CMN_Download(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {

        if (!NSAL1150CommonLogic.hasSearchCondition(cMsg)) {
            cMsg.setMessageInfo(NSAM0017E);
            return;
        }

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + CSV_FILE_NAME), ".csv");

            rs = NSAL1150Query.getInstance().getSearchDataForCSV(stmtSelect, cMsg, DOWNLOAD_LIMIT_CNT + 1);

            NSAL1150F00FMsg fMsg = new NSAL1150F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            //fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

            //csvOutFile.writeHeader(new String[] {"Customer#", "Customer Name", "Contract#", "Active Like Status", "Service Pgm", "Model#", "Serial#", "Machine Active Like Status", "Toner Allotment%", "Days From Last Bill Read",
            //        "BW Volume", "CLR Volume", "BW Allowed", "CLR Allowed", "BW Used", "CLR Used", "BW % Used", "CLR % Used" }, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
            csvOutFile.writeHeader(new String[] {"CONTRACT INFO,,,,,,,,,,USED/ALLOWED INFO,,,,,,,\r\nCustomer#", "Customer Name", "Contract#", "Active Like Status", "Service Pgm", "Model#", "Serial#", "Machine Active Like Status",
                    "Toner Allotment%", "Days From Last Bill Read", "BW Volume", "CLR Volume", "BW Allowed", "CLR Allowed", "BW Used", "CLR Used", "BW % Used", "CLR % Used" });

            int outputCount = 0;
            while (rs.next()) {
                if (rs.getRow() > DOWNLOAD_LIMIT_CNT) {
                    cMsg.setMessageInfo(NZZM0001W, null);
                    break;
                }

                setValue(fMsg.shipToCustAcctCd_A, rs.getString("SHIP_TO_CUST_ACCT_CD_A"));
                setValue(fMsg.dsAcctNm_A, rs.getString("DS_ACCT_NM_A"));
                setValue(fMsg.dsContrNum_A, rs.getString("DS_CONTR_NUM_A"));
                setValue(fMsg.actvFlg_AC, rs.getString("ACTV_FLG_AC"));
                setValue(fMsg.mdseDescShortTxt_A, rs.getString("MDSE_DESC_SHORT_TXT_A"));
                setValue(fMsg.t_MdlNm_A, rs.getString("T_MDL_NM_A"));
                setValue(fMsg.serNum_A, rs.getString("SER_NUM_A"));
                setValue(fMsg.actvFlg_AM, rs.getString("ACTV_FLG_AM"));
                setValue(fMsg.svcTermAttrbMapValCd_A, rs.getString("SVC_TERM_ATTRB_MAP_VAL_CD_A"));
                setValue(fMsg.fromLastBillDtDaysAot_A, rs.getBigDecimal("BIZ_DAYS_AOT_A"));
                setValue(fMsg.bwAvgMlyCopyCnt_A, rs.getBigDecimal("BW_AVG_MLY_COPY_CNT_A"));
                setValue(fMsg.colorAvgMlyCopyCnt_A, rs.getBigDecimal("COLOR_AVG_MLY_COPY_CNT_A"));
                setValue(fMsg.bwPrrtQty_A, rs.getBigDecimal("BW_PRRT_QTY_A"));
                setValue(fMsg.colorPrrtQty_A, rs.getBigDecimal("COLOR_PRRT_QTY_A"));
                setValue(fMsg.bwUsedQty_A, rs.getBigDecimal("BW_USED_QTY_A"));
                setValue(fMsg.colorUsedQty_A, rs.getBigDecimal("COLOR_USED_QTY_A"));
                setValue(fMsg.splyAllocPct_AB, rs.getBigDecimal("SPLY_ALLOC_PCT_AB"));
                setValue(fMsg.splyAllocPct_AC, rs.getBigDecimal("SPLY_ALLOC_PCT_AC"));

                //setValue(fMsg.xxTpCd_A, rs.getString("XX_TP_CD_A"));

                csvOutFile.write();

                outputCount++;
            }
            if (outputCount == 0) {
                cMsg.setMessageInfo(NSBM0123I, null);
            }

            csvOutFile.close();
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    private void doProcess_NSAL1150Scrn00_PageNext(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1150CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1150CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1150Scrn00_PagePrev(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1150CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1150CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1150Scrn00_Search(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {

        if (NSAL1150CommonLogic.hasSearchCondition(cMsg)) {
            NSAL1150CommonLogic.getSearchData(cMsg, sMsg);

            NSAL1150CommonLogic.pagenation(cMsg, sMsg, 0);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            cMsg.setMessageInfo(NSAM0017E);
        }

    }

}

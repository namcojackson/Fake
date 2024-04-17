/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0260;

import static business.blap.NSBL0260.constant.NSBL0260Constant.COLUMN_NAME_MDL_NM;
import static business.blap.NSBL0260.constant.NSBL0260Constant.COLUMN_NAME_SER_NUM;
import static business.blap.NSBL0260.constant.NSBL0260Constant.DOWNLOAD_FILE_NAME;
import static business.blap.NSBL0260.constant.NSBL0260Constant.LIMIT_DOWNLOAD;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0007E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0058E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0154E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NZZM0000E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NZZM0001W;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NZZM0002I;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SELECT_POPUP_SER_LINE;
import static business.blap.NSBL0260.constant.NSBL0260Constant.TABLE_NAME_MDL_NM;
import static business.blap.NSBL0260.constant.NSBL0260Constant.TABLE_NAME_SVC_MACH_MSTR;
import static business.blap.NSBL0260.constant.NSBL0260Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0260.common.NSBL0260CommonLogic;
import business.file.NSBL0260F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 * EOL Exception Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/08/01   Hitachi         Y.Osawa         Update          QC#12242
 * 2016/12/05   Hitachi         N.Arai          Create          QC#14204
 * 2017/02/06   Hitachi         N.Arai          Update          QC#17197
 *</pre>
 */
public class NSBL0260BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSBL0260_INIT".equals(screenAplID)) {
                doProcess_NSBL0260_INIT((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0260_Search((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_PageNext((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_PagePrev((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_InsertRow((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_DeleteRow((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_SelectAll((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_UnselectAll((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0260_Search((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_Download((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_Reset((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_TBLColumnSort((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else if ("NSBL0260_NSAL1240".equals(screenAplID)) {
                doProcess_NSBL0260Scrn00_NSBL0260_NSAL1240((NSBL0260CMsg) cMsg, (NSBL0260SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSBL0260CMsg
     */
    private void doProcess_NSBL0260_INIT(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        doProcess_NSBL0260_Search(cMsg, sMsg);
    }

    /**
     * do process (Search)
     * @param cMsg NSBL0260CMsg
     */
    private void doProcess_NSBL0260_Search(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);
        cMsg.setCommitSMsg(true);
        // get Detail Data and Set SMsg
        getSearchData(cMsg, sMsg);
        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (Page Next)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_PageNext(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        boolean errorFlg = false;
        int curr = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0260_ASMsg asMsg = (NSBL0260_ASMsg) sMsg.A.no(i);
            if (!NSBL0260CommonLogic.isCheckExistMdlNm(getGlobalCompanyCode(), asMsg)) {
                sMsg.A.no(i).mdlNm_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_MDL_NM, TABLE_NAME_MDL_NM });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).mdlNm_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_MDL_NM, TABLE_NAME_MDL_NM });
                }
                errorFlg = true;
            }
            if (!NSBL0260CommonLogic.isCheckExistSvcMachMstr(getGlobalCompanyCode(), asMsg)) {
                sMsg.A.no(i).serNum_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_SER_NUM, TABLE_NAME_SVC_MACH_MSTR });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).serNum_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_SER_NUM, TABLE_NAME_SVC_MACH_MSTR });
                }
                errorFlg = true;
            }
        }
        if (errorFlg) {
            return;
        }

        int rowIndex = NSBL0260CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt() + 1);
        NSBL0260CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_PagePrev(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        boolean errorFlg = false;
        int curr = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSBL0260_ASMsg asMsg = (NSBL0260_ASMsg) sMsg.A.no(i);
            if (!NSBL0260CommonLogic.isCheckExistMdlNm(getGlobalCompanyCode(), asMsg)) {
                sMsg.A.no(i).mdlNm_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_MDL_NM, TABLE_NAME_MDL_NM });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).mdlNm_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_MDL_NM, TABLE_NAME_MDL_NM });
                }
                errorFlg = true;
            }
            if (!NSBL0260CommonLogic.isCheckExistSvcMachMstr(getGlobalCompanyCode(), asMsg)) {
                sMsg.A.no(i).serNum_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_SER_NUM, TABLE_NAME_SVC_MACH_MSTR });
                curr = NSBL0260CommonLogic.getCurrLine(cMsg, sMsg, i);
                if (curr >= 0) {
                    cMsg.A.no(curr).serNum_A.setErrorInfo(1, NSBM0154E, new String[] {COLUMN_NAME_SER_NUM, TABLE_NAME_SVC_MACH_MSTR });
                }
                errorFlg = true;
            }
        }
        if (errorFlg) {
            return;
        }

        int rowIndex = NSBL0260CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt() - 1);
        NSBL0260CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (SelectAll)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_SelectAll(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {

        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
        }
        int rowIndex = NSBL0260CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt());
        NSBL0260CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (UnselectAll)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_UnselectAll(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {

        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
        int rowIndex = NSBL0260CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt());
        NSBL0260CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (InsertRow)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_InsertRow(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        int currentRowCount = sMsg.A.getValidCount();
        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NSBM0058E, new String[] {"Model", String.valueOf(sMsg.A.length()) });
            return;
        }
        NSBL0260CommonLogic.setEmptyRecord(getGlobalCompanyCode(), cMsg, sMsg, currentRowCount);

        int rowsPerPage = cMsg.A.length();
        int lastPage = NSBL0260CommonLogic.getLastPageNum(cMsg, sMsg);
        int pagenationFrom = NSBL0260CommonLogic.convertPageNumToFirstRowIndex(rowsPerPage, lastPage);
        NSBL0260CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * do process (DeleteRow)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_DeleteRow(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NSBM0007E);
            return;
        }

        int delCnt = 0;
        for (int index : checkList) {
            if (hasValue(sMsg.A.no(index).dsMdlEolExPk_AN) || hasValue(sMsg.A.no(index).dsMdlEolExPk_AS)) {
                delCnt++;
            }
            if (hasValue(sMsg.A.no(index).dsMdlEolExPk_AN)) {
                setValue(sMsg.D.no(sMsg.D.getValidCount()).dsMdlEolExPk_DN, sMsg.A.no(index).dsMdlEolExPk_AN.getValue());
                setValue(sMsg.D.no(sMsg.D.getValidCount()).ezUpTime_DN, sMsg.A.no(index).ezUpTime_AN.getValue());
                setValue(sMsg.D.no(sMsg.D.getValidCount()).ezUpTimeZone_DN, sMsg.A.no(index).ezUpTimeZone_AN.getValue());
            }
            if (hasValue(sMsg.A.no(index).dsMdlEolExPk_AS)) {
                setValue(sMsg.D.no(sMsg.D.getValidCount()).dsMdlEolExPk_DS, sMsg.A.no(index).dsMdlEolExPk_AS.getValue());
                setValue(sMsg.D.no(sMsg.D.getValidCount()).ezUpTime_DS, sMsg.A.no(index).ezUpTime_AS.getValue());
                setValue(sMsg.D.no(sMsg.D.getValidCount()).ezUpTimeZone_DS, sMsg.A.no(index).ezUpTimeZone_AS.getValue());
            }
            sMsg.D.setValidCount(delCnt);
        }

        ZYPTableUtil.deleteRows(sMsg.A, checkList);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        setCurrentPage(cMsg, sMsg);
        int rowIndex = NSBL0260CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt());
        NSBL0260CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void setCurrentPage(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        int maxCount = sMsg.A.getValidCount();
        int pageCnt = maxCount / cMsg.A.length();
        int modCnt = maxCount % cMsg.A.length();
        if (modCnt > 0) {
            pageCnt = pageCnt + 1;
        }
        if (pageCnt < cMsg.xxPageShowCurNum_A.getValueInt()) {
            setValue(cMsg.xxPageShowCurNum_A, new BigDecimal(pageCnt));
        }
    }

    /**
     * get Search Data List
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void getSearchData(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        sMsg.clear();
        S21SsmEZDResult ssmResult = NSBL0260Query.getInstance().getSearchData(cMsg, sMsg, NSBL0260CommonLogic.getSearchCriteriaCd(cMsg));
        if (ssmResult.isCodeNormal()) {
            setDsMdlEolList(ssmResult, sMsg);
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            setSearchConditionsSearchToReset(cMsg);
            // set Paging Data
            NSBL0260CommonLogic.pagenation(cMsg, sMsg, 0);

        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.xxPageShowCurNum_A.clear();
            cMsg.xxPageShowTotNum_A.clear();
        }
    }

    private void setDsMdlEolList(S21SsmEZDResult ssmResult, NSBL0260SMsg sMsg) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
        int maxCnt = 0;
        if (list.size() > sMsg.A.length()) {
            maxCnt = sMsg.A.length();
        } else {
            maxCnt = list.size();
        }
        for (int i = 0; i < maxCnt; i++) {
            Map<String, Object> map = list.get(i);
            setValue(sMsg.A.no(i).glblCmpyCd_A, (String) map.get("GLBL_CMPY_CD"));
            setValue(sMsg.A.no(i).dsMdlEolExPk_AN, (BigDecimal) map.get("DME10_DS_MDL_EOL_EX_PK"));
            setValue(sMsg.A.no(i).dsMdlEolExPk_AS, (BigDecimal) map.get("DME20_DS_MDL_EOL_EX_PK"));
            setValue(sMsg.A.no(i).mdlId_A, (BigDecimal) map.get("MDL_ID"));
            setValue(sMsg.A.no(i).mdlNm_A, (String) map.get("MDL_NM"));
            setValue(sMsg.A.no(i).serNum_A, (String) map.get("SER_NUM"));
            setValue(sMsg.A.no(i).dsMdlEolDt_AN, (String) map.get("DME10_DS_MDL_EOL_DT"));
            setValue(sMsg.A.no(i).dsMdlEolDt_AS, (String) map.get("DME20_DS_MDL_EOL_DT"));
            setValue(sMsg.A.no(i).actvFlg_A, getActiveFlag(sMsg.A.no(i).dsMdlEolDt_AS.getValue()));
            setValue(sMsg.A.no(i).dsAcctNum_A, (String) map.get("DS_ACCT_NUM"));
            setValue(sMsg.A.no(i).svcConfigMstrPk_A, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
            setValue(sMsg.A.no(i).dsAcctNm_A, (String) map.get("DS_ACCT_NM"));
            setValue(sMsg.A.no(i).ctyAddr_A, (String) map.get("CTY_ADDR"));
            setValue(sMsg.A.no(i).stCd_A, (String) map.get("ST_CD"));
            setValue(sMsg.A.no(i).ezUpTime_AN, (String) map.get("DME10_EZUPTIME"));
            setValue(sMsg.A.no(i).ezUpTimeZone_AN, (String) map.get("DME10_EZUPTIMEZONE"));
            setValue(sMsg.A.no(i).ezUpTime_AS, (String) map.get("DME20_EZUPTIME"));
            setValue(sMsg.A.no(i).ezUpTimeZone_AS, (String) map.get("DME20_EZUPTIMEZONE"));
            setValue(sMsg.A.no(i).xxRowNum_A, new BigDecimal(i + 1));
// START 2016/12/05 N.Arai [QC#14204, MOD]
            setValue(sMsg.A.no(i).xxRecHistCratTs_A, (String) map.get("XX_REC_HIST_CRAT_TS"));
            setValue(sMsg.A.no(i).xxRecHistCratByNm_A, (String) map.get("XX_REC_HIST_CRAT_BY_NM"));
            setValue(sMsg.A.no(i).xxRecHistUpdTs_A, (String) map.get("XX_REC_HIST_UPD_TS"));
            setValue(sMsg.A.no(i).xxRecHistUpdByNm_A, (String) map.get("XX_REC_HIST_UPD_BY_NM"));
            setValue(sMsg.A.no(i).xxRecHistTblNm_A, (String) map.get("XX_REC_HIST_TBL_NM"));
// END 2016/12/05 N.Arai [QC#14204, MOD]
        }
        sMsg.A.setValidCount(maxCnt);
    }

    private void setCustomerInfo(S21SsmEZDResult ssmResult, NSBL0260CMsg cMsg, int rowNum) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (list.size() > 0 && rowNum >= 0) {
            Map<String, Object> map = list.get(0);
            setValue(cMsg.A.no(rowNum).dsAcctNum_A, (String) map.get("DS_ACCT_NUM"));
            setValue(cMsg.A.no(rowNum).svcConfigMstrPk_A, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
            setValue(cMsg.A.no(rowNum).dsAcctNm_A, (String) map.get("DS_ACCT_NM"));
            setValue(cMsg.A.no(rowNum).ctyAddr_A, (String) map.get("CTY_ADDR"));
            setValue(cMsg.A.no(rowNum).stCd_A, (String) map.get("ST_CD"));
        }
    }

    private String getActiveFlag(String stopServiceDate) {
        if (!hasValue(stopServiceDate)) {
            return ZYPConstant.FLG_ON_Y;
        }
        String salesDate = ZYPDateUtil.getSalesDate();
        if (hasValue(salesDate) && salesDate.compareTo(stopServiceDate) >= 0) {
            return ZYPConstant.FLG_OFF_N;
        } else {
            return ZYPConstant.FLG_ON_Y;
        }
    }

    /**
     * do process (Download)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_Download(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSBL0260Query nsbl0260Query = NSBL0260Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(nsbl0260Query.getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(DOWNLOAD_FILE_NAME), ".csv");
            Map<String, Object> params = NSBL0260CommonLogic.getSearchCriteriaMap(cMsg, sMsg, NSBL0260CommonLogic.getSearchCriteriaCd(cMsg), LIMIT_DOWNLOAD);
            stmtSelect = ssmLLClient.createPreparedStatement("getDsMdlEol", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSBL0260F00FMsg fMsg = new NSBL0260F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);
            if (!hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(ZZZM9003I, new String[]{"Download"});
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * do process (Reset)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_Reset(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        setSearchConditionsResetToSearch(cMsg);
        doProcess_NSBL0260_INIT(cMsg, sMsg);
    }

    /**
     * do process (TBLColumnSort)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_TBLColumnSort(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        // START 2016/08/01 [QC#12242, MOD]
        NSBL0260CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // END 2016/08/01 [QC#12242, MOD]
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            // START 2016/08/01 [QC#12242, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxRowNum_A.setValue(i + 1);
            }
            // END 2016/08/01 [QC#12242, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set Paging Data
            NSBL0260CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    /**
     * do process (NSBL0260_NSAL1240)
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    private void doProcess_NSBL0260Scrn00_NSBL0260_NSAL1240(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {

        if (SELECT_POPUP_SER_LINE.equals(cMsg.xxPopPrm_SE.getValue()) && cMsg.A.getValidCount() >= cMsg.xxRowNum_SE.getValueInt()) {
            String serNum = cMsg.O.no(0).serNum_O.getValue();
            if (hasValue(serNum)) {
                S21SsmEZDResult ssmResult = NSBL0260Query.getInstance().getCustomerInfo(getGlobalCompanyCode(), serNum);
                if (ssmResult.isCodeNormal()) {
                    setCustomerInfo(ssmResult, cMsg, cMsg.xxRowNum_SE.getValueInt());
                }
            }
        }

    }

    /**
     * Write csv file
     * @param bizMsg NSBL0260CMsg
     * @param globalMsg NSBL0260SMsg
     * @param rs ResultSet
     * @param fMsg NSBL0260F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSBL0260CMsg bizMsg, NSBL0260SMsg globalMsg, ResultSet rs, NSBL0260F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // result set -> fMsg
            setValue(fMsg.mdlNm_A, rs.getString("MDL_NM"));
            setValue(fMsg.serNum_A, rs.getString("SER_NUM"));
            String dsMdlEolDtNoNew = ZYPDateUtil.formatEzd8ToDisp(rs.getString("DME10_DS_MDL_EOL_DT"));
            String dsMdlEolDtStop = ZYPDateUtil.formatEzd8ToDisp(rs.getString("DME20_DS_MDL_EOL_DT"));
            setValue(fMsg.xxDtTxt_AN, dsMdlEolDtNoNew);
            setValue(fMsg.xxDtTxt_AS, dsMdlEolDtStop);
         // START 2017/02/06 N.Arai [QC#17197, MOD]
            // setValue(fMsg.actvFlg_A, getActiveFlag(rs.getString("DME10_DS_MDL_EOL_DT")));
            setValue(fMsg.actvFlg_A, getActiveFlag(rs.getString("DME20_DS_MDL_EOL_DT")));
         // END 2017/02/06 N.Arai [QC#17197, MOD]
            setValue(fMsg.dsAcctNum_A, rs.getString("DS_ACCT_NUM"));
            setValue(fMsg.svcConfigMstrPk_A, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
            setValue(fMsg.dsAcctNm_A, rs.getString("DS_ACCT_NM"));
            setValue(fMsg.ctyAddr_A, rs.getString("CTY_ADDR"));
            setValue(fMsg.stCd_A, rs.getString("ST_CD"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0260F00FMsg fMsg, NSBL0260CMsg cMsg) {
        final String[] csvHeader = new String[] {
                "Model Name"
                , "Serial Number"
                , "No New Contracts After Date"
                , "Stop Service Date"
                , "Serv Flag"
                , "Config#"
                , "Customer#"
                , "Customer Name"
                , "City"
                , "State"};
        csvOutFile.writeHeader(csvHeader);
     }

    private void setSearchConditionsSearchToReset(NSBL0260CMsg cMsg) {
        setValue(cMsg.mdlNm_CR, cMsg.mdlNm_C);
        setValue(cMsg.serNum_CR, cMsg.serNum_C);
        setValue(cMsg.mdlNm_HR, cMsg.mdlNm_H);
        setValue(cMsg.dsAcctNum_HR, cMsg.dsAcctNum_H);
        setValue(cMsg.xxChkBox_NR, cMsg.xxChkBox_ON);
        setValue(cMsg.xxChkBox_FR, cMsg.xxChkBox_OF);
    }

    private void setSearchConditionsResetToSearch(NSBL0260CMsg cMsg) {
        setValue(cMsg.mdlNm_C, cMsg.mdlNm_CR);
        setValue(cMsg.serNum_C, cMsg.serNum_CR);
        setValue(cMsg.mdlNm_H, cMsg.mdlNm_HR);
        setValue(cMsg.dsAcctNum_H, cMsg.dsAcctNum_HR);
        setValue(cMsg.xxChkBox_ON, cMsg.xxChkBox_NR);
        setValue(cMsg.xxChkBox_OF, cMsg.xxChkBox_FR);
    }
}

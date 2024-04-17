/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0960;

import static business.blap.NSAL0960.constant.NSAL0960Constant.DOWNLOAD_FILE_NAME;
import static business.blap.NSAL0960.constant.NSAL0960Constant.LIMIT_DOWNLOAD;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0320E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0454E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0455E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0456E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NZZM0000E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NZZM0001W;
import static business.blap.NSAL0960.constant.NSAL0960Constant.PARAMETER_ARGS_DS_CONTR_VLD_LIST_PK;
import static business.blap.NSAL0960.constant.NSAL0960Constant.SELECT_POPUP_SEARCH;
import static business.blap.NSAL0960.constant.NSAL0960Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0960.common.NSAL0960CommonLogic;
import business.db.DS_CONTR_VLD_CATGTMsg;
import business.db.VLD_ACTTMsg;
import business.file.NSAL0960F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Validation List Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/11/30   Hitachi         K.Kojima        Update          QC#14204
 *</pre>
 */
public class NSAL0960BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0960_INIT".equals(screenAplID)) {
                doProcess_NSAL0960_INIT((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_Search((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_AddLine((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_DeleteLine((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_SearchForSubmit((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_Download((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_Clear((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else if ("NSAL0960Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_TBLColumnSort((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0960CMsg
     */
    private void doProcess_NSAL0960_INIT(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd_H, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cMsg.slsDt_H, ZYPDateUtil.getSalesDate());
        setValue(cMsg.effFromDt_H, ZYPDateUtil.getSalesDate());

        // Create Pulldown List
        createVldActPulldownList(cMsg);
    }

    private static void createVldActPulldownList(NSAL0960CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0960Query.getInstance().getVldActList(cMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        List<Map<String, Object>> lsit = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (lsit.size() == 0) {
            return;
        }

        cMsg.vldActCd_HC.clear();
        cMsg.vldActDescTxt_HC.clear();
        for (int i = 0; i < lsit.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) lsit.get(i);
            cMsg.vldActCd_HC.no(i).setValue((String) map.get("VLD_ACT_CD"));
            cMsg.vldActDescTxt_HC.no(i).setValue((String) map.get("VLD_ACT_DESC_TXT"));
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_Search(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {

        String dsContrVldListPk = cMsg.X.no(PARAMETER_ARGS_DS_CONTR_VLD_LIST_PK).xxComnScrColValTxt.getValue();
        if (SELECT_POPUP_SEARCH.equals(cMsg.xxPopPrm_SE.getValue()) && hasValue(dsContrVldListPk)) {
            sMsg.clear();
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.setCommitSMsg(true);
            // get List
            getData(cMsg, sMsg, new BigDecimal(dsContrVldListPk));
        }
    }

    /**
     * do process (Search for Submit)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_SearchForSubmit(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {

        BigDecimal dsContrVldListPk = cMsg.dsContrVldListPk_H.getValue();
        if (hasValue(dsContrVldListPk)) {
            sMsg.clear();
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.setCommitSMsg(true);
            // get List
            getData(cMsg, sMsg, dsContrVldListPk);
        }
    }

    /**
     * do process (AddLine)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_AddLine(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {
        int addedRowIndex = cMsg.A.getValidCount();
        if (cMsg.A.length() == addedRowIndex) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Contract Validation", String.valueOf(cMsg.A.length()) });
            return;
        }
        if (addedRowIndex > 0) {
            cMsg.A.setValidCount(addedRowIndex + 1);
            int selectedRowIndex = cMsg.xxRadioBtn_A.getValueInt();
            for (int i = cMsg.A.getValidCount() - 2; i > selectedRowIndex; i--) {
                EZDMsg.copy(cMsg.A.no(i), null, cMsg.A.no(i + 1), null);
            }
            cMsg.A.no(selectedRowIndex + 1).clear();
            setValue(cMsg.A.no(selectedRowIndex + 1).glblCmpyCd_A, getGlobalCompanyCode());
            setValue(cMsg.A.no(selectedRowIndex + 1).effFromDt_A, ZYPDateUtil.getSalesDate());
            setValue(cMsg.A.no(selectedRowIndex + 1).xxExstFlg_A, ZYPConstant.FLG_OFF_N);
            // selected add row
            setValue(cMsg.xxRadioBtn_A, new BigDecimal(selectedRowIndex + 1));
        } else {
            cMsg.A.no(0).clear();
            setValue(cMsg.A.no(0).glblCmpyCd_A, getGlobalCompanyCode());
            setValue(cMsg.A.no(0).effFromDt_A, ZYPDateUtil.getSalesDate());
            setValue(cMsg.A.no(0).xxExstFlg_A, ZYPConstant.FLG_OFF_N);
            // selected add row
            setValue(cMsg.xxRadioBtn_A, new BigDecimal(0));
            cMsg.A.setValidCount(1);
        }
    }

    /**
     * do process (DeleteLine)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_DeleteLine(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0456E);
            return;
        }
        int deleteRow = cMsg.xxRadioBtn_A.getValueInt();
        if (deleteRow < 0) {
            cMsg.setMessageInfo(NSAM0454E);
            return;
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(deleteRow).xxExstFlg_A.getValue())) {
            cMsg.setMessageInfo(NSAM0455E);
            return;
        }

        ZYPTableUtil.deleteRows(cMsg.A, Arrays.asList(deleteRow));
    }

    /**
     * do process (download)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_Download(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            if (!hasValue(cMsg.dsContrVldListPk_H)) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0960Query dsbl0960Query = NSAL0960Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(dsbl0960Query.getClass());

            // create csv file
            cMsg.xxFileData_H.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(DOWNLOAD_FILE_NAME), ".csv");
            Map<String, Object> params = setDownloadPram(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrVldList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0960F00FMsg fMsg = new NSAL0960F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_H.getTempFilePath(), fMsg);
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
     * do process (Clear)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_Clear(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {
        doProcess_NSAL0960_INIT(cMsg, sMsg);
    }

    /**
     * do process (TBLColumnSort)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_TBLColumnSort(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(cMsg.A, cMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, cMsg.A.getValidCount());
        }
    }

    /**
     * get Data List
     * @param cMsg NSAL0960CMsg
     * @return Data List
     */
    private void getData(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg, BigDecimal dsContrVldListPk) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0960Query.getInstance().getDsContrVldList(cMsg, dsContrVldListPk);
        if (ssmResult.isCodeNormal()) {
            setDsContrVldList(ssmResult, cMsg);
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * Write csv file
     * @param bizMsg NSAL0960CMsg
     * @param globalMsg NSAL0960SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0960F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0960CMsg bizMsg, NSAL0960SMsg globalMsg, ResultSet rs, NSAL0960F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // result set -> fMsg
            setValue(fMsg.dsContrVldListNm_H, rs.getString("DS_CONTR_VLD_LIST_NM"));
            setValue(fMsg.dsContrVldListDescTxt_H, rs.getString("DS_CONTR_VLD_LIST_DESC_TXT"));
            String effFromDtH = ZYPDateUtil.formatEzd8ToDisp(rs.getString("EFF_FROM_DT"));
            String effToDtH = ZYPDateUtil.formatEzd8ToDisp(rs.getString("EFF_TO_DT"));
            setValue(fMsg.xxDtTxt_HF, effFromDtH);
            setValue(fMsg.xxDtTxt_HT, effToDtH);
            setValue(fMsg.vldSortNum_A, rs.getBigDecimal("VLD_SORT_NUM"));
            DS_CONTR_VLD_CATGTMsg tMsg = (DS_CONTR_VLD_CATGTMsg) ZYPCodeDataUtil.findByCode(DS_CONTR_VLD_CATG.class, rs.getString("GLBL_CMPY_CD"), rs.getString("DS_CONTR_VLD_CATG_CD"));
            if (tMsg != null) {
                setValue(fMsg.dsContrVldCatgDescTxt_A, tMsg.dsContrVldCatgDescTxt);
            }
            setValue(fMsg.dsContrVldNm_A, rs.getString("DS_CONTR_VLD_NM"));
            setValue(fMsg.dsContrVldDescTxt_A, rs.getString("DS_CONTR_VLD_DESC_TXT"));
            VLD_ACTTMsg vldActTMsg = getVldAct(rs.getString("GLBL_CMPY_CD"), rs.getString("VLD_ACT_CD"));
            if (vldActTMsg != null) {
                setValue(fMsg.vldActDescTxt_A, vldActTMsg.vldActDescTxt);
            }
            String effFromDtA = ZYPDateUtil.formatEzd8ToDisp(rs.getString("RELN_EFF_FROM_DT"));
            String effToDtA = ZYPDateUtil.formatEzd8ToDisp(rs.getString("RELN_EFF_TO_DT"));
            setValue(fMsg.xxDtTxt_AF, effFromDtA);
            setValue(fMsg.xxDtTxt_AT, effToDtA);

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private VLD_ACTTMsg getVldAct(String glblCmpyCd, String vldActCd) {
        VLD_ACTTMsg tMsg = new VLD_ACTTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.vldActCd, vldActCd);
        return (VLD_ACTTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0960F00FMsg fMsg, NSAL0960CMsg cMsg) {
       final String[] csvHeader = new String[] {
                 "List Name"
               , "Lsit Description"
               , "List Start Date"
               , "List End Date"
               , "Seq#"
               , "Category Name"
               , "Validation Name"
               , "Description"
               , "Action"
               , "Start Date"
               , "End Date" };
       csvOutFile.writeHeader(csvHeader);
    }

    /**
     * setDownloadPram
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {
        setValue(cMsg.glblCmpyCd_H, getGlobalCompanyCode());
        Map<String, Object> params = NSAL0960CommonLogic.getSearchCriteriaMap(cMsg, cMsg.dsContrVldListPk_H.getValue(), LIMIT_DOWNLOAD);

        return params;
    }

    private void setDsContrVldList(S21SsmEZDResult ssmResult, NSAL0960CMsg cMsg) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        // List Header
        if (list.size() > 0) {
            setValue(cMsg.glblCmpyCd_H, (String) list.get(0).get("GLBL_CMPY_CD"));
            setValue(cMsg.dsContrVldListPk_H, (BigDecimal) list.get(0).get("DS_CONTR_VLD_LIST_PK"));
            setValue(cMsg.dsContrVldListNm_H, (String) list.get(0).get("DS_CONTR_VLD_LIST_NM"));
            setValue(cMsg.dsContrVldListDescTxt_H, (String) list.get(0).get("DS_CONTR_VLD_LIST_DESC_TXT"));
            setValue(cMsg.effFromDt_H, (String) list.get(0).get("EFF_FROM_DT"));
            setValue(cMsg.effToDt_H, (String) list.get(0).get("EFF_TO_DT"));
            setValue(cMsg.ezUpTime_H, (String) list.get(0).get("EZUPTIME"));
            setValue(cMsg.ezUpTimeZone_H, (String) list.get(0).get("EZUPTIMEZONE"));
            // START 2016/11/30 K.Kojima [QC#14204,ADD]
            setValue(cMsg.xxRecHistCratTs, (String) list.get(0).get("XX_REC_HIST_CRAT_TS"));
            setValue(cMsg.xxRecHistCratByNm, (String) list.get(0).get("XX_REC_HIST_CRAT_BY_NM"));
            setValue(cMsg.xxRecHistUpdTs, (String) list.get(0).get("XX_REC_HIST_UPD_TS"));
            setValue(cMsg.xxRecHistUpdByNm, (String) list.get(0).get("XX_REC_HIST_UPD_BY_NM"));
            setValue(cMsg.xxRecHistTblNm, (String) list.get(0).get("XX_REC_HIST_TBL_NM"));
            // END 2016/11/30 K.Kojima [QC#14204,ADD]

        }
        // List Detail
        int maxCnt = 0;
        if (list.size() > cMsg.A.length()) {
            maxCnt = cMsg.A.length();
        } else {
            maxCnt = list.size();
        }
        for (int i = 0; i < maxCnt; i++) {
            Map<String, Object> map = list.get(i);
            setValue(cMsg.A.no(i).glblCmpyCd_A, (String) map.get("GLBL_CMPY_CD"));
            setValue(cMsg.A.no(i).dsContrVldPk_A, (BigDecimal) map.get("DS_CONTR_VLD_PK"));
            setValue(cMsg.A.no(i).vldSortNum_A, (BigDecimal) map.get("VLD_SORT_NUM"));
            setValue(cMsg.A.no(i).dsContrVldCatgDescTxt_A, (String) map.get("DS_CONTR_VLD_CATG_DESC_TXT"));
            setValue(cMsg.A.no(i).dsContrVldNm_A, (String) map.get("DS_CONTR_VLD_NM"));
            setValue(cMsg.A.no(i).dsContrVldDescTxt_A, (String) map.get("DS_CONTR_VLD_DESC_TXT"));
            setValue(cMsg.A.no(i).vldActCd_AS, (String) map.get("VLD_ACT_CD"));
            setValue(cMsg.A.no(i).effFromDt_A, (String) map.get("RELN_EFF_FROM_DT"));
            setValue(cMsg.A.no(i).effToDt_A, (String) map.get("RELN_EFF_TO_DT"));
            setValue(cMsg.A.no(i).xxExstFlg_A, (String) map.get("XX_EXST_FLG"));
            setValue(cMsg.A.no(i).ezUpTime_A, (String) map.get("RELN_EZUPTIME"));
            setValue(cMsg.A.no(i).ezUpTimeZone_A, (String) map.get("RELN_EZUPTIMEZONE"));
            // START 2016/11/30 K.Kojima [QC#14204,ADD]
            setValue(cMsg.A.no(i).xxRecHistCratTs_A, (String) map.get("XX_REC_HIST_CRAT_TS_A"));
            setValue(cMsg.A.no(i).xxRecHistCratByNm_A, (String) map.get("XX_REC_HIST_CRAT_BY_NM_A"));
            setValue(cMsg.A.no(i).xxRecHistUpdTs_A, (String) map.get("XX_REC_HIST_UPD_TS_A"));
            setValue(cMsg.A.no(i).xxRecHistUpdByNm_A, (String) map.get("XX_REC_HIST_UPD_BY_NM_A"));
            setValue(cMsg.A.no(i).xxRecHistTblNm_A, (String) map.get("XX_REC_HIST_TBL_NM_A"));
            // END 2016/11/30 K.Kojima [QC#14204,ADD]
        }
        cMsg.A.setValidCount(maxCnt);
    }
}

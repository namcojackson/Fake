/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0950;

import static business.blap.NSAL0950.constant.NSAL0950Constant.NZZM0000E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.ZZZM9003I;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NZZM0001W;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0320E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0454E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0455E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.LIMIT_DOWNLOAD;
import static business.blap.NSAL0950.constant.NSAL0950Constant.DOWNLOAD_FILE_NAME;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0950.common.NSAL0950CommonLogic;
import business.db.DS_CONTR_VLD_CATGTMsg;
import business.file.NSAL0950F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/11/30   Hitachi         K.Kojima        Update          QC#14204
 *</pre>
 */
public class NSAL0950BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0950_INIT".equals(screenAplID)) {
                doProcess_NSAL0950_INIT((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_Search((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_AddLine((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_DeleteLine((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_OnClickList".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_OnClickList((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_Search((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_Download((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else if ("NSAL0950Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_Clear((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0950CMsg
     */
    private void doProcess_NSAL0950_INIT(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd_S, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cMsg.slsDt_S, ZYPDateUtil.getSalesDate());

        // Create Pulldown List
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_VLD_CATG.class, cMsg.dsContrVldCatgCd_SC, cMsg.dsContrVldCatgDescTxt_SC);

    }

    /**
     * do process (Search)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_Search(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        NSAL0950CommonLogic.clearInputArea(cMsg);
        cMsg.setCommitSMsg(true);
        // get  List
        getData(cMsg, sMsg);
        if (cMsg.A.getValidCount() > 0) {
            NSAL0950CommonLogic.setDetailDsContrVld(cMsg, sMsg, 0);
        }
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(ZZZM9003I, new String[]{"Search"});
        }
    }

    /**
     * do process (AddLine)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_AddLine(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        int addedRowIndex = cMsg.A.getValidCount();
        if (cMsg.A.length() == addedRowIndex) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Contract Validation", String.valueOf(cMsg.A.length()) });
            return;
        }
        cMsg.A.setValidCount(addedRowIndex + 1);
        cMsg.A.no(addedRowIndex).clear();
        setValue(cMsg.A.no(addedRowIndex).glblCmpyCd_A, getGlobalCompanyCode());
        setValue(cMsg.A.no(addedRowIndex).effFromDt_A, ZYPDateUtil.getSalesDate());
        // selected last row
        setValue(cMsg.xxRadioBtn_A, new BigDecimal(addedRowIndex));

        NSAL0950CommonLogic.clearInputArea(cMsg);

        doProcess_NSAL0950Scrn00_OnClickList(cMsg, sMsg);
    }

    /**
     * do process (DeleteLine)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_DeleteLine(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        int deleteRow = cMsg.xxRadioBtn_A.getValueInt();
        if (cMsg.A.getValidCount() == 0 || deleteRow < 0) {
            cMsg.setMessageInfo(NSAM0454E);
            return;
        }
        if (ZYPCommonFunc.hasValue(cMsg.A.no(deleteRow).dsContrVldPk_A)) {
            cMsg.setMessageInfo(NSAM0455E);
            return;
        }
        NSAL0950CommonLogic.clearInputArea(cMsg);

        ZYPTableUtil.deleteRows(cMsg.A, Arrays.asList(deleteRow));

        if (cMsg.A.getValidCount() > 0) {
            NSAL0950CommonLogic.setDetailDsContrVld(cMsg, sMsg, 0);
        }
    }

    /**
     * do process (OnClickList)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_OnClickList(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        int detailDisplayRow = cMsg.xxRadioBtn_A.getValueInt();
        if (cMsg.A.getValidCount() == 0 || detailDisplayRow < 0) {
            cMsg.setMessageInfo(NSAM0454E);
            return;
        }
        NSAL0950CommonLogic.setDetailDsContrVld(cMsg, sMsg, detailDisplayRow);
    }



    /**
     * do process (download)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_Download(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0950Query dsbl0950Query = NSAL0950Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(dsbl0950Query.getClass());

            // create csv file
            cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(DOWNLOAD_FILE_NAME), ".csv");
            Map<String, Object> params = setDownloadPram(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrVldList", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0950F00FMsg fMsg = new NSAL0950F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);
            if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(ZZZM9003I, new String[]{"Download"});
            }
            if (cMsg.A.getValidCount() > 0) {
                NSAL0950CommonLogic.setDetailDsContrVld(cMsg, sMsg, 0);
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * do process (Clear)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_Clear(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        doProcess_NSAL0950_INIT(cMsg, sMsg);
    }

    /**
     * get  Data List
     * @param cMsg NSAL0950CMsg
     * @return Data List
     */
    private void getData(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0950Query.getInstance().getDsContrVldList(cMsg, sMsg);
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
     * @param bizMsg NSAL0950CMsg
     * @param globalMsg NSAL0950SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0950F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0950CMsg bizMsg, NSAL0950SMsg globalMsg, ResultSet rs, NSAL0950F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // result set -> fMsg
            DS_CONTR_VLD_CATGTMsg tMsg = (DS_CONTR_VLD_CATGTMsg) ZYPCodeDataUtil.findByCode(DS_CONTR_VLD_CATG.class, rs.getString("GLBL_CMPY_CD"), rs.getString("DS_CONTR_VLD_CATG_CD"));
            if (tMsg != null) {
                setValue(fMsg.dsContrVldCatgDescTxt_AS, tMsg.dsContrVldCatgDescTxt);
            }
            setValue(fMsg.dsContrVldNm_A,  rs.getString("DS_CONTR_VLD_NM"));
            setValue(fMsg.dsContrVldDescTxt_A,  rs.getString("DS_CONTR_VLD_DESC_TXT"));
            setValue(fMsg.vldCmptTxt_A,  rs.getString("VLD_CMPT_TXT"));
            String effFromDt = ZYPDateUtil.formatEzd8ToDisp(rs.getString("EFF_FROM_DT"));
            String effToDt = ZYPDateUtil.formatEzd8ToDisp(rs.getString("EFF_TO_DT"));
            setValue(fMsg.xxDtTxt_AF, effFromDt);
            setValue(fMsg.xxDtTxt_AT, effToDt);
            setValue(fMsg.vldLvlContrFlg_A,  rs.getString("VLD_LVL_CONTR_FLG"));
            setValue(fMsg.vldLvlMachFlg_A,  rs.getString("VLD_LVL_MACH_FLG"));
            setValue(fMsg.vldAggrFlg_A,  rs.getString("VLD_AGGR_FLG"));
            setValue(fMsg.vldFleetFlg_A,  rs.getString("VLD_FLEET_FLG"));
            setValue(fMsg.vldRegFlg_A,  rs.getString("VLD_REG_FLG"));
            setValue(fMsg.vldWtyFlg_A,  rs.getString("VLD_WTY_FLG"));
            setValue(fMsg.primVldFlg_A,  rs.getString("PRIM_VLD_FLG"));
            setValue(fMsg.ovrdVldRsltAvalFlg_A,  rs.getString("OVRD_VLD_RSLT_AVAL_FLG"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0950F00FMsg fMsg, NSAL0950CMsg cMsg) {
       final String[] csvHeader = new String[] {
               "Category Name"
               , "Validation Name"
               , "Description"
               , "Component Name"
               , "Start Date"
               , "End Date"
               , "Level - Contract"
               , "Level - Machine"
               , "Contact Type - Aggregate"
               , "Contact Type - Fleet"
               , "Contact Type - Non-Fleet"
               , "Contact Type - Warranty"
               , "Primary Validation"
               , "Validation Result Override" };
       csvOutFile.writeHeader(csvHeader);
    }

    /**
     * setDownloadPram
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        setValue(cMsg.glblCmpyCd_S, getGlobalCompanyCode());
        Map<String, Object> params = NSAL0950CommonLogic.getSearchCriteriaMap(cMsg);

        return params;
    }

    private void setDsContrVldList(S21SsmEZDResult ssmResult, NSAL0950CMsg cMsg) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
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
            setValue(cMsg.A.no(i).dsContrVldCatgCd_AS, (String) map.get("DS_CONTR_VLD_CATG_CD"));
            setValue(cMsg.A.no(i).dsContrVldNm_A, (String) map.get("DS_CONTR_VLD_NM"));
            setValue(cMsg.A.no(i).dsContrVldDescTxt_A, (String) map.get("DS_CONTR_VLD_DESC_TXT"));
            setValue(cMsg.A.no(i).vldLvlContrFlg_A, (String) map.get("VLD_LVL_CONTR_FLG"));
            setValue(cMsg.A.no(i).vldLvlMachFlg_A, (String) map.get("VLD_LVL_MACH_FLG"));
            setValue(cMsg.A.no(i).vldAggrFlg_A, (String) map.get("VLD_AGGR_FLG"));
            setValue(cMsg.A.no(i).vldFleetFlg_A, (String) map.get("VLD_FLEET_FLG"));
            setValue(cMsg.A.no(i).vldRegFlg_A, (String) map.get("VLD_REG_FLG"));
            setValue(cMsg.A.no(i).vldWtyFlg_A, (String) map.get("VLD_WTY_FLG"));
            setValue(cMsg.A.no(i).primVldFlg_A, (String) map.get("PRIM_VLD_FLG"));
            setValue(cMsg.A.no(i).ovrdVldRsltAvalFlg_A, (String) map.get("OVRD_VLD_RSLT_AVAL_FLG"));
            setValue(cMsg.A.no(i).effFromDt_A, (String) map.get("EFF_FROM_DT"));
            setValue(cMsg.A.no(i).effToDt_A, (String) map.get("EFF_TO_DT"));
            setValue(cMsg.A.no(i).vldCmptTxt_A, (String) map.get("VLD_CMPT_TXT"));
            setValue(cMsg.A.no(i).ezUpTime_A, (String) map.get("EZUPTIME"));
            setValue(cMsg.A.no(i).ezUpTimeZone_A, (String) map.get("EZUPTIMEZONE"));
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

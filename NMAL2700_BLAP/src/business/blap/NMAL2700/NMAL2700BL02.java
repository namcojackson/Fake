/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2700;

import static business.blap.NMAL2700.constant.NMAL2700Constant.BIZ_AREA_ORG_CD_DBCOLUMN;
import static business.blap.NMAL2700.constant.NMAL2700Constant.BIZ_AREA_ORG_NM_DBCOLUMN;
import static business.blap.NMAL2700.constant.NMAL2700Constant.CSV_FILE_NAME;
import static business.blap.NMAL2700.constant.NMAL2700Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL2700.constant.NMAL2700Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NZZM0000E;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NZZM0001W;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2700.common.NMAL2700CommonLogic;
import business.file.NMAL2700F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GES_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2700BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/08   Fujitsu         M.Suzuki        Update          S21_NA#4304
 * 2016/06/02   Fujitsu         C.Yokoi         Update          CSA-QC#9372
 * 2016/11/02   Fujitsu         M.Ohno          Update          S21_NA#2680
 *</pre>
 */
public class NMAL2700BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2700CMsg bizMsg = (NMAL2700CMsg) cMsg;
            NMAL2700SMsg glblMsg = (NMAL2700SMsg) sMsg;

            if ("NMAL2700_INIT".equals(screenAplID)) {
                doProcess_NMAL2700_INIT(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_DeleteRow_RoleMnt".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_DeleteRow_RoleMnt(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_InsertRow_RoleMnt".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_InsertRow_RoleMnt(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL2700Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2700Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void  doProcess_NMAL2700Scrn00_CMN_Clear(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        doProcess_NMAL2700_INIT(bizMsg, glblMsg);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700_INIT(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.Z);
        ZYPTableUtil.clear(bizMsg.Z);

        // 2016/06/02 CSA-QC#9372 Mod Start
        // Business Area
        S21SsmEZDResult bizAreaOrgPulldownList = NMAL2700Query.getInstance().getBizAreaOrgPulldownList();

        if (bizAreaOrgPulldownList.isCodeNormal()) {
            List<Map<String, Object>> bizAreaOrgList = (List<Map<String, Object>>) bizAreaOrgPulldownList.getResultObject();
            NMAL2700CommonLogic.createPulldownList(bizMsg.firstOrgCd_P, bizMsg.bizAreaOrgNm_P, bizAreaOrgList, new String[] {BIZ_AREA_ORG_CD_DBCOLUMN, BIZ_AREA_ORG_NM_DBCOLUMN });
        }
        // 2016/06/02 CSA-QC#9372 Mod End

        ZYPCodeDataUtil.createPulldownList(GES_TP.class, bizMsg.gesTpCd_P, bizMsg.gesTpDescTxt_P);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700Scrn00_CMN_Download(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2700Query.getInstance().getClass());
            //create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "getRoleMntList";
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
            ssMParam =  NMAL2700Query.getInstance().createSearcRoleMnt(bizMsg);
            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(bizMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void writeCsvFile(NMAL2700CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL2700F00FMsg fMsg = new NMAL2700F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(new String[]{
              "Business Area"
              , "Role Code"
              , "Role name"
              , "Role Description"
              , "active"
              , "Equipment"
              , "Manager"
              , "Specialist"
              , "Commisionable"
              , "Admin"
              , "Golden Eagle Specialist"
              , "Credit/Rebill Approval Limit"
              , "Sales Rep"
              , "Assignment Contract"
              , "Third Party Tech"
              , "Tech Master Request"
              , "SFDC Exclude"
              , "SFDC Profile Name"
              });

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        do {

            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                csvOutFile.close();
                break;
            }

            fMsg.clear();
            //resultSet -> fMsg
            String firstOrgNM = null;
            String firstOrgCd = rs.getString("FIRST_ORG_CD");
            if (ZYPCommonFunc.hasValue(firstOrgCd)) {
                firstOrgNM = ZYPCodeDataUtil.getName(BIZ_AREA_ORG.class, getGlobalCompanyCode(), firstOrgCd);
            }
            String gesNM = null;
            String gesCd = rs.getString("GES");
            if (ZYPCommonFunc.hasValue(gesCd)) {
                gesNM = ZYPCodeDataUtil.getName(GES_TP.class, getGlobalCompanyCode(), gesCd);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.bizAreaOrgDescTxt , firstOrgNM);
            ZYPEZDItemValueSetter.setValue(fMsg.orgFuncRoleTpCd, rs.getString("ROLE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.orgFuncRoleTpNm, rs.getString("ROLE_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.orgFuncRoleTpDescTxt, rs.getString("ROLE_DES"));
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg, rs.getString("ACTIVE"));
            ZYPEZDItemValueSetter.setValue(fMsg.equipFlg, rs.getString("EQUIP"));
            ZYPEZDItemValueSetter.setValue(fMsg.mgrFlg, rs.getString("MGR"));
            ZYPEZDItemValueSetter.setValue(fMsg.spclFlg, rs.getString("SPCL"));
            ZYPEZDItemValueSetter.setValue(fMsg.cmsnFlg, rs.getString("CMSN"));
            ZYPEZDItemValueSetter.setValue(fMsg.adminFlg, rs.getString("ADMIN"));
            ZYPEZDItemValueSetter.setValue(fMsg.gesTpDescTxt, gesNM);
            ZYPEZDItemValueSetter.setValue(fMsg.apvlLimitAmt, rs.getBigDecimal("CREDIT"));
            ZYPEZDItemValueSetter.setValue(fMsg.slsRepFlg, rs.getString("SALES"));
            ZYPEZDItemValueSetter.setValue(fMsg.asgContrFlg, rs.getString("ASG"));
            ZYPEZDItemValueSetter.setValue(fMsg.thirdPtyTechFlg, rs.getString("THIRD"));
            ZYPEZDItemValueSetter.setValue(fMsg.techMstrReqFlg, rs.getString("TECH"));
            ZYPEZDItemValueSetter.setValue(fMsg.crmSlsExclFlg, rs.getString("EXCL"));
            ZYPEZDItemValueSetter.setValue(fMsg.crmSlsPrflTpDescTxt, rs.getString("PRFL"));
            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700Scrn00_CMN_Reset(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        doProcess_NMAL2700_INIT(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700Scrn00_CMN_Submit(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * DeleteRow_RoleMnt Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700Scrn00_DeleteRow_RoleMnt(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        //doNothing
    }

    /**
     * InsertRow_RoleMnt Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700Scrn00_InsertRow_RoleMnt(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        int nextIdx = glblMsg.A.getValidCount();
        glblMsg.A.setValidCount(nextIdx + 1);
        glblMsg.A.no(nextIdx).actvFlg_A.setValue(ZYPConstant.FLG_ON_Y);
        bizMsg.A.setValidCount(nextIdx + 1);
        bizMsg.A.no(nextIdx).actvFlg_A.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2700Scrn00_Search(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(bizMsg.Z);
        ZYPTableUtil.clear(glblMsg.Z);

        //2016/03/08 S21_NA#4304 Mod Start --------------
        //S21SsmEZDResult ssmResult = NMAL2700Query.getInstance().search(bizMsg);
        S21SsmEZDResult ssmResult = NMAL2700Query.getInstance().search(bizMsg, glblMsg);
        //2016/03/08 S21_NA#4304 Mod Start --------------

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
                bizMsg.A.setValidCount(bizMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
                bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).firstOrgCd_A, (String) resultMap.get("FIRST_ORG_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).orgFuncRoleTpCd_A, (String) resultMap.get("ROLE_CD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).orgFuncRoleTpNm_A, (String) resultMap.get("ROLE_NM"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).orgFuncRoleTpDescTxt_A, (String) resultMap.get("ROLE_DES"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).actvFlg_A, (String) resultMap.get("ACTIVE"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).equipFlg_A, (String) resultMap.get("EQUIP"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mgrFlg_A, (String) resultMap.get("MGR"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).spclFlg_A, (String) resultMap.get("SPCL"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).cmsnFlg_A, (String) resultMap.get("CMSN"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).adminFlg_A, (String) resultMap.get("ADMIN"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).gesTpCd_A, (String) resultMap.get("GES"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).apvlLimitAmt_A, (BigDecimal) resultMap.get("CREDIT"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).slsRepFlg_A, (String) resultMap.get("SALES"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).asgContrFlg_A, (String) resultMap.get("ASG"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).thirdPtyTechFlg_A, (String) resultMap.get("THIRD"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).techMstrReqFlg_A, (String) resultMap.get("TECH"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).crmSlsExclFlg_A, (String) resultMap.get("EXCL"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).crmSlsPrflNm_A, (String) resultMap.get("PRFL"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_A, (String) resultMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_A, (String) resultMap.get("EZUPTIMEZONE"));
                // Add Start 2016/11/02 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistCratTs_A, (String) resultMap.get("XX_REC_HIST_CRAT_TS"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistCratByNm_A, ZYPRecHistUtil.getFullNameForRecHist((String) resultMap.get("XX_REC_HIST_CRAT_BY_NM")));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistUpdTs_A, (String) resultMap.get("XX_REC_HIST_UPD_TS"));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistUpdByNm_A, ZYPRecHistUtil.getFullNameForRecHist((String) resultMap.get("XX_REC_HIST_UPD_BY_NM")));
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxRecHistTblNm_A, "ORG_FUNC_ROLE_TP");
                // Add End   2016/11/02 M.Ohno S21_NA#2680
                glblMsg.A.no(i).xxRowId_A.setValue(String.valueOf(i));
                EZDMsg.copy(glblMsg.A.get(i), null, bizMsg.A.get(i), null);
                if (i == glblMsg.A.length() - 1) {
                    break;
                }
            }
        }
    }

}

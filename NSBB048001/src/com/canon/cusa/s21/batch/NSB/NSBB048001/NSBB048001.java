/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB048001;

import static com.canon.cusa.s21.batch.NSB.NSBB048001.constant.NSBB048001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_TSS_ESCLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NSBB048001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         J.Kim           Create          N/A
 * 02/16/2016   Hitachi         A.Kohinata      Update          QC3373
 * </pre>
 */
public class NSBB048001 extends S21BatchMain {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** CSV String */
    private StringBuilder csvValueStr =  new StringBuilder();

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** UPDATE FLG */
    private boolean updateFlg = true;

    /** total Count */
    private int     totalCnt = 0;

    /** Error Count */
    private int     errorCnt  = 0;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }

        // "Sales Date"
        // START 2016/02/16 A.Kohinata [QC3373, MOD]
//        this.slsDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PROC_PGM_ID);
        // END 2016/02/16 A.Kohinata [QC3373, MOD]
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // initialize
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("procPgmId", PROC_PGM_ID);

        String dsBizLastProcTs = (String) ssmBatchClient.queryObject("getDsBizLastProcTs", param);

        if (hasValue(dsBizLastProcTs)) {

            PreparedStatement stmt = null;
            ResultSet rs = null;
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(1000);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("ezinTime", dsBizLastProcTs);
            stesParam.put("procFlg", ZYPConstant.FLG_OFF_N);
            stesParam.put("glblCmpyCd", this.glblCmpyCd);

            try {

                stmt =  this.ssmLLClient.createPreparedStatement("getSvcTssEsclSteInfo", stesParam, execParam);
                rs = stmt.executeQuery();

                int index = 0;
                String tmSvcTssEsclPk = null;
                while (rs.next()) {

                    index++;
                    Map<String, String> mapData = new HashMap<String, String>();
                    mapData.put(SVC_TSS_ESCL_PK, rs.getString(SVC_TSS_ESCL_PK));
                    mapData.put(SVC_TASK_NUM, rs.getString(SVC_TASK_NUM));
                    mapData.put(SVC_TSS_ESCL_DT, rs.getString(SVC_TSS_ESCL_DT));
                    mapData.put(ESCL_TP_TXT, rs.getString(ESCL_TP_TXT));
                    mapData.put(SER_NUM, rs.getString(SER_NUM));
                    mapData.put(MDL_NM, rs.getString(MDL_NM));
                    mapData.put(TECH_NM, rs.getString(TECH_NM));
                    mapData.put(CELL_PHO_NUM, rs.getString(CELL_PHO_NUM));
                    mapData.put(SVC_PBLM_TP_CD, rs.getString(SVC_PBLM_TP_CD));
                    mapData.put(SVC_PBLM_LOC_TP_CD, rs.getString(SVC_PBLM_LOC_TP_CD));
                    mapData.put(SVC_PBLM_RSN_TP_CD, rs.getString(SVC_PBLM_RSN_TP_CD));
                    mapData.put(HELP_DESK_TKT_NUM, rs.getString(HELP_DESK_TKT_NUM));
                    mapData.put(MDSE_CD, rs.getString(MDSE_CD));
                    mapData.put(SVC_CMNT_TXT, rs.getString(SVC_CMNT_TXT));
                    mapData.put(TECH_SPRT_GRP_EML_ADDR, rs.getString(TECH_SPRT_GRP_EML_ADDR));
                    mapList.add(mapData);
                }

                for (int i = 0; i < mapList.size(); i++) {

                    Map<String, String> map = mapList.get(i);
                     String svcTaskNum = map.get(SVC_TASK_NUM);
                     String svcTssEsclDt = map.get(SVC_TSS_ESCL_DT);
                     String svcTssEsclPk = map.get(SVC_TSS_ESCL_PK);
                     String techSprtGrpEmlAddr = map.get(TECH_SPRT_GRP_EML_ADDR);

                     creatCSVData(map);

                     if (i != (mapList.size() - 1)) {
                         tmSvcTssEsclPk = mapList.get(i + 1).get(SVC_TSS_ESCL_PK);
                     }

                     if ((i == (mapList.size() - 1)) || (tmSvcTssEsclPk != null && !svcTssEsclPk.equals(tmSvcTssEsclPk))) {
                         // Create email header
                         String msgTxt = sendTssMail(csvValueStr, techSprtGrpEmlAddr, svcTssEsclDt);
                         this.csvValueStr = new StringBuilder();
                         if (msgTxt == null) {
                             executeUpdateSvcTssEscl(svcTssEsclPk);
                         } else {
                             this.errorCnt++;
                             updateFlg = false;
                             sendErrorMail(svcTssEsclPk, svcTssEsclDt, svcTaskNum, msgTxt);
                         }
                     }
                }

                if (totalCnt != 0 && updateFlg) {
                    doEndProcess();
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        }
    }

    private void creatCSVData(Map<String, String> map) {

        Map<String, Object> attDataParam = new HashMap<String, Object>();
        String svcTssEsclPk = map.get(SVC_TSS_ESCL_PK);
        attDataParam.put("attDataGrpTxt", svcTssEsclPk);
        attDataParam.put("glblCmpyCd", this.glblCmpyCd);
        Map<String, Object> attDataInfo = (Map<String, Object>) ssmBatchClient.queryObject("getAttDataInfo", attDataParam);

        appendDbValueToCsv(map.get(SVC_TSS_ESCL_DT));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(ESCL_TP_TXT));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(SER_NUM));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(MDL_NM));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(TECH_NM));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(CELL_PHO_NUM));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(SVC_PBLM_TP_CD));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(SVC_PBLM_LOC_TP_CD));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(SVC_PBLM_RSN_TP_CD));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(HELP_DESK_TKT_NUM));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(MDSE_CD));
        csvValueStr.append(STR_CNM);
        appendDbValueToCsv(map.get(SVC_CMNT_TXT));
        csvValueStr.append(STR_CNM);
        if (attDataInfo != null) {
            appendDbValueToCsv(attDataInfo.get(ATT_DATA_PK)); // TODO // OMソリューション待ち
        }
        this.csvValueStr.append(STR_CRLF);
    }

    /**
     * Append value to CSV string.
     */
     private void appendDbValueToCsv(Object objValue) {

         String csvValue;

         if (objValue == null) {
             csvValue = EMPTY_STRING;
         } else {
             csvValue = String.valueOf(objValue);
         }

         csvValueStr.append(quote(csvValue));
     }

     /**
      * Escape string including "," character.
      */
     private static String quote(String str) {
         str = nullToEmpty(str);
         if (str.contains(",")) {
             str = "\"" + str + "\"";
         }
         return str;
     }

     /**
      * <pre>
      * Change null value to empty string("").
      * </pre>
      * @return String
      */
     private static String nullToEmpty(String str) {
         if (str == null) {
             return EMPTY_STRING;
         }
         return str;
     }

    private void executeUpdateSvcTssEscl(String svcTssEsclPk) {

        SVC_TSS_ESCLTMsg inSteMsg = new SVC_TSS_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(inSteMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inSteMsg.svcTssEsclPk, new BigDecimal(svcTssEsclPk));
        SVC_TSS_ESCLTMsg updSteTMsg = (SVC_TSS_ESCLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inSteMsg);

        if (updSteTMsg != null) {
            ZYPEZDItemValueSetter.setValue(updSteTMsg.svcTssEsclProcFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(updSteTMsg);
        }
    }

    private void doEndProcess() {

        DS_BIZ_PROC_LOGTMsg inDbplmsg = new DS_BIZ_PROC_LOGTMsg();
        inDbplmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inDbplmsg.setConditionValue("procPgmId01", PROC_PGM_ID);
        inDbplmsg.setSQLID(SQLID);
        DS_BIZ_PROC_LOGTMsgArray findDbplTmsgArray = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inDbplmsg);

        if (findDbplTmsgArray != null) {
            for (int i = 0; i < findDbplTmsgArray.getValidCount(); i++) {

                DS_BIZ_PROC_LOGTMsg findDbplTmsg = (DS_BIZ_PROC_LOGTMsg) findDbplTmsgArray.get(i);

                DS_BIZ_PROC_LOGTMsg upDbplmsg = new DS_BIZ_PROC_LOGTMsg();
                ZYPEZDItemValueSetter.setValue(upDbplmsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(upDbplmsg.dsBizProcLogPk, findDbplTmsg.dsBizProcLogPk);

                DS_BIZ_PROC_LOGTMsg updDbpTMsg = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(upDbplmsg);
                ZYPEZDItemValueSetter.setValue(updDbpTMsg.dsBizLastProcTs, this.currentSystemTs);
                S21FastTBLAccessor.update(updDbpTMsg);
            }
        }
    }

    private String sendTssMail(StringBuilder sb, String techSprtGrpEmlAddr, String svcTssEsclDt) {

        NSBB048001TssMail mail = new NSBB048001TssMail(this.glblCmpyCd);
        String result = mail.sendMail(sb, techSprtGrpEmlAddr, svcTssEsclDt, this.currentSystemTs);
        return result;
    }

    private void sendErrorMail(String svcTssEsclPk, String svcTssEsclDt, String svcTaskNum, String errMsg) throws SQLException {

        this.termCd = TERM_CD.WARNING_END;

        SVC_BAT_ERR_LOGTMsg inParam = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(inParam.bizAppId, BATCH_PROGRAM_ID);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_01, svcTssEsclPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_02, svcTssEsclDt);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_03, svcTaskNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_01, SVC_TSS_ESCL_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_02, SVC_TSS_ESCL_DT);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_03, SVC_TASK_NUM);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrMsgTxt, errMsg);
        S21FastTBLAccessor.insert(inParam);

        // Send error mail
        NSBB048001ErrorMail errorMail = new NSBB048001ErrorMail(this.glblCmpyCd);
        errorMail.sendMail(errMsg);
    }

    @Override
    protected void termRoutine() {
        int normalCnt = this.totalCnt - this.errorCnt;
        setTermState(termCd, normalCnt, this.errorCnt);
    }

    /**
     * 
     * @param args  String[]
     */
    public static void main(String[] args) {
        new NSBB048001().executeBatch(NSBB048001.class.getSimpleName());
    }

}

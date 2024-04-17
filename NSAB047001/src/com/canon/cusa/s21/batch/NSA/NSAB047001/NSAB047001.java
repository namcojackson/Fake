/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB047001;

import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.BASE;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.BASE_CHRG_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.BLLG_MTR_LB_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.CFS_UPLFT_CONTR_DTL_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DEF_MDL_RULE_BASE;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DEF_MDL_RULE_USAGE;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_ACCT_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_BIZ_LAST_PROC_TS;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_BASE_USG_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_BLLG_MTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_DTL_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_DTL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_DTL_TP_SHELL;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.DS_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.FLEET_LINE_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.GLBL_CMPY_CD_01;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.NSAM0031E;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.NSAM0045E;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.NSAM0178E;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.OVERAGE;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.PROC_PGM_ID_01;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.ROW_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.ROW_NUM1;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.SPCL_AGG_MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.SPCL_FLT_MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.TARGET_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPLFT_BASE_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPLFT_BASE_PRC_UP_RATIO;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPLFT_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPLFT_MTR_PRC_UP_RATIO;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPLFT_PRC_METH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPLFT_USG_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.UPTIME;
import static com.canon.cusa.s21.batch.NSA.NSAB047001.constant.NSAB047001Constant.USG_CHRG_FLG;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CFS_CONTR_PRC_UPLFTTMsg;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * NSAB047001:Create CFS Contract Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/28/2016   CITS            Keiichi Masaki  Create          N/A
 * 09/28/2016   Hitachi         N.Arai          Update          QC#12669
 *</pre>
 */

public class NSAB047001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;
    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;
    /** Message List */
    private List<String> msgList = new ArrayList<String>();
    /** Uplift Contract Detail Status Code List */
    private String[] uplftContrDtlStsCdList = null;
    /** DS_BIZ_LAST_PROC_TS */
    private String dsBizLastProcTs = null;
    /** System Date*/
    private String systemDate = null;
    /** System Date Time*/
    private String systemDateTime = null;
    /** System Time Stamp */
    private String systemTimeStamp = null;
    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;
    /** Total Count */
    private int totalCount = 0;
    /** Error Count */
    private int errorCount = 0;
// START 2016/09/30 N.Arai [QC#12669, MOD]
    /** Sales Date */
    private String slsDt = null;
// END 2016/09/30 N.Arai [QC#12669, MOD]

    @Override
    protected void initRoutine() {
        // get S21SsmBatchClient
        this.glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            setErrorInfo(NSAM0045E, new String[] {GLBL_CMPY_CD});
            sendEmail();
            throw new S21AbendException(NSAM0045E, new String[] {GLBL_CMPY_CD });
        }
        // get Variable Character Constant Value
        this.uplftContrDtlStsCdList = ZYPCodeDataUtil.getVarCharConstValue(CFS_UPLFT_CONTR_DTL_STS_CD, glblCmpyCd).split(",");
        // get System Date & Time Stamp
        this.systemDate = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
        this.systemDateTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        this.systemTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

// START 2016/09/30 N.Arai [QC#12669, MOD]
        // "Sales Date"
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E);
        }
// END 2016/09/30 N.Arai [QC#12669, MOD]

    }

    @Override
    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // get DS_BIZ_PROC_LOG
            DS_BIZ_PROC_LOGTMsgArray tMsgArray = new DS_BIZ_PROC_LOGTMsgArray();

            tMsgArray = getDsBizProcLog();

            if (tMsgArray.getValidCount() > 0) {
                this.dsBizLastProcTs = tMsgArray.no(0).dsBizLastProcTs.getValue();
// 2016/09/30 N.Arai [QC#12669, DEL]
                // delete CFS_CONTR_PRC_UPLFT
//                deleteCfsContrPrcUplft();
            }
            // Insert CFS_CONTR_PRC_UPLFT
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(GLBL_CMPY_CD, this.glblCmpyCd);
            paramMap.put(DS_ACCT_DLR_CD, TARGET_DLR_CD);
            paramMap.put(DS_CONTR_DTL_STS_CD, this.uplftContrDtlStsCdList);
            paramMap.put(DS_CONTR_DTL_TP_SHELL, DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);

            if (this.dsBizLastProcTs != null) {
                paramMap.put(DS_BIZ_LAST_PROC_TS, this.dsBizLastProcTs);
            }

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            preparedStatement = ssmLlcClient.createPreparedStatement("getInsetCfsContract", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            BigDecimal dsContrPk = null;
            BigDecimal dsContrDtlPk = null;
// START 2016/09/30 N.Arai [QC#12669, MOD]
            BigDecimal upleftBasePrcUpRatio = ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_BASE, glblCmpyCd);
            BigDecimal upleftMtrPrcUpRatio = ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_USAGE, glblCmpyCd);
// END 2016/09/30 N.Arai [QC#12669, MOD]

            while (resultSet.next()) {

                if (dsContrPk != null && dsContrDtlPk != null) {
                    if (dsContrPk.compareTo(resultSet.getBigDecimal(DS_CONTR_PK)) == 0 && dsContrDtlPk.compareTo(resultSet.getBigDecimal(DS_CONTR_DTL_PK)) == 0) {
                        continue;
                    }
                }

                dsContrPk = resultSet.getBigDecimal(DS_CONTR_PK);
                dsContrDtlPk = resultSet.getBigDecimal(DS_CONTR_DTL_PK);

                // Get Base Info
                Map<String, Object> baseParam = new HashMap<String, Object>();
                baseParam.put(GLBL_CMPY_CD, this.glblCmpyCd);
                baseParam.put(UPLFT_BASE_FLG, ZYPConstant.FLG_ON_Y);
                baseParam.put(DS_CONTR_PK, dsContrPk);
                baseParam.put(DS_CONTR_DTL_PK, dsContrDtlPk);
                baseParam.put(DS_CONTR_BASE_USG_NM, BASE);
                baseParam.put(ROW_NUM, ROW_NUM1);

                List<Map<String, Object>> baseList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getBaseUsageInfo", baseParam);

                if (baseList != null && baseList.size() > 0) {

// START 2016/09/30 N.Arai [QC#12669, MOD]
                    if (UPLFT_PRC_METH.MODEL_PERCENT.equals(resultSet.getString(UPLFT_PRC_METH_CD))){
                        // Fleet
                        if (DS_CONTR_CATG.FLEET.equals(resultSet.getString(DS_CONTR_CATG_CD))) {
                            NSZC048001PMsg fPMsg = callServiceModelApi(ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, glblCmpyCd));
                            if (!S21ApiUtil.isXxMsgId(fPMsg)) {
                                upleftBasePrcUpRatio = setPrcUpRatioForBase(fPMsg.mdlId.getValue(), resultSet);
                            }
                        // Aggregate
                        } else if (DS_CONTR_CATG.AGGREGATE.equals(resultSet.getString(DS_CONTR_CATG_CD))) {
                            NSZC048001PMsg aPMsg = callServiceModelApi(ZYPCodeDataUtil.getVarCharConstValue(SPCL_AGG_MDSE_CD, glblCmpyCd));
                            if (!S21ApiUtil.isXxMsgId(aPMsg)) {
                                upleftBasePrcUpRatio = setPrcUpRatioForBase(aPMsg.mdlId.getValue(), resultSet);
                            }
                        // Non-Fleet
                        } else {
                            upleftBasePrcUpRatio = setPrcUpRatioForNonFleetBase(resultSet);
                        }
                    }
// END 2016/09/30 N.Arai [QC#12669, MOD]
                    for (Map<String, Object> baseRec : baseList) {
                        // Insert Uplift Base
                        CFS_CONTR_PRC_UPLFTTMsg tMsg = new CFS_CONTR_PRC_UPLFTTMsg();

                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(tMsg.cfsContrPrcUplftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CONTR_PRC_UPLFT_SQ));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, resultSet.getString(DS_CONTR_NUM));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, resultSet.getBigDecimal(DS_CONTR_PK));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, resultSet.getBigDecimal(DS_CONTR_DTL_PK));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrModTxt, resultSet.getString(UPTIME));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgCd, resultSet.getString(DS_CONTR_CATG_CD));
                        ZYPEZDItemValueSetter.setValue(tMsg.uplftEffFromDt, resultSet.getString(UPLFT_EFF_FROM_DT));
                        ZYPEZDItemValueSetter.setValue(tMsg.serNum, resultSet.getString(SER_NUM));
                        ZYPEZDItemValueSetter.setValue(tMsg.baseChrgFlg, resultSet.getString(BASE_CHRG_FLG));
                        ZYPEZDItemValueSetter.setValue(tMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, resultSet.getBigDecimal(MDL_ID));
                        ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, resultSet.getString(T_MDL_NM));
// SART 2016/09/30 N.Arai [QC#12669, MOD]
                        if (UPLFT_PRC_METH.MODEL_PERCENT.equals(resultSet.getString(UPLFT_PRC_METH_CD))){
                            ZYPEZDItemValueSetter.setValue(tMsg.prcUpRatio, upleftBasePrcUpRatio);
                        } else {
                            ZYPEZDItemValueSetter.setValue(tMsg.prcUpRatio, (BigDecimal) baseRec.get(UPLFT_BASE_PRC_UP_RATIO));
                        }
                        ZYPEZDItemValueSetter.setValue(tMsg.cfsProcStsCd, CFS_PROC_STS.IN_COMPLETED);
// END 2016/09/30 N.Arai [QC#12669, MOD]
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpCd, resultSet.getString(DS_CONTR_DTL_TP_CD));
                        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, resultSet.getBigDecimal(SVC_MACH_MSTR_PK));
// SART 2016/09/30 N.Arai [QC#12669, MOD]
//                        ZYPEZDItemValueSetter.setValue(tMsg.cfsProcDtTmTs, this.systemDateTime);
// END 2016/09/30 N.Arai [QC#12669, MOD]

                        if (ZYPConstant.FLG_ON_Y.equals(resultSet.getString(FLEET_LINE_FLG))) {
                            ZYPEZDItemValueSetter.setValue(tMsg.fleetSerNum, "FLT_" + resultSet.getString(DS_CONTR_NUM));
                        }

                        EZDTBLAccessor.insert(tMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                            throw new S21AbendException(NSAM0032E, new String[] {tMsg.getTableName()});
                        }
                        this.totalCount++;
                    }
                }
                // Get Usage Info
                paramMap.put(ROW_NUM, ROW_NUM1);
                Map<String, Object> usgParam = new HashMap<String, Object>();
                usgParam.put(GLBL_CMPY_CD, this.glblCmpyCd);
                usgParam.put(UPLFT_USG_FLG, ZYPConstant.FLG_ON_Y);
                usgParam.put(DS_CONTR_PK, dsContrPk);
                usgParam.put(DS_CONTR_DTL_PK, dsContrDtlPk);
                usgParam.put(DS_CONTR_BASE_USG_NM, OVERAGE);
                usgParam.put(ROW_NUM, ROW_NUM1);
                
                List<Map<String, Object>> usgList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getBaseUsageInfo", usgParam);

                if (usgList != null && usgList.size() > 0) {
// START 2016/09/30 N.Arai [QC#12669, MOD]
                    if (UPLFT_PRC_METH.MODEL_PERCENT.equals(resultSet.getString(UPLFT_PRC_METH_CD))){
                        // Fleet
                        if (DS_CONTR_CATG.FLEET.equals(resultSet.getString(DS_CONTR_CATG_CD))) {
                            NSZC048001PMsg fPMsg = callServiceModelApi(ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, glblCmpyCd));
                            if (!S21ApiUtil.isXxMsgId(fPMsg)) {
                                upleftMtrPrcUpRatio = setPrcUpRatioForUsage(fPMsg.mdlId.getValue(), resultSet);
                            }
                        // Aggregate
                        } else if (DS_CONTR_CATG.AGGREGATE.equals(resultSet.getString(DS_CONTR_CATG_CD))) {
                            NSZC048001PMsg aPMsg = callServiceModelApi(ZYPCodeDataUtil.getVarCharConstValue(SPCL_AGG_MDSE_CD, glblCmpyCd));
                            if (!S21ApiUtil.isXxMsgId(aPMsg)) {
                                upleftMtrPrcUpRatio = setPrcUpRatioForUsage(aPMsg.mdlId.getValue(), resultSet);
                            }
                        // Non-Fleet
                        } else {
                            upleftMtrPrcUpRatio = setPrcUpRatioForNonFleetUsage(resultSet);
                        }
                    }
// END 2016/09/30 N.Arai [QC#12669, MOD]
                    for (Map<String, Object> usgRec : usgList) {
                        // Insert Uplift Usage
                        CFS_CONTR_PRC_UPLFTTMsg tMsg = new CFS_CONTR_PRC_UPLFTTMsg();

                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(tMsg.cfsContrPrcUplftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CONTR_PRC_UPLFT_SQ));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, resultSet.getString(DS_CONTR_NUM));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, resultSet.getBigDecimal(DS_CONTR_PK));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, resultSet.getBigDecimal(DS_CONTR_DTL_PK));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrModTxt, resultSet.getString(UPTIME));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgCd, resultSet.getString(DS_CONTR_CATG_CD));
                        ZYPEZDItemValueSetter.setValue(tMsg.uplftEffFromDt, resultSet.getString(UPLFT_EFF_FROM_DT));
                        ZYPEZDItemValueSetter.setValue(tMsg.serNum, resultSet.getString(SER_NUM));
                        ZYPEZDItemValueSetter.setValue(tMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.usgChrgFlg, resultSet.getString(USG_CHRG_FLG));
                        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, resultSet.getBigDecimal(MDL_ID));
                        ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, resultSet.getString(T_MDL_NM));
// START 2016/09/30 N.Arai [QC#12669, MOD]
                        if (UPLFT_PRC_METH.MODEL_PERCENT.equals(resultSet.getString(UPLFT_PRC_METH_CD))){
                            ZYPEZDItemValueSetter.setValue(tMsg.prcUpRatio, upleftMtrPrcUpRatio);
                        } else {
                            ZYPEZDItemValueSetter.setValue(tMsg.prcUpRatio, (BigDecimal) usgRec.get(UPLFT_MTR_PRC_UP_RATIO));
                        }
                        ZYPEZDItemValueSetter.setValue(tMsg.cfsProcStsCd, CFS_PROC_STS.IN_COMPLETED);
// END 2016/09/30 N.Arai [QC#12669, MOD]
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpCd, resultSet.getString(DS_CONTR_DTL_TP_CD));
                        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, resultSet.getBigDecimal(SVC_MACH_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, resultSet.getBigDecimal(DS_CONTR_BLLG_MTR_PK));
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, resultSet.getString(BLLG_MTR_LB_CD));
// START 2016/09/30 N.Arai [QC#12669, MOD]
//                        ZYPEZDItemValueSetter.setValue(tMsg.cfsProcDtTmTs, this.systemDateTime);
// END 2016/09/30 N.Arai [QC#12669, MOD]

                        if (ZYPConstant.FLG_ON_Y.equals(resultSet.getString(FLEET_LINE_FLG))) {
                            ZYPEZDItemValueSetter.setValue(tMsg.fleetSerNum, "FLT_" + resultSet.getString(DS_CONTR_NUM));
                        }

                        EZDTBLAccessor.insert(tMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                            throw new S21AbendException(NSAM0032E, new String[] {tMsg.getTableName()});
                        }
                        this.totalCount++;
                    }
                }
            }
            // Insert or Update DS_BIZ_PROC_LOG
            writeDsBizProcLog(tMsgArray);

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

// SART 2016/09/30 N.Arai [QC#12669, MOD]
    /**
     * setPrcUpRatioForBase
     * @param mdlId
     * @param resultSet
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal setPrcUpRatioForBase(BigDecimal mdlId, ResultSet rs)  throws SQLException {

        if (!ZYPCommonFunc.hasValue(mdlId)){
            return ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_BASE, glblCmpyCd);
        }

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.glblCmpyCd);
        params.put("mdlId", mdlId);
        params.put("upleftEffFromDt", rs.getString(UPLFT_EFF_FROM_DT));
        params.put("rowCnt", ROW_NUM1);

        BigDecimal resPrcUpRatio = (BigDecimal) glSsmBatchClient.queryObject("getDsMdlEsclRuleForBase", params);
        if (ZYPCommonFunc.hasValue(resPrcUpRatio)) {
            return resPrcUpRatio;
        }
        return ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_BASE, glblCmpyCd);
    }

    /**
     * setPrcUpRatioForUsage
     * @param mdlId
     * @param rs
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal setPrcUpRatioForUsage(BigDecimal mdlId, ResultSet rs) throws SQLException {

        if (!ZYPCommonFunc.hasValue(mdlId)){
            return ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_USAGE, glblCmpyCd);
        }

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.glblCmpyCd);
        params.put("mdlId", mdlId);
        params.put("upleftEffFromDt", rs.getString(UPLFT_EFF_FROM_DT));
        params.put("bllgMtrLbCd", rs.getString(BLLG_MTR_LB_CD));
        params.put("rowCnt", ROW_NUM1);

        BigDecimal resPrcUpRatio = (BigDecimal) glSsmBatchClient.queryObject("getPrcUpRatioForUsage", params);
        if (ZYPCommonFunc.hasValue(resPrcUpRatio)) {
            return resPrcUpRatio;
        }
        return ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_USAGE, glblCmpyCd);
    }

    /**
     * callServiceModelApi
     * @param prntMdseCd String
     * @return NSZC048001PMsg
     */
    private NSZC048001PMsg callServiceModelApi(String prntMdseCd) {
        NSZC048001PMsg pMsg = new NSZC048001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, prntMdseCd);

        new NSZC048001().execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage xxMsgId : msgList) {
                setErrorInfo(xxMsgId.getXxMsgid(), xxMsgId.getXxMsgPrmArray());
            }
        }
        return pMsg;
    }

    /**
     * setPrcUpRatioForNonFleetBase
     * @param rs 
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal setPrcUpRatioForNonFleetBase(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.glblCmpyCd);
        params.put("svcMachMstrPk", rs.getString(SVC_MACH_MSTR_PK));
        params.put("upleftEffFromDt", rs.getString(UPLFT_EFF_FROM_DT));
        params.put("rowCnt", ROW_NUM1);

        BigDecimal resPrcUpRatio = (BigDecimal) glSsmBatchClient.queryObject("getDsMdlEsclRuleForNonFleetBase", params);
        if (ZYPCommonFunc.hasValue(resPrcUpRatio)) {
            return resPrcUpRatio;
        }
        return ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_BASE, glblCmpyCd);
    }

    /**
     * setPrcUpRatioForNonFleetUsage
     * @param ssmLLClient 
     * @param rs 
     * @return BigDecimal
     * @throws SQLException 
     */
    private BigDecimal setPrcUpRatioForNonFleetUsage(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.glblCmpyCd);
        params.put("svcMachMstrPk", rs.getString(SVC_MACH_MSTR_PK));
        params.put("upleftEffFromDt", rs.getString(UPLFT_EFF_FROM_DT));
        params.put("bllgMtrLbCd", rs.getString(BLLG_MTR_LB_CD));
        params.put("rowCnt", ROW_NUM1);

        BigDecimal resPrcUpRatio = (BigDecimal) glSsmBatchClient.queryObject("getPrcUpRatioForNonFleetUsage", params);
        if (ZYPCommonFunc.hasValue(resPrcUpRatio)) {
            return resPrcUpRatio;
        }
        return ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_USAGE, glblCmpyCd);
    }
// END 2016/09/30 N.Arai [QC#12669, MOD]

    @Override
    protected void termRoutine() {
        // set Term State
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
    }

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NSAB047001().executeBatch(NSAB047001.class.getSimpleName());
    }

    /**
     * setErrorInfo.
     * @param msgId String.
     * @param params String[].
     */
    private void setErrorInfo(String msgId, String[] params) {

        S21InfoLogOutput.println(msgId, params);
        this.msgList.add(S21MessageFunc.clspGetMessage(msgId, params));
    }

    /**
     * getDsBizProcLog.
     * @return DS_BIZ_PROC_LOGTMsgArray
     */
    private DS_BIZ_PROC_LOGTMsgArray getDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue(GLBL_CMPY_CD_01, this.glblCmpyCd);
        tMsg.setConditionValue(PROC_PGM_ID_01, PROGRAM_ID);

        DS_BIZ_PROC_LOGTMsgArray tMsgArray = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        return tMsgArray;
    }

    /**
     * writeDsBizProcLog.
     * @param tMsgArray DS_BIZ_PROC_LOGTMsgArray.
     */
    private void writeDsBizProcLog(DS_BIZ_PROC_LOGTMsgArray tMsgArray) {

        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            // Insert DS_BIZ_PROC_LOG
            DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, PROGRAM_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, this.systemDate);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, this.systemTimeStamp);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, this.systemTimeStamp);

            EZDTBLAccessor.insert(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0032E, new String[] {tMsg.getTableName() });
            }
        } else {
            // Update DS_BIZ_PROC_LOG
            DS_BIZ_PROC_LOGTMsg tMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgArray.no(0));

            if (tMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0031E, new String[] {tMsgArray.no(0).getTableName() });
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, systemDate);
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, systemTimeStamp);
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, systemTimeStamp);

                EZDTBLAccessor.update(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0031E, new String[] {tMsg.getTableName() });
                }
            }
        }
    }

    /**
     * deleteCfsContrPrcUplft.
     * @param nothing
     */
    private void deleteCfsContrPrcUplft() {
        // Delete CFS Contract 
        Map<String, Object> cfsContractParam = new HashMap<String, Object>();
        cfsContractParam.put(GLBL_CMPY_CD, this.glblCmpyCd);
        cfsContractParam.put(DS_ACCT_DLR_CD, TARGET_DLR_CD);
        cfsContractParam.put(DS_BIZ_LAST_PROC_TS, this.dsBizLastProcTs);

        List<BigDecimal> deleteCfsContractList = (List<BigDecimal>) this.glSsmBatchClient.queryObjectList("getDeleteCfsContract", cfsContractParam);

        for (BigDecimal cfsContrPrcUplftPk : deleteCfsContractList) {
            CFS_CONTR_PRC_UPLFTTMsg tMsg = new CFS_CONTR_PRC_UPLFTTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.cfsContrPrcUplftPk, cfsContrPrcUplftPk);

            EZDTBLAccessor.remove(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()) && !EZDTBLAccessor.RTNCD_NOT_FOUND.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName()});
            }
        }
        // Delete Not CFS Contract
        Map<String, Object> notCfsContractParam = new HashMap<String, Object>();
        notCfsContractParam.put(GLBL_CMPY_CD, this.glblCmpyCd);
        notCfsContractParam.put(DS_ACCT_DLR_CD, TARGET_DLR_CD);

        List<BigDecimal> deleteNotCfsContractList = (List<BigDecimal>) this.glSsmBatchClient.queryObjectList("getDeleteNotCfsContract", notCfsContractParam);

        for (BigDecimal cfsContrPrcUplftPk : deleteNotCfsContractList) {
            CFS_CONTR_PRC_UPLFTTMsg tMsg = new CFS_CONTR_PRC_UPLFTTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.cfsContrPrcUplftPk, cfsContrPrcUplftPk);

            EZDTBLAccessor.remove(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()) && !EZDTBLAccessor.RTNCD_NOT_FOUND.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName()});
            }
        }
    }

    /**
     * sendEmail.
     * @param nothing
     */
    private void sendEmail() {
        // Transaction roll back before Error Info Insert to MAIL_SEND DB
        rollback();

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

                    tmpl.setTemplateParameter("batchId", BUSINESS_ID);
                    tmpl.setTemplateParameter("errDate", errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter("message", msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();

                    // Error Info Insert commit
                    commit();
                }
            }
        }
    }
}

/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB083001;

import static com.canon.cusa.s21.batch.NSA.NSAB083001.constant.NSAB083001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CONTR_ADMIN_PSN_UPD_INFOTMsg;
import business.db.DS_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Contract Branch Rep Update Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/07/09   Hitachi         H.Watanabe      Create          CCI-QC#60080
 *</pre>
 */
public class NSAB083001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** System Time */
    private String sysTs = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Task success count */
    private int infoSccessCnt = 0;

    /** commitLimit */
    private int commitLimit = 0;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB083001().executeBatch(NSAB083001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get the Sales Date.
        // If an error occurs, throw Exception.
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        // Get Commit Number
        this.commitLimit = getCommitCount();
        if (this.commitLimit <= 0 || this.commitLimit >= MAX_COMMIT_LIMIT) {
            this.commitLimit = MAX_COMMIT_LIMIT;
        }

        this.sysTs = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {

        PreparedStatement prcIncrStmt = null;
        ResultSet rsUpdInfo = null;

        try {

            prcIncrStmt = ssmLLClient.createPreparedStatement("getUpdContrInfo", setParamForTargetList(), excParam);
            rsUpdInfo = prcIncrStmt.executeQuery();

            int counter = 0;

            while (rsUpdInfo.next()) {
                this.searchCnt++;
                counter++;

                String resultUpdSts = updateDsContr(rsUpdInfo);
                if (resultUpdSts.equals(UPD_STS_COMPLETE)) {
                    this.infoSccessCnt++;
                }
                if (resultUpdSts.equals(UPD_STS_WARNING)) {
                    this.termCd = TERM_CD.WARNING_END;
                }
                if (resultUpdSts.equals(UPD_STS_ERROR)) {
                    this.termCd = TERM_CD.ABNORMAL_END;
                    break;
                }

                if (counter >= this.commitLimit) {
                    commit();
                    counter = 0;
                }
            }
            if (counter != 0) {
                if(this.termCd.equals(TERM_CD.ABNORMAL_END) == false){
                    commit();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rsUpdInfo);
        }
    }

    private Map<String, Object> setParamForTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("psnUpdInfoProcStsCd", PROC_STS.IN_COMPLETED);
        return paramMap;
    }

    private String updateDsContr(ResultSet rsUpdInfo) throws SQLException {

        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsContrTMsg.ezCancelFlag, "0");
        setValue(dsContrTMsg.dsContrPk, getBigDecimalFromRs(rsUpdInfo, "DS_CONTR_PK"));

        dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dsContrTMsg);

        if (dsContrTMsg == null) {
            updateContrAdminPsnUpdInfo(rsUpdInfo, S21MessageFunc.clspGetMessage(NSAM0045E, new String[] {"Contract" }), PROC_STS.ERROR);
            return UPD_STS_WARNING;
        }
        if (checkAdminPsnAuthority(dsContrTMsg.svcContrBrCd.getValue(), rsUpdInfo.getString("NEW_CONTR_ADMIN_PSN_CD")) == false) {
            updateContrAdminPsnUpdInfo(rsUpdInfo, S21MessageFunc.clspGetMessage(NSAM0762E), PROC_STS.ERROR);
            return UPD_STS_WARNING;
        }

        setValue(dsContrTMsg.contrAdminPsnCd, rsUpdInfo.getString("NEW_CONTR_ADMIN_PSN_CD"));
        setValue(dsContrTMsg.dsContrLastUpdPsnCd, UPD_SYSTEM_NAME);

        S21FastTBLAccessor.update(dsContrTMsg);
        
        if(this.searchCnt > 1000){
            return UPD_STS_ERROR;
        }

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            return UPD_STS_ERROR;
        }

        if (updateContrAdminPsnUpdInfo(rsUpdInfo, S21MessageFunc.clspGetMessage(NZZM0002I), PROC_STS.COMPLEATED) == false) {
            return UPD_STS_ERROR;
        }

        return UPD_STS_COMPLETE;
    }

    private boolean updateContrAdminPsnUpdInfo(ResultSet rsUpdInfo, String vldMsgTxt, String psnUpdInfoProcStsCd) throws SQLException {

        CONTR_ADMIN_PSN_UPD_INFOTMsg contrAdminPsnUpdInfoTMsg = new CONTR_ADMIN_PSN_UPD_INFOTMsg();
        setValue(contrAdminPsnUpdInfoTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(contrAdminPsnUpdInfoTMsg.ezCancelFlag, "0");
        setValue(contrAdminPsnUpdInfoTMsg.contrAdminPsnUpdInfoPk, getBigDecimalFromRs(rsUpdInfo, "CONTR_ADMIN_PSN_UPD_INFO_PK"));

        contrAdminPsnUpdInfoTMsg = (CONTR_ADMIN_PSN_UPD_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(contrAdminPsnUpdInfoTMsg);

        setValue(contrAdminPsnUpdInfoTMsg.psnUpdInfoProcStsCd, psnUpdInfoProcStsCd);
        setValue(contrAdminPsnUpdInfoTMsg.psnUpdInfoUpdTs, this.sysTs);
        setValue(contrAdminPsnUpdInfoTMsg.vldMsgTxt, vldMsgTxt);

        S21FastTBLAccessor.update(contrAdminPsnUpdInfoTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(contrAdminPsnUpdInfoTMsg.getReturnCode()) == false) {
            return false;
        }
        return true;
    }

    private boolean checkAdminPsnAuthority(String svcContrBrCd, String newContrAdminPsnCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcContrBrCd", svcContrBrCd);
        paramMap.put("psnCd", newContrAdminPsnCd);
        paramMap.put("effFromDt", this.slsDt);
        paramMap.put("maxData", MAX_DATA);
        BigDecimal cnt = (BigDecimal) ssmBatchClient.queryObject("getAdminPsnAuthority", paramMap);
        if (cnt.toString().equals("0")) {
            return false;
        }
        return true;
    }

    private BigDecimal getBigDecimalFromRs(ResultSet rs, String keyStr) throws SQLException {
        BigDecimal ret = null;
        ret = rs.getBigDecimal(keyStr);
        return ret;
    }

}

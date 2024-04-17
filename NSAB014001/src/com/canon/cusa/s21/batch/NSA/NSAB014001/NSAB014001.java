/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB014001;

import static com.canon.cusa.s21.batch.NSA.NSAB014001.constant.NSAB014001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINE_MTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Contract Invoice Fetch Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/17/2015   Hitachi         T.Harada        Create
 * 10/26/2016   Hitachi         T.Tomita        Update          QC#15576
 * 10/28/2016   Hitachi         T.Tomita        Update          QC#15576
 * 02/16/2017   Hitachi         N.Arai          Update          QC#17382
 * </pre>
 */
public class NSAB014001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Execution Parameter */
    private S21SsmExecutionParameter ssmExecParam = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB014001().executeBatch(NSAB014001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_SALES_DATE });
        }

        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(this.commitNumber);
        ssmExecParam.setMaxRows(0);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;

    }

    @Override
    protected void mainRoutine() {
        updateServiceInvoice();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private void updateServiceInvoice() {

        S21InfoLogOutput.println("updateServiceInvoice Method Start");

        List<SVC_INVTMsg> svcInvList = new ArrayList<SVC_INVTMsg>();
        List<SVC_INV_LINETMsg> svcInvLineList = new ArrayList<SVC_INV_LINETMsg>();
        List<SVC_INV_LINE_MTRTMsg> svcInvLineMtrList = new ArrayList<SVC_INV_LINE_MTRTMsg>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Map<String, Object> param = setUpdateSvcInvListStmtParam();
            ps = this.ssmLLClient.createPreparedStatement("getUpdateSvcInvList", param, ssmExecParam);
            rs = ps.executeQuery();

            int recordCount = 0;
            int svcInvCount = 0;
            while (rs.next()) {

                recordCount++;

                /** Execute Update */
                if (svcInvCount > 0 && rs.getBigDecimal("INV_RANK").equals(BigDecimal.ONE) && svcInvCount == this.commitNumber) {

                    updateSvcInvDate(svcInvList, svcInvLineList, svcInvLineMtrList);

                    svcInvCount = 0;
                    svcInvList = new ArrayList<SVC_INVTMsg>();
                    svcInvLineList = new ArrayList<SVC_INV_LINETMsg>();
                    svcInvLineMtrList = new ArrayList<SVC_INV_LINE_MTRTMsg>();
                }

                /** Set Update Parameter */
                if (rs.getBigDecimal("INV_RANK").equals(BigDecimal.ONE)) {
                    svcInvList.add(setSvcInvTMsg(rs));
                    svcInvCount++;
                }

                if (rs.getBigDecimal("INV_LINE_RANK").equals(BigDecimal.ONE)) {
                    svcInvLineList.add(setSvcInvLineTMsg(rs));
                }

                if (ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_INV_LINE_MTR_PK"))) {
                    svcInvLineMtrList.add(setSvcInvLineMtrTMsg(rs));
                }

            }
            if (svcInvCount > 0) {
                updateSvcInvDate(svcInvList, svcInvLineList, svcInvLineMtrList);
            }
            if (recordCount == 0) {
                S21InfoLogOutput.println(NSBM0070I);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        S21InfoLogOutput.println("update count is " + this.normalCount + ".");
        S21InfoLogOutput.println("error  count is " + this.errorCount + ".");
        S21InfoLogOutput.println("updateServiceInvoice Method End");
    }

    private Map<String, Object> setUpdateSvcInvListStmtParam() {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
     // START 2017/02/16 N.Arai [QC#17382, DEL]
//        param.put("slsDt", this.salesDate);
        param.put("svcInvOmLinkFlg", ZYPConstant.FLG_ON_Y);
        // del start 2016/10/26 CSA Defect#15576
//        param.put("completed", SVC_INV_FETCH_STS_COMPLETED);
//        param.put("error", SVC_INV_FETCH_STS_ERROR);
        // del end 2016/10/26 CSA Defect#15576
//        param.put("BillToFlg", ZYPConstant.FLG_ON_Y);
//        param.put("ShipToFlg", ZYPConstant.FLG_ON_Y);
     // END 2017/02/16 N.Arai [QC#17382, DEL]
        // add start 2016/10/28 CSA Defect#15576
        SVC_INV_LINETMsg svcInvLineTMsg = new SVC_INV_LINETMsg();
        param.put("svcPgmMdseNmLen", svcInvLineTMsg.getAttr("svcPgmMdseNm").getDigit());
        // add end 2016/10/28 CSA Defect#15576
        return param;

    }

    private SVC_INVTMsg setSvcInvTMsg(ResultSet rs) {
        SVC_INVTMsg tmsg = new SVC_INVTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.svcInvPk, rs.getBigDecimal("SVC_INV_PK"));
            tmsg = (SVC_INVTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.svcContrBrCd, rs.getString("SVC_CONTR_BR_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsAcctDlrDescTxt, rs.getString("DS_ACCT_DLR_DESC_TXT"));
         // START 2017/02/16 N.Arai [QC#17382, MOD]
//            ZYPEZDItemValueSetter.setValue(tmsg.billToAttnTxt, rs.getString("BILL_TO_ATTN_TXT"));
//            ZYPEZDItemValueSetter.setValue(tmsg.shipToAttnTxt, rs.getString("SHIP_TO_ATTN_TXT"));
         // END 2017/02/16 N.Arai [QC#17382, MOD]
            ZYPEZDItemValueSetter.setValue(tmsg.svcInvFetchStsCd, SVC_INV_FETCH_STS_COMPLETED);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    private SVC_INV_LINETMsg setSvcInvLineTMsg(ResultSet rs) {
        SVC_INV_LINETMsg tmsg = new SVC_INV_LINETMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.svcInvLinePk, rs.getBigDecimal("SVC_INV_LINE_PK"));
            tmsg = (SVC_INV_LINETMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.svcPgmMdseNm, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(tmsg.prntSerNum, rs.getString("SER_NUM"));
            // Todo TMSG [prntMachMstrId]?
            ZYPEZDItemValueSetter.setValue(tmsg.prntMachMstrId, rs.getBigDecimal("SVC_MACH_MSTR_PK")); 
            String svcInvEligFlg = null;
            if (!ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK")) && BigDecimal.ZERO.equals(rs.getBigDecimal("INV_LINE_DEAL_SLS_AMT"))) {
                svcInvEligFlg = ZYPConstant.FLG_OFF_N;
            } else {
                svcInvEligFlg = ZYPConstant.FLG_ON_Y;
            }
            ZYPEZDItemValueSetter.setValue(tmsg.svcInvEligFlg, svcInvEligFlg);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    private SVC_INV_LINE_MTRTMsg setSvcInvLineMtrTMsg(ResultSet rs) {
        SVC_INV_LINE_MTRTMsg tmsg = new SVC_INV_LINE_MTRTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.svcInvLineMtrPk, rs.getBigDecimal("SVC_INV_LINE_MTR_PK"));
            tmsg = (SVC_INV_LINE_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.mtrLbCd, rs.getString("MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.prevMtrReadDt, rs.getString("PREV_MTR_READ_DT"));
            ZYPEZDItemValueSetter.setValue(tmsg.mtrReadDt, rs.getString("MTR_READ_DT"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    private void updateSvcInvDate(List<SVC_INVTMsg> svcInvList, List<SVC_INV_LINETMsg> svcInvLineList, List<SVC_INV_LINE_MTRTMsg> svcInvLineMtrList) {

        Boolean errFlg = false;
        int retCount = 0;

        
        SVC_INVTMsg[] inSvcInvArray = new SVC_INVTMsg[svcInvList.size()];
        retCount = S21FastTBLAccessor.update(svcInvList.toArray(inSvcInvArray));
        if (retCount != inSvcInvArray.length) {
            errFlg = true;
        }
        
        SVC_INV_LINETMsg[] inSvcInvLineArray = new SVC_INV_LINETMsg[svcInvLineList.size()];
        retCount = S21FastTBLAccessor.update(svcInvLineList.toArray(inSvcInvLineArray));
        if (retCount != inSvcInvLineArray.length) {
            errFlg = true;
        }

        if (svcInvLineMtrList.size() > 0) {
            SVC_INV_LINE_MTRTMsg[] inSvcInvLineMtrArray = new SVC_INV_LINE_MTRTMsg[svcInvLineMtrList.size()];
            retCount = S21FastTBLAccessor.update(svcInvLineMtrList.toArray(inSvcInvLineMtrArray));
            if (retCount != inSvcInvLineMtrArray.length) {
                errFlg = true;
            }
        }

        if (!errFlg) {
            this.normalCount = this.normalCount + svcInvList.size();
            commit();
        } else {
            this.errorCount = this.errorCount + svcInvList.size();
            rollback();
        }

    }
}

/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB028001;

import static com.canon.cusa.s21.batch.NSA.NSAB028001.constant.NSAB028001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CFS_INV_INTFCTMsg;
import business.db.CFS_INV_INTFCTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * CFS Invoice Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Hitachi         T.Tsuchida      Created         N/A
 * 2016/11/21   Fujitsu         T.Murai         Updated         QC#14957
 * 2017/01/26   Fujitsu         T.Murai         Updated         QC#16956
 * 2017/12/12   Hitachi         K.Kojima        Update          QC#21644
 * 2018/02/13   Hitachi         Y.Takeno        Update          QC#21872
 * 2018/02/14   Hitachi         Y.Takeno        Update          QC#21872-1
 * 2018/02/20   Hitachi         Y.Takeno        Update          QC#21872-1
 * 2018/03/09   Hitachi         Y.Takeno        Update          QC#24679
 * 2018/03/14   Hitachi         Y.Takeno        Update          QC#24679
 * </pre>
 */
public class NSAB028001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** DS Invoice Type : CFS CPC REBILL */
    private String dsInvTpCfsCpcRebill;

    /** DS Invoice Type : CFS CPC CREDIT */
    private String dsInvTpCfsCpcCredit;

    // START 2016/01/26 [QC#16956,ADD]
    /** CFS Billing Type Text List : CFS BLLG TP TXT */
    private List<String> cfsBllgTpTxtList;

    /** CFS Billing Meter Label Name List : CFS BLLG MTR LB NM */
    private List<String> cfsBllgMtrLbNmList;
    // END   2016/01/26 [QC#16956,ADD]

    /** Supplier Code */
    private String splyCd;

    /** Usage Item Code */
    private String usgItemCd;

    /** FTR Item Code */
    private String ftrItemCd;

    /** Total Commit Count */
    private int totalCommitCount;

    /** Error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get VarChar Constant
        this.dsInvTpCfsCpcRebill = ZYPCodeDataUtil.getVarCharConstValue(DS_INV_TP_CFS_CPC_REBILL, this.glblCmpyCd);
        if (!hasValue(this.dsInvTpCfsCpcRebill)) {
            throw new S21AbendException(NSAM0507E);
        }

        this.dsInvTpCfsCpcCredit = ZYPCodeDataUtil.getVarCharConstValue(DS_INV_TP_CFS_CPC_CREDIT, this.glblCmpyCd);
        if (!hasValue(this.dsInvTpCfsCpcCredit)) {
            throw new S21AbendException(NSAM0508E);
        }

        this.splyCd = ZYPCodeDataUtil.getVarCharConstValue(SPLY_CD, this.glblCmpyCd);
        if (!hasValue(this.splyCd)) {
            throw new S21AbendException(NSAM0509E);
        }

        this.usgItemCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd);
        if (!hasValue(this.usgItemCd)) {
            throw new S21AbendException(NSAM0505E);
        }

        this.ftrItemCd = ZYPCodeDataUtil.getVarCharConstValue(FTR_SVC_DUMMY_MDSE_CD, this.glblCmpyCd);
        if (!hasValue(this.ftrItemCd)) {
            throw new S21AbendException(NSAM0506E);
        }

        // START 2017/01/26 [QC$16956]
        this.cfsBllgTpTxtList = getVarCharConstValueList(CFS_BLLG_TP_TXT);
        if (this.cfsBllgTpTxtList.size() == 0) {
            throw new S21AbendException(ZZZM9006E, new String[] {"CFS_BLLG_TP_TXT"});
        }

        this.cfsBllgMtrLbNmList = getVarCharConstValueList(CFS_BLLG_MTR_LB_NM);
        if (this.cfsBllgMtrLbNmList.size() == 0) {
            throw new S21AbendException(ZZZM9006E, new String[] {"CFS_BLLG_MTR_LB_NM"});
        }
        // END   2017/01/26 [QC$16956]

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        checkCfsInvIntfcData("getInputData", setSearchCondition());
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount != 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCommitCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB028001().executeBatch(NSAB028001.class.getSimpleName());
    }

    private void checkCfsInvIntfcData(String sqlId, Map<String, Object> paramMap) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            int procCount = 0;
            int normalCount = 0;

            while (rs.next()) {
                procCount++;
                CFS_INV_INTFCTMsg inTMsg = setCfsInvIntfcTMsg(rs);

                // START 2018/03/09 [QC#24679, MOD]
                // vldCheckCfsInvIntfcData(inTMsg);
                // if (!CFS_INV_PROC_STS.ERROR_RECORD.equals(inTMsg.cfsInvProcStsCd.getValue())) {
                //     setCfsInvIntfcData(inTMsg);
                // }
                boolean vldResult = vldCheckCfsInvIntfcData(inTMsg);
                if (vldResult) {
                    setCfsInvIntfcData(inTMsg);
                }
                // END   2018/03/09 [QC#24679, MOD]
                normalCount += updateCfsInvIntfc(inTMsg);

                if (this.commitNumber == normalCount) {
                    this.errorCount += procCount - normalCount;
                    this.totalCommitCount += normalCount;
                    normalCount = 0;
                    procCount = 0;
                    commit();
                }
            }
            if (procCount != 0) {
                this.errorCount += procCount - normalCount;
                this.totalCommitCount += normalCount;
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private void setErrInfo(CFS_INV_INTFCTMsg inTMsg, String errMsgId) {
        setValue(inTMsg.cfsInvProcStsCd, CFS_INV_PROC_STS.ERROR_RECORD);
        setValue(inTMsg.intfcErrMsgTxt, getRtnMsg(errMsgId));
    }

    private boolean duplicateCheck(String cfsLeasePblNum) {
        CFS_INV_INTFCTMsg inTMsg = new CFS_INV_INTFCTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("cfsLeasePblNum01", cfsLeasePblNum);
        CFS_INV_INTFCTMsgArray tMsgArray = (CFS_INV_INTFCTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (tMsgArray.getValidCount() > 1) {
            return true;
        }
        return false;
    }

    private void setCfsInvIntfcData(CFS_INV_INTFCTMsg inTMsg) {
        Map<String, Object> invDataMap = new HashMap<String, Object>();
        Map<String, Object> contrDataMap = new HashMap<String, Object>();

        if (hasValue(inTMsg.csaInvNum)) {
            invDataMap = getInvData(inTMsg.csaInvNum.getValue(), null);
            if (invDataMap == null) {
                setErrInfo(inTMsg, NSAM0484E);
                return;
            }
            if (hasValue(inTMsg.csaContrNum)) {
                invDataMap = getInvData(inTMsg.csaInvNum.getValue(), inTMsg.csaContrNum.getValue());
                if (invDataMap == null) {
                    setErrInfo(inTMsg, NSAM0486E);
                    return;
                }
                // START 2018/02/13 [QC#21872, MOD]
                // contrDataMap = getContrData(inTMsg.csaContrNum.getValue(), null);
                contrDataMap = getContrData(inTMsg.csaContrNum.getValue(), null, inTMsg.cfsBllgTpTxt.getValue(), inTMsg.bllgMtrLbNm.getValue());
                // END   2018/02/13 [QC#21872, MOD]
                if (contrDataMap == null) {
                    setErrInfo(inTMsg, NSAM0484E);
                    return;
                }
            }
        // START 2018/03/09 [QC#24679, MOD]
        // } else if (hasValue(inTMsg.csaContrNum)) {
        //     if (!existDsContr(inTMsg.csaContrNum.getValue())) {
        //         setErrInfo(inTMsg, NSAM0487E);
        //         return;
        //     }
        } else {
            if (hasValue(inTMsg.csaContrNum) && !existDsContr(inTMsg.csaContrNum.getValue())) {
                setErrInfo(inTMsg, NSAM0487E);
                return;
            }
            if (!hasValue(inTMsg.cfsSerNum)) {
                setErrInfo(inTMsg, NSAM0488E);
                return;
            }

            String bllgPerFromYM = inTMsg.bllgPerFromDt.getValue().substring(0, 6);
            String bllgPerThruYM = inTMsg.bllgPerThruDt.getValue().substring(0, 6);
            String invNum = findInvNum(inTMsg.csaContrNum.getValue(), inTMsg.cfsSerNum.getValue(),
                    bllgPerFromYM, bllgPerThruYM, inTMsg.cfsBllgTpTxt.getValue(), inTMsg.bllgMtrLbNm.getValue());
            if (hasValue(invNum)) {
                // START 2018/03/14 [QC#24679, ADD]
                setValue(inTMsg.csaInvNum, invNum);
                // END   2018/03/14 [QC#24679, ADD]
                invDataMap = getInvData(invNum, inTMsg.csaContrNum.getValue());
            }

            if (invDataMap == null) {
                setErrInfo(inTMsg, NSAM0486E);
                return;
            }
        }
        // END   2018/03/09 [QC#24679, MOD]

        if (hasValue(inTMsg.cfsSerNum)) {
            if (!existSvcMachMstr(inTMsg.cfsSerNum.getValue())) {
                setErrInfo(inTMsg, NSAM0489E);
                return;
            }
            // START 2018/02/13 [QC#21872, MOD]
            // contrDataMap = getContrData(inTMsg.csaContrNum.getValue(), inTMsg.cfsSerNum.getValue());
            contrDataMap = getContrData(inTMsg.csaContrNum.getValue(), inTMsg.cfsSerNum.getValue(), inTMsg.cfsBllgTpTxt.getValue(), inTMsg.bllgMtrLbNm.getValue());
            // END   2018/02/13 [QC#21872, MOD]
            if (contrDataMap == null) {
                setErrInfo(inTMsg, NSAM0490E);
                return;
            }
        } else {
            setErrInfo(inTMsg, NSAM0488E);
            return;
        }

        String[] dsContrStsCdArray = {DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED, DS_CONTR_CTRL_STS.CANCELLED };
        String dsContrStsCd = (String) contrDataMap.get("DS_CONTR_CTRL_STS_CD");
        String dsContrDtlStsCd = (String) contrDataMap.get("DS_CONTR_DTL_CTRL_STS_CD");
        if (Arrays.asList(dsContrStsCdArray).contains(dsContrStsCd)
                || Arrays.asList(dsContrStsCdArray).contains(dsContrDtlStsCd)) {
            setErrInfo(inTMsg, NSAM0493E);
            return;
        }

        setValue(inTMsg.dsContrNum, (String) contrDataMap.get("DS_CONTR_NUM"));
        setValue(inTMsg.dsContrPk, (BigDecimal) contrDataMap.get("DS_CONTR_PK"));
        if (ZYPConstant.FLG_ON_Y.equals(inTMsg.cfsFleetFlg.getValue())) {
            setValue(inTMsg.usgItemCd, this.usgItemCd);
        } else {
            setValue(inTMsg.usgItemCd, (String) contrDataMap.get("USG_ITEM_CD"));
        }
        setValue(inTMsg.svcItemCd, (String) contrDataMap.get("SVC_ITEM_CD"));
        if (ZYPConstant.FLG_ON_Y.equals(inTMsg.ftrFlgOrigTxt.getValue())) {
            if (BASE.equals(inTMsg.cfsBllgTpTxt.getValue())) {
                setValue(inTMsg.ftrItemCd, this.ftrItemCd);
            } else if (USAGE.equals(inTMsg.cfsBllgTpTxt.getValue())) {
                setValue(inTMsg.ftrItemCd, (String) contrDataMap.get("FTR_ITEM_CD"));
            }
        }
        setValue(inTMsg.dfrdAcctgRuleCd, (String) invDataMap.get("DFRD_ACCTG_RULE_CD"));
        if (!hasValue(inTMsg.dfrdAcctgRuleCd)) {
            setValue(inTMsg.dfrdAcctgRuleCd, (String) contrDataMap.get("DFRD_ACCTG_RULE_CD"));
        }
        setValue(inTMsg.dfrdInvRuleCd, (String) contrDataMap.get("DFRD_INV_RULE_CD"));
        //setValue(inTMsg.billToCustAcctCd, (String) invDataMap.get("BILL_TO_CUST_ACCT_CD")); // DEL 2016/11/21 [QC#14957]
        //setValue(inTMsg.billToLocNum, (String) invDataMap.get("BILL_TO_LOC_NUM")); // DEL 2016/11/21 [QC#14957]
        // START 2017/12/11 K.Kojima [QC#21644,MOD]
        // setValue(inTMsg.shipToCustAcctCd, (String) invDataMap.get("SHIP_TO_CUST_ACCT_CD"));
        // setValue(inTMsg.shipToLocNum, (String) invDataMap.get("SHIP_TO_LOC_NUM"));
        if (invDataMap.size() > 0) {
            setValue(inTMsg.shipToCustAcctCd, (String) invDataMap.get("SHIP_TO_CUST_ACCT_CD"));
            setValue(inTMsg.shipToLocNum, (String) invDataMap.get("SHIP_TO_LOC_NUM"));
        } else {
            setValue(inTMsg.shipToCustAcctCd, (String) contrDataMap.get("CUR_LOC_ACCT_NUM"));
            setValue(inTMsg.shipToLocNum, (String) contrDataMap.get("CUR_LOC_NUM"));
        }
        // END 2017/12/11 K.Kojima [QC#21644,MOD]

        setValue(inTMsg.svcLineContrDtlPk, (BigDecimal) contrDataMap.get("SVC_LINE_CONTR_DTL_PK"));
        // START 2018/02/13 [QC#21872, MOD]
        // setValue(inTMsg.usgLineContrDtlPk, (BigDecimal) contrDataMap.get("USG_LINE_CONTR_DTL_PK"));
        if (USAGE.equals(inTMsg.cfsBllgTpTxt.getValue())) {
            setValue(inTMsg.usgLineContrDtlPk, (BigDecimal) contrDataMap.get("USG_LINE_CONTR_DTL_PK"));
        }
        // END   2018/02/13 [QC#21872, MOD]
        // mod start 2016/06/21 CSA #9406
        // START 2017/12/12 K.Kojima [QC#19955,MOD]
        // setValue(inTMsg.uomCd, (String) getUomCD(invDataMap, contrDataMap));
        setValue(inTMsg.uomCd, (String) getUomCD(invDataMap, contrDataMap, inTMsg.cfsBllgTpTxt.getValue()));
        // END 2017/12/12 K.Kojima [QC#19955,MOD]
        // mod end 2016/06/21 CSA #9406
        //setValue(inTMsg.billToCustCd, (String) invDataMap.get("BILL_TO_CUST_CD")); // DEL 2016/11/21 [QC#14957]
        if (hasValue(inTMsg.invTotDealNetAmt)
                && inTMsg.invTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) >= 0) {
            setValue(inTMsg.dsInvTpCd, this.dsInvTpCfsCpcRebill);
        } else {
            setValue(inTMsg.dsInvTpCd, this.dsInvTpCfsCpcCredit);
        }
        setValue(inTMsg.contrVrsnEffFromDt, (String) contrDataMap.get("CONTR_VRSN_EFF_FROM_DT"));
        setValue(inTMsg.cfsInvProcStsCd, CFS_INV_PROC_STS.READY_FOR_INVOICE);
        // START 2016/11/21 [QC#14957, ADD]
        // START 2017/12/11 K.Kojima [QC#19955,MOD]
        // setValue(inTMsg.billToCustCd, (String) invDataMap.get("BILL_TO_CUST_CD"));
        // setValue(inTMsg.billToCustAcctCd, (String) invDataMap.get("SELL_TO_CUST_CD"));
        // setValue(inTMsg.billToLocNum, (String) invDataMap.get("LOC_NUM"));
        if (invDataMap.size() > 0) {
            setValue(inTMsg.billToCustCd, (String) invDataMap.get("BILL_TO_CUST_CD"));
            setValue(inTMsg.billToCustAcctCd, (String) invDataMap.get("SELL_TO_CUST_CD"));
            setValue(inTMsg.billToLocNum, (String) invDataMap.get("LOC_NUM"));
        } else {
            if (BASE.equals(inTMsg.cfsBllgTpTxt.getValue())) {
                setValue(inTMsg.billToCustCd, (String) contrDataMap.get("BASE_BILL_TO_CUST_CD"));
                setValue(inTMsg.billToCustAcctCd, (String) contrDataMap.get("BASE_SELL_TO_CUST_CD"));
                setValue(inTMsg.billToLocNum, (String) contrDataMap.get("BASE_LOC_NUM"));
            } else if (USAGE.equals(inTMsg.cfsBllgTpTxt.getValue())) {
                setValue(inTMsg.billToCustCd, (String) contrDataMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
                setValue(inTMsg.billToCustAcctCd, (String) contrDataMap.get("BLLG_MTR_SELL_TO_CUST_CD"));
                setValue(inTMsg.billToLocNum, (String) contrDataMap.get("BLLG_MTR_LOC_NUM"));
            }
        }
        // END 2017/12/11 K.Kojima [QC#19955,MOD]
        // END   2016/11/21 [QC#14957, ADD]
}

    private boolean vldCheckCfsInvIntfcData(CFS_INV_INTFCTMsg inTMsg) {
        // START 2018/03/09 [QC#24679, MOD]
        boolean result = false;

        if (!hasValue(inTMsg.cfsLeasePblNum)
                || !hasValue(inTMsg.invLineNum)) {
            setErrInfo(inTMsg, NSAM0482E);
            return result;
        }
        if (duplicateCheck(inTMsg.cfsLeasePblNum.getValue())) {
            setErrInfo(inTMsg, NSAM0483E);
            return result;
        }

        // START 2018/03/09 [QC#24679, DEL]
        // START 2018/02/14 [QC#21872, ADD]
        // if (!hasValue(inTMsg.csaInvNum)) {
        //     setErrInfo(inTMsg, NSAM0712E);
        //     return;
        // }
        // END   2018/02/14 [QC#21872, ADD]
        // END   2018/03/09 [QC#24679, DEL]

        if (!hasValue(inTMsg.bllgPerFromDt)
                || !hasValue(inTMsg.bllgPerThruDt)) {
            setErrInfo(inTMsg, NSAM0494E);
            return result;
        }

        if (!ZYPDateUtil.checkDate(inTMsg.bllgPerFromDt.getValue())) {
            setErrInfo(inTMsg, NSAM0495E);
            return result;
        }
        if (!ZYPDateUtil.checkDate(inTMsg.bllgPerThruDt.getValue())) {
            setErrInfo(inTMsg, NSAM0496E);
            return result;
        }

        if (!hasValue(inTMsg.cfsBllgTpTxt)) {
            setErrInfo(inTMsg, NSAM0497E);
            return result;
        }
        // MOD 2017/01/26 [QC#16956]
        // if (!getVarCharConstValueList(CFS_BLLG_TP_TXT).contains(inTMsg.cfsBllgTpTxt.getValue())) {
        if (!this.cfsBllgTpTxtList.contains(inTMsg.cfsBllgTpTxt.getValue())) {
        // MOD 2017/01/26 [QC#16956]
            setErrInfo(inTMsg, NSAM0498E);
            return result;
        }

        if (!hasValue(inTMsg.invDt)) {
            setErrInfo(inTMsg, NSAM0500E);
            return result;
        }
        if (!ZYPDateUtil.checkDate(inTMsg.invDt.getValue())) {
            setErrInfo(inTMsg, NSAM0501E);
            return result;
        }

        if (hasValue(inTMsg.bllgMtrLbNm)
                // MOD 2017/01/26 [QC#16956]
//              && !getVarCharConstValueList(CFS_BLLG_MTR_LB_NM).contains(inTMsg.bllgMtrLbNm.getValue())) {
                && !this.cfsBllgMtrLbNmList.contains(inTMsg.bllgMtrLbNm.getValue())) {
                // MOD 2017/01/26 [QC#16956]
            setErrInfo(inTMsg, NSAM0502E);
            return result;
        }

        if (hasValue(inTMsg.ftrFlgOrigTxt)
                && (!ZYPConstant.FLG_ON_Y.equals(inTMsg.ftrFlgOrigTxt.getValue())
                        && !ZYPConstant.FLG_OFF_N.equals(inTMsg.ftrFlgOrigTxt.getValue()))) {
            setErrInfo(inTMsg, NSAM0503E);
            return result;
        }

        if (hasValue(inTMsg.cmFlgOrigTxt)
                && (!ZYPConstant.FLG_ON_Y.equals(inTMsg.cmFlgOrigTxt.getValue())
                        && !ZYPConstant.FLG_OFF_N.equals(inTMsg.cmFlgOrigTxt.getValue()))) {
            setErrInfo(inTMsg, NSAM0504E);
            return result;
        }

        if (hasValue(inTMsg.cfsFleetFlg)
                && (!ZYPConstant.FLG_ON_Y.equals(inTMsg.cfsFleetFlg.getValue())
                        && !ZYPConstant.FLG_OFF_N.equals(inTMsg.cfsFleetFlg.getValue()))) {
            setErrInfo(inTMsg, NSAM0485E);
            return result;
        }

        result = true;
        return result;
        // END   2018/03/09 [QC#24679, MOD]
    }

    private int updateCfsInvIntfc(CFS_INV_INTFCTMsg inMsg) {
        CFS_INV_INTFCTMsg tMsg = new CFS_INV_INTFCTMsg();
        setValue(tMsg.glblCmpyCd, inMsg.glblCmpyCd);
        setValue(tMsg.cfsInvIntfcPk, inMsg.cfsInvIntfcPk);
        tMsg = (CFS_INV_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
        EZDMsg.copy(inMsg, null, tMsg, null);
        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0013E, new String[] {tMsg.getTableName(), "glblCmpyCd=" + this.glblCmpyCd + ", cfsLeasePblNum=" + tMsg.cfsLeasePblNum.getValue() });
            return 0;
        }
        return 1;
    }

    private List<String> getVarCharConstValueList(String key) {
        String val = ZYPCodeDataUtil.getVarCharConstValue(key, this.glblCmpyCd);
        List<String> valList = new ArrayList<String>();
        if (hasValue(val)) {
            String[] valArray = val.split(DIV_COMMA);
            valList =  Arrays.asList(valArray);
        }
        return valList;
    }

    private Map<String, Object> setSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2018/03/12 [QC#24679, MOD]
        // inParam.put("cfsInvProcStsCdList", new String[] {CFS_INV_PROC_STS.NEW_RECORD, CFS_INV_PROC_STS.ERROR_FIXED});
        inParam.put("cfsInvProcStsCdList", new String[] {CFS_INV_PROC_STS.NEW_RECORD, CFS_INV_PROC_STS.ERROR_FIXED, CFS_INV_PROC_STS.ERROR_RECORD });
        // END   2018/03/12 [QC#24679, MOD]
        return inParam;
    }

    private CFS_INV_INTFCTMsg setCfsInvIntfcTMsg(ResultSet rs) throws SQLException {
        CFS_INV_INTFCTMsg inTMsg = new CFS_INV_INTFCTMsg();

        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.cfsInvIntfcPk, rs.getBigDecimal("CFS_INV_INTFC_PK"));
        setValue(inTMsg.procDt, rs.getString("PROC_DT"));
        setValue(inTMsg.invNum, rs.getString("INV_NUM"));
        setValue(inTMsg.cfsLeasePblNum, rs.getString("CFS_LEASE_PBL_NUM"));
        setValue(inTMsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(inTMsg.csaInvNum, rs.getString("CSA_INV_NUM"));
        setValue(inTMsg.csaContrNum, rs.getString("CSA_CONTR_NUM"));
        setValue(inTMsg.csaContrModTxt, rs.getString("CSA_CONTR_MOD_TXT"));
        setValue(inTMsg.cfsSerNum, rs.getString("CFS_SER_NUM"));
        setValue(inTMsg.invTotDealNetAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
        setValue(inTMsg.bllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
        setValue(inTMsg.bllgPerThruDt, rs.getString("BLLG_PER_THRU_DT"));
        setValue(inTMsg.cfsBllgTpTxt, rs.getString("CFS_BLLG_TP_TXT"));
        setValue(inTMsg.cfsLeaseNum, rs.getString("CFS_LEASE_NUM"));
        setValue(inTMsg.endCustNm, rs.getString("END_CUST_NM"));
        setValue(inTMsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
        setValue(inTMsg.invDt, rs.getString("INV_DT"));
        setValue(inTMsg.orgCd, rs.getString("ORG_CD"));
        setValue(inTMsg.bllgMtrLbNm, rs.getString("BLLG_MTR_LB_NM"));
        setValue(inTMsg.ftrFlgOrigTxt, rs.getString("FTR_FLG_ORIG_TXT"));
        setValue(inTMsg.cmFlgOrigTxt, rs.getString("CM_FLG_ORIG_TXT"));
        setValue(inTMsg.cfsInvProcStsCd, rs.getString("CFS_INV_PROC_STS_CD"));
        setValue(inTMsg.intfcErrMsgTxt, rs.getString("INTFC_ERR_MSG_TXT"));
        setValue(inTMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(inTMsg.csaDsContrModTxt, rs.getString("CSA_DS_CONTR_MOD_TXT"));
        setValue(inTMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        setValue(inTMsg.usgItemCd, rs.getString("USG_ITEM_CD"));
        setValue(inTMsg.svcItemCd, rs.getString("SVC_ITEM_CD"));
        setValue(inTMsg.ftrItemCd, rs.getString("FTR_ITEM_CD"));
        setValue(inTMsg.dfrdAcctgRuleCd, rs.getString("DFRD_ACCTG_RULE_CD"));
        setValue(inTMsg.dfrdInvRuleCd, rs.getString("DFRD_INV_RULE_CD"));
        setValue(inTMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        setValue(inTMsg.billToLocNum, rs.getString("BILL_TO_LOC_NUM"));
        setValue(inTMsg.shipToCustAcctCd, rs.getString("SHIP_TO_CUST_ACCT_CD"));
        setValue(inTMsg.shipToLocNum, rs.getString("SHIP_TO_LOC_NUM"));
        setValue(inTMsg.svcLineContrDtlPk, rs.getBigDecimal("SVC_LINE_CONTR_DTL_PK"));
        setValue(inTMsg.usgLineContrDtlPk, rs.getBigDecimal("USG_LINE_CONTR_DTL_PK"));
        setValue(inTMsg.uomCd, rs.getString("UOM_CD"));
        setValue(inTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
        setValue(inTMsg.dsInvTpCd, rs.getString("DS_INV_TP_CD"));
        setValue(inTMsg.contrVrsnEffFromDt, rs.getString("CONTR_VRSN_EFF_FROM_DT"));
        setValue(inTMsg.cfsFleetFlg, rs.getString("CFS_FLEET_FLG"));
        setValue(inTMsg.cfsInvBatNum, rs.getString("CFS_INV_BAT_NUM"));

        return inTMsg;
    }

    private Map<String, Object> getInvData(String invNum, String dsContrNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("dsContrNum", dsContrNum);
        queryParam.put("invBolLineNum", PARAM_001);
        queryParam.put("invLineNum", PARAM_00001);
        queryParam.put("invLineSubNum", PARAM_001);
        queryParam.put("invTrxLineSubNum", PARAM_001);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getInvData", queryParam);
    }

    private boolean existDsContr(String dsContrNum) {
        DS_CONTRTMsg inTMsg = new DS_CONTRTMsg();
        inTMsg.setSQLID("003");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("dsContrNum01", dsContrNum);
        DS_CONTRTMsgArray outTMsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsgArray.length() == 0) {
            return false;
        }
        return true;
    }

    private boolean existSvcMachMstr(String serNum) {
        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("serNum01", serNum);
        SVC_MACH_MSTRTMsgArray outTMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsgArray.length() == 0) {
            return false;
        }
        return true;
    }

    // START 2018/02/13 [QC#21872, MOD]
    // private Map<String, Object> getContrData(String dsContrNum, String serNum) {
    private Map<String, Object> getContrData(String dsContrNum, String serNum, String cfsBllgTpTxt, String bllgMtrLbNm) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dsContrNum", dsContrNum);
        queryParam.put("serNum", serNum);
        queryParam.put("dsContrCatgCdIsWty", DS_CONTR_CATG.WARRANTY);

        if (USAGE.equals(cfsBllgTpTxt)) {
            if (BLLG_MTR_LB_NM_BW.equals(bllgMtrLbNm)) {
                queryParam.put("bwMtrFlg", ZYPConstant.FLG_ON_Y);
            }
            if (BLLG_MTR_LB_NM_CL.equals(bllgMtrLbNm)) {
                queryParam.put("bwColorFlg", ZYPConstant.FLG_ON_Y);
            }
        }

        // START 2018/02/20 [QC#21872-1, ADD]
        queryParam.put("dsContrCatgCdIsFlt", DS_CONTR_CATG.FLEET);
        queryParam.put("dsContrDtlTpCdIsFleet", DS_CONTR_DTL_TP.FLEET);
        // END   2018/02/20 [QC#21872-1, ADD]

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContrData", queryParam);
    }
    // END   2018/02/13 [QC#21872, MOD]

    // mod start 2016/06/21 CSA #9406
    // START 2017/12/12 K.Kojima [QC#19955,MOD]
    // private String getUomCD(Map<String, Object> invDataMap, Map<String, Object> contrDataMap) {
    private String getUomCD(Map<String, Object> invDataMap, Map<String, Object> contrDataMap, String cfsBllgTpTxt) {
    // END 2017/12/12 K.Kojima [QC#19955,MOD]

        if (hasValue((String) invDataMap.get("UOM_CD"))) {
            return (String) invDataMap.get("UOM_CD");
        }
        // START 2017/12/12 K.Kojima [QC#21644,DEL]
        // MDSE_STORE_PKGTMsg inTMsg = new MDSE_STORE_PKGTMsg();
        // inTMsg.setSQLID("002");
        // inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        // inTMsg.setConditionValue("mdseCd01", contrDataMap.get("MDSE_CD"));
        // MDSE_STORE_PKGTMsgArray outTMsgArray = (MDSE_STORE_PKGTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        // if (outTMsgArray.length() == 1) {
        //     return outTMsgArray.no(0).pkgUomCd.getValue();
        // } else if (outTMsgArray.length() >= 2) {
        //     return PKG_UOM.EACH;
        // }
        // END 2017/12/12 K.Kojima [QC#21644,DEL]
        // START 2017/12/12 K.Kojima [QC#21644,ADD]
        if (BASE.equals(cfsBllgTpTxt)) {
            return (String) contrDataMap.get("BASE_BLLG_CYCLE_UOM_CD");
        } else if (USAGE.equals(cfsBllgTpTxt)) {
            return (String) contrDataMap.get("BLLG_MTR_BLLG_CYCLE_UOM_CD");
        }
        // END 2017/12/12 K.Kojima [QC#21644,ADD]
        return null;
    }
    // mod end 2016/06/21 CSA #9406

    // START 2018/03/09 [QC#24679, ADD]
    private String findInvNum(String dsContrNum, String serNum, String bllgPerFromYM, String bllgPerThruYM, String cfsBllgTpTxt, String bllgMtrLbNm) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dsContrNum", dsContrNum);
        queryParam.put("serNum", serNum);
        queryParam.put("bllgPerFromYM", bllgPerFromYM + "%");
        queryParam.put("bllgPerThruYM", bllgPerThruYM + "%");

        List<Map<String, Object>> resultList = null;
        if (BASE.equals(cfsBllgTpTxt)) {
            queryParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        } else if (USAGE.equals(cfsBllgTpTxt)) {
            queryParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
        } else {
            return null;
        }
        resultList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("findInvData", queryParam);

        if (resultList != null && resultList.size() == 1) {
            Map<String, Object> result = resultList.get(0);
            return (String) result.get("INV_NUM");
        }

        return null;
    }
    // END   2018/03/09 [QC#24679, ADD]
}

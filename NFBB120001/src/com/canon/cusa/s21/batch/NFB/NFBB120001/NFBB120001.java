package com.canon.cusa.s21.batch.NFB.NFBB120001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AP_INV_PMTTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 *
 * <pre>
 * Create AP Payment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/19/2016   CUSA            Y.Aikawa        Create          N/A
 * 10/05/2016   Hitachi         T.Tsuchida      Update          QC#14498
 * 03/14/2017   Hitachi         Y.Tsuchimoto    Update          QC#18028
 * 01/26/2018   CITS            T.Tokutomi      Update          QC#18685
 * 03/30/2021   CITS            K.Suzuki        Update          QC#57969
 * </pre>
 */

public class NFBB120001 extends S21BatchMain implements NFBB120001Constant {
    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Commit Count (AP_INV_PMT) */
    private int apInvPmtCommitCount;
    /** Commit Count (NFBI0300_01) */
    private int nfbi0300_01CommitCount;
    /** Commit Count (NFBI0310_01) */
    private int nfbi0310_01CommitCount;
    /** Total Commit Count */
    private int totalCommitCount;
    // START 2017/03/14 Y.Tsuchimoto [QC#18028,ADD]
    /** Condition of NFBI0310_01.ARCS_INV_SRC_TXT(Comma separated) */
    private String searchCondSrcTxt;
    // END   2017/03/14 Y.Tsuchimoto [QC#18028,ADD]

    // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
//    /** For handling AP_INV_PMT Bulk TBL Accessor */
//    private AP_INV_PMTTMsg[] apInvPmtTMsgs;
    // END 2018/01/26 T.Tokutomi [QC#18685,ADD]

    /** AP_INV_PMT Bulk Insert Count */
    private int iCntApInvPmt;
    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB120001().executeBatch(NFBB120001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Get CM_PROC_DT */
        this.cmProcDt = NFBCommonBusiness.getCmProcDt(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cmProcDt)) {
            throw new S21AbendException(NFBM0028E);
        }
        // START 2017/03/14 Y.Tsuchimoto [QC#18028,ADD]
        this.searchCondSrcTxt = ZYPCodeDataUtil.getVarCharConstValue(NFBB1200_SEARCH_COND_SRC_TXT, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.searchCondSrcTxt)) {
            this.searchCondSrcTxt = NFBB1200_SEARCH_COND_SRC_TXT_DEF_VAL;
        }
        // END   2017/03/14 Y.Tsuchimoto [QC#18028,ADD]
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        /** Initialize Commit Count */
        initCommitCount();
        // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
//        /** Initialize Bulk TBL Accessor */
//        initBulkTBLAccessor();
        // END 2018/01/26 T.Tokutomi [QC#18685,ADD]
        /** Initialize Bulk Update Count */
        initBulkCount();
    }

    @Override
    protected void mainRoutine() {
        S21TransactionTableAccessor tranAccessor = new S21TransactionTableAccessor();

        // Get transaction ID for interface ID NFBI0300.
        BigDecimal[] arrTransactionId300 = tranAccessor.getIntegrationRecord(INTERFACE_ID_NFBI0300);
        // Get transaction ID for interface ID NFBI0310.
        BigDecimal[] arrTransactionId310 = tranAccessor.getIntegrationRecord(INTERFACE_ID_NFBI0310);

        if (arrTransactionId300.length == 0
        ||  arrTransactionId310.length == 0) {
            // Normal End if no integration data is found.
            EZDDebugOutput.println(1, "Not found integration data.", this);
            return;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId_300", INTERFACE_ID_NFBI0300);
        queryParam.put("interfaceId_310", INTERFACE_ID_NFBI0310);
        queryParam.put("transactionIdList_300", arrTransactionId300);
        queryParam.put("transactionIdList_310", arrTransactionId310);
        // START 2017/03/14 Y.Tsuchimoto [QC#18028,ADD]
        queryParam.put("arcsInvSrcTxt_310", this.searchCondSrcTxt.split(","));
        // END   2017/03/14 Y.Tsuchimoto [QC#18028,ADD]
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_IF_RECORD, queryParam, new SelectIfRecordHandler());
        // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
        if (bRet) {
            apInvPmtCommitCount = iCntApInvPmt;
        } else {
            // Error.
            rollback();
            return;
        }
        // END 2018/01/26 T.Tokutomi [QC#18685,ADD]

        // Update NFBI0300 interface transaction status
        for (int i = 0; i < arrTransactionId300.length; i++) {
            tranAccessor.endIntegrationProcess(INTERFACE_ID_NFBI0300, arrTransactionId300[i]);
            nfbi0300_01CommitCount++;
            S21InfoLogOutput.printlnv(ZZIM0009I, INTERFACE_ID_NFBI0300, arrTransactionId300[i]);
        }
        // Update NFBI0310 interface transaction status
        for (int i = 0; i < arrTransactionId310.length; i++) {
            tranAccessor.endIntegrationProcess(INTERFACE_ID_NFBI0310, arrTransactionId310[i]);
            nfbi0310_01CommitCount++;
            S21InfoLogOutput.printlnv(ZZIM0009I, INTERFACE_ID_NFBI0310, arrTransactionId310[i]);
        }
        commit();
    }
    @Override
    protected void termRoutine() {
        this.totalCommitCount = apInvPmtCommitCount + nfbi0300_01CommitCount + nfbi0310_01CommitCount;
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, totalCommitCount, 0, 0);
    }

    // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
    /**
     * Private class: SelectIfRecordHandler
     */
    private class SelectIfRecordHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            String  arcsStsNegotiable = ZYPCodeDataUtil.getVarCharConstValue(ARCS_PMT_STS_NEGOTIABLE, glblCmpyCd);
            String  arcsStsVoid = ZYPCodeDataUtil.getVarCharConstValue(ARCS_PMT_STS_VOID, glblCmpyCd);

            if (!ZYPCommonFunc.hasValue(arcsStsNegotiable)) {
                throw new S21AbendException(NFBM0268E, new String[] {ARCS_PMT_STS_NEGOTIABLE });
            }
            if (!ZYPCommonFunc.hasValue(arcsStsVoid)) {
                throw new S21AbendException(NFBM0268E, new String[] {ARCS_PMT_STS_VOID });
            }

            while (rs.next()) {
                // Create AP_INV_PMT record
                AP_INV_PMTTMsg apInvPmt = new AP_INV_PMTTMsg();
                ZYPEZDItemValueSetter.setValue(apInvPmt.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtId, rs.getBigDecimal(ARCS_PMT_ID));
                ZYPEZDItemValueSetter.setValue(apInvPmt.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(apInvPmt.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(apInvPmt.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));

                // DB check
                AP_INV_PMTTMsg temp = (AP_INV_PMTTMsg) EZDTBLAccessor.findByKeyForUpdate(apInvPmt);

                if(temp != null){
                    // Update
                    apInvPmt = temp;

                    ZYPEZDItemValueSetter.setValue(apInvPmt.apPmtChkNum, rs.getString(AP_PMT_CHK_NUM));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.pmtDt, rs.getString(PMT_DT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtMethCd, rs.getString(ARCS_PMT_METH_CD));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.chkStsCd, rs.getString(CHK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtStsCd, rs.getString(ARCS_PMT_STS_CD));

                    BigDecimal pmtAmt = apInvPmt.arcsPmtAmt.getValue();
                    BigDecimal upPmtAmt = initValue(rs.getBigDecimal(ARCS_PMT_AMT));

                    if(arcsStsNegotiable.equals(apInvPmt.arcsPmtStsCd.getValue())){
                        // Negotiable
                        ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtAmt, pmtAmt.add(upPmtAmt));
                    } else if (arcsStsVoid.equals(apInvPmt.arcsPmtStsCd.getValue())){
                        // Void
                        ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtAmt, upPmtAmt);
                    }

                    EZDTBLAccessor.update(apInvPmt);

                } else {
                    // New
                    ZYPEZDItemValueSetter.setValue(apInvPmt.apPmtChkNum, rs.getString(AP_PMT_CHK_NUM));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.apInvAmt, rs.getBigDecimal(AP_INV_AMT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.pmtDt, rs.getString(PMT_DT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtStsCd, rs.getString(ARCS_PMT_STS_CD));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsInvSrcTxt, rs.getString(ARCS_INV_SRC_TXT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.chkStsCd, rs.getString(CHK_STS_CD));
                    // START 2016/10/05 T.Tsuchida [QC#14498,ADD]
                    ZYPEZDItemValueSetter.setValue(apInvPmt.apPmtDiscAmt, rs.getBigDecimal(AP_PMT_DISC_AMT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.acctDistCpltFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtAmt, rs.getBigDecimal(ARCS_PMT_AMT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsPmtMethCd, rs.getString(ARCS_PMT_METH_CD));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsCcyCd, rs.getString(ARCS_CCY_CD));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsExchRate, rs.getBigDecimal(ARCS_EXCH_RATE));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsExchDt, rs.getString(ARCS_EXCH_DT));
                    ZYPEZDItemValueSetter.setValue(apInvPmt.arcsBankAcctCd, rs.getString(ARCS_BANK_ACCT_CD));
                    // END 2016/10/05 T.Tsuchida [QC#14498,ADD]

                    EZDTBLAccessor.insert(apInvPmt);
                }

                iCntApInvPmt++;

                if (EZDTBLAccessor.RTNCD_NOT_FOUND.equals(apInvPmt.getReturnCode())) {
                    String tblName = "AP_INV_PMT";
                    String pKey = "GLBL_CMPY_CD, " //
                            + ARCS_PMT_ID + ", " //
                            + AP_VND_CD + ", " //
                            + AP_VND_INV_NUM + ", " //
                            + AP_VND_INV_SQ_NUM;
                    String val = glblCmpyCd + ", " //
                            + rs.getBigDecimal(ARCS_PMT_ID) + ", " //
                            + rs.getString(AP_VND_CD) + ", " //
                            + rs.getString(AP_VND_INV_NUM) + ", " //
                            + rs.getString(AP_VND_INV_SQ_NUM);
                    S21InfoLogOutput.println(ZZMM0015E, new String[] {tblName, pKey, val });
                    return false;
                }
                if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(apInvPmt.getReturnCode())) {
                    S21InfoLogOutput.println(ZZBM0074E);
                    return false;
                }
            }
            return true;
        }

        private BigDecimal initValue(BigDecimal b) {
            if (b == null) {
                return BigDecimal.ZERO;
            } else {
                return b;
            }
        }
    }

    // END 2018/01/26 T.Tokutomi [QC#18685,ADD]
    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.apInvPmtCommitCount = 0;
        this.nfbi0300_01CommitCount = 0;
        this.nfbi0310_01CommitCount = 0;
        this.totalCommitCount = 0;
    }

    // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
//    /**
//     * Initialize Bulk TBL Accessor.
//     */
//    private void initBulkTBLAccessor() {
//        this.apInvPmtTMsgs = new AP_INV_PMTTMsg[INT_BULK_COM_LIM];
//    }
    // END 2018/01/26 T.Tokutomi [QC#18685,ADD]
    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntApInvPmt = 0;
    }

}

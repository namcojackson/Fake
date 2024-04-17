package com.canon.cusa.s21.batch.NFB.NFBB101301;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.VND_INV_ERR_WRKTMsg;
import business.db.VND_INV_WRKTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_FOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2013 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Import For WDS for 3way matching
 *  This program is written based on NFBB101001.
 *
 * 1. Select records from the following tables.
 *
 * - VND_INV_WRK
 * - VND_INV_BOL_WRK
 * - VND_INV_LINE_WRK
 * - CM_VND_BANK
 *
 * 2. Insert the selected records into the following Invoice tables.
 *
 * - CM_AP_INV_HDR
 * - CM_AP_INV_DTL
 *
 * 3. Update CM_PROC_STS_CD in VND_INV_WRK table to 'Y'.
 *
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/14/2016   CSAI            Y.Aikawa        Create          N/A
 * 07/13/2017   CITS            K.Ogino         Update          QC#19683
 * 09/12/2017   CITS            K.Ogino         Update          QC#18093
 * 03/20/2018   CITS            K.Kameoka       Update          QC#20316(Sol#202)
 * 09/26/2018   Hitachi         Y.Takeno        Update          QC#28099
 * 11/08/2018   Hitachi         Y.Takeno        Update          QC#28982
 * 05/16/2019   Hitachi         Y.Takeno        Update          QC#50204
 * 08/20/2019   Hitachi         Y.Takeno        Update          QC#52280
 * 2019/12/13   Fujitsu         H.Ikeda         Update          QC#55051
 * 2023/01/05   Hitachi         S.Nakatani      Update          QC#60248
 * </pre>
 */
public class NFBB101301 extends S21BatchMain implements NFBB101301Constant {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Commit Count */
    private int commitCount;

    /** CM_AP_INV_HDR Handling Bulk TBL Accessor */
    private CM_AP_INV_HDRTMsg[] cmApInvHdrTmsgs = new CM_AP_INV_HDRTMsg[INT_COM_LIM];

    /** CM_AP_INV_HDR Bulk Insert Count */
    private int iCntHdr;

    /** CM_AP_INV_DTL Handling Bulk TBL Accessor */
    private CM_AP_INV_DTLTMsg[] cmApInvDtlTmsgs = new CM_AP_INV_DTLTMsg[INT_COM_LIM];

    /** CM_AP_INV_DTL Bulk Insert Count */
    private int iCntDtl;

    /** VND_INV_WRK Handling Bulk TBL Accessor */
    private VND_INV_WRKTMsg[] vndInvWrkTmsgs = new VND_INV_WRKTMsg[INT_COM_LIM];

    /** VND_INV_WRK Bulk Insert Count */
    private int iCntVndInvWrk;

    /** CM_PROC_DT */
    private String cmProcDt;

    /** EXCH_RATE */
    private BigDecimal exchRate;

    /** ACCT_ARTH_TP_CD */
    private String acctArthTpCd;

    /** DECL_PNT_DIGIT_NUM */
    private BigDecimal aftDeclPntDigitNum;

    /** AC_OC_TOT_FOB_COST_AMT */
    private BigDecimal accmFobCost = BigDecimal.ZERO;

    /** AC_OC_TOT_OTH_COST_AMT */
    private BigDecimal accmOthCost = BigDecimal.ZERO;

    /** AC_SC_TOT_FOB_COST_AMT */
    private BigDecimal accmFobCostSc = BigDecimal.ZERO;

    /** ORIG_SC_TOT_FOB_COST_AMT */
    private BigDecimal accmOrigFobCostSc = BigDecimal.ZERO;

    /** ORIG_SC_TOT_OTH_COST_AMT */
    private BigDecimal accmOthCostSc = BigDecimal.ZERO;

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */

    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /** Initialize S21BatchMain */
        new NFBB101301().executeBatch(NFBB101301.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");

    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Initialize Commit Count */
        this.commitCount = 0;
        /** Initialize Bulk Insert Count */
        iCntHdr = 0;
        iCntDtl = 0;
        iCntVndInvWrk = 0;

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @Override
    protected void mainRoutine() {

        /** Get CM_PROC_DT */
        cmProcDt = NFBCommonBusiness.getCmProcDt(glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(cmProcDt)) {
            throw new S21AbendException(NFBM0028E);
        }

        // Get NFBB101301_CR_DR_RSN_CD from VAR_CHAR_CONST table.
        List<String> listCrDrRsnCd = getListCrDrRsnCd();

        if (listCrDrRsnCd == null) {
            throw new S21AbendException(MSGID.NFBM0028E.toString());
        }

        Map<String, Object> queryParam = null;
        Boolean bRet = false;

        /** Get records to be deleted. */
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("vndInvImptTpCd", VND_INV_IMPT_TP_CD_01);
        queryParam.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        // START 2018/09/26 [QC#28099, ADD]
        queryParam.put("splyPmtFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("splyPmtFlgY", ZYPConstant.FLG_ON_Y);
        // END   2018/09/26 [QC#28099, ADD]

        bRet = (Boolean) ssmBatchClient.queryObject(SELECT_DELETE_RECORDS, queryParam, new SelectDeleteRecordRsHandler());

        if (bRet == Boolean.TRUE) {
            commit();
        } else {
            rollback();
        }

        /************************************
         * Select records for data creation.
         ************************************/
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("vndInvProcStsP", VND_INV_PROC_STS.PROCESSED);
        queryParam.put("listCrDrRsnCd", listCrDrRsnCd);
        queryParam.put("cmProcStsCdN", ZYPConstant.FLG_OFF_N);
        queryParam.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        queryParam.put("apInvTpCd", AP_INV_TP_CD_00);
        queryParam.put("ifProcStsCd", ZYPConstant.FLG_OFF_N);
        // START 2018/09/26 [QC#28099, ADD]
        queryParam.put("splyPmtFlgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("splyPmtFlgY", ZYPConstant.FLG_ON_Y);
        // END   2018/09/26 [QC#28099, ADD]
        // START 2019/05/16 [QC#50204, ADD]
        queryParam.put("itrlIntfcIdParts", INTERFACE_ID_CUSA_PARTS);
        queryParam.put("invTpCdCreditMemo", INV_TP.CREDIT_MEMO);
        // START 2019/05/16 [QC#50204, ADD]

        // START 2019/12/13 [QC#55051, ADD]
        ssmBatchClient.queryObject(SELECT_ERR_RECORD, queryParam, new SelectErrRecordRsHandler());
        // END   2019/12/13 [QC#55051, ADD]

        bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD, queryParam, new SelectRecordRsHandler());

        if (bRet) {

            if (iCntHdr > 0) {

                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmApInvHdrTmsgs, iCntHdr);

                if (iRet > 0) {

                    commitCount = commitCount + iRet;
                    iCntHdr = 0;

                } else {

                    commitCount = 0;
                    throw new S21AbendException(MSGID.ZZBM0074E.toString());

                }
            }

            if (iCntDtl > 0) {

                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmApInvDtlTmsgs, iCntDtl);

                if (iRet > 0) {

                    commitCount = commitCount + iRet;
                    iCntDtl = 0;

                } else {

                    commitCount = 0;
                    throw new S21AbendException(MSGID.ZZBM0074E.toString());

                }
            }

            if (iCntVndInvWrk > 0) {

                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(vndInvWrkTmsgs, iCntVndInvWrk);

                if (iRet > 0) {
                    commitCount = commitCount + iRet;
                    iCntVndInvWrk = 0;

                } else {

                    commitCount = 0;
                    throw new S21AbendException(MSGID.NFBM0028E.toString());

                }
            }

            commit();

        } else {

            rollback();

        }

    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        /* Normal End this process */
        setTermState(TERM_CD.NORMAL_END, commitCount, 0);

        S21InfoLogOutput.println("termRoutine Method End");

    }

    // START 2019/12/13 [QC#55051, ADD]
    private class SelectErrRecordRsHandler extends S21SsmBooleanResultSetHandlerSupport {

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            while (rs.next()) {
                String apVndInvNum = rs.getString(VND_INV_NUM);

                VND_INV_WRKTMsg vndInvWrk = new VND_INV_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(vndInvWrk.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvNum, apVndInvNum);

                vndInvWrk = (VND_INV_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(vndInvWrk);
                ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvProcStsCd, VND_INV_PROC_STS.ERROR);
                S21FastTBLAccessor.update(vndInvWrk);

                insertVndInvErrWrk(rs);
            }

            return Boolean.TRUE;
        }
    }

    /**
     * insertVndInvErrWrk
     * 
     * @param rs ResultSet
     * @throws SQLException 
     */
    private void insertVndInvErrWrk(ResultSet rs) throws SQLException {

        VND_INV_ERR_WRKTMsg tMsg = new VND_INV_ERR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        BigDecimal vndInvErrWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.VND_INV_ERR_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.vndInvErrWrkPk, vndInvErrWrkPk);

        ZYPEZDItemValueSetter.setValue(tMsg.vndInvNum, rs.getString(VND_INV_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndInvErrMsgId, MSG_ID_NFBM0175E);
        ZYPEZDItemValueSetter.setValue(tMsg.batErrMsgTxt, S21MessageFunc.clspGetMessage(MSG_ID_NFBM0175E, new String[] { rs.getString(VND_INV_NUM)}));

        S21FastTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NFBM0175E, new String[] { "VND_INV_ERR_WRK", "VND_INV_ERR_WRK_PK=" + vndInvErrWrkPk.toString() });
        }
    }
    // END   2019/12/13 [QC#55051, ADD]

    /**
     * <pre>
     *  Insert records into the following tables.
     *  -CM_AP_INV_HDR
     *  -CM_AP_INV_DTL
     *  Update CM_PROC_STS_CD in VND_INV_WRK table to 'Y'.
     * </pre>
     */
    private class SelectRecordRsHandler extends S21SsmBooleanResultSetHandlerSupport {

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            /***********************************************
             * SQL Bulk Executed result count
             ***********************************************/
            int iBulkResCnt = 0;

            /***********************************************
             * AP_VND_INV_NUM value of previous record
             ***********************************************/
            String beforeApVndCd = EMPTY_STRING;
            String beforeApVndInvNum = EMPTY_STRING;
            String beforeApVndInvSqNum = EMPTY_STRING;
            boolean isExist = false;

            while (rs.next()) {
                /***************************************************
                 * Set Common Data
                 ***************************************************/
                String ccyCd = rs.getString(CCY_CD);
                String invDt = rs.getString(INV_DT);
                exchRate = NFBCommonBusiness.getAcctDlyActlExchRate(glblCmpyCd, ccyCd, invDt);

                if (exchRate == null) {

                    exchRate = NFBCommonBusiness.getAcctDlyActlExchRate(glblCmpyCd, ccyCd, cmProcDt);

                    if (exchRate == null) {

                        exchRate = BigDecimal.ONE;

                    }

                }

                CCYTMsg ccyMsg = NFBCommonBusiness.getCcyInfo(glblCmpyCd, ccyCd);
                acctArthTpCd = ccyMsg.acctArthTpCd.getValue();
                aftDeclPntDigitNum = ccyMsg.aftDeclPntDigitNum.getValue();

                if (!rs.getString(AP_VND_INV_NUM).equals(beforeApVndInvNum)) {

                    if (!rs.isFirst()) {
                        /***************************************************
                         * Insert records into CM_AP_INV_IMPT_HDR
                         * table.
                         ***************************************************/
                        rs.previous();
                        CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
                        editcmApInvHdr(rs, cmApInvHdr);
                        cmApInvHdrTmsgs[iCntHdr] = cmApInvHdr;
                        iCntHdr++;
                        iBulkResCnt = 0;

                        if (iCntHdr == INT_COM_LIM) {

                            iBulkResCnt = S21FastTBLAccessor.insert(cmApInvHdrTmsgs);

                            if (iBulkResCnt > 0) {

                                commitCount = commitCount + iBulkResCnt;
                                cmApInvHdrTmsgs = new CM_AP_INV_HDRTMsg[INT_COM_LIM];
                                iCntHdr = 0;

                            } else {

                                rollback();
                                commitCount = 0;
                                throw new S21AbendException(MSGID.ZZBM0074E.toString());

                            }

                        }

                        if (rs.getString(VND_INV_PROC_STS_CD).equals(VND_INV_PROC_STS.PROCESSED)) {
                            /*********************************************
                             * Update CM_PROC_STS_CD on VND_INV_WRK
                             * table
                             *********************************************/
                            updateCmProcSts(rs);
                        }

                        // initialize accumulated amount
                        accmFobCost = BigDecimal.ZERO;
                        accmOthCost = BigDecimal.ZERO;
                        accmFobCostSc = BigDecimal.ZERO;
                        accmOrigFobCostSc = BigDecimal.ZERO;
                        accmOthCostSc = BigDecimal.ZERO;
                        rs.next();
                    }
                }

                /***************************************************
                 * Insert records into CM_AP_INV_DTL table.
                 ***************************************************/
                CM_AP_INV_DTLTMsg cmApInvDtl = new CM_AP_INV_DTLTMsg();
                editCmApInvDtl(rs, cmApInvDtl);

                cmApInvDtlTmsgs[iCntDtl] = cmApInvDtl;
                iCntDtl++;
                iBulkResCnt = 0;

                if (iCntDtl == INT_COM_LIM) {

                    iBulkResCnt = S21FastTBLAccessor.insert(cmApInvDtlTmsgs);

                    if (iBulkResCnt > 0) {

                        commitCount = commitCount + iBulkResCnt;
                        cmApInvDtlTmsgs = new CM_AP_INV_DTLTMsg[INT_COM_LIM];
                        iCntDtl = 0;

                    } else {

                        rollback();
                        commitCount = 0;
                        throw new S21AbendException(MSGID.ZZBM0074E.toString());

                    }
                }

                /** Keep value for inserting header record. */
                beforeApVndCd = rs.getString(AP_VND_CD);
                beforeApVndInvNum = rs.getString(AP_VND_INV_NUM);
                beforeApVndInvSqNum = rs.getString(AP_VND_INV_SQ_NUM);
                isExist = true;

            }

            if (isExist) {

                rs.previous();
                CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
                editcmApInvHdr(rs, cmApInvHdr);
                cmApInvHdrTmsgs[iCntHdr] = cmApInvHdr;
                iCntHdr++;

                if (rs.getString(VND_INV_PROC_STS_CD).equals(VND_INV_PROC_STS.PROCESSED)) {
                    /*********************************************
                     * Update CM_PROC_STS_CD on VND_INV_WRK
                     * table
                     *********************************************/
                    updateCmProcSts(rs);
                }
            }

            return true;
        }

        /**
         * edit the CM_AP_INV_HDRTMsg
         * @param rs result set
         * @param cmApInvHdr CM_AP_INV_HDRTMsg
         * @throws SQLException
         */
        private void editcmApInvHdr(ResultSet rs, CM_AP_INV_HDRTMsg cmApInvHdr) throws SQLException {

            if (getIntLength(rs.getBigDecimal(AC_OC_TOT_GND_COST_AMT)) > MAX_SIZE_AMT) {

                rollback();
                throw new S21AbendException(MSGID.NFBM0164E.toString());

            }

            ZYPEZDItemValueSetter.setValue(cmApInvHdr.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, rs.getString(AP_VND_CD));
            // START 2018/09/26 [QC#28099, ADD]
            if (ZYPConstant.FLG_OFF_N.equals(rs.getString(SPLY_PMT_FLG))
                    && ZYPCommonFunc.hasValue(rs.getString(ALT_VND_CD))) {
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, rs.getString(ALT_VND_CD));
            }
            // END   2018/09/26 [QC#28099, ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvTpCd, rs.getString(AP_INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.invDt, rs.getString(INV_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.ccyCd, rs.getString(CCY_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.pmtDueDt, rs.getString(PMT_DUE_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.vndPmtTermCd, rs.getString(VND_PMT_TERM_CD));
            cmApInvHdr.acctBankCd.clear();
            cmApInvHdr.acctBankAcctPayTpCd.clear();
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.invTpCd, rs.getString(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.lastTrxDt, cmProcDt);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFobCostAmt, accmFobCost);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFrtCostAmt, rs.getBigDecimal(AC_OC_TOT_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotInsCostAmt, rs.getBigDecimal(AC_OC_TOT_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotDomExpCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotOthCostAmt, accmOthCost);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotTaxAmt, rs.getBigDecimal(AC_OC_TOT_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotDiscAmt, BigDecimal.ZERO);
         // START 2023/01/05 S.Nakatani [QC#60248,MOD]
//            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndCostAmt, cmApInvHdr.acOcTotFobCostAmt.getValue().add(
//                    cmApInvHdr.acOcTotFrtCostAmt.getValue().add(cmApInvHdr.acOcTotInsCostAmt.getValue().add(cmApInvHdr.acOcTotOthCostAmt.getValue()))));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotHdlgCostAmt, rs.getBigDecimal(AC_OC_TOT_HDLG_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndCostAmt, cmApInvHdr.acOcTotFobCostAmt.getValue().add(
                    cmApInvHdr.acOcTotFrtCostAmt.getValue().add(cmApInvHdr.acOcTotHdlgCostAmt.getValue().add(cmApInvHdr.acOcTotInsCostAmt.getValue().add(cmApInvHdr.acOcTotOthCostAmt.getValue())))));
         // END 2023/01/05 S.Nakatani [QC#60248,MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndInvAmt, cmApInvHdr.acOcTotGndCostAmt.getValue().add(cmApInvHdr.acOcTotTaxAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotFobCostAmt, accmFobCostSc);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotFrtCostAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_FRT_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotInsCostAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_INS_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotDomExpCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotOthCostAmt, accmOthCostSc);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotTaxAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_TAX_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotDiscAmt, BigDecimal.ZERO);
            // START 2023/01/05 S.Nakatani [QC#60248,MOD]
//            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndCostAmt, cmApInvHdr.acScTotFobCostAmt.getValue().add(
//                    cmApInvHdr.acScTotFrtCostAmt.getValue().add(cmApInvHdr.acScTotInsCostAmt.getValue().add(cmApInvHdr.acScTotOthCostAmt.getValue()))));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotHdlgCostAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_HDLG_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndCostAmt, cmApInvHdr.acScTotFobCostAmt.getValue().add(
                    cmApInvHdr.acScTotFrtCostAmt.getValue().add(cmApInvHdr.acScTotHdlgCostAmt.getValue().add(cmApInvHdr.acScTotInsCostAmt.getValue().add(cmApInvHdr.acScTotOthCostAmt.getValue())))));
            // END 2023/01/05 S.Nakatani [QC#60248,MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndInvAmt, cmApInvHdr.acScTotGndCostAmt.getValue().add(cmApInvHdr.acScTotTaxAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.pmtHldFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.vndInvImptTpCd, VND_INV_IMPT_TP_CD_01);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotFobCostAmt, accmOrigFobCostSc);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotFrtCostAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_FRT_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotInsCostAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_INS_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotOthCostAmt, accmOthCostSc);
            // START 2023/01/05 S.Nakatani [QC#60248,ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotHdlgCostAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_TOT_HDLG_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            // END 2023/01/05 S.Nakatani [QC#60248,ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.poNum, rs.getString(PO_ORD_NUM));
            // QC#20316(Sol#202) START
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.payAloneFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.jrnlLinkFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cmApInvHdr.cancJrnlLinkFlg, ZYPConstant.FLG_ON_Y);
            // QC#20316(Sol#202) END

            // START 2019/08/20 [QC#52280, ADD]
            if (INV_TP.CREDIT_MEMO.equals(rs.getString(INV_TP_CD)) && INTERFACE_ID_CUSA_WS.equals(rs.getString(ITRL_INTFC_ID))) {
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotFobCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFobCostAmt, cmApInvHdr.acOcTotFobCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotFrtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFrtCostAmt, cmApInvHdr.acOcTotFrtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotInsCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotInsCostAmt, cmApInvHdr.acOcTotInsCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotOthCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotOthCostAmt, cmApInvHdr.acOcTotOthCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotTaxAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotTaxAmt, cmApInvHdr.acOcTotTaxAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotGndCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndCostAmt, cmApInvHdr.acOcTotGndCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotGndInvAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndInvAmt, cmApInvHdr.acOcTotGndInvAmt.getValue().negate());
                }
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acOcTotHdlgCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotHdlgCostAmt, cmApInvHdr.acOcTotHdlgCostAmt.getValue().negate());
                }
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]

                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotFobCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotFobCostAmt, cmApInvHdr.acScTotFobCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotFrtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotFrtCostAmt, cmApInvHdr.acScTotFrtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotInsCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotInsCostAmt, cmApInvHdr.acScTotInsCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotOthCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotOthCostAmt, cmApInvHdr.acScTotOthCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotTaxAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotTaxAmt, cmApInvHdr.acScTotTaxAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotGndCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndCostAmt, cmApInvHdr.acScTotGndCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotGndInvAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndInvAmt, cmApInvHdr.acScTotGndInvAmt.getValue().negate());
                }
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvHdr.acScTotHdlgCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotHdlgCostAmt, cmApInvHdr.acScTotHdlgCostAmt.getValue().negate());
                }
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]

                if (ZYPCommonFunc.hasValue(cmApInvHdr.origScTotFobCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotFobCostAmt, cmApInvHdr.origScTotFobCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.origScTotFrtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotFrtCostAmt, cmApInvHdr.origScTotFrtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.origScTotInsCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotInsCostAmt, cmApInvHdr.origScTotInsCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvHdr.origScTotOthCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotOthCostAmt, cmApInvHdr.origScTotOthCostAmt.getValue().negate());
                }
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvHdr.origScTotHdlgCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.origScTotHdlgCostAmt, cmApInvHdr.origScTotHdlgCostAmt.getValue().negate());
                }
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]
            }
            // END   2019/08/20 [QC#52280, ADD]
        }

        /**
         * edit the CM_AP_INV_DTLTMsg
         * @param rs ResultSet
         * @param cmApInvDtl CM_AP_INV_DTLTMsg
         * @throws SQLException
         */
        private void editCmApInvDtl(ResultSet rs, CM_AP_INV_DTLTMsg cmApInvDtl) throws SQLException {

            int ccyScale = aftDeclPntDigitNum.intValue();

            ZYPEZDItemValueSetter.setValue(cmApInvDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndCd, rs.getString(AP_VND_CD));
            // START 2018/09/26 [QC#28099, ADD]
            if (ZYPConstant.FLG_OFF_N.equals(rs.getString(SPLY_PMT_FLG))
                    && ZYPCommonFunc.hasValue(rs.getString(ALT_VND_CD))) {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndCd, rs.getString(ALT_VND_CD));
            }
            // END   2018/09/26 [QC#28099, ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvNum, rs.getString(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndCd, cmApInvDtl.apVndCd);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndInvNum, cmApInvDtl.apVndInvNum);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseCd, rs.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.delyOrdNum, rs.getString(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvTpCd, rs.getString(AP_INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.poNum, rs.getString(VND_REF_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.invTpCd, rs.getString(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.invQty, rs.getBigDecimal(INV_QTY));

            BigDecimal fobCost = BigDecimal.ZERO;
            BigDecimal fobCostSc = BigDecimal.ZERO;
            BigDecimal origFobCostSc = BigDecimal.ZERO;
            BigDecimal othCost = rs.getBigDecimal(AC_OC_OTH_COST_AMT);

            if (VND_FOC_TP.FREE.equals(rs.getString(VND_FOC_TP_CD))) {
                fobCost = BigDecimal.ZERO;
                fobCostSc = BigDecimal.ZERO;
                origFobCostSc = NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_FOB_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum);
            } else {
                fobCost = rs.getBigDecimal(AC_OC_FOB_COST_AMT);
                fobCostSc = NFBCommonBusiness.calcStdAmt(fobCost, exchRate, acctArthTpCd, aftDeclPntDigitNum);
                origFobCostSc = fobCostSc;
            }
            if (BigDecimal.ZERO.compareTo(fobCost) != 0) {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFobCostAmt, fobCost.setScale(ccyScale, BigDecimal.ROUND_HALF_UP));
            } else {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFobCostAmt, fobCost);
            }

            BigDecimal frtAmt = BigDecimal.ZERO;
            BigDecimal insAmt = BigDecimal.ZERO;
            // START 2023/01/05 S.Nakatani [QC#60248,ADD]
            BigDecimal hdlgAmt = BigDecimal.ZERO;
            // END 2023/01/05 S.Nakatani [QC#60248,ADD]
            if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(AC_OC_TOT_FOB_COST_AMT)) != 0) {
                frtAmt = rs.getBigDecimal(AC_OC_FOB_COST_AMT).multiply(rs.getBigDecimal(AC_OC_TOT_FRT_COST_AMT)).divide(rs.getBigDecimal(AC_OC_TOT_FOB_COST_AMT), ccyScale, BigDecimal.ROUND_HALF_UP);
                insAmt = rs.getBigDecimal(AC_OC_FOB_COST_AMT).multiply(rs.getBigDecimal(AC_OC_TOT_INS_COST_AMT)).divide(rs.getBigDecimal(AC_OC_TOT_FOB_COST_AMT), ccyScale, BigDecimal.ROUND_HALF_UP);
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                hdlgAmt = rs.getBigDecimal(AC_OC_FOB_COST_AMT).multiply(rs.getBigDecimal(AC_OC_TOT_HDLG_COST_AMT)).divide(rs.getBigDecimal(AC_OC_TOT_FOB_COST_AMT), ccyScale, BigDecimal.ROUND_HALF_UP);
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]
            }
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFrtCostAmt, frtAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcInsCostAmt, insAmt);
            // START 2023/01/05 S.Nakatani [QC#60248,ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcHdlgCostAmt, hdlgAmt);
            // END 2023/01/05 S.Nakatani [QC#60248,ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcDomExpCostAmt, BigDecimal.ZERO);

            // accumulated amount Header
            accmFobCostSc = accmFobCostSc.add(fobCostSc);
            accmFobCost = accmFobCost.add(fobCost);
            accmOthCost = accmOthCost.add(othCost);
            accmOthCostSc = accmOthCostSc.add(NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(AC_OC_OTH_COST_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            accmOrigFobCostSc = accmOrigFobCostSc.add(origFobCostSc);

            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcOthCostAmt, rs.getBigDecimal(AC_OC_TOT_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTaxAmt, BigDecimal.ZERO);
            // START 2023/01/05 S.Nakatani [QC#60248,MOD]
//            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotCostAmt, cmApInvDtl.acOcFobCostAmt.getValue().add(cmApInvDtl.acOcFrtCostAmt.getValue()).add(cmApInvDtl.acOcInsCostAmt.getValue().add(cmApInvDtl.acOcOthCostAmt.getValue())));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotCostAmt, cmApInvDtl.acOcFobCostAmt.getValue().add(cmApInvDtl.acOcFrtCostAmt.getValue()).add(cmApInvDtl.acOcHdlgCostAmt.getValue()).add(
                    cmApInvDtl.acOcInsCostAmt.getValue().add(cmApInvDtl.acOcOthCostAmt.getValue())));
            // END 2023/01/05 S.Nakatani [QC#60248,MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotInvAmt, cmApInvDtl.acOcTotCostAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScFobCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcFobCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScFrtCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcFrtCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScInsCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcInsCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScDomExpCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScOthCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcOthCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTaxAmt, BigDecimal.ZERO);
            // START 2023/01/05 S.Nakatani [QC#60248,MOD]
//            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotCostAmt, cmApInvDtl.acScFobCostAmt.getValue().add(cmApInvDtl.acScFrtCostAmt.getValue()).add(cmApInvDtl.acScInsCostAmt.getValue().add(cmApInvDtl.acScOthCostAmt.getValue())));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScHdlgCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcHdlgCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotCostAmt, cmApInvDtl.acScFobCostAmt.getValue().add(cmApInvDtl.acScFrtCostAmt.getValue()).add(cmApInvDtl.acScHdlgCostAmt.getValue()).add(
                    cmApInvDtl.acScInsCostAmt.getValue().add(cmApInvDtl.acScOthCostAmt.getValue())));
            // END 2023/01/05 S.Nakatani [QC#60248,MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotInvAmt, cmApInvDtl.acScTotCostAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTrnstJrnlAmt, cmApInvDtl.acScTotCostAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScStkInCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.cmApInvAsgCd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.applyExchRate, exchRate);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.trnstJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.stkInJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.invRcvQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndRefNum, rs.getString(VND_REF_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndFocTpCd, rs.getString(VND_FOC_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScFobCostAmt, origFobCostSc);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScFrtCostAmt, cmApInvDtl.acScFrtCostAmt);
            // START 2023/01/05 S.Nakatani [QC#60248,ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScHdlgCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcHdlgCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            // END 2023/01/05 S.Nakatani [QC#60248,ADD];
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScInsCostAmt, cmApInvDtl.acScInsCostAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScOthCostAmt, cmApInvDtl.acScOthCostAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocFrtCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocInsOthCostAmt, BigDecimal.ZERO);
            // START 2023/01/05 S.Nakatani [QC#60248,MOD]
//            ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocExtCostAmt, rs.getBigDecimal(AC_OC_FOB_COST_AMT).add(frtAmt).add(insAmt).add(cmApInvDtl.acOcOthCostAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocExtCostAmt, rs.getBigDecimal(AC_OC_FOB_COST_AMT).add(frtAmt).add(hdlgAmt).add(insAmt).add(cmApInvDtl.acOcOthCostAmt.getValue()));
            // END 2023/01/05 S.Nakatani [QC#60248,MOD]
            if (BigDecimal.ZERO.compareTo(cmApInvDtl.invQty.getValue()) != 0) {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocUnitExtCostAmt, cmApInvDtl.ocExtCostAmt.getValue().divide(cmApInvDtl.invQty.getValue(), ccyScale, BigDecimal.ROUND_HALF_UP));
            } else {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocUnitExtCostAmt, BigDecimal.ZERO);
            }
            BigDecimal ocUnitExtCostAmt = null;
            // In case FOC
            if (VND_FOC_TP.FREE.equals(rs.getString(VND_FOC_TP_CD)) && BigDecimal.ZERO.compareTo(cmApInvDtl.invQty.getValue()) != 0) {
                // exclude FOB
                ocUnitExtCostAmt = (cmApInvDtl.ocExtCostAmt.getValue().divide(rs.getBigDecimal(AC_OC_FOB_COST_AMT))).divide(cmApInvDtl.invQty.getValue(), ccyScale, BigDecimal.ROUND_HALF_UP);
            } else {
                // include FOB
                ocUnitExtCostAmt = cmApInvDtl.ocUnitExtCostAmt.getValue();
            }

            // calculate Original Currency Amount
            BigDecimal scUnitExtCostAmt = NFBCommonBusiness.calcStdAmt(ocUnitExtCostAmt, exchRate, acctArthTpCd, aftDeclPntDigitNum);
            scUnitExtCostAmt = scUnitExtCostAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.scUnitExtCostAmt, scUnitExtCostAmt);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.scExtCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.poQty, rs.getBigDecimal(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.thisMthFobCostAmt, rs.getBigDecimal(THIS_MTH_FOB_COST_AMT));
            // START 2018/11/08 [QC#28982, MOD]
            // ZYPEZDItemValueSetter.setValue(cmApInvDtl.entDealNetUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
            if (VND_FOC_TP.FREE.equals(rs.getString(VND_FOC_TP_CD))) {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entDealNetUnitPrcAmt, rs.getBigDecimal(DEAL_NET_UNIT_PRC_AMT));
            }
            // END   2018/11/08 [QC#28982, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.entPoDtlDealNetAmt, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));
            // START 2018/11/08 [QC#28982, MOD]
            // ZYPEZDItemValueSetter.setValue(cmApInvDtl.entFuncNetUnitPrcAmt, rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT));
            if (VND_FOC_TP.FREE.equals(rs.getString(VND_FOC_TP_CD))) {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entFuncNetUnitPrcAmt, NFBCommonBusiness.calcStdAmt(rs.getBigDecimal(DEAL_NET_UNIT_PRC_AMT), exchRate, acctArthTpCd, aftDeclPntDigitNum));
            }
            // END   2018/11/08 [QC#28982, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.entPoDtlFuncNetAmt, rs.getBigDecimal(ENT_PO_DTL_FUNC_NET_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.exchRate, rs.getBigDecimal(EXCH_RATE));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvLineNum, rs.getString(AP_VND_INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtl.poOrdDtlLineNum, rs.getString(PO_ORD_DTL_LINE_NUM));

            // START 2019/08/20 [QC#52280, ADD]
            if (INV_TP.CREDIT_MEMO.equals(rs.getString(INV_TP_CD)) && INTERFACE_ID_CUSA_WS.equals(rs.getString(ITRL_INTFC_ID))) {
                if (ZYPCommonFunc.hasValue(cmApInvDtl.invQty)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.invQty, cmApInvDtl.invQty.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcFobCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFobCostAmt, cmApInvDtl.acOcFobCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcFrtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFrtCostAmt, cmApInvDtl.acOcFrtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcInsCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcInsCostAmt, cmApInvDtl.acOcInsCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcOthCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcOthCostAmt, cmApInvDtl.acOcOthCostAmt.getValue().negate());
                }
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcHdlgCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcHdlgCostAmt, cmApInvDtl.acOcHdlgCostAmt.getValue().negate());
                }
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcTotCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotCostAmt, cmApInvDtl.acOcTotCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acOcTotInvAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotInvAmt, cmApInvDtl.acOcTotInvAmt.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScFobCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScFobCostAmt, cmApInvDtl.acScFobCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScFrtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScFrtCostAmt, cmApInvDtl.acScFrtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScInsCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScInsCostAmt, cmApInvDtl.acScInsCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScOthCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScOthCostAmt, cmApInvDtl.acScOthCostAmt.getValue().negate());
                }
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScHdlgCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScHdlgCostAmt, cmApInvDtl.acScHdlgCostAmt.getValue().negate());
                }
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScTotCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotCostAmt, cmApInvDtl.acScTotCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScTotInvAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotInvAmt, cmApInvDtl.acScTotInvAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.acScTrnstJrnlAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTrnstJrnlAmt, cmApInvDtl.acScTrnstJrnlAmt.getValue().negate());
                }

                if (ZYPCommonFunc.hasValue(cmApInvDtl.origScFobCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScFobCostAmt, cmApInvDtl.origScFobCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.origScFrtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScFrtCostAmt, cmApInvDtl.origScFrtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.origScInsCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScInsCostAmt, cmApInvDtl.origScInsCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.origScOthCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScOthCostAmt, cmApInvDtl.origScOthCostAmt.getValue().negate());
                }
                // START 2023/01/05 S.Nakatani [QC#60248,ADD]
                if (ZYPCommonFunc.hasValue(cmApInvDtl.origScHdlgCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScHdlgCostAmt, cmApInvDtl.origScHdlgCostAmt.getValue().negate());
                }
                // END 2023/01/05 S.Nakatani [QC#60248,ADD]

                if (ZYPCommonFunc.hasValue(cmApInvDtl.ocUnitExtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocUnitExtCostAmt, cmApInvDtl.ocUnitExtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.ocExtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocExtCostAmt, cmApInvDtl.ocExtCostAmt.getValue().negate());
                }
                if (ZYPCommonFunc.hasValue(cmApInvDtl.scUnitExtCostAmt)) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.scUnitExtCostAmt, cmApInvDtl.scUnitExtCostAmt.getValue().negate());
                }
            }
            // END   2019/08/20 [QC#52280, ADD]
        }

        /**
         * Update the CM_PROC_STS_CD to 'Y' in VND_INV_WRK.
         * @param rs ResultSet
         * @throws SQLException
         */
        private void updateCmProcSts(ResultSet rs) throws SQLException {

            int iBulkResCnt;
            VND_INV_WRKTMsg vndInvWrk = new VND_INV_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(vndInvWrk.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvNum, rs.getString(AP_VND_INV_NUM));

            vndInvWrk = (VND_INV_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(vndInvWrk);

            ZYPEZDItemValueSetter.setValue(vndInvWrk.cmProcStsCd, ZYPConstant.FLG_ON_Y);
            vndInvWrkTmsgs[iCntVndInvWrk] = vndInvWrk;
            iCntVndInvWrk++;
            iBulkResCnt = 0;

            if (iCntVndInvWrk == INT_COM_LIM) {

                iBulkResCnt = S21FastTBLAccessor.update(vndInvWrkTmsgs);

                if (iBulkResCnt > 0) {

                    vndInvWrkTmsgs = new VND_INV_WRKTMsg[INT_COM_LIM];

                } else {

                    rollback();
                    commitCount = 0;
                    throw new S21AbendException(MSGID.NFBM0028E.toString());

                }
                commitCount = commitCount + iBulkResCnt;
                iCntVndInvWrk = 0;

            }
        }
    }

    /**
     * <pre>
     * Get length of unscaled value.
     * </pre>
     */
    private static int getIntLength(BigDecimal val) {
        BigInteger intVal = val.unscaledValue();
        intVal = intVal.abs();
        String bak = intVal.toString();
        return bak.length();
    }

    /**
     * Get NFBB101301_CR_DR_RSN_CD from VAR_CHAR_CONST table.
     * @return List<String>
     */
    private List<String> getListCrDrRsnCd() {
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBB101301_CR_DR_RSN_CD, this.glblCmpyCd);
        List<String> listCrDrRsnCd = null;
        if (ZYPCommonFunc.hasValue(varCharConstVal)) {
            listCrDrRsnCd = Arrays.asList(varCharConstVal.split(","));
        }
        return listCrDrRsnCd;
    }

    /**
     * <pre>
     *  Delete the following tables.
     *  
     *  -CM_AP_INV_HDR
     *  -CM_AP_INV_DTL
     * </pre>
     */
    private class SelectDeleteRecordRsHandler extends S21SsmBooleanResultSetHandlerSupport {

        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            while (rs.next()) {
                int iRet = 0;
                /***********************************************
                 * Delete records in CM_AP_INV_HDR table.
                 ***********************************************/
                CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                EZDTBLAccessor.remove(cmApInvHdr);

                /***********************************************
                 * Delete records in CM_AP_INV_DTL table.
                 ***********************************************/
                CM_AP_INV_DTLTMsg cmApInvDtl = new CM_AP_INV_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                // QC#20316(Sol#202) START
                iRet = S21FastTBLAccessor.removeByPartialValue(cmApInvDtl, new String[] {GLBL_CMPY_CD_J, AP_VND_CD_J, AP_VND_INV_NUM_J, AP_VND_INV_SQ_NUM_J });
                // QC#20316(Sol#202) END
                
                if (iRet < 0) {
                    rollback();
                    commitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }

            return Boolean.TRUE;
        }
    }
}

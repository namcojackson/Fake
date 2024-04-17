package com.canon.cusa.s21.batch.NFB.NFBB104501;

import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.AP_INV_TP_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.AP_VND_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.AP_VND_INV_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.AP_VND_INV_LINE_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.AP_VND_INV_SQ_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.DELY_ORD_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.NFBM0028E;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.PO_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.SELECT_AP_INVOICE;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.SELECT_TARGET_CM_MATCH_TRK;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.SELECT_TARGET_CM_STK_IN;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.VND_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.VND_INV_IMPT_TP_CD_01;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.VND_INV_IMPT_TP_CD_10;
import static com.canon.cusa.s21.batch.NFB.NFBB104501.NFBB104501Constant.VND_INV_NUM;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_MATCH_TRKTMsg;
import business.db.CM_STK_INTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
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
 * Stock-In Assignment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/09/2013   Fujitsu         K.Kimura        Create          CMEX
 * 10/14/2013   Fujitsu         T.Tanaka        update          AC_SC_STK_IN_COST_AMT = SUM(CM_STK_IN.STK_IN_SC_FOB_COST_AMT)
 * 12/16/2013   CSAI            K.Uramori       Update          Item Split Stock-In modification
 * 11/04/2016   Hitachi         Y.Tsuchimoto    Update          QC#14562
 * 2017/12/21   CITS            T.Hakodate      Update          QC#23038
 * 2018/03/15   Hitachi         Y.Takeno        Update          QC#23245
 * 2019/10/16   Hitachi         Y.Takeno        Update          QC#54123
 * </pre>
 */
public class NFBB104501 extends S21BatchMain {
    /** TERM_CD */
    private TERM_CD termCd;
    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Commit Count */
    private int commitCount;
    // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//    /** Update Count */
//    private int iCntCmStkIn;
//    /** Update Count */
//    private int iCntCmApInvDtl;
//    /** CM_STK_IN Handling Bulk TBL Accessor */
//    private CM_STK_INTMsg[] cmStkInTmsgs;
//    /** CM_AP_INV_DTL Handling Bulk TBL Accessor */
//    private CM_AP_INV_DTLTMsg[] cmApInvDtlTmsgs;
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /** Initialize S21BatchMain */
        new NFBB104501().executeBatch(NFBB104501.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");

    }

    protected void initRoutine() {
        /** Initialize TERM_CD */
        termCd = TERM_CD.NORMAL_END;
        /** Initialize SSM Batch client */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Initialize Commit Count */
        this.commitCount = 0;
        // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//        this.iCntCmStkIn = 0;
//        this.iCntCmApInvDtl = 0;
//        /** Initialize Bulk TBL Accessor */
//        cmStkInTmsgs = new CM_STK_INTMsg[INT_COM_LIM];
//        cmApInvDtlTmsgs = new CM_AP_INV_DTLTMsg[INT_COM_LIM];
        // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
    }

    protected void mainRoutine() {
        /** Get CM_PROC_DT */
        cmProcDt = NFBCommonBusiness.getCmProcDt(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cmProcDt)) {
            throw new S21AbendException(NFBM0028E);
        }

        // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//        // Update CM_STK_IN
//        updateCmStkIn();
//
//         /****************************************************************
//         * Bulk update the rest of records in CM_STK_IN table.
//         ****************************************************************/
//        bulkUpdateRestOfCmStkInRecords();
        // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]

        /***************************************************
         * Get records for creating actual invoice records.
         ***************************************************/
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
     // QC#23038 T.Hakodate MOD START
        // queryParam.put("vndInvImptTpCd01", VND_INV_IMPT_TP_CD_01);
        queryParam.put("vndInvImptTpCdList", new String[] {VND_INV_IMPT_TP_CD_01, VND_INV_IMPT_TP_CD_10 });
     // QC#23038 T.Hakodate MOD END
        queryParam.put("flagN", ZYPConstant.FLG_OFF_N);

        // START 2018/03/15 [QC#23245, ADD]
        queryParam.put("acctInvStsCd", ACCT_INV_STS.CANCEL);
        // END   2018/03/15 [QC#23245, ADD]

        // START 2016/11/04 Y.Tsuchimoto [QC#14562,MOD]
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_AP_INVOICE, queryParam, new SelectApInvoiceRsHandler());
//        if (bRet) {
//            /****************************************************************
//             * Bulk update the rest of records in CM_AP_INV_DTL table.
//             ****************************************************************/
//            bulkUpdateRestOfCmApInvDtlRecords();
        if (!bRet) {
            rollback();
            return;
        }
        // END   2016/11/04 Y.Tsuchimoto [QC#14562,MOD]

        // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//        // Initialize
//        cmApInvDtlTmsgs = new CM_AP_INV_DTLTMsg[INT_COM_LIM];
//        iCntCmApInvDtl = 0;
//
//        commit();
        // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
    }
    // START 2016/11/04 Y.Tsuchimoto [QC#14562,MOD]
    @Override
    protected void termRoutine() {
        /* Normal End this process */
        setTermState(termCd, commitCount, 0, commitCount);
    }
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,MOD]

    // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//    private void updateCmStkIn() {
//        int iRetCmStkIn = 0;
//
//        Map<String, String> cmStkInParam = new HashMap<String, String>();
//        cmStkInParam.put("glblCmpyCd", glblCmpyCd);
//        cmStkInParam.put("flagN", FLG_OFF_N);
//
//        List<CM_STK_INTMsg> cmStkInList = (List<CM_STK_INTMsg>) ssmBatchClient.queryObjectList(SELECT_CM_STK_IN, cmStkInParam);
//
//        if (cmStkInList != null) {
//
//            for (CM_STK_INTMsg cmStkIn : cmStkInList) {
//
//                cmStkIn = (CM_STK_INTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmStkIn);
//
//                if (cmStkIn != null) {
//
//                    DFBEZDItemValueSetter.setValue(cmStkIn.invAsgFlg, ZYPConstant.FLG_ON_Y);
//                    DFBEZDItemValueSetter.setValue(cmStkIn.invAsgDt, cmProcDt);
//
//                    // count up by a record.
//                    cmStkInTmsgs[iCntCmStkIn] = cmStkIn;
//                    iCntCmStkIn++;
//                    iRetCmStkIn = 0;
//                    
//                    if (iCntCmStkIn == INT_COM_LIM) {
//                        iRetCmStkIn = S21FastTBLAccessor.update(cmStkInTmsgs);
//                        if (iRetCmStkIn > 0) {
//                            commitCount = commitCount + iRetCmStkIn;
//                            // clear records
//                            cmStkInTmsgs = new CM_STK_INTMsg[INT_COM_LIM];
//                        } else {
//                            rollback();
//                            commitCount = 0;
//                            throw new S21AbendException(NFBM0028E);
//                        }
//                        iCntCmStkIn = 0;
//                    }
//                }
//            }
//        }
//    }
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]

    /**
     * <pre>
     * Create actual invoice. 
     * </pre>
     */
    private class SelectApInvoiceRsHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            // START 2016/11/04 Y.Tsuchimoto [QC#14562,ADD]
            Map<String, String> targetCmStkInParam = new HashMap<String, String>();
            targetCmStkInParam.put("glblCmpyCd", glblCmpyCd);

            List<CM_STK_INTMsg> targetCmStkInList = (List<CM_STK_INTMsg>) ssmBatchClient.queryObjectList(SELECT_TARGET_CM_STK_IN, targetCmStkInParam);
            while (rs.next()) {
                CM_AP_INV_DTLTMsg cmApInvDtl = getCmApInvDtl(rs);
                if (cmApInvDtl == null) {
                    continue;
                }
                boolean matchingCompletedFlag = false;
                for (CM_STK_INTMsg targetCmStkIn : targetCmStkInList) {
                    if (targetCmStkIn == null || ZYPConstant.FLG_ON_Y.equals(targetCmStkIn.invAsgFlg.getValue())) {
                        continue;
                    }
                    // START 2018/03/30 T.Kikuhara [QC#20316,MOD]
                    String poNum = cmApInvDtl.poNum.getValue();
                    String poOrdDtlLineNum = cmApInvDtl.poOrdDtlLineNum.getValue();
                    String mdseCd = cmApInvDtl.mdseCd.getValue();
                    // START 2019/10/16 [QC#54123, MOD]
                    // if (ZYPCommonFunc.hasValue(poNum) && poNum.equals(targetCmStkIn.poNum.getValue())
                    // && (ZYPCommonFunc.hasValue(poOrdDtlLineNum) && poOrdDtlLineNum.equals(targetCmStkIn.poOrdDtlLineNum.getValue())
                    // || !ZYPCommonFunc.hasValue(poOrdDtlLineNum) && !ZYPCommonFunc.hasValue(targetCmStkIn.poOrdDtlLineNum.getValue()))
                    //  && ZYPCommonFunc.hasValue(mdseCd) && mdseCd.equals(targetCmStkIn.mdseCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(poNum) && poNum.equals(targetCmStkIn.poNum.getValue())
                            && ((ZYPCommonFunc.hasValue(poOrdDtlLineNum) && poOrdDtlLineNum.equals(targetCmStkIn.poOrdDtlLineNum.getValue()))
                                || (!ZYPCommonFunc.hasValue(poOrdDtlLineNum) && !ZYPCommonFunc.hasValue(targetCmStkIn.poOrdDtlLineNum.getValue()) 
                                        && ZYPCommonFunc.hasValue(mdseCd) && mdseCd.equals(targetCmStkIn.mdseCd.getValue())))) {
                    // END 2019/10/16 [QC#54123, MOD]
                    // END 2018/03/30 T.Kikuhara [QC#20316,MOD]

                        // get CM_MATCH_TRK
                        CM_MATCH_TRKTMsg cmMatchTrk = getCmMatchTrk(targetCmStkIn.cmStkInPk.getValue());
                        BigDecimal stkInDispQty = BigDecimal.ZERO;
                        if (cmMatchTrk != null && ZYPCommonFunc.hasValue(cmMatchTrk.matchQty.getValue())) {
                            stkInDispQty = cmMatchTrk.matchQty.getValue();
                        }

                        BigDecimal stkInUnDispQty = targetCmStkIn.rcvQty.getValue().subtract(stkInDispQty);
                        if (!ZYPCommonFunc.hasValue(stkInUnDispQty)) {
                            stkInUnDispQty = BigDecimal.ZERO;
                        }

                        BigDecimal addInvRcvQty = cmApInvDtl.invQty.getValue().subtract(cmApInvDtl.invRcvQty.getValue());
                        if (stkInUnDispQty.compareTo(addInvRcvQty) < 0) {
                            addInvRcvQty = stkInUnDispQty;
                        }

                        BigDecimal updInvRcvQty = cmApInvDtl.invRcvQty.getValue().add(addInvRcvQty);
                        ZYPEZDItemValueSetter.setValue(cmApInvDtl.invRcvQty, updInvRcvQty);
                        ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScStkInCostAmt, targetCmStkIn.entDealNetUnitPrcAmt.getValue().multiply(updInvRcvQty));
                        if (!ZYPCommonFunc.hasValue(cmApInvDtl.stkInDt) || cmApInvDtl.stkInDt.getValue().compareTo(targetCmStkIn.invLocRcvDt.getValue()) < 0) {
                            ZYPEZDItemValueSetter.setValue(cmApInvDtl.stkInDt, targetCmStkIn.invLocRcvDt);
                        }

                        if (ZYPCommonFunc.hasValue(cmApInvDtl.invQty) && cmApInvDtl.invQty.getValue().compareTo(cmApInvDtl.invRcvQty.getValue()) == 0) {
                            ZYPEZDItemValueSetter.setValue(cmApInvDtl.cmApInvAsgCd, ZYPConstant.FLG_ON_Y);
                            matchingCompletedFlag = true;
                        }

                        // Insert CM_MATCH_TRK
                        BigDecimal cmMatchTrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CM_MATCH_TRK_SQ);

                        CM_MATCH_TRKTMsg insertCmMatchTrk = new CM_MATCH_TRKTMsg();
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.cmMatchTrkPk, cmMatchTrkPk);
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.matchQty, addInvRcvQty);
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.cmStkInPk, targetCmStkIn.cmStkInPk);
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.vndCd, cmApInvDtl.vndCd);
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.vndInvNum, cmApInvDtl.vndInvNum);
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.invSqNum, cmApInvDtl.apVndInvSqNum);
                        // START 2018/03/30 T.Kikuhara [QC#20316,MOD]
                        ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.apVndInvLineNum, cmApInvDtl.apVndInvLineNum);
                        //ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.mdseCd, cmApInvDtl.mdseCd);
                        //ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.delyOrdNum, cmApInvDtl.delyOrdNum);
                        //ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.apInvTpCd, cmApInvDtl.apInvTpCd);
                        //ZYPEZDItemValueSetter.setValue(insertCmMatchTrk.poNum, cmApInvDtl.poNum);
                        // END 2018/03/30 T.Kikuhara [QC#20316,MOD]

                        S21FastTBLAccessor.insert(insertCmMatchTrk);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertCmMatchTrk.getReturnCode())) {
                            rollback();
                            commitCount = 0;
                            throw new S21AbendException(NFBM0028E);
                        } else {
                            commitCount = commitCount + 1;
                        }

                        if (isMatchingRcvQty(targetCmStkIn, stkInDispQty, addInvRcvQty)) {
                            // Update CM_STK_IN
                            ZYPEZDItemValueSetter.setValue(targetCmStkIn.invAsgFlg, ZYPConstant.FLG_ON_Y);
                            ZYPEZDItemValueSetter.setValue(targetCmStkIn.invAsgDt, cmProcDt);
                            S21FastTBLAccessor.update(targetCmStkIn);
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(targetCmStkIn.getReturnCode())) {
                                rollback();
                                commitCount = 0;
                                throw new S21AbendException(NFBM0028E);
                            } else {
                                commitCount = commitCount + 1;
                            }
                        }
                    }
                    if (matchingCompletedFlag) {
                        break;
                    }
                }
                // Update CM_AP_INV_DTL
                S21FastTBLAccessor.update(cmApInvDtl);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cmApInvDtl.getReturnCode())) {
                    rollback();
                    commitCount = 0;
                    throw new S21AbendException(NFBM0028E);
                } else {
                    commitCount = commitCount + 1;
                }
            }
            return Boolean.TRUE;
            // END   2016/11/04 Y.Tsuchimoto [QC#14562,ADD]

            // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//            int iRetCmApInvDtl = 0;
//            while (rs.next()) {
//                    /*****************************************************************************
//                     * Keep information to update CM_AP_INV_DTL table.
//                     *****************************************************************************/
//                    Map<String, String> cmApInvDtlParam = new HashMap<String, String>();
//                    cmApInvDtlParam.put("glblCmpyCd", glblCmpyCd);
//                    cmApInvDtlParam.put("apVndCd", rs.getString(AP_VND_CD));
//                    cmApInvDtlParam.put("apVndInvNum", rs.getString(AP_VND_INV_NUM));
//                    cmApInvDtlParam.put("apVndInvSqNum", rs.getString(AP_VND_INV_SQ_NUM));
//                    cmApInvDtlParam.put("vndCd", rs.getString(VND_CD));
//                    cmApInvDtlParam.put("vndInvNum", rs.getString(VND_INV_NUM));
//                    cmApInvDtlParam.put("mdseCd", rs.getString(MDSE_CD));
//                    cmApInvDtlParam.put("delyOrdNum", rs.getString(DELY_ORD_NUM));
//                    cmApInvDtlParam.put("apInvTpCd", rs.getString(AP_INV_TP_CD));
//                    cmApInvDtlParam.put("poNum", rs.getString(PO_NUM));
//
//                    List<CM_AP_INV_DTLTMsg> cmApInvDtlList = (List<CM_AP_INV_DTLTMsg>) ssmBatchClient.queryObjectList(SELECT_CM_AP_INV_DTL, cmApInvDtlParam);
//
//                    if (cmApInvDtlList != null) {
//
//                        for (CM_AP_INV_DTLTMsg cmApInvDtl : cmApInvDtlList) {
//
//                            cmApInvDtl = (CM_AP_INV_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmApInvDtl);
//
//                            if (cmApInvDtl != null) {
//
//                                DFBEZDItemValueSetter.setValue(cmApInvDtl.stkInDt, rs.getString(INV_LOC_RCV_DT));
//                                DFBEZDItemValueSetter.setValue(cmApInvDtl.invRcvQty, rs.getBigDecimal(RCV_QTY));
//                                DFBEZDItemValueSetter.setValue(cmApInvDtl.acScStkInCostAmt, rs.getBigDecimal(STK_IN_SC_FOB_COST_AMT));
//                                DFBEZDItemValueSetter.setValue(cmApInvDtl.poQty, rs.getBigDecimal(PO_QTY));
//                                DFBEZDItemValueSetter.setValue(cmApInvDtl.thisMthFobCostAmt, rs.getBigDecimal(THIS_MTH_FOB_COST_AMT));
//
//                                // count up by a record.
//                                cmApInvDtlTmsgs[iCntCmApInvDtl] = cmApInvDtl;
//                                iCntCmApInvDtl++;
//                                iRetCmApInvDtl = 0;
//
//                                if (iCntCmApInvDtl == INT_COM_LIM) {
//                                    iRetCmApInvDtl = S21FastTBLAccessor.update(cmApInvDtlTmsgs);
//                                    if (iRetCmApInvDtl > 0) {
//                                        commitCount = commitCount + iRetCmApInvDtl;
//                                        // clear records
//                                        cmApInvDtlTmsgs = new CM_AP_INV_DTLTMsg[INT_COM_LIM];
//                                    } else {
//                                        rollback();
//                                        commitCount = 0;
//                                        throw new S21AbendException(NFBM0028E);
//                                    }
//                                    iCntCmApInvDtl = 0;
//                                }
//                            }
//                        }
//                    }
//            }
//            return Boolean.TRUE;
            // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]

        }
    }

    // START 2016/11/04 Y.Tsuchimoto [QC#14562,DEL]
//    /**
//     * bulkUpdateRestOfCmStkInRecords
//     */
//    private void bulkUpdateRestOfCmStkInRecords(){
//        /**************************************************
//         * Bulk insert records into CM_STK_IN table.
//         **************************************************/  
//        if (iCntCmStkIn > 0) {
//            int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmStkInTmsgs, iCntCmStkIn);
//            if (iRet > 0) {
//                commitCount = commitCount + iRet;
//                iCntCmStkIn = 0;
//            } else {
//                commitCount = 0;
//                throw new S21AbendException(NFBM0028E);
//            }
//        }
//    }
//
//    /**
//     * bulkUpdateRestOfCmApInvDtlRecords
//     */
//    private void bulkUpdateRestOfCmApInvDtlRecords(){
//        /***************************************************
//         * Bulk update records in CM_AP_INV_DTL table.
//         ***************************************************/  
//        if (iCntCmApInvDtl > 0) {
//            int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmApInvDtlTmsgs, iCntCmApInvDtl);
//            if (iRet > 0) {
//                commitCount = commitCount + iRet;
//                iCntCmApInvDtl = 0;
//            } else {
//                commitCount = 0;
//                throw new S21AbendException(NFBM0028E);
//            }
//        }
//    }
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,DEL]

    // START 2016/11/04 Y.Tsuchimoto [QC#14562,ADD]
    private CM_MATCH_TRKTMsg getCmMatchTrk(BigDecimal cmStkInPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cmStkInPk", cmStkInPk);
        List<CM_MATCH_TRKTMsg> targetCmMatchTrkList = (List<CM_MATCH_TRKTMsg>) ssmBatchClient.queryObjectList(SELECT_TARGET_CM_MATCH_TRK, param);
        if (targetCmMatchTrkList.size() == 1) {
            return targetCmMatchTrkList.get(0);
        } else {
            return null;
        }
    }

    private CM_AP_INV_DTLTMsg getCmApInvDtl(ResultSet rs) throws SQLException {
        CM_AP_INV_DTLTMsg cmApInvDtl = new CM_AP_INV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvDtl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndCd, rs.getString(AP_VND_CD));
        ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvNum, rs.getString(AP_VND_INV_NUM));
        ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvLineNum, rs.getString(AP_VND_INV_LINE_NUM));
        // START 2018/03/30 T.Kikuhara [QC#20316,ADD]
        //ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndCd, rs.getString(VND_CD));
        //ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndInvNum, rs.getString(VND_INV_NUM));
        //ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseCd, rs.getString(MDSE_CD));
        //ZYPEZDItemValueSetter.setValue(cmApInvDtl.delyOrdNum, rs.getString(DELY_ORD_NUM));
        //ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvTpCd, rs.getString(AP_INV_TP_CD));
        //ZYPEZDItemValueSetter.setValue(cmApInvDtl.poNum, rs.getString(PO_NUM));
        // END 2018/03/30 T.Kikuhara [QC#20316,ADD]

        return (CM_AP_INV_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmApInvDtl);
    }

    private boolean isMatchingRcvQty(CM_STK_INTMsg targetCmStkIn, BigDecimal stkInDispQty, BigDecimal addInvRcvQty) {
        if (targetCmStkIn == null || !ZYPCommonFunc.hasValue(targetCmStkIn.cmStkInPk)) {
            return false;
        }

        BigDecimal matchQty = stkInDispQty.add(addInvRcvQty);

        if (ZYPCommonFunc.hasValue(targetCmStkIn.rcvQty) && targetCmStkIn.rcvQty.getValue().compareTo(BigDecimal.ZERO) > 0 && targetCmStkIn.rcvQty.getValue().compareTo(matchQty) == 0) {
            return true;
        }
        return false;
    }
    // END   2016/11/04 Y.Tsuchimoto [QC#14562,ADD]
}

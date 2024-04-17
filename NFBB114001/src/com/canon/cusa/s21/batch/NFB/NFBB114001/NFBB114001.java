package com.canon.cusa.s21.batch.NFB.NFBB114001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;

import business.db.CCYTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_MAINT_INVTMsg;
import business.db.COA_ACCTTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_MAINT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Data Creation for AP Invoice Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/11/2016   CUSA            Y.Aikawa        Create          N/A
 * 11/16/2016   Hitachi         K.Kasai         Update          QC#15927
 * 11/17/2016   Hitachi         Y.Tsuchimoto    Update          QC#15966
 * 12/15/2016   Hitachi         T.Tsuchida      Update          QC#16673
 * 07/06/2017   CITS            K.Ogino         Update          QC#19629
 * 12/19/2017   Hitachi         Y.Takeno        Update          QC#23022
 * 2017/12/26   Hitachi         J.Kim           Update          QC#22458
 * 2018/03/28   CITS            T.Kikuhara      Update          QC#20316
 * 2018/09/05   Fujitsu         S.Ohki          Update          QC#28040
 * </pre>
 */
public class NFBB114001 extends S21BatchMain implements NFBB114001Constant {
    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Batch Process Date */
    private String btProcDt;

    /** CM_PROC_DT */
    private String cmProcDt;

    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    private String varCharConstApLineTpItem;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Commit Count (CM_MAINT_INV) */
    private int cmMaintInvCommitCount;

    // START 2018/03/28 [QC#20316, DEL]
    /** Commit Count (CM_INV_ACCT_HDR) */
    //private int cmInvAcctHdrCommitCount;
    // END 2018/03/28 [QC#20316, DEL]

    /** Commit Count (CM_INV_ACCT_DIST) */
    private int cmInvAcctDistCommitCount;

    /** Commit Count (CM_AP_INV_HDR) */
    private int cmApInvHdrCommitCount;

    /** Commit Count (CM_AP_INV_DTL) */
    private int cmApInvDtlCommitCount;

    /** Total Commit Count */
    private int totalCommitCount;

    /** For handling CM_MAINT_INV Bulk TBL Accessor */
    private CM_MAINT_INVTMsg[] cmMaintInvTMsgs;

    // START 2018/03/28 [QC#20316, DEL]
    /** For handling CM_INV_ACCT_HDR Bulk TBL Accessor */
    //private CM_INV_ACCT_HDRTMsg[] cmInvAcctHdrTMsgs;
    // END 2018/03/28 [QC#20316, DEL]

    /** For handling CM_INV_ACCT_DIST Bulk TBL Accessor */
    private CM_INV_ACCT_DISTTMsg[] cmInvAcctDistTMsgs;

    /** For handling CM_AP_INV_HDR Bulk TBL Accessor */
    private CM_AP_INV_HDRTMsg[] cmApInvHdrTMsgs;

    /** For handling CM_AP_INV_DTLTMsg Bulk TBL Accessor */
    private CM_AP_INV_DTLTMsg[] cmApInvDtlTMsgs;

    /** CM_MAINT_INV Bulk Insert Count */
    private int iCntCmMaintInv;

    // START 2018/03/28 [QC#20316, DEL]
    /** CM_INV_ACCT_HDR Bulk Insert Count */
    //private int iCntCmInvAcctHdr;
    // END 2018/03/28 [QC#20316, DEL]

    /** CM_INV_ACCT_DIST Bulk Insert Count */
    private int iCntCmInvAcctDist;

    /** CM_AP_INV_HDR Bulk Insert Count */
    private int iCntCmApInvHdr;

    /** CM_AP_INV_DTL Bulk Insert Count */
    private int iCntCmApInvDtl;

    /** EXCH_RATE */
    private BigDecimal exchRate;

    /** ACCT_ARTH_TP_CD */
    private String acctArthTpCd;

    /** DECL_PNT_DIGIT_NUM */
    private BigDecimal aftDeclPntDigitNum;

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB114001().executeBatch(NFBB114001.class.getSimpleName());
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
        /** Get Batch Process Date */
        this.btProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        /** Get VAR_CHAR_CONST : AP_LINE_TP_ITEM */
        varCharConstApLineTpItem = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AP_LINE_TP_ITEM, glblCmpyCd);
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize Bulk TBL Accessor */
        initBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
    }

    @Override
    protected void mainRoutine() {
        // ACCT_INV_STS_CD
        String acctInvStsCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBB114001_ACCT_INV_STS_CD, this.glblCmpyCd);
        // AP_INV_CATG_CD
        String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBB114001_AP_INV_CATG_CD, this.glblCmpyCd);

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2017/12/26 J.Kim [QC#22458,MOD]
        // queryParam.put("apMaintInvStsCd", AP_MAINT_INV_STS_CD_APPROVED);
        queryParam.put("apMaintInvStsCd", AP_MAINT_INV_STS.APPROVED);
        // END 2017/12/26 J.Kim [QC#22458,MOD]
        queryParam.put("cmDefAcctCd", CM_DEF_ACCT_CD_MAINT);
        queryParam.put("acctInvStsCd", acctInvStsCd);
        queryParam.put("apInvCatgCd", apInvCatgCd);
        queryParam.put("invAuthDt", cmProcDt);

        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD, queryParam, new SelectRecordHandler());
        if (bRet == Boolean.TRUE) {
            // START 2018/03/28 [QC#20316, DEL]
            //if (iCntCmInvAcctHdr > 0) {
            //    int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmInvAcctHdrTMsgs, iCntCmInvAcctHdr);
            //    if (iRet > 0) {
            //        cmInvAcctHdrCommitCount = cmInvAcctHdrCommitCount + iRet;
            //    } else {
            //        cmInvAcctHdrCommitCount = 0;
            //        throw new S21AbendException(ZZBM0074E);
            //    }
            //}
            // END 2018/03/28 [QC#20316, DEL]
            if (iCntCmInvAcctDist > 0) {
                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmInvAcctDistTMsgs, iCntCmInvAcctDist);
                if (iRet > 0) {
                    cmInvAcctDistCommitCount = cmInvAcctDistCommitCount + iRet;
                } else {
                    cmInvAcctDistCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
            if (iCntCmApInvHdr > 0) {
                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmApInvHdrTMsgs, iCntCmApInvHdr);
                if (iRet > 0) {
                    cmApInvHdrCommitCount = cmApInvHdrCommitCount + iRet;
                } else {
                    cmApInvHdrCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
            if (iCntCmApInvDtl > 0) {
                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmApInvDtlTMsgs, iCntCmApInvDtl);
                if (iRet > 0) {
                    cmApInvDtlCommitCount = cmApInvDtlCommitCount + iRet;
                } else {
                    cmApInvDtlCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
            if (iCntCmMaintInv > 0) {
                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmMaintInvTMsgs, iCntCmMaintInv);
                if (iRet > 0) {
                    cmMaintInvCommitCount = cmMaintInvCommitCount + iRet;
                } else {
                    cmMaintInvCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
            commit();
        } else {
            rollback();
        }
    }

    @Override
    protected void termRoutine() {
        // START 2018/03/28 [QC#20316, MOD]
        this.totalCommitCount = cmMaintInvCommitCount + cmInvAcctDistCommitCount + cmApInvHdrCommitCount + cmApInvDtlCommitCount;
        // END 2018/03/28 [QC#20316, MOD]
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, totalCommitCount, 0, 0);
    }

    /**
     * Private class: SelectRecordHandler
     */
    @SuppressWarnings("unchecked")
    private class SelectRecordHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            List<List> hdrPkCombinationList = new ArrayList<List>();
            boolean createDist = true;
            BigDecimal apInvAmt = BigDecimal.ZERO;
            BigDecimal apAdjAmt = BigDecimal.ZERO;
            int indexOfDist = 1;
            while (rs.next()) {
                List hdrPkList = new ArrayList();
                hdrPkList.add(rs.getString(AP_VND_CD));
                hdrPkList.add(rs.getString(AP_VND_INV_NUM));
                hdrPkList.add(rs.getString(AP_VND_INV_SQ_NUM));
                if (!hdrPkCombinationList.contains(hdrPkList)) {
                    // START 2018/03/28 [QC#20316, DEL]
                    // Reset flag when starting next invoice.
                    //createDist = true;
                    // Create CM_INV_ACCT_HDR record
                    //indexOfDist = 1;
                    //CM_INV_ACCT_HDRTMsg cmInvAcctHdr = new CM_INV_ACCT_HDRTMsg();
                    //NFBCommonBusiness.setZeroIntoCmInvAcctHdr(cmInvAcctHdr);
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.glblCmpyCd, glblCmpyCd);
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndCd, rs.getString(AP_VND_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.locNum, rs.getString(LOC_NUM));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.poNum, rs.getString(PO_NUM));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtDueDt, rs.getString(PMT_DUE_DT));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.vndPmtTermCd, rs.getString(VND_PMT_TERM_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctBankCd, rs.getString(ACCT_BANK_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctBankAcctPayTpCd, rs.getString(ACCT_BANK_ACCT_PAY_TP_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invTpCd, rs.getString(INV_TP_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldFlg, rs.getString(PMT_HLD_FLG));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldCd, rs.getString(PMT_HLD_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldDt, rs.getString(PMT_HLD_DT));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRsnCd, rs.getString(PMT_HLD_RSN_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldPsnCd, rs.getString(PMT_HLD_PSN_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelPsnCd, rs.getString(PMT_HLD_REL_PSN_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelDt, rs.getString(PMT_HLD_REL_DT));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelRsnCd, rs.getString(PMT_HLD_REL_RSN_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.pmtHldRelCmntTxt, rs.getString(PMT_HLD_REL_CMNT_TXT));
                    //apInvAmt = rs.getBigDecimal(AP_INV_AMT);
                    //apAdjAmt = rs.getBigDecimal(AP_ADJ_AMT);
                    //if (apAdjAmt.compareTo(BigDecimal.ZERO) == 0) {
                    //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acInvTotCostAmt, apInvAmt);
                    //} else {
                    //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acInvTotCostAmt, apAdjAmt);
                    //}
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apInvSrcNm, rs.getString(AP_INV_SRC_NM));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apInvDescTxt, rs.getString(AP_INV_DESC_TXT));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.payAloneFlg, rs.getString(PAY_ALONE_FLG));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invMatchTpCd, rs.getString(INV_MATCH_TP_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invPmtNum, rs.getString(INV_PMT_NUM));
                    //// START 2017/12/19 [QC#23022, MOD]
                    //// ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invHdrDescTxt, rs.getString(INV_HDR_DESC_TXT));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invHdrDescTxt, getInvHdrDescTxt(rs.getString(INV_HDR_DESC_TXT)));
                    //// END   2017/12/19 [QC#23022, MOD]
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.acctInvStsCd, rs.getString(ACCT_INV_STS_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaBrCd, rs.getString(SPLY_COA_BR_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaCcCd, rs.getString(SPLY_COA_CC_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaProdCd, rs.getString(SPLY_COA_PROD_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaChCd, rs.getString(SPLY_COA_CH_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaProjCd, rs.getString(SPLY_COA_PROJ_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.coaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apPmtChkNum, rs.getString(AP_PMT_CHK_NUM));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.ifProcStsCd, rs.getString(IF_PROC_STS_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.apInvCatgCd, rs.getString(AP_INV_CATG_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.invAuthDt, rs.getString(INV_AUTH_DT));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.prntVndCd, rs.getString(PRNT_VND_CD));
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                    //ZYPEZDItemValueSetter.setValue(cmInvAcctHdr.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                    //
                    //cmInvAcctHdrTMsgs[iCntCmInvAcctHdr] = cmInvAcctHdr;
                    //iCntCmInvAcctHdr++;
                    //if (iCntCmInvAcctHdr == INT_BULK_COM_LIM) {
                    //    int iRet = S21FastTBLAccessor.insert(cmInvAcctHdrTMsgs);
                    //    if (iRet > 0) {
                    //        cmInvAcctHdrTMsgs = new CM_INV_ACCT_HDRTMsg[INT_BULK_COM_LIM];
                    //        cmInvAcctHdrCommitCount = cmInvAcctHdrCommitCount + iRet;
                    //        iCntCmInvAcctHdr = 0;
                    //    } else {
                    //        cmInvAcctHdrCommitCount = 0;
                    //        throw new S21AbendException(ZZBM0074E);
                    //    }
                    //}

                    /***************************************************
                     * Set Common Data
                     ***************************************************/
                    // START 2018/03/28 [QC#20316, MOD]
                    createDist = true;
                    indexOfDist = 1;
                    String ccyCd = rs.getString(CCY_CD);
                    String invDt = ZYPDateUtil.getCurrentSystemTime(YYYYMMDD);
                    apInvAmt = rs.getBigDecimal(AP_INV_AMT);
                    apAdjAmt = rs.getBigDecimal(AP_ADJ_AMT);
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

                    // Create CM_AP_INV_HDR
                    // START 2018/03/28 [QC#20316, MOD]
                    CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, rs.getString(AP_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvTpCd, rs.getString(AP_INV_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invDt, invDt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.ccyCd, ccyCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.pmtDueDt, rs.getString(PMT_DUE_DT));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.vndPmtTermCd, rs.getString(VND_PMT_TERM_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctBankCd, rs.getString(ACCT_BANK_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctBankAcctPayTpCd, rs.getString(ACCT_BANK_ACCT_PAY_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invTpCd, rs.getString(INV_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.lastTrxDt, cmProcDt);
                    if (apAdjAmt.compareTo(BigDecimal.ZERO) == 0) {
                        ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFobCostAmt, apInvAmt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFobCostAmt, apAdjAmt);
                    }
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotTaxAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndCostAmt, cmApInvHdr.acOcTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndInvAmt, cmApInvHdr.acOcTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotFobCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvHdr.acOcTotFobCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotTaxAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndCostAmt, cmApInvHdr.acScTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndInvAmt, cmApInvHdr.acScTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.pmtHldFlg, rs.getString(PMT_HLD_FLG));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.poNum, rs.getString(PO_NUM));

                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.locNum, rs.getString(LOC_NUM));
                    if (apAdjAmt.compareTo(BigDecimal.ZERO) == 0) {
                        ZYPEZDItemValueSetter.setValue(cmApInvHdr.acInvTotCostAmt, apInvAmt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(cmApInvHdr.acInvTotCostAmt, apAdjAmt);
                    }
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvSrcNm, rs.getString(AP_INV_SRC_NM));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvDescTxt, rs.getString(AP_INV_DESC_TXT));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.payAloneFlg, rs.getString(PAY_ALONE_FLG));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invMatchTpCd, rs.getString(INV_MATCH_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invPmtNum, rs.getString(INV_PMT_NUM));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invHdrDescTxt, getInvHdrDescTxt(rs.getString(INV_HDR_DESC_TXT)));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctInvStsCd, rs.getString(ACCT_INV_STS_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apPmtChkNum, rs.getString(AP_PMT_CHK_NUM));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.ifProcStsCd, rs.getString(IF_PROC_STS_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvCatgCd, rs.getString(AP_INV_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invAuthDt, rs.getString(INV_AUTH_DT));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.prntVndCd, rs.getString(PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                    // END 2018/03/28 [QC#20316, MOD]

                    cmApInvHdrTMsgs[iCntCmApInvHdr] = cmApInvHdr;
                    iCntCmApInvHdr++;
                    if (iCntCmApInvHdr == INT_BULK_COM_LIM) {
                        int iRet = S21FastTBLAccessor.insert(cmApInvHdrTMsgs);
                        if (iRet > 0) {
                            cmApInvHdrTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
                            cmApInvHdrCommitCount = cmApInvHdrCommitCount + iRet;
                            iCntCmApInvHdr = 0;
                        } else {
                            cmApInvHdrCommitCount = 0;
                            throw new S21AbendException(ZZBM0074E);
                        }
                    }

                    // Update CM_MAINT_INV record
                    CM_MAINT_INVTMsg cmMaintInvTMsg = new CM_MAINT_INVTMsg();
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apInvNum, rs.getString(AP_VND_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apVndCd, rs.getString(AP_VND_CD));
                    S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvTMsg);
                    // START 2017/12/26 J.Kim [QC#22458,MOD]
                    // cmMaintInvTMsg.apMaintInvStsCd.setValue(AP_MAINT_INV_STS_CD_LINKED_TO_ARCS);
                    cmMaintInvTMsg.apMaintInvStsCd.setValue(AP_MAINT_INV_STS.AUTHORIZED);
                    // END 2017/12/26 J.Kim [QC#22458,MOD]
                    cmMaintInvTMsgs[iCntCmMaintInv] = cmMaintInvTMsg;
                    iCntCmMaintInv++;
                    if (iCntCmMaintInv == INT_BULK_COM_LIM) {
                        int iRet = S21FastTBLAccessor.update(cmMaintInvTMsgs);
                        if (iRet > 0) {
                            cmMaintInvTMsgs = new CM_MAINT_INVTMsg[INT_BULK_COM_LIM];
                            cmMaintInvCommitCount = cmMaintInvCommitCount + iRet;
                            iCntCmMaintInv = 0;
                        } else {
                            cmMaintInvCommitCount = 0;
                            throw new S21AbendException(NFBM0028E);
                        }
                    }
                    hdrPkCombinationList.add(hdrPkList);
                }
                // If adjustment amount is not 0, create only one
                // record having same amount as header.
                if (createDist) {
                    // Create CM_INV_ACCT_DIST record
                    CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
                    NFBCommonBusiness.setZeroIntoCmInvAcctDist(cmInvAcctDist);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, DUMMY_MDSE + String.format(NONE_MDSE_CD_FORMAT, indexOfDist++));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, rs.getString(AP_INV_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, rs.getString(PO_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, rs.getString(DELY_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invTpCd, rs.getString(INV_TP_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invQty, rs.getBigDecimal(INV_QTY));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poQty, rs.getBigDecimal(PO_QTY));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invRcvQty, rs.getBigDecimal(INV_RCV_QTY));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apBillQty, rs.getBigDecimal(AP_BILL_QTY));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apRejQty, rs.getBigDecimal(AP_REJ_QTY));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.uomCd, rs.getString(UOM_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.thisMthFobCostAmt, rs.getBigDecimal(THIS_MTH_FOB_COST_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvTaxAmt, rs.getBigDecimal(AC_INV_TAX_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, rs.getBigDecimal(AC_INV_JRNL_COST_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invAsgDt, rs.getString(INV_ASG_DT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.stkInDt, rs.getString(STK_IN_DT));
                    // START 2018/09/05 [QC#28040, DEL]
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, rs.getString(AP_INV_DESC_TXT));
                    // END 2018/09/05 [QC#28040, DEL]
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.csmpInvNum, rs.getString(CSMP_INV_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.serNum, rs.getString(SER_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crTermCd, rs.getString(CR_TERM_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.istlLocFirstLineAddr, rs.getString(ISTL_LOC_FIRST_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.istlLocFlNm, rs.getString(ISTL_LOC_FL_NM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.istlLocCtyAddr, rs.getString(ISTL_LOC_CTY_ADDR));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.istlLocStCd, rs.getString(ISTL_LOC_ST_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.istlLocPostCd, rs.getString(ISTL_LOC_POST_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invLineDescTxt, rs.getString(INV_LINE_DESC_TXT));
                    // START 2018/03/28 [QC#20316, MOD]
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, rs.getString(CM_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, getCoaBrCd(rs.getString(MDSE_CD), rs.getString(CM_COA_BR_CD)));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, rs.getString(CM_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, rs.getString(CM_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, rs.getString(CM_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, rs.getString(CM_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, rs.getString(CM_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, rs.getString(CM_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, rs.getString(CM_COA_EXTN_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, rs.getString(SPLY_COA_CMPY_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, rs.getString(SPLY_COA_BR_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, rs.getString(SPLY_COA_CC_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, rs.getString(SPLY_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, rs.getString(SPLY_COA_PROD_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, rs.getString(SPLY_COA_CH_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, rs.getString(SPLY_COA_AFFL_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, rs.getString(SPLY_COA_PROJ_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, rs.getString(SPLY_COA_EXTN_CD));
                    // START 2018/09/05 [QC#28040, DEL]
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, rs.getString(AP_ACCT_DESC_TXT));
                    // END 2018/09/05 [QC#28040, DEL]
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, rs.getString(IF_PROC_STS_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, rs.getString(AP_INV_AUTH_FLG));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, rs.getString(AP_JRNL_CPLT_FLG));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entDealNetUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entPoDtlDealNetAmt, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entFuncNetUnitPrcAmt, rs.getBigDecimal(ENT_FUNC_NET_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.entPoDtlFuncNetAmt, rs.getBigDecimal(ENT_PO_DTL_FUNC_NET_AMT));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.exchRate, rs.getBigDecimal(EXCH_RATE));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poOrdDtlLineNum, rs.getString(PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, varCharConstApLineTpItem);
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, rs.getString(CM_INV_ACCT_DIST_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, rs.getString(AP_VND_INV_LINE_NUM));
                    // END 2018/03/28 [QC#20316, MOD]
                    // START 2017/12/19 [QC#23022, ADD]
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseDescShortTxt, rs.getString(MDSE_NM));
                    // END   2017/12/19 [QC#23022, ADD]
                    // START 2018/09/05 [QC#28040, ADD]
                    String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, rs.getString(CM_COA_ACCT_CD));
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, coaAcctDescTxt);

                    EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
                    int txtDigit = getDigitApInvDescTxt.getDigit();

                    if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
                    } else {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, coaAcctDescTxt);
                    }
                    // END 2018/09/05 [QC#28040, ADD]

                    // QC#19629 Start
                    // Set Contract Info
                    DS_CONTR_DTLTMsg dsContrDtlTMsg = NSXC001001GetContr.getContrDtlBySerNum(glblCmpyCd, cmInvAcctDist.serNum.getValue(), cmProcDt);
                    if (dsContrDtlTMsg != null) {
                        Map<String, String> contrInfoMap = getContrInfo(dsContrDtlTMsg.dsContrDtlPk.getValue(), rs.getString(START_DT), rs.getString(END_DT));
                        if (contrInfoMap != null) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.contrNum, contrInfoMap.get(DS_CONTR_NUM));
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.custDlrCd, contrInfoMap.get(VND_CD));
                        }
                    }
                    // QC#19629 End
                    cmInvAcctDistTMsgs[iCntCmInvAcctDist] = cmInvAcctDist;
                    iCntCmInvAcctDist++;
                    if (iCntCmInvAcctDist == INT_BULK_COM_LIM) {
                        int iRet = S21FastTBLAccessor.insert(cmInvAcctDistTMsgs);
                        if (iRet > 0) {
                            cmInvAcctDistTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                            cmInvAcctDistCommitCount = cmInvAcctDistCommitCount + iRet;
                            iCntCmInvAcctDist = 0;
                        } else {
                            cmInvAcctDistCommitCount = 0;
                            throw new S21AbendException(ZZBM0074E);
                        }
                    }

                    // Create CM_AP_INV_DTL
                    CM_AP_INV_DTLTMsg cmApInvDtl = new CM_AP_INV_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndCd, cmInvAcctDist.apVndCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvNum, cmInvAcctDist.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvSqNum, cmInvAcctDist.apVndInvSqNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndCd, cmInvAcctDist.apVndCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndInvNum, cmInvAcctDist.apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseCd, cmInvAcctDist.mdseCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.delyOrdNum, cmInvAcctDist.delyOrdNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvTpCd, cmInvAcctDist.apInvTpCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.poNum, cmInvAcctDist.poNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.invTpCd, cmInvAcctDist.invTpCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.invQty, cmInvAcctDist.invQty);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFobCostAmt, cmInvAcctDist.acInvJrnlCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTaxAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotCostAmt, cmInvAcctDist.acInvJrnlCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotInvAmt, cmInvAcctDist.acInvJrnlCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScFobCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcFobCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTaxAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotCostAmt, cmApInvDtl.acScFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotInvAmt, cmApInvDtl.acScFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTrnstJrnlAmt, cmApInvDtl.acScFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.applyExchRate, exchRate);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.trnstJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.stkInJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.invRcvQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScFobCostAmt, cmApInvDtl.acScFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocUnitExtCostAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocExtCostAmt, cmApInvDtl.acOcFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.scUnitExtCostAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.poQty, cmInvAcctDist.poQty);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.thisMthFobCostAmt, cmInvAcctDist.thisMthFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.entDealNetUnitPrcAmt, cmInvAcctDist.entDealNetUnitPrcAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.entPoDtlDealNetAmt, cmInvAcctDist.entPoDtlDealNetAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.entFuncNetUnitPrcAmt, cmInvAcctDist.entFuncNetUnitPrcAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.entPoDtlFuncNetAmt, cmInvAcctDist.entPoDtlFuncNetAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.exchRate, cmInvAcctDist.exchRate);
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.poOrdDtlLineNum, cmInvAcctDist.poOrdDtlLineNum);
                    // START 2018/03/28 [QC#20316, MOD]
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvLineNum, cmInvAcctDist.apVndInvLineNum);
                    // END 2018/03/28 [QC#20316, MOD]
                    // START 2017/12/19 [QC#23022, ADD]
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseDescShortTxt, cmInvAcctDist.mdseDescShortTxt);
                    // END   2017/12/19 [QC#23022, ADD]

                    cmApInvDtlTMsgs[iCntCmApInvDtl] = cmApInvDtl;
                    iCntCmApInvDtl++;
                    if (iCntCmApInvDtl == INT_BULK_COM_LIM) {
                        int iRet = S21FastTBLAccessor.insert(cmApInvDtlTMsgs);
                        if (iRet > 0) {
                            cmApInvDtlTMsgs = new CM_AP_INV_DTLTMsg[INT_BULK_COM_LIM];
                            cmApInvDtlCommitCount = cmApInvDtlCommitCount + iRet;
                            iCntCmApInvDtl = 0;
                        } else {
                            cmApInvDtlCommitCount = 0;
                            throw new S21AbendException(ZZBM0074E);
                        }
                    }
                }
                if (apAdjAmt.compareTo(BigDecimal.ZERO) != 0) {
                    // Not to create 2nd detail record when adjustment
                    // amount is not 0.
                    createDist = false;
                }
            }
            return Boolean.TRUE;
        }

        // START 2017/12/19 [QC#23022, ADD]
        public String getInvHdrDescTxt(String invHdrDescTxt) {
            if (!ZYPCommonFunc.hasValue(invHdrDescTxt)) {
                return invHdrDescTxt;
            }

            int  sIndex = 0;
            while (sIndex < invHdrDescTxt.length() && invHdrDescTxt.charAt(sIndex) == '0') {
                sIndex++;
            }

            StringBuilder builder = new StringBuilder();
            builder.append(INV_HDR_DESC_TXT_HDR_TXT);
            builder.append(invHdrDescTxt.substring(sIndex));
            return builder.toString();
        }
        // END   2017/12/19 [QC#23022, ADD]
    }

    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.cmMaintInvCommitCount = 0;
        // START 2018/03/28 [QC#20316, DEL]
        //this.cmInvAcctHdrCommitCount = 0;
        // END 2018/03/28 [QC#20316, DEL]
        this.cmInvAcctDistCommitCount = 0;
        this.cmApInvHdrCommitCount = 0;
        this.cmApInvDtlCommitCount = 0;
        this.totalCommitCount = 0;
    }

    /**
     * Initialize Bulk TBL Accessor.
     */
    private void initBulkTBLAccessor() {
        this.cmMaintInvTMsgs = new CM_MAINT_INVTMsg[INT_BULK_COM_LIM];
        // START 2018/03/28 [QC#20316, DEL]
        //this.cmInvAcctHdrTMsgs = new CM_INV_ACCT_HDRTMsg[INT_BULK_COM_LIM];
        this.cmInvAcctDistTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
        // END 2018/03/28 [QC#20316, DEL]
        this.cmApInvHdrTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
        this.cmApInvDtlTMsgs = new CM_AP_INV_DTLTMsg[INT_BULK_COM_LIM];
    }

    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntCmMaintInv = 0;
        // START 2018/03/28 [QC#20316, DEL]
        //this.iCntCmInvAcctHdr = 0;
        // END 2018/03/28 [QC#20316, DEL]
        this.iCntCmInvAcctDist = 0;
        this.iCntCmApInvHdr = 0;
        this.iCntCmApInvDtl = 0;
    }

    /**
     * Get FIN_BR_CD From SVC_MACH_MSTR table
     */
    @SuppressWarnings("unchecked")
    private String getCoaBrCd(String serNum, String cmCoaBrCd) {
        String coaBrCd = EMPTY_STRING;
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("serNum", serNum);
        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList(SELECT_FIN_BR_CD, queryMap);
        if (ssmResult.size() > 0) {
            Map map = (Map) ssmResult.get(0);
            if (ZYPCommonFunc.hasValue((String) map.get(FIN_BR_CD))) {
                coaBrCd = (String) map.get(FIN_BR_CD);
            } else {
                coaBrCd = cmCoaBrCd;
            }
        } else {
            coaBrCd = cmCoaBrCd;
        }
        return coaBrCd;
    }

    /**
     * Get SELECT_CONTR_INFO From Contract table
     */
    private Map<String, String> getContrInfo(BigDecimal dsContrDtlPk, String stDt, String endDt) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("dsContrDtlPk", dsContrDtlPk);
        queryMap.put("stDt", stDt);
        queryMap.put("endDt", endDt);
        List<Map> ssmResult = (List<Map>) ssmBatchClient.queryObjectList(SELECT_CONTR_INFO, queryMap);
        if (ssmResult.size() > 0) {
            Map<String, String> map = (Map<String, String>) ssmResult.get(0);
            return map;
        }
        return null;
    }

    // START 2018/09/05 [QC#28040, ADD]
    /**
     * getCoaAcctDescTxt
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @retrun coaAcctDescTxt String
     */
    public static String getCoaAcctDescTxt(String glblCmpyCd, String coaAcctCd) {

        String rtnCoaAcctDescTxt = null;
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(coaAcctCd)) {
            return null;
        }
        COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, coaAcctCd);

        coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
        if (coaAcct != null) {
            rtnCoaAcctDescTxt = coaAcct.coaAcctDescTxt.getValue();
        }
        return rtnCoaAcctDescTxt;
    }
    // END 2018/09/05 [QC#28040, ADD]
}

package com.canon.cusa.s21.batch.NFB.NFBB008501;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CM_IF_OPEN_AP_DTLTMsg;
import business.db.CM_IF_OPEN_AP_HDRTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Daily Process for WDS NA
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CUSA            Y.Aikawa        Create          N/A
 * 09/30/2016   Hitachi         K.Kasai         Update          QC#14874
 * 10/25/2016   Hitachi         Y.Tsuchimoto    Update          QC#14501
 * 11/10/2016   Hitachi         K.Kasai         Update          QC#14501
 * 03/14/2018   CITS            K.Kameoka       Update          QC#20316(Sol#202)
 * 05/31/2018   Hitachi         Y.Takeno        Update          QC#26158
 * 07/11/2018   CITS            T.Tokutomi      Update          QC#27025
 * 08/01/2018   Hitachi         Y.Takeno        Update          QC#27025-1
 * 09/05/2018   Fujitsu         S.Ohki          Update          QC#28040
 * 10/11/2018   Hitachi         E.Kameishi      Update          QC#28753
 * 2019/08/28   Fujitsu         S.Takami        Update          QC#52946
 * 2020/02/05   Fujitsu         H.Ikeda         Update          QC#53413
 * 2024/04/08   Hitachi         Y.Ogura         Update          QC#63871
 * </pre>
 */
public class NFBB008501 extends S21BatchMain implements NFBB008501Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    // START 2016/11/10 K.Kasai [QC#14501,ADD]
    /** BATCH_PROC_DT */
    private String batchProcDate;
    // END   2016/11/10 Y.Kasai [QC#14501,MOD]
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Commit Count (CM_IF_OPEN_AP_HDR) */
    private int cmIfOpenApHdrCommitCount;
    /** Commit Count (CM_IF_OPEN_AP_DTL) */
    private int cmIfOpenApDtlCommitCount;
// QC#20316(Sol#202) START
    /** Commit Count (CM_AP_INV_HDR) */
    private int cmApInvHdrCommitCount;
// QC#20316(Sol#202) END
    /** Commit Count (CM_INV_ACCT_DIST) */
    private int cmInvAcctDistCommitCount;
    /** Total Commit Count */
    private int totalCommitCount;

    /** For handling CM_IF_OPEN_AP_HDR Bulk TBL Accessor */
    private CM_IF_OPEN_AP_HDRTMsg[] cmIfOpenApHdrTMsgs;
    /** For handling CM_IF_OPEN_AP_DTl Bulk TBL Accessor */
    private CM_IF_OPEN_AP_DTLTMsg[] cmIfOpenApDtlTMsgs;
// QC#20316(Sol#202) START
    /** For handling CM_AP_INV_HDR Bulk TBL Accessor */
    private CM_AP_INV_HDRTMsg[] cmApInvHdrTMsgs;
// QC#20316(Sol#202) END
    /** For handling CM_INV_ACCT_DIST Bulk TBL Accessor */
    private CM_INV_ACCT_DISTTMsg[] cmInvAcctDistTMsgs;

    /** CM_IF_OPEN_AP_HDR Bulk Insert Count */
    private int iCntCmIfOpenApHdr;
    /** CM_IF_OPEN_AP_DTL Bulk Insert Count */
    private int iCntCmIfOpenApDtl;
// QC#20316(Sol#202) START
    /** CM_AP_INV_HDR Bulk Insert Count */
    private int iCntCmApInvHdr;
// QC#20316(Sol#202) END
    /** CM_INV_ACCT_DIST Bulk Insert Count */
    private int iCntCmInvAcctDist;

    /** AP_INV_ID map */
    private Map<List<String>, String> mapApInvId;

    // START 2018/08/01 [QC#27025-1, DEL]
    // QC#27025 Update.
    // private ArrayList<NPZC004001PMsg> poUpdList = new ArrayList<NPZC004001PMsg>();
    // END  2018/08/01 [QC#27025-1, DEL]

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB008501().executeBatch(NFBB008501.class.getSimpleName());
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
        // START 2016/11/10 K.Kasai [QC#14501,ADD]
        this.batchProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        // END   2016/11/10 Y.Kasai [QC#14501,MOD]
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize Bulk TBL Accessor */
        initBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
        /** AP_INV_ID map */
        mapApInvId = new HashMap<List<String>, String>();
    }

    @Override
    protected void mainRoutine() {
        // Get Current System Time for AP_INV_ID creation
        String strCmProcDd = ZYPDateUtil.getCurrentSystemTime("ssSSS");

        // Create CM_IF_OPEN_AP_HDR records
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("acctInvStsCd_20", ACCT_INV_STS_CD_20);
        queryParam.put("acctInvStsCd_21", ACCT_INV_STS_CD_21);
        queryParam.put("cmProcDd", strCmProcDd);
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_OPEN_AP_HDR_RECORD, queryParam, new SelectOpenApHdrRecordHandler());
        if (bRet == Boolean.TRUE) {
            if (iCntCmIfOpenApHdr > 0) {
                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmIfOpenApHdrTMsgs, iCntCmIfOpenApHdr);
                if (iRet > 0) {
                    cmIfOpenApHdrCommitCount = cmIfOpenApHdrCommitCount + iRet;
                } else {
                    cmIfOpenApHdrCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
        } else {
            rollback();
            return;
        }

        // Create CM_IF_OPEN_AP_DTL records
        // Update CM_INV_ACCT_DIST records
        queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("acctInvStsCd_20", ACCT_INV_STS_CD_20);
        queryParam.put("acctInvStsCd_21", ACCT_INV_STS_CD_21);
        queryParam.put("remSqNum", REM_SQ_NUM_001);
        queryParam.put("vndInvSqNum", VND_INV_SQ_NUM_00);
        bRet = (Boolean) ssmBatchClient.queryObject(SELECT_OPEN_AP_DTL_RECORD, queryParam, new SelectOpenApDtlRecordHandler());
        if (bRet == Boolean.TRUE) {
            if (iCntCmIfOpenApDtl > 0) {
                int iRet = NFBCommonBusiness.bulkInsertRestOfRecords(cmIfOpenApDtlTMsgs, iCntCmIfOpenApDtl);
                if (iRet > 0) {
                    cmIfOpenApDtlCommitCount = cmIfOpenApDtlCommitCount + iRet;
                } else {
                    cmIfOpenApDtlCommitCount = 0;
                    throw new S21AbendException(ZZBM0074E);
                }
            }
            if (iCntCmInvAcctDist > 0) {
                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmInvAcctDistTMsgs, iCntCmInvAcctDist);
                if (iRet > 0) {
                    cmInvAcctDistCommitCount = cmInvAcctDistCommitCount + iRet;
                } else {
                    cmInvAcctDistCommitCount = 0;
                    throw new S21AbendException(NFBM0028E);
                }
            }
        } else {
            rollback();
            return;
        }

        // Update CM_AP_INV_HDR records
        queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("acctInvStsCd_20", ACCT_INV_STS_CD_20);
        queryParam.put("acctInvStsCd_21", ACCT_INV_STS_CD_21);
        // QC#20316(Sol#202) START
        bRet = (Boolean) ssmBatchClient.queryObject(SELECT_CM_AP_INV_HDR_RECORD_FOR_UPDATE, queryParam, new SelectCmApInvHdrRecordForUpdateHandler());
        // QC#20316(Sol#202) END
        if (bRet == Boolean.TRUE) {
            // QC#20316(Sol#202) START
            if (iCntCmApInvHdr > 0) {
                int iRet = NFBCommonBusiness.bulkUpdateRestOfRecords(cmApInvHdrTMsgs, iCntCmApInvHdr);
                if (iRet > 0) {
                    cmApInvHdrCommitCount = cmApInvHdrCommitCount + iRet;
                } else {
                    cmApInvHdrCommitCount = 0;
            // QC#20316(Sol#202) END
                    throw new S21AbendException(NFBM0028E);
                }

                // START 2018/08/01 [QC#27025-1, DEL]
                // // QC#27025 Update.
                // if (!poUpdList.isEmpty()) {
                //     NPZC004001 api = new NPZC004001();
                //     api.execute(poUpdList, ONBATCH_TYPE.BATCH);
                //
                //    checkApiMsg();
                //
                //    // Initialization
                //    poUpdList.clear();
                //
                // }
                // END   2018/08/01 [QC#27025-1, DEL]
            }
        } else {
            rollback();
            return;
        }
        commit();
    }
    @Override
    protected void termRoutine() {
        this.totalCommitCount = cmIfOpenApHdrCommitCount + 
                                 cmIfOpenApDtlCommitCount + 
                                 // QC#20316(Sol#202) START
                                 cmApInvHdrCommitCount + 
                                 // QC#20316(Sol#202) END
                                 cmInvAcctDistCommitCount;
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, totalCommitCount, 0, totalCommitCount);
    }

    /**
     * Private class: SelectOpenApHdrRecordHandler
     */
    private class SelectOpenApHdrRecordHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                // Create AP_INV_ID list for CM_INV_ACCT_DIST creation.
                List<String> pkList = new ArrayList<String>();
                pkList.add(rs.getString(AP_VND_CD));
                pkList.add(rs.getString(AP_VND_INV_NUM));
                pkList.add(rs.getString(AP_VND_INV_SQ_NUM));
                mapApInvId.put(pkList, rs.getString(AP_INV_ID));

                // Create CM_IF_OPEN_AP_HDR record
                CM_IF_OPEN_AP_HDRTMsg cmIfOpenApHdr = new CM_IF_OPEN_AP_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.apInvId, rs.getString(AP_INV_ID));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.remSqNum, REM_SQ_NUM_001);
                // START 2018/10/11 E.Kameishi [QC#28753,MOD]
                // START 2016/10/25 Y.Tsuchimoto [QC#14501,MOD]
                //ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.invDt, rs.getString(INV_DT));
                //String invAuthDt = rs.getString(INV_AUTH_DT);
                //if (!ZYPCommonFunc.hasValue(invAuthDt)) {
                //    invAuthDt = batchProcDate;
                //}
                //ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.invDt, invAuthDt);
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.invDt, rs.getString(INV_DT));
                // END   2016/10/25 Y.Tsuchimoto [QC#14501,MOD]
                // END 2018/10/11 E.Kameishi [QC#28753,MOD]
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.acInvTotCostAmt, rs.getBigDecimal(AC_INV_TOT_COST_AMT));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.apInvSrcNm, rs.getString(AP_INV_SRC_NM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.apInvDescTxt, rs.getString(AP_INV_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaCmpyCd, rs.getString(COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaBrCd, rs.getString(COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaCcCd, rs.getString(COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaAcctCd, rs.getString(COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaProdCd, rs.getString(COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaChCd, rs.getString(COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaAfflCd, rs.getString(COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaProjCd, rs.getString(COA_PROJ_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.coaExtnCd, rs.getString(COA_EXTN_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.vndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.vndPmtTermCd, rs.getString(VND_PMT_TERM_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.acctBankCd, rs.getString(ACCT_BANK_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.acctBankAcctPayTpCd, rs.getString(ACCT_BANK_ACCT_PAY_TP_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.pmtDueDt, rs.getString(PMT_DUE_DT));
                // START 2016/09/30 [QC#14874, MOD]
                 ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.ccyCd, rs.getString(CCY_CD));
                // END 2016/09/30 [QC#14874, ADD]
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.actlExchRate, rs.getBigDecimal(ACTL_EXCH_RATE));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.actlExchRateEntDt, cmProcDt);
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.ifProcStsCd, ZYPConstant.FLG_OFF_N);
                // START 2018/05/31 [QC#26158, ADD]
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.payAloneFlg, rs.getString(PAY_ALONE_FLG));
                // END 2018/05/31 [QC#26158, ADD]

                // START 2018/09/05 [QC#28040, ADD]
                // START 2019/08/28 S.Takami [QC#52946,MOD]
//                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.invHdrDescTxt, rs.getString(INV_HDR_DESC_TXT));
                // START 2020/02/05 H.Ikeda [QC#53413,MOD]
                String invHdrDescTxt = null;
                String dbPoNum = null;
                /** set queryMap */
                Map<String, String> queryMap = new HashMap<String, String>();
                queryMap.put("glblCmpyCd", glblCmpyCd);
                queryMap.put("apVndCd", rs.getString(AP_VND_CD));
                queryMap.put("apVndInvNum", rs.getString(AP_VND_INV_NUM));
                queryMap.put("apVndInvSqNum", rs.getString(AP_VND_INV_SQ_NUM));
                List<String> ssmResult = (List<String>) ssmBatchClient.queryObjectList("GET_PO_NUM", queryMap);
                if (ssmResult.size() > 1) {
                    for (int i = 0; i <ssmResult.size(); i++) {
                        String ponum = ssmResult.get(i);
                        if (ZYPCommonFunc.hasValue(dbPoNum)) {
                            dbPoNum = dbPoNum + ":" + ponum;
                        } else {
                            dbPoNum = ponum;
                        }
                    }
                } else {
                    dbPoNum = rs.getString(PO_NUM);
                }
                String dbInvHdrDescTxt = rs.getString(INV_HDR_DESC_TXT);
                boolean hasPoNum = ZYPCommonFunc.hasValue(dbPoNum);
                boolean hasInvHdrDescTxt = ZYPCommonFunc.hasValue(dbInvHdrDescTxt);
                if (hasPoNum && hasInvHdrDescTxt) {
                    invHdrDescTxt = dbPoNum + ":" + dbInvHdrDescTxt;
                } else if (hasPoNum && !hasInvHdrDescTxt) {
                    invHdrDescTxt = dbPoNum;
                } else if (!hasPoNum && hasInvHdrDescTxt) {
                    invHdrDescTxt = dbInvHdrDescTxt;
                }
                int maxHdrDescTxtLen = cmIfOpenApHdr.getAttr("invHdrDescTxt").getDigit();
                if (invHdrDescTxt != null && invHdrDescTxt.length() > maxHdrDescTxtLen) {
                    invHdrDescTxt = invHdrDescTxt.substring(0, maxHdrDescTxtLen);
                }
                // END   2020/02/05 H.Ikeda [QC#53413,MOD]
                ZYPEZDItemValueSetter.setValue(cmIfOpenApHdr.invHdrDescTxt, invHdrDescTxt);
                // END 2019/08/28 S.Takami [QC#52946,MOD]
                // END 2018/09/05 [QC#28040, ADD]
                cmIfOpenApHdrTMsgs[iCntCmIfOpenApHdr] = cmIfOpenApHdr;
                iCntCmIfOpenApHdr++;
                if (iCntCmIfOpenApHdr == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.insert(cmIfOpenApHdrTMsgs);
                    if (iRet > 0) {
                        cmIfOpenApHdrTMsgs = new CM_IF_OPEN_AP_HDRTMsg[INT_BULK_COM_LIM];
                        cmIfOpenApHdrCommitCount = cmIfOpenApHdrCommitCount + iRet;
                        iCntCmIfOpenApHdr = 0;
                    } else {
                        cmIfOpenApHdrCommitCount = 0;
                        throw new S21AbendException(ZZBM0074E);
                    }
                }
            }
            return Boolean.TRUE;
        }
    }

    // START 2020/02/04 H.Ikeda [QC#53413,ADD]
    /**
     * createDescTxt
     * 
     * @param rs    ResultSet
     * @param poMap Map<String, String>
     * @throws SQLException
     */
    private void createDescTxt (ResultSet rs, Map<String, String> poMap) throws SQLException {
        List<String> poKeyList = new ArrayList<String>();
        List<String> poNumList = new ArrayList<String>();
        while (rs.next()) {
            StringBuilder str = new StringBuilder();
            str.append(rs.getString(AP_VND_CD));
            str.append(rs.getString(AP_VND_INV_NUM));
            if (poKeyList.contains(str.toString())) {
                continue;
            } else {
                poKeyList.add(str.toString());
            }
        }
        rs.beforeFirst();

        for (int i = 0; i < poKeyList.size(); i++) {
            String poData = poKeyList.get(i);
            if (poMap.containsKey(poData)) {
                continue;
            } else {
                String poNum = null;
                while (rs.next()) {
                    StringBuilder str = new StringBuilder();
                    str.append(rs.getString(AP_VND_CD));
                    str.append(rs.getString(AP_VND_INV_NUM));
                    if (poData.equals(str.toString())) {
                        poNum = rs.getString(PO_NUM);
                        if (ZYPCommonFunc.hasValue(poNum) && !poNumList.contains(poNum)) {
                            poNumList.add(poNum);
                        }
                    }
                }
                rs.beforeFirst();

                boolean fstFlg = true;
                for (int j = 0; j < poNumList.size(); j++) {
                    if (fstFlg) {
                        poNum = poNumList.get(j);
                        fstFlg = false;
                    } else {
                        poNum = poNum + ":" + poNumList.get(j);
                    }
                }
                if (ZYPCommonFunc.hasValue(poNum)) {
                    poMap.put(poData, poNum);
                }
                poNumList.clear();
            }
        }
    }
    // END   2020/02/04 H.Ikeda [QC#53413,ADD]

    /**
     * Private class: SelectOpenApDtlRecordHandler
     */
    private class SelectOpenApDtlRecordHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                // Create AP_INV_ID list for CM_INV_ACCT_DIST creation.
                List<String> pkList = new ArrayList<String>();
                pkList.add(rs.getString(AP_VND_CD));
                pkList.add(rs.getString(AP_VND_INV_NUM));
                pkList.add(rs.getString(AP_VND_INV_SQ_NUM));
                String apInvId = mapApInvId.get(pkList);

                // Create CM_IF_OPEN_AP_DTL record
                CM_IF_OPEN_AP_DTLTMsg cmIfOpenApDtl = new CM_IF_OPEN_AP_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apInvId, apInvId);
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.remSqNum, REM_SQ_NUM_001);
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.vndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.vndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.vndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apInvLineNum, rs.getString(AP_INV_LINE_NUM));
                // START 2024/4/8 Y.Ogura [QC#63871, DEL]
                // ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apInvDescTxt, rs.getString(AP_INV_DESC_TXT));
                // END 2024/4/8 Y.Ogura [QC#63871, DEL]
                // START 2024/4/8 Y.Ogura [QC#63871, ADD]
                String apInvDescTxt = null;
                apInvDescTxt = prohibitSpaceCheck(glblCmpyCd,rs.getString(AP_INV_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.apInvDescTxt, apInvDescTxt);
                // END 2024/4/8 Y.Ogura [QC#63871, ADD]
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.acInvJrnlCostAmt, rs.getBigDecimal(AC_INV_JRNL_COST_AMT));
                // QC#20316(Sol#202) START
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaCmpyCd, rs.getString(DR_COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaBrCd, rs.getString(DR_COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaCcCd, rs.getString(DR_COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaAcctCd, rs.getString(DR_COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaProdCd, rs.getString(DR_COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaChCd, rs.getString(DR_COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaAfflCd, rs.getString(DR_COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaProjCd, rs.getString(DR_COA_PROJ_CD));
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.coaExtnCd, rs.getString(DR_COA_EXTN_CD));
                // QC#20316(Sol#202) END
                ZYPEZDItemValueSetter.setValue(cmIfOpenApDtl.ifProcStsCd, ZYPConstant.FLG_OFF_N);
                cmIfOpenApDtlTMsgs[iCntCmIfOpenApDtl] = cmIfOpenApDtl;
                iCntCmIfOpenApDtl++;
                if (iCntCmIfOpenApDtl == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.insert(cmIfOpenApDtlTMsgs);
                    if (iRet > 0) {
                        cmIfOpenApDtlTMsgs = new CM_IF_OPEN_AP_DTLTMsg[INT_BULK_COM_LIM];
                        cmIfOpenApDtlCommitCount = cmIfOpenApDtlCommitCount + iRet;
                        iCntCmIfOpenApDtl = 0;
                    } else {
                        cmIfOpenApDtlCommitCount = 0;
                        throw new S21AbendException(ZZBM0074E);
                    }
                }

                /**********************************************
                 * Update AP_JRNL_CPLT_FLG on CM_INV_ACCT_DIST
                 **********************************************/
                // Update CM_INV_ACCT_DIST record
                CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
             // QC#20316(Sol#202) START
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, rs.getString(AP_VND_INV_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, rs.getString(CM_INV_ACCT_DIST_LINE_NUM));
             // QC#20316(Sol#202) END
                cmInvAcctDist = (CM_INV_ACCT_DISTTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmInvAcctDist);
                if (cmInvAcctDist != null) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_ON_Y);
                }
                cmInvAcctDistTMsgs[iCntCmInvAcctDist] = cmInvAcctDist;
                iCntCmInvAcctDist++;
                if (iCntCmInvAcctDist == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.update(cmInvAcctDistTMsgs);
                    if (iRet > 0) {
                        cmInvAcctDistTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
                        cmInvAcctDistCommitCount = cmInvAcctDistCommitCount + iRet;
                        iCntCmInvAcctDist = 0;
                    } else {
                        cmInvAcctDistCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }
                }
            }
            return Boolean.TRUE;
        }
    }
    // QC#20316(Sol#202) START
    /**
     * Private class: SelectCmInvAcctHdrRecordForUpdateHandler
     */
    private class SelectCmApInvHdrRecordForUpdateHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            while (rs.next()) {
                // Update CM_INV_ACCT_HDR record
                CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, rs.getString(AP_VND_CD));
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));
                cmApInvHdr = (CM_AP_INV_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(cmApInvHdr);
                if (cmApInvHdr != null) {
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctInvStsCd, ACCT_INV_STS_CD_30);
                    // START 2016/11/10 K.Kasai [QC#14501,ADD]
                    if (!ZYPCommonFunc.hasValue(cmApInvHdr.invAuthDt)) {
                        ZYPEZDItemValueSetter.setValue(cmApInvHdr.invAuthDt, batchProcDate);
                    }
                    // END   2016/11/10 K.Kasai [QC#14501,MOD]

                    // START 2018/08/01 [QC#27025-1, DEL]
                    // // QC#27025 Update.
                    // setPoUpdateInfo(cmApInvHdr);
                    // END   2018/08/01 [QC#27025-1, DEL]

                }
                cmApInvHdrTMsgs[iCntCmApInvHdr] = cmApInvHdr;
                iCntCmApInvHdr++;
                if (iCntCmApInvHdr == INT_BULK_COM_LIM) {
                    int iRet = S21FastTBLAccessor.update(cmApInvHdrTMsgs);
                    if (iRet > 0) {
                        cmApInvHdrTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
                        cmApInvHdrCommitCount = cmApInvHdrCommitCount + iRet;
                        iCntCmApInvHdr = 0;
                    } else {
                        cmApInvHdrCommitCount = 0;
                        throw new S21AbendException(NFBM0028E);
                    }

                    // START 2018/08/01 [QC#27025-1, DEL]
                    // QC#27025 Update.
                    // if (!poUpdList.isEmpty()) {
                    //     NPZC004001 api = new NPZC004001();
                    //     api.execute(poUpdList, ONBATCH_TYPE.BATCH);
                    // 
                    //     checkApiMsg();
                    // 
                    //     // Initialization
                    //     poUpdList.clear();
                    // 
                    // }
                    // END   2018/08/01 [QC#27025-1, DEL]
                }
            }
            return Boolean.TRUE;
        }

        // START 2018/08/01 [QC#27025-1, DEL]
        //// QC#27025 Add method.
        //private void setPoUpdateInfo(CM_AP_INV_HDRTMsg cmApInvHdr) {
        //
        //    if (!ZYPCommonFunc.hasValue(cmApInvHdr.poNum)//
        //            || "NONE".equals(cmApInvHdr.poNum.getValue())) {
        //        // No target data.
        //        return;
        //    }
        //
        //    CM_AP_INV_DTLTMsg cond = new CM_AP_INV_DTLTMsg();
        //    cond.setSQLID("001");
        //    cond.setConditionValue("glblCmpyCd01", cmApInvHdr.glblCmpyCd.getValue());
        //    cond.setConditionValue("apVndCd01", cmApInvHdr.apVndCd.getValue());
        //    cond.setConditionValue("apVndInvNum01", cmApInvHdr.apVndInvNum.getValue());
        //    cond.setConditionValue("apVndInvSqNum01", cmApInvHdr.apVndInvSqNum.getValue());
        //
        //    CM_AP_INV_DTLTMsgArray dtl = (CM_AP_INV_DTLTMsgArray) EZDTBLAccessor.findByCondition(cond);
        //
        //    for (int i = 0; i < dtl.getValidCount(); i++) {
        //        CM_AP_INV_DTLTMsg d = dtl.no(i);
        //
        //        if (!ZYPCommonFunc.hasValue(d.poNum) //
        //                || !ZYPCommonFunc.hasValue(d.poOrdDtlLineNum)//
        //                || "NONE".equals(d.poNum.getValue())) {
        //            continue;
        //        }
        //
        //        // Get PO_DTL
        //        PO_DTLTMsg pd = new PO_DTLTMsg();
        //        ZYPEZDItemValueSetter.setValue(pd.glblCmpyCd, d.glblCmpyCd);
        //        ZYPEZDItemValueSetter.setValue(pd.poOrdNum, d.poNum);
        //        ZYPEZDItemValueSetter.setValue(pd.poOrdDtlLineNum, d.poOrdDtlLineNum);
        //        pd = (PO_DTLTMsg) EZDTBLAccessor.findByKey(pd);
        //
        //        if (pd == null) {
        //            continue;
        //        }
        //
        //        BigDecimal poInvQty = pd.poInvQty.getValue();
        //        BigDecimal invQty = d.invQty.getValue();
        //
        //        if (!ZYPCommonFunc.hasValue(pd.poInvQty)) {
        //            poInvQty = BigDecimal.ZERO;
        //        }
        //        if (!ZYPCommonFunc.hasValue(d.invQty)) {
        //            invQty = BigDecimal.ZERO;
        //        }
        //
        //        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        //        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, d.glblCmpyCd);
        //        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, d.poNum);
        //        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, d.poOrdDtlLineNum);
        //        ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, poInvQty.add(invQty));
        //
        //        poUpdList.add(pMsg);
        //    }
        // }
        // END   2018/08/01 [QC#27025-1, DEL]
    }
    // QC#20316(Sol#202) END

    // START 2018/08/01 [QC#27025-1, DEL]
    // QC#27025 Add method.
    // private void checkApiMsg() {
    //
    //     for (NPZC004001PMsg pMsg : poUpdList) {
    //         if (S21ApiUtil.isXxMsgId(pMsg)) {
    //             for (String msgId : S21ApiUtil.getXxMsgIdList(pMsg)) {
    //                 if (msgId.endsWith("E")) {
    //                     // Error and throw Exception.
    //                     EZDDebugOutput.println(99, msgId, new S21AbendException(msgId), this);
    //                 } else {
    //                     // Warning or Info.
    //                     EZDDebugOutput.println(99, msgId, this);
    //                 }
    //             }
    //         }
    //     }
    // }
    // END   2018/08/01 [QC#27025-1, DEL]

    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.cmIfOpenApDtlCommitCount = 0;
        this.cmIfOpenApDtlCommitCount = 0;
        // QC#20316(Sol#202) START
        this.cmApInvHdrCommitCount = 0;
        // QC#20316(Sol#202) END
        this.cmInvAcctDistCommitCount = 0;
        this.totalCommitCount = 0;
    }
    /**
     * Initialize Bulk TBL Accessor.
     */
    private void initBulkTBLAccessor() {
        this.cmIfOpenApHdrTMsgs = new CM_IF_OPEN_AP_HDRTMsg[INT_BULK_COM_LIM];
        this.cmIfOpenApDtlTMsgs = new CM_IF_OPEN_AP_DTLTMsg[INT_BULK_COM_LIM];
        // QC#20316(Sol#202) START
        this.cmApInvHdrTMsgs = new CM_AP_INV_HDRTMsg[INT_BULK_COM_LIM];
        // QC#20316(Sol#202) END
        this.cmInvAcctDistTMsgs = new CM_INV_ACCT_DISTTMsg[INT_BULK_COM_LIM];
    }
    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntCmIfOpenApHdr = 0;
        this.iCntCmIfOpenApDtl = 0;
        // QC#20316(Sol#202) START
        this.iCntCmApInvHdr = 0;
        // QC#20316(Sol#202) END
        this.iCntCmInvAcctDist = 0;
    }
    
    // START 2024/4/8 Y.Ogura [QC#63871, ADD]
    private String prohibitSpaceCheck(String glblCmpyCd, String apInvDescTxt) {
        DS_COND_CONSTTMsg tMsg = new DS_COND_CONSTTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsCondConstGrpId01", NFBB0085_FILTER_TXT);
        tMsg.setSQLID("003");
        DS_COND_CONSTTMsgArray tMsgArray = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        String val2 = "";
        if (tMsgArray != null &&  0 < tMsgArray.getValidCount()) {
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                if(!ZYPCommonFunc.hasValue(tMsgArray.no(i).dsCondConstValTxt_02)){
                    val2 = SPACE;
                } else {
                    val2 = tMsgArray.no(i).dsCondConstValTxt_02.getValue();
                }
                apInvDescTxt = apInvDescTxt.replace(tMsgArray.no(i).dsCondConstValTxt_01.getValue()
                        ,val2);
            }
        }
        return apInvDescTxt;
    }
    // END 2024/4/8 Y.Ogura [QC#63871, ADD]
}

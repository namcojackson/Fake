package com.canon.cusa.s21.batch.NFB.NFBB124001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AP_INV_ROSSTMsg;
import business.db.CCYTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.COA_ACCTTMsg;
import business.db.CPOTMsg;
import business.db.TOCTMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Import For Compensation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   CUSA            Y.Aikawa        Create          N/A
 * 07/14/2016   Hitachi         T.Tsuchida      Update          QC#11621
 * 09/28/2016   Hitachi         K.Kasai         Update          QC#14798
 * 12/19/2017   Hitachi         Y.Takeno        Update          QC#23022
 * 03/14/2018   CITS            K.Kameoka       Update          QC#20316(Sol#202)
 * 09/05/2018   Fujitsu         S.Ohki          Update          QC#28040
 * </pre>
 */
public class NFBB124001 extends S21BatchMain implements NFBB124001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** CM_PROC_DT */
    private String cmProcDt;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** List For handling CM_INV_ACCT_DIST Bulk TBL Accessor */
    private List<CM_INV_ACCT_DISTTMsg> listCmInvAcctDistTMsg;
    /** CM_INV_ACCT_DTL Bulk Insert Count */
    private int iCntCmInvAcctDist;

    /** List For handling CM_AP_INV_HDR Bulk TBL Accessor */
    private List<CM_AP_INV_HDRTMsg> listCmApInvHdrTMsg;
    /** CM_AP_INV_HDR Bulk Insert Count */
    private int iCntCmApInvHdr;

    /** List For handling CM_AP_INV_DTL Bulk TBL Accessor */
    private List<CM_AP_INV_DTLTMsg> listCmApInvDtlTMsg;
    /** CM_INV_ACCT_DTL Bulk Insert Count */
    private int iCntCmApInvDtl;

    /** List For handling AP_INV_ROSS Bulk TBL Accessor */
    private List<AP_INV_ROSSTMsg> listApInvRossTMsg;
    /** CM_INV_ACCT_DTL Bulk Insert Count */
    private int iCntApInvRoss;

    /** Commit Count (CM_INV_ACCT_DIST) */
    private int cmInvAcctDistCommitCount;
    /** Commit Count (CM_AP_INV_HDR) */
    private int cmApInvHdrCommitCount;
    /** Commit Count (CM_AP_INV_DTL) */
    private int cmApInvDtlCommitCount;
    /** Commit Count (AP_INV_ROSS) */
    private int apInvRossCommitCount;
   /** Total Commit Count */
    private int totalCommitCount;

    /** Canon USA Vendor Code */
    private String cusaApVndCd;
    /** Canon USA Parent Vendor Code */
    private String cusaPrntVndCd;

    /** SPLY_COA_CMPY_CD */
    private String splyCoaCmpyCd;
    /** SPLY_COA_BR_CD */
    private String splyCoaBrCd;
    /** SPLY_COA_CC_CD */
    private String splyCoaCcCd;
    /** SPLY_COA_ACCT_CD */
    private String splyCoaAcctCd;
    /** SPLY_COA_PROD_CD */
    private String splyCoaProdCd;
    /** SPLY_COA_CH_CD */
    private String splyCoaChCd;
    /** SPLY_COA_AFFL_CD */
    private String splyCoaAfflCd;
    /** SPLY_COA_PROJ_CD */
    private String splyCoaProjCd;
    /** SPLY_COA_EXTN_CD */
    private String splyCoaExtnCd;

    /** ACCT_INV_STS_CD */
    private String acctInvStsCd;

    /** VAR_CHAR_CONST : AP_LINE_TP_ITEM */
    private String varCharConstApLineTpItem;

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
        new NFBB124001().executeBatch(NFBB124001.class.getSimpleName());
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
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize List for bulk TBL Accessor */
        initListForBulkTBLAccessor();
        /** Initialize Bulk Update Count */
        initBulkCount();
        /** Initialize Canon USA Vendor Code  */
        getCusaVendorCode(getGlobalCompanyCode(), CONST_KEY_CUSA_AP_VND_CD);
        /** Initialize variables acquired from VND table */
        getCusaVnd();
        /** Initialize Canon USA Parent Vendor Code */
        getCusaPrntVendorCode();
        /** Initialize variables acquired from VAR_CHAR_CONST table */
        acctInvStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NFBB124001_ACCT_INV_STS_CD, this.glblCmpyCd);
        /** Get VAR_CHAR_CONST : AP_LINE_TP_ITEM */
        varCharConstApLineTpItem = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_AP_LINE_TP_ITEM, glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {
        // Get AP_INV_ROSS table value
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cusaApVndCd", cusaApVndCd);
        queryParam.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_AP_INV_ROSS, queryParam, new SelectApInvRossHandler());
        if (bRet == Boolean.TRUE) {
            if (iCntCmInvAcctDist > 0) {
                bulkInsertCmInvAcctDist();
            }
            if (iCntCmApInvHdr > 0) {
                bulkInsertCmApInvHdr();
            }
            if (iCntCmApInvDtl > 0) {
                bulkInsertCmApInvDtl();
            }
            if (iCntApInvRoss > 0) {
                bulkUpdateApInvRoss();
            }
            commit();
        } else {
            rollback();
        }        
    }
    @Override
    protected void termRoutine() {
        // QC#20316(Sol#202) START
        this.totalCommitCount = cmInvAcctDistCommitCount + cmApInvHdrCommitCount + cmApInvDtlCommitCount + apInvRossCommitCount;
        // QC#20316(Sol#202) END
        /** Normal End this process */
        setTermState(termCd, totalCommitCount, 0, 0);
    }

    /**
     * Private class: SelectApInvRossHandler
     */
    private class SelectApInvRossHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            String prevRtlInvNum = EMPTY_STRING;

            while (rs.next()) {

                String rtlInvNum = NFBCommonBusiness.chkNull(rs.getString(RTL_INV_NUM));
                // QC#20316(Sol#202) START
                String rtlInvLineNum = NFBCommonBusiness.chkNull(rs.getString(RTL_INV_LINE_NUM));
                // QC#20316(Sol#202) END
                String mdseCd = NFBCommonBusiness.chkNull(rs.getString(MDSE_CD));
                BigDecimal shipQty = NFBCommonBusiness.chkNull(rs.getBigDecimal(SHIP_QTY));
                BigDecimal acInvJrnlCostAmt = NFBCommonBusiness.chkNull(rs.getBigDecimal(AC_INV_JRNL_COST_AMT));
                String serNum = NFBCommonBusiness.chkNull(rs.getString(SER_NUM));
                String invDt = NFBCommonBusiness.chkNull(rs.getString(INV_DT));
                String rdstcSellToCustCd = NFBCommonBusiness.chkNull(rs.getString(RDSTC_SELL_TO_CUST_CD));
                String mdseDescShortTxt = NFBCommonBusiness.chkNull(rs.getString(MDSE_DESC_SHORT_TXT));
                // QC#20316(Sol#202) START
                String apVndInvLineNum = NFBCommonBusiness.chkNull(rs.getString(AP_VND_INV_LINE_NUM));
                // QC#20316(Sol#202) END
                String cmInvAcctDistLineNum = NFBCommonBusiness.chkNull(rs.getString(CM_INV_ACCT_DIST_LINE_NUM));
                // START 2017/12/19 [QC#23022, ADD]
                String rtlDivCd = NFBCommonBusiness.chkNull(rs.getString(RTL_DIV_CD));
                String sellToCustCd = NFBCommonBusiness.chkNull(rs.getString(SELL_TO_CUST_CD));
                String itrlRtlSmryNum = NFBCommonBusiness.chkNull(rs.getString(ITRL_RTL_SMRY_INV_NUM));
                // END   2017/12/19 [QC#23022, ADD]

                if (ZYPCommonFunc.hasValue(rdstcSellToCustCd)) {
                    // QC#20316(Sol#202) START
                    updateApInvRoss(rtlInvNum, rtlInvLineNum, AP_INV_ROSS_STS_CD_00);
                    // QC#20316(Sol#202) END
                } else {
                    // QC#20316(Sol#202) START
                    updateApInvRoss(rtlInvNum, rtlInvLineNum, AP_INV_ROSS_STS_CD_99);
                    // QC#20316(Sol#202) END
                    prevRtlInvNum = rtlInvNum;
                    continue;
                }

                if (!rtlInvNum.equals(prevRtlInvNum)) {

                    /***************************************************
                     * Set Common Data
                     ***************************************************/
                    // QC#20316(Sol#202) START
                    String ccyCd = getVndCcyCd(cusaApVndCd);
                    // QC#20316(Sol#202) END
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
                    CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
                    // QC#20316(Sol#202) START
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, cusaApVndCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvNum, rtlInvNum);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvTpCd, AP_INV_TP_CD_00);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invDt, invDt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.ccyCd, ccyCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.pmtDueDt, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.vndPmtTermCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctBankCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctBankAcctPayTpCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invTpCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.lastTrxDt, cmProcDt);
                    // MOD:getAcInvJrnlCostAmt
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotFobCostAmt, getAcInvJrnlCostAmt(rtlInvNum));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotTaxAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndCostAmt, cmApInvHdr.acOcTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acOcTotGndInvAmt, cmApInvHdr.acOcTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotFobCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvHdr.acOcTotFobCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotTaxAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndCostAmt, cmApInvHdr.acScTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acScTotGndInvAmt, cmApInvHdr.acScTotFobCostAmt);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.poNum, EMPTY_STRING);
                    
                    //Add Columns from CM_INV_ACCT_HDR
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.locNum, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acInvTotCostAmt, getAcInvJrnlCostAmt(rtlInvNum));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvSrcNm, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvDescTxt, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.payAloneFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invMatchTpCd, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invPmtNum, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invHdrDescTxt, getInvHdrDescTxt(rtlDivCd, sellToCustCd, itrlRtlSmryNum));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.acctInvStsCd, acctInvStsCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apPmtChkNum, EMPTY_STRING);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.ifProcStsCd, FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.apInvCatgCd, AP_INV_CATG_CD_03);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.invAuthDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.prntVndCd, cusaPrntVndCd);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdr.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
                    
                    // QC#20316(Sol#202) END

                    listCmApInvHdrTMsg.add(cmApInvHdr);
                    iCntCmApInvHdr++;
                    if (iCntCmApInvHdr >= INT_BULK_COM_LIM) {
                        bulkInsertCmApInvHdr();
                    }
                }

                // Create CM_INV_ACCT_DIST
                CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, cusaApVndCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, rtlInvNum);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, AP_INV_TP_CD_00);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, rtlInvNum);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.delyOrdNum, rtlInvNum);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invTpCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, shipQty);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invRcvQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, shipQty);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apRejQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.uomCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, acInvJrnlCostAmt);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invAsgDt, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.stkInDt, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.csmpInvNum, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.serNum, serNum);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.contrNum, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crTermCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.custDlrCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocFirstLineAddr, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocFlNm, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocCtyAddr, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocStCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocPostCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invLineDescTxt, EMPTY_STRING);

                String coaCmpyCd= rs.getString(COA_CMPY_CD);
                String coaBrCd= rs.getString(COA_BR_CD);
                String coaCcCd= rs.getString(COA_CC_CD);
                String coaAcctCd= rs.getString(COA_ACCT_CD);
                String coaProdCd= rs.getString(COA_PROD_CD);
                String coaChCd= rs.getString(COA_CH_CD);
                String coaAfflCd= rs.getString(COA_AFFL_CD);
                String coaProjCd= rs.getString(COA_PROJ_CD);
                String coaExtnCd= rs.getString(COA_EXTN_CD);

                Map<String, Object> mapSvcMachMstr= getSvcMachMstrBySerNum(serNum);
                String cpoOrdNum = (String) mapSvcMachMstr.get(CPO_ORD_NUM);
                String fldSvcBrCd = (String) mapSvcMachMstr.get(FLD_SVC_BR_CD);
                boolean existsSerNum = false;
                String dsOrdCatgCd = EMPTY_STRING;
                if (ZYPCommonFunc.hasValue(cpoOrdNum)) {
                    existsSerNum = true;
                    CPOTMsg cpo = new CPOTMsg();
                    ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, cpoOrdNum);
                    cpo = (CPOTMsg) S21FastTBLAccessor.findByKey(cpo);
                    if (cpo != null) {
                        dsOrdCatgCd = cpo.dsOrdCatgCd.getValue();
                    }
                }
                if (COA_PROJ_CD_10.equals(coaProjCd)) {
                    if (existsSerNum) {
                        if (ZYPCommonFunc.hasValue(dsOrdCatgCd) && dsOrdCatgCd.equals(DS_ORD_CATG_CD_0005)) {
                            coaBrCd= COA_BR_CD_201;
                            coaCcCd= COA_CC_CD_894103;
                        } else if (ZYPCommonFunc.hasValue(dsOrdCatgCd) && !dsOrdCatgCd.equals(DS_ORD_CATG_CD_0005)) {
                            List<Map<String, Object>> listDsCpoSlsCr = getDsCpoSlsCr(cpoOrdNum);
                            String slsRepTocCd = EMPTY_STRING;
                            if (listDsCpoSlsCr.size() > 1) {
                                for (int i = 0; i < listDsCpoSlsCr.size(); i++) {
                                    Map<String, Object> mapDsCpoSlsCr = (Map<String, Object>) listDsCpoSlsCr.get(i);
                                    String slsRepRoleTpCd = (String) mapDsCpoSlsCr.get(SLS_REP_ROLE_TP_CD);
                                    if (ZYPCommonFunc.hasValue(slsRepRoleTpCd)
                                    &&  SLS_REP_ROLE_TP_CD_ESSI.equals(slsRepRoleTpCd)) {
                                        slsRepTocCd = (String) mapDsCpoSlsCr.get(SLS_REP_TOC_CD);
                                        break;
                                    }
                                }
                            } else if (listDsCpoSlsCr.size() == 1) {
                                Map<String, Object> mapDsCpoSlsCr = (Map<String, Object>) listDsCpoSlsCr.get(0);
                                slsRepTocCd = (String) mapDsCpoSlsCr.get(SLS_REP_TOC_CD);
                            }
                            if (ZYPCommonFunc.hasValue(slsRepTocCd)) {
                                TOCTMsg toc = new TOCTMsg();
                                ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(toc.tocCd, slsRepTocCd);
                                toc = (TOCTMsg) S21FastTBLAccessor.findByKey(toc);
                                if (toc != null) {
                                    coaBrCd= toc.coaBrCd.getValue();
                                    coaCcCd= toc.coaCcCd.getValue();
                                }
                            }
                        }
                    } else {
                        coaBrCd= COA_BR_CD_201;
                        coaCcCd= COA_CC_CD_894103;
                    }
                } else if (COA_PROJ_CD_73.equals(coaProjCd)) {
                    if (existsSerNum) {
                        coaBrCd = fldSvcBrCd;
                    }
                }
                // QC#20316(Sol#202) START
                // Change COA Name.
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, coaCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, coaBrCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, coaCcCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, coaAcctCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, coaProdCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, coaChCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, coaAfflCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, coaProjCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, coaExtnCd);

                // Move COA from CM_INV_ACCT_HDR
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCmpyCd, splyCoaCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaBrCd, splyCoaBrCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCcCd, splyCoaCcCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAcctCd, splyCoaAcctCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProdCd, splyCoaProdCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaChCd, splyCoaChCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAfflCd, splyCoaAfflCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProjCd, splyCoaProjCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaExtnCd, splyCoaExtnCd);
                
                // QC#20316(Sol#202) END
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apAcctDescTxt, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.ifProcStsCd, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.exchRate, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poOrdDtlLineNum, EMPTY_STRING);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, varCharConstApLineTpItem);
                // START 2017/12/19 [QC#23022, MOD]
                // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, mdseDescShortTxt);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, getMdseDescShortTxt(serNum, mdseDescShortTxt));
                // END   2017/12/19 [QC#23022, MOD]
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, cmInvAcctDistLineNum);
                // QC#20316(Sol#202) START
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, apVndInvLineNum);
                // QC#20316(Sol#202) END

                // START 2018/09/06 [QC#28040, ADD]
                String coaAcctDescTxt = getCoaAcctDescTxt(glblCmpyCd, coaAcctCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apAcctDescTxt, coaAcctDescTxt);

                EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
                int txtDigit = getDigitApInvDescTxt.getDigit();

                if (ZYPCommonFunc.hasValue(coaAcctDescTxt) && coaAcctDescTxt.length() > txtDigit) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, S21StringUtil.subStringByLength(coaAcctDescTxt, 0, txtDigit));
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, coaAcctDescTxt);
                }
                // END 2018/09/06 [QC#28040, ADD]

                listCmInvAcctDistTMsg.add(cmInvAcctDistTMsg);
                iCntCmInvAcctDist++;
                if (iCntCmInvAcctDist >= INT_BULK_COM_LIM) {
                    bulkInsertCmInvAcctDist();
                }

                prevRtlInvNum = rtlInvNum;

                // Create CM_AP_INV_DTL
                CM_AP_INV_DTLTMsg cmApInvDtl = new CM_AP_INV_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndCd, cmInvAcctDistTMsg.apVndCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvNum, cmInvAcctDistTMsg.apVndInvNum);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvSqNum, cmInvAcctDistTMsg.apVndInvSqNum);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndCd, cmInvAcctDistTMsg.apVndCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.vndInvNum, cmInvAcctDistTMsg.apVndInvNum);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseCd, cmInvAcctDistTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.delyOrdNum, cmInvAcctDistTMsg.delyOrdNum);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvTpCd, cmInvAcctDistTMsg.apInvTpCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.poNum, cmInvAcctDistTMsg.poNum);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.invTpCd, cmInvAcctDistTMsg.invTpCd);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.invQty, cmInvAcctDistTMsg.invQty);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcFobCostAmt, acInvJrnlCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotCostAmt, cmApInvDtl.acOcFobCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acOcTotInvAmt, cmApInvDtl.acOcFobCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScFobCostAmt, NFBCommonBusiness.calcStdAmt(cmApInvDtl.acOcFobCostAmt.getValue(), exchRate, acctArthTpCd, aftDeclPntDigitNum));
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotCostAmt, cmApInvDtl.acScFobCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.acScTotInvAmt, cmApInvDtl.acScFobCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.invAsgDt, cmInvAcctDistTMsg.invAsgDt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.applyExchRate, exchRate);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.trnstJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.stkInJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.invRcvQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.origScFobCostAmt, cmApInvDtl.acScFobCostAmt);
                if (BigDecimal.ZERO.compareTo(cmApInvDtl.invQty.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocUnitExtCostAmt, BigDecimal.ZERO);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocUnitExtCostAmt, acInvJrnlCostAmt.divide(cmApInvDtl.invQty.getValue(), aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_DOWN));
                }
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.ocExtCostAmt, cmApInvDtl.acOcFobCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.scUnitExtCostAmt, cmApInvDtl.ocUnitExtCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.poQty, cmInvAcctDistTMsg.poQty);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.thisMthFobCostAmt, cmInvAcctDistTMsg.thisMthFobCostAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entDealNetUnitPrcAmt, cmInvAcctDistTMsg.entDealNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entPoDtlDealNetAmt, cmInvAcctDistTMsg.entPoDtlDealNetAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entFuncNetUnitPrcAmt, cmInvAcctDistTMsg.entFuncNetUnitPrcAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.entPoDtlFuncNetAmt, cmInvAcctDistTMsg.entPoDtlFuncNetAmt);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.exchRate, cmInvAcctDistTMsg.exchRate);
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.poOrdDtlLineNum, cmInvAcctDistTMsg.poOrdDtlLineNum);
                // QC#20316(Sol#202) START
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.apVndInvLineNum, cmInvAcctDistTMsg.apVndInvLineNum);
                // QC#20316(Sol#202) END
                ZYPEZDItemValueSetter.setValue(cmApInvDtl.mdseDescShortTxt, cmInvAcctDistTMsg.mdseDescShortTxt);

                listCmApInvDtlTMsg.add(cmApInvDtl);
                iCntCmApInvDtl++;
                if (iCntCmApInvDtl >= INT_BULK_COM_LIM) {
                    bulkInsertCmApInvDtl();
                }
            }
            return Boolean.TRUE;
        }

        // START 2017/12/19 [QC#23022, ADD]
        private String getInvHdrDescTxt(String rtlDivCd, String sellToCustCd, String itrlRtlSmryNum) {
            StringBuilder builder = new StringBuilder();
            builder.append(rtlDivCd);
            builder.append(SPACE);
            builder.append(sellToCustCd);
            builder.append(SPACE);
            builder.append(itrlRtlSmryNum);
            return builder.toString();
        }

        private String getMdseDescShortTxt(String serNum, String mdseDescShortTxt) {
            StringBuilder builder = new StringBuilder();
            builder.append(serNum);
            builder.append(SPACE);
            builder.append(mdseDescShortTxt);
            return builder.toString();
        }
        // END   2017/12/19 [QC#23022, ADD]
    }

    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.cmInvAcctDistCommitCount = 0;
        this.cmApInvHdrCommitCount = 0;
        this.cmApInvDtlCommitCount = 0;
        this.apInvRossCommitCount = 0;
        this.totalCommitCount = 0;
    }

    /**
     * Initialize list for bulk TBL accessor.
     */
    private void initListForBulkTBLAccessor() {
        this.listCmInvAcctDistTMsg = new ArrayList<CM_INV_ACCT_DISTTMsg>();
        this.listCmApInvHdrTMsg = new ArrayList<CM_AP_INV_HDRTMsg>();
        this.listCmApInvDtlTMsg = new ArrayList<CM_AP_INV_DTLTMsg>();
        this.listApInvRossTMsg = new ArrayList<AP_INV_ROSSTMsg>();
    }

    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntCmInvAcctDist = 0;
        this.iCntCmApInvHdr = 0;
        this.iCntCmApInvDtl = 0;
        this.iCntApInvRoss = 0;
    }

    /**
     * Bulk insert CM_INV_ACCT_DIST table.
     */
    private void bulkInsertCmInvAcctDist() {
        CM_INV_ACCT_DISTTMsg[] cmInvAcctDistTMsgs = listCmInvAcctDistTMsg.toArray(new CM_INV_ACCT_DISTTMsg[listCmInvAcctDistTMsg.size()]);
        int iRet = S21FastTBLAccessor.insert(cmInvAcctDistTMsgs);
        if (iRet > 0) {
            cmInvAcctDistCommitCount = cmInvAcctDistCommitCount + iRet;
            listCmInvAcctDistTMsg = new ArrayList<CM_INV_ACCT_DISTTMsg>();
            iCntCmInvAcctDist = 0;
        } else {
            cmInvAcctDistCommitCount = 0;
            throw new S21AbendException(ZZBM0074E);
        }
    }

    /**
     * Bulk insert CM_AP_INV_HDR table.
     */
    private void bulkInsertCmApInvHdr() {
        CM_AP_INV_HDRTMsg[] cmApInvHdrTMsgs = listCmApInvHdrTMsg.toArray(new CM_AP_INV_HDRTMsg[listCmApInvHdrTMsg.size()]);
        int iRet = S21FastTBLAccessor.insert(cmApInvHdrTMsgs);
        if (iRet > 0) {
            cmApInvHdrCommitCount = cmApInvHdrCommitCount + iRet;
            listCmApInvHdrTMsg = new ArrayList<CM_AP_INV_HDRTMsg>();
            iCntCmApInvHdr = 0;
        } else {
            cmApInvHdrCommitCount = 0;
            throw new S21AbendException(ZZBM0074E);
        }
    }

    /**
     * Bulk insert CM_AP_INV_DTL table.
     */
    private void bulkInsertCmApInvDtl() {
        CM_AP_INV_DTLTMsg[] cmApInvDtlTMsgs = listCmApInvDtlTMsg.toArray(new CM_AP_INV_DTLTMsg[listCmApInvDtlTMsg.size()]);
        int iRet = S21FastTBLAccessor.insert(cmApInvDtlTMsgs);
        if (iRet > 0) {
            cmApInvDtlCommitCount = cmApInvDtlCommitCount + iRet;
            listCmApInvDtlTMsg = new ArrayList<CM_AP_INV_DTLTMsg>();
            iCntCmApInvDtl = 0;
        } else {
            cmApInvDtlCommitCount = 0;
            throw new S21AbendException(ZZBM0074E);
        }
    }

    /**
     * Bulk update AP_INV_ROSS table.
     */
    private void bulkUpdateApInvRoss() {
        AP_INV_ROSSTMsg[] apInvRossTMsgs = listApInvRossTMsg.toArray(new AP_INV_ROSSTMsg[listApInvRossTMsg.size()]);
        int iRet = S21FastTBLAccessor.update(apInvRossTMsgs);
        if (iRet > 0) {
            apInvRossCommitCount = apInvRossCommitCount + iRet;
            listApInvRossTMsg = new ArrayList<AP_INV_ROSSTMsg>();
            iCntApInvRoss = 0;
        } else {
            apInvRossCommitCount = 0;
            throw new S21AbendException(NFBM0028E);
        }
    }

    /**
     * getCusaVendorCode
     * @param glblCmpyCd String 
     * @param constKey String 
     */
    private void getCusaVendorCode(String glblCmpyCd, String constKey) {
        cusaApVndCd = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);
        if (cusaApVndCd == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + constKey);
        }
    }

    /**
     * <pre>
     * getCusaVnd
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private void getCusaVnd() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cusaApVndCd", cusaApVndCd);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_CUSA_VND, ssmParam);
        if (resultList == null || resultList.size() == 0) {
            splyCoaCmpyCd = EMPTY_STRING;
            splyCoaBrCd = EMPTY_STRING;
            splyCoaCcCd = EMPTY_STRING;
            splyCoaAcctCd = EMPTY_STRING;
            splyCoaProdCd = EMPTY_STRING;
            splyCoaChCd = EMPTY_STRING;
            splyCoaAfflCd = EMPTY_STRING;
            splyCoaProjCd = EMPTY_STRING;
            splyCoaExtnCd = EMPTY_STRING;
        } else {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
            splyCoaCmpyCd = (String) resultMap.get(SPLY_COA_CMPY_CD);
            splyCoaBrCd = (String) resultMap.get(SPLY_COA_BR_CD);
            splyCoaCcCd = (String) resultMap.get(SPLY_COA_CC_CD);
            splyCoaAcctCd = (String) resultMap.get(SPLY_COA_ACCT_CD);
            splyCoaProdCd = (String) resultMap.get(SPLY_COA_PROD_CD);
            splyCoaChCd = (String) resultMap.get(SPLY_COA_CH_CD);
            splyCoaAfflCd = (String) resultMap.get(SPLY_COA_AFFL_CD);
            splyCoaProjCd = (String) resultMap.get(SPLY_COA_PROJ_CD);
            splyCoaExtnCd = (String) resultMap.get(SPLY_COA_EXTN_CD);
        }
    }

    /**
     * <pre>
     * getCusaPrntVendorCode
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private void getCusaPrntVendorCode() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("invVndCd", cusaApVndCd);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_PRNT_VND_CD, ssmParam);
        if (resultList == null || resultList.size() == 0) {
            cusaPrntVndCd = EMPTY_STRING;
        } else {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
            cusaPrntVndCd = (String) resultMap.get(PRNT_VND_CD);
        }
    }

    /**
     * getVndCcyCd
     */
    private String getVndCcyCd(String vndCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("vndCd", vndCd);

        String ccyCd = (String) ssmBatchClient.queryObject(SELECT_VND_DEAL_CCY_CD, ssmParam);

        return ccyCd;
    }

    /**
     * <pre>
     * checkSvcMachMstrSerNum
     * </pre>
     * @param serNum String
     * @return resultMap Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getSvcMachMstrBySerNum(String serNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("serNum", serNum);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(GET_SVC_MACH_MSTR_BY_SER_NUM, ssmParam);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (resultList != null && resultList.size() > 0) {
            resultMap = (Map<String, Object>) resultList.get(0);
        }
        return resultMap;
    }

    /**
     * <pre>
     * getDsCpoSlsCr
     * </pre>
     * @param cpoOrdNum String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsCpoSlsCr(String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(GET_DS_CPO_SLS_CR, ssmParam);
        return resultList;
    }

    /**
     * <pre>
     * getAcInvJrnlCostAmt
     * </pre>
     */
    private BigDecimal getAcInvJrnlCostAmt(String rtlInvNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cusaApVndCd", rtlInvNum);
        BigDecimal acInvJrnlCostAmt = (BigDecimal) ssmBatchClient.queryObject(SELECT_AC_INV_JRNL_COST_AMT, ssmParam);
        if (!ZYPCommonFunc.hasValue(acInvJrnlCostAmt)) {
            return BigDecimal.ZERO;
        } else {
            return acInvJrnlCostAmt;
        }
    }

    /**
     * <pre>
     * updateApInvRoss
     * </pre>
     * @param rtlInvNum String
     * @param rtlInvLineNum String
     * @param apInvRossStsCd String
     */
    @SuppressWarnings("unchecked")
    private void updateApInvRoss(String rtlInvNum, String rtlInvLineNum, String apInvRossStsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlInvNum", rtlInvNum);
        // QC#20316(Sol#202) START
        ssmParam.put("rtlInvLineNum", rtlInvLineNum);
        // QC#20316(Sol#202) START
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_AP_INV_ROSS_BY_PARTIAL_VALUE, ssmParam);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                boolean blUpdate = false;
                resultMap = (Map<String, Object>) resultList.get(i);
                AP_INV_ROSSTMsg apInvRossTMsg = new AP_INV_ROSSTMsg();
                ZYPEZDItemValueSetter.setValue(apInvRossTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(apInvRossTMsg.rtlInvPk, (BigDecimal)resultMap.get(RTL_INV_PK));
                ZYPEZDItemValueSetter.setValue(apInvRossTMsg.rtlInvLinePk, (BigDecimal)resultMap.get(RTL_INV_LINE_PK));
                if (apInvRossStsCd.equals(AP_INV_ROSS_STS_CD_00)) {
                    String currentApInvRossStsCd = (String)resultMap.get(AP_INV_ROSS_STS_CD);
                    if (currentApInvRossStsCd.equals(AP_INV_ROSS_STS_CD_99)) {
                        blUpdate = true;
                    } else {
                        blUpdate = false;
                    }
                } else {
                    blUpdate = true;
                }
                if (blUpdate) {
                    apInvRossTMsg = (AP_INV_ROSSTMsg)S21FastTBLAccessor.findByKeyForUpdate(apInvRossTMsg);
                    ZYPEZDItemValueSetter.setValue(apInvRossTMsg.apInvRossStsCd, apInvRossStsCd);
                    listApInvRossTMsg.add(apInvRossTMsg);
                    iCntApInvRoss++;
                    if (iCntApInvRoss >= INT_BULK_COM_LIM) {
                        bulkUpdateApInvRoss();
                    }
                }
            }
        }
    }

    // START 2018/09/06 [QC#28040, ADD]
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
    // END 2018/09/06 [QC#28040, ADD]
}

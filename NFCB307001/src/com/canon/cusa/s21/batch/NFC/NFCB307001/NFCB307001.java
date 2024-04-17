/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB307001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_CR_REBIL_RSNTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CCYTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.DS_INV_SLS_CRTMsgArray;
import business.db.DS_INV_TPTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INVTMsg;
import business.db.INV_BOLTMsg;
import business.db.INV_BOLTMsgArray;
import business.db.INV_LINETMsg;
import business.db.INV_LINETMsgArray;
import business.db.INV_PRMO_INFOTMsg;
import business.db.INV_PRMO_INFOTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.VND_INV_BOL_WRKTMsg;
import business.db.VND_INV_BOL_WRKTMsgArray;
import business.db.VND_INV_ERR_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsgArray;
import business.db.VND_INV_WRKTMsg;
import business.parts.NFZC103001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC103001.NFZC103001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_CLM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * CSMP Credit Memo Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   CSAI            Miyauchi        Create          N/A
 * 2016/07/27   Fujitsu         S.Yoshida       Update          QC#12202,10359,12432,12551
 * 2016/07/29   Fujitsu         S.Yoshida       Update          QC#12627
 * 2016/08/02   Fujitsu         C.Tanaka        Update          QC#12535
 * 2016/08/04   Fujitsu         C.Tanaka        Update          QC#12760
 * 2016/10/14   Hitachi         K.Kojima        Update          QC#15130
 * 2016/11/16   Hitachi         T.Tsuchida      Update          QC#15881
 * 2016/11/17   Fujitsu         S.Fujita        Update          QC#15882
 * 2017/11/17   Hitachi         E.Kameishi      Update          QC#19735
 * 2018/02/06   Hitachi         E.Kameishi      Update          QC#23018
 * </pre>
 */
public class NFCB307001 extends S21BatchMain implements NFCB307001Constant {

	/** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Batch Process Date */
    private String batchProcDt;
    /** Normal Record Count */
    private int normalRcCnt = 0;
    /** Total Record Count */
    private int totRcCnt = 0;
    /** Error Record Count */
    private int errRcCnt = 0;
    // START 2016/10/14 K.Kojima [QC#15130,ADD]
    /** PMT_TERM_CD */
    private String pmtTermCd = null;
    /** PMT_TERM_NM */
    private String pmtTermNm = null;
    // END 2016/10/14 K.Kojima [QC#15130,ADD]

    /**
     * @param args
     */
    public static void main(String[] args) {
    	new NFCB307001().executeBatch(NFCB307001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        S21InfoLogOutput.println("initRoutine Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = getGlobalCompanyCode();
        this.batchProcDt = ZYPDateUtil.getBatProcDate();
        normalRcCnt = 0;
        totRcCnt = 0;
        errRcCnt = 0;

        //

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");

        // START 2016/10/14 K.Kojima [QC#14586,MOD]
        // super.setTermState(TERM_CD.WARNING_END, this.normalRcCnt,
        // this.errRcCnt, this.totRcCnt);
        TERM_CD termCd = TERM_CD.NORMAL_END;
        if (this.errRcCnt > 0) {
            termCd = TERM_CD.WARNING_END;
        }
        super.setTermState(termCd, this.normalRcCnt, this.errRcCnt, this.totRcCnt);
        // END 2016/10/14 K.Kojima [QC#14586,MOD]

        S21InfoLogOutput.println("termRoutine Method End");
    }

    @Override
    protected void mainRoutine() {
        S21InfoLogOutput.println("mainRoutine Method Start");

        //Get VAR_CHAR_CONST : NFBB101301_CR_DR_RSN_CD
        String crDrRsnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFCB307001_CR_DR_RSN_CD.toString(), this.glblCmpyCd);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(SQL_ARG_J.GLBL_CMPY_CD.getValue(), this.glblCmpyCd);
//Mod Start QC#12760
        //queryMap.put(SQL_ARG_J.AR_PROCESS_FLG_P.getValue(), AR_PROCESS_FLG_P);
        List<String> invArProcStsCdList = new ArrayList<String>();
        invArProcStsCdList.add(AR_PROCESS_FLG_P);
        invArProcStsCdList.add(AR_PROCESS_FLG_E);
        queryMap.put(SQL_ARG_J.INV_AR_PROC_STS_CD.getValue(), invArProcStsCdList);
//Mod End QC#12760
        queryMap.put(SQL_ARG_J.CR_DR_RSN_CD.getValue(), crDrRsnCd);

        List<Map> ssmResult = (List<Map>) this.ssmBatchClient.queryObjectList("getTarget", queryMap);

        if ( ssmResult.size() <= 0) {
            totRcCnt = 0;
            normalRcCnt = 0;
            errRcCnt = 0;
            return;
        } else {
            // START 2016/10/14 K.Kojima [QC#15130,ADD]
            setPmtTermData();
            // END 2016/10/14 K.Kojima [QC#15130,ADD]

            VND_INV_WRKTMsg vndInvWrkTMsg = new VND_INV_WRKTMsg();
            String origInvNum = "";
            // START 2018/02/06 E.Kameishi [QC#23018, ADD]
            String cpoOrdNum = "";
            // START 2018/02/06 E.Kameishi [QC#23018, ADD]
            totRcCnt = ssmResult.size();
            for ( int iCnt=0; iCnt<ssmResult.size(); iCnt++ ) {
                /** set queryMap */
                vndInvWrkTMsg = new VND_INV_WRKTMsg();
                vndInvWrkTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                vndInvWrkTMsg.vndInvNum.setValue((String) ssmResult.get(iCnt).get(DTO_COL_NAME.VND_INV_NUM.toString()));
                /** getTarget (*Non Process Transaction Data */
                vndInvWrkTMsg = (VND_INV_WRKTMsg) EZDTBLAccessor.findByKey(vndInvWrkTMsg);
                // START 2016/11/16 T.Tsuchida [QC#15881,ADD]
                if (!INV_TP.CREDIT_MEMO.equals(ssmResult.get(iCnt).get(DTO_COL_NAME.INV_TP_CD.toString()))) {
                    //A/R Process to Success
                    updateVndInvInforForSuccess(vndInvWrkTMsg.vndInvNum.getValue());
                    normalRcCnt ++;
                    continue;
                }
                // END 2016/11/16 T.Tsuchida [QC#15881,ADD]

                // START 2018/02/06 E.Kameishi [QC#23018, MOD]
                List<Map> invData = validInv((String) vndInvWrkTMsg.custIssPoNum.getValue());
                cpoOrdNum = (String) invData.get(0).get(DTO_COL_NAME.CPO_ORD_NUM.toString());
                origInvNum = (String) invData.get(0).get(DTO_COL_NAME.INV_NUM.toString());
                //origInvNum = validInv((String) vndInvWrkTMsg.custIssPoNum.getValue());
                // START 2018/02/06 E.Kameishi [QC#23018, MOD]
                if ( origInvNum == null ) {
                    updateVndInvInforForError(vndInvWrkTMsg.vndInvNum.getValue());
//Add Start QC#12551
                    updateVndInvErrWrk(vndInvWrkTMsg.vndInvNum.getValue());
//Add End   QC#12551
                    errRcCnt++;
                    continue;
                } else {
                    vndInvWrkTMsg.origVndInvNum.setValue(origInvNum);
                }

                // In case find out the invoice, create the Credit Memo
                // START 2018/02/06 E.Kameishi [QC#23018, MOD]
                if (!createCredit(vndInvWrkTMsg, cpoOrdNum) ) {
                // END 2018/02/06 E.Kameishi [QC#23018, MOD]
                    updateVndInvInforForError(vndInvWrkTMsg.vndInvNum.getValue());
                    errRcCnt++;
                    continue;
                } else {
                    //A/R Process to Success
                    updateVndInvInforForSuccess(vndInvWrkTMsg.vndInvNum.getValue());
                    normalRcCnt ++;
                }
            }
        }
        S21InfoLogOutput.println("mainRoutine Method Start");
    }

    // START 2018/02/06 E.Kameishi [QC#23018, MOD]
    private List<Map> validInv(String invNum) {

        //Get Invoice#
        String[] invNumSplit = invNum.split(SPLIT_SYMBOL.HYPHEN.getValue());

        //Check Split
        if ( invNumSplit.length < 2 ) {
            return null;
        }

        //Search Invoice#

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(SQL_ARG_J.GLBL_CMPY_CD.getValue(), this.glblCmpyCd);
        queryMap.put(SQL_ARG_J.INV_NUM.getValue(), invNumSplit[0]);
        queryMap.put(SQL_ARG_J.INV_TP_CD_1.getValue(), INV_TP.INVOICE);

        List<Map> ssmResult = (List<Map>) this.ssmBatchClient.queryObjectList("findInvoiceInfo", queryMap);

        if ( ssmResult.size() <= 0) {
            return null;
        }

        return ssmResult;
    }
    // START 2018/02/06 E.Kameishi [QC#23018, MOD]

    private boolean updateVndInvInforForError(String vndInvNum) {

        rollback();
        VND_INV_WRKTMsg vndInvWrk = new VND_INV_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(vndInvWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvNum, vndInvNum);

        //Get ForUpdate data	
        vndInvWrk = (VND_INV_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(vndInvWrk);

        //Set Process Status for Vendor Invoice
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invArProcStsCd, AR_PROCESS_FLG_E);

        EZDTBLAccessor.update(vndInvWrk);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvWrk.getReturnCode())) {
            return false;
        }
        commit();
        return true;
    }

//Add Start QC#12551
    private boolean updateVndInvErrWrk(String vndInvNum) {
        rollback();

        //Insert VND_INV_ERR_WRK
        VND_INV_ERR_WRKTMsg insVndInvErrWrkTMsg = new VND_INV_ERR_WRKTMsg();
        insVndInvErrWrkTMsg.glblCmpyCd.setValue(glblCmpyCd);
        insVndInvErrWrkTMsg.vndInvErrWrkPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.VND_INV_ERR_WRK_SQ));
        insVndInvErrWrkTMsg.vndInvNum.setValue(vndInvNum);
        insVndInvErrWrkTMsg.vndInvErrMsgId.setValue(ERROR_MSG.NFCM0857E.name());
        EZDMessageInfo msgInfo = new EZDMessageInfo(ERROR_MSG.NFCM0857E.name(), new String[] {"CSMP Original Invoice"});
        insVndInvErrWrkTMsg.batErrMsgTxt.setValue(msgInfo.getMessage());

        EZDTBLAccessor.insert(insVndInvErrWrkTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insVndInvErrWrkTMsg.getReturnCode())) {
            return false;
        }

        commit();
        return true;
    }
//Add End   QC#12551

    private boolean updateVndInvInforForSuccess(String vndInvNum) {

        VND_INV_WRKTMsg vndInvWrk = new VND_INV_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(vndInvWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvNum, vndInvNum);

        //Get ForUpdate data	
        vndInvWrk = (VND_INV_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(vndInvWrk);

        //Set Process Status for Vendor Invoice
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invArProcStsCd, AR_PROCESS_FLG_P);

        EZDTBLAccessor.update(vndInvWrk);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvWrk.getReturnCode())) {
            return false;
        }

        commit();
        return true;
    }
    
    private boolean createCredit( VND_INV_WRKTMsg vndInvWrkTMsg, String cpoOrdNum ) {
        
        String arCrTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFCL307001_AR_CR_TP_CD.toString(), this.glblCmpyCd);
        String invTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFCL307001_DS_INV_TP_CRM_CSMP.toString(), this.glblCmpyCd);

        // Check exists Credit Rebill Reason
        //if (!isArCrRebilRsnCd(getGlobalCompanyCode(), crRebillRsnCd)) {
        //    return false;
        //}

        // Get Original Invoice
        INVTMsg origInvTMsg = getOrigInvTMsg(getGlobalCompanyCode(), vndInvWrkTMsg.origVndInvNum.getValue());
        if (origInvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(origInvTMsg.getReturnCode())) {
            return false;
        }

        // Tax
        //if (AR_CR_TP.TAX.equals(arCrTpCd)) {
        //    if (!isCreditAmountLessThanOrigInv(vndInvWrkTMsg.invTotDealTaxAmt.getValue(), origInvTMsg.invTotDealTaxAmt.getValue())) {
        //        return false;
        //    }
        // Freight
        //} else if (AR_CR_TP.FREIGHT.equals(arCrTpCd)) {
        //    if (!isCreditAmountLessThanOrigInv(vndInvWrkTMsg.invTotDealFrtAmt.getValue(), origInvTMsg.invTotDealFrtAmt.getValue())) {
        //        return false;
        //    }
            // Receivables
        //} else if (AR_CR_TP.RECEIVABLES.equals(arCrTpCd)) {
        //    if (!isCreditAmountLessThanOrigInv(vndInvWrkTMsg.invTotDealSlsAmt.getValue(), origInvTMsg.invTotDealSlsAmt.getValue())) {
        //        return false;
        //    }
        //}

        // get scale
        if (!getFuncCcyScale(getGlobalCompanyCode())) {
            return false;
        }
        // Check calculated amount under zero
        // zero check of calculated amount
        if (!isCalAmountUnderZero( vndInvWrkTMsg.invTotDealSlsAmt.getValue(), vndInvWrkTMsg.invTotDealFrtAmt.getValue(), vndInvWrkTMsg.invTotDealTaxAmt.getValue() )) {
            return false;
        }

        // Register Invoice Table (*CREDIT ONLY)
        //bizMsg.arCrRebilTpCd.setValue(AR_CR_REBIL_TP.CREDIT_ONLY);
        if (!registInvoiceTables(getGlobalCompanyCode(), origInvTMsg, vndInvWrkTMsg, invTpCd, cpoOrdNum) ) {
            return false;
        }

        return true;

    }

    /**
     * isArCrRebilRsnCd
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean isArCrRebilRsnCd(String glblCmpyCd, String crRebilRsnCd) {
        AR_CR_REBIL_RSNTMsg tMsg = new AR_CR_REBIL_RSNTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.arCrRebilRsnCd.setValue(crRebilRsnCd);

        return EZDTBLAccessor.findByKey(tMsg) != null;
    }

    /**
     * getOrigInvTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @return INVTMsg
     */
    public static INVTMsg getOrigInvTMsg(String glblCmpyCd, String origInvNum) {
    	INVTMsg tMsg = new INVTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.invNum.setValue(origInvNum);

        return (INVTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
    }

//Del Start QC#12432
//    /**
//     * isInvoiceLineNum
//     * 
//     * @param bizMsg NFCL3070CMsg
//     * @return boolean
//     */
//    public static VND_INV_LINE_WRKTMsgArray isInvoiceLineNum(String glblCmpyCd, String vndInvNum) {
//
//        VND_INV_LINE_WRKTMsg vndInvLine = new VND_INV_LINE_WRKTMsg();
//        VND_INV_LINE_WRKTMsgArray vndInvLineList = new VND_INV_LINE_WRKTMsgArray();
//        
//        vndInvLine.glblCmpyCd.setValue(glblCmpyCd);
//        vndInvLine.vndInvNum.setValue(vndInvNum);
//        
//
//         vndInvLineList = (VND_INV_LINE_WRKTMsgArray) EZDTBLAccessor.findByCondition(vndInvLine);
//
//        if ( !(vndInvLineList == null || vndInvLineList.length() <= 0) ) {
//
//            return vndInvLineList;
//        }
//        
//        return null;
//    }
//Del End   QC#12432

    /**
     * registInvoiceTables
     * 
     * @param bizMsg NFCL3070CMsg
     * @param origInvTMsg INVTMsg
     * @return boolean
     */
    public boolean registInvoiceTables(String glblCmpyCd, INVTMsg origInvTMsg, VND_INV_WRKTMsg vndInvWrkTMsg, String invTpCd, String cpoOrdNum) {

        // get original DS_INV_TPTMsg
        DS_INV_TPTMsg dsInvTpTMsg = getDsInvTpTMsg(glblCmpyCd, invTpCd);
        if (dsInvTpTMsg == null) {
            return false;
        }
        // get Account Date
        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", glblCmpyCd);
        AR_ACCT_DTTMsg outArAcctDtMsg = findArAcctDtInfo(glblCmpyCd, subSysCd);
        if (outArAcctDtMsg == null) {
            return false;
        }
        // get CopyTo Invoice Number
        // START 2017/11/17 E.Kameishi [QC#19735, MOD]
        //String creditInvNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
        String creditInvNum = ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
        // END 2017/11/17 E.Kameishi [QC#19735, MOD]
        if (!ZYPCommonFunc.hasValue(creditInvNum)) {
            return false;
        }

//Add Start QC#10359
        origInvTMsg.csmpInvClmStsCd.setValue(CSMP_INV_CLM_STS.APPLIED);
        EZDTBLAccessor.update(origInvTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(origInvTMsg.getReturnCode())) {
            return false;
        }
//Add End   QC#10359

        // START 2016/04/13 S.Fujita [S21 NA Unit Test QC#6565,MOD]
        // set System Source Code
        //ZYPEZDItemValueSetter.setValue(bizMsg.sysSrcCd, invTMsg.sysSrcCd);

        // ##### INV_LINE #####
        // TargetLineLevel
//Mod Start QC#12432
        if (!insertInvLineWithDSByCondition(glblCmpyCd, creditInvNum, vndInvWrkTMsg)) {
//Mod End   QC#12432
            return false;
        }
        // ##### INV_BOL #####
        // TargetLineLevel
        if (!insertInvBolByCondition(glblCmpyCd, creditInvNum, vndInvWrkTMsg)) {
            return false;
        }
        // ##### INV #####
        if (!insertInv(origInvTMsg, vndInvWrkTMsg, creditInvNum, subSysCd, outArAcctDtMsg.acctDt.getValue(), cpoOrdNum) ) {
            return false;
        }
        // ##### INV_PRMO_INFO #####
        if (!insertInvPrmoInfo(glblCmpyCd, creditInvNum, vndInvWrkTMsg)) {
            return false;
        }
        // create slsCrPkMap
        Map<BigDecimal, BigDecimal> slsCrPkMap = new HashMap<BigDecimal, BigDecimal>();
        // ##### DS_INV_SLS_CR #####
        if (!insertDsInvSlsCr(glblCmpyCd, creditInvNum, vndInvWrkTMsg, slsCrPkMap)) {
            return false;
        }
        // ##### AJE_INV_ACCT_DIST #####
        if (!callAcctDistAPI(glblCmpyCd, creditInvNum, ZYPDateUtil.getBatProcDate())) {
            return false;
        }

        return true;
    }

    /**
     * getDsInvTpTMsg
     * 
     * @param String glblCmpyCd
     * @param String dsInvTpCd
     * @return DS_INV_TPTMsg
     */
    public static DS_INV_TPTMsg getDsInvTpTMsg(String glblCmpyCd, String dsInvTpCd) {
        DS_INV_TPTMsg tMsg = new DS_INV_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.dsInvTpCd.setValue(dsInvTpCd);

        return (DS_INV_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * findArAcctDtInfo
     * 
     * @param bizMsg NFCL3070CMsg
     * @param subSysCd String
     * @return AR_ACCT_DTTMsg
     */
    public static AR_ACCT_DTTMsg findArAcctDtInfo(String glblCmpyCd, String subSysCd) {

        AR_ACCT_DTTMsg tMsg = new AR_ACCT_DTTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.subSysCd.setValue(subSysCd);
        tMsg.onlBatTpCd.setValue(ONL_BAT_TP);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(tMsg);

        return outMsg;
    }

    /**
     * getInvBolTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @return tMsg INV_BOLTMsg
     */
    public static VND_INV_BOL_WRKTMsgArray getInvBolTMsg(String glblCmpyCd, String origInvNum) {
        VND_INV_BOL_WRKTMsg tMsg = new VND_INV_BOL_WRKTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.vndInvNum.setValue(origInvNum);

        return (VND_INV_BOL_WRKTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    /**
     * getInvLineTMsg
     * 
     * @param bizMsg NFCL3070CMsg
     * @return tMsg INV_LINETMsg
     */
    public static INV_LINETMsg getInvLineTMsg(String glblCmpyCd, String origInvNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxnum) {
        INV_LINETMsg tMsg = new INV_LINETMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.invNum.setValue(origInvNum);
        tMsg.invBolLineNum.setValue(invBolLineNum);
        tMsg.invLineNum.setValue(invLineNum);
        tMsg.invLineSubNum.setValue(invLineSubNum);
        tMsg.invLineSubTrxNum.setValue(invLineSubTrxnum);

        return (INV_LINETMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * isCreditAmountOverInvBalance
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean isCreditAmountOverInvBalance(String glblCmpyCd, String vndInvNum, BigDecimal rebillAmount) {

        // AR Transaction Balance
        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        arTrxBalTMsg.setSQLID("001");
        arTrxBalTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        arTrxBalTMsg.setConditionValue("arTrxNum01", vndInvNum);

        AR_TRX_BALTMsgArray arTrxBalTMsgAry = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(arTrxBalTMsg);
        if (arTrxBalTMsgAry.length() > 0) {
            if (!isCreditAmountLessThanOrigInv(rebillAmount, arTrxBalTMsgAry.no(0).dealRmngBalGrsAmt.getValue())) {
                return false;
            }
        }

        return true;
    }

    /**
     * isCreditAmountLessThanOrigInv
     * 
     * @param crAmt BigDecimal
     * @param origInv BigDecimal
     * @return boolean
     */
    public static boolean isCreditAmountLessThanOrigInv(BigDecimal crAmt, BigDecimal origInv) {
        if (!ZYPCommonFunc.hasValue(crAmt) || !ZYPCommonFunc.hasValue(origInv)) {
            return false;
        }

        if (crAmt.compareTo(BigDecimal.ZERO) != 0) {
            if (crAmt.compareTo(origInv) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * getFuncCcyScale
     * 
     * @param bizMsg NFCL3070CMsg
     * @return boolean
     */
    public static boolean getFuncCcyScale(String glblCmpyCd) {
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(glblCmpyCd);

        glblTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg == null) {
            return false;
        }
        // getFuncCcyScale
        CCYTMsg ccyTMsg = new CCYTMsg();
        ccyTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyTMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());

        ccyTMsg = (CCYTMsg) S21CodeTableAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null || ccyTMsg.aftDeclPntDigitNum.getValue() == null) {
            return false;
        }

        return true;
    }

    /**
     * isCalAmountUnderZero
     * 
     * @param bizMsg NFCL3070CMsg
     * @param origInvTMsg INVTMsg
     * @return boolean
     */
    public static boolean isCalAmountUnderZero(BigDecimal invTotDealSlsAmt, BigDecimal invTotDealFrtAmt, BigDecimal invTotDealTaxAmt) {
        boolean isSuccess = true;

        BigDecimal invAmt = invTotDealSlsAmt;
        BigDecimal frtAmt = invTotDealFrtAmt;
        BigDecimal taxAmt = invTotDealTaxAmt;

        if (AR_CR_TP.RECEIVABLES.equals(AR_CR_TP_DEFAULT)) {
            if (invAmt.compareTo(BigDecimal.ZERO) == 0) {
                isSuccess = false;
            }
        } else if (AR_CR_TP.FREIGHT.equals(AR_CR_TP_DEFAULT)) {
            if (frtAmt.compareTo(BigDecimal.ZERO) == 0) {
                isSuccess = false;
            }
        } else if (AR_CR_TP.TAX.equals(AR_CR_TP_DEFAULT)) {
            if (taxAmt.compareTo(BigDecimal.ZERO) == 0) {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

//Mod Start QC#12432
    /**
     * insertInvLineByCondition
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param invTMsg INVTMsg
     * @param dsInvTpTMsg DS_INV_TPTMsg
     * @param dsInvLineTaxDtlTMsgList List<DS_INV_LINE_TAX_DTLTMsg>
     * @param calPct BigDecimal
     * @return boolean
     */
    public static boolean insertInvLineWithDSByCondition(String glblCmpyCd, String creditInvNum, VND_INV_WRKTMsg vndInvWrkTMsg) {

        String origVndInvNum = vndInvWrkTMsg.origVndInvNum.getValue();

        // get original TMsg Array
        INV_LINETMsg invLineTMsg = new INV_LINETMsg();

        invLineTMsg.setSQLID("003");
        invLineTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        invLineTMsg.setConditionValue("invNum01", origVndInvNum);

        INV_LINETMsgArray invLineTMsgAry = (INV_LINETMsgArray) EZDTBLAccessor.findByCondition(invLineTMsg);
        if (invLineTMsgAry.length() == 0) {
            return false;
        }
        invLineTMsg = invLineTMsgAry.no(0);

        // get child Vendor Invoice
        VND_INV_LINE_WRKTMsg vndInvLineWrkTMsg = new VND_INV_LINE_WRKTMsg();

        vndInvLineWrkTMsg.setSQLID("004");
        vndInvLineWrkTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        vndInvLineWrkTMsg.setConditionValue("vndInvNum01", vndInvWrkTMsg.vndInvNum.getValue());

        VND_INV_LINE_WRKTMsgArray vndInvLineWrkTMsgAry = (VND_INV_LINE_WRKTMsgArray) EZDTBLAccessor.findByCondition(vndInvLineWrkTMsg);
        if (vndInvLineWrkTMsgAry.length() == 0) {
            return false;
        }

        // set Vendor TMsg Array to copy to TMsg
        List<INV_LINETMsg> insInvLineTMsgList = new ArrayList<INV_LINETMsg>();
        INV_LINETMsg insInvLineTMsg = null;
        for (int i = 0; i < vndInvLineWrkTMsgAry.getValidCount(); i++) {
            vndInvLineWrkTMsg = vndInvLineWrkTMsgAry.no(i);

            insInvLineTMsg = new INV_LINETMsg();
            EZDMsg.copy(invLineTMsg, null, insInvLineTMsg, null);
            insInvLineTMsg.shpgPlnNum.clear();
            insInvLineTMsg.invNum.setValue(creditInvNum);
            insInvLineTMsg.invBolLineNum.setValue(vndInvLineWrkTMsg.vndInvBolLineNum.getValue());
            insInvLineTMsg.invLineNum.setValue(vndInvLineWrkTMsg.vndInvLineNum.getValue());
            insInvLineTMsg.invLineSubNum.setValue(vndInvLineWrkTMsg.vndInvLineSubNum.getValue());
            insInvLineTMsg.invLineSubTrxNum.setValue(vndInvLineWrkTMsg.vndInvLineSubTrxNum.getValue());
            insInvLineTMsg.dealNetUnitPrcAmt.setValue(vndInvLineWrkTMsg.dealNetUnitPrcAmt.getValue());
            insInvLineTMsg.invLineDealTaxAmt.setValue(vndInvLineWrkTMsg.vndInvLineDealTaxAmt.getValue());
            insInvLineTMsg.invLineDealNetAmt.setValue(vndInvLineWrkTMsg.vndInvLineDealNetAmt.getValue());
            insInvLineTMsg.dealDiscUnitPrcAmt.setValue(vndInvLineWrkTMsg.dealDiscUnitPrcAmt.getValue());
            insInvLineTMsg.funcNetUnitPrcAmt.setValue(vndInvLineWrkTMsg.dealNetUnitPrcAmt.getValue());
            insInvLineTMsg.invLineFuncTaxAmt.setValue(vndInvLineWrkTMsg.vndInvLineDealTaxAmt.getValue());
            insInvLineTMsg.invLineFuncNetAmt.setValue(vndInvLineWrkTMsg.vndInvLineDealNetAmt.getValue());
            insInvLineTMsg.funcDiscUnitPrcAmt.setValue(vndInvLineWrkTMsg.dealDiscUnitPrcAmt.getValue());
            insInvLineTMsg.origInvNum.setValue(origVndInvNum);
            insInvLineTMsg.dealGrsUnitPrcAmt.setValue(vndInvLineWrkTMsg.dealGrsUnitPrcAmt.getValue());
            insInvLineTMsg.dealGrsTotPrcAmt.setValue(vndInvLineWrkTMsg.dealGrsTotPrcAmt.getValue());
            insInvLineTMsg.funcGrsUnitPrcAmt.setValue(vndInvLineWrkTMsg.dealGrsUnitPrcAmt.getValue());
            insInvLineTMsg.funcGrsTotPrcAmt.setValue(vndInvLineWrkTMsg.dealGrsTotPrcAmt.getValue());

            insInvLineTMsgList.add(insInvLineTMsg);
        }

        if (!insInvLineTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insInvLineTMsgList.toArray(new INV_LINETMsg[0]));
            if (insCnt != insInvLineTMsgList.size()) {
                return false;
            }
        }

        return true;
    }
//Mod End   QC#12432

    /**
     * insertInvBolByCondition
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param dsInvTpTMsg DS_INV_TPTMsg
     * @param insInvBolTMsgList List<INV_BOLTMsg>
     * @param dsInvLineTaxDtlTMsgList List<DS_INV_LINE_TAX_DTLTMsg>
     * @param calPct BigDecimal
     * @return boolean
     */
    public static boolean insertInvBolByCondition(String glblCmpyCd, String creditInvNum, VND_INV_WRKTMsg vndInvWrkTMsg) {
        // get original TMsg Array
        INV_BOLTMsg tMsg = new INV_BOLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("invNum01", vndInvWrkTMsg.origVndInvNum.getValue());

        INV_BOLTMsgArray invBolTMsgAry = (INV_BOLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (invBolTMsgAry.length() == 0) {
            return false;
        }

        // set original TMsg Array to copy to TMsg
        for (int i = 0; i < invBolTMsgAry.getValidCount(); i++) {
            INV_BOLTMsg insInvBolTMsg = new INV_BOLTMsg();

            EZDTMsg.copy(invBolTMsgAry.get(i), null, insInvBolTMsg, null);
            ZYPEZDItemValueSetter.setValue(insInvBolTMsg.invNum, creditInvNum);

            EZDTBLAccessor.insert(insInvBolTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insInvBolTMsg.getReturnCode()) ) {
                 return false;
            }
        }

        return true;
    }


    /**
     * insertInv
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invTMsg INVTMsg
     * @param invNum String
     * @param subSysCd String
     * @param acctDt String
     * @return INVTMsg
     */
    public boolean insertInv(INVTMsg invTMsg, VND_INV_WRKTMsg vndInvWrkTMsg, String creditInvNum, String subSysCd, String acctDt, String cpoOrdNum) {
        INVTMsg insInvTMsg = new INVTMsg();
        EZDTMsg.copy(invTMsg, null, insInvTMsg, null);

        ZYPEZDItemValueSetter.setValue(insInvTMsg.invNum, creditInvNum);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.origInvNum, vndInvWrkTMsg.origVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(insInvTMsg.acctDt, acctDt);
//Mod Start QC#12627
        ZYPEZDItemValueSetter.setValue(insInvTMsg.netDueDt, batchProcDt);
//Mod End   QC#12627
        ZYPEZDItemValueSetter.setValue(insInvTMsg.sysSrcCd, subSysCd);
        insInvTMsg.grpInvNum.clear();

        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealNetAmt, vndInvWrkTMsg.invTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealSlsAmt, vndInvWrkTMsg.invTotDealSlsAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealFrtAmt, vndInvWrkTMsg.invTotDealFrtAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealTaxAmt, vndInvWrkTMsg.invTotDealTaxAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotDealDiscAmt, vndInvWrkTMsg.invTotDealDiscAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncNetAmt, vndInvWrkTMsg.invTotDealNetAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncSlsAmt, vndInvWrkTMsg.invTotDealSlsAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncFrtAmt, vndInvWrkTMsg.invTotDealFrtAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncTaxAmt, vndInvWrkTMsg.invTotDealTaxAmt);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTotFuncDiscAmt, vndInvWrkTMsg.invTotDealDiscAmt);

        ZYPEZDItemValueSetter.setValue(insInvTMsg.invTpCd, INV_TP.CREDIT_MEMO);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.crInvTpCd, CR_INV_TP.CREDIT_MEMO);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invPrintStsCd, ZYPConstant.FLG_OFF_0);

//Add Start QC#12627
        ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermStartDt, vndInvWrkTMsg.vndPmtTermStartDt);
        // START 2016/10/14 K.Kojima [QC#15130,ADD]
        // ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCd, vndInvWrkTMsg.vndPmtTermCd);
        // ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermNm, ZYPCodeDataUtil.getName(PMT_TERM.class, glblCmpyCd, vndInvWrkTMsg.vndPmtTermCd.getValue()));
        ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCd, this.pmtTermCd);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermNm, this.pmtTermNm);
        // END 2016/10/14 K.Kojima [QC#15130,ADD]
        ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCashDiscCd, PMT_TERM_CASH_DISC.BALANCE_DUE);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.pmtTermCashDiscDescTxt, ZYPCodeDataUtil.getName(PMT_TERM_CASH_DISC.class, glblCmpyCd, PMT_TERM_CASH_DISC.BALANCE_DUE));
//Add End   QC#12627

        if (invTMsg.invTotDealNetAmt.getValue().compareTo(vndInvWrkTMsg.invTotDealNetAmt.getValue()) >= 0) {
            ZYPEZDItemValueSetter.setValue(insInvTMsg.origInvNum, vndInvWrkTMsg.origVndInvNum);
        } else {
            insInvTMsg.origInvNum.clear();
        }

        ZYPEZDItemValueSetter.setValue(insInvTMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_ON_Y);

        // START 2018/02/06 E.Kameishi [QC#23018, ADD]
        ZYPEZDItemValueSetter.setValue(insInvTMsg.srcSysDocNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(insInvTMsg.invDt, acctDt);
        // START 2018/02/06 E.Kameishi [QC#23018, ADD]
        // insert
        EZDTBLAccessor.insert(insInvTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insInvTMsg.getReturnCode()) ) {
             return false;
        }
        return true;
    }

    /**
     * insertInvPrmoInfo
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @return boolean
     */
    public static boolean insertInvPrmoInfo(String glblCmpyCd, String creditInvNum, VND_INV_WRKTMsg vndInvWrkTMsg) {
        // get original TMsg Array
        INV_PRMO_INFOTMsg tMsg = new INV_PRMO_INFOTMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("invNum01", vndInvWrkTMsg.origVndInvNum.getValue());

        INV_PRMO_INFOTMsgArray invPrmoInfoTMsgAry = (INV_PRMO_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (invPrmoInfoTMsgAry.length() == 0) {
            return false;
        }

        // set original TMsg Array to copy to TMsg
        List<INV_PRMO_INFOTMsg> insInvPrmoInfoTMsgList = new ArrayList<INV_PRMO_INFOTMsg>();
        for (int i = 0; i < invPrmoInfoTMsgAry.getValidCount(); i++) {
            INV_PRMO_INFOTMsg insInvPrmoInfoTMsg = new INV_PRMO_INFOTMsg();

            EZDTMsg.copy(invPrmoInfoTMsgAry.get(i), null, insInvPrmoInfoTMsg, null);
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoTMsg.invPrmoInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INV_PRMO_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(insInvPrmoInfoTMsg.invNum, creditInvNum);

            // insert
            EZDTBLAccessor.insert(insInvPrmoInfoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insInvPrmoInfoTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * insertDsInvSlsCr
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param calPct BigDecimal
     * @param slsCrPkMap Map<BigDecimal, BigDecimal>
     * @return boolean
     */
    public static boolean insertDsInvSlsCr(String glblCmpyCd, String creditInvNum, VND_INV_WRKTMsg vndInvWrkTMsg, Map<BigDecimal, BigDecimal> slsCrPkMap) {

        String trxCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFCB307001_TRX_CD.toString(), glblCmpyCd);
        String trxRsnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST.NFCB307001_TRX_RSN_CD.toString(), glblCmpyCd);

        // get original TMsg Array
        DS_INV_SLS_CRTMsg tMsg = new DS_INV_SLS_CRTMsg();
        tMsg.setSQLID("001");

        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("invNum01", vndInvWrkTMsg.origVndInvNum.getValue());

        DS_INV_SLS_CRTMsgArray dsInvSlsCrTMsgAry = (DS_INV_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (dsInvSlsCrTMsgAry.length() == 0) {
            return false;
        }
        // QC#12535 MOD Start
        tMsg = dsInvSlsCrTMsgAry.no(0);

        // get child Vendor Invoice
        VND_INV_LINE_WRKTMsg vndInvLineWrkTMsg = new VND_INV_LINE_WRKTMsg();

        vndInvLineWrkTMsg.setSQLID("004");
        vndInvLineWrkTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        vndInvLineWrkTMsg.setConditionValue("vndInvNum01", vndInvWrkTMsg.vndInvNum.getValue());

        VND_INV_LINE_WRKTMsgArray vndInvLineWrkTMsgAry = (VND_INV_LINE_WRKTMsgArray) EZDTBLAccessor.findByCondition(vndInvLineWrkTMsg);
        if (vndInvLineWrkTMsgAry.length() == 0) {
            return false;
        }

        // set Vendor TMsg Array to copy to TMsg
        List<DS_INV_SLS_CRTMsg> insDsInvSlsCrTMsgList = new ArrayList<DS_INV_SLS_CRTMsg>();
        DS_INV_SLS_CRTMsg insDsInvSlsCrTMsg = null;
        BigDecimal pct100 = new BigDecimal(100);
        for (int i = 0; i < vndInvLineWrkTMsgAry.getValidCount(); i++) {
            vndInvLineWrkTMsg = vndInvLineWrkTMsgAry.no(i);
            insDsInvSlsCrTMsg = new DS_INV_SLS_CRTMsg();
            EZDTMsg.copy(tMsg, null, insDsInvSlsCrTMsg, null);

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dsInvSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_SLS_CR_SQ));

            BigDecimal orgSlsCrPk = dsInvSlsCrTMsgAry.no(i).dsInvSlsCrPk.getValue();
            BigDecimal newSlsCrPk = insDsInvSlsCrTMsg.dsInvSlsCrPk.getValue();
            // put orgSlsCrPk and newSlsCrPk
            slsCrPkMap.put(orgSlsCrPk, newSlsCrPk);

            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invNum, creditInvNum);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invBolLineNum, vndInvLineWrkTMsg.vndInvBolLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invLineNum, vndInvLineWrkTMsg.vndInvLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invLineSubNum, vndInvLineWrkTMsg.vndInvLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invTrxLineSubNum, vndInvLineWrkTMsg.vndInvLineSubTrxNum.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.mdseCd, vndInvLineWrkTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.invLineSplPct, pct100);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.slsRepCrPct, pct100);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSlsCrAmt, vndInvLineWrkTMsg.dealNetUnitPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSlsCrAmt, vndInvLineWrkTMsg.dealNetUnitPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealOrigDfrdAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcOrigDfrdAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealDfrdBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcDfrdBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.dealSchdRevAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.funcSchdRevAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.revRecogCnt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.firstRevRecogDt, "");
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.nextRevRecogDt, "");
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.revRecogProcStsCd, "");
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.trxCd, trxCd);
            ZYPEZDItemValueSetter.setValue(insDsInvSlsCrTMsg.trxRsnCd, trxRsnCd);
            insDsInvSlsCrTMsgList.add(insDsInvSlsCrTMsg);
        }

        if (!insDsInvSlsCrTMsgList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insDsInvSlsCrTMsgList.toArray(new DS_INV_SLS_CRTMsg[0]));
            if (insCnt != insDsInvSlsCrTMsgList.size()) {
                return false;
            }
        }
        // QC#12535 MOD End

        return true;
    }

    /**
     * insertAjeInvAcctDist
     * 
     * @param bizMsg NFCL3070CMsg
     * @param invNum String
     * @param acctDt String
     * @param calPct BigDecimal
     * @param slsCrPkMap Map<BigDecimal, BigDecimal>
     * @return boolean
     */
    /*public static boolean insertAjeInvAcctDist(String glblCmpyCd, String creditInvNum, VND_INV_WRKTMsg vndInvWrkTMsg, String acctDt, Map<BigDecimal, BigDecimal> slsCrPkMap) {
        // get original TMsg Array
        AJE_INV_ACCT_DISTTMsg tMsg = new AJE_INV_ACCT_DISTTMsg();
        tMsg.setSQLID("001");

        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("invNum01", vndInvWrkTMsg.origVndInvNum.getValue());

        AJE_INV_ACCT_DISTTMsgArray ajeInvAcctDistTMsgAry = (AJE_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (ajeInvAcctDistTMsgAry.length() == 0) {
            return false;
        }

        // initialize amounts
        BigDecimal jrnlDealTotAmt = BigDecimal.ZERO;
        BigDecimal jrnlDealCalTotAmt = BigDecimal.ZERO;
        BigDecimal jrnlFuncTotAmt = BigDecimal.ZERO;
        BigDecimal jrnlFuncCaltotAmt = BigDecimal.ZERO;
        // set original TMsg Array to copy to TMsg
        List<AJE_INV_ACCT_DISTTMsg> ajeInvAcctDistTMsgList = new ArrayList<AJE_INV_ACCT_DISTTMsg>();

        for (int i = 0; i < ajeInvAcctDistTMsgAry.getValidCount(); i++) {
            AJE_INV_ACCT_DISTTMsg ajeInvAcctDistTMsg = new AJE_INV_ACCT_DISTTMsg();

            EZDTMsg.copy(ajeInvAcctDistTMsgAry.get(i), null, ajeInvAcctDistTMsg, null);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.ajeInvAcctDistPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AJE_INV_ACCT_DIST_SQ));
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.invNum, creditInvNum);

            BigDecimal newSlsCrPk  = slsCrPkMap.get(ajeInvAcctDistTMsgAry.no(i).dsInvSlsCrPk.getValue());
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.dsInvSlsCrPk, newSlsCrPk);
            ajeInvAcctDistTMsg.jrnlCratDt.clear();
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.glDt, acctDt);
            ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.procStsCd, ZYPConstant.FLG_OFF_0);
            // not Tax or Freight
            if (!(ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.FRT_19) || ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.TAX_20))) {
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, ajeInvAcctDistTMsgAry.no(i).jrnlDealAmt.getValue().negate());
                ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, ajeInvAcctDistTMsgAry.no(i).jrnlFuncAmt.getValue().negate());
            // Tax or Freight
            } else {
                // get TaxAmt or FrtAmt From INV_BOL
                INV_BOLTMsg bolTMsg = new INV_BOLTMsg();
                bolTMsg.glblCmpyCd.setValue(glblCmpyCd);
                bolTMsg.invNum.setValue(creditInvNum);
                bolTMsg.invBolLineNum.setValue(ajeInvAcctDistTMsgAry.no(i).invBolLineNum.getValue());

                INV_BOLTMsg invBolTMsg = (INV_BOLTMsg) S21CacheTBLAccessor.findByKey(bolTMsg);
                if (invBolTMsg == null) {
                    return false;
                }
                // for Tax
                if (ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.TAX_20)) {
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.totBolDealTaxAmt.getValue().negate());
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.totBolFuncTaxAmt.getValue().negate());
                }
                // for Freight
                if (ajeInvAcctDistTMsgAry.no(i).ajeInvAcctClsCd.getValue().equals(AJE_LINE_IND_TP.FRT_19)) {
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlDealAmt, invBolTMsg.shipDealFrtAmt.getValue().negate());
                    ZYPEZDItemValueSetter.setValue(ajeInvAcctDistTMsg.jrnlFuncAmt, invBolTMsg.shipFuncFrtAmt.getValue().negate());
                }
            }

            // insert
            EZDTBLAccessor.insert(ajeInvAcctDistTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ajeInvAcctDistTMsg.getReturnCode())) {
                return false;
            }
        }

        return true;
    }*/

  //---- start add 2016/03/22 QC#5770
    public static boolean callAcctDistAPI(String glblCmpyCd, String creditInvNum, String invDt) {

        NFZC103001 invDistAPI = new NFZC103001();
        List<NFZC103001PMsg> pMsgList = new ArrayList<NFZC103001PMsg>();

        NFZC103001PMsg pMsg = new NFZC103001PMsg();
        setValue(pMsg.glblCmpyCd, glblCmpyCd);
        setValue(pMsg.invNum, creditInvNum);
        setValue(pMsg.procDt, invDt);
        pMsgList.add(pMsg);

        // call one by one to update INV.
        invDistAPI.excute(pMsgList, ONBATCH_TYPE.ONLINE);

        for(NFZC103001PMsg pmsg : pMsgList) {
            // update FNLZ_INV_FLG of INV table
            INVTMsg invTmsg = new INVTMsg();

            setValue(invTmsg.glblCmpyCd, glblCmpyCd);
            setValue(invTmsg.invNum, creditInvNum);

            invTmsg = (INVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(invTmsg);
            
            if (invTmsg == null) {
                return false;
            }

            if (pmsg.xxMsgIdList.getValidCount() == 0) {
                // no error
                setValue(invTmsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
            // START 2016/11/17 S.Fujita [QC#15882,MOD]
//                EZDTBLAccessor.update(invTmsg);
            } else if(pmsg.xxMsgIdList.getValidCount() > 0) {
                setValue(invTmsg.fnlzInvFlg, ZYPConstant.FLG_OFF_N);
            }
            EZDTBLAccessor.update(invTmsg);
            // END   2016/11/17 S.Fujita [QC#15882,MOD]
        }
        return true;
    }

    // START 2016/10/14 K.Kojima [QC#15130,ADD]
    /**
     * setPmtTermData
     */
    private void setPmtTermData() {
        PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, PMT_TERM_CASH_DISC.BALANCE_DUE);
        tMsg = (PMT_TERM_CASH_DISCTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.pmtTermCd)) {
            this.pmtTermCd = tMsg.pmtTermCd.getValue();
            this.pmtTermNm = ZYPCodeDataUtil.getName(PMT_TERM.class, this.glblCmpyCd, this.pmtTermCd);
        }
    }
    // END 2016/10/14 K.Kojima [QC#15130,ADD]
}
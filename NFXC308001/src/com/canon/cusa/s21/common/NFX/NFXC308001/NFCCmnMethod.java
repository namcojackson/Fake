/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * 
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/10/2009   FSSL       M.Moriyama   Create          N/A
 * 02/16/2010   Canon      K.Usui       Update          ADD For fixed value
 * 08/02/2010   Canon      I.Kondo      Update          Merge.
 * 11/03/2010   Canon      I.Kondo      Update          DefID:M15
 * 09/26/2011   Fujitsu    T.Tanaka     Update          ITG#363113
 * 01/09/2012   CSAI       Y.Suga       Update          ITG#375490
 * 07/10/2013   Fujitsu    K.Kimura     Update          WDS#1368 Hard-coding modification
 * 04/07/2016   CSAI       K.Uramori    Update          Write-Off request search
 * 06/30/2017   Hitachi    E.Kameishi   Update          QC#18595
 * 09/20/2018   Fujitsu    T.Ogura      Update          QC#28097
 * 2018/10/24   Hitachi    J.Kim        Update          QC#28892
 * 2018/11/05   Fujitsu    Y.Matsui     Update          QC#29122
 * 2019/07/31   Hitachi    Y.Takeno     Update          QC#51896
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC308001;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;

import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_RCPT_UN_APPLYTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.AR_WRT_OFF_RQSTTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;


// import parts.common.EZDDebugOutput;

/**
 */
public class NFCCmnMethod implements ZYPConstant{

    /**
     * @param strVal String
     * @return String
     */
    public static String convertDbString(String strVal) {

        if (strVal == null) {
            return NFCConst.CST_DB_INIT_VAL_STR;
        } else {
            return strVal;
        }
    }

    /**
     * @param strVal String
     * @return String
     */
    public static String convertDt(String strVal) {

        if (strVal == null) {
            return NFCConst.CST_NULL_CONV_DATE;
        } else {
            return strVal;
        }

    }

    /**
     * @param strVal String
     * @return String
     */
    public static String convertNullDt(String strVal) {

        if (NFCConst.CST_NULL_CONV_DATE.equals(strVal)) {
            return NFCConst.CST_DB_INIT_VAL_STR;
        } else {
            return strVal;
        }

    }

    /**
     * @param strVal String
     * @param type String
     * @return false:convert NG date true:convert OK date
     */
    public static boolean isDate(String strVal, String type) {

        String chkDate = S21StringUtil.trimString(strVal);

        if (S21StringUtil.isEmpty(chkDate)) {
            return false;
        }

        DateFormat format = new SimpleDateFormat(type);
        format.setLenient(false);
        try {
            format.parse(chkDate);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @param glblCmpyCd String
     * @param ccyCd String
     * @return BigDecimal
     */
    public static BigDecimal getAftDeclPntDigitNum(String glblCmpyCd, String ccyCd) {

        // input check.
        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            return null;
        }

        if (S21StringUtil.isEmpty(ccyCd)) {
            return null;
        }

        CCYTMsg keyInfo = new CCYTMsg();
        keyInfo.glblCmpyCd.setValue(glblCmpyCd);
        keyInfo.ccyCd.setValue(ccyCd);

        CCYTMsg result = (CCYTMsg) S21CacheTBLAccessor.findByKey(keyInfo);

        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(result.getReturnCode())) {
            return result.aftDeclPntDigitNum.getValue();
        } else {
            // [NFCM0041E]System error has occurred. Please contact IT
            // Support.
            throw new S21AbendException("NFCM0041E");
        }
    }

    /**
     * Get AR_RCPT_TRX_TP List
     * @param glblCmpyCd String
     * @param bizId String
     * @param exptFlg String
     * @return List
     */
    public static S21SsmEZDResult getArRcptTrxTpCdList(String bizId, String glblCmpyCd, String exptFlg) {

        String arRcptTrxTpCdList = null;

        if ("NFCL0210".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0210_RCPT_TRX_TP_DOM, glblCmpyCd);
            } else {
                arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0210_RCPT_TRX_TP_EXPT, glblCmpyCd);
            }
        } else if ("NFCL0660".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0660_RCPT_TRX_TP_DOM, glblCmpyCd);
            } else {
                arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0660_RCPT_TRX_TP_EXPT, glblCmpyCd);
            }
        } else if ("NFCL0840".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0840_RCPT_TRX_TP_DOM, glblCmpyCd);
            } else {
                arRcptTrxTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0840_RCPT_TRX_TP_EXPT, glblCmpyCd);
            }
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arRcptTrxTpCd", arRcptTrxTpCdList);
        S21SsmEZDResult ssmResult = NFXC308001Query.getInstance().getArRcptTrxTpList(ssmParam);

        return (S21SsmEZDResult) ssmResult;
    }

    /**
     * Get AR_RCPT_TP List
     * @param glblCmpyCd String
     * @param bizId String
     * @param exptFlg String
     * @return List
     */
    public static S21SsmEZDResult getArRcptTpCdList(String bizId, String glblCmpyCd, String exptFlg) {

        String arRcptTpCdList = null;

        if ("NFCL0210".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arRcptTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0210_RCPT_TP_DOM, glblCmpyCd);
            } else {
                arRcptTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0210_RCPT_TP_EXPT, glblCmpyCd);
            }
        } else if ("NFCL0660".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arRcptTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0660_RCPT_TP_DOM, glblCmpyCd);
            } else {
                arRcptTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0660_RCPT_TP_EXPT, glblCmpyCd);
            }
        } else if ("NFCL0840".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arRcptTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0840_RCPT_TP_DOM, glblCmpyCd);
            } else {
                arRcptTpCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0840_RCPT_TP_EXPT, glblCmpyCd);
            }
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arRcptTpCd", arRcptTpCdList);
        S21SsmEZDResult ssmResult = NFXC308001Query.getInstance().getArRcptTpList(ssmParam);

        return (S21SsmEZDResult) ssmResult;
    }

    /**
     * Get AR_BANK_ACCT List
     * @param bizId String
     * @param glblCmpyCd String
     * @param exptFlg String
     * @return List
     */
    public static S21SsmEZDResult getBankAcctList(String bizId, String glblCmpyCd, String exptFlg, String ccyCd) {

        String arBankAcctCdList = null;

        if ("NFCL0210".equals(bizId)) {
            if (!ZYPConstant.FLG_ON_Y.equals(exptFlg)) {
                arBankAcctCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0210_BANK_ACCT_DOM, glblCmpyCd);
            } else {
                arBankAcctCdList = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.AR_NFCL0210_BANK_ACCT_EXPT, glblCmpyCd);
            }
        }

        String newList = "'" + arBankAcctCdList.replaceAll(",", "','") + "'";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arBankAcctCd", newList);
        ssmParam.put("ccyCd", ccyCd);
        S21SsmEZDResult ssmResult = NFXC308001Query.getInstance().getArBankAcctList(ssmParam);

        return (S21SsmEZDResult) ssmResult;
    }

    /**
     * getEndofMonth
     * @param date String "YYYYMMDD'
     * @return lastDay String "YYYYMMDD"
     */
    public static String getEndofMonth(String date) {

        String lastDay = null;
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        int day = c.getActualMaximum(Calendar.DATE);

        lastDay = date.substring(0, 6) + day;

        return lastDay;
    }

    /**
     * @param glDt
     * @param arTrxDt
     * @return
     */
    public static boolean isDepositDateAndTrxDate(String glDt, String arTrxDt) {
        
        //args null case is true
        if(S21StringUtil.isEmpty(glDt) ||  S21StringUtil.isEmpty(arTrxDt)){
            return true;
        }

        int a = Integer.parseInt(glDt.substring(0, 6));
        int b = Integer.parseInt(arTrxDt.substring(0, 6));

        if (a < b) {
            return false;
        }
        return true;
    }
    
    /**
     * @param glblCmpyCd String
     * @return stdCcyCd String
     */
    public static String getDealCcyCd(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        GLBL_CMPYTMsg outMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);
        if (outMsg != null) {
            return outMsg.stdCcyCd.getValue();
        } else {
            return NFCConst.CST_DB_INIT_VAL_STR;
        }
    }
    
    /**
     * getWrtOffTargetData
     * @param batClt S21SsmBatchClient
     * @param param AR_WTR_OFF_RQSTTMsg
     * @param arTrxTpList List<String>
     * @return List<WrtOffTargetTrxBal>
     */
    @SuppressWarnings("unchecked")
    public static List<WrtOffTargetTrxBal> getWrtOffTargetData(AR_WRT_OFF_RQSTTMsg param, List<String> arTrxTpList) {
        List<WrtOffTargetTrxBal> resultListCons = null;
        List<WrtOffTargetTrxBal> resultList = new ArrayList<WrtOffTargetTrxBal>();
        
       // ** Get Target Ar Transaction **
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("rqstPk", param.arWrtOffRqstPk.getValue());
        queryParam.put("lowRmngAmt", param.lowRmngBalAmt.getValue());
        queryParam.put("highRmngAmt", param.highRmngBalAmt.getValue());
        queryParam.put("lowInvNum", param.lowInvNum.getValue());
        queryParam.put("highInvNum", param.highInvNum.getValue());
        queryParam.put("lowInvDueDt", param.lowInvDueDt.getValue());
        queryParam.put("highInvDueDt", param.highInvDueDt.getValue());
        queryParam.put("lowDsAcctNum", param.lowDsAcctNum.getValue());
        queryParam.put("highDsAcctNum", param.highDsAcctNum.getValue());
        queryParam.put("lowBillToCustCd", param.lowBillToCustCd.getValue());
        queryParam.put("highBillToCustCd", param.highBillToCustCd.getValue());
        queryParam.put("invTp", param.invTpCd.getValue());
        // START 2018/11/05 [QC#29122, MOD]
        // queryParam.put("arTrxTpRCP", AR_TRX_TP.RECEIPT);
        queryParam.put("arTrxTpINV", AR_TRX_TP.INVOICE);
        queryParam.put("arTrxTpCRM", AR_TRX_TP.CREDIT_MEMO);
        // END 2018/11/05 [QC#29122, MOD]
        queryParam.put("cashAppStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("cashAppStsP", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTpList", arTrxTpList);
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("flgN", FLG_OFF_N);
        
        // If the request is made by Write-Off screen or strategy, doesn't matter if it is consolidated invoice.
        if (AR_WRT_OFF_RQST_TP.SYSTEM.equals(param.arWrtOffRqstTpCd.getValue()) || AR_WRT_OFF_RQST_TP.USER_REQUEST.equals(param.arWrtOffRqstTpCd.getValue())) {
            queryParam.put("consChk", "Y");
        } else {
            queryParam.put("consChk", null);
        }
        
        // other than consolidated invoice
        S21SsmEZDResult ssmResult = NFXC308001Query.getInstance().getWrtOffTargetData(queryParam);
        
        if (ssmResult.isCodeNormal() || ssmResult.isCodeNotFound()) {
            resultList = (List<WrtOffTargetTrxBal>) ssmResult.getResultObject();
        } else {
            return null;
        }
        
        // If consolidated invoice included flag is 'Y' then get consolidated invoices, too. 
        if (FLG_ON_Y.equals(param.inclConslInvFlg.getValue())) {
             
            ssmResult = NFXC308001Query.getInstance().getWrtOffTargetDataForCons(queryParam);
            if (ssmResult.isCodeNormal() || ssmResult.isCodeNotFound()) {
                resultListCons = (List<WrtOffTargetTrxBal>) (List<WrtOffTargetTrxBal>) ssmResult.getResultObject();
            } else {
                return null;
            }
        }
        
        if (resultListCons != null && resultListCons.size() > 0) {
            resultList.addAll(resultListCons);
        }
        
        return resultList;
    }
    
    
    //---- start 2016/04/07 QC#6751 For research in write off request screen
    /**
     * getWrtOffTargetDataResrc
     * @param batClt S21SsmBatchClient
     * @param param AR_WTR_OFF_RQSTTMsg
     * @param arTrxTpList List<String>
     * @return List<WrtOffTargetTrxBal>
     */
    @SuppressWarnings("unchecked")
    public static List<WrtOffTargetTrxBal> getWrtOffTargetDataResrc(AR_WRT_OFF_RQSTTMsg param, List<String> arTrxTpList) {
        List<WrtOffTargetTrxBal> resultListCons = null;
        List<WrtOffTargetTrxBal> resultList = new ArrayList<WrtOffTargetTrxBal>();
        
       // ** Get Target Ar Transaction **
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("rqstPk", param.arWrtOffRqstPk.getValue());
        queryParam.put("lowRmngAmt", param.lowRmngBalAmt.getValue());
        queryParam.put("highRmngAmt", param.highRmngBalAmt.getValue());
        queryParam.put("lowInvNum", param.lowInvNum.getValue());
        queryParam.put("highInvNum", param.highInvNum.getValue());
        queryParam.put("lowInvDueDt", param.lowInvDueDt.getValue());
        queryParam.put("highInvDueDt", param.highInvDueDt.getValue());
        queryParam.put("lowDsAcctNum", param.lowDsAcctNum.getValue());
        queryParam.put("highDsAcctNum", param.highDsAcctNum.getValue());
        queryParam.put("lowBillToCustCd", param.lowBillToCustCd.getValue());
        queryParam.put("highBillToCustCd", param.highBillToCustCd.getValue());
        queryParam.put("invTp", param.invTpCd.getValue());
        // START 2018/10/24 J.Kim [QC#28892, MOD]
        // queryParam.put("arTrxTpRCP", AR_TRX_TP.RECEIPT);
        queryParam.put("arTrxTpINV", AR_TRX_TP.INVOICE);
        queryParam.put("arTrxTpCRM", AR_TRX_TP.CREDIT_MEMO);
        // END 2018/10/24 J.Kim [QC#28892, MOD]
        queryParam.put("cashAppStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("cashAppStsP", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTpList", arTrxTpList);
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("flgN", FLG_OFF_N);
        
        // If the request is made by Write-Off screen or strategy, doesn't matter if it is consolidated invoice.
        if (AR_WRT_OFF_RQST_TP.SYSTEM.equals(param.arWrtOffRqstTpCd.getValue()) || AR_WRT_OFF_RQST_TP.USER_REQUEST.equals(param.arWrtOffRqstTpCd.getValue())) {
            queryParam.put("consChk", "Y");
        } else {
            queryParam.put("consChk", null);
        }

        // START 2019/07/31 [QC#51896, ADD]
        if (AR_WRT_OFF_RQST_TP.SYSTEM.equals(param.arWrtOffRqstTpCd.getValue())) {
            queryParam.put("arAdjExclCustCdisNull", "Y");
        } else {
            queryParam.put("arAdjExclCustCdisNull", null);
        }
        // END   2019/07/31 [QC#51896, ADD]

        // other than consolidated invoice
        S21SsmEZDResult ssmResult = NFXC308001Query.getInstance().getWrtOffTargetDataResrc(queryParam);
        
        if (ssmResult.isCodeNormal() || ssmResult.isCodeNotFound()) {
            resultList = (List<WrtOffTargetTrxBal>) ssmResult.getResultObject();
        } else {
            return null;
        }
        
        // If consolidated invoice included flag is 'Y' then get consolidated invoices, too. 
        if (FLG_ON_Y.equals(param.inclConslInvFlg.getValue())) {
             
            ssmResult = NFXC308001Query.getInstance().getWrtOffTargetDataForConsResrc(queryParam);
            if (ssmResult.isCodeNormal() || ssmResult.isCodeNotFound()) {
                resultListCons = (List<WrtOffTargetTrxBal>) (List<WrtOffTargetTrxBal>) ssmResult.getResultObject();
            } else {
                return null;
            }
        }
        
        if (resultListCons != null && resultListCons.size() > 0) {
            resultList.addAll(resultListCons);
        }
        
        return resultList;
    }
    //---- end 2016/04/07

    
    /**
     * 
     * @param glblCmpyCd
     * @param arRcptTMsg
     * @return
     */
    public static boolean updateArTrxBalRcptUnApply(String glblCmpyCd, AR_RCPTTMsg arRcptTMsg) {

        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        if(arRcptTMsg.arRcptStsCd.getValue().equals(AR_RCPT_STS.APPLIED_PARTIAL_OR_APPLIED)) {
        if(arRcptTMsg.arRcptStsCd.getValue().equals(AR_RCPT_STS.PARTIAL_APPLIED)
                || arRcptTMsg.arRcptStsCd.getValue().equals(AR_RCPT_STS.APPLIED)) {
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
            return true;
        }

        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        if(arRcptTMsg.arRcptStsCd.getValue().equals(AR_RCPT_STS.OPEN)) {
        if(arRcptTMsg.arRcptStsCd.getValue().equals(AR_RCPT_STS.UNAPPLIED)) {
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
            //-----------------------------------------
            // Update AR_TRX_BAL
            //-----------------------------------------
            AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("arTrxNum01", arRcptTMsg.rcptNum.getValue());
            inMsg.setMaxCount(1);
            inMsg.setSQLID("001");
            AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (1 > outMsg.length()) {
                return false;
            }
            BigDecimal arTrxBalPk = outMsg.no(0).arTrxBalPk.getValue();
            
            inMsg.clear();
            inMsg.glblCmpyCd.setValue(glblCmpyCd);
            inMsg.arTrxBalPk.setValue(arTrxBalPk);
            AR_TRX_BALTMsg updMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (updMsg == null) {
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updMsg.dealOrigGrsAmt, arRcptTMsg.dealNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.dealRmngBalGrsAmt, arRcptTMsg.dealNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.funcOrigGrsAmt, arRcptTMsg.funcNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.funcRmngBalGrsAmt, arRcptTMsg.funcNetRcptAmt.getValue());
            
            EZDTBLAccessor.update(updMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                return false;
            }
            
            //-----------------------------------------
            // Update AR_RCPT_UN_APPLY
            //-----------------------------------------
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("arRcptNum", arRcptTMsg.rcptNum.getValue());
            S21SsmEZDResult arRcptUnApply = NFXC308001Query.getInstance().getArRcptUnApply(ssmParam);
            if (!arRcptUnApply.isCodeNormal()) {
                return false;
            }
            List<Map> arRcptUnApplyList = new ArrayList<Map>();
            arRcptUnApplyList = (List<Map>) arRcptUnApply.getResultObject();
            Map arRcptUnApplyData = arRcptUnApplyList.get(0);
            BigDecimal arCashAppPk = new BigDecimal(arRcptUnApplyData.get("AR_CASH_APP_PK").toString());
            String arCashAppSqNum = (String)arRcptUnApplyData.get("AR_CASH_APP_SQ_NUM");
            
            AR_RCPT_UN_APPLYTMsg inMsg2 = new AR_RCPT_UN_APPLYTMsg();
            inMsg2.glblCmpyCd.setValue(glblCmpyCd);
            inMsg2.arCashAppPk.setValue(arCashAppPk);
            inMsg2.arCashAppSqNum.setValue(arCashAppSqNum);
            AR_RCPT_UN_APPLYTMsg updMsg2 = (AR_RCPT_UN_APPLYTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg2);
            if (updMsg2 == null) {
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updMsg2.dealUnApplyAmt, arRcptTMsg.dealNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg2.funcUnApplyAmt, arRcptTMsg.funcNetRcptAmt.getValue());

            EZDTBLAccessor.update(updMsg2);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg2.getReturnCode())) {
                return false;
            }
        }

        //-----------------------------------------
        // Update AR_RCPT_DTL
        //-----------------------------------------
        AR_RCPT_DTLTMsg inMsg3 = new AR_RCPT_DTLTMsg();
        inMsg3.glblCmpyCd.setValue(glblCmpyCd);
        inMsg3.rcptNum.setValue(arRcptTMsg.rcptNum.getValue());
        inMsg3.rcptDtlNum.setValue("0001");
        AR_RCPT_DTLTMsg updMsg3 = (AR_RCPT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg3);
        if (updMsg3 == null) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(updMsg3.dealRcptDtlAmt, arRcptTMsg.dealNetRcptAmt.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg3.funcRcptDtlAmt, arRcptTMsg.funcNetRcptAmt.getValue());
        EZDTBLAccessor.update(updMsg3);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg3.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param date
     * @param weeks
     * @param months
     * @param years
     * @return
     */
    public static String calcDate(String date, int days, int months, int years) {
        String targetDate = null;

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));

        Calendar c = Calendar.getInstance();
        // START 2017/06/30 E.Kameishi [QC#18595, MOD]
        //if(years != 0) {
        c.set(Calendar.YEAR, year + years);
        //}
        //if(months!=0) {
        c.set(Calendar.MONTH, month + months - 1);
        //}
        //if(days!=0) {
        c.set(Calendar.DATE, day + days);
        //}
        // END 2017/06/30 E.Kameishi [QC#18595, MOD]

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        targetDate = df.format(c.getTime());
 
        return targetDate;
    }

}

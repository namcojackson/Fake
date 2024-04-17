/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0740;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFCL0740.common.NFCL0740CommonLogic;
import business.blap.NFCL0740.constant.NFCL0740Constant;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_WRT_OFF_RQSTTMsg;
import business.db.AR_WRT_OFF_RQST_DTLTMsg;
import business.db.AR_WRT_OFF_RQST_RSLTTMsg;
import business.db.HR_TTL_APVL_LIMITTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.WrtOffTargetTrxBal;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * write-Off Request Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   CSAI            K.Uramori       Create          Add submit process
 * 03/24/2016   CSAI            K.Uramori       Update          QC#5969
 * 04/07/2016   CSAI            K.Uramori       Update          QC#6751
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#8784
 * 2016/11/29   Fujitsu         H.Ikeda         Update          QC#15823
 * 2023/06/30   Hitachi         S.Fujita        Update          QC#60397
 * 2023/08/02   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public class NFCL0740BL06 extends S21BusinessHandler implements ZYPConstant, NFCL0740Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL0740Scrn00_CMN_Submit".equals(screenAplID)) {

                doProcess_NFCL0740Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFCL0740Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0740CMsg bizMsg = (NFCL0740CMsg) cMsg;

        AR_WRT_OFF_RQSTTMsg param = new AR_WRT_OFF_RQSTTMsg();
        // set the parameter of the request
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(param.arWrtOffRqstPk, new BigDecimal(-1));  // there's no request
        ZYPEZDItemValueSetter.setValue(param.lowRmngBalAmt, bizMsg.dealRmngBalGrsAmt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(param.highRmngBalAmt, bizMsg.dealRmngBalGrsAmt_H2.getValue());
        ZYPEZDItemValueSetter.setValue(param.lowInvNum, bizMsg.arTrxNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(param.highInvNum, bizMsg.arTrxNum_H2.getValue());
        ZYPEZDItemValueSetter.setValue(param.lowInvDueDt, bizMsg.invDueDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(param.highInvDueDt, bizMsg.invDueDt_H2.getValue());
        //---- start 2016/03/24 QC#5969 dsAcctNm -> dsAcctNum
        ZYPEZDItemValueSetter.setValue(param.lowDsAcctNum, bizMsg.dsAcctNum_H1.getValue());
      //---- end 2016/03/24
        // START 2016/07/08 K.Kojima [QC#8784,MOD]
        // ZYPEZDItemValueSetter.setValue(param.highDsAcctNum,
        // bizMsg.dsAcctNum_H2.getValue());
        ZYPEZDItemValueSetter.setValue(param.highDsAcctNum, bizMsg.dsAcctNum_H1.getValue());
        // END 2016/07/08 K.Kojima [QC#8784,MOD]
        ZYPEZDItemValueSetter.setValue(param.invTpCd, bizMsg.invTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(param.inclConslInvFlg, bizMsg.xxChkBox_H.getValue());

        //---- start 2016/04/07 QC#6751 this parameter was missing
        ZYPEZDItemValueSetter.setValue(param.arWrtOffRqstTpCd, AR_WRT_OFF_RQST_TP.USER_REQUEST);
        //---- end 2016/04/07

        List<String> arTrxTpList = new ArrayList<String>();

        if (INV_TP.INVOICE.equals(bizMsg.invTpCd_H1.getValue())) {
            StringTokenizer st = new StringTokenizer(bizMsg.varCharConstVal_I.getValue() , ",");

            while (st.hasMoreTokens()) {
                arTrxTpList.add(st.nextToken());
            }

        } else if (INV_TP.CREDIT_MEMO.equals(bizMsg.invTpCd_H1.getValue())) {
            StringTokenizer st = new StringTokenizer(bizMsg.varCharConstVal_C.getValue() , ",");

            while (st.hasMoreTokens()) {
                arTrxTpList.add(st.nextToken());
            }
        }

        // get target transaction
        List<WrtOffTargetTrxBal> trxList = NFCCmnMethod.getWrtOffTargetData(param, arTrxTpList);

        if (trxList == null) {
            bizMsg.setMessageInfo("NFCM0624E"); // unexpected error
            return;
        } else if (trxList.size() == 0) {
            // no data found
            bizMsg.setMessageInfo("NFCM0822I");
            return;
        }

        // loop
        BigDecimal sumAmt = BigDecimal.ZERO;
        for (WrtOffTargetTrxBal thisRec : trxList) {
            // get sum of target amount
            sumAmt = thisRec.getDealRmngBalGrsAmt();

            // not "report only"
            if (bizMsg.xxRadioBtn.getValueInt() == 2) {
                // If there's amount in reserved amount, error
                if (BigDecimal.ZERO.compareTo(thisRec.getDealApplyAdjRsvdAmt()) != 0) {
                    bizMsg.setMessageInfo("NFCM0817E");
                    return;
                }
            }
        }

        // check approval limit amount
        // START 2023/06/30 S.Fujita [QC#60397,MOD]
       //  AR_ADJ_APVL_LIMITTMsg tmsg = new AR_ADJ_APVL_LIMITTMsg();
        HR_TTL_APVL_LIMITTMsg hrTtlApvlLimitTMsg = new HR_TTL_APVL_LIMITTMsg();
        String psnCd   = getContextUserInfo().getUserId();
        String hrTtlNm = NFCL0740CommonLogic.getHrTtlNm(bizMsg.glblCmpyCd.getValue(), psnCd);
        // START 2023/08/02 S.Fuijta [QC#60397,ADD]
        if (!ZYPCommonFunc.hasValue(hrTtlNm)) {
            bizMsg.setMessageInfo("NFCM0815E");
            return;
        }
        // END 2023/08/02 S.Fuijta [QC#60397,ADD]
        BigDecimal hrTtlApvlLimitPk = NFCL0740CommonLogic.getHrTtlApvlLimitPk(bizMsg.glblCmpyCd.getValue(), hrTtlNm);
        // START 2023/08/02 S.Fuijta [QC#60397,MOD]
        BigDecimal sumAmtAbs =  sumAmt.abs();
        // ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        // ZYPEZDItemValueSetter.setValue(tmsg.arAdjApvlUsrId, bizMsg.usrId.getValue());
        // ZYPEZDItemValueSetter.setValue(tmsg.arAdjCatgCd, AR_ADJ_CATG.ADJUSTMENT);
        // ZYPEZDItemValueSetter.setValue(tmsg.apvlLimitRfFlg, FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(tmsg.apvlLimitActvFlg, FLG_ON_Y);
        hrTtlApvlLimitTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // hrTtlApvlLimitTMsg.hrTtlNm.setValue(hrTtlNm);
        hrTtlApvlLimitTMsg.hrTtlApvlLimitPk.setValue(hrTtlApvlLimitPk);
        // hrTtlApvlLimitTMsg.apvlLimitActvFlg.setValue(ZYPConstant.FLG_ON_Y);
        // END 2023/08/02 S.Fuijta [QC#60397,MOD]
        // tmsg = (AR_ADJ_APVL_LIMITTMsg) EZDTBLAccessor.findByKey(tmsg);
        hrTtlApvlLimitTMsg = (HR_TTL_APVL_LIMITTMsg) EZDTBLAccessor.findByKey(hrTtlApvlLimitTMsg);

        // no result
        // if (tmsg == null) {
        if(hrTtlApvlLimitTMsg == null) {
        // END 2023/06/30 S.Fujita [QC#60397,MOD]
            bizMsg.setMessageInfo("NFCM0815E");
            return;
        }

        // START 2023/06/30 S.Fujita [QC#60397,MOD]
        // if (tmsg.apvlLimitFromAmt.getValue().compareTo(sumAmt) <= 0 && tmsg.apvlLimitToAmt.getValue().compareTo(sumAmt) >= 0) {
        if (hrTtlApvlLimitTMsg.apvlLimitAmt.getValue().compareTo(sumAmtAbs) >= 0) {
        // END 2023/06/30 S.Fujita [QC#60397,MOD]
            // ok
        } else {
            // exceeds limit
            bizMsg.setMessageInfo("NFCM0816E");
            return;
        }

        // If it's not "report only" request, update reserved amount of the transaction and get ezuptime & ezuptimezone.
        if (bizMsg.xxRadioBtn.getValueInt() == 2) {
            List<AR_TRX_BALTMsg> listMsg = new ArrayList<AR_TRX_BALTMsg>();
            // loop
            for (WrtOffTargetTrxBal thisRec : trxList) {
                updateArTRxBal(thisRec, bizMsg, listMsg);
            }
            if (!updatePer1000(listMsg)) {
                // update error
                bizMsg.setMessageInfo("NFCM0533E", new String[] {"AR_TRX_BAL"});
                return;
            }

            // re-obtain search result (To get updated ezuptime & ezuptimezone)
            // get target transaction
            trxList = NFCCmnMethod.getWrtOffTargetDataResrc(param, arTrxTpList);
        }

        String grpKey = bizMsg.usrId.getValue().concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        // create write-off request
        BigDecimal rqstPk = createWritOffRqst(bizMsg, grpKey);

        if (rqstPk.compareTo(BigDecimal.ZERO) == 0) {
            // error
            bizMsg.setMessageInfo("NFCM0532E", new String[] {"Write Off Requset"});
            return;
        }

        // create write-off request detail
        if (!createWriteOffRqstDtl(trxList, rqstPk)) {
         // error
            bizMsg.setMessageInfo("NFCM0532E", new String[] {"Write Off Requset Detail"});
            return;
        }

        // if it's "Report Only", then create AR_WRT_OFF_RQST_RSLT
        if (bizMsg.xxRadioBtn.getValueInt() == 1) {
            if (!createWriteOffRqstRslt(trxList, bizMsg, rqstPk)) {
                // error
                bizMsg.setMessageInfo("NFCM0532E", new String[] {"Write Off Requset Result"});
                return;
            }

        }

        ZYPEZDItemValueSetter.setValue(bizMsg.arWrtOffRqstPk, rqstPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.wrtOffRqstGrpCd, grpKey);

        // set success message
        if (bizMsg.xxRadioBtn.getValueInt() == 2) {  // When request is not "Report Only". In case "Report Only", NFCL0760 will be shown.
            bizMsg.setMessageInfo("NFDM0014I", new String[] {grpKey});
        }

    }

    private boolean createWriteOffRqstRslt(List<WrtOffTargetTrxBal> trxList, NFCL0740CMsg bizMsg, BigDecimal rqstPk) {

        List<AR_WRT_OFF_RQST_RSLTTMsg> listTmsg = new ArrayList<AR_WRT_OFF_RQST_RSLTTMsg>();

        for (WrtOffTargetTrxBal thisRec : trxList) {
            AR_WRT_OFF_RQST_RSLTTMsg tmsg = new AR_WRT_OFF_RQST_RSLTTMsg();

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, thisRec.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstRsltPk, ZYPOracleSeqAccessor.getNumberBigDecimal("AR_WRT_OFF_RQST_RSLT_SQ"));
            ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstPk, rqstPk);
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxBalPk, thisRec.getArTrxBalPk());
            ZYPEZDItemValueSetter.setValue(tmsg.wrtOffRqstUsrId, bizMsg.usrId.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.wrtOffApplyAmt, thisRec.getDealRmngBalGrsAmt().add(thisRec.getDealApplyAdjRsvdAmt().negate()));
            ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.COMPLEATED);

            listTmsg.add(tmsg);
        }

        while (listTmsg.size() >= BULK_UPD_CNT) {

            AR_WRT_OFF_RQST_RSLTTMsg[] arryTMsg = listTmsg.subList(0, BULK_UPD_CNT).toArray(new AR_WRT_OFF_RQST_RSLTTMsg[BULK_UPD_CNT]);

            int rtn = S21FastTBLAccessor.insert(arryTMsg);
            if (rtn != BULK_UPD_CNT) {
                return false; // error
            }

            // reset list
            listTmsg.subList(0, BULK_UPD_CNT).clear();
        }

        if (listTmsg.size() != 0) {
            // process rest of list
            AR_WRT_OFF_RQST_RSLTTMsg[] arryTMsg = listTmsg.toArray(new AR_WRT_OFF_RQST_RSLTTMsg[listTmsg.size()]);
            int rtn = S21FastTBLAccessor.insert(arryTMsg);

            if (rtn != listTmsg.size()) {
                return false; // error
            }

        }

        return true;

    }

    private boolean createWriteOffRqstDtl(List<WrtOffTargetTrxBal> trxList, BigDecimal rqstPk) {
        List<AR_WRT_OFF_RQST_DTLTMsg> listTmsg = new ArrayList<AR_WRT_OFF_RQST_DTLTMsg>();

        for (WrtOffTargetTrxBal thisRec : trxList) {

            AR_WRT_OFF_RQST_DTLTMsg tmsg = new AR_WRT_OFF_RQST_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, thisRec.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstPk, rqstPk);
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxBalPk, thisRec.getArTrxBalPk());
            ZYPEZDItemValueSetter.setValue(tmsg.invTrxBalLastUpdTs, thisRec.getEzUpTime());
            ZYPEZDItemValueSetter.setValue(tmsg.invTrxBalTz, thisRec.getEzUpTimeZone());
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxNum, thisRec.getArTrxNum());
            ZYPEZDItemValueSetter.setValue(tmsg.billToCustCd, thisRec.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(tmsg.billToCustAcctCd, thisRec.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(tmsg.dealCcyCd, thisRec.getDealCcyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.dealOrigGrsAmt, thisRec.getDealOrigGrsAmt());
            ZYPEZDItemValueSetter.setValue(tmsg.funcOrigGrsAmt, thisRec.getFuncOrigGrsAmt());
            ZYPEZDItemValueSetter.setValue(tmsg.dealRmngBalGrsAmt, thisRec.getDealRmngBalGrsAmt());
            ZYPEZDItemValueSetter.setValue(tmsg.funcRmngBalGrsAmt, thisRec.getFuncRmngBalGrsAmt());
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxDt, thisRec.getArTrxDt());
            ZYPEZDItemValueSetter.setValue(tmsg.invDueDt, thisRec.getInvDueDt());
            ZYPEZDItemValueSetter.setValue(tmsg.dsContrNum, thisRec.getDsContrNum());
            ZYPEZDItemValueSetter.setValue(tmsg.bllgPerFromDt, thisRec.getBllgPerFromDt());
            ZYPEZDItemValueSetter.setValue(tmsg.bllgPerToDt, thisRec.getBllgPerToDt());
            ZYPEZDItemValueSetter.setValue(tmsg.daysPastDueAot, new BigDecimal(ZYPDateUtil.getDiffDays(ZYPDateUtil.getSalesDate(), tmsg.invDueDt.getValue())));

            listTmsg.add(tmsg);
        }

        while (listTmsg.size() >= BULK_UPD_CNT) {

            AR_WRT_OFF_RQST_DTLTMsg[] arryTMsg = listTmsg.subList(0, BULK_UPD_CNT).toArray(new AR_WRT_OFF_RQST_DTLTMsg[BULK_UPD_CNT]);

            int rtn = S21FastTBLAccessor.insert(arryTMsg);
            if (rtn != BULK_UPD_CNT) {
                return false; // error
            }

            // reset list
            listTmsg.subList(0, BULK_UPD_CNT).clear();
        }

        if (listTmsg.size() != 0) {
            // process rest of list
            AR_WRT_OFF_RQST_DTLTMsg[] arryTMsg = listTmsg.toArray(new AR_WRT_OFF_RQST_DTLTMsg[listTmsg.size()]);
            int rtn = S21FastTBLAccessor.insert(arryTMsg);

            if (rtn != listTmsg.size()) {
                return false; // error
            }

        }

        return true;

    }

    private BigDecimal createWritOffRqst(NFCL0740CMsg bizMsg, String grpKey) {
        AR_WRT_OFF_RQSTTMsg tmsg = new AR_WRT_OFF_RQSTTMsg();

        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal("AR_WRT_OFF_RQST_SQ");

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstPk, pk);
        ZYPEZDItemValueSetter.setValue(tmsg.wrtOffRqstGrpCd, grpKey);
        ZYPEZDItemValueSetter.setValue(tmsg.wrtOffRqstUsrId, bizMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.arAdjRsnCd, bizMsg.arAdjRsnCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.arAdjTpCd, bizMsg.arAdjTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.lowRmngBalAmt, bizMsg.dealRmngBalGrsAmt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.highRmngBalAmt, bizMsg.dealRmngBalGrsAmt_H2.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.lowInvNum, bizMsg.arTrxNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.highInvNum, bizMsg.arTrxNum_H2.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.lowInvDueDt, bizMsg.invDueDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.highInvDueDt, bizMsg.invDueDt_H2.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.lowDsAcctNum, bizMsg.dsAcctNum_H1.getValue());
        // START 2016/07/08 K.Kojima [QC#8784,MOD]
        // ZYPEZDItemValueSetter.setValue(tmsg.highDsAcctNum,
        // bizMsg.dsAcctNum_H2.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.highDsAcctNum, bizMsg.dsAcctNum_H1.getValue());
        // END 2016/07/08 K.Kojima [QC#8784,MOD]
        ZYPEZDItemValueSetter.setValue(tmsg.invTpCd, bizMsg.invTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.inclConslInvFlg, NFCL0740CommonLogic.getChkBoxVal(bizMsg.xxChkBox_H.getValue()));
        ZYPEZDItemValueSetter.setValue(tmsg.wrtOffOptTpCd, bizMsg.xxRadioBtn.getValue().toString());
        ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstTpCd, AR_WRT_OFF_RQST_TP.USER_REQUEST);
        ZYPEZDItemValueSetter.setValue(tmsg.arDsWfStsCd, AR_DS_WF_STS.APPROVED);

        // If the request is "Report Only", then create as completed.
        if (bizMsg.xxRadioBtn.getValueInt() == 1) {
            ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.COMPLEATED);
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.IN_COMPLETED);
        }

        S21FastTBLAccessor.insert(tmsg);

        if (S21FastTBLAccessor.RTNCD_DUPLICATE.equals(tmsg.getReturnCode())) {
            return BigDecimal.ZERO;
        }
        // START 2016/11/29 H.Ikeda [QC#15823,ADD]
        else if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            return pk;
        } else {
            return BigDecimal.ZERO;
        }
        //return pk;
        // END   2016/11/29 H.Ikeda [QC#15823,ADD]
    }

    private boolean updatePer1000(List<AR_TRX_BALTMsg> listMsg) {
        Map <BigDecimal, AR_TRX_BALTMsg> mapTrxBal = new HashMap <BigDecimal, AR_TRX_BALTMsg> ();

        while (listMsg.size() >= BULK_UPD_CNT) {

            AR_TRX_BALTMsg[] arryTMsg = listMsg.subList(0, BULK_UPD_CNT).toArray(new AR_TRX_BALTMsg[BULK_UPD_CNT]);

            int rtn = S21FastTBLAccessor.update(arryTMsg);
            if (rtn != BULK_UPD_CNT) {
                return false; // error
            }

            // reset list
            listMsg.subList(0, BULK_UPD_CNT).clear();
        }

        if (listMsg.size() != 0) {
            // process rest of list
            AR_TRX_BALTMsg[] arryTMsg = listMsg.toArray(new AR_TRX_BALTMsg[listMsg.size()]);
            int rtn = S21FastTBLAccessor.update(arryTMsg);

            if (rtn != listMsg.size()) {
                return false; // error
            }

        }

        return true;
    }

    private boolean updateArTRxBal(WrtOffTargetTrxBal thisRec, NFCL0740CMsg bizMsg, List<AR_TRX_BALTMsg> listMsg) {

        AR_TRX_BALTMsg tmsg = new AR_TRX_BALTMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.arTrxBalPk, thisRec.getArTrxBalPk());

        tmsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(tmsg);

        if (tmsg == null) {
            // error
            bizMsg.setMessageInfo("NFCM0624E"); // unexpected error
            return false;
        }

        // exclusive check
        if (!ZYPDateUtil.isSameTimeStamp(thisRec.getEzUpTime(), thisRec.getEzUpTimeZone(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
            // error
            bizMsg.setMessageInfo("NFCM0073E");
            return false;
        }

        // update
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjRsvdAmt, tmsg.dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.funcApplyAdjRsvdAmt, tmsg.funcRmngBalGrsAmt.getValue());

        listMsg.add(tmsg);

        return true;
    }
}

/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1120;

import static business.blap.NSAL1120.common.NSAL1120CommonLogic.cheackMeterDetails;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.pagenationForSubmitErr;
import static business.blap.NSAL1120.common.NSAL1120CommonLogic.setPagenationForAll;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CI_UPDATE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CODE_BASE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NSAM0065E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NSAM0438E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.ZZM8100I;
import static business.blap.NSAL1120.constant.NSAL1120Constant.ZZZM9004E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.DEF_SER_NUM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.parts.NSZC053001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC053001.NSZC053001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Meter and Pricing Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         K.Yamada        Update          CSA QC#7056
 * 2016/04/25   Hitachi         T.Kanasaka      Update          CSA QC#7056
 * 2017/02/21   Hitachi         K.Kishimoto     Update          CSA QC#17646
 * 2017/09/15   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/02   Hitachi         U.Kim           Update          QC#18749
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 * 2022/04/04   CITS            E.Sanchez       Update          QC#59914
 * 2022/04/29   CITS            E.Sanchez       Update          QC#59914-1
 *</pre>
 */
public class NSAL1120BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1120CMsg cMsg = (NSAL1120CMsg) arg0;
        NSAL1120SMsg sMsg = (NSAL1120SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1120Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1120Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1120Scrn00_CMN_Submit(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {
        setPagenationForAll(cMsg, sMsg);

        if (!cheackSvcCrRebilStsCd(cMsg)) {
            return;
        }
        if (!cheackMeterDetails(cMsg)) {
            return;
        }
        if (!cheackPricingDetail(sMsg)) {
            setValue(cMsg.xxPageShowFromNum_C, getErrPageFromNum(cMsg, sMsg));
            pagenationForSubmitErr(cMsg, sMsg);
            return;
        }
        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            aggSumAllowace(cMsg, sMsg);
        }

        update(cMsg, sMsg);
    }

    private boolean cheackSvcCrRebilStsCd(NSAL1120CMsg cMsg) {

        if (!SVC_CR_REBIL_STS.ENTERED.equals(cMsg.svcCrRebilStsCd.getValue())) {
            cMsg.setMessageInfo(NSAM0065E);
            return false;
        }
        return true;
    }

    private boolean cheackPricingDetail(NSAL1120SMsg sMsg) {

        boolean errFlg = true;

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            // mod start 2016/04/18 CSA Defect#7056
            if (!isFirstTierLineInMachineAndMeter(sMsg.C.no(i))) {
                continue;
            }

            List<NSAL1120_CSMsg> targetList = getSameMachineAndSameMeterLine(sMsg.C.no(i), sMsg);
            if (!checkCopyQtySequential(targetList)) {
                errFlg = false;
            }
            // mod end 2016/04/18 CSA Defect#7056
        }
        return errFlg;
    }

    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    private void aggSumAllowace(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {
        int seqNo = 0;
        String preSerNum = "";
        String preFromDt = "";
        String preMtrLbCd = "";
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            String curSerNum = sMsg.C.no(i).serNum_C.getValue();
            String curFromDt = sMsg.C.no(i).mtrBllgFromDt_C.getValue();
            String curMtrLbCd = sMsg.C.no(i).mtrLbCd_C.getValue();
            if (isSameStr(preSerNum, curSerNum) && isSameStr(preFromDt, curFromDt) && isSameStr(preMtrLbCd, curMtrLbCd)) {
                seqNo++;
            } else {
                seqNo = 1;
                preSerNum = curSerNum;
                preFromDt = curFromDt;
                preMtrLbCd = curMtrLbCd;
            }
            if (!hasValue(curSerNum)) {
                BigDecimal sumVal = sumAllowance(sMsg.C, curMtrLbCd, curFromDt, seqNo);
                setValue(sMsg.C.no(i).newXsCopyQty_C, sumVal);
            }
        }
    }

    private boolean isSameStr(String val1, String val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (!hasValue(val1) || !hasValue(val2)) {
            return false;
        }
        return val1.equals(val2);
    }

    private BigDecimal sumAllowance(NSAL1120_CSMsgArray cSMsgArray, String bllgMtrLbCd, String fromDt, int seqNo) {
        BigDecimal retVal = BigDecimal.ZERO;
        if (!hasValue(bllgMtrLbCd) || !hasValue(fromDt)) {
            return retVal;
        }

        boolean isNewInput  = false;
        int machSeq = 0;
        String preSerNum = DEF_SER_NUM;
        for (int i = 0; i < cSMsgArray.getValidCount(); i++) {
            String curSerNum = cSMsgArray.no(i).serNum_C.getValue();
            if (!hasValue(curSerNum)) {
                preSerNum = DEF_SER_NUM;
                machSeq = 0;
                continue;
            }
            String curFromDt = cSMsgArray.no(i).mtrBllgFromDt_C.getValue();
            String curMtrLbCd = cSMsgArray.no(i).mtrLbCd_C.getValue();
            if (fromDt.equals(curFromDt) && bllgMtrLbCd.equals(curMtrLbCd)) {
                if (!preSerNum.equals(cSMsgArray.no(i).serNum_C.getValue())) {
                    preSerNum = cSMsgArray.no(i).serNum_C.getValue();
                    machSeq = 1;
                } else {
                    machSeq++;
                }
                if (seqNo == machSeq) {
                    if (hasValue(cSMsgArray.no(i).oldXsCopyQty_C)) {
                        BigDecimal allowace = cSMsgArray.no(i).oldXsCopyQty_C.getValue();
                        if (hasValue(cSMsgArray.no(i).newXsCopyQty_C)) {
                            allowace = cSMsgArray.no(i).newXsCopyQty_C.getValue();
                            isNewInput = true;
                        }
                        retVal = retVal.add(allowace);
                    }
                }
            } else {
                machSeq = 0;
            }
            preSerNum = curSerNum;
        }
        if (isNewInput == false) {
            retVal = null;
        }
        return retVal;
    }
    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]

    // add start 2016/04/18 CSA Defect#7056
    private boolean isFirstTierLineInMachineAndMeter(NSAL1120_CSMsg csMsg) {
        if (!hasValue(csMsg.xxDplyCtrlFlg_C1) && !hasValue(csMsg.xxDplyCtrlFlg_C2)) {
            return true;
        }
        return false;
    }

    private List<NSAL1120_CSMsg> getSameMachineAndSameMeterLine(NSAL1120_CSMsg target, NSAL1120SMsg sMsg) {
        List<NSAL1120_CSMsg> csMsgList = new ArrayList<NSAL1120_CSMsg>();
        String serNum = target.xxScrItem40Txt_C.getValue();
        String mtrLbCd = target.mtrLbCd_C.getValue();

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL1120_CSMsg csMsg = sMsg.C.no(i);
            if (serNum.equals(csMsg.xxScrItem40Txt_C.getValue())
                    && mtrLbCd.equals(csMsg.mtrLbCd_C.getValue())) {
                csMsgList.add(csMsg);
            }
        }
        return csMsgList;
    }

    private boolean checkCopyQtySequential(List<NSAL1120_CSMsg> targetList) {
        BigDecimal preCopyQty = null;
        BigDecimal copyQty = null;
        boolean isCheckOk = true;

        for (NSAL1120_CSMsg csMsg : targetList) {
            if (hasValue(csMsg.newXsCopyQty_C)) {
                copyQty = csMsg.newXsCopyQty_C.getValue();
            } else if (hasValue(csMsg.oldXsCopyQty_C)) {
                copyQty = csMsg.oldXsCopyQty_C.getValue();
            } else {
                continue;
            }

            if (hasValue(preCopyQty)) {
                if (preCopyQty.compareTo(copyQty) >= 0) {
                    csMsg.newXsCopyQty_C.setErrorInfo(1, NSAM0438E);
                    isCheckOk = false;
                }
            }
            preCopyQty = copyQty;
        }
        return isCheckOk;
    }
    // add end 2016/04/18 CSA Defect#7056

    private boolean checkUpdate(NSAL1120CMsg cMsg) {

        if (hasValue(cMsg.svcInvLinePk) || !hasValue(cMsg.ezUpTime)) {
            return true;
        }
        SVC_CR_REBIL_DTLTMsg chkTMsg = new SVC_CR_REBIL_DTLTMsg();

        setValue(chkTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(chkTMsg.svcCrRebilDtlPk, cMsg.svcCrRebilDtlPk);

        SVC_CR_REBIL_DTLTMsg updTMsg = (SVC_CR_REBIL_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(chkTMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        return true;
    }

    private BigDecimal getErrPageFromNum(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        int errIndex = 0;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            // Error
            if (sMsg.C.no(i).newXsCopyQty_C.isError()) {
                errIndex = i;
                break;
            }
        }

        if (errIndex < cMsg.C.length()) {
            return new BigDecimal(0);
        } else {
            int pageNum = errIndex / cMsg.C.length();
            return new BigDecimal(cMsg.C.length() * pageNum);
        }
    }

    private void update(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        NSZC053001PMsg pMsg = new NSZC053001PMsg();

        if (MODE_CODE_BASE.equals(cMsg.xxModeCd.getValue())) {
            setParamForBase(cMsg, sMsg, pMsg);
        } else {
            setParamForMeter(cMsg, sMsg, pMsg);
        }

        if (!checkUpdate(cMsg)) {
            return;
        }

        execNSZC053001(cMsg, pMsg);
    }

    private List<NSAL1120_CSMsg> getPricingData(NSAL1120SMsg sMsg, NSAL1120_BSMsg targetMachine) {

        List<NSAL1120_CSMsg> csMsgList = new ArrayList<NSAL1120_CSMsg>();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (targetMachine.serNum_B.getValue().equals(sMsg.C.no(i).serNum_C.getValue())) {
                csMsgList.add(sMsg.C.no(i));
            }
        }
        return csMsgList;
    }

    private void execNSZC053001(NSAL1120CMsg cMsg, NSZC053001PMsg pMsg) {

        new NSZC053001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return;
        } else {
            cMsg.setMessageInfo(ZZM8100I);
        }
    }

    // mod start 2016/04/25 CSA Defect#7056
    private void setParamForBase(NSAL1120CMsg cMsg,  NSAL1120SMsg sMsg, NSZC053001PMsg pMsg) {

        NSAL1120_ASMsgArray asMsgArry = sMsg.A;

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, MODE_CI_UPDATE);

        if (hasValue(cMsg.svcCrRebilPk)) {
            setValue(pMsg.svcCrRebilPk, cMsg.svcCrRebilPk);
            // START 2017/10/02 U.Kim [QC#18749, ADD]
            setValue(pMsg.custIncdtId, cMsg.custIncdtId);
            // END 2017/10/02 U.Kim [QC#18749, ADD]
        } else {
            setValue(pMsg.svcCrRebilPk, asMsgArry.no(0).svcCrRebilPk_A);
        }

//        if (!hasValue(cMsg.svcInvLinePk)) {
//            setValue(pMsg.svcCrRebilPk, cMsg.svcCrRebilDtlPk);
//        }

        int i = 0;
        for (; i < asMsgArry.getValidCount(); i++) {

            setValue(pMsg.xxCrRebilDtlList.no(i).origSvcInvNum, asMsgArry.no(i).origSvcInvNum_A);
            // START 2022/04/04 E.Sanchez [QC#59914,ADD]
            setValue(pMsg.xxCrRebilDtlList.no(i).dsContrDtlPk, asMsgArry.no(i).dsContrDtlPk_A);
            // END 2022/04/04 E.Sanchez [QC#59914,ADD]
            setValue(pMsg.xxCrRebilDtlList.no(i).serNum, asMsgArry.no(i).serNum_A);
            setValue(pMsg.xxCrRebilDtlList.no(i).baseBllgFromDt, asMsgArry.no(i).baseBllgFromDt_A);
            setValue(pMsg.xxCrRebilDtlList.no(i).baseBllgThruDt, asMsgArry.no(i).baseBllgThruDt_A);
            setValue(pMsg.xxCrRebilDtlList.no(i).newBaseDealAmt, asMsgArry.no(i).newBaseDealAmt_A);
            // START 2022/04/29 E.Sanchez [QC#59914-1,ADD]
            setValue(pMsg.xxCrRebilDtlList.no(i).newBaseUnitAmt, asMsgArry.no(i).newBaseUnitAmt_A);
            setValue(pMsg.xxCrRebilDtlList.no(i).svcMachMstrPk, asMsgArry.no(i).svcMachMstrPk_A);
            // END 2022/04/29 E.Sanchez [QC#59914-1,ADD]
        }

        pMsg.xxCrRebilDtlList.setValidCount(i);
    }

    private void setParamForMeter(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, NSZC053001PMsg pMsg) {

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, MODE_CI_UPDATE);

        if (hasValue(cMsg.svcCrRebilPk)) {
            setValue(pMsg.svcCrRebilPk, cMsg.svcCrRebilPk);
            // START 2017/10/02 U.Kim [QC#18749, ADD]
            setValue(pMsg.custIncdtId, cMsg.custIncdtId);
            // END 2017/10/02 U.Kim [QC#18749, ADD]
        } else {
            setValue(pMsg.svcCrRebilPk, sMsg.B.no(0).svcCrRebilPk_B);
        }

//        if (!hasValue(cMsg.svcInvLinePk)) {
//            setValue(pMsg.svcCrRebilPk, cMsg.svcCrRebilDtlPk);
//        }

        // mod start 2016/04/18 CSA Defect#7056
        //set meter changes
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NSAL1120_BSMsg bsMsg = sMsg.B.no(i);
            setMeterChrgParam(pMsg, bsMsg);
        }
        //set price changes
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL1120_CSMsg csMsg = sMsg.C.no(i);
            setPriceChrgParam(pMsg, csMsg);
        }
        // mod end 2016/04/18 CSA Defect#7056
    }
    // mod end 2016/04/25 CSA Defect#7056

    // mod start 2016/04/25 CSA Defect#7056
    private void setMeterChrgParam(NSZC053001PMsg pMsg, NSAL1120_BSMsg bsMsg) {
        int idx = pMsg.xxCrRebilDtlList.getValidCount();
        setValue(pMsg.xxCrRebilDtlList.no(idx).origSvcInvNum, bsMsg.origSvcInvNum_B);
        setValue(pMsg.xxCrRebilDtlList.no(idx).serNum, bsMsg.serNum_B);
        setValue(pMsg.xxCrRebilDtlList.no(idx).startMtrReadDt, bsMsg.startMtrReadDt_B);
        setValue(pMsg.xxCrRebilDtlList.no(idx).endMtrReadDt, bsMsg.endMtrReadDt_B);
        setValue(pMsg.xxCrRebilDtlList.no(idx).physMtrLbCd, bsMsg.mtrLbCd_B);
        setValue(pMsg.xxCrRebilDtlList.no(idx).newStartReadMtrCnt, bsMsg.newStartReadMtrCnt_B);
        setValue(pMsg.xxCrRebilDtlList.no(idx).newEndReadMtrCnt, bsMsg.newEndReadMtrCnt_B);
        //START 2017/09/15 E.Kameishi [QC#18636,MOD]
        setValue(pMsg.xxCrRebilDtlList.no(idx).newTestMtrCnt, bsMsg.newTestMtrCnt_B);
//        setValue(pMsg.xxCrRebilDtlList.no(idx).newStartTestMtrCnt, bsMsg.newStartTestMtrCnt_B);
//        setValue(pMsg.xxCrRebilDtlList.no(idx).newEndTestMtrCnt, bsMsg.newEndTestMtrCnt_B);
        //END 2017/09/15 E.Kameishi [QC#18636,MOD]
        pMsg.xxCrRebilDtlList.setValidCount(++idx);
    }
    // mod end 2016/04/25 CSA Defect#7056

    private void setPriceChrgParam(NSZC053001PMsg pMsg, NSAL1120_CSMsg csMsg) {
        int idx = pMsg.xxCrRebilDtlList.getValidCount();
        setValue(pMsg.xxCrRebilDtlList.no(idx).origSvcInvNum, csMsg.origSvcInvNum_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).serNum, csMsg.serNum_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).bllgMtrLbCd, csMsg.mtrLbCd_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).mtrBllgFromDt, csMsg.mtrBllgFromDt_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).mtrBllgThruDt, csMsg.mtrBllgThruDt_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).oldXsCopyQty, csMsg.oldXsCopyQty_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).oldXsMtrAmtRate, csMsg.oldXsMtrAmtRate_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).newXsCopyQty, csMsg.newXsCopyQty_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).newXsMtrAmtRate, csMsg.newXsMtrAmtRate_C);
        // START 201/08/27 [QC#24555, ADD]
        setValue(pMsg.xxCrRebilDtlList.no(idx).oldUnitXsCopyQty, csMsg.oldUnitXsCopyQty_C);
        setValue(pMsg.xxCrRebilDtlList.no(idx).newUnitXsCopyQty, csMsg.newUnitXsCopyQty_C);
        // END   201/08/27 [QC#24555, ADD]
        pMsg.xxCrRebilDtlList.setValidCount(++idx);
    }

    private void copyAggLineXsRateToAggMach(NSAL1120_CSMsg aggMachMsg, NSAL1120SMsg sMsg) {
        String mtrLb = aggMachMsg.mtrLbCd_C.getValue();
        BigDecimal tierCnt = aggMachMsg.xxListNum_C.getValue();

        NSAL1120_CSMsg aggLineMsg = null;

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL1120_CSMsg csMsg = sMsg.C.no(i);
            if (hasValue(csMsg.serNum_C)) {
                // The line which has serial# is not aggregate line.
                continue;
            }
            if (mtrLb.equals(csMsg.mtrLbCd_C.getValue())
                    && tierCnt.compareTo(csMsg.xxListNum_C.getValue()) == 0) {
                aggLineMsg = csMsg;
                break;
            }
        }

        if (aggLineMsg != null) {
            setValue(aggMachMsg.newXsMtrAmtRate_C, aggLineMsg.newXsMtrAmtRate_C);
        }
    }
    // add end 2016/04/18 CSA Defect#7056
}

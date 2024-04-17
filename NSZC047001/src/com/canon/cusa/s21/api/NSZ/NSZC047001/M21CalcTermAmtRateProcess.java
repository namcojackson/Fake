package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import business.parts.NSZC047021PMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/02/2016   Hitachi         K.Kishimoto     Create          QC#4961
 * 2019/06/12   Hitachi         A.Kohinata      Update          QC#50807
 * </pre>
 */
public class M21CalcTermAmtRateProcess implements ZYPConstant {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchTp;

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        calcTermAmtRateProc(msgMap);
    }

    private void calcTermAmtRateProc(S21ApiMessageMap msgMap) {

        NSZC047021PMsg pMsg = (NSZC047021PMsg) msgMap.getPmsg();

        List<CalcSchdSmryTermAndAmtForBaseBean> tmpTopSchdBeanList = calcSchdSmryTermAndAmt(msgMap);
        for (CalcSchdSmryTermAndAmtForBaseBean tmpTopSchdBean :tmpTopSchdBeanList) {
            if (OVER_PER_SEQ.compareTo(tmpTopSchdBean.getPerSchdNum()) <= 0) {
                msgMap.addXxMsgId(NSZM1054E);
                return;
            }
        }
        int topSchdIdx = 0;
        BigDecimal ttlAmtRate = BigDecimal.ZERO;
        for (CalcSchdSmryTermAndAmtForBaseBean tmpTopSchdBean : tmpTopSchdBeanList) {
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).dsContrBllgSchdSqNum_BL, tmpTopSchdBean.getDsContrBllgSchdSqNum());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).perSchdNum_BL, tmpTopSchdBean.getPerSchdNum());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).perBllgCycleCd_BL, tmpTopSchdBean.getPerBllgCycleCd());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).bllgSchdFromDt_BL, tmpTopSchdBean.getBllgSchdFromDt());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).bllgSchdThruDt_BL, tmpTopSchdBean.getBllgSchdThruDt());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).bllgCycleCd_BL, tmpTopSchdBean.getBaseBllgCycleCd());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).basePrcDealAmt_BL, tmpTopSchdBean.getBasePrcDealAmt());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).basePrcDealAdjAmt_BL, tmpTopSchdBean.getAdjAmt());
            ttlAmtRate = ttlAmtRate.add(tmpTopSchdBean.getBaseSubTotPrcDealAmt());
            setValue(pMsg.xxBaseLineList.no(topSchdIdx).baseSubTotPrcDealAmt_BL, tmpTopSchdBean.getBaseSubTotPrcDealAmt());
            topSchdIdx++;
        }
        pMsg.xxBaseLineList.setValidCount(topSchdIdx);
        setValue(pMsg.basePrcTermDealAmtRate, ttlAmtRate);

    }

    private void checkParameter(S21ApiMessageMap msgMap) {

        NSZC047021PMsg pMsg = (NSZC047021PMsg) msgMap.getPmsg();
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.glblCmpyCd, ZZZM9007E, new String[]{"Global Company Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.slsDt, ZZZM9007E, new String[]{"Salse Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.dsContrDtlPk, ZZZM9007E, new String[]{"DS Contract Detail PK"});
        // mod start 2019/06/12 QC#50807
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.contrCloDay, ZZZM9007E, new String[]{"Contract Close Day"});
        // mod end 2019/06/12 QC#50807
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseBllgTmgCd, ZZZM9007E, new String[]{"Base Billing Timing Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.baseBllgCycleCd, ZZZM9007E, new String[]{"Base Billing Cycle Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.ccyCd, ZZZM9007E, new String[]{"Currency Code"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.effFromDt, ZZZM9007E, new String[]{"Effective From Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.effThruDt, ZZZM9007E, new String[]{"Effective Thru Date"});
        NSZC047001CommonLogic.mandatoryCheck(msgMap, pMsg.basePrcDealAmt, ZZZM9007E, new String[]{"Base Price Deal Amount"});
    }

    private List<CalcSchdSmryTermAndAmtForBaseBean> calcSchdSmryTermAndAmt(S21ApiMessageMap msgMap) {
        NSZC047021PMsg pMsg = (NSZC047021PMsg) msgMap.getPmsg();

        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(pMsg.effFromDt.getValue());
        inBean.setBllgSchdThruDt(pMsg.effThruDt.getValue());
        inBean.setBllgCycleCd(pMsg.baseBllgCycleCd.getValue());
        inBean.setContrCloDay(pMsg.contrCloDay.getValue());
        inBean.setBasePrcDealAmt(pMsg.basePrcDealAmt.getValue());
        inBean.setBaseChrgFlg(FLG_ON_Y);
        inBean.setUsgChrgFlg(FLG_OFF_N);
        inBean.setCcyCd(pMsg.ccyCd.getValue());

        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        return outBean.getBaseList();
    }

}

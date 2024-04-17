/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0560;

import static business.blap.NSAL0560.constant.NSAL0560Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0560.common.NSAL0560CommonLogic;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_TRKTMsg;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047011PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Base Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC1427
 * 2015/12/10   Hitachi         T.Kanasaka      Update          QC#1815
 * 2015/12/15   Hitachi         K.Yamada        Update          CSA QC#1983
 * 2016/01/06   Hitachi         K.Yamada        Update          CSA QC#2783
 * 2016/02/01   Hitachi         T.Tomita        Update          CSA QC#2063
 * 2016/02/08   Hitachi         K.Kishimoto     Update          QC#3884, QC#3891, QC#3898
 * 2016/05/17   Hitachi         T.Tomita        Update          QC#3891
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2018/05/11   Hitachi         K.Kim           Update          QC#25897
 * 2019/11/25   Hitachi         K.Kitachi       Update          QC#54703
 *</pre>
 */
public class NSAL0560BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0560CMsg cMsg = (NSAL0560CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0560Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0560Scrn00_CMN_Submit(cMsg);
            }
            // del start 2015/12/15 CSA Defect#1983
            //else if ("NSAL0560Scrn00_TopSchedules".equals(screenAplID)) {
            //    doProcess_NSAL0560Scrn00_TopSchedules(cMsg);
            //}
            // del end 2015/12/15 CSA Defect#1983

        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0560Scrn00_CMN_Submit(NSAL0560CMsg cMsg) {
        doProcess(cMsg);
    }

    // del start 2016/05/17 CSA Defect#3891
//    private void doProcess_NSAL0560Scrn00_TopSchedules(NSAL0560CMsg cMsg) {
//        if (NSAL0560CommonLogic.existsDsContrPrcEff(cMsg)) {
//            doProcess(cMsg);
//        }
//    }
    // del end 2016/05/17 CSA Defect#3891

    //Add Start 02/08/2016 <QC#3884, QC#3891, QC#3898>
    // START 2015/12/10 T.Kanasaka [QC#1815, MOD]
    private void doProcess(NSAL0560CMsg cMsg) {
        // del start 2016/05/17 CSA Defect#3891
//        // set reason / comment
//        NSAL0560CommonLogic.setReasonComment(cMsg);
        // del end 2016/05/17 CSA Defect#3891
        // error check
        // START 2019/11/25 K.Kitachi [QC#54703, ADD]
        boolean existUnbilledRebill = NSAL0560Query.getInstance().existUnbilledRebill(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        if (existUnbilledRebill) {
            cMsg.setMessageInfo(NSAM0754E);
            return;
        }
        // END 2019/11/25 K.Kitachi [QC#54703, ADD]
        if (NSAL0560CommonLogic.isErrorTopSchedule(cMsg)) {
            return;
        }
        // call api
        NSZC047008PMsg pMsg = NSAL0560CommonLogic.createApi(cMsg);
        NSAL0560CommonLogic.executeApi(pMsg);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue(), pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue() });
            return;
        }

        // create reason / comment
        NSAL0560CommonLogic.insertSvcMemo(cMsg);

        // START 2016/02/01 T.Tomita [QC#2063, ADD]
        // update DS_CONTR_DTL
        if (!updateDsContrDtl(cMsg)) {
            return;
        }
        // END 2016/02/01 T.Tomita [QC#2063, ADD]

        // START 2018/05/11 K.Kim [QC#25897, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd_A.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.dsContrDtlTpCd_A.getValue())) {
            sumAggregate(cMsg);
        }
        // END 2018/05/11 K.Kim [QC#25897, ADD]

        //update price effectivity for Machine of Aggregate contract
        if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.dsContrDtlTpCd_A.getValue())) {
            NSAL0560CommonLogic.updateUnderAgg(cMsg);
        }

        // add start 2016/06/03 CSA Defect#1523, 4624
        BigDecimal dsContrPk = NSAL0560Query.getInstance().getDsContrPk(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        if (!callContrTrkAPI(cMsg, dsContrPk)) {
            return;
        }
        // add end 2016/06/03 CSA Defect#1523, 4624

        // add start 2016/05/17 CSA Defect#3891
        cMsg.setMessageInfo(NSAM0200I);
        // add end 2016/05/17 CSA Defect#3891
//        // api result copy (dsContrPrcEffPk)
//        setPMsgToCMsg(cMsg, pMsg);
    }
    //Mod End   02/08/2016 <QC#3884, QC#3891, QC#3898>

    // del start 2016/05/17 CSA Defect#3891
//    private void setPMsgToCMsg(NSAL0560CMsg cMsg, NSZC047008PMsg pMsg) {
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            setValue(cMsg.A.no(i).dsContrPrcEffPk_A1, pMsg.xxBaseLineList.no(i).dsContrPrcEffPk_BL);
//        }
//    }
    // del end 2016/05/17 CSA Defect#3891
    // END 2015/12/10 T.Kanasaka [QC#1815, MOD]
    //Mod Start 02/08/2016 <QC#3884, QC#3891, QC#3898>
    // START 2016/02/01 T.Tomita [QC#2063, ADD]
    private boolean updateDsContrDtl(NSAL0560CMsg cMsg) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0560Query.getInstance().getDsContrDtl(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        if (dsContrDtlTMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Detail" });
            return false;
        }

        DS_CONTR_PRC_EFFTMsgArray prcEffArray = NSAL0560Query.getDsContrPrcEff(cMsg);
        if (prcEffArray.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Price Effectivity" });
            return false;
        }
        boolean updFlg = false;
        // Price/Period update check
        if (isUpdatePricePeriod(cMsg, prcEffArray, dsContrDtlTMsg)) {
            updFlg = true;
        }

        // Term Amount update check
        if (isUpdateTermAmt(prcEffArray, dsContrDtlTMsg)) {
            updFlg = true;
        }

        // update DS_CONTR_DTL
        if (updFlg) {
            if (!updateDsContrDtl(cMsg, dsContrDtlTMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUpdatePricePeriod(NSAL0560CMsg cMsg, DS_CONTR_PRC_EFFTMsgArray prcEffArray, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        String slsDt = cMsg.slsDt.getValue();
        if (!hasValue(slsDt)) {
            slsDt = ZYPDateUtil.getSalesDate();
            setValue(cMsg.slsDt, slsDt);
        }

        String fromDt;
        String thruDt;
        // CONTR_PRC_EFF_FROM_DT < Sales Date < CONTR_PRC_EFF_THRU_DT
        for (int i = 0; i < prcEffArray.getValidCount(); i++) {
            fromDt = prcEffArray.no(i).contrPrcEffFromDt.getValue();
            thruDt = prcEffArray.no(i).contrPrcEffThruDt.getValue();
            if (ZYPDateUtil.compare(fromDt, slsDt) <= 0 && ZYPDateUtil.compare(slsDt, thruDt) <= 0) {
                return checkPricePeriod(prcEffArray.no(i), dsContrDtlTMsg);
            }
        }

        String contrDtlFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
        String contrDtlThruDt = dsContrDtlTMsg.contrEffThruDt.getValue();
        if (ZYPDateUtil.compare(slsDt, contrDtlFromDt) < 0) {
            // DS_CONTR_DTL.CONTR_EFF_FROM_DT = DS_CONTR_PRC_EFF.CONTR_PRC_EFF_FROM_DT
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                fromDt = cMsg.A.no(i).contrPrcEffFromDt_A1.getValue();
                if (ZYPDateUtil.compare(fromDt, contrDtlFromDt) == 0) {
                    return checkPricePeriod(prcEffArray.no(i), dsContrDtlTMsg);
                }
            }
        } else {
            // DS_CONTR_DTL.CONTR_EFF_THRU_DT = DS_CONTR_PRC_EFF.CONTR_PRC_EFF_THRU_DT
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                thruDt = cMsg.A.no(i).contrPrcEffThruDt_A1.getValue();
                if (ZYPDateUtil.compare(thruDt, contrDtlThruDt) == 0) {
                    return checkPricePeriod(prcEffArray.no(i), dsContrDtlTMsg);
                }
            }
        }
        return false;
    }

    private boolean checkPricePeriod(DS_CONTR_PRC_EFFTMsg prcEff, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal prcEffBaseDealAmt = prcEff.basePrcDealAmt.getValue();
        BigDecimal dbBaseDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
        if (prcEffBaseDealAmt.compareTo(dbBaseDealAmt) != 0) {
            setValue(dsContrDtlTMsg.basePrcDealAmt, prcEffBaseDealAmt);
            return true;
        }
        return false;
    }

    private boolean isUpdateTermAmt(DS_CONTR_PRC_EFFTMsgArray prcEffArray, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal prcEffBasePrcTermDealAmtRate = BigDecimal.ZERO;
        BigDecimal dbBasePrcTermDealAmtRate = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue();
        for (int i = 0; i < prcEffArray.getValidCount(); i++) {
            if (!hasValue(prcEffArray.no(i).basePrcTermDealAmtRate)) {
                continue;
            }
            prcEffBasePrcTermDealAmtRate = prcEffBasePrcTermDealAmtRate.add(prcEffArray.no(i).basePrcTermDealAmtRate.getValue());
        }

        if (prcEffBasePrcTermDealAmtRate.compareTo(dbBasePrcTermDealAmtRate) != 0) {
            setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, prcEffBasePrcTermDealAmtRate);
            return true;
        }
        return false;
    }
	//Mod End   02/08/2016 <QC#3884, QC#3891, QC#3898>

    private boolean updateDsContrDtl(NSAL0560CMsg cMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        DS_CONTR_DTLTMsg tMsg = NSAL0560Query.getInstance().getDsContrDtlForUpdate(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Detail" });
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H1.getValue(), cMsg.ezUpTimeZone_H1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        EZDTBLAccessor.update(dsContrDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0001E, new String[] {"DS Contract Detail" });
            return false;
        }
        return true;
    }
    // END 2016/02/01 T.Tomita [QC#2063, ADD]

    // add start 2016/06/03 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0560CMsg cMsg, BigDecimal dsContrPk) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String userId = getContextUserInfo().getUserId();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        String stsMemoTxt = cMsg.svcCmntTxt_H1.getValue();
        DS_CONTR_TRKTMsg dsContrTrkTMsg = new DS_CONTR_TRKTMsg();
        if (hasValue(stsMemoTxt) && stsMemoTxt.length() > dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit()) {
            stsMemoTxt = stsMemoTxt.substring(0, dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit());
        }
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/03 CSA Defect#1523, 4624

    // START 2018/05/11 K.Kim [QC#25897, ADD]
    private void sumAggregate(NSAL0560CMsg cMsg) {
        BigDecimal dsContrPk = NSAL0560Query.getInstance().getDsContrPk(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk_H1.getValue());
        BigDecimal aggLinePk = NSAL0560Query.getInstance().getAggLinePk(cMsg.glblCmpyCd.getValue(), dsContrPk);
        if (hasValue(aggLinePk)) {
            NSZC047011PMsg pMsg = new NSZC047011PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, aggLinePk);
            NSZC047001 api = new NSZC047001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return;
            }
        }
    }
    // END 2018/05/11 K.Kim [QC#25897, ADD]
}

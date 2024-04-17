/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0600;

import static business.blap.NSAL0600.common.NSAL0600CommonLogic.*;
import static business.blap.NSAL0600.constant.NSAL0600Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047008_xxBaseLineListPMsg;
import business.parts.NSZC047008_xxMtrLineListPMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC047021PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 * Cascade Date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 * 2016/07/21   Hitachi         A.Kohinata      Update          QC#11720
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/12/20   Hitachi         T.Mizuki        Update          QC#16648
 * 2017/02/28   Hitachi         K.Kishimoto     Update          QC#17809
 * 2019/05/22   Hitachi         K.Kitachi       Update          QC#50437
 * 2023/08/28   Hitachi         S.Moriai        Update          QC#59846
 *</pre>
 */
public class NSAL0600BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0600CMsg cMsg = (NSAL0600CMsg) arg0;
        NSAL0600SMsg sMsg = (NSAL0600SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0600Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0600Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0600Scrn00_CMN_Submit(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        // copy CMsg to SMsg
        copyCMsgToSMsg(cMsg, sMsg);
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        setPagenation(cMsg, sMsg, pageFromNum);

        // input parameter check
        if (isErrVldChk(cMsg, sMsg)) {
            int errPageFromNum = getErrPageFromNum(cMsg, sMsg);
            pagenation(cMsg, sMsg, errPageFromNum);
            return;
        }

        // update Contract
        DS_CONTRTMsg dsContrTMsg = updateContractHeader(cMsg, sMsg);
        if (dsContrTMsg == null) {
            return;
        }

        // update Contract Detail
        if (!updateContractDetail(cMsg, sMsg)) {
            return;
        }

        // call Billing Schedule API
        if (!callContractBillingScheduleApi(getGlobalCompanyCode(), cMsg, sMsg, dsContrTMsg)) {
            return;
        }

        // insert Service Memo
        if (!insertSvcMemo(cMsg)) {
            return;
        }
    }

    private boolean isErrVldChk(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        boolean rtnVal = false;
        // Reason Code
        if (!hasValue(cMsg.svcMemoRsnCd_HS)) {
            cMsg.svcMemoRsnCd_HS.setErrorInfo(1, NSAM0212E, new String[] {"Reason Code" });
            rtnVal = true;
        }

        // Notes
        if (!hasValue(cMsg.svcCmntTxt_H)) {
            cMsg.svcCmntTxt_H.setErrorInfo(1, NSAM0212E, new String[] {"Notes" });
            rtnVal = true;
        }

        // Contract New Start Date
        if (!hasValue(cMsg.contrVrsnEffFromDt_N)) {
            cMsg.setMessageInfo(NSAM0403E, new String[] {"Apply To All", "Submit" });
            rtnVal = true;
        }

        // Contract New End Date
        if (!hasValue(cMsg.contrVrsnEffThruDt_N)) {
            cMsg.setMessageInfo(NSAM0403E, new String[] {"Apply To All", "Submit" });
            rtnVal = true;
        }

        // Check Contract Detail
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                // Machine New Start Date
                if (!hasValue(sMsg.A.no(i).contrEffFromDt_AN)) {
                    sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAM0212E, new String[] {"New Start Date" });
                    rtnVal = true;
                }

                // Machine New End Date
                if (!hasValue(sMsg.A.no(i).contrEffThruDt_AN)) {
                    sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAM0212E, new String[] {"New End Date" });
                    rtnVal = true;
                }
            } else {
                if (!hasValue(sMsg.contrVrsnEffThruDt_N) || !hasValue(sMsg.A.no(i).contrEffThruDt_A)) {
                    continue;
                }
                // New End Date < Old End Date
                if (ZYPDateUtil.compare(sMsg.contrVrsnEffThruDt_N.getValue(), sMsg.A.no(i).contrEffThruDt_A.getValue()) < 0) {
                    sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAM0346E, new String[] {"New End Date", "End Date" });
                    rtnVal = true;
                }
            }
        }

        return rtnVal;
    }

    private DS_CONTRTMsg updateContractHeader(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.dsContrPk, sMsg.dsContrPk_P);
        DS_CONTRTMsg outMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.ezUpTime_H.getValue(), sMsg.ezUpTimeZone_H.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }
        setValue(outMsg.contrVrsnEffFromDt, sMsg.contrVrsnEffFromDt_N);
        setValue(outMsg.contrVrsnEffThruDt, sMsg.contrVrsnEffThruDt_N);

        // add start 2016/07/21 CSA Defect#11720
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(sMsg.contrVrsnEffFromDt_N.getValue());
        param.setContrEffThruDt(sMsg.contrVrsnEffThruDt_N.getValue());
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcDuration();

        ZYPEZDItemValueSetter.setValue(outMsg.contrDurnAot, param.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(outMsg.bllgCycleUomCd, param.getCycleUomCd());
        // add end 2016/07/21 CSA Defect#11720

        S21FastTBLAccessor.update(outMsg);
        if (!RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS_CONTR" });
            return null;
        }
        return outMsg;
    }

    private boolean updateContractDetail(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        List<DS_CONTR_DTLTMsg> outMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
                setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(inMsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A);
                DS_CONTR_DTLTMsg outMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
                if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A.getValue(), sMsg.A.no(i).ezUpTimeZone_A.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                setValue(outMsg.contrEffFromDt, sMsg.A.no(i).contrEffFromDt_AN);
                setValue(outMsg.contrEffThruDt, sMsg.A.no(i).contrEffThruDt_AN);
                outMsgList.add(outMsg);
            }
        }
        if (outMsgList.size() == 0) {
            return true;
        }
        int updCnt = S21FastTBLAccessor.update(outMsgList.toArray(new DS_CONTR_DTLTMsg[outMsgList.size()]));
        if (updCnt != outMsgList.size()) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS_CONTR_DTL" });
            return false;
        }
        return true;
    }

    private static boolean callContractBillingScheduleApi(String glblCmpyCd, NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, DS_CONTRTMsg dsContrTMsg) {

        String dsContrCatgCd = dsContrTMsg.dsContrCatgCd.getValue();

        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                    DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
                    setValue(inMsg.glblCmpyCd, glblCmpyCd);
                    setValue(inMsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A);
                    DS_CONTR_DTLTMsg outMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
                    if (outMsg == null) {
                        return false;
                    }
                    if (!callApiForBase(glblCmpyCd, cMsg, sMsg, outMsg, i)) {
                        return false;
                    }
                    // START 2019/05/22 K.Kitachi [QC#50437, MOD]
//                    if (!callApiForUsage(glblCmpyCd, cMsg, sMsg, outMsg)) {
                    if (!callApiForUsage(glblCmpyCd, cMsg, sMsg, outMsg, i)) {
                    // END 2019/05/22 K.Kitachi [QC#50437, MOD]
                        return false;
                    }
                }
            }
            return true;
        }

        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
                setValue(inMsg.glblCmpyCd, glblCmpyCd);
                setValue(inMsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A);
                DS_CONTR_DTLTMsg outMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
                if (outMsg == null) {
                    return false;
                }
                if (DS_CONTR_DTL_TP.FLEET.equals(outMsg.dsContrDtlTpCd.getValue())) {
                    if (!callApiForBase(glblCmpyCd, cMsg, sMsg, outMsg, i)) {
                        return false;
                    }
                    // START 2019/05/22 K.Kitachi [QC#50437, MOD]
//                    if (!callApiForUsage(glblCmpyCd, cMsg, sMsg, outMsg)) {
                    if (!callApiForUsage(glblCmpyCd, cMsg, sMsg, outMsg, i)) {
                    // END 2019/05/22 K.Kitachi [QC#50437, MOD]
                        return false;
                    }
                    break;
                }
            }
            return true;
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
                setValue(inMsg.glblCmpyCd, glblCmpyCd);
                setValue(inMsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A);
                DS_CONTR_DTLTMsg outMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
                if (outMsg == null) {
                    return false;
                }
                if (!callApiForBase(glblCmpyCd, cMsg, sMsg, outMsg, i)) {
                    return false;
                }
                // START 2019/05/22 K.Kitachi [QC#50437, MOD]
//                if (!callApiForUsage(glblCmpyCd, cMsg, sMsg, outMsg)) {
                if (!callApiForUsage(glblCmpyCd, cMsg, sMsg, outMsg, i)) {
                // END 2019/05/22 K.Kitachi [QC#50437, MOD]
                    return false;
                }
            }
            // Add Start 2017/02/28 <QC#17809>
            NSZC047011PMsg pMsg = new NSZC047011PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd, BUSINESS_ID));
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, sMsg.A.no(0).dsContrDtlPk_A);
            NSZC047001 api = new NSZC047001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }
            // Add End   2017/02/28 <QC#17809>
            return true;
        }

        if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            return true;
        }

        return false;
    }

    // Mod Start 08/02/2016 <QC#7402>
    private static boolean callApiForBase(String glblCmpyCd, NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, int idx) {

        String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();
        if (DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)
                || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {

            BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BUSINESS_ID);

            NSAL0600Query query = NSAL0600Query.getInstance();
            // START 2023/08/28 S.Moriai [QC#59846, DEL]
//            BigDecimal curPePk = query.getCurPePk(glblCmpyCd, dsContrDtlPk, null, slsDt);
//            if (curPePk == null) {
//                return true;
//            }
//
//            DS_CONTR_PRC_EFFTMsg peTMsg = query.getDsContrPrcEff(glblCmpyCd, curPePk);
//            if (peTMsg == null) {
//                return true;
//            }
//            BigDecimal dsContrPrcEffPk = peTMsg.dsContrPrcEffPk.getValue();
//            BigDecimal dsContrPrcEffSqNum = peTMsg.dsContrPrcEffSqNum.getValue();
//            String bllgCycleCd = dsContrDtlTMsg.baseBllgCycleCd.getValue();
//            BigDecimal basePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
            // END 2023/08/28 S.Moriai [QC#59846, DEL]
            // mod start 2016/12/20 CSA QC#16648
//            BigDecimal basePrcTermDealAmtRate = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue();
            // mod end 2016/12/20 CSA QC#16648

            // START 2023/08/28 S.Moriai [QC#59846, MOD]
//            NSZC047001PMsg pMsg = new NSZC047001PMsg();
            NSZC047008PMsg pMsg = new NSZC047008PMsg();
            // END 2023/08/28 S.Moriai [QC#59846, MOD]
            setValue(pMsg.glblCmpyCd, glblCmpyCd);
            // START 2023/08/28 S.Moriai [QC#59846, MOD]
//            setValue(pMsg.xxModeCd, "01");
            setValue(pMsg.xxModeCd, "08");
            // END 2023/08/28 S.Moriai [QC#59846, MOD]
            setValue(pMsg.slsDt, slsDt);
            setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
            setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.contrCloDay, dsContrDtlTMsg.contrCloDay);
            setValue(pMsg.baseBllgTmgCd, dsContrDtlTMsg.baseBllgTmgCd);
            setValue(pMsg.contrBllgDay, dsContrDtlTMsg.contrBllgDay);
            // START 2019/05/22 K.Kitachi [QC#50437, MOD]
//            setValue(pMsg.contrEffFromDt, sMsg.contrVrsnEffFromDt_N);
//            setValue(pMsg.contrEffThruDt, sMsg.contrVrsnEffThruDt_N);
            setValue(pMsg.contrEffFromDt, selectValue(sMsg.A.no(idx).contrEffFromDt_AN, sMsg.A.no(idx).contrEffFromDt_A));
            setValue(pMsg.contrEffThruDt, selectValue(sMsg.A.no(idx).contrEffThruDt_AN, sMsg.A.no(idx).contrEffThruDt_A));
            // END 2019/05/22 K.Kitachi [QC#50437, MOD]
            // START 2023/08/28 S.Moriai [QC#59846, DEL]
//            setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, dsContrPrcEffPk);
//            setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffSqNum_BL, dsContrPrcEffSqNum);
//            // START 2019/05/22 K.Kitachi [QC#50437, MOD]
////            setValue(pMsg.xxBaseLineList.no(0).effFromDt_BL, sMsg.A.no(idx).contrEffFromDt_AN);
////            setValue(pMsg.xxBaseLineList.no(0).effThruDt_BL, sMsg.A.no(idx).contrEffThruDt_AN);
//            setValue(pMsg.xxBaseLineList.no(0).effFromDt_BL, selectValue(sMsg.A.no(idx).contrEffFromDt_AN, sMsg.A.no(idx).contrEffFromDt_A));
//            setValue(pMsg.xxBaseLineList.no(0).effThruDt_BL, selectValue(sMsg.A.no(idx).contrEffThruDt_AN, sMsg.A.no(idx).contrEffThruDt_A));
//            // END 2019/05/22 K.Kitachi [QC#50437, MOD]
//            setValue(pMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, bllgCycleCd);
//            setValue(pMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, basePrcDealAmt);
//            // mod start 2016/12/20 CSA QC#16648
////            setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, basePrcTermDealAmtRate);
//            // mod end 2016/12/20 CSA QC#16648
//            setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffStsCd_BL, peTMsg.dsContrPrcEffStsCd);
//            setValue(pMsg.xxBaseLineList.no(0).qltyAsrnHldFlg_BL, peTMsg.qltyAsrnHldFlg);
//            setValue(pMsg.xxBaseLineList.no(0).mtrHldFlg_BL, peTMsg.mtrHldFlg);
//            setValue(pMsg.xxBaseLineList.no(0).contrHldFlg_BL, peTMsg.contrHldFlg);
//            setValue(pMsg.xxBaseLineList.no(0).bllgHldFlg_BL, peTMsg.bllgHldFlg);
//            setValue(pMsg.xxBaseLineList.no(0).qltyAsrnHldPendApvlFlg_BL, peTMsg.qltyAsrnHldPendApvlFlg);
//            pMsg.xxBaseLineList.setValidCount(1);
            // END 2023/08/28 S.Moriai [QC#59846, DEL]

            // START 2023/08/28 S.Moriai [QC#59846, ADD]
            List<Map<String, Object>> dsContrPrcEffList = query.getDsContrPrcEff(pMsg, null);
            if (dsContrPrcEffList.size() == 0) {
                return true;
            }
            String oldFromDt = sMsg.A.no(idx).contrEffFromDt_A.getValue();
            String oldThruDt = sMsg.A.no(idx).contrEffThruDt_A.getValue();
            String newFromDt = sMsg.A.no(idx).contrEffFromDt_AN.getValue();
            String newThruDt = sMsg.A.no(idx).contrEffThruDt_AN.getValue();
            BigDecimal totalBasePrcTermDealAmtRate = BigDecimal.ZERO;
            int lineIdx = 0;
            for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                String effFromDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                String effThruDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_THRU_DT");
                BigDecimal basePrcTermDealAmtRate = (BigDecimal) dsContrPrcEff.get("BASE_PRC_TERM_DEAL_AMT_RATE");
                NSZC047008_xxBaseLineListPMsg baseLine = pMsg.xxBaseLineList.no(lineIdx);
                setValue(baseLine.dsContrPrcEffPk_BL, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));
                setValue(baseLine.dsContrPrcEffSqNum_BL, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                setValue(baseLine.dsContrPrcEffStsCd_BL, (String) dsContrPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                setValue(baseLine.effFromDt_BL, effFromDt);
                if (hasValue(newFromDt) && ZYPDateUtil.compare(newFromDt, oldFromDt) != 0) {
                    if (ZYPDateUtil.compare(oldFromDt, effFromDt) == 0 || (ZYPDateUtil.compare(newFromDt, effFromDt) > 0 && ZYPDateUtil.compare(newFromDt, effThruDt) < 0)) {
                        setValue(baseLine.effFromDt_BL, newFromDt);
                    }
                }
                setValue(baseLine.effThruDt_BL, effThruDt);
                if (hasValue(newThruDt) && ZYPDateUtil.compare(newThruDt, oldThruDt) != 0) {
                    if (ZYPDateUtil.compare(oldThruDt, effThruDt) == 0 || (ZYPDateUtil.compare(newThruDt, effFromDt) > 0 && ZYPDateUtil.compare(newThruDt, effThruDt) < 0)) {
                        setValue(baseLine.effThruDt_BL, newThruDt);
                    }
                }
                setValue(baseLine.baseBllgCycleCd_BL, (String) dsContrPrcEff.get("BLLG_CYCLE_CD"));
                setValue(baseLine.qltyAsrnHldFlg_BL, (String) dsContrPrcEff.get("QLTY_ASRN_HLD_FLG"));
                setValue(baseLine.mtrHldFlg_BL, (String) dsContrPrcEff.get("MTR_HLD_FLG"));
                setValue(baseLine.contrHldFlg_BL, (String) dsContrPrcEff.get("CONTR_HLD_FLG"));
                setValue(baseLine.bllgHldFlg_BL, (String) dsContrPrcEff.get("BLLG_HLD_FLG"));
                setValue(baseLine.qltyAsrnHldPendApvlFlg_BL, (String) dsContrPrcEff.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
                setValue(baseLine.basePrcDealAmt_BL, (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT"));
                setValue(baseLine.basePrcTermDealAmtRate_BL, basePrcTermDealAmtRate);
                if (ZYPDateUtil.compare(baseLine.effFromDt_BL.getValue(), effFromDt) != 0 || ZYPDateUtil.compare(baseLine.effThruDt_BL.getValue(), effThruDt) != 0) {
                    BigDecimal calcTermAmt = getTermAmt(pMsg, cMsg, dsContrPrcEff, baseLine.effFromDt_BL.getValue(), baseLine.effThruDt_BL.getValue());
                    setValue(baseLine.basePrcTermDealAmtRate_BL, calcTermAmt);
                }
                totalBasePrcTermDealAmtRate = totalBasePrcTermDealAmtRate.add(baseLine.basePrcTermDealAmtRate_BL.getValue());
                lineIdx++;
            }
            pMsg.xxBaseLineList.setValidCount(lineIdx);
            // END 2023/08/28 S.Moriai [QC#59846, ADD]

            if (!executeApi(pMsg, cMsg)) {
                return false;
            }

            // START 2023/08/28 S.Moriai [QC#59846, ADD]
            boolean updFlg = false;
            // Price/Period update check
            if (isUpdatePricePeriod(pMsg, dsContrDtlTMsg)) {
                updFlg = true;
            }

            // Term Amount update check
            if (isUpdateTermAmt(pMsg, dsContrDtlTMsg)) {
                updFlg = true;
            }

            // update DS_CONTR_DTL
            if (updFlg) {
                if (!updateDsContrDtl(cMsg, dsContrDtlTMsg)) {
                    return false;
                }
            }
            // END 2023/08/28 S.Moriai [QC#59846, ADD]
            return true;
        }
        return true;
    }

    // START 2019/05/22 K.Kitachi [QC#50437, MOD]
//    private static boolean callApiForUsage(String glblCmpyCd, NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
    private static boolean callApiForUsage(String glblCmpyCd, NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, int idx) {
    // END 2019/05/22 K.Kitachi [QC#50437, MOD]

        String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();
        if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {

            BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BUSINESS_ID);

            NSAL0600Query query = NSAL0600Query.getInstance();
            List<Map<String, Object>> mapList = query.getDsContrBllgMtrPkList(glblCmpyCd, dsContrDtlPk);
            if (mapList == null || mapList.size() == 0) {
                return true;
            }

            // START 2023/08/28 S.Moriai [QC#59846, MOD]
//            NSZC047001PMsg pMsg = new NSZC047001PMsg();
            NSZC047008PMsg pMsg = new NSZC047008PMsg();
            // END 2023/08/28 S.Moriai [QC#59846, MOD]
            setValue(pMsg.glblCmpyCd, glblCmpyCd);
            // START 2023/08/28 S.Moriai [QC#59846, MOD]
//            setValue(pMsg.xxModeCd, "01");
            setValue(pMsg.xxModeCd, "08");
            // END 2023/08/28 S.Moriai [QC#59846, MOD]
            setValue(pMsg.slsDt, slsDt);
            setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
            setValue(pMsg.mtrCloDay, dsContrDtlTMsg.mtrCloDay);
            setValue(pMsg.mtrBllgTmgCd, dsContrDtlTMsg.mtrBllgTmgCd);
            setValue(pMsg.mtrBllgDay, dsContrDtlTMsg.mtrBllgDay);
            // START 2019/05/22 K.Kitachi [QC#50437, MOD]
//            setValue(pMsg.contrEffFromDt, sMsg.contrVrsnEffFromDt_N);
//            setValue(pMsg.contrEffThruDt, sMsg.contrVrsnEffThruDt_N);
            setValue(pMsg.contrEffFromDt, selectValue(sMsg.A.no(idx).contrEffFromDt_AN, sMsg.A.no(idx).contrEffFromDt_A));
            setValue(pMsg.contrEffThruDt, selectValue(sMsg.A.no(idx).contrEffThruDt_AN, sMsg.A.no(idx).contrEffThruDt_A));
            // END 2019/05/22 K.Kitachi [QC#50437, MOD]

            for (int i = 0; i < mapList.size(); i++) {
                BigDecimal dsContrBllgMtrPk = (BigDecimal) mapList.get(i).get("DS_CONTR_BLLG_MTR_PK");
                // START 2023/08/28 S.Moriai [QC#59846, DEL]
//                BigDecimal curPePk = query.getCurPePk(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, slsDt);
//                if (curPePk == null) {
//                    return true;
//                }
//
//                DS_CONTR_PRC_EFFTMsg peTMsg = query.getDsContrPrcEff(glblCmpyCd, curPePk);
//                if (peTMsg == null) {
//                    return true;
//                }
//
//                BigDecimal dsContrPrcEffPk = peTMsg.dsContrPrcEffPk.getValue();
//                BigDecimal dsContrPrcEffSqNum = peTMsg.dsContrPrcEffSqNum.getValue();
//                String bllgCycleCd = peTMsg.bllgCycleCd.getValue();
//
//                DS_CONTR_PRC_EFF_MTRTMsgArray tMsgArray = query.getDsContrPrcEffMtrTMsgArray(glblCmpyCd, dsContrPrcEffPk, dsContrBllgMtrPk);
//                for (int j = 0; j < tMsgArray.getValidCount(); j++) {
//                    setValue(pMsg.xxMtrLineList.no(j).dsContrPrcEffPk_ML, dsContrPrcEffPk);
//                    setValue(pMsg.xxMtrLineList.no(j).dsContrPrcEffSqNum_ML, dsContrPrcEffSqNum);
//                    setValue(pMsg.xxMtrLineList.no(j).dsContrPrcEffMtrPk_ML, tMsgArray.no(j).dsContrPrcEffMtrPk);
//                    setValue(pMsg.xxMtrLineList.no(j).mtrBllgCycleCd_ML, bllgCycleCd);
//                    setValue(pMsg.xxMtrLineList.no(j).dsContrBllgMtrPk_ML, dsContrBllgMtrPk);
//                    setValue(pMsg.xxMtrLineList.no(j).contrXsCopyPk_ML, tMsgArray.no(j).contrXsCopyPk);
//                    setValue(pMsg.xxMtrLineList.no(j).xsMtrCopyQty_ML, tMsgArray.no(j).xsMtrCopyQty);
//                    setValue(pMsg.xxMtrLineList.no(j).xsMtrAmtRate_ML, tMsgArray.no(j).xsMtrAmtRate);
//                    setValue(pMsg.xxMtrLineList.no(j).xsMtrFirstFlg_ML, tMsgArray.no(j).xsMtrFirstFlg);
//                    setValue(pMsg.xxMtrLineList.no(j).dsContrPrcEffStsCd_ML, peTMsg.dsContrPrcEffStsCd);
//                    setValue(pMsg.xxMtrLineList.no(j).qltyAsrnHldFlg_ML, peTMsg.qltyAsrnHldFlg);
//                    setValue(pMsg.xxMtrLineList.no(j).mtrHldFlg_ML, peTMsg.mtrHldFlg);
//                    setValue(pMsg.xxMtrLineList.no(j).contrHldFlg_ML, peTMsg.contrHldFlg);
//                    setValue(pMsg.xxMtrLineList.no(j).bllgHldFlg_ML, peTMsg.bllgHldFlg);
//                    setValue(pMsg.xxMtrLineList.no(j).qltyAsrnHldPendApvlFlg_ML, peTMsg.qltyAsrnHldPendApvlFlg);
//                }
//                pMsg.xxMtrLineList.setValidCount(tMsgArray.getValidCount());
                // END 2023/08/28 S.Moriai [QC#59846, DEL]

                // START 2023/08/28 S.Moriai [QC#59846, ADD]
                List<Map<String, Object>> dsContrPrcEffList = query.getDsContrPrcEff(pMsg, dsContrBllgMtrPk);
                if (dsContrPrcEffList.size() == 0) {
                    return true;
                }
                String oldFromDt = sMsg.A.no(idx).contrEffFromDt_A.getValue();
                String oldThruDt = sMsg.A.no(idx).contrEffThruDt_A.getValue();
                String newFromDt = sMsg.A.no(idx).contrEffFromDt_AN.getValue();
                String newThruDt = sMsg.A.no(idx).contrEffThruDt_AN.getValue();
                int lineIdx = 0;
                for (Map<String, Object> dsContrPrcEff : dsContrPrcEffList) {
                    String effFromDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_FROM_DT");
                    String effThruDt = (String) dsContrPrcEff.get("CONTR_PRC_EFF_THRU_DT");
                    NSZC047008_xxMtrLineListPMsg mtrLine = pMsg.xxMtrLineList.no(lineIdx);
                    setValue(mtrLine.dsContrPrcEffPk_ML, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_PK"));
                    setValue(mtrLine.dsContrPrcEffSqNum_ML, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_SQ_NUM"));
                    setValue(mtrLine.dsContrPrcEffStsCd_ML, (String) dsContrPrcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                    setValue(mtrLine.effFromDt_ML, effFromDt);
                    if (hasValue(newFromDt) && ZYPDateUtil.compare(newFromDt, oldFromDt) != 0) {
                        if (ZYPDateUtil.compare(oldFromDt, effFromDt) == 0 || (ZYPDateUtil.compare(newFromDt, effFromDt) > 0 && ZYPDateUtil.compare(newFromDt, effThruDt) < 0)) {
                            setValue(mtrLine.effFromDt_ML, newFromDt);
                        }
                    }
                    setValue(mtrLine.effThruDt_ML, effThruDt);
                    if (hasValue(newThruDt) && ZYPDateUtil.compare(newThruDt, oldThruDt) != 0) {
                        if (ZYPDateUtil.compare(oldThruDt, effThruDt) == 0 || (ZYPDateUtil.compare(newThruDt, effFromDt) > 0 && ZYPDateUtil.compare(newThruDt, effThruDt) < 0)) {
                            setValue(mtrLine.effThruDt_ML, newThruDt);
                        }
                    }
                    setValue(mtrLine.mtrBllgCycleCd_ML, (String) dsContrPrcEff.get("BLLG_CYCLE_CD"));
                    setValue(mtrLine.qltyAsrnHldFlg_ML, (String) dsContrPrcEff.get("QLTY_ASRN_HLD_FLG"));
                    setValue(mtrLine.mtrHldFlg_ML, (String) dsContrPrcEff.get("MTR_HLD_FLG"));
                    setValue(mtrLine.contrHldFlg_ML, (String) dsContrPrcEff.get("CONTR_HLD_FLG"));
                    setValue(mtrLine.bllgHldFlg_ML, (String) dsContrPrcEff.get("BLLG_HLD_FLG"));
                    setValue(mtrLine.qltyAsrnHldPendApvlFlg_ML, (String) dsContrPrcEff.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
                    setValue(mtrLine.dsContrPrcEffMtrPk_ML, (BigDecimal) dsContrPrcEff.get("DS_CONTR_PRC_EFF_MTR_PK"));
                    setValue(mtrLine.contrXsCopyPk_ML, (BigDecimal) dsContrPrcEff.get("CONTR_XS_COPY_PK"));
                    setValue(mtrLine.dsContrBllgMtrPk_ML, (BigDecimal) dsContrPrcEff.get("DS_CONTR_BLLG_MTR_PK"));
                    setValue(mtrLine.xsMtrCopyQty_ML, (BigDecimal) dsContrPrcEff.get("XS_MTR_COPY_QTY"));
                    setValue(mtrLine.xsMtrAmtRate_ML, (BigDecimal) dsContrPrcEff.get("XS_MTR_AMT_RATE"));
                    setValue(mtrLine.xsMtrFirstFlg_ML, (String) dsContrPrcEff.get("XS_MTR_FIRST_FLG"));
                    lineIdx++;
                }
                pMsg.xxMtrLineList.setValidCount(lineIdx);
                // END 2023/08/28 S.Moriai [QC#59846, ADD]

                if (!executeApi(pMsg, cMsg)) {
                    return false;
                }
                // START 2023/08/28 S.Moriai [QC#59846, ADD]
                if (!createXsCopy(pMsg, cMsg, dsContrDtlPk, dsContrBllgMtrPk)) {
                    return false;
                }
                // END 2023/08/28 S.Moriai [QC#59846, ADD]
            }
            return true;
        }
        return true;
    }
    // Mod End   08/02/2016 <QC#7402>

    // START 2023/08/28 S.Moriai [QC#59846, MOD]
//    private static boolean executeApi(NSZC047001PMsg pMsg, NSAL0600CMsg cMsg) {
    private static boolean executeApi(NSZC047008PMsg pMsg, NSAL0600CMsg cMsg) {
    // END 2023/08/28 S.Moriai [QC#59846, MOD]
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            cMsg.setMessageInfo(msgId, msgPrms);
            return false;
        }
        return true;
    }

    private boolean insertSvcMemo(NSAL0600CMsg cMsg) {
        SVC_MEMOTMsg outMsg = createSvcMemoData(cMsg);
        S21FastTBLAccessor.insert(outMsg);
        if (!RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"SVC_MEMO" });
            return false;
        }
        return true;
    }

    private SVC_MEMOTMsg createSvcMemoData(NSAL0600CMsg cMsg) {
        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.CASCADE_DATE);
        setValue(svcMemoTMsg.svcCmntTxt, cMsg.svcCmntTxt_H);
        setValue(svcMemoTMsg.dsContrPk, cMsg.dsContrPk_P);
        setValue(svcMemoTMsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        setValue(svcMemoTMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_HS);
        return svcMemoTMsg;
    }

    // START 2019/05/22 K.Kitachi [QC#50437, ADD]
    private static EZDSDateItem selectValue(EZDSDateItem item1, EZDSDateItem item2) {
        if (hasValue(item1)) {
            return item1;
        }
        return item2;
    }
    // END 2019/05/22 K.Kitachi [QC#50437, ADD]

    // START 2023/08/28 S.Moriai [QC#59846, ADD]
    private static BigDecimal getTermAmt(NSZC047008PMsg pMsg, NSAL0600CMsg cMsg, Map<String, Object> dsContrPrcEff, String effFromDt, String effThruDt) {
        NSZC047021PMsg m21PMsg = new NSZC047021PMsg();
        setValue(m21PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(m21PMsg.xxModeCd, "21");
        setValue(m21PMsg.slsDt, pMsg.slsDt);
        setValue(m21PMsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        setValue(m21PMsg.contrCloDay, pMsg.contrCloDay);
        setValue(m21PMsg.baseBllgTmgCd, pMsg.baseBllgTmgCd);
        setValue(m21PMsg.baseBllgCycleCd, (String) dsContrPrcEff.get("BLLG_CYCLE_CD"));
        setValue(m21PMsg.ccyCd, (String) dsContrPrcEff.get("CCY_CD"));
        setValue(m21PMsg.effFromDt, effFromDt);
        setValue(m21PMsg.effThruDt, effThruDt);
        setValue(m21PMsg.basePrcDealAmt, (BigDecimal) dsContrPrcEff.get("BASE_PRC_DEAL_AMT"));

        NSZC047001 api = new NSZC047001();
        api.execute(m21PMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        if (m21PMsg.xxMsgIdList.getValidCount() > 0) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            cMsg.setMessageInfo(msgId, msgPrms);
            return BigDecimal.ZERO;
        }
        BigDecimal totTermAmt = m21PMsg.basePrcTermDealAmtRate.getValue();
        int bllgCycleCnt = calcBllgCycleCntFromDuration(m21PMsg);
        if (bllgCycleCnt > 0) {
            totTermAmt = m21PMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
        }

        return totTermAmt;
    }

    private static int calcBllgCycleCntFromDuration(NSZC047021PMsg pMsg) {
        // calculate duration
        DateFormat df = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date startDt;
        try {
            startDt = df.parse(pMsg.effFromDt.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = pMsg.effThruDt.getValue();
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        // get BLLG_CYCLE Info
        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, pMsg.baseBllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (bcTMsg == null || durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }

    private static boolean isUpdatePricePeriod(NSZC047008PMsg pMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        String slsDt = pMsg.slsDt.getValue();
        String fromDt;
        String thruDt;
        // CONTR_PRC_EFF_FROM_DT < Sales Date < CONTR_PRC_EFF_THRU_DT
        for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
            fromDt = pMsg.xxBaseLineList.no(i).effFromDt_BL.getValue();
            thruDt = pMsg.xxBaseLineList.no(i).effThruDt_BL.getValue();
            if (ZYPDateUtil.compare(fromDt, slsDt) <= 0 && ZYPDateUtil.compare(slsDt, thruDt) <= 0) {
                return checkPricePeriod(pMsg.xxBaseLineList.no(i), dsContrDtlTMsg);
            }
        }

        String contrDtlFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
        String contrDtlThruDt = dsContrDtlTMsg.contrEffThruDt.getValue();
        if (ZYPDateUtil.compare(slsDt, contrDtlFromDt) < 0) {
            // DS_CONTR_DTL.CONTR_EFF_FROM_DT = DS_CONTR_PRC_EFF.CONTR_PRC_EFF_FROM_DT
            for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
                fromDt = pMsg.xxBaseLineList.no(i).effFromDt_BL.getValue();
                if (ZYPDateUtil.compare(fromDt, contrDtlFromDt) == 0) {
                    return checkPricePeriod(pMsg.xxBaseLineList.no(i), dsContrDtlTMsg);
                }
            }
        } else {
            // DS_CONTR_DTL.CONTR_EFF_THRU_DT = DS_CONTR_PRC_EFF.CONTR_PRC_EFF_THRU_DT
            for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
                thruDt = pMsg.xxBaseLineList.no(i).effThruDt_BL.getValue();
                if (ZYPDateUtil.compare(thruDt, contrDtlThruDt) == 0) {
                    return checkPricePeriod(pMsg.xxBaseLineList.no(i), dsContrDtlTMsg);
                }
            }
        }
        return false;
    }

    private static boolean checkPricePeriod(NSZC047008_xxBaseLineListPMsg baseLine, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal prcEffBaseDealAmt = baseLine.basePrcDealAmt_BL.getValue();
        BigDecimal dbBaseDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
        if (prcEffBaseDealAmt.compareTo(dbBaseDealAmt) != 0) {
            setValue(dsContrDtlTMsg.basePrcDealAmt, prcEffBaseDealAmt);
            return true;
        }
        return false;
    }

    private static boolean isUpdateTermAmt(NSZC047008PMsg pMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal prcEffBasePrcTermDealAmtRate = BigDecimal.ZERO;
        BigDecimal dbBasePrcTermDealAmtRate = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue();
        for (int i = 0; i < pMsg.xxBaseLineList.getValidCount(); i++) {
            if (!hasValue(pMsg.xxBaseLineList.no(i).basePrcTermDealAmtRate_BL)) {
                continue;
            }
            prcEffBasePrcTermDealAmtRate = prcEffBasePrcTermDealAmtRate.add(pMsg.xxBaseLineList.no(i).basePrcTermDealAmtRate_BL.getValue());
        }

        if (prcEffBasePrcTermDealAmtRate.compareTo(dbBasePrcTermDealAmtRate) != 0) {
            setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, prcEffBasePrcTermDealAmtRate);
            return true;
        }
        return false;
    }

    private static boolean updateDsContrDtl(NSAL0600CMsg cMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        DS_CONTR_DTLTMsg tMsg = NSAL0600Query.getInstance().getDsContrDtlForUpdate(dsContrDtlTMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
        if (tMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Detail" });
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(dsContrDtlTMsg.ezUpTime.getValue(), dsContrDtlTMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        EZDTBLAccessor.update(dsContrDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Detail" });
            return false;
        }
        return true;
    }

    private static boolean createXsCopy(NSZC047008PMsg pMsg, NSAL0600CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        NSAL0600Query query = NSAL0600Query.getInstance();
        DS_CONTR_PRC_EFFTMsgArray prcEffArray = query.getDsContrPrcEff(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
        if (prcEffArray.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Price Effectivity" });
            return false;
        }
        boolean updFlg = false;
        String slsDt = pMsg.slsDt.getValue();
        String targetDt = slsDt;
        DS_CONTR_PRC_EFFTMsg prcEff = null;
        String contrEffThruDt = query.getContrEffThruDt(glblCmpyCd, dsContrDtlPk);
        if (slsDt.compareTo(pMsg.contrEffFromDt.getValue()) < 0) {
            targetDt = pMsg.contrEffFromDt.getValue();
        } else if (slsDt.compareTo(contrEffThruDt) > 0) {
            targetDt = contrEffThruDt;
        }
        for (int i = 0; i < prcEffArray.getValidCount(); i++) {
            if (targetDt.compareTo(prcEffArray.no(i).contrPrcEffFromDt.getValue()) >= 0 && targetDt.compareTo(prcEffArray.no(i).contrPrcEffThruDt.getValue()) <= 0) {
                prcEff = prcEffArray.no(i);
            }
        }
        if (prcEff == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Price Effectivity" });
            return false;
        }

        CONTR_XS_COPYTMsgArray xsCopyArray = query.getContrXsCopy(glblCmpyCd, dsContrBllgMtrPk);
        DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrArray = query.getPrcEffMtrForUpdate(prcEff);
        if (xsCopyArray.getValidCount() != prcEffMtrArray.getValidCount()) {
            updFlg = true;
        } else {
            for (int i = 0; i < prcEffMtrArray.getValidCount(); i++) {
                if (xsCopyArray.no(i).xsMtrAmtRate.getValue().compareTo(prcEffMtrArray.no(i).xsMtrAmtRate.getValue()) != 0
                        || xsCopyArray.no(i).xsMtrCopyQty.getValue().compareTo(prcEffMtrArray.no(i).xsMtrCopyQty.getValue()) != 0
                        || !xsCopyArray.no(i).xsMtrFirstFlg.getValue().equals(prcEffMtrArray.no(i).xsMtrFirstFlg.getValue())) {
                    updFlg = true;
                    break;
                }
                if (!hasValue(prcEffMtrArray.no(i).contrXsCopyPk)) {
                    setValue(prcEffMtrArray.no(i).contrXsCopyPk, xsCopyArray.no(i).contrXsCopyPk);
                    EZDTBLAccessor.update(prcEffMtrArray.no(i));
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEffMtrArray.no(i).getReturnCode())) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective Meter" });
                    }
                }
            }
        }
        if (!updFlg) {
            return true;
        }
        for (int i = 0; i < xsCopyArray.getValidCount(); i++) {
            EZDTBLAccessor.remove(xsCopyArray.no(i));
        }
        for (int i = 0; i < prcEffMtrArray.getValidCount(); i++) {
            CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.contrXsCopyPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ));
            setValue(inMsg.dsContrBllgMtrPk, prcEffMtrArray.no(i).dsContrBllgMtrPk);
            setValue(inMsg.xsMtrCopyQty, prcEffMtrArray.no(i).xsMtrCopyQty);
            setValue(inMsg.xsMtrAmtRate, prcEffMtrArray.no(i).xsMtrAmtRate);
            setValue(inMsg.xsMtrFirstFlg, prcEffMtrArray.no(i).xsMtrFirstFlg);
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Excess Copy" });
            }
            setValue(prcEffMtrArray.no(i).contrXsCopyPk, inMsg.contrXsCopyPk);
            EZDTBLAccessor.update(prcEffMtrArray.no(i));
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEffMtrArray.no(i).getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective Meter" });
            }
        }
        return true;
    }
    // END 2023/08/28 S.Moriai [QC#59846, ADD]
}

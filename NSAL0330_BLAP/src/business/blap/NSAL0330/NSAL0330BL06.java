/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0330;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0330.common.NSAL0330CommonLogic;
import business.blap.NSAL0330.constant.NSAL0330Constant;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047002PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2016/01/14   Hitachi         T.Tomita        Update          CSA QC#2950
 * 2016/02/23   Hitachi         T.Tomita        Update          CSA QC#3896
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/08/05   Hitachi         K.Kishimoto     Update          QC#12879
 * 2016/09/06   Hitachi         K.Kishimoto     Update          QC#12429
 * 2016/11/17   Hitachi         T.Tomita        Update          QC#15942
 *</pre>
 */
public class NSAL0330BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0330CMsg bizMsg = (NSAL0330CMsg) cMsg;
        NSAL0330SMsg sharedMsg = (NSAL0330SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0330Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_CMN_Save(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_Schedules".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_CMN_Save(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_CMN_Save <dd>The method
     * explanation: save button event
     * @param bizMsg NSAL0330CMsg
     * @param sharedMsg NSAL0330SMsg
     */
    private void doProcess_NSAL0330Scrn00_CMN_Save(NSAL0330CMsg bizMsg, NSAL0330SMsg sharedMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (!inputCheck(glblCmpyCd, bizMsg)) {
            return;
        }

        // START 2015/10/21 T.Tomita [N/A, MOD]
        boolean updateFlg = false;
        String chngRngFlg = ZYPConstant.FLG_ON_Y;
        if (NSAL0330CommonLogic.isChangedHeader(bizMsg, sharedMsg)) {
            chngRngFlg = ZYPConstant.FLG_OFF_N;
            if (!updateDsContrDtl(bizMsg, sharedMsg, glblCmpyCd)) {
                return;
            }
            // START 2016/11/17 T.Tomita [QC#15942, ADD]
            S21SsmEZDResult res = NSAL0330Query.getInstance().getDsContrDtlPkListByAggMach(glblCmpyCd, bizMsg.dsContrPk_H1.getValue());
            List<BigDecimal> dsContrDtlPkList = (List<BigDecimal>) res.getResultObject();
            for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
                dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
                setDsContrDtlData(dsContrDtlTMsg, bizMsg);
                S21FastTBLAccessor.update(dsContrDtlTMsg);
            }
            // END 2016/11/17 T.Tomita [QC#15942, ADD]
            updateFlg = true;
        }

        // START 2016/01/14 T.Tomita [QC#2950, MOD]
        if (updateFlg == true || NSAL0330CommonLogic.isChangedSchedules(bizMsg, sharedMsg)) {
            if (!createOrUpdateSvcMemo(bizMsg, sharedMsg, glblCmpyCd)) {
                return;
            }

            if (!updateScheduleInfo(bizMsg, sharedMsg, glblCmpyCd, chngRngFlg)) {
                return;
            }
        }
        // END 2016/01/14 T.Tomita [QC#2950, MOD]
        // END 2015/10/21 T.Tomita [N/A, MOD]
        bizMsg.setMessageInfo(NSAL0330Constant.NZZM0002I);
    }

    private boolean updateDsContrDtl(NSAL0330CMsg bizMsg, NSAL0330SMsg sharedMsg, String glblCmpyCd) {

        // START 2015/10/21 T.Tomita [N/A, DEL]
//        if (!NSAL0330CommonLogic.isChangedHeader(bizMsg, sharedMsg)) {
//            return true;
//        }
        // START 2015/10/21 T.Tomita [N/A, DEL]

        DS_CONTR_DTLTMsg exDsContrDtlTMsg = NSAL0330CommonLogic.findDsContrDtlForUpdate(glblCmpyCd, bizMsg.dsContrDtlPk_H1.getValue());
        if (exDsContrDtlTMsg == null) {
            String[] args = {"DS_CONTR_DTL" };
            bizMsg.setMessageInfo(NSAL0330Constant.NSAM0045E, args);
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), exDsContrDtlTMsg.ezUpTime.getValue(), exDsContrDtlTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(NSAL0330Constant.NZZM0003E);
            return false;
        }

        if (!setDsContrDtlData(exDsContrDtlTMsg, bizMsg)) {
            return false;
        }

        // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.contrCloDay_H1, exDsContrDtlTMsg.contrCloDay);
        // END 2016/05/18 T.Kanasaka [QC#2184, ADD]

        S21FastTBLAccessor.update(exDsContrDtlTMsg);
        if (!RTNCD_NORMAL.equals(exDsContrDtlTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NSAL0330Constant.NSAM0001E, new String[] {"DS_CONTR_DTL", "glblCmpyCd=" + glblCmpyCd + ", dsContrDtlPk=" + exDsContrDtlTMsg.dsContrDtlPk.getValue() });
            return false;
        }
        return true;
    }

    private boolean createOrUpdateSvcMemo(NSAL0330CMsg bizMsg, NSAL0330SMsg sharedMsg, String glblCmpyCd) {

        // START 2015/10/21 T.Tomita [N/A, DEL]
//        if (!NSAL0330CommonLogic.isChangedFooter(bizMsg, sharedMsg)) {
//            return true;
//        }
        // END 2015/10/21 T.Tomita [N/A, DEL]

        // START 2016/02/23 T.Tomita [QC#3896, MOD]
        if (!inputSvcMemoCheck(bizMsg)) {
            return false;
        }
        // END 2016/02/23 T.Tomita [QC#3896, MOD]

        // START 2015/10/21 T.Tomita [N/A, MOD]
        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();

        if (!setSvcMemoData(svcMemoTMsg, bizMsg, true)) {
            return false;
        }

        S21FastTBLAccessor.create(svcMemoTMsg);
        if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NSAL0330Constant.NSAM0012E, new String[] {"SVC_MEMO", "glblCmpyCd=" + glblCmpyCd + ", svcMemoPk=" + svcMemoTMsg.svcMemoPk.getValue() });
            return false;
        }

//        SVC_MEMOTMsg exSvcMemoTMsg = NSAL0330CommonLogic.findSvcMemoForUpdate(glblCmpyCd, bizMsg.svcMemoPk_F1.getValue());
//        if (exSvcMemoTMsg == null) {
//
//            SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
//
//            if (!setSvcMemoData(svcMemoTMsg, bizMsg, true)) {
//                return false;
//            }
//
//            S21FastTBLAccessor.create(svcMemoTMsg);
//            if (!RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NSAL0330Constant.NSAM0012E, new String[] {"SVC_MEMO", "glblCmpyCd=" + glblCmpyCd + ", svcMemoPk=" + svcMemoTMsg.svcMemoPk.getValue() });
//                return false;
//            }
//        } else {
//            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_F1.getValue(), bizMsg.ezUpTimeZone_F1.getValue(), exSvcMemoTMsg.ezUpTime.getValue(), exSvcMemoTMsg.ezUpTimeZone.getValue())) {
//                bizMsg.setMessageInfo(NSAL0330Constant.NZZM0003E);
//                return false;
//            }
//
//            if (!setSvcMemoData(exSvcMemoTMsg, bizMsg, false)) {
//                return false;
//            }
//
//            S21FastTBLAccessor.update(exSvcMemoTMsg);
//            if (!RTNCD_NORMAL.equals(exSvcMemoTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NSAL0330Constant.NSAM0001E, new String[] {"SVC_MEMO", "glblCmpyCd=" + glblCmpyCd + ", svcMemoPk=" + exSvcMemoTMsg.svcMemoPk.getValue() });
//                return false;
//            }
//        }
        // END 2015/10/21 T.Tomita [N/A, MOD]
        return true;

    }

    // START 2015/10/21 T.Tomita [N/A, MOD]
    private boolean updateScheduleInfo(NSAL0330CMsg bizMsg, NSAL0330SMsg sharedMsg, String glblCmpyCd, String chngRngFlg) {

        // START 2016/01/14 T.Tomita [QC#2950, DEL]
//        if (!NSAL0330CommonLogic.isChangedSchedules(bizMsg, sharedMsg)) {
//            return true;
//        }
        // END 2016/01/14 T.Tomita [QC#2950, DEL]

        int cnt = bizMsg.A.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NSAL0330_ACMsg acMsg = bizMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(acMsg.dsContrBllgSchdSmryPk_A1)) {
                DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTMsg = NSAL0330CommonLogic.findDsContrBllgSchdSmryForUpdate(glblCmpyCd, acMsg.dsContrBllgSchdSmryPk_A1.getValue(), bizMsg.dsContrDtlPk_H1.getValue());
                if (dsContrBllgSchdSmryTMsg == null) {
                    String[] args = {"DS_CONTR_BLLG_SCHD_SMRY" };
                    bizMsg.setMessageInfo(NSAL0330Constant.NSAM0045E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A1.getValue(), acMsg.ezUpTimeZone_A1.getValue(), dsContrBllgSchdSmryTMsg.ezUpTime.getValue(), dsContrBllgSchdSmryTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NSAL0330Constant.NZZM0003E);
                    return false;
                }

            }
        }

        // START 2016/11/17 T.Tomita [QC#15942, ADD]
        if (ZYPConstant.FLG_OFF_N.equals(chngRngFlg)) {
            S21SsmEZDResult res = NSAL0330Query.getInstance().getDsContrDtlPkListByAgg(glblCmpyCd, bizMsg.dsContrPk_H1.getValue());
            List<BigDecimal> dsContrDtlPkList = (List<BigDecimal>) res.getResultObject();
            boolean isErr = false;
            if (dsContrDtlPkList.size() == 0) {
                // Non Fleet or Fleet
                isErr = !callScheduleApiContractMode(bizMsg, glblCmpyCd, bizMsg.dsContrDtlPk_H1.getValue());
                if (isErr) {
                    return false;
                }
            } else {
                // Aggregate
                for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
                    isErr = !callScheduleApiContractMode(bizMsg, glblCmpyCd, dsContrDtlPk);
                    if (isErr) {
                        return false;
                    }
                }
            }
            DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
            dsContrPrcEffTMsg.setSQLID("006");
            dsContrPrcEffTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dsContrPrcEffTMsg.setConditionValue("dsContrDtlPk01", bizMsg.dsContrDtlPk_H1.getValue());
            DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffTMsgArray = (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(dsContrPrcEffTMsg);
            if (dsContrPrcEffTMsgArray.getValidCount() == 0) {
                bizMsg.setMessageInfo(NSAL0330Constant.NSAM0045E, new String[] {"DS_CONTR_PRC_EFF" });
                return false;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPrcEffPk_H1, dsContrPrcEffTMsgArray.no(0).dsContrPrcEffPk);
            return true;
        }
        // END 2016/11/17 T.Tomita [QC#15942, ADD]

        return callScheduleApi(bizMsg, glblCmpyCd, chngRngFlg);
    }
    // END 2015/10/21 T.Tomita [N/A, MOD]

    // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
    private boolean setDsContrDtlData(DS_CONTR_DTLTMsg dsContrDtlTMsg, NSAL0330CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseBllgTmgCd, bizMsg.baseBllgTmgCd_H1);
        // START 2016/08/05 K.Kishimoto [QC#12879, MOD]
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, bizMsg.baseDplyPerEndDay_H1);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrCloDay, bizMsg.baseDplyPerEndDay_H1);
//        if (NSAL0330Constant.RADIO_VALUE_CLOSING_DAY.compareTo(bizMsg.xxRadioBtn_H1.getValue()) == 0) {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, bizMsg.baseDplyPerEndDay_H1);

        if (NSAL0330Constant.MIN_DAY.equals(bizMsg.baseDplyPerEndDay_H1.getValue())) {
            int fromDt = 0;
            if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.contrEffFromDt)) {
                fromDt = Integer.parseInt(dsContrDtlTMsg.contrEffFromDt.getValue().substring(6));
            }
            if (fromDt >= 2 && fromDt <= 28) {
                fromDt--;
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrCloDay, new Integer(fromDt).toString());
            } else {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrCloDay, NSAL0330Constant.MAX_DAY);
            }
        }
//            } else {
//                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrCloDay, bizMsg.baseDplyPerEndDay_H1);
//            }
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.baseDplyPerEndDay, NSAL0330Constant.MAX_DAY);
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrCloDay, NSAL0330Constant.MAX_DAY);
//        }
//        if (NSAL0330Constant.RADIO_VALUE_BLLG_DAY.compareTo(bizMsg.xxRadioBtn_H2.getValue()) == 0) {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, bizMsg.contrBllgDay_H1);
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, NSAL0330Constant.MAX_DAY);
//        }
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.contrBllgDay, bizMsg.contrBllgDay_H1);
        // END 2016/08/05 K.Kishimoto [QC#12879, MOD]
        return true;
    }
    // END 2016/05/18 T.Kanasaka [QC#2184, MOD]

    private boolean setSvcMemoData(SVC_MEMOTMsg svcMemoTMsg, NSAL0330CMsg bizMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrPk, bizMsg.dsContrPk_H1);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.dsContrDtlPk, bizMsg.dsContrDtlPk_H1);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.CHANGE_VIEW_PRICING);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        }

        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, bizMsg.svcCmntTxt_F1);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoRsnCd, bizMsg.svcMemoRsnCd_F3);
        // START 2015/10/21 T.Tomita [N/A, ADD]
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTrxNm, "DS_CONTR_PRC_EFF_PK");
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTrxNum, bizMsg.dsContrPrcEffPk_H1.getValue().toString());
        // END 2015/10/21 T.Tomita [N/A, ADD]

        return true;
    }

    // private void
    // setDsContrBllgSchdSmryData(DS_CONTR_BLLG_SCHD_SMRYTMsg
    // dsContrBllgSchdSmryTMsg, NSAL0330CMsg bizMsg, NSAL0330_ACMsg
    // acMsg, boolean isCreate) {
    // String glblCmpyCd = getGlobalCompanyCode();
    //
    // if (isCreate) {
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.glblCmpyCd,
    // glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk,
    // ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_BLLG_SCHD_SMRY_SQ"));
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.dsContrDtlPk,
    // bizMsg.dsContrDtlPk_H1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.dsContrBllgMtrPk,
    // bizMsg.dsContrDtlPk_H1);
    // }
    //
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum,
    // acMsg.dsContrBllgSchdSqNum_A1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.perSchdNum,
    // acMsg.perSchdNum_A1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.perBllgCycleCd,
    // acMsg.perBllgCycleCd_A1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.bllgSchdFromDt,
    // acMsg.bllgSchdFromDt_A1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.bllgSchdThruDt,
    // acMsg.bllgSchdThruDt_A1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.bllgCycleCd,
    // acMsg.bllgCycleCd_A3);
    //
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.basePrcDealAmt,
    // acMsg.basePrcDealAmt_A1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.baseSubTotPrcFuncAmt,
    // acMsg.bllgCycleCd_A3);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.ccyCd,
    // bizMsg.ccyCd_H1);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.mlyCopyInclPrcQty,
    // acMsg.bllgCycleCd_A3);
    // ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTMsg.dsContrBllgSchdTrmnDt,
    // acMsg.bllgCycleCd_A3);
    //
    // }

    private boolean inputCheck(String glblCmpyCd, NSAL0330CMsg bizMsg) {

        boolean result = true;
        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
//        // Check closing day
//        if (NSAL0330Constant.RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH.equals(bizMsg.xxRadioBtn_H1.getValue())) {
//            if (ZYPCommonFunc.hasValue(bizMsg.baseDplyPerEndDay_H1)) {
//                bizMsg.baseDplyPerEndDay_H1.setErrorInfo(1, NSAL0330Constant.NSAM0149E, new String[] {"Last Day of the Month", "Closing Day" });
//                result = false;
//            }
//        } else {
//            if (!ZYPCommonFunc.hasValue(bizMsg.baseDplyPerEndDay_H1)) {
//                bizMsg.baseDplyPerEndDay_H1.setErrorInfo(1, NSAL0330Constant.NSAM0150E, new String[] {"Closing", "Closing Day" });
//                result = false;
//            }
//        }
//        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
//
//        // Check billing day
//        if (NSAL0330Constant.RADIO_VALUE_BLLG_LAST_DAY_OF_MONTH.equals(bizMsg.xxRadioBtn_H2.getValue())) {
//            if (ZYPCommonFunc.hasValue(bizMsg.contrBllgDay_H1)) {
//                bizMsg.contrBllgDay_H1.setErrorInfo(1, NSAL0330Constant.NSAM0149E, new String[] {"End of Month", "Billing Day" });
//                result = false;
//            }
//        } else {
//            if (!ZYPCommonFunc.hasValue(bizMsg.contrBllgDay_H1)) {
//                bizMsg.contrBllgDay_H1.setErrorInfo(1, NSAL0330Constant.NSAM0150E, new String[] {"Billing Day", "Billing Day" });
//                result = false;
//            }
//        }
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]

        if (!checkScheduleDate(glblCmpyCd, bizMsg)) {
            result = false;
        }

        return result;
    }

    // START 2016/02/23 T.Tomita [QC#3896, ADD]
    private boolean inputSvcMemoCheck(NSAL0330CMsg bizMsg) {

        boolean result = true;
        if (!ZYPCommonFunc.hasValue(bizMsg.svcMemoRsnCd_F3)) {
            bizMsg.svcMemoRsnCd_F3.setErrorInfo(1, NSAL0330Constant.ZZM9000E, new String[] {"Reason Code" });
            result = false;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.svcCmntTxt_F1)) {
            bizMsg.svcCmntTxt_F1.setErrorInfo(1, NSAL0330Constant.ZZM9000E, new String[] {"Comment" });
            result = false;
        }

        return result;
    }
    // END 2016/02/23 T.Tomita [QC#3896, ADD]

    private boolean checkScheduleDate(String glblCmpyCd, NSAL0330CMsg bizMsg) {
        boolean rtnFlg = true;

        // START 2015/10/21 T.Tomita [N/A, ADD]
        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NSAL0330Constant.NSAM0045E, new String[]{"Schedule Data" });
            return false;
        }
        // END 2015/10/21 T.Tomita [N/A, ADD]

        NSAL0330CMsg copyBizMsg = new NSAL0330CMsg();
        EZDMsg.copy(bizMsg.A, null, copyBizMsg.A, null);

        // SORT
        S21SortTarget sortTarget = new S21SortTarget(copyBizMsg.A, copyBizMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add("bllgSchdFromDt_A1", S21SortKey.ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, copyBizMsg.A.getValidCount());

        // START 2015/10/21 T.Tomita [N/A, ADD]
        setDsContrBllgSchdSqNum(copyBizMsg.A);
        EZDMsg.copy(copyBizMsg.A, null, bizMsg.A, null);
        // END 2015/10/21 T.Tomita [N/A, ADD]

        // START 2015/10/21 T.Tomita [N/A, MOD]
        NSAL0330_ACMsg acMsg = null;
        NSAL0330_ACMsg prevAcMsg = null;
        int cnt = copyBizMsg.A.getValidCount();
//        boolean hasMissing = false;
//        boolean hasDuplication = false;
        for (int i = 0; i < cnt; i++) {
            if (i == 0) {
                continue;
            }
            prevAcMsg = bizMsg.A.no(i - 1);
            acMsg = bizMsg.A.no(i);
            String prevThruDt = prevAcMsg.bllgSchdThruDt_A1.getValue();
            String fromDt = acMsg.bllgSchdFromDt_A1.getValue();
            if (fromDt.compareTo(prevThruDt) <= 0) {
//                hasDuplication = true;
//                break;
                acMsg.bllgSchdFromDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0337E);
                rtnFlg = false;
            }
            String addDate = ZYPDateUtil.addDays(prevThruDt, 1);
            if (!addDate.equals(fromDt)) {
//                hasMissing = true;
                acMsg.bllgSchdFromDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0336E);
                rtnFlg = false;
            }
        }

        // Check Price Effective From Date
        String startSchdFromDt = bizMsg.A.no(0).bllgSchdFromDt_A1.getValue();
        if (ZYPDateUtil.compare(startSchdFromDt, bizMsg.contrEffFromDt_H1.getValue()) < 0) {
            // Mod Start 09/06/2016 <QC#12429>
            bizMsg.A.no(0).bllgSchdFromDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0606E);
            // Mod End   09/06/2016 <QC#12429>
            rtnFlg = false;
        } else if (ZYPDateUtil.compare(startSchdFromDt, bizMsg.contrEffFromDt_H1.getValue()) > 0) {
            bizMsg.A.no(0).bllgSchdFromDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0336E);
            rtnFlg = false;
        }

        // Check Price Effective True Date
        int listSize = bizMsg.A.getValidCount();
        String endSchdThruDt = bizMsg.A.no(listSize - 1).bllgSchdThruDt_A1.getValue();
        if (ZYPDateUtil.compare(endSchdThruDt, bizMsg.contrEffThruDt_H1.getValue()) > 0) {
            // Mod Start 09/06/2016 <QC#12429>
            bizMsg.A.no(listSize - 1).bllgSchdThruDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0606E);
            // Mod End   09/06/2016 <QC#12429>
            rtnFlg = false;
        } else if (ZYPDateUtil.compare(endSchdThruDt, bizMsg.contrEffThruDt_H1.getValue()) < 0) {
            bizMsg.A.no(listSize - 1).bllgSchdThruDt_A1.setErrorInfo(1, NSAL0330Constant.NSAM0336E);
            rtnFlg = false;
        }

//        if (hasDuplication) {
//            bizMsg.setMessageInfo(NSAL0330Constant.NSAM0337E);
//            rtnFlg = false;
//        } else if (hasMissing) {
//            bizMsg.setMessageInfo(NSAL0330Constant.NSAM0336E);
//            rtnFlg = false;
//        }
        // END 2015/10/21 T.Tomita [N/A, MOD]
        return rtnFlg;
    }

    // START 2015/10/21 T.Tomita [N/A, ADD]
    private void setDsContrBllgSchdSqNum(NSAL0330_ACMsgArray acMsgArray) {
        int dsContrBllgSchdSqNum = 0;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            dsContrBllgSchdSqNum++;
            ZYPEZDItemValueSetter.setValue(acMsgArray.no(i).dsContrBllgSchdSqNum_A1, Integer.toString(dsContrBllgSchdSqNum));
        }
    }

    private boolean callScheduleApi(NSAL0330CMsg cMsg, String glblCmpyCd, String chngRngFlg) {
        NSZC047002PMsg pMsg = setPmsg(cMsg, glblCmpyCd, chngRngFlg);
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            cMsg.setMessageInfo(NSAL0330Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk_H1.getValue() });
            return false;
        }
        return true;
    }

    private NSZC047002PMsg setPmsg(NSAL0330CMsg cMsg, String glblCmpyCd, String chngRngFlg) {
        NSZC047002PMsg pMsg = new NSZC047002PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "02");
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, cMsg.dsContrDtlPk_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);

        String contrCloDay = cMsg.contrCloDay_H1.getValue();
        // START 2016/05/18 T.Kanasaka [QC#2184, DEL]
//        if (NSAL0330Constant.RADIO_VALUE_CLOSING_DAY.compareTo(cMsg.xxRadioBtn_H1.getValue()) != 0) {
//            contrCloDay = NSAL0330Constant.MAX_DAY;
//        }
        // END 2016/05/18 T.Kanasaka [QC#2184, DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, contrCloDay);

        ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, cMsg.baseBllgTmgCd_H1);

        String contrBllgDay = cMsg.contrBllgDay_H1.getValue();
        // START 2016/08/05 K.Kishimoto [QC#12879, DEL]
//        if (NSAL0330Constant.RADIO_VALUE_BLLG_DAY.compareTo(cMsg.xxRadioBtn_H2.getValue()) != 0) {
//            contrBllgDay = NSAL0330Constant.MAX_DAY;
//        }
        // END 2016/08/05 K.Kishimoto [QC#12879, DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, contrBllgDay);

        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, cMsg.contrEffFromDt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, cMsg.contrEffThruDt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.chngRngFlg, chngRngFlg);

        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).dsContrPrcEffSqNum_BL, cMsg.A.no(i).dsContrPrcEffSqNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).effFromDt_BL, cMsg.A.no(i).bllgSchdFromDt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).effThruDt_BL, cMsg.A.no(i).bllgSchdThruDt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).baseBllgCycleCd_BL, cMsg.A.no(i).bllgCycleCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).basePrcDealAmt_BL, cMsg.A.no(i).basePrcDealAmt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).basePrcTermDealAmtRate_BL, cMsg.A.no(i).baseSubTotPrcDealAmt_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).dsContrBllgSchdSqNum_BL, cMsg.A.no(i).dsContrBllgSchdSqNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).perBllgCycleCd_BL, cMsg.A.no(i).perBllgCycleCd_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).perSchdNum_BL, cMsg.A.no(i).perSchdNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).dsContrPrcEffPk_BL, cMsg.dsContrPrcEffPk_H1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(i).dsContrBllgSchdSmryPk_BL, cMsg.A.no(i).dsContrBllgSchdSmryPk_A1);
        }
        pMsg.xxBaseLineList.setValidCount(i);

        return pMsg;
    }
    // END 2015/10/21 T.Tomita [N/A, ADD]

    // START 2016/11/17 T.Tomita [QC#15942, ADD]
    private boolean callScheduleApiContractMode(NSAL0330CMsg cMsg, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        NSZC047001PMsg pMsg = setPMsgContractMode(cMsg, glblCmpyCd, dsContrDtlPk);
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            cMsg.setMessageInfo(NSAL0330Constant.NSAM0003E, new String[] {"NSZC047001", pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "DS_CONTR_DTL_PK=" + cMsg.dsContrDtlPk_H1.getValue() });
            return false;
        }
        return true;
    }

    private NSZC047001PMsg setPMsgContractMode(NSAL0330CMsg cMsg, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        NSZC047001PMsg pMsg = new NSZC047001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "01");
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, NSAL0330Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, cMsg.contrCloDay_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, cMsg.baseBllgTmgCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, cMsg.contrBllgDay_H1);
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(dsContrDtlTMsg);
        if(dsContrDtlTMsg == null){
            return pMsg;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, dsContrDtlTMsg.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt,dsContrDtlTMsg.contrEffThruDt);

        pMsg.xxBaseLineList.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffSqNum_BL, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).effFromDt_BL, dsContrDtlTMsg.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).effThruDt_BL, dsContrDtlTMsg.contrEffThruDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, dsContrDtlTMsg.baseBllgCycleCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, dsContrDtlTMsg.basePrcDealAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, dsContrDtlTMsg.basePrcTermDealAmtRate);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffStsCd_BL, dsContrDtlTMsg.dsContrDtlStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).qltyAsrnHldFlg_BL, dsContrDtlTMsg.qltyAsrnHldFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).mtrHldFlg_BL, dsContrDtlTMsg.mtrHldFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).contrHldFlg_BL, dsContrDtlTMsg.contrHldFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).bllgHldFlg_BL, dsContrDtlTMsg.bllgHldFlg);

        return pMsg;
    }
    // END 2016/11/17 T.Tomita [QC#15942, ADD]
}

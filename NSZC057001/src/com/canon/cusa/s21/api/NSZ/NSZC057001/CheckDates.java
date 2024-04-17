/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2015/12/07   Hitachi         K.Yamada        Update          CSA QC#1417
 * 2016/03/17   Hitachi         T.Tomita        Update          CSA QC#3058
 * 2016/06/07   Hitachi         K.Yamada        Update          QC#3051
 * 2017/06/22   Hitachi         K.Kitachi       Update          QC#19340
 * 2018/02/02   Hitachi         U.Kim           Update          QC#23687
 * 2020/03/24   Hitachi         A.Kohinata      Update          QC#54318
 * 2023/06/23   Hitachi         T.Usui          Update          QC#61408
 * </pre>
 */
public class CheckDates {

    /**
     * checkEffectivityDate
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkEffectivityDate(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (!hasValue(dsContrTMsg.contrVrsnEffThruDt)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0766E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            if (!hasValue(dsContrDtlTMsg.contrEffThruDt)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0767E, new String[] {getSerNum(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk
                        .getValue());
            } else if ((dsContrDtlTMsg.contrEffFromDt.getValue().compareTo(dsContrTMsg.contrVrsnEffFromDt.getValue()) < 0) || (dsContrDtlTMsg.contrEffFromDt.getValue().compareTo(dsContrTMsg.contrVrsnEffThruDt.getValue()) > 0)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0768E, new String[] {getSerNum(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk
                        .getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * validateFleetNewMachineStartDate
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray validateFleetNewMachineStartDate(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        String lastBilledDate = null;
        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.FLEET)) {
                lastBilledDate = dsContrDtlTMsgArray.no(i).mtrInvUpToDt.getValue();
                break;
            }
        }

        if (!hasValue(lastBilledDate)) {
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            return rtrnTMsgArray;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            if (!dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.FLEET) && !dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.ACCESSORIES)
                    && getDsContrCtrlStsCd(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()).equals(DS_CONTR_CTRL_STS.ENTERED) && dsContrDtlTMsg.contrEffFromDt.getValue().compareTo(lastBilledDate) > 0) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0769E, new String[] {getSerNum(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk
                        .getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkBaseAndUsagePeriodEndDate
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkBaseAndUsagePeriodEndDate(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // add start 2015/12/07 CSA Defect#1417
            if (isUnderFleet(dsContrTMsg, dsContrDtlTMsg)) {
                continue;
            }
            // add end 2015/12/07 CSA Defect#1417

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // mod start 2016/03/17 CSA Defect#3058
            if (ZYPConstant.FLG_OFF_N.equals(dsContrTMsg.invSeptBaseUsgFlg.getValue()) && !NSXC001001ContrValidation.checkBaseAndUsgCloDay(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue(), dsContrDtlTMsg.contrCloDay.getValue(), dsContrDtlTMsg.mtrCloDay.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0658E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            // mod end 2016/03/17 CSA Defect#3058
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkBaseAndUsageInvoiceDate
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkBaseAndUsageInvoiceDate(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // add start 2015/12/07 CSA Defect#1417
            if (isUnderFleet(dsContrTMsg, dsContrDtlTMsg)) {
                continue;
            }
            // add end 2015/12/07 CSA Defect#1417

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // mod start 2016/03/17 CSA Defect#3058
            if (ZYPConstant.FLG_OFF_N.equals(dsContrTMsg.invSeptBaseUsgFlg.getValue()) && !NSXC001001ContrValidation.checkBaseAndUsgBllgDay(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue(), dsContrDtlTMsg.contrBllgDay.getValue(), dsContrDtlTMsg.mtrBllgDay.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0659E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            // mod end 2016/03/17 CSA Defect#3058
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    // START 2016/06/07 K.Yamada [QC#3051, ADD]
    /**
     * checkCfsStartDateDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCfsStartDateDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        // START 2017/06/22 K.Kitachi [QC#19340, MOD]
        // START 2018/02/02 U.Kim [QC#23687,MOD]
        // if (!isLeaseCompany(dsContrTMsg)) {
        if (!isLeaseCompany(mainClass, dsContrTMsg)) {
        // END 2018/02/02 U.Kim [QC#23687,MOD]
        // END 2017/06/22 K.Kitachi [QC#19340, MOD]
            return rtrnTMsgArray;
        }

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            String dsContrDtlStsCd = (String) machAndContrInfo.get("DS_CONTR_DTL_STS_CD");
            if (mainUnitLineFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }
            if (!dsContrDtlStsCd.equals(DS_CONTR_DTL_STS.ACTIVE)) {
                continue;
            }

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            if (!existCfsLeaseContrForCheckDates(mainClass, param.glblCmpyCd.getValue(), serNum, param.slsDt.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0983E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // END 2016/06/07 K.Yamada [QC#3051, ADD]

    // add start 2020/03/24 QC#54318
    /**
     * checkUplftPcyDt
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkUplftPcyDt(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        // START 2023/10/18 S.Moriai [QC#61952, DEL]
        //setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        //setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        //if (existNotSetUplftPcyDtForContr(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue())) {
        //    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1376W), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        //}
        //EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        //rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        // END   2023/10/18 S.Moriai [QC#61952, DEL]
        
        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2023/10/18 S.Moriai [QC#61952, DEL]
            //if (existNotSetUplftPcyDtForContrDtl(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue())) {
            //    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1376W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            //}
            // END   2023/10/18 S.Moriai [QC#61952, DEL]
            // START 2023/10/18 S.Moriai [QC#61952, ADD]
            if (existNotSetUplftPcyDtForContrDtlLvThree(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) ) {
                if (existNotSetUplftPcyDtForContrDtlLvTwo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                    if (existNotSetUplftPcyDtForContr(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue())) {
                        setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1376W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    }
                }
            }
            // END   2023/10/18 S.Moriai [QC#61952, ADD]
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // add end 2020/03/24 QC#54318
    // QC#61408 2023/06/23 Add Start
    /**
     * checkUplftPcyDt
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkUplftPcyDtAndScheduleFromDate(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        BigDecimal dsContrPk = dsContrTMsg.dsContrPk.getValue();
        String slsDt = param.slsDt.getValue();

        List<Map<String, Object>> annualEscalationList = getAnnualEscalationList(mainClass, glblCmpyCd, dsContrPk, slsDt);
        if (annualEscalationList.size() == 0) {
            DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
            for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
                DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
                rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
                setValue(rtrnTMsg.dsContrPk, dsContrPk);
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk.getValue());
                setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
            return rtrnTMsgArray;
        }
        for (Map<String, Object> annualEscalationMap : annualEscalationList) {

            BigDecimal dsContrDtlPk = (BigDecimal) annualEscalationMap.get("DS_CONTR_DTL_PK");
            String baseFlg = (String) annualEscalationMap.get("BASE_FLG");
            String usageFlg = (String) annualEscalationMap.get("USAGE_FLG");
            String pcyDt = (String) annualEscalationMap.get("EFF_FROM_DT");
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            boolean isError = false;
            if (ZYPConstant.FLG_OFF_N.equals(baseFlg) && ZYPConstant.FLG_OFF_N.equals(usageFlg)) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(baseFlg)) {
                if (existsUnmatchPcyDateAndBllgschd(mainClass, glblCmpyCd, dsContrDtlPk, pcyDt, "existsMatchPcyDateAndBllgschdForBase")) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1395E), DS_CONTR_DTL_PK, dsContrDtlPk);
                    EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                    rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
                    isError = true;
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(usageFlg)) {
                if (existsUnmatchPcyDateAndBllgschd(mainClass, glblCmpyCd, dsContrDtlPk, pcyDt, "existsMatchPcyDateAndBllgschdForUsage")) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1397E), DS_CONTR_DTL_PK, dsContrDtlPk);
                    EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                    rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
                    isError = true;
                }
            }
            if (isError == false) {
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
        }
        return rtrnTMsgArray;
    }
    // QC#61408 2023/06/23 Add End
}

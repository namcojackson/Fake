/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetNotArvMachCntForFlt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * 2015/12/02   Hitachi         A.Kohinata      Update          QC1275
 * 2015/12/10   Hitachi         A.Kohinata      Update          QC1822
 * 2016/05/16   Hitachi         A.Kohinata      Update          QC1088
 * 2016/09/28   Hitachi         T.Kanasaka      Update          QC#9905
 * 2017/07/26   Hitachi         K.Kasai         Update          QC#18882
 * 2017/08/09   Hitachi         K.Kitachi       Update          QC#19122
 * 2017/09/14   CITS            M.Naito         Update          QC#18534
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22611
 * 2018/07/09   Hitachi         K.Kitachi       Update          QC#26834
 * </pre>
 */
public class CheckFleet {

    /**
     * checkFleetUsageBillingReadDate
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkFleetUsageBillingReadDate(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.FLEET)) {
            return rtrnTMsgArray;
        }

        // get number constant
        BigDecimal fltUsgReadBefDays = ZYPCodeDataUtil.getNumConstValue(FLEET_USAGE_READ_BEFORE_DAYS, param.glblCmpyCd.getValue());
        BigDecimal fltUsgReadAftDays = ZYPCodeDataUtil.getNumConstValue(FLEET_USAGE_READ_AFTER_DAYS, param.glblCmpyCd.getValue());
        int intFltUsgReadBefDays = 0;
        int intFltUsgReadAftDays = 0;
        if (hasValue(fltUsgReadBefDays)) {
            intFltUsgReadBefDays = fltUsgReadBefDays.intValue();
        }
        if (hasValue(fltUsgReadAftDays)) {
            intFltUsgReadAftDays = fltUsgReadAftDays.intValue();
        }

        // get start date from fleet line
        String startDate = null;
        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.FLEET)) {
                startDate = dsContrDtlTMsg.contrEffFromDt.getValue();
                break;
            }
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // START 2015/12/10 [QC1822, ADD]
            if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) && !DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END 2015/12/10 [QC1822, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            BigDecimal svcMachMstrPk = (BigDecimal) machAndContrInfo.get("SVC_MACH_MSTR_PK");
            String serNum = (String) machAndContrInfo.get("SER_NUM");
            String mtrReqMdlFlg = (String) machAndContrInfo.get("MTR_REQ_MDL_FLG");
            String mainUnitLineFlg = (String) machAndContrInfo.get("MAIN_UNIT_LINE_FLG");
            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            String contrEffFromDt = (String) machAndContrInfo.get("CONTR_EFF_FROM_DT");
            if ((mainUnitLineFlg.equals(ZYPConstant.FLG_ON_Y) && usgChrgFlg.equals(ZYPConstant.FLG_OFF_N)) || mtrReqMdlFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }

            // get start date
            String paramMtrReadDate = null;
            if (!hasValue(startDate) && !hasValue(contrEffFromDt)) {
                continue; 
            } else if (hasValue(startDate) && !hasValue(contrEffFromDt)) {
                paramMtrReadDate = startDate;
            } else if (!hasValue(startDate) && hasValue(contrEffFromDt)) {
                paramMtrReadDate = contrEffFromDt;
            } else if (startDate.compareTo(contrEffFromDt) > 0) {
                paramMtrReadDate = startDate;
            } else {
                paramMtrReadDate = contrEffFromDt;
            }

            // exist service physical meter read
            if (!existSvcPhysMtrRead(param.glblCmpyCd.getValue(), svcMachMstrPk, ZYPDateUtil.addDays(paramMtrReadDate, -intFltUsgReadBefDays), ZYPDateUtil.addDays(paramMtrReadDate, intFltUsgReadAftDays))) {
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                // START 2016/05/16 [QC1088, MOD]
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0773E, new String[] {String.valueOf(intFltUsgReadBefDays), String.valueOf(intFltUsgReadAftDays), serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                // END 2016/05/16 [QC1088, MOD]
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    // START 2016/09/28 T.Kanasaka [QC#9905, ADD]
    /**
     * checkFleetShipTo
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkFleetShipTo(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.FLEET)) {
                setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

                if (!hasValue(dsContrDtlTMsg.shipToCustCd)) {
                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1060E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                }

                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
                break;
            }
        }
        return rtrnTMsgArray;
    }
    // END 2016/09/28 T.Kanasaka [QC#9905, ADD]

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    /**
     * checkAllMachArrived
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkAllMachArrived(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        // check Not Arrived Machine in Fleet Contract
        BigDecimal notArvMachCnt = NSXC001001GetNotArvMachCntForFlt.getNotArvMachCnt(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        
        if (BigDecimal.ZERO.compareTo(notArvMachCnt) < 0) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSAM0687W), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            // START 2018/03/15 K.Kojima [QC#22611,DEL]
            // setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.WARNING);
            // END 2018/03/15 K.Kojima [QC#22611,DEL]
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }
    //END 2017/07/26 K.Kasai [QC#18882,ADD]

    // START 2017/08/09 K.Kitachi [QC#19122, ADD]
    /**
     * checkFleetMachineMeter
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkFleetMachineMeter(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.FLEET)) {
            return rtrnTMsgArray;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }

            String usgChrgFlg = (String) machAndContrInfo.get("USG_CHRG_FLG");
            if (usgChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                continue;
            }

            String serNum = (String) machAndContrInfo.get("SER_NUM");
            if (!existDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()) || !existContrPhysBllgMtrReln(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1292E, new String[] {serNum }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // END 2017/08/09 K.Kitachi [QC#19122, ADD]

    //START 2017/09/14 M.Naito [QC#18534,ADD]
    /**
     * checkCsaWarranty
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkCsaWarranty(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

        if (!NSXC001001ContrValidation.checkCsaWarranty(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), dsContrTMsg.svcPgmMdseCd.getValue())) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSAM0698E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }
    //END 2017/09/14 M.Naito [QC#18534,ADD]

    // START 2018/07/09 K.Kitachi [QC#26834, ADD]
    /**
     * checkFleetLineMeter
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkFleetLineMeter(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.FLEET)) {
            return rtrnTMsgArray;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();
            if (!dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.FLEET)) {
                continue;
            }

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if (existLineMtrNotExstMachMtr(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1339E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // END 2018/07/09 K.Kitachi [QC#26834, ADD]
}

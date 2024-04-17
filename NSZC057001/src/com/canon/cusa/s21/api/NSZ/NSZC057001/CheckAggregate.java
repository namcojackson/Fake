/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2017/08/09   Hitachi         K.Kitachi       Update          QC#19122
 * 2017/09/14   CITS            M.Naito         Update          QC#18534
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/07/09   Hitachi         K.Kitachi       Update          QC#26834
 * 2018/10/29   Hitachi         K.Kitachi       Update          QC#28889
 * 2018/11/13   Hitachi         K.Fujimoto      Update          QC#28627
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2023/07/27   CITS            R.Kurahashi     Update          QC#61710
 * 2023/11/08   CITS            R.Kurahashi     Update          QC#61568
 * </pre>
 */
public class CheckAggregate {

    /**
     * checkAllocateAllAcross
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkAllocateAllAcross(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (!NSXC001001ContrValidation.checkAggAllocation(dsContrTMsg.dsContrCatgCd.getValue(), dsContrTMsg.prcAllocByMachQtyFlg.getValue())) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0666E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    // START 2017/08/09 K.Kitachi [QC#19122, ADD]
    /**
     * checkAggregateMachineMeter
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkAggregateMachineMeter(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
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

    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
    /**
     * checkAggregateBillingMeter
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkAggregateBillingMeter(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
            return rtrnTMsgArray;
        }

        List<Map<String, Object>> bllgMtrInfoList = getBllgMtrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (bllgMtrInfoList.size() == 0) {
            return rtrnTMsgArray;
        }

        boolean isErr = false;
        int arrayIdx = 0;
        int count;
        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;
        for (Map<String, Object> bllgMtrInfo : bllgMtrInfoList) {
            count = 0;
            if (((BigDecimal) bllgMtrInfo.get("COUNT_BLLG_FREE_COPY_CNT")).compareTo(BigDecimal.ZERO) > 0) {
                count++;
            }
            if (((BigDecimal) bllgMtrInfo.get("COUNT_BLLG_MIN_CNT")).compareTo(BigDecimal.ZERO) > 0) {
                count++;
            }
            if (((BigDecimal) bllgMtrInfo.get("COUNT_BLLG_MIN_AMT_RATE")).compareTo(BigDecimal.ZERO) > 0) {
                count++;
            }
            if (((BigDecimal) bllgMtrInfo.get("COUNT_BLLG_ROLL_OVER_RATIO")).compareTo(BigDecimal.ZERO) > 0) {
                count++;
            }
            if (count > 1) {
                isErr = true;
                rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
                setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1332E, new String[] {(String) bllgMtrInfo.get("MTR_LB_DESC_TXT") }), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
        }

        if (!isErr) {
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

    // START 2018/07/09 K.Kitachi [QC#26834, ADD]
    /**
     * checkAggregateLineMeter
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkAggregateLineMeter(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
            return rtrnTMsgArray;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();
            if (!dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.AGGREGATE)) {
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

    // START 2018/10/29 K.Kitachi [QC#28889, ADD]
    /**
     * checkAggregateBillToDiff
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkAggregateBillToDiff(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
            return rtrnTMsgArray;
        }

        // START 2022/03/22 [QC#59683, MOD]
//        if (dsContrTMsg.invSeptBaseUsgFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
        if (!DS_INV_TGTR_TP.BILL_ALL_BASE_AND_USAGE_TOGETHER.equals(dsContrTMsg.dsInvTgtrTpCd.getValue()) && !DS_INV_TGTR_TP.BILL_ALL_BASE_TOGETHER_AND_ALL_USAGE_TOGETHER.equals(dsContrTMsg.dsInvTgtrTpCd.getValue())) {
            return rtrnTMsgArray;
        }
        // END   2022/03/22 [QC#59683, MOD]

        rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

        String billToCustCd = dsContrTMsg.altPayerCustCd.getValue();

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (hasValue(dsContrDtlTMsg.baseBillToCustCd) && !billToCustCd.equals(dsContrDtlTMsg.baseBillToCustCd.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1354E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

                return rtrnTMsgArray;
            }
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            // START 2018/11/13 K.Fujimoto [QC#28627, MOD]
            if(dsContrBllgMtrTMsgArray != null) {
                  for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                  DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                  if (hasValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd) && !billToCustCd.equals(dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                         setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1354E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
                         EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                         rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
                         return rtrnTMsgArray;
                      }
                 }
            }
            // END   2018/11/13 K.Fujimoto [QC#28627, MOD]
        }

        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }
    // END 2018/10/29 K.Kitachi [QC#28889, ADD]
    
    // START 2023/07/27 R.Kurahashi [QC#61710, ADD]
    /**
     * checkContrEffThruDtMatch
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkContrEffThruDtMatch(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return rtrnTMsgArray;
        }

        String doNotRenewCd = getDoNotRenewCd(mainClass, dsContrTMsg);
        if (!hasValue(doNotRenewCd)) {
            return rtrnTMsgArray;
        }

        String aggThruDt = null;
        DS_CONTR_DTLTMsg aggDsContrDtlTMsg = null;
        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                aggDsContrDtlTMsg = dsContrDtlTMsg;
                aggThruDt = dsContrDtlTMsg.contrEffThruDt.getValue();
                break;
            }
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_DTL_STS.EXPIRED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue()) || DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())
                    || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue()) || DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                continue;
            }

            if (hasValue(dsContrDtlTMsg.contrCloDt)) {
                continue;
            }

            boolean endDateCheck = isEndDateCheck(mainClass, dsContrTMsg, dsContrDtlTMsg, aggDsContrDtlTMsg, doNotRenewCd);

            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if (endDateCheck && hasValue(dsContrDtlTMsg.contrEffThruDt) && !aggThruDt.equals(dsContrDtlTMsg.contrEffThruDt.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1396E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // END 2023/07/27 R.Kurahashi [QC#61710, ADD]
    
    // START 2023/11/08 R.Kurahashi [QC#61568, ADD]
    /**
     * checkBaseBillingCycleMatch
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkBaseBillingCycleMatch(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return rtrnTMsgArray;
        }

        String aggBaseBllgCycleCd = null;
        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                aggBaseBllgCycleCd = dsContrDtlTMsg.baseBllgCycleCd.getValue();
                break;
            }
        }

        String bllgCycleCds = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_BLLG_CYCLE_CD, dsContrTMsg.glblCmpyCd.getValue());
        if (hasValue(bllgCycleCds)) {
            ArrayList<String> bllgCycleCdList = new ArrayList<String>(Arrays.asList(bllgCycleCds.split(",")));
            if (!bllgCycleCdList.contains(aggBaseBllgCycleCd)) {
                return rtrnTMsgArray;
            }
        } else {
            return rtrnTMsgArray;
        }

        ArrayList<BigDecimal> matchErrList = getBaseBillingCycleMatchErrList(mainClass, dsContrTMsg);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (hasValue(dsContrDtlTMsg.contrCloDt)) {
                continue;
            }

            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if (isBaseBillingCycleError(matchErrList, dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1408E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        return rtrnTMsgArray;
    }

    /**
     * checkUsageBillingCycleMatch
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkUsageBillingCycleMatch(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return rtrnTMsgArray;
        }

        String bllgCycleCds = ZYPCodeDataUtil.getVarCharConstValue(VAR_CONST_BLLG_CYCLE_CD, dsContrTMsg.glblCmpyCd.getValue());
        ArrayList<String> bllgCycleCdList = null;
        if (hasValue(bllgCycleCds)) {
            bllgCycleCdList = new ArrayList<String>(Arrays.asList(bllgCycleCds.split(",")));
        } else {
            return rtrnTMsgArray;
        }

        HashMap<BigDecimal, HashMap<String, String>> matchErrList = getUsageBillingCycleMatchErrList(mainClass, dsContrTMsg);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (hasValue(dsContrDtlTMsg.contrCloDt)) {
                continue;
            }

            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            if (matchErrList.get(dsContrDtlTMsg.dsContrDtlPk.getValue()) != null) {
                for (Map.Entry<String, String> entry : matchErrList.get(dsContrDtlTMsg.dsContrDtlPk.getValue()).entrySet()) {
                    setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                    setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

                    if (bllgCycleCdList.contains(entry.getValue())) {
                        setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1409E, new String[] {entry.getKey() }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    }
                    EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                    rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
                }
            } else {
                setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

                EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
                rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
            }
        }
        return rtrnTMsgArray;
    }
    // END 2023/11/08 R.Kurahashi [QC#61568, ADD]
}

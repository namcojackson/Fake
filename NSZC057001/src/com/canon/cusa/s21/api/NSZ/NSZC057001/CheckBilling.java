/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
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
 * 2016/01/18   Hitachi         K.Kishimoto     Update          CSA QC#3094
 * 2020/03/09   CITS            T.Wada          Update          CSA QC#55830
 * </pre>
 */
public class CheckBilling {

    /**
     * checkExclusiveBillingCondition
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkExclusiveBillingCondition(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

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

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                continue;
            }

            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                if (!NSXC001001ContrValidation.checkBllgMtrParam(dsContrBllgMtrTMsg.bllgMinCnt.getValue(), dsContrBllgMtrTMsg.bllgMinAmtRate.getValue(), dsContrBllgMtrTMsg.bllgRollOverRatio.getValue(), dsContrBllgMtrTMsg.bllgFreeCopyCnt
                        .getValue())) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0681E), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    break;
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkMinimumVolume
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMinimumVolume(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

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

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                continue;
            }

            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                CONTR_XS_COPYTMsgArray contrXsCopyTMsgArray = mainClass.contrXsCopyArrayCache.get(KEY + i + j);

                if (contrXsCopyTMsgArray == null || contrXsCopyTMsgArray.getValidCount() > 0) {
                    contrXsCopyTMsgArray = getContrXsCopy(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    if (contrXsCopyTMsgArray != null && contrXsCopyTMsgArray.getValidCount() > 0) {
                        mainClass.contrXsCopyArrayCache.put(KEY + i + j, contrXsCopyTMsgArray);
                    }
                }

                if (contrXsCopyTMsgArray == null || contrXsCopyTMsgArray.getValidCount() == 0) {
                    continue;
                }

                // mod Start 2016/01/18 CSA Defect#3094
                if (contrXsCopyTMsgArray.getValidCount() > 0
                        && !NSXC001001ContrValidation.checkXsMinVol(dsContrBllgMtrTMsg.bllgMinCnt.getValue(), contrXsCopyTMsgArray.getValidCount(), contrXsCopyTMsgArray.no(0).xsMtrCopyQty.getValue())) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0682E), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    break;
                }
                // mod End 2016/01/18 CSA Defect#3094
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkMinimumAmountRollOver
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMinimumAmountRollOver(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

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

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                continue;
            }

            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                CONTR_XS_COPYTMsgArray contrXsCopyTMsgArray = mainClass.contrXsCopyArrayCache.get(KEY + i + j);

                if (contrXsCopyTMsgArray == null || contrXsCopyTMsgArray.getValidCount() > 0) {
                    contrXsCopyTMsgArray = getContrXsCopy(param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    if (contrXsCopyTMsgArray != null && contrXsCopyTMsgArray.getValidCount() > 0) {
                        mainClass.contrXsCopyArrayCache.put(KEY + i + j, contrXsCopyTMsgArray);
                    }
                }

                if (contrXsCopyTMsgArray == null || contrXsCopyTMsgArray.getValidCount() == 0) {
                    continue;
                }

                // mod Start 2016/01/18 CSA Defect#3094
                if (contrXsCopyTMsgArray.getValidCount() > 0
                        && !NSXC001001ContrValidation.checkXsMinAmtRollOver(dsContrBllgMtrTMsg.bllgMinAmtRate.getValue(), dsContrBllgMtrTMsg.bllgRollOverRatio.getValue(), contrXsCopyTMsgArray.getValidCount())) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0683E), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    break;
                }
                // mod End 2016/01/18 CSA Defect#3094
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkMinimumFreeCopy
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMinimumFreeCopy(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

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

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = mainClass.dsContrBllgMtrArrayCache.get(KEY + i);

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                if (dsContrBllgMtrTMsgArray != null && dsContrBllgMtrTMsgArray.getValidCount() > 0) {
                    mainClass.dsContrBllgMtrArrayCache.put(KEY + i, dsContrBllgMtrTMsgArray);
                }
            }

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                continue;
            }

            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                if (!NSXC001001ContrValidation.checkFreeCopy(dsContrBllgMtrTMsg.bllgRollOverRatio.getValue(), dsContrBllgMtrTMsg.bllgFreeCopyCnt.getValue())) {
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0784E, new String[] {"Free Copies", "Rollover% = 100" }), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    break;
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    
    // QC#55830 Add Start
    /**
     * checkSkipSchedule
     * @param mainClass
     * @param param
     * @return
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkSkipSchedule(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if(existSkipSchedule(mainClass,param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1374E),  DS_CONTR_DTL_PK,  dsContrDtlTMsg.dsContrDtlPk.getValue());
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // QC#55830 Add End

}

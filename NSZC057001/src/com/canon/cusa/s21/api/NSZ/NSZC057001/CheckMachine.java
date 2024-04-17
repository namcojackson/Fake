/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/14   CITS            M.Naito         Create          QC#18534
 * 2018/05/11   CITS            T.Wada          Create          QC#24989
 * 2018/05/15   Hitachi         K.Kim           Update          QC#25704
 * 2018/06/20   Hitachi         K.Kim           Update          QC#26704
 * 2018/07/03   Hitachi         K.Kishimoto     Update          QC#15410
 * 2018/07/05   Hitachi         K.Kishimoto     Update          QC#27142
 * 2023/11/13   Hitachi         S.Moriai        Update          QC#61756
 * </pre>
 */
public class CheckMachine {

    /**
     * checkMachineInstalled
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMachineInstalled(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            //START 2018/05/15 K.Kim [QC#25704,ADD]
            if (DS_CONTR_DTL_STS.EXPIRED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue()) || DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                continue;
            }
            //START 2018/05/15 K.Kim [QC#25704,ADD]
            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.svcMachMstrPk) && !NSXC001001ContrValidation.checkMachineInstalled(param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1298E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // QC#24989 Add Start
    /**
     * checkInstalledMachineExistence
     * @param mainClass
     * @param param
     * @return
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkInstalledMachineExistence(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        if (!ONBATCH_TYPE.ONLINE.equals(mainClass.onBatchType)) {
            return rtrnTMsgArray;
        }

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        //START 2018/06/20 K.Kim [QC#26704,ADD]
        String dsContrStsCd = dsContrTMsg.dsContrStsCd.getValue();
        if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_STS.TERMINATED.equals(dsContrStsCd)) {
            return rtrnTMsgArray;
        }
        //END 2018/06/20 K.Kim [QC#26704,ADD]

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

        BigDecimal installedMachineCount = NSZC057001CommonLogic.countInstalledMachines(mainClass, dsContrTMsg.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
        if (installedMachineCount != null && installedMachineCount.compareTo(BigDecimal.ZERO) == 0) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1331E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }
    // QC#24989 Add End
    // QC#15410 Add Start
    /**
     * checkInstalledMachineExistence
     * @param mainClass
     * @param param
     * @return
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkDuplicateMachine(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;
        if (!ONBATCH_TYPE.ONLINE.equals(mainClass.onBatchType)) {
            return rtrnTMsgArray;
        }

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        //START 2018/07/05  [QC#27142,MOD]
        String dsContrStsCd = dsContrTMsg.dsContrStsCd.getValue();
        if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_STS.TERMINATED.equals(dsContrStsCd)) {
            return rtrnTMsgArray;
        }
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            String dsContrDtlStsCd = dsContrDtlTMsg.dsContrDtlStsCd.getValue();
            Boolean exexChk = true;
            if (DS_CONTR_DTL_STS.CANCELLED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlStsCd)) {
                exexChk = false;
            }
            if (exexChk && !NSXC001001ContrValidation.checkDuplicateContr(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrDtlTMsg.contrEffFromDt.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1340W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        //END   2018/07/05  [QC#27142,END]

        return rtrnTMsgArray;
    }
    /**
     * checkInstalledMachineExistence
     * @param mainClass
     * @param param
     * @return
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkDuplicateBllgMtr(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;
        if (!ONBATCH_TYPE.ONLINE.equals(mainClass.onBatchType)) {
            return rtrnTMsgArray;
        }

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        //START 2018/07/05  [QC#27142,MOD]
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            String dsContrDtlStsCd = dsContrDtlTMsg.dsContrDtlStsCd.getValue();
            Boolean exexChk = true;
            if (DS_CONTR_DTL_STS.CANCELLED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlStsCd)) {
                exexChk = false;
            }
            if (exexChk && NSZC057001CommonLogic.existSamePhysMtrByOthrContr(mainClass, dsContrDtlTMsg)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1341E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }
        //END   2018/07/05  [QC#27142,END]

        return rtrnTMsgArray;
    }
    // QC#15410 Add End
    
    //START 2023/11/13 S.Moriai [QC#61756, ADD]
    /**
     * checkMachineInstalledEmail
     * @param mainClass
     * @param param
     * @return
     */
     public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMachineInstalledEmail(NSZC057001 mainClass, NSZC057001PMsg param) {
         DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
         rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
         DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
         int arrayIdx = 0;
         
         for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
             DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
             DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
             
             setValue(rtrnTMsg.dsContrPk, dsContrDtlTMsg.dsContrPk);
             setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
             setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
             if (dsContrDtlTMsg.mtrReadMethCd.getValue().equals(MTR_READ_METH.EMAIL))  {
                 if (!getDsContactPersonEmail(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()))  {
                     setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1410E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                 }
             }
             EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
             rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
         }
         return rtrnTMsgArray;
     }
     //END 2023/11/13 S.Moriai [QC#61756, ADD]
}

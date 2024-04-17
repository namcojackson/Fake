/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.Arrays;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#9900
 * 2018/06/12   Hitachi         K.Kim           Update          QC#25425
 * 2018/08/03   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * </pre>
 */
public class CheckCustomer {

    /**
     * checkDoNotBillCfsCmpy
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkDoNotBillCfsCmpy(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);

        if (!hasValue(dsContrTMsg.leaseCmpyCd)) {
            return rtrnTMsgArray;
        }

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

        String cfsBillToCustCd = ZYPCodeDataUtil.getVarCharConstValue(CFS_BILL_TO_CUST_CD, param.glblCmpyCd.getValue());
        String[] cfsBillToCustCdList = cfsBillToCustCd.split(COMMA);
        if (!Arrays.asList(cfsBillToCustCdList).contains(cfsBillToCustCd)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0765E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        return rtrnTMsgArray;
    }

    /**
     * checkDoNotBillCfsOmAcct
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkDoNotBillCfsOmAcct(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (hasValue(dsContrTMsg.altPayerCustCd)
                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsAcctNum.getValue(), dsContrTMsg.altPayerCustCd.getValue(), mainClass.onBatchType)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0698E, new String[] {"Customer#", "Bill To" }), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        } else if (hasValue(dsContrTMsg.leaseCmpyCd)
                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsAcctNum.getValue(), dsContrTMsg.leaseCmpyCd.getValue(), mainClass.onBatchType)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0698E, new String[] {"Customer#", "Bill To" }), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

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

            if (hasValue(dsContrDtlTMsg.baseBillToCustCd)
                    && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsAcctNum.getValue(), dsContrDtlTMsg.baseBillToCustCd.getValue(), mainClass.onBatchType)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0698E, new String[] {"Customer#", "Bill To" }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else if (hasValue(dsContrDtlTMsg.usgBillToCustCd)
                    && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsAcctNum.getValue(), dsContrDtlTMsg.usgBillToCustCd.getValue(), mainClass.onBatchType)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0698E, new String[] {"Customer#", "Bill To" }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                    if (hasValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd)
                            && !NSXC001001ContrValidation
                                    .checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsAcctNum.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue(), mainClass.onBatchType)) {
                        setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0698E, new String[] {"Customer#", "Bill To" }), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    }
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    // START 2016/07/04 T.Tomita [QC#9900, ADD]
    /**
     * checkMachMstrAcctReln
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkMachMstrAcctReln(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // get machine & contract info
            Map<String, Object> machAndContrInfo = getMachAndContrInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (machAndContrInfo == null) {
                continue;
            }
            String curLocAcctNum = (String) machAndContrInfo.get("CUR_LOC_ACCT_NUM");
            if (!hasValue(curLocAcctNum)) {
                continue;
            }

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
            // START 2018/06/12 K.Kim [QC#25425, MOD]
            // if (!checkAcctReln(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsAcctNum.getValue(), curLocAcctNum, mainClass.onBatchType)) {
            if (!checkAcctReln(param.glblCmpyCd.getValue(), param.slsDt.getValue(), curLocAcctNum, dsContrTMsg.dsAcctNum.getValue(), mainClass.onBatchType)) {
            // END 2018/06/12 K.Kim [QC#25425, MOD]
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1024E, new String[] {"Customer# of Contract", "Current Location Account# of IB" }), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    private static boolean checkAcctReln(String glblCmpyCd, String slsDt, String dsAcctNum_I1, String dsAcctNum_I2, ONBATCH_TYPE onBatchType) {

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.dsAcctNum_I1, dsAcctNum_I1);
        setValue(apiMsg.dsAcctNum_I2, dsAcctNum_I2);
        setValue(apiMsg.slsDt, slsDt);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnRecipFlg.getValue())) {
            return true;
        }
        return false;
    }
    // END 2016/07/04 T.Tomita [QC#9900, ADD]

    // START 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]
    /**
     * checkSpecialInstruction
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkSpecialInstruction(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (hasValue(dsContrTMsg.altPayerCustCd)
                && existCustSpclInstructionCheck(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.altPayerCustCd.getValue(), dsContrTMsg.svcLineBizCd.getValue())){
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1343W), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }

        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

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

            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (hasValue(dsContrDtlTMsg.baseBillToCustCd)
                    && existCustSpclInstructionCheck(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.baseBillToCustCd.getValue(), dsContrTMsg.svcLineBizCd.getValue())){
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1343W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else if (hasValue(dsContrDtlTMsg.usgBillToCustCd)
                    && existCustSpclInstructionCheck(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.usgBillToCustCd.getValue(), dsContrTMsg.svcLineBizCd.getValue())){
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1343W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                    if (hasValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd)
                            && existCustSpclInstructionCheck(mainClass, param.glblCmpyCd.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue(), dsContrTMsg.svcLineBizCd.getValue())){
                        setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1343W), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    }
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // END 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]
    // 2022/11/25 QC#60398 Add Start
    /**
     * checkHardCopyRequried
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkHardCopyRequried(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (hasValue(dsContrTMsg.altPayerCustCd) && existCustSpclInstructionCheck(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.altPayerCustCd.getValue(), dsContrTMsg.svcLineBizCd.getValue())) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1343W), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        }

        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

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

            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (hasValue(dsContrDtlTMsg.baseBillToCustCd)
                    && existHardCopyReqCheck(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrDtlTMsg.baseBillToCustCd.getValue())) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1382W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            } else if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);

                    if (hasValue(dsContrBllgMtrTMsg.bllgMtrBillToCustCd)
                            && existHardCopyReqCheck(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                        setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1382W), DS_CONTR_BLLG_MTR_PK, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    }
                }
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // 2022/11/25 QC#60398 Add End
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2015/12/11   Hitachi         A.Kohinata      Update          QC1886
 * 2016/04/28   Hitachi         M.Gotou         Update          QC#706
 * 2016/11/25   Hitachi         K.Kojima        Update          QC#16129
 * 2017/09/22   Hitachi         K.Kitachi       Update          QC#21063
 * </pre>
 */
public class CheckFm {

    /**
     * checkFmContract
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkFmContract(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrClsCd.getValue().equals(DS_CONTR_CLS.BSD)) {
            return rtrnTMsgArray;
        }

        // revenue distribution check
        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
        if (existDsContrPrcAlloc(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue())) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0791E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        // START 2016/11/25 K.Kojima [QC#16129,ADD]
        } else if (!existFmContrSlsRepReln(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.tocCd.getValue(), dsContrTMsg.dsAcctNum.getValue(), param.slsDt.getValue())) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0775E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
        // END 2016/11/25 K.Kojima [QC#16129,ADD]
        }
        EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // START 2015/12/11 [QC1886, ADD]
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END 2015/12/11 [QC1886, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // billing timing check
            if (dsContrDtlTMsg.baseBllgTmgCd.getValue().equals(BLLG_TMG_TP.ADVANCE) && dsContrDtlTMsg.mtrBllgTmgCd.getValue().equals(BLLG_TMG_TP.ADVANCE)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0774E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());

            // add start 2016/04/28 CSA Defect#706
            // transaction type check
            } else if (!existDsContrCls(param.glblCmpyCd.getValue(), DS_CONTR_CLS.BSD)) {
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0969E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            // add end 2016/04/28 CSA Defect#706

            // START 2016/11/25 K.Kojima [QC#16129,DEL]
            // // sales rep ship to relation check
            // } else if (!existFmContrSlsRepReln(mainClass, param.glblCmpyCd.getValue(), dsContrTMsg.tocCd.getValue(), getShipToCustCd(param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue()), param.slsDt.getValue())) {
            //     setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0775E), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            // sales rep account num relation check
            // END 2016/11/25 K.Kojima [QC#16129,DEL]
            }

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkFmPriceReference
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkFmPriceReference(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        if (!dsContrTMsg.dsContrClsCd.getValue().equals(DS_CONTR_CLS.BSD)) {
            return rtrnTMsgArray;
        }

        // START 2017/09/22 K.Kitachi [QC#21063, MOD]
//        String svcContrBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_BR_CD, param.glblCmpyCd.getValue());
        String svcContrBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD, param.glblCmpyCd.getValue());
        // END 2017/09/22 K.Kitachi [QC#21063, MOD]

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            // START 2015/12/11 [QC1886, ADD]
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END 2015/12/11 [QC1886, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            BigDecimal baseChrgAmt = getBaseChrgAmt(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), param.slsDt.getValue());
            Map<String, Object> baseChrgFmContrMdlPrc = getBaseChrgFmContrMdlPrc(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), param.slsDt.getValue());
            if (baseChrgFmContrMdlPrc == null || baseChrgFmContrMdlPrc.isEmpty()) {
                continue;
            }
            // START 2017/09/22 K.Kitachi [QC#21063, MOD]
            String machBrCd = getMachBrCd(param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue());
//            if (!isError(rtrnTMsg, baseChrgFmContrMdlPrc, dsContrTMsg, dsContrDtlTMsg, svcContrBrCd, baseChrgAmt, NSZM0776E, NSZM0777E)) {
            if (!isError(rtrnTMsg, baseChrgFmContrMdlPrc, dsContrTMsg, machBrCd, svcContrBrCd, baseChrgAmt, NSZM0776E, NSZM0777E)) {
                List<Map<String, Object>> usgChrgInfo = getUsgChrgInfo(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), param.slsDt.getValue());
                for (int j = 0; j < usgChrgInfo.size(); j++) {
                    String bllgMtrLbCd = (String) usgChrgInfo.get(j).get("BLLG_MTR_LB_CD");
                    BigDecimal xsMtrAmtRate = (BigDecimal) usgChrgInfo.get(j).get("XS_MTR_AMT_RATE");
                    Map<String, Object> usgChrgFmContrMdlPrc = getUsgChrgFmContrMdlPrc(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), param.slsDt.getValue(), bllgMtrLbCd);
                    if (usgChrgFmContrMdlPrc == null || usgChrgFmContrMdlPrc.isEmpty()) {
                        continue;
                    }
//                    if (isError(rtrnTMsg, usgChrgFmContrMdlPrc, dsContrTMsg, dsContrDtlTMsg, svcContrBrCd, xsMtrAmtRate, NSZM0778E, NSZM0779E)) {
                    if (isError(rtrnTMsg, usgChrgFmContrMdlPrc, dsContrTMsg, machBrCd, svcContrBrCd, xsMtrAmtRate, NSZM0778E, NSZM0779E)) {
                        break;
                    }
                }
            }
            // END 2017/09/22 K.Kitachi [QC#21063, MOD]

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    // START 2017/09/22 K.Kitachi [QC#21063, MOD]
//    private static boolean isError(DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg, Map<String, Object> fmContrMdlPrc, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, String svcContrBrCd, BigDecimal amount, String errMsgId1, String errMsgId2) {
    private static boolean isError(DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg, Map<String, Object> fmContrMdlPrc, DS_CONTRTMsg dsContrTMsg, String machBrCd, String svcContrBrCd, BigDecimal amount, String errMsgId1, String errMsgId2) {
        BigDecimal outTrtyPrcAmt = (BigDecimal) fmContrMdlPrc.get("OUT_TRTY_PRC_AMT");
        BigDecimal inTrtyPrcAmt = (BigDecimal) fmContrMdlPrc.get("IN_TRTY_PRC_AMT");
//        if (dsContrTMsg.svcContrBrCd.getValue().equals(svcContrBrCd) && (!hasValue(outTrtyPrcAmt) || outTrtyPrcAmt.compareTo(amount) != 0)) {
        if (svcContrBrCd.equals(machBrCd) && (!hasValue(outTrtyPrcAmt) || outTrtyPrcAmt.compareTo(amount) != 0)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(errMsgId1), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            return true;
//        } else if (!dsContrTMsg.svcContrBrCd.getValue().equals(svcContrBrCd) && (!hasValue(inTrtyPrcAmt) || inTrtyPrcAmt.compareTo(amount) != 0)) {
        } else if (!svcContrBrCd.equals(machBrCd) && (!hasValue(inTrtyPrcAmt) || inTrtyPrcAmt.compareTo(amount) != 0)) {
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(errMsgId2), DS_CONTR_PK, dsContrTMsg.dsContrPk.getValue());
            return true;
        }
        return false;
    }
    // END 2017/09/22 K.Kitachi [QC#21063, MOD]

    // START 2017/09/22 K.Kitachi [QC#21063, ADD]
    private static String getMachBrCd(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        if (!hasValue(svcMachMstrPk)) {
            return null;
        }
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
        inMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg.fldSvcBrCd.getValue();
    }
    // END 2017/09/22 K.Kitachi [QC#21063, ADD]
}

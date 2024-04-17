/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
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
 * 2016/04/19   Hitachi         T.Kanasaka      Create          QC#6525
 * 2017/02/07   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/10   Hitachi         Y.Takeno        Update          QC#17494
 * 2017/07/12   Hitachi         T.Tomita        Update          QC#19855
 * 2017/08/10   Hitachi         T.Kanasaka      Update          QC#18195
 * </pre>
 */
public class CheckBillingCounter {

    /**
     * checkValidBillingCounter
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkValidBillingCounter(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
        // add start 2017/07/12 QC#19855
        boolean existUsage = existsUsageLine(dsContrDtlTMsgArray);
        // add end   2017/07/12 QC#19855

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
 
            // mod start 2017/07/12 QC#19855
//            if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) && !DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
//                continue;
//            }
            if (!isVldBillingCounterLine(dsContrTMsg.dsContrCatgCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue(), existUsage)) {
                continue;
            }
            // mod end   2017/07/12 QC#19855

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtr(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());

            if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0965E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }

            // mod start 2017/07/12 QC#19855
            if (DS_CONTR_CATG.REGULAR.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                // START 2017/02/07 [QC#17275, ADD]
//                if (!DS_CONTR_CATG.REGULAR.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
//                    continue;
//                }
                if (DS_CONTR_VLD_STS.SUCCESS.equals(rtrnTMsg.dsContrVldStsCd.getValue())) {
                    checkValidEntry(mainClass, param, rtrnTMsg, dsContrTMsg, dsContrDtlTMsg);
                }
                if (DS_CONTR_VLD_STS.SUCCESS.equals(rtrnTMsg.dsContrVldStsCd.getValue())) {
                    checkValidMultiplier(mainClass, param, rtrnTMsg, dsContrTMsg, dsContrDtlTMsg);
                }
                if (DS_CONTR_VLD_STS.SUCCESS.equals(rtrnTMsg.dsContrVldStsCd.getValue())) {
                    checkValidBillingMeterLevel(mainClass, param, rtrnTMsg, dsContrTMsg, dsContrDtlTMsg);
                }
                // END   2017/02/07 [QC#17275, ADD]
            }
            // mod end   2017/07/12 QC#19855

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    // START 2017/02/07 [QC#17275, ADD]
    private static void checkValidEntry(NSZC057001 mainClass, NSZC057001PMsg param, DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        String msgId = null;

        List<Map<String, Object>> rsltList = getContrPhysBllgMtrReln(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
        if (rsltList == null) {
            return;
        }

        for (Map<String, Object> rslt : rsltList) {
            String bllBlFlg = (String) rslt.get("BLLBL_FLG");
            String bllgMtrLbCd = (String) rslt.get("BLLG_MTR_LB_CD");

            if (!ZYPConstant.FLG_ON_Y.equals(bllBlFlg)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
                msgId = NSZM1113E;
                break;
            }
        }

        if (ZYPCommonFunc.hasValue(msgId)) {
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
        }
    }

    private static void checkValidMultiplier(NSZC057001 mainClass, NSZC057001PMsg param, DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        String msgId = null;
        String[] msgPrms = null;
        BigDecimal spcRate = BigDecimal.ONE.negate();
        BigDecimal minRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_MIN_NUM, param.glblCmpyCd.getValue());
        BigDecimal maxRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_MAX_NUM, param.glblCmpyCd.getValue());
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, param.glblCmpyCd.getValue());

        List<Map<String, Object>> rsltList = getContrPhysBllgMtrReln(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
        if (rsltList == null) {
            return;
        }

        for (Map<String, Object> rslt : rsltList) {
            String bllBlFlg = (String) rslt.get("BLLBL_FLG");
            BigDecimal multRate = (BigDecimal) rslt.get("CONTR_MTR_MULT_RATE");

            if (!ZYPConstant.FLG_ON_Y.equals(bllBlFlg)) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(multRate)) {
                if (multRate.compareTo(spcRate) != 0) {
                    if (multRate.compareTo(minRate) < 0 || multRate.compareTo(maxRate) > 0) {
                        msgId = NSZM0678E;
                        msgPrms = new String[] { minRate.toPlainString(), maxRate.toPlainString(), };
                        break;
                    }
                    if (multRate.remainder(fctNum).compareTo(BigDecimal.ZERO) != 0) {
                        msgId = NSZM0679E;
                        msgPrms = new String[] { fctNum.toPlainString() };
                        break;
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(msgId)) {
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, msgPrms), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            return;
        }

        for (int i = 0; i < rsltList.size(); i++) {
            Map<String, Object> rslt = rsltList.get(i);
            String bllBlFlg = (String) rslt.get("BLLBL_FLG");
            // START 2017/02/10 [QC#17494, DEL]
            // String bllgMtrLbCd = (String) rslt.get("BLLG_MTR_LB_CD");
            // BigDecimal multRate = (BigDecimal) rslt.get("CONTR_MTR_MULT_RATE");
            // END   2017/02/10 [QC#17494, DEL]

            if (!ZYPConstant.FLG_ON_Y.equals(bllBlFlg)) {
                continue;
            }

// START 2017/02/10 [QC#17494, DEL]
//            for (int j = i + 1; j < rsltList.size(); j++) {
//                Map<String, Object> cmptRsult = rsltList.get(j);
//                String cmpBllBlFlg = (String) rslt.get("BLLBL_FLG");
//                String cmpBllgMtrLbCd = (String) cmptRsult.get("BLLG_MTR_LB_CD");
//                BigDecimal cmpMultRate = (BigDecimal) cmptRsult.get("CONTR_MTR_MULT_RATE");
//
//                if (!ZYPConstant.FLG_ON_Y.equals(cmpBllBlFlg)) {
//                    continue;
//                }
//
//                if (isEqualStr(bllgMtrLbCd, cmpBllgMtrLbCd) && !isEqualNum(multRate, cmpMultRate)) {
//                    msgId = NSZM1114E;
//                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
//                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId, msgPrms), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
//                    return;
//                }
//            }
// END   2017/02/10 [QC#17494, DEL]
        }
    }

    private static void checkValidBillingMeterLevel(NSZC057001 mainClass, NSZC057001PMsg param, DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        String msgId = null;

        String prcVldFlg = getMtrGrpPrcVldFlg(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcConfigMstrPk.getValue());
        if (!ZYPConstant.FLG_ON_Y.equals(prcVldFlg)) {
            return;
        }

        List<Map<String, Object>> rsltList = getContrPhysBllgMtrReln(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
        if (rsltList == null) {
            return;
        }

        String bllgMtrLvlNum = null;
        for (Map<String, Object> rslt : rsltList) {
            String bllBlFlg = (String) rslt.get("BLLBL_FLG");
            if (!ZYPConstant.FLG_ON_Y.equals(bllBlFlg)) {
                continue;
            }

            String curBllgMtrLvlNum = (String) rslt.get("BLLG_MTR_LVL_NUM");
            if (isEqualStr(bllgMtrLvlNum, null)) {
                bllgMtrLvlNum = curBllgMtrLvlNum;
            } else {
                if (!isEqualStr(bllgMtrLvlNum, curBllgMtrLvlNum)) {
                    msgId = NSZM0677E;
                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    return;
                }
            }
        }

        String exclBllgMtrLbCd;
        for (Map<String, Object> rslt : rsltList) {
            String bllBlFlg = (String) rslt.get("BLLBL_FLG");
            if (!ZYPConstant.FLG_ON_Y.equals(bllBlFlg)) {
                continue;
            }

            String bllgMtrLbCd = (String) rslt.get("BLLG_MTR_LB_CD");
            String mdlMtrLbCd = (String) rslt.get("MDL_MTR_LB_CD");
            // START 2017/08/10 T.Kanasaka [QC#18195,MOD]
//            List<String> prntBllgMtrLbCdList = getPrntBllgMtr(mainClass, param.glblCmpyCd.getValue(), mdlMtrLbCd, bllgMtrLbCd, param.slsDt.getValue());
            List<String> prntBllgMtrLbCdList = getPrntBllgMtr(mainClass, param.glblCmpyCd.getValue(), mdlMtrLbCd, bllgMtrLbCd, param.slsDt.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue());
            // END 2017/08/10 T.Kanasaka [QC#18195,MOD]
            if (prntBllgMtrLbCdList != null) {
                exclBllgMtrLbCd = mdlMtrLbCd;
                if (isExistsPrntBllgMtr(rsltList, prntBllgMtrLbCdList, exclBllgMtrLbCd)) {
                    msgId = NSZM0677E;
                    setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(msgId), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                    return;
                }
            }
        }
    }

    private static boolean isExistsPrntBllgMtr(List<Map<String, Object>> mtrRelnList, List<String> prntBllgMtrLbCdList, String exclBllgMtrLbCd) {
        if (prntBllgMtrLbCdList.size() == 0) {
            return false;
        }

        for (String prntBllgMtrLbCd : prntBllgMtrLbCdList) {
            for (Map<String, Object> mtrReln : mtrRelnList) {
                String bllBlFlg = (String) mtrReln.get("BLLBL_FLG");
                String bllgMtrLbCd = (String) mtrReln.get("BLLG_MTR_LB_CD");

                if (!ZYPConstant.FLG_ON_Y.equals(bllBlFlg)) {
                    continue;
                }
                if (exclBllgMtrLbCd.equals(bllgMtrLbCd)) {
                    continue;
                }
                if (prntBllgMtrLbCd.equals(bllgMtrLbCd)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isEqualStr(String a, String b) {
        if (!ZYPCommonFunc.hasValue(a) && !ZYPCommonFunc.hasValue(b)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(a) && ZYPCommonFunc.hasValue(b) && ZYPCommonFunc.trimTail(a).equals(ZYPCommonFunc.trimTail(b))) {
            return true;
        }
        return false;
    }

// START 2017/02/10 [QC#17494, DEL]
//    private static boolean isEqualNum(BigDecimal a, BigDecimal b) {
//        if (a == null && b == null) {
//            return true;
//        }
//        if (a != null && b != null && a.compareTo(b) == 0) {
//            return true;
//        }
//        return false;
//    }
// END  2017/02/10 [QC#17494, DEL]
    // END  2017/02/07 [QC#17275, ADD]

    // add start 2017/07/12 QC#19855
    private static boolean existsUsageLine(DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray) {
        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTMsgArray.no(i).dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsgArray.no(i).dsContrDtlTpCd.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVldBillingCounterLine(String dsContrCatgCd, String dsContrDtlTpCd, boolean existUsage) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                return existUsage;
            }
        } else {
            if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTpCd)) {
                return true;
            }
        }
        return false;
    }
    // add end   2017/07/12 QC#19855
}

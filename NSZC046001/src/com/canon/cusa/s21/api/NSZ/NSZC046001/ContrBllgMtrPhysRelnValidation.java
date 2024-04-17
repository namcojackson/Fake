/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import parts.common.EZDPItem;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrPhysBllgMtrRelnListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/15   Hitachi         T.Iwamoto       Update          QC#5516
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/08/25   Hitachi         T.Tomita        Update          QC#12136
 * 2016/09/23   Hitachi         A.Kohinata      Update          QC#10972
 * 2016/09/28   Hitachi         K.Kishimoto     Update          QC#9905
 * 2016/10/06   Hitachi         K.Kishimoto     Update          QC#12178
 * </pre>
 */
public class ContrBllgMtrPhysRelnValidation {

    private static void checkPhysBllgMtrRelnParam(NSZC046001_xxContrPhysBllgMtrRelnListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxContrPhysBllgMtrRelnListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean isExistDiffBllgMtrLv(NSZC046001PMsg hdr, NSZC046001_xxContrPhysBllgMtrRelnListPMsg reln, int idx) {

        if (!ZYPCommonFunc.hasValue(reln.machMstrPk)) {
            return true;
        }
        if (FLG_OFF_N.equals(reln.bllblFlg.getValue())) {
            return true;
        }

        String bllgMtrLv = reln.bllgMtrLvlNum.getValue();
        if (!ZYPCommonFunc.hasValue(bllgMtrLv)) {
            // mod start 2016/08/25 CSA Defect#12136
//            MTR_LBTMsg mtrLb = (MTR_LBTMsg) NSZC046001CommonLogic.getCodeTbl(MTR_LB.class.getSimpleName()
//                    , hdr.glblCmpyCd.getValue(), reln.bllgMtrLbCd.getValue());
//            bllgMtrLv = mtrLb.bllgMtrLvlNum.getValue();
            //Mod Start 2016/09/28 <QC#9905>
            bllgMtrLv = NSZC046001CommonLogic.getBllgMtrLvl(hdr, hdr.glblCmpyCd.getValue(), hdr.slsDt.getValue(), reln.svcPhysMtrPk.getValue(), reln.bllgMtrLbCd.getValue());
            //Mod End   2016/09/28 <QC#9905>
            if (!ZYPCommonFunc.hasValue(bllgMtrLv)) {
                return false;
            }
            // mod end 2016/08/25 CSA Defect#12136
        }

        for (int i = 0; i < hdr.xxContrPhysBllgMtrRelnList.getValidCount(); i++) {
            if (i == idx) {
                continue;
            }
            NSZC046001_xxContrPhysBllgMtrRelnListPMsg relnMsg = hdr.xxContrPhysBllgMtrRelnList.no(i);
            // add start 2016/09/23 CSA Defect#10972
            if (!ZYPCommonFunc.hasValue(relnMsg.svcPhysMtrPk)) {
                continue;
            }
            // add end 2016/09/23 CSA Defect#10972

            String trgeBllgMtrLv = relnMsg.bllgMtrLvlNum.getValue();
            if (!ZYPCommonFunc.hasValue(trgeBllgMtrLv)) {
                // mod start 2016/08/25 CSA Defect#12136
//                MTR_LBTMsg mtrLb = (MTR_LBTMsg) NSZC046001CommonLogic.getCodeTbl(MTR_LB.class.getSimpleName()
//                        , hdr.glblCmpyCd.getValue(), relnMsg.bllgMtrLbCd.getValue());
//                trgeBllgMtrLv = mtrLb.bllgMtrLvlNum.getValue();
                // Mod Start 2016/09/28 <QC#9905>
                trgeBllgMtrLv = NSZC046001CommonLogic.getBllgMtrLvl(hdr, hdr.glblCmpyCd.getValue(), hdr.slsDt.getValue(), relnMsg.svcPhysMtrPk.getValue(), relnMsg.bllgMtrLbCd.getValue());
                // Mod End   2016/09/28 <QC#9905>
                // mod end 2016/08/25 CSA Defect#12136
            }
            // START 2016/03/15 T.Iwamoto [QC#5516, ADD]
            if (!FLG_OFF_N.equals(relnMsg.bllblFlg.getValue())) {
                if (!bllgMtrLv.equals(trgeBllgMtrLv)) {
                    return false;
                }
            }
            // END 2016/03/15 T.Iwamoto [QC#5516, ADD]
        }
        return true;
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxContrPhysBllgMtrRelnListPMsg relnMsg) {

        if (ZYPCommonFunc.hasValue(relnMsg.dsContrDtlPk)
                && !NSZC046001CommonLogic.isExistDsContrDtl(param.glblCmpyCd.getValue(), relnMsg.dsContrDtlPk.getValue())) {
            setMsg(relnMsg, NSZM0652E, relnMsg.dsContrDtlPk.getValue().toPlainString(), "DS_CONTR_DTL");
            return false;
        }
        if (ZYPCommonFunc.hasValue(relnMsg.machMstrPk)
                && !NSZC046001CommonLogic.isExistMachMstr(param.glblCmpyCd.getValue(), relnMsg.machMstrPk.getValue())) {
            setMsg(relnMsg, NSZM0652E, relnMsg.machMstrPk.getValue().toPlainString(), "SVC_MACH_MSTR");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistMtrLb(param.glblCmpyCd.getValue(), relnMsg.bllgMtrLbCd.getValue())) {
            setMsg(relnMsg, NSZM0652E, relnMsg.bllgMtrLbCd.getValue(), MTR_LB.class.getSimpleName());
            return false;
        }
        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, NSZC046001_xxContrPhysBllgMtrRelnListPMsg relnMsg, int i) {

        // Del Start 2016/10/06 <QC#12178>
//        if (!isExistDiffBllgMtrLv(param, relnMsg, i)) {
//            setMsg(relnMsg, NSZM0677E);
//            return false;
//        }
        // Del End   2016/10/06 <QC#12178>
        if (!NSXC001001ContrValidation.checkBllgMtrMultRateRange(param.glblCmpyCd.getValue()
                , relnMsg.contrMtrMultRate.getValue())) {
            BigDecimal minNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_MIN_NUM, param.glblCmpyCd.getValue());
            BigDecimal maxNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_MAX_NUM, param.glblCmpyCd.getValue());
            setMsg(relnMsg, NSZM0678E, minNum.toPlainString(), maxNum.toPlainString());
            return false;
        }
        if (!NSXC001001ContrValidation.checkBllgMtrMultRate(param.glblCmpyCd.getValue()
                , relnMsg.contrMtrMultRate.getValue())) {
            BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_FCT_NUM, param.glblCmpyCd.getValue());
            setMsg(relnMsg, NSZM0679E, fctNum.toPlainString());
            return false;
        }
        return true;
    }

    /**
     * validateForCreateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForCreateMode(NSZC046001PMsg param) {

        boolean hasRelnError = false;
        for (int i = 0; i < param.xxContrPhysBllgMtrRelnList.getValidCount(); i++) {
            NSZC046001_xxContrPhysBllgMtrRelnListPMsg relnMsg = param.xxContrPhysBllgMtrRelnList.no(i);
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.contrMtrMultRate, NSZM0589E, "Contract Meter Multiple Rate");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.bllgMtrLbCd, NSZM0589E, "Billing Meter Label Code");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.bllblFlg, NSZM0589E, "Billable Flag");

            if (ZYPCommonFunc.hasValue(relnMsg.xxMsgId)) {
                hasRelnError = true;
                continue;
            }
            if (!checkMaster(param, relnMsg)) {
                hasRelnError = true;
                continue;
            }
            if (!checkConsistency(param, relnMsg, i)) {
                hasRelnError = true;
                continue;
            }
        }

        if (hasRelnError) {
            return false;
        }
        return true;
    }

    /**
     * validateForUpdateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForUpdateMode(NSZC046001PMsg param) {

        boolean hasRelnError = false;
        for (int i = 0; i < param.xxContrPhysBllgMtrRelnList.getValidCount(); i++) {
            NSZC046001_xxContrPhysBllgMtrRelnListPMsg relnMsg = param.xxContrPhysBllgMtrRelnList.no(i);
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.contrPhysBllgMtrRelnPk, NSZM0589E, "Contract Physical Billing Meter Pk");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail Pk");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.contrMtrMultRate, NSZM0589E, "Contract Meter Multiple Rate");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.bllgMtrLbCd, NSZM0589E, "Billing Meter Label Code");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
            checkPhysBllgMtrRelnParam(relnMsg, relnMsg.bllblFlg, NSZM0589E, "Billable Flag");

            if (ZYPCommonFunc.hasValue(relnMsg.xxMsgId)) {
                hasRelnError = true;
                continue;
            }
            if (!checkMaster(param, relnMsg)) {
                hasRelnError = true;
                continue;
            }
            if (!checkConsistency(param, relnMsg, i)) {
                hasRelnError = true;
                continue;
            }
        }

        if (hasRelnError) {
            return false;
        }
        return true;
    }

    /**
     * validateForAddContrMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForAddContrMode(NSZC046001PMsg param) {
        return validateForCreateMode(param);
    }

}

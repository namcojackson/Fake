/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import parts.common.EZDPItem;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

import business.db.CONTR_UPLFT_TPTMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxDsContrRnwEsclListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/06/27   Hitachi         T.Iwamoto       Update          QC#10691
 * 2017/02/27   Hitachi         A.Kohinata      Update          QC#17656
 * </pre>
 */
public class ContrRnwValidation {

    private static void checkRenewParam(NSZC046001_xxDsContrRnwEsclListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxDsContrRnwEsclListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxDsContrRnwEsclListPMsg renewMsg) {

        if (!NSZC046001CommonLogic.isExistAutoRnwTp(param.glblCmpyCd.getValue(), renewMsg.contrAutoRnwTpCd.getValue())) {
            setMsg(renewMsg, NSZM0652E, renewMsg.contrAutoRnwTpCd.getValue(), CONTR_AUTO_RNW_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistUplftTp(param.glblCmpyCd.getValue(), renewMsg.contrUplftTpCd.getValue())) {
            setMsg(renewMsg, NSZM0652E, renewMsg.contrUplftTpCd.getValue(), CONTR_UPLFT_TP.class.getSimpleName());
            return false;
        }
        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, NSZC046001_xxDsContrRnwEsclListPMsg renewMsg) {

        if (!NSXC001001ContrValidation.checkAutoRnwMeth(param.glblCmpyCd.getValue()
                , renewMsg.contrAutoRnwTpCd.getValue(), renewMsg.rnwPrcMethCd.getValue())) {
            // mod start 2017/02/27 CSA Defect#17656
            String uplftNm = ZYPCodeDataUtil.getName(CONTR_AUTO_RNW_TP.class, param.glblCmpyCd.getValue(), renewMsg.contrAutoRnwTpCd.getValue());
            // mod end 2017/02/27 CSA Defect#17656
            setMsg(renewMsg, NSAM0081E, uplftNm, "Renwewal Price Method");
            return false;
        }

        if (!NSXC001001ContrValidation.checkBasePrcUpRatioMandatory(param.glblCmpyCd.getValue()
                , renewMsg.contrAutoRnwTpCd.getValue(), renewMsg.rnwPrcMethCd.getValue(), renewMsg.basePrcUpRatio.getValue())) {
            setMsg(renewMsg, NSAM0081E, RNW_PRC_METH.MARKUP_PERCENT, "Base Price Up Ratio");
            return false;
        }

        if (!NSXC001001ContrValidation.checkBasePrcUpRatioNotAcceptable(param.glblCmpyCd.getValue()
                , renewMsg.contrAutoRnwTpCd.getValue(), renewMsg.rnwPrcMethCd.getValue(), renewMsg.basePrcUpRatio.getValue())) {
            setMsg(renewMsg, NSZM0684E, "Base Price Up Ratio", RNW_PRC_METH.MARKUP_PERCENT);
            return false;
        }

        if (!NSXC001001ContrValidation.checkUsgPrcUpRatioMandatory(param.glblCmpyCd.getValue()
                , renewMsg.contrAutoRnwTpCd.getValue(), renewMsg.rnwPrcMethCd.getValue(), renewMsg.mtrPrcUpRatio.getValue())) {
            setMsg(renewMsg, NSAM0081E, RNW_PRC_METH.MARKUP_PERCENT, "Meter Price Up Ratio");
            return false;
        }

        if (!NSXC001001ContrValidation.checkUsgPrcUpRatioNotAcceptable(param.glblCmpyCd.getValue()
                , renewMsg.contrAutoRnwTpCd.getValue(), renewMsg.rnwPrcMethCd.getValue(), renewMsg.mtrPrcUpRatio.getValue())) {
            setMsg(renewMsg, NSZM0684E, "Meter Price Up Ratio", RNW_PRC_METH.MARKUP_PERCENT);
            return false;
        }

        if (!NSXC001001ContrValidation.checkBaseUplftRatioMandatory(param.glblCmpyCd.getValue()
                , renewMsg.contrUplftTpCd.getValue(), renewMsg.uplftPrcMethCd.getValue(), renewMsg.uplftBasePrcUpRatio.getValue())) {
            setMsg(renewMsg, NSAM0081E, RNW_PRC_METH.MARKUP_PERCENT, "Uplift Base Price Up Ratio");
            return false;
        }

        if (!NSXC001001ContrValidation.checkBaseUplftRatioNotAcceptable(param.glblCmpyCd.getValue()
                , renewMsg.contrUplftTpCd.getValue(), renewMsg.uplftPrcMethCd.getValue(), renewMsg.uplftBasePrcUpRatio.getValue())) {
            setMsg(renewMsg, NSZM0684E, "Uplift Base Price Up Ratio", RNW_PRC_METH.MARKUP_PERCENT);
            return false;
        }

        if (!NSXC001001ContrValidation.checkUsgUplftRatioMandatory(param.glblCmpyCd.getValue()
                , renewMsg.contrUplftTpCd.getValue(), renewMsg.uplftPrcMethCd.getValue(), renewMsg.uplftMtrPrcUpRatio.getValue())) {
            setMsg(renewMsg, NSAM0081E, RNW_PRC_METH.MARKUP_PERCENT, "Uplift Meter Price Up Ratio");
            return false;
        }

        if (!NSXC001001ContrValidation.checkUsgUplftRatioNotAcceptable(param.glblCmpyCd.getValue()
                , renewMsg.contrUplftTpCd.getValue(), renewMsg.uplftPrcMethCd.getValue(), renewMsg.uplftMtrPrcUpRatio.getValue())) {
            setMsg(renewMsg, NSZM0684E, "Uplift Meter Price Up Ratio", RNW_PRC_METH.MARKUP_PERCENT);
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

        boolean hasRenewError = false;
        for (int i = 0; i < param.xxDsContrRnwEsclList.getValidCount(); i++) {
            NSZC046001_xxDsContrRnwEsclListPMsg renewMsg = param.xxDsContrRnwEsclList.no(i);
            checkRenewParam(renewMsg, renewMsg.contrAutoRnwTpCd, NSZM0589E, "Contract Auto Renew Type Code");
            checkRenewParam(renewMsg, renewMsg.contrUplftTpCd, NSZM0589E, "Contract Upliftment Type Code");
            checkRenewParam(renewMsg, renewMsg.firstYrContrUplftFlg, NSZM0589E, "First Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.scdYrContrUplftFlg, NSZM0589E, "Second Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.thirdYrContrUplftFlg, NSZM0589E, "Third Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.frthYrContrUplftFlg, NSZM0589E, "Fourth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.fifthYrContrUplftFlg, NSZM0589E, "Fifth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.sixthYrContrUplftFlg, NSZM0589E, "Sixth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.svnthYrContrUplftFlg, NSZM0589E, "Seventh Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.eighthYrContrUplftFlg, NSZM0589E, "Eighth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.ninthYrContrUplftFlg, NSZM0589E, "Ninth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.tenthYrContrUplftFlg, NSZM0589E, "Tenth Year Contract Upliftment Flag");
            // START 2016/06/27 [QC#10691, MOD]
            if (ZYPCommonFunc.hasValue(renewMsg.contrUplftTpCd)) {
                CONTR_UPLFT_TPTMsg uplftTpTMsg = (CONTR_UPLFT_TPTMsg) NSZC046001CommonLogic.getCodeTbl(CONTR_UPLFT_TP.class.getSimpleName(), param.glblCmpyCd.getValue(), renewMsg.contrUplftTpCd.getValue());
                if (uplftTpTMsg != null && (ZYPConstant.FLG_ON_Y.equals(uplftTpTMsg.uplftBaseFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(uplftTpTMsg.uplftUsgFlg.getValue()))) {
                    checkRenewParam(renewMsg, renewMsg.uplftBefEndRnwDaysAot, NSZM0589E, "Upliftment Before End Renew Days Amount of Time");
                }
            }
            // END 2016/06/27 [QC#10691, MOD]

            if (ZYPCommonFunc.hasValue(renewMsg.xxMsgId)) {
                hasRenewError = true;
                continue;
            }
            if (!checkMaster(param, renewMsg)) {
                hasRenewError = true;
                continue;
            }
            if (!checkConsistency(param, renewMsg)) {
                hasRenewError = true;
                continue;
            }
        }

        if (hasRenewError) {
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

        boolean hasRenewError = false;
        for (int i = 0; i < param.xxDsContrRnwEsclList.getValidCount(); i++) {
            NSZC046001_xxDsContrRnwEsclListPMsg renewMsg = param.xxDsContrRnwEsclList.no(i);
            checkRenewParam(renewMsg, renewMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(renewMsg.xxModeCd.getValue())) {
                checkRenewParam(renewMsg, renewMsg.dsContrRnwEsclPk, NSZM0589E, "DS Contract Renewal Escalation Pk");
                if (ZYPCommonFunc.hasValue(renewMsg.xxMsgId)) {
                    hasRenewError = true;
                }
                continue;
            }

            if (CRUD_MODE_UPDATE.equals(renewMsg.xxModeCd.getValue())) {
                checkRenewParam(renewMsg, renewMsg.dsContrRnwEsclPk, NSZM0589E, "DS Contract Renewal Escalation Pk");
                checkRenewParam(renewMsg, renewMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
                checkRenewParam(renewMsg, renewMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail Pk");
            }

            checkRenewParam(renewMsg, renewMsg.contrAutoRnwTpCd, NSZM0589E, "Contract Auto Renew Type Code");
            checkRenewParam(renewMsg, renewMsg.contrUplftTpCd, NSZM0589E, "Contract Upliftment Type Code");
            checkRenewParam(renewMsg, renewMsg.firstYrContrUplftFlg, NSZM0589E, "First Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.scdYrContrUplftFlg, NSZM0589E, "Second Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.thirdYrContrUplftFlg, NSZM0589E, "Third Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.frthYrContrUplftFlg, NSZM0589E, "Fourth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.fifthYrContrUplftFlg, NSZM0589E, "Fifth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.sixthYrContrUplftFlg, NSZM0589E, "Sixth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.svnthYrContrUplftFlg, NSZM0589E, "Seventh Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.eighthYrContrUplftFlg, NSZM0589E, "Eighth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.ninthYrContrUplftFlg, NSZM0589E, "Ninth Year Contract Upliftment Flag");
            checkRenewParam(renewMsg, renewMsg.tenthYrContrUplftFlg, NSZM0589E, "Tenth Year Contract Upliftment Flag");
            // START 2016/06/27 [QC#10691, MOD]
            if (ZYPCommonFunc.hasValue(renewMsg.contrUplftTpCd)) {
                CONTR_UPLFT_TPTMsg uplftTpTMsg = (CONTR_UPLFT_TPTMsg) NSZC046001CommonLogic.getCodeTbl(CONTR_UPLFT_TP.class.getSimpleName(), param.glblCmpyCd.getValue(), renewMsg.contrUplftTpCd.getValue());
                if (uplftTpTMsg != null && (ZYPConstant.FLG_ON_Y.equals(uplftTpTMsg.uplftBaseFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(uplftTpTMsg.uplftUsgFlg.getValue()))) {
                    checkRenewParam(renewMsg, renewMsg.uplftBefEndRnwDaysAot, NSZM0589E, "Upliftment Before End Renew Days Amount of Time");
                }
            }
            // END 2016/06/27 [QC#10691, MOD]

            if (ZYPCommonFunc.hasValue(renewMsg.xxMsgId)) {
                hasRenewError = true;
                continue;
            }
            if (!checkMaster(param, renewMsg)) {
                hasRenewError = true;
                continue;
            }
            if (!checkConsistency(param, renewMsg)) {
                hasRenewError = true;
                continue;
            }
        }

        if (hasRenewError) {
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

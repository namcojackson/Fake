/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import parts.common.EZDPItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxDsContrBrAllocListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * </pre>
 */
public class ContrBrAllocValidation {

    private static void checkBrAllocParam(NSZC046001_xxDsContrBrAllocListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxDsContrBrAllocListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxDsContrBrAllocListPMsg brMsg) {

        if (ZYPCommonFunc.hasValue(brMsg.svcInvChrgTpCd)
                && !NSZC046001CommonLogic.isExistSvcInvChrgTp(param.glblCmpyCd.getValue(), brMsg.svcInvChrgTpCd.getValue())) {
            setMsg(brMsg, NSZM0652E, brMsg.svcInvChrgTpCd.getValue(), SVC_INV_CHRG_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaBr(param.glblCmpyCd.getValue(), brMsg.coaBrCd.getValue())) {
            setMsg(brMsg, NSZM0652E, brMsg.coaBrCd.getValue(), "COA_BR");
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

        boolean hasBrError = false;
        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrBrAllocListPMsg brMsg = param.xxDsContrBrAllocList.no(i);
            checkBrAllocParam(brMsg, brMsg.coaBrCd, NSZM0589E, "COA Branch Code");
            checkBrAllocParam(brMsg, brMsg.prcAllocRate, NSZM0589E, "Price Allocation Rate");

            if (ZYPCommonFunc.hasValue(brMsg.xxMsgId)) {
                hasBrError = true;
                continue;
            }
            if (!checkMaster(param, brMsg)) {
                hasBrError = true;
                continue;
            }
        }

        if (hasBrError) {
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

        boolean hasBrError = false;
        for (int i = 0; i < param.xxDsContrBrAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrBrAllocListPMsg brMsg = param.xxDsContrBrAllocList.no(i);
            checkBrAllocParam(brMsg, brMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(brMsg.xxModeCd.getValue())) {
                checkBrAllocParam(brMsg, brMsg.dsContrBrAllocPk, NSZM0589E, "DS Contract Billing Alloc Pk");
                if (ZYPCommonFunc.hasValue(brMsg.xxMsgId)) {
                    hasBrError = true;
                }
                continue;
            }

            if (CRUD_MODE_UPDATE.equals(brMsg.xxModeCd.getValue())) {
                checkBrAllocParam(brMsg, brMsg.dsContrBrAllocPk, NSZM0589E, "DS Contract Billing Alloc Pk");
                checkBrAllocParam(brMsg, brMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
            }
            checkBrAllocParam(brMsg, brMsg.coaBrCd, NSZM0589E, "COA Branch Code");
            checkBrAllocParam(brMsg, brMsg.prcAllocRate, NSZM0589E, "Price Allocation Rate");

            if (ZYPCommonFunc.hasValue(brMsg.xxMsgId)) {
                hasBrError = true;
                continue;
            }
            if (!checkMaster(param, brMsg)) {
                hasBrError = true;
                continue;
            }
        }

        if (hasBrError) {
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

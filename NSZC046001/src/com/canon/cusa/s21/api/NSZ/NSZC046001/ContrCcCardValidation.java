/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import parts.common.EZDPItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxDsContrCrCardPoListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/09/02   Hitachi         A.Kohinata      Update          QC#11243
 * </pre>
 */
public class ContrCcCardValidation {

    private static void checkCcParam(NSZC046001_xxDsContrCrCardPoListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxDsContrCrCardPoListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg) {

        if (ZYPCommonFunc.hasValue(ccMsg.crCardCustRefNum)
                // mod start 2016/09/02 CSA Defect#11243
                //&& !NSZC046001CommonLogic.isExistCcRefNum(param.glblCmpyCd.getValue(), ccMsg.crCardCustRefNum.getValue())) {
                && !NSZC046001CommonLogic.isExistCcRefNum(param.glblCmpyCd.getValue(), ccMsg)) {
                // mod end 2016/09/02 CSA Defect#11243
            setMsg(ccMsg, NWZM0971E);
            return false;
        }
        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg) {

//        if (!NSXC001001ContrValidation.checkCrCardPo(ccMsg.custPoNum.getValue(), ccMsg.crCardExprYrMth.getValue())) {
//            setMsg(ccMsg, NSAM0081E, "Customer PO#", "Credit Card Expiration Year Month");
//            return false;
//        }
        return true;
    }

    /**
     * validateForCreateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForCreateMode(NSZC046001PMsg param) {

        boolean hasCcError = false;
        for (int i = 0; i < param.xxDsContrCrCardPoList.getValidCount(); i++) {
            NSZC046001_xxDsContrCrCardPoListPMsg ccMsg = param.xxDsContrCrCardPoList.no(i);

            if (ZYPCommonFunc.hasValue(ccMsg.xxMsgId)) {
                hasCcError = true;
                continue;
            }
            if (!checkMaster(param, ccMsg)) {
                hasCcError = true;
                continue;
            }
            if (!checkConsistency(param, ccMsg)) {
                hasCcError = true;
                continue;
            }
        }

        if (hasCcError) {
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

        boolean hasCcError = false;
        for (int i = 0; i < param.xxDsContrCrCardPoList.getValidCount(); i++) {
            NSZC046001_xxDsContrCrCardPoListPMsg ccMsg = param.xxDsContrCrCardPoList.no(i);
            checkCcParam(ccMsg, ccMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(ccMsg.xxModeCd.getValue())) {
                checkCcParam(ccMsg, ccMsg.dsContrCrCardPoPk, NSZM0589E, "DS Contract Credit Card PO Pk");
                if (ZYPCommonFunc.hasValue(ccMsg.xxMsgId)) {
                    hasCcError = true;
                }
                continue;
            }

            if (CRUD_MODE_UPDATE.equals(ccMsg.xxModeCd.getValue())) {
                checkCcParam(ccMsg, ccMsg.dsContrCrCardPoPk, NSZM0589E, "DS Contract Credit Card PO Pk");
                if (ZYPCommonFunc.hasValue(ccMsg.svcMachMstrPk) && ZYPCommonFunc.hasValue(ccMsg.bllgMtrLbCd)) {
                    checkCcParam(ccMsg, ccMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
                    checkCcParam(ccMsg, ccMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail Pk");
                    checkCcParam(ccMsg, ccMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
                } else if (ZYPCommonFunc.hasValue(ccMsg.svcMachMstrPk)) {
                    checkCcParam(ccMsg, ccMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
                    checkCcParam(ccMsg, ccMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail Pk");
                } else {
                    checkCcParam(ccMsg, ccMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
                }
            }

            if (ZYPCommonFunc.hasValue(ccMsg.xxMsgId)) {
                hasCcError = true;
                continue;
            }
            if (!checkMaster(param, ccMsg)) {
                hasCcError = true;
                continue;
            }
            if (!checkConsistency(param, ccMsg)) {
                hasCcError = true;
                continue;
            }
        }

        if (hasCcError) {
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

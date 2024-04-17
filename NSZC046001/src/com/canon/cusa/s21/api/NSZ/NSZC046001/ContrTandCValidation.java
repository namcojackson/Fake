/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import parts.common.EZDPItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxSvcTermCondListPMsg;

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
public class ContrTandCValidation {

    private static void checkTandCParam(NSZC046001_xxSvcTermCondListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxSvcTermCondListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    /**
     * validateForCreateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForCreateMode(NSZC046001PMsg param) {

        boolean hasTandCError = false;
        for (int i = 0; i < param.xxSvcTermCondList.getValidCount(); i++) {
            NSZC046001_xxSvcTermCondListPMsg tcMsg = param.xxSvcTermCondList.no(i);
            checkTandCParam(tcMsg, tcMsg.svcTermCondAttrbPk, NSZM0589E, "Service Term Condition Attribute PK");
            checkTandCParam(tcMsg, tcMsg.svcTermAttrbMapValCd, NSZM0589E, "Service Term Attribute Mapping Value Code");

            if (ZYPCommonFunc.hasValue(tcMsg.xxMsgId)) {
                hasTandCError = true;
            }
        }

        if (hasTandCError) {
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

        boolean hasTandCError = false;
        for (int i = 0; i < param.xxSvcTermCondList.getValidCount(); i++) {
            NSZC046001_xxSvcTermCondListPMsg tcMsg = param.xxSvcTermCondList.no(i);
            checkTandCParam(tcMsg, tcMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(tcMsg.xxModeCd.getValue())) {
                checkTandCParam(tcMsg, tcMsg.svcTermCondPk, NSZM0589E, "Service Term Condition PK");
                if (ZYPCommonFunc.hasValue(tcMsg.xxMsgId)) {
                    hasTandCError = true;
                }
                continue;
            }

            checkTandCParam(tcMsg, tcMsg.svcTermCondAttrbPk, NSZM0589E, "Service Term Condition Attribute PK");
            checkTandCParam(tcMsg, tcMsg.svcTermAttrbMapValCd, NSZM0589E, "Service Term Attribute Mapping Value Code");

            if (CRUD_MODE_UPDATE.equals(tcMsg.xxModeCd.getValue())) {
                checkTandCParam(tcMsg, tcMsg.svcTermCondPk, NSZM0589E, "Service Term Condition PK");
                checkTandCParam(tcMsg, tcMsg.dsContrPk, NSZM0589E, "DS Contract PK");
                checkTandCParam(tcMsg, tcMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail PK");
            }

            if (ZYPCommonFunc.hasValue(tcMsg.xxMsgId)) {
                hasTandCError = true;
            }
        }

        if (hasTandCError) {
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

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;

import java.math.BigDecimal;

import parts.common.EZDPItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * </pre>
 */
public class ContrXsCopyValidation {

    private static void checkXsCopyParam(NSZC046001_xxContrXsCopyListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxContrXsCopyListPMsg pMsg, String messageId, String... param) {
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

        boolean hasXsCopyError = false;
        for (int i = 0; i < param.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsMsg = param.xxContrXsCopyList.no(i);
            checkXsCopyParam(xsMsg, xsMsg.xsMtrCopyQty, NSZM0589E, "Excess Meter Copy Quantity");
            checkXsCopyParam(xsMsg, xsMsg.xsMtrAmtRate, NSZM0589E, "Excess Meter Amount Rate");
            checkXsCopyParam(xsMsg, xsMsg.bllgMtrLbCd, NSZM0589E, "Billing Meter Label Code");

            // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfoForCreate(param, xsMsg);
            if (!NSZC046001CommonLogic.isFleetLine(dtlMsg) && !NSZC046001CommonLogic.isAggLine(dtlMsg)) {
                checkXsCopyParam(xsMsg, xsMsg.svcMachMstrPk, NSZM0589E, "Service Machine Master PK");
            }
            // END 2016/03/11 T.Iwamoto [QC#5298, MOD]

            if (ZYPCommonFunc.hasValue(xsMsg.xxMsgId)) {
                hasXsCopyError = true;
            }
        }

        if (hasXsCopyError) {
            return false;
        }
        return true;
    }

    // START 2016/03/11 T.Iwamoto [QC#5298, ADD]
    private static NSZC046001_xxContrDtlListPMsg getContrDtlInfoForCreate(NSZC046001PMsg hdr, NSZC046001_xxContrXsCopyListPMsg xsMsg) {
        BigDecimal svcMachMstrPk = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(xsMsg.svcMachMstrPk)) {
            svcMachMstrPk = xsMsg.svcMachMstrPk.getValue();
        }

        for (int i = 0; i < hdr.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtl = hdr.xxContrDtlList.no(i);
            BigDecimal dtlSvcMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(dtl.svcMachMstrPk)) {
                dtlSvcMachMstrPk = dtl.svcMachMstrPk.getValue();
            }
            if (svcMachMstrPk.compareTo(dtlSvcMachMstrPk) == 0) {
                return dtl;
            }
        }
        return null;
    }
    // END 2016/03/11 T.Iwamoto [QC#5298, ADD]

    /**
     * validateForUpdateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForUpdateMode(NSZC046001PMsg param) {

        boolean hasXsCopyError = false;
        for (int i = 0; i < param.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsMsg = param.xxContrXsCopyList.no(i);
            checkXsCopyParam(xsMsg, xsMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(xsMsg.xxModeCd.getValue())) {
                checkXsCopyParam(xsMsg, xsMsg.contrXsCopyPk, NSZM0589E, "Contract Exceess Copy Pk");
                if (ZYPCommonFunc.hasValue(xsMsg.xxMsgId)) {
                    hasXsCopyError = true;
                }
                continue;
            }

            checkXsCopyParam(xsMsg, xsMsg.xsMtrCopyQty, NSZM0589E, "Excess Meter Copy Quantity");
            checkXsCopyParam(xsMsg, xsMsg.xsMtrAmtRate, NSZM0589E, "Excess Meter Amount Rate");

            if (CRUD_MODE_UPDATE.equals(xsMsg.xxModeCd.getValue())) {
                checkXsCopyParam(xsMsg, xsMsg.contrXsCopyPk, NSZM0589E, "Contract Exceess Copy Pk");
                checkXsCopyParam(xsMsg, xsMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
                checkXsCopyParam(xsMsg, xsMsg.xsMtrFirstFlg, NSZM0589E, "Excess Meter First Flag");
            }

            if (ZYPCommonFunc.hasValue(xsMsg.xxMsgId)) {
                hasXsCopyError = true;
            }
        }

        if (hasXsCopyError) {
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

    /**
     * validateForRenewalMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForRenewalMode(NSZC046001PMsg param) {

        boolean hasXsCopyError = false;
        for (int i = 0; i < param.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsMsg = param.xxContrXsCopyList.no(i);
            checkXsCopyParam(xsMsg, xsMsg.contrXsCopyPk, NSZM0589E, "Contract Exceess Copy Pk");
            checkXsCopyParam(xsMsg, xsMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
            checkXsCopyParam(xsMsg, xsMsg.xsMtrCopyQty, NSZM0589E, "Excess Meter Copy Quantity");
            checkXsCopyParam(xsMsg, xsMsg.xsMtrAmtRate, NSZM0589E, "Excess Meter Amount Rate");
            checkXsCopyParam(xsMsg, xsMsg.xsMtrFirstFlg, NSZM0589E, "Excess Meter First Flag");

            if (ZYPCommonFunc.hasValue(xsMsg.xxMsgId)) {
                hasXsCopyError = true;
            }
        }

        if (hasXsCopyError) {
            return false;
        }
        return true;
    }
}

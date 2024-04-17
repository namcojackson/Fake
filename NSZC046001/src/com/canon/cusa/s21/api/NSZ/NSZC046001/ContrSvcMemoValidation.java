/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import parts.common.EZDPItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxSvcMemoListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2015/12/21   Hitachi         T.Tsuchida      Update          QC#2281
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * </pre>
 */
public class ContrSvcMemoValidation {

    private static void checkMemoParam(NSZC046001_xxSvcMemoListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxSvcMemoListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkConsistency(NSZC046001_xxSvcMemoListPMsg memoMsg) {

        if (ZYPCommonFunc.hasValue(memoMsg.svcMemoTrxNm) && !ZYPCommonFunc.hasValue(memoMsg.svcMemoTrxNum)) {
            setMsg(memoMsg, NSBM0049E, "Service Memo Transaction Name", "Service Memo Transaction Number");
            return false;
        }

        if (!ZYPCommonFunc.hasValue(memoMsg.svcMemoTrxNm) && ZYPCommonFunc.hasValue(memoMsg.svcMemoTrxNum)) {
            setMsg(memoMsg, NSBM0049E, "Service Memo Transaction Number", "Service Memo Transaction Name");
            return false;
        }

        if (ZYPCommonFunc.hasValue(memoMsg.dsContrDtlPk) && !ZYPCommonFunc.hasValue(memoMsg.dsContrPk)) {
            setMsg(memoMsg, NSBM0049E, "DS Contract Detail Pk", "DS Contract Pk");
            return false;
        }

        if (!ZYPCommonFunc.hasValue(memoMsg.dsContrPk) && ZYPCommonFunc.hasValue(memoMsg.svcMemoTrxNum)) {
            setMsg(memoMsg, NSAM0149E, "DS Contract Pk", "Service Memo Transaction Number");
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

        boolean hasMemoError = false;
        for (int i = 0; i < param.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg memoMsg = param.xxSvcMemoList.no(i);
            checkMemoParam(memoMsg, memoMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(memoMsg.xxModeCd.getValue())) {
                checkMemoParam(memoMsg, memoMsg.svcMemoPk, NSZM0589E, "Service Memo Pk");
                if (ZYPCommonFunc.hasValue(memoMsg.xxMsgId)) {
                    hasMemoError = true;
                }
                return false;
            }

            if (CRUD_MODE_UPDATE.equals(memoMsg.xxModeCd.getValue())) {
                checkMemoParam(memoMsg, memoMsg.svcMemoPk, NSZM0589E, "Service Memo Pk");
            }
            checkMemoParam(memoMsg, memoMsg.svcMemoCatgCd, NSZM0589E, "Service Memo Category Code");
            checkMemoParam(memoMsg, memoMsg.svcMemoTpCd, NSZM0589E, "Service Memo Type Code");
            checkMemoParam(memoMsg, memoMsg.svcCmntTxt, NSZM0589E, "Service Comment Text");
            checkMemoParam(memoMsg, memoMsg.lastUpdUsrId, NSZM0589E, "Last Update User ID");
            checkMemoParam(memoMsg, memoMsg.lastUpdTs, NSZM0589E, "Last Update Timestamp");
            checkMemoParam(memoMsg, memoMsg.svcMemoRsnCd, NSZM0589E, "Service Memo Reason Code");

            if (ZYPCommonFunc.hasValue(memoMsg.xxMsgId)) {
                hasMemoError = true;
                continue;
            }
            if (!checkConsistency(memoMsg)) {
                hasMemoError = true;
                continue;
            }
        }

        if (hasMemoError) {
            return false;
        }
        return true;
    }

    /**
     * validateForDeleteMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForDeleteMode(NSZC046001PMsg param) {
        return validateForUpdateMode(param);
    }

    /**
     * validateForTerminateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForTerminateMode(NSZC046001PMsg param) {
        return validateForUpdateMode(param);
    }

    /**
     * validateForRenewalMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForRenewalMode(NSZC046001PMsg param) {
        boolean hasMemoError = false;
        for (int i = 0; i < param.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg memoMsg = param.xxSvcMemoList.no(i);
            checkMemoParam(memoMsg, memoMsg.svcMemoCatgCd, NSZM0589E, "Service Memo Category Code");
            checkMemoParam(memoMsg, memoMsg.svcMemoTpCd, NSZM0589E, "Service Memo Type Code");
            checkMemoParam(memoMsg, memoMsg.svcCmntTxt, NSZM0589E, "Service Comment Text");
            checkMemoParam(memoMsg, memoMsg.svcMemoRsnCd, NSZM0589E, "Service Memo Reason Code");

            if (ZYPCommonFunc.hasValue(memoMsg.xxMsgId)) {
                hasMemoError = true;
                continue;
            }
            if (!checkConsistency(memoMsg)) {
                hasMemoError = true;
                continue;
            }
        }

        if (hasMemoError) {
            return false;
        }
        return true;
    }
}

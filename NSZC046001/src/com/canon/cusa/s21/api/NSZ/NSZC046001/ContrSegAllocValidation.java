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
import business.parts.NSZC046001_xxDsContrSegAllocListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2018/04/06   Hitachi         U.Kim           Update          QC#23378(Sol320)
 * 2018/04/26   Hitachi         U.Kim           Update          QC#23378(Sol320)
 * </pre>
 */
public class ContrSegAllocValidation {

    private static void checkSegAllocParam(NSZC046001_xxDsContrSegAllocListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxDsContrSegAllocListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxDsContrSegAllocListPMsg segMsg) {

        if (ZYPCommonFunc.hasValue(segMsg.svcInvChrgTpCd)
                && !NSZC046001CommonLogic.isExistSvcInvChrgTp(param.glblCmpyCd.getValue(), segMsg.svcInvChrgTpCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.svcInvChrgTpCd.getValue(), SVC_INV_CHRG_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaCmpy(param.glblCmpyCd.getValue(), segMsg.coaCmpyCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaCmpyCd.getValue(), "COA_CMPY");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaAffl(param.glblCmpyCd.getValue(), segMsg.coaAfflCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaAfflCd.getValue(), "COA_AFFL");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaBr(param.glblCmpyCd.getValue(), segMsg.coaBrCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaBrCd.getValue(), "COA_BR");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaCh(param.glblCmpyCd.getValue(), segMsg.coaChCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaChCd.getValue(), "COA_CH");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaCc(param.glblCmpyCd.getValue(), segMsg.coaCcCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaCcCd.getValue(), "COA_CC");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaAcct(param.glblCmpyCd.getValue(), segMsg.coaAcctCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaAcctCd.getValue(), "COA_ACCT");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaProd(param.glblCmpyCd.getValue(), segMsg.coaProdCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaProdCd.getValue(), "COA_PROD");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaProj(param.glblCmpyCd.getValue(), segMsg.coaProjCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaProjCd.getValue(), "COA_PROJ");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistCoaExtn(param.glblCmpyCd.getValue(), segMsg.coaExtnCd.getValue())) {
            setMsg(segMsg, NSZM0652E, segMsg.coaExtnCd.getValue(), "COA_EXTN");
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

        boolean hasSegError = false;
        // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
        boolean chkAllocRateFlg = false;
        boolean chkAllocAmtFlg = false;
        // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
        // START 2018/04/26 U.Kim [QC#23378(Sol320), ADD]
        if (param.xxDsContrSegAllocList.getValidCount() == 0) {
            return true;
        }
        // END 2018/04/26 U.Kim [QC#23378(Sol320), ADD]
        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(i);
            checkSegAllocParam(segMsg, segMsg.coaCmpyCd, NSZM0589E, "COA Company Code");
            checkSegAllocParam(segMsg, segMsg.coaAfflCd, NSZM0589E, "COA Affliate Code");
            checkSegAllocParam(segMsg, segMsg.coaBrCd, NSZM0589E, "COA Branch Code");
            checkSegAllocParam(segMsg, segMsg.coaChCd, NSZM0589E, "COA Channel Code");
            checkSegAllocParam(segMsg, segMsg.coaCcCd, NSZM0589E, "COA Cost Center Code");
            checkSegAllocParam(segMsg, segMsg.coaAcctCd, NSZM0589E, "COA Account Code");
            checkSegAllocParam(segMsg, segMsg.coaProdCd, NSZM0589E, "COA Product Code");
            checkSegAllocParam(segMsg, segMsg.coaProjCd, NSZM0589E, "COA Project Code");
            checkSegAllocParam(segMsg, segMsg.coaExtnCd, NSZM0589E, "COA Extension Code");
            // START 2018/04/06 U.Kim [QC#23378(Sol320), MOD]
            // checkSegAllocParam(segMsg, segMsg.prcAllocRate, NSZM0589E, "Price Allocation Rate");
            if (!ZYPCommonFunc.hasValue(segMsg.prcAllocRate) || chkAllocRateFlg){
                chkAllocRateFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(segMsg.prcAllocAmt) || chkAllocAmtFlg){
                chkAllocAmtFlg = true;
            }
            // END 2018/04/06 U.Kim [QC#23378(Sol320), MOD]

            if (ZYPCommonFunc.hasValue(segMsg.xxMsgId)) {
                hasSegError = true;
                continue;
            }
            if (!checkMaster(param, segMsg)) {
                hasSegError = true;
                continue;
            }
        }
        // START 2018/04/06 U.Kim [QC#23378(Sol320), MOD]
        if (chkAllocRateFlg && chkAllocAmtFlg){
            hasSegError = true;
            setMsg(param.xxDsContrSegAllocList.no(0), NSZM1328E);
        }
        if (!chkAllocRateFlg && !chkAllocAmtFlg){
            hasSegError = true;
            setMsg(param.xxDsContrSegAllocList.no(0), NSZM1329E);
        }
        // END 2018/04/06 U.Kim [QC#23378(Sol320), MOD]
        if (hasSegError) {
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

        boolean hasSegError = false;
        // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
        boolean chkAllocRateFlg = false;
        boolean chkAllocAmtFlg = false;
        // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
        // START 2018/04/26 U.Kim [QC#23378(Sol320), ADD]
        if (param.xxDsContrSegAllocList.getValidCount() == 0) {
            return true;
        }
        // END 2018/04/26 U.Kim [QC#23378(Sol320), ADD]
        for (int i = 0; i < param.xxDsContrSegAllocList.getValidCount(); i++) {
            NSZC046001_xxDsContrSegAllocListPMsg segMsg = param.xxDsContrSegAllocList.no(i);
            checkSegAllocParam(segMsg, segMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(segMsg.xxModeCd.getValue())) {
                checkSegAllocParam(segMsg, segMsg.dsContrSegAllocPk, NSZM0589E, "DS Contract Segment Allocation Pk");
                if (ZYPCommonFunc.hasValue(segMsg.xxMsgId)) {
                    hasSegError = true;
                }
                continue;
            }

            if (CRUD_MODE_UPDATE.equals(segMsg.xxModeCd.getValue())) {
                checkSegAllocParam(segMsg, segMsg.dsContrSegAllocPk, NSZM0589E, "DS Contract Segment Allocation Pk");
                checkSegAllocParam(segMsg, segMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
            }

            checkSegAllocParam(segMsg, segMsg.coaCmpyCd, NSZM0589E, "COA Company Code");
            checkSegAllocParam(segMsg, segMsg.coaAfflCd, NSZM0589E, "COA Affliate Code");
            checkSegAllocParam(segMsg, segMsg.coaBrCd, NSZM0589E, "COA Branch Code");
            checkSegAllocParam(segMsg, segMsg.coaChCd, NSZM0589E, "COA Channel Code");
            checkSegAllocParam(segMsg, segMsg.coaCcCd, NSZM0589E, "COA Cost Center Code");
            checkSegAllocParam(segMsg, segMsg.coaAcctCd, NSZM0589E, "COA Account Code");
            checkSegAllocParam(segMsg, segMsg.coaProdCd, NSZM0589E, "COA Product Code");
            checkSegAllocParam(segMsg, segMsg.coaProjCd, NSZM0589E, "COA Project Code");
            checkSegAllocParam(segMsg, segMsg.coaExtnCd, NSZM0589E, "COA Extension Code");
            // START 2018/04/06 U.Kim [QC#23378(Sol320), MOD]
            // checkSegAllocParam(segMsg, segMsg.prcAllocRate, NSZM0589E, "Price Allocation Rate");
            if (!ZYPCommonFunc.hasValue(segMsg.prcAllocRate) || chkAllocRateFlg){
                chkAllocRateFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(segMsg.prcAllocAmt) || chkAllocAmtFlg){
                chkAllocAmtFlg = true;
            }
            // END 2018/04/06 U.Kim [QC#23378(Sol320), MOD]

            if (ZYPCommonFunc.hasValue(segMsg.xxMsgId)) {
                hasSegError = true;
                continue;
            }
            if (!checkMaster(param, segMsg)) {
                hasSegError = true;
                continue;
            }
        }
        // START 2018/04/06 U.Kim [QC#23378(Sol320), MOD]
        if (chkAllocRateFlg && chkAllocAmtFlg){
            hasSegError = true;
            setMsg(param.xxDsContrSegAllocList.no(0), NSZM1328E);
        }
        if (!chkAllocRateFlg && !chkAllocAmtFlg){
            hasSegError = true;
            setMsg(param.xxDsContrSegAllocList.no(0), NSZM1329E);
        }
        // END 2018/04/06 U.Kim [QC#23378(Sol320), MOD]

        if (hasSegError) {
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

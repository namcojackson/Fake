/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDPItem;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1655
 * 2016/03/03   Hitachi         T.Kanasaka      Update          QC#3188
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/01   Hitachi         Y.Tsuchimoto    Update          QC#6287
 * 2016/09/23   Hitachi         K.Yamada        Update          QC#13686
 * </pre>
 */
public class ContrBllgMtrValidation {

    private static void checkBllgMtrParam(NSZC046001_xxDsContrBllgMtrListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxDsContrBllgMtrListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static NSZC046001_xxContrDtlListPMsg getContrDtlInfo(NSZC046001PMsg hdr, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {
        BigDecimal dsContrDtlPk = bllgMtr.dsContrDtlPk.getValue();
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return null;
        }

        for (int i = 0; i < hdr.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtl = hdr.xxContrDtlList.no(i);
            if (dsContrDtlPk.compareTo(dtl.dsContrDtlPk.getValue()) == 0) {
                return dtl;
            }
        }
        return null;
    }

    // START 2016/03/11 T.Iwamoto [QC#5298, ADD]
    private static NSZC046001_xxContrDtlListPMsg getContrDtlInfoForCreate(NSZC046001PMsg hdr, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {
        BigDecimal svcMachMstrPk = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(bllgMtr.svcMachMstrPk)) {
            svcMachMstrPk = bllgMtr.svcMachMstrPk.getValue();
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

    private static List<NSZC046001_xxContrXsCopyListPMsg> getXsCopyInfo(NSZC046001PMsg hdr, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr) {
        List<NSZC046001_xxContrXsCopyListPMsg> xsList = new ArrayList<NSZC046001_xxContrXsCopyListPMsg>();
        // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
        BigDecimal machMstrPk = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(bllgMtr.svcMachMstrPk)) {
            machMstrPk = bllgMtr.svcMachMstrPk.getValue();
        }
        // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
        String mtrLbCd = bllgMtr.bllgMtrLbCd.getValue();

        for (int i = 0; i < hdr.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsMsg = hdr.xxContrXsCopyList.no(i);
            // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
            BigDecimal xsSvcMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(xsMsg.svcMachMstrPk)) {
                xsSvcMachMstrPk = xsMsg.svcMachMstrPk.getValue();
            }
            if (machMstrPk.compareTo(xsSvcMachMstrPk) == 0 && mtrLbCd.equals(xsMsg.bllgMtrLbCd.getValue())) {
                xsList.add(xsMsg);
            }
            // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
        }

        if (xsList.isEmpty()) {
            return null;
        }

        NSZC046001CommonLogic.sortXsCopy(xsList);
        return xsList;
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg) {

        if (!NSZC046001CommonLogic.isExistMtrLb(param.glblCmpyCd.getValue(), bllgMtrMsg.bllgMtrLbCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0652E, bllgMtrMsg.bllgMtrLbCd.getValue(), MTR_LB.class.getSimpleName());
            return false;
        }
        // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
        if (ZYPCommonFunc.hasValue(bllgMtrMsg.bllgMtrBillToCustCd)
                && !NSZC046001CommonLogic.isExistBillTo(param.glblCmpyCd.getValue(), bllgMtrMsg.bllgMtrBillToCustCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0652E, bllgMtrMsg.bllgMtrBillToCustCd.getValue(), "BILL_TO_CUST");
            return false;
        }
        if (ZYPCommonFunc.hasValue(bllgMtrMsg.bllgMtrBllgCycleCd)
                && !NSZC046001CommonLogic.isExistBllgCycle(param.glblCmpyCd.getValue(), bllgMtrMsg.bllgMtrBllgCycleCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0652E, bllgMtrMsg.bllgMtrBllgCycleCd.getValue(), BLLG_CYCLE.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(bllgMtrMsg.xsChrgTpCd)
                && !NSZC046001CommonLogic.isExistXsChrgTp(param.glblCmpyCd.getValue(), bllgMtrMsg.xsChrgTpCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0652E, bllgMtrMsg.xsChrgTpCd.getValue(), XS_CHRG_TP.class.getSimpleName());
            return false;
        }
        // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
        if (ZYPCommonFunc.hasValue(bllgMtrMsg.dsContrBllgMtrStsCd)
                && !NSZC046001CommonLogic.isExistContrDtlSts(param.glblCmpyCd.getValue(), bllgMtrMsg.dsContrBllgMtrStsCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0652E, bllgMtrMsg.dsContrBllgMtrStsCd.getValue(), DS_CONTR_DTL_STS.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(bllgMtrMsg.intgMdseCd)
                && !NSZC046001CommonLogic.isExistMdse(param.glblCmpyCd.getValue(), bllgMtrMsg.intgMdseCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0652E, bllgMtrMsg.intgMdseCd.getValue(), "MDSE");
            return false;
        }
        return true;
    }

    private static boolean checkRange(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg) {

        if (ZYPCommonFunc.hasValue(bllgMtrMsg.bllgRollOverRatio)) {
            BigDecimal ratio = bllgMtrMsg.bllgRollOverRatio.getValue();
            if (BigDecimal.ZERO.compareTo(ratio) > 0
                    || BigDecimal.valueOf(100).compareTo(ratio) < 0) {
                setMsg(bllgMtrMsg, NSZM0680E);
                return false;
            }
        }
        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg, ONBATCH_TYPE onBatchType) {

        // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//        NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, bllgMtrMsg);
//        if (dtlMsg != null && !NSXC001001ContrValidation.checkBllgCycle(param.glblCmpyCd.getValue()
//                , bllgMtrMsg.bllgMtrBllgCycleCd.getValue(), dtlMsg.mtrCloDay.getValue())) {
//            String cycleNm = ZYPCodeDataUtil.getName(BLLG_CYCLE.class, param.glblCmpyCd.getValue(), bllgMtrMsg.bllgMtrBllgCycleCd.getValue());
//            setMsg(bllgMtrMsg, NSZM0657E, cycleNm);
//            return false;
//        }
        // END 2016/03/03 T.Kanasaka [QC3188, DEL]

        if (!NSXC001001ContrValidation.checkBllgMtrParam(bllgMtrMsg.bllgMinCnt.getValue(), bllgMtrMsg.bllgMinAmtRate.getValue()
                , bllgMtrMsg.bllgRollOverRatio.getValue(), bllgMtrMsg.bllgFreeCopyCnt.getValue())) {
            setMsg(bllgMtrMsg, NSZM0681E);
            return false;
        }

        List<NSZC046001_xxContrXsCopyListPMsg> xsList = getXsCopyInfo(param, bllgMtrMsg);
        if (xsList != null && !NSXC001001ContrValidation.checkXsMinVol(bllgMtrMsg.bllgMinCnt.getValue()
                , xsList.size(), xsList.get(0).xsMtrAmtRate.getValue())) {
            setMsg(bllgMtrMsg, NSZM0682E);
            return false;
        }
        if (xsList != null && !NSXC001001ContrValidation.checkXsMinAmtRollOver(bllgMtrMsg.bllgMinAmtRate.getValue()
                , bllgMtrMsg.bllgRollOverRatio.getValue(), xsList.size())) {
            setMsg(bllgMtrMsg, NSZM0683E);
            return false;
        }

        // START 2016/03/03 T.Kanasaka [QC3188, MOD]
        if (ZYPConstant.FLG_ON_Y.equals(param.usgChrgToLeaseCmpyFlg.getValue())
                && !NSXC001001ContrValidation.checkLeaseBillTo(param.leaseCmpyCd.getValue(), bllgMtrMsg.bllgMtrBillToCustCd.getValue())) {
            setMsg(bllgMtrMsg, NSZM0664E);
            return false;
        }
        // END 2016/03/03 T.Kanasaka [QC3188, MOD]

//        if (ZYPCommonFunc.hasValue(bllgMtrMsg.bllgMtrBillToCustCd)
//                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), bllgMtrMsg.bllgMtrBillToCustCd.getValue(), onBatchType)) {
//            setMsg(bllgMtrMsg, NSZM0698E, "Billing Meter Bill To", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(bllgMtrMsg.bllgMtrBillToCustCd)
//                && !NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), bllgMtrMsg.bllgMtrBillToCustCd.getValue(), onBatchType)
//                && !ZYPCommonFunc.hasValue(param.custPoNum)) {
//            setMsg(bllgMtrMsg, NSZM0698E, "Billing Meter Bill To", "Account");
//            return false;
//        }

        return true;
    }

    /**
     * validateForCreateMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForCreateMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        boolean hasBllgMtrError = false;
        for (int i = 0; i < param.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg = param.xxDsContrBllgMtrList.no(i);
            checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrLbCd, NSZM0589E, "Billing Meter Label Code");

            // START 2016/03/11 T.Iwamoto [QC#5298, MOD]
            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfoForCreate(param, bllgMtrMsg);
            if (NSZC046001CommonLogic.isRegular(param, dtlMsg) || NSZC046001CommonLogic.isFleetLine(dtlMsg)
                    || NSZC046001CommonLogic.isAggMach(param, dtlMsg)) {
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrBillToCustCd, NSZM0589E, "Billing Meter Bill To Customer Code");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrBllgCycleCd, NSZM0589E, "Billing Meter Billing Cycle Code");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.xsChrgTpCd, NSZM0589E, "Excess Charge Type Code");
                // del start 2016/09/23 CSA Defect#13686
                //checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
                // del end 2016/09/23 CSA Defect#13686
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.intgMdseCd, NSZM0589E, "Intangible Merchandise Code");
            }
            if (!NSZC046001CommonLogic.isFleetLine(dtlMsg) && !NSZC046001CommonLogic.isAggLine(dtlMsg)) {
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.svcMachMstrPk, NSZM0589E, "Service Machine Master PK");
            }
            // END 2016/03/11 T.Iwamoto [QC#5298, MOD]
            if (ZYPCommonFunc.hasValue(bllgMtrMsg.xxMsgId)) {
                hasBllgMtrError = true;
                continue;
            }

            if (!checkMaster(param, bllgMtrMsg)) {
                hasBllgMtrError = true;
                continue;
            }
            if (!checkRange(param, bllgMtrMsg)) {
                hasBllgMtrError = true;
                continue;
            }
            if (!checkConsistency(param, bllgMtrMsg, onBatchType)) {
                hasBllgMtrError = true;
                continue;
            }
        }

        if (hasBllgMtrError) {
            return false;
        }
        return true;
    }

    /**
     * validateForUpdateMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForUpdateMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        boolean hasBllgMtrError = false;
        for (int i = 0; i < param.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg = param.xxDsContrBllgMtrList.no(i);
            checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.xxModeCd, NSZM0589E, "Mode");
            if (CRUD_MODE_DELETE.equals(bllgMtrMsg.xxModeCd.getValue())) {
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
                if (ZYPCommonFunc.hasValue(bllgMtrMsg.xxMsgId)) {
                    hasBllgMtrError = true;
                }
                continue;
            }

            checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrLbCd, NSZM0589E, "Billing Meter Label Code");
            // START 2016/04/01 Y.Tsuchimoto [QC#6287, DEL]
            //checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
            // END   2016/04/01 Y.Tsuchimoto [QC#6287, DEL]

            NSZC046001_xxContrDtlListPMsg dtlMsg = getContrDtlInfo(param, bllgMtrMsg);
            if (dtlMsg == null) {
                hasBllgMtrError = true;
                continue;
            }

            if (NSZC046001CommonLogic.isRegular(param, dtlMsg) || NSZC046001CommonLogic.isFleetLine(dtlMsg)
                    || NSZC046001CommonLogic.isAggLine(dtlMsg) || NSZC046001CommonLogic.isAggMach(param, dtlMsg)) {
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrBillToCustCd, NSZM0589E, "Billing Meter Bill To Customer Code");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrBllgCycleCd, NSZM0589E, "Billing Meter Billing Cycle Code");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.xsChrgTpCd, NSZM0589E, "Excess Charge Type Code");
                // del start 2016/09/23 CSA Defect#13686
                // START 2016/04/01 Y.Tsuchimoto [QC#6287, ADD]
                //checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.ctacPsnPk, NSZM0589E, "Contact Person PK");
                // END   2016/04/01 Y.Tsuchimoto [QC#6287, ADD]
                // del end 2016/09/23 CSA Defect#13686
            }

            if (CRUD_MODE_UPDATE.equals(bllgMtrMsg.xxModeCd.getValue())) {
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail Pk");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgMtrLbCd, NSZM0589E, "Billing Meter Label Code");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.dsContrBllgMtrStsCd, NSZM0589E, "DS Contract Billing Meter Status Code");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.qltyAsrnHldFlg, NSZM0589E, "Quality Asuarance Hold Flag");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.mtrHldFlg, NSZM0589E, "Meter Hold Flag");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.contrHldFlg, NSZM0589E, "Contract Hold Flag");
                checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.bllgHldFlg, NSZM0589E, "Billing Hold Flag");
            }

            if (ZYPCommonFunc.hasValue(bllgMtrMsg.xxMsgId)) {
                hasBllgMtrError = true;
                continue;
            }

            if (!checkMaster(param, bllgMtrMsg)) {
                hasBllgMtrError = true;
                continue;
            }
            if (!checkRange(param, bllgMtrMsg)) {
                hasBllgMtrError = true;
                continue;
            }
            if (!checkConsistency(param, bllgMtrMsg, onBatchType)) {
                hasBllgMtrError = true;
                continue;
            }
        }

        if (hasBllgMtrError) {
            return false;
        }
        return true;
    }

    /**
     * validateForAddContrMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForAddContrMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {
        return validateForCreateMode(param, onBatchType);
    }

    /**
     * validateForRenewalMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForRenewalMode(NSZC046001PMsg param) {

        boolean hasBllgMtrError = false;
        for (int i = 0; i < param.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg = param.xxDsContrBllgMtrList.no(i);
            checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.dsContrDtlPk, NSZM0589E, "DS Contract Deatil Pk");
            checkBllgMtrParam(bllgMtrMsg, bllgMtrMsg.dsContrBllgMtrPk, NSZM0589E, "DS Contract Billing Meter Pk");
            if (ZYPCommonFunc.hasValue(bllgMtrMsg.xxMsgId)) {
                hasBllgMtrError = true;
            }
        }
        if (hasBllgMtrError) {
            return false;
        }
        return true;
    }

}

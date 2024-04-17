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
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxDsContrAddlChrgListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/03/22   Hitachi         T.Iwamoto       Update          QC#5806
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * </pre>
 */
public class ContrAddlChrgValidation {

    private static void checkAddChrgParam(NSZC046001_xxDsContrAddlChrgListPMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001_xxDsContrAddlChrgListPMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static boolean checkMaster(NSZC046001PMsg param, NSZC046001_xxDsContrAddlChrgListPMsg addMsg) {

        if (!NSZC046001CommonLogic.isExistAddlChrgTp(param.glblCmpyCd.getValue(), addMsg.addlChrgTpCd.getValue())) {
            setMsg(addMsg, NSZM0652E, addMsg.addlChrgTpCd.getValue(), ADDL_CHRG_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistSvcBillByTp(param.glblCmpyCd.getValue(), addMsg.svcBillByTpCd.getValue())) {
            setMsg(addMsg, NSZM0652E, addMsg.svcBillByTpCd.getValue(), SVC_BILL_BY_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistAddlChrgInvTp(param.glblCmpyCd.getValue(), addMsg.addlChrgInvTpCd.getValue())) {
            setMsg(addMsg, NSZM0652E, addMsg.addlChrgInvTpCd.getValue(), ADDL_CHRG_INV_TP.class.getSimpleName());
            return false;
        }
        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, NSZC046001_xxDsContrAddlChrgListPMsg addMsg, int i) {

        if (addMsg.effFromDt.getValue().compareTo(addMsg.effThruDt.getValue()) > 0) {
            setMsg(addMsg, NSAM0279E);
            return false;
        }
        if (!NSXC001001ContrValidation.checkAddlChrgTermDt(addMsg.trmnDt.getValue(), addMsg.effThruDt.getValue())) {
            setMsg(addMsg, NSZM0672E);
            return false;
        }
        if (!NSXC001001ContrValidation.checkAddlChrgTermDt(addMsg.trmnDt.getValue(), param.contrVrsnEffThruDt.getValue())) {
            setMsg(addMsg, NSZM0673E);
            return false;
        }

        List<String> contrBaseBllgCycleList = new ArrayList<String>();
        for (int j = 0; j < param.xxContrDtlList.getValidCount(); j++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = param.xxContrDtlList.no(j);
            if (ZYPCommonFunc.hasValue(dtlMsg.baseBllgCycleCd)
                    && !contrBaseBllgCycleList.contains(dtlMsg.baseBllgCycleCd.getValue())) {
                contrBaseBllgCycleList.add(dtlMsg.baseBllgCycleCd.getValue());
            }
        }
        if (!NSXC001001ContrValidation.checkAddlChrgBllgCycle(param.glblCmpyCd.getValue()
                , addMsg.bllgCycleCd.getValue(), contrBaseBllgCycleList)) {
            setMsg(addMsg, NSZM0674E);
            return false;
        }

        if (!NSXC001001ContrValidation.checkAddlChrgBillByFlatTp(param.glblCmpyCd.getValue()
                , addMsg.svcBillByTpCd.getValue(), addMsg.addlChrgFlatDealPrcAmt.getValue())) {
            String billByNm = ZYPCodeDataUtil.getName(SVC_BILL_BY_TP.class, param.glblCmpyCd.getValue(), addMsg.svcBillByTpCd.getValue());
            setMsg(addMsg, NSZM0675E, billByNm, "Flat Price");
            return false;
        }

        if (!NSXC001001ContrValidation.checkAddlChrgBillByPctTp(param.glblCmpyCd.getValue()
                , addMsg.svcBillByTpCd.getValue(), addMsg.addlChrgAplcPct.getValue())) {
            String billByNm = ZYPCodeDataUtil.getName(SVC_BILL_BY_TP.class, param.glblCmpyCd.getValue(), addMsg.svcBillByTpCd.getValue());
            setMsg(addMsg, NSZM0675E, billByNm, "Applicable%");
            return false;
        }

        if (!isExistDuplicateAddlChrg(param, addMsg, i)) {
            setMsg(addMsg, NSZM0676E);
            return false;
        }
        return true;
    }

    private static boolean isExistDuplicateAddlChrg(NSZC046001PMsg hdr, NSZC046001_xxDsContrAddlChrgListPMsg orgMsg, int idx) {

        // START 2016/03/22 [QC#5806, MOD]
        BigDecimal orgSvcMachMstrPk = orgMsg.svcMachMstrPk.getValue();
        if (!ZYPCommonFunc.hasValue(orgMsg.svcMachMstrPk)) {
            orgSvcMachMstrPk = BigDecimal.ZERO;
        }
        String addlChrgTp = orgMsg.addlChrgTpCd.getValue();
        String effFromDt = orgMsg.effFromDt.getValue();
        String effThruDt = orgMsg.effThruDt.getValue();

        for (int i = 0; i < hdr.xxDsContrAddlChrgList.getValidCount(); i++) {
            if (i == idx) {
                continue;
            }
            NSZC046001_xxDsContrAddlChrgListPMsg addlMsg = hdr.xxDsContrAddlChrgList.no(i);

            BigDecimal svcMachMstrPk = addlMsg.svcMachMstrPk.getValue();
            if (!ZYPCommonFunc.hasValue(addlMsg.svcMachMstrPk)) {
                svcMachMstrPk = BigDecimal.ZERO;
            }
            if (addlChrgTp.equals(addlMsg.addlChrgTpCd.getValue()) && orgSvcMachMstrPk.compareTo(svcMachMstrPk) == 0) {
                if (effFromDt.compareTo(addlMsg.effThruDt.getValue()) <= 0 && effThruDt.compareTo(addlMsg.effFromDt.getValue()) >= 0) {
                    return false;
                }
            }
        }
        return true;
        // END 2016/03/22 [QC#5806, MOD]
    }

    protected static boolean validateForCreateMode(NSZC046001PMsg param) {

        boolean hasAddError = false;
        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            NSZC046001_xxDsContrAddlChrgListPMsg addMsg = param.xxDsContrAddlChrgList.no(i);
            checkAddChrgParam(addMsg, addMsg.addlChrgTpCd, NSZM0589E, "Additional Charge Type Code");
            checkAddChrgParam(addMsg, addMsg.svcBillByTpCd, NSZM0589E, "Service Bill By Type Code");
            checkAddChrgParam(addMsg, addMsg.addlChrgInvTpCd, NSZM0589E, "Additional Charge Invoice Type Code");
            checkAddChrgParam(addMsg, addMsg.printDtlFlg, NSZM0589E, "Print Detail Flag");
            checkAddChrgParam(addMsg, addMsg.bllgCycleCd, NSZM0589E, "Billing Cycle Code");
            checkAddChrgParam(addMsg, addMsg.effFromDt, NSZM0589E, "Effective From Date");
            checkAddChrgParam(addMsg, addMsg.effThruDt, NSZM0589E, "Effective Thru Date");
            checkAddChrgParam(addMsg, addMsg.addlChrgInvdFlg, NSZM0589E, "Additional Charge Invoiced Flag");

            if (ZYPCommonFunc.hasValue(addMsg.xxMsgId)) {
                hasAddError = true;
                continue;
            }
            if (!checkMaster(param, addMsg)) {
                hasAddError = true;
                continue;
            }
            if (!checkConsistency(param, addMsg, i)) {
                hasAddError = true;
                continue;
            }
        }

        if (hasAddError) {
            return false;
        }
        return true;
    }

    protected static boolean validateForUpdateMode(NSZC046001PMsg param) {

        boolean hasAddError = false;
        for (int i = 0; i < param.xxDsContrAddlChrgList.getValidCount(); i++) {
            NSZC046001_xxDsContrAddlChrgListPMsg addMsg = param.xxDsContrAddlChrgList.no(i);
            checkAddChrgParam(addMsg, addMsg.xxModeCd, NSZM0589E, "Mode");

            if (CRUD_MODE_DELETE.equals(addMsg.xxModeCd.getValue())) {
                checkAddChrgParam(addMsg, addMsg.dsContrAddlChrgPk, NSZM0589E, "DS Contract Additional Charge Pk");
                if (ZYPCommonFunc.hasValue(addMsg.xxMsgId)) {
                    hasAddError = true;
                }
                continue;
            }

            if (CRUD_MODE_UPDATE.equals(addMsg.xxModeCd.getValue())) {
                checkAddChrgParam(addMsg, addMsg.dsContrAddlChrgPk, NSZM0589E, "DS Contract Additional Charge Pk");
                checkAddChrgParam(addMsg, addMsg.dsContrPk, NSZM0589E, "DS Contract Pk");
                checkAddChrgParam(addMsg, addMsg.dsContrDtlPk, NSZM0589E, "DS Contract Detail Pk");
                checkAddChrgParam(addMsg, addMsg.ccyCd, NSZM0589E, "Currency Code");
                checkAddChrgParam(addMsg, addMsg.addlChrgFlatDealPrcAmt, NSZM0589E, "Additional Charge Flat Deal Amount");
                checkAddChrgParam(addMsg, addMsg.addlChrgFlatFuncPrcAmt, NSZM0589E, "Additional Charge Flat Func Amount");
                checkAddChrgParam(addMsg, addMsg.addlChrgAplcPct, NSZM0589E, "Additional Charge Applicable Percent");
                checkAddChrgParam(addMsg, addMsg.invUpToDt, NSZM0589E, "Invoice Upto Date");
            }

            checkAddChrgParam(addMsg, addMsg.addlChrgTpCd, NSZM0589E, "Additional Charge Type Code");
            checkAddChrgParam(addMsg, addMsg.svcBillByTpCd, NSZM0589E, "Service Bill By Type Code");
            checkAddChrgParam(addMsg, addMsg.addlChrgInvTpCd, NSZM0589E, "Additional Charge Invoice Type Code");
            checkAddChrgParam(addMsg, addMsg.printDtlFlg, NSZM0589E, "Print Detail Flag");
            checkAddChrgParam(addMsg, addMsg.bllgCycleCd, NSZM0589E, "Billing Cycle Code");
            checkAddChrgParam(addMsg, addMsg.effFromDt, NSZM0589E, "Effective From Date");
            checkAddChrgParam(addMsg, addMsg.effThruDt, NSZM0589E, "Effective Thru Date");
            checkAddChrgParam(addMsg, addMsg.addlChrgInvdFlg, NSZM0589E, "Additional Charge Invoiced Flag");

            if (ZYPCommonFunc.hasValue(addMsg.xxMsgId)) {
                hasAddError = true;
                continue;
            }
            if (!checkMaster(param, addMsg)) {
                hasAddError = true;
                continue;
            }
            if (!checkConsistency(param, addMsg, i)) {
                hasAddError = true;
                continue;
            }
        }

        if (hasAddError) {
            return false;
        }
        return true;
    }

    protected static boolean validateForAddContrMode(NSZC046001PMsg param) {
        return validateForCreateMode(param);
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1680.common;

import static business.blap.NWAL1680.constant.NWAL1680Constant.NZZM0000E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1680.NWAL1680CMsg;
import business.blap.NWAL1680.NWAL1680Query;
import business.blap.NWAL1680.NWAL1680_ACMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWAL1680CommonLogic {

    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @param bizMsg        NWAL1650CMsg
     * </pre>
     */
    public static void setSummaryCountsByOrder(String glblCmpyCd, String cpoOrdNum, NWAL1680CMsg bizMsg) {
        // Total Configurations
        S21SsmEZDResult ssmResultLC = NWAL1680Query.getInstance().getLineConfig(glblCmpyCd, cpoOrdNum);
        if (!ssmResultLC.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        BigDecimal resutlCountLC = new BigDecimal((Integer) ssmResultLC.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_LC, resutlCountLC);

        // Total Machines
        S21SsmEZDResult ssmResultLM = NWAL1680Query.getInstance().getMachineLineConfig(glblCmpyCd, cpoOrdNum);
        if (!ssmResultLM.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        BigDecimal resutlCountLM = new BigDecimal((Integer) ssmResultLM.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_LM, resutlCountLM);

        // Total RMA Configurationss
        S21SsmEZDResult ssmResultRC = NWAL1680Query.getInstance().getRmaConfig(glblCmpyCd, cpoOrdNum);
        if (!ssmResultRC.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        BigDecimal resutlCountRC = new BigDecimal((Integer) ssmResultRC.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_RC, resutlCountRC);

        // Total RMA Machines
        S21SsmEZDResult ssmResultRM = NWAL1680Query.getInstance().getMachineRmaConfig(glblCmpyCd, cpoOrdNum);
        if (!ssmResultRM.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        BigDecimal resutlCountRM = new BigDecimal((Integer) ssmResultRM.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_RM, resutlCountRM);

        // Total Shells
        S21SsmEZDResult ssmResultSC = NWAL1680Query.getInstance().getTotalShells(glblCmpyCd, cpoOrdNum);
        if (!ssmResultSC.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        BigDecimal resutlCountSC = new BigDecimal((Integer) ssmResultSC.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_SC, resutlCountSC);

        // Total Machines Covered by Shell
        S21SsmEZDResult ssmResultSM = NWAL1680Query.getInstance().getTotalMachinesCoveredByShell(glblCmpyCd, cpoOrdNum);
        if (!ssmResultSM.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        BigDecimal resutlCountSM = new BigDecimal((Integer) ssmResultSM.getResultObject());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_SM, resutlCountSM);

    }

    /**
     * <pre>
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @param bizMsg        NWAL1650CMsg
     * </pre>
     */
    public static void setSummaryCountsByModel(String glblCmpyCd, String cpoOrdNum, NWAL1680CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL1680Query.getInstance().getSummaryByModel(glblCmpyCd, cpoOrdNum);
        List<Map<?, ?>> resultList = (List<Map<?, ?>>) ssmResult.getResultObject();
        int i = 0;
        BigDecimal totalL = new BigDecimal(0);
        BigDecimal totalR = new BigDecimal(0);
        BigDecimal totalS = new BigDecimal(0);
        for (Map<?, ?> resultMap : resultList) {
            NWAL1680_ACMsg bizMsgA = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(bizMsgA.mdlDescTxt_A, (String) resultMap.get("MODEL"));
            ZYPEZDItemValueSetter.setValue(bizMsgA.xxDtlCnt_AL, (BigDecimal) resultMap.get("TOTAL_CONF"));
            totalL = totalL.add((BigDecimal) resultMap.get("TOTAL_CONF"));
            ZYPEZDItemValueSetter.setValue(bizMsgA.xxDtlCnt_AR, (BigDecimal) resultMap.get("TOTAL_RMA"));
            totalR = totalR.add((BigDecimal) resultMap.get("TOTAL_RMA"));
            ZYPEZDItemValueSetter.setValue(bizMsgA.xxDtlCnt_AS, (BigDecimal) resultMap.get("TOTAL_SHELL"));
            totalS = totalS.add((BigDecimal) resultMap.get("TOTAL_SHELL"));
            i++;
        }
        bizMsg.A.setValidCount(i);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_TL, totalL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_TR, totalR);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlCnt_TS, totalS);
    }
}

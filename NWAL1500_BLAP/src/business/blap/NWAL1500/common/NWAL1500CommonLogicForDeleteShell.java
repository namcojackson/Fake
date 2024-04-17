/*
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForDeleteShell;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_BSMsg;
import business.db.CPOTMsg;
import business.db.MDSETMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSZC115001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/18   Fujitsu         S.Takami        Create          S21_NA#54223
 * </pre>
 */
public class NWAL1500CommonLogicForDeleteShell {

    /**
     * <pre>Delete related shell, if this order is not Retail Equipment Order.
     * @param bizMsg NWAL1500CMsg
     */
    public static void deleteRelatedShell(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return;
        }
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);
        if (cpoTMsg == null) {
            return;
        }

        S21SsmEZDResult ssmResultMdseTp = NWAL1500QueryForDeleteShell.getInstance().getSvcAddlMdseTpList(bizMsg);
        List<String> svcAddlMdseTpList = new ArrayList<String>(0);
        if (ssmResultMdseTp.isCodeNormal()) {
            for (String coaMdseTpCd : (List<String>) ssmResultMdseTp.getResultObject()) {
                svcAddlMdseTpList.add(coaMdseTpCd);
            }
        }

        boolean isOrdWithShell = false;
        for (int slctConfIndex = 0; slctConfIndex < glblMsg.A.getValidCount(); slctConfIndex++) {
            NWAL1500_ASMsg configMsg = glblMsg.A.no(slctConfIndex);
            boolean isConfTpAddToConf = CONFIG_TP.ADD_TO_CONFIG.equals(configMsg.configTpCd_LC.getValue());
            String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
            for (int slctLineIndex = 0; slctLineIndex < glblMsg.B.getValidCount(); slctLineIndex++) {
                NWAL1500_BSMsg lineMsg = glblMsg.B.no(slctLineIndex);
                if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_LL.getValue())) {
                    continue;
                }
                MDSETMsg mdseTMsg = NWAL1500CommonLogic.getMdse(bizMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_LL.getValue());
                if (isConfTpAddToConf) {
                    if (svcAddlMdseTpList.contains(mdseTMsg.coaMdseTpCd.getValue())) {
                        if (isExistsMainMachine(bizMsg.glblCmpyCd.getValue(), configMsg.svcConfigMstrPk_LC.getValue())) {
                            isOrdWithShell = true;
                        }
                    }
                } else {
                    boolean isBaseCmpt = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, lineMsg.baseCmptFlg_LL.getValue());
                    boolean isInstallBase = false;
                    if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                        isInstallBase = true;
                    }
                    if (isBaseCmpt && isInstallBase) {
                        isOrdWithShell = true;
                        break;
                    }
                }
            }
            if (isOrdWithShell) {
                break;
            }
        }

        if (isOrdWithShell) {
            BigDecimal billByTpCount = NWAL1500QueryForDeleteShell.getInstance().getBillByTpCount(bizMsg);
            if (BigDecimal.ZERO.compareTo(billByTpCount) == 0) {
                isOrdWithShell = false;
            }
        }
        if (isOrdWithShell) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL1500QueryForDeleteShell.getInstance().getDetailRelatedShell(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        List<Map<String, String>> detailRelatedShellList = (List<Map<String, String>>) ssmResult.getResultObject();

        NSZC115001PMsg shellApiPMsg = new NSZC115001PMsg();
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        shellApiPMsg.xxProcMd.setValue(NSZC115001Constant.PROC_MODE_PHYS_DEL);
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.slsDt, bizMsg.slsDt);
        int shellApiValCnt = 0;

        for (Map<String, String> detailRelatedShell : detailRelatedShellList) {
            shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoOrdNum.setValue(detailRelatedShell.get("CPO_ORD_NUM"));
            shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineNum.setValue(detailRelatedShell.get("CPO_DTL_LINE_NUM"));
            shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineSubNum.setValue(detailRelatedShell.get("CPO_DTL_LINE_SUB_NUM"));
            shellApiValCnt++;
        }
        if (shellApiValCnt > 0) {
            shellApiPMsg.svcConfigRefList.setValidCount(shellApiValCnt);

            new NSZC115001().execute(shellApiPMsg, ONBATCH_TYPE.ONLINE);
        }

        return;
    }

    private static boolean isExistsMainMachine(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        boolean rslt = false;
        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.svcConfigMstrPk, svcConfigMstrPk);
        svcConfigMstrTMsg = (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(svcConfigMstrTMsg);
        if (svcConfigMstrTMsg != null) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcConfigMstrTMsg.svcMachMstrPk);
            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstrTMsg);
            if (svcMachMstrTMsg != null) {
                rslt = true;
            }
        }
        return rslt;
    }
}

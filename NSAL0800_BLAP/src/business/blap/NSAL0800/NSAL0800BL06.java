/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0800;

import static business.blap.NSAL0800.constant.NSAL0800Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0800.common.NSAL0800CommonLogic;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_INV_TGTR_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2017/06/16   Hitachi         K.Kojima        Update          QC#19230
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0800BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL0800CMsg bizMsg = (NSAL0800CMsg) cMsg;
            NSAL0800SMsg glblMsg = (NSAL0800SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0800Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0800Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0800CMsg
     * @param sMsg NSAL0800SMsg
     */
    private void doProcess_NSAL0800Scrn00_CMN_Submit(NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd_A1) && !NSAL0800CommonLogic.isExistsSellToCust(glblCmpyCd, cMsg)) {
            cMsg.leaseCmpyCd_A1.setErrorInfo(1, NFAM0066E, new String[] {"Default Lease Company Code", "Sell To Customer Master" });
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_A1, "");
            return;
        }

        // QC#18799 UPD START
        DS_CONTR_INTFC_DEF_RULETMsg updateTmsg = NSAL0800CommonLogic.getDsContrIntfcDefRule(glblCmpyCd, cMsg);
        // QC#18799 UPD END
        if (updateTmsg == null) {
            // Insert
            DS_CONTR_INTFC_DEF_RULETMsg tMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
            setDsContrIntfcDefRuleData(tMsg, cMsg, sMsg);

            S21FastTBLAccessor.create(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NACM0746E, new String[] {"Interface Rule", tMsg.contrIntfcSrcTpCd.getValue() });
                return;
            }
        } else {
            // Update
            setDsContrIntfcDefRuleData(updateTmsg, cMsg, sMsg);

            S21FastTBLAccessor.update(updateTmsg);
            if (!RTNCD_NORMAL.equals(updateTmsg.getReturnCode())) {
                cMsg.setMessageInfo(NACM0747E, new String[] {"Interface Rule", updateTmsg.contrIntfcSrcTpCd.getValue() });
                return;
            }
        }
        cMsg.setMessageInfo(ZZM8100I);
    }

    private void setDsContrIntfcDefRuleData(DS_CONTR_INTFC_DEF_RULETMsg tMsg, NSAL0800CMsg cMsg, NSAL0800SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.contrIntfcSrcTpCd, cMsg.contrIntfcSrcTpCd_HS);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, cMsg.effFromDt_H1);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.enblFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.enblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.enblFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.mtrEstTpCd, cMsg.mtrEstTpCd_ES);
        ZYPEZDItemValueSetter.setValue(tMsg.baseBllgCycleCd, cMsg.bllgCycleCd_E2);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrBllgCycleCd, cMsg.bllgCycleCd_E4);
        // START 2017/06/16 K.Kojima [QC#19230,MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(cMsg.invSeptBaseUsgFlg_E1.getValue())) {
        //     ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_ON_Y);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
        // }
        // START 2022/03/22 [QC#59683, MOD]
//        ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgFlg, NSAL0800CommonLogic.switchFlg(cMsg.invSeptBaseUsgFlg_E1));
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTgtrTpCd, cMsg.dsInvTgtrTpCd_E1);
        DS_INV_TGTR_TPTMsg dsInvTgtrTp = NSAL0800CommonLogic.getDsInvTgtrTp(glblCmpyCd, cMsg.dsInvTgtrTpCd_E1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgFlg, dsInvTgtrTp.invSeptBaseUsgFlg);
        // END   2022/03/22 [QC#59683, MOD]
        // END 2017/06/16 K.Kojima [QC#19230,MOD]
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.prcAllocByMachQtyFlg_E1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.contrUplftTpCd, cMsg.contrUplftTpCd_FS);
        ZYPEZDItemValueSetter.setValue(tMsg.uplftPrcMethCd, cMsg.uplftPrcMethCd_FS);
        ZYPEZDItemValueSetter.setValue(tMsg.uplftBasePrcUpRatio, cMsg.uplftBasePrcUpRatio_F1);
        ZYPEZDItemValueSetter.setValue(tMsg.uplftMtrPrcUpRatio, cMsg.uplftMtrPrcUpRatio_F1);
        ZYPEZDItemValueSetter.setValue(tMsg.bllgThruTpCd, cMsg.bllgThruTpCd_GS);
        ZYPEZDItemValueSetter.setValue(tMsg.bllgThruDt, cMsg.bllgThruDt_G1);
        ZYPEZDItemValueSetter.setValue(tMsg.stubPrepFromTpCd, cMsg.stubPrepFromTpCd_IS);
        ZYPEZDItemValueSetter.setValue(tMsg.stubPrepThruTpCd, cMsg.stubPrepThruTpCd_IS);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.autoEffFleetFlg_I1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.autoEffFleetFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.autoEffFleetFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.autoEffAggrFlg_I1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.autoEffAggrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.autoEffAggrFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyCd, cMsg.leaseCmpyCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadMethCd, cMsg.mtrReadMethCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrSlsCrTpCd, cMsg.dsContrSlsCrTpCd_AS);
        ZYPEZDItemValueSetter.setValue(tMsg.contrAutoRnwTpCd, cMsg.contrAutoRnwTpCd_BS);
        ZYPEZDItemValueSetter.setValue(tMsg.rnwPrcMethCd, cMsg.rnwPrcMethCd_BS);
        ZYPEZDItemValueSetter.setValue(tMsg.befEndRnwDaysAot, cMsg.befEndRnwDaysAot_B1);
        ZYPEZDItemValueSetter.setValue(tMsg.basePrcUpRatio, cMsg.basePrcUpRatio_B1);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrPrcUpRatio, cMsg.mtrPrcUpRatio_B1);
        ZYPEZDItemValueSetter.setValue(tMsg.startReadVldTpCd, cMsg.startReadVldTpCd_CS);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadWinDaysAot, cMsg.mtrReadWinDaysAot_C1);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.allwDataCrctFlg_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.allwDataCrctFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.allwDataCrctFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#18799 UPD START
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrClsCd, cMsg.dsContrClsCd_HS);
        ZYPEZDItemValueSetter.setValue(tMsg.contrCloDay, cMsg.contrCloDay_GS);
        ZYPEZDItemValueSetter.setValue(tMsg.contrBllgDay, cMsg.contrBllgDay_GS);
        // QC#18799 UPD END
        ZYPEZDItemValueSetter.setValue(tMsg.bllgRollOverRatio, cMsg.bllgRollOverRatio_C1);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.printDtlFlg_E1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.printDtlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#18799 ADD START
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, cMsg.svcLineBizCd_HS);
        ZYPEZDItemValueSetter.setValue(tMsg.bllgLimitAmt, cMsg.bllgLimitAmt_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadWinMlyDaysAot, cMsg.mtrReadWinMlyDaysAot_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadWinOthDaysAot, cMsg.mtrReadWinOthDaysAot_C1);
        ZYPEZDItemValueSetter.setValue(tMsg.befEndUplftDaysAot, cMsg.befEndUplftDaysAot_F1);
        ZYPEZDItemValueSetter.setValue(tMsg.baseBllgTmgCd, cMsg.bllgTmgTpCd_GS);
        ZYPEZDItemValueSetter.setValue(tMsg.rnwExtMthAot, cMsg.rnwExtMthAot_B1);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.istlCallMtrReadUseFlg_C1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.istlCallMtrReadUseFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.istlCallMtrReadUseFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.invSeptBaseUsgMstrFlg_E1.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgMstrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgMstrFlg, ZYPConstant.FLG_OFF_N);
        }
        // QC#18799 ADD END
    }
}

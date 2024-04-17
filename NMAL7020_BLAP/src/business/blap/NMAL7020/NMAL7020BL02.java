/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7020;

import static business.blap.NMAL7020.constant.NMAL7020Constant.VAR_CHAR_CONST_NM_MEMO;
import static business.blap.NMAL7020.constant.NMAL7020Constant.VAR_CHAR_CONST_NM_MEMO_LINE2;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.PRC_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EFF_APPLY_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CALC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PCT_AMT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7020BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 * 2016/07/29   Fujitsu         R.Nakamura      Update          QC#9025
 *</pre>
 */
public class NMAL7020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7020CMsg bizMsg = (NMAL7020CMsg) cMsg;

            if ("NMAL7020_INIT".equals(screenAplID)) {
                doProcess_NMAL7020_INIT(bizMsg);

            } else if ("NMAL7020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7020Scrn00_CMN_Clear(bizMsg);

            } else if ("NMAL7020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7020Scrn00_CMN_Submit(bizMsg);

            } else if ("NMAL7020_NWAL1130".equals(screenAplID)) {
                doProcess_NMAL7020_NWAL1130(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7020_INIT(NMAL7020CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(PRC_CALC_TP.class, bizMsg.prcCalcTpCd_P, bizMsg.prcCalcTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(PRC_PCT_AMT_TP.class, bizMsg.prcPctAmtTpCd_P, bizMsg.prcPctAmtTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(EFF_APPLY_LVL_TP.class, bizMsg.effApplyLvlTpCd_P, bizMsg.effApplyLvlTpDescTxt_P);

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd)) {
            PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, bizMsg.prcCatgCd);
            tMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_O, tMsg.prcCatgNm);
        }

        String memo = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_MEMO, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_A, memo);

        String memo2 = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_MEMO_LINE2, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.varCharConstVal_B, memo2);

        // Add Start 2016/07/29 QC#9025
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, ZYPConstant.CHKBOX_ON_Y);
        // Add End 2016/07/29 QC#9025
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7020Scrn00_CMN_Clear(NMAL7020CMsg bizMsg) {
        bizMsg.clear();
        doProcess_NMAL7020_INIT(bizMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7020Scrn00_CMN_Submit(NMAL7020CMsg bizMsg) {
        // doNothing
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7020_NWAL1130(NMAL7020CMsg bizMsg) {
        // doNothing
    }

}

package business.blap.NMAL7300;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7300.constant.NMAL7300Constant;
import business.db.PRC_RULE_ATTRBTMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NMAL7300BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7300CMsg bizMsg = (NMAL7300CMsg) cMsg;
            if ("NMAL7300_INIT".equals(screenAplID)) {
                doProcess_NMAL7300_INIT(bizMsg);
            } else if ("NMAL7300Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7300_INIT(bizMsg);
            } else if ("NMAL7300Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NMAL7300Scrn00_CMN_Close(bizMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7300_INIT(NMAL7300CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL7300_ACMsg conditionLine = bizMsg.A.no(i);
            String descTxt = getAttribDescTxt(bizMsg, conditionLine.prcRuleAttrbCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(conditionLine.prcRuleAttrbDescTxt_A, descTxt);
            NMXC105001PriceMasterUtil.getRuleAttrbNm(conditionLine.prcRuleAttrbCd_A.getValue(), conditionLine.prcRuleCondFromTxt_A, conditionLine.xxRecNmTxt_A, descTxt);
        }
    }

    private void doProcess_NMAL7300Scrn00_CMN_Close(NMAL7300CMsg bizMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).prcRuleDtlTxt_B)) {
                NMXC105001PriceMasterUtil.checkFormat(NMAL7300Constant.CHK_TYPE_DEC_AMT, bizMsg.B.no(i).prcRuleDtlTxt_B, NMAL7300Constant.AMT_LEN, NMAL7300Constant.DEF_DIGIT_NUM, "Amount");
            }
        }
    }

    private String getAttribDescTxt(NMAL7300CMsg bizMsg, String prcRuleAttrbCd){
        PRC_RULE_ATTRBTMsg inMsg = new PRC_RULE_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prcRuleAttrbCd, prcRuleAttrbCd);
        PRC_RULE_ATTRBTMsg outMsg = (PRC_RULE_ATTRBTMsg)EZDTBLAccessor.findByKey(inMsg);
        if(outMsg == null){
            return null;
        }
        return outMsg.prcRuleAttrbDescTxt.getValue();
    }
}

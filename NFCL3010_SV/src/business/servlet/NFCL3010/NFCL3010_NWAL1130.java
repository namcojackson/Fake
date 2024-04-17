/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3010.NFCL3010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFCL3010_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        NFCL3010CMsg bizMsg = new NFCL3010CMsg();
        bizMsg.setBusinessID("NFCL3010");
        bizMsg.setFunctionCode("20");

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("Click_LinkCollector".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H, scrnMsg.P.no(2).xxComnScrColValTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_H, scrnMsg.P.no(3).xxComnScrColValTxt);
                }
                scrnMsg.setFocusItem(scrnMsg.psnCd_H);
            } else if("Click_LinkBankAccount_1".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H, scrnMsg.P.no(0).xxComnScrColValTxt);
                }
            } else if("Click_LinkBankAccount_2".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H, scrnMsg.P.no(1).xxComnScrColValTxt);
                }
            } else if("Click_LinkBankAccount_3".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(2).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.bankRteNum_H, scrnMsg.P.no(2).xxComnScrColValTxt);
                }
            } else if("Click_LinkBankAccount_4".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(3).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H, scrnMsg.P.no(3).xxComnScrColValTxt);
                }
            }
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        NFCL3010CMsg bizMsg  = (NFCL3010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}

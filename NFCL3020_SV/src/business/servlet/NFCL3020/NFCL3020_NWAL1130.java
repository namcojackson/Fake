/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3020.NFCL3020CMsg;
//import business.servlet.NFCL3020.constant.NFCL3020Constant;

import business.blap.NFCL3020.NFCL3020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/11   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3020_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        NFCL3020CMsg bizMsg = new NFCL3020CMsg();
        bizMsg.setBusinessID("NFCL3020");
        bizMsg.setFunctionCode("20");

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if("Click_LinkBankAccount_1".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H, scrnMsg.P.no(0).xxComnScrColValTxt);
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H, scrnMsg.P.no(1).xxComnScrColValTxt);
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(3).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H, scrnMsg.P.no(3).xxComnScrColValTxt);
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.remDsBankAcctPk_H, new BigDecimal(scrnMsg.P.no(4).xxComnScrColValTxt.getValue()));
            } else if("Click_LinkBankAccount_2".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H, scrnMsg.P.no(0).xxComnScrColValTxt);
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H, scrnMsg.P.no(1).xxComnScrColValTxt);
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(3).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H, scrnMsg.P.no(3).xxComnScrColValTxt);
                }
            } else if("Click_LinkBankAccount_3".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H, scrnMsg.P.no(0).xxComnScrColValTxt);
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxComnScrColValTxt)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H, scrnMsg.P.no(1).xxComnScrColValTxt);
                }
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

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        NFCL3020CMsg bizMsg  = (NFCL3020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFCL3030.NFCL3030CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 10/05/2015   Fujitsu         T.Tanaka        Update          Location Search
 * 02/02/2018   Fujitsu         T.Murai         Update          QC#21372
 * 2023/07/03   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFCL3030_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg = new NFCL3030CMsg();

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        String rtnVal = null;
        if (!"CMN_Close".equals(getLastGuard())) {
           if ("Click_LinkCustomer".equals(scrEventNm)) {
               rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
               if (ZYPCommonFunc.hasValue(rtnVal)) {
                   scrnMsg.payerCustCd_H.setValue(rtnVal);
               }
               scrnMsg.setFocusItem(scrnMsg.payerCustCd_H);
           } else if ("Click_LinkLocation".equals(scrEventNm)) {
               rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
               if (ZYPCommonFunc.hasValue(rtnVal)) {
                   scrnMsg.locNum_H.setValue(rtnVal);
               }
               rtnVal = scrnMsg.P.no(1).xxComnScrColValTxt.getValue();
               if (ZYPCommonFunc.hasValue(rtnVal)) {
                   scrnMsg.locNm_H.setValue(rtnVal);
               }
           } else if (("Click_LinkBankAccount_11".equals(scrEventNm)) || ("Click_LinkBankAccount_12".equals(scrEventNm)) || ("Click_LinkBankAccount_13".equals(scrEventNm))) {
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H1, scrnMsg.P.no(0).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H1, scrnMsg.P.no(1).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H1, scrnMsg.P.no(3).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.remDsBankAcctPk_H1, new BigDecimal(scrnMsg.P.no(4).xxComnScrColValTxt.getValue()));
               
           } else if (("Click_LinkBankAccount_21".equals(scrEventNm)) || ("Click_LinkBankAccount_22".equals(scrEventNm)) || ("Click_LinkBankAccount_23".equals(scrEventNm))) {
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H2, scrnMsg.P.no(0).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H2, scrnMsg.P.no(1).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.bankRteNum_H2, scrnMsg.P.no(2).xxComnScrColValTxt); // ADD 2018/02/02 QC#21372
               // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H2, scrnMsg.P.no(3).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_M1, scrnMsg.P.no(3).xxComnScrColValTxt);
               ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H2, scrnMsg.P.no(5).xxComnScrColValTxt);
               // END 2023/07/03 S.Nakatani [QC#55645, MOD]
               ZYPEZDItemValueSetter.setValue(scrnMsg.custDsBankAcctPk_H2, new BigDecimal(scrnMsg.P.no(4).xxComnScrColValTxt.getValue()));
           }
        }

        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("Click_LinkCustomer".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.payerCustCd_H);
            scrnMsg.addCheckItem(scrnMsg.payerCustCd_H);
            scrnMsg.putErrorScreen();
        }
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/10   Hitachi         S.Nakatani      Create          QC#55645
 *</pre>
 */
public class NFDL0030_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_BankAccountSearch".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.bankRteNum_H, scrnMsg.R.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H, scrnMsg.R.no(2).xxComnScrColValTxt);
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(3).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H2, scrnMsg.R.no(3).xxComnScrColValTxt);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(4).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustBankAcctRelnPk_H, new BigDecimal(scrnMsg.R.no(4).xxComnScrColValTxt.getValue()));
            }
        }
    }
}

/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/20   Hitachi         R.Takau         Create          QC#55645
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_BankAccountDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.altPayerCustCd)) {
            scrnMsg.altPayerCustCd.setErrorInfo(1, ZZM9000E, new String[] {"Bill To Location" });
        }
        scrnMsg.addCheckItem(scrnMsg.altPayerCustCd);
        scrnMsg.putErrorScreen();

        Object[] params = new Object[3];
        params[0] = "";
        params[1] = scrnMsg.altPayerCustCd;
        params[2] = scrnMsg.dsCustBankAcctRelnPk;
        setArgForSubScreen(params);

    }
}

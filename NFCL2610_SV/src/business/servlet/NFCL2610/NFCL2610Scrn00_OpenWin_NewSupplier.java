/*
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.NEW_SUPPLIER;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/10/28   CITS            R.Kurahashi     Create          QC#57732
 *</pre>
 */
public class NFCL2610Scrn00_OpenWin_NewSupplier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

       NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.billToCustLocCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;

        Object[] params = new Object[4];

        scrnMsg.prntVndPk_P.clear();

        params[0] = scrnMsg.prntVndPk_P;
        params[1] = scrnMsg.billToCustAcctCd;
        params[2] = scrnMsg.billToCustLocCd;
        params[3] = NEW_SUPPLIER[0];
        setArgForSubScreen(params);

    }
}

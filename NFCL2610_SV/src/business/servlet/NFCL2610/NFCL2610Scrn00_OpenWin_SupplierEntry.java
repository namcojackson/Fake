/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NFCL2610.constant.NFCL2610Constant.ENTRY_SUPPLIER;
/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7267
 * 2016/05/13   Hitachi         K.Kojima        Update          QC#7796
 * 2017/02/17   Fujitsu         T.Murai         Update          QC#16281
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 *</pre>
 */
public class NFCL2610Scrn00_OpenWin_SupplierEntry extends S21CommonHandler {

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

        // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
        //Object[] params = new Object[3];
        Object[] params = new Object[4];
        // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
        // START 2016/05/13 K.Kojima [QC#7796,DEL] // START 2017/02/17 [QC#16281,ADD]
        int index = scrnMsg.xxRadioBtn.getValueInt();
        if (index >= 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndPk_P, scrnMsg.B.no(index).prntVndPk);
        } else {
        // END 2016/05/13 K.Kojima [QC#7796,DEL] // END 2017/02/17 [QC#16281,ADD]
        scrnMsg.prntVndPk_P.clear();
        // START 2016/05/13 K.Kojima [QC#7796,DEL] // START 2017/02/17 [QC#16281,ADD]
        }
        // END 2016/05/13 K.Kojima [QC#7796,DEL] // END 2017/02/17 [QC#16281,ADD]
        params[0] = scrnMsg.prntVndPk_P;
        params[1] = scrnMsg.billToCustAcctCd;
        params[2] = scrnMsg.billToCustLocCd;
        // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
        params[3] = ENTRY_SUPPLIER[0];
        // END 2020/10/28 R.Kurahashi [QC#57732,ADD]
        setArgForSubScreen(params);
    }
}

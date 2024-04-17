/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.SCRN_TRANS_COND_ZYP0210;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 * 2023/03/14   Hitachi         S.Fujita        Update          QC#61113
 *</pre>
 */
public class NMAL2460Scrn00_MoveWin_UploadAccountOwner extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getLastGuard());
        // START 2023/03/14 S.Fujita [QC#61113, DEL]
        // NMAL2460CommonLogic.clearParam(scrnMsg);

        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, SCRN_TRANS_COND_ZYP0210);

        // Object[] params = new Object[1];
        // params[0] = scrnMsg.xxPopPrm_01;
        // setArgForSubScreen(params);
        // END 2023/03/14 S.Fujita [QC#61113, DEL]
    }
}

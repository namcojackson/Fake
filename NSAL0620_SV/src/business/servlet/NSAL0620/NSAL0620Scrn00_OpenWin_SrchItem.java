/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0620.constant.NSAL0620Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/10/13   Hitachi         M.Komatsu       Create          QC#60537
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_SrchItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL0620Constant.MDSE_SEARCH_MODE_ALL);

        Object[] params = new Object[10];
        params[0] = scrnMsg.mdseCd_H;
        params[1] = scrnMsg.xxPopPrm_01; // not used
        params[2] = scrnMsg.xxPopPrm_01; // not used
        params[3] = scrnMsg.xxPopPrm_01; // not used
        params[4] = scrnMsg.xxPopPrm_01; // not used
        params[5] = scrnMsg.xxPopPrm_01; // not used
        params[6] = scrnMsg.xxPopPrm_01; // not used
        params[7] = scrnMsg.xxPopPrm_01; // not used
        params[8] = scrnMsg.xxPopPrm_01; // not used
        params[9] = scrnMsg.xxPopPrm_00;

        setArgForSubScreen(params);
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.NSAL0300_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/12   Hitachi         T.Tomita        Update          CSA QC#2876
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_ContrMainte extends S21CommonHandler {

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
        int idex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk_P, scrnMsg.A.no(idex).dsContrPk_A);

        Object[] params = new Object[NSAL0300_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.dsContrPk_P;

        setArgForSubScreen(params);
        // START 2016/01/12 T.Tomita [QC#2876, ADD]
        openMultiModeScreen();
        // END 2016/01/12 T.Tomita [QC#2876, ADD]
    }
}
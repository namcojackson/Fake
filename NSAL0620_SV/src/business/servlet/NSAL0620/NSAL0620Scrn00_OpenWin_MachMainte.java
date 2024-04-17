/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.NSAL0010_PRM_LENGTH;
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
 * 2017/02/24   Hitachi         K.Kitachi       Update          QC#17749
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_MachMainte extends S21CommonHandler {

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
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_P, scrnMsg.A.no(idex).svcMachMstrPk_A.getValue());

        Object[] params = new Object[NSAL0010_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.svcMachMstrPk_P;

        setArgForSubScreen(params);
        // START 2017/02/24 K.Kitachi [QC#17749, ADD]
        openMultiModeScreen("Meter Entry");
        // END 2017/02/24 K.Kitachi [QC#17749, ADD]
    }
}
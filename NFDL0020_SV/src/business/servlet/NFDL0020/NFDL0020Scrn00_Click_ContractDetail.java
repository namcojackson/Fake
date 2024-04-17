/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/15   Hitachi         K.Kojima        Create          QC#11478
 *</pre>
 */
public class NFDL0020Scrn00_Click_ContractDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        Object[] params = new Object[1];
        params[0] = scrnMsg.B.no(selectIdx).dsContrPk_BD;
        setArgForSubScreen(params);
    }
}

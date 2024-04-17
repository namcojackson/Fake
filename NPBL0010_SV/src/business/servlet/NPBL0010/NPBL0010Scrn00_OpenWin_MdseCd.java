/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0010.common.NPBL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/03/06   Fujitsu         R.Nakamura      Create          QC#55940
 *</pre>
 */
public class NPBL0010Scrn00_OpenWin_MdseCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        // Making of subscreen MDSE information
        Object[] params = NPBL0010CommonLogic.setParamForMdsePopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}

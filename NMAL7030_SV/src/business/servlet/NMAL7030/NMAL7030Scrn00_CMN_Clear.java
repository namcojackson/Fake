/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7030;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7030.common.NMAL7030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   SRA             E.Inada         Create          QC#2174
 *</pre>
 */
public class NMAL7030Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        NMAL7030CommonLogic.setInputParam(scrnMsg, (Object[]) arg);

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

        NMAL7030CommonLogic.setHeaderProtect(scrnMsg);
        NMAL7030CommonLogic.setDetailProtect(scrnMsg);
    }
}

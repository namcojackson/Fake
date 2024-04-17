/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7140.NMAL7140CMsg;
//import business.servlet.NMAL7140.constant.NMAL7140Constant;

import business.servlet.NMAL7140.common.NMAL7140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/06   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NMAL7140Scrn00_OpenWin_PrmoQlfy_Mdl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7140BMsg scrnMsg = (NMAL7140BMsg) bMsg;
        Object[] argParam = NMAL7140CommonLogic.createArgumentNWAL1130_Qlfy(scrnMsg, getGlobalCompanyCode());

        if (argParam != null) {
            setArgForSubScreen(argParam);
        }

    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7140;

import static business.servlet.NMAL7140.constant.NMAL7140Constant.EVT_OPENWIN_PRMOQLFY_ITEM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7140.common.NMAL7140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/06   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NMAL7140Scrn00_OpenWin_PrmoQlfy_Item extends S21CommonHandler {

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
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVT_OPENWIN_PRMOQLFY_ITEM);

        Object[] argParam = NMAL7140CommonLogic.createArgumentNMWAL6800(scrnMsg, scrnMsg.xxPopPrm_0, scrnMsg.prcQlfyValTxt);

        if (argParam != null) {
            setArgForSubScreen(argParam);
        }

    }
}

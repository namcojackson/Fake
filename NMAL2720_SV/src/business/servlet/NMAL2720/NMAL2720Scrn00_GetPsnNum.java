/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2720.NMAL2720CMsg;
import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_GetPsnNum
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Scrn00_GetPsnNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, false);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxPsnNm_D1)) {
            scrnMsg.xxPsnNm_D1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.xxPsnNm_D1.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D1);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        NMAL2720CMsg bizMsg = new NMAL2720CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CMsg bizMsg  = (NMAL2720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.xxPsnNm_D1);
    }
}

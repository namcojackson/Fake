/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2020.common.NWAL2020CommonLogic;
import business.servlet.NWAL2020.common.NWAL2020TceppsRequestResponce;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2010Scrn00_OnBlur_CreateCrditCard
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020Scrn00_OnBlur_CreateCrditCard extends S21CommonHandler {

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

        NWAL2020BMsg scrnMsg = (NWAL2020BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxRqstNumSrchTxt_T)) {
            scrnMsg.setMessageInfo("ZZSM0001E", new String[] {scrnMsg.xxRqstNumSrchTxt_T.getValue() });
        }
        scrnMsg.xxRqstNumSrchTxt_T.clear();

        NWAL2020TceppsRequestResponce tr = new NWAL2020TceppsRequestResponce();
        tr.loadRequestParams(scrnMsg, ctx.getHttpServletRequest().getRequestURL().toString());

        NWAL2020CommonLogic.setCrCardProp(this, scrnMsg);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_OpenWin_FromUsrGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("OpenWin_FromUsrGrp");

        Object[] params = new Object[5];
        params[0] = scrnMsg.xxGrpFlg_F.getValue();
        params[1] = scrnMsg.wfUsrGrpNm_F.getValue();
        params[2] = "";
        params[3] = "OpenWin_FromUsrGrp";
        params[4] = scrnMsg.R;

        setArgForSubScreen(params);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.EVENT_NM_OPEN_WIN_ASLNAME;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/12   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NPAL1230Scrn00_OpenWin_ASLName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_Z, EVENT_NM_OPEN_WIN_ASLNAME);
        // Making of subscreen delivery information
        int selectNum = getButtonSelectNumber();
        Object[] params = NPAL1230CommonLogic.getParamNWAL1130ForASLName(scrnMsg, selectNum);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}

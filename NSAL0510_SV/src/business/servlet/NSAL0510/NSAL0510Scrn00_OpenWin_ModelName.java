/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.NSAL0480_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0510Scrn00_OpenWin_ModelName extends S21CommonHandler {

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

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        scrnMsg.t_MdlNm_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_P, scrnMsg.t_MdlNm_H);

        Object[] params = new Object[NSAL0480_PRM_LENGTH];
        params[0] = scrnMsg.t_MdlNm_P;

        setArgForSubScreen(params);
    }
}
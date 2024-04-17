/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9910;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * General Business Master Maintenance List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL9910Scrn00_SelectTblNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL9910BMsg scrnMsg = (NSAL9910BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[1];
        int i = 0;
        params[i++] = scrnMsg.A.no(selectIdx).physMaintTrgtTblNm_A;

        setArgForSubScreen(params);
    }
}

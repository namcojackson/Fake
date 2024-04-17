/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 *</pre>
 */
public class NSAL0020_NLCL0230 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//        String prevEventNm = scrnMsg.xxScrEventNm.getValue();
//        if (NSAL0020Scrn00_OpenWin_ReturnWH.class.getSimpleName().equals(prevEventNm)) {
//            scrnMsg.setFocusItem(scrnMsg.rtrnToWhCd);
//        } else if (NSAL0020Scrn00_OpenWin_ShipWH.class.getSimpleName().equals(prevEventNm)) {
//            scrnMsg.setFocusItem(scrnMsg.shipFromWhCd);
//        }
    }
}

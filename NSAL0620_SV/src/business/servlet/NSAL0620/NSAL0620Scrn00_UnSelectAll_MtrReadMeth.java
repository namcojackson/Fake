/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.CHK_BOX.XX_CHK_BOX_D;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0620Scrn00_UnSelectAll_MtrReadMeth extends S21CommonHandler {

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

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        ZYPTableUtil.unSelectAll(scrnMsg.D, XX_CHK_BOX_D.getChkBox());
        scrnMsg.setFocusItem(scrnMsg.D.no(0).xxChkBox_D);
    }
}

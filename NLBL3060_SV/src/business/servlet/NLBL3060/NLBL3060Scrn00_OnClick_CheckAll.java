/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3060;

import static business.servlet.NLBL3060.constant.NLBL3060Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/13   Hitachi         T.Kuroda        Create          QC#61166
 *</pre>
 */
public class NLBL3060Scrn00_OnClick_CheckAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        // Do check all
        ZYPTableUtil.selectAll(scrnMsg.A, CHECK_BOX_NAME, ZYPConstant.CHKBOX_ON_Y);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);

    }
}

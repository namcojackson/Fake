/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0650;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0650Scrn00_SelectAll extends S21CommonHandler {

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

        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;
        if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_AL.getValue())) {
            ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        } else {
            ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_A1");
        }
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0710.NSAL0710CMsg;
//import business.servlet.NSAL0710.constant.NSAL0710Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Update Read Methods
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0710Scrn00_SelectAllDtl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0710BMsg scrnMsg = (NSAL0710BMsg) bMsg;
        if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_BL.getValue())) {
            ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_A2", ZYPConstant.CHKBOX_ON_Y);
        } else {
            ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_A2");
        }

    }
}

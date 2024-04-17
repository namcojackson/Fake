/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/25   Hitachi         H.Watanabe      Create          QC#60398
 *</pre>
 */
public class NMAL6700Scrn00_OnChange_HardCopyReqFlgActive extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.F.no(i).dsPoReqFlg_F1.getValue())) {
                scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(false);
            } else {
                scrnMsg.F.no(i).hardCopyReqFlg_F1.setInputProtected(true);
                scrnMsg.F.no(i).hardCopyReqFlg_F1.clear();

            }
        }
    }
}

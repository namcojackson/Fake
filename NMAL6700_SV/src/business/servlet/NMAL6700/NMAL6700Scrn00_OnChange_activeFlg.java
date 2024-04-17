/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
//import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6700Scrn00_OnChange_activeFlg extends S21CommonHandler {

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

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_H1.getValue())) {
            scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(true);
            scrnMsg.dsAcctInacRsnCd_H3.clear();
            scrnMsg.effThruDt_H1.clear();
            scrnMsg.setFocusItem(scrnMsg.xxChkBox_H1);
        } else {
            scrnMsg.dsAcctInacRsnCd_H3.setInputProtected(false);
            scrnMsg.setFocusItem(scrnMsg.dsAcctInacRsnCd_H3);

        }

    }
}

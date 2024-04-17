/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0480.NSAL0480CMsg;
//import business.servlet.NSAL0480.constant.NSAL0480Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/21   Hitachi         K.Kasai         Create          QC#3586
 *</pre>
 */
public class NSAL0480_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_Mdse".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.t_ItemCd_H);

        } else if ("OpenWin_SplyMdse".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.mdseCd_H);
        }
    }
}

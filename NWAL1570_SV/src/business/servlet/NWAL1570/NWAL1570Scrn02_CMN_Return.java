/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1570.common.NWAL1570CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn02_CMN_Return
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2018/06/05   Fujitsu         H.Tomimatsu     Update          QC#24816
 *</pre>
 */
public class NWAL1570Scrn02_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        // 1. initialize GUI attribute.
        NWAL1570CommonLogic.initGuiAttrScrn00(this, scrnMsg);
        // 2. initialize GUI value.
        NWAL1570CommonLogic.initGuiValueScrnRslt(scrnMsg);
        // 3. set focus.
        scrnMsg.setFocusItem(scrnMsg.xxCpoOrdNumSrchTxt_H1);

        // 2018/06/05 S21_NA#24816 Add Start
        if (scrnMsg != null) {
            scrnMsg.xxComnColOrdTxt.clear();
        }
       // 2018/06/05 S21_NA#24816 Add End

    }
}

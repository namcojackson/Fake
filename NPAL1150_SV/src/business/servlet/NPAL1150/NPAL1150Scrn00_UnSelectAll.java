/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1150;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1150.NPAL1150CMsg;
//import business.servlet.NPAL1150.constant.NPAL1150Constant;

import business.blap.NPAL1150.NPAL1150CMsg;
import business.servlet.NPAL1150.common.NPAL1150CommonLogic;
import business.servlet.NPAL1150.constant.NPAL1150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NPAL1150 ASN Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/13/2013   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NPAL1150Scrn00_UnSelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;
        NPAL1150CMsg bizMsg = new NPAL1150CMsg();
        bizMsg.setBusinessID("NPAL1150");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1150BMsg scrnMsg = (NPAL1150BMsg) bMsg;
        NPAL1150CMsg bizMsg  = (NPAL1150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setValue(scrnMsg.xxDplyTab, NPAL1150Constant.UNDER_TAB_HEADER);

        NPAL1150CommonLogic.editProtectedScrnMsg(this, scrnMsg);
        NPAL1150CommonLogic.controlButton(this, scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.xxFromDt_A1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_A1);
        scrnMsg.putErrorScreen();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (NPAL1150CommonLogic.isSaveMode(scrnMsg, i)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(i).xxChkBox_B1);
                break;
            }
        }
    }
}

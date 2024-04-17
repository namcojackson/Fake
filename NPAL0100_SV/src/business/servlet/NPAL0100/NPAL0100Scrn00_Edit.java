/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2009   Fujitsu         I.Kondo         Create          N/A
 * 2011/10/27   CSAI            N.Sasaki        Update          362045
 *</pre>
 */
package business.servlet.NPAL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL0100.common.NPAL0100CommonLogic;
import business.servlet.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NPAL0100Scrn00_Edit extends S21CommonHandler implements NPAL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;
        int buttonNum = this.getButtonSelectNumber();

        // Control protect
        (((NPAL0100_ABMsg) scrnMsg.A.no(buttonNum)).serNum_A1).setInputProtected(false);
        this.setButtonProperties(BTN_NM.Edit.toString(), buttonNum, null, BTN_NM.Edit.toString(), 0, null);
        this.setButtonProperties(BTN_NM.Cancel.toString(), buttonNum, null, BTN_NM.Cancel.toString(), 1, null);

        // Set Focus
        scrnMsg.setFocusItem(((NPAL0100_ABMsg) scrnMsg.A.no(buttonNum)).serNum_A1);
        // Def# 362045
        NPAL0100CommonLogic.enableSubmitButton(this, scrnMsg, false);
    }

}

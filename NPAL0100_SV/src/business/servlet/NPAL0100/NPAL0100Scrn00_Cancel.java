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
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL0100.NPAL0100CMsg;
import business.servlet.NPAL0100.common.NPAL0100CommonLogic;
import business.servlet.NPAL0100.constant.NPAL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NPAL0100Scrn00_Cancel extends S21CommonHandler implements NPAL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;

        NPAL0100CMsg bizMsg = NPAL0100CommonLogic.setRequestDataToBizMsg();
        
        scrnMsg.xxRowNum.setValue(this.getButtonSelectNumber());
        
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL0100BMsg scrnMsg = (NPAL0100BMsg) bMsg;
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int buttonNum = this.getButtonSelectNumber();

        // Control protect
        (((NPAL0100_ABMsg) scrnMsg.A.no(buttonNum)).serNum_A1).setInputProtected(true);
        this.setButtonProperties(BTN_NM.Edit.toString(), buttonNum, null, BTN_NM.Edit.toString(), 1, null);
        this.setButtonProperties(BTN_NM.Cancel.toString(), buttonNum, null, BTN_NM.Cancel.toString(), 0, null);
        // Def# 362045
        NPAL0100CommonLogic.enableSubmitButton(this, scrnMsg, false);
    }

}
/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0300;

import static business.servlet.NSBL0300.constant.NSBL0300Constant.SCR_ID_00;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0300.common.NSBL0300CommonLogic;
import business.servlet.NSBL0300.constant.NSBL0300Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0300Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NSBL0300Constant.NSAM0320E, new String[]{"Insert line", String.valueOf(scrnMsg.A.length())});
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;

        int addRowIndex = scrnMsg.A.getValidCount();
        setValue(scrnMsg.A.no(addRowIndex).effFromDt_A, scrnMsg.slsDt);
        scrnMsg.A.setValidCount(addRowIndex + 1);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
        NSBL0300CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSBL0300CommonLogic.activateButtons(this, scrnMsg);
        NSBL0300CommonLogic.activateScreenItems(this, scrnMsg);
        NSBL0300CommonLogic.alternateTableRowColor(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).xxChkBox_A);

    }
}

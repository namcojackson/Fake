/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0300;

import static business.servlet.NSBL0300.constant.NSBL0300Constant.*;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0300.common.NSBL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0300Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            scrnMsg.setMessageInfo(NSAM0015E);
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

        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                deleteRows.add(i);
            }
        }

        ZYPTableUtil.deleteRows(scrnMsg.A, deleteRows);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
        NSBL0300CommonLogic.activateButtons(this, scrnMsg);
        NSBL0300CommonLogic.activateScreenItems(this, scrnMsg);
        NSBL0300CommonLogic.alternateTableRowColor(scrnMsg);
        NSBL0300CommonLogic.focusItem(scrnMsg);

    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2630;

import java.util.List;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2630.common.NMAL2630CommonLogic;
import business.servlet.NMAL2630.constant.NMAL2630Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL2630Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        NMAL2630CommonLogic.checkInput(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B1", ZYPConstant.FLG_ON_Y);
        if (selectedRows == null || selectedRows.isEmpty()) {
            scrnMsg.setMessageInfo("NMAM8054E");
            return;
        }
        ZYPTableUtil.deleteRows(scrnMsg.B, selectedRows);

        S21TableColorController tblColor = new S21TableColorController(NMAL2630Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        S21SortColumnIMGController.clearIMG(NMAL2630Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (scrnMsg.B.getValidCount() < scrnMsg.B.length()) {
            setButtonEnabled(NMAL2630Constant.BTN_INSERT, true);
        }
        if (scrnMsg.B.getValidCount() <= 0) {
            setButtonEnabled(NMAL2630Constant.BTN_DELETE, false);
        }
    }
}

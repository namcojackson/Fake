/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.SCRN_ID_00;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.DPLY_TAB_WH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1740.common.NWAL1740CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1740Scrn00_TAB_WH
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740Scrn00_TAB_WH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CommonLogic.checkMdseTpCode(scrnMsg);
        NWAL1740CommonLogic.checkLineCatgNm(scrnMsg);
        NWAL1740CommonLogic.checkWH(scrnMsg);
        NWAL1740CommonLogic.addCheckItemMdseTpRules(scrnMsg);
        NWAL1740CommonLogic.addCheckItemLineCatgRules(scrnMsg);
        NWAL1740CommonLogic.addCheckItemWHRules(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        scrnMsg.xxDplyTab.setValue(DPLY_TAB_WH);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.clearRowsBG("B", scrnMsg.B);
        tblColor.setAlternateRowsBG("C", scrnMsg.C, 1);
    }
}

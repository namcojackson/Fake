/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0440;

import static business.servlet.NSBL0440.common.NSBL0440CommonLogic.addCheckItem;
import static business.servlet.NSBL0440.constant.NSBL0440Constant.BIZ_ID;
import static business.servlet.NSBL0440.constant.NSBL0440Constant.EZD_FUNC_CD_UPD;
import static business.servlet.NSBL0440.constant.NSBL0440Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0440.NSBL0440CMsg;
import business.servlet.NSBL0440.common.NSBL0440CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/07/12   Hitachi         O.Okuma         Create          QC#11685
 *</pre>
 */
public class NSBL0440Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;

        NSBL0440CMsg bizMsg = new NSBL0440CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
     }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0440BMsg scrnMsg = (NSBL0440BMsg) bMsg;
        NSBL0440CMsg bizMsg  = (NSBL0440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0440CommonLogic.controlScreenFields(scrnMsg);

        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.mdseCd_F);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}

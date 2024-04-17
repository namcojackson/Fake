/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270;

import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;
import static business.servlet.NFAL0270.common.NFAL0270CommonLogic.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0270.NFAL0270CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
        addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;

        NFAL0270CMsg bizMsg = new NFAL0270CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
        NFAL0270CMsg bizMsg  = (NFAL0270CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        initialControlScreen(this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.mdlGrpId_H);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}

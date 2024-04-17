/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.BUSINESS_ID;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.FUNCTION_SEARCH;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0260.NSBL0260CMsg;
import business.servlet.NSBL0260.common.NSBL0260CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSBL0260Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mdlNm_C);
        scrnMsg.addCheckItem(scrnMsg.serNum_C);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_ON);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_OF);
        NSBL0260CommonLogic.checkCriteria(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;

        NSBL0260CMsg bizMsg = new NSBL0260CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0260BMsg scrnMsg = (NSBL0260BMsg) bMsg;
        NSBL0260CMsg bizMsg = (NSBL0260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSBL0260CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).mdlNm_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.mdlNm_C);
        }

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}

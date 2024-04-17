/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1290;

import static business.servlet.NSAL1290.constant.NSAL1290Constant.BUSINESS_ID;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL1290.constant.NSAL1290Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1290.NSAL1290CMsg;
import business.servlet.NSAL1290.common.NSAL1290CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL1290Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;
        NSAL1290CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;

        NSAL1290CMsg bizMsg = new NSAL1290CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1290BMsg scrnMsg = (NSAL1290BMsg) bMsg;
        NSAL1290CMsg bizMsg  = (NSAL1290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1290CommonLogic.initialControlScreen(this, scrnMsg);
        NSAL1290CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}

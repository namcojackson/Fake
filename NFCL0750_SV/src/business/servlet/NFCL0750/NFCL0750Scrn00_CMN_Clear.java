/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0750;

import static business.servlet.NFCL0750.constant.NFCL0750Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0750.NFCL0750CMsg;
import business.servlet.NFCL0750.common.NFCL0750CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NFCL0750Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        NFCL0750CMsg bizMsg = new NFCL0750CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        NFCL0750CMsg bizMsg = (NFCL0750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NFCL0750CommonLogic.initialControlScreen(this, scrnMsg);
    }
}

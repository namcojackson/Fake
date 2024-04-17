/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.BUSINESS_ID;
import static business.servlet.NSAL1200.constant.NSAL1200Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL1200.constant.NSAL1200Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1200.NSAL1200CMsg;
import business.servlet.NSAL1200.common.NSAL1200CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/20   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL1200Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;

        NSAL1200CMsg bizMsg = new NSAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
        NSAL1200CMsg bizMsg = (NSAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSAL1200CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

    }
}

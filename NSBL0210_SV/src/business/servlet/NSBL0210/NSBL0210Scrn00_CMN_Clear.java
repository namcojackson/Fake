/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import static business.servlet.NSBL0210.constant.NSBL0210Constant.BUSINESS_ID;
import static business.servlet.NSBL0210.constant.NSBL0210Constant.FUNCTION_SEARCH;
import static business.servlet.NSBL0210.constant.NSBL0210Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0210.NSBL0210CMsg;
import business.servlet.NSBL0210.common.NSBL0210CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/05/18   Hitachi         Y.Takeno        Update          NA#CSA
 * 2017/01/24   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0210Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        NSBL0210CMsg bizMsg = new NSBL0210CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
        NSBL0210CMsg bizMsg = (NSBL0210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0210CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }
}

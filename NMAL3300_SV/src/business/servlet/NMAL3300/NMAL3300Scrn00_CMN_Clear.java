/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3300;

import static business.servlet.NMAL3300.constant.NMAL3300Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL3300.NMAL3300CMsg;
import business.servlet.NMAL3300.common.NMAL3300CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        NMAL3300CMsg bizMsg = new NMAL3300CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        NMAL3300CMsg bizMsg  = (NMAL3300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL3300CommonLogic.protectScrn(this, scrnMsg);
        NMAL3300CommonLogic.setBgColor(scrnMsg);
        // Mod Start 2018/11/12 QC#28683
        //NMAL3300CommonLogic.clearFilter(scrnMsg);
        S21SortColumnIMGController.clearIMG(SCREEN_ID_00, scrnMsg, scrnMsg.D.no(0).getBaseContents());
        // Mod End 2018/11/12 QC#28683


    }
}

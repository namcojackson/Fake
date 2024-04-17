/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6790;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6790.NMAL6790CMsg;
import business.servlet.NMAL6790.common.NMAL6790CommonLogic;
import business.servlet.NMAL6790.constant.NMAL6790Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL6790Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;

        NMAL6790CMsg bizMsg = new NMAL6790CMsg();
        bizMsg.setBusinessID(NMAL6790Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NMAL6790Constant.FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6790BMsg scrnMsg = (NMAL6790BMsg) bMsg;
        NMAL6790CMsg bizMsg = (NMAL6790CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6790CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL6790Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        S21SortColumnIMGController.clearIMG(NMAL6790Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        tblColor.setAlternateRowsBG("B", scrnMsg.B);
        S21SortColumnIMGController.clearIMG(NMAL6790Constant.SCREEN_ID, scrnMsg, scrnMsg.B.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.ctacTpCd_H1);

    }
}

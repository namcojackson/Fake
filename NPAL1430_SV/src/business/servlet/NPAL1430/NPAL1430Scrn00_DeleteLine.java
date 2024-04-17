/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430;

import static business.servlet.NPAL1430.constant.NPAL1430Constant.BIZ_APP_ID;
import static business.servlet.NPAL1430.constant.NPAL1430Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1430.NPAL1430CMsg;

import business.servlet.NPAL1430.common.NPAL1430CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : Button Action to Submit
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 *</pre>
 */
public class NPAL1430Scrn00_DeleteLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;
        NPAL1430CommonLogic.checkSelectedLine(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;

        NPAL1430CMsg bizMsg = new NPAL1430CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;
        NPAL1430CMsg bizMsg = (NPAL1430CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1430CommonLogic.setTableScreen(scrnMsg, this, funcList, getLastGuard());
        scrnMsg.putErrorScreen();

        // set focus
        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).mdseCd_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.t_MdlNm);
        }
    }
}

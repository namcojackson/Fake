/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2400.NWAL2400CMsg;
import business.servlet.NWAL2400.common.NWAL2400CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWAL2400 ASL Entry
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NWAL2400Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        // checks search field.
        scrnMsg.addCheckItem(scrnMsg.rtlDlrCd);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        // set values to items of pagination for next page pagination
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NWAL2400CMsg bizMsg = new NWAL2400CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;
        NWAL2400CMsg bizMsg = (NWAL2400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2400CommonLogic.ctrlDetailButton(this, scrnMsg);
        NWAL2400CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.rtlDlrCd);
    }
}

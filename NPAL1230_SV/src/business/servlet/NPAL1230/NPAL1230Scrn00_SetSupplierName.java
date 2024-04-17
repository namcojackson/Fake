/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.BIZ_APP_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_PRNT_VND_NM;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.SCRN_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.ZZZM9007E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1230.NPAL1230CMsg;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1230Scrn00_SetSupplierName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
            scrnMsg.prntVndCd.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_NM_PRNT_VND_NM });
        }
//
//        // clear html attribute
//        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        NPAL1230CMsg bizMsg = new NPAL1230CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;
        NPAL1230CMsg bizMsg = (NPAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        
        NPAL1230CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.prntVndCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.prntVndCd);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        }
    }
}

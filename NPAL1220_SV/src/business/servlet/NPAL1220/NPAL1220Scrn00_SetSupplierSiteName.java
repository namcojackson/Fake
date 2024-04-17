/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220;

import static business.servlet.NPAL1220.constant.NPAL1220Constant.BIZ_APP_ID;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_VND_CD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.SCRN_ID;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.ZZZM9007E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1220.NPAL1220CMsg;
import business.servlet.NPAL1220.common.NPAL1220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2018   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NPAL1220Scrn00_SetSupplierSiteName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.vndCd)) {
            scrnMsg.vndCd.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_NM_VND_CD });
        }

        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

        NPAL1220CMsg bizMsg = new NPAL1220CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;
        NPAL1220CMsg bizMsg = (NPAL1220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NPAL1220CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.vndCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.vndCd);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.vndCd);
        }
    }
}

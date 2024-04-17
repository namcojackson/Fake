/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.SCRN_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.ZZZM9007E;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.DISPLAY_NM_MDSE_CD;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1520.NPAL1520CMsg;
import business.servlet.NPAL1520.common.NPAL1520CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Set Item Description
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1520Scrn00_SetItemDescription extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            scrnMsg.mdseDescShortTxt.clear();
            scrnMsg.mdseCd.setErrorInfo(1, ZZZM9007E, new String[] {DISPLAY_NM_MDSE_CD });
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        NPAL1520CMsg bizMsg = new NPAL1520CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;
        NPAL1520CMsg bizMsg  = (NPAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1520CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.mdseCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.putErrorScreen();
        } else {
            // set focus
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
        }
    }
}

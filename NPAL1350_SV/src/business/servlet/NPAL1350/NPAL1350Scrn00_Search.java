/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1350;

import static business.servlet.NPAL1350.constant.NPAL1350Constant.BIZ_APP_ID;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1350.constant.NPAL1350Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1350.NPAL1350CMsg;
import business.servlet.NPAL1350.common.NPAL1350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1350 Kitting WO Search
 * Function Name : Search
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1350Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.wrkOrdDt_FM);
        scrnMsg.addCheckItem(scrnMsg.wrkOrdDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_FM);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_TO);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1350CMsg bizMsg = new NPAL1350CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1350BMsg scrnMsg = (NPAL1350BMsg) bMsg;
        NPAL1350CMsg bizMsg  = (NPAL1350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1350CommonLogic.setTableScreen(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.wrkOrdNum);

    }
}
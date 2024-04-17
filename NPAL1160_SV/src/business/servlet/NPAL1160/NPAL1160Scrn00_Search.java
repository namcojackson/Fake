/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NPAL1160 PO Approval Setting Screen
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/24   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/08   CITS            R.Shimamoto     Update          V0.1
 *</pre>
 */
public class NPAL1160Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        // STR 2016/03/08 R.Shimamoto Delete
        // NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        // scrnMsg.addCheckItem(scrnMsg.prchGrpCd);
        // scrnMsg.putErrorScreen();
        // END 2016/03/08 R.Shimamoto Delete
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        // STR 2016/03/08 R.Shimamoto Delete
        // NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        //
        // NPAL1160CMsg bizMsg = new NPAL1160CMsg();
        // bizMsg.setBusinessID(BUSINESS_ID);
        // bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;
        // END 2016/03/08 R.Shimamoto Delete
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // no process.
        // STR 2016/03/08 R.Shimamoto Delete
        // NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        // NPAL1160CMsg bizMsg = (NPAL1160CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // scrnMsg.putErrorScreen();
        //
        // NPAL1160CommonLogic.setScreenControl(EVENT_ID_SEARCH, this,
        // scrnMsg, bizMsg.getUserID());
        // // Search Set Focus
        // if
        // (NPAL1160CommonLogic.hasUpdateAuthority(bizMsg.getUserID()))
        // {
        // if (scrnMsg.getMessageType() ==
        // EZDMessageInfo.MSGTYPE_ERROR) {
        // scrnMsg.setFocusItem(scrnMsg.prchGrpCd);
        // } else {
        // scrnMsg.setFocusItem(scrnMsg.A.no(0).xxDiscPrcAmt_A0);
        // }
        // } else {
        // scrnMsg.setFocusItem(scrnMsg.prchGrpCd);
        // }
        //
        // if (scrnMsg.getMessageType() ==
        // EZDMessageInfo.MSGTYPE_ERROR) {
        // throw new EZDFieldErrorException();
        // }
        // END 2016/03/08 R.Shimamoto Delete
    }
}

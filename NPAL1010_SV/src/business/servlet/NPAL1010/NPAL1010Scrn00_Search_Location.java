/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.BUSINESS_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.FUNC_SRCH_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1010.NPAL1010CMsg;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 *</pre>
 */
public class NPAL1010Scrn00_Search_Location extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        NPAL1010CommonLogic.addCheckItemSearchCondition(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);

        NPAL1010CMsg bizMsg = new NPAL1010CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH_ID);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        NPAL1010CMsg bizMsg = (NPAL1010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Catch Column Error
        scrnMsg.putErrorScreen();

        // Catch Screen Error
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {

            throw new EZDFieldErrorException();
        }

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        NPAL1010CommonLogic.setListItemProtected(scrnMsg);
        NPAL1010CommonLogic.setScreenList(scrnMsg);
    }
}

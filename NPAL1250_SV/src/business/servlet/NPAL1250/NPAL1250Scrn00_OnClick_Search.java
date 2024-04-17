/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1250;

import static business.servlet.NPAL1250.constant.NPAL1250Constant.SCRN_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1250.NPAL1250CMsg;
import business.servlet.NPAL1250.common.NPAL1250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPAL1250Scrn00_OnClick_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1250BMsg scrnMsg = (NPAL1250BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);

        return NPAL1250CommonLogic.copyScrnMsgToBizMsgForSearch((NPAL1250BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1250BMsg scrnMsg = (NPAL1250BMsg) bMsg;
        NPAL1250CMsg bizMsg = (NPAL1250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NPAL1250CommonLogic.setInputProtectedForDetail(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            NPAL1250CommonLogic.editButtonProperties(this);
        }
    }
}

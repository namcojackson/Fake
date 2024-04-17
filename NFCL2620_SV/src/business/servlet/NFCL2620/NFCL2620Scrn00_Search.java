/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2620;

import static business.servlet.NFCL2620.constant.NFCL2620Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2620.NFCL2620CMsg;
import business.servlet.NFCL2620.common.NFCL2620CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/08/09   Fujitsu         C.Tanaka        Update          QC#5521
 *</pre>
 */
public class NFCL2620Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;
        NFCL2620CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;

        NFCL2620CMsg bizMsg = NFCL2620CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;
        NFCL2620CMsg bizMsg = (NFCL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL2620CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        } else {
            NFCL2620CommonLogic.setListInactive(this, scrnMsg);
            NFCL2620CommonLogic.initAppFracDigit(scrnMsg);
            NFCL2620CommonLogic.setFocusRule(scrnMsg);
            // QC#5521 ADD Start
            S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
            // QC#5521 ADD End
        }
    }
}

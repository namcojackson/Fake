/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0040;

import static business.servlet.NWWL0040.constant.NWWL0040Constant.BIZ_ID;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.MESSAGE_KIND_WARNING;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.NZZM0002I;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0040.NWWL0040CMsg;
import business.servlet.NWWL0040.common.NWWL0040CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0040Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;

        NWWL0040CommonLogic.checkHdr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;

        NWWL0040CMsg bizMsg = new NWWL0040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;
        NWWL0040CMsg bizMsg = (NWWL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        } else if (!MESSAGE_KIND_WARNING.equals(bizMsg.getMessageKind())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

        NWWL0040CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWWL0040CommonLogic.controllScreen(scrnMsg);

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.ntfyDistListNm);
    }
}

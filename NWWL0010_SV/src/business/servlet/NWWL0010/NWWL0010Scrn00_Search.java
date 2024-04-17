/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0010;

import static business.servlet.NWWL0010.constant.NWWL0010Constant.BIZ_ID;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWWL0010.constant.NWWL0010Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0010.NWWL0010CMsg;
import business.servlet.NWWL0010.common.NWWL0010CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0010Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0010Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0010BMsg scrnMsg = (NWWL0010BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.ntfyHdrNm);
        scrnMsg.addCheckItem(scrnMsg.ntfyHdrDescTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0010BMsg scrnMsg = (NWWL0010BMsg) bMsg;

        NWWL0010CMsg bizMsg = new NWWL0010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0010BMsg scrnMsg = (NWWL0010BMsg) bMsg;
        NWWL0010CMsg bizMsg = (NWWL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NWWL0010CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWWL0010CommonLogic.controlListItem(scrnMsg);

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.ntfyHdrNm);
    }
}

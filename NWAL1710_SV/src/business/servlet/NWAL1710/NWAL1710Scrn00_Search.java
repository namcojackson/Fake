/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1710;

import static business.servlet.NWAL1710.constant.NWAL1710Constant.BIZ_ID;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1710.NWAL1710CMsg;
import business.servlet.NWAL1710.common.NWAL1710CommonLogic;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1710Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1710Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CommonLogic.addCheckItemHeader(scrnMsg);
        NWAL1710CommonLogic.checkDateFromTo(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg = new NWAL1710CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg  = (NWAL1710CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A, 1);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A, 1);
        scrnMsg.setFocusItem(scrnMsg.dsOrdCatgNm);
    }
}

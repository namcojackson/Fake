/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

// import static
// business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_TAB_Header
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_TAB_Header extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        scrnMsg.xxDplyTab.setValue(TAB_NAME_HEADER);

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NWWL0020CommonLogic.clearRowsBG(scrnMsg, scrnMsg.A, "A");
        NWWL0020CommonLogic.clearRowsBG(scrnMsg, scrnMsg.B, "B");
        NWWL0020CommonLogic.setControlFieldsHdrNm(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.ntfyHdrNm_H0);
    }
}

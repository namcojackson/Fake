/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_SRCH;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_TAB_Trx
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/10/07   Hitachi         J.Kim           Update          QC#5521
 *</pre>
 */
public class NLEL0060Scrn00_TAB_Trx extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0060CommonLogic.setTableBGColor(scrnMsg);
        NLEL0060CommonLogic.initCmnBtnProp(this);

        // START 2016/10/07 J.Kim [QC#13372,ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.C.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.E.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.F.no(0).getBaseContents());
        // END 2016/10/07 J.Kim [QC#13372,ADD]
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.constant.NSBL0410Constant.*;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0410.NSBL0410CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 * 2016/03/01   Hitachi         M.Gotou         Update          QC#4709
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 * 2018/06/01   Hitachi         U.Kim           Update          QC#22393
 * 2018/07/10   Fujitsu         T.Ogura         Update          QC#27080
 *</pre>
 */
public class NSBL0410Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        addCheckItem_submit(scrnMsg);

        checkDate(scrnMsg);
        if (scrnMsg.xxEndDplyTmTxt.getErrorCode() == 1) {
            scrnMsg.addCheckItem(scrnMsg.xxEndDplyTmTxt);
            scrnMsg.xxEndDplyTmTxt.setErrorInfo(1, scrnMsg.getErrorInfo("xxEndDplyTmTxt").getCode());
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        NSBL0410CMsg bizMsg = new NSBL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        NSBL0410CMsg bizMsg  = (NSBL0410CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        addCheckItem_submit(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            // START 2018/07/10 T.Ogura [QC#27080,MOD]
//            return;
            throw new EZDFieldErrorException();
            // END   2018/07/10 T.Ogura [QC#27080,MOD]
        }
        initialControlScreen(this, scrnMsg);
        // START 2018/06/01 U.Kim [QC#, ADD]
        controlScreenFieldsForINIT(scrnMsg);
        // END 2018/06/01 U.Kim [QC#, ADD]
        scrnMsg.setFocusItem(scrnMsg.svcModCmntTxt);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.BIZ_ID;
import static business.servlet.NSAL0510.constant.NSAL0510Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0510.NSAL0510CMsg;
import business.servlet.NSAL0510.common.NSAL0510CommonLogic;
import business.servlet.NSAL0510.constant.NSAL0510Constant.FUNC;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0510Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        NSAL0510CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NSAL0510CMsg bizMsg = new NSAL0510CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;
        NSAL0510CMsg bizMsg  = (NSAL0510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if (scrnMsg.A.getValidCount() > 0) {
            NSAL0510CommonLogic.protectFields(this, scrnMsg);
            NSAL0510CommonLogic.setRowColors(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }
}

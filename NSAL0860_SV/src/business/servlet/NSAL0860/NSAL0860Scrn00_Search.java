/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860;

import static business.servlet.NSAL0860.constant.NSAL0860Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0860.NSAL0860CMsg;
import business.servlet.NSAL0860.common.NSAL0860CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2016   Hitachi         Y.Osawa         Create          N/A
 * 2016/06/17   Hitachi         O.Okuma         Update          QC#9951
 *</pre>
 */
public class NSAL0860Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.curLocAcctNum);
        scrnMsg.addCheckItem(scrnMsg.iwrCondCd);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;

        NSAL0860CMsg bizMsg = new NSAL0860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        NSAL0860CMsg bizMsg = (NSAL0860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.curLocAcctNum);
        scrnMsg.addCheckItem(scrnMsg.iwrCondCd);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);

        scrnMsg.putErrorScreen();

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0860CommonLogic.screenControlProcess(this, scrnMsg, functionList);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}

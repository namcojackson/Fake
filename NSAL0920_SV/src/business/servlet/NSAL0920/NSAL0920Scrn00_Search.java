/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import static business.servlet.NSAL0920.constant.NSAL0920Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0920.NSAL0920CMsg;
import business.servlet.NSAL0920.common.NSAL0920CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/10/18   Hitachi         N.Arai          Update          QC#15216
 *</pre>
 */
public class NSAL0920Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.locNum);
        scrnMsg.addCheckItem(scrnMsg.locNm);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt);
// START 2016/10/18 N.Arai [QC#15216, MOD]
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd_H3);
// END 2016/10/18 N.Arai [QC#15216, MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowTotNum.clear();

        NSAL0920CMsg bizMsg = new NSAL0920CMsg();
        bizMsg.setBusinessID("NSAL0920");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CMsg bizMsg  = (NSAL0920CMsg) cMsg;
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0920CommonLogic.searchControlScreen(this, scrnMsg);

    }
}

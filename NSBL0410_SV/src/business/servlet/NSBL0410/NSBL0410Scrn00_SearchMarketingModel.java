/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410;

import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.addCheckItem;
import static business.servlet.NSBL0410.common.NSBL0410CommonLogic.initialControlScreen_Add;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BIZ_ID;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0410.NSBL0410CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/24   Hitachi         U.Kim           Create          QC#22393
 *</pre>
 */
public class NSBL0410Scrn00_SearchMarketingModel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;

        NSBL0410CMsg bizMsg = new NSBL0410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0410BMsg scrnMsg = (NSBL0410BMsg) bMsg;
        NSBL0410CMsg bizMsg = (NSBL0410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen_Add(this, scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.mktMdlCd);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.mktMdlCd);
    }
}

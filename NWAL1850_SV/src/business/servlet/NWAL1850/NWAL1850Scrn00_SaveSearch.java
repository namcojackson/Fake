/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850;

import static business.servlet.NWAL1850.constant.NWAL1850Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1850.NWAL1850CMsg;
import business.servlet.NWAL1850.common.NWAL1850CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1850Scrn00_SaveSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1850Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        NWAL1850CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
        NWAL1850CommonLogic.checkInputValue(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;

        NWAL1850CMsg bizMsg = new NWAL1850CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        NWAL1850CMsg bizMsg = (NWAL1850CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }
}

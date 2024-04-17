/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_Search_Carrier
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1700Scrn00_Search_Carrier extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.defCarrSvcLvlCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg  = (NWAL1700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.defCarrSvcLvlCd);
        scrnMsg.putErrorScreen();

    }
}

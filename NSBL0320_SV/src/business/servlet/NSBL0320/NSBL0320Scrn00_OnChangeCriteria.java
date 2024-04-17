/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0320;

import static business.servlet.NSBL0320.constant.NSBL0320Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSBL0320.NSBL0320CMsg;
//import business.servlet.NSBL0320.constant.NSBL0320Constant;

import business.blap.NSBL0320.NSBL0320CMsg;
import business.servlet.NSBL0320.common.NSBL0320CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         Y.Osawa         Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          CSA QC#5765
 *</pre>
 */
public class NSBL0320Scrn00_OnChangeCriteria extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0320BMsg scrnMsg = (NSBL0320BMsg) bMsg;

        NSBL0320CMsg bizMsg = new NSBL0320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        NSBL0320CommonLogic.initialControlScreen(this, scrnMsg);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0320BMsg scrnMsg = (NSBL0320BMsg) bMsg;
        NSBL0320CMsg bizMsg  = (NSBL0320CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0320CommonLogic.initialControlScreen(this, scrnMsg);
        // add start 2016/03/22 CSA Defect#5765
        NSBL0320CommonLogic.selectAll(scrnMsg);
        // add end 2016/03/22 CSA Defect#5765

    }
}

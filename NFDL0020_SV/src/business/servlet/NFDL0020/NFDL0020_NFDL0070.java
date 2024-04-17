/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0020.NFDL0020CMsg;
//import business.servlet.NFDL0020.constant.NFDL0020Constant;

import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CSAI            K.Lee           Create          Initial
 * 2016/05/26   Fujitsu         S.Fujita        Update          QC#8339
 * 2017/01/18   Fujitsu         T.Murai         Update          QC#16809
 *</pre>
 */
public class NFDL0020_NFDL0070 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/05/26 S.Fujita [QC#8339,ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END   2016/05/26 S.Fujita [QC#8339,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/05/26 S.Fujita [QC#8339,ADD]
        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg  = (NFDL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2016/05/26 S.Fujita [QC#8339,ADD]
        NFDL0020CommonLogic.initialize(this, scrnMsg); // ADD 2017/01/18 [QC#16809]
    }
}

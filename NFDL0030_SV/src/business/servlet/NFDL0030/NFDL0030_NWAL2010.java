/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.servlet.NFDL0030.common.NFDL0030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9829
 * 2016/08/31   Hitachi         K.Kojima        Update          QC#10203
 *</pre>
 */
public class NFDL0030_NWAL2010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg = new NFDL0030CMsg();

        // START 2016/06/14 T.Tsuchida [QC#9829,MOD]
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }
        // END 2016/06/14 T.Tsuchida [QC#9829,MOD]
        bizMsg.setBusinessID("NFDL0030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg  = (NFDL0030CMsg) cMsg;

        // START 2016/06/14 T.Tsuchida [QC#9829,MOD]
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        // END 2016/06/14 T.Tsuchida [QC#9829,MOD]
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/08/31 K.Kojima [QC#10203,ADD]
        NFDL0030CommonLogic.calculationTotalAmountForModeInvoice(scrnMsg);
        // END 2016/08/31 K.Kojima [QC#10203,ADD]

    }
}

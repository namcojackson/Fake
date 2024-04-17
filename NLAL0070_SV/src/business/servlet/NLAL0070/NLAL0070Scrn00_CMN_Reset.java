/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/15/2010   Fujitsu         A.Akabane       Create          N/A
 * 08/02/2010   Fujitsu         S.Yoshida       Update          79
 *</pre>
 */
package business.servlet.NLAL0070;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL0070.NLAL0070CMsg;
import business.servlet.NLAL0070.common.NLAL0070CommonLogic;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NLAL0070Scrn00_CMN_Reset extends S21CommonHandler implements NLAL0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        NLAL0070CMsg bizMsg = new NLAL0070CMsg();
        bizMsg.setBusinessID("NLAL0070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL0070CMsg bizMsg  = (NLAL0070CMsg) cMsg;

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        } else {
            // no process
        }

        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab_BK.setValue(TB_SCHEDULE);
        scrnMsg.xxRadioBtn_A1.setValue(RADIO_MDSE);
        NLAL0070CommonLogic.cntrlDispScrnItem(this, scrnMsg);
    }

}

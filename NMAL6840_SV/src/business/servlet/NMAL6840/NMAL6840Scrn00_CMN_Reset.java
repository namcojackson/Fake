/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6840.NMAL6840CMsg;
import business.servlet.NMAL6840.common.NMAL6840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <p>
 * Business ID : NMAL6840 Sub WH Setup<br>
 * Function Name : The business process for Reset<br>
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * </pre>
 */
public class NMAL6840Scrn00_CMN_Reset extends S21CommonHandler {

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #checkInput(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #setRequestData(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg)
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return NMAL6840CommonLogic.copyScrnMsgToBizMsgForSearch((NMAL6840BMsg) bMsg);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.canon.cusa.s21.framework.online.servlet.S21CommonHandler
     * #doProcess(parts.servletcommon.EZDApplicationContext,
     * parts.common.EZDBMsg, parts.common.EZDCMsg)
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;
        EZDMsg.copy((NMAL6840CMsg) cMsg, null, scrnMsg, null);

        NMAL6840CommonLogic.doProcessOfInit(scrnMsg, this);
    }
}

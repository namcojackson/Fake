/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/04/2010   Fujitsu         FAP)D.Kato      Create          N/A
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 *</pre>
 */
package business.servlet.NFCL5050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5050Scrn00_CalcGrossAmount.
 */
public class NFCL5050Scrn00_CalcGrossAmount extends S21CommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050CMsg bizMsg = null;

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        bizMsg = NFCL5050CommonLogic.setRequestData_NFCL5050Scrn00_PageNext(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}

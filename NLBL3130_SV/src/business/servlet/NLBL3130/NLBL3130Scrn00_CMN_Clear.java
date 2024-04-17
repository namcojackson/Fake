/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3130.NLBL3130CMsg;
import business.servlet.NLBL3130.common.NLBL3130CommonLogic;
import business.servlet.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLBL3130Scrn00_CMN_Clear extends S21CommonHandler implements NLBL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        NLBL3130CMsg bizMsg = new NLBL3130CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        NLBL3130CMsg bizMsg = (NLBL3130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3130CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
    }
}

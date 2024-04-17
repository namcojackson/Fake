/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3100.NLBL3100CMsg;
import business.servlet.NLBL3100.common.NLBL3100CommonLogic;
import business.servlet.NLBL3100.constant.NLBL3100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLBL3100Scrn00_CMN_Clear extends S21CommonHandler implements NLBL3100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3100BMsg scrnMsg = (NLBL3100BMsg) bMsg;

        NLBL3100CMsg bizMsg = new NLBL3100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3100BMsg scrnMsg = (NLBL3100BMsg) bMsg;
        NLBL3100CMsg bizMsg  = (NLBL3100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3100CommonLogic.clearScreenMsg(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
    }
}

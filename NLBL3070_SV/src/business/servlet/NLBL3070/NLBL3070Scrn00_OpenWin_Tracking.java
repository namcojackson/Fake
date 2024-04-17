/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/07/2018   CITS            T.Tokutomi      Create          QC#21913
 *</pre>
 */
public class NLBL3070Scrn00_OpenWin_Tracking extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = new NLBL3070CMsg();
        bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxNum.setValue(getButtonSelectNumber());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = (NLBL3070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = scrnMsg.xxNum.getValueInt();
        Object[] params = new Object[3];

        params[0] = scrnMsg.B.no(index).soNum_B1.getValue();
        params[1] = scrnMsg.B.no(index).soLineOpenFlg_B1.getValue();
        params[2] = scrnMsg.T;

        setArgForSubScreen(params);
    }
}

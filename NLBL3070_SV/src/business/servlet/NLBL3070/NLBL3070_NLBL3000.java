/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.common.NLBL3070CommonLogic;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Fujitsu         Y.Taoka         Create          N/A
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 11/03/2016   CITS            T.Hakodate      Update          QC#15795
 *</pre>
 */
public class NLBL3070_NLBL3000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        int index = getButtonSelectNumber();

        if (NLBL3070CommonLogic.isUpdateUser(getUserProfileService()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(index).soLineOpenFlg_B1.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(index).shipFlg_B1.getValue())) {

            NLBL3070CMsg bizMsg = new NLBL3070CMsg();
            bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg = (NLBL3070CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxNum_EV.getValueInt()).serNum_B1);
    }
}

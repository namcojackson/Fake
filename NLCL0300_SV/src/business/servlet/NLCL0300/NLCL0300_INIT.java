/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import static business.servlet.NLCL0300.constant.NLCL0300Constant.APPLICATION_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0300.NLCL0300CMsg;
import business.servlet.NLCL0300.common.NLCL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0300_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0]; // Inventory Order#
            ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOrdNum_H, param0);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        NLCL0300CMsg bizMsg = new NLCL0300CMsg();
        bizMsg.setBusinessID("NLCL0300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        NLCL0300CMsg bizMsg  = (NLCL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLCL0300CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        scrnMsg.rtlWhCd_H.setNameForMessage("Warehouse Code");
        scrnMsg.rtlWhNm_H.setNameForMessage("Warehouse Name");
        scrnMsg.invtyOrdNum_H.setNameForMessage("Document#");
        scrnMsg.mdseCd_H.setNameForMessage("Item Code");
        scrnMsg.serNum_H.setNameForMessage("Serial#");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdseCd_A.setNameForMessage("Item Number");
            scrnMsg.A.no(i).ordQty_A.setNameForMessage("Item Quantity");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Serial#");
        }
    }
}

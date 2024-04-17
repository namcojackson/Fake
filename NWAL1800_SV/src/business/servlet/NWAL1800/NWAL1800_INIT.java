/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1800;

import static business.servlet.NWAL1800.constant.NWAL1800Constant.BIZ_ID;
import static business.servlet.NWAL1800.constant.NWAL1800Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1800.NWAL1800CMsg;
import business.servlet.NWAL1800.common.NWAL1800CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1800_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1800_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1800BMsg scrnMsg = (NWAL1800BMsg) bMsg;
        NWAL1800CMsg bizMsg = new NWAL1800CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params[0] != null && params[0] instanceof EZDBStringItem) {
                EZDBStringItem param00 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum, param00);
            }
            if (params.length > 2) {
                EZDMsg.copy((EZDMsgArray) params[1], (String) params[2], scrnMsg.B, "B");
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1800BMsg scrnMsg = (NWAL1800BMsg) bMsg;
        NWAL1800CMsg bizMsg = (NWAL1800CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1800CommonLogic.initCmnBtnProp(this);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // do nohting
    }
}

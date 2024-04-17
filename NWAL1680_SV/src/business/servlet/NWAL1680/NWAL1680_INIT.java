/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1680;

import static business.servlet.NWAL1680.constant.NWAL1680Constant.BIZ_ID;
import static business.servlet.NWAL1680.constant.NWAL1680Constant.DETAIL_TABLE_ID;
import static business.servlet.NWAL1680.constant.NWAL1680Constant.SCRN_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1680.NWAL1680CMsg;
import business.servlet.NWAL1680.common.NWAL1680CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWAL1680_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1680BMsg scrnMsg = (NWAL1680BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctNm, (EZDBStringItem) params[1]);
        }
        NWAL1680CMsg bizMsg = new NWAL1680CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1680BMsg scrnMsg = (NWAL1680BMsg) bMsg;
        NWAL1680CMsg bizMsg = (NWAL1680CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1680CommonLogic.initButton(this);
        NWAL1680CommonLogic.initScreenField(scrnMsg);
        
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(DETAIL_TABLE_ID, scrnMsg.A);

    }
}

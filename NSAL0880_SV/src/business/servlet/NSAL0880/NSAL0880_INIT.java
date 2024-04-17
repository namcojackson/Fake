/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0880;

import static business.servlet.NSAL0880.constant.NSAL0880Constant.*;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0880.NSAL0880CMsg;
import business.servlet.NSAL0880.common.NSAL0880CommonLogic;
import business.servlet.NSAL0880.constant.NSAL0880Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0880_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0880BMsg scrnMsg = (NSAL0880BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null) {
            if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk, param0);
            }
            if (params.length > 1 && params[1] != null && params[1] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param1 = (EZDBBigDecimalItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcPhysMtrReadGrpSq, param1);
            }
        }

        NSAL0880CMsg bizMsg = new NSAL0880CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0880BMsg scrnMsg = (NSAL0880BMsg) bMsg;
        NSAL0880CMsg bizMsg = (NSAL0880CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0880CommonLogic.initControlCommonButton(this);
        NSAL0880CommonLogic.initCommonButton(this);
        NSAL0880CommonLogic.protectFields(this, scrnMsg);
        NSAL0880CommonLogic.setBackgroundColor(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        return;
    }
}

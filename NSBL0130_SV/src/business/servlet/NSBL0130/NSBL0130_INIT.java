/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0130;

import static business.servlet.NSBL0130.constant.NSBL0130Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0130.NSBL0130CMsg;
import business.servlet.NSBL0130.common.NSBL0130CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 *
 * Hold Detail Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/07   Hitachi         Y.Igarashi         Create          N/A
 * 2016/12/07   Hitachi         K.Kojima        Update          QC#14204
 *</pre>
 */
public class NSBL0130_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/12/07 K.Kojima [QC#14204,DEL]
        // checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSSINESS_ID);
        // END 2016/12/07 K.Kojima [QC#14204,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0130BMsg scrnMsg = (NSBL0130BMsg) bMsg;
        NSBL0130CMsg bizMsg = new NSBL0130CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.fsrNum, (EZDBStringItem) params[ARGS_FSR_NUMBER]);
        }
        bizMsg.setBusinessID(BUSSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0130BMsg scrnMsg = (NSBL0130BMsg) bMsg;
        NSBL0130CMsg bizMsg  = (NSBL0130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialize();

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        NSBL0130CommonLogic.setBgColor(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        // do nothing
    }

    private void initialize() {
        // Control
        setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
        setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_CLOSE, BUTTON_LABEL_CLOSE, 1, null);
    }
}

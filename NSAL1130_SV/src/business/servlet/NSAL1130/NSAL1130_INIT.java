/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1130;

import static business.servlet.NSAL1130.constant.NSAL1130Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1130.NSAL1130CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 *
 * NSAL1130 Counter History Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         K.Kojima        Create          N/A
 * 
 *</pre>
 */
public class NSAL1130_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1130BMsg scrnMsg = (NSAL1130BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.svcMachMstrPk, (EZDBBigDecimalItem) params[ARGS_SVC_MACH_MSTR_PK]);
            setValue(scrnMsg.mtrLbCd_SV, (EZDBStringItem) params[ARGS_MTR_LB_CD]);
        }

        NSAL1130CMsg bizMsg = new NSAL1130CMsg();
        bizMsg.setBusinessID("NSAL1130");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1130BMsg scrnMsg = (NSAL1130BMsg) bMsg;
        NSAL1130CMsg bizMsg = (NSAL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
        setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_CLOSE, BUTTON_LABEL_CLOSE, 1, null);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1130BMsg scrnMsg = (NSAL1130BMsg) bMsg;
        scrnMsg.mtrLbCd_SV.setNameForMessage("Meter Label");
        scrnMsg.effFromDt_SC.setNameForMessage("Read Date From");
        scrnMsg.effThruDt_SC.setNameForMessage("Read Date Thru");
    }
}

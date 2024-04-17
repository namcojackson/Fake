/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1170;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1170.NSAL1170CMsg;
import static business.servlet.NSAL1170.constant.NSAL1170Constant.*;

import business.servlet.NSAL1170.common.NSAL1170CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Model Escalation Rules Maintenance Popup.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Hitachi         T.Nishimura     Create          N/A
 * 2016/02/04   Hitachi         T.Nishimura     Update          QC#4121
 *</pre>
 */
public class NSAL1170_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        NSAL1170BMsg scrnMsg = (NSAL1170BMsg) bMsg;
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
            if (!hasValue(param0)) {
                scrnMsg.setMessageInfo("ZZZM9025E", new String[] {"Model Id" });
            }

        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1170BMsg scrnMsg = (NSAL1170BMsg) bMsg;

        NSAL1170CMsg bizMsg = new NSAL1170CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
            setValue(bizMsg.t_MdlId, param0);
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            setValue(bizMsg.t_MdlNm, param1);

        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1170BMsg scrnMsg = (NSAL1170BMsg) bMsg;
        NSAL1170CMsg bizMsg = (NSAL1170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind())) {
            NSAL1170CommonLogic.initialControlScreendisable(this, scrnMsg);
            return;
        }
        NSAL1170CommonLogic.initialControlScreen(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1170BMsg scrnMsg = (NSAL1170BMsg) bMsg;
        scrnMsg.t_MdlNm.setNameForMessage("Model Name");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1170_ABMsg detailMsg = scrnMsg.A.no(i);
            detailMsg.uplftBasePrcUpRatio.setNameForMessage("Service Percentage");
            detailMsg.uplftMtrPrcUpRatio.setNameForMessage("Billing Counter Percentage");
            detailMsg.effFromDt.setNameForMessage("Start Date");
            detailMsg.effThruDt.setNameForMessage("End Date");
        }
    }
}

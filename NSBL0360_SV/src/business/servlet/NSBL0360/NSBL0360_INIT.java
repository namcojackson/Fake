/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0360;

import java.io.Serializable;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0360.NSBL0360CMsg;
import business.servlet.NSBL0360.common.NSBL0360CommonLogic;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Sub Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0360_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NSBL0360");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0360BMsg scrnMsg = (NSBL0360BMsg) bMsg;
        NSBL0360CMsg bizMsg = new NSBL0360CMsg();
        bizMsg.setBusinessID("NSBL0360");
        bizMsg.setFunctionCode("20");

        Serializable arg = getArgForSubScreen();
        Object[] params = (Object[]) arg;

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        if (params[0] instanceof EZDBStringItem) {
            setValue(bizMsg.orgCd_HL, ((EZDBStringItem) params[0]));
        }
        if (params[1] instanceof EZDBDateItem) {
            setValue(bizMsg.cratDt_H, ((EZDBDateItem) params[1]));
        }

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0360BMsg scrnMsg = (NSBL0360BMsg) bMsg;
        NSBL0360CMsg bizMsg  = (NSBL0360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0360CommonLogic.initialControlScreen(this, scrnMsg);
        NSBL0360CommonLogic.setRowColors(scrnMsg);
        scrnMsg.cratDt_H.setInputProtected(true);

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

    }
}

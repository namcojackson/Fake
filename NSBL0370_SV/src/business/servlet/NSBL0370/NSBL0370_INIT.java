/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0370;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0370.NSBL0370CMsg;
import business.servlet.NSBL0370.common.NSBL0370CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Hourly Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0370_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NSBL0370");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0370BMsg scrnMsg = (NSBL0370BMsg) bMsg;
        NSBL0370CMsg bizMsg = new NSBL0370CMsg();
        bizMsg.setBusinessID("NSBL0370");
        bizMsg.setFunctionCode("20");

        Serializable arg = getArgForSubScreen();
        Object[] params = (Object[]) arg;

        if (params[0] instanceof EZDBStringItem) {
            setValue(scrnMsg.orgCd_HL, ((EZDBStringItem) params[0]));
        }
        if (params[1] instanceof EZDBDateItem) {
            setValue(scrnMsg.cratDt_H, ((EZDBDateItem) params[1]));
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0370BMsg scrnMsg = (NSBL0370BMsg) bMsg;
        NSBL0370CMsg bizMsg  = (NSBL0370CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0370CommonLogic.initialControlScreen(this, scrnMsg);
        NSBL0370CommonLogic.setRowColors(scrnMsg);
        scrnMsg.cratDt_H.setInputProtected(true);

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

    }
}

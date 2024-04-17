/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0110.NWCL0110CMsg;
import business.servlet.NWCL0110.common.NWCL0110CommonLogic;
import business.servlet.NWCL0110.constant.NWCL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
public class NWCL0110_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NWCL0110Constant.BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;

        NWCL0110CMsg bizMsg = new NWCL0110CMsg();
        bizMsg.setBusinessID(NWCL0110Constant.BIZ_ID);
        bizMsg.setFunctionCode(NWCL0110Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        NWCL0110CMsg bizMsg = (NWCL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0110CommonLogic.initControlCommonButton(this);
        NWCL0110CommonLogic.controlDtl(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxTpCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWCL0110BMsg scrnMsg = (NWCL0110BMsg) bMsg;
        scrnMsg.xxInvNum_FR.setNameForMessage("Request# From");
        scrnMsg.xxInvNum_TO.setNameForMessage("Request# To");
        scrnMsg.conslBillNum_FR.setNameForMessage("Bill# From");
        scrnMsg.conslBillNum_TO.setNameForMessage("Bill# To");
        scrnMsg.invNum_FR.setNameForMessage("Invoice# From");
        scrnMsg.invNum_TO.setNameForMessage("Invoice# To");
        scrnMsg.procDt_FR.setNameForMessage("Output Date From");

    }
}

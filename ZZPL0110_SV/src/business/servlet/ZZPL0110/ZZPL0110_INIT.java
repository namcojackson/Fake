/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.ZZPL0110.common.ZZPL0110CommonLogic;
import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/12   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), ZZPL0110Constant.BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        // ZZPL0110CMsg bizMsg = new ZZPL0110CMsg();
        // bizMsg.setBusinessID("ZZPL0110");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;
        // ZZPL0110CMsg bizMsg = (ZZPL0110CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZPL0110CommonLogic.setButtonProperties_INIT(this);
        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        scrnMsg.setFocusItem(scrnMsg.eipRptProcLogPk);

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) arg0;

        // ZZPL0110Scrn00 Header
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.eipRptProcLogPk.setNameForMessage("Report Process Key");
        scrnMsg.subSysCd.setNameForMessage("Subsystem Code");
        scrnMsg.rptJobId.setNameForMessage("Report Job ID");
        scrnMsg.xxFromDt.setNameForMessage("Start Date");
        scrnMsg.xxToDt.setNameForMessage("End Date");

    }
}

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0070.common.ZZZL0070CommonLogic;
import business.servlet.ZZZL0070.constant.ZZZL0070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0070_INIT extends S21INITCommonHandler implements ZZZL0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) bMsg;

        // Set Global Company Code
        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());

        // Initial Focus Control
        scrnMsg.setFocusItem(scrnMsg.batProcJobId);

        // Initial
        ZZZL0070CommonLogic.initCommonButton(this);
        ZZZL0070CommonLogic.initDate(scrnMsg);
        this.setButtonEnabled("Refresh", false);
        this.setButtonEnabled("View", false);
        this.setButtonEnabled("DelJob", false);

        ctx.setAttribute("JobTmData", null);
        ctx.setAttribute("JobCntData", null);
        ctx.setAttribute("TableSizeData", null);
        ctx.setAttribute("TableCntData", null);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        ZZZL0070BMsg scrnMsg = (ZZZL0070BMsg) arg0;
        
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.xxFromDt.setNameForMessage("From Date");
        scrnMsg.xxToDt.setNameForMessage("To Date");
        scrnMsg.batProcJobId.setNameForMessage("Job ID");
    }
}

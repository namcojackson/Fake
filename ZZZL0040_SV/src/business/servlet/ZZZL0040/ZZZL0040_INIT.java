/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0040;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0040.ZZZL0040CMsg;
import business.servlet.ZZZL0040.common.ZZZL0040CommonLogic;
import business.servlet.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZZL0040_INIT extends S21INITCommonHandler implements ZZZL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;

        ZZZL0040CMsg bizMsg = new ZZZL0040CMsg();
        bizMsg.setBusinessID("ZZZL0040");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
        ZZZL0040CMsg bizMsg  = (ZZZL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set Global Company Code
        scrnMsg.glblCmpyCd.setValue(getContextUserInfo().getUserCompanyCode());
        // Initial Focus Control
        scrnMsg.setFocusItem(scrnMsg.jvmNm_S);
        scrnMsg.xxChkBox.setValue("Y");
        scrnMsg.xxTotCnt.setInputProtected(true);
        // Set Pulldowns
        ZZZL0040CommonLogic.initCommonButton(this);
        ZZZL0040CommonLogic.initPullDowns(scrnMsg);
    }
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        ZZZL0040BMsg scrnMsg = (ZZZL0040BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.jvmNm_S.setNameForMessage("JVM name");
        scrnMsg.xxFromDt.setNameForMessage("From Date");
        scrnMsg.xxHrs_FS.setNameForMessage("From Hour");
        scrnMsg.xxMn_FS.setNameForMessage("From Minutes");
        scrnMsg.xxToDt.setNameForMessage("To Date");
        scrnMsg.xxHrs_TS.setNameForMessage("To Hour");
        scrnMsg.xxMn_TS.setNameForMessage("To Minutes");
        scrnMsg.xxChkBox.setNameForMessage("Averge");
        scrnMsg.xxTotCnt.setNameForMessage("Max number");
    }
}

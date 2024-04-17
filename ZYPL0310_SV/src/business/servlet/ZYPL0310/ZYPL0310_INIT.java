/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZYPL0310;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.ZYPL0310.ZYPL0310CMsg;
//import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import business.blap.ZYPL0310.ZYPL0310CMsg;
import business.servlet.ZYPL0310.common.ZYPL0310CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZYPL0310_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0310CommonLogic.checkScrnTransitionParameters((ZYPL0310BMsg) bMsg, getArgForSubScreen());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;

        // GLBL_CMPY_CD
        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        ZYPL0310CommonLogic.createDataTypePulldown(scrnMsg);

        return ZYPL0310CommonLogic.createCMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;
        ZYPL0310CMsg bizMsg = (ZYPL0310CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPL0310CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0310CommonLogic.setAlternateRowsBG(ZYPL0310Constant.MY_SCRN_ID, ZYPL0310Bean.A, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        ZYPL0310BMsg scrnMsg = (ZYPL0310BMsg) bMsg;

        scrnMsg.xxFileData.setNameForMessage("File Path");
        scrnMsg.xxAttDataCmntTxt_I.setNameForMessage("Description/Note");
        scrnMsg.othSysUrl.setNameForMessage("URL");
        scrnMsg.docMgtDocId.setNameForMessage("Therefore Document ID");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setNameForMessage("Check Box");
        }
    }

}

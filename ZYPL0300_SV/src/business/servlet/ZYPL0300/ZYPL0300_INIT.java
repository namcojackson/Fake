/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.ZYPL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0300.ZYPL0300CMsg;
import business.servlet.ZYPL0300.common.ZYPL0300CommonLogic;
import business.servlet.ZYPL0300.constant.ZYPL0300Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZYPL0300_INIT extends S21INITCommonHandler implements ZYPL0300Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0300CommonLogic.checkScrnTransitionParameters((ZYPL0300BMsg) bMsg, getArgForSubScreen());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;

        // GLBL_CMPY_CD
        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        return ZYPL0300CommonLogic.createCMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;
        ZYPL0300CMsg bizMsg = (ZYPL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPL0300CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0300CommonLogic.setAlternateRowsBG(MY_SCRN_ID, ZYPL0300Bean.A, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;

        scrnMsg.xxFileData.setNameForMessage("File Path");
        scrnMsg.attDataDescTxt_I.setNameForMessage("Description");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setNameForMessage("Check Box");
        }
    }

}

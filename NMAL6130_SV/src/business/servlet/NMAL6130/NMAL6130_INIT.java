/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2009   CUSA            K.Tajima        Create          N/A
 *</pre>
 */
package business.servlet.NMAL6130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6130.NMAL6130CMsg;
import business.servlet.NMAL6130.common.NMAL6130CommonLogic;
import business.servlet.NMAL6130.constant.NMAL6130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6130_INIT extends S21INITCommonHandler implements NMAL6130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6130CommonLogic.checkScrnTransitionParameters((NMAL6130BMsg) bMsg, getArgForSubScreen());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;

        // GLBL_CMPY_CD
        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        return NMAL6130CommonLogic.createCMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;
        NMAL6130CMsg bizMsg = (NMAL6130CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6130CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        NMAL6130CommonLogic.setAlternateRowsBG(MY_SCRN_ID, NMAL6130Bean.A, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;

        scrnMsg.xxFileData.setNameForMessage("File Path");
        scrnMsg.mstrAttDataDescTxt_I.setNameForMessage("Description");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setNameForMessage("Check Box");
        }
    }

}

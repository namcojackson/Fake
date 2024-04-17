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

import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0300.ZYPL0300CMsg;
import business.servlet.ZYPL0300.common.ZYPL0300CommonLogic;
import business.servlet.ZYPL0300.constant.ZYPL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0300Scrn00_Delete extends S21CommonHandler implements ZYPL0300Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;

        // validate check-boxes.
        List selectedList = ZYPTableUtil.getSelectedRows(scrnMsg.A, ZYPL0300Bean.xxChkBox, ZYPConstant.CHKBOX_ON_Y);
        if (selectedList.isEmpty()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                EZDBItem checkBox = scrnMsg.A.no(i).xxChkBox;
                checkBox.setErrorInfo(1, "ZZM9000E", new String[] {checkBox.getNameForMessage() });
                scrnMsg.addCheckItem(checkBox);
            }
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return ZYPL0300CommonLogic.createCMsgForUpdate((ZYPL0300BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0300BMsg scrnMsg = (ZYPL0300BMsg) bMsg;
        ZYPL0300CMsg bizMsg = (ZYPL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.attDataDescTxt_O.clear();

        ZYPL0300CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        ZYPL0300CommonLogic.setAlternateRowsBG(MY_SCRN_ID, ZYPL0300Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(ZYPL0300Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

}

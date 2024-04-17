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

import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6130.NMAL6130CMsg;
import business.servlet.NMAL6130.common.NMAL6130CommonLogic;
import business.servlet.NMAL6130.constant.NMAL6130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6130Scrn00_Delete extends S21CommonHandler implements NMAL6130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;

        // validate check-boxes.
        List selectedList = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL6130Bean.xxChkBox, ZYPConstant.CHKBOX_ON_Y);
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

        return NMAL6130CommonLogic.createCMsgForUpdate((NMAL6130BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;
        NMAL6130CMsg bizMsg = (NMAL6130CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.mstrAttDataDescTxt_O.clear();

        NMAL6130CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        NMAL6130CommonLogic.setAlternateRowsBG(MY_SCRN_ID, NMAL6130Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(NMAL6130Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

}

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

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6130Scrn00_Download extends S21CommonHandler implements NMAL6130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;

        scrnMsg.mstrAttDataDescTxt_O.clear();
        scrnMsg.xxCellIdx_DL.setValue(getButtonSelectNumber());

        return NMAL6130CommonLogic.createCMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6130BMsg scrnMsg = (NMAL6130BMsg) bMsg;
        NMAL6130CMsg bizMsg = (NMAL6130CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6130CommonLogic.setGUIComponentsProperties(this, getArgForSubScreen(), scrnMsg);

        NMAL6130CommonLogic.setAlternateRowsBG(MY_SCRN_ID, NMAL6130Bean.A, scrnMsg);

        S21SortColumnIMGController.clearIMG(NMAL6130Constant.MY_SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        if ("E".equals(bizMsg.getMessageKind())) {

            scrnMsg.setFocusItem(scrnMsg.xxFileData);

        } else {

            scrnMsg.mstrAttDataDescTxt_O.setValue(scrnMsg.A.no(getButtonSelectNumber()).mstrAttDataDescTxt.getValue());

            String tempFilePath = scrnMsg.xxFileData_DL.getTempFilePath();
            executeDownload(tempFilePath, true, scrnMsg.A.no(getButtonSelectNumber()).mstrAttDataNm.getValue());
        }
    }

}

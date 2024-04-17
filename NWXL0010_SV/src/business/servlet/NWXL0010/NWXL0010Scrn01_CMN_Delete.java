/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/10/2010   Fujitsu         K.Tajima        Create          N/A
 * 08/05/2010   Fujitsu         R.Nakamura      Update          QC#9078
 *</pre>
 */
package business.servlet.NWXL0010;

import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.addCheckAllItems;
import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.hasError;
import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.newCMsgForUpdate;
import static business.servlet.NWXL0010.common.NWXL0010CommonLogic.setGuiAttr;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWXL0010.NWXL0010CMsg;
import business.servlet.NWXL0010.constant.NWXL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWXL0010Scrn01_CMN_Delete extends S21CommonHandler implements NWXL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;
        
        scrnMsg.addCheckItem(scrnMsg.rptSqlId_01);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;

        return newCMsgForUpdate(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWXL0010BMsg scrnMsg = (NWXL0010BMsg) bMsg;
        NWXL0010CMsg bizMsg = (NWXL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // error judgement.
        addCheckAllItems(scrnMsg, scrnMsg.getBaseContents());
        scrnMsg.putErrorScreen();
        if (hasError(bizMsg)) {
            throw new EZDFieldErrorException();
        }

        // set GUI attribute.
        setGuiAttr(ScrnId.NWXL0010Scrn01, scrnMsg, this);

        // set focus.
        scrnMsg.setFocusItem(scrnMsg.rptSqlId_01);
    }

}

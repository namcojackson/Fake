/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1140;

import static business.servlet.NPAL1140.constant.NPAL1140Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1140.NPAL1140CMsg;
import business.servlet.NPAL1140.common.NPAL1140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 *</pre>
 */
public class NPAL1140Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;

        NPAL1140CMsg bizMsg = new NPAL1140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;
        NPAL1140CMsg bizMsg = (NPAL1140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1140CommonLogic.tabDispCtrl(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // START 06/11/2013 K.Kishimoto QC1233 Update
        //NPAL1140CommonLogic.setUnderTabDateTsItem(scrnMsg, bizMsg);
        scrnMsg.xxTsDsp19Txt_HR.clear();
        NPAL1140CommonLogic.initialize(this, scrnMsg, true);
        // START 06/11/2013 K.Kishimoto QC1233 Update
    }
}
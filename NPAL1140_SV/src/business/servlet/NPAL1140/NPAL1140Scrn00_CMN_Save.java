/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1140;

import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUSINESS_ID;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.FUNCTION_CD_UPDATE;
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
 * 04/11/2017   CITS            R.Shimamoto     Update          QC#18205
 *</pre>
 */
public class NPAL1140Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;
        // START 06/11/2013 K.Kishimoto QC1233 Add
        scrnMsg.addCheckItem(scrnMsg.poOrdNum_HT);
        // END 06/11/2013 K.Kishimoto QC1233 Add
        scrnMsg.addCheckItem(scrnMsg.B);
        // START 06/11/2013 K.Kishimoto QC1233 Update
        // scrnMsg.B.setCheckParam(new String[] {NPAL1140Bean.poOrdDtlLineNum_BX }, 1);
        // QC#18205 Mod.
//        scrnMsg.B.setCheckParam(new String[] {NPAL1140Bean.poOrdDtlLineNum_B0 }, 1);
        scrnMsg.B.setCheckParam(new String[] {NPAL1140Bean.dispPoDtlLineNum_B0 }, 1);
        // END 06/11/2013 K.Kishimoto QC1233 Update
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;

        NPAL1140CMsg bizMsg = new NPAL1140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;
        NPAL1140CMsg bizMsg = (NPAL1140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1140CommonLogic.tabDispCtrl(scrnMsg);
        // START 06/11/2013 K.Kishimoto QC1233 Add
        scrnMsg.addCheckItem(scrnMsg.poOrdNum_HT);
        // END 06/11/2013 K.Kishimoto QC1233 Add
        scrnMsg.addCheckItem(scrnMsg.B);
        // START 06/11/2013 K.Kishimoto QC1233 Update
        // scrnMsg.B.setCheckParam(new String[] {NPAL1140Bean.poOrdDtlLineNum_BX }, 1);
        // QC#18205 Mod.
//        scrnMsg.B.setCheckParam(new String[] {NPAL1140Bean.poOrdDtlLineNum_B0 }, 1);
        scrnMsg.B.setCheckParam(new String[] {NPAL1140Bean.dispPoDtlLineNum_B0 }, 1);
        // END 06/11/2013 K.Kishimoto QC1233 Update
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // START 06/11/2013 K.Kishimoto QC1233 Update
        NPAL1140CommonLogic.setUnderTabDateTsItem(scrnMsg, bizMsg);
        NPAL1140CommonLogic.itemCtrlDataDisp(this, scrnMsg, bizMsg);
        // END 06/11/2013 K.Kishimoto QC1233 Update
    }
}

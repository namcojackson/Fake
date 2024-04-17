/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1140;

import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUSINESS_ID;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.MSG_ID_NPAM1304E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1140.NPAL1140CMsg;
import business.servlet.NPAL1140.common.NPAL1140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

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
public class NPAL1140_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
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

        boolean notRtnType = true;
        if (MSG_ID_NPAM1304E.equals(scrnMsg.getMessage())) {
            notRtnType = false;
        }
        NPAL1140CommonLogic.initialize(this, scrnMsg, notRtnType);
        scrnMsg.putErrorScreen();
}

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;
        // START 06/11/2013 K.Kishimoto QC1233 Update
        // scrnMsg.poOrdNum.setNameForMessage("PO#");
        scrnMsg.ediPoOrdNum.setNameForMessage("EDI PO#");
        scrnMsg.poOrdNum_HT.setNameForMessage("PO#");
        // END 06/11/2013 K.Kishimoto QC1233 Update
        scrnMsg.xxCratDt_H1.setNameForMessage("EDI Rcv Date From");
        scrnMsg.xxCratDt_H2.setNameForMessage("EDI Rcv Date To");
        // START 06/11/2013 K.Kishimoto QC1233 Add
        scrnMsg.xxCratDt_H2.setNameForMessage("Message(*)");
        // END 06/11/2013 K.Kishimoto QC1233 Add

        for (int idx = 0; idx < scrnMsg.B.length(); idx++) {
            // START 06/11/2013 K.Kishimoto QC1233 Update
            // scrnMsg.B.no(idx).poOrdDtlLineNum_BX.setNameForMessage("PO Line#");
            // QC#18205 Mod.
//            scrnMsg.B.no(idx).poOrdDtlLineNum_B0.setNameForMessage("PO Line#");
            scrnMsg.B.no(idx).dispPoDtlLineNum_B0.setNameForMessage("PO Line#");
            // END 06/11/2013 K.Kishimoto QC1233 Update
        }
    }
}

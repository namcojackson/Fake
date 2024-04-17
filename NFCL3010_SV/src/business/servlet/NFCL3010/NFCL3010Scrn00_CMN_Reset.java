/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3010.NFCL3010CMsg;
import business.servlet.NFCL3010.common.NFCL3010CommonLogic;
import business.servlet.NFCL3010.constant.NFCL3010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/03/22   Fujitsu         H.Ikeda         Update          QC#21737
 *</pre>
 */
public class NFCL3010Scrn00_CMN_Reset extends S21CommonHandler implements NFCL3010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

        NFCL3010CMsg bizMsg = new NFCL3010CMsg();
        bizMsg.setBusinessID("NFCL3010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        NFCL3010CMsg bizMsg  = (NFCL3010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3010CommonLogic.initialize(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController("NFCL3010Scrn00", scrnMsg);
        NFCL3010CommonLogic.initialize(this, scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        // Del Start 2018/03/22 S21_NA#21737
        //tblColor.clearRowsBG(TABLE_B, scrnMsg.B);
        //tblColor.setAlternateRowsBG(TABLE_B, scrnMsg.B);
        // Del End   2018/03/22 S21_NA#21737
    }
}

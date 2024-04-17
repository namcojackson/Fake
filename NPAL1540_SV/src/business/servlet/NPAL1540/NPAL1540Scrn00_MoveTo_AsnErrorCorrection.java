/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : Move ASN Error Correction
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/25   CITS            Makoto Okigami  Create          N/A
 * 09/13/2018   CITS            K.Ogino         Update          QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 *</pre>
 */
public class NPAL1540Scrn00_MoveTo_AsnErrorCorrection extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // QC#26964/QC#26965
        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;
        
        if (scrnMsg.A.getValidCount() > 0) {
            Object[] params = new Object[1];
            params[0] = scrnMsg.A.no(0).ediPoOrdNum_A1;
            setArgForSubScreen(params);
        }
        openMultiModeScreen();

    }
}

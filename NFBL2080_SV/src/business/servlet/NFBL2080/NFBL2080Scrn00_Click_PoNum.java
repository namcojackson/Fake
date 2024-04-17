/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2016/07/28   Fujitsu         S.Yoshida       Update          QC#12555,12575
 *</pre>
 */
public class NFBL2080Scrn00_Click_PoNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

//Mod Start QC#12575
        scrnMsg.P.clear();
        int iCnt = getButtonSelectNumber();

        String submitNm = getSubmitedFieldNm(ctx);
        if ("edi".equals(submitNm)) {
            scrnMsg.P.no(0).xxPopPrm_P1.setValue(scrnMsg.B.no(iCnt).ediPoOrdNum_B1.getValue());
        } else {
            scrnMsg.P.no(0).xxPopPrm_P1.setValue(scrnMsg.B.no(iCnt).poOrdNum_B1.getValue());
        }

        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P1;
//Mod End   QC#12575

        setArgForSubScreen(params);

    }
}

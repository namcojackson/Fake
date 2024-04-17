/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;
import static business.servlet.NFAL0200.constant.NFAL0200Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0200.NFAL0200CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/11/02   Hitachi         J.Kim           Update          QC#13443
 *</pre>
 */
public class NFAL0200Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        // START 2016/11/02 J.Kim [QC#13443,ADD]
        for (int index = 0; index < scrnMsg.C.length(); index++) {
            NFAL0200_CBMsg cbMsg = scrnMsg.C.no(index);
            scrnMsg.addCheckItem(cbMsg.contrCoaBrCd_C);
            scrnMsg.addCheckItem(cbMsg.coaBrGeoTxt_C);
        }
        scrnMsg.putErrorScreen();
        // END 2016/11/02 J.Kim [QC#13443,ADD]

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        NFAL0200CMsg bizMsg = new NFAL0200CMsg();
        bizMsg.setBusinessID("NFAL0200");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        NFAL0200CMsg bizMsg  = (NFAL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/11/02 J.Kim [QC#13443,ADD]
        for (int index = 0; index < scrnMsg.C.getValidCount(); index++) {
            NFAL0200_CBMsg cbMsg = scrnMsg.C.no(index);
            scrnMsg.addCheckItem(cbMsg.contrCoaBrCd_C);
        }
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2016/11/02 J.Kim [QC#13443,ADD]
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0060;

import static business.servlet.NSAL0060.constant.NSAL0060Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0060.NSAL0060CMsg;
import business.servlet.NSAL0060.common.NSAL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi      Create          N/A
 * 08/29/2013   Hitachi         T.Aoyagi        Update          QC1955
 *</pre>
 */
public class NSAL0060Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.mdlGrpNm_SC);
        scrnMsg.addCheckItem(scrnMsg.mdlGrpDescTxt_SC);
        scrnMsg.putErrorScreen();
        // START 2013/08/29 T.Aoyagi [QC1955,DEL]
//        if (!hasValue(scrnMsg.mdlGrpNm_SC) && !hasValue(scrnMsg.mdlGrpDescTxt_SC)) {
//            scrnMsg.setMessageInfo(NSAM0005E);
//            scrnMsg.setFocusItem(scrnMsg.mdlGrpNm_SC);
//            throw new EZDFieldErrorException();
//        }
        // END 2013/08/29 T.Aoyagi [QC1955,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;

        NSAL0060CMsg bizMsg = new NSAL0060CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        NSAL0060CMsg bizMsg  = (NSAL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0060CommonLogic.setProtected(scrnMsg, this);
        NSAL0060CommonLogic.checkAuth(scrnMsg, this);
        NSAL0060CommonLogic.setBgColor(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_SR);
        } else {
            scrnMsg.setFocusItem(scrnMsg.mdlGrpNm_SC);
        }
    }
}

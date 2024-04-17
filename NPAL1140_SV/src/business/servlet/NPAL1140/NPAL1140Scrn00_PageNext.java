/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1140;

import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUSINESS_ID;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1140.NPAL1140CMsg;
import business.servlet.NPAL1140.common.NPAL1140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NPAL1140Scrn00_PageNext extends S21CommonHandler {

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
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxTsDsp19Txt_AR, NPAL1140CommonLogic.formatDateTs(bizMsg.A.no(idx).ediRcvDateTs_A0.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxTsDsp19Txt_AU, NPAL1140CommonLogic.formatDateTs(bizMsg.A.no(idx).ordLastUpdTs_A0.getValue()));
        }
        NPAL1140CommonLogic.itemCtrlDataDisp(this, scrnMsg, bizMsg);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0040.ZZIL0040CMsg;
import business.servlet.ZZIL0040.common.ZZIL0040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;

        ZZIL0040CMsg bizMsg = new ZZIL0040CMsg();
        bizMsg.setBusinessID("ZZIL0040");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;
        ZZIL0040CMsg bizMsg = (ZZIL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxFromDt_SF.clear();
        scrnMsg.xxFromDt_EF.clear();
        scrnMsg.xxToDt_ST.clear();
        scrnMsg.xxToDt_ET.clear();
        scrnMsg.xxHrs_S1.clear();
        scrnMsg.xxHrs_S2.clear();
        scrnMsg.xxHrs_E1.clear();
        scrnMsg.xxHrs_E2.clear();
        scrnMsg.intfcId_H.clear();
        scrnMsg.intfcRqstStsTxt_HS.clear();

        scrnMsg.setFocusItem(scrnMsg.intfcId_H);
        ZZIL0040CommonLogic.dspScrn00(scrnMsg, this);
    }
}

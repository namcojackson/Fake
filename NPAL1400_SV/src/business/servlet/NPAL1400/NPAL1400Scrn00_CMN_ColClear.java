/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1400;

import static business.servlet.NPAL1400.constant.NPAL1400Constant.BIZ_APP_ID;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.FUNCTION_CD_UPDATE;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1400.NPAL1400CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * Function Name : Reset
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1400Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1400BMsg scrnMsg = (NPAL1400BMsg) bMsg;

        NPAL1400CMsg bizMsg = new NPAL1400CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1400BMsg scrnMsg = (NPAL1400BMsg) bMsg;
        NPAL1400CMsg bizMsg  = (NPAL1400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
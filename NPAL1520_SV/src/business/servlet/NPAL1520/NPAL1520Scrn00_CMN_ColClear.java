/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.FUNCTION_CD_UPDATE;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1520.NPAL1520CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Common ColClear
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1520Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        NPAL1520CMsg bizMsg = new NPAL1520CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;
        NPAL1520CMsg bizMsg  = (NPAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}

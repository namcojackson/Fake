/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1570.NWAL1570CMsg;
import business.servlet.NWAL1570.common.NWAL1570CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1570Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg = (NWAL1570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 1. initialize GUI attribute.
        NWAL1570CommonLogic.initGuiAttrScrn00(this, scrnMsg);

        // 2. initialize GUI value.
        NWAL1570CommonLogic.initGuiValueScrn00(scrnMsg);

        // 3. setProtectByAuthority
        NWAL1570CommonLogic.setProtectByAuthority(this, scrnMsg);

        // 4. set focus.
        scrnMsg.setFocusItem(scrnMsg.xxCpoOrdNumSrchTxt_H1);

        // 5. set digit.
        NWAL1570CommonLogic.setAppFracDigit(scrnMsg);

    }
}

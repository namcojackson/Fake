/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.controlContactListBtn;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_Add_Row
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         S.Ohki          Create          N/A
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 *</pre>
 */
public class NWAL1510Scrn00_Add_Row extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        controlContactListBtn(this, scrnMsg);

        NWAL1510CommonLogic.setTabProtect(this, scrnMsg);   //QC#16452 add

        int index = scrnMsg.C.getValidCount() - 1;
        scrnMsg.setFocusItem(scrnMsg.C.no(index).ctacPsnTpCd_C0);
    }
}

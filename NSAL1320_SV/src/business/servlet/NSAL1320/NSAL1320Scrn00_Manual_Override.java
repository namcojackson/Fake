/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL1320.NSAL1320CMsg;
//import business.servlet.NSAL1320.constant.NSAL1320Constant;

import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/07   Fujitsu         A.Kosai         Create          QC#22482
 *</pre>
 */
public class NSAL1320Scrn00_Manual_Override extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();

        NSAL1320CommonLogic.setScrnManOvrdDspCtrl(scrnMsg, selectIndex);
        NSAL1320CommonLogic.protectLineItemForEntry(this, scrnMsg);
    }
}

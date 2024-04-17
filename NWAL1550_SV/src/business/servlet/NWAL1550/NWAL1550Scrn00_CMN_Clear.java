/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550;

import static business.servlet.NWAL1550.constant.NWAL1550Constant.SCRN_ID_00;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.SHARP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1550.common.NWAL1550CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1550Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1550Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;

        scrnMsg.clear();
        scrnMsg.A.setValidCount(0);

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;

        NWAL1550CommonLogic.initControlFields(scrnMsg);
        NWAL1550CommonLogic.initCmnBtnProp(this);
        NWAL1550CommonLogic.setControlFields(this, scrnMsg);

        for (int i=0; i<scrnMsg.A.length(); i++) {
            scrnMsg.clearGUIAttribute(SCRN_ID_00, NWAL1550Bean.diChkErrTxt_A + SHARP + i);
        }

        scrnMsg.cpoOrdNum.setInputProtected(false);
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }
}

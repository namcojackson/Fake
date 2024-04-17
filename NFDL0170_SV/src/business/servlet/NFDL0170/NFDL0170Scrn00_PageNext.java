/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0170;

import static business.servlet.NFDL0170.constant.NFDL0170Constant.BIZ_ID;
import static business.servlet.NFDL0170.constant.NFDL0170Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0170.NFDL0170CMsg;
import business.servlet.NFDL0170.common.NFDL0170CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 *</pre>
 */
public class NFDL0170Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;

        NFDL0170CMsg bizMsg = new NFDL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0170BMsg scrnMsg = (NFDL0170BMsg) bMsg;
        NFDL0170CMsg bizMsg  = (NFDL0170CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0170CommonLogic.initCmnBtnProp(this);
        NFDL0170CommonLogic.controlScreen(this, scrnMsg);
        NFDL0170CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A);
    }
}

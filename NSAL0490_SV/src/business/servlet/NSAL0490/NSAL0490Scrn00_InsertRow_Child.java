/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.common.NSAL0490CommonLogic;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/12   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NSAL0490Scrn00_InsertRow_Child extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        NSAL0490CommonLogic.checkItemConfigDetail(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        NSAL0490CMsg bizMsg = new NSAL0490CMsg();
        bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0490CommonLogic.controlItemConfigField(this, scrnMsg);
        NSAL0490CommonLogic.setTblBackColorItemConfig(scrnMsg);

        scrnMsg.xxRadioBtn_A.setValue(NSAL0490CommonLogic.getAddChildIndex(scrnMsg));
        scrnMsg.setFocusItem(scrnMsg.A.no(NSAL0490CommonLogic.getAddChildIndex(scrnMsg)).childMdseCd_A);
    }
}
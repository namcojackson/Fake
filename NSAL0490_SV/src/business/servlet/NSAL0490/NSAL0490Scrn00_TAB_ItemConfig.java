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
 * 2015/03/11   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NSAL0490Scrn00_TAB_ItemConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
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
        scrnMsg.clearAllGUIAttribute(NSAL0490Constant.SCREEN_ID_00);

        if (NSAL0490CommonLogic.hasUpdateFuncId()) {
            NSAL0490CommonLogic.controlHdrField(this, scrnMsg, true);
            NSAL0490CommonLogic.controlItemConfigField(this, scrnMsg);
        } else {
            NSAL0490CommonLogic.controlItemConfigDtlField(this, scrnMsg, false);
        }

        NSAL0490CommonLogic.controlSearchField(this, scrnMsg, false);
        NSAL0490CommonLogic.controlInitTabBtn(scrnMsg);
        NSAL0490CommonLogic.setTblBackColorItemConfig(scrnMsg);

        scrnMsg.xxRadioBtn_A.setValue(0);
        scrnMsg.setFocusItem(scrnMsg.A.no(0).prntMdseCd_A);
        scrnMsg.xxDplyTab.setValue(NSAL0490Constant.TAB_ITEM_CONFIG);
    }
}

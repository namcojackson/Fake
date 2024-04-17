/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1830;

import static business.servlet.NWAL1830.constant.NWAL1830Constant.RADIO_VAL_NEW_ORD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1830.common.NWAL1830CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1830Scrn00_OnChange_RadioBtnNewExistingOrder
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1830Scrn00_OnChange_RadioBtnNewExistingOrder extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1830BMsg scrnMsg = (NWAL1830BMsg) bMsg;
        NWAL1830CommonLogic.setControlFieldRadioBtn(scrnMsg);
        // Focus
        if (RADIO_VAL_NEW_ORD.intValue() == scrnMsg.xxRadioBtn_OL.getValueInt()) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd_OL);
        } else {
            scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_LK);
        }
    }
}

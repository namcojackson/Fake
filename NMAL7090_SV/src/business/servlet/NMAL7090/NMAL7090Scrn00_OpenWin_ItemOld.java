/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_OPEN_WIN_ITEM_OLD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7090.common.NMAL7090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name : Open Return to Item Search Popup(NMAL6800)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */

public class NMAL7090Scrn00_OpenWin_ItemOld extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        // set popup parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_OPEN_WIN_ITEM_OLD);
        Object[] params = NMAL7090CommonLogic.setMdseInfoParam(scrnMsg, getButtonSelectNumber());

        // Subscreen transition
        setArgForSubScreen(params);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_STATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Open State Search Popup(NMAL6050)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NMAL6820Scrn00_OpenWin_ReturnToState extends S21CommonHandler {

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

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        // Initialization of subscreen delivery information
        NMAL6820CommonLogic.setInitParamForReturnToStPopup(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_G2, scrnMsg.rtrnToStCd_R1);

        // Making of subscreen delivery information
        Object[] params = NMAL6820CommonLogic.setParamForReturnToStPopup(scrnMsg);

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_STATE);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}

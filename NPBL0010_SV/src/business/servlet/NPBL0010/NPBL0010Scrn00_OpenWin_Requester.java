/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_NM_FOR_AUTH_PSN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0010.common.NPBL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Open Return to Requester Search Popup(NMAL6050)
 * </pre>
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/13/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0010Scrn00_OpenWin_Requester extends S21CommonHandler {

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

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_AUTH_PSN);
        Object[] params = NPBL0010CommonLogic.getParamNWAL1130ForRequesterPopup(scrnMsg, getGlobalCompanyCode());

        // Subscreen transition
        setArgForSubScreen(params);

    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6890;

import static business.servlet.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_OPEN_WIN_OWNR;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6890.common.NMAL6890CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : Open Window for Person Search(Owner)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NMAL6890Scrn00_OpenWin_Owner extends S21CommonHandler {

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

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        // Initialization of subscreen delivery information
        NMAL6890CommonLogic.setInitParamForOwnerPopup(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_G1, scrnMsg.fullPsnNm_O1);

        // Making of subscreen delivery information
        Object[] params = NMAL6890CommonLogic.setParamForOwnerPopup(scrnMsg);

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, EVENT_NM_NMAL6890SCRN00_OPEN_WIN_OWNR);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}

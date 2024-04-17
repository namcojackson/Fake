/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.EVENT_OPEN_WIN_MDSE_HEAD;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P10;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2040.common.NLAL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLAL2040Scrn00_OpenWin_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;

        // Initialization of sub screen delivery information
        NLAL2040CommonLogic.setInitParamForItemMasterPopup(scrnMsg);

        // Making of sub screen delivery information
        Object[] params = NLAL2040CommonLogic.setParamForItemMasterPopup(scrnMsg);

        // Set Event Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, EVENT_OPEN_WIN_MDSE_HEAD);

        // Set Condition
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondNm_P1, scrnMsg.t_MdlNm);
        params[P10] = scrnMsg.xxCondNm_P1;

        // Sub screen transition
        setArgForSubScreen(params);

    }
}

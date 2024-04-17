/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Open Return to Location Search Popup(NPAL1010)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 *</pre>
 */
public class NMAL6820Scrn00_OpenWin_ReturnToLocation extends S21CommonHandler {

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

        Object[] params;

        if (PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_R1.getValue())) {
            params = NMAL6820CommonLogic.setParamForSupplierPopup(scrnMsg, scrnMsg.prntVndNm_SR.getValue(), scrnMsg.vndNm_SR.getValue());
            setResult("T");

        } else {
            NMAL6820CommonLogic.setInitParamForLocationPopup(scrnMsg, ZYPConstant.FLG_ON_Y);
            params = NMAL6820CommonLogic.setParamForLocationPopup(scrnMsg, null, scrnMsg.prntVndNm_SR, scrnMsg.vndNm_SR, ZYPConstant.FLG_OFF_N);

            setResult("W");
        }

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}

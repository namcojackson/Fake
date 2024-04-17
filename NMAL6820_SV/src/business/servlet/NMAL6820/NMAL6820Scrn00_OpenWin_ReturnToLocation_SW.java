/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW;
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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/04/2016   CSAI            D.Fukaya        Create          QC#2380
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 *</pre>
 */
public class NMAL6820Scrn00_OpenWin_ReturnToLocation_SW extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        NMAL6820CommonLogic.checkIsSrcTpWh(scrnMsg.A.no(eventRowIndex).procrTpCd_A2);
        scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).prntVndNm_AR);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        int eventRowIndex = getButtonSelectNumber();

        Object[] params;

        if (PROCR_TP.SUPPLIER.equals(scrnMsg.A.no(eventRowIndex).procrTpCd_A2.getValue())) {
            params = NMAL6820CommonLogic.setParamForSupplierPopup(scrnMsg,scrnMsg.A.no(eventRowIndex).prntVndNm_AR.getValue(), scrnMsg.A.no(eventRowIndex).vndNm_AR.getValue());
            setResult("T");

        } else {
            NMAL6820CommonLogic.setInitParamForLocationPopup(scrnMsg, ZYPConstant.FLG_ON_Y);
            params = NMAL6820CommonLogic.setParamForLocationPopup(scrnMsg, null, scrnMsg.A.no(eventRowIndex).prntVndNm_AR, scrnMsg.A.no(eventRowIndex).vndNm_AR, ZYPConstant.FLG_OFF_N);
            setResult("W");
        }

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW);

        // SubScreen transition
        setArgForSubScreen(params);
    }
}

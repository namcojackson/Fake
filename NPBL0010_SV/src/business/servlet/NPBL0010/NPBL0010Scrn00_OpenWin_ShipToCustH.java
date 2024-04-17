/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.IDX_26;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SHIP_TO_ONLY_CD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request Entry
 * Function Name : OpenWin_ShipToCustH
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2016   CITS            K.Ogino         Create          N/A
 * 09/26/2019   Fujitsu         T.Ogura         Update          QC#52362
 *</pre>
 */
public class NPBL0010Scrn00_OpenWin_ShipToCustH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        Object[] params = setParamForShipToCustPopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPBL0010BMsg
     * @param index int
     * @return ShipToCustPopup Param (NMAL6760) Object[]
     */
    private Object[] setParamForShipToCustPopup(NPBL0010BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        int paramCount = 0;
        Object[] params = new Object[IDX_26];
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        // START 2019/09/26 T.Ogura [QC#52362,MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, scrnMsg.shipToLocNm_EO);
        String shipToCustLocNm = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToLocNm_EO)) {
            shipToCustLocNm = scrnMsg.shipToLocNm_EO.getValue();
            if (shipToCustLocNm.length() == 60) {
                shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, shipToCustLocNm);
        // END   2019/09/26 T.Ogura [QC#52362,MOD]
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, SHIP_TO_ONLY_CD);
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(paramCount).xxPopPrm, scrnMsg.shipToCustCd_EO);
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;
        params[paramCount++] = scrnMsg.P.no(paramCount).xxPopPrm;

        return params;
    }
}

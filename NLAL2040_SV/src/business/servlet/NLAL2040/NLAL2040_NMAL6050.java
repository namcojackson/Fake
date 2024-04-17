/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.EVENT_OPEN_WIN_MDSE_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

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
public class NLAL2040_NMAL6050 extends S21CommonHandler {

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

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            // Return Detail MdseName
            if (EVENT_OPEN_WIN_MDSE_DETAIL.equals(scrnMsg.xxPopPrm_P1.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).t_MdlNm_A1, scrnMsg.xxCondNm_P1);
                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).t_MdlNm_A1);

            // Return header MdseName
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm, scrnMsg.xxCondNm_P1);
                scrnMsg.setFocusItem(scrnMsg.t_MdlNm);
            }
        }
    }
}

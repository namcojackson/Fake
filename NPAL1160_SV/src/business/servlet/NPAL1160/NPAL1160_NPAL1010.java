/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 *</pre>
 */
public class NPAL1160_NPAL1010 extends S21CommonHandler {

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

        if (!"CMN_Close".equals(getLastGuard())) {

            NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;

            int rowNum = getButtonSelectNumber();
            if (rowNum >= 0) {
                // detail click
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(rowNum).rtlWhCd_D1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(rowNum).rtlWhNm_D1, scrnMsg.xxPopPrm_P7);
                scrnMsg.setFocusItem(scrnMsg.D.no(rowNum).rtlWhNm_D1);

            } else {
                // header click
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_LT, scrnMsg.xxPopPrm_P7);
                scrnMsg.setFocusItem(scrnMsg.rtlWhNm_LT);
            }
        }

    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.TBL_NM_FOR_OTBD_CARR_V;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Return Action from NMAL6050(General Purpose Popup)
 * </pre>
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/13/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0010_NMAL6050 extends S21CommonHandler {

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

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_FOR_OTBD_CARR_V.equals(scrnMsg.xxTblNm_P1.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.carrNm);
            }
        }

    }
}

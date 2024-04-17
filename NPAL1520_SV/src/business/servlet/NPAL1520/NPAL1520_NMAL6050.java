/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.TBL_NM_FOR_PSN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : NMAL6050 Pop Up Return
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1520_NMAL6050 extends S21CommonHandler {

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

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_FOR_PSN.equals(scrnMsg.xxTblNm_P1.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.whMgrPsnCd);
            } else {
                scrnMsg.setFocusItem(scrnMsg.mrpPlnNm);
            }
        }

    }
}

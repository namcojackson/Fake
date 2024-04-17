/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : NMAL6050
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 *</pre>
 */
public class NPAL1280_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        if ("OpenWin_Requester".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqRqstByPsnCd, scrnMsg.xxCondCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm, scrnMsg.xxCondNm_P1.getValue());
            scrnMsg.setFocusItem(scrnMsg.fullPsnNm);
        }
    }
}

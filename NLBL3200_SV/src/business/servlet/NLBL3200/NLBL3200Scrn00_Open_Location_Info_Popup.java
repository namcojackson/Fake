/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5771
 *</pre>
 */
public class NLBL3200Scrn00_Open_Location_Info_Popup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        Object[] params = NLBL3200CommonLogic.setLocationPopupParam(scrnMsg, true);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLBL3200Constant.EVENT_NM_OPENWIN_LOCINFO_RTLWH);
        setArgForSubScreen(params);
    }
}

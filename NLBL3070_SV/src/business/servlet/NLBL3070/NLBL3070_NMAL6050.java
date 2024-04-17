/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLBL3070_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;

        if (NLBL3070Constant.EVENT_NM_OPENWIN_MODEL.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm, scrnMsg.P.no(10).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.t_MdlNm);

        } else if (NLBL3070Constant.EVENT_NM_OPENWIN_CARR.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!NLBL3070Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm, scrnMsg.P.no(10).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.carrCd);
        }

    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 *</pre>
 */
public class NLBL3080_NMAL6050 extends S21CommonHandler {

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

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        if (NLBL3080Constant.EVENT_NM_OPENWIN_MODEL.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!NLBL3080Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm, scrnMsg.P.no(10).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.t_MdlNm);

        } else if (NLBL3080Constant.EVENT_NM_OPENWIN_SLS_REP.equals(scrnMsg.xxMntEventNm.getValue())) {
            if (!NLBL3080Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.tocCd, scrnMsg.P.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.tocNm, scrnMsg.P.no(10).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.tocCd);
        }

    }
}

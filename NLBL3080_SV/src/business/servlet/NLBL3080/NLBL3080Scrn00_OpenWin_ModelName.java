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
public class NLBL3080Scrn00_OpenWin_ModelName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDL_NM_V");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "T_MDL_ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "T_MDL_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "T_MDL_ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Model Name Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Model ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Model Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Model ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Model Name");
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.t_MdlNm);
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[11];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLBL3080Constant.EVENT_NM_OPENWIN_MODEL);
        setArgForSubScreen(params);
    }
}

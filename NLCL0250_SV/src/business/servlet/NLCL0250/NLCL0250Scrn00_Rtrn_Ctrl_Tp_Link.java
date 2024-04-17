/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0250.common.NLCL0250CommonLogic;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLCL0250Scrn00_Rtrn_Ctrl_Tp_Link extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLCL0250BMsg scrnMsg = (NLCL0250BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLCL0250Constant.EVENT_NM_RTRN_CTRL_TP_LINK);
        Object[] params = NLCL0250CommonLogic.setParamForNWAL1130(scrnMsg, getGlobalCompanyCode());
        setArgForSubScreen(params);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0270.constant.NLCL0270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLCL0270_NPAL1010 extends S21CommonHandler implements NLCL0270Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;

        if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_OPENWIN_LOC_INFO_FOR_WH)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.xxPopPrm_P7);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, scrnMsg.xxPopPrm_P9);
            scrnMsg.setFocusItem(scrnMsg.rtlWhNm_H1);

        } else if (scrnMsg.xxPopPrm_EV.getValue().equals(EVENT_NM_OPENWIN_LOC_INFO_FOR_SWH)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_H1, scrnMsg.xxPopPrm_P7);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_H1, scrnMsg.xxPopPrm_P9);
            scrnMsg.setFocusItem(scrnMsg.rtlSwhNm_H1);

        }
    }
}

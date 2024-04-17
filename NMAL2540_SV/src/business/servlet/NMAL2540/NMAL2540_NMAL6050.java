/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2540.constant.NMAL2540Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2540_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;

        if (!NMAL2540Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NMAL2540Constant.TBL_NM_FOR_ST.equals(scrnMsg.xxTblNm_P1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_H1, scrnMsg.stCd_G1);
                scrnMsg.setFocusItem(scrnMsg.stCd_H1);

            } else if (NMAL2540Constant.TBL_NM_FOR_CTRY.equals(scrnMsg.xxTblNm_P1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd_H1, scrnMsg.ctryCd_G2);
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctryNm_H1, scrnMsg.ctryNm_G2);
                scrnMsg.setFocusItem(scrnMsg.ctryCd_H1);

            }
        }
    }
}

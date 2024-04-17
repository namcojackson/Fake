/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2570_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        if (!NMAL2570Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (NMAL2570Constant.TBL_NM_FOR_JOB_TTL.equals(scrnMsg.xxTblNm_P1.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.jobTtlCd_H1, scrnMsg.jobTtlCd_G1);

                scrnMsg.setFocusItem(scrnMsg.jobTtlCd_H1);
            }
        }
    }
}

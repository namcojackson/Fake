/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import business.blap.NWAL2340.NWAL2340CMsg;
// import business.servlet.NWAL2340.constant.NWAL2340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * SOM Address Mass Apply Check all
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2340Scrn00_OnChange_CheckAll extends S21CommonHandler {

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

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;

        String flg;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_H.getValue())) {
            flg = ZYPConstant.FLG_ON_Y;
        } else {
            flg = ZYPConstant.FLG_OFF_N;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A, flg);

        }
    }
}

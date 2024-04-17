/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto       Create          N/A
 * 2018/08/03   Hitachi         K.Kitachi       Update          QC#25727
 *</pre>
 */
public class NSAL0610Scrn00_OrigSelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0610BMsg scrnMsg = (NSAL0610BMsg) bMsg;
        // START 2018/08/03 K.Kitachi [QC#25727, MOD]
//        ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0610_ABMsg aBMsg = scrnMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(aBMsg.xxDplyCtrlFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(aBMsg.xxChkBox_A, ZYPConstant.FLG_ON_Y);
            }
        }
        // END 2018/08/03 K.Kitachi [QC#25727, MOD]
    }
}

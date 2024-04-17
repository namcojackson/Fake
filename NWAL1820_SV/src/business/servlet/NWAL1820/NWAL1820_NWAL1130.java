/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820;

import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_0;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_5;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1820_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/30   Fujitsu         S.Ohki          Update          S21_NA#7861
 *</pre>
 */
public class NWAL1820_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        // 2016/05/30 S21_NA#7861 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepPsnCd, scrnMsg.P.no(IDX_0).xxComnScrColValTxt_P.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum, scrnMsg.P.no(IDX_0).xxComnScrColValTxt_P.getValue());
        // 2016/05/30 S21_NA#7861 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.slsRepTocCd, scrnMsg.P.no(IDX_5).xxComnScrColValTxt_P.getValue());
        scrnMsg.setFocusItem(scrnMsg.actlShipDt_FR);
    }
}

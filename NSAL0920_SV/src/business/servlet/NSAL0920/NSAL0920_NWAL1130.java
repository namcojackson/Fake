/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0920.constant.NSAL0920Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/18   Hitachi         N.Arai          Create          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 *</pre>
 */
public class NSAL0920_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;

        // START 2017/01/23 K.Kitachi [QC#17219, MOD]
        if (NSAL0920Constant.OPENWIN_BRANCH.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_H3, scrnMsg.I.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt_H2, scrnMsg.I.no(1).xxComnScrColValTxt);
        } else if (NSAL0920Constant.OPENWIN_SUPV.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H3, scrnMsg.I.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllPsnNm_H2, scrnMsg.I.no(1).xxComnScrColValTxt);
        }
        // END 2017/01/23 K.Kitachi [QC#17219, MOD]
    }
}

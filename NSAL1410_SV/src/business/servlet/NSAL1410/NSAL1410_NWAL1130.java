/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410_NWAL1130 extends S21CommonHandler {

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
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

        String psnCd = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
        String psnNm = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
        int index = getButtonSelectNumber();
        if (index >= 0) {
            setValue(scrnMsg.A.no(index).psnCd_A2, psnCd);
            setValue(scrnMsg.A.no(index).xxPsnNm_A2, psnNm);
        } else {
            setValue(scrnMsg.psnCd_H, psnCd);
            setValue(scrnMsg.xxPsnNm_H, psnNm);
        }
    }
}

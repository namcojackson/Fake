/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/02/03   Hitachi         T.Tomita        Update          QC#3312
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 *</pre>
 */
public class NSAL0810_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/05/25 T.Tomita [QC#8898, ADD]
        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            for (int i = 0; i < scrnMsg.X.length(); i++) {
                if ("MTR_LB_CD".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                    if (hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                        setValue(scrnMsg.A.no(index).bllgMtrLbCd_A, scrnMsg.X.no(i).xxComnScrColValTxt);
                    } else {
                        scrnMsg.A.no(index).bllgMtrLbCd_A.clear();
                    }
                } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                    if (hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                        setValue(scrnMsg.A.no(index).mtrLbDescTxt_A, scrnMsg.X.no(i).xxComnScrColValTxt);
                    } else {
                        scrnMsg.A.no(index).mtrLbDescTxt_A.clear();
                    }
                }
            }
        }
        // END 2016/05/25 T.Tomita [QC#8898, ADD]
    }
}

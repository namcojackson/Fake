/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820;

import static business.servlet.NSAL0820.constant.NSAL0820Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/02/03   Hitachi         T.Tomita        Update          QC#3312
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 *</pre>
 */
public class NSAL0820_NWAL1130 extends S21CommonHandler {

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
        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;
        int index = getButtonSelectNumber();

        if (!"CMN_Close".equals(getLastGuard())) {
            String xxScrEventNm = scrnMsg.xxScrEventNm.getValue();
            if (OPENWIN_BLLGMTR.equals(xxScrEventNm)) {
                for (int i = 0; i < scrnMsg.X.length(); i++) {
                    if ("MTR_LB_CD".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                        if (hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                            setValue(scrnMsg.A.no(index).bllgMtrLbCd_A, scrnMsg.X.no(i).xxComnScrColValTxt);
                        } else {
                            scrnMsg.A.no(index).bllgMtrLbCd_A.clear();
                        }
                    } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                        if (hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                            setValue(scrnMsg.A.no(index).mtrLbDescTxt_AB, scrnMsg.X.no(i).xxComnScrColValTxt);
                        } else {
                            scrnMsg.A.no(index).mtrLbDescTxt_AB.clear();
                        }
                    }
                }
            } else if (OPENWIN_PHYSMTR.equals(xxScrEventNm)) {
                for (int i = 0; i < scrnMsg.X.length(); i++) {
                    if ("MTR_LB_CD".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                        if (hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                            setValue(scrnMsg.A.no(index).physMtrLbCd_A, scrnMsg.X.no(i).xxComnScrColValTxt);
                        } else {
                            scrnMsg.A.no(index).physMtrLbCd_A.clear();
                        }
                    } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                        if (hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                            setValue(scrnMsg.A.no(index).mtrLbDescTxt_AP, scrnMsg.X.no(i).xxComnScrColValTxt);
                        } else {
                            scrnMsg.A.no(index).mtrLbDescTxt_AP.clear();
                        }
                    }
                }
            }
        }
        // END 2016/05/25 T.Tomita [QC#8898, ADD]
    }
}

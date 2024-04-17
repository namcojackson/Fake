/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/11   Hitachi         T.Tomita        Update          QC#569
 * 2015/11/24   Hitachi         T.Tomita        Update          QC#1038
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/02/09   Hitachi         A.Kohinata      Update          QC#947
 * 2016/07/05   Hitachi         T.Tomita        Update          QC#10464
 * 2016/07/22   Hitachi         T.Tomita        Update          QC#11161
 *</pre>
 */
public class NSAL0010_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // add start 2016/02/09 QC#947
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        // START 2016/07/05 T.Tomita [QC#10464, MOD]
        if (!"CMN_Close".equals(getLastGuard())) {
            if (OPENWIN_PREFTECH.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.prfTechCd_D, scrnMsg.Z.no(0).xxComnScrColValTxt);
                setValue(scrnMsg.techNm_D1, scrnMsg.Z.no(1).xxComnScrColValTxt);
            } else if (OPENWIN_REQTECH.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.reqTechCd_D, scrnMsg.Z.no(0).xxComnScrColValTxt);
                setValue(scrnMsg.techNm_D2, scrnMsg.Z.no(1).xxComnScrColValTxt);
            } else if (OPENWIN_NONPREFTECH.equals(scrnMsg.xxScrEventNm.getValue())) {
                int index = getButtonSelectNumber();
                setValue(scrnMsg.E.no(index).nonPrfTechCd_E, scrnMsg.Z.no(0).xxComnScrColValTxt);
                setValue(scrnMsg.E.no(index).techNm_E, scrnMsg.Z.no(1).xxComnScrColValTxt);
            } else if (OPENWIN_PRCCONTR.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.prcContrNum_D, scrnMsg.Z.no(0).xxComnScrColValTxt);
            // START 2016/07/05 T.Tomita [QC#10464, ADD]
            } else if (OPENWIN_FIELDSVCBR.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.svcBrCd_D, scrnMsg.Z.no(0).xxComnScrColValTxt);
            // END 2016/07/05 T.Tomita [QC#10464, ADD]
            }
        }
        // END 2016/07/05 T.Tomita [QC#10464, MOD]
        // add end 2016/02/09 QC#947
    }
}

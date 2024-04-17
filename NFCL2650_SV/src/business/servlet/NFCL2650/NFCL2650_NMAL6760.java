/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.LOCATION;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.FROM_CD;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.FROM_NM;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.TO_CD;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.TO_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/04/15   Fujitsu         C.Naito         Update          QC#7069
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 *</pre>
 */
public class NFCL2650_NMAL6760 extends S21CommonHandler {

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

        // [QC#7069] UPDATE start
        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        if ((FROM_CD).equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_FR, scrnMsg.P.no(0).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_F1, scrnMsg.P.no(1).xxPopPrm_P);

        } else if ((TO_CD).equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_TO, scrnMsg.P.no(0).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_T1, scrnMsg.P.no(1).xxPopPrm_P);

        } else if ((FROM_NM).equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_F2, scrnMsg.P.no(1).xxPopPrm_P);

        } else if ((TO_NM).equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_T2, scrnMsg.P.no(1).xxPopPrm_P);

        } else if ((LOCATION).equals(scrnMsg.xxScrEventNm.getValue())) {
            // START 2016/11/25 T.Murai [QC#13259, MOD]
            // ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, scrnMsg.P.no(15).xxPopPrm_P);
            // // BillToCustNm (DS_LOC_NM)
            // ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_BL, scrnMsg.P.no(3).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNum, scrnMsg.P.no(2).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm, scrnMsg.P.no(3).xxPopPrm_P);
            // END   2016/11/25 T.Murai [QC#13259, MOD]

        }
        // [QC#7069] UPDATE end

    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7000_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/08/01   Fujitsu         R.Nakamura      Update          QC#10928
 *</pre>
 */
public class NMAL7000_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        // Mod Start 2016/08/01 QC#10928
        if ("OpenWin_AccountNum".equals(scrnMsg.xxScrEventNm.getValue())
                || "OpenWin_AccountName".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.R.no(1).xxComnScrColValTxt);
        } else if ("OpenWin_CSMPNum".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.csmpNum_H1, scrnMsg.R.no(2).xxComnScrColValTxt);
        } else if ("OpenWin_SupplyPlan".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyAgmtPlnNm_H1, scrnMsg.R.no(2).xxComnScrColValTxt);
        } else if ("OpenWin_ContractName".equals(scrnMsg.xxScrEventNm.getValue()) || "OpenWin_ContractNum".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNum_H1, scrnMsg.R.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrNm_H1, scrnMsg.R.no(2).xxComnScrColValTxt);
        } else if ("OpenWin_ServiceModelName".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_H1, scrnMsg.R.no(1).xxComnScrColValTxt);
        }
        // Mod End 2016/08/01 QC#10928

        scrnMsg.xxScrEventNm.clear();
    }
}

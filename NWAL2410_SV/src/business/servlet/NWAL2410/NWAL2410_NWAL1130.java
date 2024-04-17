/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.EVNT_BRANCH;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.EVNT_BR_PSN;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.EVNT_CFS;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.EVNT_PROC_PSN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2410_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/31   Fujitsu         q10253          Create          N/A
 *</pre>
 */
public class NWAL2410_NWAL1130 extends S21CommonHandler {

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

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVNT_BRANCH.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).docMgtScanBrCd_A, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).docMgtScanBrNm_A, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).docMgtScanRgNm_A, scrnMsg.R.no(2).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).docMgtScanZnNm_A, scrnMsg.R.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).docMgtScanBrNm_A);
            } else if (EVNT_PROC_PSN.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).defOrdProcPsnCd_A, scrnMsg.R.no(0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).defBrAdminPsnCd_A);
            } else if (EVNT_BR_PSN.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).defBrAdminPsnCd_A, scrnMsg.R.no(0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).leaseCmpyProcPsnCd_A);
            } else if (EVNT_CFS.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).leaseCmpyProcPsnCd_A, scrnMsg.R.no(0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).poPendEmlAddr_A);
            }
        }
    }
}

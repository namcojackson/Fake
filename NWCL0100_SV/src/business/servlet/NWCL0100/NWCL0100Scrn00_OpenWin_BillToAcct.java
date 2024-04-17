/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_DISP_HIR_ACCT_BILL_TO_ONLY;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_DISP_RLTD_ACCT_BILL_TO_ONLY;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_SRCH_MODE_QUICK_LOOKUP;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.POPUP_PRM_STATUS_ACTV_ONLY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0100Scrn00_OpenWin_BillToAcct
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0100Scrn00_OpenWin_BillToAcct extends S21CommonHandler {

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

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxAcctSrchDplyRelnCd_P0, POPUP_PRM_DISP_RLTD_ACCT_BILL_TO_ONLY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxAcctSrchModeCd_P0, POPUP_PRM_SRCH_MODE_QUICK_LOOKUP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxChkBox_P0, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxAcctSrchDplyHrchCd_P0, POPUP_PRM_STATUS_ACTV_ONLY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxAcctSrchStsCd_P0, POPUP_PRM_DISP_HIR_ACCT_BILL_TO_ONLY);

        NWCL0100_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());
        Object[] params = new Object[13];

        params[0] = abMsg.billToCustAcctCd_A0;
        params[1] = abMsg.dsAcctNm_A0;
        params[2] = scrnMsg.P.no(0).xxPopPrm_P0; // No Used
        params[3] = scrnMsg.P.no(0).xxPopPrm_P0; // No Used
        params[4] = scrnMsg.P.no(0).xxPopPrm_P0; // No Used
        params[5] = scrnMsg.P.no(0).xxPopPrm_P0; // No Used
        params[6] = scrnMsg.P.no(0).xxPopPrm_P0; // No Used
        params[7] = scrnMsg.P.no(0).xxPopPrm_P0; // No Used
        params[8] = scrnMsg.P.no(0).xxAcctSrchDplyRelnCd_P0;
        params[9] = scrnMsg.P.no(0).xxAcctSrchModeCd_P0;
        params[10] = scrnMsg.P.no(0).xxChkBox_P0;
        params[11] = scrnMsg.P.no(0).xxAcctSrchDplyHrchCd_P0;
        params[12] = scrnMsg.P.no(0).xxAcctSrchStsCd_P0;

        setArgForSubScreen(params);
    }
}

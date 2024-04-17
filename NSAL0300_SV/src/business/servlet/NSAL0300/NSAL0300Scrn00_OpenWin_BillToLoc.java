/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#1029
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886
 * 2017/10/10   CITS            M.Naito         Update          QC#21072
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_BillToLoc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);

        // START 2016/01/07 T.Tomita [QC#1029, MOD]
//        Object[] params = new Object[21];
//        scrnMsg.xxPopPrm_00.setValue("52");
//        scrnMsg.xxPopPrm_20.setValue(ZYPConstant.FLG_ON_Y);
//        params[0] = scrnMsg.xxPopPrm_00;
//        params[1] = scrnMsg.altPayerCustCd;
//        params[2] = scrnMsg.xxPopPrm_02;
//        params[3] = scrnMsg.xxPopPrm_03;
//        params[4] = scrnMsg.dsAcctNum;
//        params[5] = scrnMsg.xxPopPrm_05;
//        params[6] = scrnMsg.billToLocNm;
//        params[7] = scrnMsg.xxPopPrm_07;
//        params[8] = scrnMsg.xxPopPrm_08;
//        params[9] = scrnMsg.xxPopPrm_09;
//        params[10] = scrnMsg.xxPopPrm_10;
//        params[11] = scrnMsg.xxPopPrm_11;
//        params[12] = scrnMsg.xxPopPrm_12;
//        params[13] = scrnMsg.xxPopPrm_13;
//        params[14] = scrnMsg.xxPopPrm_14;
//        params[15] = scrnMsg.xxPopPrm_15;
//        params[16] = scrnMsg.xxPopPrm_16;
//        params[17] = scrnMsg.xxPopPrm_17;
//        params[18] = scrnMsg.xxPopPrm_18;
//        params[19] = scrnMsg.xxPopPrm_19;
//        params[20] = scrnMsg.xxPopPrm_20;
        setValue(scrnMsg.xxScrEventNm, "OpenWin_BillToLoc");
        // START 2017/10/10 M.Naito [QC#21072, MOD]
        String dsAcctNum = null;
        if (!("%").equals(scrnMsg.dsAcctNum.getValue())) {
            dsAcctNum = scrnMsg.dsAcctNum.getValue();
        }
        setValue(scrnMsg.xxPopPrm_00, dsAcctNum);
        // END 2017/10/10 M.Naito [QC#21072, MOD]
        setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_BILL);
        // START 2017/10/10 M.Naito [QC#21072, MOD]
        String altPayerCustCd = null;
        if (!("%").equals(scrnMsg.altPayerCustCd.getValue())) {
            altPayerCustCd = scrnMsg.altPayerCustCd.getValue();
        }
        setValue(scrnMsg.xxPopPrm_15, altPayerCustCd);
        // END 2017/10/10 M.Naito [QC#21072, MOD]
        Object[] params = new Object[24];
        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[3] = scrnMsg.xxPopPrm_03;
        params[4] = scrnMsg.xxPopPrm_04;
        params[5] = scrnMsg.xxPopPrm_05;
        params[6] = scrnMsg.xxPopPrm_06;
        params[7] = scrnMsg.xxPopPrm_07;
        params[8] = scrnMsg.xxPopPrm_08;
        params[9] = scrnMsg.xxPopPrm_09;
        params[10] = scrnMsg.xxPopPrm_10;
        params[11] = scrnMsg.xxPopPrm_11;
        params[12] = scrnMsg.xxPopPrm_12;
        params[13] = scrnMsg.xxPopPrm_13;
        params[14] = scrnMsg.xxPopPrm_14;
        params[15] = scrnMsg.xxPopPrm_15;
        params[16] = scrnMsg.xxPopPrm_16;
        params[17] = scrnMsg.xxPopPrm_17;
        params[18] = scrnMsg.xxPopPrm_18;
        params[19] = scrnMsg.xxPopPrm_19;
        params[20] = scrnMsg.xxPopPrm_20;
        params[21] = scrnMsg.xxPopPrm_21;
        params[22] = scrnMsg.xxPopPrm_22;
        params[23] = scrnMsg.xxPopPrm_23;
        // END 2016/01/07 T.Tomita [QC#1029, MOD]
        setArgForSubScreen(params);
    }
}

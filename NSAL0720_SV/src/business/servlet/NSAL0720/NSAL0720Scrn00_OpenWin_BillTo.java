/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static business.servlet.NSAL0720.constant.NSAL0720Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0720.common.NSAL0720CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/08   Hitachi         T.Tsuchida      Update          QC#1607
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 * 2017/01/30   Hitachi         T.Mizuki        Update          QC#17310
 *</pre>
 */
public class NSAL0720Scrn00_OpenWin_BillTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        NSAL0720CommonLogic.clearPopupParam(scrnMsg);

        // START 2016/01/05 T.Tomita [QC#1029, MOD]
//        Object[] params = new Object[21];
        // mod start 2017/01/30 CSA QC#17310
//        Object[] params = new Object[24];
        Object[] params = new Object[26];
//        scrnMsg.xxPopPrm_00.setValue("52");
        setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_BILL);
//        scrnMsg.xxPopPrm_20.setValue(ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(0).dsAcctNum_A1)) {
            params[0] = scrnMsg.A.no(0).dsAcctNum_A1;
        } else{
            params[0] = scrnMsg.xxPopPrm_00;
        }
        // mod end 2017/01/30 CSA QC#17310
//        if (rowNum == -1) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.billToCustCd_H1);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.locNm_H1);
//        } else {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.A.no(rowNum).billToCustCd_A2);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.A.no(rowNum).locNm_A1);
//        }
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
//        params[15] = scrnMsg.xxPopPrm_15;
        if (rowNum == -1) {
            params[15] = scrnMsg.billToCustCd_H1;
        } else {
            params[15] = scrnMsg.A.no(rowNum).billToCustCd_A2;
        }
        params[16] = scrnMsg.xxPopPrm_16;
        params[17] = scrnMsg.xxPopPrm_17;
        params[18] = scrnMsg.xxPopPrm_18;
        params[19] = scrnMsg.xxPopPrm_19;
        params[20] = scrnMsg.xxPopPrm_20;
        params[21] = scrnMsg.xxPopPrm_21;
        params[22] = scrnMsg.xxPopPrm_22;
        params[23] = scrnMsg.xxPopPrm_23;
        // mod start 2017/01/30 CSA QC#17310
        params[24] = scrnMsg.xxPopPrm_24;
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(0).dsAcctNum_A1)) {
            setValue(scrnMsg.xxPopPrm_25, ZYPConstant.FLG_OFF_N);
        }
        params[25] = scrnMsg.xxPopPrm_25;
        // mod end 2017/01/30 CSA QC#17310
        // END 2016/01/05 T.Tomita [QC#1029, MOD]
        setArgForSubScreen(params);

    }
}

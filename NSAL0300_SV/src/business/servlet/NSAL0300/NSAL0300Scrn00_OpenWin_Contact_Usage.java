/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Hitachi         T.Kanasaka      Create          N/A
 * 2016/03/04   Hitachi         T.Tomita        Update          QC#3048
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Contact_Usage extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/03/04 T.Tomita [QC#3048, MOD]
//        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        int rowNum = getButtonSelectNumber();
//        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(rowNum).usgBillToCustCd_B)) {
//            scrnMsg.B.no(rowNum).usgBillToCustCd_B.setErrorInfo(1, ZZM9000E, new String[] {"Bill To" });
//        }
//        scrnMsg.addCheckItem(scrnMsg.B.no(rowNum).usgBillToCustCd_B);
//        scrnMsg.putErrorScreen();
        return;
        // END 2016/03/04 T.Tomita [QC#3048, MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/03/04 T.Tomita [QC#3048, MOD]
//        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
//        scrnMsg.xxScrEventNm.setValue("OpenWin_Contact_Usage");
//        int rowNum = getButtonSelectNumber();
//
//        Object[] params = new Object[20];
//        scrnMsg.xxPopPrm_11.setValue(ZYPConstant.FLG_OFF_N);
//        scrnMsg.xxPopPrm_12.setValue(ZYPConstant.FLG_OFF_N);
//        scrnMsg.xxPopPrm_13.setValue(ZYPConstant.FLG_OFF_N);
//        params[0] = scrnMsg.xxPopPrm_00;
//        params[1] = scrnMsg.xxPopPrm_01;
//        params[2] = scrnMsg.xxPopPrm_02;
//        params[3] = scrnMsg.xxPopPrm_03;
//        params[4] = scrnMsg.xxPopPrm_04;
//        params[5] = scrnMsg.B.no(rowNum).usgBillToCustCd_B;
//        params[6] = scrnMsg.xxPopPrm_06;
//        params[7] = scrnMsg.xxPopPrm_07;
//        params[8] = scrnMsg.xxPopPrm_08;
//        params[9] = scrnMsg.xxPopPrm_09;
//        params[10] = scrnMsg.xxPopPrm_10;
//        params[11] = scrnMsg.xxPopPrm_11;
//        params[12] = scrnMsg.xxPopPrm_12;
//        params[13] = scrnMsg.xxPopPrm_13;
//        params[14] = scrnMsg.xxPopPrm_14;
//        params[15] = scrnMsg.ctacPsnPk_15;
//        params[16] = scrnMsg.ctacPsnPk_16;
//        params[17] = scrnMsg.ctacPsnPk_17;
//        params[18] = new ArrayList<Object>();
//        params[19] = scrnMsg.B.no(rowNum).ctacPsnPk_BU;
//        setArgForSubScreen(params);
        return;
        // END 2016/03/04 T.Tomita [QC#3048, MOD]
    }
}

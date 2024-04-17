/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/13   Hitachi         T.Tomita        Update          QC#647
 * 2016/01/04   Hitachi         T.Tomita        Update          QC#1029
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_OwnerAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CommonLogic.clearPopupParameter(scrnMsg);
        // START 2016/01/04 T.Tomita [QC#1029, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, OPENWIN_OWNERACCT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.ownrAcctNum_M1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, PARAMS_BILL_TO_ONLY);
        // START 2016/05/16 T.Tomita [QC#4578, DEL]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_15, scrnMsg.ownrLocNum_M1);
        // END 2016/05/16 T.Tomita [QC#4578, DEL]
        // END 2016/01/04 T.Tomita [QC#1029, ADD]
        // START 2016/01/04 T.Tomita [QC#1029, MOD]
//        Object[] params = new Object[21];
        Object[] params = new Object[24];
      // END 2016/01/04 T.Tomita [QC#1029, MOD]
        // START 2015/11/13 T.Tomita [QC#647, MOD]
        // START 2016/01/04 T.Tomita [QC#1029, DEL]
//        scrnMsg.xxPopPrm_00.setValue(DS_ACCT_RELN_TP.BILL_TO);
//        scrnMsg.xxPopPrm_01.setValue(scrnMsg.ownrLocNum_M1.getValue());
//        scrnMsg.xxPopPrm_04.setValue(scrnMsg.ownrAcctNum_M1.getValue());
//        scrnMsg.xxPopPrm_20.setValue(ZYPConstant.FLG_ON_Y);
        // END 2016/01/04 T.Tomita [QC#1029, DEL]

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
        // START 2016/01/04 T.Tomita [QC#1029, ADD]
        params[21] = scrnMsg.xxPopPrm_21;
        params[22] = scrnMsg.xxPopPrm_22;
        params[23] = scrnMsg.xxPopPrm_23;
        // END 2016/01/04 T.Tomita [QC#1029, ADD]
        // END 2015/11/13 T.Tomita [QC#647, MOD]
        setArgForSubScreen(params);
    }
}

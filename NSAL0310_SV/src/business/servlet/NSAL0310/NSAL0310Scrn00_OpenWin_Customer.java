/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import static business.servlet.NSAL0310.constant.NSAL0310Constant.DISP_HRCH_ACCT_CD_ALL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0310.common.NSAL0310CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/10/30   Hitachi         K.Kim           Create          QC#28890
 *</pre>
 */
public class NSAL0310Scrn00_OpenWin_Customer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CommonLogic.clearPopupParameter(scrnMsg);
        scrnMsg.xxScrEventNm.setValue("OpenWin_Customer");

        String dsAcctNum = null;
        if (!("%").equals(scrnMsg.dsAcctNum_H.getValue())) {
            dsAcctNum = scrnMsg.dsAcctNum_H.getValue();
        }
        setValue(scrnMsg.xxPopPrm_00, dsAcctNum);
        setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_ALL);
        Object[] prm = new Object[24];
        prm[0] = scrnMsg.xxPopPrm_00;
        prm[1] = scrnMsg.xxPopPrm_01;
        prm[2] = scrnMsg.xxPopPrm_02;
        prm[3] = scrnMsg.xxPopPrm_03;
        prm[4] = scrnMsg.xxPopPrm_04;
        prm[5] = scrnMsg.xxPopPrm_05;
        prm[6] = scrnMsg.xxPopPrm_06;
        prm[7] = scrnMsg.xxPopPrm_07;
        prm[8] = scrnMsg.xxPopPrm_08;
        prm[9] = scrnMsg.xxPopPrm_09;
        prm[10] = scrnMsg.xxPopPrm_10;
        prm[11] = scrnMsg.xxPopPrm_11;
        prm[12] = scrnMsg.xxPopPrm_12;
        prm[13] = scrnMsg.xxPopPrm_13;
        prm[14] = scrnMsg.xxPopPrm_14;
        prm[15] = scrnMsg.xxPopPrm_15;
        prm[16] = scrnMsg.xxPopPrm_16;
        prm[17] = scrnMsg.xxPopPrm_17;
        prm[18] = scrnMsg.xxPopPrm_18;
        prm[19] = scrnMsg.xxPopPrm_19;
        prm[20] = scrnMsg.xxPopPrm_20;
        prm[21] = scrnMsg.xxPopPrm_21;
        prm[22] = scrnMsg.xxPopPrm_22;
        prm[23] = scrnMsg.xxPopPrm_23;
        setArgForSubScreen(prm);
    }

}

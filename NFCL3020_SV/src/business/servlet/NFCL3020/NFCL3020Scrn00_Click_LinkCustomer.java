/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/17   Fujitsu         H.Ikeda         Create          QC#22759
 *</pre>
 */
public class NFCL3020Scrn00_Click_LinkCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        scrnMsg.xxPopPrm_24.clear();

        Object[] param = new Object[25];
        param[0] = scrnMsg.payerCustCd_BH;
        param[1] = scrnMsg.dsAcctNm_BH;
        param[2] = scrnMsg.locNum_BH;
        param[3] = scrnMsg.xxPopPrm_03;
        param[4] = scrnMsg.xxPopPrm_04;
        param[5] = scrnMsg.xxPopPrm_05;
        param[6] = scrnMsg.xxPopPrm_06;
        param[7] = scrnMsg.xxPopPrm_07;
        param[8] = scrnMsg.xxPopPrm_08;
        param[9] = scrnMsg.xxPopPrm_09;
        param[10] = scrnMsg.xxPopPrm_10;
        param[11] = scrnMsg.xxPopPrm_11;        
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, "02"); // Display Hierarchy Accounts Code: 02
        param[12] = scrnMsg.xxPopPrm_12;
        param[13] = scrnMsg.xxPopPrm_13;
        param[14] = scrnMsg.xxPopPrm_14;
        param[15] = scrnMsg.xxPopPrm_15;
        param[16] = scrnMsg.xxPopPrm_16;
        param[17] = scrnMsg.xxPopPrm_17;
        param[18] = scrnMsg.xxPopPrm_18;
        param[19] = scrnMsg.xxPopPrm_19;
        param[20] = scrnMsg.xxPopPrm_20;
        param[21] = scrnMsg.xxPopPrm_21;
        param[22] = scrnMsg.xxPopPrm_22;
        param[23] = scrnMsg.xxPopPrm_23;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_24, ZYPConstant.FLG_OFF_N);
        param[24] = scrnMsg.xxPopPrm_24;

        setArgForSubScreen(param);
        
    }
}

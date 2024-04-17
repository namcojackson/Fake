/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import static business.servlet.NFCL3030.common.NFCL3030CommonLogic.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/09/02   Hitachi         T.Tsuchida      Update          QC#14090
 * 10/17/2016   Hitachi         E.Kameishi      Update          QC#14814
 * 10/18/2016   Hitachi         E.Kameishi      Update          QC#14814
 * 02/06/2018   Fujitsu         T.Murai         Update          QC#21372
 *</pre>
 */
public class NFCL3030Scrn00_Click_LinkCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        // START 2018/02/06 [QC#21372,DEL]
//        if(!ZYPCommonFunc.hasValue(scrnMsg.payerCustCd_H.getValue())) {
//            if(ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H.getValue())) {
//                scrnMsg.dsAcctNm_H.clear();
//            }
//        }
        // END   2018/02/06 [QC#21372,DEL]
        // START 2016/10/18 E.Kameishi [QC#14814, ADD]
        scrnMsg.locNm_H.clear();
//        if(!ZYPCommonFunc.hasValue(scrnMsg.locNum_H.getValue())) {
//            if(ZYPCommonFunc.hasValue(scrnMsg.locNm_H.getValue())) {
//                scrnMsg.locNm_H.clear();
//            }
//        }
        // END 2016/10/18 E.Kameishi [QC#14814, ADD]
        // START 2016/10/17 E.Kameishi [QC#14814, ADD]
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
        // END 2016/10/17 E.Kameishi [QC#14814, ADD]

        //NMAL6760
        // START 2016/09/02 T.Tsuchida [QC#14090,MOD]
//        Object[] param = new Object[15];
//        param[0] = scrnMsg.payerCustCd_H;
//        param[1] = scrnMsg.dsAcctNm_H;
//        param[2] = scrnMsg.locNum_H;
//        param[3] = scrnMsg.locNm_H;
//        param[4] = scrnMsg.xxPopPrm_01;
//        param[5] = scrnMsg.xxPopPrm_02;
//        param[6] = scrnMsg.xxPopPrm_03;
//        param[7] = scrnMsg.xxPopPrm_04;
//        param[8] = scrnMsg.xxPopPrm_05;
//        param[9] = scrnMsg.xxPopPrm_06;
//        param[10] = scrnMsg.xxPopPrm_07;
//        param[11] = scrnMsg.xxPopPrm_08;
//        param[12] = scrnMsg.xxPopPrm_09;
//        param[13] = scrnMsg.xxPopPrm_10;
//        param[14] = scrnMsg.xxPopPrm_11;
        Object[] param = new Object[25];
        param[0] = scrnMsg.payerCustCd_H;
        param[1] = scrnMsg.dsAcctNm_H;
        param[2] = scrnMsg.locNum_H;
        param[3] = scrnMsg.locNm_H;
        param[4] = scrnMsg.xxPopPrm_01;
        param[5] = scrnMsg.xxPopPrm_02;
        param[6] = scrnMsg.xxPopPrm_03;
        param[7] = scrnMsg.xxPopPrm_04;
        param[8] = scrnMsg.xxPopPrm_05;
        param[9] = scrnMsg.xxPopPrm_06;
        param[10] = scrnMsg.xxPopPrm_07;
        param[11] = scrnMsg.xxPopPrm_08;        
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, DISP_HRCH_ACCT_CD_BILL);
        param[12] = scrnMsg.xxPopPrm_09;
        param[13] = scrnMsg.xxPopPrm_10;
        param[14] = scrnMsg.xxPopPrm_11;
        param[15] = scrnMsg.xxPopPrm_11;
        param[16] = scrnMsg.xxPopPrm_12;
        param[17] = scrnMsg.xxPopPrm_13;
        param[18] = scrnMsg.xxPopPrm_14;
        param[19] = scrnMsg.xxPopPrm_15;
        param[20] = scrnMsg.xxPopPrm_16;
        param[21] = scrnMsg.xxPopPrm_17;
        param[22] = scrnMsg.xxPopPrm_18;
        param[23] = scrnMsg.xxPopPrm_19;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_20, ZYPConstant.FLG_OFF_N);
        param[24] = scrnMsg.xxPopPrm_20;
        // END 2016/09/02 T.Tsuchida [QC#14090,MOD]

        setArgForSubScreen(param);

    }
}

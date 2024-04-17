/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_Memo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, SVC_MEMO_CATG.MACHINE_MEMO);
//        scrnMsg.xxPopPrm_01.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NSAL0010Constant.CONFIG_NUM_TITLE);
//        if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_H1)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.svcConfigMstrPk_H1.getValue().toString());
//        } else {
//            scrnMsg.xxPopPrm_03.clear();
//        }
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, NSAL0010Constant.CONFIG_TP_TITLE);
//        scrnMsg.xxPopPrm_05.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, NSAL0010Constant.SERIAL_NUM_TITLE);
//        scrnMsg.xxPopPrm_07.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, NSAL0010Constant.MDSE_CD_TITLE);
//        scrnMsg.xxPopPrm_09.clear();
//        scrnMsg.xxPopPrm_10.clear();
//        scrnMsg.xxPopPrm_11.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, "SVC_MACH_MSTR_PK");
//        if (ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_13, scrnMsg.svcMachMstrPk_H1.getValue().toString());
//        } else {
//            scrnMsg.xxPopPrm_13.clear();
//        }
//        scrnMsg.xxPopPrm_14.clear();
//        scrnMsg.xxPopPrm_15.clear();
//        scrnMsg.xxPopPrm_16.clear();
//        scrnMsg.xxPopPrm_17.clear();
//        scrnMsg.xxPopPrm_18.clear();
//        scrnMsg.xxPopPrm_19.clear();
//        scrnMsg.xxPopPrm_20.clear();
//        scrnMsg.xxPopPrm_21.clear();
//        scrnMsg.xxPopPrm_22.clear();
//        
//        Object[] params = new Object[22];
//        params[0] = scrnMsg.xxPopPrm_00;
//        params[1] = scrnMsg.xxPopPrm_01;
//        params[2] = scrnMsg.xxPopPrm_02;
//        params[3] = scrnMsg.xxPopPrm_03;
//        params[4] = scrnMsg.xxPopPrm_04;
//        params[5] = scrnMsg.svcConfigTpCd_H1;
//        params[6] = scrnMsg.xxPopPrm_06;
//        params[7] = scrnMsg.serNum_H1;
//        params[8] = scrnMsg.xxPopPrm_08;
//        params[9] = scrnMsg.mdseCd_H1;
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
//        params[21] = scrnMsg.xxPopPrm_21;
//
//        setArgForSubScreen(params);
    }
}

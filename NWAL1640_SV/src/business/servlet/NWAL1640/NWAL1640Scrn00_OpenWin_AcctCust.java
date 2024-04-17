/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640Scrn00_OpenWin_AcctCust
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/01/25   Hitachi         T.Tomita        Update          CSA QC#1029
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640Scrn00_OpenWin_AcctCust extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);

        // START 2016/01/25 T.Tomita [QC#1029, MOD]
//        scrnMsg.P.no(0).xxPopPrm_P.setValue(BILL_TO_SHIP_TO);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm_P, scrnMsg.splyNm);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, scrnMsg.splyFirstAddr);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(13).xxPopPrm_P, scrnMsg.splyCtyAddr);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(16).xxPopPrm_P, scrnMsg.splyStCd);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(17).xxPopPrm_P, scrnMsg.splyPostCd);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(20).xxPopPrm_P, ZYPConstant.FLG_OFF_N);
        // 2019/10/04 S21_NA#51372 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.splyNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.prntVndNm);
        // 2019/10/04 S21_NA#51372 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm_P, scrnMsg.splyFirstAddr);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm_P, scrnMsg.splyCtyAddr);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm_P, scrnMsg.splyStCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm_P, scrnMsg.splyPostCd);
        // END 2016/01/25 T.Tomita [QC#1029, MOD]

        Object[] param = new Object[24];
        param[0] = scrnMsg.P.no(0).xxPopPrm_P;
        param[1] = scrnMsg.P.no(1).xxPopPrm_P;
        param[2] = scrnMsg.P.no(2).xxPopPrm_P;
        param[3] = scrnMsg.P.no(3).xxPopPrm_P;
        param[4] = scrnMsg.P.no(4).xxPopPrm_P;
        param[5] = scrnMsg.P.no(5).xxPopPrm_P;
        param[6] = scrnMsg.P.no(6).xxPopPrm_P;
        param[7] = scrnMsg.P.no(7).xxPopPrm_P;
        param[8] = scrnMsg.P.no(8).xxPopPrm_P;
        param[9] = scrnMsg.P.no(9).xxPopPrm_P;
        param[10] = scrnMsg.P.no(10).xxPopPrm_P;
        param[11] = scrnMsg.P.no(11).xxPopPrm_P;
        param[12] = scrnMsg.P.no(12).xxPopPrm_P;
        param[13] = scrnMsg.P.no(13).xxPopPrm_P;
        param[14] = scrnMsg.P.no(14).xxPopPrm_P;
        param[15] = scrnMsg.P.no(15).xxPopPrm_P;
        param[16] = scrnMsg.P.no(16).xxPopPrm_P;
        param[17] = scrnMsg.P.no(17).xxPopPrm_P;
        param[18] = scrnMsg.P.no(18).xxPopPrm_P;
        param[19] = scrnMsg.P.no(19).xxPopPrm_P;
        param[20] = scrnMsg.P.no(20).xxPopPrm_P;
        // START 2016/01/25 T.Tomita [QC#1029, ADD]
        param[21] = scrnMsg.P.no(21).xxPopPrm_P;
        param[22] = scrnMsg.P.no(22).xxPopPrm_P;
        param[23] = scrnMsg.P.no(23).xxPopPrm_P;
        // END 2016/01/25 T.Tomita [QC#1029, ADD]

        setArgForSubScreen(param);
    }
}

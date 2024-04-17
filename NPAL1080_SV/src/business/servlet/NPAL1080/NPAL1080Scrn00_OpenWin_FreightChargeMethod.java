///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//
//import business.servlet.NPAL1080.constant.NPAL1080Constant.NMAL6050_PARAM;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
//import static business.servlet.NPAL1080.constant.NPAL1080Constant.*;
//
///**
// * PR Entry : NPAL1080Scrn00_OpenWin_FreightChargeMethod
// * 
// * <pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2013/05/01   Hitachi         T.Arakawa       Create          QC1093
// *</pre>
// */
//public class NPAL1080Scrn00_OpenWin_FreightChargeMethod extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        // do nothing
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//        ZYPTableUtil.clear(scrnMsg.P);
//
//        Object[] params = new Object[NMAL6050_PARAM.values().length];
//        for (int i = 0; i < params.length; i++) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i).xxPopPrm, NMAL6050_PARAM_VALUES_FRT_CHRG_METH[i]);
//            params[i] = scrnMsg.P.no(i).xxPopPrm;
//        }
//
//        final int i = NMAL6050_PARAM.COND_CD.ordinal();
//        final int n = getButtonSelectNumber();
//        if (0 <= n) {
//            // Detail button
//            params[i] = scrnMsg.A.no(n).frtChrgMethCd_D1;
//        } else {
//            // Detail header link anchor
//            params[i] = scrnMsg.frtChrgMethCd_DH;
//        }
//
//        setArgForSubScreen(params);
//    }
//}

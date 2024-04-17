///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2013/03/25   Hitachi         T.Kanasaka      Create          QC847
// * 2015/03/25   Fujitsu         T.Nishikawa     Update          CSA
// *</pre>
// */
//public class NPAL1080_NWAL0140 extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        // Start Mod 2015/03/25 CSA T.Nishikawa
////        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
////        NPAL1080CommonLogic.commonControl(this, scrnMsg);
////        scrnMsg.setFocusItem(scrnMsg.shipToFlg_AC);
//        
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        
//        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H1)) {
//            // keep previously selected ship to customer code
//            scrnMsg.shipToCustCd_H2.setValue(scrnMsg.shipToCustCd_H1.getValue());
//            scrnMsg.shipToFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
//        } else {
//            scrnMsg.shipToCustCd_H2.clear();
//            scrnMsg.shipToFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
//        }
//        
//        scrnMsg.setFocusItem(scrnMsg.shipToCustCd_H1);
//        // End Mod 2015/03/25 CSA T.Nishikawa
//    }
//}

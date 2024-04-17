///**
// * Copyright(c)2012 Canon USA Inc. All rights reserved.
// */
//package business.servlet.NPAL1080;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1080.NPAL1080CMsg;
//import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
//import business.servlet.NPAL1080.constant.NPAL1080Constant;
//
//import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
//import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * PR Entry  NPAL1080Scrn00_OpenWin_LocTo
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2012/11/16   Hitachi        Y.Nishiwaki         Create          N/A
// * 2013/05/22   Hitachi        T.Arakawa           Update          QC1209
// * 2013/06/19   Hitachi        T.Arakawa           Update          QC1209
// * 2015/03/24   Fujitsu        T.Nishikawa         Update          CSA
// *</pre>
// */
//public class NPAL1080Scrn00_OpenWin_LocTo extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        //do nothing
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        // Start Mod 2015/03/24 CSA T.Nishikawa
////        // QC1209-20130619-MOD-S
////        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
////
////        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getClass().getSimpleName());
////        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocRoleTpCdListTxt_P0, NPAL1080CommonLogic.getToLocTp());
////
////        return NPAL1080CommonLogic.setRequestData(scrnMsg, FUNC_SEARCH);
////        // QC1209-20130619-MOD-E
//        
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getClass().getSimpleName());
//        return NPAL1080CommonLogic.setRequestData(scrnMsg, FUNC_SEARCH);
//        // End Mod 2015/03/24 CSA T.Nishikawa
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        // Start Mod 2015/03/24 CSA T.Nishikawa
////        // QC1209-20130619-MOD-S
////        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
////        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
////
////        EZDMsg.copy(bizMsg, null, scrnMsg, null);
////
////        // Set transition parameters
////        Object[] params = NPAL1080CommonLogic.getTransParamsForNPAL1010(scrnMsg, scrnMsg.toInvtyLocCd_H1);
////        setArgForSubScreen(params);
////
////        //common control
////        NPAL1080CommonLogic.commonControl(this, scrnMsg);
////
////        //focus
////        scrnMsg.setFocusItem(scrnMsg.toInvtyLocCd_H1);
////        // QC1209-20130619-MOD-E
//        // End Mod 2015/03/24 CSA T.Nishikawa
//        
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        scrnMsg.P.clear();
//
//        int idx = 0;
//
//        scrnMsg.P.no(idx++).xxPopPrm.setValue(scrnMsg.rqstInvtyLocCd_H1.getValue());
//        scrnMsg.P.no(idx++).xxPopPrm.clear();
//        List<String> locTpCdList = new ArrayList<String>();
//        locTpCdList.add(LOC_TP.TECHNICIAN);
//        scrnMsg.P.no(idx++).xxPopPrm.setValue(NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(), BIZ_ID, locTpCdList));
//        scrnMsg.P.no(idx++).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
//        scrnMsg.P.no(idx++).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
//        scrnMsg.P.no(idx++).xxPopPrm.clear();
//        scrnMsg.P.no(idx++).xxPopPrm.clear();
//        scrnMsg.P.no(idx++).xxPopPrm.clear();
//        scrnMsg.P.no(idx++).xxPopPrm.clear();
//        scrnMsg.P.no(idx++).xxPopPrm.clear();
//        scrnMsg.P.setValidCount(idx);
//
//        Object[] param = new Object[idx];
//        for (int i = 0; i < idx; i++) {
//            param[i] = scrnMsg.P.no(i).xxPopPrm;
//        }
//
//        setArgForSubScreen(param);
//    }
//}

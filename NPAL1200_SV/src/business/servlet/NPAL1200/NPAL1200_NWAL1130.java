///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NPAL1200;
//
//import parts.common.EZDAbendException;
//import parts.common.EZDBItem;
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NPAL1200.common.NPAL1200CommonLogic;
//import business.servlet.NPAL1200.constant.NPAL1200Constant;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2015/04/07   Fujitsu         T.Nishikawa     Create          CSA
// *</pre>
// */
//public class NPAL1200_NWAL1130 extends S21CommonHandler {
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
//        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
//        // NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;
//        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        EZDBItem rtlWhCd = null;
//        EZDBItem rtlWhNm = null;
//
//        if (NPAL1200CommonLogic.isEventFrom(scrnMsg, NPAL1200Scrn00_OpenWinFromWarehouseH.class)) {
//            rtlWhCd = scrnMsg.rtlWhCd_H1;
//            rtlWhNm = scrnMsg.rtlWhNm_H1;
//
//        } else if (NPAL1200CommonLogic.isEventFrom(scrnMsg, NPAL1200Scrn00_OpenWinToWarehouseH.class)) {
//            rtlWhCd = scrnMsg.rtlWhCd_H2;
//            rtlWhNm = scrnMsg.rtlWhNm_H2;
//
//        } else if (NPAL1200CommonLogic.isEventFrom(scrnMsg, NPAL1200Scrn00_OpenWinFromWarehouseD.class)) {
//            rtlWhCd = scrnMsg.rtlWhCd_D1;
//            rtlWhNm = scrnMsg.rtlWhNm_D1;
//
//        } else if (NPAL1200CommonLogic.isEventFrom(scrnMsg, NPAL1200Scrn00_OpenWinToWarehouseD.class)) {
//            rtlWhCd = scrnMsg.rtlWhCd_D2;
//            rtlWhNm = scrnMsg.rtlWhNm_D2;
//
//        } else {
//            throw new EZDAbendException();
//        }
//
//        for (int i = 0; i < scrnMsg.S.length(); i++) {
//            if (NPAL1200Constant.POPUP_WH_CD_COL_NM.equals(scrnMsg.S.no(i).xxComnScrQueryColNm.getValue())) {
//                ZYPEZDItemValueSetter.setValue(rtlWhCd, scrnMsg.S.no(i).xxComnScrColValTxt);
//                scrnMsg.setFocusItem(rtlWhCd);
//
//            } else if (NPAL1200Constant.POPUP_WH_NM_COL_NM.equals(scrnMsg.S.no(i).xxComnScrQueryColNm.getValue())) {
//                ZYPEZDItemValueSetter.setValue(rtlWhNm, scrnMsg.S.no(i).xxComnScrColValTxt);
//            }
//        }
//
//        scrnMsg.setFocusItem(rtlWhCd);
//    }
//}

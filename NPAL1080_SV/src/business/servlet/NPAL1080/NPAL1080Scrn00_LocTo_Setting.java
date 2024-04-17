///**
// * Copyright(c)2012 Canon USA Inc. All rights reserved.
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1080.NPAL1080CMsg;
//import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
//import business.servlet.NPAL1080.constant.NPAL1080Constant;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
///**
// * <pre>
// * PR Entry  NPAL1080Scrn00_LocTo_Setting
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2012/11/16   Hitachi         Y.Nishiwaki        Create          N/A
// * </pre>
// */
//public class NPAL1080Scrn00_LocTo_Setting extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.toInvtyLocCd_H1);
//        scrnMsg.putErrorScreen();
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        NPAL1080CommonLogic.commonControl(this, scrnMsg);
//        scrnMsg.addCheckItem(scrnMsg.toInvtyLocCd_H1);
//        scrnMsg.putErrorScreen();
//        if (MESSAGE_KIND_ERR.equals(bizMsg.getMessageKind())) {
//            scrnMsg.invtyLocNm_H1.clear();
//            return;
//        }
//
//        scrnMsg.setFocusItem(scrnMsg.toInvtyLocCd_H1);
//    }
//
//}

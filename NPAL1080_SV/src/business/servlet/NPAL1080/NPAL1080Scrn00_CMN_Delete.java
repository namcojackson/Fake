///**
// * Copyright(c)2012 Canon USA Inc. All rights reserved.
// */
//package business.servlet.NPAL1080;
//
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDFieldErrorException;
//import parts.common.EZDMessageInfo;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1080.NPAL1080CMsg;
//import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
//import business.servlet.NPAL1080.constant.NPAL1080Constant;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
///**
// * <pre>
// * PR Entry  NPAL1080Scrn00_CMN_Delete
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2012/11/16   Hitachi         Y.Nishiwaki        Create          N/A
// * 2015/03/26   Fujitsu         T.Nishikawa        Update          CSA
// * </pre>
// */
//public class NPAL1080Scrn00_CMN_Delete extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        // do nothing
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode("40");
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
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        
//        // Start Mod 2015/03/26 CSA T.Nishikawa
////        NPAL1080CommonLogic.commonControl(this, scrnMsg);
//        NPAL1080CommonLogic.controlForPostSearch(this, scrnMsg);
//        // End Mod 2015/03/26 CSA T.Nishikawa
//        
//        scrnMsg.putErrorScreen();
//        // BLAP error return
//        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//            throw new EZDFieldErrorException();
//        }
//
//        scrnMsg.setFocusItem(scrnMsg.prchReqNum_H1);
//    }
//}

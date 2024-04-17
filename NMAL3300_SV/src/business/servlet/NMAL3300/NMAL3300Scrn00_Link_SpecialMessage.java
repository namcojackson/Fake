///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NMAL3300;
//
//import static business.servlet.NMAL3300.constant.NMAL3300Constant.DPLY_TAB_COLLAPSE;
//import static business.servlet.NMAL3300.constant.NMAL3300Constant.DPLY_TAB_EXPAND;
//import static business.servlet.NMAL3300.constant.NMAL3300Constant.SCREEN_ID_00;
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2013/03/26   CUSA            Fujitsu         Create          N/A
// *</pre>
// */
//public class NMAL3300Scrn00_Link_SpecialMessage extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        //NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        //NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
//
//        //NMAL3300CMsg bizMsg = new NMAL3300CMsg();
//        //bizMsg.setBusinessID("NMAL3300");
//        //bizMsg.setFunctionCode("20");
//        //EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        //return bizMsg;
//
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
//        if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_D.getValue())) {
//            scrnMsg.xxDplyTab_D.setValue(DPLY_TAB_COLLAPSE);
//        } else {
//            scrnMsg.xxDplyTab_D.setValue(DPLY_TAB_EXPAND);
//        }
//        scrnMsg.clearAllGUIAttribute(SCREEN_ID_00);
//    }
//}

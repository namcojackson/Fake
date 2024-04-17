///**
// * Copyright(c)2012 Canon USA Inc. All rights reserved.
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
//import business.servlet.NPAL1080.constant.NPAL1080Constant;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
///**
// *<pre>
// * PR Entry  NPAL1080Scrn00_OpenWin_MdseCode
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2012/11/16   Hitachi        Y.Nishiwaki         Create          N/A
// *</pre>
// */
//public class NPAL1080Scrn00_OpenWin_MdseCode extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        //do nothing
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CommonLogic.clearPopUpParam(scrnMsg);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_0).xxPopPrm, scrnMsg.mdseCd_DH);
//        Object[] params = NPAL1080CommonLogic.toArray_popup(scrnMsg.P, POP_PAR_2);
//        setArgForSubScreen(params);
//
//        NPAL1080CommonLogic.commonControl(this, scrnMsg);
//        scrnMsg.setFocusItem(scrnMsg.mdseCd_DH);
//    }
//
//}

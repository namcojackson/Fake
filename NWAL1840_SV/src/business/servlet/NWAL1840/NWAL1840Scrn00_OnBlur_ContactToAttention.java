///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NWAL1840;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//
//import business.servlet.NWAL1840.common.NWAL1840CommonLogic;
//
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2017/10/18   Fujitsu         S.Yamamoto      Update          S21_NA#20246
// * 2017/12/25   Fujitsu         K.Ishizuka      Delete          S21_NA#20164
// *</pre>
// */
//public class NWAL1840Scrn00_OnBlur_ContactToAttention extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        int row = getButtonSelectNumber();
//
//        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
//        NWAL1840CommonLogic.setContactToAttention(scrnMsg, row);
//
//    }
//}

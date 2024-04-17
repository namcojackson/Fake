///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NMAL6820;
//
//import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
//import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
//import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
//import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;
//import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_ACCT;
//
//import java.util.List;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6820.NMAL6820CMsg;
//import business.servlet.NMAL6820.common.NMAL6820CommonLogic;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 11/25/2015   CITS            M.Ito           Create          N/A
// *</pre>
// */
//public class NMAL6820Scrn00_TAB_Accounting extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//
//        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
//        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_H1);
//
//        scrnMsg.putErrorScreen();
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//
//        NMAL6820CMsg bizMsg = new NMAL6820CMsg();
//        bizMsg.setBusinessID(BIZ_APP_ID);
//        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
//
//        // clear html attribute
//        scrnMsg.clearAllGUIAttribute(SCRN_ID);
//
//        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {
//
//            // column and button input protection
//            NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
//        } else {
//
//            // column and button input protection
//            NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
//        }
//
//        // Tab focus
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ACCT);
//
//        // cursor focus
//        for (int i = 0; i < scrnMsg.B.length(); i++) {
//            scrnMsg.setFocusItem(scrnMsg.B.no(i).coaBrCd_AC);
//            break;
//        }
//    }
//}

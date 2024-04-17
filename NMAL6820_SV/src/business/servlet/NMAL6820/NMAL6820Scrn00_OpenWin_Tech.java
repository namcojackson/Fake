///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NMAL6820;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NMAL6820.common.NMAL6820CommonLogic;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Business ID : NMAL6820 Warehouse Setup
// * Function Name : Open Technician Search Popup(NMAL6050)
// * 
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 11/25/2015   CITS            M.Ito           Create          N/A
// *</pre>
// */
//public class NMAL6820Scrn00_OpenWin_Tech extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        // There is no processing.
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        // There is no processing.
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//
//        // Initialization of subscreen delivery information
//        NMAL6820CommonLogic.setInitParamForTechTocPopup(scrnMsg);
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd_G1, scrnMsg.techTocCd_H1);
//
//        // Making of subscreen delivery information
//        Object[] params = NMAL6820CommonLogic.setParamForTechTocPopup(scrnMsg);
//
//        // Subscreen transition
//        setArgForSubScreen(params);
//    }
//}

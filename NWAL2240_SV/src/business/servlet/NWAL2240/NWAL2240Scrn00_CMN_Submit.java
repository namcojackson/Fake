// Delete because it is unused.
///*
// * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NWAL2240;
//
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.addCheckItemBizLayerErr;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.checkFormatItem;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.checkNameMandatory;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.convTimeforScreen;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.getScrnTm;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setAppFracDigit;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setScrnTm;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.timeCompareCheck;
//import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.checkRadioButton;
//import static business.servlet.NWAL2240.constant.NWAL2240Constant.BUSINESS_ID;
//import static business.servlet.NWAL2240.constant.NWAL2240Constant.FUNC_CD_UPDT;
//import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0014E;
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDFieldErrorException;
//import parts.common.EZDMessageInfo;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL2240.NWAL2240CMsg;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
// * 2016/07/28   Fujitsu         H.Ikeda         Update          S21_NA#5030
// * 2017/08/25   Fujitsu         S.Iidaka        Update          S21_NA#20740-1
// *</pre>
// */
//public class NWAL2240Scrn00_CMN_Submit extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.ordSrcRefNum_H0)) {
//            scrnMsg.ordSrcRefNum_H0.setErrorInfo(1, NWAM0014E);
//        }
//        // S21_NA#5030 Add start
//        checkRadioButton(scrnMsg);
//        // S21_NA#5030 Add End
//        getScrnTm(scrnMsg);
//        checkFormatItem(scrnMsg);
////        dlvyIstlDateCompareCheck(scrnMsg); // 2017/08/25 S21_NA#20740-1 Del
//        timeCompareCheck(scrnMsg);
//        checkNameMandatory(scrnMsg);
//        addCheckItemBizLayerErr(scrnMsg);
//
//        scrnMsg.putErrorScreen();
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;
//
//        NWAL2240CMsg bizMsg = new NWAL2240CMsg();
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode(FUNC_CD_UPDT);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
//
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;
//        NWAL2240CMsg bizMsg = (NWAL2240CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        setAppFracDigit(scrnMsg);
//        convTimeforScreen(scrnMsg, bizMsg);
//        setScrnTm(scrnMsg);
//
//        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
//            throw new EZDFieldErrorException();
//        }
//
//        scrnMsg.setFocusItem(scrnMsg.ordSrcRefNum_H0);
//        scrnMsg.addCheckItem(scrnMsg.ordSrcRefNum_H0);
//        scrnMsg.putErrorScreen();
//    }
//}

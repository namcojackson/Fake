///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHIP_TO_ADDR_IND;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2013/03/25   Hitachi         T.Kanasaka      Create          QC847
// *</pre>
// */
//public class NPAL1080Scrn00_OnChange_ShipToAddrIndCd extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        //NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        //NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//        //NPAL1080CMsg bizMsg = new NPAL1080CMsg();
//        //bizMsg.setBusinessID("NPAL1080");
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
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CommonLogic.commonControl(this, scrnMsg);
//
//        if (SHIP_TO_ADDR_IND.SHIP_TO_CUSTOMER.equals(scrnMsg.shipToAddrIndCd_P1.getValue())) {
////            scrnMsg.setFocusItem(scrnMsg.shipToFlg_AC);
////            scrnMsg.setFocusItem(scrnMsg.shipToFlg_H1);
//        } else {
//            scrnMsg.shipToFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
//            scrnMsg.shipToCustCd_H1.clear();
//            scrnMsg.shipToLocNm_H1.clear();
//            scrnMsg.shipToAddlLocNm_H1.clear();
//            scrnMsg.shipToFirstLineAddr_H1.clear();
//            scrnMsg.shipToScdLineAddr_H1.clear();
//            scrnMsg.shipToThirdLineAddr_H1.clear();
//            scrnMsg.shipToFrthLineAddr_H1.clear();
//            scrnMsg.shipToFirstRefCmntTxt_H1.clear();
//            scrnMsg.shipToScdRefCmntTxt_H1.clear();
//            scrnMsg.shipToCtyAddr_H1.clear();
//            scrnMsg.shipToStCd_H1.clear();
//            scrnMsg.shipToProvNm_H1.clear();
//            scrnMsg.shipToCntyNm_H1.clear();
//            scrnMsg.shipToPostCd_H1.clear();
//            scrnMsg.shipToCtryCd_H1.clear();
////            scrnMsg.setFocusItem(scrnMsg.prchReqCmntTxt_H1);
//        }
//        scrnMsg.setFocusItem(scrnMsg.shipToAddrIndCd_P1);
//    }
//}

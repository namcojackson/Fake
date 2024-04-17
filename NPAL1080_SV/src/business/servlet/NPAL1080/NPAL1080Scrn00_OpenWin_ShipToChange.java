///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1080.NPAL1080CMsg;
//import business.servlet.NPAL1080.constant.NPAL1080Constant;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///** 
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2013/03/25   Hitachi         T.Kanasaka      Create          QC847
// * 2013/07/26   CSAI            K.Lee           Update          QC1438
// * 2015/03/25   Fujitsu         T.Nishikawa     Update          CSA
// *</pre>
// */
//public class NPAL1080Scrn00_OpenWin_ShipToChange extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        // QC1438-Add-S
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
//        // QC1438-Add-E
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        // QC1438-Mod-S
//        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        scrnMsg.xxPopPrm_P1.setValue(STR_NO_VALUE);
//        scrnMsg.xxPopPrm_P2.setValue(ZYPConstant.FLG_OFF_N);
//
//        // Start Mod 2015/03/25 CSA T.Nishikawa
////        if (NPAL1080CommonLogic.checkModeItem(scrnMsg) == MODE_ITEM_SUBMITTED) {
////            scrnMsg.xxPopPrm_P3.setValue(ZYPConstant.FLG_ON_Y);
////        } else {
////            scrnMsg.xxPopPrm_P3.setValue(ZYPConstant.FLG_OFF_N);
////        }
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqStsSaveFlg_H1.getValue())) {
//            scrnMsg.xxPopPrm_P3.setValue(ZYPConstant.FLG_OFF_N);
//        } else {
//            scrnMsg.xxPopPrm_P3.setValue(ZYPConstant.FLG_ON_Y);
//        }
//        // End Mod 2015/03/25 CSA T.Nishikawa
//        
//        // QC1438-Mod-E
//
//        scrnMsg.xxPopPrm_P4.setValue(STR_NO_VALUE);
//        scrnMsg.xxPopPrm_P5.setValue(STR_NO_VALUE);
//        scrnMsg.xxPopPrm_P6.setValue(STR_NO_VALUE);
//        scrnMsg.xxPopPrm_P7.setValue(STR_NO_VALUE);
//
//        Object[] params = new Object[22];
//        params[0] = scrnMsg.xxPopPrm_P1;
//        params[1] = scrnMsg.shipToCustCd_H1;
//        params[2] = scrnMsg.shipToLocNm_H1;
//        params[3] = scrnMsg.shipToAddlLocNm_H1;
//        params[4] = scrnMsg.shipToFirstLineAddr_H1;
//        params[5] = scrnMsg.shipToScdLineAddr_H1;
//        params[6] = scrnMsg.shipToThirdLineAddr_H1;
//        params[7] = scrnMsg.shipToFrthLineAddr_H1;
//        params[8] = scrnMsg.shipToFirstRefCmntTxt_H1;
//        params[9] = scrnMsg.shipToScdRefCmntTxt_H1;
//        params[10] = scrnMsg.shipToCtyAddr_H1;
//        params[11] = scrnMsg.shipToStCd_H1;
//        params[12] = scrnMsg.shipToPostCd_H1;
//        params[13] = scrnMsg.shipToCtryCd_H1;
//        params[14] = scrnMsg.shipToCntyNm_H1;
//        params[15] = scrnMsg.xxPopPrm_P2;
//        params[16] = scrnMsg.xxPopPrm_P3;
//        params[17] = scrnMsg.xxPopPrm_P4;
//        params[18] = scrnMsg.xxPopPrm_P5;
//        params[19] = scrnMsg.xxPopPrm_P6;
//        params[20] = scrnMsg.xxPopPrm_P7;
//        params[21] = scrnMsg.shipToProvNm_H1;
//
//        setArgForSubScreen(params);
//    }
//}

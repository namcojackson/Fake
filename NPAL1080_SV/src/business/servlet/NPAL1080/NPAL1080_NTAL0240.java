///**
// * Copyright(c)2012 Canon USA Inc. All rights reserved.
// */
//
//package business.servlet.NPAL1080;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
//import business.servlet.NPAL1080.constant.NPAL1080Constant;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
///**
// * <pre>
// * PR Entry  NPAL1080_NTAL0240
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2012/11/16   Hitachi         Y.Nishiwaki        Create          N/A
// * </pre>
// */
//
//public class NPAL1080_NTAL0240 extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        // do nothing
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
//        if (selectIdx != -1 && ZYPCommonFunc.hasValue(scrnMsg.P.no(POP_PAR_10).xxPopPrm)) {
//            scrnMsg.A.no(selectIdx).invtyLocCd_D1.setValue(scrnMsg.P.no(POP_PAR_9).xxPopPrm.getValue());
//            scrnMsg.A.no(selectIdx).invtyLocNm_D1.setValue(scrnMsg.P.no(POP_PAR_10).xxPopPrm.getValue());
//            scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).invtyLocCd_D1);
//        }
//        NPAL1080CommonLogic.checkAuth(this, scrnMsg);
//    }
//}

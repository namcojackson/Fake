///**
// * Copyright(c)2012 Canon USA Inc. All rights reserved.
// */
//package business.servlet.NPAL1080;
//
//import java.util.HashMap;
//import java.util.Map;
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
//import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * PR Entry : NPAL1080Scrn00_AddDetailLine
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2015/03/10   Fujitsu         T.Nishikawa     Create          CSA
// *</pre>
// */
//public class NPAL1080Scrn00_AddDetailLine extends S21CommonHandler implements NPAL1080Constant {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//        // 1. header check
//        {
//            scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_P1);
//            scrnMsg.addCheckItem(scrnMsg.rqstRtlWhCd_H1);
//            scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_H1);
//
//            if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_H1) && ZYPDateUtil.isPastDate(scrnMsg.rqstRcvDt_H1.getValue())) {
//                scrnMsg.rqstRcvDt_H1.setErrorInfo(1, NPAM1230E, new String[] {MSG_ARG_NEED_BY_DATE });
//            }
//        }
//
//        // 2. master existence check -> BLAP
//
//        // 3. detail check
//        {
//            scrnMsg.addCheckItem(scrnMsg.mdseCd_DH);
//
//            if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_DH)) {
//                String inputMdse = scrnMsg.mdseCd_DH.getValue();
//                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                    if (inputMdse.equals(scrnMsg.A.no(i).mdseCd_D1.getValue())) {
//                        scrnMsg.mdseCd_DH.setErrorInfo(1, NPAM1232E, new String[] {MSG_ARG_MDSE_CODE });
//                        break;
//                    }
//                }
//            }
//        }
//
//        // 4. max record check
//        {
//            int sum = countLines(scrnMsg);
//            if (sum >= MAX_DETAIL_LINES) {
//                scrnMsg.setMessageInfo(NPAM1199E);
//                throw new EZDFieldErrorException();
//            }
//        }
//
//        // 5. detail header check -> BLAP
//
//        scrnMsg.putErrorScreen();
//    }
//
//    private int countLines(NPAL1080BMsg scrnMsg) {
//        Map<String, Integer> mdseCdAndCnt = new HashMap<String, Integer>();
//
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            String mdseCd = scrnMsg.A.no(i).mdseCd_D1.getValue();
//            if (!mdseCdAndCnt.containsKey(mdseCd)) {
//                mdseCdAndCnt.put(mdseCd, 1);
//            } else {
//                mdseCdAndCnt.put(mdseCd, mdseCdAndCnt.get(mdseCd) + 1);
//            }
//        }
//
//        int sum = 0;
//        for (Integer i : mdseCdAndCnt.values()) {
//            if (i.intValue() == 1) {
//                sum += 1;
//            } else {
//                // when contains children, parent is not counted
//                sum += i.intValue() - 1;
//            }
//        }
//
//        return sum;
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
//
//        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode(FUNC_SEARCH);
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
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        
//        NPAL1080CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
//        NPAL1080CommonLogic.setTableAttribute(scrnMsg);
//
//        scrnMsg.addCheckItem(scrnMsg.rqstRtlWhCd_H1);
//        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
//        scrnMsg.addCheckItem(scrnMsg.mdseCd_DH);
//        scrnMsg.putErrorScreen();
//        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
//            throw new EZDFieldErrorException();
//        }
//
//        // 8.
//        scrnMsg.mdseCd_DH.clear();
//        scrnMsg.mdseNm_DH.clear();
//        scrnMsg.prchReqQty_DH.clear();
//
//        // 9.
//        {
//            int cnt = scrnMsg.A.getValidCount();
//            int idx = cnt - 1;
//            
//            // 9-1.
//            NPAL1080CommonLogic.controlForDetailLineEditable(this, scrnMsg, idx);
//            
//            if (cnt == 1) { // PR header new creation
//                NPAL1080CommonLogic.controlForPostAddLine(this, scrnMsg);
//            } // else: as is
//        }
//
//        scrnMsg.setFocusItem(scrnMsg.mdseCd_DH);
//    }
//}

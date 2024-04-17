// 2017/12/05 QC#21270 Del Start
///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NMAL2520;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2520.NMAL2520CMsg;
//import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
//import business.servlet.NMAL2520.constant.NMAL2520Constant;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2016/02/12   Fujitsu         C.Yokoi         Create          CSA-QC#2869
// * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
// * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
// * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11453
// * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431-1
// * 2017/11/30   Fujitsu         N.Sugiura       Update          QC#21893
// *</pre>
// */
//public class NMAL2520Scrn00_OnBlur_DeriveOrganization extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
//
//        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
//        NMAL2520CommonLogic.addCheckItems(scrnMsg);
//
//        scrnMsg.putErrorScreen();
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
//
//        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)) {
//            return null;
//        }
//
//        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
//        bizMsg.setBusinessID("NMAL2520");
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
//        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)) {
//            // QC#7962
//            scrnMsg.setFocusItem(scrnMsg.orgShortNm_H1);
//            return;
//        }
//
//        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NMAL2520CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);
//
//        NMAL2520CommonLogic.controlOrgLink(scrnMsg);
//        NMAL2520CommonLogic.controlContract(scrnMsg);
//        NMAL2520CommonLogic.controlRscLink(scrnMsg);
//        NMAL2520CommonLogic.controlBusinessAreaFields(scrnMsg);
//        // QC#13431
//        NMAL2520CommonLogic.controlAttachmentsButton(this, scrnMsg);
//
//        // 2016/07/27 CSA-QC#11453 Add Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            scrnMsg.setMessageInfo(NMAL2520Constant.NMAM8635I,
//                    new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION, NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION, NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION});
//        }
//        // 2016/07/27 CSA-QC#11453 Add End
//
//        // 2017/11/30 QC#21893 Add Start
//        NMAL2520CommonLogic.controlInsertDelete(this, scrnMsg);
//        // 2017/11/30 QC#21893 Add End
//
//        // QC#7962
//        scrnMsg.setFocusItem(scrnMsg.orgNm_H1);
//    }
//}
// 2017/12/05 QC#21270 Del End
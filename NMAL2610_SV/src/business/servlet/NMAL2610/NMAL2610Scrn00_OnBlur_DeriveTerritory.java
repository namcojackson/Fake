// 2017/12/05 QC#21270 Del Start
///*
// * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
// */
//package business.servlet.NMAL2610;
//
//import parts.common.EZDBMsg;
//import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
//import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2610.NMAL2610CMsg;
//import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
//import business.servlet.NMAL2610.constant.NMAL2610Constant;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//
///**
// *<pre>
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 2016/02/12   Fujitsu         C.Yokoi         Create          CSA-QC#2262
// * 2016/03/09   Fujitsu         C.Yokoi         Update          CSA-QC#5235
// * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
// * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11453
// * 2016/10/11   Hitachi         Y.Takeno        Update          QC#13431
// * 2017/03/08   Fujitsu         R.Nakamura      Update          QC#15878
// *</pre>
// */
//public class NMAL2610Scrn00_OnBlur_DeriveTerritory extends S21CommonHandler {
//
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
//
//        NMAL2610CommonLogic.clearMandantoryForHeader(scrnMsg);
//        NMAL2610CommonLogic.addCheckItem(scrnMsg, false);
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
//
//        // 2016/03/09 CSA-QC#5235 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)
//                || NMAL2610Constant.LINK_COPY_TERRITORY.equals(scrnMsg.xxEventFlgTxt.getValue())) {
//            // 2016/03/09 CSA-QC#5235 Mod End
//            return null;
//        }
//
//        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
//        bizMsg.setBusinessID("NMAL2610");
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
//
//        // 2016/03/09 CSA-QC#5235 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)
//                || NMAL2610Constant.LINK_COPY_TERRITORY.equals(scrnMsg.xxEventFlgTxt.getValue())) {
//            // 2016/03/09 CSA-QC#5235 Mod End
//            // QC#7962
//            scrnMsg.setFocusItem(scrnMsg.orgTierCd_P1);
//            return;
//        }
//
//        NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        //Add Start 2017/03/08 QC#15878
//        scrnMsg.clearAllGUIAttribute(NMAL2610Constant.SCREEN_ID);
//        if (scrnMsg.xxDplyTab.getValue().equals(NMAL2610Constant.TAB_TERRITORY_RULES)) {
//            NMAL2610CommonLogic.controlScreenTrtyRuleFields(this, scrnMsg);
//        }
//        // Add End 2017/03/08 QC#15878
//        NMAL2610CommonLogic.controlOrgLink(scrnMsg);
//        NMAL2610CommonLogic.controlRolePullDown(scrnMsg);
//
//        // 2016/10/11 CSA-QC#13431 Add Start
//        NMAL2610CommonLogic.controlAttachmentButton(this, scrnMsg);
//        // 2016/10/11 CSA-QC#13431 Add End
//
//        // 2016/07/27 CSA-QC#11453 Add Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
//            scrnMsg.setMessageInfo(NMAL2610Constant.NMAM8635I,
//                    new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY, NMAL2610Constant.NAME_FOR_MESSAGE_TERRITORY});
//        }
//        // 2016/07/27 CSA-QC#11453 Add End
//
//        // QC#7962
//        scrnMsg.setFocusItem(scrnMsg.orgTierCd_P1);
//    }
//}
// 2017/12/05 QC#21270 Del End
/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1880;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;


import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import business.blap.NWAL1880.constant.NWAL1880Constant.DPLY_BY_CONFIG;
import business.blap.NWAL1880.common.NWAL1880CommonLogic;
import business.db.CCYTMsg;
import business.db.DPLY_BY_CONFIGTMsg;
import business.db.DPLY_BY_CONFIGTMsgArray;
import business.db.GLBL_CMPYTMsg;
import static business.blap.NWAL1880.constant.NWAL1880Constant.DISP_BY_ALL_CSA;
import static business.blap.NWAL1880.constant.NWAL1880Constant.DISP_BY_CRAT_BY_USR_ID;
import static business.blap.NWAL1880.constant.NWAL1880Constant.DISP_BY_TEAM_NM;
import static business.blap.NWAL1880.constant.NWAL1880Constant.DISP_BY_ITEM_LABEL;
import static business.blap.NWAL1880.constant.NWAL1880Constant.DISP_BY_ITEM_NM;
import static business.blap.NWAL1880.constant.NWAL1880Constant.NWAM0350E;
import static business.blap.NWAL1880.constant.NWAL1880Constant.ALL_REF_AUTH;
import static business.blap.NWAL1880.constant.NWAL1880Constant.NWAL1880_SPL_CHAR_TXT;
import static business.blap.NWAL1880.constant.NWAL1880Constant.REF_ONLY_SELF_RGTN_AUTH;
import static business.blap.NWAL1880.constant.NWAL1880Constant.REF_ONLY_TEAM_AUTH;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;

/**
 *<pre>
 * NWAL1880BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1880BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1880CMsg bizMsg = (NWAL1880CMsg) cMsg;
            NWAL1880SMsg glblMsg = (NWAL1880SMsg) sMsg;

            if ("NWAL1880Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1880Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL1880Scrn00_MoveWin_OrderInquiry".equals(screenAplID)) {
                doProcess_NWAL1880Scrn00_MoveWin_OrderInquiry(bizMsg, glblMsg);

            } else if ("NWAL1880Scrn00_SearchOrder".equals(screenAplID)) {
                doProcess_NWAL1880Scrn00_SearchOrder(bizMsg, glblMsg);

            } else if ("NWAL1880_INIT".equals(screenAplID)) {
                doProcess_NWAL1880_INIT(bizMsg, glblMsg);
                doProcess_NWAL1880Scrn00_SearchOrder(bizMsg, glblMsg);

            } else if ("NWAL1880_NMAL6870".equals(screenAplID)) {
                doProcess_NWAL1880_NMAL6870(bizMsg, glblMsg);

            } else if ("NWAL1880_NWAL1570".equals(screenAplID)) {
                doProcess_NWAL1880_NWAL1570(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1880Scrn00_CMN_Reset(NWAL1880CMsg bizMsg, NWAL1880SMsg glblMsg) {
        doProcess_NWAL1880_INIT(bizMsg, glblMsg);
        doProcess_NWAL1880Scrn00_SearchOrder(bizMsg, glblMsg);

    }

    /**
     * MoveWin_OrderInquiry Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1880Scrn00_MoveWin_OrderInquiry(NWAL1880CMsg bizMsg, NWAL1880SMsg glblMsg) {
        //
    }

    /**
     * SearchOrder Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1880Scrn00_SearchOrder(NWAL1880CMsg bizMsg, NWAL1880SMsg glblMsg) {

        if (glblMsg != null) {
            ZYPTableUtil.clear(glblMsg.A);
            glblMsg.clear();
        }

        // 1. check organization structure.
        getDisplayByPairItem(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdTeamSrchTxt_BK, bizMsg.xxOrdTeamSrchTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdZnSrchTxt_BK, bizMsg.xxOrdZnSrchTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCratByUsrSrchTxt_BK, bizMsg.xxCratByUsrSrchTxt);

        EZDMsg.copy(bizMsg, null, glblMsg, null);

        // 4. search order.
        NWAL1880Query.getInstance().findOrderList(bizMsg, glblMsg, glblMsg.A.length());

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1880_INIT(NWAL1880CMsg bizMsg, NWAL1880SMsg glblMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        NWAL1880CommonLogic.setAuthority(bizMsg, userProfSvc);

        /**
         * Setting Initial Values.
         */
        // Common
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());

        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.H.length(); i++) {
            funcList.add(bizMsg.H.no(i).xxFuncId.getValue());
        }

        // Team/Zone
        if (funcList.contains(ALL_REF_AUTH)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, DISP_BY_ALL_CSA);
        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {
            NWAL1880CommonLogic.getOrdTeamZoneByUser(bizMsg, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, DISP_BY_TEAM_NM);
        } else if (funcList.contains(REF_ONLY_SELF_RGTN_AUTH)) {
            NWAL1880CommonLogic.getOrdTeamZoneByUser(bizMsg, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxCratByUsrSrchTxt, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, DISP_BY_CRAT_BY_USR_ID);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, DISP_BY_ALL_CSA);
        }

        // Delimiter
        bizMsg.xxSplCharTxt.setValue(ZYPCodeDataUtil.getVarCharConstValue(NWAL1880_SPL_CHAR_TXT, bizMsg.glblCmpyCd.getValue()));

        // ++++++++++++++++++++++++++++++
        // + Display Option
        // ++++++++++++++++++++++++++++++
        // Display By - 1, 2, 3
        DPLY_BY_CONFIGTMsg reqTMsg = new DPLY_BY_CONFIGTMsg();
        reqTMsg.setSQLID("001");
        reqTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        reqTMsg.setConditionValue("trnstRteNum01", DPLY_BY_CONFIG.TRNST_RTE_NUM.getValue());
        String trnstRteOrdNum = null;
        if (funcList.contains(ALL_REF_AUTH)) {
            trnstRteOrdNum = DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM1.getValue();
        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {
            trnstRteOrdNum = DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM2.getValue();
        } else if (funcList.contains(REF_ONLY_SELF_RGTN_AUTH)) {
            trnstRteOrdNum = DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM3.getValue();
        }
        reqTMsg.setConditionValue("trnstRteOrdNum01", trnstRteOrdNum);

        EZDTMsgArray resTMsgArray = EZDTBLAccessor.findByCondition(reqTMsg);

        if (resTMsgArray.length() > 0) {

            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, DISP_BY_ITEM_NM);
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, DISP_BY_ITEM_LABEL);

            ZYPPulldownValueSetter.set(resTMsgArray, tMsgKeys, bizMsg.xxScrItem50Txt_CD, bizMsg.xxScrItem50Txt_NM);
        }

        // Other - Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        glblTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ccyMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            ccyMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
            ccyMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                bizMsg.aftDeclPntDigitNum.setValue(ccyMsg.aftDeclPntDigitNum.getValueInt());
            }
        }

    }

    private void getDisplayByPairItem(NWAL1880CMsg cMsg) {

        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < cMsg.H.length(); i++) {
            funcList.add(cMsg.H.no(i).xxFuncId.getValue());
        }

        DPLY_BY_CONFIGTMsg reqTMsg = new DPLY_BY_CONFIGTMsg();

        reqTMsg.setSQLID("002");
        reqTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        reqTMsg.setConditionValue("trnstRteNum01", DPLY_BY_CONFIG.TRNST_RTE_NUM.getValue());
        String trnstRteOrdNum = null;
        if (funcList.contains(ALL_REF_AUTH)) {
            trnstRteOrdNum = DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM1.getValue();
        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {
            trnstRteOrdNum = DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM2.getValue();
        } else if (funcList.contains(REF_ONLY_SELF_RGTN_AUTH)) {
            trnstRteOrdNum = DPLY_BY_CONFIG.TRNST_RTE_ORD_NUM3.getValue();
        }
        reqTMsg.setConditionValue("trnstRteOrdNum01", trnstRteOrdNum);
        reqTMsg.setConditionValue("dplyByItemNm01", cMsg.xxScrItem50Txt.getValue());
        DPLY_BY_CONFIGTMsgArray resTMsgArray = (DPLY_BY_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(reqTMsg);

        // if no record by dplyBy01ItemNm on DPLY_BY_CONFIG
        if (resTMsgArray.length() != 1) {
            cMsg.setMessageInfo(NWAM0350E);
            return;
        } else {
            cMsg.pairDplyByItemNm.setValue(resTMsgArray.no(0).pairDplyByItemNm.getValue());
            cMsg.dplyByItemLbNm.setValue(resTMsgArray.no(0).dplyByItemLbNm.getValue());
        }

    }

    /**
     * NMAL6870 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1880_NMAL6870(NWAL1880CMsg bizMsg, NWAL1880SMsg glblMsg) {
        //
    }

    /**
     * NWAL1570 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1880_NWAL1570(NWAL1880CMsg bizMsg, NWAL1880SMsg glblMsg) {
        //
    }


}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2010;

import static business.blap.NWAL2010.constant.NWAL2010Constant.NWZM0971E;
import static business.blap.NWAL2010.constant.NWAL2010Constant.NZZM0000E;
import static business.blap.NWAL2010.constant.NWAL2010Constant.NZZM0001W;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2010.common.NWAL2010CommonLogic;
import business.db.DS_CR_CARDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2010BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2022/12/20   Hitachi         R.Takau         Update          QC#60823
 * 2024/03/11   CITS            M.Kobayashi     Update          QC#63757
 * 2024/04/10   CITS            M.Okamura       Update          QC#63757
 *</pre>
 */
public class NWAL2010BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;
            NWAL2010SMsg glblMsg = (NWAL2010SMsg) sMsg;

            if ("NWAL2010_INIT".equals(screenAplID)) {
                doProcess_NWAL2010_INIT(bizMsg, glblMsg);

            } else if ("NWAL2010Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL2010Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_CMN_Return(bizMsg, glblMsg);
            // Start 2024/04/10 M.Okamura [QC#63757,DEL]
//            } else if ("NWAL2010Scrn00_InsertNewCard".equals(screenAplID)) {
//                doProcess_NWAL2010Scrn00_InsertNewCard(bizMsg, glblMsg);
            // End 2024/04/10 M.Okamura [QC#63757,DEL]
            } else if ("NWAL2010Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL2010Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL2010Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_Search(bizMsg, glblMsg);
            //START 2022/12/20 R.Takau [QC#60823,ADD]
            } else if ("NWAL2010Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NWAL2010_INIT(bizMsg, glblMsg);
            //END 2022/12/20 R.Takau [QC#60823,ADD]
            //START 2024/04/10 M.Okamura [QC#63757,ADD]
            } else if ("NWAL2010_NWAL2020".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_Search(bizMsg, glblMsg);
            //END 2024/04/10 M.Okamura [QC#63757,ADD]
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2010_INIT(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(CR_CARD_TP.class, bizMsg.crCardTpCd_CC, bizMsg.crCardTpDescTxt_CD);
        ZYPCodeDataUtil.createPulldownList(CR_CARD_TP.class, bizMsg.crCardTpCd_CD, bizMsg.crCardTpDescTxt_DI);
        // Start 2024/04/10 M.Okamura [QC#63757,DEL]
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrTsTxt_T, ZYPCodeDataUtil.getVarCharConstValue(TCEPPS_TOKEN_EXPR_INTVL, getGlobalCompanyCode()));
//        ZYPEZDItemValueSetter.setValue(bizMsg.appNm_T, ZYPCodeDataUtil.getVarCharConstValue(TCEPPS_KEY_NM, getGlobalCompanyCode()));
        // End 2024/04/10 M.Okamura [QC#63757,DEL]
        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk)) {
            DS_CR_CARDTMsg dsCrCardRslt = NWAL2010CommonLogic.getDsCrCardTMsg(bizMsg, getGlobalCompanyCode());
            if (dsCrCardRslt == null) {
                bizMsg.setMessageInfo(NWZM0971E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum, dsCrCardRslt.crCardCustRefNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_BK, bizMsg.crCardCustRefNum);

            ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_SL, bizMsg.crCardCustRefNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_SL, bizMsg.dsCrCardPk);

        }

        search(bizMsg, glblMsg);
        saveSelectedValue(bizMsg);

    }

    /**
     * CMN_Return Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2010Scrn00_CMN_Return(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_A) || !ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return;
        }

        BigDecimal crCardTrxPk = NWAL2010Query.getInstance().getCrCardTrxPk(bizMsg);

//        int idxOfSelectedRow = bizMsg.xxRadioBtn_A.getValueInt();
//        String crCardCustRefNum = glblMsg.A.no(idxOfSelectedRow).crCardCustRefNum_A.getValue();
        BigDecimal idxOfSelectedRow = bizMsg.xxRadioBtn_A.getValue();
        if (idxOfSelectedRow == null) {
            return;
        }
        String crCardCustRefNum = glblMsg.A.no(idxOfSelectedRow.intValue()).crCardCustRefNum_A.getValue();
        NWAL2010CommonLogic.callCrCardValidApi(bizMsg, glblMsg, getGlobalCompanyCode(), crCardCustRefNum, crCardTrxPk);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2010Scrn00_CMN_Reset(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        bizMsg.crCardExprYrMth.clear();
        bizMsg.crCardLastDigitNum.clear();
        bizMsg.crCardTpCd.clear();
        bizMsg.crCardCustRefNum.clear();
        bizMsg.crCardValidFlg.clear();
        // START 2024/03/11 M.Kobayashi [QC#63757,DEL]
        // bizMsg.xxChkBox_C.clear();
        // START 2024/03/11 M.Kobayashi [QC#63757,DEL]

        ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum, bizMsg.crCardCustRefNum_BK);
        ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_SL, bizMsg.crCardCustRefNum_BK);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_SL, bizMsg.dsCrCardPk);
        search(bizMsg, glblMsg);
    }
    // Start 2024/04/10 M.Okamura [QC#63757,DEL]
//    /**
//     * InsertNewCard Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NWAL2010Scrn00_InsertNewCard(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {
//
//        if (NWAL2010CommonLogic.callCrCardValidApi(bizMsg, glblMsg, getGlobalCompanyCode(), null, null)) {
////            NWAL2010CommonLogic.setNewCrCardLine(bizMsg, glblMsg, getGlobalCompanyCode());
////            search(bizMsg, glblMsg);
//            NWAL2010CommonLogic.addCrCardInfo(bizMsg, glblMsg, getGlobalCompanyCode());
//            NWAL2010CommonLogic.insertRowMoveLastPage(bizMsg, glblMsg);
//            selectRadioBtnByKey(bizMsg);
//        }
//    }
    // End 2024/04/10 M.Okamura [QC#63757,DEL]
    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2010Scrn00_PageNext(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        NWAL2010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        saveSelectedValue(bizMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL2010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        selectRadioBtnByKey(bizMsg);
  }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2010Scrn00_PagePrev(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        NWAL2010CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        saveSelectedValue(bizMsg);

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL2010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

        selectRadioBtnByKey(bizMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2010Scrn00_Search(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        saveSelectedValue(bizMsg);
        search(bizMsg, glblMsg);

    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NWAL2010CMsg bizMsg, NWAL2010SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        bizMsg.setCommitSMsg(true);

        S21SsmEZDResult ssmResult = NWAL2010Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length() - 1) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length() - 1);
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NWAL2010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }

        selectRadioBtnByKey(bizMsg);
    }

    private void saveSelectedValue(NWAL2010CMsg bizMsg) {

        BigDecimal idx = bizMsg.xxRadioBtn_A.getValue();
        if (ZYPCommonFunc.hasValue(idx)) {
            NWAL2010_ACMsg acMsg = bizMsg.A.no(idx.intValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_SL, acMsg.crCardCustRefNum_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsCrCardPk_SL, acMsg.dsCrCardPk_A);
        }

    }
    private void selectRadioBtnByKey(NWAL2010CMsg bizMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.acctCdFlg_LK.getValue())) {
            return;
        }

        bizMsg.xxRadioBtn_A.clear();

        if (ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk_SL)) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (bizMsg.dsCrCardPk_SL.getValue().equals(bizMsg.A.no(i).dsCrCardPk_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_A, new BigDecimal(i));
                    break;
                }
            }
        }
    }
}

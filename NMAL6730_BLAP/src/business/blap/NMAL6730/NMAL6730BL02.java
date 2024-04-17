/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6730;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6730.common.NMAL6730CommonLogic;
import business.blap.NMAL6730.constant.NMAL6730Constant;
import business.db.COA_AFFLTMsg;
import business.db.COA_CHTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PMT_TERM_CASH_DISCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AUTO_CASH_HRCH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_CR_RISK_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_RISK_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_CR_RTG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_BASE_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_BASE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_USG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_USG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CLT_ACCT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_TAX_CALC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_GRP_EXEM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_PRNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#4337
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2016/11/11   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2017/08/25   CITS            T.Tokutomi      Update          QC#19869
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/08/01   Fujitsu         S.Ohki          Update          QC#27222
 * 2019/01/22   Fujitsu         M.Ohno          Update          QC#29371 SRNO13
 *</pre>
 */
public class NMAL6730BL02 extends S21BusinessHandler implements NMAL6730Constant {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;
        NMAL6730SMsg sharedMsg = (NMAL6730SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6730_INIT".equals(screenAplID)) {
                doProcess_NMAL6730_INIT(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_CMN_Reset(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_TAB_Financial".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_TAB_Financial(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_TAB_InvBllg".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_TAB_InvBllg(bizMsg, sharedMsg);
            // Add Start 2018/08/01 QC#27222
            } else if ("NMAL6730Scrn00_TAB_Taxing".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_TAB_Taxing(bizMsg, sharedMsg);
            // Add End 2018/08/01 QC#27222
            } else if ("NMAL6730Scrn00_AddInvoiceSource".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_AddInvoiceSource(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_DeleteInvoiceSource".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_DeleteInvoiceSource(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_AddAttribute".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_AddAttribute(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_DeleteAttribute".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_DeleteAttribute(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_GetCltCustTpNm".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_GetCltCustTpNm(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_GetCltPtfoNm".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_GetCltPtfoNm(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_ApplyTemplate".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_ApplyTemplate(bizMsg, sharedMsg);
            } else if ("NMAL6730_NMAL2570".equals(screenAplID)) {
                doProcess_NMAL6730_NMAL2570(bizMsg, sharedMsg);
            // QC#7781
            } else if ("NMAL6730_NMAL6770".equals(screenAplID)) {
                doProcess_NMAL6730_NMAL6770(bizMsg, sharedMsg);
            } else if ("NMAL6730_NMAL6050".equals(screenAplID)) {
                doProcess_NMAL6730_NMAL6050(bizMsg, sharedMsg);
            // QC#6382
            } else if ("NMAL6730_NMAL2550".equals(screenAplID)) {
                doProcess_NMAL6730_NMAL2550(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_GetCoaChNm".equals(screenAplID)) {
                doProcess_NMAL6730Scrn00_GetCoaChNm(bizMsg, sharedMsg);
            } else if ("NMAL6730Scrn00_GetInterCompanyNm".equals(screenAplID)) {
// QC#9448
//                doProcess_NMAL6730Scrn00_GetInterCompanyNm(bizMsg, sharedMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6730_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6730_INIT(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        clearMsg(cMsg, sMsg);

        ZYPCodeDataUtil.createPulldownList(CCY.class, cMsg.ccyCd_F1, cMsg.ccyNm_F1);
        ZYPCodeDataUtil.createPulldownList(CUST_CR_RTG.class, cMsg.custCrRtgCd_F1, cMsg.custCrRtgNm_F1);
        ZYPCodeDataUtil.createPulldownList(CR_CHK_REQ_TP.class, cMsg.crChkReqTpCd_F1, cMsg.crChkReqTpNm_F1);
        ZYPCodeDataUtil.createPulldownList(CR_RISK_CLS.class, cMsg.crRiskClsCd_F1, cMsg.crRiskClsNm_F1);
        // Add Start #22095 2018/01/25
        ZYPCodeDataUtil.createPulldownList(CONTR_CR_RISK_CLS.class, cMsg.contrCrRiskClsCd_F1, cMsg.contrCrRiskClsNm_F1);
        // Add End   #22095 2018/01/25
        ZYPCodeDataUtil.createPulldownList(PMT_TERM_CASH_DISC.class, cMsg.pmtTermCashDiscCd_F1, cMsg.pmtTermCashDiscNm_F1);
        ZYPCodeDataUtil.createPulldownList(DS_TAX_PRNT_TP.class, cMsg.dsTaxPrntTpCd_F1, cMsg.dsTaxPrntTpNm_F1);
        // Del Start 2018/08/01 QC#27222
//        ZYPCodeDataUtil.createPulldownList(DS_CUST_TAX_CALC.class, cMsg.dsCustTaxCalcCd_F1, cMsg.dsCustTaxCalcNm_F1);
        // Del End 2018/08/01 QC#27222
        ZYPCodeDataUtil.createPulldownList(DEF_BASE_TP.class, cMsg.defBaseTpCd_I1, cMsg.defBaseTpNm_I1);
        ZYPCodeDataUtil.createPulldownList(DEF_BASE_CYCLE.class, cMsg.defBaseCycleCd_I1, cMsg.defBaseCycleNm_I1);
        ZYPCodeDataUtil.createPulldownList(DEF_USG_TP.class, cMsg.defUsgTpCd_I1, cMsg.defUsgTpNm_I1);
        ZYPCodeDataUtil.createPulldownList(DEF_USG_CYCLE.class, cMsg.defUsgCycleCd_I1, cMsg.defUsgCycleNm_I1);
        ZYPCodeDataUtil.createPulldownList(DS_CLT_ACCT_STS.class, cMsg.dsCltAcctStsCd_F1, cMsg.dsCltAcctStsNm_F1);
        ZYPCodeDataUtil.createPulldownList(AR_STMT_ISS_CYCLE.class, cMsg.arStmtIssCycleCd_F1, cMsg.arStmtIssCycleNm_F1);
        ZYPCodeDataUtil.createPulldownList(DS_TAX_GRP_EXEM.class, cMsg.dsTaxGrpExemCd_F1, cMsg.dsTaxGrpExemNm_F1);
        // START 2018/04/17 E.Kameishi [QC#21037, ADD]
        ZYPCodeDataUtil.createPulldownList(AUTO_CASH_HRCH.class, cMsg.autoCashHrchCd_F1, cMsg.autoCashHrchNm_F1);
        // END 2018/04/17 E.Kameishi [QC#21037, ADD]
        searchHeader(cMsg, sMsg);
        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind()) || MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
            return;
        }

        // if (TAB_FINANCIAL.equals(cMsg.xxDplyTab.getValue())) {
        getFinancialInfo(cMsg, sMsg);
        // } else {
        getInvoiceSourceList(cMsg, sMsg);
        getBillingCycleInfo(cMsg, sMsg);
        getRefAttribute(cMsg, sMsg);
        // }
        // QC#19869 Set Default COA_Code.
        // getDefDplyCoaInfo(cMsg, sMsg); 2018/03/26 S21_NA#23935 Del
    }

    /**
     * Method name: doProcess_NMAL6730Scrn00_CMN_Submit <dd>The method
     * explanation: Reset values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_CMN_Submit(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind())) {
            return;
        }
        if (NMAM8333I.equals(cMsg.getMessageCode())) {
            return;
        }
        clearMsg(cMsg, sMsg);
        searchHeader(cMsg, sMsg);
        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind()) || MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
            return;
        }
        // Del Start #4332 2016/02/23
        // if (TAB_FINANCIAL.equals(cMsg.xxDplyTab.getValue())) {
        getFinancialInfo(cMsg, sMsg);
        // } else {
        getInvoiceSourceList(cMsg, sMsg);
        getBillingCycleInfo(cMsg, sMsg);
        getRefAttribute(cMsg, sMsg);
        // }
        // Del End #4332 2016/02/23
    }

    /**
     * Method name: doProcess_NMAL6730Scrn00_CMN_Reset <dd>The method
     * explanation: Reset values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_CMN_Reset(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        clearMsg(cMsg, sMsg);
        searchHeader(cMsg, sMsg);
        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind()) || MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
            return;
        }

        // if (TAB_FINANCIAL.equals(cMsg.xxDplyTab.getValue())) {
            getFinancialInfo(cMsg, sMsg);
        // } else {
            getInvoiceSourceList(cMsg, sMsg);
            getBillingCycleInfo(cMsg, sMsg);
            getRefAttribute(cMsg, sMsg);
        // }
            // QC#19869 Set Default COA_Code.
            // getDefDplyCoaInfo(cMsg, sMsg); // 2018/03/26 S21_NA#23935 Del
    }

    /**
     * Method name: doProcess_NMAL6730Scrn00_TAB_Financial <dd>The
     * method explanation: TAB Financial.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_TAB_Financial(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_FINANCIAL);
    //    getFinancialInfo(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6730Scrn00_TAB_InvBllg <dd>The
     * method explanation: TAB InvBllg.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_TAB_InvBllg(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NMAL6730Constant.TAB_INV_BLLG);

    //    getInvoiceSourceList(cMsg, sMsg);
    //    getBillingCycleInfo(cMsg, sMsg);
    }

    // Add Start 2018/08/01 QC#27222
    /**
     * Method name: doProcess_NMAL6730Scrn00_TAB_Taxing <dd>The
     * method explanation: TAB Taxing.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_TAB_Taxing(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_TAXING);
    }
    // Add End 2018/08/01 QC#27222

    private void doProcess_NMAL6730Scrn00_AddInvoiceSource(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        int index = sMsg.A.getValidCount();

        EZDMsg.copy(sMsg.A.no(index), null, cMsg.A.no(index), null);

        ZYPCodeDataUtil.createPulldownList(CUST_INV_SRC.class, cMsg.A.no(index).custInvSrcCd_A1, cMsg.A.no(index).custInvSrcNm_A1);
        ZYPCodeDataUtil.createPulldownList(CUST_BLLG_TP.class, cMsg.A.no(index).custBllgTpCd_A1, cMsg.A.no(index).custBllgTpNm_A1);

        S21SsmEZDResult result = NMAL6730Query.getInstance().getCustConslTermCdList(cMsg);

        if (result.isCodeNotFound()) {
            return;
        }

        List<PMT_TERM_CASH_DISCTMsg> pmtTermCashDisc = (List) result.getResultObject();
        cMsg.A.no(index).pmtTermCashDiscCd_A1.clear();
        cMsg.A.no(index).pmtTermCashDiscNm_A1.clear();

        for (int i = 0; i < pmtTermCashDisc.size(); i++) {
            cMsg.A.no(index).pmtTermCashDiscCd_A1.no(i).setValue(pmtTermCashDisc.get(i).pmtTermCashDiscCd.getValue());
            cMsg.A.no(index).pmtTermCashDiscNm_A1.no(i).setValue(pmtTermCashDisc.get(i).pmtTermCashDiscNm.getValue());

        }
        // 2019/01/22 QC#29371 SRNO13 Mod Start
//        ZYPCodeDataUtil.createPulldownList(CUST_BLLG_VCLE.class, cMsg.A.no(index).custBllgVcleCd_A1, cMsg.A.no(index).custBllgVcleNm_A1);
        ZYPCodeDataUtil.createPulldownList(CUST_BLLG_VCLE.class, cMsg.A.no(index).custBllgVcleCd_A1, cMsg.A.no(index).custBllgVcleDescTxt_A1);
        // 2019/01/22 QC#29371 SRNO13 Mod End
        ZYPCodeDataUtil.createPulldownList(DEF_INV_GRP.class, cMsg.A.no(index).defInvGrpCd_A1, cMsg.A.no(index).defInvGrpNm_A1);

        cMsg.A.setValidCount(index + 1);
        sMsg.A.setValidCount(index + 1);

        cMsg.setCommitSMsg(true);
    }

    private void doProcess_NMAL6730Scrn00_DeleteInvoiceSource(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        int index = 0;
        for (int i = 0; i < checkList.size(); i++) {
            index = checkList.get(i);
            checkList.set(i, index);
            if (ZYPCommonFunc.hasValue(sMsg.A.no(index).dsCustInvRulePk_A1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsg.B.getValidCount()).dsCustInvRulePk_B1, sMsg.A.no(index).dsCustInvRulePk_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTime_B1, sMsg.A.no(index).ezUpTime_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTimeZone_B1, sMsg.A.no(index).ezUpTimeZone_A1);
                sMsg.B.setValidCount(sMsg.B.getValidCount() + 1);
            }
        }
        ZYPTableUtil.deleteRows(cMsg.A, checkList);
        ZYPTableUtil.deleteRows(sMsg.A, checkList);
    }
    
    /**
     * Method name: doProcess_NMAL6730Scrn00_AddAttribute
     * method explanation: Add Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_AddAttribute(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        int rowNum = cMsg.K.getValidCount();
        if (rowNum == cMsg.K.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Attributes", String.valueOf(cMsg.K.length()) });
            return;
        }

        NMAL6730_KCMsg newKcMsg = new NMAL6730_KCMsg();

        int addNum = NMAL6730CommonLogic.getAddAttributeNum(cMsg.K);
        String addNumStr = String.valueOf(addNum);
        ZYPEZDItemValueSetter.setValue(newKcMsg.xxCtlNm_K1, CONTROL_STR + addNumStr);
        ZYPEZDItemValueSetter.setValue(newKcMsg.dsAcctRefAttrbNum_K1, addNumStr);
        newKcMsg.custEffLvlCd_K3.setValue(CUST_EFF_LVL.LOCATION_ONLY);

        NMAL6730CommonLogic.setNewAttribute(cMsg.K, newKcMsg, addNum);
    }
    /**
     * Method name: doProcess_NMAL6730Scrn00_DeleteAttribute
     * method explanation: DeleteAttribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_DeleteAttribute(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.K, "xxChkBox_K1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        int index = 0;
        for (int i = 0; i < checkList.size(); i++) {
            index = checkList.get(i);
            if (ZYPCommonFunc.hasValue(cMsg.K.no(index).dsAcctRefAttrbPk_K1)) {
                int cnt = sMsg.T.getValidCount();
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(cnt).dsAcctRefAttrbPk_T1, cMsg.K.no(index).dsAcctRefAttrbPk_K1);
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(cnt).ezUpTime_T1, cMsg.K.no(index).ezUpTime_K1);
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(cnt).ezUpTimeZone_T1, cMsg.K.no(index).ezUpTimeZone_K1);
                sMsg.T.setValidCount(cnt + 1);
            }
        }
        ZYPTableUtil.deleteRows(cMsg.K, checkList);
        ZYPTableUtil.deleteRows(sMsg.K, checkList);

    }
    private void searchHeader(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String billToCustCd = cMsg.billToCustCd_H1.getValue();

        S21SsmEZDResult res = NMAL6730Query.getInstance().getBillToCustInfoHeader(glblCmpyCd, billToCustCd, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            // Add Start 2016/11/11 M.Ohno S21_NA#2680
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
            // Add End   2016/11/11 M.Ohno S21_NA#2680

            EZDMsg.copy(sMsg, null, cMsg, null);

//            cMsg.dsAcctNm_H1.setValue(sMsg.dsAcctNm_H1.getValue());
//            cMsg.firstLineAddr_H1.setValue(sMsg.firstLineAddr_H1.getValue());
//            cMsg.scdLineAddr_H1.setValue(sMsg.scdLineAddr_H1.getValue());
//            cMsg.thirdLineAddr_H1.setValue(sMsg.thirdLineAddr_H1.getValue());
//            cMsg.frthLineAddr_H1.setValue(sMsg.frthLineAddr_H1.getValue());
//            cMsg.ctyAddr_H1.setValue(sMsg.ctyAddr_H1.getValue());
//            cMsg.stCd_H1.setValue(sMsg.stCd_H1.getValue());
//            cMsg.postCd_H1.setValue(sMsg.postCd_H1.getValue());
//            cMsg.locNum_H1.setValue(sMsg.locNum_H1.getValue());
//            cMsg.billToCustCd_H1.setValue(sMsg.billToCustCd_H1.getValue());
//            cMsg.billToCustPk_H1.setValue(sMsg.billToCustPk_H1.getValue());

        } else {
            cMsg.setMessageInfo(NMAL6730Constant.NZZM0000E);
        }
    }

    private void getFinancialInfo(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String billToCustCd = cMsg.billToCustCd_H1.getValue();

        S21SsmEZDResult res = NMAL6730Query.getInstance().getFinancialInfo(glblCmpyCd, billToCustCd, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            // Add Start 2016/11/11 M.Ohno S21_NA#2680
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm_F1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm_F1.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm_F1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm_F1.getValue()));
            // Add End   2016/11/11 M.Ohno S21_NA#2680

            EZDMsg.copy(sMsg, null, cMsg, null);
//            cMsg.ccyCd_P1.setValue(sMsg.ccyCd_P1.getValue());
//            cMsg.custCrRtgCd_P1.setValue(sMsg.custCrRtgCd_P1.getValue());
//            cMsg.crLimitAmt_F1.setValue(sMsg.crLimitAmt_F1.getValue());
//            cMsg.crChkReqTpCd_P1.setValue(sMsg.crChkReqTpCd_P1.getValue());
//            cMsg.crRiskClsCd_P1.setValue(sMsg.crRiskClsCd_P1.getValue());
//            cMsg.pmtTermCd_P1.setValue(sMsg.pmtTermCd_P1.getValue());
//            cMsg.ovrdPmtTermFlg_F1.setValue(sMsg.ovrdPmtTermFlg_F1.getValue());
//            cMsg.cashOnDelyReqFlg_F1.setValue(sMsg.cashOnDelyReqFlg_F1.getValue());
//            cMsg.dunFlg_F1.setValue(sMsg.dunFlg_F1.getValue());
//            cMsg.dunIssDay_P1.setValue(sMsg.dunIssDay_P1.getValue());
//            cMsg.arStmtFlg_F1.setValue(sMsg.arStmtFlg_F1.getValue());
//            cMsg.arStmtIssDay_P1.setValue(sMsg.arStmtIssDay_P1.getValue());
//            cMsg.cltStrgyNm_F1.setValue(sMsg.cltStrgyNm_F1.getValue());
//            cMsg.defCltrCd_F1.setValue(sMsg.defCltrCd_F1.getValue());
//            cMsg.dsCltAcctStsCd_P1.setValue(sMsg.dsCltAcctStsCd_P1.getValue());
//            cMsg.lateFeeAmt_F1.setValue(sMsg.lateFeeAmt_F1.getValue());
//            cMsg.dsCustTaxCd_F1.setValue(sMsg.dsCustTaxCd_F1.getValue());
//            cMsg.dsCustTaxCalcCd_P1.setValue(sMsg.dsCustTaxCalcCd_P1.getValue());
//            cMsg.dsTaxPrntTpCd_P1.setValue(sMsg.dsTaxPrntTpCd_P1.getValue());
//
//            for (int i = 0; i < 31; i++) {
//                int count = i + 1;
//                cMsg.arStmtIssDay_F1.no(i).setValue(String.format("%1$02d", count));
//                cMsg.arStmtIssDay_F2.no(i).setValue(String.format("%1$02d", count));
//                cMsg.dunIssDay_F1.no(i).setValue(String.format("%1$02d", count));
//                cMsg.dunIssDay_F2.no(i).setValue(String.format("%1$02d", count));
//            }

        }
    }

    private void getInvoiceSourceList(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String billToCustCd = cMsg.billToCustCd_H1.getValue();
        String locNum = cMsg.locNum_H1.getValue();

        NMAL6730Query.getInstance().getInvoiceSourceList(glblCmpyCd, billToCustCd, locNum, sMsg);
        // QC#7781
        NMAL6730CommonLogic.getInvoiceSourceListInternalReview(cMsg, sMsg, getGlobalCompanyCode());
        NMAL6730CommonLogic.getInvoiceSourceListExternalContact(cMsg, sMsg, getGlobalCompanyCode());
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        pmtTermCashDiscTMsg.setSQLID("002");
        pmtTermCashDiscTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        pmtTermCashDiscTMsg.setConditionValue("pmtTermConslFlg01", ZYPConstant.FLG_ON_Y);

        PMT_TERM_CASH_DISCTMsgArray pmtTermCashDiscTMsgArray = (PMT_TERM_CASH_DISCTMsgArray) EZDTBLAccessor.findByCondition(pmtTermCashDiscTMsg);
        if (pmtTermCashDiscTMsgArray.length() == 0) {
            cMsg.setMessageInfo(NMAM8111E, new String[] {"PMT_TERM_CASH_DISC" });
            return;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ZYPCodeDataUtil.createPulldownList(CUST_INV_SRC.class, cMsg.A.no(i).custInvSrcCd_A1, cMsg.A.no(i).custInvSrcNm_A1);
            ZYPCodeDataUtil.createPulldownList(CUST_BLLG_TP.class, cMsg.A.no(i).custBllgTpCd_A1, cMsg.A.no(i).custBllgTpNm_A1);
            // 2019/01/22 QC#29371 SRNO13 Mod Start
            ZYPCodeDataUtil.createPulldownList(CUST_BLLG_VCLE.class, cMsg.A.no(i).custBllgVcleCd_A1, cMsg.A.no(i).custBllgVcleDescTxt_A1);
            // 2019/01/22 QC#29371 SRNO13 Mod Start
            ZYPCodeDataUtil.createPulldownList(DEF_INV_GRP.class, cMsg.A.no(i).defInvGrpCd_A1, cMsg.A.no(i).defInvGrpNm_A1);
            // Add Start 2016/11/11 M.Ohno S21_NA#2680
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(cMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(cMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
            // Add End   2016/11/11 M.Ohno S21_NA#2680
            for (int k = 0; k < pmtTermCashDiscTMsgArray.getValidCount(); k++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).pmtTermCashDiscCd_A1.no(k), pmtTermCashDiscTMsgArray.no(k).pmtTermCashDiscCd.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).pmtTermCashDiscNm_A1.no(k), pmtTermCashDiscTMsgArray.no(k).pmtTermCashDiscNm.getValue());
            }
        }

        setBackupData(sMsg);
    }

    private void getBillingCycleInfo(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal billToCustPk = cMsg.billToCustPk_H1.getValue();

        S21SsmEZDResult res = NMAL6730Query.getInstance().getBillingCycleInfo(glblCmpyCd, billToCustPk, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            EZDMsg.copy(sMsg, null, cMsg, null);
//            cMsg.defBaseTpCd_P1.setValue(sMsg.defBaseTpCd_P1.getValue());
//            cMsg.defBaseCycleCd_P1.setValue(sMsg.defBaseCycleCd_P1.getValue());
//            cMsg.defUsgTpCd_P1.setValue(sMsg.defUsgTpCd_P1.getValue());
//            cMsg.defUsgCycleCd_P1.setValue(sMsg.defUsgCycleCd_P1.getValue());
//            cMsg.dsBillTgtrFlg_I1.setValue(sMsg.dsBillTgtrFlg_I1.getValue());
        }
    }

    private void getRefAttribute(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String locNum = cMsg.locNum_H1.getValue();

        NMAL6730Query.getInstance().getDsAcctRefAttrb(glblCmpyCd, locNum, sMsg);
        // Add Start 2016/11/11 M.Ohno S21_NA#2680
        for (int i =0; i < sMsg.K.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.K.no(i).xxRecHistCratByNm_K1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.K.no(i).xxRecHistCratByNm_K1.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.K.no(i).xxRecHistUpdByNm_K1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.K.no(i).xxRecHistUpdByNm_K1.getValue()));
        }
        // Add End   2016/11/08 M.Ohno S21_NA#2680
        EZDMsg.copy(sMsg.K, null, cMsg.K, null);
    }

    private void doProcess_NMAL6730_NMAL2570(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
// QC#7781
//        String psnNm = NMAL6730CommonLogic.getPsnNm(getGlobalCompanyCode(), cMsg.A.no(cMsg.xxCellIdx.getValueInt()).itrlRvwPsnCd_A1.getValue());
//        ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxCellIdx.getValueInt()).xxPsnNm_A1, psnNm);
        searchPsnNm(cMsg, sMsg, cMsg.xxCellIdx.getValueInt());
    }
    
    // QC#7781
    private void searchPsnNm(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg, int rowIndex){
        String psnCdList = cMsg.A.no(rowIndex).xxGenlFldAreaTxt_A1.getValue();
        String[] psnCdArray = NMAL6730CommonLogic.splitByComma(psnCdList);
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<psnCdArray.length; i++){
            String psnCd = psnCdArray[i];
            String psnNm = NMAL6730CommonLogic.getPsnNm(getGlobalCompanyCode(), psnCd);
            if(sb.length() > 0){
                sb.append(NMAL6730Constant.CHAR_COMMA);
            }
            sb.append(psnNm);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(rowIndex).xxCustInvRuleRcpntTxt_A1, sb.toString());
    }
    
    private void doProcess_NMAL6730_NMAL6770(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        searchCtacPsnNm(cMsg, sMsg, cMsg.xxCellIdx.getValueInt());
    }
    
    private void searchCtacPsnNm(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg, int rowIndex){
        String ctacPsnPkList = cMsg.A.no(rowIndex).xxGenlFldAreaTxt_A2.getValue();
        String[] ctacPsnPkArray = NMAL6730CommonLogic.splitByComma(ctacPsnPkList);
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<ctacPsnPkArray.length; i++){
            String ctacPsnPk = ctacPsnPkArray[i];
            String ctacPsnNm = NMAL6730CommonLogic.getCtacPsnNm(getGlobalCompanyCode(), ctacPsnPk);
            if(sb.length() > 0){
                sb.append(NMAL6730Constant.CHAR_COMMA);
            }
            sb.append(ctacPsnNm);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(rowIndex).xxCustInvRuleRcpntTxt_A2, sb.toString());
    }

    private void doProcess_NMAL6730_NMAL6050(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", getGlobalCompanyCode());
//        param.put("dsCustArTmplPk", cMsg.dsCustArTmplPk_F1.getValue());
//
//        S21SsmEZDResult res = NMAL6730Query.getInstance().getTemplate(param, sMsg);
//        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
//            EZDMsg.copy(sMsg, null, cMsg, null);
//        }
    }

    // QC#6832
    private void doProcess_NMAL6730_NMAL2550(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        if ("OpenWin_Coa".equals(cMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.coaChCd_H1)) {
                doProcess_NMAL6730Scrn00_GetCoaChNm(cMsg, sMsg);
            }
        } else if ("OpenWin_Coa2".equals(cMsg.xxScrEventNm.getValue())) {
// QC#9448
//            if (ZYPCommonFunc.hasValue(cMsg.coaAfflCd_H1)) {
//                doProcess_NMAL6730Scrn00_GetInterCompanyNm(cMsg, sMsg);
//            }
        }
    }

    private void doProcess_NMAL6730Scrn00_GetCoaChNm(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        cMsg.coaChNm_H1.clear();
        COA_CHTMsg coaChTMsg = findCoaCh(getGlobalCompanyCode(), cMsg.coaChCd_H1.getValue());
        if (coaChTMsg == null) {
            cMsg.coaChCd_H1.setErrorInfo(1, NMAM8186E, new String[] {"GL Sales Channel" });
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.coaChNm_H1, coaChTMsg.coaChNm);
    }

// QC#9448
//    private void doProcess_NMAL6730Scrn00_GetInterCompanyNm(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
//        cMsg.coaAfflNm_H1.clear();
//        COA_AFFLTMsg coaAfflTMsg = findCoaAffl(getGlobalCompanyCode(), cMsg.coaAfflCd_H1.getValue());
//        if (coaAfflTMsg == null) {
//            cMsg.coaAfflCd_H1.setErrorInfo(1, NMAM8186E, new String[] {"GL Intercompany Code" });
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm_H1, coaAfflTMsg.coaAfflNm);
//    }

    private COA_CHTMsg findCoaCh(String glblCmpyCd, String coaChCd) {
        COA_CHTMsg prmTMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaChCd, coaChCd);
        return (COA_CHTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private COA_AFFLTMsg findCoaAffl(String glblCmpyCd, String coaAfflCd) {
        COA_AFFLTMsg prmTMsg = new COA_AFFLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaAfflCd, coaAfflCd);
        return (COA_AFFLTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
    }
    
    private void doProcess_NMAL6730Scrn00_ApplyTemplate(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        if (MESSAGE_KIND_WARN.equals(cMsg.getMessageKind()) || MESSAGE_KIND_ERR.equals(cMsg.getMessageKind())) {
            return;
        }

        S21SsmEZDResult res = NMAL6730Query.getInstance().getTemplate(cMsg, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            EZDMsg.copy(sMsg, null, cMsg, null);
            NMAL6730CommonLogic.getCltCustTpNm(cMsg);
            NMAL6730CommonLogic.getCltPtfoNm(cMsg);
        } else {
            cMsg.dsCustArTmplNm_F1.setErrorInfo(1, NZZM0000E);
            cMsg.setMessageInfo(NZZM0000E);
        }
    }
    /**
     * Method name: doProcess_NMAL6730Scrn00_GetCltCustTpNm
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_GetCltCustTpNm(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        NMAL6730CommonLogic.getCltCustTpNm(cMsg);
    }
    /**
     * Method name: doProcess_NMAL6730Scrn00_GetCltPtfoNm
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6730Scrn00_GetCltPtfoNm(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {

        NMAL6730CommonLogic.getCltPtfoNm(cMsg);

    }
    /**
     * Method name: clearMsg
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void clearMsg(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.K);
        ZYPTableUtil.clear(cMsg.K);
        ZYPTableUtil.clear(sMsg.T);

    }

    private void setBackupData(NMAL6730SMsg sMsg) {
        EZDMsg.copy(sMsg.A, null, sMsg.C, null);
        sMsg.C.setValidCount(sMsg.A.getValidCount());
    }

    // 2018/03/26 S21_NA#23935 Del Start
    /**
     * QC#19869 Add Method
     * getDefDplyCoaInfo
     * @param cMsg NMAL6730CMsg
     * @param sMsg NMAL6730SMsg
     */
//    private void getDefDplyCoaInfo(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg) {
//
//        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(tMsg.appFuncId, DEF_DPLY_COA_INFO_APP_FUNC_ID);
//
//        tMsg = (DEF_DPLY_COA_INFOTMsg) EZDTBLAccessor.findByKey(tMsg);
//
//        if (tMsg != null) {
//            ZYPEZDItemValueSetter.setValue(cMsg.coaCmpyCd_H1, tMsg.coaCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_H1, tMsg.coaAfflCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaBrCd_H1, tMsg.coaBrCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaCcCd_H1, tMsg.coaCcCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaAcctCd_H1, tMsg.coaAcctCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd_H1, tMsg.coaProdCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_H1, tMsg.coaProjCd);
//            ZYPEZDItemValueSetter.setValue(cMsg.coaExtnCd_H1, tMsg.coaExtnCd);
//
//            if(!ZYPCommonFunc.hasValue(cMsg.coaChCd_H1)){
//                ZYPEZDItemValueSetter.setValue(cMsg.coaChCd_H1, tMsg.coaChCd);
//            }
//
//            if (ZYPCommonFunc.hasValue(cMsg.coaChCd_H1)) {
//                doProcess_NMAL6730Scrn00_GetCoaChNm(cMsg, sMsg);
//            }
//        }
//    }
    // 2018/03/26 S21_NA#23935 Del Start
}

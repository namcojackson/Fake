/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1600;

import static business.blap.NWAL1600.constant.NWAL1600Constant.MODE_REFERENCE;
import static business.blap.NWAL1600.constant.NWAL1600Constant.NWAM0661E;
import static business.blap.NWAL1600.constant.NWAL1600Constant.NWAM0679E;
import static business.blap.NWAL1600.constant.NWAL1600Constant.NWAM0853E;
import static business.blap.NWAL1600.constant.NWAL1600Constant.NWZM0642E;
import static business.blap.NWAL1600.constant.NWAL1600Constant.NWAM0920E;
import static business.blap.NWAL1600.constant.NWAL1600Constant.REQ_DEL;
import static business.blap.NWAL1600.constant.NWAL1600Constant.REQ_MOD;
import static business.blap.NWAL1600.constant.NWAL1600Constant.REQ_NEW;
import static business.blap.NWAL1600.constant.NWAL1600Constant.SLS_REP_MAX_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1600.common.NWAL1600CommonLogic;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1600BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/27   Fujitsu         T.Murai         Update          S21_NA#4618
 * 2017/01/18   Fujitsu         W.Honda         Update          S21_NA#16750
 * 2017/01/23   Fujitsu         W.Honda         Update          S21_NA#17262
 * 2017/02/01   SRAA            K.Aratani       Update          S21_NA#17127
 * 2017/09/08   Fujitsu         S.Ohki          Update          S21_NA#20998(16855)
 * 2017/11/01   Fujitsu         K.Ishizuka      Update          S21_NA#20146
 * 2017/11/10   Fujitsu         T.Aoi           Update          S21_NA#20146
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 *</pre>
 */
public class NWAL1600BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

            if ("NWAL1600_INIT".equals(screenAplID)) {
                doProcess_NWAL1600_INIT(bizMsg);

            } else if ("NWAL1600Scrn00_AddRowSlsCr".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_AddRowSlsCr(bizMsg);

            } else if ("NWAL1600Scrn00_DeleteRowSlsCr".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_DeleteRowSlsCr(bizMsg);

            } else if ("NWAL1600Scrn00_AddRowNonQuote".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_AddRowNonQuote(bizMsg);

            } else if ("NWAL1600Scrn00_DeleteRowNonQuote".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_DeleteRowNonQuote(bizMsg);

            } else if ("NWAL1600_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1600_NWAL1130(bizMsg);

            } else if ("NWAL1600_NMAL6050".equals(screenAplID)) {
                doProcess_NWAL1600_NMAL6050(bizMsg);

            } else if ("NWAL1600Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_CMN_Close(bizMsg);

            } else if ("NWAL1600Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1600_INIT(bizMsg);

            } else if ("NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditName".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditName(bizMsg);

            } else if ("NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditCode".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditCode(bizMsg);

            } else if ("NWAL1600Scrn00_OnBlur_DeriveFromSalesRepName".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesRepName(bizMsg);

            } else if ("NWAL1600Scrn00_OnBlur_DeriveFromSalesRepCode".equals(screenAplID)) {
                doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg);
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
     */
    private void doProcess_NWAL1600_INIT(NWAL1600CMsg bizMsg) {

        // S21_NA#4618 Add Start
        S21SsmEZDResult ssmResultQuot = NWAL1600Query.getInstance().getQuotaRoleList(getGlobalCompanyCode());
        S21SsmEZDResult ssmResultNonQuot = NWAL1600Query.getInstance().getNonQuotaRoleList(getGlobalCompanyCode());
        // S21_NA#4618 Add End

        // set Sales Credit SalesRep
        for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
            NWAL1600_ACMsg acMsg = bizMsg.A.no(idx);

            // S21_NA#4618 Mod Start
            // ZYPCodeDataUtil.createPulldownList(LINE_BIZ_ROLE_TP.class, acMsg.lineBizRoleTpCd_AC, acMsg.lineBizRoleTpDescTxt_AD);
            NWAL1600CommonLogic.setupQuotRoleTpPulldown(ssmResultQuot, acMsg);
            // S21_NA#4618 Mod End

            List<Map<String, Object>> slsrepList = NWAL1600CommonLogic.getSalesRepList(getGlobalCompanyCode(), null, null, acMsg.slsRepTocCd_A.getValue());

            if (slsrepList == null || slsrepList.size() == 0) {

                if (!S21StringUtil.isEquals(bizMsg.xxModeCd.getValue(), MODE_REFERENCE)) {
                    bizMsg.setMessageInfo(NWZM0642E);
                }
            } else {

                NWAL1600CommonLogic.setupSalesCreditLine(acMsg, slsrepList.get(0));
            }
        }

        // set Non Quote SalesRep
        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            NWAL1600_BCMsg bcMsg = bizMsg.B.no(idx);

            // S21_NA#4618 Mod Start
            // ZYPCodeDataUtil.createPulldownList(LINE_BIZ_ROLE_TP.class, bcMsg.lineBizRoleTpCd_BC, bcMsg.lineBizRoleTpDescTxt_BD);
            NWAL1600CommonLogic.setupNonQuotRoleTpPulldown(ssmResultNonQuot, bcMsg);
            // S21_NA#4618 Mod End

            // 2017/11/10 S21_NA#20146 Mod Start
            //List<Map<String, Object>> slsrepList = NWAL1600CommonLogic.getSalesRepList(getGlobalCompanyCode(), null, null, bcMsg.slsRepTocCd_B.getValue());
            List<Map<String, Object>> slsrepList = NWAL1600CommonLogic.getSalesRepListForNonQuot(getGlobalCompanyCode(), null, null, bcMsg.slsRepTocCd_B.getValue());
            // 2017/11/10 S21_NA#20146 Mod End

            if (slsrepList == null || slsrepList.size() == 0) {

                if (!S21StringUtil.isEquals(bizMsg.xxModeCd.getValue(), MODE_REFERENCE)) {
                    bizMsg.setMessageInfo(NWZM0642E);
                }
            } else {

                NWAL1600CommonLogic.setupSalesRepLine(bcMsg, slsrepList.get(0));
            }
        }

        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        if (!S21StringUtil.isEquals(bizMsg.xxModeCd.getValue(), MODE_REFERENCE) && bizMsg.xxDtlCnt.getValue().intValue() > 1) {
            doProcess_NWAL1600Scrn00_AddRowSlsCr(bizMsg);
            doProcess_NWAL1600Scrn00_AddRowNonQuote(bizMsg);
        }
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
    }

    /**
     * AddRow for Sales Credit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1600Scrn00_AddRowSlsCr(NWAL1600CMsg bizMsg) {
        // Insert one row to the last
        int idx = bizMsg.A.getValidCount();
        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
        NWAL1600_ACMsg acMsg = bizMsg.A.no(idx);

        ZYPEZDItemValueSetter.setValue(acMsg.xxRqstTpCd_A, REQ_NEW);
        ZYPEZDItemValueSetter.setValue(acMsg.dsOrdPosnNum_A, bizMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(acMsg.slsRepCrPct_A, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(acMsg.slsCrQuotFlg_A, ZYPConstant.FLG_ON_Y);
        // S21_NA#4618 Mod Start
        // ZYPCodeDataUtil.createPulldownList(LINE_BIZ_ROLE_TP.class, acMsg.lineBizRoleTpCd_AC, acMsg.lineBizRoleTpDescTxt_AD);
        S21SsmEZDResult ssmResultQuot = NWAL1600Query.getInstance().getQuotaRoleList(getGlobalCompanyCode());
        NWAL1600CommonLogic.setupQuotRoleTpPulldown(ssmResultQuot, acMsg);
        // S21_NA#4618 Mod End
    }

    /**
     * DeleteRow for Sales Credit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1600Scrn00_DeleteRowSlsCr(NWAL1600CMsg bizMsg) {
        List<Integer> idxOfSelectedRow = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        int idx = bizMsg.C.getValidCount();
        for (int i : idxOfSelectedRow) {
            NWAL1600_ACMsg acMsg = bizMsg.A.no(i);

            if (!REQ_NEW.equals(acMsg.xxRqstTpCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsCpoSlsCrPk_C, acMsg.dsCpoSlsCrPk_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsOrdPosnNum_C, acMsg.dsOrdPosnNum_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).lineBizRoleTpCd_C, acMsg.lineBizRoleTpCd_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).slsCrQuotFlg_C, acMsg.slsCrQuotFlg_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).slsRepCrPct_C, acMsg.slsRepCrPct_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).slsRepTocCd_C, acMsg.slsRepTocCd_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRqstTpCd_C, REQ_DEL);

                idx++;
                bizMsg.C.setValidCount(idx);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.A, idxOfSelectedRow);
    }

    /**
     * AddRow for Non-Quote Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1600Scrn00_AddRowNonQuote(NWAL1600CMsg bizMsg) {
        // Insert one row to the last
        int idx = bizMsg.B.getValidCount();
        bizMsg.B.setValidCount(bizMsg.B.getValidCount() + 1);
        NWAL1600_BCMsg bcMsg = bizMsg.B.no(idx);

        ZYPEZDItemValueSetter.setValue(bcMsg.xxRqstTpCd_B, REQ_NEW);
        ZYPEZDItemValueSetter.setValue(bcMsg.dsOrdPosnNum_B, bizMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(bcMsg.slsCrQuotFlg_B, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bcMsg.xxChkBox_B, ZYPConstant.FLG_OFF_N);

        // S21_NA#4618 Mod Start
        // ZYPCodeDataUtil.createPulldownList(LINE_BIZ_ROLE_TP.class, bcMsg.lineBizRoleTpCd_BC, bcMsg.lineBizRoleTpDescTxt_BD);
        S21SsmEZDResult ssmResultNonQuot = NWAL1600Query.getInstance().getNonQuotaRoleList(getGlobalCompanyCode());
        NWAL1600CommonLogic.setupNonQuotRoleTpPulldown(ssmResultNonQuot, bcMsg);
        // S21_NA#4618 Mod End
    }

    /**
     * DeleteRow for Non-Quote Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1600Scrn00_DeleteRowNonQuote(NWAL1600CMsg bizMsg) {
        List<Integer> idxOfSelectedRow = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);

        int idx = bizMsg.C.getValidCount();
        for (int i : idxOfSelectedRow) {
            NWAL1600_BCMsg bcMsg = bizMsg.B.no(i);

            if (!REQ_NEW.equals(bcMsg.xxRqstTpCd_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsCpoSlsCrPk_C, bcMsg.dsCpoSlsCrPk_B);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dsOrdPosnNum_C, bcMsg.dsOrdPosnNum_B);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).lineBizRoleTpCd_C, bcMsg.lineBizRoleTpCd_B);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).slsCrQuotFlg_C, bcMsg.slsCrQuotFlg_B);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).slsRepTocCd_C, bcMsg.slsRepTocCd_B);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxRqstTpCd_C, REQ_DEL);

                idx++;
                bizMsg.C.setValidCount(idx);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.B, idxOfSelectedRow);
    }

    /**
     * doProcess_NWAL1600_NWAL1130
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1600_NWAL1130(NWAL1600CMsg bizMsg) {
        String eventName = bizMsg.xxScrEventNm.getValue();
        int idx = bizMsg.xxCellIdx.getValueInt();

        boolean isSlsCr = true;
        if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesRepName")) {

            isSlsCr = false;
        } else if (S21StringUtil.isEquals(eventName, "OnBlur_DeriveFromSalesRepCode")) {

            isSlsCr = false;
        } else if (S21StringUtil.isEquals(eventName, "OpenWin_NoQuoteSlsRep")) {

            isSlsCr = false;
        }
        if (isSlsCr) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).slsRepTocCd_A) && !ZYPCommonFunc.hasValue(bizMsg.A.no(idx).coaBrNm_A)) {
                NWAL1600_ACMsg acMsg = bizMsg.A.no(idx);

                List<Map<String, Object>> slsrepList = NWAL1600CommonLogic.getSalesRepList(getGlobalCompanyCode(), null, null, acMsg.slsRepTocCd_A.getValue());

                if (slsrepList == null || slsrepList.size() == 0) {

                    bizMsg.setMessageInfo(NWZM0642E);
                } else {

                    NWAL1600CommonLogic.setupSalesCreditLine(acMsg, slsrepList.get(0));
                }
            }
        } else {

            if (ZYPCommonFunc.hasValue(bizMsg.B.no(idx).slsRepTocCd_B) && !ZYPCommonFunc.hasValue(bizMsg.B.no(idx).coaBrNm_B)) {
                NWAL1600_BCMsg bcMsg = bizMsg.B.no(idx);

                ZYPCodeDataUtil.createPulldownList(LINE_BIZ_ROLE_TP.class, bcMsg.lineBizRoleTpCd_BC, bcMsg.lineBizRoleTpDescTxt_BD);

                List<Map<String, Object>> slsrepList = NWAL1600CommonLogic.getSalesRepList(getGlobalCompanyCode(), null, null, bcMsg.slsRepTocCd_B.getValue());

                if (slsrepList == null || slsrepList.size() == 0) {

                    bizMsg.setMessageInfo(NWZM0642E);
                } else {

                    NWAL1600CommonLogic.setupSalesRepLine(bcMsg, slsrepList.get(0));
                }
            }
        }
    }

    /**
     * doProcess_NWAL1600_NMAL6050
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1600_NMAL6050(NWAL1600CMsg bizMsg) {
        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(idx).slsRepTocCd_B) && !ZYPCommonFunc.hasValue(bizMsg.B.no(idx).coaBrNm_B)) {
                NWAL1600_BCMsg bcMsg = bizMsg.B.no(idx);

                ZYPCodeDataUtil.createPulldownList(LINE_BIZ_ROLE_TP.class, bcMsg.lineBizRoleTpCd_BC, bcMsg.lineBizRoleTpDescTxt_BD);

                List<Map<String, Object>> slsrepList = NWAL1600CommonLogic.getSalesRepList(getGlobalCompanyCode(), null, null, bcMsg.slsRepTocCd_B.getValue());

                if (slsrepList == null || slsrepList.size() == 0) {

                    bizMsg.setMessageInfo(NWZM0642E);
                } else {

                    NWAL1600CommonLogic.setupSalesRepLine(bcMsg, slsrepList.get(0));
                }
            }
        }
    }

    /**
     * CMN_Close Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1600Scrn00_CMN_Close(NWAL1600CMsg bizMsg) {
        boolean hasError = false;
        int writerCount = 0; // S21_NA#4618 Add
        int writerCountForSlsCredit = 0; // S21_NA#20146 Add
        int writerCountForSlsRep = 0; // S21_NA#20146 Add

        // S21_NA#16750 Add Start
        boolean lengthErr = false;
        if (SLS_REP_MAX_NUM <= bizMsg.B.getValidCount() + bizMsg.A.getValidCount()) {
            hasError = true;
            lengthErr = true;
        }
        // S21_NA#16750 Add End

        // 2017/09/08 S21_NA#20998 Add Start
        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray TMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (TMsgArray != null && TMsgArray.length() > 0) {
            for (int i = 0; i < TMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = (LINE_BIZ_ROLE_TPTMsg) TMsgArray.get(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }
        // 2017/09/08 S21_NA#20998 Add End

        // check Non Quote
        for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
            NWAL1600_BCMsg bcMsg = bizMsg.B.no(idx);

            // S21_NA#4618 Add Start
            // 2017/09/08 S21_NA#20998 Mod Start
//            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue()) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue())
//                    || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue())) {
            if (targetWriterList.contains(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue())) {
            // 2017/09/08 S21_NA#20998 Mod End
                writerCountForSlsRep++;// 2017/11/01 S21_NA#20146 MOD
            }
            // S21_NA#4618 Add End 
            
            if (ZYPCommonFunc.hasValue(bcMsg.slsRepTocCd_B)) {
                // Master Check : Salesrep Number
                if (!NWAL1600CommonLogic.checkSlsrepCd(getGlobalCompanyCode(), bcMsg.slsRepTocCd_B.getValue())) {
                    bcMsg.tocNm_B.setErrorInfo(1, NWZM0642E);
                    hasError = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(bcMsg.xxRqstTpCd_B)) {
                if (NWAL1600CommonLogic.isModifiedNonQuote(bcMsg)) {
                    bcMsg.xxRqstTpCd_B.setValue(REQ_MOD);
                }
            }

            // S21_NA#16750 Add Start
            if (lengthErr) {
                bcMsg.xxChkBox_B.setErrorInfo(1, NWAM0920E, new String[] {String.valueOf(SLS_REP_MAX_NUM)});
            }
            // S21_NA#16750 Add End
        }

        // boolean hasWriter = false; // S21_NA#4618 Del
        boolean hasRoleSales = false;
        // check Sales Credit
        for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
            NWAL1600_ACMsg acMsg = bizMsg.A.no(idx);

            // 2017/09/08 S21_NA#20998 Mod Start
//            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue()) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue())
//                    || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue())) {
            if (targetWriterList.contains(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue())) {
            // 2017/09/08 S21_NA#20998 Mod End
            	// S21_NA#4618 Mod
                // hasWriter = true;
                writerCountForSlsCredit++;  // S21_NA#20146 MOD
            }

            // Master Check : Salesrep Number
            if (!NWAL1600CommonLogic.checkSlsrepCd(getGlobalCompanyCode(), acMsg.slsRepTocCd_A.getValue())) {
                acMsg.tocNm_A.setErrorInfo(1, NWZM0642E);
                hasError = true;
            }

            // Salesrep Type Check
            if (!hasRoleSales) {
                if (NWAL1600CommonLogic.isSalesSlsrep(getGlobalCompanyCode(), acMsg.slsRepTocCd_A.getValue())) {
                    hasRoleSales = true;
                }
            }

            if (!ZYPCommonFunc.hasValue(acMsg.xxRqstTpCd_A)) {
                if (NWAL1600CommonLogic.isModifiedSlsCrdt(acMsg)) {
                    acMsg.xxRqstTpCd_A.setValue(REQ_MOD);
                }
            }

            // S21_NA#16750 Add Start
            if (lengthErr) {
                acMsg.xxChkBox_A.setErrorInfo(1, NWAM0920E, new String[] {String.valueOf(SLS_REP_MAX_NUM)});
            }
            // S21_NA#16750 Add End
        }

        if (hasError) {
            return;
        }
        // S21_NA#4618 Mod Start
//        if (!hasWriter) {
//            for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
//                bizMsg.A.no(idx).lineBizRoleTpCd_A.setErrorInfo(1, NWAM0679E);
//            }
//        }
        writerCount = writerCountForSlsCredit + writerCountForSlsRep; // S21_NA#20146 MOD
        if (writerCount == 0) {
            for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
                bizMsg.A.no(idx).lineBizRoleTpCd_A.setErrorInfo(1, NWAM0679E);
            }
            for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
                bizMsg.B.no(idx).lineBizRoleTpCd_B.setErrorInfo(1, NWAM0679E);
            }
        } 
        if (writerCountForSlsCredit != 1) { // S21_NA#20146 MOD
            for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
                // 2017/09/08 S21_NA#20998 Mod Start
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue()) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue())
//                        || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue())) {
                if (targetWriterList.contains(bizMsg.A.no(idx).lineBizRoleTpCd_A.getValue())) {
                // 2017/09/08 S21_NA#20998 Mod End
                    bizMsg.A.no(idx).lineBizRoleTpCd_A.setErrorInfo(1, NWAM0853E);
                }
            }
         // 2017/11/01 S21_NA#20146 DEL START
            //for (int idx = 0; idx < bizMsg.B.getValidCount(); idx++) {
                // 2017/09/08 S21_NA#20998 Mod Start
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue()) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue())
//                        || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue())) {
                //if (targetWriterList.contains(bizMsg.B.no(idx).lineBizRoleTpCd_B.getValue())) {
                // 2017/09/08 S21_NA#20998 Mod Start
                   // bizMsg.B.no(idx).lineBizRoleTpCd_B.setErrorInfo(1, NWAM0853E);
               // }
            //}
         // 2017/11/01 S21_NA#20146 DEL END
        }
        // S21_NA#4618 Mod End

        if (!hasRoleSales) {
            for (int idx = 0; idx < bizMsg.A.getValidCount(); idx++) {
                bizMsg.A.no(idx).tocNm_A.setErrorInfo(1, NWAM0661E);
            }
        }
    }

    private void doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditName(NWAL1600CMsg bizMsg) {

        int index = bizMsg.xxCellIdx.getValueInt();

        NWAL1600_ACMsg line = bizMsg.A.no(index);
        String glblCmpyCd = getGlobalCompanyCode();
        String psnCd = null;
        String tocNm = line.tocNm_A.getValue();
        String slsRepTocCd = null;

        line.slsRepTocCd_A.clear();

        //QC#17127
        //List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepList(glblCmpyCd, psnCd, tocNm, slsRepTocCd);
        List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepListForOnBlur(glblCmpyCd, psnCd, tocNm, slsRepTocCd);

        if (salesRepList == null// 2017/01/18 S21_NA#16750 Add
                || salesRepList.size() == 0) {

            line.psnNum_A.clear();  // 2016/05/12 SA21_NA7861 Mod psnCd_B -> psnNum_B
            line.coaBrNm_A.clear();
            line.coaCcNm_A.clear();
            line.coaExtnNm_A.clear();
            line.coaExtnNm_A.clear();
            //S21_NA ADD START QC#20146
            line.xxCoaBrSrchTxt_A.clear();
            line.xxCoaExtnSrchTxt_A.clear();
            line.xxCoaProdSrchTxt_A.clear();
            //S21_NA ADD END QC#20146
            line.slsRepTocCd_A.clear();

            line.tocNm_A.setErrorInfo(1, NWZM0642E);
            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else if (salesRepList.size() == 1) {

            // set sales credit
            NWAL1600CommonLogic.setupSalesCreditLine(line, salesRepList.get(0));

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else {

            // open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    private void doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesCreditCode(NWAL1600CMsg bizMsg) {

        int index = bizMsg.xxCellIdx.getValueInt();

        NWAL1600_ACMsg line = bizMsg.A.no(index);
        String glblCmpyCd = getGlobalCompanyCode();
        String psnCd = line.psnNum_A.getValue();  // 2016/05/12 SA21_NA7861 Mod psnCd_A -> psnNum_A
        String tocNm = null;
        String slsRepTocCd = null;

        line.slsRepTocCd_A.clear();

        //QC#17127
        //List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepList(glblCmpyCd, psnCd, tocNm, slsRepTocCd);
        List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepListForOnBlur(glblCmpyCd, psnCd, tocNm, slsRepTocCd);

        if (salesRepList == null// 2017/01/23 S21_NA#17262 Add
                || salesRepList.size() == 0) {

            line.tocNm_A.clear();
            line.coaBrNm_A.clear();
            line.coaCcNm_A.clear();
            line.coaExtnNm_A.clear();
            line.slsRepTocCd_A.clear();
            //S21_NA ADD START QC#20146
            line.xxCoaBrSrchTxt_A.clear();
            line.xxCoaExtnSrchTxt_A.clear();
            line.xxCoaProdSrchTxt_A.clear();
            //S21_NA ADD END QC#20146
            line.psnNum_A.setErrorInfo(1, NWZM0642E);

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else if (salesRepList.size() == 1) {

            // set sales credit
            NWAL1600CommonLogic.setupSalesCreditLine(line, salesRepList.get(0));

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else {

            // open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    private void doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesRepName(NWAL1600CMsg bizMsg) {

        int index = bizMsg.xxCellIdx.getValueInt();

        NWAL1600_BCMsg line = bizMsg.B.no(index);
        String glblCmpyCd = getGlobalCompanyCode();
        String psnCd = null;
        String tocNm = line.tocNm_B.getValue();
        String slsRepTocCd = null;

        line.slsRepTocCd_B.clear();

        //QC#17127
        //List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepList(glblCmpyCd, psnCd, tocNm, slsRepTocCd);
        // 2017/11/10 S21_NA#20146 Mod Start
        //List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepListForOnBlur(glblCmpyCd, psnCd, tocNm, slsRepTocCd);
        List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepListForOnBlurNonQuot(glblCmpyCd, psnCd, tocNm, slsRepTocCd);
        // 2017/11/10 S21_NA#20146 Mod End

        if (salesRepList == null// 2017/01/23 S21_NA#17262 Add
                || salesRepList.size() == 0) {

            line.psnNum_B.clear(); // 2016/05/12 SA21_NA7861 Mod psnCd_B -> psnNum_B
            line.coaBrNm_B.clear();
            line.coaCcNm_B.clear();
            line.coaExtnNm_B.clear();
            line.slsRepTocCd_B.clear();
            //S21_NA ADD START QC#20146
            line.xxCoaBrSrchTxt_B.clear();
            line.xxCoaExtnSrchTxt_B.clear();
            line.xxCoaProdSrchTxt_B.clear();
            //S21_NA ADD END QC#20146
            line.tocNm_B.setErrorInfo(1, NWZM0642E);

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else if (salesRepList.size() == 1) {

            // set sales credit
            NWAL1600CommonLogic.setupSalesRepLine(line, salesRepList.get(0));

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else {

            // open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    private void doProcess_NWAL1600Scrn00_OnBlur_DeriveFromSalesRepCode(NWAL1600CMsg bizMsg) {

        int index = bizMsg.xxCellIdx.getValueInt();

        NWAL1600_BCMsg line = bizMsg.B.no(index);
        String glblCmpyCd = getGlobalCompanyCode();
        String psnNum = line.psnNum_B.getValue();  // 2016/05/12 SA21_NA7861 Mod psnCd_B -> psnNum_B
        String tocNm = null;
        String slsRepTocCd = null;

        line.slsRepTocCd_B.clear();

        //QC#17127
        //List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepList(glblCmpyCd, psnNum, tocNm, slsRepTocCd);
        // 2017/11/10 S21_NA#20146 Mod Start
        //List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepListForOnBlur(glblCmpyCd, psnNum, tocNm, slsRepTocCd);
        List<Map<String, Object>> salesRepList = NWAL1600CommonLogic.getSalesRepListForOnBlurNonQuot(glblCmpyCd, psnNum, tocNm, slsRepTocCd);
        // 2017/11/10 S21_NA#20146 Mod End

        if (salesRepList == null// 2017/01/23 S21_NA#17262 Add
                || salesRepList.size() == 0) {

            line.tocNm_B.clear();
            line.coaBrNm_B.clear();
            line.coaCcNm_B.clear();
            line.coaExtnNm_B.clear();
            line.slsRepTocCd_B.clear();
            //S21_NA ADD START QC#20146
            line.xxCoaBrSrchTxt_B.clear();
            line.xxCoaExtnSrchTxt_B.clear();
            line.xxCoaProdSrchTxt_B.clear();
            //S21_NA ADD END QC#20146
            line.psnNum_B.setErrorInfo(1, NWZM0642E); // 2016/05/12 SA21_NA7861 Mod psnCd_B -> psnNum_B

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else if (salesRepList.size() == 1) {

            // set sales credit
            NWAL1600CommonLogic.setupSalesRepLine(line, salesRepList.get(0));

            // does not open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_OFF_N);
        } else {

            // open POPUP
            bizMsg.xxCondCd.setValue(ZYPConstant.FLG_ON_Y);
        }
    }
}
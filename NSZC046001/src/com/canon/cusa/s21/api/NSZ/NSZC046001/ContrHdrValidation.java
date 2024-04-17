/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;

import java.math.BigDecimal;
//START 2017/07/25 M.Kidokoro [QC#18122, ADD]
import java.util.ArrayList;
//END 2017/07/25 M.Kidokoro [QC#18122, ADD]
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDPItem;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

//START 2017/07/25 M.Kidokoro [QC#18122, ADD]
import business.db.DS_CONTR_CTRL_STSTMsg;
import business.db.DS_CONTR_CTRL_STSTMsgArray;
//END 2017/07/25 M.Kidokoro [QC#18122, ADD]
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1655
 * 2016/03/03   Hitachi         T.Kanasaka      Update          QC#3188
 * 2016/03/14   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/23   Hitachi         T.Iwamoto       Update          QC#5035
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/08   Hitachi         A.Kohinata      Update          QC#6622
 * 2016/04/13   Hitachi         T.Iwamoto       Update          QC#5035
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/07/19   Hitachi         A.Kohinata      Update          QC#11027
 * 2016/09/23   Hitachi         K.Yamada        Update          QC#13686
 * 2017/03/27   Hitachi         K.Kitachi       Update          QC#18122
 * 2017/07/25   Hitachi         M.Kidokoro      Update          QC#18122
 * 2018/01/30   CITS            M.Naito         Update          QC#21349
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2022/03/22   Hitachi         K.Kishimoto     Update          QC#59683
 * </pre>
 */
public class ContrHdrValidation {

    // START 2016/04/08 [QC#6622, ADD]
    /** ssm client */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(ContrHdrValidation.class);
    // END 2016/04/08 [QC#6622, ADD]

    private static void checkContrHdrParam(NSZC046001PMsg pMsg, EZDPItem item, String messageId, String param) {
        if (!ZYPCommonFunc.hasValue(item)) {
            setMsg(pMsg, messageId, param);
        }
    }

    private static void setMsg(NSZC046001PMsg pMsg, String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxMsgId_HD)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId_HD, messageId);

            // START 2016/03/25 [QC#5662, MOD]
            String msgTxt = NSZC046001CommonLogic.getMsgTxt(messageId, param);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMultMsgDplyTxt_HD, msgTxt);
            // END 2016/03/25 [QC#5662, MOD]
        }
    }

    private static Map<String, Integer> groupXsCopyByMachMtr(NSZC046001PMsg hdr) {
        Map<String, Integer> xsCopyMap = new HashMap<String, Integer>();

        for (int i = 0; i < hdr.xxContrXsCopyList.getValidCount(); i++) {
            NSZC046001_xxContrXsCopyListPMsg xsMsg = hdr.xxContrXsCopyList.no(i);
            // START 2016/03/14 T.Iwamoto [QC#5298, MOD]
            BigDecimal svcMachMstrPk = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(xsMsg.svcMachMstrPk)) {
                svcMachMstrPk = xsMsg.svcMachMstrPk.getValue();
            }
            String machMstrPk = svcMachMstrPk.toPlainString();
            // END 2016/03/14 T.Iwamoto [QC#5298, MOD]
            String mtrLbCd = xsMsg.bllgMtrLbCd.getValue();

            StringBuilder sb = new StringBuilder();
            sb.append(machMstrPk);
            sb.append("-");
            sb.append(mtrLbCd);
            String xsKey = sb.toString();

            int tierCnt = 0;
            if (xsCopyMap.containsKey(xsKey)) {
                tierCnt = xsCopyMap.get(xsKey);
            }
            xsCopyMap.put(xsKey, tierCnt + 1);
        }
        return xsCopyMap;
    }

    private static boolean checkMaster(NSZC046001PMsg param) {
        if (ZYPCommonFunc.hasValue(param.dsContrStsCd)
                && !NSZC046001CommonLogic.isExistDsContrSts(param.glblCmpyCd.getValue(), param.dsContrStsCd.getValue())) {
            setMsg(param, NSZM0652E, param.dsContrStsCd.getValue(), DS_CONTR_STS.class.getSimpleName());
            return false;
        }
        if (param.contrVrsnEffFromDt.getValue().compareTo(param.contrVrsnEffThruDt.getValue()) > 0) {
            setMsg(param, NSAM0279E);
            return false;
        }
        if (ZYPCommonFunc.hasValue(param.billToCustCd)
                && !NSZC046001CommonLogic.isExistBillTo(param.glblCmpyCd.getValue(), param.billToCustCd.getValue())) {
            setMsg(param, NSZM0652E, param.billToCustCd.getValue(), "BILL_TO_CUST");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistDsContrCatg(param.glblCmpyCd.getValue(), param.dsContrCatgCd.getValue())) {
            setMsg(param, NSZM0652E, param.dsContrCatgCd.getValue(), DS_CONTR_CATG.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistPmtTerm(param.glblCmpyCd.getValue(), param.pmtTermCashDiscCd.getValue())) {
            setMsg(param, NSZM0652E, param.pmtTermCashDiscCd.getValue(), PMT_TERM_CASH_DISC.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(param.leaseCmpyCd)
                && !NSZC046001CommonLogic.isExistBillTo(param.glblCmpyCd.getValue(), param.leaseCmpyCd.getValue())) {
            setMsg(param, NSZM0652E, param.leaseCmpyCd.getValue(), "BILL_TO_CUST");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistContrUplftTp(param.glblCmpyCd.getValue(), param.contrUplftTpCd.getValue())) {
            setMsg(param, NSZM0652E, param.contrUplftTpCd.getValue(), CONTR_UPLFT_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistSellTo(param.glblCmpyCd.getValue(), param.dsAcctNum.getValue())) {
            setMsg(param, NSZM0652E, param.dsAcctNum.getValue(), "SELL_TO_CUST");
            return false;
        }
        if (ZYPCommonFunc.hasValue(param.tocCd)
                && !NSZC046001CommonLogic.isExistToc(param.glblCmpyCd.getValue(), param.tocCd.getValue())) {
            setMsg(param, NSZM0652E, param.tocCd.getValue(), "S21_ORG");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistMtrEstTp(param.glblCmpyCd.getValue(), param.mtrEstTpCd.getValue())) {
            setMsg(param, NSZM0652E, param.mtrEstTpCd.getValue(), MTR_EST_TP.class.getSimpleName());
            return false;
        }
        if (!NSZC046001CommonLogic.isExistContrBr(param.glblCmpyCd.getValue(), param.svcContrBrCd.getValue())) {
            setMsg(param, NSZM0652E, param.svcContrBrCd.getValue(), "SVC_CONTR_BR");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistContrCls(param.glblCmpyCd.getValue(), param.dsContrClsCd.getValue())) {
            setMsg(param, NSZM0652E, param.dsContrClsCd.getValue(), DS_CONTR_CLS.class.getSimpleName());
            return false;
        }
        // mod start 2016/09/23 CSA Defect#13686
        if (ZYPCommonFunc.hasValue(param.ctacPsnPk)
                && !NSZC046001CommonLogic.isExistCtacPsn(param.glblCmpyCd.getValue(), param.ctacPsnPk.getValue(), param.slsDt.getValue())) {
        // mod end 2016/09/23 CSA Defect#13686
            setMsg(param, NSZM0652E, param.ctacPsnPk.getValue().toPlainString(), "CTAC_PSN");
            return false;
        }
        if (!NSZC046001CommonLogic.isExistContrEDI(param.glblCmpyCd.getValue(), param.dsContrEdiCd.getValue())) {
            setMsg(param, NSZM0652E, param.dsContrEdiCd.getValue(), DS_CONTR_EDI.class.getSimpleName());
            return false;
        }
        if (ZYPCommonFunc.hasValue(param.svcPgmMdseCd)
                && !NSZC046001CommonLogic.isExistMdse(param.glblCmpyCd.getValue(), param.svcPgmMdseCd.getValue())) {
            setMsg(param, NSZM0652E, param.svcPgmMdseCd.getValue(), "MDSE");
            return false;
        }

        return true;
    }

    private static boolean checkConsistency(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        if (!NSXC001001ContrValidation.checkLeaseChrg(param.leaseCmpyCd.getValue()
                , param.baseChrgToLeaseCmpyFlg.getValue(), param.usgChrgToLeaseCmpyFlg.getValue())) {
            setMsg(param, NSZM0665E);
            return false;
        }
        if (!NSXC001001ContrValidation.checkAggAllocation(param.dsContrCatgCd.getValue(), param.prcAllocByMachQtyFlg.getValue())) {
            setMsg(param, NSZM0666E);
            return false;
        }
        // START 2018/01/30 M.Naito [QC#21349, DEL]
//        if (!NSXC001001ContrValidation.checkInvSepTp(param.dsContrCatgCd.getValue(), param.invSeptBaseUsgFlg.getValue())) {
//            setMsg(param, NSZM0667E);
//            return false;
//        }
        // END 2018/01/30 M.Naito [QC#21349, DEL]
        if (!NSXC001001ContrValidation.checkBillToAndLease(param.billToCustCd.getValue(), param.leaseCmpyCd.getValue())) {
            setMsg(param, NSZM0668E);
            return false;
        }
        if (!NSXC001001ContrValidation.checkWarranty(param.dsContrCatgCd.getValue(), param.leaseCmpyCd.getValue())) {
            setMsg(param, NSZM0669E);
            return false;
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(param.dsContrCatgCd.getValue())) {
            Map<String, Integer> xsCopyMap = groupXsCopyByMachMtr(param);
            int tierCnt = 0;
            for (Integer cnt : xsCopyMap.values()) {
                tierCnt = cnt;
                break;
            }

            for (Entry<String, Integer> entry : xsCopyMap.entrySet()) {
                if (tierCnt != entry.getValue()) {
                    setMsg(param, NSZM0670E);
                    return false;
                }
            }
        }

//        if (ZYPCommonFunc.hasValue(param.billToCustCd)
//                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), param.billToCustCd.getValue(), onBatchType)) {
//            setMsg(param, NSZM0698E, "Bill To", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(param.leaseCmpyCd)
//                && !NSXC001001ContrValidation.checkAcctBillEligible(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), param.leaseCmpyCd.getValue(), onBatchType)) {
//            setMsg(param, NSZM0698E, "Lease Company", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(param.billToCustCd)
//                && !NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), param.billToCustCd.getValue(), onBatchType)
//                && !ZYPCommonFunc.hasValue(param.custPoNum)) {
//            setMsg(param, NSZM0698E, "Bill To", "Account");
//            return false;
//        }
//
//        if (ZYPCommonFunc.hasValue(param.leaseCmpyCd)
//                && !NSXC001001ContrValidation.checkPoRequired(param.glblCmpyCd.getValue(), param.slsDt.getValue()
//                , param.dsAcctNum.getValue(), param.leaseCmpyCd.getValue(), onBatchType)
//                && !ZYPCommonFunc.hasValue(param.custPoNum)) {
//            setMsg(param, NSZM0698E, "Lease Company", "Account");
//            return false;
//        }

        if (param.xxContrDtlList.getValidCount() == 0) {
            setMsg(param, NSZM0671E);
            return false;
        }
        return true;
    }

    // START 2016/04/08 [QC#6622, ADD]
    // START 2016/07/19 [QC#11027, MOD]
    private static boolean checkContrSts(NSZC046001PMsg param, String validFlg, String... validStsCdList) {
        Map<String, Object> dsContrStsMap = getDsContrStsV(param.glblCmpyCd.getValue(), param.dsContrPk.getValue());
        String dsContrStsCd = (String) dsContrStsMap.get("DS_CONTR_CTRL_STS_CD");
        String dsContrStsNm = (String) dsContrStsMap.get("DS_CONTR_CTRL_STS_NM");

        if (!NSZC046001CommonLogic.checkStsCd(dsContrStsCd, validFlg, validStsCdList)) {
            setMsg(param, NSZM0961E, "Contract Status", dsContrStsNm);
            return false;
        }
        return true;
    }

    private static Map<String, Object> getDsContrStsV(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrStsV", ssmPrm);
    }
    // END 2016/07/19 [QC#11027, MOD]
    // END 2016/04/08 [QC#6622, ADD]

    /**
     * validateForCreateMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForCreateMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {
        checkContrHdrParam(param, param.contrVrsnEffFromDt, NSZM0589E, "Contract Version Effective From Date");
        checkContrHdrParam(param, param.contrVrsnEffThruDt, NSZM0589E, "Contract Version Effective Thru Date");
        checkContrHdrParam(param, param.dsContrCatgCd, NSZM0589E, "Contract Category Code");
        // START 2016/03/23 [QC#5035, MOD]
        if (!NSZC046001CommonLogic.isWarranty(param)) {
            checkContrHdrParam(param, param.pmtTermCashDiscCd, NSZM0589E, "Payment Term Cash Discount Code");
        }
        // END 2016/03/23 [QC#5035, MOD]
        // Start 2022/03/22 [QC#59683, Add]
        checkContrHdrParam(param, param.dsInvTgtrTpCd, NSZM0589E, "Invoice Together Type Code");
        // End   2022/03/22 [QC#59683, Add]
        checkContrHdrParam(param, param.invSeptBaseUsgFlg, NSZM0589E, "Invoice Separate Base Usage Flag");
        checkContrHdrParam(param, param.dsContrCratDt, NSZM0589E, "DS Contract Create Date");
        checkContrHdrParam(param, param.dsContrCratPsnCd, NSZM0589E, "DS Contract Create Person Code");
        checkContrHdrParam(param, param.dsAcctNum, NSZM0589E, "DS Account Number");
        // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//        checkContrHdrParam(param, param.tocCd, NSZM0589E, "TOC Code");
//        checkContrHdrParam(param, param.mtrEstTpCd, NSZM0589E, "Meter Estimation Type Code");
        // END 2016/03/03 T.Kanasaka [QC3188, DEL]
        checkContrHdrParam(param, param.prcAllocByMachQtyFlg, NSZM0589E, "Price Allocation by Machine Quantity Flag");
        checkContrHdrParam(param, param.svcContrBrCd, NSZM0589E, "Service Contract Branch Code");
        // START 2016/06/30 T.Iwamoto [QC#10661, ADD]
        checkContrHdrParam(param, param.contrAdminPsnCd_HD, NSZM0589E, "Contract Admin Person Code");
        // END 2016/06/30 T.Iwamoto [QC#10661, ADD]
        checkContrHdrParam(param, param.dsContrClsCd, NSZM0589E, "DS Contract Classfication Code");
        // del start 2016/09/23 CSA Defect#13686
        //checkContrHdrParam(param, param.ctacPsnPk, NSZM0589E, "Contact Person PK");
        // del end 2016/09/23 CSA Defect#13686
        checkContrHdrParam(param, param.svcLineBizCd, NSZM0589E, "Service Line of Business Code");
        checkContrHdrParam(param, param.dsContrSrcTpCd_HD, NSZM0589E, "DS Contract Source Type Code");
        // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//        checkContrHdrParam(param, param.dsContrEdiCd, NSZM0589E, "DS Contract EDI Code");
        // END 2016/03/03 T.Kanasaka [QC3188, DEL]

        if (ZYPCommonFunc.hasValue(param.xxMsgId_HD)) {
            return false;
        }

        if (!checkMaster(param)) {
            return false;
        }

        if (!checkConsistency(param, onBatchType)) {
            return false;
        }
        return true;
    }

    /**
     * validateForUpdateMode
     * @param param NSZC046001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean validateForUpdateMode(NSZC046001PMsg param, ONBATCH_TYPE onBatchType) {

        // 2018/05/07 QC#22482 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(param.manContrOvrdFlg.getValue())) {
            return true;
        }
        // 2018/05/07 QC#22482 Add End

        checkContrHdrParam(param, param.dsContrSqNum, NSZM0589E, "DS Contract Sequence Number");
        checkContrHdrParam(param, param.dsContrStsCd, NSZM0589E, "DS Contract Status Code");
        checkContrHdrParam(param, param.contrVrsnEffFromDt, NSZM0589E, "Contract Version Effective From Date");
        checkContrHdrParam(param, param.contrVrsnEffThruDt, NSZM0589E, "Contract Version Effective Thru Date");
        checkContrHdrParam(param, param.dsContrCatgCd, NSZM0589E, "Contract Category Code");
        // START 2016/03/23 [QC#5035, MOD]
        if (!NSZC046001CommonLogic.isWarranty(param)) {
            checkContrHdrParam(param, param.pmtTermCashDiscCd, NSZM0589E, "Payment Term Cash Discount Code");
        }
        // END 2016/03/23 [QC#5035, MOD]
        checkContrHdrParam(param, param.ccyCd, NSZM0589E, "Currency Code");
        checkContrHdrParam(param, param.invSeptBaseUsgFlg, NSZM0589E, "Invoice Separate Base Usage Flag");
        checkContrHdrParam(param, param.dsContrCratDt, NSZM0589E, "DS Contract Create Date");
        checkContrHdrParam(param, param.dsContrCratPsnCd, NSZM0589E, "DS Contract Create Person Code");
        checkContrHdrParam(param, param.dsContrLastUpdDt, NSZM0589E, "DS Contract Last Update Date");
        checkContrHdrParam(param, param.dsContrLastUpdPsnCd, NSZM0589E, "DS Contract Last Update Person Code");
        checkContrHdrParam(param, param.dsAcctNum, NSZM0589E, "DS Account Number");
        // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//        checkContrHdrParam(param, param.tocCd, NSZM0589E, "TOC Code");
        // END 2016/03/03 T.Kanasaka [QC3188, DEL]
        checkContrHdrParam(param, param.baseChrgToLeaseCmpyFlg, NSZM0589E, "Base Charge to Lease Company Flag");
        checkContrHdrParam(param, param.usgChrgToLeaseCmpyFlg, NSZM0589E, "Usage Charge to Lease Company Flag");
        checkContrHdrParam(param, param.prcAllocByMachQtyFlg, NSZM0589E, "Price Allocation by Machine Quantity Flag");
        checkContrHdrParam(param, param.svcContrBrCd, NSZM0589E, "Service Contract Branch Code");
        // START 2016/06/30 T.Iwamoto [QC#10661, ADD]
        checkContrHdrParam(param, param.contrAdminPsnCd_HD, NSZM0589E, "Contract Admin Person Code");
        // END 2016/06/30 T.Iwamoto [QC#10661, ADD]
        checkContrHdrParam(param, param.dsContrClsCd, NSZM0589E, "DS Contract Classfication Code");
        // del start 2016/09/23 CSA Defect#13686
        //checkContrHdrParam(param, param.ctacPsnPk, NSZM0589E, "Contact Person PK");
        // del end 2016/09/23 CSA Defect#13686
        checkContrHdrParam(param, param.svcLineBizCd, NSZM0589E, "Service Line of Business Code");
        // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//        checkContrHdrParam(param, param.dsContrEdiCd, NSZM0589E, "DS Contract EDI Code");
        // END 2016/03/03 T.Kanasaka [QC3188, DEL]
        checkContrHdrParam(param, param.qltyAsrnHldFlg, NSZM0589E, "Quality Asuarance Hold Flag");
        checkContrHdrParam(param, param.mtrHldFlg, NSZM0589E, "Meter Hold Flag");
        checkContrHdrParam(param, param.contrHldFlg, NSZM0589E, "Contract Hold Flag");
        checkContrHdrParam(param, param.bllgHldFlg, NSZM0589E, "Billing Hold Flag");
        checkContrHdrParam(param, param.svcPgmMdseCd, NSZM0589E, "Service Program Merchandise Code");
        // START 2016/03/03 T.Kanasaka [QC3188, DEL]
//        checkContrHdrParam(param, param.contrAdminPsnCd_HD, NSZM0589E, "Contract Admin Person Code");
        // END 2016/03/03 T.Kanasaka [QC3188, DEL]

        if (ZYPCommonFunc.hasValue(param.xxMsgId_HD)) {
            return false;
        }

        if (!checkMaster(param)) {
            return false;
        }

        if (!checkConsistency(param, onBatchType)) {
            return false;
        }

        // START 2016/04/08 [QC#6622, ADD]
        String[] ngStsList = new String[] {DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED};
        if (!checkContrSts(param, ZYPConstant.FLG_OFF_N, ngStsList)) {
            return false;
        }
        // END 2016/04/08 [QC#6622, ADD]
        return true;
    }

    // START 2016/04/08 [QC#6622, ADD]
    /**
     * validateForAddContrMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForAddContrMode(NSZC046001PMsg param) {
        String[] ngStsList = new String[] {DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED };
        if (!checkContrSts(param, ZYPConstant.FLG_OFF_N, ngStsList)) {
            return false;
        }
        return true;
    }

    /**
     * validateForDeleteMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForDeleteMode(NSZC046001PMsg param) {
        String[] okStsList = new String[] {DS_CONTR_CTRL_STS.DRAFT, DS_CONTR_CTRL_STS.ENTERED };
        if (!checkContrSts(param, ZYPConstant.FLG_ON_Y, okStsList)) {
            return false;
        }
        return true;
    }

    /**
     * validateForTerminateMode
     * @param param NSZC046001PMsg
     * @return boolean
     */
    public static boolean validateForTerminateMode(NSZC046001PMsg param) {
        // START 2017/03/27 K.Kitachi [QC#18122, MOD]
//      String[] okStsList = new String[] {DS_CONTR_CTRL_STS.SINGED, DS_CONTR_CTRL_STS.ACTIVE, DS_CONTR_CTRL_STS.RENEWAL_HOLD, DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO };

//        String[] okStsList = new String[] {DS_CONTR_CTRL_STS.SINGED, DS_CONTR_CTRL_STS.ACTIVE, DS_CONTR_CTRL_STS.RENEWAL_HOLD, DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO, DS_CONTR_CTRL_STS.SYSTEM_HOLD };
        // END 2017/07/25 M.Kidokoro [QC#18122, DEL]
        // END 2017/03/27 K.// START 2017/07/25 M.Kidokoro [QC#18122, DEL]Kitachi [QC#18122, MOD]
        // START 2017/07/25 M.Kidokoro [QC#18122, ADD]
        String[] okStsList = NSZC046001CommonLogic.getDsContrCtrlSts(param.glblCmpyCd.getValue(), ZYPConstant.FLG_ON_Y);

        if (okStsList == null) {
            return false;
        }
        // END 2017/07/25 M.Kidokoro [QC#18122, ADD]
        if (!checkContrSts(param, ZYPConstant.FLG_ON_Y, okStsList)) {
            return false;
        }
        return true;
    }
    // END 2016/04/08 [QC#6622, ADD]
}

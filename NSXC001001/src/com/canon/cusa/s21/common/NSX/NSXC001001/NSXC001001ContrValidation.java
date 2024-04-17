/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_AUTO_RNW_TPTMsg;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.RNW_PRC_METHTMsg;
import business.db.SVC_BILL_BY_TPTMsg;
import business.db.UPLFT_PRC_METHTMsg;
import business.parts.NMZC610001PMsg;
import business.db.MDSETMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PGM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;


/**
 * <pre>
 * get Contract Validation
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Hitachi         K.Yamada        Create          N/A
 * 2015/11/16   Hitachi         T.Kanasaka      Update          QC729
 * 2016/01/18   Hitachi         T.Tomita        Update          QC#2948
 * 2016/02/12   Hitachi         T.Kanasaka      Update          QC3889
 * 2016/02/25   Hitachi         T.Kanasaka      Update          QC4086
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#7637
 * 2016/06/07   Hitachi         K.Yamada        Update          QC#3051
 * 2016/06/27   Hitachi         T.Iwamoto       Update          QC#10691
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12199
 * 2016/08/23   Hitachi         T.Tomita        Update          QC#13704
 * 2017/02/27   Hitachi         A.Kohinata      Update          QC#17656
 * 2017/06/22   Hitachi         K.Kitachi       Update          QC#19340
 * 2017/09/14   CITS            M.Naito         Update          QC#18534
 * 2017/09/19   Fujitsu         S.Fujita        Update          QC#18534
 * 2017/10/06   Hitachi         A.Kohinata      Update          QC#21639
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23629
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 *</pre>
 */
public class NSXC001001ContrValidation {

    /** billing cycle last day */
    private static final String PERIOD_END_LAST_DAY = "99";

    /** MTR_MULT_RATE_MIN_NUM */
    private static final String MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";

    /** MTR_MULT_RATE_MAX_NUM */
    private static final String MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";

    /** MTR_MULT_RATE_FCT_NUM */
    private static final String MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    /** PROCESS_MODE_ELIGIBLE_CHECK */
    private static final String PROCESS_MODE_ELIGIBLE_CHECK = "06";

    /** PROCESS_MODE_TRANSACTION */
    private static final String PROCESS_MODE_TRANSACTION = "01";

    // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//    /** MAX_RATIO */
//    private static final int MAX_RATIO = 100;
    // END 2018/05/14 K.Kitachi [QC#23541, DEL]

    // START 2016/06/07 K.Yamada [QC#3051, ADD]
    /** CSA_DEALER_CODE */
    private static final String CSA_DEALER_CODE = "CSA_DEALER_CODE";

    /** COMMA */
    private static final String COMMA = ",";
    // END 2016/06/07 K.Yamada [QC#3051, ADD]

    // del start 2017/10/06 QC#21639
    // START 2017/09/14 M.Naito [QC#18534, ADD]
//    /** ISTL_CALL_TP_CD */
//    private static final String ISTL_CALL_TP_CD = "ISTL_CALL_TP_CD";
    // END 2017/09/14 M.Naito [QC#18534, ADD]
    // del end 2017/10/06 QC#21639
    
    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ContrValidation.class);

    /**
     * checkLeaseChrg
     * @param leaseCmpyCd
     * @param baseChrgToLeaseCmpyFlg
     * @param usgChrgToLeaseCmpyFlg
     * @return
     */
    public static boolean checkLeaseChrg(String leaseCmpyCd, String baseChrgToLeaseCmpyFlg
            , String usgChrgToLeaseCmpyFlg) {

        if (!hasValue(leaseCmpyCd)) {
            return true;
        }
        if (FLG_OFF_N.equals(baseChrgToLeaseCmpyFlg) && FLG_OFF_N.equals(usgChrgToLeaseCmpyFlg)) {
            return false;
        }
        return true;
    }

    /**
     * checkAggAllocation
     * @param dsContrCatgCd
     * @param prcAllocByMachQtyFlg
     * @return boolean
     */
    public static boolean checkAggAllocation(String dsContrCatgCd, String prcAllocByMachQtyFlg) {
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && FLG_ON_Y.equals(prcAllocByMachQtyFlg)) {
            return false;
        }
        return true;
    }

    /**
     * checkInvSepTp
     * @param dsContrCatgCd
     * @param invSepBaseUsgFlg
     * @return boolean
     */
    public static boolean checkInvSepTp(String dsContrCatgCd, String invSepBaseUsgFlg) {
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && FLG_ON_Y.equals(invSepBaseUsgFlg)) {
            return false;
        }
        return true;
    }

    /**
     * checkBillToAndLease
     * @param billToCustCd
     * @param leaseCmpyCd
     * @return boolean
     */
    public static boolean checkBillToAndLease(String billToCustCd, String leaseCmpyCd) {
        // START 2016/01/18 T.Tomita [QC#2948, DEL]
//        if (hasValue(billToCustCd) && hasValue(leaseCmpyCd)) {
//            return false;
//        }
        // END 2016/01/18 T.Tomita [QC#2948, DEL]
        if (!hasValue(billToCustCd) && !hasValue(leaseCmpyCd)) {
            return false;
        }
        return true;
    }

    /**
     * checkWarranty
     * @param dsContrCatgCd
     * @param leaseCmpyCd
     * @return boolean
     */
    public static boolean checkWarranty(String dsContrCatgCd, String leaseCmpyCd) {
        if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd) && hasValue(leaseCmpyCd)) {
            return false;
        }
        return true;
    }

    /**
     * checkBllgCycle
     * @param glblCmpyCd
     * @param bllgCycleCd
     * @param contrCloDay
     * @return boolean
     */
    public static boolean checkBllgCycle(String glblCmpyCd, String bllgCycleCd, String contrCloDay) {
        BLLG_CYCLETMsg msg = getBllgCycle(glblCmpyCd, bllgCycleCd);

        if (msg == null || !hasValue(msg.bllgCycleLvlCd)) {
            return false;
        }
        if (msg.bllgCycleLvlCd.getValue().compareTo("1") > 0
                && !PERIOD_END_LAST_DAY.equals(contrCloDay)) {
            return false;
        }
        return true;
    }

    /**
     * checkBaseAndUsgCloDay
     * @param glblCmpyCd
     * @param dsContrDtlTpCd
     * @param contrCloDay
     * @param mtrCloDay
     * @return boolean
     */
    public static boolean checkBaseAndUsgCloDay(String glblCmpyCd, String dsContrDtlTpCd, String contrCloDay, String mtrCloDay) {
        DS_CONTR_DTL_TPTMsg msg = new DS_CONTR_DTL_TPTMsg();
        setValue(msg.glblCmpyCd, glblCmpyCd);
        setValue(msg.dsContrDtlTpCd, dsContrDtlTpCd);
        msg = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(msg);

        if (msg == null) {
            return false;
        }
        if (FLG_ON_Y.equals(msg.baseChrgFlg.getValue()) && FLG_ON_Y.equals(msg.usgChrgFlg.getValue())) {
            if (!hasValue(contrCloDay) || !hasValue(mtrCloDay)) {
                return false;
            }
            if (!contrCloDay.equals(mtrCloDay)) {
                return false;
            }
        }
        return true;
    }

    /**
     * checkBaseAndUsgBllgDay
     * @param glblCmpyCd
     * @param dsContrDtlTpCd
     * @param contrBllgDay
     * @param mtrBllgDay
     * @return boolean
     */
    public static boolean checkBaseAndUsgBllgDay(String glblCmpyCd, String dsContrDtlTpCd, String contrBllgDay, String mtrBllgDay) {
        DS_CONTR_DTL_TPTMsg msg = new DS_CONTR_DTL_TPTMsg();
        setValue(msg.glblCmpyCd, glblCmpyCd);
        setValue(msg.dsContrDtlTpCd, dsContrDtlTpCd);
        msg = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(msg);

        if (msg == null) {
            return false;
        }
        if (FLG_ON_Y.equals(msg.baseChrgFlg.getValue()) && FLG_ON_Y.equals(msg.usgChrgFlg.getValue())) {
            if (!hasValue(contrBllgDay) || !hasValue(mtrBllgDay)) {
                return false;
            }
            if (!contrBllgDay.equals(mtrBllgDay)) {
                return false;
            }
        }
        return true;
    }

    /**
     * checkContrDtlEffPeriodWithHdr
     * @param contrEffFromDt
     * @param contrEffThruDt
     * @param contrVrsnEffFromDt
     * @param contrVrsnEffThruDt
     * @return boolean
     */
    public static boolean checkContrDtlEffPeriodWithHdr(String contrEffFromDt, String contrEffThruDt
            ,String contrVrsnEffFromDt, String contrVrsnEffThruDt) {

        if (contrEffFromDt.compareTo(contrVrsnEffFromDt) < 0
                || contrEffThruDt.compareTo(contrVrsnEffThruDt) > 0) {
            return false;
        }
        return true;
    }

    /**
     * checkAccEffPeriodWithMach
     * @param contrEffFromDt
     * @param contrEffThruDt
     * @param machContrEffFromDt
     * @param machContrEffThruDt
     * @return boolean
     */
    public static boolean checkAccEffPeriodWithMach(String contrEffFromDt, String contrEffThruDt
            ,String machContrEffFromDt, String machContrEffThruDt) {

        if (contrEffFromDt.compareTo(machContrEffFromDt) < 0
                || contrEffThruDt.compareTo(machContrEffThruDt) > 0) {
            return false;
        }
        return true;
    }

    /**
     * checkDuplicateContr
     * @param glblCmpyCd
     * @param dsContrCatgCd
     * @param svcMachMstrPk
     * @param dsContrDtlPk
     * @param contrEffFromDt
     * @param contrEffThruDt
     * @return boolean
     */
    public static boolean checkDuplicateContr(String glblCmpyCd, String dsContrCatgCd, BigDecimal svcMachMstrPk,
            BigDecimal dsContrDtlPk, String contrEffFromDt, String contrEffThruDt) {

        Map<String, Object> prmMap = new HashMap<String, Object>();

        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        prmMap.put("dsContrDtlPk", dsContrDtlPk);
        if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            prmMap.put("wtyFlg", FLG_ON_Y);
        } else {
            prmMap.put("wtyFlg", FLG_OFF_N);
        }
        prmMap.put("warranty", DS_CONTR_CATG.WARRANTY);
        prmMap.put("fromDate", contrEffFromDt);
        prmMap.put("thruDate", contrEffThruDt);
        // START 2016/02/25 T.Kanasaka [QC4086, ADD]
        prmMap.put("dsContrDtlStsCd_Cancelled", DS_CONTR_DTL_STS.CANCELLED);
        // END 2016/02/25 T.Kanasaka [QC4086, ADD]
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        prmMap.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        @SuppressWarnings("unchecked")
        List<BigDecimal> duplicateList = (List<BigDecimal>) SSM_CLIENT.queryObjectList("getDuplicateContr", prmMap);
        if (duplicateList != null && !duplicateList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * checkLeaseBillTo
     * @param leaseCmpyCd
     * @param billToCustCd
     * @return boolean
     */
    public static boolean checkLeaseBillTo(String leaseCmpyCd, String billToCustCd) {
        if (hasValue(leaseCmpyCd) && hasValue(billToCustCd)) {
            if (!leaseCmpyCd.equals(billToCustCd)) {
                return false;
            }
        }
        return true;

    }

    /**
     * checkBllgMtrParam
     * @param bllgMinCnt
     * @param bllgMinAmtRate
     * @param bllgRoleOverRatio
     * @param bllgFreeCopyCnt
     * @return boolean
     */
    public static boolean checkBllgMtrParam(BigDecimal bllgMinCnt, BigDecimal bllgMinAmtRate
            , BigDecimal bllgRoleOverRatio, BigDecimal bllgFreeCopyCnt) {

        int setColumn = 0;
        if (hasValue(bllgMinCnt)) {
            setColumn++;
        }
        if (hasValue(bllgMinAmtRate)) {
            setColumn++;
        }
        if (hasValue(bllgFreeCopyCnt)) {
            setColumn++;
        }
        if (hasValue(bllgRoleOverRatio)) {
            // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//            if (!(hasValue(bllgFreeCopyCnt) && bllgRoleOverRatio.compareTo(BigDecimal.valueOf(MAX_RATIO)) == 0)) {
//                setColumn++;
//            }
            setColumn++;
            // END 2018/05/14 K.Kitachi [QC#23541, MOD]
        }

        if (setColumn > 1) {
            return false;
        }
        return true;
    }

    // START 2015/11/16 T.Kanasaka [QC729, MOD]
    /**
     * checkXsMinVol
     * @param bllgMinCnt
     * @param tierCnt
     * @param xsMtrCopyQty
     * @return boolean
     */
    public static boolean checkXsMinVol(BigDecimal bllgMinCnt, int tierCnt, BigDecimal xsMtrCopyQty) {
        if (hasValue(bllgMinCnt) && tierCnt > 1) {
            return false;
        }
        if (hasValue(bllgMinCnt) && (!hasValue(xsMtrCopyQty) || xsMtrCopyQty.compareTo(BigDecimal.ZERO) != 0)) {
            return false;
        }
        return true;
    }
    // END 2015/11/16 T.Kanasaka [QC729, MOD]

    // START 2016/05/23 M.Gotou [QC#7637, ADD]
    /**
     * checkXsMinVol
     * @param bllgMinCnt
     * @param tierCnt
     * @return boolean
     */
    public static boolean checkXsMinVol(BigDecimal bllgMinCnt, int tierCnt) {
        if (hasValue(bllgMinCnt) && tierCnt > 1) {
            return false;
        }
        return true;
    }

    /**
     * checkMinVol
     * @param bllgMinCnt
     * @param xsMtrCopyQty
     * @return boolean
     */
    public static boolean checkXsMinVol(BigDecimal bllgMinCnt, BigDecimal xsMtrCopyQty) {
        if (hasValue(bllgMinCnt) && (!hasValue(xsMtrCopyQty) || xsMtrCopyQty.compareTo(BigDecimal.ZERO) != 0)) {
            return false;
        }
        return true;
    }
    // END 2016/05/23 M.Gotou [QC#7637, ADD]

    /**
     * checkXsMinAmtRollOver
     * @param bllgMinAmtRate
     * @param bllgRoleOverRatio
     * @param tierCnt
     * @return boolean
     */
    public static boolean checkXsMinAmtRollOver(BigDecimal bllgMinAmtRate, BigDecimal bllgRoleOverRatio, int tierCnt) {
        if (hasValue(bllgMinAmtRate) && tierCnt > 1) {
            return false;
        }
        // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//        if (hasValue(bllgRoleOverRatio) && tierCnt > 1) {
//            return false;
//        }
        // END   2018/05/14 K.Kitachi [QC#23541, DEL]
        return true;
    }

    // START 2016/05/23 M.Gotou [QC#7637, ADD]
    /**
     * checkXsMinAmt
     * @param bllgMinAmtRate
     * @param tierCnt
     * @return boolean
     */
    public static boolean checkXsMinAmt(BigDecimal bllgMinAmtRate, int tierCnt) {
        if (hasValue(bllgMinAmtRate) && tierCnt > 1) {
            return false;
        }
        return true;
    }

    /**
     * checkXsRollOver
     * @param bllgRoleOverRatio
     * @param tierCnt
     * @return boolean
     */
    public static boolean checkRollOver(BigDecimal bllgRoleOverRatio, int tierCnt) {
        if (hasValue(bllgRoleOverRatio) && tierCnt > 1) {
            return false;
        }
        return true;
    }
    // END 2016/05/23 M.Gotou [QC#7637, ADD]

    /**
     * checkFreeCopy
     * @param bllgRoleOverRatio
     * @param bllgFreeCopyCnt
     * @return boolean
     */
    public static boolean checkFreeCopy(BigDecimal bllgRoleOverRatio, BigDecimal bllgFreeCopyCnt) {
        // START 2018/02/19 M.Kidokoro [QC#23629, DEL]
//        if (hasValue(bllgFreeCopyCnt) && bllgRoleOverRatio.compareTo(BigDecimal.valueOf(MAX_RATIO)) != 0) {
//            return false;
//        }
        // END 2018/02/19 M.Kidokoro [QC#23629, DEL]
        return true;
    }

    // START 2016/05/23 M.Gotou [QC#7637, ADD]
    /**
     * checkFreeCopy
     * @param bllgFreeCopyCnt
     * @param tierCnt
     * @return boolean
     */
    public static boolean checkFreeCopy(BigDecimal bllgFreeCopyCnt, int tierCnt) {
        // START 2018/02/19 M.Kidokoro [QC#23629, DEL]
//        if (hasValue(bllgFreeCopyCnt) && tierCnt > 1) {
//            return false;
//        }
        // END 2018/02/19 M.Kidokoro [QC#23629, DEL]
        return true;
    }
    // END 2016/05/23 M.Gotou [QC#7637, ADD]

    /**
     * checkAutoRnwMeth
     * @param glblCmpyCd
     * @param contrAutoRnwTpCd
     * @param rnwPrcMethCd
     * @return boolean
     */
    public static boolean checkAutoRnwMeth(String glblCmpyCd, String contrAutoRnwTpCd, String rnwPrcMethCd) {
        // mod start 2017/02/27 CSA Defect#17656
        //CONTR_AUTO_RNW_TPTMsg rnwTp = getRnwTp(glblCmpyCd, contrAutoRnwTpCd);
        //if (rnwTp == null) {
        //    return false;
        //}
        //if (FLG_ON_Y.equals(rnwTp.autoRnwFlg.getValue()) && !hasValue(rnwPrcMethCd)) {
        //    return false;
        //}
        if (!CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(contrAutoRnwTpCd) && !hasValue(rnwPrcMethCd)) {
            return false;
        }
        // mod end 2017/02/27 CSA Defect#17656
        return true;
    }

    /**
     * checkBasePrcUpRatioMandatory
     * @param glblCmpyCd
     * @param contrAutoRnwTpCd
     * @param rnwPrcMethCd
     * @param basePrcUpRatio
     * @return boolean
     */
    public static boolean checkBasePrcUpRatioMandatory(String glblCmpyCd, String contrAutoRnwTpCd
            ,String rnwPrcMethCd, BigDecimal basePrcUpRatio) {

        CONTR_AUTO_RNW_TPTMsg rnwTp = getRnwTp(glblCmpyCd, contrAutoRnwTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (rnwTp == null) {
            return false;
        }
        // START 2016/07/26 [QC#12199, DEL]
        //if (FLG_OFF_N.equals(rnwTp.autoRnwFlg.getValue())) {
        //    return true;
        //}
        // END 2016/07/26 [QC#12199, DEL]
        // add start 2016/08/23 CSA Defect#13704
        if (CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(contrAutoRnwTpCd)) {
            return true;
        }
        // add end 2016/08/23 CSA Defect#13704
        RNW_PRC_METHTMsg rnwMeth = getRnwMeth(glblCmpyCd, rnwPrcMethCd);

        if (rnwMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (FLG_ON_Y.equals(rnwTp.rnwBaseFlg.getValue())
                && RNW_PRC_METH.MARKUP_PERCENT.equals(rnwMeth.rnwPrcMethCd.getValue())
                && !hasValue(basePrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkBasePrcUpRatioNotAcceptable
     * @param glblCmpyCd
     * @param contrAutoRnwTpCd
     * @param rnwPrcMethCd
     * @param basePrcUpRatio
     * @return boolean
     */
    public static boolean checkBasePrcUpRatioNotAcceptable(String glblCmpyCd, String contrAutoRnwTpCd
            ,String rnwPrcMethCd, BigDecimal basePrcUpRatio) {

        CONTR_AUTO_RNW_TPTMsg rnwTp = getRnwTp(glblCmpyCd, contrAutoRnwTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (rnwTp == null) {
            return false;
        }
        // START 2016/07/26 [QC#12199, DEL]
        //if (FLG_OFF_N.equals(rnwTp.autoRnwFlg.getValue())) {
        //    return true;
        //}
        // END 2016/07/26 [QC#12199, DEL]
        // add start 2016/08/23 CSA Defect#13704
        if (CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(contrAutoRnwTpCd)) {
            return true;
        }
        // add end 2016/08/23 CSA Defect#13704
        RNW_PRC_METHTMsg rnwMeth = getRnwMeth(glblCmpyCd, rnwPrcMethCd);

        if (rnwMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (!(FLG_ON_Y.equals(rnwTp.rnwBaseFlg.getValue())
                && RNW_PRC_METH.MARKUP_PERCENT.equals(rnwMeth.rnwPrcMethCd.getValue()))
                && hasValue(basePrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkUsgPrcUpRatioMandatory
     * @param glblCmpyCd
     * @param contrAutoRnwTpCd
     * @param rnwPrcMethCd
     * @param usgPrcUpRatio
     * @return boolean
     */
    public static boolean checkUsgPrcUpRatioMandatory(String glblCmpyCd, String contrAutoRnwTpCd
            ,String rnwPrcMethCd, BigDecimal usgPrcUpRatio) {

        CONTR_AUTO_RNW_TPTMsg rnwTp = getRnwTp(glblCmpyCd, contrAutoRnwTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (rnwTp == null) {
            return false;
        }
        // START 2016/07/26 [QC#12199, DEL]
        //if (FLG_OFF_N.equals(rnwTp.autoRnwFlg.getValue())) {
        //    return true;
        //}
        // END 2016/07/26 [QC#12199, DEL]
        // add start 2016/08/23 CSA Defect#13704
        if (CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(contrAutoRnwTpCd)) {
            return true;
        }
        // add end 2016/08/23 CSA Defect#13704
        RNW_PRC_METHTMsg rnwMeth = getRnwMeth(glblCmpyCd, rnwPrcMethCd);

        if (rnwMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (FLG_ON_Y.equals(rnwTp.rnwUsgFlg.getValue())
                && RNW_PRC_METH.MARKUP_PERCENT.equals(rnwMeth.rnwPrcMethCd.getValue())
                && !hasValue(usgPrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkUsgPrcUpRatioNotAcceptable
     * @param glblCmpyCd
     * @param contrAutoRnwTpCd
     * @param rnwPrcMethCd
     * @param usgPrcUpRatio
     * @return boolean
     */
    public static boolean checkUsgPrcUpRatioNotAcceptable(String glblCmpyCd, String contrAutoRnwTpCd
            ,String rnwPrcMethCd, BigDecimal usgPrcUpRatio) {

        CONTR_AUTO_RNW_TPTMsg rnwTp = getRnwTp(glblCmpyCd, contrAutoRnwTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (rnwTp == null) {
            return false;
        }
        // START 2016/07/26 [QC#12199, DEL]
        //if (FLG_OFF_N.equals(rnwTp.autoRnwFlg.getValue())) {
        //    return true;
        //}
        // END 2016/07/26 [QC#12199, DEL]
        // add start 2016/08/23 CSA Defect#13704
        if (CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(contrAutoRnwTpCd)) {
            return true;
        }
        // add end 2016/08/23 CSA Defect#13704
        RNW_PRC_METHTMsg rnwMeth = getRnwMeth(glblCmpyCd, rnwPrcMethCd);

        if (rnwMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (!(FLG_ON_Y.equals(rnwTp.rnwUsgFlg.getValue())
                && RNW_PRC_METH.MARKUP_PERCENT.equals(rnwMeth.rnwPrcMethCd.getValue()))
                && hasValue(usgPrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkBaseUplftRatioMandatory
     * @param glblCmpyCd
     * @param contrUplftTpCd
     * @param uplftMethCd
     * @param uplftBasePrcUpRatio
     * @return boolean
     */
    public static boolean checkBaseUplftRatioMandatory(String glblCmpyCd, String contrUplftTpCd
            ,String uplftMethCd, BigDecimal uplftBasePrcUpRatio) {

        CONTR_UPLFT_TPTMsg uplftTp = getUplftTp(glblCmpyCd, contrUplftTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (uplftTp == null) {
            return false;
        }
        if (FLG_OFF_N.equals(uplftTp.uplftBaseFlg.getValue()) && FLG_OFF_N.equals(uplftTp.uplftUsgFlg.getValue())) {
            return true;
        }
        UPLFT_PRC_METHTMsg uplftMeth = getUplftMeth(glblCmpyCd, uplftMethCd);
        if (uplftMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (FLG_ON_Y.equals(uplftTp.uplftBaseFlg.getValue())
                && UPLFT_PRC_METH.MARKUP_PERCENT.equals(uplftMeth.uplftPrcMethCd.getValue())
                && !hasValue(uplftBasePrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkBaseUplftRatioNotAcceptable
     * @param glblCmpyCd
     * @param contrUplftTpCd
     * @param uplftMethCd
     * @param uplftBasePrcUpRatio
     * @return boolean
     */
    public static boolean checkBaseUplftRatioNotAcceptable(String glblCmpyCd, String contrUplftTpCd
            ,String uplftMethCd, BigDecimal uplftBasePrcUpRatio) {

        CONTR_UPLFT_TPTMsg uplftTp = getUplftTp(glblCmpyCd, contrUplftTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (uplftTp == null) {
            return false;
        }
        if (FLG_OFF_N.equals(uplftTp.uplftBaseFlg.getValue()) && FLG_OFF_N.equals(uplftTp.uplftUsgFlg.getValue())) {
            return true;
        }
        UPLFT_PRC_METHTMsg uplftMeth = getUplftMeth(glblCmpyCd, uplftMethCd);
        if (uplftMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (!(FLG_ON_Y.equals(uplftTp.uplftBaseFlg.getValue())
                && UPLFT_PRC_METH.MARKUP_PERCENT.equals(uplftMeth.uplftPrcMethCd.getValue()))
                && hasValue(uplftBasePrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkUsgUplftRatioMandatory
     * @param glblCmpyCd
     * @param contrUplftTpCd
     * @param uplftMethCd
     * @param uplftUsgPrcUpRatio
     * @return boolean
     */
    public static boolean checkUsgUplftRatioMandatory(String glblCmpyCd, String contrUplftTpCd
            ,String uplftMethCd, BigDecimal uplftUsgPrcUpRatio) {

        CONTR_UPLFT_TPTMsg uplftTp = getUplftTp(glblCmpyCd, contrUplftTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (uplftTp == null) {
            return false;
        }
        if (FLG_OFF_N.equals(uplftTp.uplftBaseFlg.getValue()) && FLG_OFF_N.equals(uplftTp.uplftUsgFlg.getValue())) {
            return true;
        }
        UPLFT_PRC_METHTMsg uplftMeth = getUplftMeth(glblCmpyCd, uplftMethCd);
        if (uplftMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (FLG_ON_Y.equals(uplftTp.uplftUsgFlg.getValue())
                && UPLFT_PRC_METH.MARKUP_PERCENT.equals(uplftMeth.uplftPrcMethCd.getValue())
                && !hasValue(uplftUsgPrcUpRatio)) {
            return false;
        }
        return true;
    }

    /**
     * checkUsgUplftRatioNotAcceptable
     * @param glblCmpyCd
     * @param contrUplftTpCd
     * @param uplftMethCd
     * @param uplftUsgPrcUpRatio
     * @return boolean
     */
    public static boolean checkUsgUplftRatioNotAcceptable(String glblCmpyCd, String contrUplftTpCd
            ,String uplftMethCd, BigDecimal uplftUsgPrcUpRatio) {

        CONTR_UPLFT_TPTMsg uplftTp = getUplftTp(glblCmpyCd, contrUplftTpCd);
        // START 2016/06/27 [QC#10691, MOD]
        if (uplftTp == null) {
            return false;
        }
        if (FLG_OFF_N.equals(uplftTp.uplftBaseFlg.getValue()) && FLG_OFF_N.equals(uplftTp.uplftUsgFlg.getValue())) {
            return true;
        }
        UPLFT_PRC_METHTMsg uplftMeth = getUplftMeth(glblCmpyCd, uplftMethCd);
        if (uplftMeth == null) {
            return false;
        }
        // END 2016/06/27 [QC#10691, MOD]

        if (!(FLG_ON_Y.equals(uplftTp.uplftUsgFlg.getValue())
                && UPLFT_PRC_METH.MARKUP_PERCENT.equals(uplftMeth.uplftPrcMethCd.getValue()))
                && hasValue(uplftUsgPrcUpRatio)) {
            return false;
        }
        return true;
    }

    private static CONTR_AUTO_RNW_TPTMsg getRnwTp(String glblCmpyCd, String contrAutoRnwTpCd) {
        CONTR_AUTO_RNW_TPTMsg rnwTp = new CONTR_AUTO_RNW_TPTMsg();
        setValue(rnwTp.glblCmpyCd, glblCmpyCd);
        setValue(rnwTp.contrAutoRnwTpCd, contrAutoRnwTpCd);

        return (CONTR_AUTO_RNW_TPTMsg) S21CodeTableAccessor.findByKey(rnwTp);
    }

    private static RNW_PRC_METHTMsg getRnwMeth(String glblCmpyCd, String rnwPrcMethCd) {
        RNW_PRC_METHTMsg rnwMeth = new RNW_PRC_METHTMsg();
        setValue(rnwMeth.glblCmpyCd, glblCmpyCd);
        setValue(rnwMeth.rnwPrcMethCd, rnwPrcMethCd);

        return (RNW_PRC_METHTMsg) S21CodeTableAccessor.findByKey(rnwMeth);
    }

    private static CONTR_UPLFT_TPTMsg getUplftTp(String glblCmpyCd, String contrUplftTpCd) {
        CONTR_UPLFT_TPTMsg uplftTp = new CONTR_UPLFT_TPTMsg();
        setValue(uplftTp.glblCmpyCd, glblCmpyCd);
        setValue(uplftTp.contrUplftTpCd, contrUplftTpCd);

        return (CONTR_UPLFT_TPTMsg) S21CodeTableAccessor.findByKey(uplftTp);
    }

    private static UPLFT_PRC_METHTMsg getUplftMeth(String glblCmpyCd, String uplftMethCd) {
        UPLFT_PRC_METHTMsg upLft = new UPLFT_PRC_METHTMsg();
        setValue(upLft.glblCmpyCd, glblCmpyCd);
        setValue(upLft.uplftPrcMethCd, uplftMethCd);

        return (UPLFT_PRC_METHTMsg) S21CodeTableAccessor.findByKey(upLft);
    }

    private static BLLG_CYCLETMsg getBllgCycle(String glblCmpyCd, String bllgCycleCd) {
        BLLG_CYCLETMsg msg = new BLLG_CYCLETMsg();
        setValue(msg.glblCmpyCd, glblCmpyCd);
        setValue(msg.bllgCycleCd, bllgCycleCd);

        return (BLLG_CYCLETMsg) S21CodeTableAccessor.findByKey(msg);
    }

    private static SVC_BILL_BY_TPTMsg getSvcBillByTp(String glblCmpyCd, String svcBillByTpCd) {
        SVC_BILL_BY_TPTMsg msg = new SVC_BILL_BY_TPTMsg();
        setValue(msg.glblCmpyCd, glblCmpyCd);
        setValue(msg.svcBillByTpCd, svcBillByTpCd);

        return (SVC_BILL_BY_TPTMsg) S21CodeTableAccessor.findByKey(msg);
    }

    /**
     * checkCrCardPo
     * @param custPoNum
     * @param crCardExprYrMth
     * @return boolean
     */
    public static boolean checkCrCardPo(String custPoNum, String crCardExprYrMth) {
        if (hasValue(custPoNum) && !hasValue(crCardExprYrMth)) {
            return false;
        }
        if (!hasValue(custPoNum) && hasValue(crCardExprYrMth)) {
            return false;
        }
        return true;
    }

    // START 2016/02/12 T.Kanasaka [QC3889, ADD]
    // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//    /**
//     * checkPoExprDt
//     * @param custPoNum
//     * @param poExprDt
//     * @return boolean
//     */
//    public static boolean checkPoExprDt(String custPoNum, String poExprDt) {
//        if (hasValue(custPoNum) && !hasValue(poExprDt)) {
//            return false;
//        }
//        if (!hasValue(custPoNum) && hasValue(poExprDt)) {
//            return false;
//        }
//        return true;
//    }
    /**
     * checkPoExprDt
     * @param poNum String
     * @param fromDt String
     * @param thruDt String
     * @return boolean
     */
    public static boolean checkPoExprDt(String poNum, String fromDt, String thruDt) {
        if (!hasValue(poNum) && !hasValue(fromDt) && !hasValue(thruDt)) {
            return true;
        }
        if (!hasValue(poNum)) {
            return false;
        }
        if (!hasValue(fromDt)) {
            return false;
        }
        if (!hasValue(thruDt)) {
            return false;
        }
        return true;
    }
    // END 2019/01/09 K.Kitachi [QC#26928, MOD]
    // END 2016/02/12 T.Kanasaka [QC3889, ADD]

    // START 2019/01/09 K.Kitachi [QC#26928, ADD]
    /**
     * checkConsistentPoDt
     * @param fromDt String
     * @param thruDt String
     * @return boolean
     */
    public static boolean checkConsistentPoDt(String fromDt, String thruDt) {
        if (!hasValue(fromDt)) {
            return true;
        }
        if (!hasValue(thruDt)) {
            return true;
        }
        if (ZYPDateUtil.compare(fromDt, thruDt) > 0) {
            return false;
        }
        return true;
    }

    /**
     * checkDuplicatePoDt
     * @param glblCmpyCd String
     * @param dsContrCrCardPoPk BigDecimal
     * @param dsContrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param dsContrMachLvlNum String
     * @param poNum String
     * @param fromDt String
     * @param thruDt String
     * @return boolean
     */
    public static boolean checkDuplicatePoDt(String glblCmpyCd, BigDecimal dsContrCrCardPoPk, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String dsContrMachLvlNum, String poNum, String fromDt, String thruDt) {

        if (!hasValue(dsContrPk)) {
            return true;
        }
        if (!hasValue(poNum)) {
            return true;
        }
        if (!hasValue(fromDt)) {
            return true;
        }
        if (!hasValue(thruDt)) {
            return true;
        }

        boolean isUpdate = false;
        if (hasValue(dsContrCrCardPoPk)) {
            DS_CONTR_CR_CARD_POTMsg inMsg = new DS_CONTR_CR_CARD_POTMsg();
            setValue(inMsg.glblCmpyCd, glblCmpyCd);
            setValue(inMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
            DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = (DS_CONTR_CR_CARD_POTMsg) S21ApiTBLAccessor.findByKey(inMsg);
            if (dsContrCrCardPoTMsg == null) {
                return true;
            }
            if (dsContrCrCardPoTMsg.custPoNum.equals(poNum)) {
                isUpdate = true;
            }
            if (dsContrCrCardPoTMsg.poFromDt.equals(fromDt) && dsContrCrCardPoTMsg.poDt.equals(thruDt)) {
                isUpdate = true;
            }
        }

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        if (!isUpdate) {
            prmMap.put("dsContrCrCardPoPk", dsContrCrCardPoPk);
        }
        prmMap.put("dsContrPk", dsContrPk);
        prmMap.put("dsContrDtlPk", dsContrDtlPk);
        prmMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        prmMap.put("dsContrMachLvlNum", dsContrMachLvlNum);
        prmMap.put("poNum", poNum);
        prmMap.put("fromDt", fromDt);
        prmMap.put("thruDt", thruDt);

        BigDecimal count = (BigDecimal) SSM_CLIENT.queryObject("countDupPoDt", prmMap);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }
    // END 2019/01/09 K.Kitachi [QC#26928, ADD]

    /**
     * checkAddlChrgTermDt
     * @param trmnDt
     * @param effThruDt
     * @return boolean
     */
    public static boolean checkAddlChrgTermDt(String trmnDt, String effThruDt) {
        if (hasValue(trmnDt) && trmnDt.compareTo(effThruDt) > 0) {
            return false;
        }
        return true;
    }

    /**
     * checkAddlChrgBllgCycle
     * @param glblCmpyCd
     * @param bllgCycleCd
     * @param contrBaseBllgCycleList
     * @return boolean
     */
    public static boolean checkAddlChrgBllgCycle(String glblCmpyCd, String bllgCycleCd, List<String> contrBaseBllgCycleList) {

        BLLG_CYCLETMsg addlChrgCycle = getBllgCycle(glblCmpyCd, bllgCycleCd);
        if (addlChrgCycle == null) {
            return false;
        }
        String addlCycleLv = addlChrgCycle.bllgCycleLvlCd.getValue();
        if (!hasValue(addlCycleLv)) {
            return false;
        }

        boolean hasSmallerBllgCycle = false;
        for (String baseBllgCycleCd : contrBaseBllgCycleList) {
            BLLG_CYCLETMsg contrCycle = getBllgCycle(glblCmpyCd, baseBllgCycleCd);

            if (contrCycle == null) {
                hasSmallerBllgCycle = true;
                break;
            }
            String contrCycleLv = contrCycle.bllgCycleLvlCd.getValue();
            if (!hasValue(contrCycleLv)) {
                hasSmallerBllgCycle = true;
                break;
            }

            if (addlCycleLv.compareTo(contrCycleLv) < 0) {
                hasSmallerBllgCycle = true;
                break;
            }
            if (addlCycleLv.compareTo(contrCycleLv) == 0 && !bllgCycleCd.equals(baseBllgCycleCd)) {
                hasSmallerBllgCycle = true;
                break;
            }
        }

        if (hasSmallerBllgCycle) {
            return false;
        }
        return true;

    }

    /**
     * checkAddlChrgBillByFlatTp
     * @param glblCmpyCd
     * @param billByTpCd
     * @param flatPrcDealAmt
     * @return boolean
     */
    public static boolean checkAddlChrgBillByFlatTp(String glblCmpyCd, String billByTpCd, BigDecimal flatPrcDealAmt) {

        SVC_BILL_BY_TPTMsg billByTp = getSvcBillByTp(glblCmpyCd, billByTpCd);

        if (billByTp == null) {
            return false;
        }
        if (FLG_OFF_N.equals(billByTp.flatPrcAvalFlg.getValue()) && hasValue(flatPrcDealAmt)) {
            return false;
        }
        return true;
    }

    /**
     * checkAddlChrgBillByPctTp
     * @param glblCmpyCd
     * @param billByTpCd
     * @param apclPct
     * @return boolean
     */
    public static boolean checkAddlChrgBillByPctTp(String glblCmpyCd, String billByTpCd, BigDecimal apclPct) {

        SVC_BILL_BY_TPTMsg billByTp = getSvcBillByTp(glblCmpyCd, billByTpCd);

        if (billByTp == null) {
            return false;
        }
        if (FLG_OFF_N.equals(billByTp.pctPrcAvalFlg.getValue()) && hasValue(apclPct)) {
            return false;
        }
        return true;
    }

    /**
     * checkBllgMtrMultRateRange
     * @param glblCmpyCd
     * @param contrMtrMultRate
     * @return boolean
     */
    public static boolean checkBllgMtrMultRateRange(String glblCmpyCd, BigDecimal contrMtrMultRate) {
       BigDecimal minNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_MIN_NUM, glblCmpyCd);
       BigDecimal maxNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_MAX_NUM, glblCmpyCd);

       if (minNum.compareTo(contrMtrMultRate) > 0 || maxNum.compareTo(contrMtrMultRate) < 0) {
           return false;
       }
       return true;
    }

    /**
     * checkBllgMtrMultRate
     * @param glblCmpyCd
     * @param contrMtrMultRate
     * @return boolean
     */
    public static boolean checkBllgMtrMultRate(String glblCmpyCd, BigDecimal contrMtrMultRate) {
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_FCT_NUM, glblCmpyCd);

        BigDecimal result = contrMtrMultRate.divide(fctNum);
        if (result.scale() > 0) {
            return false;
        }
        return true;
    }

    /**
     * checkAcctBillEligible
     * @param glblCmpyCd String
     * @param slsDt
     * @param dsAcctNum
     * @param billToCustCd
     * @param onBatchType
     * @return boolean
     */
    public static boolean checkAcctBillEligible(String glblCmpyCd, String slsDt, String dsAcctNum, String billToCustCd, ONBATCH_TYPE onBatchType) {

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.billToCustCd, billToCustCd);
        setValue(apiMsg.dsAcctNum_I2, dsAcctNum);
        setValue(apiMsg.slsDt, slsDt);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        if (FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnBillToFlg_B.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkPoRequired
     * @param glblCmpyCd
     * @param slsDt
     * @param dsAcctNum
     * @param billToCustCd
     * @param onBatchType
     * @return boolean
     */
    public static boolean checkPoRequired(String glblCmpyCd, String slsDt, String dsAcctNum, String billToCustCd, ONBATCH_TYPE onBatchType) {

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, PROCESS_MODE_TRANSACTION);
        setValue(apiMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.CONTRACT);
        setValue(apiMsg.billToCustCd, billToCustCd);
        setValue(apiMsg.dsAcctNum_I1, dsAcctNum);
        setValue(apiMsg.slsDt, slsDt);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        if (FLG_ON_Y.equals(apiMsg.TransactionRuleList.no(0).dsPoReqFlg.getValue())) {
            return true;
        }
        return false;
    }

    // START 2016/06/07 K.Yamada [QC#3051, ADD]
    // START 2017/06/22 K.Kitachi [QC#19340, MOD]
    /**
     * isLeaseCompany
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return boolean
     */
    public static boolean isLeaseCompany(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("019");
        billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        billToCustTMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(billToCustTMsg);
        if (billToCustTMsgArray.getValidCount() == 0) {
            return false;
        }
        String sellToCustCd = billToCustTMsgArray.no(0).sellToCustCd.getValue();
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("003");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        sellToCustTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray sellToCustTMsgArray = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(sellToCustTMsg);
        if (sellToCustTMsgArray.getValidCount() == 0) {
            return false;
        }
        String dsAcctDlrCd = sellToCustTMsgArray.no(0).dsAcctDlrCd.getValue();
        if (!hasValue(dsAcctDlrCd)) {
            return false;
        }
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CSA_DEALER_CODE, glblCmpyCd);
        if (!hasValue(constVal)) {
            return false;
        }
        String[] csaDlrCdList = constVal.split(COMMA);
        for (String csaDlrCd : csaDlrCdList) {
            if (dsAcctDlrCd.equals(csaDlrCd)) {
                return true;
            }
        }
        return false;
    }
    // END 2017/06/22 K.Kitachi [QC#19340, MOD]
    // END 2016/06/07 K.Yamada [QC#3051, ADD]

    // START 2017/09/14 M.Naito [QC#18534, ADD]
    /**
     * checkMachineInstalled
     * @param glblCmpyCd String
     * @param svcMachMstrPk String
     * @return boolean
     */
    public static boolean checkMachineInstalled(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        Map<String, Object> prmMap = new HashMap<String, Object>();

        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        // del start 2017/10/06 QC#21639
//        prmMap.put("istlCallTpCd", ISTL_CALL_TP_CD);
//        prmMap.put("cancelled", SVC_TASK_STS.CANCELLED);
        // del end 2017/10/06 QC#21639
        // add start 2017/10/06 QC#21639
        prmMap.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        // add end 2017/10/06 QC#21639

        BigDecimal count = (BigDecimal) SSM_CLIENT.queryObject("getMachineNotInstalledCount", prmMap);
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return true;
        } else {
            return false;
        }
    }
    // END 2017/09/14 M.Naito [QC#18534, ADD]

    // START 2017/09/19 S.Fujita [QC#18534,ADD]
    /**
     * checkCsaWarranty
     * @param glblCmpyCd String
     * @param dsContrCatgCd String
     * @param svcPgmMdseCd String
     * @return boolean
     */
    public static boolean checkCsaWarranty(String glblCmpyCd, String dsContrCatgCd, String svcPgmMdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mdseCd, svcPgmMdseCd);
        MDSETMsg rtnMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg != null && hasValue(rtnMsg.svcPgmTpCd.getValue())) {
            if ((DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) && SVC_PGM_TP.CSA_WARRANTY.equals(rtnMsg.svcPgmTpCd.getValue())) {
                return false;
            }
        }
        return true;
    }
    // END 2017/09/19 S.Fujita [QC#18534,ADD]

    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
    /**
     * checkXsMinAmt
     * @param bllgMinAmtRate
     * @param xsMtrCopyQty
     * @return boolean
     */
    public static boolean checkXsMinAmt(BigDecimal bllgMinAmtRate, BigDecimal xsMtrCopyQty) {
        if (hasValue(bllgMinAmtRate) && (!hasValue(xsMtrCopyQty) || xsMtrCopyQty.compareTo(BigDecimal.ZERO) != 0)) {
            return false;
        }
        return true;
    }

    /**
     * checkRollOver
     * @param bllgRoleOverRatio
     * @param xsMtrCopyQty
     * @return boolean
     */
    public static boolean checkRollOver(BigDecimal bllgRoleOverRatio, BigDecimal xsMtrCopyQty) {
        if (hasValue(bllgRoleOverRatio) && hasValue(xsMtrCopyQty) && xsMtrCopyQty.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END 2018/05/14 K.Kitachi [QC#23541, ADD]
}

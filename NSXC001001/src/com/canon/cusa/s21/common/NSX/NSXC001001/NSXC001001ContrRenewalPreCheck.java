/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_RNW_ERR_RSN;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Renewal Pre-Check
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         T.Iwamoto       Create          NA
 * 2017/10/11   Hitachi         M.Kidokoro      Update          QC#21546
 * 2017/10/31   Hitachi         U.Kim           Update          QC#21546-1
 * 2018/06/18   Hitachi         U.Kim           Update          QC#24903
 * 2018/12/19   Fujitsu         W.Honda         Update          QC#29636
 *</pre>
 */
public class NSXC001001ContrRenewalPreCheck {

    /** parameterList */
    private List<ContrRenewalPreCheckInfo> paramList;

    /** ssm client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NSXC001001ContrRenewalPreCheck.class);

    /** The target data does not exist in DS_CONTR_PRC_EFF. */
    private static final String NSZM0618E = "NSZM0618E";

    /**
     * The number of contract renewal times has reached the upper
     * limit. Contract cannot be renewed.
     */
    private static final String NSZM0844E = "NSZM0844E";

    /**
     * Base charge exceeds the upper limit to appreciation rate after
     * renewal. Contract cannot be renewed.
     */
    private static final String NSZM0845E = "NSZM0845E";

    /**
     * Excess Rate exceeds the upper limit to appreciation rate after
     * renewal. Contract cannot be renewed.
     */
    private static final String NSZM0846E = "NSZM0846E";

    // START 2018/06/18 U.Kim [QC#24903,DEL]
    // // START 2017/10/31 U.Kim [QC#21546-1, ADD]
    // /** The corresponding data does not exist in "SVC_INV_LINE. */
    // private static final String NSZM0137E = "NSZM0137E";
    // 
    // // END 2017/10/31 U.Kim [QC#21546-1, ADD]
    // END 2018/06/18 U.Kim [QC#24903,DEL]

    /**
     * Constructor
     * @param paramList List<ContrRenewalPreCheckInfo>
     */
    public NSXC001001ContrRenewalPreCheck(List<ContrRenewalPreCheckInfo> paramList) {
        this.paramList = paramList;
    }

    /**
     * Contract Renewal Pre-Check
     */
    public void contrRenewalPreCheck() {

        for (ContrRenewalPreCheckInfo param : paramList) {
            // get Contract Detail Info
            Map<String, Object> contrdtlInfo = getContrDtlInfo(param);
            // START 2017/10/31 U.Kim [QC#21546-1, ADD]
            // START 2018/06/18 U.Kim [QC#24903,DEL]
            // Map<String, Object> originalBaseAmt = getOriginalBaseAmt(param);
            // Map<String, Object> originalUsageAmt = getOriginalUsageAmt(param);
            // END 2018/06/18 U.Kim [QC#24903,DEL]
            // END 2017/10/31 U.Kim [QC#21546-1, ADD]
            if (contrdtlInfo == null) {
                param.setContrRnwErrRsnCd(CONTR_RNW_ERR_RSN.UNEXPECTED_ERROR);
                param.setXxMsgId(NSZM0618E);
                param.setXxMsgTxt(S21MessageFunc.clspGetMessage(NSZM0618E));
                return;
            }
            BigDecimal maxContrRnwCnt = (BigDecimal) contrdtlInfo.get("MAX_CONTR_RNW_CNT");
            BigDecimal contrRnwTotCnt = (BigDecimal) contrdtlInfo.get("CONTR_RNW_TOT_CNT");
            BigDecimal maxPrcUpRatio = (BigDecimal) contrdtlInfo.get("MAX_PRC_UP_RATIO");
            // START 2018/06/18 U.Kim [QC#24903,MOD]
            // // START 2017/10/31 U.Kim [QC#21546-1, MOD]
            // // BigDecimal effBasePrcDealAmt = (BigDecimal) contrdtlInfo.get("BASE_PRC_DEAL_AMT");
            // // BigDecimal effXsMtrAmtRate = (BigDecimal) contrdtlInfo.get("XS_MTR_AMT_RATE");
            // BigDecimal effBasePrcDealAmt;
            // BigDecimal effXsMtrAmtRate;
            // if (originalBaseAmt == null) {
            //     effBasePrcDealAmt = (BigDecimal) contrdtlInfo.get("BASE_PRC_DEAL_AMT");
            // } else {
            //     effBasePrcDealAmt = (BigDecimal) originalBaseAmt.get("INV_DISP_UNIT_PRC_AMT");
            // }
            // if (originalUsageAmt == null) {
            //     effXsMtrAmtRate = (BigDecimal) contrdtlInfo.get("XS_MTR_AMT_RATE");
            // } else {
            //     effXsMtrAmtRate = (BigDecimal) originalUsageAmt.get("XS_MTR_AMT_RATE");
            // }
            // // END 2017/10/31 U.Kim [QC#21546-1, MOD]
            BigDecimal effBasePrcDealAmt = (BigDecimal) contrdtlInfo.get("BASE_PRC_DEAL_AMT");
            BigDecimal effXsMtrAmtRate = (BigDecimal) contrdtlInfo.get("XS_MTR_AMT_RATE");
            // END 2018/06/18 U.Kim [QC#24903,MOD]

            // Check MAX_CONTR_RNW_CNT
            if (!checkMaxContrRnwCnt(param, contrRnwTotCnt, maxContrRnwCnt)) {
                return;
            }

            // Check MAX_PRC_UP_RATIO for Base
            if (!checkMaxPrcUpRatioBase(param, maxPrcUpRatio, effBasePrcDealAmt)) {
                return;
            }

            // Check MAX_PRC_UP_RATIO for Usage
            if (!checkMaxPrcUpRatioUsage(param, maxPrcUpRatio, effXsMtrAmtRate)) {
                return;
            }
        }
    }

    /**
     * getContrDtlInfo
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getContrDtlInfo(ContrRenewalPreCheckInfo param) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", param.getGlblCmpyCd());
        paramMap.put("dsContrDtlPk", param.getDsContrDtlPk());
        if (hasValue(param.getDsContrBllgMtrPk())) {
            paramMap.put("dsContrBllgMtrPk", param.getDsContrBllgMtrPk());
            // START 2018/06/18 U.Kim [QC#24903,ADD]
            paramMap.put("xsMtrCopyQty", param.getXsMtrCopyQty());
            // END 2018/06/18 U.Kim [QC#24903,ADD]
            paramMap.put("usageFlg", ZYPConstant.FLG_ON_Y);
        } else {
            paramMap.put("baseFlg", ZYPConstant.FLG_ON_Y);
        }
        // START 2018/12/19 W.Honda [QC#29636,ADD]
        paramMap.put("machLvlNumLv1", "1");
        paramMap.put("machLvlNumLv2", "2");
        paramMap.put("machLvlNumLv3", "3");
        paramMap.put("baseUsgNumBase", "BASE");
        paramMap.put("baseUsgNumUsg", "OVERAGE");
        // END 2018/12/19 W.Honda [QC#29636,ADD]

        return (Map<String, Object>) ssmBatchClient.queryObject("getContrDtlInfo", paramMap);
    }

    // START 2018/06/18 U.Kim [QC#24903,DEL]
    // // START 2017/10/31 U.Kim [QC#21546-1, ADD]
    // /**
    //  * getOriginalBaseAmt
    //  * @param param
    //  * @return
    //  */
    // @SuppressWarnings("unchecked")
    // private Map<String, Object> getOriginalBaseAmt(ContrRenewalPreCheckInfo param) {
    //     Map<String, Object> paramMap = new HashMap<String, Object>();
    //     paramMap.put("glblCmpyCd", param.getGlblCmpyCd());
    //     paramMap.put("dsContrDtlPk", param.getDsContrDtlPk());
    //     paramMap.put("baseFlg", ZYPConstant.FLG_ON_Y);
    // 
    //     return (Map<String, Object>) ssmBatchClient.queryObject("getOriginalBaseAmt", paramMap);
    //     
    // }
    // 
    // /**
    //  * getOriginalUsageAmt
    //  * @param param
    //  * @return
    //  */
    // @SuppressWarnings("unchecked")
    // private Map<String, Object> getOriginalUsageAmt(ContrRenewalPreCheckInfo param) {
    //     Map<String, Object> paramMap = new HashMap<String, Object>();
    //     paramMap.put("glblCmpyCd", param.getGlblCmpyCd());
    //     paramMap.put("dsContrDtlPk", param.getDsContrDtlPk());
    //     if (hasValue(param.getDsContrBllgMtrPk())) {
    //         paramMap.put("dsContrBllgMtrPk", param.getDsContrBllgMtrPk());
    //         paramMap.put("usageFlg", ZYPConstant.FLG_ON_Y);
    // 
    //         return (Map<String, Object>) ssmBatchClient.queryObject("getOriginalUsageAmt", paramMap);
    //     } else {
    //         return null;
    //     }
    // }
    // 
    // // END 2017/10/31 U.Kim [QC#21546-1, ADD]
    // END 2018/06/18 U.Kim [QC#24903,DEL]

    /**
     * checkMaxContrRnwCnt
     * @param param
     * @param contrRnwTotCnt
     * @param maxContrRnwCnt
     * @return
     */
    private boolean checkMaxContrRnwCnt(ContrRenewalPreCheckInfo param, BigDecimal contrRnwTotCnt, BigDecimal maxContrRnwCnt) {
        if (hasValue(maxContrRnwCnt)) {
            if (!hasValue(contrRnwTotCnt)) {
                contrRnwTotCnt = BigDecimal.ZERO;
            }
            if (contrRnwTotCnt.compareTo(maxContrRnwCnt) >= 0) {
                param.setContrRnwErrRsnCd(CONTR_RNW_ERR_RSN.MAX_CONTRACT_RENEWAL_COUNT_ERROR);
                param.setXxMsgId(NSZM0844E);
                param.setXxMsgTxt(S21MessageFunc.clspGetMessage(NSZM0844E));
                return false;
            }
        }
        return true;
    }

    /**
     * checkMaxPrcUpRatioBase
     * @param param
     * @param maxPrcUpRatio
     * @param effBasePrcDealAmt
     * @return
     */
    private boolean checkMaxPrcUpRatioBase(ContrRenewalPreCheckInfo param, BigDecimal maxPrcUpRatio, BigDecimal effBasePrcDealAmt) {
        BigDecimal basePrcDealAmt = param.getBasePrcDealAmt();
        if (hasValue(basePrcDealAmt) && hasValue(maxPrcUpRatio) && hasValue(effBasePrcDealAmt)) {
            // START 2017/10/11 M.Kidokoro [QC#21546,MOD]
//            if (basePrcDealAmt.compareTo(maxPrcUpRatio.multiply(effBasePrcDealAmt)) > 0) {
            if (basePrcDealAmt.compareTo((maxPrcUpRatio.divide(new BigDecimal(100)).add(new BigDecimal(1))).multiply(effBasePrcDealAmt)) > 0) {
            // END 2017/10/11 M.Kidokoro [QC#21546,MOD]
                param.setContrRnwErrRsnCd(CONTR_RNW_ERR_RSN.MAX_PRICE_UP_RATIO_ERROR);
                param.setXxMsgId(NSZM0845E);
                param.setXxMsgTxt(S21MessageFunc.clspGetMessage(NSZM0845E));
                return false;
            }
        }
        return true;
    }

    /**
     * checkMaxPrcUpRatioUsage
     * @param param
     * @param maxPrcUpRatio
     * @param effXsMtrAmtRate
     * @return
     */
    private boolean checkMaxPrcUpRatioUsage(ContrRenewalPreCheckInfo param, BigDecimal maxPrcUpRatio, BigDecimal effXsMtrAmtRate) {
        BigDecimal xsMtrAmtRate = param.getXsMtrAmtRate();
        if (hasValue(xsMtrAmtRate) && hasValue(maxPrcUpRatio) && hasValue(effXsMtrAmtRate)) {
            // START 2017/10/11 M.Kidokoro [QC#21546,MOD]
//            if (effXsMtrAmtRate.compareTo(maxPrcUpRatio.multiply(xsMtrAmtRate)) > 0) {
            if (xsMtrAmtRate.compareTo((maxPrcUpRatio.divide(new BigDecimal(100)).add(new BigDecimal(1))).multiply(effXsMtrAmtRate)) > 0) {
            // END 2017/10/11 M.Kidokoro [QC#21546,MOD]
                param.setContrRnwErrRsnCd(CONTR_RNW_ERR_RSN.MAX_PRICE_UP_RATIO_ERROR);
                param.setXxMsgId(NSZM0846E);
                param.setXxMsgTxt(S21MessageFunc.clspGetMessage(NSZM0846E));
                return false;
            }
        }
        return true;
    }

}

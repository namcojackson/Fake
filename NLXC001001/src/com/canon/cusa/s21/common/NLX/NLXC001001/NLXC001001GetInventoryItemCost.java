/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   CITS            T.Tokutomi      Create          CSA
 * 2017/07/19   CITS            T.Tokutomi      Update          QC#19909
 * 04/09/2018   CITS            T.Kikuhara      Update          QC#24994
 *</pre>
 */
public class NLXC001001GetInventoryItemCost {

    /**
     * NLXM1023E: An input parameter, [Global Company Code], has not
     * been set.
     */
    private static final String NLXM1023E = "NLXM1023E";

    /**
     * NLXM1024E: An input parameter, [Merchandise Code], has not been
     * set.
     */
    private static final String NLXM1024E = "NLXM1024E";

    /** NLXM1027E: Data does not exist in Merchandise Master. */
    private static final String NLXM1027E = "NLXM1027E";

    /**
     * NLXM1032E: An input parameter, [Inventory Location Code], has
     * not been set.
     */
    private static final String NLXM1032E = "NLXM1032E";

    /**
     * NLXM1034E: It failed to get Mdse Cost Type and Inventory Cost
     * Percent linked to the specified Inventory Location Code.
     */
    private static final String NLXM1034E = "NLXM1034E";

    /**
     * NLXM1035E: It failed to get Merchandise Cost linked to the
     * specified Merchandise Code.
     */
    private static final String NLXM1035E = "NLXM1035E";

    /**
     * NLXM1036E: Input parameter has not been set.
     */
    private static final String NLXM1036E = "NLXM1036E";

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NLXC001001GetInventoryItemCost.class);

    /**
     * Parameter Name: GLBL_CMPY_CD
     */
    private static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * Parameter Name: INVTY_LOC_CD
     */
    private static final String PARAM_INVTY_LOC_CD = "invtyLocCd";

    /**
     * Parameter Name: MDSE_CD
     */
    private static final String PARAM_MDSE_CD = "mdseCd";

    /**
     * Column Name: MDSE_COST_TP_CD
     */
    private static final String MDSE_COST_TP_CD = "MDSE_COST_TP_CD";

    /**
     * Column Name: MDSE_INVTY_COST_PCT
     */
    private static final String MDSE_INVTY_COST_PCT = "MDSE_INVTY_COST_PCT";

    /**
     * Column Name: INVTY_OWNR_CD
     */
    private static final String CMPY_INVTY_FLG = "CMPY_INVTY_FLG";

    /**
     * Column Name: INVTY_ACCT_CD
     */
    private static final String INVTY_ACCT_CD = "INVTY_ACCT_CD";

    /**
     * Column Name: THIS_MTH_TOT_STD_COST_AMT
     */
    private static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /**
     * Column Name: ASSET_RECOV_COST_AMT
     */
    private static final String ASSET_RECOV_COST_AMT = "ASSET_RECOV_COST_AMT";

    /**
     * Var Char Name: COST_ROUND_OPTION
     */
    private static final String COST_ROUND_OPTION = "COST_ROUND_OPTION";

    /**
     * Column Name: COST_PRECISION
     */
    private static final String COST_PRECISION = "COST_PRECISION";

    /**
     * Column Name: DISPLAY_COST_PRECISION
     */
    private static final String DISPLAY_COST_PRECISION = "DISPLAY_COST_PRECISION";

    /**
     * (to divide by 100)
     */
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    /**
     * Cost Round Option; Round up
     */
    private static final String ROUND_UP = "1";

    /**
     * Cost Round Option; Round
     */
    private static final String ROUND = "2";

    /**
     * Calculates inventory item cost and set it to given bean.
     * @param bean I/O
     * @return bean NLXC001001GetInventoryItemCostBean
     */
    public static NLXC001001GetInventoryItemCostBean getInventoryItemCost(NLXC001001GetInventoryItemCostBean bean) {
        checkParam(bean);

        if (hasError(bean)) {
            return bean;
        }

        execute(bean);

        return bean;
    }

    /**
     * Input Parameter check
     * @param bean
     */
    private static void checkParam(NLXC001001GetInventoryItemCostBean bean) {
        if (bean == null) {
            bean = new NLXC001001GetInventoryItemCostBean();
            addError(bean, NLXM1036E);
        }

        if (!hasValue(bean.getGlblCmpyCd())) {
            addError(bean, NLXM1023E);
        }
        if (!hasValue(bean.getMdseCd())) {
            addError(bean, NLXM1024E);
        }
        if (!hasValue(bean.getInvtyLocCd())) {
            addError(bean, NLXM1032E);
        }
        if (!hasValue(bean.getQty())) {
            bean.setQty(BigDecimal.ONE);
        }
    }

    /**
     * main program
     * @param bean NLXC001001GetInventoryItemCostBean
     */
    private static void execute(NLXC001001GetInventoryItemCostBean bean) {
        Map<String, Object> mdseInventoryCostInfo = getMdseInventoryCostPercent(bean);

        if (hasError(bean)) {
            return;
        }

        // check Inventory owner
        // if this is not inventory owner, amount zero.
        if (ZYPConstant.FLG_OFF_N.equals((String) mdseInventoryCostInfo.get(CMPY_INVTY_FLG))) {
            bean.setUnitPrcAmt(BigDecimal.ZERO);
            bean.setTotPrcAmt(BigDecimal.ZERO);
            bean.setDspUnitPrcAmt(BigDecimal.ZERO);
            bean.setDspTotPrcAmt(BigDecimal.ZERO);
            return;
        }

        // check Inventory Account
        // if inventory account is asset, amount zero.
        if (INVTY_ACCT.ASSET.equals((String) mdseInventoryCostInfo.get(INVTY_ACCT_CD))) {
            bean.setUnitPrcAmt(BigDecimal.ZERO);
            bean.setTotPrcAmt(BigDecimal.ZERO);
            bean.setDspUnitPrcAmt(BigDecimal.ZERO);
            bean.setDspTotPrcAmt(BigDecimal.ZERO);
            return;
        }

        Map<String, Object> mdseCost = getMdseCost(bean);

        if (hasError(bean)) {
            return;
        }

        // Calculate Cost
        BigDecimal unitAmt = BigDecimal.ZERO;
        if (MDSE_COST_TP.STANDARD_COST.equals((String) mdseInventoryCostInfo.get(MDSE_COST_TP_CD))) {
            unitAmt = ((BigDecimal) mdseCost.get(THIS_MTH_TOT_STD_COST_AMT)).multiply( //
                    ((BigDecimal) mdseInventoryCostInfo.get(MDSE_INVTY_COST_PCT)).divide(ONE_HUNDRED));
        } else {
            unitAmt = ((BigDecimal) mdseCost.get(ASSET_RECOV_COST_AMT)).multiply( //
                    ((BigDecimal) mdseInventoryCostInfo.get(MDSE_INVTY_COST_PCT)).divide(ONE_HUNDRED));
        }

        // set output parameter and set scale
        String costRound = ZYPCodeDataUtil.getVarCharConstValue(COST_ROUND_OPTION, bean.getGlblCmpyCd());
        int costPrecision = Integer.valueOf(ZYPCodeDataUtil.getVarCharConstValue(COST_PRECISION, bean.getGlblCmpyCd()));
        int displayCostPrecision = Integer.valueOf(ZYPCodeDataUtil.getVarCharConstValue(DISPLAY_COST_PRECISION, bean.getGlblCmpyCd()));

        // QC#19909 07/19/2017
        if (ROUND_UP.equals(costRound)) {
            BigDecimal unitPrcAmt = unitAmt.setScale(costPrecision, BigDecimal.ROUND_UP);
            BigDecimal dspUnitPrcAmt = unitAmt.setScale(displayCostPrecision, BigDecimal.ROUND_UP);

            bean.setUnitPrcAmt(unitPrcAmt);
            bean.setTotPrcAmt((unitPrcAmt.multiply(bean.getQty())).setScale(costPrecision, BigDecimal.ROUND_UP));
            bean.setDspUnitPrcAmt(dspUnitPrcAmt);
            bean.setDspTotPrcAmt((dspUnitPrcAmt.multiply(bean.getQty())).setScale(displayCostPrecision, BigDecimal.ROUND_UP));
        } else if (ROUND.equals(costRound)) {
            BigDecimal unitPrcAmt = unitAmt.setScale(costPrecision, BigDecimal.ROUND_HALF_UP);
            BigDecimal dspUnitPrcAmt = unitAmt.setScale(displayCostPrecision, BigDecimal.ROUND_HALF_UP);

            bean.setUnitPrcAmt(unitPrcAmt);
            bean.setTotPrcAmt((unitPrcAmt.multiply(bean.getQty())).setScale(costPrecision, BigDecimal.ROUND_HALF_UP));
            bean.setDspUnitPrcAmt(dspUnitPrcAmt);
            bean.setDspTotPrcAmt((dspUnitPrcAmt.multiply(bean.getQty())).setScale(displayCostPrecision, BigDecimal.ROUND_HALF_UP));
        } else {
            // Default Round down
            BigDecimal unitPrcAmt = unitAmt.setScale(costPrecision, BigDecimal.ROUND_DOWN);
            BigDecimal dspUnitPrcAmt = unitAmt.setScale(displayCostPrecision, BigDecimal.ROUND_DOWN);

            bean.setUnitPrcAmt(unitPrcAmt);
            bean.setTotPrcAmt((unitPrcAmt.multiply(bean.getQty())).setScale(costPrecision, BigDecimal.ROUND_DOWN));
            bean.setDspUnitPrcAmt(dspUnitPrcAmt);
            bean.setDspTotPrcAmt((dspUnitPrcAmt.multiply(bean.getQty())).setScale(displayCostPrecision, BigDecimal.ROUND_DOWN));
        }

        // QC#24994 ADD START
        bean.setMdseCostTpCd((String) mdseInventoryCostInfo.get(MDSE_COST_TP_CD));
        bean.setMdseInvtyCostPct((BigDecimal) mdseInventoryCostInfo.get(MDSE_INVTY_COST_PCT));
        // QC#24994 ADD END

    }

    /**
     * Get Merchandise Inventory Cost Percent
     * @param bean NLXC001001GetInventoryItemCostBean
     * @return result Map<String, Object>
     */
    private static Map<String, Object> getMdseInventoryCostPercent(NLXC001001GetInventoryItemCostBean bean) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, bean.getGlblCmpyCd());
        param.put(PARAM_INVTY_LOC_CD, bean.getInvtyLocCd());

        Map<String, Object> result = (Map) SSM_CLIENT.queryObject("getMdseInventoryCostPercent", param);

        if (result == null) {
            addError(bean, NLXM1034E);
            return null;
        } else {
            if (result.get(MDSE_COST_TP_CD) == null) {
                result.put(MDSE_COST_TP_CD, MDSE_COST_TP.STANDARD_COST);
            }
            if (result.get(MDSE_INVTY_COST_PCT) == null) {
                result.put(MDSE_INVTY_COST_PCT, ONE_HUNDRED);
            }
        }

        return result;
    }

    /**
     * Get Merchandise cost (THIS_MTH_TOT_STD_COST_AMT,
     * ASSET_RECOV_COST_AMT)
     * @param bean NLXC001001GetInventoryItemCostBean
     * @return result Map<String, Object>
     */
    private static Map<String, Object> getMdseCost(NLXC001001GetInventoryItemCostBean bean) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, bean.getGlblCmpyCd());
        param.put(PARAM_MDSE_CD, bean.getMdseCd());

        Map<String, Object> result = (Map) SSM_CLIENT.queryObject("getMdseCost", param);

        if (result == null) {
            addError(bean, NLXM1035E);
            return null;
        }

        return result;
    }

    private static boolean hasError(NLXC001001GetInventoryItemCostBean bean) {
        return !bean.getErrList().isEmpty();
    }

    private static void addError(NLXC001001GetInventoryItemCostBean bean, String xxMsgId) {
        bean.getErrList().add(xxMsgId);
    }

}

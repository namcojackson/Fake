/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_IMPT_SVC_CONFIG_REFTMsg;
import business.db.NWAI4150_16TMsg;
import business.db.NWAI4150_18TMsg;
import business.db.NWAI4150_19TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptSvcConfigRefBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class EopsDsImptSvcConfigRefBean extends DS_IMPT_SVC_CONFIG_REFTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.SHELL_TYPE shellType;

    /**  */
    public EopsDsImptSvcDtlBean dsImptSvcDtlBean;

    /**  */
    public NWAI4150_16TMsg nwai4150_16;

    /**  */
    public NWAI4150_18TMsg nwai4150_18;

    /**  */
    public NWAI4150_19TMsg nwai4150_19;

    /**  */
    public EopsDsImptOrdConfigBean eopsDsImptOrdConfigBean;

    /**  */
    public EopsDsImptSvcPrcBean eopsDsImptSvcPrcBean;

    /**
     * EopsDsImptSvcConfigRefBean
     * @param shellType                 NWAB415001Constant.SHELL_TYPE
     * @param dsImptSvcDtlBean          EopsDsImptSvcDtlBean
     * @param nwai415016                NWAI4150_16TMsg
     * @param nwai415018                NWAI4150_18TMsg
     * @param eopsDsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param eopsDsImptSvcPrcBean      EopsDsImptSvcPrcBean
     */
    public EopsDsImptSvcConfigRefBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4150_16TMsg nwai415016, NWAI4150_18TMsg nwai415018, EopsDsImptOrdConfigBean eopsDsImptOrdConfigBean, EopsDsImptSvcPrcBean eopsDsImptSvcPrcBean) {
        this(shellType, dsImptSvcDtlBean, nwai415016, eopsDsImptOrdConfigBean, eopsDsImptSvcPrcBean);

        this.nwai4150_18 = nwai415018;
    }

    /**
     * EopsDsImptSvcConfigRefBean
     * @param shellType                 NWAB415001Constant.SHELL_TYPE
     * @param dsImptSvcDtlBean          EopsDsImptSvcDtlBean
     * @param nwai415016                NWAI4150_16TMsg
     * @param nwai415019                NWAI4150_19TMsg
     * @param eopsDsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param eopsDsImptSvcPrcBean      EopsDsImptSvcPrcBean
     */
    public EopsDsImptSvcConfigRefBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4150_16TMsg nwai415016, NWAI4150_19TMsg nwai415019, EopsDsImptOrdConfigBean eopsDsImptOrdConfigBean, EopsDsImptSvcPrcBean eopsDsImptSvcPrcBean) {
        this(shellType, dsImptSvcDtlBean, nwai415016, eopsDsImptOrdConfigBean, eopsDsImptSvcPrcBean);

        this.nwai4150_19 = nwai415019;
    }

    private EopsDsImptSvcConfigRefBean(NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4150_16TMsg nwai415016, EopsDsImptOrdConfigBean eopsDsImptOrdConfigBean, EopsDsImptSvcPrcBean eopsDsImptSvcPrcBean) {
        super();

        this.shellType = shellType;
        this.nwai4150_16 = nwai415016;
        this.dsImptSvcDtlBean = dsImptSvcDtlBean;
        this.eopsDsImptOrdConfigBean = eopsDsImptOrdConfigBean;
        this.eopsDsImptSvcPrcBean = eopsDsImptSvcPrcBean;
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcConfigRefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_CONFIG_REF_SQ));
        //Fleet
        if (NWAB415001Constant.CONTRACT_TYPE.FLEET.equals(this.dsImptSvcDtlBean.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.eopsDsImptSvcPrcBean.dsImptSvcPrcPk);
            //Aggregate
        } else if (NWAB415001Constant.CONTRACT_TYPE.AGGREGATE.equals(this.dsImptSvcDtlBean.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.eopsDsImptSvcPrcBean.dsImptSvcPrcPk);
            ZYPEZDItemValueSetter.setValue(this.eopsDsImptSvcPrcBean.dsImptSvcConfigRefPk, this.dsImptSvcConfigRefPk);
            //Regular
        } else if (NWAB415001Constant.CONTRACT_TYPE.REGULAR.equals(this.dsImptSvcDtlBean.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.eopsDsImptSvcPrcBean.dsImptSvcPrcPk);
            ZYPEZDItemValueSetter.setValue(this.eopsDsImptSvcPrcBean.dsImptSvcConfigRefPk, this.dsImptSvcConfigRefPk);
        }
        dsImptSvcDtlBean.dsImptSvcConfigRefBeanList.add(this);

    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcDtlBean.dsImptSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, getDsImptOrdDtlPk());

        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptSvcDtlBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, getSvcConfigMstrPk());

        ZYPEZDItemValueSetter.setValue(// FIXME
                this.custIssPoDt, this.dsImptSvcDtlBean.dsImptOrdBean.nwai415001.custIssPoDt);
        if (ZYPCommonFunc.hasValue(this.dsImptSvcDtlBean.dsImptOrdBean.nwai415001.somMaintPoNum)) {
            //XXX somMaintPoDt not exist in nwai4150_16
            //            ZYPEZDItemValueSetter.setValue(this.custIssPoDt, this.nwai4150_16.somMaintPoDt);
            ZYPEZDItemValueSetter.setValue(//
                    this.custIssPoNum, NWXC412001.cutString(//
                            this.dsImptSvcDtlBean.dsImptOrdBean.nwai415001.somMaintPoNum.getValue(), CUTOFF_LEN.SOM_MAINT_PO_NUM.getLen()));
        } else {
            this.custIssPoNum.clear();
        }

        // meter method
        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(shellType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.mtrReadMethCd, NWXC412001.getMtrReadMethCd(glblCmpyCd, CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), this.nwai4150_18.mtrReadMethTxt.getValue()));
        } else {

            this.mtrReadMethCd.clear();
        }

        // amount
        boolean isOriginalAmount = isOriginalAmount(glblCmpyCd);

        String trnsfOvrdIndSomTxt = this.nwai4150_16.trnsfOvrdIndSomTxt.getValue();
        if (!ZYPCommonFunc.hasValue(trnsfOvrdIndSomTxt)) {
            trnsfOvrdIndSomTxt = ZYPConstant.FLG_ON_Y;
        }
        Map<Object, BigDecimal> srtCtAmtMap = getSrtCtAmt(glblCmpyCd);
        if (isOriginalAmount && trnsfOvrdIndSomTxt.equals(ZYPConstant.FLG_OFF_N)) {

            BigDecimal origSvcRevTrnsfAmt //
            = srtCtAmtMap.get("ORIG_SVC_REV_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("ORIG_SVC_REV_TRNSF_AMT") : BigDecimal.ZERO;
            ZYPEZDItemValueSetter.setValue(this.dealSvcRevTrnsfAmt, origSvcRevTrnsfAmt);
            ZYPEZDItemValueSetter.setValue(this.funcSvcRevTrnsfAmt, origSvcRevTrnsfAmt);
            BigDecimal origCostTrnsfAmt //
            = srtCtAmtMap.get("ORIG_COST_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("ORIG_COST_TRNSF_AMT") : BigDecimal.ZERO;
            ZYPEZDItemValueSetter.setValue(this.dealSvcCostTrnsfAmt, origCostTrnsfAmt);
            ZYPEZDItemValueSetter.setValue(this.funcSvcCostTrnsfAmt, origCostTrnsfAmt);
        } else {

            BigDecimal totSvcRevTrnsfAmt //
            = srtCtAmtMap.get("TOT_SVC_REV_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("TOT_SVC_REV_TRNSF_AMT") : BigDecimal.ZERO;
            ZYPEZDItemValueSetter.setValue(this.dealSvcRevTrnsfAmt, totSvcRevTrnsfAmt);
            ZYPEZDItemValueSetter.setValue(this.funcSvcRevTrnsfAmt, totSvcRevTrnsfAmt);
            BigDecimal totCostTrnsfAmt //
            = srtCtAmtMap.get("TOT_COST_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("TOT_COST_TRNSF_AMT") : BigDecimal.ZERO;
            ZYPEZDItemValueSetter.setValue(this.dealSvcCostTrnsfAmt, totCostTrnsfAmt);
            ZYPEZDItemValueSetter.setValue(this.funcSvcCostTrnsfAmt, totCostTrnsfAmt);
        }

        return true;
    }

    private BigDecimal getDsImptOrdDtlPk() {

        BigDecimal somConfigId = null;
        String somSvcItemCd = null;
        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            somConfigId = this.nwai4150_18.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4150_18.somMdlId.getValue();
        } else {

            somConfigId = this.nwai4150_19.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4150_19.somItemCd.getValue();
        }

        if (S21StringUtil.isEmpty(somSvcItemCd)) {
            // invalid case
            return null;
        }

        for (EopsDsImptOrdDtlBean detail : this.eopsDsImptOrdConfigBean.dsImptOrdDtlBeanList) {

            if (detail.nwai415010.somConfigId.getValue().compareTo(somConfigId) != 0) {
                continue;
            }

            if (S21StringUtil.isEquals(detail.mdseCd.getValue(), somSvcItemCd)) {
                return detail.dsImptOrdDtlPk.getValue();
            }
        }

        // invalid case
        return null;
    }

//    private BigDecimal getConfigQuantity() {
//        BigDecimal somConfigId = null;
//        String somSvcItemCd = null;
//        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
//
//            somConfigId = this.nwai4150_18.somSrvLineId.getValue();
//            somSvcItemCd = this.nwai4150_18.somMdlId.getValue();
//        } else {
//
//            somConfigId = this.nwai4150_19.somSrvLineId.getValue();
//            somSvcItemCd = this.nwai4150_19.somItemCd.getValue();
//        }
//
//        if (S21StringUtil.isEmpty(somSvcItemCd)) {
//            // invalid case
//            return BigDecimal.ONE;
//        }
//
//        for (EopsDsImptOrdDtlBean detail : this.eopsDsImptOrdConfigBean.dsImptOrdDtlBeanList) {
//
//            if (detail.nwai4150_10.somConfigId.getValue().compareTo(somConfigId) != 0) {
//                continue;
//            }
//            return detail.nwai4150_10.somConfigQty.getValue();
//        }
//        // invalid case
//        return BigDecimal.ONE;
//    }

    private BigDecimal getSvcConfigMstrPk() {

        BigDecimal somConfigId = null;
        String somSvcItemCd = null;
        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            somConfigId = this.nwai4150_18.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4150_18.somSvcItemCd.getValue();
        } else {

            somConfigId = this.nwai4150_19.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4150_19.somItemCd.getValue();
        }

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return null;
        }

        for (EopsDsImptOrdConfigBean config : this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdConfigBeanList) {

            if (config.nwai415012.somConfigId.getValue().compareTo(somConfigId) == 0) {
                return config.svcConfigMstrPk.getValue();
            }
        }
        // invalid case
        return null;
    }

    private boolean isOriginalAmount(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.nwai4150_16.interfaceId.getValue());
        ssmParam.put("transactionId", this.nwai4150_16.transactionId.getValue());
        ssmParam.put("somQuoteId", this.dsImptSvcDtlBean.dsImptOrdBean.nwai415001.somQuoteId.getValue());

        Map<Object, BigDecimal> getTrnsfAmtCnt //
        = NWXC412001.autoCast(this.dsImptSvcDtlBean.dsImptOrdBean.ssmBatchClient.queryObject("getTrnsfAmtCnt", ssmParam));

        int lPositiveExistsCnt = Integer.valueOf(getTrnsfAmtCnt.get("L_POSITIVE_EXISTS_CNT").toPlainString());
        int lSrtExistsCnt = Integer.valueOf(getTrnsfAmtCnt.get("L_SRT_EXISTS_CNT").toPlainString());
        boolean trnsfOvrdInd = (S21StringUtil.isEquals(this.nwai4150_16.trnsfOvrdIndSomTxt.getValue(), ZYPConstant.FLG_OFF_N));

        return trnsfOvrdInd & lPositiveExistsCnt > 0 & lSrtExistsCnt > 0;
    }

    //QC#20097
    private Map<Object, BigDecimal> getSrtCtAmt(String glblCmpyCd) {
        String sqlId;

        BigDecimal somSrvLineId = BigDecimal.ZERO;
        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
            sqlId = "getSrtCtAmtFrom18";
            somSrvLineId = this.nwai4150_18.somSrvLineId.getValue();
        } else {
            sqlId = "getSrtCtAmtFrom19";
            somSrvLineId = this.nwai4150_19.somSrvLineId.getValue();
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.nwai4150_16.interfaceId.getValue());
        ssmParam.put("transactionId", this.nwai4150_16.transactionId.getValue());
        ssmParam.put("somQuoteId", this.dsImptSvcDtlBean.dsImptOrdBean.nwai415001.somQuoteId.getValue());
        ssmParam.put("somSrvLineId", somSrvLineId);

        Map<Object, BigDecimal> map //
        = NWXC412001.autoCast(this.dsImptSvcDtlBean.dsImptOrdBean.ssmBatchClient.queryObject(sqlId, ssmParam));
        return map;
    }

}

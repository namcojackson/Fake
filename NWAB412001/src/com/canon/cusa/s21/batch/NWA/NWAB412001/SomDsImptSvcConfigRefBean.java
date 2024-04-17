/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_IMPT_SVC_CONFIG_REFTMsg;
import business.db.NWAI4120_16TMsg;
import business.db.NWAI4120_18TMsg;
import business.db.NWAI4120_19TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcConfigRefBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 01/23/2017   Fujitsu         M.Ohno          Update          QC#17232
 * 02/02/2017   Fujitsu         M.Ohno          Update          QC#17232-2
 * 07/21/2017   SRAA            K.Aratani       Update          QC#19993
 * 08/08/2017   SRAA            K.Aratani       Update          QC#20097
 * 08/18/2017   SRAA            K.Aratani       Update          QC#20097
 *</pre>
 */
public class SomDsImptSvcConfigRefBean extends DS_IMPT_SVC_CONFIG_REFTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SHELL_TYPE shellType;

    public SomDsImptSvcDtlBean dsImptSvcDtlBean;

    // public SomDsImptSvcPrcBean dsImptSvcPrcBean;

    public final NWAI4120_16TMsg nwai4120_16;

    public NWAI4120_18TMsg nwai4120_18;

    public NWAI4120_19TMsg nwai4120_19;
    
    public SomDsImptOrdConfigBean somDsImptOrdConfigBean;
    
    public SomDsImptSvcPrcBean somDsImptSvcPrcBean;

    public SomDsImptSvcConfigRefBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4120_16TMsg nwai4120_16, NWAI4120_18TMsg nwai4120_18, SomDsImptOrdConfigBean somDsImptOrdConfigBean, SomDsImptSvcPrcBean somDsImptSvcPrcBean) {
        this(shellType, dsImptSvcDtlBean, nwai4120_16, somDsImptOrdConfigBean, somDsImptSvcPrcBean);

        this.nwai4120_18 = nwai4120_18;
    }

    public SomDsImptSvcConfigRefBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4120_16TMsg nwai4120_16, NWAI4120_19TMsg nwai4120_19, SomDsImptOrdConfigBean somDsImptOrdConfigBean, SomDsImptSvcPrcBean somDsImptSvcPrcBean) {
        this(shellType, dsImptSvcDtlBean, nwai4120_16, somDsImptOrdConfigBean, somDsImptSvcPrcBean);

        this.nwai4120_19 = nwai4120_19;
    }

    private SomDsImptSvcConfigRefBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4120_16TMsg nwai4120_16, SomDsImptOrdConfigBean somDsImptOrdConfigBean, SomDsImptSvcPrcBean somDsImptSvcPrcBean) {
        super();

        this.shellType = shellType;
        this.nwai4120_16 = nwai4120_16;
        this.dsImptSvcDtlBean = dsImptSvcDtlBean;
        this.somDsImptOrdConfigBean = somDsImptOrdConfigBean;
        this.somDsImptSvcPrcBean = somDsImptSvcPrcBean;
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcConfigRefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_CONFIG_REF_SQ));
        //Fleet
        if (NWAB412001Constant.CONTRACT_TYPE.FLEET.equals(this.dsImptSvcDtlBean.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.somDsImptSvcPrcBean.dsImptSvcPrcPk);
        //Aggregate
        } else if (NWAB412001Constant.CONTRACT_TYPE.AGGREGATE.equals(this.dsImptSvcDtlBean.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.somDsImptSvcPrcBean.dsImptSvcPrcPk);
            ZYPEZDItemValueSetter.setValue(this.somDsImptSvcPrcBean.dsImptSvcConfigRefPk, this.dsImptSvcConfigRefPk);
        //Regular
        } else if (NWAB412001Constant.CONTRACT_TYPE.REGULAR.equals(this.dsImptSvcDtlBean.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.somDsImptSvcPrcBean.dsImptSvcPrcPk);
            ZYPEZDItemValueSetter.setValue(this.somDsImptSvcPrcBean.dsImptSvcConfigRefPk, this.dsImptSvcConfigRefPk);
        }
        dsImptSvcDtlBean.dsImptSvcConfigRefBeanList.add(this);
        
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(this.dsImptSvcConfigRefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_CONFIG_REF_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcDtlBean.dsImptSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, getDsImptOrdDtlPk());

        if (this.dsImptSvcDtlBean.dsImptSvcPrcBeanList != null && !this.dsImptSvcDtlBean.dsImptSvcPrcBeanList.isEmpty()) {

            // 2017/01/23 QC#17232 Mod Start
            //if (ZYPCommonFunc.hasValue(this.dsImptSvcDtlBean.dsImptSvcPrcBeanList.get(0).dsImptSvcConfigRefPk) //
            //        && !S21StringUtil.isEquals(DS_CONTR_CATG.FLEET, this.dsImptSvcDtlBean.dsContrCatgCd.getValue())) { // 2017/02/02 QC#17232-2 Mod 
//                SomDsImptSvcPrcBean copyBean = (SomDsImptSvcPrcBean) this.dsImptSvcDtlBean.dsImptSvcPrcBeanList.get(0).clone();
//                ZYPEZDItemValueSetter.setValue(copyBean.dsImptSvcConfigRefPk, this.dsImptSvcConfigRefPk);
//                ZYPEZDItemValueSetter.setValue(copyBean.dsImptSvcPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_PRC_SQ));
//                this.dsImptSvcDtlBean.dsImptSvcPrcBeanList.add(copyBean);
//                ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, copyBean.dsImptSvcPrcPk);
            //} else {
            //    ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlBean.dsImptSvcPrcBeanList.get(0).dsImptSvcConfigRefPk, this.dsImptSvcConfigRefPk);
            //    ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.dsImptSvcDtlBean.dsImptSvcPrcBeanList.get(0).dsImptSvcPrcPk);
            //}
            // 2017/01/23 QCS#17232 Mod End
        }
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptSvcDtlBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, getSvcConfigMstrPk());

        if (ZYPCommonFunc.hasValue(this.dsImptSvcDtlBean.dsImptOrdBean.nwai4120_01.somMaintPoNum)) {
            ZYPEZDItemValueSetter.setValue(this.custIssPoDt, this.nwai4120_16.somMaintPoDt);
            //ZYPEZDItemValueSetter.setValue(this.custIssPoNum, this.dsImptSvcDtlBean.dsImptOrdBean.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(this.custIssPoNum, NWXC412001.cutString(this.dsImptSvcDtlBean.dsImptOrdBean.nwai4120_01.somMaintPoNum.getValue(), 35));
        } else {
            this.custIssPoDt.clear();
            this.custIssPoNum.clear();
        }

        // meter method
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(shellType)) {

            ZYPEZDItemValueSetter.setValue(this.mtrReadMethCd, NWXC412001.getMtrReadMethCd(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), this.nwai4120_18.mtrReadMethTxt.getValue()));
        } else {

            this.mtrReadMethCd.clear();
        }
        //ZYPEZDItemValueSetter.setValue(this.custIssPoDt, this.dsImptSvcDtlBean.dsImptOrdBean.custIssPoDt);

        // amount
        boolean isOriginalAmount = isOriginalAmount(glblCmpyCd);
        //QC#20097
        String trnsfOvrdIndSomTxt = this.nwai4120_16.trnsfOvrdIndSomTxt.getValue();
        if (!ZYPCommonFunc.hasValue(trnsfOvrdIndSomTxt)) {
            trnsfOvrdIndSomTxt = ZYPConstant.FLG_ON_Y;
        }
        Map<Object, BigDecimal> srtCtAmtMap = getSrtCtAmt(glblCmpyCd); //QC#20097
        BigDecimal configQuantity = getConfigQuantity();
//        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(shellType)) {
            //QC#20097
            if (isOriginalAmount && trnsfOvrdIndSomTxt.equals(ZYPConstant.FLG_OFF_N)) {

                //BigDecimal origSvcRevTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_18.origSvcRevTrnsfAmt) ? this.nwai4120_18.origSvcRevTrnsfAmt.getValue() : BigDecimal.ZERO;
                BigDecimal origSvcRevTrnsfAmt = srtCtAmtMap.get("ORIG_SVC_REV_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("ORIG_SVC_REV_TRNSF_AMT") : BigDecimal.ZERO;
                origSvcRevTrnsfAmt = origSvcRevTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
                ZYPEZDItemValueSetter.setValue(this.dealSvcRevTrnsfAmt, origSvcRevTrnsfAmt.negate());
                ZYPEZDItemValueSetter.setValue(this.funcSvcRevTrnsfAmt, origSvcRevTrnsfAmt.negate());
                //BigDecimal origCostTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_18.origCostTrnsfAmt) ? this.nwai4120_18.origCostTrnsfAmt.getValue() : BigDecimal.ZERO;
                BigDecimal origCostTrnsfAmt = srtCtAmtMap.get("ORIG_COST_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("ORIG_COST_TRNSF_AMT") : BigDecimal.ZERO;
                origCostTrnsfAmt = origCostTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
                ZYPEZDItemValueSetter.setValue(this.dealSvcCostTrnsfAmt, origCostTrnsfAmt);
                ZYPEZDItemValueSetter.setValue(this.funcSvcCostTrnsfAmt, origCostTrnsfAmt);
            } else {

                //BigDecimal totSvcRevTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_18.totSvcRevTrnsfAmt) ? this.nwai4120_18.totSvcRevTrnsfAmt.getValue() : BigDecimal.ZERO;
                BigDecimal totSvcRevTrnsfAmt = srtCtAmtMap.get("TOT_SVC_REV_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("TOT_SVC_REV_TRNSF_AMT") : BigDecimal.ZERO;
                totSvcRevTrnsfAmt = totSvcRevTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
                ZYPEZDItemValueSetter.setValue(this.dealSvcRevTrnsfAmt, totSvcRevTrnsfAmt.negate());
                ZYPEZDItemValueSetter.setValue(this.funcSvcRevTrnsfAmt, totSvcRevTrnsfAmt.negate());
                //BigDecimal totCostTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_18.totCostTrnsfAmt) ? this.nwai4120_18.totCostTrnsfAmt.getValue() : BigDecimal.ZERO;
                BigDecimal totCostTrnsfAmt = srtCtAmtMap.get("TOT_COST_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("TOT_COST_TRNSF_AMT") : BigDecimal.ZERO;
                totCostTrnsfAmt = totCostTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
                ZYPEZDItemValueSetter.setValue(this.dealSvcCostTrnsfAmt, totCostTrnsfAmt);
                ZYPEZDItemValueSetter.setValue(this.funcSvcCostTrnsfAmt, totCostTrnsfAmt);
            }
//        } else {
//
//            if (isOriginalAmount) {
//
//                //BigDecimal origSvcRevTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_19.origSvcRevTrnsfAmt) ? this.nwai4120_19.origSvcRevTrnsfAmt.getValue() : BigDecimal.ZERO;
//                BigDecimal origSvcRevTrnsfAmt = srtCtAmtMap.get("ORIG_SVC_REV_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("ORIG_SVC_REV_TRNSF_AMT") : BigDecimal.ZERO;
//                origSvcRevTrnsfAmt = origSvcRevTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
//                ZYPEZDItemValueSetter.setValue(this.dealSvcRevTrnsfAmt, origSvcRevTrnsfAmt);
//                //BigDecimal origCostTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_19.origCostTrnsfAmt) ? this.nwai4120_19.origCostTrnsfAmt.getValue() : BigDecimal.ZERO;
//                BigDecimal origCostTrnsfAmt = srtCtAmtMap.get("ORIG_COST_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("ORIG_COST_TRNSF_AMT") : BigDecimal.ZERO;
//                origCostTrnsfAmt = origCostTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
//                ZYPEZDItemValueSetter.setValue(this.dealSvcCostTrnsfAmt, origCostTrnsfAmt);
//            } else {
//
//                //BigDecimal totSvcRevTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_19.totSvcRevTrnsfAmt) ? this.nwai4120_19.totSvcRevTrnsfAmt.getValue() : BigDecimal.ZERO;
//                BigDecimal totSvcRevTrnsfAmt = srtCtAmtMap.get("TOT_SVC_REV_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("TOT_SVC_REV_TRNSF_AMT") : BigDecimal.ZERO;
//                totSvcRevTrnsfAmt = totSvcRevTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
//                ZYPEZDItemValueSetter.setValue(this.dealSvcRevTrnsfAmt, totSvcRevTrnsfAmt);
//                //BigDecimal totCostTrnsfAmt = ZYPCommonFunc.hasValue(this.nwai4120_19.totCostTrnsfAmt) ? this.nwai4120_19.totCostTrnsfAmt.getValue() : BigDecimal.ZERO;
//                BigDecimal totCostTrnsfAmt = srtCtAmtMap.get("TOT_COST_TRNSF_AMT") != null ? (BigDecimal) srtCtAmtMap.get("TOT_COST_TRNSF_AMT") : BigDecimal.ZERO;
//                totCostTrnsfAmt = totCostTrnsfAmt.divide(configQuantity, 2, BigDecimal.ROUND_DOWN);
//                ZYPEZDItemValueSetter.setValue(this.dealSvcCostTrnsfAmt, totCostTrnsfAmt);
//            }
//        }

//QC#QC#20097
//        this.funcSvcRevTrnsfAmt.clear();
//        this.funcSvcCostTrnsfAmt.clear();

        return true;
    }

    private BigDecimal getDsImptOrdDtlPk() {

        BigDecimal somConfigId = null;
        String somSvcItemCd = null;
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            somConfigId = this.nwai4120_18.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_18.somMdlId.getValue();
        } else {

            somConfigId = this.nwai4120_19.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_19.somItemCd.getValue();
        }

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return null;
        }
        
        for (SomDsImptOrdDtlBean detail : this.somDsImptOrdConfigBean.dsImptOrdDtlBeanList) {

            if (detail.nwai4120_10.somConfigId.getValue().compareTo(somConfigId) != 0) {

                continue;
            }

            if (S21StringUtil.isEquals(detail.mdseCd.getValue(), somSvcItemCd)) {

                return detail.dsImptOrdDtlPk.getValue();
            }
        }

        // invalid case
        return null;
    }

    private BigDecimal getConfigQuantity() {
        BigDecimal somConfigId = null;
        String somSvcItemCd = null;
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            somConfigId = this.nwai4120_18.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_18.somMdlId.getValue();
        } else {

            somConfigId = this.nwai4120_19.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_19.somItemCd.getValue();
        }

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return BigDecimal.ONE;
        }
        
        for (SomDsImptOrdDtlBean detail : this.somDsImptOrdConfigBean.dsImptOrdDtlBeanList) {

            if (detail.nwai4120_10.somConfigId.getValue().compareTo(somConfigId) != 0) {

                continue;
            } else {
                
                return detail.nwai4120_10.somConfigQty.getValue();
            }

        }

        // invalid case
        return BigDecimal.ONE;
    }
    
    private BigDecimal getSvcConfigMstrPk() {

        BigDecimal somConfigId = null;
        String somSvcItemCd = null;
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            somConfigId = this.nwai4120_18.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_18.somSvcItemCd.getValue();
        } else {

            somConfigId = this.nwai4120_19.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_19.somItemCd.getValue();
        }

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return null;
        }

        for (SomDsImptOrdConfigBean config : this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdConfigBeanList) {

            if (config.nwai4120_12.somConfigId.getValue().compareTo(somConfigId) == 0) {

                return config.svcConfigMstrPk.getValue();
            }
        }

        // invalid case
        return null;
    }

    private boolean isOriginalAmount(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.nwai4120_16.interfaceId.getValue());
        ssmParam.put("transactionId", this.nwai4120_16.transactionId.getValue());
        ssmParam.put("somQuoteId", this.dsImptSvcDtlBean.dsImptOrdBean.nwai4120_01.somQuoteId.getValue());
        @SuppressWarnings("unchecked")
        Map<Object, BigDecimal> getTrnsfAmtCnt = (Map<Object, BigDecimal>) this.dsImptSvcDtlBean.dsImptOrdBean.ssmBatchClient.queryObject("getTrnsfAmtCnt", ssmParam);

        int lPositiveExistsCnt = Integer.valueOf(getTrnsfAmtCnt.get("L_POSITIVE_EXISTS_CNT").toPlainString());
        int lSrtExistsCnt = Integer.valueOf(getTrnsfAmtCnt.get("L_SRT_EXISTS_CNT").toPlainString());
        boolean trnsfOvrdInd = !(S21StringUtil.isEquals(this.nwai4120_16.trnsfOvrdIndSomTxt.getValue(), ZYPConstant.FLG_OFF_N));

        return !trnsfOvrdInd & lPositiveExistsCnt > 0 & lSrtExistsCnt > 0;
    }
    //QC#20097
    private Map<Object, BigDecimal> getSrtCtAmt(String glblCmpyCd) {

        BigDecimal somSrvLineId = BigDecimal.ZERO;
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
            somSrvLineId = this.nwai4120_18.somSrvLineId.getValue();
        } else {
            somSrvLineId = this.nwai4120_19.somSrvLineId.getValue();
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.nwai4120_16.interfaceId.getValue());
        ssmParam.put("transactionId", this.nwai4120_16.transactionId.getValue());
        ssmParam.put("somQuoteId", this.dsImptSvcDtlBean.dsImptOrdBean.nwai4120_01.somQuoteId.getValue());
        ssmParam.put("somSrvLineId", somSrvLineId);
        @SuppressWarnings("unchecked")
        Map<Object, BigDecimal> map = (Map<Object, BigDecimal>) this.dsImptSvcDtlBean.dsImptOrdBean.ssmBatchClient.queryObject("getSrtCtAmt", ssmParam);
        return map;
    }
}

/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.NWAI4120_18TMsg;
import business.db.NWAI4120_19TMsg;
import business.db.NWAI4120_16TMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcPrcBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 01/20/2017   SRAA            K.Aratani       Update          QC#17122
 * 07/27/2017   SRAA            K.Aratani       Update          QC#20393
 * 08/17/2017   SRAA            K.Aratani       Update          QC#19968
 * 09/05/2017   SRAA            K.Aratani       Update          QC#20950
 *</pre>
 */
public class SomDsImptSvcPrcBean extends DS_IMPT_SVC_PRCTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SHELL_TYPE shellType;

    public SomDsImptOrdBean dsImptOrdBean;

    public SomDsImptSvcDtlBean dsImptSvcDtlBean;

    public final List<SomDsImptSvcUsgPrcBean> dsImptSvcUsgPrcBeanList;

    public NWAI4120_18TMsg nwai4120_18;

    public NWAI4120_19TMsg nwai4120_19;

    public NWAI4120_16TMsg nwai4120_16;
    
    private BigDecimal reqBaseAmt;

    public SomDsImptSvcPrcBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_18TMsg nwai4120_18, NWAI4120_16TMsg nwai4120_16) {
        this(shellType, dsImptOrdBean);

        this.nwai4120_18 = nwai4120_18;
        this.nwai4120_16 = nwai4120_16;
    }

    public SomDsImptSvcPrcBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_19TMsg nwai4120_19, NWAI4120_16TMsg nwai4120_16) {
        this(shellType, dsImptOrdBean);

        this.nwai4120_19 = nwai4120_19;
        this.nwai4120_16 = nwai4120_16;
    }

    private SomDsImptSvcPrcBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptOrdBean dsImptOrdBean) {
        super();

        this.shellType = shellType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptSvcUsgPrcBeanList = new ArrayList<SomDsImptSvcUsgPrcBean>();
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_PRC_SQ));
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        String somPkgNm = null;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_PRC_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcDtlBean.dsImptSvcDtlPk);
        
        //Fleet Case
        if (S21StringUtil.isEquals(this.dsImptSvcDtlBean.dsContrCatgCd.getValue(), DS_CONTR_CATG.FLEET)) {
            this.mdlId.clear();
            ZYPEZDItemValueSetter.setValue(this.maintPrcCatgCd, this.nwai4120_18.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(this.maintFlPrcCatgCd, this.maintPrcCatgCd);
            somPkgNm = this.nwai4120_18.somPkgNm.getValue();
            BigDecimal reqBaseAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetBwReqBaseAmt)) {
                reqBaseAmt = this.nwai4120_16.fleetBwReqBaseAmt.getValue().multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot());
            }
            if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetClrReqBaseAmt)) {
                reqBaseAmt = reqBaseAmt.add(this.nwai4120_16.fleetClrReqBaseAmt.getValue().multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot()));
            }
            ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, reqBaseAmt);
            ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, reqBaseAmt);
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, reqBaseAmt);
            ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, reqBaseAmt);
        //Non Fleet Case(Regular/Aggregate)
        } else {
            if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

                BigDecimal mdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.nwai4120_18.somMdlDescTxt.getValue())) {
                    mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.nwai4120_18.somMdlDescTxt.getValue());
                    this.dsImptOrdBean.getCacheModelMap().put(this.nwai4120_18.somMdlDescTxt.getValue(), mdlId);
                } else {
                    mdlId = this.dsImptOrdBean.getCacheModelMap().get(this.nwai4120_18.somMdlDescTxt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                ZYPEZDItemValueSetter.setValue(this.maintPrcCatgCd, this.nwai4120_18.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(this.maintFlPrcCatgCd, this.maintPrcCatgCd);
                somPkgNm = this.nwai4120_18.somPkgNm.getValue();
                //BigDecimal reqBaseAmt = BigDecimal.ZERO;
                //if (ZYPCommonFunc.hasValue(this.nwai4120_18.reqBaseAmt)) {
                //    reqBaseAmt = this.nwai4120_18.reqBaseAmt.getValue();
                //}
                ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, this.reqBaseAmt.multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot()));
                ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, this.reqBaseAmt.multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot()));
                ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.reqBaseAmt.multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot()));
                ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, this.reqBaseAmt.multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot()));
            } else if (NWAB412001Constant.SHELL_TYPE.NON_COPIER.equals(this.shellType)) {

                //QC#20950(SOM side changed the value correctly.)
                BigDecimal tmpMdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.nwai4120_19.somMdlDescLongTxt.getValue())) {
                    tmpMdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.nwai4120_19.somMdlDescLongTxt.getValue());
                    if (tmpMdlId == null) {
                        tmpMdlId = NWXC412001.getMdlIdByMdseForNonCopier(glblCmpyCd, this.nwai4120_19.somItemCd.getValue());
                    }
                    this.dsImptOrdBean.getCacheModelMap().put(this.nwai4120_19.somMdlDescLongTxt.getValue(), tmpMdlId);
                } else {
                    tmpMdlId = this.dsImptOrdBean.getCacheModelMap().get(this.nwai4120_19.somMdlDescLongTxt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(this.mdlId, tmpMdlId);
                //ZYPEZDItemValueSetter.setValue(this.mdlId, NWXC412001.getMdlIdByMdseForNonCopier(glblCmpyCd, this.nwai4120_19.somItemCd.getValue()));
                ZYPEZDItemValueSetter.setValue(this.maintPrcCatgCd, this.nwai4120_19.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(this.maintFlPrcCatgCd, this.maintPrcCatgCd);
                somPkgNm = this.nwai4120_19.somPkgNm.getValue();
                BigDecimal reqSvcPrcAmt = BigDecimal.ZERO;
                //QC#17122
                //if (ZYPCommonFunc.hasValue(this.nwai4120_19.reqSvcPrcAmt)) {
                //    reqSvcPrcAmt = this.nwai4120_19.reqSvcPrcAmt.getValue();
                //}
                if (ZYPCommonFunc.hasValue(this.reqBaseAmt)) {
                    reqSvcPrcAmt = this.reqBaseAmt;
                }
                ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, reqSvcPrcAmt);
                ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, reqSvcPrcAmt);
                ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, reqSvcPrcAmt);
                ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, reqSvcPrcAmt);
            }
        }

        this.prcTierSvcOfferCd.clear();
        BigDecimal prcMtrPkgPk = null;
        if (!this.dsImptOrdBean.getCachePrcMtrPkgPkMap().containsKey(somPkgNm)) {
            prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), somPkgNm, dsImptOrdBean.slsDt);
            this.dsImptOrdBean.getCachePrcMtrPkgPkMap().put(somPkgNm, prcMtrPkgPk);
            ZYPEZDItemValueSetter.setValue(this.prcMtrPkgPk, prcMtrPkgPk);
        } else {
            prcMtrPkgPk = this.dsImptOrdBean.getCachePrcMtrPkgPkMap().get(somPkgNm);
            ZYPEZDItemValueSetter.setValue(this.prcMtrPkgPk, prcMtrPkgPk);
        }
        Map<String, Object> prcMtrPkgMap = NWXC412001.getPrcMtrPkgFromName(glblCmpyCd, somPkgNm, salesDate);
        if (prcMtrPkgMap != null) {
            ZYPEZDItemValueSetter.setValue(this.corpAdvPrcFlg, (String) prcMtrPkgMap.get("CORP_ADV_PRC_FLG"));
        } else {
            ZYPEZDItemValueSetter.setValue(this.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
        }
        //QC#19968
        //Account#
        ZYPEZDItemValueSetter.setValue(this.billToCustCd, this.dsImptSvcDtlBean.getBillToCustAcctCdForPrc());
        
        //Bill To Customer Code
        ZYPEZDItemValueSetter.setValue(this.billToLocNum, this.dsImptSvcDtlBean.getBillToCustCdForPrc());

        for (SomDsImptSvcUsgPrcBean svcUsgPrcBean : this.dsImptSvcUsgPrcBeanList) {
            svcUsgPrcBean.doImptMapping(glblCmpyCd, salesDate);
        }

        return true;
    }
    
    public void setReqBaseAmt(BigDecimal reqBaseAmt) {
        this.reqBaseAmt = reqBaseAmt;
    }
    //QC#20393 start
    public BigDecimal getReqBaseAmt() {
        return this.reqBaseAmt;
    }
    //QC#20393 end
}

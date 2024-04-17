/*
 * <pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.NWAI4150_16TMsg;
import business.db.NWAI4150_18TMsg;
import business.db.NWAI4150_19TMsg;
import business.db.NWAI4150_22TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface Batch EopsDsImptSvcPrcBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class EopsDsImptSvcPrcBean extends DS_IMPT_SVC_PRCTMsg implements IEopsDsImptOrd {
    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.SHELL_TYPE shellType;

    /**  */
    public EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public EopsDsImptSvcDtlBean dsImptSvcDtlBean;

    /**  */
    public final List<EopsDsImptSvcUsgPrcBean> dsImptSvcUsgPrcBeanList;

    /**  */
    public NWAI4150_18TMsg nwai4150_18;

    /**  */
    public NWAI4150_19TMsg nwai4150_19;

    /**  */
    public NWAI4150_16TMsg nwai4150_16;

    /**  */
    private NWAI4150_22TMsg nwai4150_22;

    /**  */
    private BigDecimal reqBaseAmt;

    /**
     * EopsDsImptSvcPrcBean
     * @param shellType     NWAB415001Constant.SHELL_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415018    NWAI4150_18TMsg
     * @param nwai415016    NWAI4150_16TMsg
     * @param nwai415022    NWAI4150_22TMsg
     */
    public EopsDsImptSvcPrcBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_18TMsg nwai415018, NWAI4150_16TMsg nwai415016, NWAI4150_22TMsg nwai415022) {
        this(shellType, dsImptOrdBean);

        this.nwai4150_18 = nwai415018;
        this.nwai4150_16 = nwai415016;
        this.nwai4150_22 = nwai415022;
    }

    /**
     * EopsDsImptSvcPrcBean
     * @param shellType     NWAB415001Constant.SHELL_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415019    NWAI4150_19TMsg
     * @param nwai415016    NWAI4150_16TMsg
     * @param nwai415022    NWAI4150_22TMsg
     */
    public EopsDsImptSvcPrcBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_19TMsg nwai415019, NWAI4150_16TMsg nwai415016, NWAI4150_22TMsg nwai415022) {
        this(shellType, dsImptOrdBean);

        this.nwai4150_19 = nwai415019;
        this.nwai4150_16 = nwai415016;
        this.nwai4150_22 = nwai415022;
    }

    private EopsDsImptSvcPrcBean(NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptOrdBean dsImptOrdBean) {
        super();

        this.shellType = shellType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptSvcUsgPrcBeanList = new ArrayList<EopsDsImptSvcUsgPrcBean>();
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_PRC_SQ));
    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        String eopsPkgNm = null;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcDtlBean.dsImptSvcDtlPk);
        this.reqBaseAmt = new BigDecimal(this.nwai4150_22.reqBaseTxt.getValue());

        //Fleet Case
        if (S21StringUtil.isEquals(this.dsImptSvcDtlBean.dsContrCatgCd.getValue(), DS_CONTR_CATG.FLEET)) {
            this.mdlId.clear();
            ZYPEZDItemValueSetter.setValue(this.maintPrcCatgCd, this.nwai4150_18.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(this.maintFlPrcCatgCd, this.maintPrcCatgCd);
            eopsPkgNm = this.nwai4150_18.eopsPkgNm.getValue();
            //            BigDecimal reqBaseAmt = BigDecimal.ZERO;
            //            if (ZYPCommonFunc.hasValue(this.nwai4150_16.fleetBwReqBaseAmt)) {
            //                reqBaseAmt = this.nwai4150_16.fleetBwReqBaseAmt.getValue().multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot());
            //            }
            //            if (ZYPCommonFunc.hasValue(this.nwai4150_16.fleetClrReqBaseAmt)) {
            //                reqBaseAmt = reqBaseAmt.add(this.nwai4150_16.fleetClrReqBaseAmt.getValue().multiply(this.dsImptSvcDtlBean.getBaseBllgCycleMthAot()));
            //            }
            //            ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, reqBaseAmt);
            //            ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, reqBaseAmt);
            //            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, reqBaseAmt);
            //            ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, reqBaseAmt);
            //Non Fleet Case(Regular/Aggregate)
        } else {
            if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

                BigDecimal mdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.nwai4150_18.somMdlDescTxt.getValue())) {
                    mdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.nwai4150_18.somMdlDescTxt.getValue());
                    this.dsImptOrdBean.getCacheModelMap().put(this.nwai4150_18.somMdlDescTxt.getValue(), mdlId);
                } else {
                    mdlId = this.dsImptOrdBean.getCacheModelMap().get(this.nwai4150_18.somMdlDescTxt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(this.mdlId, mdlId);
                ZYPEZDItemValueSetter.setValue(this.maintPrcCatgCd, this.nwai4150_18.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(this.maintFlPrcCatgCd, this.maintPrcCatgCd);
                eopsPkgNm = this.nwai4150_18.eopsPkgNm.getValue();
                //                ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, reqBaseAmt);
                //                ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, reqBaseAmt);
                //                ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, reqBaseAmt);
                //                ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, reqBaseAmt);
            } else if (NWAB415001Constant.SHELL_TYPE.NON_COPIER.equals(this.shellType)) {

                //QC#20950(SOM side changed the value correctly.)
                BigDecimal tmpMdlId = null;
                if (!this.dsImptOrdBean.getCacheModelMap().containsKey(this.nwai4150_19.somMdlDescLongTxt.getValue())) {
                    tmpMdlId = NWXC412001.getMdlIdByName(glblCmpyCd, this.nwai4150_19.somMdlDescLongTxt.getValue());
                    //                    if (tmpMdlId == null) {
                    //                        tmpMdlId = NWXC412001.getMdlIdByMdseForNonCopier(glblCmpyCd, this.nwai4150_19.somItemCd.getValue());
                    //                    }
                    this.dsImptOrdBean.getCacheModelMap().put(this.nwai4150_19.somMdlDescLongTxt.getValue(), tmpMdlId);
                } else {
                    tmpMdlId = this.dsImptOrdBean.getCacheModelMap().get(this.nwai4150_19.somMdlDescLongTxt.getValue());
                }
                ZYPEZDItemValueSetter.setValue(this.mdlId, tmpMdlId);
                ZYPEZDItemValueSetter.setValue(this.maintPrcCatgCd, this.nwai4150_19.prcCatgCd);
                ZYPEZDItemValueSetter.setValue(this.maintFlPrcCatgCd, this.maintPrcCatgCd);
                eopsPkgNm = this.nwai4150_19.somPkgNm.getValue();
                //                BigDecimal reqSvcPrcAmt = BigDecimal.ZERO;
                //                if (ZYPCommonFunc.hasValue(this.reqBaseAmt)) {
                //                    reqSvcPrcAmt = this.reqBaseAmt;
                //                }
                //                ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, reqSvcPrcAmt);
                //                ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, reqSvcPrcAmt);
                //                ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, reqSvcPrcAmt);
                //                ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, reqSvcPrcAmt);
            }
        }
        ZYPEZDItemValueSetter.setValue(this.basePrcDealAmt, this.reqBaseAmt);
        ZYPEZDItemValueSetter.setValue(this.basePrcFuncAmt, this.reqBaseAmt);
        ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.reqBaseAmt);
        ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, this.reqBaseAmt);

        BigDecimal prcMtrPkgPk = null;
        if (!this.dsImptOrdBean.getCachePrcMtrPkgPkMap().containsKey(eopsPkgNm)) {
            prcMtrPkgPk = NWXC412001.getPrcMtrPkgPkFromName(dsImptOrdBean.glblCmpyCd.getValue(), eopsPkgNm, dsImptOrdBean.slsDt);
            this.dsImptOrdBean.getCachePrcMtrPkgPkMap().put(eopsPkgNm, prcMtrPkgPk);
            ZYPEZDItemValueSetter.setValue(this.prcMtrPkgPk, prcMtrPkgPk);
        } else {
            prcMtrPkgPk = this.dsImptOrdBean.getCachePrcMtrPkgPkMap().get(eopsPkgNm);
            ZYPEZDItemValueSetter.setValue(this.prcMtrPkgPk, prcMtrPkgPk);
        }
        this.prcTierSvcOfferCd.clear();
        Map<String, Object> prcMtrPkgMap = NWXC412001.getPrcMtrPkgFromName(glblCmpyCd, eopsPkgNm, salesDate);
        if (prcMtrPkgMap != null) {
            ZYPEZDItemValueSetter.setValue(this.corpAdvPrcFlg, (String) prcMtrPkgMap.get("CORP_ADV_PRC_FLG"));
        } else {
            ZYPEZDItemValueSetter.setValue(this.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
        }

        //Bill To Customer Code
        this.billToLocNum.clear();
        //Account#
        this.billToCustCd.clear();

        for (EopsDsImptSvcUsgPrcBean svcUsgPrcBean : this.dsImptSvcUsgPrcBeanList) {
            svcUsgPrcBean.doImptMapping(glblCmpyCd, salesDate);
        }

        return true;
    }

    /**
     * setReqBaseAmt
     * @param reqBaseAmt    BigDecimal
     */
    public void setReqBaseAmt(BigDecimal reqBaseAmt) {
        this.reqBaseAmt = reqBaseAmt;
    }

    /**
     * getReqBaseAmt
     */
    public BigDecimal getReqBaseAmt() {
        return this.reqBaseAmt;
    }

}

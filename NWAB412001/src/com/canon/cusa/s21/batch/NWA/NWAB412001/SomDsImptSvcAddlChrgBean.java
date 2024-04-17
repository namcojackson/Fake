/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;

import business.db.DS_IMPT_SVC_ADDL_CHRGTMsg;
import business.db.NWAI4120_20TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcAddlChrgBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 2016/11/29   Fujitsu         M.Yamada        Update          QC#16266
 *</pre>
 */
public class SomDsImptSvcAddlChrgBean extends DS_IMPT_SVC_ADDL_CHRGTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SHELL_TYPE shellType;

    public final SomDsImptSvcDtlBean dsImptSvcDtlBean;
    
    public final SomDsImptOrdConfigBean somDsImptOrdConfigBean;

    public final NWAI4120_20TMsg nwai4120_20;

    public SomDsImptSvcAddlChrgBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4120_20TMsg nwai4120_20, SomDsImptOrdConfigBean somDsImptOrdConfigBean) {
        super();

        this.shellType = shellType;
        this.dsImptSvcDtlBean = dsImptSvcDtlBean;
        this.nwai4120_20 = nwai4120_20;
        this.somDsImptOrdConfigBean = somDsImptOrdConfigBean;
        dsImptSvcDtlBean.dsImptSvcAddlChrgBeanList.add(this);
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcAddlChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_ADDL_CHRG_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcDtlBean.dsImptSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, getDsImptOrdDtlPk());
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.addlChrgPrcCatgCd, this.nwai4120_20.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(this.addlChrgMdseCd, this.nwai4120_20.somSvcItemCd);
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
            ZYPEZDItemValueSetter.setValue(this.addlChrgPrcDealAmt, this.nwai4120_20.reqSvcPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.addlChrgPrcFuncAmt, this.nwai4120_20.reqSvcPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_20.reqSvcPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, this.nwai4120_20.reqSvcPrcAmt);
        } else {
            if (this.dsImptSvcDtlBean.nwai4120_19 != null && ZYPCommonFunc.hasValue(this.dsImptSvcDtlBean.nwai4120_19.somDurnId)) {
                BigDecimal prcAmt = this.nwai4120_20.reqSvcPrcAmt.getValue().divide(this.dsImptSvcDtlBean.nwai4120_19.somDurnId.getValue(), 4, BigDecimal.ROUND_HALF_UP);
                ZYPEZDItemValueSetter.setValue(this.addlChrgPrcDealAmt, prcAmt);
                ZYPEZDItemValueSetter.setValue(this.addlChrgPrcFuncAmt, prcAmt);
                ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, prcAmt);
                ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, prcAmt);
            }
        }
        ZYPEZDItemValueSetter.setValue(this.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_ADDITIONAL_CHARGE);
        //this.addlChrgPrcFuncAmt.clear();
        //this.funcPrcListPrcAmt.clear();
        ZYPEZDItemValueSetter.setValue(this.printDtlFlg, ZYPConstant.FLG_OFF_N); // QC#16266

        return true;
    }

    private BigDecimal getDsImptOrdDtlPk() {

        String somSvcItemCd = this.nwai4120_20.engnrItemCd.getValue();

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return null;
        }

        for (SomDsImptOrdDtlBean detail : this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdDtlBeanList) {

            if (detail.dsImptOrdConfigPk.getValue().compareTo(this.somDsImptOrdConfigBean.dsImptOrdConfigPk.getValue()) == 0
                && S21StringUtil.isEquals(detail.mdseCd.getValue(), somSvcItemCd)) {

                return detail.dsImptOrdDtlPk.getValue();
            }
        }

        // invalid case
        return null;
    }
}

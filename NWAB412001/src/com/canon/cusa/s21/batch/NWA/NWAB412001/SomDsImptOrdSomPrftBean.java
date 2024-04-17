/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

import business.db.DS_IMPT_ORD_SOM_PRFTTMsg;
import business.db.LEASE_PRCH_OPTTMsg;
import business.db.NWAI4120_04TMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdSomPrftBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 02/07/2016   FUJITSU         H.Nagashima     Update          QC#17120
 * 08/18/2017   SRAA            K.Aratani       Update          QC#20097
 *</pre>
 */
public class SomDsImptOrdSomPrftBean extends DS_IMPT_ORD_SOM_PRFTTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAI4120_04TMsg nwai4120_04;

    public final SomDsImptOrdBean dsImptOrdBean;

    public SomDsImptOrdSomPrftBean(SomDsImptOrdBean dsImptOrdBean, NWAI4120_04TMsg nwai4120_04) {
        super();

        this.nwai4120_04 = nwai4120_04;
        this.dsImptOrdBean = dsImptOrdBean;
        dsImptOrdBean.dsImptOrdSomPrftBeanList.add(this);
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSomPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_ORD_SOM_PRFT_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.somQuoteId, this.nwai4120_04.somQuoteId);
        ZYPEZDItemValueSetter.setValue(this.somCmsnAmt, this.nwai4120_04.somCmsnAmt);
        ZYPEZDItemValueSetter.setValue(this.svcCostAmt, this.nwai4120_04.svcCostAmt);
        ZYPEZDItemValueSetter.setValue(this.svcCostPmtAmt, this.nwai4120_04.svcCostPmtAmt);
        ZYPEZDItemValueSetter.setValue(this.svcCostTrnsfAmt, this.nwai4120_04.svcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(this.somMkupAmt, this.nwai4120_04.somMkupAmt);
        ZYPEZDItemValueSetter.setValue(this.ordTotAmt, this.nwai4120_04.ordTotAmt);
        ZYPEZDItemValueSetter.setValue(this.somTotRevAmt, this.nwai4120_04.somTotRevAmt);
        ZYPEZDItemValueSetter.setValue(this.somTotEarnAmt, this.nwai4120_04.somTotEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.interRgEarnAmt, this.nwai4120_04.interRgEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.interTrtyRevAmt, this.nwai4120_04.interTrtyRevAmt);
        ZYPEZDItemValueSetter.setValue(this.interTrtyEarnAmt, this.nwai4120_04.interTrtyEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.spiffRevAmt, this.nwai4120_04.spiffRevAmt);
        ZYPEZDItemValueSetter.setValue(this.spiffEarnAmt, this.nwai4120_04.spiffEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.unitSldEarnAmt, this.nwai4120_04.unitSoldEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcAmt, this.nwai4120_04.sbscrSvcAmt);
        ZYPEZDItemValueSetter.setValue(this.gpEquipRevAmt, this.nwai4120_04.gpEquipRevAmt);
        ZYPEZDItemValueSetter.setValue(this.gpEquipEarnAmt, this.nwai4120_04.gpEquipEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcRevAmt, this.nwai4120_04.sbscrSvcRevAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcEarnAmt, this.nwai4120_04.sbscrSvcEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.interRgRevAmt, this.nwai4120_04.interRgRevAmt);
        ZYPEZDItemValueSetter.setValue(this.repEquipRevAmt, this.nwai4120_04.repEquipRevAmt);
        ZYPEZDItemValueSetter.setValue(this.gpEquipAmt, this.nwai4120_04.gpEquipAmt);
        ZYPEZDItemValueSetter.setValue(this.extFlPrcAmt, this.nwai4120_04.extFlPrcAmt);
        ZYPEZDItemValueSetter.setValue(this.somPrmoAmt, this.nwai4120_04.somPrmoAmt);
        ZYPEZDItemValueSetter.setValue(this.somTradeInAmt, this.nwai4120_04.somTradeInAmt);
        ZYPEZDItemValueSetter.setValue(this.somTaxAmt, this.nwai4120_04.somTaxAmt);
        ZYPEZDItemValueSetter.setValue(this.somCbsInvAmt, this.nwai4120_04.somCbsInvAmt);
        ZYPEZDItemValueSetter.setValue(this.somFinalFlAmt, this.nwai4120_04.somFinalFlAmt);
        ZYPEZDItemValueSetter.setValue(this.gpCostTrnsfAmt, this.nwai4120_04.gpCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(this.totRepRevAmt, this.nwai4120_04.totRepRevAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcRepRevAmt, this.nwai4120_04.sbscrSvcRepRevAmt);
        ZYPEZDItemValueSetter.setValue(this.somByotFinAmt, this.nwai4120_04.somByotFinAmt);
        ZYPEZDItemValueSetter.setValue(this.miscNonTaxAbleAmt, this.nwai4120_04.miscNonTaxAbleAmt);
        ZYPEZDItemValueSetter.setValue(this.somOthEquipAmt, this.nwai4120_04.somOthEquipAmt);
        ZYPEZDItemValueSetter.setValue(this.msrpListPrcAmt, this.nwai4120_04.msrpListPrcAmt);
        ZYPEZDItemValueSetter.setValue(this.somTaxAbleAmt, this.nwai4120_04.somTaxAbleAmt);
        ZYPEZDItemValueSetter.setValue(this.nonTaxAbleAmt, this.nwai4120_04.nonTaxAbleAmt);
        ZYPEZDItemValueSetter.setValue(this.somTotFinAmt, this.nwai4120_04.somTotFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somEquipFinAmt, this.nwai4120_04.somEquipFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somEquipPayAmt, this.nwai4120_04.somEquipPayAmt);
        ZYPEZDItemValueSetter.setValue(this.somSvcFinAmt, this.nwai4120_04.somSvcFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somSplyFinAmt, this.nwai4120_04.somSplyFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somUpgFinAmt, this.nwai4120_04.somUpgFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somPctMkupRate, this.nwai4120_04.somPctMkupRate);
        ZYPEZDItemValueSetter.setValue(this.somSoftPctCostRate, this.nwai4120_04.somSoftPctCostRate);
        ZYPEZDItemValueSetter.setValue(this.somFinVsListRate, this.nwai4120_04.somFinVsListRate);
        ZYPEZDItemValueSetter.setValue(this.gpCostPctTrnsfRate, this.nwai4120_04.gpCostPctTrnsfRate);
        ZYPEZDItemValueSetter.setValue(this.gpPctEquipRate, this.nwai4120_04.gpPctEquipRate);
        ZYPEZDItemValueSetter.setValue(this.unitSldRevAmt, this.nwai4120_04.unitSoldRevAmt);
        ZYPEZDItemValueSetter.setValue(this.gpWotCostTrnsfAmt, this.nwai4120_04.gpWotCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(this.flAdjAmt, this.nwai4120_04.flAdjAmt);
        //QC#20097
        if (ZYPCommonFunc.hasValue(this.nwai4120_04.somSvcRevTrnsfAmt)) {
            ZYPEZDItemValueSetter.setValue(this.somSvcRevTrnsfAmt, this.nwai4120_04.somSvcRevTrnsfAmt.getValue().negate());
        } else {
            this.somSvcRevTrnsfAmt.clear();
        }
        ZYPEZDItemValueSetter.setValue(this.somTotCostTrnsfAmt, this.nwai4120_04.somTotCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(this.leaseCmpyNm, this.dsImptOrdBean.nwai4120_01.leaseCmpyNm);
        ZYPEZDItemValueSetter.setValue(this.leasePmtAmt, this.dsImptOrdBean.nwai4120_01.leasePmtAmt);
        //QC#18023
        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.leaseTpTxt)) {
            ZYPEZDItemValueSetter.setValue(this.leaseTpTxt, this.dsImptOrdBean.nwai4120_01.leaseTpTxt);
        } else {
            if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.leasePrchOptCd)) {
                LEASE_PRCH_OPTTMsg leasePrchOpt = new LEASE_PRCH_OPTTMsg();
                ZYPEZDItemValueSetter.setValue(leasePrchOpt.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(leasePrchOpt.leasePrchOptCd, this.dsImptOrdBean.leasePrchOptCd);
                leasePrchOpt = (LEASE_PRCH_OPTTMsg) S21CodeTableAccessor.findByKey(leasePrchOpt);
                if (leasePrchOpt != null) {
                    ZYPEZDItemValueSetter.setValue(this.leaseTpTxt, leasePrchOpt.leasePrchOptDescTxt.getValue());
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(this.csmpIndSomTxt, this.dsImptOrdBean.nwai4120_01.csmpIndSomTxt);
        ZYPEZDItemValueSetter.setValue(this.csmpCrAmt, this.dsImptOrdBean.nwai4120_01.csmpCrAmt);
        ZYPEZDItemValueSetter.setValue(this.proSvcAmt, this.nwai4120_04.proSvcAmt);
        ZYPEZDItemValueSetter.setValue(this.gpWotCostTrnsfRate, this.nwai4120_04.gpWotCostTrnsfRate);   //QC#17120 add

        return true;
    }
}

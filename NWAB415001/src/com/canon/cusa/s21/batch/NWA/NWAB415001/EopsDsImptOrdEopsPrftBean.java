/*
 * <Pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import business.db.DS_IMPT_ORD_SOM_PRFTTMsg;
import business.db.LEASE_PRCH_OPTTMsg;
import business.db.NWAI4150_04TMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptOrdEopsPrftBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public class EopsDsImptOrdEopsPrftBean extends DS_IMPT_ORD_SOM_PRFTTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAI4150_04TMsg nwai415004;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**
     * EopsDsImptOrdEopsPrftBean
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415004    NWAI4150_04TMsg
     */
    public EopsDsImptOrdEopsPrftBean(//
            EopsDsImptOrdBean dsImptOrdBean, NWAI4150_04TMsg nwai415004) {
        super();

        this.nwai415004 = nwai415004;
        this.dsImptOrdBean = dsImptOrdBean;
        dsImptOrdBean.dsImptOrdEopsPrftBeanList.add(this);
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
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSomPrftPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_ORD_SOM_PRFT_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.somQuoteId, this.nwai415004.somQuoteId);
        ZYPEZDItemValueSetter.setValue(this.somCmsnAmt, this.nwai415004.somCmsnAmt);
        ZYPEZDItemValueSetter.setValue(this.svcCostAmt, this.nwai415004.svcCostAmt);
        ZYPEZDItemValueSetter.setValue(this.svcCostPmtAmt, this.nwai415004.svcCostPmtAmt);
        ZYPEZDItemValueSetter.setValue(this.svcCostTrnsfAmt, this.nwai415004.svcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(this.somMkupAmt, this.nwai415004.somMkupAmt);
        ZYPEZDItemValueSetter.setValue(this.ordTotAmt, this.nwai415004.ordTotAmt);
        ZYPEZDItemValueSetter.setValue(this.somTotRevAmt, this.nwai415004.somTotRevAmt);
        ZYPEZDItemValueSetter.setValue(this.somTotEarnAmt, this.nwai415004.somTotEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.interRgEarnAmt, this.nwai415004.interRgEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.interTrtyRevAmt, this.nwai415004.interTrtyRevAmt);
        ZYPEZDItemValueSetter.setValue(this.interTrtyEarnAmt, this.nwai415004.interTrtyEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.spiffRevAmt, this.nwai415004.spiffRevAmt);
        ZYPEZDItemValueSetter.setValue(this.spiffEarnAmt, this.nwai415004.spiffEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.unitSldEarnAmt, this.nwai415004.unitSoldEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcAmt, this.nwai415004.sbscrSvcAmt);
        ZYPEZDItemValueSetter.setValue(this.gpEquipRevAmt, this.nwai415004.gpEquipRevAmt);
        ZYPEZDItemValueSetter.setValue(this.gpEquipEarnAmt, this.nwai415004.gpEquipEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcRevAmt, this.nwai415004.sbscrSvcRevAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcEarnAmt, this.nwai415004.sbscrSvcEarnAmt);
        ZYPEZDItemValueSetter.setValue(this.interRgRevAmt, this.nwai415004.interRgRevAmt);
        ZYPEZDItemValueSetter.setValue(this.repEquipRevAmt, this.nwai415004.repEquipRevAmt);
        ZYPEZDItemValueSetter.setValue(this.gpEquipAmt, this.nwai415004.gpEquipAmt);
        ZYPEZDItemValueSetter.setValue(this.extFlPrcAmt, this.nwai415004.extFlPrcAmt);
        ZYPEZDItemValueSetter.setValue(this.somPrmoAmt, this.nwai415004.somPrmoAmt);
        ZYPEZDItemValueSetter.setValue(this.somTradeInAmt, this.nwai415004.somTradeInAmt);
        ZYPEZDItemValueSetter.setValue(this.somTaxAmt, this.nwai415004.somTaxAmt);
        ZYPEZDItemValueSetter.setValue(this.somCbsInvAmt, this.nwai415004.somCbsInvAmt);
        ZYPEZDItemValueSetter.setValue(this.somFinalFlAmt, this.nwai415004.somFinalFlAmt);
        ZYPEZDItemValueSetter.setValue(this.gpCostTrnsfAmt, this.nwai415004.gpCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(this.totRepRevAmt, this.nwai415004.totRepRevAmt);
        ZYPEZDItemValueSetter.setValue(this.sbscrSvcRepRevAmt, this.nwai415004.sbscrSvcRepRevAmt);
        ZYPEZDItemValueSetter.setValue(this.somByotFinAmt, this.nwai415004.somByotFinAmt);
        ZYPEZDItemValueSetter.setValue(this.miscNonTaxAbleAmt, this.nwai415004.miscNonTaxAbleAmt);
        ZYPEZDItemValueSetter.setValue(this.somOthEquipAmt, this.nwai415004.somOthEquipAmt);
        ZYPEZDItemValueSetter.setValue(this.msrpListPrcAmt, this.nwai415004.msrpListPrcAmt);
        ZYPEZDItemValueSetter.setValue(this.somTaxAbleAmt, this.nwai415004.somTaxAbleAmt);
        ZYPEZDItemValueSetter.setValue(this.nonTaxAbleAmt, this.nwai415004.nonTaxAbleAmt);
        ZYPEZDItemValueSetter.setValue(this.somTotFinAmt, this.nwai415004.somTotFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somEquipFinAmt, this.nwai415004.somEquipFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somEquipPayAmt, this.nwai415004.somEquipPayAmt);
        ZYPEZDItemValueSetter.setValue(this.somSvcFinAmt, this.nwai415004.somSvcFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somSplyFinAmt, this.nwai415004.somSplyFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somUpgFinAmt, this.nwai415004.somUpgFinAmt);
        ZYPEZDItemValueSetter.setValue(this.somPctMkupRate, this.nwai415004.somPctMkupRate);
        ZYPEZDItemValueSetter.setValue(this.somSoftPctCostRate, this.nwai415004.somSoftPctCostRate);
        ZYPEZDItemValueSetter.setValue(this.somFinVsListRate, this.nwai415004.somFinVsListRate);
        ZYPEZDItemValueSetter.setValue(this.gpCostPctTrnsfRate, this.nwai415004.gpCostPctTrnsfRate);
        ZYPEZDItemValueSetter.setValue(this.gpPctEquipRate, this.nwai415004.gpPctEquipRate);
        ZYPEZDItemValueSetter.setValue(this.unitSldRevAmt, this.nwai415004.unitSoldRevAmt);

        this.gpWotCostTrnsfAmt.clear();
        this.flAdjAmt.clear();
        this.somSvcRevTrnsfAmt.clear();
        ZYPEZDItemValueSetter.setValue(this.somTotCostTrnsfAmt, this.nwai415004.somTotCostTrnsfAmt);

        ZYPEZDItemValueSetter.setValue(this.leaseCmpyNm, this.dsImptOrdBean.nwai415001.leaseCmpyNm);
        ZYPEZDItemValueSetter.setValue(this.leasePmtAmt, this.dsImptOrdBean.nwai415001.leasePmtAmt);


        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai415001.leaseTpTxt)) {
            ZYPEZDItemValueSetter.setValue(this.leaseTpTxt, this.dsImptOrdBean.nwai415001.leaseTpTxt);
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
        ZYPEZDItemValueSetter.setValue(this.csmpIndSomTxt, this.dsImptOrdBean.nwai415001.csmpIndSomTxt);
        ZYPEZDItemValueSetter.setValue(this.csmpCrAmt, this.dsImptOrdBean.nwai415001.csmpCrAmt);

        this.proSvcAmt.clear();
        this.gpWotCostTrnsfRate.clear();

        return true;
    }

}

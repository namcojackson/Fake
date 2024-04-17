/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_JRNL_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

import business.db.DS_IMPT_PRC_CALC_BASETMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptPrcCalcBaseBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 *</pre>
 */
public class SomDsImptPrcCalcBaseBean extends DS_IMPT_PRC_CALC_BASETMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.PRC_CALC_BASE_TYPE prcCalcBaseType;

    public final SomDsImptOrdDtlBean dsImptOrdDtlBean;

    public SomDsImptPrcCalcBaseBean(NWAB412001Constant.PRC_CALC_BASE_TYPE prcCalcBaseType, SomDsImptOrdDtlBean dsImptOrdDtlBean) {
        super();

        this.prcCalcBaseType = prcCalcBaseType;
        this.dsImptOrdDtlBean = dsImptOrdDtlBean;
        dsImptOrdDtlBean.dsImptPrcCalcBaseBeanList.add(this);
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptPrcCalcBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_PRC_CALC_BASE_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, this.dsImptOrdDtlBean.dsImptOrdDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdDtlBean.dsImptOrdPk);

        if (NWAB412001Constant.PRC_CALC_BASE_TYPE.FREIGHT_AMOUNT.equals(prcCalcBaseType)) {

            // for freight
            this.prcCondTpCd.clear();
            ZYPEZDItemValueSetter.setValue(this.prcDtlGrpCd, PRC_DTL_GRP.FREIGHT);
            this.prcJrnlGrpCd.clear();

        } else {

            // not for freight
            ZYPEZDItemValueSetter.setValue(this.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(this.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(this.prcJrnlGrpCd, PRC_JRNL_GRP.BASE_PRICE);
        }

        ZYPEZDItemValueSetter.setValue(this.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(this.prcCondUnitCd, PRC_COND_UNIT.AMT);
        this.prcCalcMethCd.clear();
        this.dsMdsePrcPk.clear();

        String quoteLineTpTxt = null;
        if (this.dsImptOrdDtlBean.nwai4120_05 != null) {

            quoteLineTpTxt = this.dsImptOrdDtlBean.nwai4120_05.quoteLineTpTxt.getValue();
        }
        
        String keyQuoteLineTpTxt = quoteLineTpTxt == null ? "" : quoteLineTpTxt;
        String keyPrcCondTpCd = this.prcCondTpCd.getValue() == null ? "" : this.prcCondTpCd.getValue();
        String specCondPrcKey = CPO_SRC_TP.SOM + NWAB412001Constant.INTERFACE_ID.NWAI4120.name() + keyQuoteLineTpTxt + keyPrcCondTpCd;
        BigDecimal specCondPrcPk = null;
        if (!this.dsImptOrdDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().containsKey(specCondPrcKey)) {
            specCondPrcPk = NWXC412001.getSpecCondPrcPk(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), quoteLineTpTxt, this.prcCondTpCd.getValue());
            this.dsImptOrdDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().put(specCondPrcKey, specCondPrcPk);
            //for SomDsImptRtrnPrcCalcBean
            if (ZYPCommonFunc.hasValue(this.prcCondTpCd)) {
                this.dsImptOrdDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().put(this.prcCondTpCd.getValue(), specCondPrcPk);
            }
        } else {
            specCondPrcPk = this.dsImptOrdDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().get(specCondPrcKey);
        }
        ZYPEZDItemValueSetter.setValue(this.specCondPrcPk, specCondPrcPk);
        this.frtPerWtPk.clear();
        ZYPEZDItemValueSetter.setValue(this.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(this.prcCondManAddFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(this.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);

        // amount
        if (NWAB412001Constant.PRC_CALC_BASE_TYPE.REGULAR_SHIP_LINE.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_10.sellPrcListAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_10.sellPrcListAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_10.extSellPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.nwai4120_10.sellPrcListAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.OCE_PROMO.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.nwai4120_14.somPrmoAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.TRADE_IN.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somExtPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.UPGRADEBUYOUTREBATE.equals(prcCalcBaseType)) {

            BigDecimal somPrcAmt = NWXC412001.getSomAmtForRebate(this.dsImptOrdDtlBean.nwai4120_05.somItemDescTxt.getValue(), this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt.getValue());
            BigDecimal somPrcExtAmt = NWXC412001.getSomAmtForRebate(this.dsImptOrdDtlBean.nwai4120_05.somItemDescTxt.getValue(), this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt.getValue());

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, somPrcExtAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, somPrcAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN.equals(prcCalcBaseType)) {

            BigDecimal somPrcAmt = NWXC412001.getSomAmtForRebate(this.dsImptOrdDtlBean.nwai4120_05.somItemDescTxt.getValue(), this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt.getValue());
            BigDecimal somPrcExtAmt = NWXC412001.getSomAmtForRebate(this.dsImptOrdDtlBean.nwai4120_05.somItemDescTxt.getValue(), this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt.getValue());

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, somPrcExtAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, somPrcAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.BUYOUT.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somExtPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.HEADER_REBATE.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.dsImptOrdBean.nwai4120_01.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.dsImptOrdBean.nwai4120_01.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.dsImptOrdBean.nwai4120_01.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.dsImptOrdBean.nwai4120_01.somRebAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.SPIFF_ITEM.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_08.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_08.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_08.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.nwai4120_08.spiffDtlAmt);

        } else if (NWAB412001Constant.PRC_CALC_BASE_TYPE.FREIGHT_AMOUNT.equals(prcCalcBaseType)) {

            ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, this.dsImptOrdDtlBean.nwai4120_05.somExtPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, this.dsImptOrdDtlBean.nwai4120_05.somPrcAmt);

        } else {

            // none
        }

        this.maxPrcAmtRate.clear();
        this.minPrcAmtRate.clear();

        this.coaAcctCd.clear();
        this.autoPrcCcyCd.clear();
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(this.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        this.prcRulePrcdPk.clear();
        // QC#9700  2018/09/03 Add End

        return true;
    }
}

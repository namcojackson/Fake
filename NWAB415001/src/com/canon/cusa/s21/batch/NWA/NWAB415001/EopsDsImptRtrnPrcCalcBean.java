/*
 * <pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;

import business.db.DS_IMPT_RTRN_PRC_CALCTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_JRNL_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

/**
 * <pre>
 * EOPS Interface Batch EopsDsImptRtrnPrcCalcBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 
 * </pre>
 */
public class EopsDsImptRtrnPrcCalcBean extends DS_IMPT_RTRN_PRC_CALCTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.RTRN_DTL_TYPE rtrnDtlType;

    /**  */
    public final EopsDsImptOrdRtrnDtlBean dsImptOrdRtrnDtlBean;

    /**
     * EopsDsImptRtrnPrcCalcBean
     * @param rtrnDtlType           NWAB415001Constant.RTRN_DTL_TYPE
     * @param dsImptOrdRtrnDtlBean  EopsDsImptOrdRtrnDtlBean
     */
    public EopsDsImptRtrnPrcCalcBean(NWAB415001Constant.RTRN_DTL_TYPE rtrnDtlType, EopsDsImptOrdRtrnDtlBean dsImptOrdRtrnDtlBean) {
        super();

        this.rtrnDtlType = rtrnDtlType;
        this.dsImptOrdRtrnDtlBean = dsImptOrdRtrnDtlBean;
        dsImptOrdRtrnDtlBean.dsImptRtrnPrcCalcBeanList.add(this);
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
        ZYPEZDItemValueSetter.setValue(this.dsImptRtrnPrcCalcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_RTRN_PRC_CALC_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdRtrnDtlPk, this.dsImptOrdRtrnDtlBean.dsImptOrdRtrnDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdRtrnDtlBean.dsImptOrdPk);

        ZYPEZDItemValueSetter.setValue(this.prcCondTpCd, PRC_COND_TP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(this.prcDtlGrpCd, PRC_DTL_GRP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(this.prcJrnlGrpCd, PRC_JRNL_GRP.BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(this.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(this.prcCondUnitCd, PRC_COND_UNIT.AMT);
        this.prcCalcMethCd.clear();
        this.dsMdsePrcPk.clear();

        if (!this.dsImptOrdRtrnDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().containsKey(this.prcCondTpCd.getValue())) {
            BigDecimal specCondPrcPk = NWXC412001.getSpecCondPrcPkByPrcCondTpCd(glblCmpyCd, PRC_COND_TP.BASE_PRICE);
            ZYPEZDItemValueSetter.setValue(this.specCondPrcPk, specCondPrcPk);
            this.dsImptOrdRtrnDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().put(this.prcCondTpCd.getValue(), specCondPrcPk);
        } else {
            ZYPEZDItemValueSetter.setValue(this.specCondPrcPk, this.dsImptOrdRtrnDtlBean.dsImptOrdBean.getCacheSpecCondPrcMap().get(this.prcCondTpCd.getValue()));
        }
        this.frtPerWtPk.clear();
        ZYPEZDItemValueSetter.setValue(this.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(this.prcCondManAddFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(this.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(this.autoPrcAmtRate, BigDecimal.ZERO);
        this.maxPrcAmtRate.clear();
        this.minPrcAmtRate.clear();
        ZYPEZDItemValueSetter.setValue(this.manPrcAmtRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(this.calcPrcAmtRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(this.unitPrcAmt, BigDecimal.ZERO);
        this.coaAcctCd.clear();
        this.autoPrcCcyCd.clear();
        // QC#9700  2018/09/03 Add Start
        ZYPEZDItemValueSetter.setValue(this.prcRuleApplyFlg, ZYPConstant.FLG_ON_Y);
        this.prcRulePrcdPk.clear();
        // QC#9700  2018/09/03 Add End

        return true;
    }
}

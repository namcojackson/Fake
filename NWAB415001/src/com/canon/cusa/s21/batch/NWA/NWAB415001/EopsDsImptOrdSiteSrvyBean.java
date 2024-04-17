/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

/**
 * <pre>
 * EOPS Interface Batch EopsDsImptOrdSiteSrvyBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class EopsDsImptOrdSiteSrvyBean extends DS_IMPT_ORD_SITE_SRVYTMsg implements IEopsDsImptOrd {
    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.SITE_SRVY_TYPE siteSrvyType;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final EopsDsImptOrdConfigBean dsImptOrdConfigBean;

    /**
     * EopsDsImptOrdSiteSrvyBean
     * @param siteSrvyType          NWAB415001Constant.SITE_SRVY_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     */
    public EopsDsImptOrdSiteSrvyBean(NWAB415001Constant.SITE_SRVY_TYPE siteSrvyType, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.siteSrvyType = siteSrvyType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        dsImptOrdConfigBean.dsImptOrdSiteSrvyBean = this;
    }

    /**
     * EopsDsImptOrdSiteSrvyBean
     * @param siteSrvyType  NWAB415001Constant.SITE_SRVY_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     */
    public EopsDsImptOrdSiteSrvyBean(NWAB415001Constant.SITE_SRVY_TYPE siteSrvyType, EopsDsImptOrdBean dsImptOrdBean) {
        super();

        this.siteSrvyType = siteSrvyType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = null;
        dsImptOrdBean.dsImptOrdSiteSrvyBean = this;
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
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SITE_SRVY_SQ));
        if (NWAB415001Constant.SITE_SRVY_TYPE.HEADER_DELY_INFO.equals(this.siteSrvyType)) {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
            this.dsImptOrdConfigPk.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        }
        this.cmpyInfoAptBldgNm.clear();
        this.cmpyInfoFlNm.clear();
        this.cmpyInfoDeptNm.clear();
        this.cmpyInfoCtacNm.clear();
        this.cmpyInfoTelNum.clear();
        this.otsdStepNum.clear();
        this.insdStepNum.clear();
        ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        this.flgtStairNum.clear();
        ZYPEZDItemValueSetter.setValue(this.elevAvalFlg, ZYPConstant.FLG_OFF_N);
        this.elevAvalFromHourMn.clear();
        this.elevAvalToHourMn.clear();
        ZYPEZDItemValueSetter.setValue(this.elevApptReqFlg, ZYPConstant.FLG_OFF_N);
        this.elevCtacTelNum.clear();
        ZYPEZDItemValueSetter.setValue(this.elevProtReqFlg, ZYPConstant.FLG_OFF_N);
        this.elevWdt.clear();
        this.elevDepthNum.clear();
        this.elevCtacPsnNm.clear();
        this.elevCapWt.clear();
        this.elevDoorHgt.clear();
        this.elevDoorWdt.clear();
        this.stairAndLdgWdt.clear();
        this.crdrWdt.clear();
        this.doorWdt.clear();
        ZYPEZDItemValueSetter.setValue(this.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        this.loadDockHgt.clear();
        ZYPEZDItemValueSetter.setValue(this.trctrAndTrailAccsFlg, ZYPConstant.FLG_OFF_N);
        this.bldgEntDoorHgt.clear();
        this.bldgEntDoorWdt.clear();
        this.loadDockAvalFromHourMn.clear();
        this.loadDockAvalToHourMn.clear();
        this.carrDelyTmHourMn.clear();
        this.delyTrnspOptCd.clear();
        this.siteSrvyAddlCmntTxt.clear();

        return true;
    }
}

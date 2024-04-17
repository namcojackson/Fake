/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdSiteSrvyBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 *</pre>
 */
public class SomDsImptOrdSiteSrvyBean extends DS_IMPT_ORD_SITE_SRVYTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SITE_SRVY_TYPE siteSrvyType;

    public final SomDsImptOrdBean dsImptOrdBean;
    
    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public SomDsImptOrdSiteSrvyBean(NWAB412001Constant.SITE_SRVY_TYPE siteSrvyType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.siteSrvyType = siteSrvyType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        dsImptOrdConfigBean.dsImptOrdSiteSrvyBean = this;
    }

    public SomDsImptOrdSiteSrvyBean(NWAB412001Constant.SITE_SRVY_TYPE siteSrvyType, SomDsImptOrdBean dsImptOrdBean) {
        super();

        this.siteSrvyType = siteSrvyType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = null;
        dsImptOrdBean.dsImptOrdSiteSrvyBean = this;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SITE_SRVY_SQ));
        if (NWAB412001Constant.SITE_SRVY_TYPE.HEADER_DELY_INFO.equals(this.siteSrvyType)) {
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

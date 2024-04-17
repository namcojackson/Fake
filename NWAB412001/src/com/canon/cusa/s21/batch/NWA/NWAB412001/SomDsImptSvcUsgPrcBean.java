/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.util.Map;

import business.db.DS_IMPT_SVC_USG_PRCTMsg;
import business.db.NWAI4120_18TMsg;
import business.db.NWAI4120_16TMsg;
import java.math.BigDecimal;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcUsgPrcBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 07/21/2017   SRAA            K.Aratani       Update          QC#19993
 * 08/17/2017   SRAA            K.Aratani       Update          QC#19968
 *</pre>
 */
public class SomDsImptSvcUsgPrcBean extends DS_IMPT_SVC_USG_PRCTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SVC_USG_PRC_TYPE svcUsgPrcType;

    public SomDsImptSvcPrcBean dsImptSvcPrcBean;

    public final NWAI4120_16TMsg nwai4120_16;

    public final NWAI4120_18TMsg nwai4120_18;

    public Map<String, Object> prcMtrPkgMtrStru;

    public SomDsImptSvcUsgPrcBean(NWAB412001Constant.SVC_USG_PRC_TYPE svcUsgPrcType, SomDsImptSvcPrcBean dsImptSvcPrcBean, 
            NWAI4120_18TMsg nwai4120_18, Map<String, Object> prcMtrPkgMtrStru, NWAI4120_16TMsg nwai4120_16) {
        super();

        this.svcUsgPrcType = svcUsgPrcType;
        this.nwai4120_16 = nwai4120_16;
        this.nwai4120_18 = nwai4120_18;
        this.prcMtrPkgMtrStru = prcMtrPkgMtrStru;
        this.dsImptSvcPrcBean = dsImptSvcPrcBean;

        dsImptSvcPrcBean.dsImptSvcUsgPrcBeanList.add(this);
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcUsgPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_USG_PRC_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcPrcBean.dsImptSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.dsImptSvcPrcBean.dsImptSvcPrcPk);

        if (S21StringUtil.isEquals(this.dsImptSvcPrcBean.dsImptSvcDtlBean.dsContrCatgCd.getValue(), DS_CONTR_CATG.FLEET)) {

            this.prcListBandCd.clear();
            this.prcBookMdseCd.clear();
        } else {

            ZYPEZDItemValueSetter.setValue(this.prcListBandCd, this.nwai4120_18.somBandCd);
            ZYPEZDItemValueSetter.setValue(this.prcBookMdseCd, this.nwai4120_18.somSvcItemCd);
        }

        if (prcMtrPkgMtrStru != null) {

            ZYPEZDItemValueSetter.setValue(this.bllgMtrLbCd, (String) prcMtrPkgMtrStru.get("BLLG_MTR_LB_CD"));
        }

        if (NWAB412001Constant.SVC_USG_PRC_TYPE.BILLABLE.equals(this.svcUsgPrcType)) {

            if (prcMtrPkgMtrStru != null) {

                ZYPEZDItemValueSetter.setValue(this.usgMdseCd, (String) prcMtrPkgMtrStru.get("INTG_MDSE_CD"));
            }

            this.regMtrLbCd.clear();

            if (prcMtrPkgMtrStru != null) {
                if (S21StringUtil.isEquals(this.dsImptSvcPrcBean.dsImptSvcDtlBean.dsContrCatgCd.getValue(), DS_CONTR_CATG.FLEET)) {

                    BigDecimal volCnt = BigDecimal.ZERO;
                    if (ZYPConstant.FLG_ON_Y.equals((String) prcMtrPkgMtrStru.get("BW_MTR_FLG"))) {
                        if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetVolBwCnt)) {
                            volCnt = volCnt.add(this.nwai4120_16.fleetVolBwCnt.getValue());
                        }
                    } else if (ZYPConstant.FLG_ON_Y.equals((String) prcMtrPkgMtrStru.get("COLOR_MTR_FLG"))) {
                        if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetVolClrCnt)) {
                            volCnt = volCnt.add(this.nwai4120_16.fleetVolClrCnt.getValue());
                        }
                    } else if (ZYPConstant.FLG_ON_Y.equals((String) prcMtrPkgMtrStru.get("TOT_MTR_FLG"))) {
                        if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetVolBwCnt)) {
                            volCnt = volCnt.add(this.nwai4120_16.fleetVolBwCnt.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetVolClrCnt)) {
                            volCnt = volCnt.add(this.nwai4120_16.fleetVolClrCnt.getValue());
                        }
                    } else {
                        if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetVolBwCnt)) {
                            volCnt = volCnt.add(this.nwai4120_16.fleetVolBwCnt.getValue());
                        }
                    }
                    //QC#19993
                    BigDecimal mlyCopyInclPrcQty = BigDecimal.ZERO;
                    if (ZYPCommonFunc.hasValue(this.dsImptSvcPrcBean.dsImptSvcDtlBean.getUsgBllgCycleMthAot())) {
                        mlyCopyInclPrcQty = volCnt.multiply(this.dsImptSvcPrcBean.dsImptSvcDtlBean.getUsgBllgCycleMthAot());
                    }
                    //ZYPEZDItemValueSetter.setValue(this.mlyCopyInclPrcQty, volCnt);
                    ZYPEZDItemValueSetter.setValue(this.mlyCopyInclPrcQty, mlyCopyInclPrcQty);
                    
                } else {
                    
                    //QC#19993
                    BigDecimal mlyCopyInclPrcQty = BigDecimal.ZERO;
                    if (ZYPCommonFunc.hasValue(this.nwai4120_18.somVolTotSrvCnt) && ZYPCommonFunc.hasValue(this.dsImptSvcPrcBean.dsImptSvcDtlBean.getUsgBllgCycleMthAot())) {
                        mlyCopyInclPrcQty = this.nwai4120_18.somSrvVolComitCnt.getValue().multiply(this.dsImptSvcPrcBean.dsImptSvcDtlBean.getUsgBllgCycleMthAot());
                    }
                    //ZYPEZDItemValueSetter.setValue(this.mlyCopyInclPrcQty, this.nwai4120_18.somVolTotSrvCnt);
                    //ZYPEZDItemValueSetter.setValue(this.mlyCopyInclPrcQty, this.nwai4120_18.somSrvVolComitCnt);
                    ZYPEZDItemValueSetter.setValue(this.mlyCopyInclPrcQty, mlyCopyInclPrcQty);
                    
                }
            }

            if (S21StringUtil.isEquals(this.dsImptSvcPrcBean.dsImptSvcDtlBean.dsContrCatgCd.getValue(), DS_CONTR_CATG.FLEET)) {
                BigDecimal cpcCnt = BigDecimal.ZERO;
                if (prcMtrPkgMtrStru != null) {
	                if (ZYPConstant.FLG_ON_Y.equals((String) prcMtrPkgMtrStru.get("BW_MTR_FLG"))) {
	                    if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetBwCpcCnt)) {
	                        cpcCnt = cpcCnt.add(this.nwai4120_16.fleetBwCpcCnt.getValue());
	                    }
	                } else if (ZYPConstant.FLG_ON_Y.equals((String) prcMtrPkgMtrStru.get("COLOR_MTR_FLG"))) {
	                    if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetClrCpcCnt)) {
	                        cpcCnt = cpcCnt.add(this.nwai4120_16.fleetClrCpcCnt.getValue());
	                    }
	                } else if (ZYPConstant.FLG_ON_Y.equals((String) prcMtrPkgMtrStru.get("TOT_MTR_FLG"))) {
	                    if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetBwCpcCnt)) {
	                        cpcCnt = cpcCnt.add(this.nwai4120_16.fleetBwCpcCnt.getValue());
	                    }
	                    if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetClrCpcCnt)) {
	                        cpcCnt = cpcCnt.add(this.nwai4120_16.fleetClrCpcCnt.getValue());
	                    }
	                }
                }
                ZYPEZDItemValueSetter.setValue(this.xsMtrAmtRate, cpcCnt);

            } else {
                ZYPEZDItemValueSetter.setValue(this.xsMtrAmtRate, this.nwai4120_18.reqCpcCnt);
            }
            this.contrMtrMultRate.clear();

        } else if (NWAB412001Constant.SVC_USG_PRC_TYPE.PHYSICAL.equals(this.svcUsgPrcType)) {

            this.usgMdseCd.clear();
            if (prcMtrPkgMtrStru != null) {

                ZYPEZDItemValueSetter.setValue(this.regMtrLbCd, (String) prcMtrPkgMtrStru.get("REG_MTR_LB_CD"));
            }
            this.mlyCopyInclPrcQty.clear();
            this.xsMtrAmtRate.clear();
            ZYPEZDItemValueSetter.setValue(this.contrMtrMultRate, this.nwai4120_18.somMultCnt);
        }

        //QC#19968
        //Account#
        ZYPEZDItemValueSetter.setValue(this.billToCustCd, this.dsImptSvcPrcBean.billToCustCd);
        
        //Bill To Customer Code
        ZYPEZDItemValueSetter.setValue(this.billToLocNum, this.dsImptSvcPrcBean.billToLocNum);


        this.prcSvcTierTpCd.clear();
        this.minCopyVolCnt.clear();
        this.maxCopyVolCnt.clear();

        return true;
    }
}

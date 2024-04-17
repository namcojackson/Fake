/*
 * <pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;
import java.util.Map;

import business.db.DS_IMPT_SVC_USG_PRCTMsg;
import business.db.NWAI4150_16TMsg;
import business.db.NWAI4150_18TMsg;
import business.db.NWAI4150_22TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface Batch
 * EopsDsImptSvcUsgPrcBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class EopsDsImptSvcUsgPrcBean extends DS_IMPT_SVC_USG_PRCTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.SVC_USG_PRC_TYPE svcUsgPrcType;

    /**  */
    public EopsDsImptSvcPrcBean dsImptSvcPrcBean;

    /**  */
    public final NWAI4150_16TMsg nwai4150_16;

    /**  */
    public final NWAI4150_18TMsg nwai4150_18;

    /**  */
    public final NWAI4150_22TMsg nwai4150_22;

    /**  */
    public Map<String, Object> prcMtrPkgMtrStru;

    /**
     * EopsDsImptSvcUsgPrcBean
     * @param svcUsgPrcType     NWAB415001Constant.SVC_USG_PRC_TYPE
     * @param dsImptSvcPrcBean  EopsDsImptSvcPrcBean
     * @param nwai415018        NWAI4150_18TMsg
     * @param prcMtrPkgMtrStru  Map<String, Object>
     * @param nwai415016        NWAI4150_16TMsg
     * @param nwai415022        NWAI4150_22TMsg
     */
    public EopsDsImptSvcUsgPrcBean(//
            NWAB415001Constant.SVC_USG_PRC_TYPE svcUsgPrcType //
            , EopsDsImptSvcPrcBean dsImptSvcPrcBean //
            , NWAI4150_18TMsg nwai415018 //
            , Map<String, Object> prcMtrPkgMtrStru //
            , NWAI4150_16TMsg nwai415016 //
            , NWAI4150_22TMsg nwai415022) {
        super();

        this.svcUsgPrcType = svcUsgPrcType;
        this.nwai4150_18 = nwai415018;
        this.nwai4150_16 = nwai415016;
        this.nwai4150_22 = nwai415022;
        this.prcMtrPkgMtrStru = prcMtrPkgMtrStru;
        this.dsImptSvcPrcBean = dsImptSvcPrcBean;

        dsImptSvcPrcBean.dsImptSvcUsgPrcBeanList.add(this);
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
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcUsgPrcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_USG_PRC_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcPrcBean.dsImptSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcPrcPk, this.dsImptSvcPrcBean.dsImptSvcPrcPk);

        if (S21StringUtil.isEquals(//
                this.dsImptSvcPrcBean.dsImptSvcDtlBean.dsContrCatgCd.getValue(), DS_CONTR_CATG.FLEET)) {
            this.prcListBandCd.clear();
            this.prcBookMdseCd.clear();
        } else {

            ZYPEZDItemValueSetter.setValue(this.prcListBandCd, this.nwai4150_18.somBandCd);
            ZYPEZDItemValueSetter.setValue(this.prcBookMdseCd, this.nwai4150_18.somSvcItemCd);
        }

        if (prcMtrPkgMtrStru != null) {

            ZYPEZDItemValueSetter.setValue(this.bllgMtrLbCd, (String) prcMtrPkgMtrStru.get("BLLG_MTR_LB_CD"));
        }

        if (NWAB415001Constant.SVC_USG_PRC_TYPE.BILLABLE.equals(this.svcUsgPrcType)) {

            if (prcMtrPkgMtrStru != null) {

                ZYPEZDItemValueSetter.setValue(this.usgMdseCd, (String) prcMtrPkgMtrStru.get("INTG_MDSE_CD"));
            }

            this.regMtrLbCd.clear();

            ZYPEZDItemValueSetter.setValue(this.mlyCopyInclPrcQty, new BigDecimal(this.nwai4150_22.somSrvVolComitTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(this.xsMtrAmtRate, new BigDecimal(this.nwai4150_22.reqCpcTxt.getValue()));
            this.contrMtrMultRate.clear();

        } else if (NWAB415001Constant.SVC_USG_PRC_TYPE.PHYSICAL.equals(this.svcUsgPrcType)) {

            this.usgMdseCd.clear();
            if (prcMtrPkgMtrStru != null) {

                ZYPEZDItemValueSetter.setValue(this.regMtrLbCd, (String) prcMtrPkgMtrStru.get("REG_MTR_LB_CD"));
            }
            this.mlyCopyInclPrcQty.clear();
            this.xsMtrAmtRate.clear();
            ZYPEZDItemValueSetter.setValue(this.contrMtrMultRate, this.nwai4150_18.somMultCnt);
        }
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

/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.Map;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.db.DS_IMPT_SVC_ADDL_BASETMsg;
import business.db.NWAI4120_17TMsg;
import business.db.NWAI4120_21TMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcAddlBaseBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 04/18/2017   SRAA            K.Aratani       UPDATE          QC#15539-15
 * 09/27/2017   SRAA            K.Aratani       UPDATE          QC#21442
 *</pre>
 */
public class SomDsImptSvcAddlBaseBean extends DS_IMPT_SVC_ADDL_BASETMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SHELL_TYPE shellType;

    public final NWAB412001Constant.RENTAL_TYPE rentalType;

    public final SomDsImptSvcDtlBean dsImptSvcDtlBean;

    public final NWAI4120_17TMsg nwai4120_17;
    public final NWAI4120_21TMsg nwai4120_21;
    public String rentalPrcCatgCd;
    public final SomDsImptOrdConfigBean somDsImptOrdConfigBean;

    public SomDsImptSvcAddlBaseBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4120_17TMsg nwai4120_17, SomDsImptOrdConfigBean somDsImptOrdConfigBean) {
        super();

        this.shellType = shellType;
        this.rentalType = null;
        this.dsImptSvcDtlBean = dsImptSvcDtlBean;
        this.nwai4120_17 = nwai4120_17;
        this.nwai4120_21 = null;
        this.rentalPrcCatgCd = null;
        this.somDsImptOrdConfigBean = somDsImptOrdConfigBean;

        dsImptSvcDtlBean.dsImptSvcAddlBaseBeanList.add(this);
    }

    //QC#15539-14
    public SomDsImptSvcAddlBaseBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptSvcDtlBean dsImptSvcDtlBean, NWAI4120_21TMsg nwai4120_21, NWAB412001Constant.RENTAL_TYPE rentalType, SomDsImptOrdConfigBean somDsImptOrdConfigBean) {
        super();

        this.shellType = shellType;
        this.rentalType = rentalType;
        this.dsImptSvcDtlBean = dsImptSvcDtlBean;
        this.nwai4120_17 = null;
        this.nwai4120_21 = nwai4120_21;
        this.rentalPrcCatgCd = null;
        this.somDsImptOrdConfigBean = somDsImptOrdConfigBean;

        boolean addFlag = true;
        String needRentalAvailItemChk = (String)this.dsImptSvcDtlBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_NEED_RENTAL_AVAIL_ITEM_CHK.name());
        //QC#15539-15
        if (NWAB412001Constant.RENTAL_TYPE.EQUIPMENT.equals(this.rentalType) && ZYPCommonFunc.hasValue(nwai4120_21.somEquipPrcListNm)) {
            this.rentalPrcCatgCd = nwai4120_21.somEquipPrcListNm.getValue();
            //if (!dsImptSvcDtlBean.dsImptOrdBean.getCachePrcCatgNmMap().containsKey(nwai4120_21.somEquipPrcListNm.getValue())) {
            //    this.rentalPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptSvcDtlBean.dsImptOrdBean.glblCmpyCd.getValue(), nwai4120_21.somEquipPrcListNm.getValue());
            //}
            //If need to rental avail item check, else import screen has rental avail item check
            if (!ZYPCommonFunc.hasValue(needRentalAvailItemChk) || needRentalAvailItemChk.equals(ZYPConstant.FLG_OFF_N)) {
                Map<String, Object> availMap = NWXC412001.getAvailableRentalItem(this.dsImptSvcDtlBean.dsImptOrdBean.glblCmpyCd.getValue(), 
                        getMdseCdForRental(), null);
                //If INSTL_BASE_CTRL_FLG = 'Y', not add to shell
                if (availMap != null && availMap.get("MDSE_CD") != null) {
                    addFlag = true;
                } else {
                    addFlag = false;
                }
            }
        } else if (NWAB412001Constant.RENTAL_TYPE.SERVICE.equals(this.rentalType) && ZYPCommonFunc.hasValue(nwai4120_21.somSvcPrcListNm)){
            this.rentalPrcCatgCd = nwai4120_21.somSvcPrcListNm.getValue();
            //if (!dsImptSvcDtlBean.dsImptOrdBean.getCachePrcCatgNmMap().containsKey(nwai4120_21.somSvcPrcListNm.getValue())) {
            //    this.rentalPrcCatgCd = NWXC412001.getPrcCatgCdFromName(dsImptSvcDtlBean.dsImptOrdBean.glblCmpyCd.getValue(), nwai4120_21.somSvcPrcListNm.getValue());
            //}
            //If need to rental avail item check, else import screen has rental avail item check
            if (!ZYPCommonFunc.hasValue(needRentalAvailItemChk) || needRentalAvailItemChk.equals(ZYPConstant.FLG_OFF_N)) {
                Map<String, Object> availMap = NWXC412001.getAvailableRentalItem(this.dsImptSvcDtlBean.dsImptOrdBean.glblCmpyCd.getValue(), 
                        getMdseCdForRental(), new String[] {MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS });
                //If INSTL_BASE_CTRL_FLG = 'Y' AND COA_MDSE_TP_CD NOT IN ('10'), not add to shell
                if (availMap != null && availMap.get("MDSE_CD") != null) {
                    addFlag = true;
                } else {
                    addFlag = false;
                }
            }
        }
        if (addFlag) {
            dsImptSvcDtlBean.dsImptSvcAddlBaseBeanList.add(this);
        }
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcAddlBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_IMPT_SVC_ADDL_BASE_SQ"));
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, this.dsImptSvcDtlBean.dsImptSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdPk);
        this.funcPrcListPrcAmt.clear();
        
        //QC#15539-14
        //QC#15539-15
        //Accessory Base Only
        if (this.nwai4120_17 != null) {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, getDsImptOrdDtlPk());
            ZYPEZDItemValueSetter.setValue(this.addlBasePrcCatgCd, this.nwai4120_17.prcCatgCd);
            if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
                ZYPEZDItemValueSetter.setValue(this.addlBasePrcDealAmt, this.nwai4120_17.reqBaseAmt);
                ZYPEZDItemValueSetter.setValue(this.addlBasePrcFuncAmt, this.nwai4120_17.reqBaseAmt);
                ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_17.reqBaseAmt);
            } else {
                if (this.dsImptSvcDtlBean.nwai4120_19 != null && ZYPCommonFunc.hasValue(this.dsImptSvcDtlBean.nwai4120_19.somDurnId)) {
                    BigDecimal prcAmt = this.nwai4120_17.reqBaseAmt.getValue().divide(this.dsImptSvcDtlBean.nwai4120_19.somDurnId.getValue(), 4, BigDecimal.ROUND_HALF_UP);
                    ZYPEZDItemValueSetter.setValue(this.addlBasePrcDealAmt, prcAmt);
                    ZYPEZDItemValueSetter.setValue(this.addlBasePrcFuncAmt, prcAmt);
                    ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, prcAmt);
                }
            }

            //QC#21442
            //this.svcPrcCatgCd.clear();
            ZYPEZDItemValueSetter.setValue(this.svcPrcCatgCd, SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
        //Rental
        } else {
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, getDsImptOrdDtlPkForRental());
            ZYPEZDItemValueSetter.setValue(this.addlBasePrcCatgCd, this.rentalPrcCatgCd);
            //Copier
            if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
                //Rental Equipment Base
                if (NWAB412001Constant.RENTAL_TYPE.EQUIPMENT.equals(this.rentalType)) {
                    ZYPEZDItemValueSetter.setValue(this.addlBasePrcDealAmt, this.nwai4120_21.somEquipBaseAmt);
                    ZYPEZDItemValueSetter.setValue(this.addlBasePrcFuncAmt, this.nwai4120_21.somEquipBaseAmt);
                    ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_21.somEquipBaseAmt);
                //Rental Service Base
                } else {
                    ZYPEZDItemValueSetter.setValue(this.addlBasePrcDealAmt, this.nwai4120_21.somSvcBaseAmt);
                    ZYPEZDItemValueSetter.setValue(this.addlBasePrcFuncAmt, this.nwai4120_21.somSvcBaseAmt);
                    ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_21.somSvcBaseAmt);
                }
            //Non Copier
            } else {
                if (this.dsImptSvcDtlBean.nwai4120_19 != null && ZYPCommonFunc.hasValue(this.dsImptSvcDtlBean.nwai4120_19.somDurnId)) {
                    //Rental Equipment Base
                    if (NWAB412001Constant.RENTAL_TYPE.EQUIPMENT.equals(this.rentalType)) {
                        BigDecimal prcAmt = this.nwai4120_21.somEquipBaseAmt.getValue().divide(this.dsImptSvcDtlBean.nwai4120_19.somDurnId.getValue(), 4, BigDecimal.ROUND_HALF_UP);
                        ZYPEZDItemValueSetter.setValue(this.addlBasePrcDealAmt, prcAmt);
                        ZYPEZDItemValueSetter.setValue(this.addlBasePrcFuncAmt, prcAmt);
                        ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, prcAmt);
                    //Rental Service Base
                    } else {
                        BigDecimal prcAmt = this.nwai4120_21.somSvcBaseAmt.getValue().divide(this.dsImptSvcDtlBean.nwai4120_19.somDurnId.getValue(), 4, BigDecimal.ROUND_HALF_UP);
                        ZYPEZDItemValueSetter.setValue(this.addlBasePrcDealAmt, prcAmt);
                        ZYPEZDItemValueSetter.setValue(this.addlBasePrcFuncAmt, prcAmt);
                        ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, prcAmt);
                    }
                }
            }
            if (NWAB412001Constant.RENTAL_TYPE.EQUIPMENT.equals(this.rentalType)) {
                ZYPEZDItemValueSetter.setValue(this.svcPrcCatgCd, SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
            } else {
                ZYPEZDItemValueSetter.setValue(this.svcPrcCatgCd, SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
            }
        }

        return true;
    }

    private BigDecimal getDsImptOrdDtlPk() {

        String somSvcItemCd = this.nwai4120_17.somSvcItemCd.getValue();

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return null;
        }

        
        for (SomDsImptOrdDtlBean detail : this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdDtlBeanList) {

            if (detail.dsImptOrdConfigPk.getValue().compareTo(this.somDsImptOrdConfigBean.dsImptOrdConfigPk.getValue()) == 0
                    && S21StringUtil.isEquals(detail.mdseCd.getValue(), somSvcItemCd)) {

            //if (S21StringUtil.isEquals(detail.mdseCd.getValue(), somSvcItemCd)) {
                return detail.dsImptOrdDtlPk.getValue();
            }
        }

        // invalid case
        return null;
    }
    
    //QC#15539-14
    private BigDecimal getDsImptOrdDtlPkForRental() {
        
        BigDecimal rentalConfigId = this.nwai4120_21.somConfigId.getValue();
        BigDecimal rentalSortNum = this.nwai4120_21.somConfigLineId.getValue();

        for (SomDsImptOrdDtlBean detail : this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdDtlBeanList) {

            BigDecimal lineConfigId = detail.nwai4120_10.somConfigId.getValue();
            BigDecimal lineSortNum = detail.nwai4120_10.somSortOrdNum.getValue();
            if (detail.dsImptOrdConfigPk.getValue().compareTo(this.somDsImptOrdConfigBean.dsImptOrdConfigPk.getValue()) == 0
                    && rentalConfigId != null && rentalSortNum != null && lineConfigId != null && lineSortNum != null
                    && rentalConfigId.compareTo(lineConfigId) == 0
                    && rentalSortNum.compareTo(lineSortNum) == 0) {

                return detail.dsImptOrdDtlPk.getValue();
            }
        }

        // invalid case
        return null;
    }
    
    private String getMdseCdForRental() {
        
        BigDecimal rentalConfigId = this.nwai4120_21.somConfigId.getValue();
        BigDecimal rentalSortNum = this.nwai4120_21.somConfigLineId.getValue();

        for (SomDsImptOrdDtlBean detail : this.dsImptSvcDtlBean.dsImptOrdBean.dsImptOrdDtlBeanList) {

            BigDecimal lineConfigId = detail.nwai4120_10.somConfigId.getValue();
            BigDecimal lineSortNum = detail.nwai4120_10.somSortOrdNum.getValue();
            if (rentalConfigId != null && rentalSortNum != null && lineConfigId != null && lineSortNum != null
                    && rentalConfigId.compareTo(lineConfigId) == 0
                    && rentalSortNum.compareTo(lineSortNum) == 0) {

                return detail.nwai4120_10.somMercCd.getValue();
            }
        }

        // invalid case
        return null;
    }
}

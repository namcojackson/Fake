/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.NWAI4120_01TMsg;
import business.db.NWAI4120_05TMsg;
import business.db.NWAI4120_06TMsg;
import business.db.NWAI4120_12TMsg;
import business.db.NWAI4120_14TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdCtacPsnBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 08/23/2017   SRAA            K.Aratani       Update          QC#20755
 * 03/04/2019   FUJITSU         Mz.Takahashi    Update          QC#30562
 *</pre>
 */
public class SomDsImptOrdCtacPsnBean extends DS_IMPT_ORD_CTAC_PSNTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.CTAC_PSN_TYPE ctacPsnType;

    public final NWAB412001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType;

    public final SomDsImptOrdBean dsImptOrdBean;

    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public NWAI4120_01TMsg nwai4120_01;

    public NWAI4120_05TMsg nwai4120_05;

    public NWAI4120_06TMsg nwai4120_06;

    public NWAI4120_12TMsg nwai4120_12;

    public NWAI4120_14TMsg nwai4120_14;

    public SomDsImptOrdCtacPsnBean(NWAB412001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB412001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, SomDsImptOrdBean dsImptOrdBean) {
        this(ctacPsnType, ctacPsnSubType, dsImptOrdBean, null);

        dsImptOrdBean.dsImptOrdCtacPsnBeanList.add(this);
        this.nwai4120_01 = dsImptOrdBean.nwai4120_01;
    }

    public SomDsImptOrdCtacPsnBean(NWAB412001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB412001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        this(ctacPsnType, ctacPsnSubType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        dsImptOrdConfigBean.dsImptOrdCtacPsnBeanList.add(this);

        this.nwai4120_05 = dsImptOrdConfigBean.nwai4120_05;
        if (dsImptOrdConfigBean.noSerIf06TMsg != null) {
            this.nwai4120_06 = dsImptOrdConfigBean.noSerIf06TMsg;

            if (this.nwai4120_06 == null) {

                this.nwai4120_06 = new NWAI4120_06TMsg();
            }
        }
        this.nwai4120_12 = dsImptOrdConfigBean.nwai4120_12;
    }

    public SomDsImptOrdCtacPsnBean(NWAB412001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB412001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_14TMsg nwai4120_14) {
        this(ctacPsnType, ctacPsnSubType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        dsImptOrdConfigBean.dsImptOrdCtacPsnBeanList.add(this);
        this.nwai4120_14 = nwai4120_14;
    }

    private SomDsImptOrdCtacPsnBean(NWAB412001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB412001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, SomDsImptOrdBean dsImptOrdBean, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.ctacPsnType = ctacPsnType;
        this.ctacPsnSubType = ctacPsnSubType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        if (NWAB412001Constant.CTAC_PSN_TYPE.HEADER.equals(ctacPsnType)) {

            createHeaderCtacPsn(glblCmpyCd, salesDate);

        } else if (NWAB412001Constant.CTAC_PSN_TYPE.REGULAR_SHIP_CONFIG.equals(ctacPsnType)) {

            createConfigCtacPsn(glblCmpyCd, salesDate);

        } else if (NWAB412001Constant.CTAC_PSN_TYPE.OCE_PROMOTION_RMA.equals(ctacPsnType)) {

            createPromotionRmaCtacPsn(glblCmpyCd, salesDate);
        } else {

            createRmaCtacPsn(glblCmpyCd, salesDate);
        }
        return true;
    }

    private void createHeaderCtacPsn(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        this.dsImptOrdConfigPk.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnPk.clear();
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), ctacPsnSubType.name()));

        if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_DELIVERY.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, S21StringUtil.subStringByLength(this.nwai4120_01.delyFirstNm.getValue(), 0, 30));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, S21StringUtil.subStringByLength(this.nwai4120_01.delyLastNm.getValue(), 0, 30));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, S21StringUtil.subStringByLength(this.nwai4120_01.delyEmlTxt.getValue(), 0, 320));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, S21StringUtil.subStringByLength(this.nwai4120_01.delyPhoExtTxt.getValue(), 0, 10));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_01.delyPhoTxt.getValue(), 0, 20));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFaxNum, S21StringUtil.subStringByLength(this.nwai4120_01.delyFaxTxt.getValue(), 0, 20));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_01.hrsOpTxt.getValue(), this.nwai4120_01.delyInstnTxt.getValue(), null));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_BILLING.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_01.billToFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_01.billToLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_01.billToEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_01.billToPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_01.billToPhoTxt.getValue(), 0, 20));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFaxNum, this.nwai4120_01.billToFaxTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_01.hrsOpTxt.getValue(), this.nwai4120_01.delyInstnTxt.getValue(), null));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_IT.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_01.itFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_01.itLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_01.itEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_01.itPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_01.itPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_01.hrsOpTxt.getValue(), this.nwai4120_01.delyInstnTxt.getValue(), null));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_SUBSERVICES.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_01.subSvcCtacFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_01.subSvcCtacLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_01.subSvcCtacEmlTxt);
            this.ctacPsnExtnNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_01.subSvcCtacPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_01.hrsOpTxt.getValue(), this.nwai4120_01.delyInstnTxt.getValue(), null));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_EMANAGE_USER.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_01.csaPmtFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_01.csaPmtLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_01.csaPmtEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_01.csaPmtPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_01.csaPmtPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_01.hrsOpTxt.getValue(), this.nwai4120_01.delyInstnTxt.getValue(), null));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_EMANAGE_ADMIN.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_01.csaPmtFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_01.csaPmtLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_01.csaPmtEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_01.csaPmtPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_01.csaPmtPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_01.hrsOpTxt.getValue(), this.nwai4120_01.delyInstnTxt.getValue(), null));

        } else {

            // none
        }
    }

    private void createConfigCtacPsn(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnPk.clear();
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), ctacPsnSubType.name()));

        //QC#20755
        boolean noConfigFlg = false;
        if (!ZYPCommonFunc.hasValue(this.nwai4120_12.primCtacFirstNm) 
                && !ZYPCommonFunc.hasValue(this.nwai4120_12.scdCtacFirstNm) 
                && !ZYPCommonFunc.hasValue(this.nwai4120_12.csaPmtFirstNm)) {
            noConfigFlg = true;
        }

        if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_PRIMARY.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_12.primCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_12.primCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_12.primEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_12.primPhoExtTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_12.primPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_12.hrsOpTxt.getValue(), this.nwai4120_12.somInstnTxt.getValue(), null));
            }
            

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_SECONDARY.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_12.scdCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_12.scdCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_12.scdEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_12.scdPhoExtTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_12.scdPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_12.hrsOpTxt.getValue(), this.nwai4120_12.somInstnTxt.getValue(), null));
            }

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_USER.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_12.csaPmtFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_12.csaPmtLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_12.csaPmtEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_12.csaPmtPhoExtDescTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_12.csaPmtPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_12.hrsOpTxt.getValue(), this.nwai4120_12.somInstnTxt.getValue(), null));
            }

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_ADMIN.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_12.csaPmtFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_12.csaPmtLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_12.csaPmtEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_12.csaPmtPhoExtDescTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_12.csaPmtPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_12.hrsOpTxt.getValue(), this.nwai4120_12.somInstnTxt.getValue(), null));
            }

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_PICKUP.equals(ctacPsnSubType)) {

            // invalid case
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_06.pickUpCtacFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_06.pickUpCtacLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_06.pickUpEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_06.pickUpPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_06.pickUpPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_12.hrsOpTxt.getValue(), this.nwai4120_12.somInstnTxt.getValue(), null));

        //QC#23965
        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.H_SUBSERVICES.equals(ctacPsnSubType)) {

            // invalid case
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.subSvcCtacFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.subSvcCtacLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.subSvcCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.subSvcCtacPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_12.hrsOpTxt.getValue(), this.nwai4120_12.somInstnTxt.getValue(), null));

        } else {

            // none
        }
    }

    private void createPromotionRmaCtacPsn(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnPk.clear();
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), ctacPsnSubType.name()));

        ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_14.pickUpCtacFirstNm);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_14.pickUpCtacLastNm);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_14.pickUpEmlTxt);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_14.pickUpPhoExtTxt);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_14.pickUpPhoTxt.getValue(), 0, 20));
        this.ctacPsnFaxNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, this.nwai4120_14.somRmvInstnTxt);
    }

    private void createRmaCtacPsn(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnPk.clear();
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), ctacPsnSubType.name()));

        //QC#20755
        boolean noConfigFlg = false;
        if (!ZYPCommonFunc.hasValue(this.nwai4120_05.primCtacFirstNm) 
                && !ZYPCommonFunc.hasValue(this.nwai4120_05.scdCtacFirstNm) 
                && !ZYPCommonFunc.hasValue(this.nwai4120_06.pickUpCtacFirstNm)) {
            noConfigFlg = true;
        }
        
        if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_PRIMARY.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_05.primCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_05.primCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_05.primEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_05.primPhoExtTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_05.primPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_05.hrsOpTxt.getValue(), null, this.nwai4120_06.rmvInstnTxt.getValue()));
            }

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_SECONDARY.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_05.scdCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_05.scdCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_05.scdEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_05.scdPhoExtTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_05.scdPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_05.hrsOpTxt.getValue(), null, this.nwai4120_06.rmvInstnTxt.getValue()));
            }

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_USER.equals(ctacPsnSubType)) {

            // invalid case
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_12.csaPmtFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_12.csaPmtLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_12.csaPmtEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_12.csaPmtPhoExtDescTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_12.csaPmtPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_05.hrsOpTxt.getValue(), null, this.nwai4120_06.rmvInstnTxt.getValue()));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_ADMIN.equals(ctacPsnSubType)) {

            // invalid case
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_12.csaPmtFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_12.csaPmtLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_12.csaPmtEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_12.csaPmtPhoExtDescTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_12.csaPmtPhoTxt.getValue(), 0, 20));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_05.hrsOpTxt.getValue(), null, this.nwai4120_06.rmvInstnTxt.getValue()));

        } else if (NWAB412001Constant.CTAC_PSN_SUB_TYPE.C_PICKUP.equals(ctacPsnSubType)) {

            //QC#20755
            SomDsImptOrdCtacPsnBean headerCtacBean = null;
            if (this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size() > 0) {
                for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                    SomDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                    if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) 
                            && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd)
                            && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                        headerCtacBean = ctacBean;
                        break;
                    }
                }
            }
            
            if (noConfigFlg && headerCtacBean != null) {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, headerCtacBean.ctacPsnFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, headerCtacBean.ctacPsnLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, headerCtacBean.ctacPsnEmlAddr);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, headerCtacBean.ctacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, headerCtacBean.ctacPsnTelNum);
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, headerCtacBean.ctacPsnCmntTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai4120_06.pickUpCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai4120_06.pickUpCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai4120_06.pickUpEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai4120_06.pickUpPhoExtTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai4120_06.pickUpPhoTxt.getValue(), 0, 20));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai4120_05.hrsOpTxt.getValue(), null, this.nwai4120_06.rmvInstnTxt.getValue()));
            }

        } else {

            // none
        }
    }

    private String creteCtacPsnCmntTxt(String hrsOptTxt, String delyInstnTxt, String rmvInstnTxt) {
        //2019/03/04 QC#30562 Mod Start
        String ret = "";

        if (S21StringUtil.isNotEmpty(hrsOptTxt)) {
            ret = String.format(NWAB412001Constant.FORMAT_CTAC_PSN_CMNT_TXT, delyInstnTxt, hrsOptTxt);
        } else if (S21StringUtil.isNotEmpty(rmvInstnTxt)) {
            ret = String.format(NWAB412001Constant.FORMAT_CTAC_PSN_CMNT_TXT, delyInstnTxt, rmvInstnTxt);
        } else {
            ret = delyInstnTxt;
        }

        if (S21StringUtil.isNotEmpty(ret)){
            ret = ret.replaceAll("\r\n|\r|\n", "\r\n");
        }

        return S21StringUtil.subStringByLength(ret, 0, 1000);
        
        //if (S21StringUtil.isNotEmpty(hrsOptTxt)) {
        //
        //    return S21StringUtil.subStringByLength(String.format(NWAB412001Constant.FORMAT_CTAC_PSN_CMNT_TXT, delyInstnTxt, hrsOptTxt), 0, 1000);
        //
        //}

        //if (S21StringUtil.isNotEmpty(rmvInstnTxt)) {
        //
        //    return S21StringUtil.subStringByLength(String.format(NWAB412001Constant.FORMAT_CTAC_PSN_CMNT_TXT, delyInstnTxt, hrsOptTxt), 0, 1000);
        //}

        //return S21StringUtil.subStringByLength(delyInstnTxt, 0, 1000);
        //2019/03/04 QC#30562 Mod End
    }
}

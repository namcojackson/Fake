/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.NWAI4150_01TMsg;
import business.db.NWAI4150_05TMsg;
import business.db.NWAI4150_06TMsg;
import business.db.NWAI4150_12TMsg;
import business.db.NWAI4150_14TMsg;
import business.db.NWAI4150_24TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CTAC_PSN_SUB_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CTAC_PSN_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
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
 * EOPS Interface Batch EopsDsImptOrdCtacPsnBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 
 * </pre>
 */
public class EopsDsImptOrdCtacPsnBean extends DS_IMPT_ORD_CTAC_PSNTMsg implements IEopsDsImptOrd {

    /** default */
    private static final long serialVersionUID = 1L;

    /**  */
    public NWAI4150_01TMsg nwai415001;

    /**  */
    public NWAI4150_05TMsg nwai415005;

    /**  */
    public NWAI4150_06TMsg nwai415006;

    /**  */
    public NWAI4150_12TMsg nwai415012;

    /**  */
    public NWAI4150_14TMsg nwai415014;

    /**  */
    public NWAI4150_24TMsg nwai415024;

    /**  */
    private CTAC_PSN_TYPE ctacPsnType;

    /**  */
    private CTAC_PSN_SUB_TYPE ctacPsnSubType;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final EopsDsImptOrdConfigBean dsImptOrdConfigBean;

    /**
     * EopsDsImptOrdCtacPsnBean
     * @param ctacPsnType       NWAB415001Constant.CTAC_PSN_TYPE
     * @param ctacPsnSubType    NWAB415001Constant.CTAC_PSN_SUB_TYPE
     * @param dsImptOrdBean     EopsDsImptOrdBean
     */
    public EopsDsImptOrdCtacPsnBean(NWAB415001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB415001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, EopsDsImptOrdBean dsImptOrdBean) {
        this(ctacPsnType, ctacPsnSubType, dsImptOrdBean, null);

        dsImptOrdBean.dsImptOrdCtacPsnBeanList.add(this);
        this.nwai415001 = dsImptOrdBean.nwai415001;
    }

    /**
     * 
     * @param ctacPsnType           NWAB415001Constant.CTAC_PSN_TYPE
     * @param ctacPsnSubType        NWAB415001Constant.CTAC_PSN_SUB_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     */
    public EopsDsImptOrdCtacPsnBean(NWAB415001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB415001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        this(ctacPsnType, ctacPsnSubType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        dsImptOrdConfigBean.dsImptOrdCtacPsnBeanList.add(this);

        this.nwai415005 = dsImptOrdConfigBean.nwai415005;
        if (dsImptOrdConfigBean.noSerIf06TMsg != null) {
            this.nwai415006 = dsImptOrdConfigBean.noSerIf06TMsg;

            if (this.nwai415006 == null) {

                this.nwai415006 = new NWAI4150_06TMsg();
            }
        }
        this.nwai415012 = dsImptOrdConfigBean.nwai415012;
    }

    /**
     * EopsDsImptOrdCtacPsnBean
     * @param ctacPsnType           NWAB415001Constant.CTAC_PSN_TYPE
     * @param ctacPsnSubType        NWAB415001Constant.CTAC_PSN_SUB_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param nwai415014            NWAI4150_14TMsg
     */
    public EopsDsImptOrdCtacPsnBean(NWAB415001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB415001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, EopsDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4150_14TMsg nwai415014) {
        this(ctacPsnType, ctacPsnSubType, dsImptOrdConfigBean.dsImptOrdBean, dsImptOrdConfigBean);

        dsImptOrdConfigBean.dsImptOrdCtacPsnBeanList.add(this);
        this.nwai415014 = nwai415014;
    }

    private EopsDsImptOrdCtacPsnBean(NWAB415001Constant.CTAC_PSN_TYPE ctacPsnType, NWAB415001Constant.CTAC_PSN_SUB_TYPE ctacPsnSubType, EopsDsImptOrdBean dsImptOrdBean, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.ctacPsnType = ctacPsnType;
        this.ctacPsnSubType = ctacPsnSubType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        if (CTAC_PSN_TYPE.HEADER.equals(ctacPsnType)) {
            createHeaderCtacPsn(glblCmpyCd, salesDate);

        } else if (CTAC_PSN_TYPE.REGULAR_SHIP_CONFIG.equals(ctacPsnType)) {
            createConfigCtacPsn(glblCmpyCd, salesDate);

        } else if (CTAC_PSN_TYPE.OCE_PROMOTION_RMA.equals(ctacPsnType)) {
            createPromotionRmaCtacPsn(glblCmpyCd);

        } else if (CTAC_PSN_TYPE.EOPS_RMA.equals(ctacPsnType)) {
            createEopsRmaCtacPsn(glblCmpyCd);

        } else {
            createRmaCtacPsn(glblCmpyCd, salesDate);
        }
        return true;
    }

    private void createRmaCtacPsn(String glblCmpyCd, String salesDate) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), ctacPsnSubType.name()));
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        this.ctacPsnPk.clear();

        boolean noConfigFlg = false;
        if (!ZYPCommonFunc.hasValue(this.nwai415005.primEopsCtacFirstNm) //
                && !ZYPCommonFunc.hasValue(this.nwai415005.scdEopsCtacFirstNm) //
                && !ZYPCommonFunc.hasValue(this.nwai415006.pickUpFirstNm)) {
            noConfigFlg = true;
        }

        if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_PRIMARY.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnFirstNm //
                        , S21StringUtil.subStringByLength(this.nwai415005.primEopsCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnLastNm //
                        , S21StringUtil.subStringByLength(this.nwai415005.primEopsCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415005.eopsPrimEmlTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnExtnNum //
                        , S21StringUtil.subStringByLength(this.nwai415005.primPhoExtTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415005.primCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnCmntTxt //
                        , creteCtacPsnCmntTxt(this.nwai415005.hrsOpTxt.getValue(), null, this.nwai415006.rmvInstnTxt.getValue()));
            }

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_SECONDARY.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnFirstNm //
                        , S21StringUtil.subStringByLength(this.nwai415005.scdEopsCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnLastNm //
                        , S21StringUtil.subStringByLength(this.nwai415005.scdEopsCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415005.scdCtacEmlTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnExtnNum //
                        , S21StringUtil.subStringByLength(this.nwai415005.scdPhoExtTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415005.scdCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415005.hrsOpTxt.getValue(), null, this.nwai415006.rmvInstnTxt.getValue()));
            }

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_USER.equals(ctacPsnSubType)) {

            // invalid case
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415012.csaPmtCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnExtnNum //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtPhoExtDescTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415005.hrsOpTxt.getValue(), null, this.nwai415006.rmvInstnTxt.getValue()));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_ADMIN.equals(ctacPsnSubType)) {

            // invalid case
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415012.csaPmtCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnExtnNum //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtPhoExtDescTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt //
                    , creteCtacPsnCmntTxt(this.nwai415005.hrsOpTxt.getValue(), null, this.nwai415006.rmvInstnTxt.getValue()));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_PICKUP.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnFirstNm //
                        , S21StringUtil.subStringByLength(this.nwai415006.pickUpFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnLastNm //
                        , S21StringUtil.subStringByLength(this.nwai415006.pickUpLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415006.pickUpCtacEmlTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnExtnNum //
                        , S21StringUtil.subStringByLength(this.nwai415006.pickUpPhoExtTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415006.pickUpCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnCmntTxt //
                        , creteCtacPsnCmntTxt(this.nwai415005.hrsOpTxt.getValue(), null, this.nwai415006.rmvInstnTxt.getValue()));
            }
        }
    }

    private void createEopsRmaCtacPsn(String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), ctacPsnSubType.name()));
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        this.ctacPsnPk.clear();

        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnFirstNm //
                , S21StringUtil.subStringByLength(this.nwai415024.rtrnCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnLastNm //
                , S21StringUtil.subStringByLength(this.nwai415024.rtrnCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
        ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415024.rtrnPickUpEmlTxt);
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnExtnNum //
                , S21StringUtil.subStringByLength(this.nwai415024.rtrnPhoExtTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTelNum //
                , S21StringUtil.subStringByLength(this.nwai415024.pickUpCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
        this.ctacPsnFaxNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, this.nwai415024.eopsRtrnCmntTxt);
    }

    private void createPromotionRmaCtacPsn(String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), ctacPsnSubType.name()));
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        this.ctacPsnPk.clear();

        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnFirstNm //
                , S21StringUtil.subStringByLength(this.nwai415014.pickUpCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnLastNm //
                , S21StringUtil.subStringByLength(this.nwai415014.pickUpCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
        ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415014.pickUpEmlTxt);
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnExtnNum //
                , S21StringUtil.subStringByLength(this.nwai415014.pickUpPhoExtTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTelNum //
                , S21StringUtil.subStringByLength(this.nwai415014.pickUpPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
        this.ctacPsnFaxNum.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt, this.nwai415014.somRmvInstnTxt);
    }

    private void createConfigCtacPsn(String glblCmpyCd, String salesDate) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), ctacPsnSubType.name()));
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        this.ctacPsnPk.clear();

        boolean noConfigFlg = false;
        if (!ZYPCommonFunc.hasValue(this.nwai415012.primEopsCtacFirstNm) //
                && !ZYPCommonFunc.hasValue(this.nwai415012.scdEopsCtacFirstNm) //
                && !ZYPCommonFunc.hasValue(this.nwai415012.csaPmtCtacFirstNm)) {
            noConfigFlg = true;
        }

        if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_PRIMARY.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai415012.primEopsCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai415012.primEopsCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415012.primCtacEmlTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnExtnNum //
                        , S21StringUtil.subStringByLength(this.nwai415012.primPhoExtTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_EXTN_NUM.getLen()));
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415012.primCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(this.ctacPsnCmntTxt //
                        , creteCtacPsnCmntTxt(this.nwai415012.hrsOpTxt.getValue(), this.nwai415012.eopsInstnTxt.getValue(), null));
            }

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_SECONDARY.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai415012.scdEopsCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai415012.scdEopsCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415012.scdCtacEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai415012.scdPhoExtTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415012.scdCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415012.hrsOpTxt.getValue(), this.nwai415012.eopsInstnTxt.getValue(), null));
            }

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_USER.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai415012.csaPmtCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai415012.csaPmtCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415012.csaPmtCtacEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai415012.csaPmtPhoExtDescTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415012.hrsOpTxt.getValue(), this.nwai415012.eopsInstnTxt.getValue(), null));
            }

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_EMANAGE_ADMIN.equals(ctacPsnSubType)) {

            EopsDsImptOrdCtacPsnBean headerCtacBean = null;
            for (int i = 0; i < this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.size(); i++) {
                EopsDsImptOrdCtacPsnBean ctacBean = this.dsImptOrdBean.dsImptOrdCtacPsnBeanList.get(i);
                if (ZYPCommonFunc.hasValue(this.ctacPsnTpCd) //
                        && ZYPCommonFunc.hasValue(ctacBean.ctacPsnTpCd) //
                        && this.ctacPsnTpCd.getValue().equals(ctacBean.ctacPsnTpCd.getValue())) {
                    headerCtacBean = ctacBean;
                    break;
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
                ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai415012.csaPmtCtacFirstNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai415012.csaPmtCtacLastNm);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415012.csaPmtCtacEmlTxt);
                ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai415012.csaPmtPhoExtDescTxt);
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnTelNum //
                        , S21StringUtil.subStringByLength(this.nwai415012.csaPmtCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
                this.ctacPsnFaxNum.clear();
                ZYPEZDItemValueSetter.setValue(//
                        this.ctacPsnCmntTxt //
                        , creteCtacPsnCmntTxt(this.nwai415012.hrsOpTxt.getValue(), this.nwai415012.eopsInstnTxt.getValue(), null));
            }

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.C_PICKUP.equals(ctacPsnSubType)) {
            // invalid case
            ZYPEZDItemValueSetter.setValue(this.ctacPsnFirstNm, this.nwai415006.pickUpFirstNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnLastNm, this.nwai415006.pickUpLastNm);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415006.pickUpCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai415006.pickUpPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415006.pickUpCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt //
                    , creteCtacPsnCmntTxt(this.nwai415012.hrsOpTxt.getValue(), this.nwai415012.eopsInstnTxt.getValue(), null));

        }
    }

    private void createHeaderCtacPsn(String glblCmpyCd, String salesDate) {
        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CTAC_PSN_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        this.dsImptOrdConfigPk.clear();
        ZYPEZDItemValueSetter.setValue(this.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
        this.ctacPsnMidNm.clear();
        this.ctacPsnCellPhoNum.clear();
        this.ctacPsnPk.clear();
        ZYPEZDItemValueSetter.setValue(//
                this.ctacPsnTpCd, NWXC412001.getCtacPsnTpCd(glblCmpyCd, CPO_SRC_TP.EOPS, NWAB415001Constant.INTERFACE_ID.NWAI4150.name(), ctacPsnSubType.name()));

        if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.H_DELIVERY.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.delyCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.delyCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnEmlAddr, this.nwai415001.delyCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnExtnNum, this.nwai415001.delyCtacPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum, S21StringUtil.subStringByLength(this.nwai415001.delyCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFaxNum, S21StringUtil.subStringByLength(this.nwai415001.delyFaxTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FAX_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415001.hrsOpTxt.getValue(), this.nwai415001.delyInstnTxt.getValue(), null));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.H_BILLING.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.billToCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.billToCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnEmlAddr, this.nwai415001.billToCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnExtnNum, this.nwai415001.billToCtacPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415001.billToCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFaxNum, S21StringUtil.subStringByLength(this.nwai415001.billToCtacFaxTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FAX_NUM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415001.hrsOpTxt.getValue(), this.nwai415001.delyInstnTxt.getValue(), null));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.H_IT.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.itCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.itCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnEmlAddr, this.nwai415001.itCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnExtnNum, this.nwai415001.itCtacPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415001.itCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415001.hrsOpTxt.getValue(), this.nwai415001.delyInstnTxt.getValue(), null));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.H_SUBSERVICES.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.subSvcFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.subSvcLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415001.subSvcEmlTxt);
            this.ctacPsnExtnNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415001.subSvcPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt, creteCtacPsnCmntTxt(this.nwai415001.hrsOpTxt.getValue(), this.nwai415001.delyInstnTxt.getValue(), null));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.H_EMANAGE_USER.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.csaPmtCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.csaPmtCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415001.csaPmtCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai415001.csaPmtPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415001.csaPmtCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt //
                    , creteCtacPsnCmntTxt(this.nwai415001.hrsOpTxt.getValue(), this.nwai415001.delyInstnTxt.getValue(), null));

        } else if (NWAB415001Constant.CTAC_PSN_SUB_TYPE.H_EMANAGE_ADMIN.equals(ctacPsnSubType)) {

            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnFirstNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.csaPmtCtacFirstNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_FIRST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnLastNm //
                    , S21StringUtil.subStringByLength(this.nwai415001.csaPmtCtacLastNm.getValue(), 0, CUTOFF_LEN.CTAC_PSN_LAST_NM.getLen()));
            ZYPEZDItemValueSetter.setValue(this.ctacPsnEmlAddr, this.nwai415001.csaPmtCtacEmlTxt);
            ZYPEZDItemValueSetter.setValue(this.ctacPsnExtnNum, this.nwai415001.csaPmtPhoExtTxt);
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnTelNum //
                    , S21StringUtil.subStringByLength(this.nwai415001.csaPmtCtacPhoTxt.getValue(), 0, CUTOFF_LEN.CTAC_PSN_TEL_NUM.getLen()));
            this.ctacPsnFaxNum.clear();
            ZYPEZDItemValueSetter.setValue(//
                    this.ctacPsnCmntTxt //
                    , creteCtacPsnCmntTxt(this.nwai415001.hrsOpTxt.getValue(), this.nwai415001.delyInstnTxt.getValue(), null));

        }
    }

    private String creteCtacPsnCmntTxt(String hrsOptTxt, String delyInstnTxt, String rmvInstnTxt) {
        if (S21StringUtil.isNotEmpty(hrsOptTxt)) {
            return S21StringUtil.subStringByLength(String.format(//
                    NWAB415001Constant.FORMAT_CTAC_PSN_CMNT_TXT, delyInstnTxt, hrsOptTxt), 0, CUTOFF_LEN.CTAC_PSN_CMNT_TXT.getLen());
        }

        if (S21StringUtil.isNotEmpty(rmvInstnTxt)) {
            return S21StringUtil.subStringByLength(//
                    String.format(NWAB415001Constant.FORMAT_CTAC_PSN_CMNT_TXT, delyInstnTxt, hrsOptTxt), 0, CUTOFF_LEN.CTAC_PSN_CMNT_TXT.getLen());
        }
        return S21StringUtil.subStringByLength(delyInstnTxt, 0, CUTOFF_LEN.CTAC_PSN_CMNT_TXT.getLen());
    }

}

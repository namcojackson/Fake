/*
 * <Pre>Copyright (c) 2017-2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.NWAI4150_05TMsg;
import business.db.NWAI4150_06TMsg;
import business.db.NWAI4150_08TMsg;
import business.db.NWAI4150_10TMsg;
import business.db.NWAI4150_14TMsg;
import business.db.NWAI4150_23TMsg;
import business.db.NWAI4150_24TMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.DTL_TYPE;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.INTERFACE_ID;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.SOM_BYOT_NM;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.VAR_CHAR_CONST_NM;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * EopsDsImptOrdDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 2019/10/04   Fujitsu         K.Kato          Update          QC#51372
 *</pre>
 */
public class EopsDsImptOrdDtlBean extends DS_IMPT_ORD_DTLTMsg implements IEopsDsImptOrd, IEopsErrorInfo {

    /** default */
    private static final long serialVersionUID = 1L;

    /**  */
    public NWAI4150_05TMsg nwai415005;

    /**  */
    public NWAI4150_08TMsg nwai415008;

    /**  */
    public NWAI4150_10TMsg nwai415010;

    /**  */
    public NWAI4150_14TMsg nwai415014;

    /**  */
    public NWAI4150_23TMsg nwai415023;

    /**  */
    public NWAI4150_24TMsg nwai415024;

    /**  */
    public ArrayList<NWAI4150_06TMsg> nwai4150_06List;

    /**  */
    public final DTL_TYPE dtlType;

    /**  */
    public final EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final EopsDsImptOrdConfigBean dsImptOrdConfigBean;

    /**  */
    public final List<EopsDsImptPrcCalcBaseBean> dsImptPrcCalcBaseBeanList;

    /**  */
    private ArrayList<NWAB415001ErrorInfo> errInfoList;

    /**  */
    private String tempBaseCmptFlg;

    /**
     * EopsDsImptOrdDtlBean
     * @param dtlType               NWAB415001Constant.DTL_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param nwai415005            NWAI4150_05TMsg
     */
    public EopsDsImptOrdDtlBean(NWAB415001Constant.DTL_TYPE dtlType, EopsDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4150_05TMsg nwai415005) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai415005 = nwai415005;
    }

    /**
     * EopsDsImptOrdDtlBean
     * @param dtlType               NWAB415001Constant.DTL_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param nwai415008            NWAI4150_08TMsg
     */
    public EopsDsImptOrdDtlBean(NWAB415001Constant.DTL_TYPE dtlType, EopsDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4150_08TMsg nwai415008) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai415008 = nwai415008;
    }

    /**
     * EopsDsImptOrdDtlBean
     * @param dtlType               NWAB415001Constant.DTL_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param nwai415010            NWAI4150_10TMsg
     * @param nwai415023            NWAI4150_23TMsg
     */
    public EopsDsImptOrdDtlBean(NWAB415001Constant.DTL_TYPE dtlType, EopsDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4150_10TMsg nwai415010, NWAI4150_23TMsg nwai415023) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai415010 = nwai415010;
        this.nwai415023 = nwai415023;
    }

    /**
     * EopsDsImptOrdDtlBean
     * @param dtlType               NWAB415001Constant.DTL_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     * @param nwai415014            NWAI4150_14TMsg
     */
    public EopsDsImptOrdDtlBean(NWAB415001Constant.DTL_TYPE dtlType, EopsDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4150_14TMsg nwai415014) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai415014 = nwai415014;
    }

    /**
     * EopsDsImptOrdDtlBean
     * @param dtlType               NWAB415001Constant.DTL_TYPE
     * @param dsImptOrdConfigBean   EopsDsImptOrdConfigBean
     */
    public EopsDsImptOrdDtlBean(NWAB415001Constant.DTL_TYPE dtlType, EopsDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.dtlType = dtlType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        this.dsImptOrdConfigBean.addDsImptOrdDtlBean(this);
        this.dsImptPrcCalcBaseBeanList = new ArrayList<EopsDsImptPrcCalcBaseBean>();
        this.nwai4150_06List = new ArrayList<NWAI4150_06TMsg>();
        this.errInfoList = new ArrayList<NWAB415001ErrorInfo>();
        this.tempBaseCmptFlg = ZYPConstant.FLG_OFF_N;
    }

    public EopsDsImptOrdDtlBean(DTL_TYPE dtlType, EopsDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4150_24TMsg nwai415024) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai415024 = nwai415024;
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
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        if (this.nwai415010 != null) {
            ZYPEZDItemValueSetter.setValue(this.ordSrcRefLineNum, this.nwai415010.eopsSortOrdNum.getValue().toPlainString());
        } else {
            this.ordSrcRefLineNum.clear();
        }
        this.ordSrcRefLineSubNum.clear();

        // merchandise
        String mdseCd = null;
        String mdseNm = null;
        Map<String, Object> mdse = null;
        if (DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {

            mdseCd = S21StringUtil.subStringByLength(//
                    this.nwai415010.somMercCd.getValue(), 0, CUTOFF_LEN.MDSE_CD.getLen());
            mdseNm = this.nwai415010.somMercNm.getValue();

        } else if (DTL_TYPE.OCE_PROMO.equals(dtlType)) {

            mdseCd = S21StringUtil.subStringByLength(//
                    this.nwai415014.somPrmoCd.getValue(), 0, CUTOFF_LEN.MDSE_CD.getLen());

        } else if (DTL_TYPE.TRADE_IN.equals(dtlType)) {

            mdseCd = (String) dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(VAR_CHAR_CONST_NM.EOPS_MDSE_TRADE_IN.name());

        } else if (DTL_TYPE.HEADER_REBATE.equals(dtlType)) {

            mdseCd = S21StringUtil.subStringByLength(//
                    this.dsImptOrdBean.nwai415001.rebMercCd.getValue(), 0, CUTOFF_LEN.MDSE_CD.getLen());

        } else if (DTL_TYPE.SPIFF_ITEM.equals(dtlType)) {

            mdseCd = this.nwai415008.somItemCd.getValue();

        } else {

            String itemCd = S21StringUtil.subStringByLength(this.nwai415005.somMercItemCd.getValue(), 0, CUTOFF_LEN.MDSE_CD.getLen());
            if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().containsKey(itemCd)) {
                mdse = NWXC412001.getMdse(glblCmpyCd, itemCd);
                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().put(itemCd, mdse);
            } else {
                mdse = (Map<String, Object>) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().get(itemCd);
            }
            if (mdse == null || mdse.isEmpty()) {

                mdse = NWXC412001.getSoftCostItemInfo(//
                        glblCmpyCd, CPO_SRC_TP.EOPS, INTERFACE_ID.NWAI4150.name(), this.nwai415005.somMercItemCd.getValue(), this.nwai415005.quoteLineTpTxt.getValue());

                if (mdse != null) {
                    mdseCd = (String) mdse.get("TRGT_ATTRB_TXT_04");

                    if (S21StringUtil.isEquals((String) mdse.get("TRGT_ATTRB_TXT_02"), ZYPConstant.FLG_OFF_N)) {

                        ZYPEZDItemValueSetter.setValue(this.imptLineFlg, ZYPConstant.FLG_OFF_N);
                    }
                }

            } else {

                mdseCd = (String) mdse.get("MDSE_CD");
                mdseNm = (String) mdse.get("MDSE_NM");
            }
        }
        if (mdseNm == null) {

            if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().containsKey(mdseCd)) {
                mdse = NWXC412001.getMdse(glblCmpyCd, mdseCd);
                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().put(mdseCd, mdse);
            } else {
                mdse = (Map<String, Object>) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().get(mdseCd);
            }
            if (mdse != null && !mdse.isEmpty()) {

                mdseCd = (String) mdse.get("MDSE_CD");
                mdseNm = (String) mdse.get("MDSE_NM");
            }
        }
        ZYPEZDItemValueSetter.setValue(this.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(this.mdseNm, S21StringUtil.subStringByLength(mdseNm, 0, CUTOFF_LEN.MDSE_NM.getLen()));

        this.origMdseCd.clear();
        this.origMdseNm.clear();
        this.setMdseCd.clear();
        ZYPEZDItemValueSetter.setValue(this.dropShipFlg, this.dsImptOrdConfigBean.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(this.shipToCustCd, this.dsImptOrdConfigBean.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(this.shipToLocNm, this.dsImptOrdConfigBean.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(this.shipToAddlLocNm, this.dsImptOrdConfigBean.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.shipToFirstLineAddr, this.dsImptOrdConfigBean.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToScdLineAddr, this.dsImptOrdConfigBean.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToThirdLineAddr, this.dsImptOrdConfigBean.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToFrthLineAddr, this.dsImptOrdConfigBean.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToFirstRefCmntTxt, this.dsImptOrdConfigBean.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.shipToScdRefCmntTxt, this.dsImptOrdConfigBean.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.shipToCtyAddr, this.dsImptOrdConfigBean.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.shipToStCd, this.dsImptOrdConfigBean.shipToStCd);
        ZYPEZDItemValueSetter.setValue(this.shipToProvNm, this.dsImptOrdConfigBean.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(this.shipToCntyNm, this.dsImptOrdConfigBean.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.shipToPostCd, this.dsImptOrdConfigBean.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(this.shipToCtryCd, this.dsImptOrdConfigBean.shipToCtryCd);

        // order quantity
        if (NWAB415001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {

            if (ZYPCommonFunc.hasValue(this.nwai415010.somOrdQty) //
                    && BigDecimal.ONE.compareTo(this.nwai415010.somOrdQty.getValue()) <= 0) {
                ZYPEZDItemValueSetter.setValue(this.ordQty, this.nwai415010.somOrdQty);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
            }
            ZYPEZDItemValueSetter.setValue(this.invtyLocCd, getInvtyLocCd());

        } else {
            ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
            //            if (NWAB415001Constant.DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN.equals(dtlType)) {
            //
            //                if (ZYPCommonFunc.hasValue(this.nwai4150_05.somOrdQty) && BigDecimal.ONE.compareTo(this.nwai4150_05.somOrdQty.getValue()) <= 0) {
            //                    ZYPEZDItemValueSetter.setValue(this.ordQty, this.nwai4150_05.somOrdQty);
            //                } else {
            //                    ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
            //                }
            //            } else {
            //                ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
            //            }

            this.invtyLocCd.clear();
        }
        ZYPEZDItemValueSetter.setValue(this.ordCustUomQty, this.ordQty);

        // floor price list(from configuration level)
        ZYPEZDItemValueSetter.setValue(this.flPrcListCd, this.dsImptOrdConfigBean.getFlPrcListCd());

        // price list(from configuration level)
        ZYPEZDItemValueSetter.setValue(this.prcCatgCd, this.dsImptOrdConfigBean.getPrcCatgCd());
        if (!ZYPCommonFunc.hasValue(this.prcCatgCd)) {

            for (EopsDsImptOrdConfigBean config : this.dsImptOrdBean.dsImptOrdConfigBeanList) {

                for (EopsDsImptOrdDtlBean detail : config.dsImptOrdDtlBeanList) {

                    // first configuration price list
                    ZYPEZDItemValueSetter.setValue(this.prcCatgCd, detail.flPrcListCd);
                    break;
                }
                break;
            }
        }

        if (!ZYPCommonFunc.hasValue(this.prcCatgCd)) {
            // default price list
            ZYPEZDItemValueSetter.setValue(//
                    this.prcCatgCd, (String) dsImptOrdConfigBean.dsImptOrdBean.getDefaultPrcCatgCd());
        }

        // function amount
        this.funcPrcListPrcAmt.clear();
        this.entFuncNetUnitPrcAmt.clear();
        this.cpoDtlFuncNetAmt.clear();
        this.cpoDtlFuncSlsAmt.clear();

        // amount
        if (NWAB415001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai415023.eopsTotCstAmt);
            ZYPEZDItemValueSetter.setValue(this.funcPrcListPrcAmt, this.nwai415023.eopsTotCstAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai415023.eopsTotCstAmt);
            ZYPEZDItemValueSetter.setValue(this.entFuncNetUnitPrcAmt, this.nwai415023.eopsTotCstAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai415023.eopsTotCstAmt.getValue().multiply(this.nwai415010.somOrdQty.getValue()));
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.cpoDtlDealNetAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlFuncNetAmt, this.cpoDtlDealNetAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlFuncSlsAmt, this.cpoDtlDealNetAmt);

        } else if (NWAB415001Constant.DTL_TYPE.OCE_PROMO.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai415014.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai415014.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai415014.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai415014.somPrmoAmt);

        } else if (NWAB415001Constant.DTL_TYPE.TRADE_IN.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, NWXC412001.multiplyBigDecimal(this.nwai415005.somPrcAmt.getValue(), BigDecimal.ONE.negate()));
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, NWXC412001.multiplyBigDecimal(this.nwai415005.somPrcAmt.getValue(), BigDecimal.ONE.negate()));
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, NWXC412001.multiplyBigDecimal(this.nwai415005.somExtPrcAmt.getValue(), BigDecimal.ONE.negate()));
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, NWXC412001.multiplyBigDecimal(this.nwai415005.somExtPrcAmt.getValue(), BigDecimal.ONE.negate()));

        } else if (NWAB415001Constant.DTL_TYPE.UPGRADEBUYOUTREBATE.equals(dtlType)) {

            BigDecimal somPrcAmt = NWXC412001.getSomAmtForRebate(this.nwai415005.somItemDescTxt.getValue(), this.nwai415005.somPrcAmt.getValue());
            BigDecimal somPrcExtAmt = NWXC412001.getSomAmtForRebate(this.nwai415005.somItemDescTxt.getValue(), this.nwai415005.somExtPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, somPrcExtAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, somPrcExtAmt);

        } else if (NWAB415001Constant.DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN.equals(dtlType)) {

            BigDecimal somPrcAmt = NWXC412001.getSomAmtForRebate(this.nwai415005.somItemDescTxt.getValue(), this.nwai415005.somPrcAmt.getValue());
            BigDecimal somPrcExtAmt = NWXC412001.getSomAmtForRebate(this.nwai415005.somItemDescTxt.getValue(), this.nwai415005.somExtPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, somPrcExtAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, somPrcExtAmt);

        } else if (NWAB415001Constant.DTL_TYPE.BUYOUT.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai415005.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai415005.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai415005.somExtPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai415005.somExtPrcAmt);

        } else if (NWAB415001Constant.DTL_TYPE.HEADER_REBATE.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.dsImptOrdBean.nwai415001.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.dsImptOrdBean.nwai415001.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.dsImptOrdBean.nwai415001.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.dsImptOrdBean.nwai415001.somRebAmt);

        } else if (NWAB415001Constant.DTL_TYPE.SPIFF_ITEM.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai415008.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai415008.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai415008.spiffDtlAmt);

        }

        ZYPEZDItemValueSetter.setValue(this.prcBaseDt, NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.cratTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.ccyCd, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(this.exchRate, BigDecimal.ONE);

        // RDD
        if (NWAB415001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {
            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415012.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.nwai4150_12.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.OCE_PROMO.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415012.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.nwai4150_12.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.TRADE_IN.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415005.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4150_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.UPGRADEBUYOUTREBATE.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415005.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4150_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415005.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4150_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.BUYOUT.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415005.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4150_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.HEADER_REBATE.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai415005.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai415001.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4150_12.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB415001Constant.DTL_TYPE.SPIFF_ITEM.equals(dtlType)) {

            this.rddDt.clear();

        }

        this.rsdDt.clear();
        this.shipCpltCd.clear();
        ZYPEZDItemValueSetter.setValue(this.uomCpltFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(this.stkStsCd, STK_STS.GOOD);

        if (!this.dsImptOrdBean.getCacheSlsRepTocCdMap().containsKey(this.dsImptOrdBean.nwai415001.somSlsRepId.getValue())) {
            Map<String, Object> slsRepTocCdMap = NWXC412001.getSlsRepTocByResourceId(glblCmpyCd, this.dsImptOrdBean.nwai415001.somSlsRepId.getValue(), this.dsImptOrdBean.slsDt);
            if (slsRepTocCdMap != null) {
                ZYPEZDItemValueSetter.setValue(this.slsRepOrSlsTeamTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
                this.dsImptOrdBean.getCacheSlsRepTocCdMap().put(this.dsImptOrdBean.nwai415001.somSlsRepId.getValue(), slsRepTocCdMap);
            } else {
                this.dsImptOrdBean.getCacheSlsRepTocCdMap().put(this.dsImptOrdBean.nwai415001.somSlsRepId.getValue(), new java.util.HashMap<String, Object>());
            }
        } else {
            Map<String, Object> slsRepTocCdMap = this.dsImptOrdBean.getCacheSlsRepTocCdMap().get(this.dsImptOrdBean.nwai415001.somSlsRepId.getValue());
            ZYPEZDItemValueSetter.setValue(this.slsRepOrSlsTeamTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
        }
        this.custMdseCd.clear();
        ZYPEZDItemValueSetter.setValue(this.custUomCd, PKG_UOM.EACH);
        this.ediStsCd.clear();
        this.ediNum.clear();
        this.ediSubNum.clear();
        this.origCpoOrdNum.clear();
        this.origInvNum.clear();
        ZYPEZDItemValueSetter.setValue(this.setItemShipCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(this.dsOrdPosnNum, this.dsImptOrdConfigBean.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(//
                this.unitNetWt, NWXC412001.getUnitNetWt(glblCmpyCd, this.mdseCd.getValue(), this.custUomCd.getValue(), this.ordCustUomQty.getValue()));
        ZYPEZDItemValueSetter.setValue(this.configItemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(this.baseCmptFlg, this.tempBaseCmptFlg);

        ZYPEZDItemValueSetter.setValue(this.custIstlFlg, NWXC412001.getSvcCustIstlFlg(glblCmpyCd, this.dsImptOrdBean.dsImptOrdIstlInfoBean.svcIstlRuleNum.getValue()));
        this.svcConfigMstrPk.clear();
        this.svcMachMstrPk.clear();
        this.ordLineSrcCd.clear();
        this.rtlWhCd.clear();
        this.rtlSwhCd.clear();
        this.serNum.clear();
        this.refCpoDtlLineNum.clear();
        this.refCpoDtlLineSubNum.clear();

        List<Map<String, Object>> existingConfigurationList = this.dsImptOrdConfigBean.existingConfigurationList;
        if (NWXC412001.hasValueList(existingConfigurationList)) {
            for (Map<String, Object> existingConfigurationMap : existingConfigurationList) {
                if (existingConfigurationMap != null //
                        && ZYPCommonFunc.hasValue(this.mdseCd) //
                        && !ZYPConstant.FLG_ON_Y.equals((String) existingConfigurationMap.get("PROC_FLG")) && this.mdseCd.getValue().equals((String) existingConfigurationMap.get("MDSE_CD"))) {

                    ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, (BigDecimal) existingConfigurationMap.get("SVC_CONFIG_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(this.svcMachMstrPk, (BigDecimal) existingConfigurationMap.get("SVC_MACH_MSTR_PK"));
                    //                    if (existingConfigurationMap.get("SER_NUM") == null) {
                    //                        this.serNum.clear();
                    //                    } else {
                    //                        ZYPEZDItemValueSetter.setValue(this.serNum, (String) existingConfigurationMap.get("SER_NUM"));
                    //                    }
                    if (ZYPCommonFunc.hasValue((String) existingConfigurationMap.get("INVTY_LOC_CD"))) {
                        //                        ZYPEZDItemValueSetter.setValue(this.rtlWhCd, (String) existingConfigurationMap.get("RTL_WH_CD"));
                        //                        ZYPEZDItemValueSetter.setValue(this.rtlSwhCd, (String) existingConfigurationMap.get("RTL_SWH_CD"));
                        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, (String) existingConfigurationMap.get("INVTY_LOC_CD"));
                        ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);
                    }
                    existingConfigurationMap.put("PROC_FLG", ZYPConstant.FLG_ON_Y);
                }
            }
        }

        this.dsContrNum.clear();
        this.dsContrSqNum.clear();
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(this.dsOrdLineCatgCd, this.dsImptOrdBean.getOutBndDsOrdLineCatgCd());

        String leaseByotMdseCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.LEASE_BYOT_MDSE_CD.name());
        if (ZYPCommonFunc.hasValue(leaseByotMdseCd) && ZYPCommonFunc.hasValue(this.mdseCd) && S21StringUtil.isEquals(leaseByotMdseCd, this.mdseCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(this.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LEASE_BUYOUT);
        }

        //if Inter-Territorial, set ITT
        if (ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.getSplitTypeTxt()) //
                && "Inter-Territorial".equals(this.dsImptOrdConfigBean.getSplitTypeTxt())) {

            String defVndKey = this.mdseCd.getValue();
            if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheVndMap().containsKey(defVndKey)) {
                NPZC113001PMsg pMsg = new NPZC113001PMsg();
                pMsg.glblCmpyCd.setValue(this.glblCmpyCd.getValue());
                pMsg.slsDt.setValue(this.dsImptOrdConfigBean.dsImptOrdBean.slsDt);
                pMsg.mdseCd.setValue(this.mdseCd.getValue());
                new NPZC113001().execute(pMsg, ONBATCH_TYPE.BATCH);
                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheVndMap().put(defVndKey, pMsg);

            }

            //            String dropShipRtlWhCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name());
            ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, ORD_LINE_SRC.ITT_DROP_SHIP);
            //            ZYPEZDItemValueSetter.setValue(this.rtlWhCd, dropShipRtlWhCd);
            this.rtlSwhCd.clear();
            NPZC113001PMsg pMsg = (NPZC113001PMsg) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheVndMap().get(defVndKey);
            if (pMsg != null) {
                ZYPEZDItemValueSetter.setValue(this.invtyLocCd, pMsg.vndCd.getValue());
            }
            //if not Inter-Territorial
        } else {
            //QC#17768
            if (!ZYPCommonFunc.hasValue(this.svcMachMstrPk)) {
                String defWhOutboundKey = "O" + this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd() + this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd() + this.mdseCd.getValue() + this.shipToPostCd.getValue()
                        + this.ordQty.getValue();

                if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().containsKey(defWhOutboundKey)) {

                    NWZC180001PMsg pMsg = new NWZC180001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.dsImptOrdConfigBean.dsImptOrdBean.slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd());
                    ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd());
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, this.mdseCd.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.postCd, this.shipToPostCd.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.ordQty, this.ordQty.getValue());
                    new NWZC180001().execute(pMsg, ONBATCH_TYPE.BATCH);
                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().put(defWhOutboundKey, pMsg);
                }

                NWZC180001PMsg nwzc180001PMsg = (NWZC180001PMsg) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().get(defWhOutboundKey);
                if (nwzc180001PMsg != null) {
                    ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, nwzc180001PMsg.ordLineSrcCd.getValue());
                    //                    ZYPEZDItemValueSetter.setValue(this.rtlWhCd, nwzc180001PMsg.rtlWhCd.getValue());
                    //                    ZYPEZDItemValueSetter.setValue(this.rtlSwhCd, nwzc180001PMsg.rtlSwhCd.getValue());
                    String dropShipRtlWhCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name());
                    if (dropShipRtlWhCd != null && dropShipRtlWhCd.equals(nwzc180001PMsg.rtlWhCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, nwzc180001PMsg.vndCd.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, nwzc180001PMsg.rtlWhCd.getValue() + nwzc180001PMsg.rtlSwhCd.getValue());
                    }
                }
            }
        }

        this.dplyLineRefNum.clear();
        this.crRebilCd.clear();

        // supply vendor
        if (NWAB415001Constant.DTL_TYPE.BUYOUT.equals(dtlType)) {

            if (S21StringUtil.isEquals(this.nwai415005.somByotNm.getValue(), SOM_BYOT_NM.CANON_FINANCIAL_SERVICES.getValue())) {
                //CFS
                Map<String, Object> vnd = this.dsImptOrdConfigBean.getVndMap();
                if (vnd != null) {

                    ZYPEZDItemValueSetter.setValue(this.splyTpCd, SPLY_TP.CFS);
                    // 2019/10/04 QC#51372 Mod Start
                    //ZYPEZDItemValueSetter.setValue(this.splyNm, (String) vnd.get("LOC_NM"));
                    String locNm = (String) vnd.get("LOC_NM");
                    if (locNm.length() > this.getAttr("prntVndNm").getDigit()) {
                        locNm = locNm.substring(0, this.getAttr("prntVndNm").getDigit());
                    }
                    ZYPEZDItemValueSetter.setValue(this.prntVndNm, locNm);
                    // 2019/10/04 QC#51372 Mod End
                    ZYPEZDItemValueSetter.setValue(this.splyFirstAddr, (String) vnd.get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, (String) vnd.get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(this.splyStCd, (String) vnd.get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(this.splyPostCd, (String) vnd.get("POST_CD"));
                }
            } else {

                // Customer
                ZYPEZDItemValueSetter.setValue(this.splyTpCd, SPLY_TP.CUSTOMER);
                    // 2019/10/04 QC#51372 Mod Start
                //ZYPEZDItemValueSetter.setValue(this.splyNm, this.dsImptOrdBean.nwai415001.somShipToCustNm);
                ZYPEZDItemValueSetter.setValue(this.prntVndNm, this.dsImptOrdBean.nwai415001.somShipToCustNm);
                    // 2019/10/04 QC#51372 Mod End
                if (ZYPCommonFunc.hasValue(this.nwai415005.eopsByotAddr_01)) {
                    ZYPEZDItemValueSetter.setValue(this.splyFirstAddr, this.nwai415005.eopsByotAddr_01);
                    ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, this.nwai415005.eopsByotCityTxt);
                    ZYPEZDItemValueSetter.setValue(this.splyStCd, this.nwai415005.eopsByotStCd);
                    ZYPEZDItemValueSetter.setValue(this.splyPostCd, this.nwai415005.eopsByotZipCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(//
                            this.splyFirstAddr, S21StringUtil.concatStrings(//
                                    this.dsImptOrdBean.nwai415001.shipToAddrDescTxt_01.getValue() //
                                    , " " //
                                    , this.dsImptOrdBean.nwai415001.shipToAddrDescTxt_02.getValue()));
                    ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, this.dsImptOrdBean.nwai415001.shipToCityTxt);
                    ZYPEZDItemValueSetter.setValue(this.splyStCd, this.dsImptOrdBean.nwai415001.shipToStTxt);
                    ZYPEZDItemValueSetter.setValue(this.splyPostCd, this.dsImptOrdBean.nwai415001.somShipToZipCd);
                }
            }

        } else {
            this.splyTpCd.clear();
            this.splyNm.clear();
            // 2019/10/04 QC#51372 Add Start
            this.prntVndNm.clear();
            // 2019/10/04 QC#51372 Add End
            this.splyFirstAddr.clear();
            this.splyCtyAddr.clear();
            this.splyStCd.clear();
            this.splyPostCd.clear();
        }

        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.csmpContrNum)) {
            ZYPEZDItemValueSetter.setValue(this.csmpContrNum, this.dsImptOrdBean.csmpContrNum);
        } else {
            this.csmpContrNum.clear();
        }
        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.dlrRefNum)) {
            ZYPEZDItemValueSetter.setValue(this.dlrRefNum, this.dsImptOrdBean.dlrRefNum);
        } else {
            this.dlrRefNum.clear();
        }
        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.csmpContrNum) //
                && ZYPCommonFunc.hasValue(this.dsImptOrdBean.dlrRefNum)) {
            ZYPEZDItemValueSetter.setValue(this.csmpPrcListCd, this.dsImptOrdBean.getCsmpPrcListCd());
        } else {
            this.csmpPrcListCd.clear();
        }

        this.rntlTrmnDt.clear();
        this.firstBllgAttrbNm.clear();
        this.firstBllgAttrbValTxt.clear();
        this.scdBllgAttrbNm.clear();
        this.scdBllgAttrbValTxt.clear();
        this.thirdBllgAttrbNm.clear();
        this.thirdBllgAttrbValTxt.clear();
        this.frthBllgAttrbNm.clear();
        this.frthBllgAttrbValTxt.clear();
        this.fifthBllgAttrbNm.clear();
        this.fifthBllgAttrbValTxt.clear();
        this.sixthBllgAttrbNm.clear();
        this.sixthBllgAttrbValTxt.clear();
        this.sbstMdseCd.clear();
        ZYPEZDItemValueSetter.setValue(this.supdLockFlg, ZYPConstant.FLG_ON_Y);
        this.prcListEquipConfigNum.clear();
        ZYPEZDItemValueSetter.setValue(this.imptLineFlg, ZYPConstant.FLG_ON_Y);

        for (EopsDsImptPrcCalcBaseBean prcCalcBaseBean : this.dsImptPrcCalcBaseBeanList) {
            prcCalcBaseBean.doImptMapping(glblCmpyCd, salesDate);
        }
        ZYPEZDItemValueSetter.setValue(this.preExistFlg, ZYPConstant.FLG_OFF_N); // QC#16266
        ZYPEZDItemValueSetter.setValue(this.finItemLineFlg, ZYPConstant.FLG_ON_Y); // QC#16266

        return true;
    }

    private String getInvtyLocCd() {
        String defWhOutboundKey //
        = S21StringUtil.concatStrings(//
                CONFIG_CATG.OUTBOUND //
                , this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd() //
                , this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd() //
                , this.mdseCd.getValue() //
                , this.shipToPostCd.getValue() //
                , this.ordQty.getValue());

        if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().containsKey(defWhOutboundKey)) {

            NWZC180001PMsg pMsg = new NWZC180001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.dsImptOrdConfigBean.dsImptOrdBean.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, this.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.postCd, this.shipToPostCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.ordQty, this.ordQty.getValue());
            new NWZC180001().execute(pMsg, ONBATCH_TYPE.BATCH);
            this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().put(defWhOutboundKey, pMsg);
        }

        NWZC180001PMsg nwzc180001PMsg = (NWZC180001PMsg) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().get(defWhOutboundKey);
        if (nwzc180001PMsg != null) {
            ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, nwzc180001PMsg.ordLineSrcCd.getValue());
            ZYPEZDItemValueSetter.setValue(this.rtlWhCd, nwzc180001PMsg.rtlWhCd.getValue());
            ZYPEZDItemValueSetter.setValue(this.rtlSwhCd, nwzc180001PMsg.rtlSwhCd.getValue());
            String dropShipRtlWhCd //
            = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB415001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name());
            if (dropShipRtlWhCd != null //
                    && dropShipRtlWhCd.equals(nwzc180001PMsg.rtlWhCd.getValue())) {
                return nwzc180001PMsg.vndCd.getValue();
            } else {
                return S21StringUtil.concatStrings(nwzc180001PMsg.rtlWhCd.getValue(), nwzc180001PMsg.rtlSwhCd.getValue());
            }
        }
        return "";
    }

    //    private NWAI4150_06TMsg getEopsRewriteAddrInfo() {
    //        BigDecimal somSoftCostId = nwai4150_05.somSoftCostId.getValue();
    //        if (!ZYPCommonFunc.hasValue(somSoftCostId)) {
    //            return null;
    //        }
    //        NWAI4150_06TMsg reWriteNwai4150_06TMsg = null;
    //        NWAI4150_06TMsg noReWriteNwai4150_06TMsg = null;
    //        NWAI4150_06TMsg noReWriteNwai4150_06TMsg2 = null;
    //        for (NWAI4150_06TMsg nwai4150_06TMsg : nwai4150_06List) {
    //            BigDecimal somSoftCostItemId = nwai4150_06TMsg.somSoftCostItemId.getValue();
    //            if (!ZYPCommonFunc.hasValue(somSoftCostItemId)) {
    //                continue;
    //            }
    //            if (somSoftCostId.compareTo(somSoftCostItemId) != 0) {
    //                continue;
    //            }
    //            if (S21StringUtil.isEquals("YES", nwai4150_06TMsg.pickUpDtSameTxt.getValue()) //
    //                    && ZYPCommonFunc.hasValue(nwai4150_06TMsg.pickUpEopsAddr_01) //
    //                    && reWriteNwai4150_06TMsg == null) {
    //                reWriteNwai4150_06TMsg = new NWAI4150_06TMsg();
    //                EZDMsg.copy(nwai4150_06TMsg, null, reWriteNwai4150_06TMsg, null);
    //                break;
    //            }
    //            if (!S21StringUtil.isEquals("YES", nwai4150_06TMsg.pickUpDtSameTxt.getValue()) //
    //                    && (S21StringUtil.isEquals("0", nwai4150_06TMsg.somDescSerNum.getValue())) //
    //                    && ZYPCommonFunc.hasValue(nwai4150_06TMsg.pickUpEopsAddr_01) //
    //                    && noReWriteNwai4150_06TMsg == null) {
    //                noReWriteNwai4150_06TMsg = new NWAI4150_06TMsg();
    //                EZDMsg.copy(nwai4150_06TMsg, null, noReWriteNwai4150_06TMsg, null);
    //            }
    //            if (!S21StringUtil.isEquals("YES", nwai4150_06TMsg.pickUpDtSameTxt.getValue()) //
    //                    && (!ZYPCommonFunc.hasValue(nwai4150_06TMsg.somDescSerNum)) //
    //                    && ZYPCommonFunc.hasValue(nwai4150_06TMsg.pickUpEopsAddr_01) //
    //                    && noReWriteNwai4150_06TMsg2 == null) {
    //                noReWriteNwai4150_06TMsg2 = new NWAI4150_06TMsg();
    //                EZDMsg.copy(nwai4150_06TMsg, null, noReWriteNwai4150_06TMsg2, null);
    //            }
    //        }
    //        if (reWriteNwai4150_06TMsg != null) {
    //            return reWriteNwai4150_06TMsg;
    //        } else if (noReWriteNwai4150_06TMsg != null) {
    //            return noReWriteNwai4150_06TMsg;
    //        } else if (noReWriteNwai4150_06TMsg2 != null) {
    //            return noReWriteNwai4150_06TMsg2;
    //        } else {
    //            return null;
    //        }
    //    }

    /**
     * @param errorInfo NWXC220001ErrorInfo
     */
    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        BigDecimal transactionId = null;
        BigDecimal unitId = null;
        BigDecimal seqNumber = null;

        if (this.nwai415005 != null) {
            transactionId = this.nwai415005.transactionId.getValue();
            unitId = this.nwai415005.transactionId.getValue();
            seqNumber = this.nwai415005.transactionId.getValue();
        } else if (this.nwai415008 != null) {
            transactionId = this.nwai415008.transactionId.getValue();
            unitId = this.nwai415008.transactionId.getValue();
            seqNumber = this.nwai415008.transactionId.getValue();
        } else if (this.nwai415010 != null) {
            transactionId = this.nwai415010.transactionId.getValue();
            unitId = this.nwai415010.transactionId.getValue();
            seqNumber = this.nwai415010.transactionId.getValue();
        } else if (this.nwai415014 != null) {
            transactionId = this.nwai415014.transactionId.getValue();
            unitId = this.nwai415014.transactionId.getValue();
            seqNumber = this.nwai415014.transactionId.getValue();
        }

        this.errInfoList.add(new NWAB415001ErrorInfo(transactionId, unitId, seqNumber, errorInfo));
    }

    /**
     * @param errorInfoList List<NWXC220001ErrorInfo>
     */
    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            addErrorInfo(errorInfo);
        }
    }

    /**
     * @return errInfoList
     */
    @Override
    public List<NWAB415001ErrorInfo> getErrorInfo() {
        return this.errInfoList;
    }

    /**
     * @return if error then true.
     */
    @Override
    public boolean hasError() {
        return (this.getErrorInfo().size() > 0);
    }

    /**
     * setTempBaseCmptFlg
     * @param tempBaseCmptFlg String
     */
    public void setTempBaseCmptFlg(String tempBaseCmptFlg) {
        this.tempBaseCmptFlg = tempBaseCmptFlg;
    }
}

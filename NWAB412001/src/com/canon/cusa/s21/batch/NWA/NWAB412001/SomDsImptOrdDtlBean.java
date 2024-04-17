/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.NWAI4120_05TMsg;
import business.db.NWAI4120_06TMsg;
import business.db.NWAI4120_08TMsg;
import business.db.NWAI4120_10TMsg;
import business.db.NWAI4120_14TMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 2016/11/29   Fujitsu         M.Yamada        Update          QC#16266
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 03/08/2017   SRAA            K.Aratani       Update          QC#17768
 * 04/03/2017   Fujitsu         S.Iidaka        Update          QC#18179
 * 03/31/2017   SRAA            K.Aratani       Update          QC#18211
 * 04/10/2017   SRAA            K.Aratani       Update          QC#18306
 * 08/22/2017   SRAA            K.Aratani       Update          QC#20739
 * 2017/09/11   Fujitsu         S.Takami        Update          QC#21011
 * 2018/01/15   Fujitsu         S.Ohki          Update          QC#22372
 * 2018/02/16   SRAA            K.Aratani       Update          QC#24077
 * 2018/02/16   SRAA            K.Aratani       Update          QC#24115
 * 2018/02/16   SRAA            K.Aratani       Update          QC#24092
 * 2018/02/16   SRAA            K.Aratani       Update          QC#24207
 * 2019/10/04   Fujitsu         K.Kato          Update          QC#51372
 *</pre>
 */
public class SomDsImptOrdDtlBean extends DS_IMPT_ORD_DTLTMsg implements ISomDsImptOrd, ISomErrorInfo {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.DTL_TYPE dtlType;

    public final SomDsImptOrdBean dsImptOrdBean;

    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public final List<SomDsImptPrcCalcBaseBean> dsImptPrcCalcBaseBeanList;

    public NWAI4120_05TMsg nwai4120_05;

    public NWAI4120_08TMsg nwai4120_08;

    public final List<NWAI4120_06TMsg> nwai4120_06List;

    public NWAI4120_10TMsg nwai4120_10;

    public NWAI4120_14TMsg nwai4120_14;
    //QC#18211
    private String tempBaseCmptFlg;
    //QC#24092
    private boolean otherComponentSerializedFlag = false;
    
    /** List<NWAB412001ErrorInfo> */
    final public List<NWAB412001ErrorInfo> errInfoList;

    public SomDsImptOrdDtlBean(NWAB412001Constant.DTL_TYPE dtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_05TMsg nwai4120_05) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai4120_05 = nwai4120_05;
    }

    //QC#24092
    public SomDsImptOrdDtlBean(NWAB412001Constant.DTL_TYPE dtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_05TMsg nwai4120_05, boolean otherComponentSerializedFlag) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai4120_05 = nwai4120_05;
        this.otherComponentSerializedFlag = otherComponentSerializedFlag;
    }
    
    public SomDsImptOrdDtlBean(NWAB412001Constant.DTL_TYPE dtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_08TMsg nwai4120_08) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai4120_08 = nwai4120_08;
    }

    public SomDsImptOrdDtlBean(NWAB412001Constant.DTL_TYPE dtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_10TMsg nwai4120_10) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai4120_10 = nwai4120_10;
    }

    public SomDsImptOrdDtlBean(NWAB412001Constant.DTL_TYPE dtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_14TMsg nwai4120_14) {
        this(dtlType, dsImptOrdConfigBean);
        this.nwai4120_14 = nwai4120_14;
    }

    public SomDsImptOrdDtlBean(NWAB412001Constant.DTL_TYPE dtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.dtlType = dtlType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        this.dsImptOrdConfigBean.addDsImptOrdDtlBean(this);
        this.dsImptPrcCalcBaseBeanList = new ArrayList<SomDsImptPrcCalcBaseBean>();
        this.nwai4120_06List = new ArrayList<NWAI4120_06TMsg>();
        this.errInfoList = new ArrayList<NWAB412001ErrorInfo>();
        this.tempBaseCmptFlg = ZYPConstant.FLG_ON_Y; //QC#18211
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
        if (this.nwai4120_10 != null) {
            ZYPEZDItemValueSetter.setValue(this.ordSrcRefLineNum, this.nwai4120_10.somSortOrdNum.getValue().toPlainString());
        } else {
            this.ordSrcRefLineNum.clear();
        }
        this.ordSrcRefLineSubNum.clear();

        // merchandise
        String mdseCd = null;
        String mdseNm = null;
        Map<String, Object> mdse = null;
        if (NWAB412001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {

            mdseCd = S21StringUtil.subStringByLength(this.nwai4120_10.somMercCd.getValue(), 0, 16);
            mdseNm = this.nwai4120_10.somMercNm.getValue();

        } else if (NWAB412001Constant.DTL_TYPE.OCE_PROMO.equals(dtlType)) {

            mdseCd = S21StringUtil.subStringByLength(this.nwai4120_14.somPrmoCd.getValue(), 0, 16);

        } else if (NWAB412001Constant.DTL_TYPE.HEADER_REBATE.equals(dtlType)) {

            mdseCd = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.rebMercCd.getValue(), 0, 16);

        } else if (NWAB412001Constant.DTL_TYPE.TRADE_IN.equals(dtlType)) {

            mdseCd = (String) dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_MDSE_TRADE_IN.name());

        } else if (NWAB412001Constant.DTL_TYPE.SPIFF_ITEM.equals(dtlType)) {

            mdseCd = this.nwai4120_08.somItemCd.getValue();

        } else {

            if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().containsKey(S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16))) {
                mdse = NWXC412001.getMdse(glblCmpyCd, S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16));
                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().put(S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16), mdse);
            } else {
                mdse = (Map<String, Object>) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().get(S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16));
            }
            if (mdse == null || mdse.isEmpty()) {

                mdse = NWXC412001.getSoftCostItemInfo(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), this.nwai4120_05.somMercItemCd.getValue(), this.nwai4120_05.quoteLineTpTxt.getValue());

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
        ZYPEZDItemValueSetter.setValue(this.mdseNm, S21StringUtil.subStringByLength(mdseNm, 0, 30));

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
        if (NWAB412001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {

            if (ZYPCommonFunc.hasValue(this.nwai4120_10.somOrdQty) && BigDecimal.ONE.compareTo(this.nwai4120_10.somOrdQty.getValue()) <= 0) {
                ZYPEZDItemValueSetter.setValue(this.ordQty, this.nwai4120_10.somOrdQty);
            } else {
                ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
            }

        } else {
            if (NWAB412001Constant.DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN.equals(dtlType)) {
                //QC#24092
                if (ZYPCommonFunc.hasValue(this.nwai4120_05.somOrdQty) && BigDecimal.ONE.compareTo(this.nwai4120_05.somOrdQty.getValue()) <= 0 && !otherComponentSerializedFlag) {
                    ZYPEZDItemValueSetter.setValue(this.ordQty, this.nwai4120_05.somOrdQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE);
            }

        }
        ZYPEZDItemValueSetter.setValue(this.ordCustUomQty, this.ordQty);

        this.invtyLocCd.clear();

        // floor price list(from configuration level)
        ZYPEZDItemValueSetter.setValue(this.flPrcListCd, this.dsImptOrdConfigBean.getFlPrcListCd());

        // price list(from configuration level)
        ZYPEZDItemValueSetter.setValue(this.prcCatgCd, this.dsImptOrdConfigBean.getPrcCatgCd());
        if (!ZYPCommonFunc.hasValue(this.prcCatgCd)) {

            for (SomDsImptOrdConfigBean config : this.dsImptOrdBean.dsImptOrdConfigBeanList) {

                for (SomDsImptOrdDtlBean detail : config.dsImptOrdDtlBeanList) {

                    // first configuration price list
                    ZYPEZDItemValueSetter.setValue(this.prcCatgCd, detail.flPrcListCd);
                    break;
                }
                break;
            }
        }

        if (!ZYPCommonFunc.hasValue(this.prcCatgCd)) {

            // default price list
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, (String) dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_DEFAULT_PRICE_LIST.name()));
        }

        // amount
        if (NWAB412001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_10.sellPrcListAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai4120_10.sellPrcListAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai4120_10.extSellPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai4120_10.extSellPrcAmt);

        } else if (NWAB412001Constant.DTL_TYPE.OCE_PROMO.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai4120_14.somPrmoAmt);

        } else if (NWAB412001Constant.DTL_TYPE.TRADE_IN.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, NWXC412001.multiplyBigDecimal(this.nwai4120_05.somPrcAmt.getValue(), BigDecimal.ONE.negate()));
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, NWXC412001.multiplyBigDecimal(this.nwai4120_05.somPrcAmt.getValue(), BigDecimal.ONE.negate()));
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, NWXC412001.multiplyBigDecimal(this.nwai4120_05.somExtPrcAmt.getValue(), BigDecimal.ONE.negate()));
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, NWXC412001.multiplyBigDecimal(this.nwai4120_05.somExtPrcAmt.getValue(), BigDecimal.ONE.negate()));

        } else if (NWAB412001Constant.DTL_TYPE.UPGRADEBUYOUTREBATE.equals(dtlType)) {

            BigDecimal somPrcAmt = NWXC412001.getSomAmtForRebate(this.nwai4120_05.somItemDescTxt.getValue(), this.nwai4120_05.somPrcAmt.getValue());
            BigDecimal somPrcExtAmt = NWXC412001.getSomAmtForRebate(this.nwai4120_05.somItemDescTxt.getValue(), this.nwai4120_05.somExtPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, somPrcExtAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, somPrcExtAmt);

        } else if (NWAB412001Constant.DTL_TYPE.NOT_UPGRADEBUYOUTREBATE_AND_TRADE_IN.equals(dtlType)) {

            BigDecimal somPrcAmt = NWXC412001.getSomAmtForRebate(this.nwai4120_05.somItemDescTxt.getValue(), this.nwai4120_05.somPrcAmt.getValue());
            BigDecimal somPrcExtAmt = NWXC412001.getSomAmtForRebate(this.nwai4120_05.somItemDescTxt.getValue(), this.nwai4120_05.somExtPrcAmt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, somPrcExtAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, somPrcExtAmt);

        } else if (NWAB412001Constant.DTL_TYPE.BUYOUT.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai4120_05.somPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai4120_05.somExtPrcAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai4120_05.somExtPrcAmt);

        } else if (NWAB412001Constant.DTL_TYPE.HEADER_REBATE.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, this.dsImptOrdBean.nwai4120_01.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.dsImptOrdBean.nwai4120_01.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.dsImptOrdBean.nwai4120_01.somRebAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.dsImptOrdBean.nwai4120_01.somRebAmt);

        } else if (NWAB412001Constant.DTL_TYPE.SPIFF_ITEM.equals(dtlType)) {

            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai4120_08.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, this.nwai4120_08.spiffDtlAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, this.nwai4120_08.spiffDtlAmt);

        } else {

            // none
        }

        // 2018/01/15 S21_NA#22372 Add Start
        //QC#24027
        if (this.nwai4120_10 != null && ZYPCommonFunc.hasValue(this.nwai4120_10.usedDemoId) && BigDecimal.ZERO.compareTo(this.nwai4120_10.usedDemoId.getValue()) != 0) {
            ZYPEZDItemValueSetter.setValue(this.funcUnitFlPrcAmt, this.nwai4120_10.flPrcAmt);
        }
        // 2018/01/15 S21_NA#22372 Add End

        // function amount
        this.funcPrcListPrcAmt.clear();
        this.entFuncNetUnitPrcAmt.clear();
        this.cpoDtlFuncNetAmt.clear();
        this.cpoDtlFuncSlsAmt.clear();

        ZYPEZDItemValueSetter.setValue(this.prcBaseDt, NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.cratTs.getValue()));
        ZYPEZDItemValueSetter.setValue(this.ccyCd, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(this.exchRate, BigDecimal.ONE);

        // RDD
        if (NWAB412001Constant.DTL_TYPE.REGULAR_SHIP_LINE.equals(dtlType)) {
            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB412001Constant.DTL_TYPE.OCE_PROMO.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB412001Constant.DTL_TYPE.TRADE_IN.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4120_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB412001Constant.DTL_TYPE.UPGRADEBUYOUTREBATE.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4120_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB412001Constant.DTL_TYPE.BUYOUT.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(this.nwai4120_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB412001Constant.DTL_TYPE.HEADER_REBATE.equals(dtlType)) {

            String pRddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
            if (!ZYPCommonFunc.hasValue(rddDt)) {
                pRddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            }
            //ZYPEZDItemValueSetter.setValue(this.rddDt, NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rddDt, pRddDt);

        } else if (NWAB412001Constant.DTL_TYPE.SPIFF_ITEM.equals(dtlType)) {

            this.rddDt.clear();

        } else {

            // none
        }

        this.rsdDt.clear();
        this.shipCpltCd.clear();
        ZYPEZDItemValueSetter.setValue(this.uomCpltFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(this.stkStsCd, STK_STS.GOOD);
        
        if (!this.dsImptOrdBean.getCacheSlsRepTocCdMap().containsKey(this.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue())) {
            Map<String, Object> slsRepTocCdMap = NWXC412001.getSlsRepTocByResourceId(glblCmpyCd, this.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue(), this.dsImptOrdBean.slsDt);
            if (slsRepTocCdMap != null) {
                ZYPEZDItemValueSetter.setValue(this.slsRepOrSlsTeamTocCd, (String) slsRepTocCdMap.get("SLS_REP_TOC_CD"));
                this.dsImptOrdBean.getCacheSlsRepTocCdMap().put(this.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue(), slsRepTocCdMap);
            } else {
                this.dsImptOrdBean.getCacheSlsRepTocCdMap().put(this.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue(), new java.util.HashMap<String, Object>());
            }
        } else {
            Map<String, Object> slsRepTocCdMap = this.dsImptOrdBean.getCacheSlsRepTocCdMap().get(this.dsImptOrdBean.nwai4120_01.somSlsRepId.getValue());
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
        ZYPEZDItemValueSetter.setValue(this.unitNetWt, NWXC412001.getUnitNetWt(glblCmpyCd, this.mdseCd.getValue(), this.custUomCd.getValue(), this.ordCustUomQty.getValue()));
        ZYPEZDItemValueSetter.setValue(this.configItemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(this.baseCmptFlg, this.tempBaseCmptFlg); //QC#18211
        // TODO DS_IMPT_ORD_ISTL_INFO
        ZYPEZDItemValueSetter.setValue(this.custIstlFlg, ZYPConstant.FLG_OFF_N);
        //QC#17768
        //ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, this.dsImptOrdConfigBean.svcConfigMstrPk);
        //ZYPEZDItemValueSetter.setValue(this.svcMachMstrPk, this.dsImptOrdConfigBean.getSvcConfigMstrPk());
        this.serNum.clear();
        this.svcConfigMstrPk.clear();
        this.svcMachMstrPk.clear();
        this.ordLineSrcCd.clear();
        this.rtlWhCd.clear();
        this.rtlSwhCd.clear();
        List<Map<String, Object>> existingConfigurationList = this.dsImptOrdConfigBean.existingConfigurationList;
        if (existingConfigurationList != null && !existingConfigurationList.isEmpty()) {
            for (Map<String, Object> existingConfigurationMap : existingConfigurationList) {
                if (existingConfigurationMap != null 
                    && ZYPCommonFunc.hasValue(this.mdseCd) 
                    && !ZYPConstant.FLG_ON_Y.equals((String) existingConfigurationMap.get("PROC_FLG"))
                    && this.mdseCd.getValue().equals((String) existingConfigurationMap.get("MDSE_CD"))
                    && this.dsImptOrdConfigBean.isExistingConfigurationFlag) {  //QC#24077

                    ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, (BigDecimal) existingConfigurationMap.get("SVC_CONFIG_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(this.svcMachMstrPk, (BigDecimal) existingConfigurationMap.get("SVC_MACH_MSTR_PK"));
                    //QC#24207
                    //if (existingConfigurationMap.get("SER_NUM") == null) {
                    //    this.serNum.clear();
                    //} else {
                    //    ZYPEZDItemValueSetter.setValue(this.serNum, (String) existingConfigurationMap.get("SER_NUM"));
                    //}
                    if (ZYPCommonFunc.hasValue((String) existingConfigurationMap.get("INVTY_LOC_CD"))) {
                        ZYPEZDItemValueSetter.setValue(this.rtlWhCd, (String) existingConfigurationMap.get("RTL_WH_CD"));
                        ZYPEZDItemValueSetter.setValue(this.rtlSwhCd, (String) existingConfigurationMap.get("RTL_SWH_CD"));
                        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, (String) existingConfigurationMap.get("INVTY_LOC_CD"));
                        ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);
                    }
                    existingConfigurationMap.put("PROC_FLG", ZYPConstant.FLG_ON_Y);
                }
            }
        }
        //QC#24207 Serial from SOM in case of Pre-Owned
        if (this.nwai4120_10 != null && ZYPCommonFunc.hasValue(this.nwai4120_10.usedDemoId) && BigDecimal.ZERO.compareTo(this.nwai4120_10.usedDemoId.getValue()) != 0) {
            ZYPEZDItemValueSetter.setValue(this.serNum, this.nwai4120_10.somDescSerNum);
        }
        
        this.dsContrNum.clear();
        this.dsContrSqNum.clear();
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(this.dsOrdLineCatgCd, this.dsImptOrdBean.getOutBndDsOrdLineCatgCd());

        // QC#18179 2017/04/03 ADD START
        String leaseByotMdseCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.LEASE_BYOT_MDSE_CD.name());
        if(ZYPCommonFunc.hasValue(leaseByotMdseCd) && ZYPCommonFunc.hasValue(this.mdseCd) && S21StringUtil.isEquals(leaseByotMdseCd, this.mdseCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(this.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LEASE_BUYOUT);
        }
        // QC#18179 2017/04/03 ADD END

        //if Inter-Territorial, set ITT
        if (ZYPCommonFunc.hasValue(this.dsImptOrdConfigBean.getSplitTypeTxt()) 
                && "Inter-Territorial".equals(this.dsImptOrdConfigBean.getSplitTypeTxt())) {

            String defVndKey = this.mdseCd.getValue();
            if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheVndMap().containsKey(defVndKey)) {
                NPZC113001PMsg pMsg = new NPZC113001PMsg();
                pMsg.glblCmpyCd.setValue(this.glblCmpyCd.getValue());
                pMsg.slsDt.setValue(this.dsImptOrdConfigBean.dsImptOrdBean.slsDt);
                pMsg.mdseCd.setValue(this.mdseCd.getValue());
                new NPZC113001().execute(pMsg, ONBATCH_TYPE.BATCH);
                //if (S21ApiUtil.getXxMsgList(pMsg).size() <= 0) {
                //}
                this.dsImptOrdConfigBean.dsImptOrdBean.getCacheVndMap().put(defVndKey, pMsg);
                
            }
            
            String dropShipRtlWhCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name());
            ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, ORD_LINE_SRC.ITT_DROP_SHIP);
            ZYPEZDItemValueSetter.setValue(this.rtlWhCd, dropShipRtlWhCd);
            this.rtlSwhCd.clear();
            NPZC113001PMsg pMsg = (NPZC113001PMsg) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheVndMap().get(defVndKey);
            if (pMsg != null) {
                ZYPEZDItemValueSetter.setValue(this.invtyLocCd, pMsg.vndCd.getValue());
            }
        //if not Inter-Territorial
        } else {
            //QC#17768
            if (!ZYPCommonFunc.hasValue(this.svcMachMstrPk)) {
                String defWhOutboundKey = "O"
                    + this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd() 
                    + this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd()
                    + this.mdseCd.getValue()
                    + this.shipToPostCd.getValue()
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
                    //if (S21ApiUtil.getXxMsgList(pMsg).size() <= 0) {
                    //}
                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().put(defWhOutboundKey, pMsg);
                }
                
                NWZC180001PMsg nwzc180001PMsg = (NWZC180001PMsg) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().get(defWhOutboundKey);
                if (nwzc180001PMsg != null) {
                    ZYPEZDItemValueSetter.setValue(this.ordLineSrcCd, nwzc180001PMsg.ordLineSrcCd.getValue());
                    ZYPEZDItemValueSetter.setValue(this.rtlWhCd, nwzc180001PMsg.rtlWhCd.getValue());
                    ZYPEZDItemValueSetter.setValue(this.rtlSwhCd, nwzc180001PMsg.rtlSwhCd.getValue());
                    String dropShipRtlWhCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.DROP_SHIP_RTL_WH_CD.name());
                    if (dropShipRtlWhCd != null && dropShipRtlWhCd.equals(nwzc180001PMsg.rtlWhCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, nwzc180001PMsg.vndCd.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, nwzc180001PMsg.rtlWhCd.getValue() + nwzc180001PMsg.rtlSwhCd.getValue());
                    }
                }
            }
        }
        
        
        //this.serNum.clear();
        this.refCpoDtlLineNum.clear();
        this.refCpoDtlLineSubNum.clear();
        this.dplyLineRefNum.clear();
        this.crRebilCd.clear();

        // supply vendor
        if (NWAB412001Constant.DTL_TYPE.BUYOUT.equals(dtlType)) {

            //QC#18306
            if (S21StringUtil.isEquals(this.nwai4120_05.somPrcTpTxt.getValue(), "CFS")) {
            //if (!S21StringUtil.isEquals(this.nwai4120_05.somByotNm.getValue(), NWAB412001Constant.SOM_BUYOUT_NM_YOU)) {

                //CFS
                Map<String, Object> vnd = this.dsImptOrdConfigBean.getVndMap();
                if (vnd != null) {

                    ZYPEZDItemValueSetter.setValue(this.splyTpCd, SPLY_TP.CFS);
                    // 2019/10/04 QC#51372 Add Start
                    //ZYPEZDItemValueSetter.setValue(this.splyNm, (String) vnd.get("LOC_NM"));
                    String locNm = (String) vnd.get("LOC_NM");
                    if (locNm.length() > this.getAttr("prntVndNm").getDigit()) {
                        locNm = locNm.substring(0, this.getAttr("prntVndNm").getDigit());
                    }
                    ZYPEZDItemValueSetter.setValue(this.prntVndNm, locNm);
                    // 2019/10/04 QC#51372 Add End
                    ZYPEZDItemValueSetter.setValue(this.splyFirstAddr, (String) vnd.get("FIRST_LINE_ADDR"));
                    ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, (String) vnd.get("CTY_ADDR"));
                    ZYPEZDItemValueSetter.setValue(this.splyStCd, (String) vnd.get("ST_CD"));
                    ZYPEZDItemValueSetter.setValue(this.splyPostCd, (String) vnd.get("POST_CD"));
                }
            } else {

                // Customer
                ZYPEZDItemValueSetter.setValue(this.splyTpCd, SPLY_TP.CUSTOMER);
                // 2019/10/04 QC#51372 Mod Start
                //ZYPEZDItemValueSetter.setValue(this.splyNm, this.dsImptOrdBean.nwai4120_01.somShipToCustNm);
                ZYPEZDItemValueSetter.setValue(this.prntVndNm, this.dsImptOrdBean.nwai4120_01.somShipToCustNm);
                // 2019/10/04 QC#51372 Mod End
                if (ZYPCommonFunc.hasValue(this.nwai4120_05.somByotAddr_01)) {
                    ZYPEZDItemValueSetter.setValue(this.splyFirstAddr, this.nwai4120_05.somByotAddr_01);
                    ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, this.nwai4120_05.somByotCityTxt);
                    ZYPEZDItemValueSetter.setValue(this.splyStCd, this.nwai4120_05.somByotStCd);
                    ZYPEZDItemValueSetter.setValue(this.splyPostCd, this.nwai4120_05.somByotZipCd);
                } else {
                    // 2017/09/11 QC#21011 Add Start
                    NWAI4120_06TMsg nwai4120_06TMsg =  getSomRewriteAddrInfo();
                    // 2017/09/11 QC#21011 Add End
                    if (nwai4120_06TMsg == null) { // 2017/09/11 QC#21011 Add Condition
                        StringBuffer sbHeaderSpAddr = new StringBuffer();
                        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.shipToFirstLineAddr)) {
                            sbHeaderSpAddr.append(this.dsImptOrdBean.shipToFirstLineAddr.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.shipToScdLineAddr)) {
                            sbHeaderSpAddr.append(" ");
                            sbHeaderSpAddr.append(this.dsImptOrdBean.shipToScdLineAddr.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.shipToThirdLineAddr)) {
                            sbHeaderSpAddr.append(" ");
                            sbHeaderSpAddr.append(this.dsImptOrdBean.shipToThirdLineAddr.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.shipToFrthLineAddr)) {
                            sbHeaderSpAddr.append(" ");
                            sbHeaderSpAddr.append(this.dsImptOrdBean.shipToFrthLineAddr.getValue());
                        }
                        String headerSpAddr = sbHeaderSpAddr.toString();
                        if (ZYPCommonFunc.hasValue(headerSpAddr)) {
                            headerSpAddr = headerSpAddr.trim();
                            if (ZYPCommonFunc.hasValue(headerSpAddr)) {
                                headerSpAddr = cutOffString(headerSpAddr, 60);
                            }
                        }
    
                        ZYPEZDItemValueSetter.setValue(this.splyFirstAddr, headerSpAddr);
                        ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, this.dsImptOrdBean.shipToCtyAddr);
                        ZYPEZDItemValueSetter.setValue(this.splyStCd, this.dsImptOrdBean.shipToStCd);
                        ZYPEZDItemValueSetter.setValue(this.splyPostCd, this.dsImptOrdBean.shipToPostCd);
                    } else { // 2017/09/11 QC#21011 Add Start
                        StringBuffer sbHeaderSpAddr = new StringBuffer("");
                        if (ZYPCommonFunc.hasValue(nwai4120_06TMsg.pickUpAddr_01)) {
                            sbHeaderSpAddr.append(nwai4120_06TMsg.pickUpAddr_01.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(nwai4120_06TMsg.pickUpAddr_02)) {
                            if (sbHeaderSpAddr.toString().length() > 0) {
                                sbHeaderSpAddr.append(" ");
                            }
                            sbHeaderSpAddr.append(nwai4120_06TMsg.pickUpAddr_02.getValue());
                        }
                        String headerSpAddr = sbHeaderSpAddr.toString();
                        if (ZYPCommonFunc.hasValue(headerSpAddr)) {
                            headerSpAddr = headerSpAddr.trim();
                            if (ZYPCommonFunc.hasValue(headerSpAddr)) {
                                headerSpAddr = cutOffString(headerSpAddr, 60);
                            }
                        }
                        ZYPEZDItemValueSetter.setValue(this.splyFirstAddr, headerSpAddr);
                        ZYPEZDItemValueSetter.setValue(this.splyCtyAddr, nwai4120_06TMsg.pickUpCityAddr);
                        ZYPEZDItemValueSetter.setValue(this.splyStCd, nwai4120_06TMsg.pickUpStCd);
                        ZYPEZDItemValueSetter.setValue(this.splyPostCd, nwai4120_06TMsg.pickUpZipCd);
                    } // 2017/09/11 QC#21011 Add End
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
        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.csmpContrNum) && ZYPCommonFunc.hasValue(this.dsImptOrdBean.dlrRefNum)) {
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

        if (!ZYPCommonFunc.hasValue(this.imptLineFlg)) {

            ZYPEZDItemValueSetter.setValue(this.imptLineFlg, ZYPConstant.FLG_ON_Y);
        }

        for (SomDsImptPrcCalcBaseBean prcCalcBaseBean : this.dsImptPrcCalcBaseBeanList) {
            prcCalcBaseBean.doImptMapping(glblCmpyCd, salesDate);
        }
        ZYPEZDItemValueSetter.setValue(this.preExistFlg, ZYPConstant.FLG_OFF_N); // QC#16266
        ZYPEZDItemValueSetter.setValue(this.finItemLineFlg, ZYPConstant.FLG_ON_Y); // QC#16266

        return true;
    }

    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        BigDecimal transactionId = null;
        BigDecimal unitId = null;
        BigDecimal seqNumber = null;

        if (this.nwai4120_05 != null) {
            transactionId = this.nwai4120_05.transactionId.getValue();
            unitId = this.nwai4120_05.transactionId.getValue();
            seqNumber = this.nwai4120_05.transactionId.getValue();
        } else if (this.nwai4120_08 != null) {
            transactionId = this.nwai4120_08.transactionId.getValue();
            unitId = this.nwai4120_08.transactionId.getValue();
            seqNumber = this.nwai4120_08.transactionId.getValue();
        } else if (this.nwai4120_10 != null) {
            transactionId = this.nwai4120_10.transactionId.getValue();
            unitId = this.nwai4120_10.transactionId.getValue();
            seqNumber = this.nwai4120_10.transactionId.getValue();
        } else if (this.nwai4120_14 != null) {
            transactionId = this.nwai4120_14.transactionId.getValue();
            unitId = this.nwai4120_14.transactionId.getValue();
            seqNumber = this.nwai4120_14.transactionId.getValue();
        }

        this.errInfoList.add(new NWAB412001ErrorInfo(transactionId, unitId, seqNumber, errorInfo));
    }

    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            addErrorInfo(errorInfo);
        }
    }

    @Override
    public List<NWAB412001ErrorInfo> getErrorInfo() {
        return this.errInfoList;
    }

    @Override
    public boolean hasError() {
        return (this.getErrorInfo().size() > 0);
    }
    //QC#18211
    public void setTempBaseCmptFlg(String tempBaseCmptFlg) {
        this.tempBaseCmptFlg = tempBaseCmptFlg;
    }
    private String cutOffString(String val, int len) {
        if (ZYPCommonFunc.hasValue(val) && val.length() > len) {
            return val.substring(0, len);
        } else {
            return val;
        }
    }

    // 2017/09/11 QC#21011 Add Start
    private NWAI4120_06TMsg getSomRewriteAddrInfo() {

        BigDecimal somSoftCostId = nwai4120_05.somSoftCostId.getValue();
        if (!ZYPCommonFunc.hasValue(somSoftCostId)) {
            return null;
        }
        NWAI4120_06TMsg reWriteNwai4120_06TMsg = null;
        NWAI4120_06TMsg noReWriteNwai4120_06TMsg = null;
        NWAI4120_06TMsg noReWriteNwai4120_06TMsg2 = null;
        for (NWAI4120_06TMsg nwai4120_06TMsg : nwai4120_06List) {
            BigDecimal somSoftCostItemId = nwai4120_06TMsg.somSoftCostItemId.getValue();
            if (!ZYPCommonFunc.hasValue(somSoftCostItemId)) {
                continue;
            }
            if (somSoftCostId.compareTo(somSoftCostItemId) != 0) {
                continue;
            }
            if (S21StringUtil.isEquals("YES", nwai4120_06TMsg.pickUpDtSameTxt.getValue()) //
                    && ZYPCommonFunc.hasValue(nwai4120_06TMsg.pickUpAddr_01) //
                    && reWriteNwai4120_06TMsg == null) {
                reWriteNwai4120_06TMsg = new NWAI4120_06TMsg();
                EZDMsg.copy(nwai4120_06TMsg, null, reWriteNwai4120_06TMsg, null);
                break;
            }
            if (!S21StringUtil.isEquals("YES", nwai4120_06TMsg.pickUpDtSameTxt.getValue()) //
                    && (S21StringUtil.isEquals("0", nwai4120_06TMsg.somDescSerNum.getValue())) //
                    && ZYPCommonFunc.hasValue(nwai4120_06TMsg.pickUpAddr_01) //
                    && noReWriteNwai4120_06TMsg == null) {
                noReWriteNwai4120_06TMsg = new NWAI4120_06TMsg();
                EZDMsg.copy(nwai4120_06TMsg, null, noReWriteNwai4120_06TMsg, null);
            }
            if (!S21StringUtil.isEquals("YES", nwai4120_06TMsg.pickUpDtSameTxt.getValue()) //
                    && (!ZYPCommonFunc.hasValue(nwai4120_06TMsg.somDescSerNum)) //
                    && ZYPCommonFunc.hasValue(nwai4120_06TMsg.pickUpAddr_01) //
                    && noReWriteNwai4120_06TMsg2 == null) {
                noReWriteNwai4120_06TMsg2 = new NWAI4120_06TMsg();
                EZDMsg.copy(nwai4120_06TMsg, null, noReWriteNwai4120_06TMsg2, null);
            }
        }
        if (reWriteNwai4120_06TMsg != null) {
            return reWriteNwai4120_06TMsg;
        } else if (noReWriteNwai4120_06TMsg != null) {
            return noReWriteNwai4120_06TMsg;
        } else if (noReWriteNwai4120_06TMsg2 != null) {
            return noReWriteNwai4120_06TMsg2;
        } else {
            return null;
        }
    }
    // 2017/09/11 QC#21011 Add End
}

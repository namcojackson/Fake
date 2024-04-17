/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.NWAI4120_05TMsg;
import business.db.NWAI4120_06TMsg;
import business.db.NWAI4120_14TMsg;
import business.parts.NWZC180001PMsg;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdRtrnDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 08/22/2017   SRAA            K.Aratani       Update          QC#20739
 * 2018/01/15   Fujitsu         S.Ohki          Update          QC#22372
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24078, QC#24093
 * 02/16/2018   SRAA            K.Aratani       Update          QC#24206
 *</pre>
 */
public class SomDsImptOrdRtrnDtlBean extends DS_IMPT_ORD_RTRN_DTLTMsg implements ISomDsImptOrd, ISomErrorInfo {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.RTRN_DTL_TYPE rtrnDtlType;

    public final SomDsImptOrdBean dsImptOrdBean;

    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public final List<SomDsImptRtrnPrcCalcBean> dsImptRtrnPrcCalcBeanList;

    public NWAI4120_05TMsg nwai4120_05;

    public NWAI4120_06TMsg nwai4120_06;

    public NWAI4120_14TMsg nwai4120_14;

    /** List<NWAB412001ErrorInfo> */
    final public List<NWAB412001ErrorInfo> errInfoList;

    public SomDsImptOrdRtrnDtlBean(NWAB412001Constant.RTRN_DTL_TYPE rtrnDtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_05TMsg nwai4120_05, NWAI4120_06TMsg nwai4120_06) {
        this(rtrnDtlType, dsImptOrdConfigBean);

        this.nwai4120_05 = nwai4120_05;
        this.nwai4120_06 = nwai4120_06;
    }

    public SomDsImptOrdRtrnDtlBean(NWAB412001Constant.RTRN_DTL_TYPE rtrnDtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean, NWAI4120_14TMsg nwai4120_14) {
        this(rtrnDtlType, dsImptOrdConfigBean);

        this.nwai4120_14 = nwai4120_14;
    }

    private SomDsImptOrdRtrnDtlBean(NWAB412001Constant.RTRN_DTL_TYPE rtrnDtlType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.rtrnDtlType = rtrnDtlType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        this.dsImptOrdConfigBean.dsImptOrdDtlRtrnBeanList.add(this);
        this.dsImptRtrnPrcCalcBeanList = new ArrayList<SomDsImptRtrnPrcCalcBean>();
        this.errInfoList = new ArrayList<NWAB412001ErrorInfo>();
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdRtrnDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_RTRN_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);

        // order source line reference number
        this.ordSrcRefLineSubNum.clear();
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(this.dsOrdPosnNum, this.dsImptOrdConfigBean.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(this.dsCpoLineCatgCd, this.dsImptOrdBean.getInBndDsOrdLineCatgCd());
        this.ordLineSrcCd.clear();

        // merchandise
        String mdseCd = null;
        String mdseNm = null;
        Map<String, Object> mdse = null;
        String serialNumber = null;
        if (NWAB412001Constant.RTRN_DTL_TYPE.OCE_PROMO.equals(rtrnDtlType)) {

            if (ZYPCommonFunc.hasValue(this.nwai4120_14.somPrmoSerNum)) {
                mdse = NWXC412001.getMdseBySerNum(glblCmpyCd, this.nwai4120_14.somPrmoSerNum.getValue(), salesDate);
                if (mdse != null) {

                    mdseCd = (String) mdse.get("MDSE_CD");
                }
                serialNumber = this.nwai4120_14.somPrmoSerNum.getValue();
            }
        } else if (NWAB412001Constant.RTRN_DTL_TYPE.TRADE_IN_RMA.equals(rtrnDtlType)) {

            if (ZYPCommonFunc.hasValue(this.nwai4120_05.somDescSerNum)) {
                mdse = NWXC412001.getMdseBySerNum(glblCmpyCd, this.nwai4120_05.somDescSerNum.getValue(), salesDate);
                if (mdse != null) {

                    mdseCd = (String) mdse.get("MDSE_CD");
                    
                } else {

                    mdseCd = S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16);

                    if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().containsKey(mdseCd)) {
                        mdse = NWXC412001.getMdse(glblCmpyCd, mdseCd);
                        this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().put(mdseCd, mdse);
                    } else {
                        mdse = (Map<String, Object>) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().get(mdseCd);
                    }
                    if (mdse == null) {

                        mdse = NWXC412001.getSoftCostItemInfo(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), this.nwai4120_05.somMercItemCd.getValue(), this.nwai4120_05.quoteLineTpTxt.getValue());

                        if (mdse != null) {

                            mdseCd = (String) mdse.get("TRGT_ATTRB_TXT_04");
                        }
                    }
                }
                serialNumber = this.nwai4120_05.somDescSerNum.getValue();
            } else {

                mdseCd = S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16);

                if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().containsKey(mdseCd)) {
                    mdse = NWXC412001.getMdse(glblCmpyCd, mdseCd);
                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().put(mdseCd, mdse);
                } else {
                    mdse = (Map<String, Object>) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().get(mdseCd);
                }
                if (mdse == null) {

                    mdse = NWXC412001.getSoftCostItemInfo(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), this.nwai4120_05.somMercItemCd.getValue(), this.nwai4120_05.quoteLineTpTxt.getValue());

                    if (mdse != null) {

                        mdseCd = (String) mdse.get("TRGT_ATTRB_TXT_04");
                    }
                }
            }
        } else if (NWAB412001Constant.RTRN_DTL_TYPE.BUYOUT_RMA.equals(rtrnDtlType)) {

            if (this.nwai4120_06 != null) {

                if (ZYPCommonFunc.hasValue(this.nwai4120_06.somDescSerNum)) {

                    mdse = NWXC412001.getMdseBySerNum(glblCmpyCd, this.nwai4120_06.somDescSerNum.getValue(), salesDate);

                    serialNumber = this.nwai4120_06.somDescSerNum.getValue();
                    
                }
            }
            if (mdse != null) {

                mdseCd = (String) mdse.get("MDSE_CD");
            } else {

                mdseCd = S21StringUtil.subStringByLength(this.nwai4120_05.somMercItemCd.getValue(), 0, 16);

                if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().containsKey(mdseCd)) {
                    mdse = NWXC412001.getMdse(glblCmpyCd, mdseCd);
                    this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().put(mdseCd, mdse);
                } else {
                    mdse = (Map<String, Object>) this.dsImptOrdConfigBean.dsImptOrdBean.getCacheItemMap().get(mdseCd);
                }
                if (mdse == null) {

                    mdse = NWXC412001.getSoftCostItemInfo(glblCmpyCd, CPO_SRC_TP.SOM, NWAB412001Constant.INTERFACE_ID.NWAI4120.name(), this.nwai4120_05.somMercItemCd.getValue(), this.nwai4120_05.quoteLineTpTxt.getValue());

                    if (mdse != null) {

                        mdseCd = (String) mdse.get("TRGT_ATTRB_TXT_04");
                    }
                }
            }

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.UPGRADE_RETURN_RMA.equals(rtrnDtlType)) {

        	if (this.nwai4120_06 != null) {

                if (ZYPCommonFunc.hasValue(this.nwai4120_06.somDescSerNum)) {

                    mdse = NWXC412001.getMdseBySerNum(glblCmpyCd, this.nwai4120_06.somDescSerNum.getValue(), salesDate);
                    
                    serialNumber = this.nwai4120_06.somDescSerNum.getValue();
                }
            }
            if (mdse != null) {

                mdseCd = (String) mdse.get("MDSE_CD");
            } else {

                mdseCd = (String) dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_MDSE_UPGRADE_RETURN_RMA.name());
            }

        } else {

            // none
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
        ZYPEZDItemValueSetter.setValue(this.mdseNm, mdseNm);

        this.origMdseCd.clear();
        this.origMdseNm.clear();
        this.custMdseCd.clear();
        this.setMdseCd.clear();
        ZYPEZDItemValueSetter.setValue(this.baseCmptFlg, ZYPConstant.FLG_OFF_N);
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
        ZYPEZDItemValueSetter.setValue(this.custUomCd, PKG_UOM.EACH);

        // order quantity
        ZYPEZDItemValueSetter.setValue(this.ordQty, BigDecimal.ONE.negate());

        ZYPEZDItemValueSetter.setValue(this.ordCustUomQty, this.ordQty);
        this.invtyLocCd.clear();
        this.rtlWhCd.clear();
        this.rtlSwhCd.clear();
        ZYPEZDItemValueSetter.setValue(this.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(this.prcBaseDt, NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.cratTs.getValue()));

        // floor price list
        ZYPEZDItemValueSetter.setValue(this.flPrcListCd, this.dsImptOrdConfigBean.dsImptOrdBean.flPrcListCd);

        // price list
        // QC#24078, QC#24093
        // RMA price list from floor price list
        String rmaDefPrcListCd = (String) this.dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.SOM_RMA_DEF_PRC_LIST.name());
        if (ZYPCommonFunc.hasValue(rmaDefPrcListCd)) {
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, rmaDefPrcListCd);
        } else {
            ZYPEZDItemValueSetter.setValue(this.prcCatgCd, this.dsImptOrdConfigBean.dsImptOrdBean.prcCatgCd);
        }

        // deal amount
        if (NWAB412001Constant.RTRN_DTL_TYPE.OCE_PROMO.equals(rtrnDtlType)) {

            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, this.nwai4120_14.somPrmoAmt);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, BigDecimal.ZERO);

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.TRADE_IN_RMA.equals(rtrnDtlType)) {

            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, BigDecimal.ZERO);

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.BUYOUT_RMA.equals(rtrnDtlType)) {

            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, BigDecimal.ZERO);

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.UPGRADE_RETURN_RMA.equals(rtrnDtlType)) {

            ZYPEZDItemValueSetter.setValue(this.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.cpoDtlDealSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(this.dealPrcListPrcAmt, BigDecimal.ZERO);

        } else {

            // none
        }

        // 2018/01/15 S21_NA#22372 Add Start
        this.funcUnitFlPrcAmt.clear();
        // 2018/01/15 S21_NA#22372 Add End

        // function amount
        this.entFuncNetUnitPrcAmt.clear();
        this.cpoDtlFuncNetAmt.clear();
        this.cpoDtlFuncSlsAmt.clear();
        this.funcPrcListPrcAmt.clear();

        ZYPEZDItemValueSetter.setValue(this.ccyCd, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(this.exchRate, BigDecimal.ZERO);
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
        //this.serNum.clear();
        ZYPEZDItemValueSetter.setValue(this.serNum, serialNumber);

        //QC#20739
        // request pick up date
        if (NWAB412001Constant.RTRN_DTL_TYPE.OCE_PROMO.equals(rtrnDtlType)) {

            ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.nwai4120_14.pickUpDtOthDescTxt.getValue()));

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.TRADE_IN_RMA.equals(rtrnDtlType)) {

            //ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.nwai4120_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.getPickUpDtOthDescTxt()));

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.BUYOUT_RMA.equals(rtrnDtlType)) {

            //ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.nwai4120_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.getPickUpDtOthDescTxt()));

        } else if (NWAB412001Constant.RTRN_DTL_TYPE.UPGRADE_RETURN_RMA.equals(rtrnDtlType)) {

            //ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.nwai4120_05.erlstDelyTs.getValue()));
            ZYPEZDItemValueSetter.setValue(this.rqstPickUpDt, NWXC412001.getValidDate(this.dsImptOrdConfigBean.getPickUpDtOthDescTxt()));

        } else {

            // none
        }

        this.origCpoOrdNum.clear();
        this.origInvNum.clear();
        ZYPEZDItemValueSetter.setValue(this.svcConfigMstrPk, this.dsImptOrdConfigBean.svcConfigMstrPk);
        this.dsContrNum.clear();
        this.dsContrSqNum.clear();
        ZYPEZDItemValueSetter.setValue(this.svcMachMstrPk, this.dsImptOrdConfigBean.getSvcConfigMstrPk());
        this.refCpoDtlLineNum.clear();
        this.refCpoDtlLineSubNum.clear();
        this.dplyLineRefNum.clear();
        this.csmpContrNum.clear();
        this.dlrRefNum.clear();
        this.csmpPrcListCd.clear();
        this.hddRmvCd.clear();
        
        // request pick up date
        if (NWAB412001Constant.RTRN_DTL_TYPE.OCE_PROMO.equals(rtrnDtlType)) {

            ZYPEZDItemValueSetter.setValue(this.rtrnRsnCd, RTRN_RSN.REGULAR_RETURN);

        } else {

            ZYPEZDItemValueSetter.setValue(this.rtrnRsnCd, this.dsImptOrdConfigBean.getRtrnRsnCd());
        }
        //QC#24206
        //if IB owner is CFS, update to return cfs
        if (NWXC412001.isCFSOwnerInstallBase(this.glblCmpyCd.getValue(), this.svcMachMstrPk.getValue())) {
            ZYPEZDItemValueSetter.setValue(this.rtrnRsnCd, RTRN_RSN.CANON_FINANCIAL_LEASE_RETURN);
        }

        ZYPEZDItemValueSetter.setValue(this.unitNetWt, NWXC412001.getUnitNetWt(glblCmpyCd, this.mdseCd.getValue(), this.custUomCd.getValue(), this.ordCustUomQty.getValue()));
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
        
        String defWhOutboundKey = "I"
            + this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd() 
            + this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd()
            + this.mdseCd.getValue()
            + this.rtrnRsnCd.getValue()
            + this.shipToPostCd.getValue()
            + this.svcMachMstrPk.getValue()
            + this.ordQty.getValue();
        if (!this.dsImptOrdConfigBean.dsImptOrdBean.getCacheDefaultWHMap().containsKey(defWhOutboundKey)) {
            
            NWZC180001PMsg pMsg = new NWZC180001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.dsImptOrdConfigBean.dsImptOrdBean.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdCatgCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, this.dsImptOrdConfigBean.dsImptOrdBean.getDsOrdTpCd());
            ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, this.rtrnRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, this.svcMachMstrPk.getValue());
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
        

        for (SomDsImptRtrnPrcCalcBean rtrnPrcCalcBean : this.dsImptRtrnPrcCalcBeanList) {
            rtrnPrcCalcBean.doImptMapping(glblCmpyCd, salesDate);
        }

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
}

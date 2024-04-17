/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.NWAI4150_16TMsg;
import business.db.NWAI4150_18TMsg;
import business.db.NWAI4150_19TMsg;
import business.db.NWAI4150_22TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.CUTOFF_LEN;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.EOPS_CONTR_IND;
import com.canon.cusa.s21.batch.NWA.NWAB415001.constant.NWAB415001Constant.INTERFACE_ID;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * EOPS Interface Batch EopsDsImptSvcDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 * 2018/02/08   Hitachi         K.Kojima        Update          QC#23357
 * 
 * </pre>
 */
public class EopsDsImptSvcDtlBean extends DS_IMPT_SVC_DTLTMsg implements IEopsDsImptOrd {

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    public final NWAB415001Constant.SHELL_TYPE shellType;

    /**  */
    public final NWAB415001Constant.CONTRACT_TYPE contractType;

    /**  */
    public EopsDsImptOrdBean dsImptOrdBean;

    /**  */
    public final List<EopsDsImptSvcConfigRefBean> dsImptSvcConfigRefBeanList;

    /**  */
    public final List<EopsDsImptSvcPrcBean> dsImptSvcPrcBeanList;

    //    public final List<EopsDsImptSvcAddlBaseBean> dsImptSvcAddlBaseBeanList;
    //    public final List<EopsDsImptSvcAddlChrgBean> dsImptSvcAddlChrgBeanList;

    /**  */
    public final NWAI4150_16TMsg nwai4150_16;

    /**  */
    public NWAI4150_18TMsg nwai4150_18;

    /**  */
    public NWAI4150_19TMsg nwai4150_19;

    /**  */
    public NWAI4150_22TMsg nwai4150_22;

    /**  */
    private BigDecimal baseBllgCycleMthAot;

    /**  */
    private BigDecimal usgBllgCycleMthAot;

    /**  */
    private String billToCustAcctCdForPrc;

    /**  */
    private String billToCustCdForPrc;

    /**  */
    private BigDecimal reqBaseAmt;

    /**
     * EopsDsImptSvcDtlBean
     * @param shellType     NWAB415001Constant.SHELL_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415016    NWAI4150_16TMsg
     * @param nwai415018    NWAI4150_18TMsg
     * @param nwai415022    NWAI4150_22TMsg
     * @param contractType  NWAB415001Constant.CONTRACT_TYPE
     */
    public EopsDsImptSvcDtlBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_16TMsg nwai415016, NWAI4150_18TMsg nwai415018, NWAI4150_22TMsg nwai415022, NWAB415001Constant.CONTRACT_TYPE contractType) {

        this(shellType, dsImptOrdBean, nwai415016, contractType);
        this.nwai4150_18 = nwai415018;
        this.nwai4150_22 = nwai415022;
    }

    /**
     * EopsDsImptSvcDtlBean
     * @param shellType     NWAB415001Constant.SHELL_TYPE
     * @param dsImptOrdBean EopsDsImptOrdBean
     * @param nwai415016    NWAI4150_16TMsg
     * @param nwai415019    NWAI4150_19TMsg
     * @param contractType  NWAB415001Constant.CONTRACT_TYPE
     */
    public EopsDsImptSvcDtlBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_16TMsg nwai415016, NWAI4150_19TMsg nwai415019, NWAB415001Constant.CONTRACT_TYPE contractType) {

        this(shellType, dsImptOrdBean, nwai415016, contractType);
        this.nwai4150_19 = nwai415019;
    }

    private EopsDsImptSvcDtlBean(//
            NWAB415001Constant.SHELL_TYPE shellType, EopsDsImptOrdBean dsImptOrdBean, NWAI4150_16TMsg nwai415016, NWAB415001Constant.CONTRACT_TYPE contractType) {
        super();

        this.shellType = shellType;
        this.contractType = contractType;
        this.dsImptSvcPrcBeanList = new ArrayList<EopsDsImptSvcPrcBean>();
        this.dsImptSvcConfigRefBeanList = new ArrayList<EopsDsImptSvcConfigRefBean>();
        this.nwai4150_16 = nwai415016;

        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdBean.dsImptSvcDtlBeanList.add(this);

        this.billToCustAcctCdForPrc = "";
        this.billToCustCdForPrc = "";
    }

    /**
     * doImptMapping
     * @param glblCmpyCd    glblCmpyCd
     * @param salesDate     salesDate
     * @return boolean
     */
    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {
        String intfcId = INTERFACE_ID.NWAI4150.name();
        String cpoSrcTpCd = CPO_SRC_TP.EOPS;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);

        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            String contrTpDescTxt = this.nwai4150_18.eopsContrTpDescTxt.getValue();
            String planDescTxt = this.nwai4150_18.eopsPlanDescTxt.getValue();
            String baseBllgCycleDescTxt = this.nwai4150_18.somBllgCycleDescTxt.getValue();
            String usgBllgCycleDescTxt = this.nwai4150_18.usgCycleDescTxt.getValue();
            String dsImptSvcMdseCd = NWXC412001.getDsImptSvcMdseCd(//
                    glblCmpyCd //
                    , this.dsImptOrdBean.cpoSrcTpCd.getValue() //
                    , this.dsImptOrdBean.nwai415001.interfaceId.getValue() //
                    , this.dsImptOrdBean.nwai415001.eopsTrxTpTxt.getValue() //
                    , NWAB415001Constant.SHELL_TYPE_KEY.COPIER.getValue() //"Copier" //
                    , null //
                    , this.nwai4150_18.dclnMaintIndSomTxt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcMdseCd, dsImptSvcMdseCd);
            ZYPEZDItemValueSetter.setValue(//
                    this.prcSvcContrTpCd, NWXC412001.getPrcSvcContrTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, contrTpDescTxt));
            ZYPEZDItemValueSetter.setValue(//
                    this.prcSvcPlnTpCd, NWXC412001.getPrcSvcPlnTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, planDescTxt));

            String baseBllgCycleCd = NWXC412001.getBllgCycleCd(glblCmpyCd, cpoSrcTpCd, intfcId, baseBllgCycleDescTxt);
            if (baseBllgCycleCd != null) {
                BLLG_CYCLETMsg bllgCycle = new BLLG_CYCLETMsg();
                ZYPEZDItemValueSetter.setValue(bllgCycle.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bllgCycle.bllgCycleCd, baseBllgCycleCd);
                bllgCycle = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bllgCycle);
                if (bllgCycle != null) {
                    this.setBaseBllgCycleMthAot(bllgCycle.bllgCycleMthAot.getValue());
                }
            }
            ZYPEZDItemValueSetter.setValue(this.baseBllgCycleCd, baseBllgCycleCd);
            //QC#19993
            String usgBllgCycleCd = NWXC412001.getBllgCycleCd(glblCmpyCd, cpoSrcTpCd, intfcId, usgBllgCycleDescTxt);
            if (usgBllgCycleCd != null) {
                BLLG_CYCLETMsg bllgCycle = new BLLG_CYCLETMsg();
                ZYPEZDItemValueSetter.setValue(bllgCycle.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bllgCycle.bllgCycleCd, usgBllgCycleCd);
                bllgCycle = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bllgCycle);
                if (bllgCycle != null) {
                    this.setUsgBllgCycleMthAot(bllgCycle.bllgCycleMthAot.getValue());
                }
            }
            ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, usgBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(this.toPerMthNum, this.nwai4150_18.somSrvTermId);
            ZYPEZDItemValueSetter.setValue(this.termMthAot, this.nwai4150_18.somSrvTermId);

            BigDecimal baseAmt //
            = new BigDecimal(this.nwai4150_22.reqBaseTxt.getValue()).multiply(this.nwai4150_18.somSrvTermId.getValue());
            ZYPEZDItemValueSetter.setValue(this.totBasePrcDealAmt, baseAmt);
            ZYPEZDItemValueSetter.setValue(this.totBasePrcFuncAmt, baseAmt);

        } else if (NWAB415001Constant.SHELL_TYPE.NON_COPIER.equals(this.shellType)) {

            String dsImptSvcMdseCd = NWXC412001.getDsImptSvcMdseCd(//
                    glblCmpyCd //
                    , this.dsImptOrdBean.cpoSrcTpCd.getValue() //
                    , this.dsImptOrdBean.nwai415001.interfaceId.getValue() //
                    , this.dsImptOrdBean.nwai415001.eopsTrxTpTxt.getValue() //
                    , NWAB415001Constant.SHELL_TYPE_KEY.NON_COPIER.getValue() // "Non-Copier" //
                    , null //
                    , this.nwai4150_19.dclnMaintIndSomTxt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcMdseCd, dsImptSvcMdseCd);
            ZYPEZDItemValueSetter.setValue(this.prcSvcContrTpCd, PRC_SVC_CONTR_TP.SERVICE_ONLY);
            ZYPEZDItemValueSetter.setValue(this.prcSvcPlnTpCd, PRC_SVC_PLN_TP.FIXED);
            ZYPEZDItemValueSetter.setValue(this.baseBllgCycleCd, BLLG_CYCLE.MONTHLY);
            BLLG_CYCLETMsg bllgCycle = new BLLG_CYCLETMsg();
            ZYPEZDItemValueSetter.setValue(bllgCycle.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bllgCycle.bllgCycleCd, this.baseBllgCycleCd);
            bllgCycle = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bllgCycle);
            if (bllgCycle != null) {
                this.setBaseBllgCycleMthAot(bllgCycle.bllgCycleMthAot.getValue());
            }
            this.usgBllgCycleCd.clear();
            ZYPEZDItemValueSetter.setValue(this.toPerMthNum, this.nwai4150_19.somDurnId);
            ZYPEZDItemValueSetter.setValue(this.termMthAot, this.nwai4150_19.somDurnId);

            BigDecimal baseAmt = new BigDecimal(this.nwai4150_22.reqBaseTxt.getValue()).multiply(this.nwai4150_19.somDurnId.getValue());
            ZYPEZDItemValueSetter.setValue(this.totBasePrcDealAmt, baseAmt);
            ZYPEZDItemValueSetter.setValue(this.totBasePrcFuncAmt, baseAmt);
        }
        //        }
        String isFltIndFlg = null;
        if (NWAB415001Constant.EOPS_CONTR_IND.N.name().equals(this.nwai4150_16.contrIndSomTxt.getValue())) {
            if (ZYPCommonFunc.hasValue(nwai4150_16.fleetPlanDescTxt)) {
                isFltIndFlg = ZYPConstant.FLG_ON_Y;
            } else {
                isFltIndFlg = ZYPConstant.FLG_OFF_N;
            }
        }
        ZYPEZDItemValueSetter.setValue(//
                this.dsContrCatgCd, NWXC412001.getDsContrCatgCd(//
                        glblCmpyCd //
                        , cpoSrcTpCd //
                        , intfcId //
                        , this.nwai4150_16.contrIndSomTxt.getValue() //
                        , isFltIndFlg));

        ZYPEZDItemValueSetter.setValue(this.fromPerMthNum, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        //        if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
        //            if (S21StringUtil.isEquals(this.nwai4150_18.svcContrIndSomTxt.getValue(), NWAB415001Constant.FLG.YES.name())) {
        //                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_ON_Y);
        //            } else {
        //                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        //            }
        //        } else if (NWAB415001Constant.SHELL_TYPE.NON_COPIER.equals(this.shellType)) {
        //            if (S21StringUtil.isEquals(this.nwai4150_19.svcContrIndSomTxt.getValue(), NWAB415001Constant.FLG.YES.name())) {
        //                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_ON_Y);
        //            } else {
        //                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        //            }
        //        }

        String fleetBillByTxt = this.nwai4150_16.fleetBillByTxt.getValue();
        if (!ZYPCommonFunc.hasValue(fleetBillByTxt)) {
            fleetBillByTxt = NWAB415001Constant.FLEET_BILL_BY_TXT.CSA.name();
        }
        String billByTpCd = NWXC412001.getBillByTpCd(//
                glblCmpyCd, cpoSrcTpCd, intfcId, fleetBillByTxt, dsImptOrdBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(this.billByTpCd, billByTpCd);

        //QC#19968
        if (S21StringUtil.isEquals(//
                this.dsImptOrdBean.nwai415001.maintBillIndSomTxt.getValue(), NWAB415001Constant.FLG.YES.name())) {

            String zip //
            = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.maintBllgZipTxt.getValue(), 0, CUTOFF_LEN.ZIP_CD.getLen());
            String address //
            = S21StringUtil.concatStrings(//
                    NWXC412001.toUpperCase(this.dsImptOrdBean.nwai415001.maintBllgAddrDescTxt.getValue()) //
                    , NWXC412001.toUpperCase(this.dsImptOrdBean.nwai415001.maintBllgCityDescTxt.getValue()) //
                    , NWXC412001.toUpperCase(this.dsImptOrdBean.nwai415001.maintBllgStTxt.getValue()) //
                    , NWXC412001.toUpperCase(zip));
            Map<String, Object> soldTo = NWXC412001.getSoldToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai415001.bllgCmpyTxt.getValue());

            if (soldTo != null && !soldTo.isEmpty()) {

                ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, (String) soldTo.get("BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(this.sellToCustCd, (String) soldTo.get("DS_ACCT_NUM"));
            } else {

                ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.soldToCustLocCd);
                ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.sellToCustCd);
            }
        } else {

            ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.sellToCustCd);
        }

        this.svcAgmtVrsnNum.clear();
        ZYPEZDItemValueSetter.setValue(this.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        this.manContrOvrdRsnCd.clear();
        this.manContrOvrdCmntTxt.clear();

        ZYPEZDItemValueSetter.setValue(this.useEquipBillToFlg, ZYPConstant.FLG_OFF_N);
        this.svcPrcCatgCd.clear();

        if (S21StringUtil.isEquals(fleetBillByTxt, NWAB415001Constant.FLEET_BILL_BY_TXT.CFS.name())) {

            // CFS
            // START 2018/02/08 K.Kojima [QC#23357,MOD]
            // Map<String, Object> defMaintCustMap //
            // = NWXC412001.getDefaultMaintenanceCustomer(glblCmpyCd, dsImptOrdBean.getDsOrdCatgCd(), billByTpCd);
            Map<String, Object> defMaintCustMap //
            = NWXC412001.getDefaultMaintenanceCustomer(glblCmpyCd, dsImptOrdBean.getDsOrdCatgCd(), dsImptOrdBean.getDsOrdTpCd(), billByTpCd);
            // END 2018/02/08 K.Kojima [QC#23357,MOD]
            if (defMaintCustMap != null && defMaintCustMap.get("SPEC_DS_ACCT_NUM") != null) {
                this.billToCustAcctCdForPrc = (String) defMaintCustMap.get("SPEC_DS_ACCT_NUM");
            }
            if (defMaintCustMap != null && defMaintCustMap.get("SPEC_BILL_TO_LOC_CD") != null) {
                this.billToCustCdForPrc = (String) defMaintCustMap.get("SPEC_BILL_TO_LOC_CD");
            }

        } else {

            this.billToCustAcctCdForPrc = this.sellToCustCd.getValue();
            this.billToCustCdForPrc = this.soldToCustLocCd.getValue();
        }

        //        //QC#17995
        //        if (ZYPCommonFunc.hasValue(this.soldToCustLocCd) && ZYPCommonFunc.hasValue(this.dsImptOrdBean.billToCustCd) && this.soldToCustLocCd.getValue().equals(this.dsImptOrdBean.billToCustCd.getValue())
        //                && ZYPCommonFunc.hasValue(this.sellToCustCd) && ZYPCommonFunc.hasValue(this.dsImptOrdBean.billToCustAcctCd) && this.sellToCustCd.getValue().equals(this.dsImptOrdBean.billToCustAcctCd.getValue())) {
        //            ZYPEZDItemValueSetter.setValue(this.useEquipBillToFlg, ZYPConstant.FLG_ON_Y);
        //        } else {
        //            ZYPEZDItemValueSetter.setValue(this.useEquipBillToFlg, ZYPConstant.FLG_OFF_N);
        //        }

        //In case of Fleet or Aggregate Only
        if (EOPS_CONTR_IND.F.toString().equals(nwai4150_16.contrIndSomTxt.getValue()) //
                || EOPS_CONTR_IND.P.toString().equals(nwai4150_16.contrIndSomTxt.getValue())) {
            ZYPEZDItemValueSetter.setValue(this.dsContrPk, NWXC412001.getDsContrPkByContrNum(glblCmpyCd, this.nwai4150_16.somContrNum.getValue(), salesDate));
            if (EOPS_CONTR_IND.F.toString().equals(nwai4150_16.contrIndSomTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.FLEET);
            } else if (EOPS_CONTR_IND.P.toString().equals(nwai4150_16.contrIndSomTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.AGGREGATE);
            }
        } else {
            this.dsContrPk.clear();
        }

        BigDecimal calcBasePrcAmt = BigDecimal.ZERO;
        //Fleet
        if (DS_CONTR_CATG.FLEET.equals(this.dsContrCatgCd.getValue())) {
            this.reqBaseAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(this.nwai4150_16.fleetBwReqBaseAmt)) {
                this.reqBaseAmt = this.nwai4150_16.fleetBwReqBaseAmt.getValue();
            }
            if (ZYPCommonFunc.hasValue(this.nwai4150_16.fleetClrReqBaseAmt)) {
                this.reqBaseAmt = this.reqBaseAmt.add(this.nwai4150_16.fleetClrReqBaseAmt.getValue());
            }
            calcBasePrcAmt = this.reqBaseAmt.multiply(this.nwai4150_16.fleetTermNum.getValue());
        } else {
            if (NWAB415001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
                calcBasePrcAmt = this.reqBaseAmt.multiply(this.nwai4150_18.somSrvTermId.getValue());
            } else {
                calcBasePrcAmt = this.reqBaseAmt;
            }
        }

        for (EopsDsImptSvcPrcBean svcPrcBean : this.dsImptSvcPrcBeanList) {
            svcPrcBean.doImptMapping(glblCmpyCd, salesDate);
        }

        for (EopsDsImptSvcConfigRefBean svcConfigRefBean : this.dsImptSvcConfigRefBeanList) {
            svcConfigRefBean.doImptMapping(glblCmpyCd, salesDate);
        }

        ZYPEZDItemValueSetter.setValue(this.totBasePrcDealAmt, calcBasePrcAmt);
        ZYPEZDItemValueSetter.setValue(this.totBasePrcFuncAmt, calcBasePrcAmt);

        // 2017/01/30 S21_NA#17119 Add Start
        //        String sldToLocNm = null;
        //        String sldToAddlLocNm = null;
        String sldToFirstLineAddr = null;
        String sldToScdLineAddr = null;
        String sldToThirdLineAddr = null;
        String sldToFrthLineAddr = null;
        //        String sldToFirstRefCmntTxt = null;
        //        String sldToScdRefCmntTxt = null;
        String sldToCtyAddr = null;
        String sldToStCd = null;
        //        String sldToProvNm = null;
        String sldToCntyNm = null;
        String sldToPostCd = null;
        String sldToCtryCd = null;
        //        String sldToAcctNm = null;
        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai415001.bllgAddrDescTxt)) {

            if (this.dsImptOrdBean.nwai415001.bllgAddrDescTxt.getValue().length() <= CUTOFF_LEN.LINE_ADDR.getLen()) {
                sldToFirstLineAddr = this.dsImptOrdBean.nwai415001.bllgAddrDescTxt.getValue();

            } else {
                String[] sldToLineAddrs = this.dsImptOrdBean.nwai415001.bllgAddrDescTxt.getValue().split(" ");
                StringBuffer[] addrs //
                = {new StringBuffer(""), new StringBuffer(""), new StringBuffer(""), new StringBuffer("") };
                int j = 0;

                for (int i = 0; i < sldToLineAddrs.length; i++) {
                    if (addrs[j].length() + sldToLineAddrs[i].length() + 1 > CUTOFF_LEN.LINE_ADDR.getLen()) {
                        j++;
                    }

                    if (addrs[j].length() > 0) {
                        addrs[j] = addrs[j].append(" ");
                    }
                    addrs[j] = addrs[j].append(sldToLineAddrs[i]);
                }
                int ix = 0;
                sldToFirstLineAddr = addrs[ix++].toString();
                sldToScdLineAddr = addrs[ix++].toString();
                sldToThirdLineAddr = addrs[ix++].toString();
                sldToFrthLineAddr = addrs[ix].toString();
            }
        }
        sldToCtyAddr //
        = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.bllgCityDescTxt.getValue(), 0, CUTOFF_LEN.CTY_ADDR.getLen());
        sldToStCd = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.bllgStTxt.getValue(), 0, CUTOFF_LEN.ST.getLen());
        sldToCntyNm = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.maintBllgCntyTxt.getValue(), 0, CUTOFF_LEN.CNTY_NM.getLen());
        sldToPostCd = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai415001.bllgZipTxt.getValue(), 0, CUTOFF_LEN.POST_CD.getLen());
        sldToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;

        ZYPEZDItemValueSetter.setValue(this.sldToLocNm, this.dsImptOrdBean.nwai415001.somShipToCustNm);
        this.sldToAddlLocNm.clear();
        ZYPEZDItemValueSetter.setValue(this.sldToFirstLineAddr, sldToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToScdLineAddr, sldToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToThirdLineAddr, sldToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFrthLineAddr, sldToFrthLineAddr);

        this.sldToFirstRefCmntTxt.clear();
        this.sldToScdRefCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(this.sldToCtyAddr, sldToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToStCd, sldToStCd);
        this.sldToProvNm.clear();
        ZYPEZDItemValueSetter.setValue(this.sldToCntyNm, sldToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.sldToPostCd, sldToPostCd);
        ZYPEZDItemValueSetter.setValue(this.sldToCtryCd, sldToCtryCd);
        this.sldToAcctNm.clear();

        return true;
    }

    /**
     * getBaseBllgCycleMthAot
     * @return  baseBllgCycleMthAot
     */
    public BigDecimal getBaseBllgCycleMthAot() {
        return this.baseBllgCycleMthAot;
    }

    /**
     * setBaseBllgCycleMthAot
     * @param baseBllgCycleMthAot   BigDecimal
     */
    public void setBaseBllgCycleMthAot(BigDecimal baseBllgCycleMthAot) {
        this.baseBllgCycleMthAot = baseBllgCycleMthAot;
    }

    /**
     * getUsgBllgCycleMthAot
     * @return  usgBllgCycleMthAot
     */
    public BigDecimal getUsgBllgCycleMthAot() {
        return this.usgBllgCycleMthAot;
    }

    /**
     * setUsgBllgCycleMthAot
     * @param usgBllgCycleMthAot    BigDecimal
     */
    public void setUsgBllgCycleMthAot(BigDecimal usgBllgCycleMthAot) {
        this.usgBllgCycleMthAot = usgBllgCycleMthAot;
    }

    /**
     * getBillToCustAcctCdForPrc
     * @return  billToCustAcctCdForPrc
     */
    public String getBillToCustAcctCdForPrc() {
        return this.billToCustAcctCdForPrc;
    }

    /**
     * getBillToCustCdForPrc
     * @return  billToCustCdForPrc
     */
    public String getBillToCustCdForPrc() {
        return this.billToCustCdForPrc;
    }

    /**
     * setReqBaseAmt
     * @param reqBaseAmt    BigDecimal
     */
    public void setReqBaseAmt(BigDecimal reqBaseAmt) {
        this.reqBaseAmt = reqBaseAmt;
    }

}

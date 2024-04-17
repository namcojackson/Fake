/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BLLG_CYCLETMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.NWAI4120_16TMsg;
import business.db.NWAI4120_18TMsg;
import business.db.NWAI4120_19TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.INTERFACE_ID;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SOM_CONTR_IND;
//import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.SOM_CONTR_IND;
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
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptSvcDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 01/30/2017   SRA             K.Aratani       Update          QC#17120
 * 02/02/2017   Fujitsu         M.Ohno          Update          QC#17119
 * 03/07/2017   SRAA            K.Aratani       Update          QC#17995
 * 03/31/2017   SRAA            K.Aratani       Update          QC#18210
 * 04/10/2017   SRAA            K.Aratani       Update          QC#18304
 * 07/21/2017   SRAA            K.Aratani       Update          QC#19993
 * 07/26/2017   SRAA            K.Aratani       Update          QC#20189
 * 08/17/2017   SRAA            K.Aratani       Update          QC#19968
 * 09/27/2017   SRAA            K.Aratani       Update          QC#21442
 * 2018/02/08   Hitachi         K.Kojima        Update          QC#23357
 *</pre>
 */
public class SomDsImptSvcDtlBean extends DS_IMPT_SVC_DTLTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.SHELL_TYPE shellType;

    public final NWAB412001Constant.CONTRACT_TYPE contractType;
    
    public SomDsImptOrdBean dsImptOrdBean;

    public final List<SomDsImptSvcConfigRefBean> dsImptSvcConfigRefBeanList;

    public final List<SomDsImptSvcPrcBean> dsImptSvcPrcBeanList;

    public final List<SomDsImptSvcAddlBaseBean> dsImptSvcAddlBaseBeanList;

    public final List<SomDsImptSvcAddlChrgBean> dsImptSvcAddlChrgBeanList;

    public final NWAI4120_16TMsg nwai4120_16;

    public NWAI4120_18TMsg nwai4120_18;

    public NWAI4120_19TMsg nwai4120_19;

    private BigDecimal reqBaseAmt;
    
    private BigDecimal baseBllgCycleMthAot;
    
    private BigDecimal usgBllgCycleMthAot; //QC#19993

    private String billToCustAcctCdForPrc;  //QC#19968
    
    private String billToCustCdForPrc;  //QC#19968
    
    public SomDsImptSvcDtlBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_16TMsg nwai4120_16, NWAI4120_18TMsg nwai4120_18, NWAB412001Constant.CONTRACT_TYPE contractType) {

        this(shellType, dsImptOrdBean, nwai4120_16, contractType);
        this.nwai4120_18 = nwai4120_18;
    }

    public SomDsImptSvcDtlBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_16TMsg nwai4120_16, NWAI4120_19TMsg nwai4120_19, NWAB412001Constant.CONTRACT_TYPE contractType) {

        this(shellType, dsImptOrdBean, nwai4120_16, contractType);
        this.nwai4120_19 = nwai4120_19;
    }

    private SomDsImptSvcDtlBean(NWAB412001Constant.SHELL_TYPE shellType, SomDsImptOrdBean dsImptOrdBean, NWAI4120_16TMsg nwai4120_16, NWAB412001Constant.CONTRACT_TYPE contractType) {
        super();

        this.shellType = shellType;
        this.contractType = contractType; //QC#18210
        this.dsImptSvcPrcBeanList = new ArrayList<SomDsImptSvcPrcBean>();
        this.dsImptSvcAddlBaseBeanList = new ArrayList<SomDsImptSvcAddlBaseBean>();
        this.dsImptSvcAddlChrgBeanList = new ArrayList<SomDsImptSvcAddlChrgBean>();
        this.dsImptSvcConfigRefBeanList = new ArrayList<SomDsImptSvcConfigRefBean>();
        this.nwai4120_16 = nwai4120_16;

        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdBean.dsImptSvcDtlBeanList.add(this);
        //QC#19968
        this.billToCustAcctCdForPrc = "";
        this.billToCustCdForPrc = "";
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        String intfcId = INTERFACE_ID.NWAI4120.name();
        String cpoSrcTpCd = CPO_SRC_TP.SOM;

        ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(this.dsImptSvcDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_SVC_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);

        //QC#18210
        if (NWAB412001Constant.CONTRACT_TYPE.FLEET.equals(this.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.FLEET);
        } else if (NWAB412001Constant.CONTRACT_TYPE.AGGREGATE.equals(this.contractType)) {
            ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.AGGREGATE);
        } else {
            ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.REGULAR);
        }
        //Fleet
        if (DS_CONTR_CATG.FLEET.equals(this.dsContrCatgCd.getValue())) {
            String contrTpTxt = this.nwai4120_16.contrTpTxt.getValue();
            String fleetPlanDescTxt = this.nwai4120_16.fleetPlanDescTxt.getValue();
            String baseBllgCycleDescTxt = null;
            String usgBllgCycleDescTxt = null;
            String dsImptSvcMdseCd = null;
            baseBllgCycleDescTxt = this.nwai4120_18.somBllgCycleDescTxt.getValue();
            usgBllgCycleDescTxt = this.nwai4120_18.usgCycleDescTxt.getValue();
            dsImptSvcMdseCd = NWXC412001.getDsImptSvcMdseCd(glblCmpyCd, 
                    this.dsImptOrdBean.cpoSrcTpCd.getValue(), 
                    this.dsImptOrdBean.nwai4120_01.interfaceId.getValue(), 
                    this.dsImptOrdBean.nwai4120_01.trxTpTxt.getValue(), 
                    "Copier",
                    contrTpTxt, 
                    this.nwai4120_18.dclnMaintIndSomTxt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcMdseCd, dsImptSvcMdseCd);
            ZYPEZDItemValueSetter.setValue(this.prcSvcContrTpCd, NWXC412001.getPrcSvcContrTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, contrTpTxt));
            ZYPEZDItemValueSetter.setValue(this.prcSvcPlnTpCd, NWXC412001.getPrcSvcPlnTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, fleetPlanDescTxt));

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
            //ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, NWXC412001.getBllgCycleCd(glblCmpyCd, cpoSrcTpCd, intfcId, usgBllgCycleDescTxt));
            ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, usgBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(this.toPerMthNum, this.nwai4120_16.fleetTermNum);
            ZYPEZDItemValueSetter.setValue(this.termMthAot, this.nwai4120_16.fleetTermNum);
        //Aggregate
        } else if (DS_CONTR_CATG.AGGREGATE.equals(this.dsContrCatgCd.getValue())) {
            String contrTpTxt = this.nwai4120_16.contrTpTxt.getValue();
            String fleetPlanDescTxt = this.nwai4120_16.fleetPlanDescTxt.getValue();
            String baseBllgCycleDescTxt = null;
            String usgBllgCycleDescTxt = null;
            String dsImptSvcMdseCd = null;
            baseBllgCycleDescTxt = this.nwai4120_18.somBllgCycleDescTxt.getValue();
            usgBllgCycleDescTxt = this.nwai4120_18.usgCycleDescTxt.getValue();
            dsImptSvcMdseCd = NWXC412001.getDsImptSvcMdseCd(glblCmpyCd, 
                    this.dsImptOrdBean.cpoSrcTpCd.getValue(), 
                    this.dsImptOrdBean.nwai4120_01.interfaceId.getValue(), 
                    this.dsImptOrdBean.nwai4120_01.trxTpTxt.getValue(), 
                    "Copier",
                    contrTpTxt, 
                    this.nwai4120_18.dclnMaintIndSomTxt.getValue());
            ZYPEZDItemValueSetter.setValue(this.dsImptSvcMdseCd, dsImptSvcMdseCd);
            ZYPEZDItemValueSetter.setValue(this.prcSvcContrTpCd, NWXC412001.getPrcSvcContrTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, contrTpTxt));
            ZYPEZDItemValueSetter.setValue(this.prcSvcPlnTpCd, NWXC412001.getPrcSvcPlnTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, fleetPlanDescTxt));

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
            //ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, NWXC412001.getBllgCycleCd(glblCmpyCd, cpoSrcTpCd, intfcId, usgBllgCycleDescTxt));
            ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, usgBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(this.toPerMthNum, this.nwai4120_16.fleetTermNum);
            ZYPEZDItemValueSetter.setValue(this.termMthAot, this.nwai4120_16.fleetTermNum);
        } else {
            if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

                String contrTpDescTxt = this.nwai4120_18.contrTpDescTxt.getValue();
                String planDescTxt = this.nwai4120_18.planDescTxt.getValue();
                String baseBllgCycleDescTxt = this.nwai4120_18.somBllgCycleDescTxt.getValue();
                String usgBllgCycleDescTxt = this.nwai4120_18.usgCycleDescTxt.getValue();
                String dsImptSvcMdseCd = NWXC412001.getDsImptSvcMdseCd(glblCmpyCd, this.dsImptOrdBean.cpoSrcTpCd.getValue(), this.dsImptOrdBean.nwai4120_01.interfaceId.getValue(), this.dsImptOrdBean.nwai4120_01.trxTpTxt.getValue(), "Copier",
                        contrTpDescTxt, this.nwai4120_18.dclnMaintIndSomTxt.getValue());
                ZYPEZDItemValueSetter.setValue(this.dsImptSvcMdseCd, dsImptSvcMdseCd);
                ZYPEZDItemValueSetter.setValue(this.prcSvcContrTpCd, NWXC412001.getPrcSvcContrTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, contrTpDescTxt));
                ZYPEZDItemValueSetter.setValue(this.prcSvcPlnTpCd, NWXC412001.getPrcSvcPlnTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, planDescTxt));

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
                //ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, NWXC412001.getBllgCycleCd(glblCmpyCd, cpoSrcTpCd, intfcId, usgBllgCycleDescTxt));
                ZYPEZDItemValueSetter.setValue(this.usgBllgCycleCd, usgBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(this.toPerMthNum, this.nwai4120_18.somSrvTermId);
                ZYPEZDItemValueSetter.setValue(this.termMthAot, this.nwai4120_18.somSrvTermId);

            } else if (NWAB412001Constant.SHELL_TYPE.NON_COPIER.equals(this.shellType)) {

                String dsImptSvcMdseCd = NWXC412001.getDsImptSvcMdseCd(glblCmpyCd, this.dsImptOrdBean.cpoSrcTpCd.getValue(), this.dsImptOrdBean.nwai4120_01.interfaceId.getValue(), this.dsImptOrdBean.nwai4120_01.trxTpTxt.getValue(),
                        "Non-Copier", null, this.nwai4120_19.dclnMaintIndSomTxt.getValue());
                ZYPEZDItemValueSetter.setValue(this.dsImptSvcMdseCd, dsImptSvcMdseCd);
                ZYPEZDItemValueSetter.setValue(this.prcSvcContrTpCd, PRC_SVC_CONTR_TP.SERVICE_ONLY);
                ZYPEZDItemValueSetter.setValue(this.prcSvcPlnTpCd, PRC_SVC_PLN_TP.FIXED);
                //QC#20189
                //ZYPEZDItemValueSetter.setValue(this.baseBllgCycleCd, BLLG_CYCLE.MONTHLY);
                ZYPEZDItemValueSetter.setValue(this.baseBllgCycleCd, BLLG_CYCLE.YEARLY);
                BLLG_CYCLETMsg bllgCycle = new BLLG_CYCLETMsg();
                ZYPEZDItemValueSetter.setValue(bllgCycle.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bllgCycle.bllgCycleCd, this.baseBllgCycleCd);
                bllgCycle = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bllgCycle);
                if (bllgCycle != null) {
                    this.setBaseBllgCycleMthAot(bllgCycle.bllgCycleMthAot.getValue());
                }
                //this.setBaseBllgCycleMthAot(BigDecimal.ONE);
                this.usgBllgCycleCd.clear();
                ZYPEZDItemValueSetter.setValue(this.toPerMthNum, this.nwai4120_19.somDurnId);
                ZYPEZDItemValueSetter.setValue(this.termMthAot, this.nwai4120_19.somDurnId);

            }
        }


        ZYPEZDItemValueSetter.setValue(this.fromPerMthNum, BigDecimal.ONE);

        //QC#15539-14
        //if (S21StringUtil.isEquals(this.nwai4120_16.somBllgFromOmTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
        //
        //    ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_ON_Y);
        //} else {
        //
        //    ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        //}
        //QC#18304
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
            if (S21StringUtil.isEquals(this.nwai4120_18.svcContrIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
            }
        }  else if (NWAB412001Constant.SHELL_TYPE.NON_COPIER.equals(this.shellType)) {
            if (S21StringUtil.isEquals(this.nwai4120_19.svcContrIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(this.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
            }
        }

        String billByTpCd = NWXC412001.getBillByTpCd(glblCmpyCd, cpoSrcTpCd, intfcId, this.nwai4120_16.fleetBillByTxt.getValue(), dsImptOrdBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(this.billByTpCd, billByTpCd);

        //QC#19968
        if (S21StringUtil.isEquals(this.dsImptOrdBean.nwai4120_01.maintBillIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {

            String zip = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.maintBllgZipTxt.getValue(), 0, 5);
            String address = 
                S21StringUtil.concatStrings(
                    NWXC412001.toUpperCase(this.dsImptOrdBean.nwai4120_01.maintBllgAddrDescTxt.getValue()), 
                    NWXC412001.toUpperCase(this.dsImptOrdBean.nwai4120_01.maintBllgCityDescTxt.getValue()), 
                    NWXC412001.toUpperCase(this.dsImptOrdBean.nwai4120_01.maintBllgStTxt.getValue()), 
                    NWXC412001.toUpperCase(zip));
            Map<String, Object> soldTo = NWXC412001.getSoldToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai4120_01.bllgCmpyTxt.getValue());

            if (soldTo != null && !soldTo.isEmpty()) {

                ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, (String) soldTo.get("BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(this.sellToCustCd, (String) soldTo.get("DS_ACCT_NUM"));
            } else {

                //QC#17995
                ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.soldToCustLocCd);
                ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.sellToCustCd);
                //ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.billToCustCd);
                //ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.billToCustAcctCd);
            }
        } else {

            //QC#17995
            ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.sellToCustCd);
            //ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.billToCustCd);
            //ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.billToCustAcctCd);
        }
        
        if (S21StringUtil.isEquals(this.nwai4120_16.fleetBillByTxt.getValue(), NWAB412001Constant.SOM_FLEET_BILL_BY_TYPE_CFS)) {

            // CFS
            //ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, (String) this.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.CANON_E22_CFS_INFO_BILL_TO.name()));
            //ZYPEZDItemValueSetter.setValue(this.sellToCustCd, (String) this.dsImptOrdBean.getVarCharConstMap().get(NWAB412001Constant.VAR_CHAR_CONST_NM.CANON_E22_CFS_INFO_ACCT.name()));
            // START 2018/02/08 K.Kojima [QC#23357,MOD]
            // Map<String, Object> defMaintCustMap = NWXC412001.getDefaultMaintenanceCustomer(glblCmpyCd, dsImptOrdBean.getDsOrdCatgCd(), billByTpCd);
            Map<String, Object> defMaintCustMap = NWXC412001.getDefaultMaintenanceCustomer(glblCmpyCd, dsImptOrdBean.getDsOrdCatgCd(), dsImptOrdBean.getDsOrdTpCd(), billByTpCd);
            // END 2018/02/08 K.Kojima [QC#23357,MOD]
            if (defMaintCustMap != null && defMaintCustMap.get("CONTR_LEASE_DS_ACCT_NUM") != null) {
//                ZYPEZDItemValueSetter.setValue(this.sellToCustCd, (String) defMaintCustMap.get("CONTR_LEASE_DS_ACCT_NUM"));
                this.billToCustAcctCdForPrc = (String) defMaintCustMap.get("CONTR_LEASE_DS_ACCT_NUM");
            }
            if (defMaintCustMap != null && defMaintCustMap.get("CONTR_LEASE_BILL_TO_CUST_CD") != null) {
//                ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, (String) defMaintCustMap.get("SPEC_BILL_TO_LOC_CD"));
                this.billToCustCdForPrc = (String) defMaintCustMap.get("CONTR_LEASE_BILL_TO_CUST_CD");
            }

        } else {

            this.billToCustAcctCdForPrc = this.sellToCustCd.getValue();
            this.billToCustCdForPrc = this.soldToCustLocCd.getValue();
//            if (S21StringUtil.isEquals(this.dsImptOrdBean.nwai4120_01.maintBillIndSomTxt.getValue(), NWAB412001Constant.FLG.YES.name())) {
//
//                String zip = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.maintBllgZipTxt.getValue(), 0, 5);
//                String address = 
//                    S21StringUtil.concatStrings(
//                        NWXC412001.toUpperCase(this.dsImptOrdBean.nwai4120_01.maintBllgAddrDescTxt.getValue()), 
//                        NWXC412001.toUpperCase(this.dsImptOrdBean.nwai4120_01.maintBllgCityDescTxt.getValue()), 
//                        NWXC412001.toUpperCase(this.dsImptOrdBean.nwai4120_01.maintBllgStTxt.getValue()), 
//                        NWXC412001.toUpperCase(zip));
//                Map<String, Object> soldTo = NWXC412001.getSoldToInfoFromAddress(glblCmpyCd, address, salesDate, this.dsImptOrdBean.nwai4120_01.bllgCmpyTxt.getValue());
//
//                if (soldTo != null && !soldTo.isEmpty()) {
//
//                    ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, (String) soldTo.get("BILL_TO_CUST_CD"));
//                    ZYPEZDItemValueSetter.setValue(this.sellToCustCd, (String) soldTo.get("DS_ACCT_NUM"));
//                } else {
//
//                    //QC#17995
//                    ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.soldToCustLocCd);
//                    ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.sellToCustCd);
//                    //ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.billToCustCd);
//                    //ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.billToCustAcctCd);
//                }
//            } else {
//
//                //QC#17995
//                ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.soldToCustLocCd);
//                ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.sellToCustCd);
//                //ZYPEZDItemValueSetter.setValue(this.soldToCustLocCd, this.dsImptOrdBean.billToCustCd);
//                //ZYPEZDItemValueSetter.setValue(this.sellToCustCd, this.dsImptOrdBean.billToCustAcctCd);
//            }
        }
        
        //QC#17995
        if (ZYPCommonFunc.hasValue(this.soldToCustLocCd) 
                && ZYPCommonFunc.hasValue(this.dsImptOrdBean.billToCustCd) 
                && this.soldToCustLocCd.getValue().equals(this.dsImptOrdBean.billToCustCd.getValue())
                && ZYPCommonFunc.hasValue(this.sellToCustCd)
                && ZYPCommonFunc.hasValue(this.dsImptOrdBean.billToCustAcctCd)
                && this.sellToCustCd.getValue().equals(this.dsImptOrdBean.billToCustAcctCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(this.useEquipBillToFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(this.useEquipBillToFlg, ZYPConstant.FLG_OFF_N);
        }

        this.svcAgmtVrsnNum.clear();
        ZYPEZDItemValueSetter.setValue(this.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        this.manContrOvrdRsnCd.clear();
        this.manContrOvrdCmntTxt.clear();
        //In case of Fleet or Aggregate Only
        if (SOM_CONTR_IND.F.toString().equals(nwai4120_16.contrIndSomTxt.getValue())
            || SOM_CONTR_IND.P.toString().equals(nwai4120_16.contrIndSomTxt.getValue())) {
            ZYPEZDItemValueSetter.setValue(this.dsContrPk, NWXC412001.getDsContrPkByContrNum(glblCmpyCd, this.nwai4120_16.somContrNum.getValue(), salesDate));
            if (SOM_CONTR_IND.F.toString().equals(nwai4120_16.contrIndSomTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.FLEET);
            } else if (SOM_CONTR_IND.P.toString().equals(nwai4120_16.contrIndSomTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(this.dsContrCatgCd, DS_CONTR_CATG.AGGREGATE);
            }
        } else {
            this.dsContrPk.clear();
        }
        ZYPEZDItemValueSetter.setValue(this.useEquipBillToFlg, ZYPConstant.FLG_OFF_N);

        //ZYPEZDItemValueSetter.setValue(this.svcPrcCatgCd, SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);  //TODO
        BigDecimal calcBasePrcAmt = BigDecimal.ZERO;
        BigDecimal termNum = BigDecimal.ONE;
        //Fleet
        if (DS_CONTR_CATG.FLEET.equals(this.dsContrCatgCd.getValue())) {
            this.reqBaseAmt = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetBwReqBaseAmt)) {
                this.reqBaseAmt = this.nwai4120_16.fleetBwReqBaseAmt.getValue();
            }
            if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetClrReqBaseAmt)) {
                this.reqBaseAmt = this.reqBaseAmt.add(this.nwai4120_16.fleetClrReqBaseAmt.getValue());
            }
            calcBasePrcAmt = this.reqBaseAmt.multiply(this.nwai4120_16.fleetTermNum.getValue());
            termNum = this.nwai4120_16.fleetTermNum.getValue();
        //Aggregate
        //} else if (DS_CONTR_CATG.AGGREGATE.equals(this.dsContrCatgCd.getValue())) {
        //    this.reqBaseAmt = BigDecimal.ZERO;
        //    if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetBwReqBaseAmt)) {
        //        this.reqBaseAmt = this.nwai4120_16.fleetBwReqBaseAmt.getValue();
        //    }
        //    if (ZYPCommonFunc.hasValue(this.nwai4120_16.fleetClrReqBaseAmt)) {
        //        this.reqBaseAmt = this.reqBaseAmt.add(this.nwai4120_16.fleetClrReqBaseAmt.getValue());
        //    }
        //    calcBasePrcAmt = this.reqBaseAmt.multiply(this.nwai4120_16.fleetTermNum.getValue());
        //Regular & Aggregate
        } else {
            if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {
                calcBasePrcAmt = this.reqBaseAmt.multiply(this.nwai4120_18.somSrvTermId.getValue());
                termNum = this.nwai4120_18.somSrvTermId.getValue();
            } else {
                calcBasePrcAmt = this.reqBaseAmt;
            }
        }
        
        for (SomDsImptSvcPrcBean svcPrcBean : this.dsImptSvcPrcBeanList) {
            svcPrcBean.doImptMapping(glblCmpyCd, salesDate);
        }

        for (SomDsImptSvcConfigRefBean svcConfigRefBean : this.dsImptSvcConfigRefBeanList) {
            svcConfigRefBean.doImptMapping(glblCmpyCd, salesDate);
        }

        BigDecimal addlBaseAmt = BigDecimal.ZERO;
        for (SomDsImptSvcAddlBaseBean svcAddlBaseBean : this.dsImptSvcAddlBaseBeanList) {
            if (svcAddlBaseBean.nwai4120_17 != null) {
                if (ZYPCommonFunc.hasValue(svcAddlBaseBean.nwai4120_17.reqBaseAmt)) {
                    //QC#21442
                    //addlBaseAmt = addlBaseAmt.add(svcAddlBaseBean.nwai4120_17.reqBaseAmt.getValue()));
                    addlBaseAmt = addlBaseAmt.add(svcAddlBaseBean.nwai4120_17.reqBaseAmt.getValue().multiply(svcAddlBaseBean.nwai4120_17.somDurnId.getValue()));
                }
            } else {
                if (NWAB412001Constant.RENTAL_TYPE.EQUIPMENT.equals(svcAddlBaseBean.rentalType)) {
                    if (ZYPCommonFunc.hasValue(svcAddlBaseBean.nwai4120_21.somEquipBaseAmt)) {
                        if (ZYPCommonFunc.hasValue(this.termMthAot) && ZYPCommonFunc.hasValue(svcAddlBaseBean.nwai4120_21.somEquipBaseAmt)) {
                            addlBaseAmt = addlBaseAmt.add(svcAddlBaseBean.nwai4120_21.somEquipBaseAmt.getValue().multiply(this.termMthAot.getValue()));
                        }
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(svcAddlBaseBean.nwai4120_21.somSvcBaseAmt)) {
                        if (ZYPCommonFunc.hasValue(this.termMthAot) && ZYPCommonFunc.hasValue(svcAddlBaseBean.nwai4120_21.somSvcBaseAmt)) {
                            addlBaseAmt = addlBaseAmt.add(svcAddlBaseBean.nwai4120_21.somSvcBaseAmt.getValue().multiply(this.termMthAot.getValue()));
                        }
                    }
                }
            }
            svcAddlBaseBean.doImptMapping(glblCmpyCd, salesDate);
        }

        BigDecimal addlChrgAmt = BigDecimal.ZERO;
        for (SomDsImptSvcAddlChrgBean svcAddlChrgBean : this.dsImptSvcAddlChrgBeanList) {
            if (ZYPCommonFunc.hasValue(svcAddlChrgBean.nwai4120_20.reqSvcPrcAmt)) {
                addlChrgAmt = addlChrgAmt.add(svcAddlChrgBean.nwai4120_20.reqSvcPrcAmt.getValue().multiply(termNum));
            }
            svcAddlChrgBean.doImptMapping(glblCmpyCd, salesDate);
        }
        
        //Total
        BigDecimal totBasePrcAmt = calcBasePrcAmt.add(addlBaseAmt).add(addlChrgAmt);
        
        ZYPEZDItemValueSetter.setValue(this.totBasePrcDealAmt, totBasePrcAmt);
        ZYPEZDItemValueSetter.setValue(this.totBasePrcFuncAmt, totBasePrcAmt);
        
     // 2017/01/30 S21_NA#17119 Add Start
        String sldToLocNm = null;
        String sldToAddlLocNm = null;
        String sldToFirstLineAddr = null;
        String sldToScdLineAddr = null;
        String sldToThirdLineAddr = null;
        String sldToFrthLineAddr = null;
        String sldToFirstRefCmntTxt = null;
        String sldToScdRefCmntTxt = null;
        String sldToCtyAddr = null;
        String sldToStCd = null;
        String sldToProvNm = null;
        String sldToCntyNm = null;
        String sldToPostCd = null;
        String sldToCtryCd = null;
        String sldToAcctNm = null;
        if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.bllgAddrDescTxt)) {

            if (this.dsImptOrdBean.nwai4120_01.bllgAddrDescTxt.getValue().length() <= 60) {
                sldToFirstLineAddr = this.dsImptOrdBean.nwai4120_01.bllgAddrDescTxt.getValue();

            } else {
                String[] sldToLineAddrs = this.dsImptOrdBean.nwai4120_01.bllgAddrDescTxt.getValue().split(" ");
                StringBuffer[] addrs = {new StringBuffer(""),new StringBuffer(""),new StringBuffer(""),new StringBuffer("")};
                int j=0;

                for (int i=0; i<sldToLineAddrs.length;i++) {
                    if (addrs[j].length() + sldToLineAddrs[i].length() + 1 > 60) {
                        j++;
                    }

                    if (addrs[j].length() > 0) {
                        addrs[j] = addrs[j].append(" ");
                    }
                    addrs[j] = addrs[j].append(sldToLineAddrs[i]);
                }
                sldToFirstLineAddr = addrs[0].toString();
                sldToScdLineAddr = addrs[1].toString();
                sldToThirdLineAddr = addrs[2].toString();
                sldToFrthLineAddr = addrs[3].toString();
            }
        }
        sldToCtyAddr = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.bllgCityDescTxt.getValue(), 0, 25);
        sldToStCd = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.bllgStTxt.getValue(), 0, 2);
        sldToPostCd = S21StringUtil.subStringByLength(this.dsImptOrdBean.nwai4120_01.bllgZipTxt.getValue(), 0, 15);
        sldToCtryCd = CTRY.UNITED_STATES_OF_AMERICA;

        ZYPEZDItemValueSetter.setValue(this.sldToLocNm, sldToLocNm);
        ZYPEZDItemValueSetter.setValue(this.sldToAddlLocNm, sldToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(this.sldToFirstLineAddr, sldToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToScdLineAddr, sldToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToThirdLineAddr, sldToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFrthLineAddr, sldToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToFirstRefCmntTxt, sldToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.sldToScdRefCmntTxt, sldToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(this.sldToCtyAddr, sldToCtyAddr);
        ZYPEZDItemValueSetter.setValue(this.sldToStCd, sldToStCd);
        ZYPEZDItemValueSetter.setValue(this.sldToProvNm, sldToProvNm);
        ZYPEZDItemValueSetter.setValue(this.sldToCntyNm, sldToCntyNm);
        ZYPEZDItemValueSetter.setValue(this.sldToPostCd, sldToPostCd);
        ZYPEZDItemValueSetter.setValue(this.sldToCtryCd, sldToCtryCd);
        ZYPEZDItemValueSetter.setValue(this.sldToAcctNm, sldToAcctNm);
        // 2017/01/30 S21_NA#17119 Add End

        return true;
    }
    
    public void setReqBaseAmt(BigDecimal reqBaseAmt) {
        this.reqBaseAmt = reqBaseAmt;
    }
    
    public BigDecimal getBaseBllgCycleMthAot() {
        return this.baseBllgCycleMthAot;
    }
    public void setBaseBllgCycleMthAot(BigDecimal baseBllgCycleMthAot) {
        this.baseBllgCycleMthAot = baseBllgCycleMthAot;
    }
    //QC#19993
    public BigDecimal getUsgBllgCycleMthAot() {
        return this.usgBllgCycleMthAot;
    }
    public void setUsgBllgCycleMthAot(BigDecimal usgBllgCycleMthAot) {
        this.usgBllgCycleMthAot = usgBllgCycleMthAot;
    }
    //QC#19968
    public String getBillToCustAcctCdForPrc() {
        return this.billToCustAcctCdForPrc;
    }
    public String getBillToCustCdForPrc() {
        return this.billToCustCdForPrc;
    }
    //QC#18210
    //public void setMixCopierNonCopierFlg(boolean mixCopierNonCopierFlg) {
    //    this.mixCopierNonCopierFlg = mixCopierNonCopierFlg;
    //}
    
    //QC#15539-15
    public BigDecimal getSomSortOrdNum() {

        BigDecimal somConfigId = null;
        String somSvcItemCd = null;
        if (NWAB412001Constant.SHELL_TYPE.COPIER.equals(this.shellType)) {

            somConfigId = this.nwai4120_18.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_18.somMdlId.getValue();
        } else {

            somConfigId = this.nwai4120_19.somSrvLineId.getValue();
            somSvcItemCd = this.nwai4120_19.somItemCd.getValue();
        }

        if (S21StringUtil.isEmpty(somSvcItemCd)) {

            // invalid case
            return null;
        }
        
        for (SomDsImptOrdDtlBean detail : this.dsImptOrdBean.dsImptOrdDtlBeanList) {

            if (detail.nwai4120_10.somConfigId.getValue().compareTo(somConfigId) != 0) {

                continue;
            }

            if (S21StringUtil.isEquals(detail.nwai4120_10.somMercCd.getValue(), somSvcItemCd)) {

                return detail.nwai4120_10.somSortOrdNum.getValue();
            }
        }

        // invalid case
        return null;
    }

    
}

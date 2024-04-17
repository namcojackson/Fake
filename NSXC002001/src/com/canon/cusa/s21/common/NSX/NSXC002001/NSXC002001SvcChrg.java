/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_SVC_CALL_TPTMsg;
import business.db.SVC_CHRG_TPTMsg;
import business.db.SVC_LBOR_CHRG_TPTMsg;
import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001Contr;
import com.canon.cusa.s21.common.NSX.NSXC002001.constant.NSXC002001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Charge Information
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/16/2013   Fujitsu         S.Nakai         Create          N/A
 * 09/06/2013   SRA             E.Inada         Update          QC#1806
 * 09/11/2013   SRA             E.Inada         Update          QC#2207
 * 08/31/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 *</pre>
 */
public class NSXC002001SvcChrg implements NSXC002001Constant {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC002001SvcChrg.class);

    /**
     * <pre>
     * is Labor Charge
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param svcTaskNum svcTaskNum
     * @param svcMachMstrPk svcMachMstrPk(not mandatory)
     * @return is charged
     */
    public static boolean isLborChrg(String glblCmpyCd, String svcTaskNum, BigDecimal svcMachMstrPk, String svcDt) {

        String chrgTpCd = getSvcChrgTpCd(glblCmpyCd, svcTaskNum, svcMachMstrPk, svcDt);
        // QC#1806 Start
        SVC_CHRG_TPTMsg svcChrgTpTMsg = getSvcChrgTpTMsg(glblCmpyCd, chrgTpCd);
        if (svcChrgTpTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(svcChrgTpTMsg.lborChrgFlg.getValue())) {
                return true;
            }
            return false;
        }
//        if (SVC_CHRG_TP.CHARGE.equals(chrgTpCd)) {
//            return true;
//        } else if (SVC_CHRG_TP.CHARGE_FOR_LABOR_ONLY.equals(chrgTpCd)) {
//            return true;
//        } else if (SVC_CHRG_TP.CHARGE_FOR_LABOR_OR_PARTS.equals(chrgTpCd)) {
//            return true;
//        } else if (SVC_CHRG_TP.CHARGE_FOR_LABOR_OR_TRAVEL.equals(chrgTpCd)) {
//            return true;
//        }
        // QC#1806 End
        return false;
    }
    /**
     * <pre>
     * is Travel Charge
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param fsrNum fsrNum
     * @param fsrVisitNum fsrVisitNum
     * @param svcMachMstrPk svcMachMstrPk(not mandatory)
     * @return is charged
     */
    public static boolean isTrvlChrg(String glblCmpyCd, String fsrNum, String fsrVisitNum, BigDecimal svcMachMstrPk, String svcDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("fsrNum", fsrNum);
        params.put("fsrVisitNum", fsrVisitNum);
        //
        List<String> svcTaskList = (List<String>) SSM_CLIENT.queryObjectList("getTaskList", params);

        boolean isTrvlChrg = false;

        //Travel fee is charged, if one or more task is charged.
        for (int i = 0; i < svcTaskList.size(); i++) {
            String chrgTpCd = getSvcChrgTpCd(glblCmpyCd, svcTaskList.get(i), svcMachMstrPk, svcDt);

            // QC#1806 Start
            SVC_CHRG_TPTMsg svcChrgTpTMsg = getSvcChrgTpTMsg(glblCmpyCd, chrgTpCd);
            if (svcChrgTpTMsg != null) {
                if (ZYPConstant.FLG_ON_Y.equals(svcChrgTpTMsg.trvlChrgFlg.getValue())) {
                    isTrvlChrg = true;
                } else {
                    isTrvlChrg = false;
                }
            }
//            if (SVC_CHRG_TP.CHARGE.equals(chrgTpCd) || SVC_CHRG_TP.CHARGE_FOR_LABOR_OR_TRAVEL.equals(chrgTpCd) || SVC_CHRG_TP.CHARGE_FOR_TRAVEL_OR_PARTS.equals(chrgTpCd)) {
//                isTrvlChrg = true;
//                break;
//            }
            // QC#1806 End
        }
        return isTrvlChrg;
    }
    /**
     * <pre>
     * is Parts Charge
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param svcTaskNum svcTaskNum
     * @param svcMachMstrPk svcMachMstrPk(not mandatory)
     * @return is charged
     */
    public static boolean isPrtsChrg(String glblCmpyCd, String svcTaskNum, BigDecimal svcMachMstrPk, String svcDt) {

        String chrgTpCd = getSvcChrgTpCd(glblCmpyCd, svcTaskNum, svcMachMstrPk, svcDt);

        // QC#1806 Start
        SVC_CHRG_TPTMsg svcChrgTpTMsg = getSvcChrgTpTMsg(glblCmpyCd, chrgTpCd);
        if (svcChrgTpTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(svcChrgTpTMsg.prtChrgFlg.getValue())) {
                return true;
            }
            return false;
        }
//        if (SVC_CHRG_TP.CHARGE.equals(chrgTpCd)) {
//            return true;
//        } else if (SVC_CHRG_TP.CHARGE_FOR_PARTS_ONLY.equals(chrgTpCd)) {
//            return true;
//        } else if (SVC_CHRG_TP.CHARGE_FOR_LABOR_OR_PARTS.equals(chrgTpCd)) {
//            return true;
//        } else if (SVC_CHRG_TP.CHARGE_FOR_TRAVEL_OR_PARTS.equals(chrgTpCd)) {
//            
//        }
        // QC#1806 End
        return false;
    }
    private static String getSvcChrgTpCd(String glblCmpyCd, String svcTaskNum, BigDecimal svcMachMstrPk, String svcDt) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcTaskNum", svcTaskNum);

        Map<String, Object> chrgTpCdMap = (Map<String, Object>) SSM_CLIENT.queryObject("getChrgTpFromTask", params);

        String chrgTpCd = (String) chrgTpCdMap.get(SVC_CHRG_TP_CD);

        if (ANY_TP.equals(chrgTpCd)) {
            NSXC001001PMsg pmsg = new NSXC001001PMsg();
            pmsg.glblCmpyCd.setValue(glblCmpyCd);
            if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                pmsg.svcMachMstrPk.setValue(svcMachMstrPk);
            } else {
                BigDecimal machMstrPk = (BigDecimal) chrgTpCdMap.get(SVC_MACH_MSTR_PK);
                if (ZYPCommonFunc.hasValue(machMstrPk)) {
                    pmsg.svcMachMstrPk.setValue(machMstrPk);
                }
            }
            if (ZYPCommonFunc.hasValue(svcDt)) {
                pmsg.slsDt.setValue(svcDt);
            } else {
                pmsg.slsDt.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
            }
            NSXC001001Contr.getContr(pmsg);

            if (pmsg.xxContrList != null && pmsg.xxContrList.length() > 0 && ZYPCommonFunc.hasValue(pmsg.xxContrList.no(0).svcBillTpCd)) {
                params.clear();
                params.put("glblCmpyCd", glblCmpyCd);
                params.put("svcBillTpCd",  pmsg.xxContrList.no(0).svcBillTpCd.getValue());
                chrgTpCd = (String) SSM_CLIENT.queryObject("getChrgTpFromContr", params);
            } else {
                chrgTpCd = (String) chrgTpCdMap.get(SVC_TASK_CHRG_TP_CD);
            }
        }
        return chrgTpCd;
    }
    /**
     * <pre>
     * is Flat Rate
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param fsrNum fsrNum
     * @param svcTaskNum svcTaskNum
     * @param svcMachMstrPk svcMachMstrPk
     * @param slsDt sales date
     * @return is flat rate
     */
    public static boolean isFlatRate(String glblCmpyCd, String fsrNum, String svcTaskNum, BigDecimal svcMachMstrPk, String slsDt, String dsSvcCallTpCd) {

        boolean isFlatRt = false;

        NSXC001001PMsg pmsg = new NSXC001001PMsg();
        pmsg.glblCmpyCd.setValue(glblCmpyCd);
        pmsg.svcMachMstrPk.setValue(svcMachMstrPk);
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            pmsg.slsDt.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
        } else {
            pmsg.slsDt.setValue(slsDt);
        }

        NSXC001001Contr.getCov(pmsg);
        if (pmsg.xxContrList != null && pmsg.xxContrList.getValidCount() > 0 && ZYPCommonFunc.hasValue(pmsg.xxContrList.no(0).svcLborChrgTpCd.getValue())) {
             String svcRateChrgTpCd = pmsg.xxContrList.no(0).svcLborChrgTpCd.getValue();
//             if (SVC_LBOR_CHRG_TP.FLAT_RATE.equals(svcRateChrgTpCd)) {
//                 return true;
//             }

             String flatRateFlg = getFlatRateFlg(glblCmpyCd, svcRateChrgTpCd);
             if (flatRateFlg != null) {
                 if (ZYPConstant.FLG_ON_Y.equals(flatRateFlg)) {
                     isFlatRt = true;
                 }
             }
        } else {
            if (ZYPCommonFunc.hasValue(svcTaskNum)) {
                Map<String, Object> ssmMap = new HashMap<String, Object>();
                ssmMap.put("glblCmpyCd", glblCmpyCd);
                ssmMap.put("fsrNum", fsrNum);
                ssmMap.put("svcTaskNum", svcTaskNum);
                List<String> svcFlatChrgFlgList = (List<String>) SSM_CLIENT.queryObjectList("getSvcFlatChrgFlgList", ssmMap);
                if (svcFlatChrgFlgList != null && svcFlatChrgFlgList.size() > 0) {
                    for (String svcFlatChrgFlg : svcFlatChrgFlgList) {
                        if (ZYPConstant.FLG_ON_Y.equals(svcFlatChrgFlg)) {
                            isFlatRt = true;
                        }
                    }
                }
            } else {
                DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = new DS_SVC_CALL_TPTMsg();
                dsSvcCallTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
                dsSvcCallTpTMsg.dsSvcCallTpCd.setValue(dsSvcCallTpCd);
                dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) S21CacheTBLAccessor.findByKey(dsSvcCallTpTMsg);
                if (dsSvcCallTpTMsg != null) {
                    if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.svcFlatChrgFlg.getValue())) {
                        isFlatRt = true;
                    }
                }
            }
        }
        return isFlatRt;
    }

    /**
     * getSvcChrgTpTMsg
     * @param glblCmpyCd String
     * @param svcChrgTpCd String
     * @return SVC_CHRG_TPTMsg
     */
    private static SVC_CHRG_TPTMsg getSvcChrgTpTMsg(String glblCmpyCd, String svcChrgTpCd) {
        return (SVC_CHRG_TPTMsg) ZYPCodeDataUtil.findByCode(SVC_CHRG_TP.class, glblCmpyCd, svcChrgTpCd);
    }

    /**
     * getFlatRateFlg
     * @param glblCmpyCd String
     * @param svcLborChrgTpCd String
     * @return String
     */
    private static String getFlatRateFlg(String glblCmpyCd, String svcLborChrgTpCd) {
        if (!ZYPCommonFunc.hasValue(svcLborChrgTpCd)) {
            return null;
        }
        SVC_LBOR_CHRG_TPTMsg svcLborChrgTpTMsg = new SVC_LBOR_CHRG_TPTMsg();
        svcLborChrgTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        svcLborChrgTpTMsg.svcLborChrgTpCd.setValue(svcLborChrgTpCd);

        svcLborChrgTpTMsg =  (SVC_LBOR_CHRG_TPTMsg) S21CacheTBLAccessor.findByKey(svcLborChrgTpTMsg);
        if (svcLborChrgTpTMsg == null) {
            return null;
        }
        return svcLborChrgTpTMsg.flatRateFlg.getValue();
    }

//NA#ASCC CLICK ADD Start
    /**
     * <pre>
     * getSrvPgm
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param svcMachMstrPk svcMachMstrPk
     * @param slsDt sales date
     * @return svcPgmBean SvcPgmBean
     */
    public static SvcPgmBean getSrvPgm(String glblCmpyCd, BigDecimal svcMachMstrPk, String slsDt) {
//TODO Taizo Need to be modified due to logic for stub.
        SvcPgmBean svcPgm = new SvcPgmBean();
        svcPgm.setDefBillTpCd("4");
        svcPgm.setIsAHSAvalFlg(ZYPConstant.FLG_ON_Y);
        svcPgm.setSvcLborUnitAmt(new BigDecimal(50.05));

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", ZYPCommonFunc.hasValue(glblCmpyCd) ? glblCmpyCd : "ADB");
        List<Map<String, Object>> stubParams = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getStubParams", ssmMap);
        String stubNm = null;
        String stubVal = null;

        for (Map<String, Object> stubParam : stubParams) {

            stubNm = (String) stubParam.get("VAR_CHAR_CONST_NM");
            stubVal = (String) stubParam.get("VAR_CHAR_CONST_VAL");

            if ("DEF_SVC_BILL_TP_CD".equals(stubNm)) {

                svcPgm.setDefBillTpCd(ZYPCommonFunc.hasValue(stubVal) ? stubVal : "4");

            } else if ("AHS_AVAL_FLG".equals(stubNm)) {

                svcPgm.setIsAHSAvalFlg(ZYPCommonFunc.hasValue(stubVal) ? stubVal : ZYPConstant.FLG_ON_Y);

            } else if ("SVC_LBOR_UNIT_AMT".equals(stubNm)) {

                svcPgm.setSvcLborUnitAmt(new BigDecimal(ZYPCommonFunc.hasValue(stubVal) ? stubVal : "50.05"));
            }
        }

        return svcPgm;
    }
//NA#ASCC CLICK ADD End
}

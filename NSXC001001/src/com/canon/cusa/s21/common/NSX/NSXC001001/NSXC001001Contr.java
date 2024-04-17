/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.NSXC001001Constant;
import com.canon.cusa.s21.common.NSX.NSXC002001.CovInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetCovInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_COV_SVC_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * get Contract Information
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/10/2013   Fujitsu         S.Nakai         Create          N/A
 * 06/07/2013   Fujitsu         J.Sakamoto      Update          N/A
 * 08/27/2013   Hitachi         Y.Igarashi      Update          QC1851/QC1852
 * 09/13/2013   SRA             E.Inada         Update          QC2190/QC2216
 * 11/24/2015   Hitachi         T.Harada        Update          [CSA,QC-983]
 *</pre>
 */
public class NSXC001001Contr implements NSXC001001Constant {

    /** S21ApiMessageMap */
    private static S21ApiMessageMap msgMap = null;

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001Contr.class);

    /**
     * private constructor.
     */
    public NSXC001001Contr() {
    }

    private static boolean init(NSXC001001PMsg pMsg) {
        msgMap = new S21ApiMessageMap(pMsg);

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsgId(NSXM0001E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk) && !ZYPCommonFunc.hasValue(pMsg.dsContrNum)) {
            setErrMsgId(NSXM0002E);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * get Contract
     * </pre>
     * @param pMsg NSXC001001PMsg
     */
    public static void getContr(NSXC001001PMsg pMsg) {

        if (!init(pMsg)) {
            return;
        }
        BigDecimal fleetDtlPk = null;
        // search warranty
        List<Map<String, Object>> contrList = searchContr(pMsg, WARRANTY, null);
        if (contrList == null) {
            return;
        }
        int cnt = 0;
        for (int i = 0; i < contrList.size(); i++) {
            Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
            if (ZYPCommonFunc.hasValue(getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK))) {
                fleetDtlPk = getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK);
            }
            setContrPmsg(pMsg, contrMap, WARRANTY, cnt);
        }
        // search warranty fleet
        if (ZYPCommonFunc.hasValue(fleetDtlPk)) {
            contrList.clear();
            contrList = searchContr(pMsg, WARRANTY_FLEET, fleetDtlPk);
            // init fleetDtlPk
            fleetDtlPk = null;
            if (contrList == null) {
                return;
            }
            for (int i = 0; i < contrList.size(); i++) {
                Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
                setContrPmsg(pMsg, contrMap, WARRANTY_FLEET, cnt);
            }
        }
        // search machine
        contrList.clear();
        contrList = searchContr(pMsg, MACHINE, null);
        if (contrList == null) {
            return;
        }
        for (int i = 0; i < contrList.size(); i++) {
            Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
            if (ZYPCommonFunc.hasValue(getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK))) {
                fleetDtlPk = getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK);
            }
            setContrPmsg(pMsg, contrMap, MACHINE, cnt);
        }
        // search fleet
        if (ZYPCommonFunc.hasValue(fleetDtlPk)) {
            contrList.clear();
            contrList = searchContr(pMsg, FLEET, fleetDtlPk);
            if (contrList == null) {
                return;
            }
            for (int i = 0; i < contrList.size(); i++) {
                Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
                setContrPmsg(pMsg, contrMap, FLEET, cnt);
            }
        }

        pMsg.xxContrList.setValidCount(cnt);
    }

    private static List<Map<String, Object>> searchContr(NSXC001001PMsg prmPmsg, String searchTp, BigDecimal dsContrDtlPk) {

        String svcDt = null;

        Map<String, Object> prmMap = new HashMap<String, Object>();

        prmMap.put("glblCmpyCd", prmPmsg.glblCmpyCd.getValue());
        prmMap.put("svcMachMstrPk", prmPmsg.svcMachMstrPk.getValue());
        prmMap.put("dsContrNum", prmPmsg.dsContrNum.getValue());
        prmMap.put("dsContrTpCd", DS_CONTR_TP.WARRANTY);
        prmMap.put("dsContrDtlPk", dsContrDtlPk);

        setSsmSearchTpParam(prmMap, searchTp);
        if (ZYPCommonFunc.hasValue(prmPmsg.slsDt)) {
            svcDt = prmPmsg.slsDt.getValue();
        } else {
            svcDt = ZYPDateUtil.getSalesDate(prmPmsg.glblCmpyCd.getValue());
        }
        prmMap.put("svcDt", svcDt);
        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getContrList", prmMap);
    }

    private static List<Map<String, Object>> searchCov(NSXC001001PMsg prmPmsg, String searchTp, BigDecimal dsContrDtlPk) {

        String svcDt = null;

        Map<String, Object> prmMap = new HashMap<String, Object>();

        prmMap.put("glblCmpyCd", prmPmsg.glblCmpyCd.getValue());
        prmMap.put("svcMachMstrPk", prmPmsg.svcMachMstrPk.getValue());
        prmMap.put("dsContrNum", prmPmsg.dsContrNum.getValue());
        prmMap.put("dsContrTpCd", DS_CONTR_TP.WARRANTY);
        prmMap.put("dsContrDtlPk", dsContrDtlPk);

        setSsmSearchTpParam(prmMap, searchTp);
        if (ZYPCommonFunc.hasValue(prmPmsg.slsDt)) {
            svcDt = prmPmsg.slsDt.getValue();
        } else {
            svcDt = ZYPDateUtil.getSalesDate(prmPmsg.glblCmpyCd.getValue());
        }
        prmMap.put("svcDt", svcDt);
        setParamDayOfWeek(prmMap, svcDt, prmPmsg.apptHourMn.getValue());
        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getCovList", prmMap);
    }

    private static void setSsmSearchTpParam(Map<String, Object> prmMap, String searchTp) {
        if (WARRANTY.equals(searchTp) || WARRANTY_FLEET.equals(searchTp)) {
            prmMap.put("warranty", ZYPConstant.FLG_ON_Y);
        } else {
            prmMap.put("warranty", ZYPConstant.FLG_OFF_N);
        }
        if (FLEET.equals(searchTp) || WARRANTY_FLEET.equals(searchTp)) {
            prmMap.put("fleet", ZYPConstant.FLG_ON_Y);
            prmMap.put("fleetLineFlg", ZYPConstant.FLG_ON_Y);
        } else {
            prmMap.put("fleet", ZYPConstant.FLG_OFF_N);
            prmMap.put("splyLineFlg", ZYPConstant.FLG_OFF_N);
        }
    }

    private static List<Map<String, Object>> searchContr(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk, String contrDt, String searchTp, BigDecimal dsContrPk, String svcTm) {

        String svcDt = null;

        Map<String, Object> prmMap = new HashMap<String, Object>();

//        boolean covFlg = ZYPCommonFunc.hasValue(svcTm);

        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        prmMap.put("svcConfigMstrPk", svcConfigMstrPk);
        prmMap.put("dsContrTpCd", DS_CONTR_TP.WARRANTY);

        setSsmSearchTpParam(prmMap, searchTp);
        if (ZYPCommonFunc.hasValue(contrDt)) {
            svcDt = contrDt;
        } else {
            svcDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        }
        prmMap.put("svcDt", svcDt);

        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getContrList", prmMap);
    }

    private static void setParamDayOfWeek(Map<String, Object> prmMap, String contrDt, String svcTm) {
//        prmMap.put("svcTm", svcTm);
        int dayOfWeek = getDayOfWeek(contrDt);
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                prmMap.put("sunday", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.MONDAY:
                prmMap.put("monday", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.TUESDAY:
                prmMap.put("tuesday", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.WEDNESDAY:
                prmMap.put("wednesday", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.THURSDAY:
                prmMap.put("thursday", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.FRIDAY:
                prmMap.put("friday", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.SATURDAY:
                prmMap.put("saturday", ZYPConstant.FLG_ON_Y);
                break;
            default:
                break;
        }
        return;
    }

    private static BigDecimal getBigDecimal(Map<String, Object> map, SELECT select) {
        return (BigDecimal) map.get(select.toString());
    }

    private static String getString(Map<String, Object> map, SELECT select) {
        return nullToEmpty((String) map.get(select.toString()));
    }

    private static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * <pre>
     * search Contract Info
     * </pre>
     * @param prmPmsg NSXC001001PMsg
     */
    public static void searchMachMstrList(NSXC001001PMsg prmPmsg) {

        Map<String, Object> prmMap = new HashMap<String, Object>();

        prmMap.put("glblCmpyCd", prmPmsg.glblCmpyCd.getValue());
        prmMap.put("dsContrNum", prmPmsg.dsContrNum.getValue());
        // QC1852 Add Start
        prmMap.put("limitCount", prmPmsg.xxContrList.length());
        // QC1852 Add End

        List<BigDecimal> machMstrPkList = (List<BigDecimal>) SSM_CLIENT.queryObjectList("getMachMstrPkList", prmMap);

        int i = 0;
        for (i = 0; i < machMstrPkList.size(); i++) {
            // QC1852 Modify Start
            //prmPmsg.xxContrList.no(0).svcMachMstrPk.setValue(machMstrPkList.get(i));
            prmPmsg.xxContrList.no(i).svcMachMstrPk.setValue(machMstrPkList.get(i));
            // QC1852 Modify End
        }
        prmPmsg.xxContrList.setValidCount(i);
    }

    /**
     * <pre>
     * get Coverage
     * </pre>
     * @param pMsg NSXC001001PMsg
     */
    public static void getCov(NSXC001001PMsg pMsg) {

        if (!init(pMsg)) {
            return;
        }
        // search warranty
        boolean isWarranty = false;
        String svcBillTpCd = null;
        BigDecimal fleetDtlPk = null;
        List<Map<String, Object>> contrList = searchCov(pMsg, WARRANTY, null);
        if (contrList == null) {
            return;
        }
        int cnt = 0;
        for (int i = 0; i < contrList.size(); i++) {
            Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
            if (ZYPCommonFunc.hasValue(getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK))) {
                fleetDtlPk = getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK);
            }
            setCovPmsg(pMsg, contrMap, WARRANTY, cnt);
            isWarranty = true;
        }
        // search warranty fleet
        if (ZYPCommonFunc.hasValue(fleetDtlPk)) {
            contrList.clear();
            contrList = searchCov(pMsg, WARRANTY_FLEET, fleetDtlPk);
            // init fleetDtlPk
            fleetDtlPk = null;
            if (contrList == null) {
                return;
            }
            for (int i = 0; i < contrList.size(); i++) {
                Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
                setCovPmsg(pMsg, contrMap, WARRANTY_FLEET, cnt);
            }
        }

        if (isWarranty) {
            svcBillTpCd = calcSvcBillTpCd(pMsg, ZYPConstant.FLG_ON_Y);
        }

        if (!ZYPCommonFunc.hasValue(svcBillTpCd)) {
            // search machine
            contrList.clear();
            contrList = searchCov(pMsg, MACHINE, null);
            if (contrList == null) {
                return;
            }
            for (int i = 0; i < contrList.size(); i++) {
                Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
                if (ZYPCommonFunc.hasValue(getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK))) {
                    fleetDtlPk = getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK);
                }
                setCovPmsg(pMsg, contrMap, MACHINE, cnt);
            }
            // search fleet
            if (ZYPCommonFunc.hasValue(fleetDtlPk)) {
                contrList.clear();
                contrList = searchCov(pMsg, FLEET, fleetDtlPk);
                if (contrList == null) {
                    return;
                }
                for (int i = 0; i < contrList.size(); i++) {
                    Map<String, Object> contrMap = (Map<String, Object>) contrList.get(i);
                    setCovPmsg(pMsg, contrMap, FLEET, cnt);
                }
            }

            svcBillTpCd = calcSvcBillTpCd(pMsg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(cnt).svcBillTpCd, svcBillTpCd);
        cnt++;
        pMsg.xxContrList.setValidCount(cnt);
    }
    /**
     * Get Contract Info.
     * @param glblCmpyCd glblCmpyCd
     * @param svcConfigMstrPk svcConfigMstrPk
     * @param svcMachMstrPk svcMachMstrPk
     * @param contrDt contrDt
     * @return ContrInfoBean
     */
    public ContrInfoBean getContrInfo(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk, String contrDt) {

        BigDecimal fleetDtlPk = null;

        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk) && !ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }

        // search warranty
        List<Map<String, Object>> contrList = searchContr(glblCmpyCd, svcConfigMstrPk, svcMachMstrPk, contrDt, WARRANTY, null, null);
        ContrInfoBean contrInfoBean = new ContrInfoBean();
        if (contrList != null) {
            for (int i = 0; i < contrList.size(); i++) {
                Map<String, Object> contrMap = contrList.get(0);
                if (ZYPCommonFunc.hasValue(getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK))) {
                    fleetDtlPk = getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK);
                }
                setConrInfo(contrInfoBean, contrMap, WARRANTY);
            }
        }
        // search fleet
        if (ZYPCommonFunc.hasValue(fleetDtlPk)) {
            contrList.clear();
            contrList = searchContr(glblCmpyCd, svcConfigMstrPk, svcMachMstrPk, contrDt, WARRANTY_FLEET, fleetDtlPk, null);
            if (contrList != null) {
                for (int i = 0; i < contrList.size(); i++) {
                    Map<String, Object> contrMap = contrList.get(0);
                    setConrInfo(contrInfoBean, contrMap, WARRANTY_FLEET);
                }
            }
        }
        // search machine
        contrList.clear();
        contrList = searchContr(glblCmpyCd, svcConfigMstrPk, svcMachMstrPk, contrDt, MACHINE, null, null);
        if (contrList != null) {
            for (int i = 0; i < contrList.size(); i++) {
                Map<String, Object> contrMap = contrList.get(0);
                if (ZYPCommonFunc.hasValue(getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK))) {
                    fleetDtlPk = getBigDecimal(contrMap, SELECT.PRNT_DS_CONTR_DTL_PK);
                }
                setConrInfo(contrInfoBean, contrMap, MACHINE);
            }
        }
        // search fleet
        if (ZYPCommonFunc.hasValue(fleetDtlPk)) {
            contrList.clear();
            contrList = searchContr(glblCmpyCd, svcConfigMstrPk, svcMachMstrPk, contrDt, FLEET, fleetDtlPk, null);
            if (contrList != null) {
                for (int i = 0; i < contrList.size(); i++) {
                    Map<String, Object> contrMap = contrList.get(0);
                    setConrInfo(contrInfoBean, contrMap, FLEET);
                }
            }
        }

        return contrInfoBean;
    }

    /**
     * Calc Svc Bill Tp Cd
     * @param pMsg NSXC001001PMsg
     * @return svcBillTpCd
     */
    public static String calcSvcBillTpCd(NSXC001001PMsg pMsg, String wtyFlg) {

        String svcBillTpCd = null;
        if (!ZYPCommonFunc.hasValue(pMsg.xxContrList.no(0).svcPrtDiscRate_D)) {
            pMsg.xxContrList.no(0).svcPrtDiscRate_D.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxContrList.no(0).svcPrtDiscRate_C)) {
            pMsg.xxContrList.no(0).svcPrtDiscRate_C.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxContrList.no(0).svcLborDiscRate)) {
            pMsg.xxContrList.no(0).svcLborDiscRate.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxContrList.no(0).svcTrvlDiscRate)) {
            pMsg.xxContrList.no(0).svcTrvlDiscRate.setValue(BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxContrList.no(0).svcPrtDiscRate)) {
            pMsg.xxContrList.no(0).svcPrtDiscRate.setValue(BigDecimal.ZERO);
        }

        // get Bill Type
        //START 11/24/2015 [CSA,QC-983]
        //svcBillTpCd = getSvcBillTpCd(pMsg.glblCmpyCd.getValue(), lborChrgFlg, trvlChrgFlg, prtChrgFlg, wtyFlg);
        CovInfoBean covInfo = NSXC002001GetCovInfo.getCovInfo(pMsg.glblCmpyCd.getValue(), null, pMsg.svcMachMstrPk.getValue());
        if (covInfo != null) {
            svcBillTpCd = covInfo.getSvcBillTpCd();
        }
        //END 11/24/2015 [CSA,QC-983]


        return svcBillTpCd;
    }
    private void setConrInfo(ContrInfoBean contrInfoBean, Map<String, Object> contrMap, String searchTp) {
        // QC1851 Add Start
        contrInfoBean.setContrPk(getBigDecimal(contrMap, SELECT.DS_CONTR_PK));
        // QC1851 Add End
        contrInfoBean.setContrNum(getString(contrMap, SELECT.DS_CONTR_NUM));
        contrInfoBean.setContrSeq(getString(contrMap, SELECT.DS_CONTR_SQ_NUM));
        contrInfoBean.setCv(getString(contrMap, SELECT.DS_CONTR_TP_NM));
        contrInfoBean.setContrEffDt(getMinTm(contrInfoBean.getContrEffDt(), getString(contrMap, SELECT.CONTR_EFF_FROM_DT)));
        contrInfoBean.setContrExprDt(getMaxTm(contrInfoBean.getContrExprDt(), getString(contrMap, SELECT.CONTR_EFF_THRU_DT)));
        // QC#2153 delete start
        // contrInfoBean.setCustAvalFromHourMn(getMinTm(contrInfoBean.getCustAvalFromHourMn(), getString(contrMap, SELECT.SVC_COV_FROM_HOUR_MN)));
        // contrInfoBean.setCustAvalToHourMn(getMaxTm(contrInfoBean.getCustAvalToHourMn(), getString(contrMap, SELECT.SVC_COV_TO_HOUR_MN)));
        // QC#2153 end
        contrInfoBean.setSvcRspTmDownMnAot(getMinRspTmAot(contrInfoBean.getSvcRspTmDownMnAot(), getBigDecimal(contrMap, SELECT.RSP_TM_DOWN_MN_AOT)));
        contrInfoBean.setSvcRspTmUpMnAot(getMinRspTmAot(contrInfoBean.getSvcRspTmUpMnAot(), getBigDecimal(contrMap, SELECT.RSP_TM_UP_MN_AOT)));
        contrInfoBean.setPoNum(getString(contrMap, SELECT.CUST_PO_NUM));
        contrInfoBean.setPoDt(getString(contrMap, SELECT.PO_DT));
        contrInfoBean.setInvCcyCd(getString(contrMap, SELECT.CCY_CD));
        contrInfoBean.setSvcLaborChargeType(getString(contrMap, SELECT.SVC_LBOR_CHRG_TP_CD));
        contrInfoBean.setPmtTermCashDiscCd(getString(contrMap, SELECT.PMT_TERM_CASH_DISC_CD));
        if (WARRANTY.equals(searchTp) || WARRANTY_FLEET.equals(searchTp)) {
            contrInfoBean.setWtyStartDt(getString(contrMap, SELECT.CONTR_EFF_FROM_DT));
            contrInfoBean.setWtyEndDt(getString(contrMap, SELECT.CONTR_EFF_THRU_DT));
        }
    }
    private static void setContrPmsg(NSXC001001PMsg pMsg, Map<String, Object> contrMap, String searchTp, int row) {
        pMsg.xxContrList.no(row).dsContrPk.setValue(getBigDecimal(contrMap, SELECT.DS_CONTR_PK));
        pMsg.xxContrList.no(row).dsContrNum.setValue(getString(contrMap, SELECT.DS_CONTR_NUM));
        pMsg.xxContrList.no(row).dsContrDtlPk.setValue(getBigDecimal(contrMap, SELECT.DS_CONTR_DTL_PK));
        pMsg.xxContrList.no(row).dsContrSqNum.setValue(getString(contrMap, SELECT.DS_CONTR_SQ_NUM));
        pMsg.xxContrList.no(row).svcMachMstrPk.setValue(getBigDecimal(contrMap, SELECT.SVC_MACH_MSTR_PK));
        pMsg.xxContrList.no(row).pmtTermCashDiscCd.setValue(getString(contrMap, SELECT.PMT_TERM_CASH_DISC_CD));
        pMsg.xxContrList.no(row).svcLborChrgTpCd.setValue(getString(contrMap, SELECT.SVC_LBOR_CHRG_TP_CD));
        //pMsg.xxContrList.no(row).xxViewNm.setValue(getString(contrMap, SELECT.DS_CONTR_DTL_TP_NM));
        pMsg.xxContrList.no(row).contrVrsnEffFromDt.setValue(getMinTm(pMsg.xxContrList.no(row).contrVrsnEffFromDt.getValue(), getString(contrMap, SELECT.CONTR_VRSN_EFF_FROM_DT)));
        pMsg.xxContrList.no(row).contrVrsnEffThruDt.setValue(getMaxTm(pMsg.xxContrList.no(row).contrVrsnEffThruDt.getValue(), getString(contrMap, SELECT.CONTR_VRSN_EFF_THRU_DT)));
        pMsg.xxContrList.no(row).contrEffFromDt.setValue(getMinTm(pMsg.xxContrList.no(row).contrEffFromDt.getValue(), getString(contrMap, SELECT.CONTR_EFF_FROM_DT)));
        pMsg.xxContrList.no(row).contrEffThruDt.setValue(getMaxTm(pMsg.xxContrList.no(row).contrEffThruDt.getValue(), getString(contrMap, SELECT.CONTR_EFF_THRU_DT)));
        pMsg.xxContrList.no(row).svcConfigMstrPk.setValue(getBigDecimal(contrMap, SELECT.SVC_CONFIG_MSTR_PK));
        pMsg.xxContrList.no(row).custPoNum.setValue(getString(contrMap, SELECT.CUST_PO_NUM));
        pMsg.xxContrList.no(row).poDt.setValue(getString(contrMap, SELECT.PO_DT));
        pMsg.xxContrList.no(row).rspTmDownMnAot.setValue(getBigDecimal(contrMap, SELECT.RSP_TM_DOWN_MN_AOT));
        pMsg.xxContrList.no(row).rspTmUpMnAot.setValue(getBigDecimal(contrMap, SELECT.RSP_TM_UP_MN_AOT));
        return;
    }

    private static void setCovPmsg(NSXC001001PMsg pMsg, Map<String, Object> contrMap, String searchTp, int row) {

        String dsCovSvcTpCd = getString(contrMap, SELECT.DS_COV_SVC_TP_CD);
        String svcRateChrgTpCd = getString(contrMap, SELECT.SVC_LBOR_CHRG_TP_CD);
        BigDecimal covSvcRt = getBigDecimal(contrMap, SELECT.DS_COV_SVC_RATE);

        if (DS_COV_SVC_TP.DRUM.equals(dsCovSvcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcPrtDiscRate_D, getMaxRate(pMsg.xxContrList.no(row).svcPrtDiscRate_D.getValue(), covSvcRt));
        }
        if (DS_COV_SVC_TP.CONSUMABLE.equals(dsCovSvcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcPrtDiscRate_C, getMaxRate(pMsg.xxContrList.no(row).svcPrtDiscRate_C.getValue(), covSvcRt));
        }
        if (DS_COV_SVC_TP.LABOR.equals(dsCovSvcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcLborDiscRate, getMaxRate(pMsg.xxContrList.no(row).svcLborDiscRate.getValue(), covSvcRt));
        }
        if (DS_COV_SVC_TP.TRAVEL.equals(dsCovSvcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcTrvlDiscRate, getMaxRate(pMsg.xxContrList.no(row).svcTrvlDiscRate.getValue(), covSvcRt));
        }
        if (DS_COV_SVC_TP.PARTS.equals(dsCovSvcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcPrtDiscRate, getMaxRate(pMsg.xxContrList.no(row).svcPrtDiscRate.getValue(), covSvcRt));
        }
        ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcLborChrgTpCd, svcRateChrgTpCd);
        // QC#2153 Add start
        ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcCovFromHourMn, getString(contrMap, SELECT.SVC_COV_FROM_HOUR_MN));
        ZYPEZDItemValueSetter.setValue(pMsg.xxContrList.no(row).svcCovToHourMn, getString(contrMap, SELECT.SVC_COV_TO_HOUR_MN));
        // QC#2153 end
        return;
    }

    private static BigDecimal getMinRspTmAot(BigDecimal orgRspTm, BigDecimal rspTm) {
        if (!ZYPCommonFunc.hasValue(orgRspTm)) {
            return rspTm;
        } else if (!ZYPCommonFunc.hasValue(rspTm)) {
            return orgRspTm;
        } else if (orgRspTm.compareTo(rspTm) > 0) {
            return rspTm;
        } else {
            return orgRspTm;
        }
    }

    private static BigDecimal getMaxRate(BigDecimal orgRate, BigDecimal rate) {
        if (!ZYPCommonFunc.hasValue(orgRate)) {
            return rate;
        } else if (!ZYPCommonFunc.hasValue(rate)) {
            return orgRate;
        } else if (orgRate.compareTo(rate) > 0) {
            return orgRate;
        } else {
            return rate;
        }
    }

    private static String getMinTm(String orgTm, String rspTm) {
        if (!ZYPCommonFunc.hasValue(orgTm)) {
            return rspTm;
        } else if (!ZYPCommonFunc.hasValue(rspTm)) {
            return orgTm;
        } else if (orgTm.compareTo(rspTm) > 0) {
            return rspTm;
        } else {
            return orgTm;
        }
    }

    private static String getMaxTm(String orgTm, String rspTm) {
        if (!ZYPCommonFunc.hasValue(orgTm)) {
            return rspTm;
        } else if (!ZYPCommonFunc.hasValue(rspTm)) {
            return orgTm;
        } else if (orgTm.compareTo(rspTm) > 0) {
            return orgTm;
        } else {
            return rspTm;
        }
    }

    private static int getDayOfWeek(String yyyymmdd) {
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(ymdFormat.parse(yyyymmdd));
        } catch (ParseException e) {
            return 0;
        }
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * set Error Message.
     * @param msgId Message ID
     */
    private static void setErrMsgId(String msgId) {
        msgMap.addXxMsgId(msgId);
        msgMap.flush();
    }
    

    public static ScheduleInfo getScheduleInfo(String glblCmpyCd, String bllgSchdFromDt, String bllgSchdThruDt, String bllgCycleCd) {
        // TODO
        ScheduleInfo info = new ScheduleInfo();
        info.setBllgCycleUomCd(BLLG_CYCLE_UOM.DAYS);
        info.setPerSchdNum(BigDecimal.TEN);
        return info;
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC402001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BOLTMsg;
import business.db.PODTMsg;
import business.db.POD_STSTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DELY_INSTNTMsg;
import business.db.SHPG_ORD_DELY_TRKTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_PLN_UPD_MODETMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NLZC402001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NWZC003001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC402001.constant.NLZC402001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Delivery Confirmation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/10/2015   CSAI            K.Lee           Create          Initial
 * 04/25/2016   CSAI            K.Lee           Update          QC#7567
 * 07/11/2016   CSAI            Y.Imazu         Update          QC#10917
 * 08/12/2016   CSAI            Y.Imazu         Update          QC#13308
 * 09/13/2016   CITS            K.Ogino         Update          QC#13991
 * 09/15/2016   CSAI            Y.Imazu         Update          QC#14613
 * 11/15/2016   CSAI            Y.Imazu         Update          QC#15964
 * 07/19/2017   Hitachi         K.Kojima        Update          QC#19987
 * 08/04/2017   CITS            T.Tokutomi      Update          QC#20062
 * 04/20/2018   CITS            K.Ogino         Update          QC#24999
 * 05/09/2018   CITS            M.Naito         Update          QC#26023
 * 06/06/2018   CITS            M.Naito         Update          QC#26066
 * 06/07/2018   CITS            M.Naito         Update          QC#26023
 * 05/07/2019   CITS            K.Ogino         Update          QC#31201
 * 10/15/2019   Hitachi         K.Fujimoto      Update          QC#53865
 * 01/08/2019   Fujitsu         R.Nakamura      Update          QC#55310
 *</pre>
 */
public class NLZC402001 extends S21ApiCommonBase implements NLZC402001Constant {

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmBatchClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** BOL information list */
    private List<Map<String, Object>> bolInfoList;

    /** Shipping Plan Information List */
    private List<Map<String, Object>> shpgPlnInfoList;

    /** POD sequence number */
    private String podSeqNum = "";

    public NLZC402001() {

        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NSZC010001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NLZC402001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        this.onBatchType = onBatchType;
        doProcess();
        msgMap.flush();
    }

    /**
     * execute This can be called method from external class.
     * @param List<NSZC010001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NLZC402001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NLZC402001PMsg param : list) {

            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC402001PMsg pMsg = (NLZC402001PMsg) msgMap.getPmsg();

        bolInfoList = new ArrayList<Map<String, Object>>();
        shpgPlnInfoList = new ArrayList<Map<String, Object>>();

        if (isChkParamErr(pMsg)) {

            return;
        }

        if (!createPod(pMsg)) {

            return;
        }

        if (!updateBol(pMsg)) {

            return;
        }

        closeShippingScheduleStatus(pMsg);
    }

    /**
     * createPod
     * @param pMsg NLZC402001PMsg
     * @return boolean
     */
    private boolean createPod(NLZC402001PMsg pMsg) {

        boolean procBySo = false;

        // Process By Pro Number
        if (ZYPCommonFunc.hasValue(pMsg.ediProNum)
                && (ZYPCommonFunc.hasValue(pMsg.ediTpCd) || ZYPCommonFunc.hasValue(pMsg.carrCd))) {

            setBolInfo(pMsg);

            if (this.bolInfoList.isEmpty() && ZYPCommonFunc.hasValue(pMsg.soNum)) {

                procBySo = true;

            } else {

                // QC#31201
                if (this.bolInfoList.isEmpty() && !ZYPCommonFunc.hasValue(pMsg.soNum)) {
                    // Non CSA Carrier IF Process. NLBB021001 only.
                    execPartCallFsrUpdApiByEDI(pMsg);
                    return false;

                } else if (isChkBolInfoErr(pMsg)) { // Check BOL Information

                    return false;
                }

                // Insert POD
                if (!insertPod(pMsg, (String) this.bolInfoList.get(0).get(CARR_CD), pMsg.ediProNum.getValue())) {

                    return false;
                }
            }

        } else {

            procBySo = true;
        }

        // Process By SO NUmber
        if (procBySo) {

            setShpgPlnInfo(pMsg);

            // Check Shipping Plan Information
            if (isChkShpgPlnInfoErr(pMsg)) {

                return false;
            }

            // Insert POD
            Map<String, List<String>> carrProNumMap = new HashMap<String, List<String>>();

            for (Map<String, Object> shpgPlnInfoMap : this.shpgPlnInfoList) {

                String carrCd = (String) shpgPlnInfoMap.get(CARR_CD);
                String proNum = (String) shpgPlnInfoMap.get(PRO_NUM);

                if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(proNum)) { 

                    List<String> proNumList = carrProNumMap.get(carrCd);

                    if (proNumList == null) {

                        proNumList = new ArrayList<String>();

                    } else if (proNumList.contains(proNum)) {

                        continue;
                    }

                    proNumList.add(proNum);
                    carrProNumMap.put(carrCd, proNumList);

                    if (!insertPod(pMsg, carrCd, proNum)) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * updateBol
     * @param pMsg NLZC402001PMsg
     * @return boolean
     */
    private boolean updateBol(NLZC402001PMsg pMsg) {

        if (this.bolInfoList.isEmpty() && this.shpgPlnInfoList.isEmpty()) {

            this.bizErrorProcess(pMsg, NLBM1094E);
            return false;
        }

        // Get POD Status information
        POD_STSTMsg podStsTMsg = new POD_STSTMsg();
        ZYPEZDItemValueSetter.setValue(podStsTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(podStsTMsg.podStsCd, pMsg.ediStsCd.getValue());
        podStsTMsg = (POD_STSTMsg) S21ApiTBLAccessor.findByKey(podStsTMsg);

        if (podStsTMsg == null) {

            bizErrorProcess(pMsg, NLBM1063E, "ediStsCd", pMsg.ediStsCd.getValue());
            return false;
        }

        // QC#31201 PART CALL FSR Update Process.
        String constValue = ZYPCodeDataUtil.getVarCharConstValue("POD_UPD_STS_CD", pMsg.glblCmpyCd.getValue());
        String[] podUpdStss = null;
        List<String> podUpdStsList = null;
        if (constValue != null) {
            podUpdStss = constValue.split(",");
        }
        if (podUpdStss != null) {
            podUpdStsList = Arrays.asList(podUpdStss);
        }

        if (podUpdStsList != null && podUpdStsList.contains(podStsTMsg.podStsCd.getValue())) {

            if (!execPartCallFsrUpdApiByBol(pMsg, podStsTMsg.podStsCd.getValue())) {

                return false;
            }

            if (!execPartCallFsrUpdApiByShpgPln(pMsg, podStsTMsg.podStsCd.getValue())) {

                return false;
            }
        }

        String updShpgStsCd = podStsTMsg.shpgStsCd.getValue();

        boolean isArrivedByBol = false;
        boolean isArrivedBySo = false;

        if (ZYPConstant.FLG_ON_Y.equals(podStsTMsg.shpgStsUpdFlg.getValue())) {

            if (!ZYPCommonFunc.hasValue(updShpgStsCd)) {

                bizErrorProcess(pMsg, NLBM1063E, "ediStsCd", pMsg.ediStsCd.getValue());
                return false;

            } else {

                SHPG_PLN_UPD_MODETMsg bolShpgPlnUpdModeTMsg = new SHPG_PLN_UPD_MODETMsg();
                ZYPEZDItemValueSetter.setValue(bolShpgPlnUpdModeTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(bolShpgPlnUpdModeTMsg.shpgPlnUpdModeCd, MODE_ARRIVED_SOCLOSE);
                bolShpgPlnUpdModeTMsg = (SHPG_PLN_UPD_MODETMsg) S21ApiTBLAccessor.findByKey(bolShpgPlnUpdModeTMsg);

                if (bolShpgPlnUpdModeTMsg != null && updShpgStsCd.equals(bolShpgPlnUpdModeTMsg.shpgStsCd.getValue())) {

                    isArrivedByBol = true;
                }

                SHPG_PLN_UPD_MODETMsg soShpgPlnUpdModeTMsg = new SHPG_PLN_UPD_MODETMsg();
                ZYPEZDItemValueSetter.setValue(soShpgPlnUpdModeTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(soShpgPlnUpdModeTMsg.shpgPlnUpdModeCd, MODE_ARRIVED_SHPG_PLN);
                soShpgPlnUpdModeTMsg = (SHPG_PLN_UPD_MODETMsg) S21ApiTBLAccessor.findByKey(soShpgPlnUpdModeTMsg);

                if (soShpgPlnUpdModeTMsg != null && updShpgStsCd.equals(soShpgPlnUpdModeTMsg.shpgStsCd.getValue())) {

                    isArrivedBySo = true;
                }
            }
        }

        String arrivalDate = pMsg.ediStsDt.getValue();

        if (!ZYPCommonFunc.hasValue(arrivalDate)) {

            arrivalDate = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
        }

        // Process by BOL Information
        if (!this.bolInfoList.isEmpty()) {

            if (!updateBolByBol(pMsg, arrivalDate, isArrivedByBol, podStsTMsg, updShpgStsCd)) {

                return false;
            }
        }

        // Process by Shipping Plan Information
        if (!this.shpgPlnInfoList.isEmpty()) {

            if (!updateBolByShpgPln(pMsg, arrivalDate, isArrivedBySo, podStsTMsg, updShpgStsCd)) {

                return false;
            }
        }

        bolInfoList.clear();
        shpgPlnInfoList.clear();
        return true;
    }

    /**
     * updateBolByBol
     * @param pMsg NLZC402001PMsg
     * @param arrivalDate String
     * @param isArrived boolean
     * @param podStsTMsg POD_STSTMsg
     * @param updShpgStsCd String
     * @return boolean true : OK, false : NG
     */
    private boolean updateBolByBol(NLZC402001PMsg pMsg, String arrivalDate, boolean isArrived, POD_STSTMsg podStsTMsg, String updShpgStsCd) {

        for (Map<String, Object> bolInfoMap : this.bolInfoList) {

            // Arrived
            if (isArrived) {

                // Get Shipping Plan
                Map<String, String> shpgPlnPrm = new HashMap<String, String>();
                shpgPlnPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                shpgPlnPrm.put("bolNum", (String) bolInfoMap.get(BOL_NUM));
                shpgPlnPrm.put("whCd", (String) bolInfoMap.get(WH_CD));

                List<Map<String, Object>> shpgPlnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShppingPlanList", shpgPlnPrm);

                // No Data Found
                if (shpgPlnList == null || shpgPlnList.isEmpty()) {

                    setShpgPlnInfoFromSo(shpgPlnPrm, pMsg);
                    continue;
                }

                List<String> invtyLocCdList = new ArrayList<String>();

                for (Map<String, Object> shpgPlnMap : shpgPlnList) {

                    String invtyLocCd = (String) shpgPlnMap.get(INVTY_LOC_CD);

                    if (!invtyLocCdList.contains(invtyLocCd)) {

                        invtyLocCdList.add(invtyLocCd);
                    }
                }

                // Update Shipping Plan
                List<NWZC003001PMsg> list = new ArrayList<NWZC003001PMsg>();

                for (String invtyLocCd : invtyLocCdList) {

                    NWZC003001PMsg nwzc003001PMsg = new NWZC003001PMsg();
                    nwzc003001PMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                    nwzc003001PMsg.shpgModeCd.setValue(MODE_ARRIVED_SOCLOSE);
                    nwzc003001PMsg.invtyLocCd.setValue(invtyLocCd);
                    nwzc003001PMsg.bolNum.setValue((String) bolInfoMap.get(BOL_NUM));
                    nwzc003001PMsg.actlArvDt.setValue(arrivalDate);
                    list.add(nwzc003001PMsg);
                }

                NWZC003001 api = new NWZC003001();
                api.execute(list, this.onBatchType);

                for (NWZC003001PMsg apiPMsg : list) {

                    if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(apiPMsg), BOL_NUM, (String) bolInfoMap.get(BOL_NUM))) {

                        return false;
                    }
                }

                // Update Shipping Order Detail
                for (Map<String, Object> shpgPlnMap : shpgPlnList) {

                    bolInfoMap.put(SCHD_DELY_DT, (String) shpgPlnMap.get(SCHD_DELY_DT));

                    if (!updShpgOrdDtl(pMsg, (String) shpgPlnMap.get(SO_NUM), (String) shpgPlnMap.get(SO_SLP_NUM), arrivalDate, bolInfoMap)) {

                        return false;
                    }
                }
            }

            // Shipping Status Update for BOL
            if (ZYPConstant.FLG_ON_Y.equals(podStsTMsg.shpgStsUpdFlg.getValue())) {

                String bolShpgStsCd = (String) bolInfoMap.get(SHPG_STS_CD);

                if (ZYPCommonFunc.hasValue(updShpgStsCd) && updShpgStsCd.compareTo(bolShpgStsCd) > 0) {

                    // Update BOL
                    BOLTMsg bolTMsg = new BOLTMsg();
                    ZYPEZDItemValueSetter.setValue(bolTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(bolTMsg.whCd, (String) bolInfoMap.get(WH_CD));
                    ZYPEZDItemValueSetter.setValue(bolTMsg.bolNum, (String) bolInfoMap.get(BOL_NUM));
                    ZYPEZDItemValueSetter.setValue(bolTMsg.shpgStsCd, updShpgStsCd);
                    S21ApiTBLAccessor.updateSelectionField(bolTMsg, new String[]{"shpgStsCd"});

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(bolTMsg.getReturnCode())) {

                        bizErrorProcess(pMsg, NLBM1064E, BOL_NUM, bolTMsg.bolNum.getValue());
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * updateBolByShpgPln
     * @param pMsg NLZC402001PMsg
     * @param arrivalDate String
     * @param isArrived boolean
     * @param podStsTMsg POD_STSTMsg
     * @param updShpgStsCd String
     * @return boolean true : OK, false : NG
     */
    private boolean updateBolByShpgPln(NLZC402001PMsg pMsg, String arrivalDate, boolean isArrived, POD_STSTMsg podStsTMsg, String updShpgStsCd) {

        Map<String, List<String>> whBolMap = new HashMap<String, List<String>>();

        for (Map<String, Object> shpgPlnInfoMap : this.shpgPlnInfoList) {

            // Arrived
            if (isArrived) {

                // Update Shipping Plan
                NWZC003001PMsg nwzc003001PMsg = new NWZC003001PMsg();
                ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.shpgModeCd, MODE_ARRIVED_SHPG_PLN);
                ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.shpgPlnNum, (String) shpgPlnInfoMap.get(SHPG_PLN_NUM));
                ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.proNum, (String) shpgPlnInfoMap.get(PRO_NUM));
                ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.carrCd, (String) shpgPlnInfoMap.get(CARR_CD));
                ZYPEZDItemValueSetter.setValue(nwzc003001PMsg.actlArvDt, arrivalDate);

                List<NWZC003001PMsg> list = new ArrayList<NWZC003001PMsg>();
                list.add(nwzc003001PMsg);

                NWZC003001 api = new NWZC003001();
                api.execute(list, this.onBatchType);

                for (NWZC003001PMsg apiPMsg : list) {

                    if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(apiPMsg), SHPG_PLN_NUM, apiPMsg.shpgPlnNum.getValue())) {

                        return false;
                    }
                }

                // Update Shipping Order Detail
                if (!updShpgOrdDtl(pMsg, (String) shpgPlnInfoMap.get(SO_NUM), (String) shpgPlnInfoMap.get(SO_SLP_NUM), arrivalDate, shpgPlnInfoMap)) {

                    return false;
                }
            }

            // Shipping Status Update for BOL
            if (ZYPConstant.FLG_ON_Y.equals(podStsTMsg.shpgStsUpdFlg.getValue())) {

                String bolNum = (String) shpgPlnInfoMap.get("BOL_NUM");
                String whCd = (String) shpgPlnInfoMap.get("RTL_WH_CD");

                if (ZYPCommonFunc.hasValue(bolNum) && ZYPCommonFunc.hasValue(whCd)) {

                    List<String> bolList = whBolMap.get(whCd);

                    if (bolList == null) {

                        bolList = new ArrayList<String>();

                    } else if (bolList.contains(bolNum)) {

                        continue;
                    }

                    bolList.add(bolNum);
                    whBolMap.put(whCd, bolList);

                    BOLTMsg bolTMsg = new BOLTMsg();
                    ZYPEZDItemValueSetter.setValue(bolTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(bolTMsg.whCd, whCd);
                    ZYPEZDItemValueSetter.setValue(bolTMsg.bolNum, bolNum);
                    bolTMsg = (BOLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(bolTMsg);

                    if (bolTMsg != null) {

                        if (ZYPCommonFunc.hasValue(updShpgStsCd) && updShpgStsCd.compareTo(bolTMsg.shpgStsCd.getValue()) > 0) {

                            // Update BOL
                            ZYPEZDItemValueSetter.setValue(bolTMsg.shpgStsCd, updShpgStsCd);
                            S21ApiTBLAccessor.updateSelectionField(bolTMsg, new String[]{"shpgStsCd"});

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(bolTMsg.getReturnCode())) {

                                bizErrorProcess(pMsg, NLBM1064E, BOL_NUM, bolTMsg.bolNum.getValue());
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * chkApiRslt
     * @param pMsg NLZC402001PMsg
     * @param msgIdList List<String>
     * @param keyInfo String
     * @param keyVal String
     * @return boolean true : OK, false : NG
     */
    private boolean chkApiRslt(EZDPMsg pMsg, List<String> msgIdList, String keyInfo, String keyVal) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith("E")) {

                    bizErrorProcess(pMsg, msgId, keyInfo, keyVal);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * updShpgOrdDtl
     * @param pMsg NLZC402001PMsg
     * @param soNum String
     * @param soSlpNum String
     * @param actlDelyDt String
     * @param bolProMap Map<String, Object>
     * @return boolean
     */
    private boolean updShpgOrdDtl(NLZC402001PMsg pMsg, String soNum, String soSlpNum, String actlDelyDt, Map<String, Object> bolProMap) {

        SHPG_ORD_DTLTMsg shpgOrdDtlMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.soSlpNum, soSlpNum);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.actlDelyDt, actlDelyDt);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.actlDelyTm, pMsg.ediStsTm.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());

        if (ZYPCommonFunc.hasValue(shpgOrdDtlMsg.glblCmpyCd) && ZYPCommonFunc.hasValue(shpgOrdDtlMsg.soNum) && ZYPCommonFunc.hasValue(shpgOrdDtlMsg.soSlpNum)) {

            String[] updList = new String[] {"actlDelyDt", "actlDelyTm", "carrRsnCd", "carrCmntTxt"};
            S21ApiTBLAccessor.updateSelectionField(shpgOrdDtlMsg, updList);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDtlMsg.getReturnCode())) {

                bizErrorProcess(pMsg, NLZM2257E, SO_NUM, soNum);
                return false;
            }

            // Update SO Delivery Tracking
            Map<String, String> soDelyTrkparam = new HashMap<String, String>();
            soDelyTrkparam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            soDelyTrkparam.put("soNum", soNum);
            soDelyTrkparam.put("soSlpNum", soSlpNum);
            soDelyTrkparam.put("flgY", ZYPConstant.FLG_ON_Y);

            List<BigDecimal> shpgOrdDelyTrkPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getLatestShpgOrdDelyTrk", soDelyTrkparam);

            if (shpgOrdDelyTrkPkList != null && !shpgOrdDelyTrkPkList.isEmpty()) {

                for (BigDecimal shpgOrdDelyTrkPk : shpgOrdDelyTrkPkList) {

                    SHPG_ORD_DELY_TRKTMsg updateMsg = new SHPG_ORD_DELY_TRKTMsg();
                    ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(updateMsg.shpgOrdDelyTrkPk, shpgOrdDelyTrkPk);
                    updateMsg = (SHPG_ORD_DELY_TRKTMsg) S21ApiTBLAccessor.findByKey(updateMsg);

                    if (updateMsg != null) {

                        ZYPEZDItemValueSetter.setValue(updateMsg.podLtstRecFlg, ZYPConstant.FLG_OFF_N);
                        S21ApiTBLAccessor.update(updateMsg);
                    }
                }
            }

            // Insert SO Delivery Tracking
            SHPG_ORD_DELY_TRKTMsg shpgOrdDelyTrkTMsg = new SHPG_ORD_DELY_TRKTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.shpgOrdDelyTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SHPG_ORD_DELY_TRK_SQ"));
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.updUsrId, shpgOrdDtlMsg.ezUpUserID.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.updTs, shpgOrdDtlMsg.ezUpTime.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.soNum, soNum);
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.soSlpNum, soSlpNum);
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.bolNum, (String) bolProMap.get(BOL_NUM));
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.proNum, (String) bolProMap.get(PRO_NUM));
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.carrCd, (String) bolProMap.get(CARR_CD));
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.schdDelyDt, (String) bolProMap.get(SCHD_DELY_DT));
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.carrRsnCd, pMsg.carrRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.carrCmntTxt, pMsg.carrCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podStsRsnCd, pMsg.ediStsRsnCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podStsCd, pMsg.ediStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podStsDt, pMsg.ediStsDt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podStsTm, pMsg.ediStsTm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podTmCd, pMsg.ediTmCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podStsCtyNm, pMsg.ediStsCtyNm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podStsStCd, pMsg.ediStsStCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podExCd, pMsg.ediExCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podExPkgCd, pMsg.ediExPkgCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podExLdgQty, pMsg.ediExLoadQty.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podLdgQty, pMsg.ediLoadQty.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podPkgCd, pMsg.ediPkgCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podWt, pMsg.ediWt.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.shpprNm, pMsg.ediShpprNm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.shpprAddr, pMsg.ediShpprAddr.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.shpprCtyNm, pMsg.ediShpprCtyNm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.shpprStCd, pMsg.ediShpprStCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.shpprPostCd, pMsg.ediShpprPostCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podCnsgnNm, pMsg.ediCnsgnNm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.cnsgnAddr, pMsg.ediCnsgnAddr.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.cnsgnCtyNm, pMsg.ediCnsgnCtyNm.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.cnsgnStCd, pMsg.ediCnsgnStCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.cnsgnPostCd, pMsg.ediCnsgnPostCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podLtstRecFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(shpgOrdDelyTrkTMsg.podSrcTpCd, pMsg.podSrcTpCd.getValue());
            S21ApiTBLAccessor.insert(shpgOrdDelyTrkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDelyTrkTMsg.getReturnCode())) {

                bizErrorProcess(pMsg, NLBM1064E, SO_NUM, soNum);
                return false;
            }

            String actlDelyDtTm = actlDelyDt;

            if (ZYPCommonFunc.hasValue(pMsg.ediStsTm)) {

                actlDelyDtTm = actlDelyDtTm + pMsg.ediStsTm.getValue();
            }

            // Update FSR for Install
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            param.put("soNum", soNum);
            param.put("soSlpNum", soSlpNum);
            param.put("svcTaskStsPendInstl", SVC_TASK_STS.PENDING_INSTALL);
            // START 2018/05/09 M.Naito [QC#26023,ADD]
            param.put("svcTaskStsTBO", SVC_TASK_STS.TBO);
            param.put("svcTaskStsScheduled", SVC_TASK_STS.SCHEDULED);
            param.put("svcTaskStsAssigned", SVC_TASK_STS.ASSIGNED);
            // END 2018/05/09 M.Naito [QC#26023,ADD]

            List<Map<String, Object>> fsrTaskMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrTaskList", param);

            if (!execFsrUpdApi(fsrTaskMapList, pMsg, shpgOrdDtlMsg, actlDelyDtTm, true)) {

                return false;
            }

            // Update FSR for HDD Removal
            fsrTaskMapList = new ArrayList<Map<String, Object>>();
            param.put("dsSvcCallTpRmaWithInstl", DS_SVC_CALL_TP.HARD_DRIVE_REMOVAL_INSTALL);

            fsrTaskMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getRmvFsrTaskList", param);

            if (!execFsrUpdApi(fsrTaskMapList, pMsg, shpgOrdDtlMsg, actlDelyDtTm, false)) {

                return false;
            }

            // MM Update API. Update MIF(SVC_MACH_USG_STS=AT_CUSTOMER)
            HashMap<String, String> paramMach = new HashMap<String, String>();
            paramMach.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            paramMach.put("soNum", soNum);
            paramMach.put("soSlpNum", soSlpNum);
            paramMach.put("stsWtngForInstl", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
            paramMach.put("usgStsAtCust", SVC_MACH_USG_STS.AT_CUSTOMER);

            List<Map<String, Object>> sMMPkMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getSMMPk", paramMach);

            if (sMMPkMapList != null && !sMMPkMapList.isEmpty()) {

                for (Map<String, Object> sMMPkMap : sMMPkMapList) {

                    NSZC001001 mmrUpdApi = new NSZC001001();
                    NSZC001001PMsg mmrPMsg = createSMMRUpdateApiPMsg(pMsg, (BigDecimal) sMMPkMap.get("SVC_MACH_MSTR_PK"), (String) sMMPkMap.get("SER_NUM"));
                    mmrUpdApi.execute(mmrPMsg, this.onBatchType);

                    if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(mmrPMsg), SO_NUM, soNum)) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * execFsrUpdApi
     * @param fsrTaskMapList List<Map<String, Object>>
     * @param pMsg NLZC402001PMsg
     * @param shpgOrdDtlMsg SHPG_ORD_DTLTMsg
     * @param actlDelyDtTm String
     * @param isInstlFsr boolean
     * @return boolean
     */
    private boolean execFsrUpdApi(List<Map<String, Object>> fsrTaskMapList, NLZC402001PMsg pMsg, SHPG_ORD_DTLTMsg shpgOrdDtlMsg, String actlDelyDtTm, boolean isInstlFsr) {

        if (fsrTaskMapList != null && !fsrTaskMapList.isEmpty()) {

            for (Map<String, Object> fsrTaskMap : fsrTaskMapList) {

                // START 2018/06/06 M.Naito [QC#26066,MOD]
                String techMeetTruckFlg = (String) fsrTaskMap.get("TECH_MEET_TRUCK_FLG");
                // START 2017/07/19 K.Kojima [QC#19987,ADD]
                String erlStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
                if (ZYPConstant.FLG_OFF_N.equals(techMeetTruckFlg)) {
                    erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                            pMsg.glblCmpyCd.getValue()
                          , BigDecimal.ZERO
                          , (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK")
                          , erlStartTs.substring(0, 8)
                          , erlStartTs.substring(8, 14)
                          , false
                    );
                }
                // END 2017/07/19 K.Kojima [QC#19987,ADD]

                // check Instructions Comment
                boolean shpgOrdDelyInstnFlg = false;
                SHPG_ORD_DELY_INSTNTMsg shpgOrdDelyInstnTMsg = new SHPG_ORD_DELY_INSTNTMsg();
                shpgOrdDelyInstnTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                shpgOrdDelyInstnTMsg.soNum.setValue(pMsg.soNum.getValue());
                shpgOrdDelyInstnTMsg = (SHPG_ORD_DELY_INSTNTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDelyInstnTMsg);
                if (shpgOrdDelyInstnTMsg != null) {
                    if (ZYPCommonFunc.hasValue(shpgOrdDelyInstnTMsg.istlInstnCmntTxt) || ZYPCommonFunc.hasValue(shpgOrdDelyInstnTMsg.techInstnCmntTxt)) {
                        shpgOrdDelyInstnFlg = true;
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals(techMeetTruckFlg) && !shpgOrdDelyInstnFlg) {
                    continue;
                }
                // END 2018/06/06 M.Naito [QC#26066,MOD]

                NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                // START 2018/06/07 M.Naito [QC#26066,ADD]
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.slsDt, pMsg.ediStsDt.getValue());
                // START 2018/06/07 M.Naito [QC#26066,ADD]
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, shpgOrdDtlMsg.ezUpUserID.getValue());
                // START 2019/10/15  K.Fujimoto [QC#53865, MOD]
                // START 2018/06/07 M.Naito [QC#26023,MOD]
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
                // END 2018/06/07 M.Naito [QC#26023,MOD]
                // END 2019/10/15  K.Fujimoto [QC#53865, MOD]
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
                // START 2017/07/19 K.Kojima [QC#19987,MOD]
                // ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, ZYPCommonFunc.rightPad(actlDelyDtTm, 17, "0"));
                // START 2018/06/06 M.Naito [QC#26066,MOD]
                if (ZYPConstant.FLG_OFF_N.equals(techMeetTruckFlg)) {
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, erlStartTs);
                }
                // END 2018/06/06 M.Naito [QC#26066,MOD]
                // START 2017/07/19 K.Kojima [QC#19987,MOD]
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);

                if (isInstlFsr) {

                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).techCd, (String) fsrTaskMap.get("ISTL_TECH_CD"));
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
                }

                fsrUpdPMsg.taskList.setValidCount(1);

                // set Instructions Comment for ClickMobile
                if (shpgOrdDelyInstnFlg) {
                    int i = 0;
                    if (ZYPCommonFunc.hasValue(shpgOrdDelyInstnTMsg.istlInstnCmntTxt)) {
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMemoList.no(i).svcCmntTxt, shpgOrdDelyInstnTMsg.istlInstnCmntTxt.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMemoList.no(i).svcMemoTpCd, SVC_MEMO_TP.TECHNICIAN_PAGE_NOTES);
                        i++;
                    }
                    if (ZYPCommonFunc.hasValue(shpgOrdDelyInstnTMsg.techInstnCmntTxt)) {
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMemoList.no(i).svcCmntTxt, shpgOrdDelyInstnTMsg.techInstnCmntTxt.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMemoList.no(i).svcMemoTpCd, SVC_MEMO_TP.TECHNICIAN_PAGE_NOTES);
                        i++;
                    }
                    fsrUpdPMsg.svcMemoList.setValidCount(i);
                }

                NSZC043001 api = new NSZC043001();
                api.execute(fsrUpdPMsg, this.onBatchType);

                if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(fsrUpdPMsg), SO_NUM, shpgOrdDtlMsg.soNum.getValue())) {

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * isChkParamErr
     * @param pMsg NLZC402001PMsg
     * @return boolean
     */
    private boolean isChkParamErr(NLZC402001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.ediProNum) && !ZYPCommonFunc.hasValue(pMsg.soNum)) {

            this.bizErrorProcess(pMsg, NLBM1089E);
            return true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.soNum) && !ZYPCommonFunc.hasValue(pMsg.ediTpCd) && !ZYPCommonFunc.hasValue(pMsg.carrCd)) {

            this.bizErrorProcess(pMsg, NLBM1090E);
            return true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.ediStsCd)) {

            this.bizErrorProcess(pMsg, NLBM1093E);
            return true;
        }

        if (ZYPCommonFunc.hasValue(pMsg.ediStsDt) && !ZYPDateUtil.checkDate(pMsg.ediStsDt.getValue())) {

            this.bizErrorProcess(pMsg, NLBM1091E, "ediStsDt", pMsg.ediStsDt.getValue());
            return true;
        }

        if (ZYPCommonFunc.hasValue(pMsg.ediStsTm) && !checkHHMISS(pMsg.ediStsTm.getValue())) {

            this.bizErrorProcess(pMsg, NLBM1092E, "ediStsTm", pMsg.ediStsTm.getValue());
            return true;
        }

        if (ZYPCommonFunc.hasValue(pMsg.ediPickUpDt) && !ZYPDateUtil.checkDate(pMsg.ediPickUpDt.getValue())) {

            this.bizErrorProcess(pMsg, NLBM1102E, "ediPickUpDt", pMsg.ediPickUpDt.getValue());
            return true;
        }

        if (ZYPCommonFunc.hasValue(pMsg.ediPickUpTm) && !checkHHMISS(pMsg.ediPickUpTm.getValue())) {

            this.bizErrorProcess(pMsg, NLBM1102E, "ediPickUpTm", pMsg.ediPickUpTm.getValue());
            return true;
        }

        return false;
    }

    /**
     * isChkBolInfoErr
     * @param pMsg NLZC402001PMsg
     * @return boolean
     */
    private boolean isChkBolInfoErr(NLZC402001PMsg pMsg) {

        if (this.bolInfoList.isEmpty()) {

            this.bizErrorProcess(pMsg, NLBM1094E);
            return true;
        }

        boolean hasErr = false;

        for (Map<String, Object> bolInfoMap : this.bolInfoList) {

            String carrCd = (String) bolInfoMap.get(CARR_CD);
            String shipFlg = (String) bolInfoMap.get(SHIP_FLG);

            if (!ZYPCommonFunc.hasValue(carrCd)) {

                this.bizErrorProcess(pMsg, NLBM1090E);
                hasErr = true;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(shipFlg)) {

                this.bizErrorProcess(pMsg, NLZM2472E, SHPG_STS_CD, (String) bolInfoMap.get(SHPG_STS_CD));
                hasErr = true;
            }
        }

        if (hasErr) {

            return true;
        }

        return false;
    }

    /**
     * bizErrorProcess
     * @param pMsg NLZC402001PMsg
     * @param messageCd String
     */
    private void bizErrorProcess(NLZC402001PMsg pMsg, String messageCd) {

        S21InfoLogOutput.println(messageCd);
        msgMap.addXxMsgId(messageCd);
        S21InfoLogOutput.println(msgMap.getPmsg().toString());
    }

    /**
     * bizErrorProcess
     * @param pMsg NLZC402001PMsg
     * @param messageCd String
     * @param keyInfo String
     * @param keyVal String
     */
    private void bizErrorProcess(EZDPMsg pMsg, String messageCd, String keyInfo, String keyVal) {

        S21InfoLogOutput.println(messageCd + " " + keyInfo + ":" + keyVal);
        msgMap.addXxMsgId(messageCd);
        S21InfoLogOutput.println(msgMap.getPmsg().toString());
    }

    /**
     * setBolInfo
     * @param stsInfo Map
     */
    private void setBolInfo(NLZC402001PMsg pMsg) {

        List<Map<String, Object>> bolInfoMapList = new ArrayList<Map<String, Object>>();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("carrCd", pMsg.carrCd.getValue());
        params.put("proNum", pMsg.ediProNum.getValue());

        if (!ZYPCommonFunc.hasValue(pMsg.carrCd)) {

            params.put("ediTpCd", pMsg.ediTpCd.getValue());
        }

        bolInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBolStatus", params);

        // Use "like condition" and retry because EDI_PRO_NUM often lost last char 
        if (bolInfoMapList == null || bolInfoMapList.isEmpty()) {

            params.put("proNum", pMsg.ediProNum.getValue() + "%");

            bolInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBolStatus", params);
        }

        // Set BOL Information
        if (bolInfoMapList != null && !bolInfoMapList.isEmpty()) {

            for (Map<String, Object> bolInfoMap : bolInfoMapList) {

                bolInfoList.add(bolInfoMap);
            }
        }
    }

    /**
     * setShpgPlnInfo
     * @param pMsg NLZC402001PMsg
     */
    private void setShpgPlnInfo(NLZC402001PMsg pMsg) {

        List<Map<String, Object>> shpgPlnInfoMapList = new ArrayList<Map<String, Object>>();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("soNum", pMsg.soNum.getValue());
        params.put("soSlpNum", pMsg.soSlpNum.getValue());

        shpgPlnInfoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShpgPlnInfo", params);

        // Set Shipping Plan Information
        if (shpgPlnInfoMapList != null && !shpgPlnInfoMapList.isEmpty()) {

            for (Map<String, Object> shpgPlnInfoMap : shpgPlnInfoMapList) {

                shpgPlnInfoList.add(shpgPlnInfoMap);
            }
        }
    }

    /**
     * setShpgPlnInfoFromSo
     * @param shpgPlnPrm Map<String, String>
     * @param pMsg NLZC402001PMsg
     */
    private void setShpgPlnInfoFromSo(Map<String, String> shpgPlnPrm, NLZC402001PMsg pMsg) {

        List<Map<String, Object>> shpgPlnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShppingPlanListFromSo", shpgPlnPrm);

        if (shpgPlnList != null && !shpgPlnList.isEmpty()) {

            for (Map<String, Object> shpgPlnMap : shpgPlnList) {

                shpgPlnInfoList.add(shpgPlnMap);
            }

            return;
        }

        if (ZYPCommonFunc.hasValue(pMsg.soNum)) {

            setShpgPlnInfo(pMsg);
        }
    }

    /**
     * isChkShpgPlnInfoErr
     * @param pMsg NLZC402001PMsg
     * @return boolean
     */
    private boolean isChkShpgPlnInfoErr(NLZC402001PMsg pMsg) {

        if (this.shpgPlnInfoList.isEmpty()) {

            this.bizErrorProcess(pMsg, NLZM2467E);
            return true;
        }

        boolean hasErr = false;

        for (int i = 0; i < this.shpgPlnInfoList.size(); i++) {

            Map<String, Object> shpgPlnInfoMap = this.shpgPlnInfoList.get(i);

            String carrCd = (String) shpgPlnInfoMap.get(CARR_CD);
            String ediTpCd = (String) shpgPlnInfoMap.get(EDI_TP_CD);
            String proNum = (String) shpgPlnInfoMap.get(PRO_NUM);
            String shipFlg = (String) shpgPlnInfoMap.get(SHIP_FLG);

            // Pro Number
            if (ZYPCommonFunc.hasValue(pMsg.ediProNum)) { 

                if(ZYPCommonFunc.hasValue(proNum)) {

                    if (!pMsg.ediProNum.getValue().equals(proNum)) {

                        this.bizErrorProcess(pMsg, NLZM2468E, "ediProNum", pMsg.ediProNum.getValue());
                        hasErr = true;
                    }

                } else {

                    this.shpgPlnInfoList.get(i).put(PRO_NUM, pMsg.ediProNum.getValue());
                }
            }

            // Carrier Code
            if (ZYPCommonFunc.hasValue(pMsg.carrCd)) {

                if (ZYPCommonFunc.hasValue(carrCd)) {

                    if (!pMsg.carrCd.getValue().equals(carrCd)) {

                        this.bizErrorProcess(pMsg, NLZM2469E, "carrCd", pMsg.carrCd.getValue());
                        hasErr = true;
                    }

                } else {

                    this.shpgPlnInfoList.get(i).put(CARR_CD, pMsg.carrCd.getValue());
                }
            }

            // EDI Type Code
            if (ZYPCommonFunc.hasValue(pMsg.ediTpCd) && ZYPCommonFunc.hasValue(ediTpCd)) {

                if (!pMsg.ediTpCd.getValue().equals(ediTpCd)) {

                    this.bizErrorProcess(pMsg, NLZM2470E, "ediTpCd", pMsg.ediTpCd.getValue());
                    hasErr = true;
                }
            }

            // Shipping Status
            if (!ZYPConstant.FLG_ON_Y.equals(shipFlg)) {

                this.bizErrorProcess(pMsg, NLZM2473E, SHPG_STS_CD, (String) shpgPlnInfoMap.get(SHPG_STS_CD));
                hasErr = true;
            }
        }

        if (hasErr) {

            return true;
        }

        return false;
    }

    /**
     * insertPod
     * @param pMsg NLZC402001PMsg
     * @param carrCd String
     * @param proNum String
     * @return boolean
     */
    private boolean insertPod(NLZC402001PMsg pMsg, String carrCd, String proNum) {

        // Get POD sequence number
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("carrCd", carrCd);
        params.put("proNum", proNum);
        String maxNum = (String) ssmBatchClient.queryObject("getMaxPodSeqNum", params);

        if (ZYPCommonFunc.hasValue(maxNum)) {

            this.podSeqNum =  plusOne(maxNum);

        } else {

            this.podSeqNum = FIRST_SEQUENCE_NUMBER;
        }

        // Update POD
        PODTMsg updateMsg = new PODTMsg();
        ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(updateMsg.carrCd, carrCd);
        ZYPEZDItemValueSetter.setValue(updateMsg.proNum, proNum);
        ZYPEZDItemValueSetter.setValue(updateMsg.podLtstRecFlg, ZYPConstant.FLG_OFF_N);
        S21ApiTBLAccessor.updateByPartialKey(updateMsg, new String[]{"podLtstRecFlg"});

        // Insert POD
        PODTMsg insertMsg = new PODTMsg();
        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.carrCd, carrCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.proNum, proNum);
        ZYPEZDItemValueSetter.setValue(insertMsg.podSqNum, this.podSeqNum);
        ZYPEZDItemValueSetter.setValue(insertMsg.podStsRsnCd, pMsg.ediStsRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podStsCd, pMsg.ediStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podStsDt, pMsg.ediStsDt.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podStsTm, pMsg.ediStsTm.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podTmCd, pMsg.ediTmCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podStsCtyNm, pMsg.ediStsCtyNm.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podStsStCd, pMsg.ediStsStCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podExCd, pMsg.ediExCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podExPkgCd, pMsg.ediExPkgCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podExLdgQty, pMsg.ediExLoadQty.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podLdgQty, pMsg.ediLoadQty.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podPkgCd, pMsg.ediPkgCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podWt, pMsg.ediWt.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.shpprNm, pMsg.ediShpprNm.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.shpprAddr, pMsg.ediShpprAddr.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.shpprCtyNm, pMsg.ediShpprCtyNm.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.shpprStCd, pMsg.ediShpprStCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.shpprPostCd, pMsg.ediShpprPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podCnsgnNm, pMsg.ediCnsgnNm.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.cnsgnAddr, pMsg.ediCnsgnAddr.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.cnsgnCtyNm, pMsg.ediCnsgnCtyNm.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.cnsgnStCd, pMsg.ediCnsgnStCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.cnsgnPostCd, pMsg.ediCnsgnPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(insertMsg.podLtstRecFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insertMsg.podSrcTpCd, pMsg.podSrcTpCd.getValue());
        S21ApiTBLAccessor.insert(insertMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertMsg.getReturnCode())) {

            bizErrorProcess(pMsg, NLBM1064E, PRO_NUM, proNum);
            // Add Start 2020/01/08 QC#55310
            // Output Return Code
            S21InfoLogOutput.println(insertMsg.getReturnCode());
            // Add End 2020/01/08 QC#55310
            return false;
        }

        return true;
    }

    /**
     * Check correct time(HHMISS)
     * @param str String
     * @return boolean
     */
    private boolean checkHHMISS(String str) {

        if (str.length() != 6) {

            return false;
        }

        try {

            int hh = Integer.parseInt(str.substring(0, 2));
            int mm = Integer.parseInt(str.substring(2, 4));
            int ss = Integer.parseInt(str.substring(4, 6));

            if (hh < 0 || hh > 23) {

                return false;
            }

            if (mm < 0 || mm > 59) {

                return false;
            }

            if (ss < 0 || ss > 59) {

                return false;
            }

        } catch (NumberFormatException e) {

            return false;
        }

        return true;
    }

    /**
     * Add one to the parameter
     * @param str String
     * @return String
     */
    private String plusOne(String str) {

        int num = Integer.parseInt(str);
        num += 1;
        return ZYPCommonFunc.leftPad(Integer.toString(num), SEQUENCE_LENGTH, PADDING_NUMBER);
    }

    /**
     * Create NSZC001001PMsg
     * @param inMsg NLZC402001PMsg
     * @param svcMachMstrPk BigDecimal
     * @param serNum String
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg createSMMRUpdateApiPMsg(NLZC402001PMsg inMsg, BigDecimal svcMachMstrPk, String serNum) {

        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(inMsg, svcMachMstrPk, serNum);

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, inMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, inMsg.ediStsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_MACHINE_IN_FIELD.code);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, svcMachMstrTMsg.locNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addlLocNm, svcMachMstrTMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, svcMachMstrTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, svcMachMstrTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.thirdLineAddr, svcMachMstrTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.frthLineAddr, svcMachMstrTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, svcMachMstrTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, svcMachMstrTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(pMsg.provNm, svcMachMstrTMsg.provNm);
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, svcMachMstrTMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, svcMachMstrTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, svcMachMstrTMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachFlNm, svcMachMstrTMsg.svcMachFlNm);
        ZYPEZDItemValueSetter.setValue(pMsg.iwrCondCd, svcMachMstrTMsg.iwrCondCd);
        ZYPEZDItemValueSetter.setValue(pMsg.istlDt, svcMachMstrTMsg.istlDt);
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, svcMachMstrTMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, svcMachMstrTMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);
        ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTMsg.prntSerNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, svcMachMstrTMsg.svcMachTrxTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, svcMachMstrTMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, svcMachMstrTMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, svcMachMstrTMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, svcMachMstrTMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, svcMachMstrTMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, svcMachMstrTMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, svcMachMstrTMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, svcMachMstrTMsg.svcMachQty);
        pMsg.xxCmptMachList.setValidCount(0);
        return pMsg;
    }

    /**
     * find SVC_MACH_MSTR
     * @param pMsg NLZC402001PMsg
     * @param svcMachMstrPk BigDecimal
     * @param serNum String
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(NLZC402001PMsg pMsg, BigDecimal svcMachMstrPk, String serNum) {

        SVC_MACH_MSTRTMsg rtnMsg = null;
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();

        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {

            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
            rtnMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);

        } else {

            inMsg.setSQLID("002");
            inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("serNum01", serNum);
            SVC_MACH_MSTRTMsgArray rtnList = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

            if (rtnList.getValidCount() > 0) {

                rtnMsg = rtnList.no(0);
            }
        }

        return rtnMsg;
    }

    /**
     * closeShippingScheduleStatus
     * @param pMsg NLZC402001PMsg
     */
    private void closeShippingScheduleStatus(NLZC402001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // QC#24999
        if (ZYPCommonFunc.hasValue(pMsg.ediProNum.getValue())) {
            Map<String, String> shpgPlnPrm = new HashMap<String, String>();
            shpgPlnPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            shpgPlnPrm.put("proNum", pMsg.ediProNum.getValue());

            List<String> soList = (List<String>) ssmBatchClient.queryObjectList("getShppingPlanSoNum", shpgPlnPrm);

            if (soList != null && !soList.isEmpty()) {

                for (String soNum : soList) {
                    updateSchdCoordSts(glblCmpyCd, soNum);
                }
                return;
            }
        }

        String soNum = pMsg.soNum.getValue();

        updateSchdCoordSts(glblCmpyCd, soNum);

    }

    /**
     * isAllDeliveryConf
     * @param glblCmpyCd String
     * @param soNum String
     * @return true:All Delivery Conf / false: Not All Delivery
     */
    private boolean isAllDeliveryConf(String glblCmpyCd, String soNum) {

        Map<String, String> searchCriteria = new HashMap<String, String>();
        searchCriteria.put("glblCmpyCd", glblCmpyCd);
        searchCriteria.put("soNum", soNum);
        searchCriteria.put("shpgStsCd", SHPG_STS.SHIPPED);

        int notDeliveryRecordCount = (Integer) ssmBatchClient.queryObject("countNotDeliveryConfRecord", searchCriteria);

        if (notDeliveryRecordCount > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Add QC#24999
     * updateSchdCoordSts
     * @param glblCmpyCd String
     * @param soNum String
     */
    private void updateSchdCoordSts(String glblCmpyCd, String soNum) {

        if (ZYPCommonFunc.hasValue(soNum) && isAllDeliveryConf(glblCmpyCd, soNum)) {
            // Update Shipping Order Schedule Status
            SHPG_ORDTMsg shpgOrd = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrd.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrd.soNum, soNum);
            shpgOrd = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shpgOrd);

            if (shpgOrd == null) {
                this.msgMap.addXxMsgId(NLZM2391E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(shpgOrd.schdCoordStsCd, SCHD_COORD_STS.CLOSED);
            S21ApiTBLAccessor.update(shpgOrd);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrd.getReturnCode())) {
                this.msgMap.addXxMsgId(NLZM2513E);
            }

        }
    }

    /**
     * execPartCallFsrUpdApiByBol Add QC#31201.
     * @param pMsg NLZC402001PMsg
     * @param podStsCd String
     * @return boolean
     */
    private boolean execPartCallFsrUpdApiByBol(NLZC402001PMsg pMsg, String podStsCd) {

        for (Map<String, Object> bolInfoMap : this.bolInfoList) {
            // Get Shipping Plan
            Map<String, String> shpgPlnPrm = new HashMap<String, String>();
            shpgPlnPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            shpgPlnPrm.put("bolNum", (String) bolInfoMap.get(BOL_NUM));
            shpgPlnPrm.put("whCd", (String) bolInfoMap.get(WH_CD));

            List<Map<String, Object>> shpgPlnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShppingPlanList", shpgPlnPrm);

            // No Data Found
            if (shpgPlnList == null || shpgPlnList.isEmpty()) {
                continue;
            }

            String arrivalDate = pMsg.ediStsDt.getValue();

            if (!ZYPCommonFunc.hasValue(arrivalDate)) {

                arrivalDate = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
            }

            String actlDelyDtTm = arrivalDate;

            if (ZYPCommonFunc.hasValue(pMsg.ediStsTm)) {

                actlDelyDtTm = actlDelyDtTm + pMsg.ediStsTm.getValue();

            } else {
                String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
                actlDelyDtTm = actlDelyDtTm + curTime.substring(8, 14);
            }

            for (Map<String, Object> shpgPlnMap : shpgPlnList) {

                if (ZYPCommonFunc.hasValue(actlDelyDtTm) && actlDelyDtTm.length() != 14) {
                    String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
                    actlDelyDtTm = actlDelyDtTm + curTime.substring(8, 14);
                }
                String soNum = (String) shpgPlnMap.get(SO_NUM);
                String soSlpNum = (String) shpgPlnMap.get(SO_SLP_NUM);

                SHPG_ORD_DTLTMsg shpgOrdDtlMsg = new SHPG_ORD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.soNum, soNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.soSlpNum, soSlpNum);

                shpgOrdDtlMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlMsg);

                if (shpgOrdDtlMsg == null) {
                    continue;
                }

                HashMap<String, Object> pCallParam = new HashMap<String, Object>();
                pCallParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                pCallParam.put("soNum", soNum);
                pCallParam.put("soSlpNum", soSlpNum);

                String constValue = ZYPCodeDataUtil.getVarCharConstValue("PART_CALL_FSR_VISIT_STS", pMsg.glblCmpyCd.getValue());
                String[] fsrVisitStsCd = null;
                List<String> fsrVisitStsList = null;
                if (constValue != null) {
                    fsrVisitStsCd = constValue.split(",");
                }
                if (fsrVisitStsCd != null) {
                    fsrVisitStsList = Arrays.asList(fsrVisitStsCd);
                    pCallParam.put("fsrVisitStsList", fsrVisitStsList);
                }

                List<Map<String, Object>> fsrTaskPartsCallMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getPartCallFsrTaskList", pCallParam);

                if (fsrTaskPartsCallMapList != null && !fsrTaskPartsCallMapList.isEmpty()) {

                    for (Map<String, Object> fsrTaskMap : fsrTaskPartsCallMapList) {

                        NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.slsDt, pMsg.ediStsDt.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, shpgOrdDtlMsg.ezUpUserID.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, (String) fsrTaskMap.get("FSR_VISIT_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, actlDelyDtTm);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);
                        fsrUpdPMsg.taskList.setValidCount(1);

                        NSZC043001 api = new NSZC043001();
                        api.execute(fsrUpdPMsg, this.onBatchType);

                        if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(fsrUpdPMsg), SO_NUM, shpgOrdDtlMsg.soNum.getValue())) {

                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * execPartCallFsrUpdApiByShpgPln Add QC#31201.
     * @param pMsg NLZC402001PMsg
     * @param podStsCd String
     * @return boolean
     */
    private boolean execPartCallFsrUpdApiByShpgPln(NLZC402001PMsg pMsg, String podStsCd) {

        if (!this.shpgPlnInfoList.isEmpty()) {

            String arrivalDate = pMsg.ediStsDt.getValue();

            if (!ZYPCommonFunc.hasValue(arrivalDate)) {

                arrivalDate = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
            }

            String actlDelyDtTm = arrivalDate;

            if (ZYPCommonFunc.hasValue(pMsg.ediStsTm)) {

                actlDelyDtTm = actlDelyDtTm + pMsg.ediStsTm.getValue();

            } else {
                String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
                actlDelyDtTm = actlDelyDtTm + curTime.substring(8, 14);
            }

            String constValue = ZYPCodeDataUtil.getVarCharConstValue("POD_PICKUP_STS_CD", pMsg.glblCmpyCd.getValue());
            String[] podPickupStsCd = null;
            List<String> podPickupStsList = null;
            if (constValue != null) {
                podPickupStsCd = constValue.split(",");
            }
            if (podPickupStsCd != null) {
                podPickupStsList = Arrays.asList(podPickupStsCd);
            } else {
                podPickupStsList = new ArrayList<String>();
                podPickupStsList.add("CP");
                podPickupStsList.add("08");
            }

            for (Map<String, Object> shpgPlnMap : this.shpgPlnInfoList) {

                if (podPickupStsList.contains(podStsCd)) {
                    actlDelyDtTm = (String) shpgPlnMap.get("PDD_DT");
                }

                if (ZYPCommonFunc.hasValue(actlDelyDtTm) && actlDelyDtTm.length() != 14) {
                    String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
                    actlDelyDtTm = actlDelyDtTm + curTime.substring(8, 14);
                }

                String soNum = (String) shpgPlnMap.get(SO_NUM);
                String soSlpNum = (String) shpgPlnMap.get(SO_SLP_NUM);

                SHPG_ORD_DTLTMsg shpgOrdDtlMsg = new SHPG_ORD_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.soNum, soNum);
                ZYPEZDItemValueSetter.setValue(shpgOrdDtlMsg.soSlpNum, soSlpNum);

                shpgOrdDtlMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlMsg);

                if (shpgOrdDtlMsg == null) {
                    continue;
                }

                HashMap<String, Object> pCallParam = new HashMap<String, Object>();
                pCallParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                pCallParam.put("soNum", soNum);
                pCallParam.put("soSlpNum", soSlpNum);
                pCallParam.put("dsSvcCallTpPartCall", DS_SVC_CALL_TP.PART_CALL);

                constValue = ZYPCodeDataUtil.getVarCharConstValue("PART_CALL_FSR_VISIT_STS", pMsg.glblCmpyCd.getValue());
                String[] fsrVisitStsCd = null;
                List<String> fsrVisitStsList = null;
                if (constValue != null) {
                    fsrVisitStsCd = constValue.split(",");
                }
                if (fsrVisitStsCd != null) {
                    fsrVisitStsList = Arrays.asList(fsrVisitStsCd);
                    pCallParam.put("fsrVisitStsList", fsrVisitStsList);
                }

                List<Map<String, Object>> fsrTaskPartsCallMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getPartCallFsrTaskList", pCallParam);

                if (fsrTaskPartsCallMapList != null && !fsrTaskPartsCallMapList.isEmpty()) {

                    for (Map<String, Object> fsrTaskMap : fsrTaskPartsCallMapList) {

                        NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.slsDt, pMsg.ediStsDt.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, shpgOrdDtlMsg.ezUpUserID.getValue());
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, (String) fsrTaskMap.get("FSR_VISIT_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, actlDelyDtTm);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);
                        fsrUpdPMsg.taskList.setValidCount(1);

                        NSZC043001 api = new NSZC043001();
                        api.execute(fsrUpdPMsg, this.onBatchType);

                        if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(fsrUpdPMsg), SO_NUM, shpgOrdDtlMsg.soNum.getValue())) {

                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * execPartCallFsrUpdApiByEDI Add QC#31201.
     * @param pMsg NLZC402001PMsg
     * @return boolean
     */
    private boolean execPartCallFsrUpdApiByEDI(NLZC402001PMsg pMsg) {

     // Get POD Status information
        POD_STSTMsg podStsTMsg = new POD_STSTMsg();
        ZYPEZDItemValueSetter.setValue(podStsTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(podStsTMsg.podStsCd, pMsg.ediStsCd.getValue());
        podStsTMsg = (POD_STSTMsg) S21ApiTBLAccessor.findByKey(podStsTMsg);

        if (podStsTMsg == null) {

            bizErrorProcess(pMsg, NLBM1063E, "ediStsCd", pMsg.ediStsCd.getValue());
            return false;
        }

        String podStsCd = podStsTMsg.podStsCd.getValue();
        String curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        // PART CALL FSR Update Process.
        String constValue = ZYPCodeDataUtil.getVarCharConstValue("POD_UPD_STS_CD", pMsg.glblCmpyCd.getValue());
        String[] podUpdStss = null;
        List<String> podUpdStsList = null;
        if (constValue != null) {
            podUpdStss = constValue.split(",");
        }
        if (podUpdStss != null) {
            podUpdStsList = Arrays.asList(podUpdStss);
        }

        if (podUpdStsList != null && podUpdStsList.contains(podStsTMsg.podStsCd.getValue())) {

            String arrivalDate = pMsg.ediStsDt.getValue();

            if (!ZYPCommonFunc.hasValue(arrivalDate)) {

                arrivalDate = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
            }

            String actlDelyDtTm = arrivalDate;

            if (ZYPCommonFunc.hasValue(pMsg.ediStsTm)) {

                actlDelyDtTm = actlDelyDtTm + pMsg.ediStsTm.getValue();

            } else {
                actlDelyDtTm = actlDelyDtTm + curTime.substring(8, 14);
            }

            HashMap<String, Object> pCallParam = new HashMap<String, Object>();
            pCallParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            pCallParam.put("bolNum", pMsg.ediProNum.getValue());
            pCallParam.put("dsSvcCallTpPartCall", DS_SVC_CALL_TP.PART_CALL);

            constValue = ZYPCodeDataUtil.getVarCharConstValue("PART_CALL_FSR_VISIT_STS", pMsg.glblCmpyCd.getValue());
            String[] fsrVisitStsCd = null;
            List<String> fsrVisitStsList = null;
            if (constValue != null) {
                fsrVisitStsCd = constValue.split(",");
            }
            if (fsrVisitStsCd != null) {
                fsrVisitStsList = Arrays.asList(fsrVisitStsCd);
                pCallParam.put("fsrVisitStsList", fsrVisitStsList);
            }

            List<Map<String, Object>> fsrTaskPartsCallMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getPartCallFsrTaskListByEDI", pCallParam);

            if (fsrTaskPartsCallMapList != null && !fsrTaskPartsCallMapList.isEmpty()) {
                String preSvcTaskNum = "";
                for (Map<String, Object> fsrTaskMap : fsrTaskPartsCallMapList) {

                    if (preSvcTaskNum.equals((String) fsrTaskMap.get("SVC_TASK_NUM"))) {
                        continue;
                    }

                    if (ZYPCommonFunc.hasValue(actlDelyDtTm) && actlDelyDtTm.length() != 14) {
                        actlDelyDtTm = actlDelyDtTm + curTime.substring(8, 14);
                    }

                    NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.slsDt, pMsg.ediStsDt.getValue());
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, (String) fsrTaskMap.get("EZUPUSERID"));
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, (String) fsrTaskMap.get("FSR_VISIT_STS_CD"));
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, actlDelyDtTm);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);
                    fsrUpdPMsg.taskList.setValidCount(1);

                    NSZC043001 api = new NSZC043001();
                    api.execute(fsrUpdPMsg, this.onBatchType);

                    if (!chkApiRslt(pMsg, S21ApiUtil.getXxMsgIdList(fsrUpdPMsg), "EDI_PRO_NUM", pMsg.ediProNum.getValue())) {

                        return false;
                    }

                    preSvcTaskNum = (String) fsrTaskMap.get("SVC_TASK_NUM");
                }
            }
        }
        

        return true;
    }
}

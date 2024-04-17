/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC056001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.api.NWZ.NWZC056001.constant.NWZC056001Constant.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ACPOTMsg;
import business.db.DS_ACPO_TRGT_DTLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.SVC_TERM_CONDTMsg;
import business.parts.NWZC056001PMsg;
import business.parts.NWZC056001_runningCountUpdateListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Running Toner Count Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/23/2015   Hitachi         K.Yamada        Create          New
 * 03/25/2016   Hitachi         K.Kasai         Update          QC#6056
 * 03/31/2016   Hitachi         K.Kasai         Update          QC#6344
 * 09/29/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 18/07/2017   Hitachi         U.Kim           Update          QC#19691
 * 06/05/2018   Fujitsu         Nagashima       Update          QC#25966
 * 03/07/2019   Hitachi         S.Kitamura      Update          QC#30661
 * </pre>
 */
public class NWZC056001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Black/White running attribute name */
    private String bwRunAttrbNm = null;

    /** Color running attribute name */
    private String clrRunAttrbNm = null;

    /** Total running attribute name */
    private String totRunAttrbNm = null;
    // START 2019/03/07 S.Kitamura [QC#30661, ADD]
    /** Black/White orignal attribute name */
    private String bwOrigAttrbNm = null;

    /** Color orignal attribute name */
    private String clrOrigAttrbNm = null;

    /** Total orignal attribute name */
    private String totOrigAttrbNm = null;
    // END 2019/03/07 S.Kitamura [QC#30661, ADD]
    /**
     * Constructor
     */
    public NWZC056001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NWZC056001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC056001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (checkParameter(msgMap)) {
            doProcess(msgMap);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NWZM0188E);
        mandatoryCheck(msgMap, pMsg.slsDt, NWZM0346E);
        if (pMsg.runningCountUpdateList.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM1569E);
        }
        for (int i = 0; i < pMsg.runningCountUpdateList.getValidCount(); i++) {
            NWZC056001_runningCountUpdateListPMsg runCntUpdMsg = pMsg.runningCountUpdateList.no(i);
            mandatoryCheck(msgMap, runCntUpdMsg.dsContrNum, NWZM1143E);
            mandatoryCheck(msgMap, runCntUpdMsg.mdseCd, NWZM0996E);
            mandatoryCheck(msgMap, runCntUpdMsg.xxRqstQty, NWZM1571E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        //get terms and conditions attribute name
        this.bwRunAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_BW_RUN_ATTRB_NM, glblCmpyCd);
        this.clrRunAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_CLR_RUN_ATTRB_NM, glblCmpyCd);
        this.totRunAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_TOT_RUN_ATTRB_NM, glblCmpyCd);
        // START 2019/03/07 S.Kitamura [QC#30661, ADD]
        this.bwOrigAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_BW_ORG_ATTRB_NM, glblCmpyCd);
        this.clrOrigAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_CLR_ORG_ATTRB_NM, glblCmpyCd);
        this.totOrigAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_CAP_TOT_ORG_ATTRB_NM, glblCmpyCd);
        // END 2019/03/07 S.Kitamura [QC#30661, ADD]

        // START 2018/06/01 H.Nagashima [QC#25966, ADD]
        List<String> trgtImgSplyTpCdList = new ArrayList<String>();
        String trgtImgSplyTpCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_TRGT_IMG_SPLY_TP, glblCmpyCd);
        if (trgtImgSplyTpCdCsv != null) {
            trgtImgSplyTpCdList = Arrays.asList(trgtImgSplyTpCdCsv.split(","));
        }
        // END 2018/06/01 H.Nagashima [QC#25966, ADD]

        for (int i = 0; i < pMsg.runningCountUpdateList.getValidCount(); i++) {
            NWZC056001_runningCountUpdateListPMsg detailMsg = pMsg.runningCountUpdateList.no(i);

            //get mdse info
            // START 2017/07/14 U.Kim [QC#19933, MOD]
            // String bwClrFlg = getBwClrFlg(msgMap, detailMsg);
            // if (!hasValue(bwClrFlg)) {
            Map<String, Object> supplyInfo = getSupplyInfo(msgMap, detailMsg);
            // START 2018/06/05 H.Nagashima [QC#25966, DEL]
//            if (supplyInfo == null) {
//            // END 2017/07/14 U.Kim [QC#19933, MOD]
//                msgMap.addXxMsgId(NWZM0598E);
//                break;
//            }
            // END 2018/06/05 H.Nagashima [QC#25966, DEL]

            if (supplyInfo != null) {   // 2018/06/05 H.Nagashima [QC#25966, ADD]
                //get terms and conditions
                List<Map<String, Object>> termAndCondInfo = getTermAndCondInfo(msgMap, detailMsg);
                if (termAndCondInfo != null && !termAndCondInfo.isEmpty()) {
                    // START 2017/07/14 U.Kim [QC#19933, ADD]
                    String bwClrFlg = (String) supplyInfo.get("BLACK_COLOR_FLG");
                    String imgSplyTpCd = (String) supplyInfo.get("IMG_SPLY_TP_CD");
                    // START 2018/06/01 H.Nagashima [QC#25966, MOD]
//                if (ZYPCommonFunc.hasValue(imgSplyTpCd) && imgSplyTpCd.equals(IMG_SPLY_TP.TONER)) {
                    if (ZYPCommonFunc.hasValue(imgSplyTpCd) && trgtImgSplyTpCdList.contains(imgSplyTpCd)) {
                        // END 2018/06/01 H.Nagashima [QC#25966, MOD]
                        // END 2017/07/14 U.Kim [QC#19933, ADD]
                        //update terms and conditions
                        updTermAndCondInfo(msgMap, detailMsg, termAndCondInfo, bwClrFlg);
                        if (msgMap.getMsgMgr().isXxMsgId()) {
                            break;
                        }
                        // START 2017/07/14 U.Kim [QC#19933, ADD]
                    }
                    // END 2017/07/14 U.Kim [QC#19933, ADD]
                }
            }   // 2018/06/05 H.Nagashima [QC#25966, ADD]

            //create/update advanced CPO
            registerDsACPOInfo(msgMap, detailMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                break;
            }
        }
    }

    // START 2017/07/14 U.Kim [QC#19933, MOD]
    // private String getBwClrFlg(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {
    private Map<String, Object> getSupplyInfo(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {
    // START 2017/07/14 U.Kim [QC#19933, MOD]
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("mdseCd", detailMsg.mdseCd.getValue());
        // START 2017/07/18 U.Kim [QC#19691, MoD]
        //String bwClrFlg = (String) this.ssmBatchClient.queryObject("getSupplyColorTp", param);
        Map<String, Object> bwClrFlg = (Map<String, Object>) this.ssmBatchClient.queryObject("getSupplyInfo", param);
        // END 2017/07/18 U.Kim [QC#19691, MoD]
        return bwClrFlg;
    }

    private List<Map<String, Object>> getTermAndCondInfo(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", detailMsg.dsContrNum.getValue());
        param.put("svcMachMstrPk", detailMsg.svcMachMstrPk.getValue());
        param.put("bwRun", this.bwRunAttrbNm);
        param.put("clrRunNm", this.clrRunAttrbNm);
        param.put("totRunNm", this.totRunAttrbNm);
        // START 2019/03/07 S.Kitamura [QC#30661, ADD]
        param.put("bwOrigNm", this.bwOrigAttrbNm);
        param.put("clrOrigNm", this.clrOrigAttrbNm);
        param.put("totOrigNm", this.totOrigAttrbNm);
        // END 2019/03/07 S.Kitamura [QC#30661, ADD]
        param.put("slsDt", pMsg.slsDt.getValue());

        List<Map<String, Object>> termAndCondInfo;
        if (isFleetContract(pMsg.glblCmpyCd.getValue(), detailMsg.dsContrNum.getValue())) {
            termAndCondInfo = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTermAndCondInfoForFleet", param);
        } else {
            termAndCondInfo = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTermAndCondInfo", param);
        }
        return termAndCondInfo;
    }

    private void updTermAndCondInfo(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg
            , List<Map<String, Object>> termAndCondInfo, String bwClrFlg) {
        // START 2019/03/07 S.Kitamura [QC#30661, ADD]
        String bwOrigFlg = FLG_OFF_N;
        String clrOrigFlg = FLG_OFF_N;
        String totOrigFlg = FLG_OFF_N;
        for (Map<String, Object> termAndCond : termAndCondInfo) {
            String svcTermCondAttrbNm = (String) termAndCond.get("SVC_TERM_COND_ATTRB_NM");
            String svcTermAttrbVal = (String) termAndCond.get("SVC_TERM_ATTRB_MAP_VAL_CD");
            if (this.bwOrigAttrbNm.equals(svcTermCondAttrbNm) && hasValue(svcTermAttrbVal)) {
                bwOrigFlg = FLG_ON_Y;
            } else if (this.clrOrigAttrbNm.equals(svcTermCondAttrbNm) && hasValue(svcTermAttrbVal)) {
                clrOrigFlg = FLG_ON_Y;
            } else if (this.totOrigAttrbNm.equals(svcTermCondAttrbNm) && hasValue(svcTermAttrbVal)) {
                totOrigFlg = FLG_ON_Y;
            }
        }
        // END 2019/03/07 S.Kitamura [QC#30661, ADD]

        for (Map<String, Object> termAndCond : termAndCondInfo) {
            BigDecimal svcTermCondPk = (BigDecimal) termAndCond.get("SVC_TERM_COND_PK");
            String tmpSvcTermAttrbVal = (String) termAndCond.get("SVC_TERM_ATTRB_MAP_VAL_CD");
            BigDecimal svcTermAttrbVal = convStrToBigDecimal(tmpSvcTermAttrbVal);
            String svcTermCondAttrbNm = (String) termAndCond.get("SVC_TERM_COND_ATTRB_NM");

            if (!hasValue(svcTermAttrbVal)) {
                svcTermAttrbVal = BigDecimal.ZERO;
            }

            BigDecimal updQty = svcTermAttrbVal.add(detailMsg.xxRqstQty.getValue());
            // START 2019/03/07 S.Kitamura [QC#30661, MOD]
            // if (this.bwRunAttrbNm.equals(svcTermCondAttrbNm) && FLG_ON_Y.equals(bwClrFlg)) {
            if (this.bwRunAttrbNm.equals(svcTermCondAttrbNm) && FLG_ON_Y.equals(bwClrFlg) && FLG_ON_Y.equals(bwOrigFlg)) {
                updateTermAndCond(msgMap, svcTermCondPk, svcTermAttrbVal, updQty);
            // } else if (this.clrRunAttrbNm.equals(svcTermCondAttrbNm) && FLG_OFF_N.equals(bwClrFlg)) {
            } else if (this.clrRunAttrbNm.equals(svcTermCondAttrbNm) && FLG_OFF_N.equals(bwClrFlg) && FLG_ON_Y.equals(clrOrigFlg)) {
                updateTermAndCond(msgMap, svcTermCondPk, svcTermAttrbVal, updQty);
            // } else if (this.totRunAttrbNm.equals(svcTermCondAttrbNm)) {
            } else if (this.totRunAttrbNm.equals(svcTermCondAttrbNm) && FLG_ON_Y.equals(totOrigFlg)) {
                updateTermAndCond(msgMap, svcTermCondPk, svcTermAttrbVal, updQty);
            }
            // END 2019/03/07 S.Kitamura [QC#30661, MOD]
            if (msgMap.getMsgMgr().isXxMsgId()) {
                break;
            }
        }
    }

    private void registerDsACPOInfo(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {

        //get DS_ACPO
        String dsAcpoNum = null;
        Map<String, Object> dsACpoInfo = getDsACPO(msgMap, detailMsg);
        if (dsACpoInfo == null || dsACpoInfo.isEmpty()) {
            //create DS_ACPO
            dsAcpoNum = createDsACPO(msgMap, detailMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        } else {
            dsAcpoNum = (String) dsACpoInfo.get("DS_ACPO_NUM");
        }

        //get DS_ACPO_TRGT_DTL
        Map<String, Object> dsACpoDtlInfo = getDsACPODtl(msgMap, detailMsg);
        if (dsACpoDtlInfo == null || dsACpoDtlInfo.isEmpty()) {
            createDsACPODtl(msgMap, detailMsg, dsAcpoNum);
        } else {
            updateDsAcpoDtl(msgMap, dsACpoDtlInfo, detailMsg);
        }
    }

    private Map<String, Object> getDsACPO(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", detailMsg.dsContrNum.getValue());
        param.put("svcMachMstrPk", detailMsg.svcMachMstrPk.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);

        Map<String, Object> dsACpoInfo;
        if (isFleetContract(pMsg.glblCmpyCd.getValue(), detailMsg.dsContrNum.getValue())) {
            dsACpoInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsACPOForFleet", param);
        } else {
            dsACpoInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsACPO", param);
        }
        return dsACpoInfo;
    }

    private Map<String, Object> getDsACPODtl(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", detailMsg.dsContrNum.getValue());
        param.put("svcMachMstrPk", detailMsg.svcMachMstrPk.getValue());
        param.put("mdseCd", detailMsg.mdseCd.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);

        Map<String, Object> dsACpoDtlInfo;
        if (isFleetContract(pMsg.glblCmpyCd.getValue(), detailMsg.dsContrNum.getValue())) {
            dsACpoDtlInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsACPODtlForFleet", param);
        } else {
            dsACpoDtlInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsACPODtl", param);
        }
        return dsACpoDtlInfo;
    }

    private BigDecimal getDsContrDtlPk(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrNum", detailMsg.dsContrNum.getValue());
        param.put("svcMachMstrPk", detailMsg.svcMachMstrPk.getValue());
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);

        BigDecimal dsContrDtlPk;
        if (isFleetContract(pMsg.glblCmpyCd.getValue(), detailMsg.dsContrNum.getValue())) {
            dsContrDtlPk = (BigDecimal) this.ssmBatchClient.queryObject("getDsContrDtlPkForFleet", param);
        } else {
            dsContrDtlPk = (BigDecimal) this.ssmBatchClient.queryObject("getDsContrDtlPk", param);
        }
        return dsContrDtlPk;
    }

    private String getNextDsAcpoTrgtLineNum(S21ApiMessageMap msgMap, String dsAcpoNum) {
        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsAcpoNum", dsAcpoNum);
        param.put("dsAcpoTrgtLineNum", BigDecimal.ONE);

        String result = (String) this.ssmBatchClient.queryObject("getNextDsAcpoTrgtLineNum", param);
        if (hasValue(result)) {
            return result;
        }
        return "001";
    }

    private String createDsACPO(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg) {

        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        DS_ACPOTMsg inMsg = new DS_ACPOTMsg();

        BigDecimal dsContrDtkPk = getDsContrDtlPk(msgMap, detailMsg);
        if (!hasValue(dsContrDtkPk)) {
            msgMap.addXxMsgId(NWZM1302E);
            return null;
        }
        String dsAcpoNum = ZYPNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), DS_ACPO_NUM_SEQ);

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsAcpoNum, dsAcpoNum);
        setValue(inMsg.dsContrDtlPk, dsContrDtkPk);
        setValue(inMsg.addDropShipFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1573E);
        }
        return dsAcpoNum;
    }

    private String createDsACPODtl(S21ApiMessageMap msgMap, NWZC056001_runningCountUpdateListPMsg detailMsg
            , String dsAcpoNum) {

        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        DS_ACPO_TRGT_DTLTMsg inMsg = new DS_ACPO_TRGT_DTLTMsg();

        String dsAcpoTrgeLineNum = getNextDsAcpoTrgtLineNum(msgMap, dsAcpoNum);

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsAcpoNum, dsAcpoNum);
        setValue(inMsg.dsAcpoTrgtLineNum, dsAcpoTrgeLineNum);
        setValue(inMsg.shipQty, detailMsg.xxRqstQty);
        setValue(inMsg.mdseCd, detailMsg.mdseCd);
        setValue(inMsg.qtyCtrlNoLimitFlg, FLG_OFF_N);
        setValue(inMsg.dropShipFlg, FLG_OFF_N);
        setValue(inMsg.manPrcFlg, FLG_OFF_N);

        S21ApiTBLAccessor.insert(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1574E);
        }
        return dsAcpoNum;
    }

    private void updateTermAndCond(S21ApiMessageMap msgMap, BigDecimal svcTermCondPk, BigDecimal svcTermAttrbVal
            , BigDecimal updQty) {

        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcTermCondPk, svcTermCondPk);
        inMsg = (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        setValue(inMsg.svcTermAttrbMapValCd, updQty.toPlainString());
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1564E);
        }
    }

    private void updateDsAcpoDtl(S21ApiMessageMap msgMap, Map<String, Object> dsACpoDtlInfo
            , NWZC056001_runningCountUpdateListPMsg detailMsg) {

        NWZC056001PMsg pMsg = (NWZC056001PMsg) msgMap.getPmsg();

        DS_ACPO_TRGT_DTLTMsg inMsg = new DS_ACPO_TRGT_DTLTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsAcpoNum, (String) dsACpoDtlInfo.get("DS_ACPO_NUM"));
        setValue(inMsg.dsAcpoTrgtLineNum, (String) dsACpoDtlInfo.get("DS_ACPO_TRGT_LINE_NUM"));
        inMsg = (DS_ACPO_TRGT_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        BigDecimal shipQty = inMsg.shipQty.getValue();
        if (shipQty == null) {
            shipQty = detailMsg.xxRqstQty.getValue();
        } else {
            shipQty = shipQty.add(detailMsg.xxRqstQty.getValue());
        }

        setValue(inMsg.shipQty, shipQty);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1574E);
        }
    }

    private BigDecimal convStrToBigDecimal(String val) {
        if (!hasValue(val)) {
            return null;
        }
        try {
            BigDecimal convVal = new BigDecimal(val);
            return convVal;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isFleetContract(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setSQLID("003");
        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrTMsg);
        if (dsContrTMsgArray.getValidCount() > 0 && DS_CONTR_CATG.FLEET.equals(dsContrTMsgArray.no(0).dsContrCatgCd.getValue())) {
            return true;
        }
        return false;
    }
}

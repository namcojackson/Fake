/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC051001;

import static com.canon.cusa.s21.api.NSZ.NSZC051001.constant.NSZC051001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NSZC010001PMsg;
import business.parts.NSZC010001_APMsg;
import business.parts.NSZC010001_xxMsgIdListPMsg;
import business.parts.NSZC051001PMsg;
import business.parts.NSZC051001_meterListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_PROC_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Meter Bulk Upload API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         K.Kojima        Create          N/A
 * 2016/03/09   Hitachi         T.Tomita        Update          CSA Defect#5203
 * 2016/03/09   Hitachi         T.Tomita        Update          CSA Defect#5204, 5205
 * 2016/03/18   Hitachi         T.Tomita        Update          CSA Defect#5451
 * 2016/12/22   Hitachi         K.Kojima        Update          QC#16600
 * 2017/05/22   Hitachi         K.Kitachi       Update          QC#18342
 * 2017/07/06   Hitachi         K.Kitachi       Update          QC#18292
 * 2017/09/19   Hitachi         T.Tomita        Update          QC#21185
 * 2018/02/08   Hitachi         M.Kidokoro      Update          QC#18145
 * 2019/09/11   Hitachi         K.Kitachi       Update          QC#53420
 * </pre>
 */
public class NSZC051001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType = null;

    // START 2017/05/22 K.Kitachi [QC#18342, DEL]
//    /** DS_MTR_PROC_TS */
//    private String dsMtrProcTs = null;
    // END 2017/05/22 K.Kitachi [QC#18342, DEL]

    /**
     * Constructor
     */
    public NSZC051001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC051001PMsg
     * @param onBtType ONBATCH_TYPE
     */
    public void execute(final NSZC051001PMsg param, final ONBATCH_TYPE onBtType) {
        if (param == null) {
            return;
        }
        this.onBatchType = onBtType;
        // START 2017/05/22 K.Kitachi [QC#18342, MOD]
//        List<DS_MTR_INTFCTMsg> createTMsg = new ArrayList<DS_MTR_INTFCTMsg>(param.meterList.length());
//        // add start 2016/03/10 CSA Defect#5205
//        List<DS_MSGTMsg> dsMsgTMsg = new ArrayList<DS_MSGTMsg>();
//        this.dsMtrProcTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
//        // add end 2016/03/10 CSA Defect#5205
//        setValue(param.xxRsltStsCd, RETURN_CODE_NORMAL);
//        executeMachineLine(param, createTMsg, dsMsgTMsg);
//        if (createTMsg.size() > 0) {
//            executeInsert(createTMsg);
//        }
//        // add start 2016/03/10 CSA Defect#5205
//        if (dsMsgTMsg.size() > 0) {
//            executeDsMsgInsert(dsMsgTMsg);
//        }
//        // add end 2016/03/10 CSA Defect#5205
        setValue(param.xxRsltStsCd, RETURN_CODE_NORMAL);
        executeMachineLine(param);
        // END 2017/05/22 K.Kitachi [QC#18342, MOD]
    }

    /**
     * execute
     * @param param List<NSZC051001PMsg>
     * @param onBtType ONBATCH_TYPE
     */
    public void execute(final List<NSZC051001PMsg> param, final ONBATCH_TYPE onBtType) {
        if (param == null || param.size() == 0) {
            return;
        }
        for (int i = 0; i < param.size(); i++) {
            execute(param.get(i), onBtType);
        }
    }

    // START 2017/05/22 K.Kitachi [QC#18342, MOD]
    // mod start 2016/03/10 CSA Defect#5205
    /**
     * executeMachineLine
     * @param pMsg NSZC051001PMsg
     */
//    private void executeMachineLine(final NSZC051001PMsg pMsg, final List<DS_MTR_INTFCTMsg> createTMsg, List<DS_MSGTMsg> dsMsgTMsg) {
    private void executeMachineLine(final NSZC051001PMsg pMsg) {

        // mod start 2016/03/10 CSA Defect#5205
        // Input Mandatory Check Level 1
        if (!checkMandatoryLevel1(pMsg)) {
            return;
        }
        // mod end 2016/03/10 CSA Defect#5205

        // Input Mandatory Check Level 2
//        if (!checkMandatoryLevel2(pMsg, createTMsg)) {
        if (!checkMandatoryLevel2(pMsg)) {
            return;
        }

        // mod start 2016/03/10 CSA Defect#5205
        // Identify the Machine.
        Map<String, Object> svcMachMstrInfo = getSvcMachMstrInfo(pMsg);
        // mod end 2016/03/10 CSA Defect#5205
        if (svcMachMstrInfo == null) {
            return;
        }
        BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrInfo.get(SVC_MACH_MSTR_PK);
        String mdseCd = (String) svcMachMstrInfo.get(MDSE_CD);
        // mod start 2016/03/09 CSA Defect#5203
        String serNum = (String) svcMachMstrInfo.get(SER_NUM);
        setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(pMsg.serNum, serNum);
        // mod end 2016/03/09 CSA Defect#5203

        // NSZC0100 Meter Update API Create PMsg
        NSZC010001PMsg callPMsg = new NSZC010001PMsg();
        setValue(callPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(callPMsg.mtrReadSrcTpCd, pMsg.mtrReadSrcTpCd.getValue());
        setValue(callPMsg.dsMtrReadTpCd, pMsg.dsMtrReadTpCd.getValue());
        setValue(callPMsg.slsDt, pMsg.slsDt.getValue());
        setValue(callPMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(callPMsg.fsrNum, (String) null);
        setValue(callPMsg.fsrVisitNum, (String) null);
        setValue(callPMsg.dsMtrReadTpGrpCd, pMsg.dsMtrReadTpGrpCd.getValue());
        // Mod Start 2017/09/19 QC#21185
        // START 2017/07/06 K.Kitachi [QC#18292, MOD]
        setValue(callPMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
//        setValue(callPMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/07/06 K.Kitachi [QC#18292, MOD]
        setValue(callPMsg.xxRqstFlg_WR, pMsg.xxRqstFlg_WR);
        // Mod Start 2017/09/19 QC#21185
        // Get Physical Mater Info
        boolean checkResult = true;
        for (int i = 0; i < pMsg.meterList.getValidCount(); i++) {
            NSZC051001_meterListPMsg meterListPMsg = (NSZC051001_meterListPMsg) pMsg.meterList.get(i);
            BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(pMsg.glblCmpyCd.getValue(), svcMachMstrPk, meterListPMsg.mdlMtrLbCd.getValue());
            if (svcPhysMtrPk == null) {
                addXxMsgIdWithPrm(pMsg, NSZM0396E, new String[] {"Service Machine Master PK:" + svcMachMstrPk, "Service Machine Master" });
                checkResult = false;
                // mod start 2016/03/18 CSA Defect#5451
                continue;
                // mod end 2016/03/18 CSA Defect#5451
            }
            setValue(callPMsg.A.no(i).physMtrNum, (String) null);
            setValue(callPMsg.A.no(i).physMtrId, (String) null);
            setValue(callPMsg.A.no(i).mtrReadDt, pMsg.mtrReadDt);
            setValue(callPMsg.A.no(i).rgtnMtrDt, pMsg.rgtnMtrDt);
            setValue(callPMsg.A.no(i).svcPhysMtrReadPk, (BigDecimal) null);
            setValue(callPMsg.A.no(i).readMtrCnt, meterListPMsg.readMtrCnt);
            setValue(callPMsg.A.no(i).testMtrCnt, meterListPMsg.testMtrCnt);
            setValue(callPMsg.A.no(i).rgtnUsrId, pMsg.rgtnUsrId);
            setValue(callPMsg.A.no(i).estFlg, ZYPConstant.FLG_OFF_N);
            setValue(callPMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(callPMsg.A.no(i).mtrLbCd, (String) null);
            setValue(callPMsg.A.no(i).mtrClsTpCd, (String) null);
            setValue(callPMsg.A.no(i).dsMdlMtrPk, (BigDecimal) null);
            setValue(callPMsg.A.no(i).svcPhysMtrPk, svcPhysMtrPk);
            // START 2018/02/08 M.Kidokoro [QC#18145,MOD]
//            setValue(callPMsg.A.no(i).mtrEntryCmntTxt, (String) null);
            setValue(callPMsg.A.no(i).mtrEntryCmntTxt, meterListPMsg.mtrEntryCmntTxt.getValue());
            // END 2018/02/08 M.Kidokoro [QC#18145,MOD]
            setValue(callPMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            setValue(callPMsg.A.no(i).mdlMtrLbCd, meterListPMsg.mdlMtrLbCd.getValue());
            callPMsg.A.setValidCount(callPMsg.A.getValidCount() + 1);
        }

        if (checkResult == false) {
            // del start 2016/03/10 CSA Defect#5205
//            for (int i = 0; i < callPMsg.A.getValidCount(); i++) {
//                NSZC010001_APMsg a = callPMsg.A.no(i);
//                createTMsg.add(createTMsg(pMsg, a.svcPhysMtrReadGrpSq.getValue(), mdseCd, a.svcPhysMtrPk.getValue(), a.mdlMtrLbCd.getValue(), a.readMtrCnt.getValue(), DS_MTR_PROC_STS.ERROR));
//            }
            // del end 2016/03/10 CSA Defect#5205
            return;
        }

        new NSZC010001().execute(callPMsg, this.onBatchType);

        // mod start 2016/03/10 CSA Defect#5205
//        for (int i = 0; i < callPMsg.xxMsgIdList.getValidCount(); i++) {
//            NSZC010001_xxMsgIdListPMsg xxMsgIdList = callPMsg.xxMsgIdList.no(i);
//            addXxMsgIdWithPrm(pMsg, xxMsgIdList.xxMsgId.getValue(), xxMsgIdList.xxMsgPrmTxt_0.getValue(), xxMsgIdList.xxMsgPrmTxt_1.getValue(), xxMsgIdList.xxMsgPrmTxt_2.getValue(), xxMsgIdList.xxMsgPrmTxt_3.getValue(),
//                    xxMsgIdList.xxMsgPrmTxt_4.getValue());
//        }
        String apiMsg = null;
        // START 2019/09/11 K.Kitachi [QC#53420, MOD]
//        if (callPMsg.xxMsgIdList.getValidCount() > 0) {
//            apiMsg = getMessage(callPMsg.xxMsgIdList.no(0));
//        }
        for (int i = 0; i < callPMsg.xxMsgIdList.getValidCount(); i++) {
            NSZC010001_xxMsgIdListPMsg msgInfo = callPMsg.xxMsgIdList.no(i);
            String xxMsgId = msgInfo.xxMsgId.getValue();
            if (xxMsgId.length() > 1 && xxMsgId.substring(xxMsgId.length() - 1).equals("E")) {
                apiMsg = getMessage(msgInfo);
                break;
            }
            if (apiMsg == null) {
                apiMsg = getMessage(msgInfo);
            }
        }
        // END 2019/09/11 K.Kitachi [QC#53420, MOD]
        // mod end 2016/03/10 CSA Defect#5205

        // mod start 2016/03/09 CSA Defect#5203
        String dsMtrProcStsCd = DS_MTR_PROC_STS.NORMAL;
        if (callPMsg.xxRsltStsCd.getValue().equals(RETURN_CODE_ERROR)) {
            // add start 2016/03/10 CSA Defect#5204
            setValue(pMsg.xxRsltStsCd, "9");
            // add end 2016/03/10 CSA Defect#5204
            if (callPMsg.xxMsgId.getValue().length() > 1 && callPMsg.xxMsgId.getValue().substring(callPMsg.xxMsgId.getValue().length() - 1).equals("W")) {
                dsMtrProcStsCd = DS_MTR_PROC_STS.WARNING;
            } else if (callPMsg.xxMsgId.getValue().length() > 1 && callPMsg.xxMsgId.getValue().substring(callPMsg.xxMsgId.getValue().length() - 1).equals("E")) {
                dsMtrProcStsCd = DS_MTR_PROC_STS.ERROR;
                // add start 2016/03/10 CSA Defect#5204
                setValue(pMsg.xxMsgIdList.no(0).xxMsgId, NSZM0872W);
                pMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount() + 1);
                // add end 2016/03/10 CSA Defect#5204
            }
        }
        // mod end 2016/03/09 CSA Defect#5203
        // set out parameters.
        setValue(pMsg.mdseCd_O, mdseCd);
        setValue(pMsg.dsMtrProcStsCd, dsMtrProcStsCd);
        setValue(pMsg.dsMsgTxt, apiMsg);
        // mod start 2016/03/10 CSA Defect#5205
        NSZC010001_APMsg a;
//        DS_MTR_INTFCTMsg dsMtrIntfcTMsg;
//        BigDecimal dsMtrIntfcPk = null;
//        BigDecimal errGrpQq = null;
        for (int i = 0; i < callPMsg.A.getValidCount(); i++) {
            a = callPMsg.A.no(i);
//            dsMtrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MTR_INTFC_SQ);
//            if (DS_MTR_PROC_STS.ERROR.equals(dsMtrProcStsCd) && !ZYPCommonFunc.hasValue(errGrpQq)) {
//                errGrpQq = dsMtrIntfcPk.negate();
//            }
//            dsMtrIntfcTMsg = createTMsg(pMsg, dsMtrIntfcPk, a.svcPhysMtrReadGrpSq.getValue(), mdseCd, a.svcPhysMtrPk.getValue(), a.mdlMtrLbCd.getValue(), a.readMtrCnt.getValue(), dsMtrProcStsCd, errGrpQq);
//            createTMsg.add(dsMtrIntfcTMsg);
//            if (ZYPCommonFunc.hasValue(apiMsg)) {
//                dsMsgTMsg.add(createDsMsgTMsg(dsMtrIntfcTMsg, apiMsg));
//            }
            // set out parameter details.
            setValue(pMsg.rsltPrmList.no(i).svcPhysMtrReadGrpSq, a.svcPhysMtrReadGrpSq);
            setValue(pMsg.rsltPrmList.no(i).svcPhysMtrPk, a.svcPhysMtrPk);
            setValue(pMsg.rsltPrmList.no(i).mdlMtrLbCd, a.mdlMtrLbCd);
            setValue(pMsg.rsltPrmList.no(i).readMtrCnt, a.readMtrCnt);
        }
        // mod end 2016/03/10 CSA Defect#5205
        pMsg.rsltPrmList.setValidCount(callPMsg.A.getValidCount());
    }
    // mod end 2016/03/10 CSA Defect#5205
    // END 2017/05/22 K.Kitachi [QC#18342, MOD]

    // mod start 2016/03/10 CSA Defect#5205
    /**
     * Input Check Level.1
     * @param pMsg
     * @param msgMap
     * @return
     */
    private boolean checkMandatoryLevel1(NSZC051001PMsg pMsg) {
        boolean checkResult = true;
//        boolean createFlag = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"glblCmpyCd", "Input Parameters" });
            checkResult = false;
//            createFlag = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.mtrReadSrcTpCd)) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"mtrReadSrcTpCd", "Input Parameters" });
            checkResult = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsMtrReadTpCd)) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"dsMtrReadTpCd", "Input Parameters" });
            checkResult = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"slsDt", "Input Parameters" });
            checkResult = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk) && !ZYPCommonFunc.hasValue(pMsg.serNum)) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"svcMachMstrPk or serNum", "Input Parameters" });
            checkResult = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.dsMtrReadTpGrpCd)) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"dsMtrReadTpGrpCd", "Input Parameters" });
            checkResult = false;
        }
        if (pMsg.meterList.getValidCount() == 0) {
            addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"meterList", "Input Parameters" });
            checkResult = false;
        }

//        if (!checkResult && createFlag) {
//            createTmsg.add(createTMsg(pMsg, null, null, null, null, null, DS_MTR_PROC_STS.ERROR));
//        }

        return checkResult;
    }
    // mod end 2016/03/10 CSA Defect#5205

    // START 2017/05/22 K.Kitachi [QC#18342, MOD]
    /**
     * Input Check Level.2
     * @param pMsg
     * @return
     */
//    private boolean checkMandatoryLevel2(NSZC051001PMsg pMsg, List<DS_MTR_INTFCTMsg> createTmsg) {
    private boolean checkMandatoryLevel2(NSZC051001PMsg pMsg) {
        boolean checkResult = true;

        for (int i = 0; i < pMsg.meterList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.meterList.no(i).mdlMtrLbCd)) {
                addXxMsgIdWithPrm(pMsg, NSZM0401E, new String[] {"mdlMtrLbCd", "Input Parameters" });
                checkResult = false;
            }
        }

        // del start 2016/03/10 CSA Defect#5205
//        if (checkResult == false) {
//            for (int i = 0; i < pMsg.meterList.getValidCount(); i++) {
//                createTmsg.add(createTMsg(pMsg, null, null, null, pMsg.meterList.no(i).mdlMtrLbCd.getValue(), pMsg.meterList.no(i).readMtrCnt.getValue(), DS_MTR_PROC_STS.ERROR));
//            }
//        }
        // del end 2016/03/10 CSA Defect#5205

        return checkResult;
    }
    // END 2017/05/22 K.Kitachi [QC#18342, MOD]

    // mod start 2016/03/10 CSA Defect#5205
    /**
     * Get SVC_MACH_MSTR_INFO
     * @param pMsg
     * @return
     */
    private Map<String, Object> getSvcMachMstrInfo(NSZC051001PMsg pMsg) {
        Map<String, Object> svcMachMstrInfo = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            // START 2016/12/22 K.Kojima [QC#16600,MOD]
            // svcMachMstrInfo = getSvcMachMstrInfo(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), null);
            // if (svcMachMstrInfo == null) {
            List<Map<String, Object>> svcMachMstrInfoList = getSvcMachMstrInfo(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), null, null);
            if (svcMachMstrInfoList == null || svcMachMstrInfoList.size() == 0) {
            // END 2016/12/22 K.Kojima [QC#16600,MOD]
                addXxMsgIdWithPrm(pMsg, NSZM0396E, new String[] {"Service Machine Master PK:" + pMsg.svcMachMstrPk.getValue(), "Service Machine Master" });
//                createTmsg.add(createTMsg(pMsg, null, null, null, null, null, DS_MTR_PROC_STS.ERROR));
                return null;
            }
            // START 2016/12/22 K.Kojima [QC#16600,ADD]
            svcMachMstrInfo = svcMachMstrInfoList.get(0);
            // START 2016/12/22 K.Kojima [QC#16600,ADD]
        } else if (ZYPCommonFunc.hasValue(pMsg.serNum)) {
            // START 2016/12/22 K.Kojima [QC#16600,MOD]
            // svcMachMstrInfo = getSvcMachMstrInfo(pMsg.glblCmpyCd.getValue(), null, pMsg.serNum.getValue());
            // if (svcMachMstrInfo == null) {
            List<Map<String, Object>> svcMachMstrInfoList = getSvcMachMstrInfo(pMsg.glblCmpyCd.getValue(), null, pMsg.serNum.getValue(), pMsg.mdseCd.getValue());
            if (svcMachMstrInfoList == null || svcMachMstrInfoList.size() == 0) {
                // END 2016/12/22 K.Kojima [QC#16600,MOD]
                addXxMsgIdWithPrm(pMsg, NSZM0396E, new String[] {"Serial Number:" + pMsg.serNum.getValue(), "Service Machine Master" });
//                createTmsg.add(createTMsg(pMsg, null, null, null, null, null, DS_MTR_PROC_STS.ERROR));
                return null;
                // START 2016/12/22 K.Kojima [QC#16600,ADD]
            } else if (svcMachMstrInfoList.size() > 1) {
                addXxMsgIdWithPrm(pMsg, NSZM1096E, new String[] {"Serial Number:" + pMsg.serNum.getValue(), "Service Machine Master" });
                return null;
                // END 2016/12/22 K.Kojima [QC#16600,ADD]
            }
            // START 2016/12/22 K.Kojima [QC#16600,ADD]
            svcMachMstrInfo = svcMachMstrInfoList.get(0);
            // START 2016/12/22 K.Kojima [QC#16600,ADD]
        }
        return svcMachMstrInfo;
    }
    // mod end 2016/03/10 CSA Defect#5205

    /**
     * @param glblCmpyCd
     * @param svnMachMstrPk
     * @param serNum
     * @return
     */
    // START 2016/12/22 K.Kojima [QC#16600,MOD]
    // private Map<String, Object> getSvcMachMstrInfo(String glblCmpyCd, BigDecimal svnMachMstrPk, String serNum) {
    private List<Map<String, Object>> getSvcMachMstrInfo(String glblCmpyCd, BigDecimal svnMachMstrPk, String serNum, String mdseCd) {
    // END 2016/12/22 K.Kojima [QC#16600,MOD]

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        // mod start 2016/03/18 CSA Defect#5451
        if (ZYPCommonFunc.hasValue(svnMachMstrPk)) {
            param.put("svcMachMstrPk", svnMachMstrPk);
        }
        // mod end 2016/03/18 CSA Defect#5451
        param.put("serNum", serNum);
        // START 2016/12/22 K.Kojima [QC#16600,ADD]
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            param.put("mdseCd", mdseCd);
        }
        // END 2016/12/22 K.Kojima [QC#16600,ADD]

        // START 2016/12/22 K.Kojima [QC#16600,MOD]
        // Map<String, Object> svcMachMstrInfo = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstrPk", param);
        List<Map<String, Object>> svcMachMstrInfo = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMachMstrPk", param);
        // END 2016/12/22 K.Kojima [QC#16600,MOD]
        return svcMachMstrInfo;
    }

    /**
     * @param glblCmpyCd
     * @param svnMachMstrPk
     * @param serNum
     * @return
     */
    private BigDecimal getSvcPhysMtrPk(String glblCmpyCd, BigDecimal svnMachMstrPk, String mdlMtrLbCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        // mod start 2016/03/18 CSA Defect#5451
        param.put("svcMachMstrPk", svnMachMstrPk);
        // mod end 2016/03/18 CSA Defect#5451
        param.put("mdlMtrLbCd", mdlMtrLbCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("getSvcPhysMtrPk", param);

    }

    // START 2017/05/22 K.Kitachi [QC#18342, DEL]
//    // mod start 2016/03/10 CSA Defect#5204
//    /**
//     * createTMsg
//     * @param pMsg NSZC051001PMsg
//     * @param dsMtrIntfcPk BigDecimal
//     * @param svcPhysMtrReadGrpSq BigDecimal
//     * @param mdseCd String
//     * @param svcPhysMtrPk BigDecimal
//     * @param mdlMtrLbCd String
//     * @param readMtrCnt BigDecimal
//     * @param dsMtrProcStsCd String
//     * @param errGrpQq BigDecimal
//     * @return DS_MTR_INTFCTMsg
//     */
//    private DS_MTR_INTFCTMsg createTMsg(NSZC051001PMsg pMsg, BigDecimal dsMtrIntfcPk, BigDecimal svcPhysMtrReadGrpSq, String mdseCd, BigDecimal svcPhysMtrPk, String mdlMtrLbCd, BigDecimal readMtrCnt, String dsMtrProcStsCd,  BigDecimal errGrpQq) {
//        DS_MTR_INTFCTMsg tMsg = new DS_MTR_INTFCTMsg();
//        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
//        setValue(tMsg.dsMtrIntfcPk, dsMtrIntfcPk);
//        setValue(tMsg.mtrReadSrcTpCd, pMsg.mtrReadSrcTpCd);
//        if (!DS_MTR_PROC_STS.ERROR.equals(dsMtrProcStsCd)) {
//            setValue(tMsg.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
//        } else {
//            setValue(tMsg.svcPhysMtrReadGrpSq, errGrpQq);
//        }
//        setValue(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
//        setValue(tMsg.serNum, pMsg.serNum);
//        setValue(tMsg.mdseCd, mdseCd);
//        setValue(tMsg.svcPhysMtrPk, svcPhysMtrPk);
//        setValue(tMsg.mdlMtrLbCd, mdlMtrLbCd);
//        setValue(tMsg.readMtrCnt, readMtrCnt);
//        setValue(tMsg.mtrReadDt, pMsg.mtrReadDt);
//        setValue(tMsg.dsMtrProcStsCd, dsMtrProcStsCd);
//        setValue(tMsg.rgtnUsrId, pMsg.rgtnUsrId);
//        setValue(tMsg.dsMtrProcTs, this.dsMtrProcTs);
//        return tMsg;
//    }
//    // mod end 2016/03/10 CSA Defect#5204
//
//    /**
//     * executeInsert
//     * @param createTMsg List<DS_MTR_INTFCTMsg>
//     */
//    private void executeInsert(List<DS_MTR_INTFCTMsg> createTMsg) {
//        DS_MTR_INTFCTMsg[] createList = new DS_MTR_INTFCTMsg[createTMsg.size()];
//        S21FastTBLAccessor.insert(createTMsg.toArray(createList));
//    }
    // END 2017/05/22 K.Kitachi [QC#18342, DEL]

    /**
     * addXxMsgIdWithPrm
     * @param pMsg NSZC051001PMsg
     * @param xxMsgId String
     * @param xxMsgPrm String[]
     */
    private void addXxMsgIdWithPrm(final NSZC051001PMsg pMsg, String xxMsgId, String[] xxMsgPrm) {
        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgId, xxMsgId);
        if (xxMsgPrm != null && xxMsgPrm.length >= 1) {
            setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_0, xxMsgPrm[0]);
        }
        if (xxMsgPrm != null && xxMsgPrm.length >= 2) {
            setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_1, xxMsgPrm[1]);
        }
        if (xxMsgPrm != null && xxMsgPrm.length >= 3) {
            setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_2, xxMsgPrm[2]);
        }
        if (xxMsgPrm != null && xxMsgPrm.length >= 4) {
            setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_3, xxMsgPrm[3]);
        }
        if (xxMsgPrm != null && xxMsgPrm.length >= 5) {
            setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_4, xxMsgPrm[4]);
        }
        pMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount() + 1);
        setValue(pMsg.xxRsltStsCd, "9");
    }

    // del start 2016/03/10 CSA Defect#5204
//    /**
//     * addXxMsgIdWithPrm
//     * @param pMsg NSZC051001PMsg
//     * @param xxMsgId String
//     * @param xxMsgPrmTxt_0 String
//     * @param xxMsgPrmTxt_1 String
//     * @param xxMsgPrmTxt_2 String
//     * @param xxMsgPrmTxt_3 String
//     * @param xxMsgPrmTxt_4 String
//     */
//    private void addXxMsgIdWithPrm(final NSZC051001PMsg pMsg, String xxMsgId, String xxMsgPrmTxt0, String xxMsgPrmTxt1, String xxMsgPrmTxt2, String xxMsgPrmTxt3, String xxMsgPrmTxt4) {
//        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgId, xxMsgId);
//        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_0, xxMsgPrmTxt0);
//        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_1, xxMsgPrmTxt1);
//        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_2, xxMsgPrmTxt2);
//        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_3, xxMsgPrmTxt3);
//        setValue(pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgPrmTxt_4, xxMsgPrmTxt4);
//        pMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount() + 1);
//        setValue(pMsg.xxRsltStsCd, RETURN_CODE_ERROR);
//    }
    // del end 2016/03/10 CSA Defect#5204

    // add start 2016/03/10 CSA Defect#5205
    private String getMessage(NSZC010001_xxMsgIdListPMsg xxMsgIdListPMsg) {
        String rtnMsg = null;
        String xxMsgId = xxMsgIdListPMsg.xxMsgId.getValue();
        List<String> paramList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(xxMsgIdListPMsg.xxMsgPrmTxt_0)) {
            paramList.add(xxMsgIdListPMsg.xxMsgPrmTxt_0.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsgIdListPMsg.xxMsgPrmTxt_1)) {
            paramList.add(xxMsgIdListPMsg.xxMsgPrmTxt_1.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsgIdListPMsg.xxMsgPrmTxt_2)) {
            paramList.add(xxMsgIdListPMsg.xxMsgPrmTxt_2.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsgIdListPMsg.xxMsgPrmTxt_3)) {
            paramList.add(xxMsgIdListPMsg.xxMsgPrmTxt_3.getValue());
        }
        if (ZYPCommonFunc.hasValue(xxMsgIdListPMsg.xxMsgPrmTxt_4)) {
            paramList.add(xxMsgIdListPMsg.xxMsgPrmTxt_4.getValue());
        }

        // get Message
        if (paramList.size() > 0) {
            rtnMsg = S21MessageFunc.clspGetMessage(xxMsgId, (String[]) paramList.toArray(new String[paramList.size()]));
        } else {
            rtnMsg = S21MessageFunc.clspGetMessage(xxMsgId);
        }

        if (ZYPCommonFunc.hasValue(rtnMsg) && rtnMsg.length() > MSG_ID_LEN) {
            rtnMsg = rtnMsg.substring(MSG_ID_LEN);
        }
        return rtnMsg;
    }

    // START 2017/05/22 K.Kitachi [QC#18342, DEL]
//    private DS_MSGTMsg createDsMsgTMsg(DS_MTR_INTFCTMsg dsMtrIntfcTMsg, String apiMsg) {
//        DS_MSGTMsg tMsg = new DS_MSGTMsg();
//        setValue(tMsg.glblCmpyCd, dsMtrIntfcTMsg.glblCmpyCd);
//        setValue(tMsg.dsMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MSG_SQ));
//        setValue(tMsg.dsMsgTrxNum, dsMtrIntfcTMsg.dsMtrIntfcPk.getValue().toString());
//        setValue(tMsg.dsMsgTrxNm, DS_MTR_INTFC_PK);
//        setValue(tMsg.dsMsgTxt, apiMsg);
//        return tMsg;
//    }
//
//    /**
//     * executeDsMsgInsert
//     * @param createDsMsgTMsg List<DS_MSGTMsg>
//     */
//    private void executeDsMsgInsert(List<DS_MSGTMsg> createDsMsgTMsg) {
//        DS_MSGTMsg[] createList = new DS_MSGTMsg[createDsMsgTMsg.size()];
//        S21FastTBLAccessor.insert(createDsMsgTMsg.toArray(createList));
//    }
    // END 2017/05/22 K.Kitachi [QC#18342, DEL]
    // add end 2016/03/10 CSA Defect#5205
}

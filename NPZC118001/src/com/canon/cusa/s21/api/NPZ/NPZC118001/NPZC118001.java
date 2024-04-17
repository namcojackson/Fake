/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC118001;

import static com.canon.cusa.s21.api.NPZ.NPZC118001.constant.NPZC118001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_TECH_RCV_PRTTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.FSR_VISITTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NPZC118001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Tech Receive Parts API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/11/2015   Hitachi         T.Iwamoto       Create          NA
 * 01/04/2016   Fujitsu         S.Nakai         Update          QC#2425
 * 01/13/2016   Fujitsu         S.Nakai         Update          QC#2862
 * 02/10/2016   Hitachi         T.Iwamoto       Update          QC#3727
 * 08/03/2016   Hitachi         A.Kohinata      Update          QC#10805
 * 10/18/2016   Hitachi         T.Iwamoto       Update          QC#14447
 * 11/10/2016   Hitachi         A.Kohinata      Update          QC#15880
 * 2017/02/13   Hitachi         K.Kojima        Update          QC#16301
 * 2017/02/21   Hitachi         A.Kohinata      Update          QC#16123
 * 2017/08/07   Hitachi         K.Kojima        Update          QC#18571
 * 2018/03/27   CITS            T.Tokutomi      Update          QC#12110
 * 2018/04/03   CITS            T.Tokutomi      Update          QC#25198
 * 2018/06/07   CITS            T.Tokutomi      Update          QC#26527
 * 2018/08/28   CITS            T.Tokutomi      Update          QC#27631
 * 2019/07/29   Hitachi         T.Kanasaka      Update          QC#51585
 * 2019/10/15   Hitachi         K.Fujimoto      Update          QC#53956
 * 2019/01/24   Hitachi         Y.Takeno        Update          QC#55358
 * 2019/01/31   Hitachi         Y.Takeno        Update          QC#55358
 * 2020/06/10   CITS            T.Wada          Update          QC#57140
 * 2020/06/12   Hitachi         K.Kitachi       Update          QC#56501
 * 2020/12/07   CITS            T.Wada          Update          QC#58023
 * 2022/04/20   Hitachi         K.Kitachi       Update          QC#59897
 * </pre>
 */
public class NPZC118001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** ReceiveTaskInfoMap */
    private DS_COND_CONSTTMsg rtnStsMsg = null;

    /** ClickFormat */
    private String clickFormat = null;

    // START 02/10/2016 T.Iwamoto [QC#3727, ADD]
    /** updateTime */
    private String updateTime = null;
    // END 02/10/2016 T.Iwamoto [QC#3727, ADD]

    // add start 2016/08/03 CSA Defect#10805
    /** Rws Closed Timestamp */
    private String lastRwsCloDtTmTs = null;
    // add end 2016/08/03 CSA Defect#10805

    /**
     * Constructor
     */
    public NPZC118001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NPZC118001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NPZC118001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (getConstData(msgMap, param)) {
            if (checkParameter(msgMap, param)) {
                doProcess(msgMap, param);
            }
        }

        msgMap.flush();
    }

    private boolean getConstData(S21ApiMessageMap msgMap, NPZC118001PMsg pMsg) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9007E, new String[] {PRAM_COMPANY });
        }

        if (!getConstDataForDateFormat(pMsg)) {
            // mod start 2016/08/03 CSA Defect#10805
            // Abend
            //throw new S21AbendException(ZZZM9006E, new String[] {PRAM_CONST });
            msgMap.addXxMsgIdWithPrm(ZZZM9006E, new String[] {PRAM_CONST });
            return false;
            // mod end 2016/08/03 CSA Defect#10805
        }

        if (!getConstDataForStatus(pMsg)) {
            // mod start 2016/08/03 CSA Defect#10805
            // Abend
            //throw new S21AbendException(ZZZM9006E, new String[] {PRAM_CONST });
            msgMap.addXxMsgIdWithPrm(ZZZM9006E, new String[] {PRAM_CONST });
            return false;
            // mod end 2016/08/03 CSA Defect#10805
        }
        return true;
    }

    private boolean getConstDataForStatus(NPZC118001PMsg pMsg) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsCondConstGrpId, NPZC1180);
        setValue(inMsg.dsCondConstCd, RECEIVE_STATUS);
        rtnStsMsg = (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnStsMsg == null) {
            return false;
        }
        if (!hasValue(rtnStsMsg.dsCondConstValTxt_01)) {
            return false;
        }
        if (!hasValue(rtnStsMsg.dsCondConstValTxt_02)) {
            return false;
        }
        if (!hasValue(rtnStsMsg.dsCondConstValTxt_03)) {
            return false;
        }
        if (!hasValue(rtnStsMsg.dsCondConstValTxt_04)) {
            return false;
        }
        if (!hasValue(rtnStsMsg.dsCondConstValTxt_05)) {
            return false;
        }
        return true;
    }

    private boolean getConstDataForDateFormat(NPZC118001PMsg pMsg) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsCondConstGrpId, CLICK_COMMON);
        setValue(inMsg.dsCondConstCd, DATE_FORMAT);
        inMsg = (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return false;
        }
        if (!hasValue(inMsg.dsCondConstValTxt_01)) {
            return false;
        }
        clickFormat = (String) inMsg.dsCondConstValTxt_01.getValue();
        return true;
    }

    private boolean checkParameter(S21ApiMessageMap msgMap, NPZC118001PMsg pMsg) {

        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.xxSrchNum, PRAM_SO_NUM);
        mandatoryCheck(msgMap, pMsg.rwsStsDescTxt, PRAM_STATUS);
        mandatoryCheck(msgMap, pMsg.techCd, PRAM_TECH);
        // del start 2016/08/03 CSA Defect#10805
        //mandatoryCheck(msgMap, pMsg.whCd, PRAM_WH);
        // del end 2016/08/03 CSA Defect#10805
        // add start 2016/08/03 CSA Defect#10805
        mandatoryCheck(msgMap, pMsg.clickKeyTxt, PRAM_CLICK_KEY);
        // add end 2016/08/03 CSA Defect#10805

        // START 2020/06/12 K.Kitachi [QC#56501, MOD]
//        if (hasValue(pMsg.xxUsrSysDtTxt) && hasValue(pMsg.techCd)) { // mod 2016/10/18 CSA Defect#14447
//            if (!hasValue(convertFormat((String) pMsg.xxUsrSysDtTxt.getValue()))) {
//                msgMap.addXxMsgIdWithPrm(ZZZM9026E, new String[] {PRAM_UPDATED_DATE });
//            }
//            // mod start 2016/08/03 CSA Defect#10805
//            // START 02/10/2016 T.Iwamoto [QC#3727, ADD]
////            String techTocCd = getTechTocCd(pMsg);
////            if (hasValue(techTocCd)) {
////                SvcTimeZoneInfo svcTz = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, pMsg.xxUsrSysDtTxt.getValue(), techTocCd);
////                this.updateTime = svcTz.getDateTime().substring(0, pMsg.xxUsrSysDtTxt.getValue().length());
////            } else {
////                this.updateTime = pMsg.xxUsrSysDtTxt.getValue();
////            }
//            SvcTimeZoneInfo svcTz = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, pMsg.xxUsrSysDtTxt.getValue(), pMsg.techCd.getValue());
//            this.updateTime = svcTz.getDateTime().substring(0, pMsg.xxUsrSysDtTxt.getValue().length());
//            // END 02/10/2016 T.Iwamoto [QC#3727, ADD]
//            // mod end 2016/08/03 CSA Defect#10805
//        }
        if (hasValue(pMsg.xxUsrSysDtTxt)) {
            if (!hasValue(convertFormat((String) pMsg.xxUsrSysDtTxt.getValue()))) {
                msgMap.addXxMsgIdWithPrm(ZZZM9026E, new String[] {PRAM_UPDATED_DATE });
            } else {
                this.updateTime = pMsg.xxUsrSysDtTxt.getValue();
            }
        }
        // END 2020/06/12 K.Kitachi [QC#56501, MOD]

        if (msgMap.getMsgMgr().isXxMsgId()) {
            // Shipment does not exist
            setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
            insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String param) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(ZZZM9007E, new String[] {param });
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, NPZC118001PMsg pMsg) {

        // START 2019/07/29 T.Kanasaka [QC#51585, ADD]
        if (isAlreadySuccess(pMsg)) {
            return;
        }
        // END 2019/07/29 T.Kanasaka [QC#51585, ADD]

        List<Map<String, Object>> rwsList = specificRws(pMsg);
        if (rwsList.size() == 0) {
            // Shipment does not exist
            setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
            insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
            return;
        }

        // RWS Data Check :Status
        // QC#57140 Mod Start
//        for (Map<String, Object> rwsLine : rwsList) {
//            if (RWS_STS.RECEIVED.equals((String) rwsLine.get("RWS_STS_CD"))) {
//                // Shipment already received
//                setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_03.getValue());
//                insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_03.getValue());
//                msgMap.addXxMsgId(NPZM0196E);
//                return;
//            }
//        }
        int receivedCnt = 0;
        List<Map<String, Object>> rwsListTmp = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> rwsLine : rwsList) {
            if (RWS_STS.RECEIVED.equals((String) rwsLine.get("RWS_STS_CD"))) {
                receivedCnt++;
            } else {
                rwsListTmp.add(rwsLine);
            }
        }
        if (receivedCnt > 0) {
            if (receivedCnt == rwsList.size() ) {
                // Shipment already received
                setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_03.getValue());
                insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_03.getValue());
                msgMap.addXxMsgId(NPZM0196E);
                return;
            }
            rwsList = rwsListTmp;
        }
        // QC#57140 Mod End

        // del start 2016/08/03 CSA Defect#10805
//        // RWS Data Check :TechWh
//        if (hasValue(pMsg.svcAsgTechCd)) {
//            for (Map<String, Object> rwsLine : rwsList) {
//                if (!pMsg.svcAsgTechCd.getValue().equals((String) rwsLine.get("WH_CD"))) {
//                    // Assigned to different technician
//                    setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
//                    insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
//                    msgMap.addXxMsgId(NPZM0197E);
//                    return;
//                }
//            }
//        } else {
//            for (Map<String, Object> rwsLine : rwsList) {
//                if (!pMsg.whCd.getValue().equals((String) rwsLine.get("WH_CD"))) {
//                    // Assigned to different technician
//                    setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
//                    insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
//                    msgMap.addXxMsgId(NPZM0198E);
//                    return;
//                }
//            }
//
//        }
        // del end 2016/08/03 CSA Defect#10805
        // add start 2016/08/03 CSA Defect#10805
        // RWS Data Check :TechWh
        Map<String, Object> rtlWhMap = new HashMap<String, Object>();
        for (Map<String, Object> rwsLine : rwsList) {
            rtlWhMap = getRtlWh(pMsg, (String) rwsLine.get("RWS_NUM"));
            // START 2017/08/07 K.Kojima [QC#18571,MOD]
            // if (rtlWhMap == null || !pMsg.techCd.getValue().equals((String) rtlWhMap.get("TECH_TOC_CD"))) {
            String targetTechCd = pMsg.techCd.getValue();
            if (ZYPCommonFunc.hasValue(pMsg.svcAsgTechCd) && !pMsg.techCd.getValue().equals(pMsg.svcAsgTechCd.getValue())) {
                targetTechCd = pMsg.svcAsgTechCd.getValue();
            }
            // START 2020/01/24 [QC#55358, MOD]
            // if (rtlWhMap == null || !targetTechCd.equals((String) rtlWhMap.get("TECH_TOC_CD"))) {
            if (rtlWhMap == null || (!targetTechCd.equals((String) rtlWhMap.get("TECH_TOC_CD")) 
                                        && !targetTechCd.equals((String) rtlWhMap.get("ALT_PSN_CD")))) {
            // END 2017/08/07 K.Kojima [QC#18571,MOD]
            // END   2020/01/24 [QC#55358, MOD]
                // Assigned to different technician
                setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
                insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
                msgMap.addXxMsgId(NPZM0273E);
                return;
            }
        }
        if (rtlWhMap != null) {
            setValue(pMsg.whCd, (String) rtlWhMap.get("RTL_WH_CD"));
            setValue(pMsg.rtlWhNm, (String) rtlWhMap.get("RTL_WH_NM"));
        }

        // QC#27631 Add boolean.
        boolean isAltarnateOwner = isAltarnateOwner(pMsg.glblCmpyCd.getValue(), pMsg.whCd.getValue(), pMsg.techCd.getValue());
        // START 2020/01/31 [QC#55358, ADD]
        boolean isOwner = isOwner(pMsg.glblCmpyCd.getValue(), pMsg.whCd.getValue(), pMsg.techCd.getValue());
        // END   2020/01/31 [QC#55358, ADD]

        // QC#27631 Update condition.
        // START 2020/01/31 [QC#55358, MOD]
        // if (hasValue(pMsg.svcAsgTechCd) && !isAltarnateOwner) {
        if (hasValue(pMsg.svcAsgTechCd) && !(isAltarnateOwner || isOwner)) {
        // END   2020/01/31 [QC#55358, MOD]
            Map<String, Object> rtlSwhMap = new HashMap<String, Object>();
            for (Map<String, Object> rwsLine : rwsList) {
                rtlSwhMap = getRtlSwh(pMsg, (String) rwsLine.get("RWS_NUM"));
                if (rtlSwhMap == null) {
                    // Assigned to different technician
                    setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
                    insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_04.getValue());
                    msgMap.addXxMsgId(NPZM0274E);
                    return;
                }
            }
            if (rtlSwhMap != null) {
                setValue(pMsg.whCd, (String) rtlSwhMap.get("RTL_WH_CD"));
                setValue(pMsg.rtlWhNm, (String) rtlSwhMap.get("RTL_WH_NM"));
            }
        }
        // add end 2016/08/03 CSA Defect#10805

        // START 2020/06/12 K.Kitachi [QC#56501, ADD]
        if (hasValue(pMsg.xxUsrSysDtTxt) && hasValue(pMsg.whCd)) {
            if (!hasValue(convertFormat((String) pMsg.xxUsrSysDtTxt.getValue()))) {
                // Shipment does not exist
                setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
                insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
                msgMap.addXxMsgIdWithPrm(ZZZM9026E, new String[] {PRAM_UPDATED_DATE });
                return;
            }
            SvcTimeZoneInfo svcTz = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, pMsg.xxUsrSysDtTxt.getValue(), pMsg.whCd.getValue());
            if (svcTz != null) {
                this.updateTime = svcTz.getDateTime().substring(0, pMsg.xxUsrSysDtTxt.getValue().length());
            }
        }
        // END 2020/06/12 K.Kitachi [QC#56501, ADD]

        String msgId = null;
        String msgTxt = null;
        // Call API
        for (Map<String, Object> rwsLine : rwsList) {
            // START 2020/01/31 [QC#55358, MOD]
            // NLZC206001PMsg putAwayApiMsg = callNLZC2060API(pMsg, (String) rwsLine.get("RWS_NUM"), isAltarnateOwner);
            NLZC206001PMsg putAwayApiMsg = callNLZC2060API(pMsg, (String) rwsLine.get("RWS_NUM"), isAltarnateOwner || isOwner);
            // END   2020/01/31 [QC#55358, MOD]
            if (putAwayApiMsg.xxMsgIdList.getValidCount() != 0) {
                for (int j = 0; j < putAwayApiMsg.xxMsgIdList.getValidCount(); j++) {
                    msgId = putAwayApiMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    msgTxt = ZYPCommonFunc.concatString((String) rwsLine.get("RWS_NUM"), ":", getRtnMsg(msgId));
                    if (msgTxt.length() > MSG_LEN) {
                        msgTxt = msgTxt.substring(0, MSG_LEN);
                    }
                    msgMap.addXxMsgIdWithPrm(msgId, new String[] {msgTxt });
                }
                // Shipment does not exist
                // START 2017/02/13 K.Kojima [QC#16301,ADD]
                // This API is called directly from Clicksoft that will not control any transactions in S21.
                // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
                EZDConnectionMgr.getInstance().rollback();
                // END 2017/02/13 K.Kojima [QC#16301,ADD]
                setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
                insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
                break;
            }

            NLZC207001PMsg completeApiMsg = callNLZC2070API(pMsg, rwsLine);
            if (completeApiMsg.xxMsgIdList.getValidCount() != 0) {
                for (int j = 0; j < completeApiMsg.xxMsgIdList.getValidCount(); j++) {
                    msgId = completeApiMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    msgTxt = ZYPCommonFunc.concatString((String) rwsLine.get("RWS_NUM"), ":", getRtnMsg(msgId));
                    if (msgTxt.length() > MSG_LEN) {
                        msgTxt = msgTxt.substring(0, MSG_LEN);
                    }
                    msgMap.addXxMsgIdWithPrm(msgId, new String[] {msgTxt });
                }
                // Shipment does not exist
                // START 2017/02/13 K.Kojima [QC#16301,ADD]
                // This API is called directly from Clicksoft that will not control any transactions in S21.
                // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
                EZDConnectionMgr.getInstance().rollback();
                // END 2017/02/13 K.Kojima [QC#16301,ADD]
                setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
                insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_02.getValue());
                break;
            }

            // START 04/03/2018 T.Tokutomi [QC#25198, ADD]
            // Delete processing of Internal Transfer.
            // Processing of Internal Transfer ia carried out in NLZC206001.
            // END 04/03/2018 T.Tokutomi [QC#25198, ADD]

            // START 2019/10/15 K.Fujimoto [QC#53956, ADD]
            String svcAsgTechCd = pMsg.svcAsgTechCd.getValue();
            String techCd = pMsg.techCd.getValue();
            // START 2020/01/31 [QC#55358, MOD]
            // if(hasValue(svcAsgTechCd) && !svcAsgTechCd.equals(techCd) && !isAltarnateOwner) {

            // QC#58023 DEL START
            // if(hasValue(svcAsgTechCd) && !svcAsgTechCd.equals(techCd) && !(isAltarnateOwner || isOwner)) {
            // END   2020/01/31 [QC#55358, MOD]
                // updateFsrVisit(pMsg, (String) rwsLine.get("RWS_NUM"), techCd);
            // }
            // QC#58023 DEL END
            // END   2019/10/15 K.Fujimoto [QC#53956, ADD]
        }

        if (!msgMap.getMsgMgr().isXxMsgId()) {
            for (Map<String, Object> rwsLine : rwsList) {
                msgMap.addXxMsgIdWithPrm(NPZM0199I, new String[] {(String) rwsLine.get("RWS_NUM") });
            }
            // Success
            setValue(pMsg.rwsStsDescTxt, (String) rtnStsMsg.dsCondConstValTxt_05.getValue());
            // add start 2016/08/03 CSA Defect#10805
            if (rwsList.size() > 0) {
                this.lastRwsCloDtTmTs = getRwsCloDtTmTs(pMsg, (String) rwsList.get(rwsList.size() - 1).get("RWS_NUM"));
                if (hasValue(this.lastRwsCloDtTmTs)) {
                    SvcTimeZoneInfo svcTz = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, this.lastRwsCloDtTmTs, pMsg.whCd.getValue());
                    String convRwsCloDtTmTs = svcTz.getDateTime().substring(0, this.lastRwsCloDtTmTs.length());
                    setValue(pMsg.rwsDtTmCloTxt, convertFormat(convRwsCloDtTmTs, DATE_TIME_FORMAT, clickFormat));
                }
            }
            // add end 2016/08/03 CSA Defect#10805
            // mod start 2017/02/21 CSA Defect#16123
            // START 2022/04/20 K.Kitachi [QC#59897, MOD]
//            if (insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_05.getValue())) {
//                // Call Send My Inventory to Click API
//                callNLZC4100API(pMsg, rwsList);
//            }
            insertClickTechRcvPrt(pMsg, (String) rtnStsMsg.dsCondConstValTxt_05.getValue());
            // END 2022/04/20 K.Kitachi [QC#59897, MOD]
            // mod end 2017/02/21 CSA Defect#16123
        }
    }

    private List<Map<String, Object>> specificRws(NPZC118001PMsg pMsg) {

        List<Map<String, Object>> outArrayList = new ArrayList<Map<String, Object>>();
        RWS_HDRTMsgArray trackingArray = getRwsByTrackingNum(pMsg);
        if (trackingArray.getValidCount() != 0) {
            for (int i = 0; i < trackingArray.getValidCount(); i++) {
                Map<String, Object> outArray = new HashMap<String, Object>();
                outArray.put("RWS_NUM", (String) trackingArray.no(i).rwsNum.getValue());
                outArray.put("RWS_STS_CD", (String) trackingArray.no(i).rwsStsCd.getValue());
                outArray.put("WH_CD", (String) trackingArray.no(i).whCd.getValue());
                outArray.put("SCE_ORD_TP_CD", (String) trackingArray.no(i).sceOrdTpCd.getValue());
                outArray.put("RWS_REF_NUM", (String) trackingArray.no(i).rwsRefNum.getValue());
                outArrayList.add(outArray);
            }
            return outArrayList;
        }

        outArrayList = getRwsByVendorDoNum(pMsg);
        if (outArrayList.size() != 0) {
            return outArrayList;
        }

        RWS_HDRTMsgArray poNumArray = getRwsByCsaPoNum(pMsg);
        if (poNumArray.getValidCount() != 0) {
            for (int i = 0; i < poNumArray.getValidCount(); i++) {
                Map<String, Object> outArray = new HashMap<String, Object>();
                outArray.put("RWS_NUM", (String) poNumArray.no(i).rwsNum.getValue());
                outArray.put("RWS_STS_CD", (String) poNumArray.no(i).rwsStsCd.getValue());
                outArray.put("WH_CD", (String) poNumArray.no(i).whCd.getValue());
                outArray.put("SCE_ORD_TP_CD", (String) poNumArray.no(i).sceOrdTpCd.getValue());
                outArray.put("RWS_REF_NUM", (String) poNumArray.no(i).rwsRefNum.getValue());
                outArrayList.add(outArray);
            }
        }
        return outArrayList;
    }

    private RWS_HDRTMsgArray getRwsByTrackingNum(NPZC118001PMsg pMsg) {
        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        inMsg.setSQLID("016");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("imptInvBolNum01", pMsg.xxSrchNum.getValue());
        return (RWS_HDRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private List<Map<String, Object>> getRwsByVendorDoNum(NPZC118001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("shipFromSoNum", pMsg.xxSrchNum.getValue());
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRwsByDoNum", param);
        return rs;
    }

    private RWS_HDRTMsgArray getRwsByCsaPoNum(NPZC118001PMsg pMsg) {
        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        inMsg.setSQLID("017");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("rwsRefNum01", pMsg.xxSrchNum.getValue());
        return (RWS_HDRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // QC#27631 Update method.
    private NLZC206001PMsg callNLZC2060API(NPZC118001PMsg pMsg, String rwsNum, boolean isAltarnateOwner) {
        NLZC206001PMsg apiPMsg = new NLZC206001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);

        List<Map<String, Object>> rwsDetailList = getRwsDetail(pMsg, rwsNum);
        // START 04/03/2018 T.Tokutomi [QC#25198, ADD]
        Map<String, Object> rtlSwhMap = getRtlSwh(pMsg, rwsNum);
        // END 04/03/2018 T.Tokutomi [QC#25198, ADD]
        int trgtCnt = 0;
        for (Map<String, Object> rwsDetail : rwsDetailList) {
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).rwsNum, (String) rwsDetail.get("RWS_NUM"));
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).rwsDtlLineNum, (String) rwsDetail.get("RWS_DTL_LINE_NUM"));
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).invtyStkStsCd, (String) rwsDetail.get("INVTY_STK_STS_CD"));
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).sceOrdTpCd, (String) rwsDetail.get("SCE_ORD_TP_CD"));

            // START 02/10/2016 T.Iwamoto [QC#3727, ADD]
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).rwsStkDtTmTs, convertFormat(updateTime));
            // END 02/10/2016 T.Iwamoto [QC#3727, ADD]
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).rwsStkQty, (BigDecimal) rwsDetail.get("RWS_QTY"));
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).whCd, (String) rwsDetail.get("INVTY_LOC_CD"));
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).mdseCd, (String) rwsDetail.get("MDSE_CD"));
            setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).rwsRefNum, (String) rwsDetail.get("RWS_REF_NUM"));
            // START 04/03/2018 T.Tokutomi [QC#25198, ADD]
            // QC#26527 Update.
            // QC#27631 Add condition.
            if (hasValue(pMsg.svcAsgTechCd) && !isAltarnateOwner) {
                setValue(apiPMsg.RWSPutAwayList.no(trgtCnt).destInvtyLocCd, (String) rtlSwhMap.get("INVTY_LOC_CD"));
            }
            // END 04/03/2018 T.Tokutomi [QC#25198, ADD]
            trgtCnt++;
        }
        apiPMsg.RWSPutAwayList.setValidCount(trgtCnt);

        RWS_SERTMsgArray serArray = getRwsSerial(pMsg, rwsNum);
        int serCnt = 0;
        for (; serCnt < serArray.getValidCount(); serCnt++) {
            setValue(apiPMsg.RcvSerNumList.no(serCnt).rwsDtlLineNum, serArray.no(serCnt).rwsLineNum);
            setValue(apiPMsg.RcvSerNumList.no(serCnt).serNum, serArray.no(serCnt).serNum);
            setValue(apiPMsg.RcvSerNumList.no(serCnt).mdseCd, serArray.no(serCnt).mdseCd);
        }
        apiPMsg.RcvSerNumList.setValidCount(serCnt);

        NLZC206001 api = new NLZC206001();
        api.execute(apiPMsg, this.onBatchType);
        return apiPMsg;
    }

    private RWS_SERTMsgArray getRwsSerial(NPZC118001PMsg pMsg, String rwsNum) {
        RWS_SERTMsg inMsg = new RWS_SERTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("rwsNum01", rwsNum);
        return (RWS_SERTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private List<Map<String, Object>> getRwsDetail(NPZC118001PMsg pMsg, String rwsNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rwsNum", rwsNum);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRwsDetail", param);
        return rs;
    }

    private NLZC207001PMsg callNLZC2070API(NPZC118001PMsg pMsg, Map<String, Object> rwsLine) {
        // START 02/10/2016 T.Iwamoto [QC#3727, ADD]
        String rwsCloDtTmTs = convertFormat(updateTime);
        // END 02/10/2016 T.Iwamoto [QC#3727, ADD]

        NLZC207001PMsg apiPMsg = new NLZC207001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.rwsNum, (String) rwsLine.get("RWS_NUM"));
        setValue(apiPMsg.sceOrdTpCd, (String) rwsLine.get("SCE_ORD_TP_CD"));
        setValue(apiPMsg.rwsCloDtTmTs, rwsCloDtTmTs);
        setValue(apiPMsg.whCd, (String) rwsLine.get("WH_CD"));
        setValue(apiPMsg.rwsRefNum, (String) rwsLine.get("RWS_REF_NUM"));

        NLZC207001 api = new NLZC207001();
        api.execute(apiPMsg, this.onBatchType);
        return apiPMsg;
    }

    private String convertFormat(String dateTimeData) {
        String outputDtTm = null;
        if (hasValue(dateTimeData)) {
            SimpleDateFormat inputFormat = new SimpleDateFormat(clickFormat);
            Date inputDtTm = null;
            SimpleDateFormat outputFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            try {
                inputDtTm = inputFormat.parse(dateTimeData);
                outputDtTm = outputFormat.format(inputDtTm);
            } catch (ParseException e) {
                return null;
            }
        }
        return outputDtTm;
    }

    private String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }
    private boolean insertClickTechRcvPrt(NPZC118001PMsg pmsg, String rwsStsDescTxt) {

        // START 2019/07/29 T.Kanasaka [QC#51585, ADD]
        if (!((String) rtnStsMsg.dsCondConstValTxt_05.getValue()).equals(rwsStsDescTxt)) {
            if (isAlreadyExist(pmsg, rwsStsDescTxt)) {
                return true;
            }
        }
        // END 2019/07/29 T.Kanasaka [QC#51585, ADD]

        CLICK_TECH_RCV_PRTTMsg clickTechRcvPrtTMsg = new CLICK_TECH_RCV_PRTTMsg();
        BigDecimal clickTechRcvPrtPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CLICK_TECH_RCV_PRT_SQ");
        setValue(clickTechRcvPrtTMsg.glblCmpyCd, pmsg.glblCmpyCd.getValue());
        setValue(clickTechRcvPrtTMsg.clickTechRcvPrtPk, clickTechRcvPrtPk);
        setValue(clickTechRcvPrtTMsg.clickTrkNum, pmsg.xxSrchNum.getValue());
        setValue(clickTechRcvPrtTMsg.rwsStsDescTxt, rwsStsDescTxt);
        setValue(clickTechRcvPrtTMsg.techCd, pmsg.techCd.getValue());
        setValue(clickTechRcvPrtTMsg.whCd, pmsg.whCd.getValue());
        // add start 2016/08/03 CSA Defect#10805
        setValue(clickTechRcvPrtTMsg.rtlWhNm, pmsg.rtlWhNm);
        // add end 2016/08/03 CSA Defect#10805
        setValue(clickTechRcvPrtTMsg.svcAsgTechCd, pmsg.svcAsgTechCd.getValue());
        // START 02/10/2016 T.Iwamoto [QC#3727, ADD]
        setValue(clickTechRcvPrtTMsg.lastDtTmUpdTxt, updateTime);
        // END 02/10/2016 T.Iwamoto [QC#3727, ADD]
        // add start 2016/08/03 CSA Defect#10805
        setValue(clickTechRcvPrtTMsg.rwsDtTmCloTxt, convertFormat(this.lastRwsCloDtTmTs, DATE_TIME_FORMAT, clickFormat));
        // add end 2016/08/03 CSA Defect#10805
        setValue(clickTechRcvPrtTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        setValue(clickTechRcvPrtTMsg.clickKeyTxt, pmsg.clickKeyTxt.getValue());

        S21FastTBLAccessor.insert(clickTechRcvPrtTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickTechRcvPrtTMsg.getReturnCode())) {
            S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
            msgMap.addXxMsgId(NPZM0208E);
            msgMap.flush();
            return false;
        }
        // START 2017/02/13 K.Kojima [QC#16301,ADD]
        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        EZDConnectionMgr.getInstance().commit();
        // END 2017/02/13 K.Kojima [QC#16301,ADD]
        return true;
    }

    // add start 2017/02/21 CSA Defect#16123
    // START 2022/04/20 K.Kitachi [QC#59897, DEL]
//    private void callNLZC4100API(NPZC118001PMsg pMsg, List<Map<String, Object>> rwsList) {
//        List<String> invtyLocCdList = new ArrayList<String>();
//        List<String> mdseCdList = new ArrayList<String>();
//
//        for (Map<String, Object> rwsLine : rwsList) {
//            List<Map<String, Object>> rwsDetailList = getRwsDetail(pMsg, (String) rwsLine.get("RWS_NUM"));
//            for (Map<String, Object> rwsDetail : rwsDetailList) {
//                if (!invtyLocCdList.contains((String) rwsDetail.get("INVTY_LOC_CD"))) {
//                    invtyLocCdList.add((String) rwsDetail.get("INVTY_LOC_CD"));
//                }
//                // Modify 2018/03/27 QC#12110 Tool Item.
//                String mdseCd = (String) rwsDetail.get("MDSE_CD");
//                if (!mdseCdList.contains(mdseCd) // 
//                        && !isToolItem(pMsg.glblCmpyCd.getValue(), mdseCd)) {
//                    mdseCdList.add(mdseCd);
//                }
//            }
//
//            if (hasValue(pMsg.svcAsgTechCd) && !pMsg.techCd.getValue().equals(pMsg.svcAsgTechCd.getValue())) {
//                Map<String, Object> rtlSwhMap = getRtlSwh(pMsg, (String) rwsLine.get("RWS_NUM"));
//                if (rtlSwhMap != null) {
//                    if (!invtyLocCdList.contains((String) rtlSwhMap.get("INVTY_LOC_CD"))) {
//                        invtyLocCdList.add((String) rtlSwhMap.get("INVTY_LOC_CD"));
//                    }
//                }
//            }
//        }
//
//        for (String invtyLocCd : invtyLocCdList) {
//            for (String mdseCd : mdseCdList) {
//                NLZC410001PMsg apiPMsg = new NLZC410001PMsg();
//                setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//                setValue(apiPMsg.procDt, pMsg.slsDt);
//                setValue(apiPMsg.xxModeCd, NLZC410001Constant.MODE_DAILIY);
//                setValue(apiPMsg.mdseCd, mdseCd);
//                setValue(apiPMsg.invtyLocCd, invtyLocCd);
//                NLZC410001 api = new NLZC410001();
//                api.execute(apiPMsg, this.onBatchType);
//                if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
//                    EZDConnectionMgr.getInstance().rollback();
//                    return;
//                }
//            }
//        }
//        EZDConnectionMgr.getInstance().commit();
//    }
    // END 2022/04/20 K.Kitachi [QC#59897, DEL]
    // add end 2017/02/21 CSA Defect#16123

    private String convertFormat(String dateTimeData, String fromFormat, String toFormat) {
        String outputDtTm = null;
        if (hasValue(dateTimeData)) {
            SimpleDateFormat inputFormat = new SimpleDateFormat(fromFormat);
            Date inputDtTm = null;
            SimpleDateFormat outputFormat = new SimpleDateFormat(toFormat);
            try {
                inputDtTm = inputFormat.parse(dateTimeData);
                outputDtTm = outputFormat.format(inputDtTm);
            } catch (ParseException e) {
                return null;
            }
        }
        return outputDtTm;
    }

    private Map<String, Object> getRtlWh(NPZC118001PMsg pMsg, String rwsNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rwsNum", rwsNum);
        param.put("slsDt", pMsg.slsDt);
        param.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRtlWh", param);
    }

    private Map<String, Object> getRtlSwh(NPZC118001PMsg pMsg, String rwsNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rwsNum", rwsNum);
        // START 2017/08/07 K.Kojima [QC#18571,MOD]
        // param.put("techTocCd", pMsg.svcAsgTechCd.getValue());
        param.put("techTocCd", pMsg.techCd.getValue());
        // END 2017/08/07 K.Kojima [QC#18571,MOD]
        param.put("rtlWhCatgTechCarStoc", RTL_WH_CATG.TECH_CAR_STOCK);
        param.put("slsDt", pMsg.slsDt);
        param.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRtlSwh", param);
    }

    private String getRwsCloDtTmTs(NPZC118001PMsg pMsg, String rwsNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rwsNum", rwsNum);
        param.put("rwsStsReceived", RWS_STS.RECEIVED);
        return (String) this.ssmBatchClient.queryObject("getRwsCloDtTmTs", param);
    }
    // add end 2016/08/03 CSA Defect#10805

    // START 2022/04/20 K.Kitachi [QC#59897, DEL]
//    /**
//     * isToolItem
//     * QC#12110 Add method.
//     * @param glblCmpyCd
//     * @param mdseCd
//     * @return
//     */
//    private boolean isToolItem(String glblCmpyCd, String mdseCd) {
//
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("mdseCd", mdseCd);
//
//        String prtItemTpCd = (String) this.ssmBatchClient.queryObject("getPrtItemTp", queryParam);
//
//        if (ZYPCommonFunc.hasValue(prtItemTpCd) && PRT_ITEM_TP.TOOL.equals(prtItemTpCd)) {
//            return true;
//        }
//        return false;
//    }
    // END 2022/04/20 K.Kitachi [QC#59897, DEL]

    /**
     * isAltarnateOwner
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param psnCd String
     * @return true:Altarnate Owner
     */
    private boolean isAltarnateOwner(String glblCmpyCd, String rtlWhCd, String psnCd) {

        RTL_WHTMsg cond = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.rtlWhCd, rtlWhCd);

        RTL_WHTMsg rtlWh = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(cond);

        if (ZYPCommonFunc.hasValue(psnCd) && //
                psnCd.equals(rtlWh.altPsnCd.getValue())) {
            return true;
        }

        return false;
    }

    // START 2019/07/29 T.Kanasaka [QC#51585, ADD]
    private boolean isAlreadySuccess(NPZC118001PMsg pMsg) {
        if (!hasValue(pMsg.xxSrchNum) || !hasValue(pMsg.clickKeyTxt)) {
            return false;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("clickTrkNum", pMsg.xxSrchNum.getValue());
        param.put("rwsStsDescTxt_Success", (String) rtnStsMsg.dsCondConstValTxt_05.getValue());
        param.put("clickKeyTxt", pMsg.clickKeyTxt.getValue());
        @SuppressWarnings("unchecked")
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countSuccess", param);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }

    private boolean isAlreadyExist(NPZC118001PMsg pMsg, String rwsStsDescTxt) {
        if (!hasValue(pMsg.clickKeyTxt) || !hasValue(rwsStsDescTxt)) {
            return false;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rwsStsDescTxt", rwsStsDescTxt);
        param.put("clickKeyTxt", pMsg.clickKeyTxt.getValue());
        param.put("clickTrkNum", pMsg.xxSrchNum.getValue());
        param.put("techCd", pMsg.techCd.getValue());
        param.put("whCd", pMsg.whCd.getValue());
        param.put("rtlWhNm", pMsg.rtlWhNm.getValue());
        param.put("svcAsgTechCd", pMsg.svcAsgTechCd.getValue());
        @SuppressWarnings("unchecked")
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countClickTechRcvPrt", param);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }
    // END 2019/07/29 T.Kanasaka [QC#51585, ADD]
    // START 2019/10/10 K.Fujimoto [QC#53956, ADD]
    private void updateFsrVisit(NPZC118001PMsg pMsg, String rwsNum, String svcAsgTechCd) {
        if (!hasValue(rwsNum)) { 
            return;
        }
        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitByRws(pMsg, rwsNum);
        if (fsrVisitTMsg == null) { 
            return;
        }
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        setValue(fsrVisitTMsg.techCd, svcAsgTechCd);
        S21ApiTBLAccessor.update(fsrVisitTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrVisitTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0299E);
            return;
        }
    }
    private FSR_VISITTMsg getFsrVisitByRws(NPZC118001PMsg pMsg, String rwsNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("rwsNum", rwsNum);
        Map<String, Object> fsrVisitMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getFsrVisitByRws", param);

        if(fsrVisitMap != null) {
            FSR_VISITTMsg paramTMsg = new FSR_VISITTMsg();
            setValue(paramTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(paramTMsg.fsrNum, (String)fsrVisitMap.get("FSR_NUM"));
            setValue(paramTMsg.fsrVisitNum, (String)fsrVisitMap.get("FSR_VISIT_NUM"));
            FSR_VISITTMsg fsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
            if(fsrVisitTMsg != null) {
                return fsrVisitTMsg;
            }
        }
        return null;
    }
    // END   2019/10/10 K.Fujimoto [QC#53956, ADD]

// START 2020/01/31 [QC#55358, ADD]
    /**
     * isOwner
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param psnCd String
     * @return true:Owner
     */
    private boolean isOwner(String glblCmpyCd, String rtlWhCd, String psnCd) {

        RTL_WHTMsg cond = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.rtlWhCd, rtlWhCd);

        RTL_WHTMsg rtlWh = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(cond);

        if (ZYPCommonFunc.hasValue(psnCd) && //
                psnCd.equals(rtlWh.techTocCd.getValue())) {
            return true;
        }

        return false;
    }
// END   2020/01/31 [QC#55358, ADD]
}

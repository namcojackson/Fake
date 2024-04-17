/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC070001;

import static com.canon.cusa.s21.api.NSZ.NSZC070001.constant.NSZC070001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPItem;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.FSRTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;

import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRXTMsgArray;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TM_EVENTTMsg;
import business.db.SVC_TM_EVENTTMsgArray;
import business.db.TECH_LOCTMsg;
import business.db.TECH_LOCTMsgArray;
import business.db.TECH_MSTRTMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC070001PMsg;
import business.parts.NSZC070001_xxChargeableListPMsg;
import business.parts.NSZC070001_xxExpenseListPMsg;
import business.parts.NSZC070001_xxFsrIstlChkListPMsg;
import business.parts.NSZC070001_xxLaborListPMsg;
import business.parts.NSZC070001_xxPartsUsageListPMsg;
import business.parts.NSZC107001PMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * FSR Debrief API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   Hitachi         A.Kohinata      Create          N/A
 * 12/16/2015   Hitachi         T.Tomita        Update          CSA QC#978
 * 2016/01/07   Hitachi         K.Yamada        Update          CSA QC#2554
 * 01/19/2016   Fujitsu         S.Nakai         Update          CSA QC#2750
 * 02/16/2016   Hitachi         T.Iwamoto       Update          CSA QC#2748,4135
 * 02/22/2016   Hitachi         T.Iwamoto       Update          CSA QC#2695
 * 02/25/2016   Hitachi         T.Iwamoto       Update          CSA QC#3991
 * 04/14/2016   Hitachi         A.Kohinata      Update          CSA QC#7017
 * 04/19/2016   Hitachi         T.Iwamoto       Update          CSA QC#7165
 * 04/22/2016   Hitachi         T.Iwamoto       Update          CSA QC#7448
 * 05/19/2016   Hitachi         T.Iwamoto       Update          CSA QC#8457
 * 06/03/2016   Hitachi         Y.Takeno        Update          CSA QC#3727
 * 06/16/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 07/21/2016   Hitachi         T.Iwamoto       Update          CSA QC#12154
 * 08/02/2016   Hitachi         T.Iwamoto       Update          CSA QC#12678
 * 08/22/2016   Hitachi         T.Iwamoto       Update          CSA QC#13611
 * 09/28/2016   Hitachi         T.Iwamoto       Update          CSA QC#14251
 * 10/04/2016   Hitachi         T.Iwamoto       Update          CSA QC#14981
 * 01/20/2017   Hitachi         Y.Takeno        Update          CSA QC#15092
 * 02/01/2017   Hitachi         Y.Takeno        Update          CSA QC#17291
 * 04/19/2017   Hitachi         K.Kitachi       Update          CSA QC#18075
 * 05/31/2017   Hitachi         K.Kitachi       Update          CSA QC#18447
 * 09/01/2017   Hitachi         K.Kim           Update          CSA QC#20672
 * 12/27/2017   CITS            M.Naito         Update          CSA QC#18646
 * 01/15/2018   Hitachi         K.Ochiai        Update          CSA QC#18646
 * 05/14/2018   CITS            M.Naito         Update          CSA QC#23898
 * 08/27/2018   CITS            T.Wada          Update          CSA QC#27882
 * 01/10/2019   Hitachi         K.Fujimoto      Update          CSA QC#26861
 * 03/08/2019   Hitachi         A.Kohinata      Update          CSA QC#30709
 * 08/14/2019   Hitachi         K.Fujimoto      Update          CSA QC#52546
 * 2019/10/10   Hitachi         K.Fujimoto      Update          CSA QC#54070
 * 2023/11/30   CITS            R.Kurahashi     Update          QC#62436
 *</pre>
 */
public class NSZC070001 extends S21ApiCommonBase {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;

    /** Arrive Date */
    private String arvDt = null;

    /** Arrive Time */
    private String arvTm = null;

    /** Complete Date */
    private String cpltDt = null;

    /** Complete Time */
    private String cpltTm = null;

    // Add Start 2019/01/10 K.Fujimoto QC#26861
    /** Current FSR Status **/
    private String currentFsrSts = null;
    // Add End   2019/01/10 K.Fujimoto QC#26861

    /** Travel Time */
    private BigDecimal trvlTmNum = BigDecimal.ZERO;

    /** Labor Time */
    private BigDecimal lborTmNum = BigDecimal.ZERO;

    /** Travel List */
    private List<NSZC070001_xxLaborListPMsg> trvlList = new ArrayList<NSZC070001_xxLaborListPMsg>();

    /** Labor List */
    private List<NSZC070001_xxLaborListPMsg> lborList = new ArrayList<NSZC070001_xxLaborListPMsg>();

    // add start 2016/02/22 CSA Defect#2695
    /** max Date */
    private static final String MAX_DT = "99991231";

    /** max Time */
    private static final String MAX_TM = "235959";
    // add end 2016/02/22 CSA Defect#2695

    // add start 2016/06/03 CSA Defect#3727
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;
    // add end 2016/06/03 CSA Defect#3727

    /**
     * Constructor
     */
    public NSZC070001() {
        super();
        // add start 2016/06/03 CSA Defect#3727
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // add end 2016/06/03 CSA Defect#3727
    }

    /**
     * execute
     * @param pMsgList List<NSZC070001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC070001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC070001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC070001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC070001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        this.onBatTp = onBatchType;

        if (hasValue(pMsg.xxModeCd)) {
            if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
                doProcDebrief(msgMap);
            } else if (MODE_CHARGES.equals(pMsg.xxModeCd.getValue())) {
                doProcCharges(msgMap);
            } else if (MODE_INSTALL_CHECK.equals(pMsg.xxModeCd.getValue())) {
                doProcInstallCheck(msgMap);
            } else {
                msgMap.addXxMsgId(NSZM0039E);
            }
        } else {
            msgMap.addXxMsgId(NSZM0003E);
        }
        msgMap.flush();

        // add start 2016/06/16 CSA Defect#9677
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return;
        }
        // Call Send Task Info API To Click
        // Mod Start 2019/01/10 K.Fujimoto QC#26861
        if (this.currentFsrSts != null && !this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            callSendTaskInfoApi(msgMap, pMsg);
        }
        // Mod End   2019/01/10 K.Fujimoto QC#26861
        msgMap.flush();
        // add end 2016/06/16 CSA Defect#9677
    }

    private void doProcDebrief(S21ApiMessageMap msgMap) {
        if (!checkParamDebrief(msgMap)) {
            return;
        }

        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        FSR_VISITTMsg fsrVisitTMsg = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.svcTaskNum.getValue());
        if (fsrVisitTMsg == null) {
            msgMap.addXxMsgId(NSZM0182E);
            return;
        }
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
        if (svcTaskTMsg == null) {
            msgMap.addXxMsgId(NSZM0079E);
            return;
        }
        FSRTMsg fsrTMsg = getFsr(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg == null) {
            msgMap.addXxMsgId(NSZM0181E);
            return;
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        this.currentFsrSts = fsrTMsg.fsrStsCd.getValue();
        // Add End   2019/01/10 K.Fujimoto QC#26861

        getDtTmDebrief(msgMap, fsrVisitTMsg);

        TECH_LOCTMsg techLocTMsg = getTechLoc(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.techCd.getValue());
        // mod start 2016/04/14 CSA Defect#7017
        // START 2017/04/19 K.Kitachi [QC#18075, MOD]
//        if (!SVC_TASK_STS.WAITING_FOR_CUSTOMER_ACTION.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
        if (!SVC_TASK_STS.WAITING_FOR_CUSTOMER_ACTION.equals(fsrVisitTMsg.fsrVisitStsCd.getValue()) && pMsg.xxPartsUsageList.getValidCount() > 0) {
        // END 2017/04/19 K.Kitachi [QC#18075, MOD]
            // START 2018/01/15 K.Ochiai [QC#18646, MOD]
            for (int i = 0; i < pMsg.xxPartsUsageList.getValidCount(); i++) {
                if (techLocTMsg == null && !FLG_ON_Y.equals(pMsg.xxPartsUsageList.no(i).svcInvtyExFlg.getValue())) {
                    msgMap.addXxMsgId(NSZM0772E);
                    return;
                }
            }
            // END 2018/01/15 K.Ochiai [QC#18646, MOD]
        }
        // mod end 2016/04/14 CSA Defect#7017

        if (!callSvcDiptCpltApiDebrief(msgMap, fsrVisitTMsg, svcTaskTMsg, fsrTMsg, techLocTMsg)) {
            return;
        }

        // START 2017/05/31 K.Kitachi [QC#18447, MOD]
//        if (hasValue(pMsg.svcMemoTpCd) && hasValue(pMsg.svcCmntTxt)) {
//            insertSvcMemo(msgMap);
//        }
        for (int i = 0; i < pMsg.svcMemoList.getValidCount(); i++) {
            if (hasValue(pMsg.svcMemoList.no(i).svcMemoTpCd) && hasValue(pMsg.svcMemoList.no(i).svcCmntTxt)) {
                insertSvcMemo(msgMap, i);
            }
        }
        // END 2017/05/31 K.Kitachi [QC#18447, MOD]
    }

    // START 2015/12/16 T.Tomita [QC#978, MOD]
    private void doProcCharges(S21ApiMessageMap msgMap) {
        if (!checkParamCharges(msgMap)) {
            return;
        }

        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        FSR_VISITTMsg fsrVisitTMsg = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.svcTaskNum.getValue());
        if (fsrVisitTMsg == null) {
            msgMap.addXxMsgId(NSZM0182E);
            return;
        }

        FSRTMsg fsrTMsg = getFsrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg == null) {
            msgMap.addXxMsgId(NSZM0181E);
            return;
        }
        // Add Start 2019/01/10 K.Fujimoto QC#26861
        this.currentFsrSts = fsrTMsg.fsrStsCd.getValue();
        // Add End   2019/01/10 K.Fujimoto QC#26861

//        getDtTmCharges(msgMap);

        if (!callSvcDiptCpltApiCharges(msgMap, fsrVisitTMsg, fsrTMsg)) {
            return;
        }
    }
    // END 2015/12/16 T.Tomita [QC#978, MOD]
    private void doProcInstallCheck(S21ApiMessageMap msgMap) {
        if (!checkParamInstallCheck(msgMap)) {
            return;
        }

        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        FSR_VISITTMsg fsrVisitTMsg = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue(), pMsg.svcTaskNum.getValue());
        if (fsrVisitTMsg == null) {
            msgMap.addXxMsgId(NSZM0182E);
            return;
        }

        FSRTMsg fsrTMsg = getFsr(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg == null) {
            msgMap.addXxMsgId(NSZM0181E);
            return;
        }

        // Add Start 2019/01/10 K.Fujimoto QC#26861
        this.currentFsrSts = fsrTMsg.fsrStsCd.getValue();
        if (this.currentFsrSts.equals(SVC_TASK_STS.CLOSED)) {
            return;
        }
        // Add End   2019/01/10 K.Fujimoto QC#26861
        if (!callSvcDiptCpltApiIstallCheck(msgMap, fsrVisitTMsg, fsrTMsg)) {
            return;
        }
    }

    private boolean checkParamDebrief(S21ApiMessageMap msgMap) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        checkMandatory(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        checkMandatory(msgMap, pMsg.slsDt, NSZM0002E);
        checkMandatory(msgMap, pMsg.usrId, NSZM0293E);
        checkMandatory(msgMap, pMsg.fsrNum, NSZM0180E);
        checkMandatory(msgMap, pMsg.svcTaskNum, NSZM0082E);
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);
        checkMandatory(msgMap, pMsg.svcPblmTpCd, NSZM0243E);
        checkMandatory(msgMap, pMsg.svcPblmLocTpCd, NSZM0244E);
        checkMandatory(msgMap, pMsg.svcPblmRsnTpCd, NSZM0245E);
        checkMandatory(msgMap, pMsg.svcPblmCrctTpCd, NSZM0246E);
//        checkMandatory(msgMap, pMsg.iwrStsCd, NSZM0792E);
        checkMandatory(msgMap, pMsg.machDownFlg, NSZM0278E);

        for (int i = 0; i < pMsg.xxLaborList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).svcTaskNum, NSZM0082E);
            // START  02/16/2016 T.Iwamoto [QC#2748, DEL]
            // START  08/27/2018 T.Wada [QC#27882, ADD]
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).svcTmEventFromDt, NSZM0273E);
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).svcTmEventFromTm, NSZM0274E);
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).svcTmEventToDt, NSZM0275E);
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).svcTmEventToTm, NSZM0276E);
            // END  08/27/2018 T.Wada [QC#27882, ADD]
            // END  02/16/2016 T.Iwamoto [QC#2748, DEL]

            checkMandatory(msgMap, pMsg.xxLaborList.no(i).durnTmNum, NSZM0761E);
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).mdseCd, NSZM0794E);
            checkMandatory(msgMap, pMsg.xxLaborList.no(i).mdseItemBillTpCd, NSZM0795E);
        }

        for (int i = 0; i < pMsg.xxPartsUsageList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.xxPartsUsageList.no(i).svcTaskNum, NSZM0082E);
            checkMandatory(msgMap, pMsg.xxPartsUsageList.no(i).mdseCd, NSZM0013E);
            checkMandatory(msgMap, pMsg.xxPartsUsageList.no(i).mdseNm, NSZM0249E);
            checkMandatory(msgMap, pMsg.xxPartsUsageList.no(i).svcPrtQty, NSZM0250E);
            checkMandatory(msgMap, pMsg.xxPartsUsageList.no(i).pkgUomCd, NSZM0798E);
            checkMandatory(msgMap, pMsg.xxPartsUsageList.no(i).svcExecDt, NSZM0739E);
        }

        for (int i = 0; i < pMsg.xxExpenseList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.xxExpenseList.no(i).svcTaskNum, NSZM0082E);
            checkMandatory(msgMap, pMsg.xxExpenseList.no(i).mdseCd, NSZM0013E);
            checkMandatory(msgMap, pMsg.xxExpenseList.no(i).expQty, NSZM0801E);
            checkMandatory(msgMap, pMsg.xxExpenseList.no(i).pkgUomCd, NSZM0798E);
            checkMandatory(msgMap, pMsg.xxExpenseList.no(i).svcExecDt, NSZM0739E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    // START 2015/12/16 T.Tomita [QC#978, MOD]
    private boolean checkParamCharges(S21ApiMessageMap msgMap) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        checkMandatory(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        checkMandatory(msgMap, pMsg.slsDt, NSZM0002E);
        checkMandatory(msgMap, pMsg.usrId, NSZM0293E);
        checkMandatory(msgMap, pMsg.fsrNum, NSZM0180E);
        checkMandatory(msgMap, pMsg.svcTaskNum, NSZM0082E);
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);

        // DEL Start 04/19/2016 QC#7165
        // String svcInvChrgTpCd;
        // DEL End 04/19/2016 QC#7165
        for (int i = 0; i < pMsg.xxChargeableList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).fsrNum, NSZM0180E);
            // MOD Start 05/19/2016 QC#8457
            // checkMandatory(msgMap, pMsg.xxChargeableList.no(i).fsrVisitNum, NSZM0125E);
            // checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcTaskNum, NSZM0082E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgTrxTpCd, NSZM0807E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).mdseCd, NSZM0013E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgQty, NSZM0808E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).uomCd, NSZM0644E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgUnitAmt, NSZM0809E);
            // checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgDealAmt, NSZM0810E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgFlg, NSZM0811E);
            // del start 08/22/2016 QC#13611
            // checkMandatory(msgMap, pMsg.xxChargeableList.no(i).prcCatgCd, NSZM0812E);
            // del end 08/22/2016 QC#13611
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcInvChrgTpCd, NSZM0127E);
            checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgDiscRate, NSZM0973E);
            // MOD End 05/19/2016 QC#8457

            // DEL Start 04/19/2016 QC#7165
            // svcInvChrgTpCd = pMsg.xxChargeableList.no(i).svcInvChrgTpCd.getValue();
            // if (hasValue(svcInvChrgTpCd) && (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(svcInvChrgTpCd) || SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(svcInvChrgTpCd))) {
            //     checkMandatory(msgMap, pMsg.xxChargeableList.no(i).svcChrgUnitAot, NSZM0813E);
            // }
            // DEL End 04/19/2016 QC#7165

        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }
    // END 2015/12/16 T.Tomita [QC#978, MOD]
    private boolean checkParamInstallCheck(S21ApiMessageMap msgMap) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        checkMandatory(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        checkMandatory(msgMap, pMsg.slsDt, NSZM0002E);
        checkMandatory(msgMap, pMsg.usrId, NSZM0293E);
        checkMandatory(msgMap, pMsg.fsrNum, NSZM0180E);
        checkMandatory(msgMap, pMsg.svcTaskNum, NSZM0082E);
        checkMandatory(msgMap, pMsg.svcMachMstrPk, NSZM0074E);

        for (int i = 0; i < pMsg.xxFsrIstlChkList.getValidCount(); i++) {
            checkMandatory(msgMap, pMsg.xxFsrIstlChkList.no(i).fsrNum, NSZM0291E);
            checkMandatory(msgMap, pMsg.xxFsrIstlChkList.no(i).svcTaskNum, NSZM0082E);
            checkMandatory(msgMap, pMsg.xxFsrIstlChkList.no(i).svcConfigMstrPk, NSZM0570E);
            checkMandatory(msgMap, pMsg.xxFsrIstlChkList.no(i).mdseCd, NSZM0842E);
            checkMandatory(msgMap, pMsg.xxFsrIstlChkList.no(i).istlChkVerFlg, NSZM0843E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void getDtTmDebrief(S21ApiMessageMap msgMap, FSR_VISITTMsg fsrVisitTMsg) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxLaborList.getValidCount(); i++) {
            NSZC070001_xxLaborListPMsg dtlPMsg = (NSZC070001_xxLaborListPMsg) pMsg.xxLaborList.no(i).clone();
            if (MDSE_ITEM_BILL_TP.LABOR.equals(dtlPMsg.mdseItemBillTpCd.getValue())) {
                lborList.add(dtlPMsg);
            } else if (MDSE_ITEM_BILL_TP.TRAVEL.equals(dtlPMsg.mdseItemBillTpCd.getValue())) {
                trvlList.add(dtlPMsg);
            }
        }

        if (lborList.size() > 0) {
            // add start 2016/06/07 CSA Defect#3727
            String ctryCd = null;
            String postCd = null;
            Map<String, Object> shipToCust = getShipToCust(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
            if (shipToCust != null) {
                ctryCd = (String) shipToCust.get("CTRY_CD");
                postCd = (String) shipToCust.get("POST_CD");
            }
            // add end 2016/06/07 CSA Defect#3727

            Collections.sort(lborList, new Comparator<NSZC070001_xxLaborListPMsg>() {
                public int compare(NSZC070001_xxLaborListPMsg line1, NSZC070001_xxLaborListPMsg line2) {
                    int compared;
                    // mod start 2016/02/22 CSA Defect#2695
                    String svcTmEventFromDt1 = line1.svcTmEventFromDt.getValue();
                    if (!hasValue(line1.svcTmEventFromDt)) {
                        svcTmEventFromDt1 = MAX_DT;
                    }
                    String svcTmEventFromDt2 = line2.svcTmEventFromDt.getValue();
                    if (!hasValue(line2.svcTmEventFromDt)) {
                        svcTmEventFromDt2 = MAX_DT;
                    }
                    compared = svcTmEventFromDt1.compareTo(svcTmEventFromDt2);
                    if (compared != 0) {
                        return compared;
                    }

                    String svcTmEventFromTm1 = line1.svcTmEventFromTm.getValue();
                    if (!hasValue(line1.svcTmEventFromTm)) {
                        svcTmEventFromTm1 = MAX_TM;
                    }
                    String svcTmEventFromTm2 = line2.svcTmEventFromTm.getValue();
                    if (!hasValue(line2.svcTmEventFromTm)) {
                        svcTmEventFromTm2 = MAX_TM;
                    }
                    compared = svcTmEventFromTm1.compareTo(svcTmEventFromTm2);
                    if (compared != 0) {
                        return compared;
                    }
                    // mod end 2016/02/22 CSA Defect#2695
                    return 0;
                }
            });

            this.arvDt = lborList.get(0).svcTmEventFromDt.getValue();
            this.arvTm = lborList.get(0).svcTmEventFromTm.getValue();

            // mod start 2016/06/07 CSA Defect#3727
            if (this.arvDt != null && this.arvTm != null) {
                SvcTimeZoneInfo cnvTzArv = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, this.arvDt + this.arvTm, ctryCd, postCd);
                if (cnvTzArv != null) {
                    String cnvTsArv = cnvTzArv.getDateTime();
                    if (cnvTsArv != null) {
                        this.arvDt = S21StringUtil.subStringByLength(cnvTsArv, DATE_START_POS, DATE_END_POS);
                        this.arvTm = S21StringUtil.subStringByLength(cnvTsArv, TIME_START_POS, TIME_END_POS);
                    }
                }
            }
            // mod end 2016/06/07 CSA Defect#3727

            Collections.sort(lborList, new Comparator<NSZC070001_xxLaborListPMsg>() {
                public int compare(NSZC070001_xxLaborListPMsg line1, NSZC070001_xxLaborListPMsg line2) {
                    int compared;
                    compared = line1.svcTmEventToDt.getValue().compareTo(line2.svcTmEventToDt.getValue());
                    if (compared != 0) {
                        return compared;
                    }
                    compared = line1.svcTmEventToTm.getValue().compareTo(line2.svcTmEventToTm.getValue());
                    if (compared != 0) {
                        return compared;
                    }
                    return 0;
                }
            });

            this.cpltDt = lborList.get(lborList.size() - 1).svcTmEventToDt.getValue();
            this.cpltTm = lborList.get(lborList.size() - 1).svcTmEventToTm.getValue();

            // mod start 2016/06/07 CSA Defect#3727
            if (this.cpltDt != null && this.cpltTm != null) {
                SvcTimeZoneInfo cnvTzCplt = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, this.cpltDt + this.cpltTm, ctryCd, postCd);
                if (cnvTzCplt != null) {
                    String cnvTsCplt = cnvTzCplt.getDateTime();
                    if (cnvTsCplt != null) {
                        this.cpltDt = S21StringUtil.subStringByLength(cnvTsCplt, DATE_START_POS, DATE_END_POS);
                        this.cpltTm = S21StringUtil.subStringByLength(cnvTsCplt, TIME_START_POS, TIME_END_POS);
                    }
                }
            }
            // mod end 2016/06/07 CSA Defect#3727

            for (int i = 0; i < lborList.size(); i++) {
                this.lborTmNum = this.lborTmNum.add(lborList.get(i).durnTmNum.getValue());
            }
            // START  02/01/2017 [QC#17291, ADD]
            if (this.lborTmNum.compareTo(TM_NUM_MAX_VALUE) > 0) {
                this.lborTmNum = TM_NUM_MAX_VALUE;
            }
            // END    02/01/2017 [QC#17291, ADD]

            // START  02/16/2016 T.Iwamoto [QC#4135, DEL]
//            this.lborTmNum = this.lborTmNum.multiply(TM_VAL);
            // END  02/16/2016 T.Iwamoto [QC#4135, DEL]
       } else {
            this.arvDt = fsrVisitTMsg.fsrVisitArvDt.getValue();
            this.arvTm = fsrVisitTMsg.fsrVisitArvTm.getValue();
            this.cpltDt = fsrVisitTMsg.fsrVisitCpltDt.getValue();
            this.cpltTm = fsrVisitTMsg.fsrVisitCpltTm.getValue();
        }

        for (int i = 0; i < trvlList.size(); i++) {
            this.trvlTmNum = this.trvlTmNum.add(trvlList.get(i).durnTmNum.getValue());
        }
        // START  02/01/2017 [QC#17291, ADD]
        if (this.trvlTmNum.compareTo(TM_NUM_MAX_VALUE) > 0) {
            this.trvlTmNum = TM_NUM_MAX_VALUE;
        }
        // END    02/01/2017 [QC#17291, ADD]

        // START  02/16/2016 T.Iwamoto [QC#4135, DEL]
//        this.trvlTmNum = this.trvlTmNum.multiply(TM_VAL);
        // END  02/16/2016 T.Iwamoto [QC#4135, DEL]
    }

    // START  2015/12/16 T.Tomita [QC#978, DEL]
//    private void getDtTmCharges(S21ApiMessageMap msgMap) {
//        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();
//
//        for (int i = 0; i < pMsg.xxLaborList.getValidCount(); i++) {
//            NSZC070001_xxLaborListPMsg dtlPMsg = (NSZC070001_xxLaborListPMsg) pMsg.xxLaborList.no(i).clone();
//            if (MDSE_ITEM_BILL_TP.LABOR.equals(dtlPMsg.mdseItemBillTpCd.getValue())) {
//                lborList.add(dtlPMsg);
//            } else if (MDSE_ITEM_BILL_TP.TRAVEL.equals(dtlPMsg.mdseItemBillTpCd.getValue())) {
//                trvlList.add(dtlPMsg);
//            }
//        }
//
//        for (int i = 0; i < lborList.size(); i++) {
//            this.lborTmNum = this.lborTmNum.add(lborList.get(i).durnTmNum.getValue());
//        }
//        this.lborTmNum = this.lborTmNum.multiply(TM_VAL);
//        for (int i = 0; i < trvlList.size(); i++) {
//            this.trvlTmNum = this.trvlTmNum.add(trvlList.get(i).durnTmNum.getValue());
//        }
//        this.trvlTmNum = this.trvlTmNum.multiply(TM_VAL);
//    }
    // END 2015/12/16 T.Tomita [QC#978, DEL]

    private boolean callSvcDiptCpltApiDebrief(S21ApiMessageMap msgMap, FSR_VISITTMsg fsrVisitTMsg, SVC_TASKTMsg svcTaskTMsg, FSRTMsg fsrTMsg, TECH_LOCTMsg techLocTMsg) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();
        NSZC005001PMsg apiPMsg = new NSZC005001PMsg();

        // Set Visit Header Parameter
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // START 2015/12/16 T.Tomita [QC#978, ADD]
        setValue(apiPMsg.xxModeCd, MODE_CD_DEBRIEF);
        // END 2015/12/16 T.Tomita [QC#978, ADD]
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.userId, pMsg.usrId);
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        setValue(apiPMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.fsrVisitArvDt, this.arvDt);
        setValue(apiPMsg.fsrVisitArvTm, this.arvTm);
        setValue(apiPMsg.fsrVisitCpltDt, this.cpltDt);
        setValue(apiPMsg.fsrVisitCpltTm, this.cpltTm);
        setValue(apiPMsg.svcTrvlTmNum, this.trvlTmNum);
        setValue(apiPMsg.invCcyCd, svcTaskTMsg.invCcyCd);
        setValue(apiPMsg.ccyExchRate, svcTaskTMsg.ccyExchRate);
        setValue(apiPMsg.svcChrgContFlg, FLG_OFF_N);
        setValue(apiPMsg.svcBillTpCd, svcTaskTMsg.svcBillTpCd);
        setValue(apiPMsg.pmtTermCashDiscCd, fsrTMsg.pmtTermCashDiscCd);
        setValue(apiPMsg.svcLborTpCd, pMsg.svcLborTpCd);
        setValue(apiPMsg.iwrStsCd, pMsg.iwrStsCd);
        // START 2018/05/14 M.Naito [QC#23898, ADD]
        setValue(apiPMsg.ittNum, pMsg.ittNum);
        // END 2018/05/14 M.Naito [QC#23898, ADD]


        // Set Visit for Task Parameter
        setValue(apiPMsg.xxVisitTaskList.no(0).svcTaskNum, pMsg.svcTaskNum);
        setValue(apiPMsg.xxVisitTaskList.no(0).svcPblmTpCd, pMsg.svcPblmTpCd);
        setValue(apiPMsg.xxVisitTaskList.no(0).svcPblmLocTpCd, pMsg.svcPblmLocTpCd);
        setValue(apiPMsg.xxVisitTaskList.no(0).svcPblmRsnTpCd, pMsg.svcPblmRsnTpCd);
        setValue(apiPMsg.xxVisitTaskList.no(0).svcPblmCrctTpCd, pMsg.svcPblmCrctTpCd);
        setValue(apiPMsg.xxVisitTaskList.no(0).svcLborTmNum, this.lborTmNum);
        setValue(apiPMsg.xxVisitTaskList.no(0).xxVisitTaskCpltFlg, FLG_ON_Y);
        apiPMsg.xxVisitTaskList.setValidCount(1);

        // add start 2016/06/03 CSA Defect#3727
        String ctryCd = null;
        String postCd = null;
        Map<String, Object> shipToCust = getShipToCust(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (shipToCust != null) {
            ctryCd = (String) shipToCust.get("CTRY_CD");
            postCd = (String) shipToCust.get("POST_CD");
        }
        // add end 2016/06/03 CSA Defect#3727

        // Set Parts Usage Parameter
        for (int i = 0; i < pMsg.xxPartsUsageList.getValidCount(); i++) {
            NSZC070001_xxPartsUsageListPMsg dtlPMsg = pMsg.xxPartsUsageList.no(i);
            setValue(apiPMsg.xxFsrUsgList.no(i).fsrUsgPk, dtlPMsg.fsrUsgPk);
            setValue(apiPMsg.xxFsrUsgList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
            setValue(apiPMsg.xxFsrUsgList.no(i).dsSvcCallTpCd, svcTaskTMsg.dsSvcCallTpCd);
            // mod start 2016/04/14 CSA Defect#7017
            if (techLocTMsg != null) {
                setValue(apiPMsg.xxFsrUsgList.no(i).prtUsedByTechLocCd, techLocTMsg.techLocCd);
            }
            // mod end 2016/04/14 CSA Defect#7017
            setValue(apiPMsg.xxFsrUsgList.no(i).prtUsedByTechCd, fsrVisitTMsg.techCd);
            setValue(apiPMsg.xxFsrUsgList.no(i).mdseCd, dtlPMsg.mdseCd);
            setValue(apiPMsg.xxFsrUsgList.no(i).mdseNm, dtlPMsg.mdseNm);
            setValue(apiPMsg.xxFsrUsgList.no(i).svcPrtQty, dtlPMsg.svcPrtQty);
            setValue(apiPMsg.xxFsrUsgList.no(i).uomCd, dtlPMsg.pkgUomCd);
            setValue(apiPMsg.xxFsrUsgList.no(i).svcExecDt, dtlPMsg.svcExecDt);
            setValue(apiPMsg.xxFsrUsgList.no(i).fsrUsgProcFlg, FLG_OFF_N);
            // START 2017/12/27 M.Naito [QC#18646, MOD]
            String svcInvtyExFlg = FLG_OFF_N;
            if (hasValue(dtlPMsg.svcInvtyExFlg)) {
                svcInvtyExFlg = dtlPMsg.svcInvtyExFlg.getValue();
            }
            setValue(apiPMsg.xxFsrUsgList.no(i).svcInvtyExFlg, svcInvtyExFlg);
            // END 2017/12/27 M.Naito [QC#18646, MOD]
            setValue(apiPMsg.xxFsrUsgList.no(i).svcModPlnId, dtlPMsg.svcModPlnId);
            setValue(apiPMsg.xxFsrUsgList.no(i).svcPrtCmntTxt, dtlPMsg.svcPrtCmntTxt);
            // add start 2016/01/07 CSA Defect#2554
            setValue(apiPMsg.xxFsrUsgList.no(i).serNumTxt, dtlPMsg.serNumTxt);
            setValue(apiPMsg.xxFsrUsgList.no(i).modItemTxt, dtlPMsg.modItemTxt);
            // add end 2016/01/07 CSA Defect#2554
        }
        apiPMsg.xxFsrUsgList.setValidCount(pMsg.xxPartsUsageList.getValidCount());

        // Set Time Event Parameter
        for (int i = 0; i < pMsg.xxLaborList.getValidCount(); i++) {
            NSZC070001_xxLaborListPMsg dtlPMsg = pMsg.xxLaborList.no(i);
            setValue(apiPMsg.xxTmEventList.no(i).fsrVisitTmEventPk, dtlPMsg.fsrVisitTmEventPk);
            setValue(apiPMsg.xxTmEventList.no(i).fsrNum, pMsg.fsrNum);
            setValue(apiPMsg.xxTmEventList.no(i).fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
            setValue(apiPMsg.xxTmEventList.no(i).mdseCd, dtlPMsg.mdseCd);
            if (MDSE_ITEM_BILL_TP.TRAVEL.equals(dtlPMsg.mdseItemBillTpCd.getValue())) {
                setValue(apiPMsg.xxTmEventList.no(i).svcTmEventCd, SVC_TM_EVENT.TRAVEL);
            // add start 2016/02/22 CSA Defect#2695
            } else if (isModification(pMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd.getValue())) {
                setValue(apiPMsg.xxTmEventList.no(i).svcTmEventCd, SVC_TM_EVENT.MODIFICATION);
            // add end 2016/02/22 CSA Defect#2695
            } else {
                setValue(apiPMsg.xxTmEventList.no(i).svcTmEventCd, SVC_TM_EVENT.LABOR);
            }
            setValue(apiPMsg.xxTmEventList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
            // mod start 2016/06/03 CSA Defect#3727
            if (hasValue(dtlPMsg.svcTmEventFromDt) && hasValue(dtlPMsg.svcTmEventFromTm)) {
                SvcTimeZoneInfo cnvTzSvcTmEventFrom = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, dtlPMsg.svcTmEventFromDt.getValue() + dtlPMsg.svcTmEventFromTm.getValue(), ctryCd, postCd);
                if (cnvTzSvcTmEventFrom != null) {
                    String cnvTsSvcTmEventFrom = cnvTzSvcTmEventFrom.getDateTime();
                    if (cnvTsSvcTmEventFrom != null) {
                        setValue(apiPMsg.xxTmEventList.no(i).svcTmEventFromDt, S21StringUtil.subStringByLength(cnvTsSvcTmEventFrom, DATE_START_POS, DATE_END_POS));
                        setValue(apiPMsg.xxTmEventList.no(i).svcTmEventFromTm,  S21StringUtil.subStringByLength(cnvTsSvcTmEventFrom, TIME_START_POS, TIME_END_POS));
                    }
                }
            }
//            setValue(apiPMsg.xxTmEventList.no(i).svcTmEventFromDt, dtlPMsg.svcTmEventFromDt);
//            setValue(apiPMsg.xxTmEventList.no(i).svcTmEventFromTm, dtlPMsg.svcTmEventFromTm);

            if (hasValue(dtlPMsg.svcTmEventToDt.getValue()) && hasValue(dtlPMsg.svcTmEventToTm.getValue())) {
                SvcTimeZoneInfo cnvTzSvcTmEventTo = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, dtlPMsg.svcTmEventToDt.getValue() + dtlPMsg.svcTmEventToTm.getValue(), ctryCd, postCd);
                if (cnvTzSvcTmEventTo != null) {
                    String cnvTsSvcTmEventTo = cnvTzSvcTmEventTo.getDateTime();
                    if (cnvTsSvcTmEventTo != null) {
                        setValue(apiPMsg.xxTmEventList.no(i).svcTmEventToDt, S21StringUtil.subStringByLength(cnvTsSvcTmEventTo, DATE_START_POS, DATE_END_POS));
                        setValue(apiPMsg.xxTmEventList.no(i).svcTmEventToTm, S21StringUtil.subStringByLength(cnvTsSvcTmEventTo, TIME_START_POS, TIME_END_POS));

                        // START 2023/11/30 R.Kurahashi [QC#62436, ADD]
                        if (isModification(pMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd.getValue())) {
                            setFsrVisitCplt(apiPMsg, cnvTsSvcTmEventTo);
                        }
                        // END 2023/11/30 R.Kurahashi [QC#62436, ADD]
                    }
                }
            }
//            setValue(apiPMsg.xxTmEventList.no(i).svcTmEventToDt, dtlPMsg.svcTmEventToDt);
//            setValue(apiPMsg.xxTmEventList.no(i).svcTmEventToTm, dtlPMsg.svcTmEventToTm);
            // mod end 2016/06/03 CSA Defect#3727
            setValue(apiPMsg.xxTmEventList.no(i).durnTmNum, dtlPMsg.durnTmNum);
            setValue(apiPMsg.xxTmEventList.no(i).machDownFlg, pMsg.machDownFlg);
            setValue(apiPMsg.xxTmEventList.no(i).svcModPlnId, dtlPMsg.svcModPlnId);
            setValue(apiPMsg.xxTmEventList.no(i).svcLborCmntTxt, dtlPMsg.svcLborCmntTxt);
            // add start 2016/01/07 CSA Defect#2554
            setValue(apiPMsg.xxTmEventList.no(i).serNumTxt, dtlPMsg.serNumTxt);
            setValue(apiPMsg.xxTmEventList.no(i).modItemTxt, dtlPMsg.modItemTxt);
            // add end 2016/01/07 CSA Defect#2554
        }
        apiPMsg.xxTmEventList.setValidCount(pMsg.xxLaborList.getValidCount());

        // Set Expense Parameter
        for (int i = 0; i < pMsg.xxExpenseList.getValidCount(); i++) {
            NSZC070001_xxExpenseListPMsg dtlPMsg = pMsg.xxExpenseList.no(i);
            setValue(apiPMsg.xxExpenseList.no(i).fsrExpPk, dtlPMsg.fsrExpPk);
            setValue(apiPMsg.xxExpenseList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
            setValue(apiPMsg.xxExpenseList.no(i).mdseCd, dtlPMsg.mdseCd);
            setValue(apiPMsg.xxExpenseList.no(i).svcExpQty, dtlPMsg.expQty);
            setValue(apiPMsg.xxExpenseList.no(i).uomCd, dtlPMsg.pkgUomCd);
            setValue(apiPMsg.xxExpenseList.no(i).svcExecDt, dtlPMsg.svcExecDt);
            MDSETMsg mdseTMsg = getMdse(pMsg.glblCmpyCd.getValue(), dtlPMsg.mdseCd.getValue());
            if (mdseTMsg != null) {
                setValue(apiPMsg.xxExpenseList.no(i).mdseNm, mdseTMsg.mdseNm);
            }
            setValue(apiPMsg.xxExpenseList.no(i).techCd, fsrVisitTMsg.techCd);
            if (hasValue(fsrVisitTMsg.techCd)) {
                TECH_MSTRTMsg techMstrTMsg = getTechMstr(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.techCd.getValue());
                if (techMstrTMsg != null) {
                    setValue(apiPMsg.xxExpenseList.no(i).techNm, techMstrTMsg.techNm);
                }
            }
            setValue(apiPMsg.xxExpenseList.no(i).svcExpCmntTxt, dtlPMsg.svcExpCmntTxt);
            // add start 2016/08/02 CSA Defect#12678
            setValue(apiPMsg.xxExpenseList.no(i).svcExpDealAmt, dtlPMsg.svcExpDealAmt);
            setValue(apiPMsg.xxExpenseList.no(i).svcExpUnitAmt, dtlPMsg.ovrdExpUnitAmt);
            // add end 2016/08/02 CSA Defect#12678
        }
        apiPMsg.xxExpenseList.setValidCount(pMsg.xxExpenseList.getValidCount());

        new NSZC005001().execute(apiPMsg, this.onBatTp);
        return checkErrMsg(msgMap, apiPMsg);
    }

    private boolean callSvcDiptCpltApiIstallCheck(S21ApiMessageMap msgMap, FSR_VISITTMsg fsrVisitTMsg, FSRTMsg fsrTMsg) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();
        NSZC005001PMsg apiPMsg = new NSZC005001PMsg();

        // Set Visit Header Parameter
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, MODE_CD_INSTALL_CHECK);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.userId, pMsg.usrId);
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        // START 2017/09/01 K.Kim [QC#20672, ADD]
        setValue(apiPMsg.istlCpltFlg, pMsg.istlCpltFlg);
        // END 2017/09/01 K.Kim [QC#20672, ADD]

        // Set Install Check Parameter
        for (int i = 0; i < pMsg.xxFsrIstlChkList.getValidCount(); i++) {
            NSZC070001_xxFsrIstlChkListPMsg dtlPMsg = pMsg.xxFsrIstlChkList.no(i);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).fsrIstlChkListPk, dtlPMsg.fsrIstlChkListPk);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).fsrNum, dtlPMsg.fsrNum);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).svcConfigMstrPk, dtlPMsg.svcConfigMstrPk);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).mdseCd, dtlPMsg.mdseCd);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).mdlId, dtlPMsg.mdlId);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).mdlNm, dtlPMsg.mdlNm);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).serNum, dtlPMsg.serNum);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).istlChkVerFlg, dtlPMsg.istlChkVerFlg);
            setValue(apiPMsg.xxFsrIstlChkList.no(i).crctSerNum, dtlPMsg.crctSerNum);
        }
        apiPMsg.xxFsrIstlChkList.setValidCount(pMsg.xxFsrIstlChkList.getValidCount());

        new NSZC005001().execute(apiPMsg, this.onBatTp);
        return checkErrMsg(msgMap, apiPMsg);
    }
    // START 2015/12/16 T.Tomita [QC#978, MOD]
    private boolean callSvcDiptCpltApiCharges(S21ApiMessageMap msgMap, FSR_VISITTMsg fsrVisitTMsg, FSRTMsg fsrTMsg) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        // add start 2016/02/25 CSA Defect#3991
        // Call Credit Card Validation API
        if (!callCreditCardValidationApi(msgMap, pMsg, fsrTMsg)) {
            return false;
        }

        // update po infomation
        if (!updatePoInfo(msgMap, pMsg)) {
            return false;
        }
        // add end 2016/02/25 CSA Defect#3991

        NSZC005001PMsg apiPMsg = new NSZC005001PMsg();

        // Set Visit Header Parameter
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, MODE_CD_CHARGES);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.userId, pMsg.usrId);
        setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        setValue(apiPMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
        setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(apiPMsg.invCcyCd, fsrTMsg.invCcyCd);

        // Set Visit for Task Parameter
        // Set Parts Usage Parameter
        // Set Time Event Parameter
        // Set Expense Parameter

        // Set Chargeable Parameter
        for (int i = 0; i < pMsg.xxChargeableList.getValidCount(); i++) {
            NSZC070001_xxChargeableListPMsg dtlPMsg = pMsg.xxChargeableList.no(i);
            setValue(apiPMsg.xxChargesList.no(i).fsrChrgDtlPk, dtlPMsg.fsrChrgDtlPk);
            setValue(apiPMsg.xxChargesList.no(i).fsrNum, dtlPMsg.fsrNum);
            setValue(apiPMsg.xxChargesList.no(i).fsrVisitNum, dtlPMsg.fsrVisitNum);
            setValue(apiPMsg.xxChargesList.no(i).svcTaskNum, dtlPMsg.svcTaskNum);
            setValue(apiPMsg.xxChargesList.no(i).svcChrgTrxTpCd, dtlPMsg.svcChrgTrxTpCd);
            setValue(apiPMsg.xxChargesList.no(i).mdseCd, dtlPMsg.mdseCd);
            setValue(apiPMsg.xxChargesList.no(i).svcChrgQty, dtlPMsg.svcChrgQty);
            setValue(apiPMsg.xxChargesList.no(i).uomCd, dtlPMsg.uomCd);
            setValue(apiPMsg.xxChargesList.no(i).svcChrgUnitAot, dtlPMsg.svcChrgUnitAot);
            setValue(apiPMsg.xxChargesList.no(i).svcChrgUnitAmt, dtlPMsg.svcChrgUnitAmt);
            setValue(apiPMsg.xxChargesList.no(i).svcChrgDealAmt, dtlPMsg.svcChrgDealAmt);
            apiPMsg.xxChargesList.no(i).svcChrgFuncAmt.clear();
            setValue(apiPMsg.xxChargesList.no(i).svcChrgDiscRate, dtlPMsg.svcChrgDiscRate);
            setValue(apiPMsg.xxChargesList.no(i).svcChrgDealDiscAmt, dtlPMsg.svcChrgDealDiscAmt);
            apiPMsg.xxChargesList.no(i).svcChrgFuncDiscAmt.clear();
            setValue(apiPMsg.xxChargesList.no(i).svcChrgFlg, dtlPMsg.svcChrgFlg);
            setValue(apiPMsg.xxChargesList.no(i).ovrdSvcChrgUnitAmt, dtlPMsg.ovrdSvcChrgUnitAmt);
            setValue(apiPMsg.xxChargesList.no(i).ovrdChngRsnCd, dtlPMsg.ovrdChngRsnCd);
            setValue(apiPMsg.xxChargesList.no(i).ovrdChngUsrId, dtlPMsg.ovrdChngUsrId);
            setValue(apiPMsg.xxChargesList.no(i).prcCatgCd, dtlPMsg.prcCatgCd);
            setValue(apiPMsg.xxChargesList.no(i).svcInvChrgTpCd, dtlPMsg.svcInvChrgTpCd);
        }
        apiPMsg.xxChargesList.setValidCount(pMsg.xxChargeableList.getValidCount());

        new NSZC005001().execute(apiPMsg, this.onBatTp);
        return checkErrMsg(msgMap, apiPMsg);
    }
    // END 2015/12/16 T.Tomita [QC#978, MOD]

    // START 2017/05/31 K.Kitachi [QC#18447, MOD]
//    private void insertSvcMemo(S21ApiMessageMap msgMap) {
    private void insertSvcMemo(S21ApiMessageMap msgMap, int svcMemoListIdx) {
        NSZC070001PMsg pMsg = (NSZC070001PMsg) msgMap.getPmsg();

        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
//        setValue(svcMemoTMsg.svcMemoTpCd, pMsg.svcMemoTpCd);
//        setValue(svcMemoTMsg.svcCmntTxt, pMsg.svcCmntTxt);
        setValue(svcMemoTMsg.svcMemoTpCd, pMsg.svcMemoList.no(svcMemoListIdx).svcMemoTpCd);
        setValue(svcMemoTMsg.svcCmntTxt, pMsg.svcMemoList.no(svcMemoListIdx).svcCmntTxt);
        setValue(svcMemoTMsg.svcMemoRsnCd, pMsg.svcMemoList.no(svcMemoListIdx).svcMemoRsnCd);
        setValue(svcMemoTMsg.svcTaskNum, pMsg.svcTaskNum);
        setValue(svcMemoTMsg.fsrNum, pMsg.fsrNum);
        setValue(svcMemoTMsg.lastUpdUsrId, pMsg.usrId);
        setValue(svcMemoTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TS));

        S21ApiTBLAccessor.create(svcMemoTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0475E);
        }
    }
    // END 2017/05/31 K.Kitachi [QC#18447, MOD]

    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg paramTMsg = new MDSETMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg paramTMsg = new SVC_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private FSRTMsg getFsr(String glblCmpyCd, String fsrNum) {
        FSRTMsg paramTMsg = new FSRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }
    private TECH_MSTRTMsg getTechMstr(String glblCmpyCd, String techTocCd) {
        TECH_MSTRTMsg paramTMsg = new TECH_MSTRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.techTocCd, techTocCd);
        return (TECH_MSTRTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private FSR_VISITTMsg getFsrVisit(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        FSR_VISITTMsgArray fsrVisitTMsgArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (fsrVisitTMsgArray == null || fsrVisitTMsgArray.getValidCount() == 0) {
            return null;
        }
        return (FSR_VISITTMsg) fsrVisitTMsgArray.no(0);
    }

    private TECH_LOCTMsg getTechLoc(String glblCmpyCd, String techCd) {
        TECH_LOCTMsg tMsg = new TECH_LOCTMsg();
        // MOD START 2019/08/14 K.Fujimoto QC#52546
        //tMsg.setSQLID("005");
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        //tMsg.setConditionValue("techTocCd01", techCd);
        tMsg.setConditionValue("techLocCd01", techCd + PART_USG_INVTY_LOC_SFX);
        tMsg.setConditionValue("prtyLocFlg01", FLG_ON_Y);
        // MOD END 2019/08/14 K.Fujimoto QC#52546
        TECH_LOCTMsgArray techLocTMsgArray = (TECH_LOCTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (techLocTMsgArray == null || techLocTMsgArray.getValidCount() == 0) {
            return null;
        }
        return (TECH_LOCTMsg) techLocTMsgArray.no(0);
    }

    private void checkMandatory(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean checkErrMsg(S21ApiMessageMap msgMap, EZDPMsg apiPMsg) {
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                msgMap.addXxMsgId(xxMsgId);
            }
            return false;
        }
        return true;
    }
    // add start 2016/02/22 CSA Defect#2695
    private boolean isModification(String glblCmpyCd, String mdseCd) {
        if (hasValue(mdseCd)) {
            String svcTmEventCd = getSvcTmEventCd(glblCmpyCd, mdseCd);
            if (SVC_TM_EVENT.MODIFICATION.equals(svcTmEventCd)) {
                return true;
            }
        }
        return false;
    }

    // add start 2016/02/22 CSA Defect#2695
    private String getSvcTmEventCd(String glblCmpyCd, String mdseCd) {
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("intgMdseCd01", mdseCd);
        SVC_TM_EVENTTMsgArray outArray = (SVC_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).svcTmEventCd.getValue();
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean callCreditCardValidationApi(S21ApiMessageMap msgMap, NSZC070001PMsg pMsg, FSRTMsg fsrTMsg) {
        // check target
        if (!checkTargetForCreditCard(msgMap, pMsg, fsrTMsg)) {
            return true;
        }

        // create or update DS_CR_CARD
        if (!execDsCrCard(msgMap, pMsg)) {
            return false;
        }

        // create or update CR_CARD_TRX
        if (!execCrCardtrx(msgMap, pMsg)) {
            return false;
        }
        return true;
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean checkTargetForCreditCard(S21ApiMessageMap msgMap, NSZC070001PMsg pMsg, FSRTMsg fsrTMsg) {
        if (!hasValue(fsrTMsg.pmtTermCashDiscCd.getValue())) {
            return false;
        }

        // add start 2016/09/28 CSA Defect#14251
        if (hasValue(pMsg.crCardCustRefNum)) {
            setValue(fsrTMsg.pmtTermCashDiscCd, PMT_TERM_CASH_DISC.CREDIT_CARD);
            S21ApiTBLAccessor.update(fsrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0171E);
                return false;
            }
        // add start 2017/01/20 CSA Defect#15092
        } else {
            return false;
        }
        // add end 2017/01/20 CSA Defect#15092
        // add end 2016/09/28 CSA Defect#14251

        PMT_TERM_CASH_DISCTMsg ptcdTMsg = new PMT_TERM_CASH_DISCTMsg();
        ptcdTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        ptcdTMsg.pmtTermCashDiscCd.setValue(fsrTMsg.pmtTermCashDiscCd.getValue());
        ptcdTMsg.ezCancelFlag.setValue(ZYPConstant.FLG_OFF_0);
        ptcdTMsg = (PMT_TERM_CASH_DISCTMsg) ZYPCodeDataUtil.findByKey(ptcdTMsg);
        if (ptcdTMsg == null) {
            return false;
        }
        return ZYPConstant.FLG_ON_Y.equals(ptcdTMsg.pmtCcFlg.getValue());
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean execDsCrCard(S21ApiMessageMap msgMap, NSZC070001PMsg pMsg) {
        if (!existsDsCrCard(pMsg)) {
            // get Account info
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
            if (hasValue(pMsg.svcMachMstrPk)) {
                svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            }
            String curLocAcctNum = null;
            if (svcMachMstrTMsg != null) {
                curLocAcctNum = svcMachMstrTMsg.curLocAcctNum.getValue();
            }

            // Call credit Card Validation API,
            NWZC203001PMsg inPMsg = new NWZC203001PMsg();
            setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_MSTR);
            setValue(inPMsg.slsDt, pMsg.slsDt);
            setValue(inPMsg.crCardCustRefNum, pMsg.crCardCustRefNum);
            setValue(inPMsg.sellToCustCd, curLocAcctNum);
            // mod start 2019/03/08 QC#30709
            //setValue(inPMsg.xxPmtcAcctNum, curLocAcctNum);
            String crCardLastDigitNum = pMsg.crCardLastDigitNum.getValue();
            if (!hasValue(crCardLastDigitNum)) {
                crCardLastDigitNum = ZYPCodeDataUtil.getVarCharConstValue("DUMMY_PMTC_ACCT_NUM", pMsg.glblCmpyCd.getValue());
            }
            setValue(inPMsg.xxPmtcAcctNum, crCardLastDigitNum);
            // mod end 2019/03/08 QC#30709
            setValue(inPMsg.xxPmtcExprDtTxt, pMsg.crCardExprYrMth.getValue());
            setValue(inPMsg.xxPmtcAvsNm, pMsg.crCardHldNm);
            setValue(inPMsg.crCardTpCd, pMsg.crCardTpCd);

            NWZC203001 nwzc020301API = new NWZC203001();
            nwzc020301API.execute(inPMsg, this.onBatTp);
            return checkErrMsg(msgMap, inPMsg);
        }
        return true;
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean existsDsCrCard(NSZC070001PMsg pMsg) {
        DS_CR_CARDTMsg inMsg = new DS_CR_CARDTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("crCardCustRefNum01", pMsg.crCardCustRefNum.getValue());
        DS_CR_CARDTMsgArray outArray = (DS_CR_CARDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean execCrCardtrx(S21ApiMessageMap msgMap, NSZC070001PMsg pMsg) {

        // get CrCardtrx
        CR_CARD_TRXTMsgArray cctTMsgArryay = getCrCardtrx(pMsg);

        // Cancel CrCardtrx
        for (int i = 0; i < cctTMsgArryay.getValidCount(); i++) {
            // Call credit Card Validation API,
            NWZC203001PMsg inPMsg = new NWZC203001PMsg();
            CR_CARD_TRXTMsg cctTMsg = cctTMsgArryay.no(i);

            // copy
            EZDMsg.copy(cctTMsg, null, inPMsg, null);
            // set parameter
            setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(inPMsg.slsDt, pMsg.slsDt);
            setValue(inPMsg.sellToCustCd, cctTMsg.billToCustAcctCd);
            setValue(inPMsg.crCardCancDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
            setValue(inPMsg.crCardTrxTpCd, CR_CARD_TRX_TP.SERVICE_REQUEST);
            setValue(inPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.VOID_COMPLETED);
            setValue(inPMsg.firstTrxInfoTxt, pMsg.fsrNum);
            setValue(inPMsg.xxPmtcProcStsCd, cctTMsg.crCardTrxProcStsCd);
            setValue(inPMsg.xxPmtcApvlStsNum, cctTMsg.crCardTrxApvlStsCd);
            setValue(inPMsg.xxPmtcTrxRefIdxCd, cctTMsg.crCardRefIdxNum);

            NWZC203001 nwzc020301API = new NWZC203001();
            nwzc020301API.execute(inPMsg, this.onBatTp);
            if (!checkErrMsg(msgMap, inPMsg)) {
                return false;
            }
        }

        // get Account info
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        if (hasValue(pMsg.svcMachMstrPk)) {
            svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        }
        String curLocAcctNum = null;
        if (svcMachMstrTMsg != null) {
            curLocAcctNum = svcMachMstrTMsg.curLocAcctNum.getValue();
        }
        // Insert CrCardtrx
        NWZC203001PMsg inPMsg = new NWZC203001PMsg();
        setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
        setValue(inPMsg.slsDt, pMsg.slsDt);
        setValue(inPMsg.crCardCustRefNum, pMsg.crCardCustRefNum);
        setValue(inPMsg.crCardAuthRefNum, pMsg.crCardAuthRefNum);
        setValue(inPMsg.sellToCustCd, curLocAcctNum);
        setValue(inPMsg.crCardAuthAmt, pMsg.crCardAuthAmt);
        setValue(inPMsg.crCardTrxTpCd, CR_CARD_TRX_TP.SERVICE_REQUEST);
        setValue(inPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);
        setValue(inPMsg.firstTrxInfoTxt, pMsg.fsrNum);

        NWZC203001 nwzc020301API = new NWZC203001();
        nwzc020301API.execute(inPMsg, this.onBatTp);
        return checkErrMsg(msgMap, inPMsg);
    }

    // add start 2016/02/25 CSA Defect#3991, 2016/07/21 CSA Defect#12154
    private CR_CARD_TRXTMsgArray getCrCardtrx(NSZC070001PMsg pMsg) {
        CR_CARD_TRXTMsg inMsg = new CR_CARD_TRXTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("firstTrxInfoTxt01", pMsg.fsrNum.getValue());
        inMsg.setConditionValue("crCardAuthStsCd01", CR_CARD_AUTH_STS.SAVED);
        inMsg.setConditionValue("crCardTrxTpCd01", CR_CARD_TRX_TP.SERVICE_REQUEST);
        return (CR_CARD_TRXTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // add start 2016/02/25 CSA Defect#3991
    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg paramTMsg = new SVC_MACH_MSTRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean updatePoInfo(S21ApiMessageMap msgMap, NSZC070001PMsg pMsg) {
        if (hasValue(pMsg.custPoNum) || hasValue(pMsg.custPoDt)) {
            SVC_TASKTMsg svcTaskTMsg = getSvcTaskForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
            if (svcTaskTMsg == null) {
                msgMap.addXxMsgId(NSZM0079E);
                return false;
            }
            setValue(svcTaskTMsg.custPoNum, pMsg.custPoNum);
            setValue(svcTaskTMsg.custPoDt, pMsg.custPoDt);
            S21ApiTBLAccessor.update(svcTaskTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0080E);
                return false;
            }

        }
        if (hasValue(pMsg.poVerFlg)) {
            FSRTMsg fsrTMsg = getFsrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
            if (fsrTMsg == null) {
                msgMap.addXxMsgId(NSZM0181E);
                return false;
            }
            setValue(fsrTMsg.poVerFlg, pMsg.poVerFlg);
            S21ApiTBLAccessor.update(fsrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0171E);
                return false;
            }
        }
        return true;
    }

    // add start 2016/02/25 CSA Defect#3991
    private SVC_TASKTMsg getSvcTaskForUpdate(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg paramTMsg = new SVC_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    // add start 2016/02/25 CSA Defect#3991
    private FSRTMsg getFsrForUpdate(String glblCmpyCd, String fsrNum) {
        FSRTMsg paramTMsg = new FSRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    // add start 2016/06/03 CSA Defect#3727
    private Map<String, Object> getShipToCust(String glblCmpyCd, String fsrNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("fsrNum", fsrNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getShipToCust", param);
    }

    // add start 2016/06/16 CSA Defect#9677
    private void callSendTaskInfoApi(S21ApiMessageMap msgMap, NSZC070001PMsg pMsg) {
        List<NSZC107001PMsg> inPMsgList = new ArrayList<NSZC107001PMsg>();
        List<String> taskList = getSendTaskList(pMsg);
        for (String svcTaskNum : taskList) {
            NSZC107001PMsg inPMsg = new NSZC107001PMsg();
            setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inPMsg.slsDt, pMsg.slsDt);
            setValue(inPMsg.svcTaskNum, svcTaskNum);
            inPMsgList.add(inPMsg);
        }

        NSZC107001 nszc107001API = new NSZC107001();
        nszc107001API.execute(inPMsgList, this.onBatTp);
        for (NSZC107001PMsg inPMsg : inPMsgList) {
            if (S21ApiUtil.isXxMsgId(inPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(inPMsg);
                for (S21ApiMessage msg : msgList) {
                    if (hasValue(msg.getXxMsgid()) && msg.getXxMsgid().endsWith("E")) {
                        msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                }
            }
        }
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private List<String> getSendTaskList(NSZC070001PMsg pMsg) {
        List<String> taskList = new ArrayList<String>();
        if (hasValue(pMsg.svcTaskNum)) {
            taskList.add(pMsg.svcTaskNum.getValue());
        }

        if (MODE_DEBRIEF.equals(pMsg.xxModeCd.getValue())) {
            String follUpTaskNum = getFollUpTask(pMsg);
            if (hasValue(follUpTaskNum)) {
                // add start 2019/10/16 K.Fujimoto [QC#54070,MOD]
                // taskList.add(follUpTaskNum);
                taskList.add(0, follUpTaskNum);
                // add end   2019/10/16 K.Fujimoto [QC#54070,MOD]
            }

        } else if (MODE_CHARGES.equals(pMsg.xxModeCd.getValue())) {
            List<String> closeTaskList = getCloseTaskList(pMsg);
            for (String svcTaskNum : closeTaskList) {
                taskList.add(svcTaskNum);
            }
        }

        return taskList;
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private String getFollUpTask(NSZC070001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        return (String) ssmBatchClient.queryObject("getFollUpTask", paramMap);
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private List<String> getCloseTaskList(NSZC070001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        if (hasValue(pMsg.svcTaskNum)) {
            paramMap.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        }
        paramMap.put("svcTaskStsCd", SVC_TASK_STS.CLOSED);
        return (List<String>) ssmBatchClient.queryObjectList("getCloseTaskList", paramMap);
    }
    // add end 2016/06/16 CSA Defect#9677
    
    // START 2023/11/30 R.Kurahashi [QC#62436, ADD]
    private void setFsrVisitCplt(NSZC005001PMsg apiPMsg, String svcTmEventTo) {
        SimpleDateFormat sdfDt = new SimpleDateFormat(FORMAT_Dt);
        SimpleDateFormat sdfTs = new SimpleDateFormat(FORMAT_TS);
        String fsrVisitCpltDt = apiPMsg.fsrVisitCpltDt.getValue();
        String fsrVisitCpltTm = apiPMsg.fsrVisitCpltTm.getValue();

        if (hasValue(fsrVisitCpltDt) && hasValue(fsrVisitCpltTm)) {
            try {
                if (sdfTs.parse(svcTmEventTo).compareTo(sdfDt.parse(fsrVisitCpltDt + fsrVisitCpltTm)) > 0) {
                    setValue(apiPMsg.fsrVisitCpltDt, S21StringUtil.subStringByLength(svcTmEventTo, DATE_START_POS, DATE_END_POS));
                    setValue(apiPMsg.fsrVisitCpltTm, S21StringUtil.subStringByLength(svcTmEventTo, TIME_START_POS, TIME_END_POS));
                }
            } catch (ParseException e) {
                return;
            }
        }
        return;
    }
    // END 2023/11/30 R.Kurahashi [QC#62436, ADD]

}

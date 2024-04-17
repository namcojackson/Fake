/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0400;

import static business.blap.NSAL0400.constant.NSAL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0400.common.NSAL0400CommonLogic;

import business.blap.NSAL0400.constant.NSAL0400Constant.MSG_PRM;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_TRKTMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxSvcMemoListPMsg;
import business.parts.NWZC194001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NWZ.NWZC194001.NWZC194001;
import com.canon.cusa.s21.api.NWZ.NWZC194001.constant.NWZC194001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         M.Yamada        Create          N/A
 * 2016/01/17   Hitachi         K.Yamada        Update          CSA Modify
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3691
 * 2016/06/02   Hitachi         T.Tomita        Update          QC1523, 4624
 * 2016/07/12   Hitachi         T.Kanasaka      Update          QC#11027
 * 2016/08/01   Hitachi         A.Kohinata      Update          QC#2853
 * 2017/02/28   Hitachi         K.Kishimoto     Update          QC#17809
 * 2017/07/03   Hitachi         A.Kohinata      Update          QC#18349
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 * 2018/02/22   CITS            M.Naito         Update          QC#23179
 * 2018/05/28   Hitachi         U.Kim           Update          QC#25933
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2023/05/29   CITS            T.Kojima        Update          QC#61529
 *</pre>
 */
public class NSAL0400BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        //        cMsg.setCommitSMsg(true);

        String screenAplId = cMsg.getScreenAplID();

        try {

            if (screenAplId.startsWith("NSAL0400Scrn00")) {
                //NSAL0400CommonLogic.updateSMsg((NSAL0400CMsg) cMsg, (NSAL0400SMsg) sMsg);
                if (screenAplId.endsWith("_Submit")) {
                    doProcess_NSAL0400Scrn00_CMN_Submit((NSAL0400CMsg) cMsg);

                }
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
            return;

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0400Scrn00_CMN_Submit(NSAL0400CMsg cMsg) {

        // START 2023/05/29 T.Kojima [QC#61529, ADD]
        NSAL0400Query query = NSAL0400Query.getInstance();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0400_BCMsg bcMsg = cMsg.B.no(i);
            int crCnt = query.countCreditRebill(bcMsg.dsContrPk_B.getValue());
            if (crCnt > 0) {
                cMsg.setMessageInfo("NSAM0777E");
                return;
            }
        }
        // END 2023/05/29 T.Kojima [QC#61529, ADD]

        // Check Box
        final List<Integer> targetList = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_AD", "Y");
        if (targetList == null || targetList.size() == 0) {
            //Please select at least 1 check box.
            cMsg.setMessageInfo(NSAM0015E);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).xxChkBox_AD.setErrorInfo(1, NSAM0015E);
            }
            return;
        }

        // START 2022/02/04 K.Kitachi [QC#59684, DEL]
//        NSAL0400CommonLogic.setContrHdrInfoToDtl(cMsg);
        // END 2022/02/04 K.Kitachi [QC#59684, DEL]

        boolean hasError = false;
        // Service Memo Reason Code
        if (!hasValue(cMsg.svcMemoRsnCd_FS)) {
            cMsg.svcMemoRsnCd_FS.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.REASON_CODE.getMsgPrm()) });
            hasError = true;
        }

        // Service Comment Text
        if (!hasValue(cMsg.svcCmntTxt_F)) {
            cMsg.svcCmntTxt_F.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.COMMENT.getMsgPrm()) });
            hasError = true;
        }
        if (hasError) {
            return;
        }

        // START 2022/02/04 K.Kitachi [QC#59684, DEL]
//        if (NSAL0400CommonLogic.hasErrDtlOfReview(cMsg, targetList)) {
//            return;
//        }
        // END 2022/02/04 K.Kitachi [QC#59684, DEL]

        // START 2018/05/28 U.Kim [QC#25933, ADD]
        hasError = false;
        if (!NSAL0400CommonLogic.checkAccessoryEndDate(cMsg, targetList)){
            hasError = true;
        }
        if (!NSAL0400CommonLogic.checkMachineEndDate(cMsg, targetList)){
            hasError = true;
        }
        if (hasError) {
            return;
        }
        // END 2018/05/28 U.Kim [QC#25933, ADD]

        for (int rowIndex : targetList) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(rowIndex);
            // mod start 2017/07/03 QC#18349
            NSAL0400CommonLogic.calcCreditAmt(cMsg, acMsg);
            // mod end 2017/07/03 QC#18349
        }

        if (NSAL0400CommonLogic.hasErrDtlOfSubmit(cMsg, targetList)) {
            return;
        }

        // add start 2016/08/01 CSA Defect#2853
        if (NSAL0400CommonLogic.hasWarningDtlOfSubmit(cMsg, targetList)) {
            return;
        }
        // add end 2016/08/01 CSA Defect#2853

        if (!terminateContract(cMsg, targetList)) {
            cMsg.setMessageInfo(NSAM0394W);
        } else {
            cMsg.setMessageInfo(NZZM0002I);
        }

    }

    private boolean terminateContract(NSAL0400CMsg cMsg, List<Integer> targetList) {

        boolean result = true;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0400_BCMsg bcMsg = cMsg.B.no(i);

            List<NSAL0400_ACMsg> terminationList = getTerminationList(cMsg, bcMsg.dsContrPk_B.getValue()
                    , targetList);
            if (terminationList == null || terminationList.isEmpty()) {
                continue;
            }

            if (!callContrUpdateApi(cMsg, bcMsg, terminationList)) {
                result = false;
                EZDConnectionMgr.getInstance().rollback();
            } else {
                EZDConnectionMgr.getInstance().commit();
            }
        }

        return result;
    }

    private List<NSAL0400_ACMsg> getTerminationList(NSAL0400CMsg cMsg, BigDecimal dsContrPk
            , List<Integer> targetList) {

        List<NSAL0400_ACMsg> terminationList = new ArrayList<NSAL0400_ACMsg>();
        for (int rowIndex : targetList) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(rowIndex);
            if (dsContrPk.compareTo(acMsg.dsContrPk_AH.getValue()) == 0
                    && FLG_ON_Y.equals(acMsg.xxChkBox_AD.getValue())) {
                terminationList.add(acMsg);
            }
        }
        return terminationList;
    }

    private boolean callContrUpdateApi(NSAL0400CMsg cMsg, NSAL0400_BCMsg bcMsg
            , List<NSAL0400_ACMsg> terminationList) {

        NSZC046001PMsg pMsg = new NSZC046001PMsg();
        //header
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, TERM_MODE);
        setValue(pMsg.slsDt, cMsg.slsDt);
        setValue(pMsg.dsContrSrcTpCd, DS_CONTR_SRC_TP.CONTRACT_MAINTENACE);
        setValue(pMsg.psnCd, getContextUserInfo().getUserId());
        setValue(pMsg.dsContrPk, bcMsg.dsContrPk_B);
        setValue(pMsg.dsContrNum, bcMsg.dsContrNum_B);
        // Add Start 2017/02/28 <QC#17809>
        setValue(pMsg.dsContrCatgCd, bcMsg.dsContrCatgCd_B);
        // Add End   2017/02/28 <QC#17809>

        int dtlCnt = terminationList.size();
        String currentTs = ZYPDateUtil.getCurrentSystemTime(TS_FMT);
        String userId = getContextUserInfo().getUserId();

        for (int i = 0; i < dtlCnt; i++) {
            NSAL0400_ACMsg acMsg = terminationList.get(i);
            //detail
            setValue(pMsg.xxContrDtlList.no(i).dsContrDtlPk, acMsg.dsContrDtlPk_AD);
            // START 2018/02/22 M.Naito [QC23179, MOD]
            // Mod Start 2017/11/20 QC#21724
//            setValue(pMsg.xxContrDtlList.no(i).contrCloDt, NSAL0400CommonLogic.addCloDt(acMsg.contrCloDt_AD.getValue(), -1));
            // Mod End 2017/11/20 QC#21724
            setValue(pMsg.xxContrDtlList.no(i).contrCloDt, acMsg.contrCloDt_AD);
            // END 2018/02/22 M.Naito [QC23179, MOD]
            setValue(pMsg.xxContrDtlList.no(i).trmnTotAmt, acMsg.trmnTotAmt_AD);
            setValue(pMsg.xxContrDtlList.no(i).trmnOvrdTotAmt, acMsg.trmnOvrdTotAmt_AD);
            if (hasValue(acMsg.supprCrFlg_AD)) {
                setValue(pMsg.xxContrDtlList.no(i).supprCrFlg, acMsg.supprCrFlg_AD);
            } else {
                setValue(pMsg.xxContrDtlList.no(i).supprCrFlg, FLG_OFF_N);
            }
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue())) {
                setValue(pMsg.xxContrDtlList.no(i).manTrmnTpCd, MAN_TRMN_TP.ALL_PERIOD);
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]

            //service memo
            setValue(pMsg.xxSvcMemoList.no(i).xxModeCd, "C");
            setValue(pMsg.xxSvcMemoList.no(i).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            setValue(pMsg.xxSvcMemoList.no(i).svcMemoTpCd, SVC_MEMO_TP.TERMINATION_NOTE);
            setValue(pMsg.xxSvcMemoList.no(i).svcCmntTxt, cMsg.svcCmntTxt_F);
            setValue(pMsg.xxSvcMemoList.no(i).dsContrPk, acMsg.dsContrPk_AH);
            setValue(pMsg.xxSvcMemoList.no(i).dsContrDtlPk, acMsg.dsContrDtlPk_AD);
            setValue(pMsg.xxSvcMemoList.no(i).lastUpdUsrId, userId);
            setValue(pMsg.xxSvcMemoList.no(i).lastUpdTs, currentTs);
            setValue(pMsg.xxSvcMemoList.no(i).svcMemoRsnCd, cMsg.svcMemoRsnCd_FS);
        }

        if (DS_CONTR_CATG.FLEET.equals(bcMsg.dsContrCatgCd_B.getValue())
                && NSAL0400CommonLogic.isAllTermination(cMsg, bcMsg.dsContrPk_B.getValue())) {
            int fleetLineIdx = dtlCnt;
            dtlCnt++;

            DS_CONTR_DTLTMsg dsContrDtl = NSAL0400CommonLogic.getFleetLine(cMsg, bcMsg.dsContrPk_B.getValue());
            setValue(pMsg.xxContrDtlList.no(fleetLineIdx).dsContrDtlPk, dsContrDtl.dsContrDtlPk);
            // START 2018/02/22 M.Naito [QC23179, MOD]
            // Mod Start 2017/11/20 QC#21724
//            setValue(pMsg.xxContrDtlList.no(fleetLineIdx).contrCloDt, NSAL0400CommonLogic.addCloDt(bcMsg.contrCloDt_B.getValue(), -1));
            // Mod End 2017/11/20 QC#21724
            setValue(pMsg.xxContrDtlList.no(fleetLineIdx).contrCloDt, bcMsg.contrCloDt_B);
            // END 2018/02/22 M.Naito [QC23179, MOD]
            setValue(pMsg.xxContrDtlList.no(fleetLineIdx).trmnTotAmt, bcMsg.trmnTotAmt_B);
            setValue(pMsg.xxContrDtlList.no(fleetLineIdx).trmnOvrdTotAmt, bcMsg.trmnOvrdTotAmt_B);
            if (hasValue(bcMsg.supprCrFlg_B)) {
                setValue(pMsg.xxContrDtlList.no(fleetLineIdx).supprCrFlg, bcMsg.supprCrFlg_B);
            } else {
                setValue(pMsg.xxContrDtlList.no(fleetLineIdx).supprCrFlg, FLG_OFF_N);
            }
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (FLG_ON_Y.equals(bcMsg.contrTrmnFlg_B.getValue())) {
                setValue(pMsg.xxContrDtlList.no(fleetLineIdx).manTrmnTpCd, MAN_TRMN_TP.ALL_PERIOD);
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]

            //service memo
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).xxModeCd, "C");
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).svcMemoTpCd, SVC_MEMO_TP.TERMINATION_NOTE);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).svcCmntTxt, cMsg.svcCmntTxt_F);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).dsContrPk, bcMsg.dsContrPk_B);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).dsContrDtlPk, dsContrDtl.dsContrDtlPk);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).lastUpdUsrId, userId);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).lastUpdTs, currentTs);
            setValue(pMsg.xxSvcMemoList.no(fleetLineIdx).svcMemoRsnCd, cMsg.svcMemoRsnCd_FS);
        }
        // START 2016/02/15 T.Aoyagi [QC3691, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(bcMsg.dsContrCatgCd_B.getValue())
                && NSAL0400CommonLogic.isAllTermination(cMsg, bcMsg.dsContrPk_B.getValue())) {
            int aggLineIdx = dtlCnt;
            dtlCnt++;

            DS_CONTR_DTLTMsg dsContrDtl = NSAL0400CommonLogic.getAggLine(cMsg, bcMsg.dsContrPk_B.getValue());
            setValue(pMsg.xxContrDtlList.no(aggLineIdx).dsContrDtlPk, dsContrDtl.dsContrDtlPk);

            String contrCloDt = NSAL0400CommonLogic.getLastCloDt(cMsg, bcMsg.dsContrPk_B.getValue());
            // START 2018/02/22 M.Naito [QC23179, MOD]
            // Mod Start 2017/11/20 QC#21724
//            setValue(pMsg.xxContrDtlList.no(aggLineIdx).contrCloDt, NSAL0400CommonLogic.addCloDt(contrCloDt, -1));
            // Mod End 2017/11/20 QC#21724
            setValue(pMsg.xxContrDtlList.no(aggLineIdx).contrCloDt, contrCloDt);
            // END 2018/02/22 M.Naito [QC23179, MOD]
            setValue(pMsg.xxContrDtlList.no(aggLineIdx).trmnTotAmt, bcMsg.trmnTotAmt_B);
            setValue(pMsg.xxContrDtlList.no(aggLineIdx).trmnOvrdTotAmt, bcMsg.trmnOvrdTotAmt_B);
            if (hasValue(bcMsg.supprCrFlg_B)) {
                setValue(pMsg.xxContrDtlList.no(aggLineIdx).supprCrFlg, bcMsg.supprCrFlg_B);
            } else {
                setValue(pMsg.xxContrDtlList.no(aggLineIdx).supprCrFlg, FLG_OFF_N);
            }
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            if (FLG_ON_Y.equals(bcMsg.contrTrmnFlg_B.getValue())) {
                setValue(pMsg.xxContrDtlList.no(aggLineIdx).manTrmnTpCd, MAN_TRMN_TP.ALL_PERIOD);
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]

            //service memo
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).xxModeCd, "C");
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).svcMemoTpCd, SVC_MEMO_TP.TERMINATION_NOTE);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).svcCmntTxt, cMsg.svcCmntTxt_F);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).dsContrPk, bcMsg.dsContrPk_B);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).dsContrDtlPk, dsContrDtl.dsContrDtlPk);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).lastUpdUsrId, userId);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).lastUpdTs, currentTs);
            setValue(pMsg.xxSvcMemoList.no(aggLineIdx).svcMemoRsnCd, cMsg.svcMemoRsnCd_FS);
        }
        // END 2016/02/15 T.Aoyagi [QC3691, ADD]

        pMsg.xxContrDtlList.setValidCount(dtlCnt);
        pMsg.xxSvcMemoList.setValidCount(dtlCnt);

        new NSZC046001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }

        // START 2016/07/12 T.Kanasaka [QC#11027, ADD]
        if (hasValue(pMsg.xxDsMultMsgDplyTxt_HD)) {
            for (int i = 0; i < terminationList.size(); i++) {
                NSAL0400_ACMsg acMsg = terminationList.get(i);
                setValue(acMsg.xxGenlFldAreaTxt_AD, pMsg.xxDsMultMsgDplyTxt_HD);
            }
            cMsg.setMessageInfo("ZZM9037E");
            return false;
        }
        // END 2016/07/12 T.Kanasaka [QC#11027, ADD]

        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = pMsg.xxContrDtlList.no(i);
            if (hasValue(dtlMsg.xxDsMultMsgDplyTxt_DT)) {
                BigDecimal dsContrDtlPk = dtlMsg.dsContrDtlPk.getValue();
                for (int j = 0; j < terminationList.size(); j++) {
                    NSAL0400_ACMsg acMsg = terminationList.get(j);
                    if (dsContrDtlPk.compareTo(acMsg.dsContrDtlPk_AD.getValue()) == 0) {
                        setValue(acMsg.xxGenlFldAreaTxt_AD, dtlMsg.xxDsMultMsgDplyTxt_DT);
                        break;
                    }
                }
                cMsg.setMessageInfo("ZZM9037E");
                return false;
            }
        }

        for (int i = 0; i < pMsg.xxSvcMemoList.getValidCount(); i++) {
            NSZC046001_xxSvcMemoListPMsg svcMemoMsg = pMsg.xxSvcMemoList.no(i);
            if (hasValue(svcMemoMsg.xxDsMultMsgDplyTxt)) {
                BigDecimal dsContrDtlPk = svcMemoMsg.dsContrDtlPk.getValue();
                for (int j = 0; j < terminationList.size(); j++) {
                    NSAL0400_ACMsg acMsg = terminationList.get(j);
                    if (dsContrDtlPk.compareTo(acMsg.dsContrDtlPk_AD.getValue()) == 0) {
                        setValue(acMsg.xxGenlFldAreaTxt_AD, svcMemoMsg.xxDsMultMsgDplyTxt);
                        break;
                    }
                }
                cMsg.setMessageInfo("ZZM9037E");
                return false;
            }
        }

        // add start 2016/06/02 CSA Defect#1523, 4624
        // Call Contract Tracking API
        if (!callContrTrkAPI(cMsg, bcMsg)) {
            return false;
        }
        // add end 2016/06/02 CSA Defect#1523, 4624
        // START 2022/06/01 [QC#59973, ADD]
        // Contract Schedule Agreement Cancel
        for (int j = 0; j < terminationList.size(); j++) {
            NSAL0400_ACMsg acMsg = terminationList.get(j);
            if (!callSchdAgmtAdjContrApi(cMsg, acMsg)) {
                return false;
            }
        }
        // END   2022/06/01 [QC#59973, ADD]
        return true;
    }

    // add start 2016/06/02 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0400CMsg cMsg, NSAL0400_BCMsg bcMsg) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        BigDecimal dsContrPk = bcMsg.dsContrPk_B.getValue();
        String userId = getContextUserInfo().getUserId();
        String slsDt = cMsg.slsDt.getValue();
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        String stsMemoTxt = cMsg.svcCmntTxt_F.getValue();
        DS_CONTR_TRKTMsg dsContrTrkTMsg = new DS_CONTR_TRKTMsg();
        if (hasValue(stsMemoTxt) && stsMemoTxt.length() > dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit()) {
            stsMemoTxt = stsMemoTxt.substring(0, dsContrTrkTMsg.getAttr("stsMemoTxt").getDigit());
        }
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/02 CSA Defect#1523, 4624

    // START 2022/06/01 [QC#59973, ADD]
    private boolean callSchdAgmtAdjContrApi(NSAL0400CMsg cMsg, NSAL0400_ACMsg acMsg) {
        // Create ApiMsg
        NWZC194001PMsg apiPMsg = new NWZC194001PMsg();
        setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(apiPMsg.xxModeCd, NWZC194001Constant.MODE_TERMINATE);
        setValue(apiPMsg.svcMachMstrPk, acMsg.svcMachMstrPk_AD.getValue());
        setValue(apiPMsg.dsContrPk, acMsg.dsContrPk_AH.getValue());
        // Call Api
        new NWZC194001().execute(apiPMsg, ONBATCH_TYPE.ONLINE);
        // Check returns
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
    // END 2022/06/01 [QC#59973, ADD]
}

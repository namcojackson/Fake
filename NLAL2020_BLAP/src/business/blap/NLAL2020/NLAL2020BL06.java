/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLAL2020.common.NLAL2020CommonLogic;
import business.blap.NLAL2020.constant.NLAL2020Constant;
import business.db.CPOTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTRTMsgArray;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC206001_RWSPutAwayListPMsg;
import business.parts.NLZC206001_RcvSerNumListPMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SerialInfo;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SvcMachMstrCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NLAL2020BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 04/26/2016   CSAI            Y.Imazu         Update          QC#7655
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7977
 * 06/03/2016   CSAI            Y.Imazu         Update          QC#7981
 * 06/06/2016   CSAI            Y.Imazu         Update          QC#5061
 * 07/19/2016   CSAI            Y.Imazu         Update          QC#7981
 * 08/25/2016   CITS            S.Endo          Update          QC#13142
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 11/28/2016   CITS            R.Shimamoto     Update          QC#15806
 * 04/13/2017   CITS            T.Tokutomi      Update          Merge DS
 * 05/08/2017   CITS            R.Shimamoto     Update          QC#18427
 * 07/07/2017   CITS            Y.Imazu         Update          QC#19730
 * 08/30/2017   CITS            T.Hakodate      Update          Sol#069(QC#18270)
 * 09/15/2017   CITS            K.Fukumura      Update          QC#20532
 * 10/06/2017   CITS            K.Ogino         Update          QC#21701
 * 04/24/2018   CITS            K.Ogino         Update          QC#19154
 * 08/23/2018   CITS            T.Kikuhara      Update          QC#27890
 * 10/25/2018   CITS            K.Ogino         Update          QC#28926
 * 11/06/2018   CITS            M.Naito         Update          QC#28967
 * 12/17/2018   CITS            T.Tokutomi      Update          QC#29283
 * 04/03/2019   Fujitsu         T.Ogura         Update          QC#31000
 * 05/20/2020   Fujitsu         T.Ogura         Update          QC#56650
 * 12/16/2020   CITS            A.Marte         Update          QC#58070
 * 04/28/2022   CITS            J.Evangelista   Update          QC#58807
 * 2022/10/18   Hitachi         T.Doi           Update          QC#60606
 * 10/26/2022   Hitachi         M.Kikushima     Update          QC#60413
 *</pre>
 */
public class NLAL2020BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;
            NLAL2020SMsg glblMsg = (NLAL2020SMsg) sMsg;
            cMsg.setCommitSMsg(true);

            if ("NLAL2020Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_RWS_Close".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_RWS_Close(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_Receive".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_Receive(bizMsg, glblMsg);

            } else if ("NLAL2020Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLAL2020Scrn00_SaveSearch(bizMsg, glblMsg);

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2020Scrn00_DeleteSearch(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {

            bizMsg.srchOptPk_S.setErrorInfo(1, NLAL2020Constant.NLZM2274E, new String[] {converter.convLabel2i18nLabel(NLAL2020Constant.SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NLAL2020CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg);
    }

    /**
     * RWS_Close Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2020Scrn00_RWS_Close(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Error Check
        if (!inputCheckRwsClose(bizMsg, glblMsg)) {

            return;
        }

        // START 2019/04/03 T.Ogura [QC#31000,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg_RC)) {
            bizMsg.xxWrnSkipFlg_RC.clear();
        } else {
            bizMsg.setMessageInfo(NLAL2020Constant.NLAM1352W);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_RC, ZYPConstant.FLG_ON_Y);
            return;
        }
        // END   2019/04/03 T.Ogura [QC#31000,ADD]

        // START 2022/10/26 M.Kikushima [QC#60413,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg_CL)) {
            bizMsg.xxWrnSkipFlg_CL.clear();
        } else {

            if (NLAL2020CommonLogic.validateForCancel(bizMsg, glblMsg, false)) {
                bizMsg.setMessageInfo(NLAL2020Constant.NLAM1358W, new String[] {"RWS Close"});
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_RC, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_CL, ZYPConstant.FLG_ON_Y);
                return;
            }

        }
        // START 2022/10/26 M.Kikushima [QC#60413,ADD]

        String rcvDtTm = NLAL2020CommonLogic.getRcvDateTime();
        HashSet<String> rwsNumSet = new HashSet<String>();

        // RWS Close
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A1.getValue())) {

                // Skip already processed RWS
                if (!rwsNumSet.isEmpty() && rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    continue;
                }

                // Close RWS Process
                if (!closeRws(bizMsg, glblMsg, glblMsgLine, rcvDtTm)) {

                    return;
                }

                rwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());
            }
        }
    }

    /**
     * closeRws
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param glblMsgLine NLAL2020_ASMsg
     * @param rcvDtTm String
     */
    private boolean closeRws(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgLine, String rcvDtTm) {

        // Call API NLZC2070 : RWS Complete from S21 DC
        if (!completeRWS(glblMsg, bizMsg, glblMsgLine, rcvDtTm)) {
            return false;
        }

        // START 2022/04/28 J.Evangelista [QC#58807,ADD]
        if (!trmnIntgItem(bizMsg, glblMsg, glblMsgLine)) {

            return false;
        }
        // END 2022/04/28 J.Evangelista [QC#58807,ADD]

        return true;
    }

    // START 2022/04/28 J.Evangelista [QC#58807,ADD]
    /**
     * trmnIntgItem
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return boolean
     */
    private boolean trmnIntgItem(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgLine) {

        if (!SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue())) {
            return true; // No further processing necessary.
        }

        S21SsmEZDResult ssmResult =  NLAL2020Query.getInstance().chkRtrnMachInRws(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rwsNum_A1.getValue());
        if (ssmResult.isCodeNotFound()) {
            return true; // No further processing necessary.
        }

        BigDecimal svcConfigMstrPk = glblMsgLine.svcConfigMstrPk_A1.getValue();

        ssmResult =  NLAL2020Query.getInstance().getIntgItemForTrmn(bizMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
        if (ssmResult.isCodeNormal()) {

            NSZC001001 api = new NSZC001001();

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> result : resultList) {

                NSZC001001PMsg pMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.RWS.code);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, (BigDecimal) result.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
                ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, bizMsg.slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, svcConfigMstrPk);

                api.execute(pMsg, ONBATCH_TYPE.ONLINE);

                // Check Error in API
                if (!NLAL2020CommonLogic.chkApiRslt(bizMsg, S21ApiUtil.getXxMsgIdList(pMsg))) {

                    bizMsg.xxPageShowFromNum.setValue(0);
                    NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
                    return false;
                }
            }
        }

        return true;
    }
    // END 2022/04/28 J.Evangelista [QC#58807,ADD]

    /**
     * Receive Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2020Scrn00_Receive(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        NLAL2020CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Input Check
        if (!inputCheckReceive(bizMsg, glblMsg)) {

            return;
        }

        // Error/Warning Check
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {

            if (!inputCheckReceiveWarningErrorByMachMstr(bizMsg, glblMsg)) {

                if (!"E".equals(bizMsg.getMessageKind())) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                }

                return;
            }

            // START 2020/12/16 A.Marte [QC#58070, ADD]
            // Check warehouse code
            if (!inputCheckReceiveWarningErrorByWhCd(bizMsg, glblMsg)) {

                if (!"E".equals(bizMsg.getMessageKind())) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                }

                return;
            }
            // END 2020/12/16 A.Marte [QC#58070, ADD]

        }

        bizMsg.xxWrnSkipFlg.clear();

        // Call API NLZC2060 : Put Away Confirmation from S21 DC API
        if (!receivePutAway(bizMsg, glblMsg)) {

            return;
        }

        String rcvDtTm = NLAL2020CommonLogic.getRcvDateTime();
        HashSet<String> rwsNumSet = new HashSet<String>();

        // RWS Close
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A2.getValue())) {

                // Skip already processed RWS
                if (!rwsNumSet.isEmpty() && rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    continue;
                }

                rwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());

                if (checkAllCloseRws(bizMsg, glblMsgLine) || !ZYPConstant.FLG_ON_Y.equals(glblMsgLine.xxPutAwayFlg_A1.getValue())) {

                    // Close RWS Process
                    if (!closeRws(bizMsg, glblMsg, glblMsgLine, rcvDtTm)) {

                        return;
                    }
                }
            }
        }

        // End Normally
        bizMsg.setMessageInfo(NLAL2020Constant.ZZZM9003I, new String[] {"Receive" });
    }

    /**
     * receivePutAway 
     * 1. Call API NLZC2060 : Put Away Confirmation from S21 DC API
     * 2. Call API NLZC3020 : Serial Transaction API
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private boolean receivePutAway(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        HashSet<String> rwsNumSet = new HashSet<String>();
        List<NLZC206001PMsg> putAwayPMsgList = new ArrayList<NLZC206001PMsg>();

        String rcvDtTm = NLAL2020CommonLogic.getRcvDateTime();

        for (int h = 0; h < glblMsg.A.getValidCount(); h++) {
            NLAL2020_ASMsg hLine = glblMsg.A.no(h);

            if (ZYPConstant.CHKBOX_ON_Y.equals(hLine.xxChkBox_A2.getValue())) {

                // Processed RWS -> Skip
                if (rwsNumSet.contains(hLine.rwsNum_A1.getValue())) {
                    continue;
                }

                NLZC206001PMsg putAwayPMsg = new NLZC206001PMsg();

                int dtlIdx = 0;
                int serIdx = 0;

                for (int d = 0; d < glblMsg.A.getValidCount(); d++) {

                    NLAL2020_ASMsg dLine = glblMsg.A.no(d);

                    // Not Same RWS# -> Skip
                    if (!hLine.rwsNum_A1.getValue().equals(dLine.rwsNum_A1.getValue())) {
                        continue;
                    }

                    if (ZYPConstant.CHKBOX_ON_Y.equals(dLine.xxChkBox_A2.getValue())) {

                        ZYPEZDItemValueSetter.setValue(putAwayPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsg.slsDt, bizMsg.slsDt.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsg.machMstrUpdFlg, ZYPConstant.FLG_ON_Y);

                        NLZC206001_RWSPutAwayListPMsg putAwayPMsgDtl = putAwayPMsg.RWSPutAwayList.no(dtlIdx);
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.rwsNum, dLine.rwsNum_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.rwsDtlLineNum, dLine.rwsDtlLineNum_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.invtyStkStsCd, dLine.invtyStkStsCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.sceOrdTpCd, dLine.sceOrdTpCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.rwsStkDtTmTs, rcvDtTm);
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.rwsStkQty, dLine.poBalQty_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.whCd, dLine.rcvInvtyLocCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.mdseCd, dLine.mdseCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.rwsRefNum, dLine.rwsRefNum_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(putAwayPMsgDtl.destInvtyLocCd, dLine.rtlWhCd_A1.getValue() + dLine.rtlSwhCd_A1.getValue());
                        dtlIdx++;
                        putAwayPMsg.RWSPutAwayList.setValidCount(dtlIdx);

                        // Create Parameter for Serial
                        if (ZYPCommonFunc.hasValue(dLine.serNum_A1) && ZYPConstant.FLG_OFF_N.equals(dLine.xxSerNumSrchTxt_A1.getValue())) {
                            NLZC206001_RcvSerNumListPMsg putAwayPMsgSer = putAwayPMsg.RcvSerNumList.no(serIdx);
                            ZYPEZDItemValueSetter.setValue(putAwayPMsgSer.rwsDtlLineNum, dLine.rwsDtlLineNum_A1);
                            ZYPEZDItemValueSetter.setValue(putAwayPMsgSer.serNum, dLine.serNum_A1);
                            ZYPEZDItemValueSetter.setValue(putAwayPMsgSer.mdseCd, dLine.mdseCd_A1);
                            serIdx++;
                            putAwayPMsg.RcvSerNumList.setValidCount(serIdx);
                        } else if (ZYPConstant.FLG_ON_Y.equals(dLine.xxSerNumSrchTxt_A1.getValue()) //
                                || !ZYPCommonFunc.hasValue(dLine.serNum_A1) && ZYPConstant.FLG_ON_Y.equals(dLine.serNumTakeFlg_A1.getValue())) {
                            // Get Serial#
                            for (int s = 0; s < glblMsg.S.getValidCount(); s++) {

                                NLAL2020_SSMsg sLine = glblMsg.S.no(s);

                                if (sLine.rwsNum_S1.getValue().equals(dLine.rwsNum_A1.getValue()) && sLine.rwsLineNum_S1.getValue().equals(dLine.rwsDtlLineNum_A1.getValue())) {
                                    // Same RWS Line# -> Create
                                    // Parameter for Serial
                                    NLZC206001_RcvSerNumListPMsg putAwayPMsgSer = putAwayPMsg.RcvSerNumList.no(serIdx);
                                    ZYPEZDItemValueSetter.setValue(putAwayPMsgSer.rwsDtlLineNum, sLine.rwsLineNum_S1);
                                    ZYPEZDItemValueSetter.setValue(putAwayPMsgSer.serNum, sLine.serNum_S1);
                                    ZYPEZDItemValueSetter.setValue(putAwayPMsgSer.mdseCd, sLine.mdseCd_S1);
                                    serIdx++;
                                    putAwayPMsg.RcvSerNumList.setValidCount(serIdx);
                                }
                            }
                        }
                    }
                }

                putAwayPMsgList.add(putAwayPMsg);
                rwsNumSet.add(hLine.rwsNum_A1.getValue());
            }
        }

        for (NLZC206001PMsg pMsg : putAwayPMsgList) {
            if (!NLAL2020CommonLogic.checkPurchaseOrderDetail(pMsg, bizMsg, glblMsg)) {
                NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
                return false;
            }
        }

        // Call API NLZC2060 : Put Away Confirmation from S21 DC API
        NLZC206001 putAwayAPI = new NLZC206001();
        putAwayAPI.execute(putAwayPMsgList, ONBATCH_TYPE.ONLINE);

        if (!NLAL2020CommonLogic.chkPutAwayApiResult(putAwayPMsgList, bizMsg)) {
            bizMsg.xxPageShowFromNum.setValue(0);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * completeRWS
     * @param glblMsg NLAL2020SMsg
     * @param bizMsg NLAL2020CMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @param slsDtTm String
     * @return boolean
     */
    private boolean completeRWS(NLAL2020SMsg glblMsg, NLAL2020CMsg bizMsg, NLAL2020_ASMsg glblMsgLine, String slsDtTm) {

        // Create Parameter NLZC2070 : RWS Complete from S21 DC
        NLZC207001PMsg completeRWSPMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.slsDt, bizMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.rwsNum, glblMsgLine.rwsNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.sceOrdTpCd, glblMsgLine.sceOrdTpCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.rwsCloDtTmTs, slsDtTm);
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.whCd, glblMsgLine.shipToRtlWhCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(completeRWSPMsg.rwsRefNum, glblMsgLine.rwsRefNum_A1.getValue());

        // Call API
        NLZC207001 completeRWSAPI = new NLZC207001();
        completeRWSAPI.execute(completeRWSPMsg, ONBATCH_TYPE.ONLINE);

        // Check Error in API
        if (!NLAL2020CommonLogic.chkApiRslt(bizMsg, S21ApiUtil.getXxMsgIdList(completeRWSPMsg))) {

            bizMsg.xxPageShowFromNum.setValue(0);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * checkAllCloseRws
     * @param bizMsg NLAL2020CMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return boolean
     */
    private boolean checkAllCloseRws(NLAL2020CMsg bizMsg, NLAL2020_ASMsg glblMsgLine) {

        // Get RWS_DTL
        RWS_DTLTMsg rwsDtl = new RWS_DTLTMsg();
        rwsDtl.setSQLID("001");
        rwsDtl.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        rwsDtl.setConditionValue("rwsNum01", glblMsgLine.rwsNum_A1.getValue());
        RWS_DTLTMsgArray rwsDtlList = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(rwsDtl);

        if (rwsDtlList == null || rwsDtlList.getValidCount() == 0) {

            return false;
        }

        boolean allClose = true;

        for (int i = 0; i < rwsDtlList.getValidCount(); i++) {

            if (!RWS_STS.CANCELED.equals(rwsDtlList.no(i).rwsStsCd.getValue()) && !RWS_STS.RECEIVED.equals(rwsDtlList.no(i).rwsStsCd.getValue())) {

                allClose = false;
                break;
            }
        }

        return allClose;
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2020Scrn00_SaveSearch(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {

            bizMsg.srchOptNm_S.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {converter.convLabel2i18nLabel(NLAL2020Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        if (NLAL2020CommonLogic.isExistSaveSearchName(bizMsg)) {

            // You can not [@] this record. Because of [@] already exists.
            bizMsg.srchOptNm_S.setErrorInfo(1, NLAL2020Constant.NLZM2273E
                    , new String[] {converter.convLabel2i18nLabel(NLAL2020Constant.SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(NLAL2020Constant.SCRN_ID_00, "Search Option Name") });
            return;
        }

        NLAL2020CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg);
    }

    /**
     * inputCheckReceive
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private boolean inputCheckReceive(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int firstErrIdx = -1;
        boolean chkCount = false;
        boolean hasErr = false;
        HashSet<String> rwsNumSet = new HashSet<String>();
        HashSet<String> notPiRcvLocSet = new HashSet<String>();
        HashSet<String> piErrRcvLocSet = new HashSet<String>();

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLAL2020Constant.BIZ_ID);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A2.getValue())) {

                chkCount = true;

                /*************************************************************
                 * Check Status
                 ************************************************************/
                if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.rwsOpenFlg_A1.getValue())) {

                    glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2283E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    continue;

                } else if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.openFlg_A1.getValue())) {

                    glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2283E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    continue;
                }

                /*************************************************************
                 * Check TimeStamp
                 ************************************************************/
                if (!checkTimeStamp(bizMsg, glblMsg, glblMsgLine, rwsNumSet)) {

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    continue;
                }

                /*************************************************************
                 * Check WH Permission
                 ************************************************************/
                if (!NLAL2020CommonLogic.isWarehousePermission(bizMsg, glblMsgLine.rtlWhCd_A1.getValue(), functionList)) {
                    glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2313E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    continue;
                }

                /*************************************************************
                 * Check Received Location
                 ************************************************************/
                if (!NLAL2020CommonLogic.getRcvByNm(bizMsg.glblCmpyCd.getValue(), null, glblMsgLine)) {

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;

                } else {

                    // Already PI Checked
                    if (piErrRcvLocSet.contains(glblMsgLine.destInvtyLocCd_A1.getValue())) {

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        glblMsgLine.rtlWhCd_A1.setErrorInfo(1, NLAL2020Constant.NLBM1347E);
                        hasErr = true;

                        // Check PI Activity
                    } else if (notPiRcvLocSet.isEmpty() || !notPiRcvLocSet.contains(glblMsgLine.destInvtyLocCd_A1.getValue())) {

                        if (!chkPiActivity(bizMsg.glblCmpyCd.getValue(), glblMsgLine.destInvtyLocCd_A1.getValue())) {

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }

                            glblMsgLine.rtlWhCd_A1.setErrorInfo(1, NLAL2020Constant.NLBM1347E);
                            piErrRcvLocSet.add(glblMsgLine.destInvtyLocCd_A1.getValue());
                            hasErr = true;
                        }

                        notPiRcvLocSet.add(glblMsgLine.destInvtyLocCd_A1.getValue());
                    }
                }

                /*************************************************************
                 * Check Receiving QTY
                 ************************************************************/
                if (!ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1)) {

                    glblMsgLine.poBalQty_A1.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Receiving Qty" });

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;

                    // Receiving QTY = 0 --> Error
                } else if (0 == glblMsgLine.poBalQty_A1.getValueInt()) {

                    glblMsgLine.poBalQty_A1.setErrorInfo(1, NLAL2020Constant.NLZM2277E, new String[] {"Receiving Qty", "0" });

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;

                    // Planned Receiving QTY < Input Receiving QTY -->
                    // Error
                } else if (glblMsgLine.poBalQty_A2.getValueInt() < glblMsgLine.poBalQty_A1.getValueInt()) {

                    glblMsgLine.poBalQty_A1.setErrorInfo(1, NLAL2020Constant.NLZM2316E, new String[] {"Receiving Qty", glblMsgLine.poBalQty_A2.getValue().toString() });

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;

                    // If cannot partial receiving, Planned Receiving
                    // QTY <> Input Receiving QTY --> Error
                } else if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.xxPrtlAcptFlg_A1.getValue()) && glblMsgLine.poBalQty_A2.getValueInt() != glblMsgLine.poBalQty_A1.getValueInt()) {

                    glblMsgLine.poBalQty_A1.setErrorInfo(1, NLAL2020Constant.NLZM2280E, new String[] {"Receiving Qty", glblMsgLine.poBalQty_A2.getValue().toString() });

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                }

                /*************************************************************
                 * Check Serial
                 ************************************************************/
                if (!checkSerial(bizMsg, glblMsg, glblMsgLine)) {

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                }

                // QC#19154
                /*************************************************************
                 * Check MM Creation Batch
                 ************************************************************/
                if (SCE_ORD_TP.DC_TRANSFER.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                        && ZYPCommonFunc.hasValue(glblMsgLine.instlBaseCtrlFlg_A1) //
                        && ZYPConstant.FLG_ON_Y.equals(glblMsgLine.instlBaseCtrlFlg_A1.getValue())
                        && ZYPCommonFunc.hasValue(glblMsgLine.soNum_A1) //
                        && ZYPCommonFunc.hasValue(glblMsgLine.soSlpNum_A1)) {

                    SHPG_ORD_DTLTMsg soDtlTMsg = new SHPG_ORD_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(soDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(soDtlTMsg.soNum, glblMsgLine.soNum_A1);
                    ZYPEZDItemValueSetter.setValue(soDtlTMsg.soSlpNum, glblMsgLine.soSlpNum_A1);

                    soDtlTMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKey(soDtlTMsg);
                    if (soDtlTMsg != null) {
                        String svcMachProcStsCd = soDtlTMsg.svcMachProcStsCd.getValue();
                        if (SVC_MACH_PROC_STS.IN_COMPLETED.equals(svcMachProcStsCd) || SVC_MACH_PROC_STS.ERROR.equals(svcMachProcStsCd)) {

                            glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLAM1347E, null);

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }

                            hasErr = true;
                        }
                    }
                }
                /*************************************************************
                 * Check Partial Receive
                 ************************************************************/
                if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.xxAllwRcvLineFlg_A1.getValue()) && !rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    int chkCnt = 0;
                    int dbCnt = 0;

                    // If exist check-off line in same rwsNum
                    for (int c = 0; c < glblMsg.A.getValidCount(); c++) {

                        NLAL2020_ASMsg chkLine = glblMsg.A.no(c);

                        if (!glblMsgLine.rwsNum_A1.getValue().equals(chkLine.rwsNum_A1.getValue())) {

                            // Different rwsNum
                            continue;
                        }

                        if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.openFlg_A1.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A2.getValue())) {

                            glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2315E, new String[] {"receive" });

                            if (firstErrIdx == -1) {

                                firstErrIdx = i;
                            }

                            hasErr = true;

                        } else {

                            chkCnt++;
                        }
                    }

                    // Check DB
                    RWS_DTLTMsgArray rwsDtlList = getRwsDtl(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rwsNum_A1.getValue());

                    if (rwsDtlList == null || rwsDtlList.getValidCount() < 1) {

                        // Error
                        glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2315E, new String[] {"receive" });

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        hasErr = true;
                    }

                    for (int r = 0; r < rwsDtlList.getValidCount(); r++) {

                        if (!RWS_STS.CANCELED.equals(rwsDtlList.no(r).rwsStsCd.getValue()) && !RWS_STS.RECEIVED.equals(rwsDtlList.no(r).rwsStsCd.getValue())) {

                            dbCnt++;
                        }
                    }

                    if (dbCnt != chkCnt) {

                        glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2315E, new String[] {"receive" });

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        hasErr = true;
                    }
                }

                // START 05/20/2020 T.Ogura [QC#56650,ADD]
                /*************************************************************
                 * Check Shipped for Subcontract
                 ************************************************************/
                if (!checkShippedForSubcontract(bizMsg, glblMsgLine)) {

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                }
                // END   05/20/2020 T.Ogura [QC#56650,ADD]

                if (rwsNumSet.isEmpty() || !rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    rwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());
                }
            }
        }

        if (!chkCount) {

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                glblMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLBM0002E);
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            bizMsg.setMessageInfo(NLAL2020Constant.NLBM0002E);
            return false;
        }
        
        // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) START
        // Shipping status check. shipped item receive.
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            // QC#28926
            if (!ZYPCommonFunc.hasValue(glblMsgLine.xxChkBox_A1)) {
                continue;
            }

            if (SCE_ORD_TP.KITTING.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                    || SCE_ORD_TP.UN_KITTING.equals(glblMsgLine.sceOrdTpCd_A1.getValue()) //
                    || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(glblMsgLine.sceOrdTpCd_A1.getValue())) {//

                ArrayList<String> shpgStatusList = NLAL2020Query.getInstance().getShpgStatus(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rwsNum_A1.getValue());

                if (shpgStatusList.size() > 0) {
                    hasErr = true;
                    glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2283E);
                }
            }

        }
        // 08/30/2017 CITS T.Hakodate Add Sol#069(QC#18270) END
        
        
        if (hasErr) {

            bizMsg.xxPageShowFromNum.setValue(firstErrIdx);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * inputCheckRwsClose
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private boolean inputCheckRwsClose(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        // check count
        boolean chkCount = false;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {

                chkCount = true;
                break;
            }
        }

        if (!chkCount) {

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                glblMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLBM0002E);
            }

            bizMsg.xxPageShowFromNum.setValue(1);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            bizMsg.setMessageInfo(NLAL2020Constant.NLBM0002E);
            return false;
        }

        // Check TimeStamp
        if (!checkTimeStampRwsClose(bizMsg, glblMsg)) {

            return false;
        }

        int firstErrIdx = -1;
        boolean hasErr = false;
        HashSet<String> rwsNumSet = new HashSet<String>();
        HashSet<String> piErrRwsNumSet = new HashSet<String>();
        HashSet<String> notPiRwsNumSet = new HashSet<String>();

        // Role Function
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLAL2020Constant.BIZ_ID);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A1.getValue())) {

                /*************************************************************
                 * Check WH Permission
                 ************************************************************/
                if (!NLAL2020CommonLogic.isWarehousePermission(bizMsg, glblMsgLine.rtlWhCd_A1.getValue(), functionList)) {
                    glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLZM2313E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    continue;
                }

                // PI Activity Status Error
                if (piErrRwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLBM1347E);
                    continue;
                }

                // Check PI Activity
                if (notPiRwsNumSet.isEmpty() || !notPiRwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    if (!chkPiActivity(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rwsNum_A1.getValue())) {

                        if (firstErrIdx == -1) {

                            firstErrIdx = i;
                        }

                        hasErr = true;
                        glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLBM1347E);
                        piErrRwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());
                        continue;
                    }

                    notPiRwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());
                }

                // Check Status
                if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.rwsOpenFlg_A1.getValue())) {

                    glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLZM2283E);

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                }

                if (rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                    continue;
                }

                ArrayList<String> rwsDtlRcvFlgList = NLAL2020Query.getInstance().getRwsDtlRcvFlgList(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rwsNum_A1.getValue());

                if (rwsDtlRcvFlgList == null || rwsDtlRcvFlgList.isEmpty()) {

                    glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.ZZZM9006E, new String[] {"RWS Data" });
                    hasErr = true;
                    break;
                }

                // Not Allowed Partially Received
                if (ZYPConstant.FLG_OFF_N.equals(glblMsgLine.xxAllwRcvLineFlg_A1.getValue()) && rwsDtlRcvFlgList.contains(ZYPConstant.FLG_ON_Y)) {

                    // Exist received line
                    glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLZM2315E, new String[] {"receive/cancel " });

                    if (firstErrIdx == -1) {

                        firstErrIdx = i;
                    }

                    hasErr = true;
                    break;
                }

                // START 2018/11/06 M.Naito [QC#28967,DEL]
//                // Not Exists Received Line
//                if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.rwsScrCancAvalFlg_A1.getValue()) && !rwsDtlRcvFlgList.contains(ZYPConstant.FLG_ON_Y)) {
//
//                    glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLBM1323E);
//
//                    if (firstErrIdx == -1) {
//
//                        firstErrIdx = i;
//                    }
//
//                    hasErr = true;
//                    break;
//                }
                // END 2018/11/06 M.Naito [QC#28967,DEL]

                rwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());
            }
        }

        if (hasErr) {

            bizMsg.xxPageShowFromNum.setValue(firstErrIdx);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * inputCheckReceiveWarningErrorByMachMstr
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private boolean inputCheckReceiveWarningErrorByMachMstr(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int firstErrIdx = -1;
        int firstWarIdx = -1;
        boolean hasErr = false;
        boolean hasWar = false;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A2.getValue())) {

                if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.instlBaseCtrlFlg_A1.getValue())) {

                    continue;
                }

                if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.serNumTakeFlg_A1.getValue())) {

                    // If already checked having warning info, Skip
                    if (ZYPCommonFunc.hasValue(glblMsgLine.serTrxCmntTxt_A1) && glblMsgLine.serTrxCmntTxt_A1.getValue().endsWith("W")) {

                        continue;
                    }

                    // Check Machine Master (For QTY = 1)
                    if (ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1) && ZYPConstant.FLG_OFF_N.equals(glblMsgLine.xxSerNumSrchTxt_A1.getValue())) {

                        NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
                        serialInfo.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
                        serialInfo.setDupChkCd(bizMsg.dupErrCd.getValue());
                        serialInfo.setSceOrdTpCd(glblMsgLine.sceOrdTpCd_A1.getValue());
                        serialInfo.setMdseCd(glblMsgLine.mdseCd_A1.getValue());
                        serialInfo.setSerNum(glblMsgLine.serNum_A1.getValue());
                        /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                        // serialInfo.setShipFromLocCd(glblMsgLine.shipLocCd_A1.getValue());
                        serialInfo.setShipFromLocCd(glblMsgLine.shipFromInvtyLocCd_A1.getValue());
                        /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */
                        serialInfo.setRcvRtlWhCd(glblMsgLine.rtlWhCd_A1.getValue());
                        serialInfo.setTrxHdrNum(glblMsgLine.serAllocTrxHdrNum_A1.getValue());
                        serialInfo.setRwsNum(glblMsgLine.rwsNum_A1.getValue());
                        // QC#21701
                        if (SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue())) {
                            if (ZYPCommonFunc.hasValue(glblMsgLine.svcConfigMstrPk_RT) && NLAL2020CommonLogic.chkSvcConfigPk(bizMsg, glblMsgLine, glblMsgLine.serNum_A1.getValue())) {
                                serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_RT.getValue());
                            } else {
                                serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_A1.getValue());
                            }
                        } else {
                            serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_A1.getValue());
                        }

                        serialInfo.setMdlId(glblMsgLine.mdlId_A1.getValue());
                        /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                        serialInfo.setOnBatchType(ONBATCH_TYPE.ONLINE);
                        /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                        serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForRcvSerial(serialInfo);

                        ZYPEZDItemValueSetter.setValue(glblMsgLine.svcMachMstrPk_A1, serialInfo.getSvcMachMstrPk());
                        ZYPEZDItemValueSetter.setValue(glblMsgLine.serTrxCmntTxt_A1, serialInfo.getErrMsgId());
                        ZYPEZDItemValueSetter.setValue(glblMsgLine.serErrStsCd_A1, serialInfo.getSerErrStsCd());

                        List<String> modeCdList = serialInfo.getModeCdList();

                        if (modeCdList != null && !modeCdList.isEmpty()) {

                            if (modeCdList.size() > 0) {

                                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxModeCd_A1, modeCdList.get(0));
                            }

                            if (modeCdList.size() > 1) {

                                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxModeCd_A2, modeCdList.get(1));
                            }

                            if (modeCdList.size() > 2) {

                                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxModeCd_A3, modeCdList.get(2));
                            }

                            if (modeCdList.size() > 3) {

                                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxModeCd_A4, modeCdList.get(3));
                            }

                            if (modeCdList.size() > 4) {

                                ZYPEZDItemValueSetter.setValue(glblMsgLine.xxModeCd_A5, modeCdList.get(4));
                            }
                        }

                        if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

                            if (serialInfo.getErrMsgId().endsWith("W")) {

                                // QC#27890 MOD START
                                firstWarIdx = i;
                                if (serialInfo.getSerNum() != null && serialInfo.getSerNum().indexOf(',') > 0) {
                                    glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLZM2520E);
                                    ZYPEZDItemValueSetter.setValue(glblMsgLine.serTrxCmntTxt_A1, "");
                                    hasErr = true;
                                } else {
                                    glblMsgLine.serNum_A1.setErrorInfo(2, serialInfo.getErrMsgId());
                                    hasWar = true;
                                }
                                // QC#27890 MOD END

                            } else if (serialInfo.getErrMsgId().endsWith("E")) {

                                glblMsgLine.serNum_A1.setErrorInfo(1, serialInfo.getErrMsgId());
                                firstErrIdx = i;
                                hasErr = true;

                            } else {

                                bizMsg.setMessageInfo(NLAL2020Constant.NLAM1077E);
                                return false;
                            }
                        }

                        // Check Machine Master (For QTY > 1)
                    } else {

                        for (int s = 0; s < glblMsg.S.getValidCount(); s++) {

                            NLAL2020_SSMsg serLine = glblMsg.S.no(s);

                            if (serLine.rwsNum_S1.getValue().equals(glblMsgLine.rwsNum_A1.getValue()) && serLine.rwsLineNum_S1.getValue().equals(glblMsgLine.rwsDtlLineNum_A1.getValue())) {

                                NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
                                serialInfo.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
                                serialInfo.setDupChkCd(bizMsg.dupErrCd.getValue());
                                serialInfo.setSceOrdTpCd(glblMsgLine.sceOrdTpCd_A1.getValue());
                                serialInfo.setMdseCd(glblMsgLine.mdseCd_A1.getValue());
                                serialInfo.setSerNum(serLine.serNum_S1.getValue());
                                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                                // serialInfo.setShipFromLocCd(glblMsgLine.shipLocCd_A1.getValue());
                                serialInfo.setShipFromLocCd(glblMsgLine.shipFromInvtyLocCd_A1.getValue());
                                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */
                                serialInfo.setRcvRtlWhCd(glblMsgLine.rtlWhCd_A1.getValue());
                                serialInfo.setTrxHdrNum(glblMsgLine.serAllocTrxHdrNum_A1.getValue());
                                serialInfo.setRwsNum(glblMsgLine.rwsNum_A1.getValue());
                                // QC#21701
                                if (SCE_ORD_TP.RETURN.equals(glblMsgLine.sceOrdTpCd_A1.getValue())) {
                                    if (ZYPCommonFunc.hasValue(glblMsgLine.svcConfigMstrPk_RT) && NLAL2020CommonLogic.chkSvcConfigPk(bizMsg, glblMsgLine, glblMsgLine.serNum_A1.getValue())) {
                                        serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_RT.getValue());
                                    } else {
                                        serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_A1.getValue());
                                    }
                                } else {
                                    serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_A1.getValue());
                                }

                                serialInfo.setOrdSvcConfigMstrPk(glblMsgLine.svcConfigMstrPk_A1.getValue());
                                serialInfo.setMdlId(glblMsgLine.mdlId_A1.getValue());
                                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
                                serialInfo.setOnBatchType(ONBATCH_TYPE.ONLINE);
                                /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

                                serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForRcvSerial(serialInfo);

                                ZYPEZDItemValueSetter.setValue(serLine.svcMachMstrPk_S1, serialInfo.getSvcMachMstrPk());
                                ZYPEZDItemValueSetter.setValue(serLine.serTrxCmntTxt_S1, serialInfo.getErrMsgId());
                                ZYPEZDItemValueSetter.setValue(serLine.serErrStsCd_S1, serialInfo.getSerErrStsCd());

                                List<String> modeCdList = serialInfo.getModeCdList();

                                if (modeCdList != null && !modeCdList.isEmpty()) {

                                    if (modeCdList.size() > 0) {

                                        ZYPEZDItemValueSetter.setValue(serLine.xxModeCd_S1, modeCdList.get(0));
                                    }

                                    if (modeCdList.size() > 1) {

                                        ZYPEZDItemValueSetter.setValue(serLine.xxModeCd_S2, modeCdList.get(1));
                                    }

                                    if (modeCdList.size() > 2) {

                                        ZYPEZDItemValueSetter.setValue(serLine.xxModeCd_S3, modeCdList.get(2));
                                    }

                                    if (modeCdList.size() > 3) {

                                        ZYPEZDItemValueSetter.setValue(serLine.xxModeCd_S4, modeCdList.get(3));
                                    }

                                    if (modeCdList.size() > 4) {

                                        ZYPEZDItemValueSetter.setValue(serLine.xxModeCd_S5, modeCdList.get(4));
                                    }
                                }

                                if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

                                    if (serialInfo.getErrMsgId().endsWith("W")) {

                                        glblMsgLine.serNum_A1.setErrorInfo(2, serialInfo.getErrMsgId());
                                        firstWarIdx = i;
                                        hasWar = true;

                                    } else if (serialInfo.getErrMsgId().endsWith("E")) {

                                        glblMsgLine.serNum_A1.setErrorInfo(1, serialInfo.getErrMsgId());
                                        firstErrIdx = i;
                                        hasErr = true;

                                    } else {

                                        bizMsg.setMessageInfo(NLAL2020Constant.NLAM1077E);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (hasErr || hasWar) {

            if (hasErr) {

                bizMsg.setMessageInfo(NLAL2020Constant.ZZM9037E);
                bizMsg.xxPageShowFromNum.setValue(firstErrIdx);

            } else {

                bizMsg.setMessageInfo(NLAL2020Constant.NLZM2282W, new String[] {"Receive" });
                bizMsg.xxPageShowFromNum.setValue(firstWarIdx);
            }

            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * checkTimeStamp
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @param rwsNumSet HashSet<String>
     * @return boolean
     */
    private boolean checkTimeStamp(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgLine, HashSet<String> rwsNumSet) {

        // RWS Header
        if (!rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

            if (checkTimeStampHdr(bizMsg, glblMsg, glblMsgLine, false)) {

                return false;
            }
        }

        // RWS Detail
        if (!checkTimeStampDtl(bizMsg, glblMsg, glblMsgLine)) {

            return false;
        }

        return true;
    }

    /**
     * checkTimeStampRwsClose
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private boolean checkTimeStampRwsClose(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int firstErrIdx = -1;
        boolean hasErr = false;
        HashSet<String> rwsNumSet = new HashSet<String>();

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A1.getValue()) && !rwsNumSet.contains(glblMsgLine.rwsNum_A1.getValue())) {

                if (checkTimeStampHdr(bizMsg, glblMsg, glblMsgLine, true)) {

                    // Error
                    firstErrIdx = i;
                    hasErr = true;
                }

                rwsNumSet.add(glblMsgLine.rwsNum_A1.getValue());
            }
        }

        if (hasErr) {

            bizMsg.xxPageShowFromNum.setValue(firstErrIdx);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }

    /**
     * checkTimeStampHdr
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @param rwsClose boolean
     * @return boolean true(Error)
     */
    private boolean checkTimeStampHdr(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgLine, boolean rwsClose) {

        boolean hasErr = false;

        // RWS Header
        RWS_HDRTMsg rwsHdr = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdr.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsHdr.rwsNum, glblMsgLine.rwsNum_A1);
        rwsHdr = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdr);

        if (rwsHdr == null) {

            hasErr = true;

        } else if (!ZYPDateUtil.isSameTimeStamp(glblMsgLine.ezUpTime_RH.getValue(), glblMsgLine.ezUpTimeZone_RH.getValue(), rwsHdr.ezUpTime.getValue(), rwsHdr.ezUpTimeZone.getValue())) {

            hasErr = true;
        }

        // anyone update
        if (hasErr) {

            if (rwsClose) {

                glblMsgLine.xxChkBox_A1.setErrorInfo(1, NLAL2020Constant.NLBM0009E);

            } else {

                glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLBM0009E);
            }
        }

        // QC#29283 Update.
        return hasErr;
    }

    /**
     * checkTimeStampDtl
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return boolean
     */
    private boolean checkTimeStampDtl(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgLine) {

        // RWS Detail
        RWS_DTLTMsg rwsDtl = new RWS_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rwsDtl.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsNum, glblMsgLine.rwsNum_A1);
        ZYPEZDItemValueSetter.setValue(rwsDtl.rwsDtlLineNum, glblMsgLine.rwsDtlLineNum_A1);
        rwsDtl = (RWS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsDtl);

        if (rwsDtl == null) {

            glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLBM0009E);
            return false;

        } else if (!ZYPDateUtil.isSameTimeStamp(glblMsgLine.ezUpTime_RD.getValue(), glblMsgLine.ezUpTimeZone_RD.getValue(), rwsDtl.ezUpTime.getValue(), rwsDtl.ezUpTimeZone.getValue())) {

            // anyone update
            glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLBM0009E);
            return false;
        }

        return true;
    }

    /**
     * checkSerial
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return boolean
     */
    private boolean checkSerial(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg, NLAL2020_ASMsg glblMsgLine) {

        if (!ZYPConstant.FLG_ON_Y.equals(glblMsgLine.serNumTakeFlg_A1.getValue())) {

            return true;
        }

        HashMap<String, HashSet<String>> serNumList = new HashMap<String, HashSet<String>>();

        // Serial Number Mandatory
        if (ZYPConstant.FLG_ON_Y.equals(glblMsgLine.openFlg_A1.getValue())) {

            if (ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1) && glblMsgLine.poBalQty_A1.getValueInt() == 1) {

                // For QTY = 1
                if (!ZYPCommonFunc.hasValue(glblMsgLine.serNum_A1)) {

                    glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.ZZM9000E, new String[] {"Serial Number" });
                    return false;

                }
                // QC#18427 Add Start.
                // Duplicate Check in RWS_PUT_AWAY_SER
                if (!NLAL2020CommonLogic.checkDuplicateRwsPutAwaySer(bizMsg, glblMsgLine, glblMsgLine.serNum_A1.getValue())) {

                    glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLAM1338E);
                    return false;
                }

                // QC#18427 Add End.

                addSerNumList(glblMsgLine.mdseCd_A1.getValue(), glblMsgLine.serNum_A1.getValue(), serNumList);

            } else if (ZYPCommonFunc.hasValue(glblMsgLine.poBalQty_A1) && 1 < glblMsgLine.poBalQty_A1.getValueInt()) {

                // For 1 < QTY
                int serNumCnt = 0;

                for (int i = 0; i < glblMsg.S.getValidCount(); i++) {

                    NLAL2020_SSMsg serLine = glblMsg.S.no(i);

                    if (glblMsgLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && glblMsgLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue()) && ZYPCommonFunc.hasValue(serLine.serNum_S1)) {

                        // QC#18427 Add Start.
                        // Duplicate Check in RWS_PUT_AWAY_SER
                        if (!NLAL2020CommonLogic.checkDuplicateRwsPutAwaySer(bizMsg, glblMsgLine, serLine.serNum_S1.getValue())) {

                            glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLAM1338E);
                            return false;
                        }

                        // QC#18427 Add End.

                        // Duplicate in the same line
                        if (serNumList.containsKey(glblMsgLine.mdseCd_A1.getValue())) {
                            HashSet<String> serialList = serNumList.get(glblMsgLine.mdseCd_A1.getValue());

                            if (!serialList.isEmpty() && serialList.contains(serLine.serNum_S1.getValue())) {

                                glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLBM1313E);
                                return false;
                            }
                        }

                        serNumCnt++;
                        addSerNumList(glblMsgLine.mdseCd_A1.getValue(), serLine.serNum_S1.getValue(), serNumList);
                    }
                }

                if (serNumCnt == 0) {

                    glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLAM1335E);
                    return false;

                } else if (serNumCnt != glblMsgLine.poBalQty_A1.getValueInt()) {

                    glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLAM1336E);
                    return false;
                }
            }
        }

        if (!serNumList.isEmpty()) {

            // Duplicate Check
            boolean isDupSer = false;

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

                NLAL2020_ASMsg chkLine = glblMsg.A.no(i);

                if (ZYPConstant.CHKBOX_ON_Y.equals(chkLine.xxChkBox_A2.getValue())) {

                    // Non-serialized item
                    if (!ZYPConstant.FLG_ON_Y.equals(chkLine.serNumTakeFlg_A1.getValue())) {

                        continue;
                    }

                    // It is myself.-->Skip
                    if (glblMsgLine.rwsNum_A1.getValue().equals(chkLine.rwsNum_A1.getValue()) && glblMsgLine.rwsDtlLineNum_A1.getValue().equals(chkLine.rwsDtlLineNum_A1.getValue())) {

                        continue;
                    }

                    // Not same item.-->Skip
                    if (!glblMsgLine.mdseCd_A1.getValue().equals(chkLine.mdseCd_A1.getValue())) {
                        continue;
                    }

                    // For QTY = 1
                    if (!glblMsgLine.rwsNum_A1.getValue().equals(chkLine.rwsNum_A1.getValue()) || !glblMsgLine.rwsDtlLineNum_A1.getValue().equals(chkLine.rwsDtlLineNum_A1.getValue())) {

                        HashSet<String> serial = serNumList.get(chkLine.mdseCd_A1.getValue());

                        if (ZYPCommonFunc.hasValue(chkLine.serNum_A1) && serial.contains(chkLine.serNum_A1.getValue())) {

                            glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLBM1313E);
                            chkLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLBM1313E);
                            isDupSer = true;
                        }
                    }

                    // For 1 < QTY
                    for (int s = 0; s < glblMsg.S.getValidCount(); s++) {

                        NLAL2020_SSMsg serLine = glblMsg.S.no(s);

                        if (glblMsgLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && glblMsgLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue())) {

                            // It is myself.-->Skip
                            continue;
                        }

                        if (chkLine.rwsNum_A1.getValue().equals(serLine.rwsNum_S1.getValue()) && chkLine.rwsDtlLineNum_A1.getValue().equals(serLine.rwsLineNum_S1.getValue())) {

                            HashSet<String> serial = serNumList.get(chkLine.mdseCd_A1.getValue());

                            if (ZYPCommonFunc.hasValue(serLine.serNum_S1) && serial.contains(serLine.serNum_S1.getValue())) {

                                glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLBM1313E);
                                chkLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLBM1313E);
                                isDupSer = true;
                            }
                        }
                    }
                }
            }

            if (isDupSer) {

                return false;
            }

            // Check Asset
            if (INVTY_ACCT.ASSET.equals(glblMsgLine.invtyAcctCd_A1.getValue())) {

                HashSet<String> serial = serNumList.get(glblMsgLine.mdseCd_A1.getValue());

                for (String serNum : serial) {

                    DS_ASSET_MSTRTMsgArray dsAssetList = getAssetMstrList(bizMsg.glblCmpyCd.getValue(), serNum, glblMsgLine.mdseCd_A1.getValue());

                    if (dsAssetList == null || dsAssetList.getValidCount() < 1) {

                        // Not exist DS_ASSET_MSTR
                        glblMsgLine.serNum_A1.setErrorInfo(1, NLAL2020Constant.NLBM1290E, new String[] {"Asset" });
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * addSerNumList
     * @param mdseCd
     * @param serNum
     * @param serNumList
     */
    private void addSerNumList(String mdseCd, String serNum, HashMap<String, HashSet<String>> serNumList) {

        if (serNumList.containsKey(mdseCd)) {
            HashSet<String> set = serNumList.get(mdseCd);
            set.add(serNum);
        } else {
            HashSet<String> set = new HashSet<String>();
            set.add(serNum);
            serNumList.put(mdseCd, set);
        }
    }

    /**
     * getAssetMstrList
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return DS_ASSET_MSTRTMsgArray
     */
    private DS_ASSET_MSTRTMsgArray getAssetMstrList(String glblCmpyCd, String serNum, String mdseCd) {

        DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        dsAssetMstrTMsg.setSQLID("003");
        dsAssetMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsAssetMstrTMsg.setConditionValue("serNum01", serNum);
        dsAssetMstrTMsg.setConditionValue("mdseCd01", mdseCd);
        dsAssetMstrTMsg.setConditionValue("actvAssetFlg01", ZYPConstant.FLG_ON_Y);

        return (DS_ASSET_MSTRTMsgArray) EZDTBLAccessor.findByCondition(dsAssetMstrTMsg);
    }

    /**
     * getRwsDtl
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return RWS_DTLTMsgArray
     */
    private RWS_DTLTMsgArray getRwsDtl(String glblCmpyCd, String rwsNum) {

        RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
        rwsDtlTMsg.setSQLID("002");
        rwsDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsDtlTMsg.setConditionValue("rwsNum01", rwsNum);

        return (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(rwsDtlTMsg);
    }

    /**
     * chkPiActivity
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return boolean
     */
    private boolean chkPiActivity(String glblCmpyCd, String invtyLocCd) {

        NLZC060001PMsg piActivityStsApiPMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.invtyLocCd, invtyLocCd);

        NLZC060001 piActivityStsApi = new NLZC060001();
        piActivityStsApi.execute(piActivityStsApiPMsg, ONBATCH_TYPE.ONLINE);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(piActivityStsApiPMsg.xxRsltStsCd.getValue())) {

            return false;
        }

        return true;
    }

    /**
     * isServiceExchange
     * @param svcExchgRwsNumSet
     * @param svcExchgCatgList
     * @param glblCmpyCd
     * @param srcOrdNum
     * @return
     */
    private boolean isServiceExchange(HashSet<String> svcExchgRwsNumSet, List<String> svcExchgCatgList, String glblCmpyCd, String srcOrdNum) {
        boolean isSvcExchg = false;

        if (svcExchgCatgList != null && !svcExchgCatgList.isEmpty()) {

            if (svcExchgRwsNumSet.contains(srcOrdNum)) {

                isSvcExchg = true;

            } else {

                CPOTMsg cpoTMsg = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, srcOrdNum);
                cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);

                if (cpoTMsg != null && svcExchgCatgList.contains(cpoTMsg.dsOrdCatgCd.getValue())) {
                    isSvcExchg = true;
                    svcExchgRwsNumSet.add(srcOrdNum);
                }
            }
        }
        return isSvcExchg;
    }

    // START 05/20/2020 T.Ogura [QC#56650,ADD]
    /**
     * checkShippedForSubcontract
     * @param bizMsg NLAL2020CMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @return boolean
     */
    private boolean checkShippedForSubcontract(NLAL2020CMsg bizMsg, NLAL2020_ASMsg glblMsgLine) {

        if (!SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(glblMsgLine.sceOrdTpCd_A1.getValue())) {

            return true;
        }

        // Check Shipment
        String shpgStsCd = NLAL2020Query.getInstance().getShpgStsCd(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rwsNum_A1.getValue(), glblMsgLine.rwsDtlLineNum_A1.getValue());

        if (!ZYPCommonFunc.hasValue(shpgStsCd) || !SHPG_STS.SHIPPED.equals(shpgStsCd)) {

            glblMsgLine.xxChkBox_A2.setErrorInfo(1, NLAL2020Constant.NLAM1354E);
            return false;
        }

        return true;
    }
    // END   05/20/2020 T.Ogura [QC#56650,ADD]

    // START 2020/12/16 A.Marte [QC#58070, ADD]
    /**
     * inputCheckReceiveWarningErrorByWhCd
     * @param bizMsg NLAL2020CMsg
     * @param glblMsg NLAL2020SMsg
     * @return boolean
     */
    private boolean inputCheckReceiveWarningErrorByWhCd(NLAL2020CMsg bizMsg, NLAL2020SMsg glblMsg) {

        int firstWarIdx = -1;
        boolean hasWar = false;
        List<String> whSysTpCdList = new ArrayList<String>();

        //Get Do not receive warehouse code list
        String whSysTpCdConst = ZYPCodeDataUtil.getVarCharConstValue("NLAL2020_NOT_ALLOW_RCV", bizMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(whSysTpCdConst)) {
            whSysTpCdList = Arrays.asList(whSysTpCdConst.split(","));
        } else
        {
            whSysTpCdList = Arrays.asList("00");
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {

            NLAL2020_ASMsg glblMsgLine = glblMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(glblMsgLine.xxChkBox_A2.getValue())) {

                //Get WH_SYS_TP_CD of current RWS
                S21SsmEZDResult ssmResult = NLAL2020Query.getInstance().getWhSysTpCd(bizMsg.glblCmpyCd.getValue(), glblMsgLine.rtlWhCd_A1.getValue());

                if (ssmResult.isCodeNormal()) {
                    //NO PDLT Record
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

                    for (Map<String, Object> result : resultList) {

                        for (String whSysTpCd : whSysTpCdList) {
                            String whSysTpCdVal = (String) result.get("WH_SYS_TP_CD");
                            if (whSysTpCdVal.compareTo(whSysTpCd) == 0) {
                                //WMS WH CODE
                                if (firstWarIdx == -1) {
                                    firstWarIdx = i;
                                    // START 2022/10/18 T.Doi [QC#60606, ADD]
                                    glblMsgLine.xxChkBox_A2.setErrorInfo(2, NLAL2020Constant.NLAM1356W);
                                    // END 2022/10/18 T.Doi [QC#60606, ADD]
                                    hasWar = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (hasWar) {

            bizMsg.setMessageInfo(NLAL2020Constant.NLAM1356W);
            bizMsg.xxPageShowFromNum.setValue(firstWarIdx);
            NLAL2020CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return false;
        }

        return true;
    }
    // END 2020/12/16 A.Marte [QC#58070, ADD]

}

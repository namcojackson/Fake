/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ------------------------------------------------------------------------------------
 * 07/28/2009   Fujitsu         FAP)H.Aoki      Create          N/A
 * 12/29/2009   Fujitsu         A.Akabane       Update          N/A
 * 03/05/2010   Fujitsu         A.Akabane       Update          Workshop RQ No.233-237
 * 10/13/2010   Fujitsu         S.Yoshida       Update          N/A
 *</pre>
 */
package business.blap.NLAL0070;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLAL0070.common.NLAL0070CommonLogic;
import business.blap.NLAL0070.constant.NLAL0070Constant;
import business.db.IMPT_INV_LTTMsgArray;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.RWS_HDRTMsgArray;
import business.db.WH_SCHDTMsg;
import business.db.WH_SCHDTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NLAL0070BL06 extends S21BusinessHandler implements NLAL0070Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NLAL0070Scrn00_CMN_Submit
            if ("NLAL0070Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLAL0070Scrn00_CMN_Submit(cMsg, sMsg);

            // Unexpected
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL0070Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_CMN_Submit================================START", this);

        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        NLAL0070SMsg globalMsg = (NLAL0070SMsg) sMsg;

        NLAL0070CommonLogic.setScrnInputData(bizMsg, globalMsg);

        int globalMsgACount = globalMsg.A.getValidCount();

        if (!checkSelectCheck(globalMsg, globalMsgACount)) {
            bizMsg.setMessageInfo("NLAM0023E", null);
            return;
        } else {
            // do nothing
        }

        if (!checkFormatScheduleEta(globalMsg, bizMsg, globalMsgACount)) {
            return;
        } else {
            // do nothing
        }

        if (!checkFormatWhEtaTemp(globalMsg, bizMsg, globalMsgACount)) {
            return;
        } else {
            // do nothing
        }

        if (!checkLtForTrmEtaTemp(globalMsg, bizMsg, globalMsgACount)) {
            return;
        } else {
            // do nothing
        }

        if (!checkFormatFinalScheduleEta(globalMsg, bizMsg, globalMsgACount)) {
            return;
        } else {
            // do nothing
        }

        if (!checkFormatRailAvailableDate(globalMsg, bizMsg, globalMsgACount)) {
            return;
        } else {
            // do nothing
        }

//Unnecessary
//        if (!checkDiffTerminalEta(globalMsg, bizMsg, globalMsgACount)) {
//            return;
//        } else {
//            // do nothing
//        }

        int pagenationFrom = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int pcnt = pagenationFrom;
        int cnt = 0;
        for (; pcnt < pagenationFrom + bizMsg.A.length(); pcnt++) {
            if (cnt < bizMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(pcnt - pagenationFrom), null, globalMsg.A.no(pcnt), null);
            } else {
                break;
            }
            cnt++;
        }

        if (!this.submitData(bizMsg, globalMsg, globalMsgACount)) {
            return;
        }

        EZDDebugOutput.println(1, "doProcess_NLAL0070Scrn00_CMN_Submit================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     * @param globalMsgBK NLAL0070SMsg
     * @param globalMsgBCount int
     * @return boolean
     */
    private boolean submitData(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, int globalMsgACount) {

        List<WH_SCHDTMsg>  whSchdUpdateList  = new ArrayList<WH_SCHDTMsg>();
        List<INBD_VISTMsg> inbdVisDeleteList = new ArrayList<INBD_VISTMsg>();
        List<INBD_VISTMsg> inbdVisUpdateList = new ArrayList<INBD_VISTMsg>();
        List<INBD_VISTMsg> inbdVisInsertList = new ArrayList<INBD_VISTMsg>();

        List<Integer> scrnUpdateList = new ArrayList<Integer>();
        String ezUpTime = "";
        String ezUpTimeZone = "";

        String sysDateTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);
        for (int i = 0; i < globalMsgACount; i++) {

            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            } else {
                // do nothing
            }

            WH_SCHDTMsgArray whSchdTMsgArray = new WH_SCHDTMsgArray();
            whSchdTMsgArray = NLAL0070CommonLogic.getWhSchdInfoForUpdateNoWait(bizMsg, globalMsg, i);

            if (whSchdTMsgArray == null) {
                return false;
            }

            if (whSchdTMsgArray.getValidCount() == 0) {
                bizMsg.setMessageInfo("NLAM0014E", null);
                return false;
            }

            for (int j = 0; j < whSchdTMsgArray.getValidCount(); j++) {
                whSchdUpdateList.add(NLAL0070CommonLogic.updateWhSchdInfo(globalMsg, whSchdTMsgArray.no(j), i));
            }

            // Set index number
            scrnUpdateList.add(i);

            if (INBD_VIS_EVENT.INVOICE_NL_RECEIVE.equals(whSchdTMsgArray.no(0).inbdVisEventCd.getValue())) {

                boolean rwsSts = true;
                String rwsStsCd = null;
                INBD_VISTMsgArray outMsgsForVis = new INBD_VISTMsgArray();

                if (INBD_SRC_TP.INVOICE.equals(whSchdTMsgArray.no(0).inbdSrcTpCd.getValue())) {

                    RWS_HDRTMsgArray outMsgsForRWS = NLAL0070CommonLogic.getRWSInfoOfInvoiceOfNLReceive(whSchdTMsgArray.no(0));
                    for (int j = 0; j < outMsgsForRWS.getValidCount(); j++) {
                        if (RWS_STS.RECEIVING.equals(outMsgsForRWS.no(j).rwsStsCd.getValue())
                                || RWS_STS.RECEIVED.equals(outMsgsForRWS.no(j).rwsStsCd.getValue())) {
                            rwsSts = false;
                            rwsStsCd = outMsgsForRWS.no(j).rwsStsCd.getValue();
                            break;
                        }
                    }
                    if (!rwsSts) {
                        bizMsg.setMessageInfo("NLAM0260E", new String[]{getRwsStsNm(rwsStsCd)});
                        return false;
                    }

                    outMsgsForVis = NLAL0070CommonLogic.getInbdVisInfoOfInvoiceOfNLReceiveForUpdateNoWait(whSchdTMsgArray.no(0));

                } else if (INBD_SRC_TP.CONTAINER.equals(whSchdTMsgArray.no(0).inbdSrcTpCd.getValue())) {

                    RWS_HDRTMsgArray outMsgsForRWS = NLAL0070CommonLogic.getRWSInfoOfContainerOfNLReceive(whSchdTMsgArray.no(0));
                    for (int j = 0; j < outMsgsForRWS.getValidCount(); j++) {
                        if (RWS_STS.RECEIVING.equals(outMsgsForRWS.no(j).rwsStsCd.getValue())
                                || RWS_STS.RECEIVED.equals(outMsgsForRWS.no(j).rwsStsCd.getValue())) {
                            rwsSts = false;
                            rwsStsCd = outMsgsForRWS.no(j).rwsStsCd.getValue();
                            break;
                        }
                    }
                    if (!rwsSts) {
                        bizMsg.setMessageInfo("NLAM0260E", new String[]{getRwsStsNm(rwsStsCd)});
                        return false;
                    }

                    outMsgsForVis = NLAL0070CommonLogic.getInbdVisInfoOfContainerOfNLReceiveForUpdateNoWait(whSchdTMsgArray.no(0));
                }

                String schdEtaChkFlg = globalMsg.A.no(i).schdEtaChkFlg_A1.getValue();
                String finalEtaChkFlg = globalMsg.A.no(i).finalEtaChkFlg_A1.getValue();
                // Pattern A (Schedule Check Flag OFF and Final Schedule Check Flag OFF)
                if (!ZYPConstant.FLG_ON_Y.equals(schdEtaChkFlg)
                        && !ZYPConstant.FLG_ON_Y.equals(finalEtaChkFlg)) {

                    for (int m = 0; m < outMsgsForVis.getValidCount(); m++) {

                        if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())
                                || INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())) {
                            inbdVisDeleteList.add(NLAL0070CommonLogic.deleteInbdVisInfo(outMsgsForVis.no(m)));
                        }

                        if (INBD_VIS_EVENT.INVOICE_NL_RECEIVE.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())
                                &&  ZYPConstant.FLG_OFF_N.equals(outMsgsForVis.no(m).inbdLtstRecFlg.getValue())) {
                            inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatest(outMsgsForVis.no(m)));
                        }
                    }

                // Pattern B (Schedule ON and Final Schedule OFF)
                } else if (ZYPConstant.FLG_ON_Y.equals(schdEtaChkFlg)
                        && !ZYPConstant.FLG_ON_Y.equals(finalEtaChkFlg)) {

                    boolean existSchFlg = false;
                    for (int m = 0; m < outMsgsForVis.getValidCount(); m++) {

                        if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())
                                && ZYPConstant.FLG_ON_Y.equals(outMsgsForVis.no(m).inbdLtstRecFlg.getValue())) {
                            inbdVisDeleteList.add(NLAL0070CommonLogic.deleteInbdVisInfo(outMsgsForVis.no(m)));
                        }

                        if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())) {
                            outMsgsForVis.no(m).etaEtdDt.setValue(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue());
                            inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatestPlus(outMsgsForVis.no(m), sysDateTs));
                            existSchFlg = true;
                        }

                        if (INBD_VIS_EVENT.INVOICE_NL_RECEIVE.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())
                                &&  ZYPConstant.FLG_ON_Y.equals(outMsgsForVis.no(m).inbdLtstRecFlg.getValue())) {
                            inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistory(outMsgsForVis.no(m)));
                        }

                    }
                    if (!existSchFlg) {
                        for (int m = 0; m < outMsgsForVis.getValidCount(); m++) {
                            if (INBD_VIS_EVENT.INVOICE_NL_RECEIVE.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())) {
                                if (!existSchFlg) {
                                    inbdVisInsertList.add(NLAL0070CommonLogic.insertInbdVisInfoForSchedule(globalMsg, i, outMsgsForVis.no(m), sysDateTs, ZYPConstant.FLG_ON_Y));
                                }
                            }
                        }
                    }

                // Pattern C (Schedule ON and Final Schedule ON)
                } else if (ZYPConstant.FLG_ON_Y.equals(schdEtaChkFlg)
                        && ZYPConstant.FLG_ON_Y.equals(finalEtaChkFlg)) {

                    boolean existFinalFlg = false;
                    boolean existSchFlg = false;
                    for (int m = 0; m < outMsgsForVis.getValidCount(); m++) {

                        if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())) {
                            outMsgsForVis.no(m).etaEtdDt.setValue(globalMsg.A.no(i).finalWhInEtaDt_A1.getValue());
                            inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatestPlus(outMsgsForVis.no(m), sysDateTs));
                            existFinalFlg = true;
                        }

                        if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())) {
                            outMsgsForVis.no(m).etaEtdDt.setValue(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue());
                            inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistoryPlus(outMsgsForVis.no(m), sysDateTs));
                            existSchFlg = true;
                        }

                        if (INBD_VIS_EVENT.INVOICE_NL_RECEIVE.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())
                                &&  ZYPConstant.FLG_ON_Y.equals(outMsgsForVis.no(m).inbdLtstRecFlg.getValue())) {
                            inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistory(outMsgsForVis.no(m)));
                        }
                    }

                    if (!existFinalFlg
                            || !existSchFlg) {
                        for (int m = 0; m < outMsgsForVis.getValidCount(); m++) {
                            if (INBD_VIS_EVENT.INVOICE_NL_RECEIVE.equals(outMsgsForVis.no(m).inbdVisEventCd.getValue())) {
                                if (!existFinalFlg) {
                                    inbdVisInsertList.add(NLAL0070CommonLogic.insertInbdVisInfoForFinalSchedule(globalMsg, i, outMsgsForVis.no(m), sysDateTs, ZYPConstant.FLG_ON_Y));
                                }

                                if (!existSchFlg) {
                                    inbdVisInsertList.add(NLAL0070CommonLogic.insertInbdVisInfoForSchedule(globalMsg, i, outMsgsForVis.no(m), sysDateTs, ZYPConstant.FLG_OFF_N));
                                }
                            }
                        }
                    }

                }

            } else {

                for (int k = 0; k < whSchdTMsgArray.getValidCount(); k++) {

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("glblCmpyCd", whSchdTMsgArray.no(k).glblCmpyCd.getValue());
                    map.put("whSchdPk", whSchdTMsgArray.no(k).whSchdPk.getValue());
                    map.put("rwsStsCd", RWS_STS.CANCELED);

                    // Container w. W/H Change & ASN
                    if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(whSchdTMsgArray.no(k).sceOrdTpCd.getValue())) {
                        map.put("rwsRefNum", whSchdTMsgArray.no(k).rwsRefNum.getValue());
                    }

                    INBD_VISTMsgArray outMsgs = new INBD_VISTMsgArray();

                    if (INBD_SRC_TP.INVOICE.equals(whSchdTMsgArray.no(k).inbdSrcTpCd.getValue())
                            || INBD_SRC_TP.CONTAINER.equals(whSchdTMsgArray.no(k).inbdSrcTpCd.getValue())
                            || INBD_SRC_TP.SCP_ORDER.equals(whSchdTMsgArray.no(k).inbdSrcTpCd.getValue())
                            || INBD_SRC_TP.SCP_ASN.equals(whSchdTMsgArray.no(k).inbdSrcTpCd.getValue())) {

                        // SQL calls
                        NLAL0070Query.getInstance().getInfoListToConnectInbdVisForImport(map, globalMsg);

                        // Container w. W/H Change & ASN
                        if (NLXSceConst.SCE_ORD_TP_CD_CA.equals(whSchdTMsgArray.no(k).sceOrdTpCd.getValue())) {
                            if (!ZYPCommonFunc.hasValue(whSchdTMsgArray.no(k).truckCntnrPk)) {
                                bizMsg.setMessageInfo("NLAM0260E", new String[] {getRwsStsNm(globalMsg.G.no(0).rwsStsCd_G1.getValue()) });
                                return false;
                            }

                        }
                        
                        outMsgs = NLAL0070CommonLogic.getInbdVisInfoOfImportForUpdateNoWait(globalMsg);

                        if (ZYPCommonFunc.hasValue(globalMsg.G.no(0).rwsStsCd_G1)) {
                            if (!(RWS_STS.SAVED.equals(globalMsg.G.no(0).rwsStsCd_G1.getValue())
                                    || RWS_STS.PRINTED.equals(globalMsg.G.no(0).rwsStsCd_G1.getValue()))) {
                                bizMsg.setMessageInfo("NLAM0260E", new String[] {getRwsStsNm(globalMsg.G.no(0).rwsStsCd_G1.getValue()) });
                                return false;
                            }
                        }

                    } else if (INBD_SRC_TP.PO_RECEIVING.equals(whSchdTMsgArray.no(k).inbdSrcTpCd.getValue())) {

                        // SQL calls
                        NLAL0070Query.getInstance().getInfoListToConnectInbdVisForDomestic(map, globalMsg);

                        outMsgs = NLAL0070CommonLogic.getInbdVisInfoOfDomesticForUpdateNoWait(globalMsg);

                        if (ZYPCommonFunc.hasValue(globalMsg.G.no(0).rwsStsCd_G1)) {
                            if (!(RWS_STS.SAVED.equals(globalMsg.H.no(0).rwsStsCd_H1.getValue())
                                    || RWS_STS.PRINTED.equals(globalMsg.H.no(0).rwsStsCd_H1.getValue()))) {
                                bizMsg.setMessageInfo("NLAM0260E", new String[]{getRwsStsNm(globalMsg.H.no(0).rwsStsCd_H1.getValue())});
                                return false;
                            }
                        }

                    }

                    String schdEtaChkFlg = globalMsg.A.no(i).schdEtaChkFlg_A1.getValue();
                    String finalEtaChkFlg = globalMsg.A.no(i).finalEtaChkFlg_A1.getValue();

                    // For Latest Record Infomation
                    List<INBD_VISTMsg> ltstInfoAry = new ArrayList<INBD_VISTMsg>();
                    INBD_VISTMsg ltstInfo = null;

                    // Check Inbound Visibility Records
                    String ltstScheduleExist = ZYPConstant.FLG_OFF_N;
                    String ltstFinalExist = ZYPConstant.FLG_OFF_N;

                    String eventCd = null;
                    String inbdLtstRecFlg = null;

                    boolean scheduleRegister = false;
                    boolean existFinalFlg = false;
                    boolean existSchFlg = false;

                    for (int l = 0; l < outMsgs.getValidCount(); l++) {
                        eventCd = outMsgs.no(l).inbdVisEventCd.getValue();
                        inbdLtstRecFlg = outMsgs.no(l).inbdLtstRecFlg.getValue();

                        if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(eventCd)
                                && ZYPConstant.FLG_ON_Y.equals(inbdLtstRecFlg)) {
                            ltstScheduleExist = ZYPConstant.FLG_ON_Y;
                        }
                        if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(eventCd)
                                && ZYPConstant.FLG_ON_Y.equals(inbdLtstRecFlg)) {
                            ltstFinalExist = ZYPConstant.FLG_ON_Y;
                        }
                        if (ZYPConstant.FLG_ON_Y.equals(inbdLtstRecFlg)) {
                            if (INBD_VIS_EVENT.WH_CHANGE_CR.equals(eventCd)) {
                                continue;
                            }
                            ltstInfo = new INBD_VISTMsg();
                            EZDMsg.copy(outMsgs.no(l), null, ltstInfo, null);
                            ltstInfoAry.add(ltstInfo);
                        }
                    }

                    // Pattern A (Schedule Check Flag OFF and Final Schedule Check Flag OFF)
                    if (!ZYPConstant.FLG_ON_Y.equals(schdEtaChkFlg)
                            && !ZYPConstant.FLG_ON_Y.equals(finalEtaChkFlg)) {

                        for (int m = 0; m < outMsgs.getValidCount(); m++) {

                            // Pattern 1 
                            // Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // One of Final Schedule Event Records have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            if (ZYPConstant.FLG_ON_Y.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_OFF_N.equals(ltstScheduleExist)) {

                                if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {
                                    inbdVisDeleteList.add(NLAL0070CommonLogic.deleteInbdVisInfo(outMsgs.no(m)));
                                }

                                if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && !scheduleRegister) {
                                    inbdVisDeleteList.add(NLAL0070CommonLogic.deleteInbdVisInfo(outMsgs.no(m)));
                                    scheduleRegister = true;
                                }

                                String inbdVisEventCd = whSchdTMsgArray.no(k).inbdVisEventCd.getValue();
                                if (inbdVisEventCd.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        &&  ZYPConstant.FLG_OFF_N.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatest(outMsgs.no(m)));
                                }

                            // Pattern 2 
                            // One of Schedule Event Records have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // Final Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            } else if (ZYPConstant.FLG_OFF_N.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_ON_Y.equals(ltstScheduleExist)) {

                                if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {
                                    inbdVisDeleteList.add(NLAL0070CommonLogic.deleteInbdVisInfo(outMsgs.no(m)));
                                }

                                String inbdVisEventCd = whSchdTMsgArray.no(k).inbdVisEventCd.getValue();
                                if (inbdVisEventCd.equals(outMsgs.no(m).inbdVisEventCd.getValue())) {
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatest(outMsgs.no(m)));
                                }

                            // Pattern 3 
                            // Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // Final Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            } else if (ZYPConstant.FLG_OFF_N.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_OFF_N.equals(ltstScheduleExist)) {

                                // do nothing
                            }
                        }

                    // Pattern B (Schedule ON and Final Schedule OFF)
                    } else if (ZYPConstant.FLG_ON_Y.equals(schdEtaChkFlg)
                            && !ZYPConstant.FLG_ON_Y.equals(finalEtaChkFlg)) {


                        for (int m = 0; m < outMsgs.getValidCount(); m++) {

                            // Pattern 1 
                            // Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // One of Final Schedule Event Records have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            if (ZYPConstant.FLG_ON_Y.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_OFF_N.equals(ltstScheduleExist)) {

                                if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {
                                    inbdVisDeleteList.add(NLAL0070CommonLogic.deleteInbdVisInfo(outMsgs.no(m)));
                                }

                                if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && !scheduleRegister) {

                                    outMsgs.no(m).etaEtdDt.setValue(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue());
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatestPlus(outMsgs.no(m), sysDateTs));
                                    scheduleRegister = true;
                                }

                            // Pattern 2 
                            // One of Schedule Event Records have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // Final Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            } else if (ZYPConstant.FLG_OFF_N.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_ON_Y.equals(ltstScheduleExist)) {

                                if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(ltstScheduleExist)) {

                                    outMsgs.no(m).etaEtdDt.setValue(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue());
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatestPlus(outMsgs.no(m), sysDateTs));
                                }

                            // Pattern 3 
                            // Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // Final Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            } else if (ZYPConstant.FLG_OFF_N.equals(ltstFinalExist)
                                            && ZYPConstant.FLG_OFF_N.equals(ltstScheduleExist)) {

                                if (!existSchFlg) {
                                    inbdVisInsertList.addAll(NLAL0070CommonLogic.insertInbdVisInfoForSchedule(globalMsg, i, ltstInfoAry, sysDateTs, ZYPConstant.FLG_ON_Y));
                                    existSchFlg = true;
                                }

                                String inbdVisEventCd = whSchdTMsgArray.no(k).inbdVisEventCd.getValue();
                                if (inbdVisEventCd.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {

                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistory(outMsgs.no(m)));
                                }
                            }

                        }

                    // Pattern C (Schedule ON and Final Schedule ON)s
                    } else if (ZYPConstant.FLG_ON_Y.equals(schdEtaChkFlg)
                            && ZYPConstant.FLG_ON_Y.equals(finalEtaChkFlg)) {

                        for (int m = 0; m < outMsgs.getValidCount(); m++) {

                            // Pattern 1 
                            // Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // One of Final Schedule Event Records have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            if (ZYPConstant.FLG_ON_Y.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_OFF_N.equals(ltstScheduleExist)) {

                                if (INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {

                                    outMsgs.no(m).etaEtdDt.setValue(globalMsg.A.no(i).finalWhInEtaDt_A1.getValue());
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToLatestPlus(outMsgs.no(m), sysDateTs));
                                }

                                if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && !scheduleRegister) {

                                    outMsgs.no(m).etaEtdDt.setValue(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue());
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistoryPlus(outMsgs.no(m), sysDateTs));
                                    scheduleRegister = true;
                                }

                            // Pattern 2 
                            // One of Schedule Event Records have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // Final Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            } else if (ZYPConstant.FLG_OFF_N.equals(ltstFinalExist)
                                    && ZYPConstant.FLG_ON_Y.equals(ltstScheduleExist)) {

                                if (!existFinalFlg) {
                                    inbdVisInsertList.addAll(NLAL0070CommonLogic.insertInbdVisInfoForFinalSchedule(globalMsg, i, ltstInfoAry, sysDateTs, ZYPConstant.FLG_ON_Y));
                                    existFinalFlg = true;
                                }

                                if (INBD_VIS_EVENT.WH_SCHEDULE_ENTRY.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {

                                    outMsgs.no(m).etaEtdDt.setValue(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue());
                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistoryPlus(outMsgs.no(m), sysDateTs));
                                }

                            // Pattern 3 
                            // Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            // Final Schedule Event Records don"t have "Y" at inbdLtstRecFlag in INBD_VIS table.
                            } else if (ZYPConstant.FLG_OFF_N.equals(ltstFinalExist)
                                            && ZYPConstant.FLG_OFF_N.equals(ltstScheduleExist)) {

                                if (!existFinalFlg) {
                                    inbdVisInsertList.addAll(NLAL0070CommonLogic.insertInbdVisInfoForFinalSchedule(globalMsg, i, ltstInfoAry, sysDateTs, ZYPConstant.FLG_ON_Y));
                                    existFinalFlg = true;
                                }

                                if (!existSchFlg) {
                                    inbdVisInsertList.addAll(NLAL0070CommonLogic.insertInbdVisInfoForSchedule(globalMsg, i, ltstInfoAry, sysDateTs, ZYPConstant.FLG_OFF_N));
                                    existSchFlg = true;
                                }

                                String inbdVisEventCd = whSchdTMsgArray.no(k).inbdVisEventCd.getValue();
                                if (inbdVisEventCd.equals(outMsgs.no(m).inbdVisEventCd.getValue())
                                        && ZYPConstant.FLG_ON_Y.equals(outMsgs.no(m).inbdLtstRecFlg.getValue())) {

                                    inbdVisUpdateList.add(NLAL0070CommonLogic.updateInbdVisInfoToHistory(outMsgs.no(m)));
                                }
                            }
                        }
                    }
                }
            }
        }
        int cnt0 = whSchdUpdateList.size();
        if (cnt0 > 0) {
            WH_SCHDTMsg[] tmsgs = new WH_SCHDTMsg[cnt0];

            for (int i = 0; i < cnt0; i++) {
                tmsgs[i] = whSchdUpdateList.get(i);
            }
            S21FastTBLAccessor.update(tmsgs);

            // Set Update Time For Updating Screen Info
            ezUpTime = tmsgs[0].ezUpTime.getValue();
            ezUpTimeZone = tmsgs[0].ezUpTimeZone.getValue();
        }

        int cnt1 = inbdVisDeleteList.size();
        if (cnt1 > 0) {
            INBD_VISTMsg[] tmsgs = new INBD_VISTMsg[cnt1];

            for (int i = 0; i < cnt1; i++) {
                tmsgs[i] = inbdVisDeleteList.get(i);
            }
            S21FastTBLAccessor.update(tmsgs);
            S21FastTBLAccessor.removeLogical(tmsgs);
        }

        int cnt2 = inbdVisUpdateList.size();
        if (cnt2 > 0) {
            INBD_VISTMsg[] tmsgs = new INBD_VISTMsg[cnt2];

            for (int i = 0; i < cnt2; i++) {
                tmsgs[i] = inbdVisUpdateList.get(i);
            }
            S21FastTBLAccessor.update(tmsgs);
        }

        int cnt3 = inbdVisInsertList.size();
        if (cnt3 > 0) {
            INBD_VISTMsg[] tmsgs = new INBD_VISTMsg[cnt3];

            for (int i = 0; i < cnt3; i++) {
                tmsgs[i] = inbdVisInsertList.get(i);
            }
            S21FastTBLAccessor.insert(tmsgs);
        }

        // Set update Time to Screen Message
        if (scrnUpdateList.size() > 0) {

            String ezUpTimeGMT = NLAL0070CommonLogic.convertDateTimeToGMT(ezUpTime, ezUpTimeZone);

            for (int index : scrnUpdateList) {
                globalMsg.A.no(index).ezUpTime_A1.setValue(ezUpTimeGMT);
                globalMsg.A.no(index).xxChkBox_A1.clear();

                for (int j = 0; j < globalMsg.J.getValidCount(); j++) {
                    if (globalMsg.J.no(j).invtyLocCd_J1.getValue().equals(globalMsg.A.no(index).invtyLocCd_A1.getValue())
                            && globalMsg.J.no(j).rwsRefNum_J1.getValue().equals(globalMsg.A.no(index).rwsRefNum_A1.getValue())) {
                        globalMsg.J.no(j).ezUpTime_J1.setValue(ezUpTimeGMT);
                    }
                }
            }
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NLAL0070SMsg
     * @param globalMsgBCount int
     * @return boolean
     */
    private boolean checkSelectCheck(NLAL0070SMsg globalMsg, int globalMsgBCount) {

        for (int i = 0; i < globalMsgBCount; i++) {
            // If the checkbox is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                return true;
            } else {
                // do nothing
            }
        }
        return false;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param globalMsgBCount int
     * @return boolean
     */
    private boolean checkFormatWhEtaTemp(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int globalMsgACount) {

        List<String> errgyoNoList = new ArrayList<String>();

        for (int i = 0; i < globalMsgACount; i++) {

            // If the checkbox is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).tempWhInEtaDt_A1)) {
                    // WH ETA TEMP Appropriate date check
                    if (!ZYPDateUtil.checkDate(globalMsg.A.no(i).tempWhInEtaDt_A1.getValue())) {
                        errgyoNoList.add(String.valueOf(i));
                    } else {
                        // do nothing
                    }
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }
        }

        if (!errgyoNoList.isEmpty()) {
            setErrorPageData(errgyoNoList, globalMsg, bizMsg, "ZZM9032E", CHECK_WETA_MEMO);
            bizMsg.setCommitSMsg(true);
            return false;
        } else {
            // do nothing
        }

        return true;
    }

    /**
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param globalMsgACount int
     * @return boolean
     */
    private boolean checkLtForTrmEtaTemp(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int globalMsgACount) {

        List<String> errgyoNoList = new ArrayList<String>();

        for (int i = 0; i < globalMsgACount; i++) {

            // If the checkbox is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {

                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).tempWhInEtaDt_A1)
                        && !SCHD_TP.DOMESTIC.equals(globalMsg.A.no(i).schdTpCd_A1.getValue())) {

                     IMPT_INV_LTTMsgArray outMsgs = NLAL0070CommonLogic.getImptInvLtInfo(globalMsg, bizMsg, i);
                     if (outMsgs.getValidCount() == 0) {
                         errgyoNoList.add(String.valueOf(i));
                         setErrorPageData(errgyoNoList, globalMsg, bizMsg, "NLZM1005E", CHECK_TERMINAL_TERMINAL_ETA_MEMO);
                         bizMsg.setCommitSMsg(true);
                         return false;
                     }
                }

            }
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param globalMsgBCount int
     * @return boolean
     */
    private boolean checkFormatScheduleEta(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int globalMsgBCount) {

        List<String> errgyoNoList = new ArrayList<String>();

        for (int i = 0; i < globalMsgBCount; i++) {
            // If the checkbox is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {

                // Schedule ETA Appropriate date check
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).schdWhInEtaDt_A1)
                        && !ZYPDateUtil.checkDate(globalMsg.A.no(i).schdWhInEtaDt_A1.getValue())) {
                    errgyoNoList.add(String.valueOf(i));
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }
        }

        if (!errgyoNoList.isEmpty()) {
            setErrorPageData(errgyoNoList, globalMsg, bizMsg, "ZZM9032E", CHECK_SCHEDULE_WETA);
            bizMsg.setCommitSMsg(true);
            return false;
        } else {
            // do nothing
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param globalMsgBCount int
     * @return boolean
     */
    private boolean checkFormatRailAvailableDate(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int globalMsgACount) {

        List<String> errgyoNoList = new ArrayList<String>();

        for (int i = 0; i < globalMsgACount; i++) {

            // If the checkbox is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).railAvalDt_A1)) {
                    // Final Schedule ETA Appropriate date check
                    if (!ZYPDateUtil.checkDate(globalMsg.A.no(i).railAvalDt_A1.getValue())) {
                        errgyoNoList.add(String.valueOf(i));
                    } else {
                        // do nothing
                    }
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }
        }

        if (!errgyoNoList.isEmpty()) {
            setErrorPageData(errgyoNoList, globalMsg, bizMsg, "ZZM9032E", CHECK_RAIL_AVAILABLE_DATE);
            bizMsg.setCommitSMsg(true);
            return false;
        } else {
            // do nothing
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param globalMsgBCount int
     * @return boolean
     */
    private boolean checkFormatFinalScheduleEta(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int globalMsgACount) {

        List<String> errgyoNoList = new ArrayList<String>();

        for (int i = 0; i < globalMsgACount; i++) {

            // If the checkbox is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).finalWhInEtaDt_A1)) {
                    // Final Schedule ETA Appropriate date check
                    if (!ZYPDateUtil.checkDate(globalMsg.A.no(i).finalWhInEtaDt_A1.getValue())) {
                        errgyoNoList.add(String.valueOf(i));
                    } else {
                        // do nothing
                    }
                } else {
                    // do nothing
                }
            } else {
                // do nothing
            }
        }

        if (!errgyoNoList.isEmpty()) {
            setErrorPageData(errgyoNoList, globalMsg, bizMsg, "ZZM9032E", CHECK_FINAL_SCHEDULE_WETA);
            bizMsg.setCommitSMsg(true);
            return false;
        } else {
            // do nothing
        }

        return true;
    }

//Unnecessary
//    /**
//     * <dd>The method explanation: The business peculiarity
//     * @param globalMsg NLAL0070SMsg
//     * @param bizMsg NLAL0070CMsg
//     * @param globalMsgACount int
//     * @return
//     */
//    private boolean checkDiffTerminalEta(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int globalMsgACount) {
//
//        List<String> errgyoNoList = new ArrayList<String>();
//
//        checkLoop:
//        for (int i = 0; i < globalMsgACount; i++) {
//
//            // If the checkbox is selected
//            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
//
//                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).imptTrmEtaDt_A1)) {
//                    String terminalEtaDt = globalMsg.A.no(i).imptTrmEtaDt_A1.getValue();
//
//                    // Final Schedule ETA Appropriate date check
//                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).finalWhInEtaDt_A1)) {
//                        String finalEtaDt = globalMsg.A.no(i).finalWhInEtaDt_A1.getValue();
//
//                        int diff = ZYPDateUtil.getDiffDays(finalEtaDt, terminalEtaDt);
//                        if (diff < 0) {
//                            errgyoNoList.add(String.valueOf(i));
//                            setErrorPageData(errgyoNoList, globalMsg, bizMsg, "NLAM0190E", CHECK_DIFF_TERMINAL_WETA_FINAL_WETA);
//                            break checkLoop;
//                        } else {
//                            // do nothing
//                        }
//                    } else {
//                        // do nothing
//                    }
//
//                    // Shedule ETA Appropriate date check
//                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).schdWhInEtaDt_A1)) {
//                        String scheduleEtaDt = globalMsg.A.no(i).schdWhInEtaDt_A1.getValue();
//
//                        int diff = ZYPDateUtil.getDiffDays(scheduleEtaDt, terminalEtaDt);
//                        if (diff < 0) {
//                            errgyoNoList.add(String.valueOf(i));
//                            setErrorPageData(errgyoNoList, globalMsg, bizMsg, "NLAM0190E", CHECK_DIFF_TERMINAL_WETA_SCHEDULE_WETA);
//                            break checkLoop;
//                        } else {
//                            // do nothing
//                        }
//                    } else {
//                        // do nothing
//                    }
//
//                }
//            }
//        }
//
//        if (!errgyoNoList.isEmpty()) {
//            bizMsg.setCommitSMsg(true);
//            return false;
//        } else {
//            // do nothing
//        }
//
//        return true;
//    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param list List
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param errId String
     * @param type int
     * @return void
     */
    private void setErrorPageData(List<String> list, NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, String errId, int type) {

        if (!list.isEmpty()) {

            int errcount = Integer.parseInt(list.get(0));
            int pagenationFrom = errcount / bizMsg.A.length();
            pagenationFrom = pagenationFrom * bizMsg.A.length();
            int i = pagenationFrom;

            for (; i < pagenationFrom + bizMsg.A.length(); i++) {
                if (i < globalMsg.A.getValidCount()) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);

                    for (int j = 0; j < list.size(); j++) {
                        if (Integer.parseInt(list.get(j)) == i) {
                            if (CHECK_SCHEDULE_WETA == type) {
                                bizMsg.A.no(i - pagenationFrom).schdWhInEtaDt_A1.setErrorInfo(1, errId, new String[] {"Schedule WETA" });
                            } else if (CHECK_WETA_MEMO == type) {
                                bizMsg.A.no(i - pagenationFrom).tempWhInEtaDt_A1.setErrorInfo(1, errId, new String[] {"WETA Memo" });
                            } else if (CHECK_FINAL_SCHEDULE_WETA == type) {
                                bizMsg.A.no(i - pagenationFrom).finalWhInEtaDt_A1.setErrorInfo(1, errId, new String[] {"Final Schedule WETA" });
                            } else if (CHECK_RAIL_AVAILABLE_DATE == type) {
                                bizMsg.A.no(i - pagenationFrom).railAvalDt_A1.setErrorInfo(1, errId, new String[] {"Rail Available Date" });
                            } else if (CHECK_DIFF_TERMINAL_WETA_FINAL_WETA == type) {
                                bizMsg.A.no(i - pagenationFrom).finalWhInEtaDt_A1.setErrorInfo(1, errId, null);
                            } else if (CHECK_DIFF_TERMINAL_WETA_SCHEDULE_WETA == type) {
                                bizMsg.A.no(i - pagenationFrom).schdWhInEtaDt_A1.setErrorInfo(1, errId, null);
                            } else if (CHECK_TERMINAL_TERMINAL_ETA_MEMO == type) {
                                bizMsg.A.no(i - pagenationFrom).tempWhInEtaDt_A1.setErrorInfo(1, errId, null);
                            } else {
                                // do nothing
                            }
                        }
                    }
                } else {
                    break;
                }
            }

            bizMsg.A.setValidCount(i - pagenationFrom);
            bizMsg.xxPageShowFromNum_A1.setValue(pagenationFrom + 1);
            bizMsg.xxPageShowToNum_A1.setValue(pagenationFrom + bizMsg.A.getValidCount());
        } else {
            // do nothing
        }
    }

    /**
     * Get RWS Status Name
     * 
     * @param rwsStsCd RWS Status Code
     * @return RWS Status Name
     */
    private String getRwsStsNm(String rwsStsCd) {

        if (RWS_STS.RECEIVING.equals(rwsStsCd)) {
            return "RECEIVING";
        } else if (RWS_STS.RECEIVED.equals(rwsStsCd)) {
            return "RECEIVED";
        } else if (RWS_STS.CANCELED.equals(rwsStsCd)) {
            return "CANCELED";
        } else if (RWS_STS.SAVED.equals(rwsStsCd)) {
            return "SAVED";
        } else if (RWS_STS.PRINTED.equals(rwsStsCd)) {
            return "PRINTED";
        }

        return "";
    }
}

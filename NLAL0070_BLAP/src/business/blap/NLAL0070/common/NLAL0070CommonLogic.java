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
 * ----------------------------------------------------------------------
 * 07/28/2009   Fujitsu         FAP)H.Naoki     Create          N/A
 * 12/29/2009   Fujitsu         A.Akabane       Update          N/A
 * 10/13/2010   Fujitsu         S.Yoshida       Update          N/A
 *</pre>
 */
package business.blap.NLAL0070.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLAL0070.NLAL0070CMsg;
import business.blap.NLAL0070.NLAL0070SMsg;
import business.blap.NLAL0070.constant.NLAL0070Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.IMPT_INV_LTTMsg;
import business.db.IMPT_INV_LTTMsgArray;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.MDSETMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.WH_SCHDTMsg;
import business.db.WH_SCHDTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NLAL0070CommonLogic implements NLAL0070Constant {

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    public static void setScrnInputData(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg) {

        int pagenationFrom = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int i = pagenationFrom;
        int cnt = 0;

        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (cnt < bizMsg.A.getValidCount()) {
                globalMsg.A.no(i).xxChkBox_A1.setValue(bizMsg.A.no(cnt).xxChkBox_A1.getValue());
                globalMsg.A.no(i).whInPrtyFlg_A1.setValue(bizMsg.A.no(cnt).whInPrtyFlg_A1.getValue());
                globalMsg.A.no(i).tempWhInEtaDt_A1.setValue(bizMsg.A.no(cnt).tempWhInEtaDt_A1.getValue());
                globalMsg.A.no(i).schdWhInEtaDt_A1.setValue(bizMsg.A.no(cnt).schdWhInEtaDt_A1.getValue());
                globalMsg.A.no(i).schdEtaChkFlg_A1.setValue(bizMsg.A.no(cnt).schdEtaChkFlg_A1.getValue());
                globalMsg.A.no(i).finalWhInEtaDt_A1.setValue(bizMsg.A.no(cnt).finalWhInEtaDt_A1.getValue());
                globalMsg.A.no(i).finalEtaChkFlg_A1.setValue(bizMsg.A.no(cnt).finalEtaChkFlg_A1.getValue());
                globalMsg.A.no(i).railAvalDt_A1.setValue(bizMsg.A.no(cnt).railAvalDt_A1.getValue());
                globalMsg.A.no(i).apptTmTxt_A1.setValue(bizMsg.A.no(cnt).apptTmTxt_A1.getValue());
                globalMsg.A.no(i).apptDrygCarrCd_A2.setValue(bizMsg.A.no(cnt).apptDrygCarrCd_A2.getValue());
                globalMsg.A.no(i).whSchdCmntTxt_A1.setValue(bizMsg.A.no(cnt).whSchdCmntTxt_A1.getValue());
            } else {
                break;
            }
            cnt++;
        }

    }

    /**
     * 
     * @param bizMsg NLAL0070CMsg
     * @param inMsg MDSETMsg
     * @return MDSETMsg
     */
    public static ALL_MDSE_VTMsg findMdseInfo(NLAL0070CMsg bizMsg) {
        ALL_MDSE_VTMsg cond = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;
        cond.setSQLID("003");
        cond.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_BK.getValue());
        cond.setConditionValue("mdseCd01", bizMsg.mdseCd_P1.getValue());

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(cond);
        if (outAllMdseVTMsg == null || outAllMdseVTMsg.length() == 0) {
            return null;
        } else {
            return (ALL_MDSE_VTMsg)outAllMdseVTMsg.get(0);
        }
    }

    /**
     * 
     * @param bizMsg NLAL0070CMsg
     * @param globalMsg NLAL0070SMsg
     * @param index index
     * @return WH_SCHDTMsgArray
     */
    public static WH_SCHDTMsgArray getWhSchdInfoForUpdateNoWait(NLAL0070CMsg bizMsg, NLAL0070SMsg globalMsg, int index) {

        List<WH_SCHDTMsg> whSchdAry = new ArrayList<WH_SCHDTMsg>();
        WH_SCHDTMsgArray outMsgs = null;
        String ezUpTimeGMT = null;

        for (int j = 0; j < globalMsg.J.getValidCount(); j++) {
            if (globalMsg.J.no(j).invtyLocCd_J1.getValue().equals(globalMsg.A.no(index).invtyLocCd_A1.getValue())
                    && globalMsg.J.no(j).rwsRefNum_J1.getValue().equals(globalMsg.A.no(index).rwsRefNum_A1.getValue())) {

                WH_SCHDTMsg inMsg = new WH_SCHDTMsg();
                inMsg.setConditionValue("glblCmpyCd01",        bizMsg.glblCmpyCd_BK.getValue());
                inMsg.setConditionValue("whSchdRefKeyNumSq01", globalMsg.J.no(j).whSchdRefKeyNumSq_J1.getValue());
                inMsg.setConditionValue("invtyLocCd01",        globalMsg.A.no(index).invtyLocCd_A1.getValue());
                inMsg.setConditionValue("ltstRecFlg01",        ZYPConstant.FLG_ON_Y);
                inMsg.setSQLID("012");

                outMsgs = (WH_SCHDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

                for (int i = 0; i < outMsgs.getValidCount(); i++) {

                    ezUpTimeGMT = convertDateTimeToGMT(outMsgs.no(i).ezUpTime.getValue(), outMsgs.no(i).ezUpTimeZone.getValue());

                    if (ezUpTimeGMT.compareTo(globalMsg.J.no(j).ezUpTime_J1.getValue()) > 0) {
                        bizMsg.setMessageInfo("NZZM0003E");
                        S21InfoLogOutput.println("NLAM0259E",
                                new String[]{outMsgs.no(i).ezUpAplID.getValue(), outMsgs.no(i).ezUpUserID.getValue()});
                        return null;
                    }

                    whSchdAry.add(outMsgs.no(i));
                }
            }
        }

        WH_SCHDTMsgArray whSchdTMsgAry = new WH_SCHDTMsgArray();
        whSchdTMsgAry.setMsgList(whSchdAry.toArray(new EZDTMsg[0]));
        whSchdTMsgAry.setValidCount(whSchdAry.size());

        return whSchdTMsgAry;
    }

    /**
     * 
     * @param globalMsg NLAL0070SMsg
     * @param inMsg WH_SCHDTMsg
     * @param index index
     * @return WH_SCHDTMsg
     */
    public static WH_SCHDTMsg updateWhSchdInfo(NLAL0070SMsg globalMsg, WH_SCHDTMsg inMsg, int index) {

        WH_SCHDTMsg whSchdTMsg = new WH_SCHDTMsg();
        EZDMsg.copy(inMsg, null, whSchdTMsg, null);
        whSchdTMsg.tempWhInEtaDt.setValue(globalMsg.A.no(index).tempWhInEtaDt_A1.getValue());
        whSchdTMsg.railAvalDt.setValue(globalMsg.A.no(index).railAvalDt_A1.getValue());
        whSchdTMsg.apptTmTxt.setValue(globalMsg.A.no(index).apptTmTxt_A1.getValue());
        whSchdTMsg.apptDrygCarrCd.setValue(globalMsg.A.no(index).apptDrygCarrCd_A2.getValue());
        whSchdTMsg.whSchdCmntTxt.setValue(globalMsg.A.no(index).whSchdCmntTxt_A1.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).whInPrtyFlg_A1.getValue())) {
            whSchdTMsg.whInPrtyFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            whSchdTMsg.whInPrtyFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).schdEtaChkFlg_A1.getValue())) {
            whSchdTMsg.schdWhInEtaDt.setValue(globalMsg.A.no(index).schdWhInEtaDt_A1.getValue());
        } else {
            whSchdTMsg.schdWhInEtaDt.clear();
        }

        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).finalEtaChkFlg_A1.getValue())) {
            whSchdTMsg.finalWhInEtaDt.setValue(globalMsg.A.no(index).finalWhInEtaDt_A1.getValue());
        } else {
            whSchdTMsg.finalWhInEtaDt.clear();
        }

        // set LTST_WH_IN_ETA_DT
        if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).schdEtaChkFlg_A1.getValue())
                && ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).finalEtaChkFlg_A1.getValue())) {
            whSchdTMsg.ltstWhInEtaDt.setValue(globalMsg.A.no(index).finalWhInEtaDt_A1.getValue());
        } else if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).schdEtaChkFlg_A1.getValue())
                && !ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).finalEtaChkFlg_A1.getValue())) {
            whSchdTMsg.ltstWhInEtaDt.setValue(globalMsg.A.no(index).schdWhInEtaDt_A1.getValue());
        } else if (!ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).schdEtaChkFlg_A1.getValue())
                && !ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(index).finalEtaChkFlg_A1.getValue())) {
            whSchdTMsg.ltstWhInEtaDt.setValue(globalMsg.A.no(index).orgWhInEtaDt_A1.getValue());
        }

        return whSchdTMsg;
    }

    /**
     * @param refMsg WH_SCHDTMsg
     * @return RWS_HDRTMsgArray
     */
    public static RWS_HDRTMsgArray getRWSInfoOfInvoiceOfNLReceive(WH_SCHDTMsg refMsg) {

        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", refMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("rwsRefNum01", refMsg.imptInvNum.getValue());
        inMsg.setConditionValue("whCd01", refMsg.invtyLocCd.getValue());

        inMsg.setSQLID("011");

        RWS_HDRTMsgArray outMsgs = (RWS_HDRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsgs;
    }

    /**
     * 
     * @param refMsg WH_SCHDTMsg
     * @return RWS_HDRTMsgArray
     */
    public static RWS_HDRTMsgArray getRWSInfoOfContainerOfNLReceive(WH_SCHDTMsg refMsg) {

        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", refMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("rwsRefNum01", refMsg.rwsRefNum.getValue());
        inMsg.setConditionValue("imptCntnrLotNum01", refMsg.imptCntnrLotNum.getValue());
        inMsg.setConditionValue("whCd01", refMsg.invtyLocCd.getValue());

        inMsg.setSQLID("012");

        RWS_HDRTMsgArray outMsgs = (RWS_HDRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsgs;
    }

    /**
     * @param refMsg WH_SCHDTMsg
     * @return INBD_VISTMsgArray
     */
    public static INBD_VISTMsgArray getInbdVisInfoOfInvoiceOfNLReceiveForUpdateNoWait(WH_SCHDTMsg refMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setConditionValue("glblCmpyCd01", refMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("imptInvNum01", refMsg.imptInvNum.getValue());
        inMsg.setConditionValue("visLocCd01", refMsg.invtyLocCd.getValue());
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);

        inMsg.setSQLID("042");
        INBD_VISTMsgArray outMsgs = (INBD_VISTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        return outMsgs;
    }

    /**
     * @param refMsg WH_SCHDTMsg
     * @return INBD_VISTMsgArray
     */
    public static INBD_VISTMsgArray getInbdVisInfoOfContainerOfNLReceiveForUpdateNoWait(WH_SCHDTMsg refMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setConditionValue("glblCmpyCd01", refMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("imptCntnrLotNum01", refMsg.imptCntnrLotNum.getValue());
        inMsg.setConditionValue("imptCntnrNum01", refMsg.rwsRefNum.getValue());
        inMsg.setConditionValue("visLocCd01", refMsg.invtyLocCd.getValue());
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);

        inMsg.setSQLID("043");
        INBD_VISTMsgArray outMsgs = (INBD_VISTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        return outMsgs;
    }

    /**
     * @param globalMsg NLAL0070SMsg
     * @return INBD_VISTMsgArray
     */
    public static INBD_VISTMsgArray getInbdVisInfoOfImportForUpdateNoWait(NLAL0070SMsg globalMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setConditionValue("glblCmpyCd01", globalMsg.glblCmpyCd_BK.getValue());
        inMsg.setConditionValue("imptInvNum01", globalMsg.G.no(0).imptInvNum_G1.getValue());
        inMsg.setConditionValue("imptInvClsCd01", globalMsg.G.no(0).imptInvClsCd_G1.getValue());
        inMsg.setConditionValue("imptInvVerNum01", globalMsg.G.no(0).imptInvVerNum_G1.getValue());
        inMsg.setConditionValue("imptInvSlpClsCd01", globalMsg.G.no(0).imptInvSlpClsCd_G1.getValue());
        inMsg.setConditionValue("imptInvDoNum01", globalMsg.G.no(0).imptInvDoNum_G1.getValue());
        inMsg.setConditionValue("imptDoClsCd01", globalMsg.G.no(0).imptDoClsCd_G1.getValue());
        inMsg.setConditionValue("imptPackSlpNum01", globalMsg.G.no(0).imptPackSlpNum_G1.getValue());
        inMsg.setConditionValue("imptPackSlpLineNum01", globalMsg.G.no(0).imptPackSlpLineNum_G1.getValue());
        inMsg.setConditionValue("imptPackSlpDtlLineNum01", globalMsg.G.no(0).imptPackSlpDtlLineNum_G1.getValue());
        inMsg.setConditionValue("mdseCd01", globalMsg.G.no(0).mdseCd_G1.getValue());
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);

        if (!NLXSceConst.SCE_ORD_TP_CD_CA.equals(globalMsg.G.no(0).sceOrdTpCd_G1.getValue())) {
            inMsg.setSQLID("036");
        } else {
            inMsg.setConditionValue("truckCntnrPk01", globalMsg.G.no(0).truckCntnrPk_G1.getValue());
            inMsg.setSQLID("044");
        }

        INBD_VISTMsgArray outMsgs = (INBD_VISTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        return outMsgs;
    }

    /**
     * @param globalMsg NLAL0070SMsg
     * @return INBD_VISTMsgArray
     */
    public static INBD_VISTMsgArray getInbdVisInfoOfDomesticForUpdateNoWait(NLAL0070SMsg globalMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        inMsg.setConditionValue("glblCmpyCd01", globalMsg.glblCmpyCd_BK.getValue());
        inMsg.setConditionValue("poRcvNum01", globalMsg.H.no(0).poRcvNum_H1.getValue());
        inMsg.setConditionValue("poRcvLineNum01", globalMsg.H.no(0).poRcvLineNum_H1.getValue());
        inMsg.setConditionValue("inbdVisDataTpCd01", INBD_VIS_DATA_TP.STOCK_IN_DC);

        inMsg.setSQLID("037");
        INBD_VISTMsgArray outMsgs = (INBD_VISTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);

        return outMsgs;
    }

    /**
     * @param refMsg INBD_VISTMsg
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg deleteInbdVisInfo(INBD_VISTMsg refMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        inMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_OFF_N);

        return inMsg;
    }

    /**
     * @param refMsg INBD_VISTMsg
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg updateInbdVisInfoToLatest(INBD_VISTMsg refMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        inMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);

        return inMsg;
    }

    /**
     * @param refMsg INBD_VISTMsg
     * @param sysTs System time stamp
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg updateInbdVisInfoToLatestPlus(INBD_VISTMsg refMsg, String sysTs) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        inMsg.cratTs.setValue(sysTs);
        inMsg.regdTs.setValue(sysTs);
        inMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_ON_Y);

        return inMsg;
    }

    /**
     * @param refMsg INBD_VISTMsg
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg updateInbdVisInfoToHistory(INBD_VISTMsg refMsg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        inMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_OFF_N);

        return inMsg;
    }

    /**
     * @param refMsg INBD_VISTMsg
     * @param sysTs System time stamp
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg updateInbdVisInfoToHistoryPlus(INBD_VISTMsg refMsg, String sysTs) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        inMsg.cratTs.setValue(sysTs);
        inMsg.regdTs.setValue(sysTs);
        inMsg.inbdLtstRecFlg.setValue(ZYPConstant.FLG_OFF_N);

        return inMsg;
    }

    /**
     * 
     * @param globalMsg NLAL0070SMsg
     * @param index index
     * @param refMsg INBD_VISTMsg
     * @param sysTs System time stamp
     * @param lastFlg latest flag
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg insertInbdVisInfoForSchedule(NLAL0070SMsg globalMsg, int index, INBD_VISTMsg refMsg, String sysTs, String lastFlg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        BigDecimal inbdVisPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);
        inMsg.inbdVisPk.setValue(inbdVisPk);
        inMsg.inbdLtstRecFlg.setValue(lastFlg);
        inMsg.inbdVisEventCd.setValue(INBD_VIS_EVENT.WH_SCHEDULE_ENTRY);
        inMsg.inbdVisDataTpCd.setValue(INBD_VIS_DATA_TP.STOCK_IN_DC);
        inMsg.inbdVisActlFlg.setValue(ZYPConstant.FLG_OFF_N);
        inMsg.visLocTpCd.setValue(VIS_LOC_TP.DC);
        inMsg.etaEtdDt.setValue(globalMsg.A.no(index).schdWhInEtaDt_A1.getValue());
        inMsg.calcFlg.setValue(ZYPConstant.FLG_OFF_N);
        inMsg.cratTs.setValue(sysTs);
        inMsg.regdTs.setValue(sysTs);
        inMsg.invtyStkStsCd.setValue(STOCK_STS_CD_GOOD);

        return inMsg;

    }

    /**
     * 
     * @param globalMsg NLAL0070SMsg
     * @param index index
     * @param refMsgAry List int the INBD_VISTMsg
     * @param sysTs System time stamp
     * @param lastFlg latest flag
     * @return INBD_VISTMsg
     */
    public static List<INBD_VISTMsg> insertInbdVisInfoForSchedule(NLAL0070SMsg globalMsg, int index, List<INBD_VISTMsg> refMsgAry, String sysTs, String lastFlg) {

        List<INBD_VISTMsg> insInbdVisAry = new ArrayList<INBD_VISTMsg>();

        for (INBD_VISTMsg refMsg : refMsgAry) {
            insInbdVisAry.add(insertInbdVisInfoForSchedule(globalMsg, index, refMsg, sysTs, lastFlg));
        }

        return insInbdVisAry;

    }

    /**
     * 
     * @param globalMsg NLAL0070SMsg
     * @param index index
     * @param refMsg INBD_VISTMsg
     * @param sysTs System time stamp
     * @param lastFlg latest flag
     * @return INBD_VISTMsg
     */
    public static INBD_VISTMsg insertInbdVisInfoForFinalSchedule(NLAL0070SMsg globalMsg, int index, INBD_VISTMsg refMsg, String sysTs, String lastFlg) {

        INBD_VISTMsg inMsg = new INBD_VISTMsg();
        EZDMsg.copy(refMsg, null, inMsg, null);

        BigDecimal inbdVisPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);
        inMsg.inbdVisPk.setValue(inbdVisPk);
        inMsg.inbdLtstRecFlg.setValue(lastFlg);
        inMsg.inbdVisEventCd.setValue(INBD_VIS_EVENT.FINAL_WH_SCHEDULE_ENTRY);
        inMsg.inbdVisDataTpCd.setValue(INBD_VIS_DATA_TP.STOCK_IN_DC);
        inMsg.inbdVisActlFlg.setValue(ZYPConstant.FLG_OFF_N);
        inMsg.visLocTpCd.setValue(VIS_LOC_TP.DC);
        inMsg.etaEtdDt.setValue(globalMsg.A.no(index).finalWhInEtaDt_A1.getValue());
        inMsg.calcFlg.setValue(ZYPConstant.FLG_OFF_N);
        inMsg.cratTs.setValue(sysTs);
        inMsg.regdTs.setValue(sysTs);
        inMsg.invtyStkStsCd.setValue(STOCK_STS_CD_GOOD);

        return inMsg;

    }

    /**
     * 
     * @param globalMsg NLAL0070SMsg
     * @param index index
     * @param refMsgAry List int the INBD_VISTMsg
     * @param sysTs System time stamp
     * @param lastFlg latest flag
     * @return INBD_VISTMsg
     */
    public static List<INBD_VISTMsg> insertInbdVisInfoForFinalSchedule(NLAL0070SMsg globalMsg, int index, List<INBD_VISTMsg> refMsgAry, String sysTs, String lastFlg) {

        List<INBD_VISTMsg> insInbdVisAry = new ArrayList<INBD_VISTMsg>();

        for (INBD_VISTMsg refMsg : refMsgAry) {
            insInbdVisAry.add(insertInbdVisInfoForFinalSchedule(globalMsg, index, refMsg, sysTs, lastFlg));
        }

        return insInbdVisAry;

    }

    /**
     * @param globalMsg NLAL0070SMsg
     * @param bizMsg NLAL0070CMsg
     * @param index int
     * @return IMPT_INV_LTTMsgArray
     */
    public static IMPT_INV_LTTMsgArray getImptInvLtInfo(NLAL0070SMsg globalMsg, NLAL0070CMsg bizMsg, int index) {

        IMPT_INV_LTTMsg inMsg = new IMPT_INV_LTTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd_BK.getValue());
        inMsg.setConditionValue("invtyLocCd01", globalMsg.A.no(index).invtyLocCd_A1.getValue());
        inMsg.setConditionValue("shpgMethTpCd01", globalMsg.A.no(index).shpgMethTpCd_A1.getValue());
        inMsg.setConditionValue("imptTrmCd01", globalMsg.A.no(index).imptTrmCd_A1.getValue());
        inMsg.setConditionValue("effFromDt01", globalMsg.A.no(index).tempWhInEtaDt_A1.getValue());
        inMsg.setConditionValue("effThruDt01", globalMsg.A.no(index).tempWhInEtaDt_A1.getValue());

        inMsg.setSQLID("003");

        IMPT_INV_LTTMsgArray outMsgs = (IMPT_INV_LTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsgs;
    }

    /**
     * @param dateTime Date & Time
     * @param timeZone TimeZone
     * @return IMPT_INV_LTTMsgArray
     */
    public static String convertDateTimeToGMT(String dateTime, String timeZone) {

        String inFormat  = "yyyyMMddHHmmssSSSzzz";
        String outFormat = "yyyyMMddHHmmssSSS";

        // Parse the input date.
        SimpleDateFormat smplDtFrmt = new SimpleDateFormat(inFormat);
        Date inDate = null;
        try {
            inDate = smplDtFrmt.parse(dateTime.concat(timeZone));
        } catch (ParseException e) {
            return dateTime;
        }

        // Convert time zone.
        smplDtFrmt.applyPattern(outFormat);
        smplDtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return smplDtFrmt.format(inDate);
    }
}

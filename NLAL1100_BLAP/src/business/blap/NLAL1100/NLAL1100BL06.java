/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL1100;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDPMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLAL1100.common.NLAL1100CommonLogic;
import business.blap.NLAL1100.constant.NLAL1100Constant;
import business.db.RTRN_TRK_NTFY_GRPTMsg;
import business.db.RTRN_TRK_NTFY_WRKTMsg;
import business.db.RTRN_TRK_STSTMsg;
import business.db.RTRN_TRX_CMNTTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_SCHD_DTL_TRKTMsg;
import business.parts.NLZC408001PMsg;
import business.parts.NLZC408001PMsgArray;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MSG_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_TRK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_COORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NLAL1100 Manage RMA Orders
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 06/29/2016   CSAI            Y.Imazu         Update          QC#10503
 * 11/22/2016   CITS            T.Tokutomi      Update          QC#15145
 * 11/22/2016   CITS            Y.Fujii         Update          R362
 * 07/05/2017   CITS            K.Fukumura      Update          QC#19712
 * 10/19/2017   CITS            T.Kikuhara      Update          QC#21195
 * 11/17/2017   CITS            K.Ogino         Update          QC#22587
 * 02/07/2018   CITS            K.Ogino         Update          QC#24010
 * 03/28/2018   CITS            K.Fukumura      Update          QC#25025
 * 06/11/2018   CITS            K.Fukumura      Update          QC#26007
 * 05/09/2019   Fujitsu         T.Ogura         Update          QC#50027
 * 02/05/2020   CITS            M.Furugoori     Update          QC#50121
 * 12/07/2023   Hitachi         K.Ishizuka      Update          QC#61300
 *</pre>
 */
public class NLAL1100BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NLAL1100Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Cmn_Submit((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Save_Comment".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Save_Comment((NLAL1100CMsg) cMsg);

            } else if ("NLAL1100Scrn00_Send_Rqst".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Send_Rqst((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Save_Search".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_SaveSearchOption((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_Delete_Search".equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_DeleteSearchOption((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if ("NLAL1100Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else if (NLAL1100Constant.EVENT_NM_NLAL1100_PRINT.equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Print((NLAL1100CMsg) cMsg, (NLAL1100SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    /**
     * Save Search option
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_SaveSearchOption(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S1) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {

            cMsg.srchOptNm_TX.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {converter.convLabel2i18nLabel(NLAL1100Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        if (NLAL1100CommonLogic.isExistSaveSearchName(cMsg)) {

            cMsg.srchOptNm_TX.setErrorInfo(1, NLAL1100Constant.NLZM2273E
                    , new String[] {converter.convLabel2i18nLabel(NLAL1100Constant.SCREEN_ID, "Save")
                    , converter.convLabel2i18nLabel(NLAL1100Constant.SCREEN_ID, "Search Option Name") });
            return;
        }

        NLAL1100CommonLogic.callNszc0330forSaveSearchOption(cMsg);
    }

    /**
     * Delete Search option
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_DeleteSearchOption(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S1)) {

            cMsg.srchOptPk_S1.setErrorInfo(1, NLAL1100Constant.NLZM2274E, new String[] {"Save Search Options" });
            return;
        }

        NLAL1100CommonLogic.callNszc0330forDeleteSearchOption(cMsg);
    }

    /**
     * The method explanation: Entered Retail Warehouse info is
     * registered/
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL1100Scrn00_Cmn_Submit(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        // Check Error
        if (!checkSubmitError(cMsg, sMsg)) {

            return;
        }

        // START 2020/02/13 [QC#50121, MOD]
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_G1.getValue())) {
            // Check Warning.
            if (!checkSubmitWarning(cMsg, sMsg)) {

                return;
            }
        }
        // END 2020/02/13 [QC#50121, MOD]

        cMsg.xxWrnSkipFlg_G1.clear();

        // Update Data
        if (!submitRmaData(cMsg, sMsg)) {

            return;
        }

        cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Submit"});
    }

    /**
     * checkSubmitError
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     * @return boolean true : OK, false : NG
     */
    private boolean checkSubmitError(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        boolean noTarget = true;
        int firstErrIndex = -1;

        // Detail
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);

            // START 2020/02/07 [QC#50121, ADD]
            if (changedValueLinePrev(sMsgALine)) {

                cMsg.xxWrnSkipFlg_G1.clear();
            }
            // END   2020/02/07 [QC#50121, ADD]

            if (changedValueLine(sMsgALine)) {

                noTarget = false;

                // Check TimeStamp.
                if (!NLAL1100CommonLogic.isSameTimeStamp(cMsg, sMsgALine)) {

                    if (firstErrIndex == -1) {

                        firstErrIndex = i;
                    }

                    continue;
                }

                // Check Input Items.
                if (!checkInputItems(cMsg, sMsgALine)) {

                    if (firstErrIndex == -1) {

                        firstErrIndex = i;
                    }

                    continue;
                }
            }
        }

        // RMA Comment
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).manCratFlg_C1.getValue())) {

                noTarget = false;
                break;
            }
        }

        // No Update Data
        if (noTarget) {

            cMsg.setMessageInfo(NLAL1100Constant.NLCM0123E);
            return false;
        }

        // Error Occurred
        if (0 <= firstErrIndex) {

            NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(firstErrIndex, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Check input items.
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : OK, false : NG
     */
    private boolean checkInputItems(NLAL1100CMsg cMsg, NLAL1100_ASMsg sMsgALine) {

        boolean hasMstrChkErr = false;

        // Assigned to Group
        if (ZYPCommonFunc.hasValue(sMsgALine.rtrnTrkNtfyGrpCd_A1)) {

            sMsgALine.rtrnTrkNtfyGrpDescTxt_A1.clear();

            RTRN_TRK_NTFY_GRPTMsg rtrnTrkNtfyGrpTMsg = new RTRN_TRK_NTFY_GRPTMsg();
            ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyGrpTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyGrpTMsg.rtrnTrkNtfyGrpCd, sMsgALine.rtrnTrkNtfyGrpCd_A1.getValue());
            rtrnTrkNtfyGrpTMsg = (RTRN_TRK_NTFY_GRPTMsg) EZDFastTBLAccessor.findByKey(rtrnTrkNtfyGrpTMsg);

            if (rtrnTrkNtfyGrpTMsg == null) {

                sMsgALine.rtrnTrkNtfyGrpCd_A1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[]{"Assigned to Group"});
                hasMstrChkErr = true;

            } else {

                ZYPEZDItemValueSetter.setValue(sMsgALine.rtrnTrkNtfyGrpDescTxt_A1, rtrnTrkNtfyGrpTMsg.rtrnTrkNtfyGrpDescTxt.getValue());
            }
        }

        // Carrier
        if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {

            sMsgALine.locNm_A1.clear();

            S21SsmEZDResult result = NLAL1100Query.getInstance().getCarrNm(cMsg.glblCmpyCd_G1.getValue(), sMsgALine.carrCd_A1.getValue());

            if (result.isCodeNormal()) {

                List carrNmList = (List) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(sMsgALine.locNm_A1, (String) carrNmList.get(0));

            } else {

                sMsgALine.carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLZM2278E, new String[] {"Carrier" });
                hasMstrChkErr = true;
            }
        }

        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        // Scheduled Pickup Time
        if ((changedValue(sMsgALine.rcvTsDplyTxt_A1.getValue(), sMsgALine.rcvTsDplyTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rcvTsDplyTxt_A1))
                || (changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rqstRcvDtTxt_A1))) {

            if (!checkTimeAndAmPm(sMsgALine.rcvTsDplyTxt_A1, sMsgALine.rqstRcvDtTxt_A1)) {

                hasMstrChkErr = true;

            }
        }
        // END   2019/05/09 T.Ogura [QC#50027,ADD]

        // Master Check Error
        if (hasMstrChkErr) {

            return false;
        }

        // Carrier Service Level Check
        if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BK.getValue())
                || changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_BK.getValue())) {

            if (ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1) && ZYPCommonFunc.hasValue(sMsgALine.shpgSvcLvlCd_A1)) {

                S21SsmEZDResult result = NLAL1100Query.getInstance().getCarrSvcLvlCnt(cMsg.glblCmpyCd_G1.getValue(), sMsgALine.carrCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_A1.getValue());

                if (result.isCodeNormal()) {

                    BigDecimal carrSvcLvlCnt = (BigDecimal) result.getResultObject();

                    if (carrSvcLvlCnt == null || carrSvcLvlCnt.intValue() == 0) {

                        sMsgALine.carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                        sMsgALine.shpgSvcLvlCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                        return false;
                    }

                } else {

                    sMsgALine.carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                    sMsgALine.shpgSvcLvlCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1308E, new String[] {"Pickup Service Level", "Carrier"});
                    return false;
                }
            }
        }

        return true;
    }

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * checkTimeAndAmPm
     * @param timeHHMM EZDSStringItem
     * @param timeAMPM EZDSStringItem
     * @return boolean
     */
    public static boolean checkTimeAndAmPm(EZDSStringItem timeHHMM, EZDSStringItem timeAMPM) {

        boolean checkTimeAndAmPm = true;

        if (ZYPCommonFunc.hasValue(timeHHMM) && !ZYPCommonFunc.hasValue(timeAMPM)) {

            timeAMPM.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Time(AM/PM)" });
            checkTimeAndAmPm = false;
        }

        if (!ZYPCommonFunc.hasValue(timeHHMM) && ZYPCommonFunc.hasValue(timeAMPM)) {

            timeHHMM.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Time" });
            checkTimeAndAmPm = false;
        }

        if (ZYPCommonFunc.hasValue(timeHHMM) && ZYPCommonFunc.hasValue(timeAMPM)) {

            if (!checkTime(timeHHMM.getValue(), timeAMPM.getValue())) {

                timeHHMM.setErrorInfo(1, NLAL1100Constant.NWAM0665E);
                checkTimeAndAmPm = false;
            }
        }

        return checkTimeAndAmPm;
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * checkTime
     * @param timeHHMM String
     * @param timeAMPM String
     * @return boolean
     */
    private static boolean checkTime(String timeHHMM, String timeAMPM) {

        String hourMinute = getAllDayTimes(timeHHMM, timeAMPM);

        if (!ZYPCommonFunc.hasValue(hourMinute)) {

            return false;
        }

        return true;
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * getAllDayTimes
     * @param timeHHMM String
     * @param timeAMPM String
     * @return String
     */
    public static String getAllDayTimes(String timeHHMM, String timeAMPM) {

        int hour = 0;
        int minute = 0;

        if (0 <= timeHHMM.indexOf(":")) {

            String[] temp = timeHHMM.split(":");

            if (temp.length != 2) {

                return null;
            }

            if (!ZYPCommonFunc.isNumberCheck(temp[0]) || !ZYPCommonFunc.isNumberCheck(temp[1])) {

                return null;
            }

            hour = Integer.valueOf(temp[0]);
            minute = Integer.valueOf(temp[1]);

        } else {

            if (!ZYPCommonFunc.isNumberCheck(timeHHMM)) {

                return null;
            }

            if (timeHHMM.length() == NLAL1100Constant.HOUR_MINUTE_STR_LENGTH) {

                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else if (timeHHMM.length() == NLAL1100Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                timeHHMM = "0" + timeHHMM;
                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else {

                return null;
            }
        }

        if (hour < 0 || minute < 0) {

            return null;

        } else if (NLAL1100Constant.HALF_DAY_HOURS <= hour) {

            return null;

        } else if (NLAL1100Constant.ONE_HOUR_MINUTES <= minute) {

            return null;
        }

        if (NLAL1100Constant.TIME_PM.equals(timeAMPM)) {

            hour += NLAL1100Constant.HALF_DAY_HOURS;
        }

        return (String.format("%1$02d", hour)).concat(String.format("%1$02d", minute));
    }
    // END   2019/05/09 T.Ogura [QC#50027,ADD]

    /**
     * checkSubmitWarning
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     * @return boolean true : OK, false : NG
     */
    private boolean checkSubmitWarning(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        int firstWrngIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);

            // START 2020/02/13 [QC#50121, DEL]
//            if (changedValueLinePrev(sMsgALine)) {
//
//                cMsg.xxWrnSkipFlg_G1.clear();
//            }
            // END 2020/02/13 [QC#50121, DEL]

            // START 2020/02/13 [QC#50121, ADD]
            if (!ZYPCommonFunc.hasValue(sMsgALine.rcvTsDplyTxt_A1) && !ZYPCommonFunc.hasValue(sMsgALine.rqstRcvDtTxt_A1) && ZYPConstant.FLG_ON_Y.equals(sMsgALine.techMeetTruckFlg_A1.getValue())) {

                cMsg.setMessageInfo(NLAL1100Constant.NATM0001W);
                sMsgALine.schdPickUpDt_A1.setErrorInfo(2, NLAL1100Constant.NMAM8163W, new String[] {"Tech Meet The Truck", "Time Stop" });
                sMsgALine.rcvTsDplyTxt_A1.setErrorInfo(2, NLAL1100Constant.NMAM8163W, new String[] {"Tech Meet The Truck", "Time Stop" });
                sMsgALine.rqstRcvDtTxt_A1.setErrorInfo(2, NLAL1100Constant.NMAM8163W, new String[] {"Tech Meet The Truck", "Time Stop" });

                firstWrngIndex = setFirstWrngIndex(firstWrngIndex, i);
            }
            // END   2020/02/13 [QC#50121, ADD]

            // Set Value for Warning Check
            ZYPEZDItemValueSetter.setValue(sMsgALine.rtrnTrkNtfyGrpCd_BW, sMsgALine.rtrnTrkNtfyGrpCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.schdPickUpDt_BW, sMsgALine.schdPickUpDt_A1.getValue());
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            ZYPEZDItemValueSetter.setValue(sMsgALine.rcvTsDplyTxt_BW, sMsgALine.rcvTsDplyTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.rqstRcvDtTxt_BW, sMsgALine.rqstRcvDtTxt_A1.getValue());
            // START 2020/02/13 [QC#50121, MOD]
//            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.techMeetTruckFlg_A1.getValue())) {
//                ZYPEZDItemValueSetter.setValue(sMsgALine.techMeetTruckFlg_BW, ZYPConstant.FLG_ON_Y);
//            }
//              else {
//                ZYPEZDItemValueSetter.setValue(sMsgALine.techMeetTruckFlg_BW, ZYPConstant.FLG_OFF_N);
//            }
            ZYPEZDItemValueSetter.setValue(sMsgALine.techMeetTruckFlg_BW, sMsgALine.techMeetTruckFlg_A1.getValue());
            // END   2020/02/13 [QC#50121, MOD]
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
            ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_BW, sMsgALine.carrCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.rtrnTrkStsCd_BW, sMsgALine.rtrnTrkStsCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.shpgSvcLvlCd_BW, sMsgALine.shpgSvcLvlCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.carrRsnCd_BW, sMsgALine.carrRsnCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.actlPickUpDt_BW, sMsgALine.actlPickUpDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(sMsgALine.inspCpltDt_BW, sMsgALine.inspCpltDt_A1.getValue());

            if (changedValueLine(sMsgALine)) {

                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_G1.getValue())) {

                    if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.sendRqstFlg_A1.getValue()) && changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BK.getValue())) {

                        // START 2020/02/06 [QC#50121, DEL]
//                        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_G1, ZYPConstant.FLG_ON_Y);
                        // END   2020/02/06 [QC#50121, DEL]
                        cMsg.setMessageInfo(NLAL1100Constant.NATM0001W);
                        sMsgALine.carrCd_A1.setErrorInfo(2, NLAL1100Constant.NLBM1327W);

                        firstWrngIndex = setFirstWrngIndex(firstWrngIndex, i);
                    }
                }
            }
        }
        // START   2020/02/06 [QC#50121, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_G1, ZYPConstant.FLG_ON_Y);
        // END     2020/02/06 [QC#50121, ADD]

        // Warning Occurred
        if (0 <= firstWrngIndex) {

            NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(firstWrngIndex, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * setFirstWrngIndex
     * @param firstWrngIndex int
     * @param i int 
     * @return index of first warning record.
     */
    private int setFirstWrngIndex(int firstWrngIndex, int i) {
        if (firstWrngIndex == -1) {
            return i;
        } else {
            return firstWrngIndex;
        }
    }

    /**
     * submitRmaData
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     * @return boolean true : OK, false : NG
     */
    private boolean submitRmaData(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        int firstErrIndex = -1;
        String returnCode = "";
        boolean skipNoAddCmntErr = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);

            if (!changedValueLine(sMsgALine)) {

                continue;
            }

            skipNoAddCmntErr = true;

            // Get RWS Detail
            RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, sMsgALine.rwsNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsDtlLineNum, sMsgALine.rwsDtlLineNum_A1.getValue());
            rwsDtlTMsg = (RWS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(rwsDtlTMsg);

            if (rwsDtlTMsg == null) {

                if (firstErrIndex == -1) {

                    firstErrIndex = i;
                }

                sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0009E);
                continue;
            }

            boolean updRwsSchdDtl = false;
            boolean updRwsDtl = false;

            if (changedValueForRwsSchd(sMsgALine)) {

                // Update RWS Schedule Detail
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rtrnTrkNtfyGrpCd, sMsgALine.rtrnTrkNtfyGrpCd_A1.getValue());

                // START 2019/05/09 T.Ogura [QC#50027,MOD]
//                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpDt, sMsgALine.schdPickUpDt_A1.getValue());
                if (ZYPCommonFunc.hasValue(sMsgALine.rcvTsDplyTxt_A1)) {

                    String schdPickUpTm = NLAL1100CommonLogic.getAllDayTimes(sMsgALine.rcvTsDplyTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_A1.getValue());
                    String systemTimeZoneTs = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_G1.getValue());

                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
                        systemTimeZoneTs = sMsgALine.schdPickUpDt_A1.getValue();
                    }

                    // Date + time
                    systemTimeZoneTs = systemTimeZoneTs + schdPickUpTm;

                    SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, systemTimeZoneTs, sMsgALine.addShipToCtryCd_A1.getValue(), sMsgALine.addShipToPostCd_A1.getValue());

                    String date = timeInfo.getDateTime().substring(0, 8);
                    String time = timeInfo.getDateTime().substring(8, 12);

                    // START 2020/02/28 [QC#50121, MOD]
//                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
//                        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpDt, date);
//                    }
                    ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpDt, date);
                    // END   2020/02/28 [QC#50121, MOD]
                    ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpTm, time);
                } else {
                    ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpDt, sMsgALine.schdPickUpDt_A1.getValue());
                    // START 2020/02/28 [QC#50121, ADD]
                    ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdPickUpTm, sMsgALine.rcvTsDplyTxt_A1.getValue());
                    // END   2020/02/28 [QC#50121, ADD]
                }

                String techMeetTruckFlg = sMsgALine.techMeetTruckFlg_A1.getValue();
                if (!ZYPCommonFunc.hasValue(techMeetTruckFlg)) {
                    techMeetTruckFlg = ZYPConstant.FLG_OFF_N;
                }
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.techMeetTruckFlg, techMeetTruckFlg);
                // END   2019/05/09 T.Ogura [QC#50027,MOD]

                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.carrCd, sMsgALine.carrCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rtrnTrkStsCd, sMsgALine.rtrnTrkStsCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.shpgSvcLvlCd, sMsgALine.shpgSvcLvlCd_A1.getValue());

                boolean changedRtrnTrkStsCd = changedValue(sMsgALine.rtrnTrkStsCd_A1.getValue(), sMsgALine.rtrnTrkStsCd_BK.getValue());
                boolean changedSchdPickUpDt = changedValue(sMsgALine.schdPickUpDt_A1.getValue(), sMsgALine.schdPickUpDt_BK.getValue());
                // START 2019/05/09 T.Ogura [QC#50027,ADD]
                boolean changedSchdPickUpTm = changedValue(sMsgALine.rcvTsDplyTxt_A1.getValue(), sMsgALine.rcvTsDplyTxt_BK.getValue());
                boolean changedRqstRcvDtTxt = changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue());
                boolean changedTechMeetTruckFlg = changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BK.getValue());
                // END   2019/05/09 T.Ogura [QC#50027,ADD]

                // START 2019/05/09 T.Ogura [QC#50027,MOD]
//                if (!changedRtrnTrkStsCd && changedSchdPickUpDt && ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
                if (!changedRtrnTrkStsCd && (changedSchdPickUpDt || changedSchdPickUpTm || changedRqstRcvDtTxt) && ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {
                // END   2019/05/09 T.Ogura [QC#50027,MOD]

                    if (ZYPConstant.FLG_OFF_N.equals(sMsgALine.outOfWhInvtyProcFlg_A1.getValue())) {

                        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rtrnTrkStsCd, RTRN_TRK_STS.P_OR_U_SCHEDULED_BY_LOCAL_WAREHOUSE);

                    } else if (ZYPConstant.FLG_ON_Y.equals(sMsgALine.outOfWhInvtyProcFlg_A1.getValue())) {

                        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rtrnTrkStsCd, RTRN_TRK_STS.PICKUP_SCHEDULED_BY_RMA_GROUP);
                    }
                }

                // START 2019/05/09 T.Ogura [QC#50027,MOD]
//                if (changedSchdPickUpDt) {
                if (changedSchdPickUpDt || changedSchdPickUpTm || changedRqstRcvDtTxt) {
                // END   2019/05/09 T.Ogura [QC#50027,MOD]

                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_BK)) {

                        if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {

                            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdCoordStsCd, SCHD_COORD_STS.RE_SCHEDULED);

                        } else {

                            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdCoordStsCd, SCHD_COORD_STS.AWAITING_SCHEDULING);
                        }

                    } else {

                        if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1)) {

                            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdCoordStsCd, SCHD_COORD_STS.SCHEDULED);

                        } else {

                            ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.schdCoordStsCd, SCHD_COORD_STS.AWAITING_SCHEDULING);
                        }
                    }
                }

                EZDTBLAccessor.update(rwsDtlTMsg);

                returnCode = rwsDtlTMsg.getReturnCode();

                if (!RTNCD_NORMAL.equals(returnCode)) {

                    if (firstErrIndex == -1) {

                        firstErrIndex = i;
                    }

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM1295E, new String[]{"RWS Schedule Detail"});
                    continue;
                }

                updRwsSchdDtl = true;

                // Check Required Notification
                RTRN_TRK_STSTMsg rtrnTrkStsTMsg = new RTRN_TRK_STSTMsg();
                ZYPEZDItemValueSetter.setValue(rtrnTrkStsTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnTrkStsTMsg.rtrnTrkStsCd, rwsDtlTMsg.rtrnTrkStsCd.getValue());
                rtrnTrkStsTMsg = (RTRN_TRK_STSTMsg) EZDTBLAccessor.findByKey(rtrnTrkStsTMsg);

                if (rtrnTrkStsTMsg != null && ZYPConstant.FLG_ON_Y.equals(rtrnTrkStsTMsg.ntfyMlSendFlg.getValue())) {

                    // Insert Return Tracking Notification Work
                    RTRN_TRK_NTFY_WRKTMsg rtrnTrkNtfyWrkTMsg = new RTRN_TRK_NTFY_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RTRN_TRK_NTFY_WRK_SQ));
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.updUsrId, rwsDtlTMsg.ezUpUserID.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.updTs, rwsDtlTMsg.ezUpTime.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rwsNum, rwsDtlTMsg.rwsNum.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rwsDtlLineNum, rwsDtlTMsg.rwsDtlLineNum.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdCoordStsCd, rwsDtlTMsg.schdCoordStsCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdCoordPsnCd, rwsDtlTMsg.schdCoordPsnCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdPickUpDt, rwsDtlTMsg.schdPickUpDt.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.carrCd, rwsDtlTMsg.carrCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.shpgSvcLvlCd, rwsDtlTMsg.shpgSvcLvlCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkStsCd, rwsDtlTMsg.rtrnTrkStsCd.getValue());
                    ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyGrpCd, rwsDtlTMsg.rtrnTrkNtfyGrpCd.getValue());
                    EZDTBLAccessor.insert(rtrnTrkNtfyWrkTMsg);

                    returnCode = rtrnTrkNtfyWrkTMsg.getReturnCode();

                    if (!RTNCD_NORMAL.equals(returnCode)) {

                        if (firstErrIndex == -1) {

                            firstErrIndex = i;
                        }

                        sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM1295E, new String[]{"Return Tracking Notification Work"});
                        continue;
                    }
                }
            }

            if (changedValueForDsRws(sMsgALine)) {

                // Update RWS Detail.
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.carrRsnCd, sMsgALine.carrRsnCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.actlPickUpDt, sMsgALine.actlPickUpDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.inspCpltDt, sMsgALine.inspCpltDt_A1.getValue());
                EZDTBLAccessor.update(rwsDtlTMsg);

                returnCode = rwsDtlTMsg.getReturnCode();

                if (!RTNCD_NORMAL.equals(returnCode)) {

                    if (firstErrIndex == -1) {

                        firstErrIndex = i;
                    }

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM1295E, new String[]{"DS RWS Detail"});
                    continue;
                }

                updRwsDtl = true;
            }

            // Create RWS Schedule Detail Tracking.
            if (updRwsSchdDtl || updRwsDtl) {

                String updUsrId = rwsDtlTMsg.ezUpUserID.getValue();

                if (!updRwsSchdDtl) {

                    updUsrId = rwsDtlTMsg.ezUpUserID.getValue();
                }

                String updTs = rwsDtlTMsg.ezUpTime.getValue();

                if (!updRwsSchdDtl) {

                    updTs = rwsDtlTMsg.ezUpTime.getValue();
                }

                RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrkTMsg = new RWS_SCHD_DTL_TRKTMsg();
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsSchdDtlTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RWS_SCHD_DTL_TRK_SQ));
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updUsrId, updUsrId);
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updTs, updTs);
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsNum, rwsDtlTMsg.rwsNum.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsDtlLineNum, rwsDtlTMsg.rwsDtlLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordStsCd, rwsDtlTMsg.schdCoordStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordPsnCd, rwsDtlTMsg.schdCoordPsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdPickUpDt, rwsDtlTMsg.schdPickUpDt.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCd, rwsDtlTMsg.carrCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.shpgSvcLvlCd, rwsDtlTMsg.shpgSvcLvlCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkStsCd, rwsDtlTMsg.rtrnTrkStsCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkNtfyGrpCd, rwsDtlTMsg.rtrnTrkNtfyGrpCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrRsnCd, rwsDtlTMsg.carrRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCmntTxt, rwsDtlTMsg.carrCmntTxt.getValue());
                EZDTBLAccessor.insert(rwsSchdDtlTrkTMsg);

                returnCode = rwsSchdDtlTrkTMsg.getReturnCode();

                if (!RTNCD_NORMAL.equals(returnCode)) {

                    if (firstErrIndex == -1) {

                        firstErrIndex = i;
                    }

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM1295E, new String[]{"RWS Schedule Detail Tracking"});
                    continue;
                }
            }
        }

        // START 2023/12/07 K.Ishizuka [QC#61300 ADD]
        //Sort FSR_Num > OLD Pickup Date > OLD Pickup Time
        S21SortTarget beforeSortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey beforeSortKey = new S21SortKey();
        beforeSortKey.add("fsrNum_A1", "ASC");
        beforeSortKey.add("schdPickUpDt_BK", "ASC");
        beforeSortKey.add("rqstRcvDtTxt_BK", "ASC");
        beforeSortKey.add("rcvTsDplyTxt_BK", "ASC");
        S21EZDMsgArraySort.sort(beforeSortTarget, beforeSortKey, 0, sMsg.A.getValidCount());

        Map<String, String> beforeTimeMap = new HashMap<String, String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String dateTime = "";
            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);
            if (i == 0) {
                if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rcvTsDplyTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rqstRcvDtTxt_BK.getValue())) {
                    dateTime = sMsgALine.schdPickUpDt_BK.getValue() + getAllDayTimes(sMsgALine.rcvTsDplyTxt_BK.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue());
                }
                beforeTimeMap.put(sMsgALine.fsrNum_A1.getValue(), dateTime);
            }else {
                if (!sMsgALine.fsrNum_A1.getValue().equals(sMsg.A.no(i-1).fsrNum_A1.getValue())) {
                    if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rcvTsDplyTxt_BK.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rqstRcvDtTxt_BK.getValue())) {
                        dateTime = sMsgALine.schdPickUpDt_BK.getValue() + getAllDayTimes(sMsgALine.rcvTsDplyTxt_BK.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue());
                    }
                    beforeTimeMap.put(sMsgALine.fsrNum_A1.getValue(), dateTime);
                }
            }
        }

        //Sort FSR_Num > NEW Pickup Date > NEW Pickup Time
        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add("fsrNum_A1", "ASC");
        sortKey.add("schdPickUpDt_A1", "ASC");
        sortKey.add("rqstRcvDtTxt_A1", "ASC");
        sortKey.add("rcvTsDplyTxt_A1", "ASC");
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        boolean sendMailFlg = false;
        boolean sendTaskInfoErrFlg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String beforeDateTime = "";
            String afterDateTime = "";
            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);
            if (i > 0) {
                if (!sMsgALine.fsrNum_A1.getValue().equals(sMsg.A.no(i-1).fsrNum_A1.getValue()) || !ZYPCommonFunc.hasValue(sMsgALine.fsrNum_A1.getValue())) {
                    sendMailFlg = false;
                }
            }
            
            beforeDateTime = beforeTimeMap.get(sMsgALine.fsrNum_A1.getValue());
            if (ZYPCommonFunc.hasValue(sMsgALine.schdPickUpDt_A1.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rcvTsDplyTxt_A1.getValue()) && ZYPCommonFunc.hasValue(sMsgALine.rqstRcvDtTxt_A1.getValue())) {
                afterDateTime = sMsgALine.schdPickUpDt_A1.getValue() + getAllDayTimes(sMsgALine.rcvTsDplyTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_A1.getValue());
            }
            if (i == 0 || !sMsgALine.fsrNum_A1.getValue().equals(sMsg.A.no(i-1).fsrNum_A1.getValue()) || sendMailFlg == false) {
                sendMailFlg = true;
                if (changeDeliveryDateTime(sMsgALine, beforeDateTime, afterDateTime)) {
                    if (!ZYPCommonFunc.hasValue(beforeDateTime) && afterDateTime != null) {
                        sendTaskInfoErrFlg = sendMailAndSvcTask(cMsg, sMsgALine, beforeDateTime, afterDateTime);
                    }  else if (ZYPDateUtil.compare(beforeDateTime, afterDateTime) == 0) {
                        int beforeTime = Integer.parseInt(beforeDateTime.substring(8, 12));
                        int afterTime = Integer.parseInt(afterDateTime.substring(8, 12));
                        if (beforeTime != afterTime) {
                            sendTaskInfoErrFlg = sendMailAndSvcTask(cMsg, sMsgALine, beforeDateTime, afterDateTime);
                        }
                    }else {
                        sendTaskInfoErrFlg = sendMailAndSvcTask(cMsg, sMsgALine, beforeDateTime, afterDateTime);
                    }
                }
            }
                if (sendTaskInfoErrFlg) {
                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM1295E, new String[]{"Submit"});
                    continue;
                }
        }
        // END   2023/12/07 K.Ishizuka [QC#61300 ADD]

        // Error Occurred
        if (0 <= firstErrIndex) {

            NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(firstErrIndex, cMsg, sMsg));
            return false;
        }

        // Insert Return Comment
        if (!insertRmaComment(cMsg, skipNoAddCmntErr)) {

            return false;
        }

        return true;
    }

    /**
     * insertRmaComment
     * @param cMsg NLAL1100CMsg
     * @param skipNoAddCmntErr boolean
     * @return boolean true : OK, false : NG
     */
    private boolean insertRmaComment(NLAL1100CMsg cMsg, boolean skipNoAddCmntErr) {

        boolean addComment = false;
        int i = cMsg.C.getValidCount();

        for (; 0 < i; i--) {

            int indexC = i - 1;

            // QC#24010
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(indexC).manCratFlg_C1.getValue()) && ZYPCommonFunc.hasValue(cMsg.C.no(indexC).rtrnTrxCmntTxt_C1)) {

                addComment = true;

                // Insert Return Transaction Comment
                RTRN_TRX_CMNTTMsg rtrnTrxCmntTMsg = new RTRN_TRX_CMNTTMsg();
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.rtrnTrxCmntPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RTRN_TRX_CMNT_SQ));
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.msgCtrlTpCd, MSG_CTRL_TP.RMA_COMMENT);
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.trxHdrNum, cMsg.cpoOrdNum_C1.getValue());
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.updUsrId, cMsg.srchOptUsrId_U1.getValue());
                // QC#22587 Start
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.updTs, cMsg.slsDt_G1.getValue() + ZYPDateUtil.getCurrentSystemTime(NLAL1100Constant.DT_FMT_HHMMSSSSS));
                // QC#22587 End
                ZYPEZDItemValueSetter.setValue(rtrnTrxCmntTMsg.rtrnTrxCmntTxt, cMsg.C.no(indexC).rtrnTrxCmntTxt_C1.getValue());
                EZDTBLAccessor.insert(rtrnTrxCmntTMsg);

                String returnCode = rtrnTrxCmntTMsg.getReturnCode();

                if (!RTNCD_NORMAL.equals(returnCode)) {

                    cMsg.setMessageInfo(NLAL1100Constant.NLBM1295E, new String[]{"RMA Comment"});
                    return false;
                }
            }
        }

        if (!addComment && !skipNoAddCmntErr) {

            cMsg.setMessageInfo(NLAL1100Constant.NLCM0123E);
            return false;
        }

        return true;
    }

    /**
     * doProcess_NLAL1100Scrn00_Save_Comment
     * @param cMsg NLAL1100CMsg
     */
    private void doProcess_NLAL1100Scrn00_Save_Comment(NLAL1100CMsg cMsg) {

        if (!insertRmaComment(cMsg, false)) {

            return;
        }

        cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[]{"Save"});
    }

    /**
     * The method explanation: Entered Retail Warehouse info is
     * registered/
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLAL1100Scrn00_Send_Rqst(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int errIndex = -1;
        boolean noTarget = true;

        // Check
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue())) {

                noTarget = false;

                // Input Check
                if (!inputCheckSendRqst(cMsg, sMsgALine)) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }

                // The check of TimeStamp.
                if (!NLAL1100CommonLogic.isSameTimeStamp(cMsg, sMsgALine)) {

                    if (errIndex == -1) {

                        errIndex = i;
                    }

                    continue;
                }
            }
        }
        

        if (noTarget) {

            cMsg.setMessageInfo(NLAL1100Constant.NLCM0123E);
            return;
        }
        
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLAL1100_ASMsg sMsgALine = sMsg.A.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue())) {
                
                // Update
                RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, sMsgALine.rwsNum_A1.getValue());
                rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(rwsHdrTMsg);

                if (rwsHdrTMsg == null) {
                    // Error
                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0009E);
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.pickUpRqstFlg, ZYPConstant.FLG_ON_Y);
                EZDTBLAccessor.update(rwsHdrTMsg);

                String returnCode = rwsHdrTMsg.getReturnCode();

                if (!RTNCD_NORMAL.equals(returnCode)) {

                    sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM1295E, new String[] {"Send Request" });

                    if (errIndex == -1) {

                        errIndex = i;
                    }
                }
            }
        }


        // Having Error
        if (0 <= errIndex) {

            NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(errIndex, cMsg, sMsg));
            return;
        }

        cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Send Request"});
    }

    /**
     * inputCheckSendRqst
     * @param cMsg NLAL1100CMsg
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : Changed, false : Not changed
     */
    private boolean inputCheckSendRqst(NLAL1100CMsg cMsg, NLAL1100_ASMsg sMsgALine) {

        // Status Check
        if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.openStsFlg_A1.getValue())) {

            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLZM2283E);
            return false;
        }

        // Carrier Check
        if (!ZYPCommonFunc.hasValue(sMsgALine.carrCd_A1)) {

            sMsgALine.carrCd_A1.setErrorInfo(1, NLAL1100Constant.ZZM9000E, new String[] {"Carrier"});
            return false;
        }

        if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BK.getValue())) {

            sMsgALine.carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1286E);
            return false;
        }

        // Send Carrier Check
        if (!ZYPConstant.FLG_ON_Y.equals(sMsgALine.manSendRqstFlg_A1.getValue())) {

            sMsgALine.carrCd_A1.setErrorInfo(1, NLAL1100Constant.NLBM1311E);
            return false;
        }

        return true;
    }

    /**
     * changedValueLine
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : Changed, false : Not changed
     */
    private boolean changedValueLine(NLAL1100_ASMsg sMsgALine) {

        // For RWS Schedule
        if (changedValueForRwsSchd(sMsgALine)) {
            return true;
        }

        // For DS RWS
        if (changedValueForDsRws(sMsgALine)) {
            return true;
        }

        return false;
    }

    /**
     * changedValueForRwsSchd
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : Changed, false : Not changed
     */
    private boolean changedValueForRwsSchd(NLAL1100_ASMsg sMsgALine) {

        // rtrnTrkNtfyGrpCd
        if (changedValue(sMsgALine.rtrnTrkNtfyGrpCd_A1.getValue(), sMsgALine.rtrnTrkNtfyGrpCd_BK.getValue())) {
            return true;
        }

        // schdPickUpDt
        if (changedValue(sMsgALine.schdPickUpDt_A1.getValue(), sMsgALine.schdPickUpDt_BK.getValue())) {
            return true;
        }

        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        // schdPickUpTm
        if (changedValue(sMsgALine.rcvTsDplyTxt_A1.getValue(), sMsgALine.rcvTsDplyTxt_BK.getValue())) {
            return true;
        }

        // rqstRcvDtTxt
        if (changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue())) {
            return true;
        }

        // techMeetTruckFlg
        String techMeetTruckFlg = sMsgALine.techMeetTruckFlg_A1.getValue();
        if (!ZYPCommonFunc.hasValue(techMeetTruckFlg)) {
            techMeetTruckFlg = ZYPConstant.FLG_OFF_N;
        }
        if (changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BK.getValue())) {
            return true;
        }
        // END   2019/05/09 T.Ogura [QC#50027,ADD]

        // carrCd
        if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BK.getValue())) {
            return true;
        }

        // rtrnTrkStsCd
        if (changedValue(sMsgALine.rtrnTrkStsCd_A1.getValue(), sMsgALine.rtrnTrkStsCd_BK.getValue())) {
            return true;
        }

        // pickUpSvcLvlCd
        if (changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_BK.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * changedValueForDsRws
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : Changed, false : Not changed
     */
    private boolean changedValueForDsRws(NLAL1100_ASMsg sMsgALine) {

        // carrRsnCd
        if (changedValue(sMsgALine.carrRsnCd_A1.getValue(), sMsgALine.carrRsnCd_BK.getValue())) {
            return true;
        }

        // actlPickUpDt
        if (changedValue(sMsgALine.actlPickUpDt_A1.getValue(), sMsgALine.actlPickUpDt_BK.getValue())) {
            return true;
        }

        // inspCpltDt
        if (changedValue(sMsgALine.inspCpltDt_A1.getValue(), sMsgALine.inspCpltDt_BK.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * changedValue
     * @param newVal String
     * @param oldVal String
     * @return boolean true : Changed, false : Not changed
     */
    private boolean changedValue(String newVal, String oldVal) {

        if (ZYPCommonFunc.hasValue(newVal) && !ZYPCommonFunc.hasValue(oldVal)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(newVal) && ZYPCommonFunc.hasValue(oldVal)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(newVal) && !ZYPCommonFunc.hasValue(oldVal)) {

            return false;

        } else if (newVal.equals(oldVal)) {

            return false;
        }

        return true;
    }

    /**
     * changedValueLinePrev
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : Changed, false : Not changed
     */
    private boolean changedValueLinePrev(NLAL1100_ASMsg sMsgALine) {

        // rtrnTrkNtfyGrpCd
        if (changedValue(sMsgALine.rtrnTrkNtfyGrpCd_A1.getValue(), sMsgALine.rtrnTrkNtfyGrpCd_BW.getValue())) {
            return true;
        }

        // schdPickUpDt
        if (changedValue(sMsgALine.schdPickUpDt_A1.getValue(), sMsgALine.schdPickUpDt_BW.getValue())) {
            return true;
        }

        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        // schdPickUpTm
        if (changedValue(sMsgALine.rcvTsDplyTxt_A1.getValue(), sMsgALine.rcvTsDplyTxt_BW.getValue())) {
            return true;
        }

        // rqstRcvDtTxt
        if (changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BW.getValue())) {
            return true;
        }

        // techMeetTruckFlg
        String techMeetTruckFlg = sMsgALine.techMeetTruckFlg_A1.getValue();
        // START 2020/02/06 [QC#50121, DEL]
//        if (!ZYPCommonFunc.hasValue(techMeetTruckFlg)) {
//            techMeetTruckFlg = ZYPConstant.FLG_OFF_N;
//        }
        // END   2020/02/06 [QC#50121, DEL]
        if (changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BW.getValue())) {
            return true;
        }
        // END   2019/05/09 T.Ogura [QC#50027,ADD]

        // carrCd
        if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BW.getValue())) {
            return true;
        }

        // rtrnTrkStsCd
        if (changedValue(sMsgALine.rtrnTrkStsCd_A1.getValue(), sMsgALine.rtrnTrkStsCd_BW.getValue())) {
            return true;
        }

        // pickUpSvcLvlCd
        if (changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_BW.getValue())) {
            return true;
        }

        // carrRsnCd
        if (changedValue(sMsgALine.carrRsnCd_A1.getValue(), sMsgALine.carrRsnCd_BW.getValue())) {
            return true;
        }

        // actlPickUpDt
        if (changedValue(sMsgALine.actlPickUpDt_A1.getValue(), sMsgALine.actlPickUpDt_BW.getValue())) {
            return true;
        }

        // inspCpltDt
        if (changedValue(sMsgALine.inspCpltDt_A1.getValue(), sMsgALine.inspCpltDt_BW.getValue())) {
            return true;
        }

        return false;
    }
    /**
     * doProcess_NLAL1100Scrn00_Print
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private void doProcess_NLAL1100Scrn00_Print(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        NLAL1100CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        int targetNo = 0;
        int selectCount = 0;
        NLAL1100_ASMsg sMsgALine;

        // QC#25025 2018/03/28 Start
        ArrayList<NLZC408001PMsg> rptPrmList = new ArrayList<NLZC408001PMsg>();
        ArrayList<NLAL1100_ASMsg> sMsgAList = new ArrayList<NLAL1100_ASMsg>();
        // QC#25025 2018/03/28 End

        // QC#26007 2018/06/11 Start
        String prtGrpRwsRefNumAndRwsNum = "";
        ArrayList<String> prtGrpRwsRefNumAndRwsNumList = new ArrayList<String>();
        // QC#26007 2018/06/11 End

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsgALine = sMsg.A.no(i);
            // QC#26007 2018/06/11 Start
            prtGrpRwsRefNumAndRwsNum = sMsg.A.no(i).rwsRefNum_PR.getValue() + sMsg.A.no(i).rwsNum_A1.getValue();
            //if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue())) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A1.getValue()) && !prtGrpRwsRefNumAndRwsNumList.contains(prtGrpRwsRefNumAndRwsNum)) {
            // QC#26007 2018/06/11 End
                targetNo = i;
                selectCount++;
                // QC#25025 2018/03/28 Start
                NLZC408001PMsg reportParam = new NLZC408001PMsg();
                ZYPEZDItemValueSetter.setValue(reportParam.glblCmpyCd, cMsg.glblCmpyCd_G1);
                ZYPEZDItemValueSetter.setValue(reportParam.rwsRefNum, sMsg.A.no(i).rwsRefNum_PR);
                ZYPEZDItemValueSetter.setValue(reportParam.rwsNum, sMsg.A.no(i).rwsNum_A1);
                ZYPEZDItemValueSetter.setValue(reportParam.procPgmId, NLAL1100Constant.BIZ_APP_ID);
                rptPrmList.add(reportParam);

                sMsgAList.add(sMsg.A.no(i));
                // QC#25025 2018/03/28 End
                // QC#26007 2018/06/11 Start
                prtGrpRwsRefNumAndRwsNumList.add(prtGrpRwsRefNumAndRwsNum);
                // QC#26007 2018/06/11 End
            }
        }

        // QC#25025 2018/03/28 Start
//        if (selectCount != 1) {
//            if (selectCount == 0) {
//                cMsg.setMessageInfo(NLAL1100Constant.NPAM0049E);
//            } else {
//                cMsg.setMessageInfo(NLAL1100Constant.NLAM0046E);
//            }
//            return;
//        }
        if (selectCount == 0) {
            cMsg.setMessageInfo(NLAL1100Constant.NPAM0049E);
            return;
        }
        // QC#25025 2018/03/28 End
        sMsgALine = sMsg.A.no(targetNo);

        // Check TimeStamp.
        // QC#25025 2018/03/28 Start
        //if (!NLAL1100CommonLogic.isSameTimeStamp(cMsg, sMsgALine)) {
        //    NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(targetNo, cMsg, sMsg));
        //}
        //createWrkTableForReport(cMsg, sMsgALine);
        for (NLAL1100_ASMsg sMsgA : sMsgAList) {
            if (!NLAL1100CommonLogic.isSameTimeStamp(cMsg, sMsgA)) {
                NLAL1100CommonLogic.pagenation(cMsg, sMsg, NLAL1100CommonLogic.getPageStartRowIndex(targetNo, cMsg, sMsg));
            }
        }
        createWrkTableForReport(cMsg, rptPrmList);
        // QC#25025 2018/03/28 End
        

    }

// QC#25025 2018/03/28 Start
//    /**
//     * createWrkTableForReport
//     * @param cMsg NLAL1100CMsg
//     */
//    private static void createWrkTableForReport(NLAL1100CMsg cMsg, NLAL1100_ASMsg sMsgALine) {
//        // execute RMA Create Report Work API
//        NLZC408001 reportAPI = new NLZC408001();
//
//        NLZC408001PMsg reportParam = new NLZC408001PMsg();
//        ZYPEZDItemValueSetter.setValue(reportParam.glblCmpyCd, cMsg.glblCmpyCd_G1);
//        ZYPEZDItemValueSetter.setValue(reportParam.rwsRefNum, sMsgALine.rwsRefNum_PR);
//        //QC#21195 ADD START
//        ZYPEZDItemValueSetter.setValue(reportParam.rwsNum, sMsgALine.rwsNum_A1);
//        //QC#21195 ADD END
//        ZYPEZDItemValueSetter.setValue(reportParam.procPgmId, NLAL1100Constant.BIZ_APP_ID);
//
//        try {
//            reportAPI.execute(reportParam, ONBATCH_TYPE.ONLINE);
//            if (checkApiResult(reportParam, cMsg)) {
//                //ZYPEZDItemValueSetter.setValue(cMsg.csaRmaRptPrintRqstSq_PR, reportParam.csaRmaRptPrintRqstSq);
//                //ZYPEZDItemValueSetter.setValue(cMsg.rwsRefNum_PR, reportParam.rwsRefNum);
//                cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Print" });
//            }
//        } catch (SQLException e) {
//            throw new S21AbendException(e, NLAL1100Constant.NLBM0024E, new String[] {"NLZC408001" });
//        }
//    }
    /**
     * createWrkTableForReport
     * @param ArrayList<NLZC408001PMsg> rptPrmList
     */
    private static void createWrkTableForReport(NLAL1100CMsg cMsg, ArrayList<NLZC408001PMsg> rptPrmList) {
        // execute RMA Create Report Work API
        NLZC408001 reportAPI = new NLZC408001();
        int index = 0;
        for (NLZC408001PMsg reportParam : rptPrmList) {
            try {
                reportAPI = new NLZC408001();
                reportAPI.execute(reportParam, ONBATCH_TYPE.ONLINE);
                if (checkApiResult(reportParam, cMsg)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(index).csaRmaRptPrintRqstSq_PR, reportParam.csaRmaRptPrintRqstSq);
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(index).rwsRefNum_PR, reportParam.rwsRefNum);
                    index++;
                    cMsg.L.setValidCount(index);
                }
            } catch (SQLException e) {
                throw new S21AbendException(e, NLAL1100Constant.NLBM0024E, new String[] {"NLZC408001" });
            }
        }
        cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Print" });
    }
    // QC#25025 2018/03/28 End
    /**
     * checkApiResult
     * @param rtnPMsg EZDPMsg
     * @param cMsg NLAL1100CMsg
     * @param boolean
     */
    private static boolean checkApiResult(EZDPMsg rtnPMsg, NLAL1100CMsg cMsg) {

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(rtnPMsg);

        if (msgIdList.size() == 0) {
            // It has no Message.
            return true;
        }

        for (int i = 0; i < msgIdList.size(); i++) {
            String xxMsgId = msgIdList.get(i);
            if (xxMsgId.endsWith(NLAL1100Constant.MSG_TYPE_ERROR) || xxMsgId.endsWith(NLAL1100Constant.MSG_TYPE_WARNING)) {
                cMsg.setMessageInfo(xxMsgId);
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        int lastIndex = msgIdList.size() - 1;

        // set latest Warning or Information Message
        cMsg.setMessageInfo(msgIdList.get(lastIndex));

        return true;
    }
    
    // START 2023/12/07 K.Ishizuka [QC#61300 ADD]
    /**
     * changeDeliveryDateTime
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean true : Changed, false : Not changed
     */
    private boolean changeDeliveryDateTime (NLAL1100_ASMsg sMsgALine, String beforeTime, String afterTime) {
        // schdPickUpDt
        if (changedValue(sMsgALine.schdPickUpDt_A1.getValue(), sMsgALine.schdPickUpDt_BK.getValue())) {
            return true;
        }

        // schdPickUpTm
        if (changedValue(beforeTime, afterTime)) {
            return true;
        }
        return false;
    }
    
    private boolean sendMailAndSvcTask(NLAL1100CMsg cMsg, NLAL1100_ASMsg sMsgALine, String beforeDateTime, String afterDateTime){
        boolean sendTaskInfoErrFlg = false;
        // get Service Branch Manager Address
        String svcBrMngrEmlAddr = NLAL1100CommonLogic.getEmlAddrFromTaskSvcBrMgr(cMsg, sMsgALine.cpoOrdNum_A1.getValue());

        if (!ZYPCommonFunc.hasValue(svcBrMngrEmlAddr)) {

            svcBrMngrEmlAddr = NLAL1100CommonLogic.getSvcBrMngrEmlAddr(cMsg, sMsgALine.cpoOrdNum_A1.getValue());

        }
        //sendMail
        String afterErlStartTs = NLAL1100CommonLogic.sendMail(cMsg, sMsgALine.cpoOrdNum_A1.getValue(), svcBrMngrEmlAddr, beforeDateTime, afterDateTime);
        //update SvcTask
        NSZC043001PMsg sendTaskInfoApiPMsg = NLAL1100CommonLogic.updateSvcTaskErlStartTs(cMsg, sMsgALine.cpoOrdNum_A1.getValue(), afterErlStartTs, getContextUserInfo().getUserId(), beforeDateTime, afterDateTime);

        if (!NLAL1100CommonLogic.chkApiRslt(cMsg, sMsgALine.xxChkBox_A1, S21ApiUtil.getXxMsgIdList(sendTaskInfoApiPMsg))) {

            sendTaskInfoErrFlg = true;

        }

        return sendTaskInfoErrFlg;
    }
    // END   2023/12/07 K.Ishizuka [QC#61300 ADD]
}

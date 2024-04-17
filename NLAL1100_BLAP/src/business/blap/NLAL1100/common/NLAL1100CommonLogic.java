/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL1100.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLAL1100.NLAL1100CMsg;
import business.blap.NLAL1100.NLAL1100Query;
import business.blap.NLAL1100.NLAL1100SMsg;
import business.blap.NLAL1100.NLAL1100_ASMsg;
import business.blap.NLAL1100.constant.NLAL1100Constant;
import business.db.RWS_DTLTMsg;
import business.db.RWS_HDRTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC033001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Business ID : NLAL1100 Manage RMA Orders
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 * 05/09/2019   Fujitsu         T.Ogura         Update          QC#50027
 * 12/07/2023   Hitachi         K.Ishizuka      Update          QC#61300
 *</pre>
 */
public class NLAL1100CommonLogic {

    /**
     * clear Msg
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    public static void clearMsg(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        // Clear Table
        clearTable(cMsg, sMsg);

        // Hidden
        cMsg.xxWrnSkipFlg_G1.clear();
        cMsg.xxFileData.clear();

        // Search Option
        cMsg.srchOptPk_S1.clear();
        cMsg.srchOptPk_L1.clear();
        cMsg.srchOptNm_L1.clear();
        cMsg.srchOptNm_TX.clear();

        // Search Criteria
        cMsg.rmaNum_H1.clear();
        cMsg.soNum_H1.clear();
        cMsg.rwsNum_H1.clear();
        cMsg.rtrnLineDplyStsCd_H1.clear();
        cMsg.rtrnLineDplyStsCd_L1.clear();
        cMsg.rtrnLineDplyStsDescTxt_L1.clear();
        cMsg.ordAgingBcktCd_H1.clear();
        cMsg.ordAgingBcktCd_L1.clear();
        cMsg.ordAgingBcktDescTxt_L1.clear();
        cMsg.rtlWhCd_H1.clear();
        cMsg.rtlWhNm_H1.clear();
        cMsg.rtlSwhCd_H1.clear();
        cMsg.rtlSwhNm_H1.clear();
        cMsg.shipToCustCd_H1.clear();
        cMsg.dsAcctNm_H1.clear();
        cMsg.carrCd_H1.clear();
        cMsg.carrNm_H1.clear();
        cMsg.slsRepBrCd_H1.clear();
        cMsg.tocNm_H1.clear();
        cMsg.rqstPickUpDt_HF.clear();
        cMsg.rqstPickUpDt_HT.clear();
        cMsg.schdPickUpDt_HF.clear();
        cMsg.schdPickUpDt_HT.clear();
        cMsg.actlPickUpDt_HF.clear();
        cMsg.actlPickUpDt_HT.clear();
        cMsg.serNum_H1.clear();
        cMsg.svcConfigMstrPk_H1.clear();
        cMsg.psnCd_H1.clear();
        cMsg.xxPsnFirstMidLastNm_H1.clear();
        cMsg.xxChkBox_CL.clear();
        // QC#23044
        cMsg.sendRqstFlg_Y.clear();
        cMsg.sendRqstFlg_N.clear();

        // RMA Comment
        cMsg.cpoOrdNum_C1.clear();

        // Detail Pulldown
        cMsg.shpgSvcLvlCd_L1.clear();
        cMsg.shpgSvcLvlDescTxt_L1.clear();
        cMsg.rtrnTrkStsCd_L1.clear();
        cMsg.rtrnTrkStsDescTxt_L1.clear();
        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        cMsg.rqstRcvDtTxt_CD.clear();
        cMsg.rqstRcvDtTxt_DI.clear();
        // END   2019/05/09 T.Ogura [QC#50027,ADD]
        cMsg.carrRsnCd_L1.clear();
        cMsg.carrRsnDescTxt_L1.clear();

        // Detail Header Control
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        cMsg.xxPageShowCurNum.clear();
        cMsg.xxPageShowTotNum.clear();
        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();
        cMsg.xxNum_EV.clear();

        // Popup Parameter
        cMsg.xxPopPrm_EV.clear();
        cMsg.svcConfigMstrPk_P.clear();

        // Apply
        cMsg.rtrnTrkNtfyGrpCd_G.clear();
        cMsg.rtrnTrkNtfyGrpDescTxt_G.clear();
        cMsg.carrCd_G.clear();
        cMsg.locNm_G.clear();
        cMsg.schdPickUpDt_G.clear();
        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        cMsg.rcvTsDplyTxt_G.clear();
        cMsg.rqstRcvDtTxt_G.clear();
        // END   2019/05/09 T.Ogura [QC#50027,ADD]
        cMsg.inspCpltDt_G.clear();
        cMsg.shpgSvcLvlCd_G.clear();
        cMsg.actlPickUpDt_G.clear();
        cMsg.carrRsnCd_G.clear();
        cMsg.rtrnTrkStsCd_G.clear();
    }

    /**
     * clear Table
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    private static void clearTable(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(sMsg.A);
        sMsg.clear();
    }

    /**
     * Create Pulldown Search Option
     * @param cMsg NLAL1100CMsg
     */
    public static void createPullDownSearchOption(NLAL1100CMsg cMsg) {

        // Clear Pulldown Data
        cMsg.srchOptPk_L1.clear();
        cMsg.srchOptNm_L1.clear();

        // Execute
        S21SsmEZDResult result = NLAL1100Query.getInstance().getSearchOptionPulldownList(cMsg);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> searchOptionList = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {

                if (i >= cMsg.srchOptPk_L1.length()) {

                    break;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_L1.no(i), (BigDecimal) searchOptionList.get(i).get("SRCH_OPT_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_L1.no(i), (String) searchOptionList.get(i).get("SRCH_OPT_NM"));
            }
        }
    }

    /**
     * Create setPulldownTimeAmPm
     * @param cMsg NLAL1100CMsg
     */
    public static void setPulldownTimeAmPm(NLAL1100CMsg cMsg) {

        cMsg.rqstRcvDtTxt_CD.no(0).setValue(NLAL1100Constant.TIME_AM);
        cMsg.rqstRcvDtTxt_CD.no(1).setValue(NLAL1100Constant.TIME_PM);
        cMsg.rqstRcvDtTxt_DI.no(0).setValue(NLAL1100Constant.TIME_AM);
        cMsg.rqstRcvDtTxt_DI.no(1).setValue(NLAL1100Constant.TIME_PM);
    }

    /**
     * The method explanation: The search processing.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult search(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        clearTable(cMsg, sMsg);
        cMsg.cpoOrdNum_C1.clear();
        // Apply Clear
        cMsg.rtrnTrkNtfyGrpCd_G.clear();
        cMsg.rtrnTrkNtfyGrpDescTxt_G.clear();
        cMsg.carrCd_G.clear();
        cMsg.locNm_G.clear();
        cMsg.schdPickUpDt_G.clear();
        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        cMsg.rcvTsDplyTxt_G.clear();
        cMsg.rqstRcvDtTxt_G.clear();
        // END   2019/05/09 T.Ogura [QC#50027,ADD]
        cMsg.inspCpltDt_G.clear();
        cMsg.shpgSvcLvlCd_G.clear();
        cMsg.actlPickUpDt_G.clear();
        cMsg.carrRsnCd_G.clear();
        cMsg.rtrnTrkStsCd_G.clear();

        S21SsmEZDResult ssmResult = NLAL1100Query.getInstance().search(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > sMsg.A.length() - 1) {

                cMsg.setMessageInfo(NLAL1100Constant.NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length() - 1);
                queryResCnt = sMsg.A.length() - 1;
            }

            List<String> cpoOrdNumList = new ArrayList<String>();

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxNum_A1, new BigDecimal(i));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A1, ZYPConstant.FLG_ON_Y);

                Map<String, String> outOfWhChkMap = new HashMap<String, String>();
                outOfWhChkMap.put("DS_ORD_CATG_CD", sMsg.A.no(i).dsOrdCatgCd_A1.getValue());
                outOfWhChkMap.put("DS_ORD_TP_CD", sMsg.A.no(i).dsOrdTpCd_A1.getValue());
                outOfWhChkMap.put("RTRN_RSN_CD", sMsg.A.no(i).rtrnRsnCd_A1.getValue());
                outOfWhChkMap.put("MDSE_CD", sMsg.A.no(i).mdseCd_A1.getValue());
                outOfWhChkMap.put("SHIP_TO_POST_CD", sMsg.A.no(i).addShipToPostCd_A1.getValue());
                outOfWhChkMap.put("RCV_RTL_WH_CD", sMsg.A.no(i).rtlWhCd_A1.getValue());

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).outOfWhInvtyProcFlg_A1, getOutOfWhInvtyProc(cMsg, outOfWhChkMap));

                // Create CPO# List for comment Display Check
                if (cpoOrdNumList.isEmpty() || !cpoOrdNumList.contains(sMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    cpoOrdNumList.add(sMsg.A.no(i).cpoOrdNum_A1.getValue());
                }

                // START 2019/05/09 T.Ogura [QC#50027,ADD]
                // Scheduled Pickup Time
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rcvTsDplyTxt_A1)) {

                    String systemTimeZoneTs = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_G1.getValue());

                    if(ZYPCommonFunc.hasValue(sMsg.A.no(i).schdPickUpDt_A1)){
                        systemTimeZoneTs = sMsg.A.no(i).schdPickUpDt_A1.getValue();
                    }

                    // Date + time
                    systemTimeZoneTs = systemTimeZoneTs + sMsg.A.no(i).rcvTsDplyTxt_A1.getValue();

                    SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, systemTimeZoneTs, sMsg.A.no(i).addShipToCtryCd_A1.getValue(), sMsg.A.no(i).addShipToPostCd_A1.getValue());

                    String date = timeInfo.getDateTime().substring(0, 8);
                    String time = timeInfo.getDateTime().substring(8, 12);

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdPickUpDt_A1, date);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdPickUpDt_BK, date);

                    splitHourMinute(sMsg.A.no(i).rcvTsDplyTxt_A1, sMsg.A.no(i).rqstRcvDtTxt_A1, time);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rcvTsDplyTxt_BK, sMsg.A.no(i).rcvTsDplyTxt_A1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rqstRcvDtTxt_BK, sMsg.A.no(i).rqstRcvDtTxt_A1);
                }
                // END   2019/05/09 T.Ogura [QC#50027,ADD]
            }

            // Copy from SMsg to cMsg
            int i = 0;

            for (; i < queryResCnt; i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Search" });

            // Search Comment
            if (cpoOrdNumList.size() == 1) {

                ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdNum_C1, cpoOrdNumList.get(0));
                searchComment(cMsg);
            }

        } else {

            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.setMessageInfo(NLAL1100Constant.NZZM0000E);
        }

        return ssmResult;
    }

    /**
     * getOutOfWhInvtyProc
     * @param cMsg NLAL1100CMsg
     * @param outOfWhChkMap Map<String, String>
     * @return String
     */
    public static String getOutOfWhInvtyProc(NLAL1100CMsg cMsg, Map<String, String> outOfWhChkMap) {

        // set Out Of Territory (NWZC1800 : Default WH API)
        NWZC180001PMsg nwzc180001PMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.slsDt, cMsg.slsDt_G1.getValue());
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.dsOrdCatgCd, outOfWhChkMap.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.dsOrdTpCd, outOfWhChkMap.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.dsRtrnRsnCd, outOfWhChkMap.get("RTRN_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.mdseCd, outOfWhChkMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(nwzc180001PMsg.postCd, outOfWhChkMap.get("SHIP_TO_POST_CD"));

        // API(NWZC1800) is executed
        NWZC180001 nwzc1800 = new NWZC180001();
        nwzc1800.execute(nwzc180001PMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(nwzc180001PMsg.rtlWhCd) && nwzc180001PMsg.rtlWhCd.getValue().equals(outOfWhChkMap.get("RCV_RTL_WH_CD"))) {

            return ZYPConstant.FLG_OFF_N;

        } else {

            return ZYPConstant.FLG_ON_Y;
        }
    }

    /**
     * The method explanation: The search processing. (Comment)
     * @param cMsg Business Component Interface Message
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchComment(NLAL1100CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLAL1100Query.getInstance().searchComment(cMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > cMsg.C.length()) {

                queryResCnt = cMsg.C.length();
            }

            cMsg.C.setValidCount(queryResCnt);
        }

        return ssmResult;
    }

    // START 2019/05/09 T.Ogura [QC#50027,ADD]
    /**
     * getAllDayTimes
     * @param timeHHMM EZDSStringItem
     * @param timeAMPM EZDSStringItem
     * @param hourMinute String
     */
    public static void splitHourMinute(EZDSStringItem timeHHMM, EZDSStringItem timeAMPM, String hourMinute) {

        if (ZYPCommonFunc.hasValue(hourMinute) && ZYPCommonFunc.isNumberCheck(hourMinute)) {

            String hh = null;
            String mm = null;
            int hour = 0;

            if (hourMinute.length() == NLAL1100Constant.HOUR_MINUTE_STR_LENGTH) {

                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NLAL1100Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                hourMinute = "0" + hourMinute;
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NLAL1100Constant.HOUR_MINUTE_STR_LENGTH + 2) {
                // ACTL_DELY_TM, SCHD_DELY_TM
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2, 4);
                hour = Integer.valueOf(hh);

            } else {

                timeHHMM.clear();
                timeAMPM.clear();
                return;
            }

            if (NLAL1100Constant.HALF_DAY_HOURS <= hour) {

                hour -= NLAL1100Constant.HALF_DAY_HOURS;
                ZYPEZDItemValueSetter.setValue(timeAMPM, NLAL1100Constant.TIME_PM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(String.format("%1$02d", hour), ":", mm));
                return;

            } else {

                ZYPEZDItemValueSetter.setValue(timeAMPM, NLAL1100Constant.TIME_AM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(hh, ":", mm));
                return;
            }
        }

        timeHHMM.clear();
        timeAMPM.clear();
        return;
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
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NLAL1100CMsg
     */
    public static void callNszc0330forSaveSearchOption(NLAL1100CMsg cMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) || isSameSaveSearchName(cMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S1);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);

        } else {

            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL1100Constant.BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, cMsg.srchOptUsrId_U1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.rmaNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.soNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.rwsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.rtrnLineDplyStsCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.ordAgingBcktCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.rtlWhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.rtlSwhCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.shipToCustCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.carrCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.slsRepBrCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.rqstPickUpDt_HF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.rqstPickUpDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.schdPickUpDt_HF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.schdPickUpDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.actlPickUpDt_HF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.actlPickUpDt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.serNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.psnCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.xxChkBox_CL.getValue());
        // QC#23044
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.sendRqstFlg_Y.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.sendRqstFlg_N.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_H1)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.svcConfigMstrPk_H1.getValue().toString());
        }

        if (executeNszc0330(cMsg, pMsg)) {

            createPullDownSearchOption(cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S1, pMsg.srchOptPk);

            cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Save Search" });
        }
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NLAL1100CMsg
     */
    public static void callNszc0330forDeleteSearchOption(NLAL1100CMsg cMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL1100Constant.BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, cMsg.srchOptUsrId_U1.getValue());

        if (executeNszc0330(cMsg, pMsg)) {

            createPullDownSearchOption(cMsg);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_S1.clear();
            cMsg.setMessageInfo(NLAL1100Constant.ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NLAL1100CMsg
     */
    public static void callNszc0330forSearchSearchOption(NLAL1100CMsg cMsg) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLAL1100Constant.BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, cMsg.srchOptUsrId_U1.getValue());

        if (!executeNszc0330(cMsg, pMsg)) {

            return;
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rmaNum_H1, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rwsNum_H1, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnLineDplyStsCd_H1, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.ordAgingBcktCd_H1, pMsg.srchOptTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H1, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_H1, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd_H1, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.slsRepBrCd_H1, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstPickUpDt_HF, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rqstPickUpDt_HT, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.schdPickUpDt_HF, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.schdPickUpDt_HT, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.actlPickUpDt_HF, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.actlPickUpDt_HT, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.serNum_H1, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.psnCd_H1, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CL, pMsg.srchOptTxt_20.getValue());
        // QC#23044
        ZYPEZDItemValueSetter.setValue(cMsg.sendRqstFlg_Y, pMsg.srchOptTxt_21.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.sendRqstFlg_N, pMsg.srchOptTxt_22.getValue());

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_18)) {

            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk_H1, new BigDecimal(pMsg.srchOptTxt_18.getValue()));

        } else {

            cMsg.svcConfigMstrPk_H1.clear();
        }
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NLAL1100CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NLAL1100CMsg cMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (String msgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        cMsg.srchOptPk_S1.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_TX.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * check duplicate search name
     * @param cMsg NLAL1100CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLAL1100CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L1.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L1.no(i))) {

                return false;
            }

            if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_L1.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S1) && cMsg.srchOptPk_S1.getValue().compareTo(cMsg.srchOptPk_L1.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * isSameSaveSearchName
     * @param cMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLAL1100CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S1)) {

            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L1.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L1.no(i))) {

                return false;
            }

            if (cMsg.srchOptPk_S1.getValue().compareTo(cMsg.srchOptPk_L1.no(i).getValue()) == 0) {

                if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_L1.no(i).getValue())) {

                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NLAL1100CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLAL1100CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S1)) {

            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L1.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L1.no(i))) {

                return;
            }

            if (cMsg.srchOptPk_S1.getValue().compareTo(cMsg.srchOptPk_L1.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L1.no(i));
            }
        }

        return;
    }

    /**
     * saveCurrentPageToSMsgDeliveries
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     */
    public static void saveCurrentPageToSMsg(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(cMsg.A.no(i).xxNum_A1.getValueInt()), null);
        }
    }

    /**
     * pagenation
     * @param bizMsg    NLAL1100CMsg
     * @param globalMsg NLAL1100SMsg
     * @param fromIdx   int
     */
    public static void pagenation(NLAL1100CMsg bizMsg, NLAL1100SMsg globalMsg, int fromIdx) {

        int sMsgIdx = 0;
        int cMsgIdx = 0;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(globalMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {

                if (fromIdx <= sMsgIdx && cMsgIdx < bizMsg.A.length()) {

                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(cMsgIdx), null);
                    cMsgIdx++;
                }

                sMsgIdx++;
            }
        }

        // set value to pagenation items
        bizMsg.A.setValidCount(cMsgIdx);
        bizMsg.xxPageShowFromNum.setValue(fromIdx + 1);
        bizMsg.xxPageShowToNum.setValue(fromIdx + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgIdx);
    }

    /**
     * <pre>
     * The check of TimeStamp for Update Tables.
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @param sMsgALine NLAL1100_ASMsg
     * @return boolean
     */
    public static boolean isSameTimeStamp(NLAL1100CMsg cMsg, NLAL1100_ASMsg sMsgALine) {

        // RWS Header
        RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, sMsgALine.rwsNum_A1);
        rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdrTMsg);

        if (rwsHdrTMsg == null) {

            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0009E);
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(sMsgALine.ezUpTime_A1.getValue(), sMsgALine.ezUpTimeZone_A1.getValue(), rwsHdrTMsg.ezUpTime.getValue(), rwsHdrTMsg.ezUpTimeZone.getValue())) {

            // anyone update
            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0009E);
            return false;
        }

        // RWS Detail
        RWS_DTLTMsg rwsDtlTMsg = new RWS_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsNum, sMsgALine.rwsNum_A1);
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.rwsDtlLineNum, sMsgALine.rwsDtlLineNum_A1);
        rwsDtlTMsg = (RWS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsDtlTMsg);

        if (rwsDtlTMsg == null) {

            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0009E);
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(sMsgALine.ezUpTime_A2.getValue(), sMsgALine.ezUpTimeZone_A2.getValue(), rwsDtlTMsg.ezUpTime.getValue(), rwsDtlTMsg.ezUpTimeZone.getValue())) {

            // anyone update
            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLAL1100Constant.NLBM0009E);
            return false;
        }

        return true;
    }

    /**
     * sort
     * @param sMsgArray EZDSMsgArray
     * @param sortItemNm String
     * @param sortOrdBy String
     * @param baseContents String[][] 
     * @param nullLast boolean
     */
    public static void sort(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents, boolean nullLast) {

        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);

        if (nullLast) {

            S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, sMsgArray.getValidCount());

        } else {

            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        }
    }

    /**
     * getPageStartRowIndex
     * @param index  int
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     * @return int
     */
    public static int getPageStartRowIndex(int index, NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        int startIndex = 0;

        String preRwsNum = "";
        int convIndex = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (index == i) {

                index = convIndex;
                break;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {

                convIndex++;

            } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxSmryLineFlg_A1.getValue()) && !preRwsNum.equals(sMsg.A.no(i).rwsNum_A1.getValue())) {

                // Summary Flag == Y
                convIndex++;
            }

            preRwsNum = sMsg.A.no(i).rwsNum_A1.getValue();
        }

        startIndex = (index / cMsg.A.length()) * cMsg.A.length();

        return startIndex;
    }
// START 2023/12/07 K.Ishizuka [QC#61300,ADD]
    /**
     * getEmlAddrFromTaskSvcBrMgr
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     */
    public static String getEmlAddrFromTaskSvcBrMgr(NLAL1100CMsg cMsg, String rmaNum) {
        S21SsmEZDResult ssmResult = NLAL1100Query.getInstance().getEmlAddrFromTaskSvcBrMgr(cMsg, rmaNum);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }
    
    /**
     * getSvcBrMngrEmlAddr
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     */
    public static String getSvcBrMngrEmlAddr(NLAL1100CMsg cMsg, String rmaNum) {

        S21SsmEZDResult ssmResult = NLAL1100Query.getInstance().getSvcBrMngrEmlAddr(cMsg, rmaNum);

        if (!ssmResult.isCodeNormal()) {
            return null;
        }

        Map<String, String> svcBrMngrEmlAddrMap = (Map<String, String>) ssmResult.getResultObject();
        return svcBrMngrEmlAddrMap.get("EML_ADDR").toString();
    }
    
    /**
     * sendMail
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @param strToAddr String
     * @param beforeTime
     * @param afterTime
     * @return void
     */
    public static String sendMail(NLAL1100CMsg cMsg, String rmaNum, String strToAddr, String beforeDateTime, String afterDateTime) {
        String afterErlStartTs = null;

        Map<String, Object> mailInfoMap = getDeliveryScheduleMailInfo(cMsg, rmaNum);

        if (mailInfoMap == null) {
            return afterErlStartTs;
        }

        if (ZYPCommonFunc.hasValue(beforeDateTime)) {
            SvcTimeZoneInfo convertBeforeDateTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, beforeDateTime, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
            String convertBeforeDate = "";
            String convertBeforeTime = "";
            if (convertBeforeDateTime != null) {
                convertBeforeDate = convertBeforeDateTime.getDateTime().substring(0, 8);
                convertBeforeTime = convertBeforeDateTime.getDateTime().substring(8, 12);
                beforeDateTime = convertDateTxt(convertBeforeDate) + "/" + convertBeforeTime;
            }
        }

        if (ZYPCommonFunc.hasValue(afterDateTime)) {
            SvcTimeZoneInfo convertAfterDateTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, afterDateTime, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
            String convertAfterDate = "";
            String convertAfterTime = "";
            if (convertAfterDateTime != null) {
                convertAfterDate = convertAfterDateTime.getDateTime().substring(0, 8);
                convertAfterTime = convertAfterDateTime.getDateTime().substring(8, 12);
                afterErlStartTs = convertAfterDateTime.getDateTime();
                afterDateTime = convertDateTxt(convertAfterDate) + "/" + convertAfterTime;
            }
        }

        if (!ZYPCommonFunc.hasValue(strToAddr)) {
            return afterErlStartTs;
        }

        S21MailGroup fromGrp = new S21MailGroup(cMsg.glblCmpyCd_G1.getValue(), NLAL1100Constant.MAIL_GROUP_ID_FROM);
        fromGrp.setMailKey1(NLAL1100Constant.MAIL_FROM_GRP);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(cMsg.glblCmpyCd_G1.getValue());

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailAddress toAddr = new S21MailAddress(cMsg.glblCmpyCd_G1.getValue(), strToAddr);
            mail.setToAddress(toAddr);

            S21MailTemplate template = new S21MailTemplate(cMsg.glblCmpyCd_G1.getValue(), NLAL1100Constant.MAIL_TEMPLATE_ID);

            if (template == null) {
                cMsg.setMessageInfo(NLAL1100Constant.NLBM1065E);
                return afterErlStartTs;
            }

            template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_BEFORE_DATE_TIME, beforeDateTime);
            template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_AFTER_DATE_TIME, afterDateTime);
            template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TASK_NUM, (String) mailInfoMap.get("SVC_TASK_NUM"));
            template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_FSR_NUM, (String) mailInfoMap.get("FSR_NUM"));
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("DS_ACCT_NM"))) {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_CUST_NM, (String) mailInfoMap.get("DS_ACCT_NM"));
            } else {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_CUST_NM, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("DEINS_LOC"))) {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_DEINS_LOC, (String) mailInfoMap.get("DEINS_LOC"));
            } else {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_DEINS_LOC, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("SER_NUM"))) {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_SER_NUM, (String) mailInfoMap.get("SER_NUM"));
            } else {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_SER_NUM, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("PSN_FIRST_NM")) && ZYPCommonFunc.hasValue((String) mailInfoMap.get("PSN_LAST_NM"))) {
                String techNm = (String) mailInfoMap.get("PSN_FIRST_NM");
                if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("PSN_MID_NM"))) {
                    techNm = techNm + " " + (String) mailInfoMap.get("PSN_MID_NM");
                }
                techNm = techNm + " " + (String) mailInfoMap.get("PSN_LAST_NM");
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TECH_NM, techNm);
            } else {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TECH_NM, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("EML_ADDR"))) {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TECH_EML_ADDR, (String) mailInfoMap.get("EML_ADDR"));
            } else {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TECH_EML_ADDR, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("OFC_TEL_NUM"))) {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TECH_PHONE_NUM, (String) mailInfoMap.get("OFC_TEL_NUM"));
            } else {
                template.setTemplateParameter(NLAL1100Constant.EMAIL_PARAM_TECH_PHONE_NUM, "");
            }

            // Set mail subject
            mail.setSubject(template.getSubject(NLAL1100Constant.ML_LANG), NLAL1100Constant.ML_LANG_CD);
            mail.setMailTemplate(template);

            // Post
            mail.postMail();
        }
        return afterErlStartTs;
    }
    
    /**
     * getSvcBrMngrEmlAddr
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @return String
     */
    public static Map<String, Object> getDeliveryScheduleMailInfo(NLAL1100CMsg cMsg, String rmaNum) {

        S21SsmEZDResult ssmResult = NLAL1100Query.getInstance().getDeliveryScheduleMailInfo(cMsg, rmaNum);

        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        return (Map<String, Object>) resultList.get(0);
    }
    
    /**
     * callSendTaskInfoAPI
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @param afterErlStartTs String
     * @param userId String
     * @param beforeTime String
     * @param afterTime String
     * @return boolean
     */
    public static NSZC043001PMsg updateSvcTaskErlStartTs(NLAL1100CMsg cMsg, String rmaNum, String afterErlStartTs, String userId, String beforeDateTime, String afterDateTime) {

        NSZC043001PMsg pMsg = new NSZC043001PMsg();
        if (!ZYPCommonFunc.hasValue(afterErlStartTs)) {
            return pMsg;
        }
        S21SsmEZDResult result = NLAL1100Query.getInstance().getSvcTaskInfoFromRmaNum(cMsg, rmaNum);
        Map<String, Object> svcTaskInfoMap = (Map<String, Object>) result.getResultObject();
        if (svcTaskInfoMap == null) {
            return pMsg;
        }
        String svcTaskNum = (String) svcTaskInfoMap.get("SVC_TASK_NUM");
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            return pMsg;
        }

        // get late start time
        BigDecimal meetTruckRspTmMnAot = ZYPCodeDataUtil.getNumConstValue(NLAL1100Constant.NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT, cMsg.glblCmpyCd_G1.getValue());
        if (!ZYPCommonFunc.hasValue(meetTruckRspTmMnAot)) {
            meetTruckRspTmMnAot = NLAL1100Constant.DEF_MEET_TRUCK_RSP_TM_MN_AOT;
        }
        String erlStartDt = afterErlStartTs.substring(0, 8);
        String erlStartTm = afterErlStartTs.substring(8, 12);
        String afterLateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(cMsg.glblCmpyCd_G1.getValue(), meetTruckRspTmMnAot, (BigDecimal) svcTaskInfoMap.get("SVC_MACH_MSTR_PK"), erlStartDt, convertTimeForHHmmss(erlStartTm),
                false);

        if (ZYPCommonFunc.hasValue(afterLateStartTs) && ZYPCommonFunc.hasValue(afterLateStartTs)) {
            pMsg = callFsrUpdateAPI(cMsg, svcTaskNum, userId, rmaNum, beforeDateTime, afterDateTime);
        }
        return pMsg;
    }
    
    /**
     * Check API Result
     * @param cMsg NLAL1100CMsg
     * @param errChkItem EZDSStringItem
     * @param apiErrMsgList List<String>
     * @return boolean true : OK, false : NG
     */
    public static boolean chkApiRslt(NLAL1100CMsg cMsg, EZDSStringItem errChkItem, List<String> apiErrMsgList) {

        if (apiErrMsgList != null && !apiErrMsgList.isEmpty()) {

            for (String msgId : apiErrMsgList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId, null);

                    if (msgId.endsWith("E")) {

                        errChkItem.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    /**
     * convertTimeForHHmmss
     * @param time String time
     * @return String
     */
    public static String convertTimeForHHmmss(String time) {
        if (!ZYPCommonFunc.hasValue(time) || time.length() != NLAL1100Constant.HOUR_MINUTE_LEN) {
            time = NLAL1100Constant.DEF_HHMMSS;
        } else {
            time = time + "00";
        }
        return time;
    }
    
    /**
     * callFsrUpdateAPI
     * @param cMsg NLAL1100CMsg
     * @param svcTaskNum String
     * @param userId String
     * @param rmaNum String
     * @param beforeTime String
     * @param afterTime String
     * @return NSZC043001PMsg
     */
    public static NSZC043001PMsg callFsrUpdateAPI(NLAL1100CMsg cMsg, String svcTaskNum, String userId, String rmaNum, String beforeDateTime, String afterDateTime) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
        svcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKey(svcTaskTMsg);

        NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, cMsg.glblCmpyCd_G1.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, svcTaskTMsg.fsrNum.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.slsDt, cMsg.slsDt_G1.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, userId);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, svcTaskTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, svcTaskNum);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.GENERAL);

        Map<String, Object> mailInfoMap = getDeliveryScheduleMailInfo(cMsg, rmaNum);

        if (ZYPCommonFunc.hasValue(beforeDateTime)) {
            SvcTimeZoneInfo convertBeforeDateTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, beforeDateTime, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
            String convertBeforeDate = "";
            String convertBeforeTime = "";
            if (convertBeforeDateTime != null) {
                convertBeforeDate = convertBeforeDateTime.getDateTime().substring(0, 8);
                convertBeforeTime = convertBeforeDateTime.getDateTime().substring(8, 12);
                beforeDateTime = convertDateTxt(convertBeforeDate) + "/" + convertBeforeTime;
            }
        }

        if (ZYPCommonFunc.hasValue(afterDateTime)) {
            SvcTimeZoneInfo convertAfterDateTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, afterDateTime, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
            String convertAfterDate = "";
            String convertAfterTime = "";
            if (convertAfterDateTime != null) {
                convertAfterDate = convertAfterDateTime.getDateTime().substring(0, 8);
                convertAfterTime = convertAfterDateTime.getDateTime().substring(8, 12);
                afterDateTime = convertDateTxt(convertAfterDate) + "/" + convertAfterTime;
            }
        }

        StringBuilder sbSvcCmntTxt = new StringBuilder();
        sbSvcCmntTxt.append("Scheduled Pickup Date of this task has been updated. Please confirm and adjust the schedule if you need to.");
        sbSvcCmntTxt.append("\n");
        sbSvcCmntTxt.append("Before Schedule Date/Time : ");
        sbSvcCmntTxt.append(beforeDateTime);
        sbSvcCmntTxt.append(". After Schedule Date/Time : ");
        sbSvcCmntTxt.append(afterDateTime);
        sbSvcCmntTxt.append(".");
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMemoList.no(0).svcCmntTxt, sbSvcCmntTxt.toString());
        fsrUpdPMsg.taskList.setValidCount(1);
        fsrUpdPMsg.svcMemoList.setValidCount(1);

        NSZC043001 api = new NSZC043001();
        api.execute(fsrUpdPMsg, ONBATCH_TYPE.ONLINE);

        return fsrUpdPMsg;
    }
 
    /**
     * convertDateTxt
     * @param strDate String
     * @return String
     */
    public static String convertDateTxt(String strDate) {
        SimpleDateFormat dateFormatIn = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        SimpleDateFormat dateFormatOut = new SimpleDateFormat(NLAL1100Constant.DATE_FORMAT_DDMMMYYYY, Locale.US);
        String result = "";
        if (!ZYPCommonFunc.hasValue(strDate)) {
            return result;
        }
        try {
            Date resultDate = dateFormatIn.parse(strDate);
            result = dateFormatOut.format(resultDate);
        } catch (ParseException e) {
            return result;
        }
        return result;
    }

    /**
     * convertDateTxt
     * @param String glblCmpyCd
     * @param String svcByLineBizTp
     * @return String
     */
    private static String getMeetTruckErlStrtTm(String glblCmpyCd, String svcByLineBizTp) {
        String meetTruckErlStrtTm = null;

        if (LINE_BIZ_TP.LFS.equals(svcByLineBizTp)) {
            meetTruckErlStrtTm = ZYPCodeDataUtil.getVarCharConstValue(NLAL1100Constant.VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS, glblCmpyCd);
        } else if (LINE_BIZ_TP.PPS.equals(svcByLineBizTp)) {
            meetTruckErlStrtTm = ZYPCodeDataUtil.getVarCharConstValue(NLAL1100Constant.VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS, glblCmpyCd);
        } else {
            meetTruckErlStrtTm = ZYPCodeDataUtil.getVarCharConstValue(NLAL1100Constant.VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS, glblCmpyCd);
        }
        return meetTruckErlStrtTm;
    }
// END   2023/12/07 K.Ishizuka [QC#61300,ADD]
}

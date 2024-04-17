/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0270;

import static business.blap.NSBL0270.constant.NSBL0270Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0270.common.NSBL0270CommonLogic;
import business.db.SVC_PRC_SHIFTTMsg;
import business.db.SVC_PRC_SHIFTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Service Pricing Shift Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0270CMsg cMsg = (NSBL0270CMsg) arg0;
        NSBL0270SMsg sMsg = (NSBL0270SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0270Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0270Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0270Scrn00_CMN_Submit(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }
        //check DB uploaded by another person
        SVC_PRC_SHIFTTMsgArray maxSvcPrcShiftArray = NSBL0270CommonLogic.getMaxSvcPrcShiftNum(cMsg);
        if (maxSvcPrcShiftArray.getValidCount() != sMsg.A.getValidCount()) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        //timestamp check
        boolean isErr = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSBL0270_ACMsg aCMsg = cMsg.A.no(i);
            setValue(aCMsg.xxRowNum_A, BigDecimal.valueOf(i));
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A1, aCMsg.xxEndDplyTmTxt_A1, "Monday Start", "Monday End", "Monday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A2, aCMsg.xxEndDplyTmTxt_A2, "Tuesday Start", "Tuesday End", "Tuesday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A3, aCMsg.xxEndDplyTmTxt_A3, "Wednesday Start", "Wednesday End", "Wednesday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A4, aCMsg.xxEndDplyTmTxt_A4, "Thursday Start", "Thursday End", "Thursday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A5, aCMsg.xxEndDplyTmTxt_A5, "Friday Start", "Friday End", "Friday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A6, aCMsg.xxEndDplyTmTxt_A6, "Saturday Start", "Saturday End", "Saturday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A7, aCMsg.xxEndDplyTmTxt_A7, "Sunday Start", "Sunday End", "Sunday")) {
                isErr = true;
            }
            if (!checkStartEndSetting(aCMsg.xxStartDplyTmTxt_A8, aCMsg.xxEndDplyTmTxt_A8, "Holiday Start", "Holiday End", "Holiday")) {
                isErr = true;
            }
        }
        if (isErr) {
            return;
        }
        //check time of days
        //sort
        // Sort xxTblSortNum_F, xxSortNum_F
        List<NSBL0270_ACMsg> monSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> tueSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> wedSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> thuSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> friSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> satSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> sunSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> holSortList = new ArrayList<NSBL0270_ACMsg>();
        List<NSBL0270_ACMsg> activeSortList = new ArrayList<NSBL0270_ACMsg>();
        List<String> lobList = new ArrayList<String>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSBL0270_ACMsg aCMsg = (NSBL0270_ACMsg) cMsg.A.no(i).clone();
            createList(monSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A1);
            createList(tueSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A2);
            createList(wedSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A3);
            createList(thuSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A4);
            createList(friSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A5);
            createList(satSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A6);
            createList(sunSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A7);
            createList(holSortList, aCMsg, aCMsg.xxChkBox_A1, aCMsg.xxStartDplyTmTxt_A8);
            createList(activeSortList, lobList, aCMsg, aCMsg.xxChkBox_A1);
        }
        Collections.sort(monSortList, new TsComparatorMon());
        Collections.sort(tueSortList, new TsComparatorTue());
        Collections.sort(wedSortList, new TsComparatorWed());
        Collections.sort(thuSortList, new TsComparatorThu());
        Collections.sort(friSortList, new TsComparatorFri());
        Collections.sort(satSortList, new TsComparatorSat());
        Collections.sort(sunSortList, new TsComparatorSun());
        Collections.sort(holSortList, new TsComparatorHol());
        //check timestamp
        isErr = checkTmStampMon(cMsg, monSortList, isErr, lobList);
        isErr = checkTmStampTue(cMsg, tueSortList, isErr, lobList);
        isErr = checkTmStampWed(cMsg, wedSortList, isErr, lobList);
        isErr = checkTmStampThu(cMsg, thuSortList, isErr, lobList);
        isErr = checkTmStampFri(cMsg, friSortList, isErr, lobList);
        isErr = checkTmStampSat(cMsg, satSortList, isErr, lobList);
        isErr = checkTmStampSun(cMsg, sunSortList, isErr, lobList);
        isErr = checkTmStampHol(cMsg, holSortList, isErr, lobList);
        //check After Hours Shift Flag is one record for each LOB
        isErr = checkAhs(cMsg, activeSortList, isErr, lobList);
        if (isErr) {
            return;
        }

        //insert or update SVC_PRC_SHIFT info
        List<NSBL0270_ACMsg> submitList = getSubmitData(cMsg, sMsg);
        if (submitList == null || submitList.isEmpty()) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
        doSubmit(cMsg, submitList);

    }

    private boolean checkTmStampMon(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A1.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A1.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A1, aCMsg.xxEndDplyTmTxt_A1, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A1.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }

            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineMon(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobMon(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampTue(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A2.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A2.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A2, aCMsg.xxEndDplyTmTxt_A2, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A2.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineTue(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobTue(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampWed(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A3.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A3.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A3, aCMsg.xxEndDplyTmTxt_A3, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A3.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineWed(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobWed(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampThu(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A4.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A4.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A4, aCMsg.xxEndDplyTmTxt_A4, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A4.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineThu(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobThu(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampFri(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A5.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A5.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A5, aCMsg.xxEndDplyTmTxt_A5, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A5.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineFri(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobFri(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampSat(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A6.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A6.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A6, aCMsg.xxEndDplyTmTxt_A6, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A6.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineSat(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobSat(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampSun(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A7.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A7.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A7, aCMsg.xxEndDplyTmTxt_A7, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A7.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineSun(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobSun(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTmStampHol(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<BigDecimal, String>> errMsgListForLine = new ArrayList<Map<BigDecimal, String>>();
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        String errMsg = null;
        String prevSvcLineBizCd = null;
        boolean isErrNSAM0464E = false;
        boolean isFirst = true;
        Calendar calTo = Calendar.getInstance();
        try {
            for (int i = 0; i < sortList.size(); i++) {
                NSBL0270_ACMsg aCMsg = sortList.get(i);
                if ((prevSvcLineBizCd != null && !prevSvcLineBizCd.equals(aCMsg.svcLineBizCd_A.getValue())) || i == sortList.size() - 1) {
                    if (i != sortList.size() - 1) {
                        checkLastTmStamp(sortList.get(i - 1).xxEndDplyTmTxt_A8.getValue(), errMsgListForLob, isErrNSAM0464E, prevSvcLineBizCd);
                        calTo.clear();
                        isErrNSAM0464E = false;
                        isFirst = true;
                    } else {
                        checkLastTmStamp(aCMsg.xxEndDplyTmTxt_A8.getValue(), errMsgListForLob, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                    }
                }
                errMsg = procTmStmp(aCMsg.xxStartDplyTmTxt_A8, aCMsg.xxEndDplyTmTxt_A8, calTo, isFirst);
                setErrMsgForLine(sortList, i, errMsg, errMsgListForLine);
                isErrNSAM0464E = setErrMsgForLob(errMsgListForLob, errMsg, isErrNSAM0464E, aCMsg.svcLineBizCd_A.getValue());
                calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(aCMsg.xxEndDplyTmTxt_A8.getValue()));
                prevSvcLineBizCd = aCMsg.svcLineBizCd_A.getValue();
                isFirst = false;
            }
            checkNotExistLob(sortList, lobList, errMsgListForLob);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                isErr = setErrInfoForLineHol(cMsg, errMsgListForLine, i, isErr);
                isErr = setErrInfoForLobHol(cMsg, errMsgListForLob, i, isErr);
            }
            return isErr;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkAhs(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> sortList, boolean isErr, List<String> lobList) {
        List<Map<String, String>> errMsgListForLob = new ArrayList<Map<String, String>>();
        for (String lobString : lobList) {
            if (sortList.size() != 0) {
                int cnt = 0;
                for (NSBL0270_ACMsg sortMsg :  sortList) {
                    if (sortMsg.svcLineBizCd_A.getValue().equals(lobString) && ZYPConstant.FLG_OFF_N.equals(sortMsg.xxChkBox_A2.getValue())) {
                        cnt++;
                    }
                }
                if (cnt != 1) {
                    HashMap<String, String> errMap = new HashMap<String, String>();
                    errMap.put(lobString, NSAM0468E);
                    errMsgListForLob.add(errMap);
                }
            }
        }
        if (!errMsgListForLob.isEmpty()) {
            isErr = true;
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                for (Map<String, String> errMsgMap : errMsgListForLob) {
                    if (errMsgMap.get(cMsg.A.no(i).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                        cMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, errMsgMap.get(cMsg.A.no(i).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(i).svcLineBizCd_A.getValue()});
                    }
                }
            }
        }
        return isErr;
    }

    /**
     * @param sortList
     * @param lobList
     * @param errMsgListForLob
     * @param isErrNSAM0464E
     * @param isErrLob
     */
    private void checkNotExistLob(List<NSBL0270_ACMsg> sortList, List<String> lobList, List<Map<String, String>> errMsgListForLob) {
        boolean isErrLob = true;
        for (String lobString : lobList) {
            if (sortList.size() != 0) {
                for (NSBL0270_ACMsg sortMsg : sortList) {
                    if (sortMsg.svcLineBizCd_A.getValue().equals(lobString)) {
                        isErrLob = false;
                        break;
                    }
                }
            }
            if (isErrLob) {
                setErrMsgForLob(errMsgListForLob, NSAM0464E, false, lobString);
            }
            isErrLob = true;
        }
    }

    private void checkLastTmStamp(String xxEndDplyTmTxt, List<Map<String, String>> errMsgListForLob, boolean isErrNSAM0464E, String svcLineBizCd) throws ParseException {
        Calendar calTo = Calendar.getInstance();
        Calendar calLast = Calendar.getInstance();
        calTo.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(xxEndDplyTmTxt));
        calLast.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(TS_END));
        if (calTo.compareTo(calLast) != 0) {
            setErrMsgForLob(errMsgListForLob, NSAM0464E, isErrNSAM0464E, svcLineBizCd);
        }
    }

    private boolean setErrInfoForLineMon(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A1.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A1.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineTue(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A2.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A2.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineWed(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A3.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A3.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineThu(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A4.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A4.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineFri(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A5.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A5.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineSat(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A6.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A6.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineSun(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A7.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A7.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLineHol(NSBL0270CMsg cMsg, List<Map<BigDecimal, String>> errMsgListForLine, int idx, boolean isErr) {
        if (!errMsgListForLine.isEmpty()) {
            for (Map<BigDecimal, String> errMsgMap : errMsgListForLine) {
                if (errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()) != null) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A8.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A8.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).xxRowNum_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobMon(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A1.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A1.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobTue(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A2.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A2.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobWed(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A3.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A3.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobThu(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A4.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A4.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobFri(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A5.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A5.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobSat(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A6.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A6.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobSun(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A7.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A7.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrInfoForLobHol(NSBL0270CMsg cMsg, List<Map<String, String>> errMsgListForLob, int idx, boolean isErr) {
        if (!errMsgListForLob.isEmpty()) {
            for (Map<String, String> errMsgMap : errMsgListForLob) {
                if (errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()) != null && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(idx).xxChkBox_A1.getValue())) {
                    cMsg.A.no(idx).xxStartDplyTmTxt_A8.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                    cMsg.A.no(idx).xxEndDplyTmTxt_A8.setErrorInfo(1, errMsgMap.get(cMsg.A.no(idx).svcLineBizCd_A.getValue()), new String[]{cMsg.A.no(idx).svcLineBizCd_A.getValue()});
                }
            }
            return true;
        }
        return isErr;
    }

    private boolean setErrMsgForLob(List<Map<String, String>> errMsgListForLob, String errMsg, boolean isErrNSAM0464E, String svcLineBizCd) {
        if (!isErrNSAM0464E && errMsg != null && NSAM0464E.equals(errMsg)) {
            HashMap<String, String> errMap = new HashMap<String, String>();
            errMap.put(svcLineBizCd, errMsg);
            errMsgListForLob.add(errMap);
            return true;
        }
        return isErrNSAM0464E;
    }

    private void setErrMsgForLine(List<NSBL0270_ACMsg> sortList, int idx, String errMsg, List<Map<BigDecimal, String>> errMsgList) {
        HashMap<BigDecimal, String> errMap = new HashMap<BigDecimal, String>();
        if (errMsg != null) {
            if (NSAM0463E.equals(errMsg)) {
                errMap.put(sortList.get(idx - 1).xxRowNum_A.getValue(), errMsg);
                errMsgList.add(errMap);
                errMap.put(sortList.get(idx).xxRowNum_A.getValue(), errMsg);
                errMsgList.add(errMap);
            }
        }
    }

    private String procTmStmp(EZDCStringItem fromItem, EZDCStringItem toItem, Calendar calTo, boolean isFirst) throws ParseException {
        Date fromTsDt;
        Calendar calFrom = Calendar.getInstance();
        fromTsDt = new SimpleDateFormat(FORMAT_HHMMSS).parse(fromItem.getValue());
        calFrom.setTime(fromTsDt);
        String errMsg = checkTimeStamp(calFrom, calTo, isFirst);
        return errMsg;
    }

    private String checkTimeStamp(Calendar calFrom, Calendar calTo, boolean isFirst) throws ParseException {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(new SimpleDateFormat(FORMAT_HHMMSS).parse(TS_START));
        if (isFirst) {
            if (calStart.compareTo(calFrom) != 0) {
                return NSAM0464E;
            }
        } else {
            calTo.add(Calendar.SECOND, 1);
            if (calFrom.compareTo(calTo) > 0) {
                return NSAM0464E;
            } else if (calFrom.compareTo(calTo) < 0) {
                return NSAM0463E;
            }
        }
        return null;
    }

    /**
     * SVC_PRC_SHIFT sort class(Monday)
     *
     */
    private static final class TsComparatorMon implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A1.getValue().compareTo(line2.xxStartDplyTmTxt_A1.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Tuesday)
     *
     */
    private static final class TsComparatorTue implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A2.getValue().compareTo(line2.xxStartDplyTmTxt_A2.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Wednesday)
     *
     */
    private static final class TsComparatorWed implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A3.getValue().compareTo(line2.xxStartDplyTmTxt_A3.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Thursday)
     *
     */
    private static final class TsComparatorThu implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A4.getValue().compareTo(line2.xxStartDplyTmTxt_A4.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Friday)
     *
     */
    private static final class TsComparatorFri implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A5.getValue().compareTo(line2.xxStartDplyTmTxt_A5.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Saturday)
     *
     */
    private static final class TsComparatorSat implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A6.getValue().compareTo(line2.xxStartDplyTmTxt_A6.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Sunday)
     *
     */
    private static final class TsComparatorSun implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A7.getValue().compareTo(line2.xxStartDplyTmTxt_A7.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    /**
     * SVC_PRC_SHIFT sort class(Holiday)
     *
     */
    private static final class TsComparatorHol implements Comparator<NSBL0270_ACMsg> {
        @Override
        public int compare(NSBL0270_ACMsg line1, NSBL0270_ACMsg line2) {
            int compared;
            compared = line1.svcLineBizCd_A.getValue().compareTo(line2.svcLineBizCd_A.getValue());
            if (compared != 0) {
                return compared;
            }
            compared = line1.xxStartDplyTmTxt_A8.getValue().compareTo(line2.xxStartDplyTmTxt_A8.getValue());
            if (compared != 0) {
                return compared;
            }
            return 0;
        }
    }

    private void createList(List<NSBL0270_ACMsg> sortList, NSBL0270_ACMsg aCMsg, EZDCStringItem activeFlg, EZDCStringItem startDplyTmTxt) {
        //exclude inactive data
        if (ZYPConstant.FLG_ON_Y.equals(activeFlg.getValue()) && hasValue(startDplyTmTxt)) {
            sortList.add(aCMsg);
        }
    }

    private void createList(List<NSBL0270_ACMsg> sortList, List<String> sortListString, NSBL0270_ACMsg aCMsg, EZDCStringItem activeFlg) {
        //exclude inactive data
        if (ZYPConstant.FLG_ON_Y.equals(activeFlg.getValue())) {
            sortList.add(aCMsg);
            if (!sortListString.contains(aCMsg.svcLineBizCd_A.getValue())) {
                sortListString.add(aCMsg.svcLineBizCd_A.getValue());
            }
        }
    }

    private boolean checkStartEndSetting(EZDCStringItem fromItem, EZDCStringItem toItem, String errItemFrom, String errItemTo, String errDay) {
        if (!hasValue(fromItem) && hasValue(toItem)) {
            fromItem.setErrorInfo(1, NSAM0461E, new String[]{errItemFrom});
            return false;
        } else if (hasValue(fromItem) && !hasValue(toItem)) {
            toItem.setErrorInfo(1, NSAM0461E, new String[]{errItemTo});
            return false;
        } else if (hasValue(fromItem) && hasValue(toItem)) {
            BigDecimal fromItemBc = new BigDecimal(fromItem.getValue());
            BigDecimal toItemBc = new BigDecimal(toItem.getValue());
            if (fromItemBc.compareTo(toItemBc) > 0) {
                fromItem.setErrorInfo(1, NSAM0462E, new String[]{errDay});
                toItem.setErrorInfo(1, NSAM0462E, new String[]{errDay});
                return false;
            }
        }
        return true;
    }

    private List<NSBL0270_ACMsg> getSubmitData(NSBL0270CMsg cMsg, NSBL0270SMsg sMsg) {

        List<NSBL0270_ACMsg> submitList = new ArrayList<NSBL0270_ACMsg>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            //update data
            if (hasValue(cMsg.A.no(i).svcPrcShiftPk_A)) {
                for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                    if (cMsg.A.no(i).svcPrcShiftPk_A.getValueInt() == sMsg.A.no(j).svcPrcShiftPk_A.getValueInt()) {
                        if (isChangeData(cMsg.A.no(i), sMsg.A.no(j))) {
                            submitList.add(cMsg.A.no(i));
                        }
                    }
                }
            //insert data
            } else {
                submitList.add(cMsg.A.no(i));
            }
        }
        return submitList;
    }

    private boolean isChangeData(NSBL0270_ACMsg acMsg, NSBL0270_ASMsg asMsg) {

        if (!acMsg.svcLineBizCd_A.getValue().equals(asMsg.svcLineBizCd_A.getValue())) {
            return true;
        }
        if (!acMsg.svcPrcShiftNum_A.getValue().equals(asMsg.svcPrcShiftNum_A.getValue())) {
            return true;
        }
        if (!acMsg.xxChkBox_A1.getValue().equals(asMsg.xxChkBox_A1.getValue())) {
            return true;
        }
        if (!acMsg.xxChkBox_A2.getValue().equals(asMsg.xxChkBox_A2.getValue())) {
            return true;
        }
        if (!acMsg.svcLineBizCd_A.getValue().equals(asMsg.svcLineBizCd_A.getValue())) {
            return true;
        }
        if (!acMsg.svcPrcShiftDescTxt_A.getValue().equals(asMsg.svcPrcShiftDescTxt_A.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A1.getValue().equals(asMsg.xxStartDplyTmTxt_A1.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A1.getValue().equals(asMsg.xxEndDplyTmTxt_A1.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A2.getValue().equals(asMsg.xxStartDplyTmTxt_A2.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A2.getValue().equals(asMsg.xxEndDplyTmTxt_A2.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A3.getValue().equals(asMsg.xxStartDplyTmTxt_A3.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A3.getValue().equals(asMsg.xxEndDplyTmTxt_A3.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A4.getValue().equals(asMsg.xxStartDplyTmTxt_A4.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A4.getValue().equals(asMsg.xxEndDplyTmTxt_A4.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A5.getValue().equals(asMsg.xxStartDplyTmTxt_A5.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A5.getValue().equals(asMsg.xxEndDplyTmTxt_A5.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A6.getValue().equals(asMsg.xxStartDplyTmTxt_A6.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A6.getValue().equals(asMsg.xxEndDplyTmTxt_A6.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A7.getValue().equals(asMsg.xxStartDplyTmTxt_A7.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A7.getValue().equals(asMsg.xxEndDplyTmTxt_A7.getValue())) {
            return true;
        }
        if (!acMsg.xxStartDplyTmTxt_A8.getValue().equals(asMsg.xxStartDplyTmTxt_A8.getValue())) {
            return true;
        }
        if (!acMsg.xxEndDplyTmTxt_A8.getValue().equals(asMsg.xxEndDplyTmTxt_A8.getValue())) {
            return true;
        }
        return false;
    }

    private void doSubmit(NSBL0270CMsg cMsg, List<NSBL0270_ACMsg> submitList) {

        for (int i = 0; i < submitList.size(); i++) {
            NSBL0270_ACMsg acMsg = submitList.get(i);
            if (hasValue(acMsg.svcPrcShiftPk_A)) {
                if (!updateSvcPrcShift(cMsg, acMsg)) {
                    return;
                }
            } else {
                if (!insertSvcPrcShift(cMsg, acMsg)) {
                    return;
                }
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private boolean insertSvcPrcShift(NSBL0270CMsg cMsg, NSBL0270_ACMsg acMsg) {

        SVC_PRC_SHIFTTMsg insMsg  = new SVC_PRC_SHIFTTMsg();

        setValue(insMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insMsg.svcPrcShiftPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PRC_SHIFT_SQ));

        SVC_PRC_SHIFTTMsgArray maxSvcPrcShiftArray = NSBL0270CommonLogic.getMaxSvcPrcShiftNum(cMsg);
        String initSvcPrcShiftNum = ZYPCodeDataUtil.getVarCharConstValue(INIT_SVC_PRC_SHIFT_NUM, cMsg.glblCmpyCd.getValue());
        String maxSvcPrcShiftNumStr = null;
        int svcPrcShiftNumLen = 0;
        if (initSvcPrcShiftNum != null) {
            svcPrcShiftNumLen = initSvcPrcShiftNum.length();
        }
        if (maxSvcPrcShiftArray.getValidCount() > 0) {
            int maxSvcPrcShiftNumInt = Integer.parseInt(maxSvcPrcShiftArray.no(0).svcPrcShiftNum.getValue());
            maxSvcPrcShiftNumInt++;
            maxSvcPrcShiftNumStr = String.valueOf(maxSvcPrcShiftNumInt);
            if (maxSvcPrcShiftNumStr.length() < svcPrcShiftNumLen) {
                maxSvcPrcShiftNumStr = ZYPCommonFunc.leftPad(maxSvcPrcShiftNumStr, svcPrcShiftNumLen, BigDecimal.ZERO.toString());
            }
        } else {
            maxSvcPrcShiftNumStr = initSvcPrcShiftNum;
        }
        setValue(acMsg.svcPrcShiftNum_A, maxSvcPrcShiftNumStr);

        setParam(cMsg, acMsg, insMsg);
        S21FastTBLAccessor.insert(insMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_SVC_PRC_SHIFT });
            return false;
        }
        return true;
    }

    private boolean updateSvcPrcShift(NSBL0270CMsg cMsg, NSBL0270_ACMsg acMsg) {

        SVC_PRC_SHIFTTMsg inMsg  = new SVC_PRC_SHIFTTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.svcPrcShiftPk, acMsg.svcPrcShiftPk_A);

        SVC_PRC_SHIFTTMsg updTMsg = (SVC_PRC_SHIFTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setParam(cMsg, acMsg, updTMsg);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_SVC_PRC_SHIFT });
            return false;
        }
        return true;
    }

    private void setParam(NSBL0270CMsg cMsg, NSBL0270_ACMsg acMsg, SVC_PRC_SHIFTTMsg tMsg) {

        setValue(tMsg.svcPrcShiftNum, acMsg.svcPrcShiftNum_A);
        if (hasValue(acMsg.xxChkBox_A1)) {
            setValue(tMsg.svcPrcShiftActvFlg, acMsg.xxChkBox_A1);
        } else {
            setValue(tMsg.svcPrcShiftActvFlg, ZYPConstant.FLG_OFF_N);
        }

        if (hasValue(acMsg.xxChkBox_A2)) {
            setValue(tMsg.svcPrcShiftAhsFlg, acMsg.xxChkBox_A2);
        } else {
            setValue(tMsg.svcPrcShiftAhsFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(tMsg.svcLineBizCd, acMsg.svcLineBizCd_A);
        setValue(tMsg.svcPrcShiftDescTxt, acMsg.svcPrcShiftDescTxt_A);
        setValue(tMsg.svcPrcMonStartValTxt, acMsg.xxStartDplyTmTxt_A1);
        setValue(tMsg.svcPrcMonEndValTxt, acMsg.xxEndDplyTmTxt_A1);
        setValue(tMsg.svcPrcTueStartValTxt, acMsg.xxStartDplyTmTxt_A2);
        setValue(tMsg.svcPrcTueEndValTxt, acMsg.xxEndDplyTmTxt_A2);
        setValue(tMsg.svcPrcWedStartValTxt, acMsg.xxStartDplyTmTxt_A3);
        setValue(tMsg.svcPrcWedEndValTxt, acMsg.xxEndDplyTmTxt_A3);
        setValue(tMsg.svcPrcThuStartValTxt, acMsg.xxStartDplyTmTxt_A4);
        setValue(tMsg.svcPrcThuEndValTxt, acMsg.xxEndDplyTmTxt_A4);
        setValue(tMsg.svcPrcFriStartValTxt, acMsg.xxStartDplyTmTxt_A5);
        setValue(tMsg.svcPrcFriEndValTxt, acMsg.xxEndDplyTmTxt_A5);
        setValue(tMsg.svcPrcSatStartValTxt, acMsg.xxStartDplyTmTxt_A6);
        setValue(tMsg.svcPrcSatEndValTxt, acMsg.xxEndDplyTmTxt_A6);
        setValue(tMsg.svcPrcSunStartValTxt, acMsg.xxStartDplyTmTxt_A7);
        setValue(tMsg.svcPrcSunEndValTxt, acMsg.xxEndDplyTmTxt_A7);
        setValue(tMsg.svcPrcHolStartValTxt, acMsg.xxStartDplyTmTxt_A8);
        setValue(tMsg.svcPrcHolEndValTxt, acMsg.xxEndDplyTmTxt_A8);
    }

}

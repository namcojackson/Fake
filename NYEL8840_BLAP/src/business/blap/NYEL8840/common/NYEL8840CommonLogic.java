/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8840.common;

import static business.blap.NYEL8840.constant.NYEL8840Constant.WF_BIZ_APP_ID_ALL;
import static business.blap.NYEL8840.constant.NYEL8840Constant.HHMM_SEPARATOR;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZSM4199E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0003W;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0010E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0016E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0017E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0021I;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDMsg;

import business.blap.NYEL8840.NYEL8840CMsg;
import business.blap.NYEL8840.NYEL8840Query;
import business.blap.NYEL8840.NYEL8840SMsg;
import business.blap.NYEL8840.NYEL8840_ACMsg;
import business.blap.NYEL8840.NYEL8840_ACMsgArray;
import business.blap.NYEL8840.NYEL8840_ASMsg;
import business.blap.NYEL8840.NYEL8840_ASMsgArray;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NYEL8840CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 * 2016/09/09   Fujitsu         M.Ugaki         Update          N/A
 * 2022/12/26   Fujitsu         Mz.Takahashi    Update          QC#60743
 *</pre>
 */
public class NYEL8840CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param bizMsg NYEL8840CMsg
     * @param bizMsgAry NYEL8840_ACMsgArray
     * @param glblMsgAry NYEL8840_ASMsgArray
     */
    public static void loadOnePageToCMsg(NYEL8840CMsg bizMsg, NYEL8840_ACMsgArray bizMsgAry, NYEL8840_ASMsgArray glblMsgAry) {
        // TODO 自動生成されたメソッドスタブ

    }

    /**
     * getAssigneeTp
     * @param asgCd String
     * @param glblCmpyCd String
     * @return this is stub method, return only "User".
     */
    public static String getAssigneeTp(String asgCd, String glblCmpyCd) {
        // TODO 自動生成されたメソッドスタブ
        return "User";
    }

    /**
     * initPullDown
     * @param bizMsg
     */
    public static void initPullDown(NYEL8840CMsg bizMsg){
        bizMsg.wfBizAppId_L.clear();
        bizMsg.wfDescTxt_L.clear();

// MOD Start 2018/09/26
//        if (!ZYPCommonFunc.hasValue(bizMsg.wfBizAppId)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId, WF_BIZ_APP_ID_ALL);
//        }
// MOD End 2018/09/26

        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId_L.no(0), WF_BIZ_APP_ID_ALL);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_L.no(0), WF_BIZ_APP_ID_ALL);
        
        S21SsmEZDResult result =  NYEL8840Query.getInstance().getProcDfnList(bizMsg);
        
        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();
            int max = resultList.size();
            
            if (bizMsg.wfBizAppId_L.length() - 1 < max){
                max = bizMsg.wfBizAppId_L.length() - 1;
            }

            for (int cnt = 0; cnt < max; cnt++){
                Map map = (Map) resultList.get(cnt);

                ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId_L.no(cnt + 1), (String) map.get("WF_BIZ_APP_ID"));
                ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_L.no(cnt + 1), (String) map.get("WF_DESC_TXT"));
            }
        }

        List<String> hh = Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        List<String> mm = Arrays.asList("00","05","10","15","20","25","30","35","40","45","50","55");

        int cnt = 0;

        for (String h: hh){
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromHourMn_FH.no(cnt), h);
            ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_FH.no(cnt), h);
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruHourMn_TH.no(cnt), h);
            ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_TH.no(cnt), h);
            cnt++;
        }

        cnt = 0;

        for (String m: mm){
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromHourMn_FM.no(cnt), m);
            ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_FM.no(cnt), m);
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruHourMn_TM.no(cnt), m);
            ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_TM.no(cnt), m);
            cnt++;
        }
    }

    /**
     * getAssignerNm
     * @param bizMsg NYEL8840CMsg
     * @return String for assigner name for delegation
     */
    public static String getAssignerNm(NYEL8840CMsg bizMsg, String myUser) {

        boolean isAdmin = NYXC880002.isAdministrator(myUser);

        S21SsmEZDResult result = NYXC880001.getAssigners(bizMsg.wfBizAppId.getValue(),
            myUser, isAdmin, bizMsg.xxWfAsgCd_F.getValue(), null, null, 1);

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();
            Map map = (Map) resultList.get(0);
            String lastNm = (String) map.get("LAST_NM");
            String firstNm = (String) map.get("FIRST_NM");
            return firstNm + " " + lastNm;
        } else {
            bizMsg.xxWfAsgCd_F.setErrorInfo(1, NYEM0016E, new String[] {"Assigner" });
            return "";
        }
    }

    /**
     * getAssigneeNmFromS21Psn
     * @param bizMsg NYEL8840CMsg
     * @return String for Assignee Name
     */
    public static String getAssigneeNm(NYEL8840CMsg bizMsg) {

        String assigneeNm = NYXC880001.getAssigneeNmFromS21Psn(bizMsg.xxWfAsgCd.getValue());

        if ((assigneeNm == null) || (assigneeNm.length() <= 0)) {
            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0016E, new String[] {"Assignee" });
            return "";
        }

        return assigneeNm;
    }

    /**
     * isValidPeriod
     * @param bizMsg NYEL8840CMsg
     * @param glblMsg NYEL8840SMsg
     * @return f delegate period is valid, return true.
     */
    public static boolean isValidPeriod(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        String fromHHMM = bizMsg.effFromHourMn_H.getValue() + bizMsg.effFromHourMn_M.getValue();
        String toHHMM = bizMsg.effThruHourMn_H.getValue() + bizMsg.effThruHourMn_M.getValue();
        String fromDTHHMM = bizMsg.effFromDt.getValue() + fromHHMM;
        String toDTHHMM = bizMsg.effThruDt.getValue() + toHHMM;
        String curDTHHMM = S21SystemDate.getFWCurrentSystemTime("yyyyMMddHHmm");

        if (curDTHHMM.compareTo(fromDTHHMM) > 0){
            bizMsg.effFromDt.setErrorInfo(1, ZZSM4199E, new String[] {"Period From is Past day", "", "", "" });
            return false;
        }

        if (curDTHHMM.compareTo(toDTHHMM) > 0){
            bizMsg.effThruDt.setErrorInfo(1, ZZSM4199E, new String[] {"Period To is Past day", "", "", "" });
            return false;
        }

        if (fromDTHHMM.compareTo(toDTHHMM) >= 0){
            bizMsg.effFromDt.setErrorInfo(1, ZZSM4199E, new String[] {"Period From is greater than Period To", "", "", "" });
            return false;
        }

        if (isOverlapPeriod(//
                bizMsg.xxWfAsgCd_F.getValue()
                ,bizMsg.xxWfAsgCd.getValue()
                , bizMsg.wfBizAppId.getValue()
                , bizMsg.effFromDt //
                , fromHHMM
                , bizMsg.effThruDt //
                , toHHMM
                , glblMsg.A
                , true)) {
            return false;
        }
        if (isOverlapPeriod(//
                bizMsg.xxWfAsgCd_F.getValue()
                ,bizMsg.xxWfAsgCd.getValue()
                , bizMsg.wfBizAppId.getValue()
                , bizMsg.effFromDt //
                , fromHHMM
                , bizMsg.effThruDt //
                , toHHMM
                , bizMsg
                , true)) {
            return false;
        }

        return true;
    }

    /**
     * 
     * @param cur
     * @param to
     * @return
     */
    public static boolean isDisplay(Date cur, Date to) {

        return (to.compareTo(cur) >= 0);

    }

// MOD Start 2018/09/26
    private static boolean isOverlapPeriod(//
            String assignFromCd
            , String assigneeCd
            , String processName
            , EZDCDateItem fromDt
            , String fromHHMM
            , EZDCDateItem toDt
            , String toHHMM
            , NYEL8840_ASMsgArray glblMsgAry
            , boolean setErrFlg) {
        for (int i = 0; i < glblMsgAry.getValidCount(); i++) {
            NYEL8840_ASMsg glblLineMsg = glblMsgAry.no(i);
            if (assigneeCd.equals(glblLineMsg.xxWfAsgCd_A1.getValue())
                    && (assignFromCd.equals(glblLineMsg.xxWfAsgCd_A2.getValue()))
                    && (processName.equals(glblLineMsg.wfBizAppId_A1.getValue())
                    || WF_BIZ_APP_ID_ALL.equals(glblLineMsg.wfBizAppId_A1.getValue())
                    || WF_BIZ_APP_ID_ALL.equals(processName))) {
                if (isOverlap(//
                        fromDt //
                        , fromHHMM
                        , toDt //
                        , toHHMM
                        , glblLineMsg.effFromDt_A1.getValue() //
                        , glblLineMsg.effFromHourMn_AH.getValue() + glblLineMsg.effFromHourMn_AM.getValue()
                        , glblLineMsg.effThruDt_A1.getValue()
                        , glblLineMsg.effThruHourMn_AH.getValue() + glblLineMsg.effThruHourMn_AM.getValue()
                        , setErrFlg)) {
                    return true;
                }
            }
        }
        return false;
    }
// MOD End 2018/09/26

// ADD Start 2018/09/26
    private static boolean isOverlapPeriod(//
            String assignFromCd
            , String assigneeCd
            , String processName
            , EZDCDateItem fromDt
            , String fromHHMM
            , EZDCDateItem toDt 
            , String toHHMM
            , NYEL8840CMsg bizMsg
            , boolean setErrFlg) {
        S21SsmEZDResult result = NYEL8840Query.getInstance().checkPeriodOverlap(bizMsg);

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();

            int max = resultList.size() ;

            if (max > 0) {
                Map map = (Map) resultList.get(0);
                isOverlap(
                        fromDt
                        , fromHHMM
                        , toDt
                        , toHHMM
                        , (String) map.get("EFF_FROM_DT")
                        , (String) map.get("EFF_FROM_HOUR_MN")
                        , (String) map.get("EFF_THRU_DT")
                        , (String) map.get("EFF_THRU_HOUR_MN")
                        , setErrFlg);
                return true;
            }
        }
        return false;
    }
// ADD End 2018/09/26

    private static boolean isOverlap(EZDCDateItem fromDt, String fromHHMM, EZDCDateItem toDt, String toHHMM, String dlgtPerFromDtA, String dlgtPerFromHHMM, String dlgtPerToDtA, String dlgtPerToHHMM, boolean setErrFlg) {

        String fromDTHHMM = fromDt.getValue() + fromHHMM;
        String toDTHHMM = toDt.getValue() + toHHMM;
        String dlgtPerFrom = dlgtPerFromDtA + dlgtPerFromHHMM;
        String dlgtPerTo = dlgtPerToDtA + dlgtPerToHHMM;

        if ((toDTHHMM).compareTo(dlgtPerFrom) < 0 //
                || (dlgtPerTo).compareTo(fromDTHHMM) < 0) {
            return false;
        }

        if ((dlgtPerFrom).compareTo(fromDTHHMM) <= 0) {
            if (setErrFlg){
                fromDt.setErrorInfo(1, NYEM0017E);
            }
            return true;
        }
        if ((toDTHHMM).compareTo(dlgtPerTo) <= 0) {
            if (setErrFlg){
                toDt.setErrorInfo(1, NYEM0017E);
            }
            return true;
        }

        if (setErrFlg){
            fromDt.setErrorInfo(1, NYEM0017E);
            toDt.setErrorInfo(1, NYEM0017E);
        }
        return true;

    }

    /**
     * search
     * @param bizMsg NYEL8840CMsg
     * @param glblMsg NYEL8840SMsg
     */
    public static void search(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        String myUser = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();

        if (!ZYPCommonFunc.hasValue(bizMsg.xxWfAsgCd_F)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgCd_F, myUser);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgNm_F, NYEL8840CommonLogic.getAssignerNm(bizMsg, myUser));

        bizMsg.wfBizAppId_S.clear();
        bizMsg.xxWfAsgCd_FS.clear();

        S21SsmEZDResult result = NYEL8840Query.getInstance().getDelegateUser(bizMsg, glblMsg);

        if (result.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId_S, bizMsg.wfBizAppId.getValue());
            bizMsg.setMessageInfo(NYEM0021I);
            return;
        }
        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAppId_S, bizMsg.wfBizAppId.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgCd_FS, bizMsg.xxWfAsgCd_F.getValue());

            List resultList = (List) result.getResultObject();

            int max = resultList.size() ;

            // Add For Redmine#2106 Bug fix
            if (max > glblMsg.A.length()) {
//                bizMsg.setMessageInfo("NZZM0001W");
                bizMsg.setMessageInfo(NYEM0003W);
                 max = glblMsg.A.length();
            }

            SimpleDateFormat formatA = new SimpleDateFormat("yyyyMMddHHmm");
            Date curDt = null;

            try {
                
                curDt = formatA.parse(S21SystemDate.getFWCurrentSystemTime("yyyyMMddHHmm"));
            } catch (ParseException e) {
                e.printStackTrace();
                throw new S21AbendException("Cannot parse system time.");
            }

            int dispCnt = 0;
            for (int cnt = 0; cnt < max; cnt++) {
                Map map = (Map) resultList.get(cnt);
                NYEL8840_ASMsg msg = glblMsg.A.no(dispCnt);

                SimpleDateFormat formatT = new SimpleDateFormat("yyyyMMddHHmm");

                try {
                    if (NYEL8840CommonLogic.isDisplay(curDt, formatT.parse((String) map.get("EFF_THRU_DT") + (String) map.get("EFF_THRU_HOUR_MN"))) == false) {
                        continue;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new S21AbendException("Cannot parse time.");
                }

                // Assigner Cd
                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgCd_A2, bizMsg.xxWfAsgCd_F.getValue());

                // Assigner Name
                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgNm_A2, NYEL8840CommonLogic.getAssignerNm(bizMsg, myUser));

                // Assignee Cd
                String asgCd = (String) map.get("WF_USR_ID");
                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgCd_A1, asgCd);

                // Assignee Name
                ZYPEZDItemValueSetter.setValue(msg.xxWfAsgNm_A1, NYXC880001.getAssigneeNmFromS21Psn(asgCd));

                // Process Name
                ZYPEZDItemValueSetter.setValue(msg.wfBizAppId_A1, (String) map.get("WF_BIZ_APP_ID"));
                ZYPEZDItemValueSetter.setValue(msg.wfDescTxt_AP, (String) map.get("PROCNM"));

                // From
                ZYPEZDItemValueSetter.setValue(msg.effFromDt_A1, (String) map.get("EFF_FROM_DT"));

                // From HH:MM
                String HHMM = (String) map.get("EFF_FROM_HOUR_MN");
                String HH = "";
                String MM = "";
                if (S21StringUtil.isNotEmpty(HHMM)){
                    HH = HHMM.substring(0, 2);
                    MM = HHMM.substring(2, 4);

                    HHMM = HH + HHMM_SEPARATOR + MM;
                }

                ZYPEZDItemValueSetter.setValue(msg.effFromHourMn_AH, HH);
                ZYPEZDItemValueSetter.setValue(msg.effFromHourMn_AM, MM);
                ZYPEZDItemValueSetter.setValue(msg.wfDescTxt_F1, 
                        ZYPDateUtil.DateFormatter(msg.effFromDt_A1.getValue(), "yyyyMMdd", "MM/dd/yyyy")
                        + " "  
                        + HHMM);

                // To
                ZYPEZDItemValueSetter.setValue(msg.effThruDt_A1, (String) map.get("EFF_THRU_DT"));

                // To HH:MM
                HHMM = (String) map.get("EFF_THRU_HOUR_MN");

                if (S21StringUtil.isNotEmpty(HHMM)){
                    HH = HHMM.substring(0, 2);
                    MM = HHMM.substring(2, 4);

                    HHMM = HH + HHMM_SEPARATOR + MM;
                }

                ZYPEZDItemValueSetter.setValue(msg.effThruHourMn_AH, HH);
                ZYPEZDItemValueSetter.setValue(msg.effThruHourMn_AM, MM);
                ZYPEZDItemValueSetter.setValue(msg.wfDescTxt_T1, 
                        ZYPDateUtil.DateFormatter(msg.effThruDt_A1.getValue(), "yyyyMMdd", "MM/dd/yyyy")
                        + " " 
                        + HHMM);

                // Comment
                ZYPEZDItemValueSetter.setValue(msg.wfDescTxt_A1, (String) map.get("WF_DESC_TXT"));

                // EZUPTIME (WF_DLGT_USR)
                ZYPEZDItemValueSetter.setValue(msg.ezUpTime_A1, (String) map.get("EZUPTIME"));

                // WF Delegate User PK
                ZYPEZDItemValueSetter.setValue(msg.wfDlgtUsrPk_A1, (BigDecimal) map.get("WF_DLGT_USR_PK"));

                dispCnt++;
            }

//            glblMsg.A.setValidCount(max);
            glblMsg.A.setValidCount(dispCnt);
            bizMsg.xxPageShowFromNum.clear();
        }

    }

    /**
     * Copy Search Data CMsg to SMsg
     * @param bizMsg NYEL8840CMsg
     * @param glblMsg NYEL8840SMsg
     */
    public static void copyCMsgToSMsg(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        int curCnt = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int bizMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + bizMsg.A.getValidCount()); i++) {

            EZDMsg.copy(bizMsg.A.no(bizMsgCnt), null, glblMsg.A.no(i), null);
            bizMsgCnt++;
        }
    }

    /**
     * copyFromSMsgOntoCmsg
     * @param bizMsg NYEL8840CMsg
     * @param glblMsg NYEL8840SMsg
     */
    public static void copySMsgToCMsg(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(bizMsg, (pagenationFrom + 1), (pagenationFrom + bizMsg.A.getValidCount()), glblMsg.A.getValidCount());
    }

    /**
     * 
     * setPageNum
     * 
     * @param cMsg NYEL8840CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NYEL8840CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * Delete SMsg
     * @param bizMsg NYEL8840CMsg
     * @param glblMsg NYEL8840SMsg
     */
    public static void deleteRowSMsg(NYEL8840CMsg bizMsg, NYEL8840SMsg glblMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            bizMsg.setMessageInfo(NYEM0010E);
            return;
        }

        int delCnt;
        List<Integer> delRows = new ArrayList<Integer>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(glblMsg.A.no(i).ezUpTime_A1)) {
                // New line
                delRows.add(i);
                continue;
            }

            delCnt = glblMsg.D.getValidCount();

            // Assigner Cd
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).xxWfAsgCd_D2, glblMsg.A.no(i).xxWfAsgCd_A2.getValue());

            // Assigner Name
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).xxWfAsgNm_D2, glblMsg.A.no(i).xxWfAsgNm_A2.getValue());

            // Assignee Cd
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).xxWfAsgCd_D1, glblMsg.A.no(i).xxWfAsgCd_A1.getValue());

            // Assignee Name
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).xxWfAsgNm_D1, glblMsg.A.no(i).xxWfAsgNm_A1.getValue());

            // Process Name
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).wfDescTxt_DP, glblMsg.A.no(i).wfDescTxt_AP.getValue());

            // Business Application ID
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).wfBizAppId_D1, glblMsg.A.no(i).wfBizAppId_A1.getValue());

            // From
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).effFromDt_D1, glblMsg.A.no(i).effFromDt_A1.getValue());

            // From (Hour)
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).effFromHourMn_DH, glblMsg.A.no(i).effFromHourMn_AH.getValue());

            // From (Minute)
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).effFromHourMn_DM, glblMsg.A.no(i).effFromHourMn_AM.getValue());

            // To
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).effThruDt_D1, glblMsg.A.no(i).effThruDt_A1.getValue());

            // To (Hour)
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).effThruHourMn_DH, glblMsg.A.no(i).effThruHourMn_AH.getValue());

            // To (Minute)
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).effThruHourMn_DM, glblMsg.A.no(i).effThruHourMn_AM.getValue());

            // Comment
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).wfDescTxt_D1, glblMsg.A.no(i).wfDescTxt_A1.getValue());

            // WF Delegate User PK
            ZYPEZDItemValueSetter.setValue(glblMsg.D.no(delCnt).wfDlgtUsrPk_D1, glblMsg.A.no(i).wfDlgtUsrPk_A1.getValue());

            glblMsg.D.setValidCount(delCnt + 1);
            delRows.add(i);
        }

        if (delRows.size() > 0) {
            // delete line from SMsg
            ZYPTableUtil.deleteRows(glblMsg.A, delRows);
        }
        if ((glblMsg.A.getValidCount() == 0)
                && (glblMsg.D.getValidCount() == 0)) {
            bizMsg.xxWfAsgCd_FS.clear();
        }
    }

    // Redmine#2106 Bug fix
    /**
     * @param bizMsg
     * @return
     */
    public static void adjustMsgs(NYEL8840CMsg bizMsg) {
        int dataSize = bizMsg.A.getValidCount();
        if (dataSize == 0) {
            return;
        }

        // Remove another assigners data
        NYEL8840CMsg newBizMsg = new NYEL8840CMsg();
        String asgFromCd = bizMsg.xxWfAsgCd_F.getValue();

        int j = 0;
        for (int i=0; i<bizMsg.A.getValidCount(); i++) {
            if (bizMsg.A.no(i).xxWfAsgCd_A2.getValue().equals(asgFromCd)) {
                EZDMsg.copy(bizMsg.A.no(i), null, newBizMsg.A.no(j), null);
                j++;
            }
        }
        newBizMsg.A.setValidCount(j);
        bizMsg.A.clear();
        EZDMsg.copy(newBizMsg.A, null, bizMsg.A, null);
    }
// ADD End 2018/09/26
}

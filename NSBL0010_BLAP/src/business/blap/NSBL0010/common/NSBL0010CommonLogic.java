/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0010.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0010.NSBL0010CMsg;
import business.blap.NSBL0010.NSBL0010Query;
import business.blap.NSBL0010.NSBL0010SMsg;
import business.blap.NSBL0010.NSBL0010_ASMsg;
import business.blap.NSBL0010.constant.NSBL0010Constant;
import business.db.FSRTMsg;
import business.db.FSR_VISITTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSXC001001PMsg;
import business.parts.NSZC003001PMsg;
import business.parts.NSZC003001_xxSvcTaskListPMsg;
import business.parts.NSZC012001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC003001.NSZC003001;
import com.canon.cusa.s21.api.NSZ.NSZC003001.constant.NSZC003001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC012001.NSZC012001;
import com.canon.cusa.s21.api.NSZ.NSZC012001.constant.NSZC012001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001Contr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_CPLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2014/03/07   Fujitsu         S.Takami        Update          S21_NA QC#0046
 * 2014/03/18   Fujitsu         C.Naito         Update          S21_NA QC#0134
 * 2014/04/11   Fujitsu         Y.Kamide        Update          S21_NA QC#42
 * 2014/06/02   Hitachi         T.Aoyagi        Update          CSA-166
 * 2015/11/18   Hitachi         T.Harada        Update          CSA
 * 2016/11/29   Hitachi         N.Arai          Update          QC#13901
 * 2017/01/04   Hitachi         N.Arai          Update          QC#16449
 * 2017/01/17   Hitachi         N.Arai          Update          QC#16331
 * 2019/01/29   Hitachi         K.Kitachi       Update          QC#30122
 *</pre>
 */
public class NSBL0010CommonLogic implements NSBL0010Constant {

    /**
     * createPulldownList
     * @param bizMsg NSBL0010CMsg
     */
    public static void createPulldownList(NSBL0010CMsg bizMsg) {
        // DS Service Call Type
        ZYPCodeDataUtil.createPulldownList(TBL_DS_SVC_CALL_TP, bizMsg.dsSvcCallTpCd_H1, bizMsg.xxStCdListTxt_H2, COLON);

// START 2016/11/29 N.Arai [QC#13901, MOD]
        // Service Bill Type
        createSvcBillTpList(bizMsg);
// END 2016/11/29 N.Arai [QC#13901, MOD]
        // Svc Line Biz Cd
        createSvcLineBizList(bizMsg);
        // DS Service Task Status
        createSvcTaskStsList(bizMsg);
    }

// START 2016/11/29 N.Arai [QC#13901, MOD]
    /**
     * createsvcLineBizList
     * @param bizMsg NSBL0010CMsg
     */
    public static void createSvcBillTpList(NSBL0010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NSBL0010Query.getInstance().getSvcBillTpList();
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.svcBillTpCd_H1.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            bizMsg.svcBillTpCd_H1.no(i).setValue((String) resultMap.get(SVC_BILL_TP_CD.toString()));
            // START 2019/01/29 K.Kitachi [QC#30122, MOD]
//            bizMsg.xxTblItemTxt_H2.no(i).setValue((String) resultMap.get(SVC_BILL_TP_DESC_TXT.toString()));
            bizMsg.svcBillTpDescTxt_H2.no(i).setValue((String) resultMap.get(SVC_BILL_TP_DESC_TXT.toString()));
            // END 2019/01/29 K.Kitachi [QC#30122, MOD]
        }
    }
// END 2016/11/29 N.Arai [QC#13901, MOD]

    /**
     * createSvcLineBizList
     * @param bizMsg NSBL0010CMsg
     */
    public static void createSvcLineBizList(NSBL0010CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NSBL0010Query.getInstance().getSvcLineBizList(bizMsg.glblCmpyCd.getValue());
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.lineBizTpCd_L.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            bizMsg.lineBizTpCd_L.no(i).setValue((String) resultMap.get(LINE_BIZ_TP_CD.toString()));
            bizMsg.lineBizTpDescTxt_L.no(i).setValue((String) resultMap.get(LINE_BIZ_TP_DESC_TXT.toString()));
        }
    }

    /**
     * createSvcTaskStsList
     * @param bizMsg NSBL0010CMsg
     */
    public static void createSvcTaskStsList(NSBL0010CMsg bizMsg) {

        S21SsmEZDResult svcTaskStsListRs = NSBL0010Query.getInstance().getSvcTaskStsList();

        if (svcTaskStsListRs.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSBM0002E);
            return;
        }
        List<Map<String, String>> svcTaskStsList = (List<Map<String, String>>) svcTaskStsListRs.getResultObject();

        int cnt = 0;
        for (Map<String, String> map : svcTaskStsList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.svcTaskStsCd_H1.no(cnt), map.get(SVC_TASK_STS_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcTaskStsNm_H2.no(cnt), map.get(SVC_TASK_STS_NM));
            cnt++;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.svcTaskStsCd_H1.no(cnt), SVC_TASK_STS_CD_WAITING_FOR_SCHEDULING);
        ZYPEZDItemValueSetter.setValue(bizMsg.svcTaskStsNm_H2.no(cnt), SVC_TASK_STS_NM_WAITING_FOR_SCHEDULING);
        cnt++;

        ZYPEZDItemValueSetter.setValue(bizMsg.svcTaskStsCd_H1.no(cnt), SVC_TASK_STS_CD_ACTIVE_TASK_FSR);
        ZYPEZDItemValueSetter.setValue(bizMsg.svcTaskStsNm_H2.no(cnt), SVC_TASK_STS_NM_ACTIVE_TASK_FSR);
        cnt++;
    }

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    public static void copyBizToShare(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {

        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (pageFrom + j < shareMsg.A.length()) {
                EZDMsg.copy(bizMsg.A.no(j), null, shareMsg.A.no(pageFrom + j), null);
            } else {
                break;
            }
        }
    }

    /**
     * copy shareMsg to bizMsg
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    public static void copyShareToBiz(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (pageFrom + j < shareMsg.A.length()) {
                EZDMsg.copy(shareMsg.A.no(pageFrom + j), null, bizMsg.A.no(j), null);
            } else {
                break;
            }
        }
    }

    /**
     * inputCheck
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     * @param mode String
     * @return boolean
     */
    public static boolean inputCheck(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String mode) {
        NSBL0010_ASMsg dtlMsg = null;
        String svcTaskStsCd = null;
        int cnt = 0;
        int curr = 0;
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int size = shareMsg.A.getValidCount();
        boolean retFlg = true;

        for (int i = 0; i < size; i++) {
            dtlMsg = shareMsg.A.no(i);
            if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                continue;
            }
            cnt++;
            curr = i - pageFrom;

            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {
                if (!ZYPCommonFunc.hasValue(dtlMsg.techCd_A)) {
                    bizMsg.setMessageInfo(ZZM9000E, new String[] {TECH_CD });
                    if (curr >= 0 && curr < bizMsg.A.length()) {
                        bizMsg.A.no(curr).techCd_A.setErrorInfo(1, ZZM9000E, new String[] {TECH_CD });
                    }
                    retFlg = false;
                }
                if (ZYPCommonFunc.hasValue(dtlMsg.xxDtTm_A1)) {
                    if (!ZYPCommonFunc.hasValue(dtlMsg.techSchdFromDt_A)) {
                        bizMsg.setMessageInfo(ZZM9000E, new String[] {TECH_SCHD_FROM_DT });
                        if (curr >= 0 && curr < bizMsg.A.length()) {
                            bizMsg.A.no(curr).techSchdFromDt_A.setErrorInfo(1, ZZM9000E, new String[] {TECH_SCHD_FROM_DT });
                        }
                        retFlg = false;
                    }
                }
                if (ZYPCommonFunc.hasValue(dtlMsg.xxDtTm_A2)) {
                    if (!ZYPCommonFunc.hasValue(dtlMsg.techSchdToDt_A)) {
                        bizMsg.setMessageInfo(ZZM9000E, new String[] {TECH_SCHD_TO_DT });
                        if (curr >= 0 && curr < bizMsg.A.length()) {
                            bizMsg.A.no(curr).techSchdToDt_A.setErrorInfo(1, ZZM9000E, new String[] {TECH_SCHD_TO_DT });
                        }
                        retFlg = false;
                    }
                }
                if (!ZYPCommonFunc.hasValue(dtlMsg.svcCallPrtyCd_A)) {
                    bizMsg.setMessageInfo(ZZM9000E, new String[] {SVC_CALL_PRTY_CD });
                    if (curr >= 0 && curr < bizMsg.A.length()) {
                        bizMsg.A.no(curr).svcCallPrtyCd_A.setErrorInfo(1, ZZM9000E, new String[] {SVC_CALL_PRTY_CD });
                    }
                    retFlg = false;
                }
            }

            // check Task/FSR Status
            svcTaskStsCd = dtlMsg.svcTaskStsCd_A.getValue();
            if (MODE_SCHEDULE.equals(mode)) {
// START 2016/11/29 N.Arai [QC#13901, MOD]
//                if (!SVC_TASK_STS.APPROVED.equals(svcTaskStsCd) && !SVC_TASK_STS.CONTINUOUS_OPEN.equals(svcTaskStsCd) && !SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
//                    String[] errMsg = new String[] {"Schedule", "Approved, Continuous Completed" };
                if (!SVC_TASK_STS.OPEN.equals(svcTaskStsCd) && !SVC_TASK_STS.TBO.equals(svcTaskStsCd)) {
                    String[] errMsg = new String[] {"Schedule", "TBO, Open" };
// END 2016/11/29 N.Arai [QC#13901, MOD]
                    bizMsg.setMessageInfo(NSBM0010E, errMsg);
                    if (curr >= 0 && curr < bizMsg.A.length()) {
                        bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0010E, errMsg);
                    }
                    retFlg = false;
                }
            } else if (MODE_DISPATCH.equals(mode)) {
                // START 2015/11/18 T.Harada [CSA,CHANGE]
                //if (!SVC_TASK_STS.APPROVED.equals(svcTaskStsCd) && !SVC_TASK_STS.CONTINUOUS_OPEN.equals(svcTaskStsCd) && !SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
                //    String[] errMsg = new String[] {"Schedule", "Approved, Continuous Completed, Scheduled" };
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
//                if (!SVC_TASK_STS.TBO.equals(svcTaskStsCd) && !SVC_TASK_STS.OPEN.equals(svcTaskStsCd)) {
//                    String[] errMsg = new String[] {"Dispatch", "TBO, Open" };
                if (!SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd) && !SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
                    String[] errMsg = new String[] {"InRoute", "Scheduled, Assigned" };
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
                // END 2015/11/18 T.Harada [CSA,CHANGE]
                    bizMsg.setMessageInfo(NSBM0010E, errMsg);
                    if (curr >= 0 && curr < bizMsg.A.length()) {
                        bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0010E, errMsg);
                    }
                    retFlg = false;
                }
            } else if (MODE_CANCEL.equals(mode)) {
                // START 2015/11/18 T.Harada [CSA,CHANGE]
                //if (!SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd) && !SVC_TASK_STS.DISPATCHED.equals(svcTaskStsCd)) {
                //    String[] errMsg = new String[] {"Schedule", "Scheduled, Dispatched" };
                if (!SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd) && !SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
                    String[] errMsg = new String[] {"Un Disptach", "Scheduled, Assigned" };
                // END 2015/11/18 T.Harada [CSA,CHANGE]
                    bizMsg.setMessageInfo(NSBM0010E, errMsg);
                    if (curr >= 0 && curr < bizMsg.A.length()) {
                        bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0010E, errMsg);
                    }
                    retFlg = false;
                }
            }
        }

        // check box count
        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode) || MODE_CANCEL.equals(mode)) {
            if (cnt == 0) {
                bizMsg.setMessageInfo(NSBM0007E);
                retFlg = false;
            }
        }
        return retFlg;
    }

    /**
     * masterCheck
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     * @param mode String
     * @return boolean
     */
    public static boolean masterCheck(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String mode) {
        // Tech Code check
        NSBL0010_ASMsg dtlMsg = null;
        Map<String, Boolean> techMap = new HashMap<String, Boolean>();
        String techCd = null;
        int size = shareMsg.A.getValidCount();
        boolean isError = false;
        boolean retFlg = true;

        // Technician Master check
        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {
            for (int i = 0; i < size; i++) {
                dtlMsg = shareMsg.A.no(i);
                if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                    continue;
                }

                isError = false;
                techCd = shareMsg.A.no(i).techCd_A.getValue();

                if (techMap.containsKey(techCd)) {
                    if (!techMap.get(techCd)) {
                        isError = true;
                    }
                } else {
                    S21SsmEZDResult rsTechCd = NSBL0010Query.getInstance().checkTechMstr(techCd);

                    if (rsTechCd.isCodeNotFound()) {
                        isError = true;
                        techMap.put(techCd, false);
                    } else {
                        techMap.put(techCd, true);
                    }
                }

                if (isError) {
                    int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
                    int curr = i - pageFrom;
                    bizMsg.setMessageInfo(NSBM0011E, new String[] {TECH_CD });
                    if (curr >= 0 && curr < bizMsg.A.length()) {
                        bizMsg.A.no(curr).techCd_A.setErrorInfo(1, NSBM0011E, new String[] {TECH_CD });
                    }
                    retFlg = false;
                }
            }
        }

        // Technician Training History check (for warning)
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxErrFlg.getValue())) {
            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {
                String svcTaskNum = null;
                for (int i = 0; i < size; i++) {
                    dtlMsg = shareMsg.A.no(i);
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                        continue;
                    }

                    techCd = shareMsg.A.no(i).techCd_A.getValue();
                    svcTaskNum = shareMsg.A.no(i).svcTaskNum_A.getValue();

                    S21SsmEZDResult rsTechTngHist = NSBL0010Query.getInstance().checkTechTngHist(techCd, svcTaskNum);

                    if (rsTechTngHist.isCodeNotFound() || ZYPConstant.FLG_OFF_N.equals((String) rsTechTngHist.getResultObject())) {
                        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
                        int curr = i - pageFrom;
                        bizMsg.xxErrFlg.setValue(ZYPConstant.FLG_ON_Y);
                        if (curr >= 0 && curr < bizMsg.A.length()) {
                            bizMsg.A.no(curr).techCd_A.setErrorInfo(2, NSBM0013W, new String[] {techCd, svcTaskNum });
                        }
                        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                            bizMsg.setMessageInfo(NSBM0013W, new String[] {techCd, svcTaskNum });
                        }
                        retFlg = false;
                    }
                }
            }
        }

        // START 2015/11/18 T.Harada [CSA,ADD]
        // Non Preferred Tech check (for warning)
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxErrFlg.getValue())) {
            if (MODE_DISPATCH.equals(mode)) {
                //String svcTaskNum = null;
                for (int i = 0; i < size; i++) {
                    dtlMsg = shareMsg.A.no(i);
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                        continue;
                    }

                    techCd = shareMsg.A.no(i).techCd_A.getValue();
                    BigDecimal svcMachMstrPk = shareMsg.A.no(i).svcMachMstrPk_A.getValue();

                    S21SsmEZDResult rsSvcNonPrfTech = NSBL0010Query.getInstance().checkSvcNonPrfTech(techCd, svcMachMstrPk);

                    if (rsSvcNonPrfTech.getQueryResultCount() > 0) {
                        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
                        int curr = i - pageFrom;
                        bizMsg.xxErrFlg.setValue(ZYPConstant.FLG_ON_Y);
                        if (curr >= 0 && curr < bizMsg.A.length()) {
                            bizMsg.A.no(curr).techCd_A.setErrorInfo(2, NSBM0129W);
                        }
                        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                            bizMsg.setMessageInfo(NSBM0129W);
                        }
                        retFlg = false;
                    }
                }
            }
        }
        // END 2015/11/18 T.Harada [CSA,ADD]
        return retFlg;
    }

    // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
    /**
     * receivedPartsCheck
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     * @param mode String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean scheduleCheck(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String mode) {

        NSBL0010_ASMsg dtlMsg = null;
        boolean retFlg = true;

        if (!MODE_SCHEDULE.equals(mode)) {
            return retFlg;
        }

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxErrFlg_PT.getValue())) {

            for (int i = 0; i < shareMsg.A.getValidCount(); i++) {
                dtlMsg = shareMsg.A.no(i);

                if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                    continue;
                }

                S21SsmEZDResult rt = NSBL0010Query.getInstance().getFsrVisitByContinuousCall(dtlMsg);
                if (rt.isCodeNotFound()) {
                    // not found.
                    continue;
                }

                List<Map<String, String>> fsrVisitList = (List<Map<String, String>>) rt.getResultObject();
                String svcCallCpltTpCd = fsrVisitList.get(0).get(SVC_CALL_CPLT_TP_CD);
                // Check shipped parts.
                if (!checkShippedParts(bizMsg, dtlMsg, i, svcCallCpltTpCd)) {
                    retFlg = false;
                    continue;
                }

            }
        }
        return retFlg;
    }

    private static boolean checkShippedParts(NSBL0010CMsg bizMsg, NSBL0010_ASMsg aSMsg, int row, String svcCallCpltTpCd) {

        boolean isWarning = false;

        if (SVC_CALL_CPLT_TP.WAITING_PARTS.equals(svcCallCpltTpCd)) {
            isWarning = true;
        }

        if (isWarning) {
            int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            int curr = row - pageFrom;
            if (curr >= 0 && curr < bizMsg.A.length()) {
                bizMsg.A.no(curr).techSchdFromDt_A.setErrorInfo(2, NSBM0127W);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo(NSBM0127W);
            }
            bizMsg.xxErrFlg_PT.setValue(ZYPConstant.FLG_ON_Y);
            return false;
        }
        return true;
    }
    // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

    /**
     * setSvcTaskValue
     * @param shareMsg NSBL0010SMsg
     */
    public static void setSvcTaskValue(NSBL0010SMsg shareMsg) {
        NSBL0010_ASMsg dtlMsg = null;
        long rspAot = 0;
        long rspTm = 0;
        Long rcvDtL = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SYS_DT_TM);
        String currDtTm = sdf.format(Calendar.getInstance().getTime().getTime());
        StringBuilder sbDt = new StringBuilder();
        int size = shareMsg.A.getValidCount();

        for (int i = 0; i < size; i++) {
            dtlMsg = shareMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(dtlMsg.techSchdFromTm_A)) {
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_A1, replaceSchdTm(dtlMsg.techSchdFromTm_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(dtlMsg.techSchdToTm_A)) {
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_A2, replaceSchdTm(dtlMsg.techSchdToTm_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(dtlMsg.svcTaskRcvTm_A)) {
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_A3, replaceRcvTm(dtlMsg.svcTaskRcvTm_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(dtlMsg.fsrVisitEtaTm_A)) {
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_A5, replaceSchdTm(dtlMsg.fsrVisitEtaTm_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(dtlMsg.techAcptTm_A)) {
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_A6, replaceSchdTm(dtlMsg.techAcptTm_A.getValue()));
            }

            // for Sort ScheduleDate
            sbDt.setLength(0);
            // Date
            if (ZYPCommonFunc.hasValue(dtlMsg.techSchdFromDt_A)) {
                sbDt.append(dtlMsg.techSchdFromDt_A.getValue());
            } else {
                sbDt.append(STR_DEF_DT);
            }
            // Date + Time
            if (ZYPCommonFunc.hasValue(dtlMsg.techSchdFromDt_A)) {
                sbDt.append(dtlMsg.techSchdFromDt_A.getValue());
            }
            ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_A4, sbDt.toString());

            rspAot = 0;
            rspTm = 0;
            // Response Time AOT
            if (ZYPCommonFunc.hasValue(dtlMsg.svcRspTmMnAot_A)) {
                rspAot = dtlMsg.svcRspTmMnAot_A.getValue().longValue();
            }

            ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_R1, String.format(FORMAT_RSP_TM, rspAot / HOUR_MIN, rspAot % HOUR_MIN));

            // Response Time
            if (ZYPCommonFunc.hasValue(dtlMsg.svcRspTmNum_A)) {
                rspTm = rspAot - dtlMsg.svcRspTmNum_A.getValue().longValue();
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_R2, String.format(FORMAT_RSP_TM, rspTm / HOUR_MIN, Math.abs(rspTm) % HOUR_MIN));
                ZYPEZDItemValueSetter.setValue(dtlMsg.xxTotAmt_A, new BigDecimal(rspTm));

            } else if (ZYPCommonFunc.hasValue(dtlMsg.svcTaskRcvDt_A) && ZYPCommonFunc.hasValue(dtlMsg.svcTaskRcvTm_A)) {

                String avalFromHourMn = "";
                String avalToHourMn = "";

                //get Available Time
                NSXC001001PMsg pmsg = new NSXC001001PMsg();
                setValue(pmsg.glblCmpyCd, dtlMsg.glblCmpyCd_A);
                setValue(pmsg.svcMachMstrPk, dtlMsg.svcMachMstrPk_A);
                setValue(pmsg.slsDt, dtlMsg.svcTaskRcvDt_A);
                NSXC001001Contr.getCov(pmsg);
                if (pmsg.xxContrList != null && pmsg.xxContrList.length() > 0) {
                    avalFromHourMn = pmsg.xxContrList.no(0).svcCovFromHourMn.getValue();
                    avalToHourMn = pmsg.xxContrList.no(0).svcCovToHourMn.getValue();
                }

                String currDt = currDtTm.substring(0, 8);
                String currTm = currDtTm.substring(8, 14);

                rcvDtL = NSXC001001GetRspTime.getRspTime(dtlMsg.glblCmpyCd_A.getValue(), dtlMsg.svcTaskRcvDt_A.getValue(), dtlMsg.svcTaskRcvTm_A.getValue(), currDt, currTm, avalFromHourMn, avalToHourMn);

                if (rcvDtL == null) {
                    rspTm = rspAot;
                } else {
                    rspTm = rspAot - (rcvDtL / HOUR_MIN_SEC);
                }

                ZYPEZDItemValueSetter.setValue(dtlMsg.xxTotAmt_A, new BigDecimal(rspTm));
                if (rspTm > MAX_RSP_TM) {
                    rspTm = MAX_RSP_TM;
                } else if (rspTm < -MAX_RSP_TM) {
                    rspTm = -MAX_RSP_TM;
                }

                ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_R2, String.format(FORMAT_RSP_TM, rspTm / HOUR_MIN, Math.abs(rspTm) % HOUR_MIN));
            }

            if (ZYPCommonFunc.hasValue(dtlMsg.custAvalFromHourMn_A)) {
                if (dtlMsg.custAvalFromHourMn_A.getValue().length() == AVAL_HOUR_SIZE) {
                    ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_H1, String.valueOf(dtlMsg.custAvalFromHourMn_A.getValue()).replaceAll(FORMAT_AVAL_HOUR_FROM, FORMAT_AVAL_HOUR_TO));
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_H1, String.valueOf(dtlMsg.custAvalFromHourMn_A.getValue()));
                }
            }
            if (ZYPCommonFunc.hasValue(dtlMsg.custAvalToHourMn_A)) {
                if (dtlMsg.custAvalToHourMn_A.getValue().length() == AVAL_HOUR_SIZE) {
                    ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_H2, String.valueOf(dtlMsg.custAvalToHourMn_A.getValue()).replaceAll(FORMAT_AVAL_HOUR_FROM, FORMAT_AVAL_HOUR_TO));
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlMsg.xxDtTm_H2, String.valueOf(dtlMsg.custAvalToHourMn_A.getValue()));
                }
            }
        }
    }

    /**
     * replace Schedule Time format
     * @param srcTm String
     * @return String
     */
    public static String replaceSchdTm(String srcTm) {
        if (srcTm != null) {
            return srcTm.replaceAll(FORMAT_SCHD_TM_FROM, FORMAT_SCHD_TM_TO);
        } else {
            return "";
        }
    }

    /**
     * replace Received Time format
     * @param srcTm String
     * @return String
     */
    public static String replaceRcvTm(String srcTm) {
        if (srcTm != null) {
            return srcTm.replaceAll(FORMAT_RCV_TM_FROM, FORMAT_RCV_TM_TO);
        } else {
            return "";
        }
    }

    /**
     * callApi
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     * @param mode String
     */
    public static void callApi(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String mode) {

        copyBizToShare(bizMsg, shareMsg);

        if (!inputCheck(bizMsg, shareMsg, mode)) {
            return;
        }

        if (!masterCheck(bizMsg, shareMsg, mode)) {
            return;
        }

        // START 2014/06/02 T.Aoyagi [CSA-166,ADD]
        if (!scheduleCheck(bizMsg, shareMsg, mode)) {
            return;
        }
        // END 2014/06/02 T.Aoyagi [CSA-166,ADD]

        List<NSZC003001PMsg> pMsgList = makeApiPMsgList(bizMsg, shareMsg, mode, bizMsg.getUserID());

        if (pMsgList == null) {
            return;
        }

        NSZC003001 svcDispatchApi = new NSZC003001();
        svcDispatchApi.execute(pMsgList, ONBATCH_TYPE.ONLINE);

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A_CHK_BOX, ZYPConstant.FLG_ON_Y);

        for (NSZC003001PMsg pMsg : pMsgList) {

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

                String xxMsgId = xxMsgIdList.get(0);
                bizMsg.setMessageInfo(xxMsgId);

                boolean isLineErr = false;
                for (int pIdx = 0; pIdx < pMsg.xxSvcTaskList.getValidCount(); pIdx++) {
                    if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(pIdx).xxMsgId)) {
                        for (int selectIdx : selectRows) {
                            if (pMsg.xxSvcTaskList.no(pIdx).svcTaskNum.getValue().equals(bizMsg.A.no(selectIdx).svcTaskNum_A.getValue())) {
                                bizMsg.A.no(selectIdx).xxChkBox_A.setErrorInfo(1, pMsg.xxSvcTaskList.no(pIdx).xxMsgId.getValue());
                                isLineErr = true;
                            }
                        }
                    }
                }
                if (isLineErr) {
                    continue;
                }

                for (int selectIdx : selectRows) {
                    for (int pIdx = 0; pIdx < pMsg.xxSvcTaskList.getValidCount(); pIdx++) {
                        if (pMsg.xxSvcTaskList.no(pIdx).svcTaskNum.getValue().equals(bizMsg.A.no(selectIdx).svcTaskNum_A.getValue())) {
                            bizMsg.A.no(selectIdx).xxChkBox_A.setErrorInfo(1, xxMsgId);
                        }
                    }
                }
            }
        }
    }

    private static boolean lockSvcTask(NSBL0010_ASMsg dtlSMsg, String svcTaskNum, String glblCmpyCd) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
        svcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(svcTaskTMsg);

        if (svcTaskTMsg == null) {
            return false;
        }

        String findEzUpTime = dtlSMsg.ezUpTime_A.getValue();
        String findEzUpTimeZone = dtlSMsg.ezUpTimeZone_A.getValue();
        String currentEzUpTime = svcTaskTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = svcTaskTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return false;
        }

        return true;
    }

    private static List<NSZC003001PMsg> makeApiPMsgList(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String mode, String userId) {
        Map<String, NSZC003001PMsg> fsrNumMap = new HashMap<String, NSZC003001PMsg>();
        Map<BigDecimal, String> machPkMap = new HashMap<BigDecimal, String>();

        String key = null;
        String fsrNum = null;
        BigDecimal svcMachMstrPk = null;
        String svcMachMstrStr = null;
        NSZC003001PMsg pMsg = null;

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(shareMsg.A, CHK_A_CHK_BOX, ZYPConstant.FLG_ON_Y);

        for (int selectIdx : selectRows) {
            fsrNum = shareMsg.A.no(selectIdx).fsrNum_A.getValue();
            svcMachMstrPk = shareMsg.A.no(selectIdx).svcMachMstrPk_A.getValue();

            if (!lockSvcTask(shareMsg.A.no(selectIdx), shareMsg.A.no(selectIdx).svcTaskNum_A.getValue(), shareMsg.A.no(selectIdx).glblCmpyCd_A.getValue())) {
                bizMsg.setMessageInfo(NSBM0006E);
                return null;
            }

            // Add Start 2014/04/11 S21 NA Def#42.
            if (!isSameTimeStampFsrVisit(shareMsg.A.no(selectIdx))) {
                bizMsg.setMessageInfo(NSBM0006E);
                return null;
            }
            // Add End 2014/04/11 S21 NA Def#42.

            if (ZYPCommonFunc.hasValue(fsrNum)) {

                if (fsrNumMap.containsKey(fsrNum)) {

                    pMsg = fsrNumMap.get(fsrNum);

                } else {

                    pMsg = new NSZC003001PMsg();
                    fsrNumMap.put(fsrNum, pMsg);
                    machPkMap.put(svcMachMstrPk, fsrNum);
                }

                setPMsgValues(pMsg, shareMsg, selectIdx, mode, userId);
            }
        }

        for (int selectIdx : selectRows) {
            fsrNum = shareMsg.A.no(selectIdx).fsrNum_A.getValue();
            svcMachMstrPk = shareMsg.A.no(selectIdx).svcMachMstrPk_A.getValue();
            if (svcMachMstrPk == null) {
                svcMachMstrStr = "";
            } else {
                svcMachMstrStr = svcMachMstrPk.toString();
            }

            if (!ZYPCommonFunc.hasValue(fsrNum)) {

                key = ZYPCommonFunc.concatString(svcMachMstrStr, COLON, svcMachMstrStr);

                if (machPkMap.containsKey(svcMachMstrPk)) {

                    fsrNum = machPkMap.get(svcMachMstrPk);
                    pMsg = fsrNumMap.get(fsrNum);

                } else if (fsrNumMap.containsKey(key)) {

                    pMsg = fsrNumMap.get(key);

                } else {
                    pMsg = new NSZC003001PMsg();
                    fsrNumMap.put(key, pMsg);
                }

                setPMsgValues(pMsg, shareMsg, selectIdx, mode, userId);
            }
        }

        List<NSZC003001PMsg> pMsgList = new ArrayList<NSZC003001PMsg>();

        for (String mapKey : fsrNumMap.keySet()) {
            pMsgList.add(fsrNumMap.get(mapKey));
        }

        if (pMsgList.size() == 0) {
            return null;
        }

        return pMsgList;
    }

    private static boolean isSameTimeStampFsrVisit(NSBL0010_ASMsg dtlSMsg) {
        if (!ZYPCommonFunc.hasValue(dtlSMsg.fsrNum_A) || !ZYPCommonFunc.hasValue(dtlSMsg.fsrVisitNum_A)) {
            return true;
        }

        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitForUpdate(dtlSMsg.glblCmpyCd_A.getValue(), dtlSMsg.fsrNum_A.getValue(), dtlSMsg.fsrVisitNum_A.getValue());
        if (fsrVisitTMsg == null) {
            return false;
        }

        String findEzUpTime = dtlSMsg.ezUpTime_FV.getValue();
        String findEzUpTimeZone = dtlSMsg.ezUpTimeZone_FV.getValue();
        String currentEzUpTime = fsrVisitTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = fsrVisitTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return false;
        }

        return true;
    }

    private static void setPMsgValues(NSZC003001PMsg pMsg, NSBL0010SMsg shareMsg, int index, String mode, String userId) {

        // START 2015/11/18 T.Harada [CSA,CHANGE]
        String glblCmpyCd = shareMsg.A.no(index).glblCmpyCd_A.getValue();
        String techCd = shareMsg.A.no(index).techCd_A.getValue();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);

        //ZYPEZDItemValueSetter.setValue(pMsg.xxBizProcTp, mode);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBizProcTp, UPDATE_CALL);
        if (MODE_DISPATCH.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.IN_ROUTE);
        } else if (MODE_CANCEL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, getSvcTaskSts4Cancel(glblCmpyCd, techCd));
        } else if (MODE_SCHEDULE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.SCHEDULED);
        }
        // END 2015/11/18 T.Harada [CSA,CHANGE]

        if (ZYPCommonFunc.hasValue(shareMsg.A.no(index).fsrNum_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, shareMsg.A.no(index).fsrNum_A.getValue());

            // START 2015/11/18 T.Harada [CSA,ADD]
            FSRTMsg fsrTmsg = new FSRTMsg();
            ZYPEZDItemValueSetter.setValue(fsrTmsg.glblCmpyCd, shareMsg.A.no(index).glblCmpyCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTmsg.fsrNum, shareMsg.A.no(index).fsrNum_A.getValue());
            fsrTmsg = (FSRTMsg) EZDTBLAccessor.findByKey(fsrTmsg);

            if(fsrTmsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcCallSrcTpCd, fsrTmsg.svcCallSrcTpCd);
                ZYPEZDItemValueSetter.setValue(pMsg.svcPblmTpCd, fsrTmsg.svcPblmTpCd);
                ZYPEZDItemValueSetter.setValue(pMsg.svcCallRqstOwnrTocCd, fsrTmsg.svcCallRqstOwnrTocCd);
                ZYPEZDItemValueSetter.setValue(pMsg.svcCallIncdtDt, fsrTmsg.svcCallIncdtDt);
                ZYPEZDItemValueSetter.setValue(pMsg.svcCallIncdtTm, fsrTmsg.svcCallIncdtTm);
                ZYPEZDItemValueSetter.setValue(pMsg.custCseNum, fsrTmsg.custCseNum);
                ZYPEZDItemValueSetter.setValue(pMsg.ittNum, fsrTmsg.ittNum);
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, fsrTmsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustUpdFlg, fsrTmsg.billToCustUpdFlg);
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustUpdFlg, fsrTmsg.shipToCustUpdFlg);
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, fsrTmsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(pMsg.billToUpdCustCd, fsrTmsg.billToUpdCustCd);
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, fsrTmsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(pMsg.shipToUpdCustCd, fsrTmsg.shipToUpdCustCd);
                ZYPEZDItemValueSetter.setValue(pMsg.fsrTpCd, fsrTmsg.fsrTpCd);
            }
            // END 2015/11/18 T.Harada [CSA,ADD]
        }

        int cnt = pMsg.xxSvcTaskList.getValidCount();
        NSZC003001_xxSvcTaskListPMsg dtlPMsg = pMsg.xxSvcTaskList.no(cnt);
        NSBL0010_ASMsg asMsg = shareMsg.A.no(index);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcTaskNum, asMsg.svcTaskNum_A.getValue());
        ZYPEZDItemValueSetter.setValue(dtlPMsg.userId, userId);

        if (!MODE_CANCEL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcCallPrtyCd, asMsg.svcCallPrtyCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.techCd, asMsg.techCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.techSchdFromDt, asMsg.techSchdFromDt_A.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.techSchdFromTm, asMsg.techSchdFromTm_A.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.techSchdToDt, asMsg.techSchdToDt_A.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.techSchdToTm, asMsg.techSchdToTm_A.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.techSchdTz, asMsg.techSchdTz_A.getValue()); // 2014/03/07 S21_NA QC#0046 release comment out
        }

        if (MODE_SCHEDULE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.schdDisptEmlFlg, asMsg.schdDisptEmlFlg_A.getValue());
        }

        // START 2015/11/18 T.Harada [CSA,ADD]
        ZYPEZDItemValueSetter.setValue(dtlPMsg.mblIntfcFlg, ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(shareMsg.A.no(index).fsrNum_A.getValue()) &&
             ZYPCommonFunc.hasValue(shareMsg.A.no(index).fsrVisitNum_A.getValue())) {

            FSR_VISITTMsg fsrVisitTmsg = new FSR_VISITTMsg();
            ZYPEZDItemValueSetter.setValue(fsrVisitTmsg.glblCmpyCd, shareMsg.A.no(index).glblCmpyCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTmsg.fsrNum, shareMsg.A.no(index).fsrNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTmsg.fsrVisitNum, shareMsg.A.no(index).fsrVisitNum_A.getValue());
            fsrVisitTmsg = (FSR_VISITTMsg) EZDTBLAccessor.findByKey(fsrVisitTmsg);
            
            if(fsrVisitTmsg != null){
                ZYPEZDItemValueSetter.setValue(dtlPMsg.svcLttdNum, fsrVisitTmsg.svcLttdNum);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.svcLgtdNum, fsrVisitTmsg.svcLgtdNum);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.svcUnAsgRsnTxt, fsrVisitTmsg.svcUnAsgRsnTxt);
            }
        }
        // END 2015/11/18 T.Harada [CSA,ADD]

        pMsg.xxSvcTaskList.setValidCount(cnt + 1);
    }

    /**
     * getSvcTaskSts4Cancel
     * @param gcc String
     * @param techCd String
     * @return String
     */
    private static String getSvcTaskSts4Cancel(String gcc, String techCd) {

        String svcTaskStsCd = null;

        if (!ZYPCommonFunc.hasValue(techCd)) {
            return SVC_TASK_STS.TBO;
        }

        S21_PSNTMsg s21PsnTmsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(s21PsnTmsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(s21PsnTmsg.psnCd, techCd);
        
        s21PsnTmsg = (S21_PSNTMsg) EZDTBLAccessor.findByKey(s21PsnTmsg);
        
        if(s21PsnTmsg != null){
            if(PSN_TP.EMPLOYEE.equals(s21PsnTmsg.psnTpCd.getValue())){
                svcTaskStsCd = SVC_TASK_STS.TBO;
            }else{
                svcTaskStsCd = SVC_TASK_STS.OPEN;
            }
        } else {
            svcTaskStsCd = SVC_TASK_STS.TBO;
        }

        return svcTaskStsCd;
    }

    /**
     * setMsgFlg
     * @param bizMsg NSBL0010CMsg
     */
    public static void setMsgFlg(NSBL0010CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() < 0) {
            return;
        }
        String svcTaskNum = bizMsg.A.no(bizMsg.xxRowNum.getValueInt()).svcTaskNum_A.getValue();

        String msgFlg = NSBL0010Query.getInstance().getMsgFlg(bizMsg, svcTaskNum);

        if (ZYPCommonFunc.hasValue(msgFlg)) {
            bizMsg.A.no(bizMsg.xxRowNum.getValueInt()).xxBtnFlg_AM.setValue(msgFlg);
        }
        bizMsg.xxRowNum.clear();
    }

    /**
     * accept
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    public static void accept(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String glblCmpyCd) {

        copyBizToShare(bizMsg, shareMsg);

        if (!inputCheckForAccept(bizMsg, shareMsg)) {
            return;
        }

        List<NSZC003001PMsg> pMsgList = makeApiPMsgListForAccept(bizMsg, shareMsg, bizMsg.getUserID());

        if (pMsgList == null) {
            return;
        }

        NSZC003001 svcDispatchApi = new NSZC003001();
        svcDispatchApi.execute(pMsgList, ONBATCH_TYPE.ONLINE);

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A_CHK_BOX, ZYPConstant.FLG_ON_Y);

        for (NSZC003001PMsg pMsg : pMsgList) {

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

                String xxMsgId = xxMsgIdList.get(0);
                bizMsg.setMessageInfo(xxMsgId);

                boolean isLineErr = false;
                for (int pIdx = 0; pIdx < pMsg.xxSvcTaskList.getValidCount(); pIdx++) {
                    if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(pIdx).xxMsgId)) {
                        for (int selectIdx : selectRows) {
                            if (pMsg.xxSvcTaskList.no(pIdx).svcTaskNum.getValue().equals(bizMsg.A.no(selectIdx).svcTaskNum_A.getValue())) {
                                bizMsg.A.no(selectIdx).xxChkBox_A.setErrorInfo(1, pMsg.xxSvcTaskList.no(pIdx).xxMsgId.getValue());
                                isLineErr = true;
                            }
                        }
                    }
                }
                if (isLineErr) {
                    continue;
                }

                for (int selectIdx : selectRows) {
                    for (int pIdx = 0; pIdx < pMsg.xxSvcTaskList.getValidCount(); pIdx++) {
                        if (pMsg.xxSvcTaskList.no(pIdx).svcTaskNum.getValue().equals(bizMsg.A.no(selectIdx).svcTaskNum_A.getValue())) {
                            bizMsg.A.no(selectIdx).xxChkBox_A.setErrorInfo(1, xxMsgId);
                        }
                    }
                }
            }
        }
    }

    /**
     * inputCheckForAccept
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     * @return boolean
     */
    private static boolean inputCheckForAccept(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        NSBL0010_ASMsg dtlMsg = null;
        String svcTaskStsCd = null;
        int cnt = 0;
        int curr = 0;
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int size = shareMsg.A.getValidCount();
        boolean retFlg = true;

        for (int i = 0; i < size; i++) {
            dtlMsg = shareMsg.A.no(i);
            if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                continue;
            }
            cnt++;
            curr = i - pageFrom;

            if (!ZYPCommonFunc.hasValue(dtlMsg.fsrNum_A)) {
                bizMsg.setMessageInfo(NSBM0100E);
                if (curr >= 0 && curr < bizMsg.A.length()) {
                    bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0037E);
                }
                retFlg = false;
            }

            if (ZYPConstant.FLG_ON_Y.equals(dtlMsg.techAcptFlg_A.getValue())) {
                bizMsg.setMessageInfo(NSBM0100E);
                if (curr >= 0 && curr < bizMsg.A.length()) {
                    bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0112E);
                }
                retFlg = false;
            }

            // check Task/FSR Status
            svcTaskStsCd = dtlMsg.svcTaskStsCd_A.getValue();
            if (!SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
                String[] errMsg = new String[] {"Accept", "Scheduled" };
                bizMsg.setMessageInfo(NSBM0100E);
                if (curr >= 0 && curr < bizMsg.A.length()) {
                    bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0010E, errMsg);
                }
                retFlg = false;
            }

        }

        // check box count
        if (cnt == 0) {
            bizMsg.setMessageInfo(NSBM0007E);
            retFlg = false;
        }
        return retFlg;
    }

    private static List<NSZC003001PMsg> makeApiPMsgListForAccept(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String userId) {
        Map<String, NSZC003001PMsg> fsrNumMap = new HashMap<String, NSZC003001PMsg>();
        Map<String, List<Integer>> fsrIdxMap = new HashMap<String, List<Integer>>();

        String glblCmpyCd = null;
        String fsrNum = null;
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
        String svcTaskNum = null;
        NSZC003001PMsg pMsg = null;

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(shareMsg.A, CHK_A_CHK_BOX, ZYPConstant.FLG_ON_Y);

        for (int selectIdx : selectRows) {
            glblCmpyCd = shareMsg.A.no(selectIdx).glblCmpyCd_A.getValue();
            fsrNum = shareMsg.A.no(selectIdx).fsrNum_A.getValue();
            svcTaskNum = shareMsg.A.no(selectIdx).svcTaskNum_A.getValue();

            if (!lockSvcTask(shareMsg.A.no(selectIdx), shareMsg.A.no(selectIdx).svcTaskNum_A.getValue(), shareMsg.A.no(selectIdx).glblCmpyCd_A.getValue())) {
                bizMsg.setMessageInfo(NSBM0006E);
                return null;
            }

            // Add Start 2014/04/11 S21 NA Def#42.
            if (!isSameTimeStampFsrVisit(shareMsg.A.no(selectIdx))) {
                bizMsg.setMessageInfo(NSBM0006E);
                return null;
            }
            // Add End 2014/04/11 S21 NA Def#42.

            if (fsrNumMap.containsKey(fsrNum)) {

                pMsg = fsrNumMap.get(fsrNum);
                fsrIdxMap.get(fsrNum).add(selectIdx);
            } else {

                pMsg = new NSZC003001PMsg();
                fsrNumMap.put(fsrNum, pMsg);

                List<Integer> idxList = new ArrayList<Integer>();
                idxList.add(selectIdx);
                fsrIdxMap.put(fsrNum, idxList);
            }

//            setPMsgValuesForAccept(pMsg, glblCmpyCd, fsrNum, userId);
            setPMsgValuesForAccept(pMsg, glblCmpyCd, fsrNum, userId, svcTaskNum);
// END 2017/01/05 N.Arai [QC#13901-2, MOD]

        }

        if (isAlreadyAccept(bizMsg, shareMsg, fsrNumMap, fsrIdxMap)) {
            return null;
        }

        List<NSZC003001PMsg> pMsgList = new ArrayList<NSZC003001PMsg>();
        for (String mapKey : fsrNumMap.keySet()) {
            pMsgList.add(fsrNumMap.get(mapKey));
        }

        if (pMsgList.size() == 0) {
            return null;
        }

        return pMsgList;
    }

    private static boolean isAlreadyAccept(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, Map<String, NSZC003001PMsg> fsrNumMap, Map<String, List<Integer>> fsrIdxMap) {
        boolean result = false;
        for (String fsrNum : fsrNumMap.keySet()) {
            int index = fsrIdxMap.get(fsrNum).get(0);
            String glblCmpyCd = shareMsg.A.no(index).glblCmpyCd_A.getValue();
            S21SsmEZDResult rs = NSBL0010Query.getInstance().getFsrVisitByLtstFlg(glblCmpyCd, fsrNum, ZYPConstant.FLG_ON_Y);
            if (rs.isCodeNotFound()) {
                result = true;
                setErr(bizMsg, fsrIdxMap, fsrNum, NSBM0062E);
                continue;
            }

            List<Map<String, String>> fsrVisitList = (List<Map<String, String>>) rs.getResultObject();
            Map<String, String> map = fsrVisitList.get(0);
            FSR_VISITTMsg fsrVisitTMsg = getFsrVisitForUpdate(glblCmpyCd, fsrNum, (String)map.get("FSR_VISIT_NUM"));
            if (fsrVisitTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(fsrVisitTMsg.getReturnCode())) {
                result = true;
                setErr(bizMsg, fsrIdxMap, fsrNum, NSBM0062E);
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(fsrVisitTMsg.techAcptFlg.getValue())) {
                result = true;
                setErr(bizMsg, fsrIdxMap, fsrNum, NSBM0112E);
                continue;
            }
        }
        return result;
    }


    private static FSR_VISITTMsg getFsrVisitForUpdate(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        FSR_VISITTMsg paramFsrTMsg = new FSR_VISITTMsg();
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrNum, fsrNum);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrVisitNum, fsrVisitNum);

        return (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramFsrTMsg);
    }
    
    private static void setErr(NSBL0010CMsg bizMsg, Map<String, List<Integer>> fsrIdxMap, String fsrNum, String msgId) {
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        List<Integer> idxList = fsrIdxMap.get(fsrNum);
        for (Integer idx : idxList) {
            int curr = idx - pageFrom;
            if (curr >= 0 && curr < bizMsg.A.length()) {
                bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, msgId);
            }
        }
    }

// START 2017/01/05 N.Arai [QC#13901-2, MOD]
    private static void setPMsgValuesForAccept(NSZC003001PMsg pMsg, String glblCmpyCd, String fsrNum, String userId, String svcTaskNum) {

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxBizProcTp, NSZC003001Constant.MODE_ACCEPT);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, fsrNum);

        int cnt = pMsg.xxSvcTaskList.getValidCount();
        NSZC003001_xxSvcTaskListPMsg dtlPMsg = pMsg.xxSvcTaskList.no(cnt);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.userId, userId);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcTaskNum, svcTaskNum);

        pMsg.xxSvcTaskList.setValidCount(cnt + 1);
    }
// END 2017/01/05 N.Arai [QC#13901-2, MOD]

    /**
     * updateETA
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     */
    public static void updateETA(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String glblCmpyCd) {

        copyBizToShare(bizMsg, shareMsg);

        if (!inputCheckForUpdateETA(bizMsg, shareMsg)) {
            return;
        }

        NSZC012001PMsg pMsg = makeTechArrivedUpdateApiPMsg(bizMsg, shareMsg, bizMsg.getUserID(), glblCmpyCd);

        if (pMsg == null) {
            return;
        }

        new NSZC012001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            String xxMsgId = xxMsgIdList.get(0);
            bizMsg.setMessageInfo(xxMsgId);
        }
    }
    
    /**
     * inputCheckForUpdateETA
     * @param bizMsg NSBL0010CMsg
     * @param shareMsg NSBL0010SMsg
     * @return boolean
     */
    private static boolean inputCheckForUpdateETA(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg) {
        NSBL0010_ASMsg dtlMsg = null;
        String svcTaskStsCd = null;
        int cnt = 0;
        int curr = 0;
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int size = shareMsg.A.getValidCount();
        boolean retFlg = true;

        for (int i = 0; i < size; i++) {
            dtlMsg = shareMsg.A.no(i);
            if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                continue;
            }
            cnt++;
            curr = i - pageFrom;

            if (!ZYPCommonFunc.hasValue(dtlMsg.fsrNum_A)) {
                bizMsg.setMessageInfo(NSBM0100E);
                if (curr >= 0 && curr < bizMsg.A.length()) {
                    bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0037E);
                }
                retFlg = false;
            }

            // check Task/FSR Status
            svcTaskStsCd = dtlMsg.svcTaskStsCd_A.getValue();
            if (!SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd) && !SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
                String[] errMsg = new String[] {"Update ETA", "Scheduled, Assigned" };
                bizMsg.setMessageInfo(NSBM0100E);
                if (curr >= 0 && curr < bizMsg.A.length()) {
                    bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0010E, errMsg);
                }
                retFlg = false;
            }

        }

        // check box count
        if (cnt == 0) {
            bizMsg.setMessageInfo(NSBM0007E);
            retFlg = false;
        }
        return retFlg;
    }

    private static NSZC012001PMsg makeTechArrivedUpdateApiPMsg(NSBL0010CMsg bizMsg, NSBL0010SMsg shareMsg, String userId, String glblCmpyCd) {

        NSZC012001PMsg pMsg = new NSZC012001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSZC012001Constant.PROCESS_MODE_ETA);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        boolean isErr = false;
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int cnt = 0;
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(shareMsg.A, CHK_A_CHK_BOX, ZYPConstant.FLG_ON_Y);
        for (int selectIdx : selectRows) {
            String fsrNum = shareMsg.A.no(selectIdx).fsrNum_A.getValue();
            String fsrVisitNum = shareMsg.A.no(selectIdx).fsrVisitNum_A.getValue();

            FSR_VISITTMsg fsrVisitTMsg = getFsrVisitForUpdate(shareMsg.A.no(selectIdx).glblCmpyCd_A.getValue(), fsrNum, fsrVisitNum);
            if (fsrVisitTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(fsrVisitTMsg.getReturnCode())) {
                isErr = true;
                bizMsg.setMessageInfo(NSBM0100E);
                int curr = selectIdx - pageFrom;
                if (curr >= 0 && curr < bizMsg.A.length()) {
                    bizMsg.A.no(curr).xxChkBox_A.setErrorInfo(1, NSBM0113E);
                }
                continue;
            }

            String findEzUpTime = shareMsg.A.no(selectIdx).ezUpTime_FV.getValue();
            String findEzUpTimeZone = shareMsg.A.no(selectIdx).ezUpTimeZone_FV.getValue();
            String currentEzUpTime = fsrVisitTMsg.ezUpTime.getValue();
            String currentEzUpTimeZone = fsrVisitTMsg.ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                bizMsg.setMessageInfo(NSBM0006E);
                return null;
            }

            ZYPEZDItemValueSetter.setValue(pMsg.FSRVisitList.no(cnt).fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(pMsg.FSRVisitList.no(cnt).fsrVisitNum, fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(pMsg.FSRVisitList.no(cnt).svcTaskNum, shareMsg.A.no(selectIdx).svcTaskNum_A);
            ZYPEZDItemValueSetter.setValue(pMsg.FSRVisitList.no(cnt).fsrVisitEtaDt, shareMsg.A.no(selectIdx).fsrVisitEtaDt_A);
            ZYPEZDItemValueSetter.setValue(pMsg.FSRVisitList.no(cnt).fsrVisitEtaTm, shareMsg.A.no(selectIdx).fsrVisitEtaTm_A);
            ZYPEZDItemValueSetter.setValue(pMsg.FSRVisitList.no(cnt).userId, userId);
            cnt++;
        }

        if (isErr || cnt == 0) {
            return null;
        }
        pMsg.FSRVisitList.setValidCount(cnt);

        return pMsg;
    }

 // START 2017/01/17 N.Arai [QC#16331, MOD]
    /**
     * clearMsg
     * @param cMsg
     * @param sMsg
     */
    public static void clearMsg(NSBL0010CMsg cMsg, NSBL0010SMsg sMsg) {
        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
    }
 // END 2017/01/17 N.Arai [QC#16331, MOD]
}

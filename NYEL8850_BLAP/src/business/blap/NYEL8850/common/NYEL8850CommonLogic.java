/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8850.common;

import static business.blap.NYEL8810.constant.NYEL8810Constant.ADMIN_GRP;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_OPERATOR_NAME;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_RIGHTS;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0002E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0003W;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ACT_USER_WFSYSTEM;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.TEXTAREA_NEWLINE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;

import business.blap.NYEL8850.NYEL8850CMsg;
import business.blap.NYEL8850.NYEL8850SMsg;
import business.blap.NYEL8850.constant.NYEL8850Constant;

/**
 *<pre>
 * NYEL8850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 * 2018/10/24   Fujitsu         Q10814          Update          QC#28914
 *</pre>
 */
public class NYEL8850CommonLogic {

    /**
     * search
     * @param bizMsg Business Message
     * @param sMsg Server Message
     * @param usrId user Id
     */
    public static void search(NYEL8850CMsg bizMsg, NYEL8850SMsg sMsg, String usrId) {
        int index = bizMsg.xxCellIdx.getValueInt();

        if (index < 0) {
            index = 0;
        }

        search(bizMsg, sMsg, usrId, index);
    }

    /**
     * search
     * @param bizMsg Business Message
     * @param sMsg Server Message
     * @param usrId user Id
     */
    public static void search(NYEL8850CMsg bizMsg, NYEL8850SMsg sMsg, String usrId, int index) {
        String uid = usrId;

        if (NYXC880002.isAdministrator(usrId)) {
            uid = "";
        }

        S21SsmEZDResult result = null;

        if (NYEL8850Constant.PARAM_DOC_ID.equals(bizMsg.P.no(0).xxPopPrm_P1.getValue())) {
            result = NYXC880002Query.getInstance().getProc(bizMsg.P.no(0).xxPopPrm_P2.getValue(), uid, WF_PROC_COND_STS.ALL);
        } else if (NYEL8850Constant.PARAM_PROC_TAG.equals(bizMsg.P.no(0).xxPopPrm_P1.getValue())) {
            result = NYXC880002Query.getInstance().getProcByGroupNm(bizMsg.P.no(0).xxPopPrm_P3.getValue(), uid, WF_PROC_COND_STS.ALL);
        } else {
            bizMsg.setMessageInfo(NYEM0002E);
            return;
        }

        if (result.isCodeNotFound()) {
            bizMsg.setMessageInfo(NYEM0002E);
            return;
        }

        String psts = "";

        if (result.isCodeNormal()) {

            List resultList = (List) result.getResultObject();

            // set Header
            if (!search_Header(bizMsg, resultList, index, uid)) {
                return;
            }

            psts =  (String)((Map) resultList.get(index)).get("WFPROCSTCD");
        }

        result = NYXC880002Query.getInstance().getTask(bizMsg.H.no(index).wfProcPk_H.getValue(), uid, NYXC880001.toWorklistProcStatusCd(psts));

        if (result.isCodeNotFound()) {
            bizMsg.setMessageInfo(NYEM0002E);
            return;
        }

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();

            // set Status
            if (!search_Status(bizMsg, sMsg, resultList)) {
                return;
            }
        }

        result = NYXC880002Query.getInstance().getHistory(bizMsg.H.no(index).wfProcPk_H.getValue(), uid, NYXC880001.toWorklistProcStatusCd(psts));

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();

            // set History
            if (!search_History(bizMsg, sMsg, resultList)) {
                return;
            }
        }
    }

    private static boolean search_Header(NYEL8850CMsg bizMsg, List resultList, int targetIndex, String uid) {
        ZYPTableUtil.clear(bizMsg.H);

        // Normal
        int max = resultList.size();

        if (max <= 0) {
            return false;
        }

        int bizHMax = bizMsg.H.length();
        int maxIndex = max;
        int index = 0;

        if (max > bizHMax) {
            index = max - bizHMax;
            max = bizMsg.H.length();
            bizMsg.setMessageInfo(NYEM0003W);
        }

        int cnt = 0;
        for (; index < maxIndex; index++) {
            Map map = (Map) resultList.get(index);

            if (targetIndex == cnt) {
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).xxSelFlg_H, ZYPConstant.FLG_ON_1);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).xxSelFlg_H, ZYPConstant.FLG_OFF_0);
            }

            // ProcessPK
            if (map.containsKey("WFPROCPK")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).wfProcPk_H, (BigDecimal) map.get("WFPROCPK"));
            }
            
            // DocumentID
            if (map.containsKey("WFPROCDOCID")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).wfProcDocId_H, (String) map.get("WFPROCDOCID"));
            }

            // ProcessName
            if (map.containsKey("WFPROCNM")) {
                String procNm = (String) map.get("WFPROCNM");
                
                if (procNm.length() > NYXC880001constant.PROCESS_NM_LENGTH){
                    procNm = procNm.substring(0, NYXC880001constant.PROCESS_NM_LENGTH);
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).wfProcNm_H, procNm);
            }

            // ProcessStatus
            if (map.containsKey("WFPROCSTCD")) {
                String psts = (String) map.get("WFPROCSTCD");

                if (S21StringUtil.isNotEmpty(psts)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).xxWfProcStsNm_H, NYXC880001.toProcStatusNm(psts));
                }
            }

            // Process Date end
            if (map.containsKey("WFENDTS")) {
                String ended = (String) map.get("WFENDTS");

                if (S21StringUtil.isNotEmpty(ended)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(cnt).xxDtTm_PE, ZYPDateUtil.formatEzd17ToDisp(ended));
                }
            }

            if (targetIndex == cnt) {
                // Process Description
                if (map.containsKey("WFCMNTTXT")) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_PR, (String) map.get("WFCMNTTXT"));
                }

                // Process Group
                search_Group(bizMsg, bizMsg.H.no(cnt).wfProcPk_H.getValue(), uid);
            }

            cnt++;
        }

        if (bizHMax > max) {
            bizMsg.H.setValidCount(max);
        } else {
            bizMsg.H.setValidCount(bizHMax);
        }

        if (max <= 0) {
            bizMsg.setMessageInfo(NYEM0002E);
        }

        return true;

    }

    /**
     * search_Group
     * @param bizMsg Business Message
     * @param wfProcPk Process PK
     * @param uid user ID
     * @return boolean
     */
    private static boolean search_Group(NYEL8850CMsg bizMsg, BigDecimal wfProcPk, String uid) {

        S21SsmEZDResult result = NYXC880002Query.getInstance().getGroupByProcPk(wfProcPk, uid);

        if (result.isCodeNotFound()) {
            return false;
        }

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();
            StringBuilder sb = new StringBuilder();

            int max = resultList.size();

            String crlf = System.getProperty("line.separator");
            
            for (int cnt = 0; cnt < max; cnt++) {
                Map map = (Map) resultList.get(cnt);

                String groupNm = (String) map.get("WFPROCTAGNM");
                String pad = "";

                if (max > 1){
                    if (NYEL8850Constant.PARAM_PROC_TAG.equals(bizMsg.P.no(0).xxPopPrm_P1.getValue())) {
                        pad = "  ";
                        
                        if (bizMsg.P.no(0).xxPopPrm_P3.getValue().equals(groupNm)) {
                            pad = NYEL8850Constant.TARGET_GROUP;
                        }
                    }
                }

                sb.append(pad + groupNm + crlf);
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcTagNm, sb.toString());

        }

        return true;
    }

    /**
     * search_Status
     * @param bizMsg Business Message
     * @param glblMsg Server Message
     * @param resultList Process Search Result
     * @return boolean
     */
    private static boolean search_Status(NYEL8850CMsg bizMsg, NYEL8850SMsg glblMsg, List resultList) {
        ZYPTableUtil.clear(bizMsg.A);

        // Normal
        int max = resultList.size();

        if (max <= 0) {
            return false;
        }

        int bizAMax = bizMsg.A.length();
        int maxIndex = max;
        int index = 0;

        if (max > bizAMax) {
            index = max - bizAMax;
            max = bizMsg.A.length();
            bizMsg.setMessageInfo(NYEM0003W);
        }

        int cnt = 0;
        for (; index < maxIndex; index++) {
            Map map = (Map) resultList.get(index);

            // Task Status
            String taskSts = "";
            if (map.containsKey("WFWRKITEMSTSCD")) {
                taskSts = (String) map.get("WFWRKITEMSTSCD");
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemStsCd_A, taskSts);
            }

            // Task ID
            if (map.containsKey("WFWRKITEMID")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemId_A, (String) map.get("WFWRKITEMID"));
            }

            // Task Name
            if (map.containsKey("WFWRKITEMNM")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemNm_A, (String) map.get("WFWRKITEMNM"));
            }

            // Task ID(Hidden)
            BigDecimal taskID = BigDecimal.ZERO;
            if (map.containsKey("WFWRKITEMPK")) {
                taskID = (BigDecimal) map.get("WFWRKITEMPK");
            }
            
            if ((S21NwfWorkItem.STATUS.IDLE.getCode().equals(taskSts)) || 
                    (S21NwfWorkItem.STATUS.RUN.getCode().equals(taskSts))){
                // To User
                S21SsmEZDResult  userList = NYXC880002Query.getInstance().getToUsers(taskID.toPlainString(), "", WF_PROC_COND_STS.ALL);

                if (userList.isCodeNormal()) {
                    // Normal
                    List userltList = (List) userList.getResultObject();
                    int maxSize = userltList.size();
                    String toUserNm = "";
                    String isAdmin = "";
                    String isGroup = "";
// 2018/10/24 QC#28914 DEL START
//                    boolean hasAdminUser = false;
// 2018/10/24 QC#28914 DEL END
                    StringBuilder sb = new StringBuilder();
                    Map<String, String> grpMap = new HashMap<String,String>();

                    for (int i = 0; i < maxSize; i++) {
                        Map userMap = (Map) userltList.get(i);
                        toUserNm = (String) userMap.get("WFUSRNM");
                        isAdmin = (String) userMap.get("ISADMIN");
                        isGroup = (String) userMap.get("ISGROUP");

// 2018/10/24 QC#28914 DEL START
//                        if (ZYPConstant.FLG_ON_1.equals(isGroup)) {
//                            if (ADMIN_GRP.equals(toUserNm)) {
//                                hasAdminUser = true;
//                                continue;
//                            }
//                        } else  if (ZYPConstant.FLG_ON_1.equals(isAdmin) || ADMIN_GRP.equals(toUserNm)) {
//                            hasAdminUser = true;
//                            continue;
//                        }
// 2018/10/24 QC#28914 DEL END

                        if (grpMap.containsKey(toUserNm)){
                            continue;
                        }

// 2018/10/24 QC#28914 MOD START
//                        if (S21StringUtil.isEmpty(toUserNm)) {
//                            toUserNm = "";
//                        }
//
//                        if (toUserNm != null) {
//                            if (sb.length() > 0) {
//                                sb.append(TEXTAREA_NEWLINE);
//                            }
//
//                            if (S21StringUtil.isNotEmpty(toUserNm)){
//                                sb.append(toUserNm);
//                                grpMap.put(toUserNm, toUserNm);
//
//                            }
//                        }
                        if (S21StringUtil.isNotEmpty(toUserNm)) {
                            if (sb.length() > 0) {
                                sb.append(TEXTAREA_NEWLINE);
                            }
                            sb.append(toUserNm);
                            if (ZYPConstant.FLG_ON_1.equals(isAdmin) && ZYPConstant.FLG_OFF_0.equals(isGroup)) {
                                sb.append(" (").append(ADMIN_RIGHTS).append(")");
                            }
                            grpMap.put(toUserNm, toUserNm);
                        }
// 2018/10/24 QC#28914 MOD END
                    }

// 2018/10/24 QC#28914 DEL START
//                    if (hasAdminUser && (sb.length() <= 0)){
//                        sb.append(ADMIN_OPERATOR_NAME);
//                    }
// 2018/10/24 QC#28914 DEL END
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).xxWfAsgToNm_A, sb.toString());
                }
            }
            cnt++;
        }

        if (bizAMax > max) {
            bizMsg.A.setValidCount(max);
        } else {
            bizMsg.A.setValidCount(bizAMax);
        }

        if (max <= 0) {
            bizMsg.setMessageInfo(NYEM0002E);
        }

        return true;

    }

    /**
     * search_History
     * @param bizMsg Business Message
     * @param sMsg Server Message
     * @param resultList Process Search Result
     * @return boolean
     */
    private static boolean search_History(NYEL8850CMsg bizMsg, NYEL8850SMsg glblMsg, List resultList) {
        ZYPTableUtil.clear(bizMsg.B);

        int max = resultList.size();

        if (max <= 0) {
            return false;
        }

        int bizBMax = bizMsg.B.length();
        int maxIndex = max;
        int index = 0;

        if (max > bizBMax) {
            index = max - bizBMax;
            max = bizMsg.B.length();
            bizMsg.setMessageInfo(NYEM0003W);
        }

        int cnt = 0;
        for (; index < maxIndex; index++) {
            Map map = (Map) resultList.get(index);

            // NumCol
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxRowNum_B, BigDecimal.valueOf(cnt + 1));

            // FYIConfLt
            bizMsg.B.no(cnt).xxWfFyiBtnVisFlg_B.setValue(ZYPConstant.FLG_OFF_N);

            // Task ID
            if (map.containsKey("WFWRKITEMID")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).wfWrkItemId_B, (String) map.get("WFWRKITEMID"));
            }

            // Task Name
            if (map.containsKey("WFWRKITEMNM")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).wfWrkItemNm_B, (String) map.get("WFWRKITEMNM"));
            }

            String isAdmin = (String) map.get("ISADMIN");
            boolean isAuto = false;

            if (map.containsKey("WFEVENTAUTOFLG")) {
                String autoflg = (String) map.get("WFEVENTAUTOFLG");

                if (S21StringUtil.isNotEmpty(autoflg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(autoflg)) {
                        isAuto = true;
                    }
                }
            }

            // Action
            String eventCd = "";
            if (map.containsKey("WFEVENTTPCD")) {
                eventCd = (String) map.get("WFEVENTTPCD");

                if (S21StringUtil.isNotEmpty(eventCd)) {
                    String actNm = NYXC880001.toActNm(eventCd);

                    if (S21StringUtil.isNotEmpty(actNm)) {
                        if (isAuto) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).actWfCondNm_B, "AUTO " + actNm);
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).actWfCondNm_B, actNm);
                        }
                    }
                }
            }

            // Operator User
// 2018/10/24 QC#28914 ADD START
            String isAdminFU = (String) map.get("FROMUSER_ISADMIN");
// 2018/10/24 QC#28914 ADD END
            if (isAuto) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, ACT_USER_WFSYSTEM);
// 2018/10/24 QC#28914 MOD START
//            } else if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
            } else if (eventCd.equals("A0") || eventCd.equals("A1") || eventCd.equals("A2") || eventCd.equals("A3")) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, ADMIN_OPERATOR_NAME);
                if (map.containsKey("WFACTOPID")) {
                    String actOpId = (String) map.get("WFACTOPID");

                    if (S21StringUtil.isNotEmpty(actOpId)) {
                        if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s (%s)", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId), ADMIN_RIGHTS));
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId)));
                        }
                    }
                }
            } else {
                if (map.containsKey("WFACTOPID")) {
                    String actOpId = (String) map.get("WFACTOPID");

                    if (S21StringUtil.isNotEmpty(actOpId)) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId)));
                        if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s (%s)", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId), ADMIN_RIGHTS));
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId)));
                        }
                    }
                }
            }
// 2018/10/24 QC#28914 MOD END

            // Comment
            if (map.containsKey("WFCMNTTXT")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).wfCmntTxt_B, (String) map.get("WFCMNTTXT"));
            }

            // Attrb1-5
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxScrItem130Txt_B1, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_01"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_01")));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxScrItem130Txt_B2, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_02"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_02")));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxScrItem130Txt_B3, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_03"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_03")));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxScrItem130Txt_B4, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_04"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_04")));
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxScrItem130Txt_B5, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_05"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_05")));

            // From
// 2018/10/24 QC#28914 DEL START
//            String isAdminFU = (String) map.get("FROMUSER_ISADMIN");
// 2018/10/24 QC#28914 DEL END

// 2018/10/24 QC#28914 MOD START
//            if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfAsgFromNm_B, ADMIN_OPERATOR_NAME);
//            } else {
//
//                if (map.containsKey("FROMUSRID")) {
//                    String fromUsrID = (String) map.get("FROMUSRID");
//
//                    if (S21StringUtil.isNotEmpty(fromUsrID)) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfAsgFromNm_B, String.format("%s %s", fromUsrID, NYXC880001.getAssigneeNmFromS21Psn(fromUsrID)));
//                    }
//                }
//            }
            if (map.containsKey("FROMUSRID")) {
                String fromUsrID = (String) map.get("FROMUSRID");
                if (S21StringUtil.isNotEmpty(fromUsrID)) {
                    if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfAsgFromNm_B, String.format("%s %s (%s)", fromUsrID, NYXC880001.getAssigneeNmFromS21Psn(fromUsrID), ADMIN_RIGHTS));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfAsgFromNm_B, String.format("%s %s", fromUsrID, NYXC880001.getAssigneeNmFromS21Psn(fromUsrID)));
                    }
                }
            }
// 2018/10/24 QC#28914 MOD END

            // To
            if (map.containsKey("TOUSRID")) {
                String toUsrId = (String) map.get("TOUSRID");

                if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
// 2018/10/24 QC#28914 MOD START
//                    bizMsg.B.no(cnt).xxWfAsgToNm_B.setValue(ADMIN_OPERATOR_NAME);
                    bizMsg.B.no(cnt).xxWfAsgToNm_B.setValue(String.format("%s %s (%s)", toUsrId, NYXC880001.getAssigneeNmFromS21Psn(toUsrId), ADMIN_RIGHTS));
// 2018/10/24 QC#28914 MOD END
                } else if (S21StringUtil.isNotEmpty(toUsrId)) {
                    bizMsg.B.no(cnt).xxWfAsgToNm_B.setValue(String.format("%s %s", toUsrId, NYXC880001.getAssigneeNmFromS21Psn(toUsrId)));
                }
            }

            // End
            if (map.containsKey("WFENDTS")) {
                String wfEndTs = (String) map.get("WFENDTS");

                if (S21StringUtil.isNotEmpty(wfEndTs)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxDtTm_BT, ZYPDateUtil.formatEzd17ToDisp(wfEndTs));
                }
            }

            cnt++;
        }

        if (bizBMax > cnt) {
            bizMsg.B.setValidCount(cnt);
        } else {
            bizMsg.B.setValidCount(bizBMax);
        }

        return true;

    }
}

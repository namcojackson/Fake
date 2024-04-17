/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8830.common;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_OPERATOR_NAME;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_RIGHTS;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0002E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0003W;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ACT_USER_WFSYSTEM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NYEL8830.NYEL8830CMsg;
import business.blap.NYEL8830.NYEL8830SMsg;

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
import com.canon.cusa.s21.framework.nwf.core.history.impl.S21NwfHistUserAction;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;

/**
 *<pre>
 * NYEL8830CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         C.Yokoi         Create          N/A
 * 2018/09/25   Fujitsu         Q10814          Update
 *</pre>
 */
public class NYEL8830CommonLogic {

    /**
     * search
     * @param bizMsg Business Message
     * @param sMsg Server Message
     */
    public static void search(NYEL8830CMsg bizMsg, NYEL8830SMsg sMsg, String usrId) {
        String uid = usrId;

        if (NYXC880002.isAdministrator(usrId)) {
            uid = "";
        }

        String psts = NYXC880001.toWorklistProcStatusCd(bizMsg.wfProcStsCd.getValue());
        S21SsmEZDResult result = NYXC880002Query.getInstance().getProc(bizMsg.wfProcPk.getValue(), uid, psts);

        if (result.isCodeNotFound()) {
            
            psts = reverseProcSts(psts);
            result = NYXC880002Query.getInstance().getProc(bizMsg.wfProcPk.getValue(), uid, psts);

            if (result.isCodeNotFound()) {
                bizMsg.setMessageInfo(NYEM0002E);
                return;
            }
        }

        if (result.isCodeNormal()) {

            List resultList = (List) result.getResultObject();

            // set Header
            if (!search_Header(bizMsg, resultList)) {
                return;
            }
        }

        result = NYXC880002Query.getInstance().getTask(bizMsg.wfProcPk.getValue(), uid, psts);

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

        result = NYXC880002Query.getInstance().getHistory(bizMsg.wfProcPk.getValue(), uid, psts);

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();

            // set History
            if (!search_History(bizMsg, sMsg, resultList)) {
                return;
            }
        }
    }
    
    /**
     * reverse Process Status
     * @param psts 
     * @return new process status
     */
    private static String reverseProcSts(String psts){
        String ret = psts;

        if (WF_PROC_COND_STS.COMPLETED.equals(psts)){
            ret = WF_PROC_COND_STS.ACTIVE;
        } else {
            ret = WF_PROC_COND_STS.COMPLETED;
        }

        return ret;
    }

    /**
     * search_Header
     * @param bizMsg Business Message
     * @param resultList Process Search Result
     * @return boolean
     */
    private static boolean search_Header(NYEL8830CMsg bizMsg, List resultList) {
        clearHead(bizMsg);

        // Normal
        int max = resultList.size();

        if (max <= 0) {
            return false;
        }

        Map map = (Map) resultList.get(0);

        // ProcessName
        if (map.containsKey("WFPROCNM")) {
            String procNm = (String) map.get("WFPROCNM");

            if (procNm.length() > NYXC880001constant.PROCESS_NM_LENGTH){
                procNm = procNm.substring(0, NYXC880001constant.PROCESS_NM_LENGTH);
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm, procNm);
        }

        // ProcessStatus
        if (map.containsKey("WFPROCSTCD")) {
            String psts = (String) map.get("WFPROCSTCD");

            if (S21StringUtil.isNotEmpty(psts)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWfProcStsNm, NYXC880001.toProcStatusNm(psts));
            }
        }

        // Process Date end
        if (map.containsKey("WFENDTS")) {
            String ended = (String) map.get("WFENDTS");

            if (S21StringUtil.isNotEmpty(ended)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_PE, ZYPDateUtil.formatEzd17ToDisp(ended));
            }
        }

        // Process Description
        if (map.containsKey("WFCMNTTXT")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wfDescTxt_PR, (String) map.get("WFCMNTTXT"));
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
    private static boolean search_Status(NYEL8830CMsg bizMsg, NYEL8830SMsg glblMsg, List resultList) {
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

            // Business Application ID
            if (map.containsKey("WFTOKENBIZPK")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfTokenBizPk_A, (BigDecimal) map.get("WFTOKENBIZPK"));
            }

            // Task Status
            if (map.containsKey("WFWRKITEMSTSCD")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemStsCd_A, (String) map.get("WFWRKITEMSTSCD"));
            }

            // Task ID
            if (map.containsKey("WFWRKITEMID")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemId_A, (String) map.get("WFWRKITEMID"));
            }

            // Task UID
            if (map.containsKey("WFWRKITEMPK")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemPk_A, (BigDecimal) map.get("WFWRKITEMPK"));
            }

            // Task Name
            if (map.containsKey("WFWRKITEMNM")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfWrkItemNm_A, (String) map.get("WFWRKITEMNM"));
            }

            // BtnEnabledFLg
            if (map.containsKey("XXWFAPRCHKVISFLG")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).xxWfAprChkVisFlg_A, (String) map.get("XXWFAPRCHKVISFLG"));
            }

            // Business Application ID
// 2018/05/16 MOD START
//            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).bizAppId_A, (String) map.get("WF_BIZ_SCR_ID"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfBizScrId_A, (String) map.get("WF_BIZ_SCR_ID"));
// 2018/05/16 MOD END

            // Attribute1
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(cnt).wfBizAttrbTxt_A1, (String) map.get("WF_BIZ_ATTRB_TXT_01"));

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
    private static boolean search_History(NYEL8830CMsg bizMsg, NYEL8830SMsg glblMsg, List resultList) {
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

            // Task UID
            if (map.containsKey("WFWRKITEMPK")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).wfWrkItemPk_B, (BigDecimal) map.get("WFWRKITEMPK"));
            }

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

// 2018/09/25 ADD START
            String isAdminFU = (String) map.get("FROMUSER_ISADMIN");
// 2018/09/25 ADD END
            // Operator User
            if (isAuto) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, ACT_USER_WFSYSTEM);
// 2018/09/25 MOD START
//            } else if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
            } else if (eventCd.equals("A0") || eventCd.equals("A1") || eventCd.equals("A2") || eventCd.equals("A3")) {
                //ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, ADMIN_OPERATOR_NAME);
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
                        if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s (%s)", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId), ADMIN_RIGHTS));
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfActOpNm_B, String.format("%s %s", actOpId, NYXC880001.getAssigneeNmFromS21Psn(actOpId)));
                        }
                    }
                }
            }
// 2018/09/25 MOD END

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
// 2018/09/25 DEL START
//            String isAdminFU = (String) map.get("FROMUSER_ISADMIN");
// 2018/09/25 DEL END

// 2018/09/25 MOD START
//            if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfAsgFromNm_B, ADMIN_OPERATOR_NAME);
//            } else {
//
//                if (map.containsKey("FROMUSRID")) {
//                    String fromUsrID = (String) map.get("FROMUSRID");
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
// 2018/09/25 MOD END

            // To
            if (map.containsKey("TOUSRID")) {
                String toUsrId = (String) map.get("TOUSRID");

                if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
// 2018/09/25 MOD START
                    //bizMsg.B.no(cnt).xxWfAsgToNm_B.setValue(ADMIN_OPERATOR_NAME);
                    bizMsg.B.no(cnt).xxWfAsgToNm_B.setValue(String.format("%s %s (%s)", toUsrId, NYXC880001.getAssigneeNmFromS21Psn(toUsrId), ADMIN_RIGHTS));
// 2018/09/25 MOD END
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

            // BtnEnabledFLg
            if (map.containsKey("XXWFAPRCHKVISFLG")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(cnt).xxWfAprChkVisFlg_B, (String) map.get("XXWFAPRCHKVISFLG"));
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

    /**
     * clearHead
     * @param bizMsg Business Msg
     */
    private static void clearHead(NYEL8830CMsg bizMsg) {
        bizMsg.wfProcNm.clear();
        bizMsg.xxWfProcStsNm.clear();
    }
}

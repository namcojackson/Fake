/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8820.common;

import static business.blap.NYEL8810.constant.NYEL8810Constant.ADMIN_GRP;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ACT_USER_WFSYSTEM;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_RIGHTS;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.MESSAGE_KIND_ERROR;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.TEXTAREA_NEWLINE;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0006I;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZMM0004E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenSerializer;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.mail.S21NwfUtilMailTemplate;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem.TYPE;
import com.canon.cusa.s21.framework.nwf.core.model.impl.S21NwfWorkItemImpl;
import com.canon.cusa.s21.framework.nwf.core.notify.S21NwfMailTemplate.MAILTYPE;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;

import business.blap.NYEL8820.NYEL8820CMsg;
import business.blap.NYEL8820.NYEL8820SMsg;

/**
 *<pre>
 * NYEL8820CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 * 2018/06/26   Fujitsu         Q10627          Update          QC#26525
 * 2018/09/25   Fujitsu         Q10814          Update          
 * 2018/11/19   Fujitsu         Q10627          Update          QC#29337
 *</pre>
 */
public class NYEL8820CommonLogic {

    /**
     * Template Default ID
     */
    private static final String NWFDEFAULT = "NWFDEFAULT";

    /**
     * next
     * @param bizMsg
     * @return
     */
    public static boolean next(NYEL8820CMsg bizMsg){
        boolean flg = false;
        int index = bizMsg.xxCellIdx.getValue().intValue();
        int max =  bizMsg.P.getValidCount();
        index++;

        if (index < max){
            bizMsg.xxCellIdx.setValue(index);
            flg = true;
        }

        return flg;
    }

    /**
     * back
     * @param bizMsg
     * @return
     */
    public static boolean back(NYEL8820CMsg bizMsg){
        boolean flg = false;
        int index = bizMsg.xxCellIdx.getValue().intValue();
        index--;

        if (index >= 0){
            bizMsg.xxCellIdx.setValue(index);
            flg = true;
        }
        
        return flg;
    }

 // 2018/11/19 ADD START QC#29337
    /**
     * @param bizMsg
     * @param glblMsg
     * @param glblCmpyCd
     * @param usrId
     */
    public static void search(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg, String glblCmpyCd, String usrId) {
        search(bizMsg, glblMsg, glblCmpyCd, usrId, null);
    }
 // 2018/11/19 ADD END  QC#29337

    /**
     * @param bizMsg
     * @param glblMsg
     * @param glblCmpyCd
     * @param process
     */
// 2018/11/19 UPD START QC#29337
//     public static void search(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg, String glblCmpyCd, String usrId) {
     public static void search(NYEL8820CMsg bizMsg, NYEL8820SMsg glblMsg, String glblCmpyCd, String usrId, S21NwfProcess process) {
// 2018/11/19 UPD END   QC#29337
        int index = bizMsg.xxCellIdx.getValue().intValue();

        BigDecimal procID = bizMsg.P.no(index).wfProcPk_P.getValue();
        BigDecimal taskID = bizMsg.P.no(index).wfWrkItemPk_P.getValue();
        String procSts = NYXC880001.toWorklistProcStatusCd(bizMsg.P.no(index).wfProcStsCd_P.getValue());

        S21SsmEZDResult result = NYXC880002Query.getInstance().getDetail(procID, taskID, usrId, procSts);

        if (result.isCodeNotFound()) {
            procSts = reverseProcSts(procSts);
            result = NYXC880002Query.getInstance().getDetail(procID, taskID, usrId, procSts);

            if (result.isCodeNotFound()) {
                bizMsg.setMessageInfo(NYEM0006I);
            }
        }

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();
            Map map = (Map) resultList.get(0);

            // header
// 2018/11/19 UPD START QC#29337
//            getHdrData(bizMsg, glblCmpyCd, map, usrId);
            getHdrData(bizMsg, glblCmpyCd, map, usrId, process);
// 2018/11/19 UPD END   QC#29337

            if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
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

// 2018/11/19 UPD START QC#29337
//    private static void getHdrData(NYEL8820CMsg bizMsg, String glblCmpyCd, Map map, String usrId) {
    private static void getHdrData(NYEL8820CMsg bizMsg, String glblCmpyCd, Map map, String usrId, S21NwfProcess process) {
// 2018/11/19 UPD END   QC#29337
        BigDecimal procID = BigDecimal.ZERO;
        BigDecimal taskID = (BigDecimal) map.get("WFWRKITEMPK");

        // Process ID
        if (map.containsKey("WFPROCPK")) {
            procID = (BigDecimal) map.get("WFPROCPK");
            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcPk, procID);
        }

        // Process Name
        if (map.containsKey("WFPROCNM")) {
            String procNm = (String) map.get("WFPROCNM");

            if (procNm.length() > NYXC880001constant.PROCESS_NM_LENGTH) {
                procNm = procNm.substring(0, NYXC880001constant.PROCESS_NM_LENGTH);
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm, procNm);
        }

        // Process Name(for mail template)
        if (map.containsKey("WFPROCNMTMPL")) {
            String procNm = (String) map.get("WFPROCNMTMPL");

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm_T, procNm);
        }

        // Process Status
        if (map.containsKey("WFPROCSTSCD")) {
            String pStst = (String) map.get("WFPROCSTSCD");

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcStsCd, NYXC880001.toWorklistProcStatusCd(pStst));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfProcStsNm, NYXC880001.toProcStatusNm(pStst));
        }

        // Task Name
        if (map.containsKey("WFWRKITEMNM")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, (String) map.get("WFWRKITEMNM"));
        }

        // Task Description
        if (map.containsKey("WFCMNTTXT")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wfCmntTxt_T, (String) map.get("WFCMNTTXT"));
        }

        BigDecimal wfTokenBizPk = (BigDecimal) map.get("WFTOKENBIZPK");
        ZYPEZDItemValueSetter.setValue(bizMsg.wfTokenBizPk, wfTokenBizPk);
        S21NwfTokenSerializer ser = new S21NwfTokenSerializer();
        S21NwfTokenObj tokenBiz;

        // Attribute1-5
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_01, (String) map.get("WF_BIZ_ATTRB_TXT_01"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_02, (String) map.get("WF_BIZ_ATTRB_TXT_02"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_03, (String) map.get("WF_BIZ_ATTRB_TXT_03"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_04, (String) map.get("WF_BIZ_ATTRB_TXT_04"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_05, (String) map.get("WF_BIZ_ATTRB_TXT_05"));

        // AttrbLbl1-5
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_01, (String) map.get("WF_BIZ_ATTRB_LB_TXT_01"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_02, (String) map.get("WF_BIZ_ATTRB_LB_TXT_02"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_03, (String) map.get("WF_BIZ_ATTRB_LB_TXT_03"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_04, (String) map.get("WF_BIZ_ATTRB_LB_TXT_04"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_05, (String) map.get("WF_BIZ_ATTRB_LB_TXT_05"));

        // Business Application ID
// 2018/05/16 MOD START
//        ZYPEZDItemValueSetter.setValue(bizMsg.bizAppId, (String) map.get("WF_BIZ_SCR_ID"));
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizScrId, (String) map.get("WF_BIZ_SCR_ID"));
// 2018/05/16 MOD END

        try {
            tokenBiz = ser.findByKey(wfTokenBizPk, WF_PROC_COND_STS.COMPLETED.equals(bizMsg.wfProcStsCd.getValue()));

            if (tokenBiz != null) {
                // Biz Data
                getBizDat(bizMsg, glblCmpyCd, tokenBiz, (String) map.get("MLTMPLID"));
            }
        } catch (S21NwfSystemException e) {
            e.printStackTrace();

            EZDMessageInfo info = e.getMessageInfo();

            if (info != null) {
                bizMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                bizMsg.setMessageInfo(ZZMM0004E, new String[] {wfTokenBizPk.toPlainString() });
            }
        }

        String isAdminFU = (String) map.get("FROMUSER_ISADMIN");

        // From
// 2018/09/25 MOD START
//        if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgFromNm, ADMIN_OPERATOR_NAME);
//        } else {
//
//            if (map.containsKey("FROMUSRID")) {
//                String fromGrpNm = (String) map.get("WFUSRGRPNM_F");
//                String fromUsrId = (String) map.get("FROMUSRID");
//
//                if (S21StringUtil.isNotEmpty(fromUsrId)) {
//                    if (S21StringUtil.isNotEmpty(fromGrpNm)){
//                        ZYPEZDItemValueSetter.setValue(//
//                                bizMsg.xxWfAsgFromNm //
//                                , fromGrpNm);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(//
//                                bizMsg.xxWfAsgFromNm //
//                                , S21StringUtil.concatStrings(fromUsrId, " ", NYXC880001.getAssigneeNmFromS21Psn(fromUsrId)));
//                    }
//                }
//            }
//        }
        if (map.containsKey("FROMUSRID")) {
            String fromGrpNm = (String) map.get("WFUSRGRPNM_F");
            String fromUsrId = (String) map.get("FROMUSRID");

            if (S21StringUtil.isNotEmpty(fromUsrId)) {
                if (S21StringUtil.isNotEmpty(fromGrpNm)){
                    ZYPEZDItemValueSetter.setValue(//
                            bizMsg.xxWfAsgFromNm //
                            , S21StringUtil.concatStrings(fromUsrId, " ", NYXC880001.getAssigneeNmFromS21Psn(fromUsrId)));
                } else {
                    if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
                        ZYPEZDItemValueSetter.setValue(//
                                bizMsg.xxWfAsgFromNm //
                                , S21StringUtil.concatStrings(fromUsrId, " ", NYXC880001.getAssigneeNmFromS21Psn(fromUsrId), " (", ADMIN_RIGHTS, ")"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(//
                                bizMsg.xxWfAsgFromNm //
                                , S21StringUtil.concatStrings(fromUsrId, " ", NYXC880001.getAssigneeNmFromS21Psn(fromUsrId)));
                    }
                }
            }
        }
// 2018/09/25 MOD END

        // To User
        S21SsmEZDResult userList = null;

        // To User
        userList = NYXC880002Query.getInstance().getToUsers(taskID.toPlainString(), usrId, bizMsg.wfProcStsCd.getValue());

        boolean isSetUserNm = false;
        boolean hasAdminUser = false;

        if (userList.isCodeNormal()) {
            // Normal
            List userltList = (List) userList.getResultObject();
            int maxSize = userltList.size();
            //String toGrpNm = "";
            //String toUserID = "";
            String toUserNm = "";
            String isAdmin = "";
            String isGroup = "";
            StringBuilder sb = new StringBuilder();
            Map<String, String> grpMap = new HashMap<String,String>();

            for (int i = 0; i < maxSize; i++) {
                Map userMap = (Map) userltList.get(i);
                toUserNm = (String) userMap.get("WFUSRNM");
                isAdmin = (String) userMap.get("ISADMIN");
                isGroup = (String) userMap.get("ISGROUP");
// 2018/09/25 ADD START
                hasAdminUser = false;
// 2018/09/25 ADD END

                if (ZYPConstant.FLG_ON_1.equals(isGroup)) {
                    if (ADMIN_GRP.equals(toUserNm)) {
                        hasAdminUser = true;
                        continue;
                    }
                } else  if (ZYPConstant.FLG_ON_1.equals(isAdmin) || ADMIN_GRP.equals(toUserNm)) {
                    hasAdminUser = true;
// 2018/09/25 DEL START
//                    continue;
// 2018/09/25 DEL END
                }

                if (grpMap.containsKey(toUserNm)){
                    continue;
                }

                if (S21StringUtil.isEmpty(toUserNm)) {
                    toUserNm = "";
                }

                if (toUserNm != null) {
                    if (sb.length() > 0) {
                        sb.append(TEXTAREA_NEWLINE);
                    }

                    if (S21StringUtil.isNotEmpty(toUserNm)){
                        sb.append(toUserNm);
// 2018/09/25 ADD START
                        if (hasAdminUser && ZYPConstant.FLG_OFF_0.equals(isGroup)) {
                            sb.append(" (").append(ADMIN_RIGHTS).append(")");
                        }
// 2018/09/25 ADD END
                        grpMap.put(toUserNm, toUserNm);
                        isSetUserNm = true;
                    }
                }
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, sb.toString());
        }

        if (hasAdminUser && (isSetUserNm == false)){
// 2018/09/25 MOD START
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, ADMIN_OPERATOR_NAME);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, ADMIN_RIGHTS);
// 2018/09/25 MOD END
        }

        S21SsmEZDResult resultLv = NYXC880002Query.getInstance().getLeaveLog(procID, taskID, bizMsg.wfProcStsCd.getValue());
        String eventType = "";

        if (resultLv.isCodeNormal()) {
            List resultList = (List) resultLv.getResultObject();
            Map mapLv = (Map) resultList.get(0);

            // Task End
            if (mapLv.containsKey("WFENDTS")) {
                String wfEndTs = (String) mapLv.get("WFENDTS");

                if (S21StringUtil.isNotEmpty(wfEndTs)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm_T, ZYPDateUtil.formatEzd17ToDisp(wfEndTs));
                }
            }

            String isAdmin = (String) mapLv.get("ISADMIN");
            boolean isAuto = false;

            if (mapLv.containsKey("WFEVENTAUTOFLG")) {
                String autoflg = (String) mapLv.get("WFEVENTAUTOFLG");

                if (S21StringUtil.isNotEmpty(autoflg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(autoflg)) {
                        isAuto = true;
                    }
                }
            }

            String toUserId = (String) mapLv.get("TOUSRID");

            if (S21StringUtil.isEmpty(toUserId)) {
                toUserId = "";
            }

// 2018/09/25 ADD START
            String toUserNm = (String) mapLv.get("TOUSRNM");

            if (S21StringUtil.isEmpty(toUserNm)) {
                toUserNm = "";
            }
// 2018/09/25 ADD END

            // actOp
            if (isAuto) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWfActOpNm, ACT_USER_WFSYSTEM);

                if (isSetUserNm == false) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, ACT_USER_WFSYSTEM);
                }
            } else if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
// 2018/09/25 MOD START
//                ZYPEZDItemValueSetter.setValue(bizMsg.xxWfActOpNm, ADMIN_OPERATOR_NAME);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWfActOpNm, String.format("%s %s (%s)", toUserId, toUserNm, ADMIN_RIGHTS));
// 2018/09/25 MOD END

                if (isSetUserNm == false) {
// 2018/09/25 MOD START
//                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, ADMIN_OPERATOR_NAME);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, String.format("%s %s (%s)", toUserId, toUserNm, ADMIN_RIGHTS));
// 2018/09/25 MOD END
                }
            } else {
// 2018/09/25 DEL START
//                String toUserNm = (String) mapLv.get("TOUSRNM");
//
//                if (S21StringUtil.isEmpty(toUserNm)) {
//                    toUserNm = "";
//                }
// 2018/09/25 DEL END

                if (S21StringUtil.isNotEmpty(toUserId)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxWfActOpNm, String.format("%s %s", toUserId, toUserNm));

                    if (isSetUserNm == false) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm, String.format("%s %s", toUserId, toUserNm));
                    }
                }
            }

            // Action
            if (mapLv.containsKey("WFEVENTTPCD")) {
                eventType = (String) mapLv.get("WFEVENTTPCD");
                String actNm = NYXC880001.toActNm(eventType);

                if (S21StringUtil.isNotEmpty(actNm)) {
                    if (isAuto) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.actWfCondNm, "AUTO " + actNm);
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.actWfCondNm, actNm);
                    }
                }
            }

            // Comment
            if (mapLv.containsKey("WFCMNTTXT")) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wfCmntTxt, (String) mapLv.get("WFCMNTTXT"));
            }
        }

        // Task Status
        String tSts = "";
        if (map.containsKey("WFWRKITEMSTSCD")) {
            tSts = (String) map.get("WFWRKITEMSTSCD");

            ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemStsCd, tSts);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfWrkItemStsNm, NYXC880001.toTaskStatusNm(tSts, eventType));
        }

// 2018/06/26 ADD START QC#26525
// 2018/11/19 UPD START QC#29337
//        NYEL8820CommonLogic.setNextApprovers(bizMsg, usrId);
        NYEL8820CommonLogic.setNextApprovers(bizMsg, usrId, process);
// 2018/11/19 UPD END   QC#29337
// 2018/06/26 ADD END QC#26525

    }

    /**
     * business data
     * @param bizMsg
     * @param glblCmpyCd
     * @param tokenObj
     * @param templateId
     */
    private static void getBizDat(NYEL8820CMsg bizMsg, String glblCmpyCd, S21NwfTokenObj tokenObj, String templateId) {

        // templateId
        if (S21StringUtil.isEmpty(templateId)) {
            templateId = bizMsg.wfProcNm_T.getValue();
        }

        S21NwfUtilMailTemplate template = createTemplate(bizMsg, glblCmpyCd, tokenObj, templateId);
        String body = template.getBody();

        //
        body = body.replace("\"", "'");

        // Detail
        if (S21StringUtil.isNotEmpty(body)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfBizDtlTxt, body);
        } else {
            template = createTemplate(bizMsg, glblCmpyCd, tokenObj, NWFDEFAULT);
            body = template.getBody();
            body = body.replace("\"", "'");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfBizDtlTxt, body);
        }

        // Subject
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfBizSubjTxt, template.getSubject());

    }

    private static S21NwfUtilMailTemplate createTemplate(NYEL8820CMsg bizMsg, String glblCmpyCd, S21NwfTokenObj tokenObj, String templateId) {
        S21NwfUtilMailTemplate template = new S21NwfUtilMailTemplate(glblCmpyCd, templateId);

        // Set Token Object Values
        template.setTemplateParameter(tokenObj);

        // Mail Type
        template.setMailType(MAILTYPE.SCREEN);

        // Set Process UID
        template.setHistoryTagKey(bizMsg.P.no(bizMsg.xxCellIdx.getValue().intValue()).wfProcPk_P.getValue());

        return template;

    }

// 2018/06/26 ADD START QC#26525

    /**
     * Set Next Approvers
     * @param bizMsg
     * @param userId
     * @param process
     */
// 2018/11/19 UPD START QC#29337
//     private static void setNextApprovers(NYEL8820CMsg bizMsg, String userId) {
     private static void setNextApprovers(NYEL8820CMsg bizMsg, String userId, S21NwfProcess process) {
         bizMsg.xxWfAsgToNm_NX.clear();
// 2018/11/19 UPD END   QC#29337

         if (process == null) {
// 2018/11/19 UPD END   QC#29337

             // Get Process
             S21NwfUtilContextFactory factory = new S21NwfUtilContextFactory();
// 2018/11/19 DEL START QC#29337
//             S21NwfProcess process = null;
// 2018/11/19 DEL END   QC#29337
             try {
                 S21NwfContext context = factory.getContex();
                 context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());

                 process = context.getProcess(bizMsg.wfProcPk.getValue());
             } catch (S21NwfSystemException e1) {
                 EZDMessageInfo info = e1.getMessageInfo();

                 if (info != null) {
                     bizMsg.setMessageInfo(info.getCode(), info.getParameter());
                 } else {
                     e1.printStackTrace();
                 }
             }

             // Error
             if (process == null) {
                 return;
             }

// 2018/11/19 ADD START QC#29337
         }
// 2018/11/19 ADD END   QC#29337


        int index = bizMsg.xxCellIdx.getValue().intValue();
        BigDecimal taskID = bizMsg.P.no(index).wfWrkItemPk_P.getValue();

        // Get Specified Task
        S21SsmEZDResult userList = null;
        S21NwfWorkItemImpl wi = process.findWorkItem(taskID);
        try {
            // Get Next Approval Task
            while (wi != null) {
// 2018/11/19 ADD START QC#29337
                if ("End".equalsIgnoreCase(wi.getWorkItemName())) {
                    break;
                }
// 2018/11/19 ADD END   QC#29337
                wi = wi.next();
                if (wi.getType() == TYPE.TASK) {
                    break;
                }
            }

            if (wi != null) {
                String procCndStsCd = WF_PROC_COND_STS.ACTIVE;
                if (process.getStatus()== S21NwfProcess.STATUS.CLOSE ||
                    process.getStatus()== S21NwfProcess.STATUS.CANCEL ||
                    process.getStatus()== S21NwfProcess.STATUS.ABORT) {
                    procCndStsCd = WF_PROC_COND_STS.COMPLETED;
                }

                // Get Approvers
                userList = NYXC880002Query.getInstance().getToUsers(wi.getWorkItemUId(), userId, procCndStsCd);
            }
        } catch (S21NwfSystemException e) {
            e.printStackTrace();
        }

        boolean isSetUserNm = false;
        boolean hasAdminUser = false;

        if (userList != null && userList.isCodeNormal()) {
            List userltList = (List) userList.getResultObject();
            int maxSize = userltList.size();

            String toUserNm = "";
            String isAdmin = "";
            String isGroup = "";
            StringBuffer sb = new StringBuffer();
            Map<String, String> grpMap = new HashMap<String,String>();
            for (int i = 0; i < maxSize; i++) {
                Map userMap = (Map) userltList.get(i);
                toUserNm = (String) userMap.get("WFUSRNM");
                isAdmin = (String) userMap.get("ISADMIN");
                isGroup = (String) userMap.get("ISGROUP");
// 2018/09/25 ADD START
                hasAdminUser = false;
// 2018/09/25 ADD END

                if (ZYPConstant.FLG_ON_1.equals(isGroup)) {
                    if (ADMIN_GRP.equals(toUserNm)) {
                        hasAdminUser = true;
                        continue;
                    }
                } else  if (ZYPConstant.FLG_ON_1.equals(isAdmin) || ADMIN_GRP.equals(toUserNm)) {
                    hasAdminUser = true;
// 2018/09/25 DEL START
//                    continue;
// 2018/09/25 DEL END
                }

                if (grpMap.containsKey(toUserNm)){
                    continue;
                }

                if (S21StringUtil.isEmpty(toUserNm)) {
                    toUserNm = "";
                }

                if (toUserNm != null) {
                    if (sb.length() > 0) {
                        sb.append(TEXTAREA_NEWLINE);
                    }

                    if (S21StringUtil.isNotEmpty(toUserNm)){
                        sb.append(toUserNm);
// 2018/09/25 ADD START
                        if (hasAdminUser && ZYPConstant.FLG_OFF_0.equals(isGroup)) {
                            sb.append(" (").append(ADMIN_RIGHTS).append(")");
                        }
// 2018/09/25 ADD END
                        grpMap.put(toUserNm, toUserNm);
                        isSetUserNm = true;
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm_NX, sb.toString());
        }

        if (hasAdminUser && (isSetUserNm == false)){
// 2018/09/25 MOD START
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm_NX, ADMIN_OPERATOR_NAME);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAsgToNm_NX, ADMIN_RIGHTS);
// 2018/09/25 MOD END
        }
    }
// 2018/06/26 ADD END QC#26525
}

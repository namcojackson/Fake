/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8810.common;

import static business.blap.NYEL8810.constant.NYEL8810Constant.PSBL_ACT_APPROVE;
import static business.blap.NYEL8810.constant.NYEL8810Constant.PSBL_ACT_CANCEL;
import static business.blap.NYEL8810.constant.NYEL8810Constant.PSBL_ACT_FYI;
import static business.blap.NYEL8810.constant.NYEL8810Constant.MESSAGE_KIND_ERROR;
import static business.blap.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;
import static business.blap.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import static business.blap.NYEL8810.constant.NYEL8810Constant.ADMIN_GRP;
import static business.blap.NYEL8810.constant.NYEL8810Constant.MAX_CMNT_L;
import static business.blap.NYEL8810.constant.NYEL8810Constant.TEXTAREA_OMIT;
import static business.blap.NYEL8810.constant.NYEL8810Constant.TASK_ID_SUBMISSION;
import static business.blap.NYEL8810.constant.NYEL8810Constant.TASK_ID_APPROVAL;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ACT_USER_WFSYSTEM;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_OPERATOR_NAME;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ADMIN_RIGHTS;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0002E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0003W;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0016E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZSM4199E;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.PROC_ID_ALL;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.TASK_ID_ALL;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.TEXTAREA_NEWLINE;
//import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZSM4110E;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NYEL8810.NYEL8810CMsg;
import business.blap.NYEL8810.NYEL8810SMsg;
import business.blap.NYEL8810.constant.NYEL8810Constant;
import business.file.NYEL8810F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_GRP_TP;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.nwf.core.common.util.S21NwfDateUtil;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfNotifyEvent;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.notify.S21NwfNotifyAction;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;

/**
 *<pre>
 * NYEL8810CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 * 2017/11/01   Fujitsu         Q10814          Update          QC#21078
 * 2018/02/01   Fujitsu         Q10814          Update          
 * 2018/02/06   Fujitsu         Q10814          Update          QC#22692
 * 2018/02/09   Fujitsu         Q10814          Update          
 * 2018/02/27   Fujitsu         Q10627          Update          QC#21934 
 * 2018/03/20   Fujitsu         Q10627          Update          QC#24931
 * 2018/04/17   Fujitsu         Q10814          Update          
 * 2018/05/07   Fujitsu         Q10814          Update          QC#23516
 * 2018/08/23   Fujitsu         Q10814          Update          QC#21387
 * 2018/08/24   Fujitsu         Q10814          Update          
 * 2018/09/25   Fujitsu         Q10814          Update          
 * 2018/10/10   Fujitsu         Q10627          Update          QC#28705
 * 2018/12/18   Fujitsu         Q10627          Update          QC#29682
 *</pre>
 */
public class NYEL8810CommonLogic {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
    //private static final S21Logger logger = S21LoggerFactory.getLogger(NYEL8810CommonLogic.class);
    
    public static String getUserNmFromS21Psn(NYEL8810CMsg bizMsg, String subOrdinateId, String userId) {
        String ret = "";

        if (userId.equals(subOrdinateId)) {
            ret = NYXC880001.getAssigneeNmFromS21Psn(userId);
        } else {
            S21SsmEZDResult result = NYXC880002Query.getInstance().getSubOrdinate(subOrdinateId, userId);

            if (result.isCodeNormal()) {
                List resultList = (List) result.getResultObject();
                if (resultList.size() > 0) {
                    Map map = (Map) resultList.get(0);
                    ret = (String) map.get("FULL_PSN_NM");
                }
            }
        }

        if (S21StringUtil.isEmpty(ret)) {
            // QC#23516 MOD START 2018/05/07
//            bizMsg.usrId.setErrorInfo(1, ZZSM4110E, new String[] {"User Name" });
            bizMsg.usrId.setErrorInfo(1, NYEM0016E, new String[] {"User Name" });
            // QC#23516 MOD END 2018/05/07
            return "";
        }

        return ret;
    }

    public static String getUserNmFromS21PsnForAdmin(NYEL8810CMsg bizMsg, String userId) {
        String ret = NYXC880001.getAssigneeNmFromS21Psn(userId);

        if (S21StringUtil.isEmpty(ret)) {
            // QC#23516 MOD START 2018/05/07
//            bizMsg.usrId.setErrorInfo(1, ZZSM4110E, new String[] {"User Name" });
            bizMsg.usrId.setErrorInfo(1, NYEM0016E, new String[] {"User Name" });
            // QC#23516 MOD END 2018/05/07
            return "";
        }

        return ret;
    }

    public static String getAssigneeNmFromS21Psn(NYEL8810CMsg bizMsg, String userId) {
        String result = NYXC880001.getAssigneeNmFromS21Psn(userId);

        if ((result == null) || (result.length() <= 0)) {
            // QC#23516 MOD START 2018/05/07
//            bizMsg.xxWfAsgCd.setErrorInfo(1, ZZSM4110E, new String[] {"Assignee" });
            bizMsg.xxWfAsgCd.setErrorInfo(1, NYEM0016E, new String[] {"Assignee" });
            // QC#23516 MOD END 2018/05/07
            return "";
        }

        return result;
    }

    public static void search(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg, String glblCmpyCd, String loginUser) {
        search(bizMsg, sMsg, glblCmpyCd, loginUser, true);
    }
    
    /**
     * search
     * @param bizMsg
     * @param loginUser
     */
    public static void search(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg, String glblCmpyCd, String loginUser, boolean ignoreNotFound) {

        String usrId = bizMsg.usrId.getValue();
        String usrIdForDisp = usrId;
        String usrNm = "";

        // Search Parameter
        // QC#21387 MOD START 2018/08/23
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//
//        boolean isAdmin = NYXC880002.isAdministrator(loginUser);
//
//        if (isAdmin) {
//            if (loginUser.equals(usrId)) {
//                //usrId = NYXC880001constant.ADMIN_OPERATOR_NAME;
//            } else {
//                params.put("OtherUser", ZYPConstant.FLG_ON_1);
//            }
//        } else {
//            if (!loginUser.equals(usrId)) {
//                usrNm = getUserNmFromS21Psn(bizMsg, usrId, loginUser);
//
//                if (S21StringUtil.isEmpty(usrNm)) {
//                    return;
//                }
//            }
//        }
//
//        if (S21StringUtil.isEmpty(usrNm)) {
//            usrNm = getUserNmFromS21PsnForAdmin(bizMsg, usrIdForDisp);
//        }
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.usrNm, usrNm);
//
//        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfAprChkFlg.getValue())) {
//            params.put("xxWfAprChkFlg", bizMsg.xxWfAprChkFlg.getValue());
//        }
//
//        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfFyiChkFlg.getValue())) {
//            params.put("xxWfFyiChkFlg", bizMsg.xxWfFyiChkFlg.getValue());
//        }
//
//        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfConfFlg.getValue())) {
//            params.put("xxWfConfFlg", bizMsg.xxWfConfFlg.getValue());
//        }
//
//        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfSubChkFlg.getValue())) {
//            params.put("xxWfSubChkFlg", bizMsg.xxWfSubChkFlg.getValue());
//        }
//
//        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfVisChkFlg.getValue())) {
//            params.put("xxWfVisChkFlg", bizMsg.xxWfVisChkFlg.getValue());
//        }
//
//        if (S21StringUtil.isNotEmpty(usrId)) {
//            params.put("usrId", usrId);
//        }
//
//        if (!bizMsg.wfProcPk.isClear()){
//            params.put("wfProcPk", bizMsg.wfProcPk.getValue());
//        }
//
//        if (WF_PROC_COND_STS.ALL.equals(bizMsg.procStsCd.getValue())) {
//            //params.put("PROC_STS_ALL", bizMsg.procStsCd.getValue());
//            params.put("PROC_STS_ACTIVE", bizMsg.procStsCd.getValue());
//            params.put("PROC_STS_COMPLETE", bizMsg.procStsCd.getValue());
//        } else if (WF_PROC_COND_STS.ACTIVE.equals(bizMsg.procStsCd.getValue())) {
//            params.put("PROC_STS_ACTIVE", bizMsg.procStsCd.getValue());
//        } else if (WF_PROC_COND_STS.COMPLETED.equals(bizMsg.procStsCd.getValue())) {
//            params.put("PROC_STS_COMPLETE", bizMsg.procStsCd.getValue());
//        }
//
//        if (!PROC_ID_ALL.equals(bizMsg.wfProcNm.getValue())) {
//            params.put("wfProcNm", bizMsg.wfProcNm.getValue());
//        }
//
//        if (!TASK_ID_ALL.equals(bizMsg.wfWrkItemNm.getValue())) {
//            params.put("wfWrkItemNm", bizMsg.wfWrkItemNm.getValue() + "%");
//        }
//
//        if (S21StringUtil.isNotEmpty(bizMsg.wfProcDocId.getValue())){
//            params.put("wfProcDocId", bizMsg.wfProcDocId.getValue());
//        }
//
//        if (S21StringUtil.isNotEmpty(bizMsg.wfBizAttrbTxt.getValue())) {
//            params.put("wfBizAttrbTxt", "%" + bizMsg.wfBizAttrbTxt.getValue() + "%");
//        }
//
//        //Start Date Start
//        if (S21StringUtil.isNotEmpty(bizMsg.xxFromDt_SS.getValue())){
//            params.put("xxFromDt_SS", bizMsg.xxFromDt_SS.getValue() + NYEL8810Constant.MIN_HHMMssSSSS);
//        }
//
//        //Start Date End
//        if (S21StringUtil.isNotEmpty(bizMsg.xxThruDt_SE.getValue())){
//            params.put("xxThruDt_SE", bizMsg.xxThruDt_SE.getValue() + NYEL8810Constant.MAX_HHMMssSSSS);
//        } else {
//            //params.put("xxThruDt_SE", ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//        }
//
//        //Due Date Start
//        if (S21StringUtil.isNotEmpty(bizMsg.xxFromDt_DS.getValue())){
//            params.put("xxFromDt_DS", bizMsg.xxFromDt_DS.getValue() + NYEL8810Constant.MIN_HHMMssSSSS);
//        }
//
//        //Due Date End
//        if (S21StringUtil.isNotEmpty(bizMsg.xxThruDt_DE.getValue())){
//            params.put("xxThruDt_DE", bizMsg.xxThruDt_DE.getValue() + NYEL8810Constant.MAX_HHMMssSSSS);
//        }
//
//        //From
//        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm_F.getValue())){
//            if ("0".equals(bizMsg.xxGrpFlg_F.getValue())){
//                //ALL
//                params.put("xxGrpFlg_F1", bizMsg.wfUsrGrpNm_F.getValue());
//                
//            } else if ("1".equals(bizMsg.xxGrpFlg_F.getValue())){
//                //GROUP
//                params.put("xxGrpFlg_F2", bizMsg.wfUsrGrpNm_F.getValue());
//                
//            } else if ("2".equals(bizMsg.xxGrpFlg_F.getValue())){
//                //USER
//                params.put("xxGrpFlg_F3", bizMsg.wfUsrGrpNm_F.getValue());
//            }
//        }
//
//        //To
//        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm_T.getValue())){
//            if ("0".equals(bizMsg.xxGrpFlg_T.getValue())){
//                //ALL
//                params.put("xxGrpFlg_T", true);
//                params.put("xxGrpFlg_T1", bizMsg.wfUsrGrpNm_T.getValue());
//            } else if ("1".equals(bizMsg.xxGrpFlg_T.getValue())){
//                //GROUP
//                params.put("xxGrpFlg_T", true);
//                params.put("xxGrpFlg_T2", bizMsg.wfUsrGrpNm_T.getValue());
//            } else if ("2".equals(bizMsg.xxGrpFlg_T.getValue())){
//                //USER
//                params.put("xxGrpFlg_T", true);
//                params.put("xxGrpFlg_T3", bizMsg.wfUsrGrpNm_T.getValue());
//            }
//        }
//
//        if (isAdmin){
//            params.put("Administrator", ZYPConstant.FLG_ON_1);
//        }
//
//        params.put("wfNtfyEventTpCdList", parameterJoin(new String[]{
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_2.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_3.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_4.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_5.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_6.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_7.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_8.getCode(),
//                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_9.getCode()}));
//        params.put("wfNtfyActTpCd", S21NwfNotifyAction.NTF_ACT_TYPE.FYI.getCode());
        Map<String, Object> params = getParams(bizMsg, glblCmpyCd, loginUser);
        // QC#21387 MOD END 2018/08/23

        // QC#28705 ADD START 2018/10/10
        if (params == null) {
            return;
        }
        // QC#28705 ADD END   2018/10/10

        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "getWorklist"));
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        S21SsmEZDResult result = null;

        result = NYXC880002Query.getInstance().getWorklist(params, sMsg.A.length() + 1);
        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "getWorklist", sw.getElapsedMilliSec()));

        // No Data
        if (result.isCodeNormal()) {

            // 2018/02/06 ADD START
            List<Integer> checkedList1 = new ArrayList<Integer>();
            List<Integer> checkedList2 = new ArrayList<Integer>();
            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxWfAprChkFlg_A", ZYPConstant.CHKBOX_ON_Y);
            for (int i: selectedRows) {
                checkedList1.add(bizMsg.A.no(i).wfProcPk_A.getValueInt());
                checkedList2.add(bizMsg.A.no(i).wfWrkItemPk_A.getValueInt());
            }
            // 2018/02/06 ADD END
            toMsg(bizMsg, sMsg, result, usrId, loginUser);

            if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                // 2018/02/06 ADD START
                int pagenation = 0;
                if (!checkedList1.isEmpty()) {
                    for ( int i=0; i < sMsg.A.getValidCount(); i++ ) {
                        for (int j=0; j < checkedList1.size(); j++) {
                            if ((checkedList1.get(j) == sMsg.A.no(i).wfProcPk_A.getValueInt())
                                    && (checkedList2.get(j) == sMsg.A.no(i).wfWrkItemPk_A.getValueInt())) {
                                pagenation = i/bizMsg.A.length();
                                break;
                            }
                        }
                    }
                }
                // 2018/02/06 ADD END
                S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "CopycMsg2sMsg"));
                sw = new S21StopWatch();
                sw.start();

                // 2018/02/06 MOD START
                // set value to pagenation items
                int pagenationFrom = pagenation * bizMsg.A.length();
                int i = pagenationFrom;
                for (; i < pagenationFrom + bizMsg.A.length(); i++) {
                    if (i < sMsg.A.getValidCount()) {
                        EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
                        for (int j=0; j < checkedList1.size(); j++) {
                            if ((checkedList1.get(j) == sMsg.A.no(i).wfProcPk_A.getValueInt())
                                    && (checkedList2.get(j) == sMsg.A.no(i).wfWrkItemPk_A.getValueInt())) {
                                bizMsg.A.no(i - pagenationFrom).xxWfAprChkFlg_A.setValue(ZYPConstant.CHKBOX_ON_Y);
                            }
                        }
                    } else {
                        break;
                    }
                }
                bizMsg.A.setValidCount(i - pagenationFrom);
                bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
                bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
                bizMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
//              int i = 0;
//                for( ; i < sMsg.A.getValidCount(); i++ ) {
//                    if( i == bizMsg.A.length() ) {
//                        break;
//                    }
//                    EZDMsg.copy( sMsg.A.no( i ), null, bizMsg.A.no( i ), null );
//                }
//                bizMsg.A.setValidCount( i );
//
//                bizMsg.xxPageShowFromNum.setValue( 1 );
//                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
//                bizMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
                // 2018/02/06 MOD END

                sw.stop();
                S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "CopycMsg2sMsg", sw.getElapsedMilliSec()));
            }
        } else {
            bizMsg.A.clear();
            bizMsg.A.setValidCount(0);
            if (ignoreNotFound == false){
                bizMsg.setMessageInfo( "NZZM0000E" );
            }
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }

        //
        // All Select
        //
        // Approve
        bizMsg.xxWfAprAllChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        // FYI
        bizMsg.xxWfFyiAllChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        // Reassign
        bizMsg.xxWfRasgAllChkFlg.setValue(ZYPConstant.FLG_OFF_N);

    }

    private static List<Integer> exceptDupulicateTaskid(List resultList){
        List<Integer> ret = new ArrayList<Integer>();
        int max = resultList.size();
        Map<BigDecimal, BigDecimal> dupCtrl = new HashMap<BigDecimal, BigDecimal>();

        for (int index = 0; index < max; index++){
            Map map = (Map) resultList.get(index);

            if (!map.containsKey("WFWRKITEMPK")){
                continue;
            }

            BigDecimal taskID = (BigDecimal) map.get("WFWRKITEMPK");

            if (dupCtrl.containsKey(taskID)){
                continue;
            }

            dupCtrl.put(taskID, taskID);
            ret.add(index);
        }

        return ret;

    }

    private static void toMsg(NYEL8810CMsg bizMsg, NYEL8810SMsg sMsg, S21SsmEZDResult result, String usrId, String loginUser) {

        // Normal
        List resultList = (List) result.getResultObject();

        // Except Duplicate TaskId
        List<Integer> exIndex = exceptDupulicateTaskid(resultList);
        
        int max = exIndex.size();

        if (max > 0) {
            sMsg.A.clear();
        }

        int bizMax = sMsg.A.length();
        // DEL START 2018/04/17
        // int maxIndex = max;
        // DEL END 2018/04/17
        int index = 0;

        if (max > bizMax) {
            // MOD START 2018/04/17
            // index = max - bizMax;
            // max = maxIndex;
            max = bizMax;
            // MOD END 2018/04/17
            bizMsg.setMessageInfo(NYEM0003W);
        }

        int tIndex = index;
        StringBuilder sbToUser = new StringBuilder();
        while (tIndex < max) {
            Map map = (Map) resultList.get(exIndex.get(tIndex));
            BigDecimal taskID = (BigDecimal) map.get("WFWRKITEMPK");
            
            if (taskID != null) {
                
                String param = taskID.toPlainString();
                if (sbToUser.length() <= 0){
                    sbToUser.append(param);
                } else {
                    sbToUser.append(" , " + param);
                }
            }
            tIndex++;
        }
        S21StopWatch sw = new S21StopWatch();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "getToUsers"));
        sw.start();

        //ToUsersGet
        S21SsmEZDResult userList = NYXC880002Query.getInstance().getToUsers(sbToUser.toString(), loginUser, bizMsg.procStsCd.getValue());
        
        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "getToUsers", sw.getElapsedMilliSec()));

// QC#23516 ADD START 2018/05/07
        sw = new S21StopWatch();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "getCommentList"));
        sw.start();

        //CommentGet
        S21SsmEZDResult cmntList = NYXC880002Query.getInstance().getCommentList(sbToUser.toString(), bizMsg.procStsCd.getValue());

        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "getCommentList", sw.getElapsedMilliSec()));

// QC#23516 ADD END 2018/05/07
        sw = new S21StopWatch();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]Start %s", "toMsg"));
        sw.start();
        
        int cnt = 0;

        while (index < max) {
            Map map = (Map) resultList.get(exIndex.get(index));
            BigDecimal procID = BigDecimal.ZERO;
            BigDecimal taskID = BigDecimal.ZERO;
            BigDecimal wfTokenBizPk = BigDecimal.ZERO;

            // TaskID(Hidden)
            if (map.containsKey("WFWRKITEMPK")) {
                taskID = (BigDecimal) map.get("WFWRKITEMPK");
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfWrkItemPk_A, taskID);
            }

            // Token Biz BojectPK(Hidden)
            if (map.containsKey("WFTOKENBIZPK")) {
                wfTokenBizPk = (BigDecimal) map.get("WFTOKENBIZPK");
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfTokenBizPk_A, wfTokenBizPk);
            }

            // Group ID(Hidden)
            if (map.containsKey("XXWFPROCGRPCD")) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfProcTagPk_A, (BigDecimal) map.get("XXWFPROCGRPCD"));
            }

            // Document ID
            if (map.containsKey("WFPROCDOCID")) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfProcDocId_A, (String) map.get("WFPROCDOCID"));
            }

            // Process ID(Hidden)
            if (map.containsKey("WFPROCPK")) {
                procID = (BigDecimal) map.get("WFPROCPK");
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfProcPk_A, procID);
            }

            // Process name
            if (map.containsKey("WFPROCNM")) {
                String procNm = (String) map.get("WFPROCNM");
                
                if (procNm.length() > NYXC880001constant.PROCESS_NM_LENGTH){
                    procNm = procNm.substring(0, NYXC880001constant.PROCESS_NM_LENGTH);
                }
                    
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfProcNm_A, procNm);
            }

            // Process Status
            if (map.containsKey("WFPROCSTSCD")) {
                String wfProcStsCd = (String) map.get("WFPROCSTSCD");

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfProcStsCd_A, wfProcStsCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).procStsNm_A, NYXC880001.toProcStatusNm(wfProcStsCd));
            }

            // Task
            if (map.containsKey("WFWRKITEMNM")) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfWrkItemNm_A, (String) map.get("WFWRKITEMNM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfWrkItemNm_LK, (String) map.get("WFWRKITEMNM"));
            }

            // Attribute1-5, Attribute1-5 Label
// 2018/05/16 MOD START
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).bizAppId_A, (String) map.get("WF_BIZ_SCR_ID"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizScrId_A, (String) map.get("WF_BIZ_SCR_ID"));
// 2018/05/16 MOD END
// 2017/11/01  Delete start QC#21078
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V1, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_01"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_01")));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V2, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_02"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_02")));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V3, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_03"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_03")));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V4, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_04"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_04")));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V5, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_05"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_05")));
// 2017/11/01  Delete end QC#21078

// 2017/11/01  Add start QC#21078
// 2018/05/16 MOD START
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).bizAppId_A1, (String) map.get("WF_BIZ_SCR_ID_01"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).bizAppId_A2, (String) map.get("WF_BIZ_SCR_ID_02"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).bizAppId_A3, (String) map.get("WF_BIZ_SCR_ID_03"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).bizAppId_A4, (String) map.get("WF_BIZ_SCR_ID_04"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).bizAppId_A5, (String) map.get("WF_BIZ_SCR_ID_05"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizScrId_A1, (String) map.get("WF_BIZ_SCR_ID_01"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizScrId_A2, (String) map.get("WF_BIZ_SCR_ID_02"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizScrId_A3, (String) map.get("WF_BIZ_SCR_ID_03"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizScrId_A4, (String) map.get("WF_BIZ_SCR_ID_04"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizScrId_A5, (String) map.get("WF_BIZ_SCR_ID_05"));
// 2018/05/16 MOD END
// 2018/08/24 MOD START
//          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V1, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_01"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_01")).replaceAll(TEXTAREA_NEWLINE,"<br>"));
//          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V2, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_02"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_02")).replaceAll(TEXTAREA_NEWLINE,"<br>"));
//          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V3, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_03"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_03")).replaceAll(TEXTAREA_NEWLINE,"<br>"));
//          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V4, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_04"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_04")).replaceAll(TEXTAREA_NEWLINE,"<br>"));
//          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V5, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_05"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_05")).replaceAll(TEXTAREA_NEWLINE,"<br>"));
          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V1, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_01"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_01")));
          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V2, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_02"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_02")));
          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V3, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_03"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_03")));
          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V4, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_04"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_04")));
          ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxScrItem130Txt_V5, NYXC880001.toAttr((String) map.get("WF_BIZ_ATTRB_TXT_05"), (String) map.get("WF_BIZ_ATTRB_LB_TXT_05")));
// 2018/08/24 MOD END
// 2017/11/01  Add end QC#21078
// 2018/05/15 ADD START
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfBizAttrbTxt_A1, (String) map.get("WF_BIZ_ATTRB_TXT_01"));
// 2018/05/15 ADD END

            String isAdminFU = (String) map.get("FROMUSER_ISADMIN");

            // From User
// 2018/09/25 MOD START
//            if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgFromNm_A, ADMIN_OPERATOR_NAME);
//            } else {
//                if (map.containsKey("FROMUSRID")) {
//                    String fromGrpNm = (String) map.get("WFUSRGRPNM_F");
//                    String fromUsrId = (String) map.get("FROMUSRID");
//                    String fromUsrNm = (String) map.get("FROMUSRNM");
//
//                    if (S21StringUtil.isEmpty(fromUsrNm)) {
//                        fromUsrNm = "";
//                    }
//
//                    if (S21StringUtil.isNotEmpty(fromUsrId)) {
//                        if (S21StringUtil.isNotEmpty(fromGrpNm)){
//                            ZYPEZDItemValueSetter.setValue(//
//                                    sMsg.A.no(cnt).xxWfAsgFromNm_A //
//                                    , fromGrpNm);
//                        } else {
//                            ZYPEZDItemValueSetter.setValue(//
//                                    sMsg.A.no(cnt).xxWfAsgFromNm_A //
//                                    , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm));
//                        }
//                    }
//                }
//            }
            if (map.containsKey("FROMUSRID")) {
                String fromGrpNm = (String) map.get("WFUSRGRPNM_F");
                String fromUsrId = (String) map.get("FROMUSRID");
                String fromUsrNm = (String) map.get("FROMUSRNM");

                if (S21StringUtil.isEmpty(fromUsrNm)) {
                    fromUsrNm = "";
                }

                if (S21StringUtil.isNotEmpty(fromUsrId)) {
                    if (S21StringUtil.isNotEmpty(fromGrpNm)){
                        ZYPEZDItemValueSetter.setValue(//
                                sMsg.A.no(cnt).xxWfAsgFromNm_A //
                                , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm));
                    } else {
                        if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
                            ZYPEZDItemValueSetter.setValue(//
                                    sMsg.A.no(cnt).xxWfAsgFromNm_A //
                                    , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm, " (", ADMIN_RIGHTS, ")"));
                        } else {
                            ZYPEZDItemValueSetter.setValue(//
                                    sMsg.A.no(cnt).xxWfAsgFromNm_A //
                                    , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm));
                        }
                    }
                }
            }
// 2018/09/25 MOD END

            // To User

            boolean isSetUserNm = false;
            boolean hasAdminUser = false;

            // No Data
            if (userList.isCodeNormal()) {
                // Normal
                List userltList = (List) userList.getResultObject();
                int maxSize = userltList.size();
                String toUserNm = "";
                String isAdmin = "";
                String isGroup = "";
                StringBuilder sb = new StringBuilder();
                Map<String, String> usrMap = new HashMap<String,String>();

                boolean isContinue = true;
                for (int i = 0; i < maxSize; i++) {
                    Map userMap = (Map) userltList.get(i);
                    if (!taskID.equals((BigDecimal)userMap.get("WF_WRK_ITEM_PK"))){
                        if (isContinue){
                            continue;
                        } else {
                            break;
                        }
                    }

                    isContinue = false;
// 2018/09/25 ADD START
                    hasAdminUser = false;
// 2018/09/25 ADD END
                    toUserNm = (String) userMap.get("WFUSRNM");
                    isAdmin = (String) userMap.get("ISADMIN");
                    isGroup = (String) userMap.get("ISGROUP");

                    if (ZYPConstant.FLG_ON_1.equals(isGroup)) {
                        if (ADMIN_GRP.equals(toUserNm)) {
                            hasAdminUser = true;
                            continue;
                        }
                    } else  if (ZYPConstant.FLG_ON_1.equals(isAdmin) || ADMIN_GRP.equals(toUserNm)) {
                        hasAdminUser = true;
// 2018/09/25 DEL START
                        //continue;
// 2018/09/25 DEL END
                    }

                    if (usrMap.containsKey(toUserNm)){
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
                            usrMap.put(toUserNm, toUserNm);
                            isSetUserNm = true;
                        }
                    }
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, sb.toString());
            }

            String wfEndTs = "";
            String eventType = "";
            String toUsrIsAdmin = (String) map.get("TOUSER_ISADMIN");
            boolean isAuto = false;

            if (map.containsKey("WFEVENTAUTOFLG")) {
                String autoflg = (String) map.get("WFEVENTAUTOFLG");

                if (S21StringUtil.isNotEmpty(autoflg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(autoflg)) {
                        isAuto = true;
                    }
                }
            }

            if (hasAdminUser && (isSetUserNm == false)){
// 2018/09/25 MOD START
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, ADMIN_OPERATOR_NAME);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, ADMIN_RIGHTS);
// 2018/09/25 MOD END
            }

            String toUserId = (String) map.get("TOUSRID");
// 2018/09/25 ADD START
            String toUserNm = (String) map.get("TOUSRNM");
            if (S21StringUtil.isEmpty(toUserNm)) {
                toUserNm = "";
            }
// 2018/09/25 ADD END

            if (toUserId != null) {
                if (isAuto) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfActOpNm_A, ACT_USER_WFSYSTEM);

                    if (isSetUserNm == false) {
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, String.format("%s %s", toUserId, ACT_USER_WFSYSTEM));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, ACT_USER_WFSYSTEM);
                    }
                } else if (ZYPConstant.FLG_ON_1.equals(toUsrIsAdmin)) {
// 2018/09/25 MOD START
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfActOpNm_A, ADMIN_RIGHTS);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfActOpNm_A, String.format("%s %s (%s)", toUserId, toUserNm, ADMIN_RIGHTS));
// 2018/09/25 MOD END

                    if (isSetUserNm == false) {
                        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, String.format("%s %s", toUserId, ADMIN_OPERATOR_NAME));
// 2018/09/25 MOD START
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, ADMIN_OPERATOR_NAME);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, String.format("%s %s (%s)", toUserId, toUserNm, ADMIN_RIGHTS));
// 2018/09/25 MOD END
                    }
                } else {
// 2018/09/25 DEL START
//                    String toUserNm = (String) map.get("TOUSRNM");
//
//                    if (S21StringUtil.isEmpty(toUserNm)) {
//                        toUserNm = "";
//                    }
// 2018/09/25 DEL END

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfActOpNm_A, String.format("%s %s", toUserId, toUserNm));

                    if (isSetUserNm == false) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfAsgToNm_A, String.format("%s %s", toUserId, toUserNm));
                    }
                }
            }

            // Action
            String actNm = (String) map.get("LEAVEWFCONDNM");

            if (S21StringUtil.isNotEmpty(actNm)) {
                if (isAuto) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).actWfCondNm_A, "AUTO " + actNm);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).actWfCondNm_A, actNm);
                }
            }
/*            
            eventType = (String) map.get("WFEVENTTPCD");

            if (S21StringUtil.isNotEmpty(eventType)) {
                String actNm = NYXC880001.toActNm(eventType);

                if (S21StringUtil.isNotEmpty(actNm)) {
                    if (isAuto) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).actWfCondNm_A, "AUTO " + actNm);
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).actWfCondNm_A, actNm);
                    }
                }
            }
*/
            // Comment
// QC#23516 MOD START 2018/05/07
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfCmntTxt_A, (String) map.get("WFCMNTTXT"));
//            String wfCmntTxt = (String) map.get("WFCMNTTXT");
//            if (wfCmntTxt != null) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfCmntTxt_A, wfCmntTxt.replace("\\\\r\\\\n", TEXTAREA_NEWLINE));
//            }
            // No Data
            if (cmntList.isCodeNormal()) {
                // Normal
                List cmndltList = (List) cmntList.getResultObject();
                int maxSize = cmndltList.size();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < maxSize; i++) {
                    Map cmndMap = (Map) cmndltList.get(i);
                    if (taskID.equals((BigDecimal)cmndMap.get("WF_WRK_ITEM_PK"))){
                        String cmndTxt = (String) cmndMap.get("WF_CMNT_TXT");
                        if (cmndTxt != null) {
                            if (sb.length() > 0) {
                                sb.append(TEXTAREA_NEWLINE).append(TEXTAREA_NEWLINE);
                            }

                            if (S21StringUtil.isNotEmpty(cmndTxt)){
                                sb.append(cmndTxt);
                                if (sb.length() > MAX_CMNT_L) {
                                    break;
                                }
                            }
                        }
                    }
                }

                String dispCmnt = sb.toString();
                if (dispCmnt.length() > MAX_CMNT_L) {
                    dispCmnt = sb.substring(0, (MAX_CMNT_L - 4)) + TEXTAREA_OMIT;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfCmntTxt_A, dispCmnt);
            }
// QC#23516 MOD END 2018/05/07

            // End
            wfEndTs = (String) map.get("WFENDTS");

            if (S21StringUtil.isNotEmpty(wfEndTs)) {
                String formatStr = ZYPDateUtil.formatEzd17ToDisp(wfEndTs);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxDtTm_AE, formatStr);
            }

            // Started, Age
            if (map.containsKey("WFSTARTTS")) {
                String formatStr = ZYPDateUtil.formatEzd17ToDisp((String) map.get("WFSTARTTS"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxDtTm_AS, formatStr);

                String age = "";

                if (S21StringUtil.isEmpty(wfEndTs)){
                    age = getAge((String) map.get("WFSTARTTS"));
                } else {
                    age = getAge((String) map.get("WFSTARTTS"), wfEndTs);
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxBizCalTxt_A, age);
            }

            // Due Date
            if (map.containsKey("WF_DUE_TS")) {
                String yyyymmdd = (String) map.get("WF_DUE_TS");

                if ((S21StringUtil.isNotEmpty(yyyymmdd)) && (yyyymmdd.length() >= ZYPDateUtil.TYPE_YYYYMMDD.length())){
                    String formatStr = ZYPDateUtil.formatEzd8ToDisp(yyyymmdd.substring(0, ZYPDateUtil.TYPE_YYYYMMDD.length()));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxDtTm_AD, formatStr);
                }
            }

            // Task Status
            String wfWrkItemStsCd = "";

            if (map.containsKey("WFWRKITEMSTSCD")) {
                wfWrkItemStsCd = (String) map.get("WFWRKITEMSTSCD");
                if (wfWrkItemStsCd == null) {
                    wfWrkItemStsCd = NYXC880001.toTaskStatusCode(S21NwfWorkItem.STATUS.IDLE);
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).wfWrkItemStsCd_A, wfWrkItemStsCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(cnt).xxWfWrkItemStsNm_A, NYXC880001.toTaskStatusNm(wfWrkItemStsCd, eventType));
            }

            String wfaccessApp = ZYPConstant.FLG_OFF_N;
            String wfinpApp = ZYPConstant.FLG_OFF_N;
            String wfreasgApp = ZYPConstant.FLG_OFF_N;
            String wffyiApp = ZYPConstant.FLG_OFF_N;
            String wfcancApp = ZYPConstant.FLG_OFF_N;

            String wfProcStsCd = (String) map.get("WFPROCSTSCD");
            String xxWfAprChkFlg = (String) map.get("XXWFAPRCHKFLG");
            String xxWfFyiChkFlg = (String) map.get("XXWFFYICHKFLG");
            String xxWfReasgChkFlg = (String) map.get("XXWFREASGCHKFLG");;
            String wfCancFlg = (String) map.get("WFCANCCHKFLG");

            String possibleAct = "";

            if (!(wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CLOSE)) || wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CANCEL)))) {
                if (ZYPConstant.CHKBOX_ON_1.equals(wfCancFlg)) {
                    wfcancApp = ZYPConstant.FLG_ON_Y;
                    possibleAct = PSBL_ACT_CANCEL;
                }
            }

            if (ZYPConstant.CHKBOX_ON_1.equals(xxWfFyiChkFlg)) {
                wffyiApp = ZYPConstant.FLG_ON_Y;
                possibleAct = PSBL_ACT_FYI;
            }

            if (!(wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CLOSE)) || wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CANCEL))
                    || wfWrkItemStsCd.equals(NYXC880001.toTaskStatusCode(S21NwfWorkItem.STATUS.CLOSE)) || wfWrkItemStsCd.equals(NYXC880001.toTaskStatusCode(S21NwfWorkItem.STATUS.CANCEL)))) {

                if (ZYPConstant.CHKBOX_ON_1.equals(xxWfAprChkFlg)) {
                    wfaccessApp = ZYPConstant.FLG_ON_Y;
                    possibleAct = PSBL_ACT_APPROVE;
                }

                if (ZYPConstant.CHKBOX_ON_1.equals(xxWfReasgChkFlg)) {
                    wfreasgApp = ZYPConstant.FLG_ON_Y;
                }
            }

            if ((S21StringUtil.isNotEmpty(usrId)) && (!usrId.equals(loginUser))) {
                wfaccessApp = ZYPConstant.FLG_OFF_N;
                wfinpApp = ZYPConstant.FLG_OFF_N;
                wfreasgApp = ZYPConstant.FLG_OFF_N;
                wffyiApp = ZYPConstant.FLG_OFF_N;
                wfcancApp = ZYPConstant.FLG_OFF_N;
            }

            // Possible Action
            sMsg.A.no(cnt).xxWfPsblActNm_A.setValue(possibleAct);

            // Approve
            sMsg.A.no(cnt).xxWfAprChkVisFlg_A.setValue(wfaccessApp);

            // Input
            sMsg.A.no(cnt).xxWfInpChkVisFlg_A.setValue(wfinpApp);

            // FYI
            sMsg.A.no(cnt).xxWfFyiChkVisFlg_A.setValue(wffyiApp);

            // Reassign
            sMsg.A.no(cnt).xxWfRasgChkVisFlg_A.setValue(wfreasgApp);

            // Cancel
            sMsg.A.no(cnt).cancFlg_VF.setValue(wfcancApp);

            index++;
            cnt++;
        }

        // MOD START 2018/04/17
        // if (bizMax > max) {
        //     sMsg.A.setValidCount(max);
        // } else {
        //     sMsg.A.setValidCount(bizMax);
        // }
        sMsg.A.setValidCount(max);
        // MOD END 2018/04/17

        if (max <= 0) {
            bizMsg.setMessageInfo(NYEM0002E);
        }
        
        sw.stop();
        S21InfoLogOutput.println(String.format("[NYEL8810_doPorcess]End %s [%d]", "toMsg", sw.getElapsedMilliSec()));
    }
    
    private static String getAge(String start){
        return getAge(start, S21NwfDateUtil.getSystemTimestamp());
    }
    
    private static String getAge(String start, String end){

        if (S21StringUtil.isEmpty(start)){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        long age = S21NwfDateUtil.calcDeffTimestamp(start, end);
        long days = 0;
        long hours = 0;
        long mins = 0;
        long seconds = 0;
        long msec = 0;

        if (age > 0){
            msec = age % 1000;
            age /= 1000;
        }

        if (age > 0){
            days = age / (24 * 60 * 60);
            hours = age % (24 * 60 * 60);

            if (hours > 0 ){
                mins =  hours % (60 * 60);
                hours /= (60 * 60);

                if (mins > 0){
                    seconds = mins % 60;
                    mins /= 60;
                }
            }
        }

        if (days > 0){
            sb.append(String.format("%4d Days ", days));
        } else {
            sb.append(String.format("%10s", ""));
        }

        if ((hours > 0) || (mins > 0) || (seconds > 0) || (msec > 0)){
            sb.append(String.format("%02d:%02d:%02d:%03d", hours, mins, seconds, msec));
        } else {
            sb.append("00:00:00:000");
        }

        return sb.toString();
    }

    /**
     * Pulldown Initialize
     * @param bizMsg
     * @param uid
     */
    public static void initPullDown(NYEL8810CMsg bizMsg, String uid) {

        boolean isAdmin = NYXC880002.isAdministrator(uid);

        if (isAdmin) {
            uid = "";
        }

        // Status
        ZYPCodeDataUtil.createPulldownList(WF_PROC_COND_STS.class.getSimpleName(), bizMsg.procStsCd_L, bizMsg.procStsNm_L);
        ZYPEZDItemValueSetter.setValue(bizMsg.procStsCd, WF_PROC_COND_STS.ACTIVE);

        // Process Name
        bizMsg.wfProcNm_L.clear();
        bizMsg.wfProcNm_LD.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm, PROC_ID_ALL);

        // Task Name
// QC#29682 MOD START 2018/12/18
//        bizMsg.wfWrkItemNm_L.clear();
//        bizMsg.wfWrkItemNm_LD.clear();
//        ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, TASK_ID_ALL);
//        ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(0), TASK_ID_ALL);
//        ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(0), TASK_ID_ALL);
        initTaskNamePullDown(bizMsg, uid);
// QC#29682 MOD END   2018/12/18

        S21SsmEZDResult result = NYXC880002Query.getInstance().getWorklistPullDown(uid);

        if (!result.isCodeNormal()) {
            return;
        }

        List resultList = (List) result.getResultObject();
        int max = resultList.size();

        for (int cnt = 0; cnt < max; cnt++) {
            Map map = (Map) resultList.get(cnt);

            // Process Name
            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm_L.no(cnt), (String) map.get("WFPROCNM"));

            String procNmDsp = (String) map.get("WFPROCNMDSP");

            if (procNmDsp.length() > NYXC880001constant.PROCESS_NM_LENGTH){
                procNmDsp = procNmDsp.substring(0, NYXC880001constant.PROCESS_NM_LENGTH);
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm_LD.no(cnt), procNmDsp);
        }

        // From
        // Status
        //ZYPCodeDataUtil.createPulldownList(WF_GRP_TP.class.getSimpleName(), bizMsg.xxGrpFlg_FL, bizMsg.xxCondNm_FD);
        
        //bizMsg.xxCondNm_FD.clear();

        
        
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCondNm_FD.no(0), "USER");
        ZYPEZDItemValueSetter.setValue(bizMsg.xxGrpFlg_FL.no(0), WF_GRP_TP.USER);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxGrpFlg_F, WF_GRP_TP.USER);

        // TO
        ZYPCodeDataUtil.createPulldownList(WF_GRP_TP.class.getSimpleName(), bizMsg.xxGrpFlg_TL, bizMsg.xxCondNm_TD);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxGrpFlg_T, WF_GRP_TP.ALL);
    }

    public static void initTaskNamePullDown(NYEL8810CMsg bizMsg, String uid) {
// QC#29682 MOD START 2018/12/18
//        String procNm = bizMsg.wfProcNm.getValue();
//        if (S21StringUtil.isEmpty(procNm)){
//            return;
//        }
//
//        // QC#24931 ADD START 2018/03/20
//        String inTaskNm = bizMsg.wfWrkItemNm.getValue();
//
//
//        if (S21StringUtil.isEmpty(inTaskNm) || inTaskNm.equals(TASK_ID_ALL)) {
//        // QC#24931 ADD END   2018/03/20
//            bizMsg.wfWrkItemNm_L.clear();
//            bizMsg.wfWrkItemNm_LD.clear();
//            ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, TASK_ID_ALL);
//            ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(0), TASK_ID_ALL);
//            ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(0), TASK_ID_ALL);
//        // QC#24931 ADD START 2018/03/20
//        }
//
//        // QC#24931 ADD END   2018/03/20
//        if (PROC_ID_ALL.equals(procNm)){
//            return;
//        }
//
//        boolean isAdmin = NYXC880002.isAdministrator(uid);
//
//        if (isAdmin) {
//            uid = "";
//        }
//        
//        // QC#24931 ADD START 2018/03/20
//        boolean isTaskNmExist = false;
//        // QC#24931 ADD END   2018/03/20
//
//        S21NwfUtilMasterDefXmlFactory factory = new S21NwfUtilMasterDefXmlFactory();
//        Map<String, String> taskMap = new HashMap<String, String>();
//        try {
//            S21NwfUtilDef def = factory.createCondDef(procNm);
//            List<Detail>list  = def.getDetailDef();
//            int max = list.size();
//
//            for (int cnt = 0; cnt < max; cnt++) {
//                Detail dtl = list.get(cnt);
//                String taskNm = dtl.getRenewTaskName();
//
//                if (S21StringUtil.isEmpty(taskNm)){
//                    taskNm = dtl.getTargetTaskName();
//
//                    if (S21StringUtil.isEmpty(taskNm)){
//                        taskNm = dtl.getAttachTaskName();
//                    }
//                }
//
//                if (!taskMap.containsKey(taskNm)){
//                    taskMap.put(taskNm, taskNm);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(cnt + 1), taskNm);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(cnt + 1), taskNm);
//                }
//
//                // QC#24931 ADD START 2018/03/20
//                if (isTaskNmExist == false && S21StringUtil.isNotEmpty(inTaskNm) && inTaskNm.equals(taskNm)) {
//                    isTaskNmExist = true;
//                }
//                // QC#24931 ADD END   2018/03/20
//
//            }
//
//            // QC#24931 ADD START 2018/03/20
//            if (isTaskNmExist) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, inTaskNm);
//            }
//            // QC#24931 ADD END   2018/03/20
//
//        } catch (S21NwfSystemException e) {
//            // TODO 自動生成された catch ブロック
//            e.printStackTrace();
//        }

    if (S21StringUtil.isEmpty(bizMsg.wfWrkItemNm.getValue())) {
        ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, TASK_ID_APPROVAL);
    }

    bizMsg.wfWrkItemNm_L.clear();
    bizMsg.wfWrkItemNm_LD.clear();
    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(0), TASK_ID_ALL);
    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(0), TASK_ID_ALL);   
    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(1), TASK_ID_SUBMISSION);
    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(1), TASK_ID_SUBMISSION);
    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(2), TASK_ID_APPROVAL);
    ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(2), TASK_ID_APPROVAL);
// QC#29682 MOD END   2018/12/18

/*
        S21SsmEZDResult result = NYXC880002Query.getInstance().getWorklistTaskPullDown(procNm, uid);

        if (!result.isCodeNormal()) {
            return;
        }

        List resultList = (List) result.getResultObject();
        int max = resultList.size();

        for (int cnt = 0; cnt < max; cnt++) {
            Map map = (Map) resultList.get(cnt);

            // Task Name
            String tskNm = (String) map.get("WFWRKITEMNM");

            if (S21StringUtil.isNotEmpty(tskNm)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_L.no(cnt + 1), tskNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm_LD.no(cnt + 1), tskNm);
            }
        }
*/
    }

    private static String parameterJoin(String[] parameter){
        StringBuilder sb = new StringBuilder();

        if (parameter != null){
            for (String param : parameter){
                param = "'" + param + "'";
                if (sb.length() <= 0){
                    sb.append(param);
                } else {
                    sb.append(" , " + param);
                }
            }
        }
        return sb.toString();
    }

    public static boolean conv2FromNm(NYEL8810CMsg bizMsg, String usrId, boolean isAdmin){
        boolean ret = true;

        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm_F.getValue())){
            String fromNm = "";
            StringBuilder grpDtlNm = new StringBuilder();
            // 2018/02/01  Mod start Bug fix
            // boolean isGrpNmExists = true;
            boolean isGrpNmExists = false;
            // 2018/02/01  Mod end Bug fix

            if ("0".equals(bizMsg.xxGrpFlg_F.getValue())){
                //ALL
                fromNm = NYXC880001.getAssigneeNmFromS21Psn(bizMsg.wfUsrGrpNm_F.getValue());

                if (S21StringUtil.isEmpty(fromNm)){
                    if (isAdmin){
                        isGrpNmExists = conv2FromGrpNm("", bizMsg.wfUsrGrpNm_F.getValue(), grpDtlNm);
                    } else{
                        isGrpNmExists = conv2FromGrpNm(usrId, bizMsg.wfUsrGrpNm_F.getValue(), grpDtlNm);
                    }

                    if (isGrpNmExists && (grpDtlNm.length() > 0)){
                        fromNm = grpDtlNm.toString();
                    }
                }
            } else if ("1".equals(bizMsg.xxGrpFlg_F.getValue())){
                //GROUP
                if (S21StringUtil.isEmpty(fromNm)){
                    if (isAdmin){
                        isGrpNmExists = conv2FromGrpNm("", bizMsg.wfUsrGrpNm_F.getValue(), grpDtlNm);
                    } else{
                        isGrpNmExists = conv2FromGrpNm(usrId, bizMsg.wfUsrGrpNm_F.getValue(), grpDtlNm);
                    }

                    if (isGrpNmExists && (grpDtlNm.length() > 0)){
                        fromNm = grpDtlNm.toString();
                    }
                }
            } else if ("2".equals(bizMsg.xxGrpFlg_F.getValue())){
                //USER
                fromNm = NYXC880001.getAssigneeNmFromS21Psn(bizMsg.wfUsrGrpNm_F.getValue());
            }

            if (S21StringUtil.isEmpty(fromNm) && (isGrpNmExists == false)){
                ret = false;
                // QC#23516 MOD START 2018/05/07
//                bizMsg.wfUsrGrpNm_F.setErrorInfo(1, ZZSM4110E, new String[]{"From"});
                bizMsg.wfUsrGrpNm_F.setErrorInfo(1, NYEM0016E, new String[]{"From"});
                // QC#23516 MOD END 2018/05/07
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlNm_F, fromNm);
            }
        } else {
            bizMsg.xxDtlNm_F.clear();
        }

        return ret;

    }

    /**
     * conv2FromGrpNm
     * @param usrId
     * @param wfUsrGrpNm
     * @param grpDtlNm
     * @return
     */
    public static boolean conv2FromGrpNm(String usrId, String wfUsrGrpNm, StringBuilder grpDtlNm){
        boolean ret = false;

        S21SsmEZDResult result = NYXC880002Query.getInstance().getFromUsrGrpList(usrId, wfUsrGrpNm, "");

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();
            if (resultList.size() > 0) {
                Map map = (Map) resultList.get(0);
                if (map.get("WF_USR_GRP_DESC_TXT") != null){
                    grpDtlNm.append((String) map.get("WF_USR_GRP_DESC_TXT"));
                }
                ret = true;
            }
        }

        return ret;
    }

    public static boolean conv2ToNm(NYEL8810CMsg bizMsg, String usrId, boolean isAdmin){
        boolean ret = true;

        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm_T.getValue())){
            String toNm = "";
            StringBuilder grpDtlNm = new StringBuilder();
            // 2018/02/01  Mod start Bug fix
            // boolean isGrpNmExists = true;
            boolean isGrpNmExists = false;
            // 2018/02/01  Mod end Bug fix

            if ("0".equals(bizMsg.xxGrpFlg_T.getValue())){
                //ALL
                toNm = NYXC880001.getAssigneeNmFromS21Psn(bizMsg.wfUsrGrpNm_T.getValue());

                if (S21StringUtil.isEmpty(toNm)){
                    if (isAdmin){
                        isGrpNmExists = conv2ToGrpNm("", bizMsg.wfUsrGrpNm_T.getValue(), grpDtlNm);
                    } else{
                        isGrpNmExists = conv2ToGrpNm(usrId, bizMsg.wfUsrGrpNm_T.getValue(), grpDtlNm);
                    }

                    if (isGrpNmExists && (grpDtlNm.length() > 0)){
                        toNm = grpDtlNm.toString();
                    }
                }
            } else if ("1".equals(bizMsg.xxGrpFlg_T.getValue())){
                //GROUP
                if (S21StringUtil.isEmpty(toNm)){
                    if (isAdmin){
                        isGrpNmExists = conv2ToGrpNm("", bizMsg.wfUsrGrpNm_T.getValue(), grpDtlNm);
                    } else{
                        isGrpNmExists = conv2ToGrpNm(usrId, bizMsg.wfUsrGrpNm_T.getValue(), grpDtlNm);
                    }

                    if (isGrpNmExists && (grpDtlNm.length() > 0)){
                        toNm = grpDtlNm.toString();
                    }
                }
            } else if ("2".equals(bizMsg.xxGrpFlg_T.getValue())){
                //USER
                toNm = NYXC880001.getAssigneeNmFromS21Psn(bizMsg.wfUsrGrpNm_T.getValue());
            }

            if (S21StringUtil.isEmpty(toNm) && (isGrpNmExists == false)){
                ret = false;
                // QC#23516 MOD START 2018/05/07
//                bizMsg.wfUsrGrpNm_T.setErrorInfo(1, ZZSM4110E, new String[]{"To"});
                bizMsg.wfUsrGrpNm_T.setErrorInfo(1, NYEM0016E, new String[]{"To"});
                // QC#23516 MOD END 2018/05/07
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlNm_T, toNm);
            }
        } else {
            bizMsg.xxDtlNm_T.clear();
        }

        return ret;

    }

    /**
     * conv2ToGrpNm
     * @param usrId
     * @param wfUsrGrpNm
     * @param grpDtlNm
     * @return
     */
    public static boolean conv2ToGrpNm(String usrId, String wfUsrGrpNm, StringBuilder grpDtlNm){
        boolean ret = false;

        S21SsmEZDResult result = NYXC880002Query.getInstance().getToUsrGrpList(usrId, wfUsrGrpNm, "");

        if (result.isCodeNormal()) {
            List resultList = (List) result.getResultObject();
            if (resultList.size() > 0) {
                Map map = (Map) resultList.get(0);
                if (map.get("WF_USR_GRP_DESC_TXT") != null){
                    grpDtlNm.append((String) map.get("WF_USR_GRP_DESC_TXT"));
                }
                ret = true;
            }
        }

        return ret;
    }

    /*
     * Save Search Option
     */

    public static void callNszc0330forDeleteSearch(NYEL8810CMsg bizMsg, NYEL8810SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId, glblCmpyCd);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    public static boolean isExistSaveSearchName(NYEL8810CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void callNszc0330forSaveSearch(NYEL8810CMsg bizMsg, NYEL8810SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.usrId);
        if (!bizMsg.wfProcPk.isClear()){
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.wfProcPk.getValue().toPlainString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.procStsCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.xxWfProcGrpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.wfProcNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.wfWrkItemNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.wfProcDocId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.xxGrpFlg_F);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.wfUsrGrpNm_F);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.xxDtlNm_F);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.xxGrpFlg_T);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.wfUsrGrpNm_T);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.xxDtlNm_T);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.wfBizAttrbTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.xxFromDt_SS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.xxThruDt_SE.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.xxFromDt_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.xxThruDt_DE.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.xxWfAprChkFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.xxWfFyiChkFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.xxWfConfFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.xxWfSubChkFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.xxWfVisChkFlg);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NYEL8810CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NYEL8810CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }


    private static boolean isSameSaveSearchName(NYEL8810CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    
    private static boolean callNszc0330(NYEL8810CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NYEL8810CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NYEL8810CMsg bizMsg, String srchOptUsrId, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NYXC880002Query.getInstance().getSavedSearchOptionList(srchOptUsrId, BIZ_ID, glblCmpyCd);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NYEL8810CMsg
     * @param sMsg NYEL8810SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NYEL8810CMsg bizMsg, NYEL8810SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.usrId, pMsg.srchOptTxt_01);

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_02)){
            ZYPEZDItemValueSetter.setValue(bizMsg.wfProcPk, new BigDecimal(pMsg.srchOptTxt_02.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.procStsCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfProcGrpCd, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfProcNm, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfWrkItemNm, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfProcDocId, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxGrpFlg_F, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfUsrGrpNm_F, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlNm_F, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxGrpFlg_T, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfUsrGrpNm_T, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlNm_T, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxFromDt_SS, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxThruDt_SE, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxFromDt_DS, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxThruDt_DE, pMsg.srchOptTxt_18.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfAprChkFlg, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfFyiChkFlg, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfConfFlg, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfSubChkFlg, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxWfVisChkFlg, pMsg.srchOptTxt_23);

    }

    /**
     * isValidParameter
     * @param bizMsg
     * @return
     */
    public static boolean isValidParameter(NYEL8810CMsg bizMsg, String loginUser){
        boolean ret = false;

        //User Group(To)
        ret = isValidGroup(bizMsg, loginUser);

        if (ret == false){
            return ret;
        }

        //Period
        ret = isValidPeriod(bizMsg);

        return ret;
    }

    /**
     * isValidGroup
     * @param bizMsg
     * @param loginUser
     * @return
     */
    private static boolean isValidGroup(NYEL8810CMsg bizMsg, String loginUser){
        boolean ret = false;
        boolean isAdmin = NYXC880002.isAdministrator(loginUser);

        //User Group(From)
        ret = conv2FromNm(bizMsg, loginUser, isAdmin);

        if (ret == false){
            return ret;
        }

        //User Group(To)
        ret = conv2ToNm(bizMsg, loginUser, isAdmin);

        if (ret == false){
            return ret;
        }

        return ret;
    }

    /**
     * isValidPeriod
     * @param bizMsg NYEL8810CMsg
     * @return if period is valid, return true.
     */
    private  static boolean isValidPeriod(NYEL8810CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxFromDt_SS) && ZYPCommonFunc.hasValue(bizMsg.xxThruDt_SE)) {
            if (ZYPDateUtil.compare(bizMsg.xxFromDt_SS.getValue(), bizMsg.xxThruDt_SE.getValue()) > 0) {
                // 2018/02/09 MOD START
                //bizMsg.xxFromDt_SS.setErrorInfo(1, ZZSM4199E, new String[] {"Send Date From is greater than Send Date To.", "", "", "" });
                bizMsg.xxFromDt_SS.setErrorInfo(1, ZZSM4199E, new String[] {"Sent Date From is greater than Sent Date To", "", "", "" });
                // 2018/02/09 MOD END
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxFromDt_DS) && ZYPCommonFunc.hasValue(bizMsg.xxThruDt_DE)) {
            if (ZYPDateUtil.compare(bizMsg.xxFromDt_DS.getValue(), bizMsg.xxThruDt_DE.getValue()) > 0) {
                bizMsg.xxFromDt_DS.setErrorInfo(1, ZZSM4199E, new String[] {"Due Date From is greater than Due Date To", "", "", "" });
                return false;
            }
        }

        return true;
    }

    // QC#21387 ADD START 2018/08/23
    /**
     * writeCsvFileOrder
     * @param cMsg NYEL8810CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NYEL8810CMsg cMsg, ResultSet rs) throws SQLException {

        NYEL8810F00FMsg fMsg = new NYEL8810F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        // set column sort
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // set exclusion column
        fMsg.addExclusionItem("xxWfAprChkFlg_A");

        // write header
        String[] hString = new String[] {"Check Box", "Type", "Workflow ID", "Source#", "Process Name", "Process Status", "Task Name", "Task Status", "Subject1", "Subject2", "Subject3", "Subject4", "Subject5", "Sent Date", "Due Date",
                "Closed Date", "From", "To", "Age", "Last Action", "Last Action By", "Comment" };
        csvOutFile.writeHeader(hString, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo("NZZM0000E", null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {

            if (rs.getRow() >= NYEL8810Constant.LIMIT_DL_ROWNUM) {

                cMsg.setMessageInfo("NZZM0001W", null);
                break;
            }

            // resultSet -> fMsg
            // Document ID
            ZYPEZDItemValueSetter.setValue(fMsg.wfProcDocId_A, (String) rs.getString("WFPROCDOCID"));

            // Process ID
            BigDecimal procID = (BigDecimal) rs.getBigDecimal("WFPROCPK");
            if (procID != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.wfProcPk_A, procID);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.wfProcPk_A, BigDecimal.ZERO);
            }

            // Process Name
            String procNm = (String) rs.getString("WFPROCNM");
            if (procNm != null) {
                if (procNm.length() > NYXC880001constant.PROCESS_NM_LENGTH){
                    procNm = procNm.substring(0, NYXC880001constant.PROCESS_NM_LENGTH);
                }

                ZYPEZDItemValueSetter.setValue(fMsg.wfProcNm_A, procNm);
            } else {
                fMsg.wfProcNm_A.clear();
            }

            // Process Status
            String wfProcStsCd = (String) rs.getString("WFPROCSTSCD");
            if (wfProcStsCd != null) {

                ZYPEZDItemValueSetter.setValue(fMsg.procStsNm_A, NYXC880001.toProcStatusNm(wfProcStsCd));
            } else {
                fMsg.procStsNm_A.clear();
            }

            // Task Name
            ZYPEZDItemValueSetter.setValue(fMsg.wfWrkItemNm_A, (String) rs.getString("WFWRKITEMNM"));

            // Subject1-5, Subject1-5 Label
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_V1, NYXC880001.toAttr((String) rs.getString("WF_BIZ_ATTRB_TXT_01"), (String) rs.getString("WF_BIZ_ATTRB_LB_TXT_01")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_V2, NYXC880001.toAttr((String) rs.getString("WF_BIZ_ATTRB_TXT_02"), (String) rs.getString("WF_BIZ_ATTRB_LB_TXT_02")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_V3, NYXC880001.toAttr((String) rs.getString("WF_BIZ_ATTRB_TXT_03"), (String) rs.getString("WF_BIZ_ATTRB_LB_TXT_03")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_V4, NYXC880001.toAttr((String) rs.getString("WF_BIZ_ATTRB_TXT_04"), (String) rs.getString("WF_BIZ_ATTRB_LB_TXT_04")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_V5, NYXC880001.toAttr((String) rs.getString("WF_BIZ_ATTRB_TXT_05"), (String) rs.getString("WF_BIZ_ATTRB_LB_TXT_05")));

            String isAdminFU = (String) rs.getString("FROMUSER_ISADMIN");

            // From User
            fMsg.xxWfAsgFromNm_A.clear();
// 2018/09/25 MOD START
//            if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
//                ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgFromNm_A, ADMIN_OPERATOR_NAME);
//            } else {
//                String fromUsrId = (String) rs.getString("FROMUSRID");
//                if (fromUsrId != null) {
//                    String fromGrpNm = (String) rs.getString("WFUSRGRPNM_F");
//                    String fromUsrNm = (String) rs.getString("FROMUSRNM");
//
//                    if (S21StringUtil.isEmpty(fromUsrNm)) {
//                        fromUsrNm = "";
//                    }
//
//                    if (S21StringUtil.isNotEmpty(fromUsrId)) {
//                        if (S21StringUtil.isNotEmpty(fromGrpNm)){
//                            ZYPEZDItemValueSetter.setValue(//
//                                    fMsg.xxWfAsgFromNm_A //
//                                    , fromGrpNm);
//                        } else {
//                            ZYPEZDItemValueSetter.setValue(//
//                                    fMsg.xxWfAsgFromNm_A //
//                                    , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm));
//                        }
//                    }
//                }
//            }
            String fromUsrId = (String) rs.getString("FROMUSRID");
            if (fromUsrId != null) {
                String fromGrpNm = (String) rs.getString("WFUSRGRPNM_F");
                String fromUsrNm = (String) rs.getString("FROMUSRNM");
                Map<String, String> usrMap = new HashMap<String,String>();

                if (S21StringUtil.isEmpty(fromUsrNm)) {
                    fromUsrNm = "";
                }

                if (S21StringUtil.isNotEmpty(fromUsrId)) {
                    if (S21StringUtil.isNotEmpty(fromGrpNm)){
                        ZYPEZDItemValueSetter.setValue(//
                                fMsg.xxWfAsgFromNm_A //
                                , fromGrpNm);
                    } else {
                        if (ZYPConstant.FLG_ON_1.equals(isAdminFU)) {
                            ZYPEZDItemValueSetter.setValue(//
                                    fMsg.xxWfAsgFromNm_A //
                                    , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm, " (", ADMIN_RIGHTS, ")"));
                        } else {
                            ZYPEZDItemValueSetter.setValue(//
                                    fMsg.xxWfAsgFromNm_A //
                                    , S21StringUtil.concatStrings(fromUsrId, " ", fromUsrNm));
                        }
                    }
                }
            }
// 2018/09/25 MOD END

            // To User
            boolean isSetUserNm = false;
            boolean hasAdminUser = false;

            fMsg.xxWfAsgToNm_A.clear();
            fMsg.xxWfActOpNm_A.clear();
            String toUser = (String) rs.getString("TO_WFUSRNM");
            if (toUser != null) {
                String toUserNm = "";
                String isAdmin = "";
                String isGroup = "";
                StringBuilder sb = new StringBuilder();

                toUserNm = (String) rs.getString("TO_WFUSRNM");
                isAdmin = (String) rs.getString("TO_ISADMIN");
                isGroup = (String) rs.getString("TO_ISGROUP");

// 2018/09/25 MOD START
//                if (ZYPConstant.FLG_ON_1.equals(isGroup)) {
//                    if (ADMIN_GRP.equals(toUserNm)) {
//                        hasAdminUser = true;
//                    }
//                } else  if (ZYPConstant.FLG_ON_1.equals(isAdmin) || ADMIN_GRP.equals(toUserNm)) {
//                    hasAdminUser = true;
//                }
//
//                if (S21StringUtil.isEmpty(toUserNm)) {
//                    toUserNm = "";
//                }
//
//                if (toUserNm != null) {
//                    String[] users = toUserNm.split(",", 0);
//
//                    List<String> toUsers = new ArrayList<String>();
//                    for (int i=0; i<users.length; i++) {
//                        if (toUsers.contains(users[i])) {
//                            continue;
//                        }
//                        if (sb.length() > 0) {
//                            sb.append(TEXTAREA_NEWLINE);
//                        }
//                        if (S21StringUtil.isNotEmpty(users[i])){
//                            sb.append(users[i]);
//                            toUsers.add(users[i]);
//                            isSetUserNm = true;
//                        }
//                        
//                    }
//
//                }
                String[] users = toUserNm.split("\",\"", 0);
                String[] admin = isAdmin.split(",", 0);
                String[] group = isGroup.split(",",0);
                Map<String, String> usrMap = new HashMap<String,String>();
                for (int i=0; i<users.length; i++) {
                    hasAdminUser = false;
                    if (ZYPConstant.FLG_ON_1.equals(group[i])) {
                        if (ADMIN_GRP.equals(users[i])) {
                            hasAdminUser = true;
                        }
                    } else  if (ZYPConstant.FLG_ON_1.equals(admin[i]) || ADMIN_GRP.equals(users[i])) {
                        hasAdminUser = true;
                    }

                    if (users.length == 0) {
                        users[i] = "";
                    }

                    if (usrMap.containsKey(users[i])) {
                        continue;
                    }
                    if (sb.length() > 0) {
                        sb.append(TEXTAREA_NEWLINE);
                    }
                    if (S21StringUtil.isNotEmpty(users[i])){
                        sb.append(users[i]);
                        if (hasAdminUser && ZYPConstant.FLG_OFF_0.equals(group[i])) {
                            sb.append(" (").append(ADMIN_RIGHTS).append(")");
                        }
                        usrMap.put(users[i], users[i]);
                        isSetUserNm = true;
                    }
                    
                }
// 2018/09/25 MOD END

                ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, sb.toString());
            }

            String wfEndTs = "";
            String eventType = "";
            String toUsrIsAdmin = (String) rs.getString("TOUSER_ISADMIN");
            boolean isAuto = false;

            String autoflg = (String) rs.getString("WFEVENTAUTOFLG");
            if (autoflg != null) {
                if (S21StringUtil.isNotEmpty(autoflg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(autoflg)) {
                        isAuto = true;
                    }
                }
            }

            if (hasAdminUser && (isSetUserNm == false)){
// 2018/09/25 MOD START
                // ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, ADMIN_OPERATOR_NAME);
                ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, ADMIN_RIGHTS);
// 2018/09/25 MOD END
            }

            String toUserId = (String) rs.getString("TOUSRID");
// 2018/09/25 ADD START
            String toUserNm = (String) rs.getString("TOUSRNM");

            if (S21StringUtil.isEmpty(toUserNm)) {
                toUserNm = "";
            }
// 2018/09/25 ADD END

            if (toUserId != null) {
                if (isAuto) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxWfActOpNm_A, ACT_USER_WFSYSTEM);

                    if (isSetUserNm == false) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, ACT_USER_WFSYSTEM);
                    }
                } else if (ZYPConstant.FLG_ON_1.equals(toUsrIsAdmin)) {
// 2018/09/25 MOD START
//                    ZYPEZDItemValueSetter.setValue(fMsg.xxWfActOpNm_A, ADMIN_OPERATOR_NAME);
                    ZYPEZDItemValueSetter.setValue(fMsg.xxWfActOpNm_A, String.format("%s %s (%s)", toUserId, toUserNm, ADMIN_RIGHTS));
// 2018/09/25 MOD END

                    if (isSetUserNm == false) {
// 2018/09/25 MOD START
//                        ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, ADMIN_OPERATOR_NAME);
                        ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, String.format("%s %s (%s)", toUserId, toUserNm, ADMIN_RIGHTS));
// 2018/09/25 MOD END
                    }
                } else {
// 2018/09/25 DEL START
//                    String toUserNm = (String) rs.getString("TOUSRNM");
//
//                    if (S21StringUtil.isEmpty(toUserNm)) {
//                        toUserNm = "";
//                    }
// 2018/09/25 DEL END
                    ZYPEZDItemValueSetter.setValue(fMsg.xxWfActOpNm_A, String.format("%s %s", toUserId, toUserNm));

                    if (isSetUserNm == false) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxWfAsgToNm_A, String.format("%s %s", toUserId, toUserNm));
                    }
                }
            }

            // Action
            String actNm = (String) rs.getString("LEAVEWFCONDNM");

            if (S21StringUtil.isNotEmpty(actNm)) {
                if (isAuto) {
                    ZYPEZDItemValueSetter.setValue(fMsg.actWfCondNm_A, "AUTO " + actNm);
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.actWfCondNm_A, actNm);
                }
            } else {
                fMsg.actWfCondNm_A.clear();
            }

            // Comment
            String prntCmnt = (String) rs.getString("WFCMNTTXT");
            if (prntCmnt != null) {
                if (prntCmnt.length() > MAX_CMNT_L) {
                    prntCmnt = prntCmnt.substring(0, (MAX_CMNT_L - 4)) + TEXTAREA_OMIT;
                }
                ZYPEZDItemValueSetter.setValue(fMsg.wfCmntTxt_A, prntCmnt);
            } else {
                fMsg.wfCmntTxt_A.clear();
            }

            // End
            wfEndTs = (String) rs.getString("WFENDTS");

            if (S21StringUtil.isNotEmpty(wfEndTs)) {
                String formatStr = ZYPDateUtil.formatEzd17ToDisp(wfEndTs);
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_AE, formatStr);
            } else {
                fMsg.xxDtTm_AE.clear();
            }

            String formatStr = null;

            // Started, Age
            formatStr = ZYPDateUtil.formatEzd17ToDisp((String) rs.getString("WFSTARTTS"));
            if (formatStr != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_AS, formatStr);

                String age = "";

                if (S21StringUtil.isEmpty(wfEndTs)){
                    age = getAge((String) rs.getString("WFSTARTTS"));
                } else {
                    age = getAge((String) rs.getString("WFSTARTTS"), wfEndTs);
                }

                ZYPEZDItemValueSetter.setValue(fMsg.xxBizCalTxt_A, age);
            } else {
                fMsg.xxBizCalTxt_A.clear();
            }

            // Due Date
            String yyyymmdd = (String) rs.getString("WF_DUE_TS");
            if (yyyymmdd != null) {
                if ((S21StringUtil.isNotEmpty(yyyymmdd)) && (yyyymmdd.length() >= ZYPDateUtil.TYPE_YYYYMMDD.length())){
                    formatStr = ZYPDateUtil.formatEzd8ToDisp(yyyymmdd.substring(0, ZYPDateUtil.TYPE_YYYYMMDD.length()));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_AD, formatStr);
                }
            } else {
                fMsg.xxDtTm_AD.clear();
            }

            // Task Status
            String wfWrkItemStsCd = (String) rs.getString("WFWRKITEMSTSCD");
            if (wfWrkItemStsCd != null) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxWfWrkItemStsNm_A, NYXC880001.toTaskStatusNm(wfWrkItemStsCd, eventType));
            } else {
                wfWrkItemStsCd = NYXC880001.toTaskStatusCode(S21NwfWorkItem.STATUS.IDLE);
            }


//          fMsg.XX_WF_PSBL_ACT_NM_A

            String xxWfAprChkFlg = (String) rs.getString("XXWFAPRCHKFLG");
            String xxWfFyiChkFlg = (String) rs.getString("XXWFFYICHKFLG");
            String wfCancFlg = (String) rs.getString("WFCANCCHKFLG");

            String possibleAct = "";

            if (!(wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CLOSE)) || wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CANCEL)))) {
                if (ZYPConstant.CHKBOX_ON_1.equals(wfCancFlg)) {
                    possibleAct = PSBL_ACT_CANCEL;
                }
            }

            if (ZYPConstant.CHKBOX_ON_1.equals(xxWfFyiChkFlg)) {
                possibleAct = PSBL_ACT_FYI;
            }

            if (!(wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CLOSE)) || wfProcStsCd.equals(NYXC880001.toProcStatusCode(S21NwfProcess.STATUS.CANCEL))
                    || wfWrkItemStsCd.equals(NYXC880001.toTaskStatusCode(S21NwfWorkItem.STATUS.CLOSE)) || wfWrkItemStsCd.equals(NYXC880001.toTaskStatusCode(S21NwfWorkItem.STATUS.CANCEL)))) {

                if (ZYPConstant.CHKBOX_ON_1.equals(xxWfAprChkFlg)) {
                    possibleAct = PSBL_ACT_APPROVE;
                }

            }

            // Possible Action
            ZYPEZDItemValueSetter.setValue(fMsg.xxWfPsblActNm_A, possibleAct);

            csvOutFile.write();

        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * get search params
     * @param bizMsg NYEL8810CMsg
     * @param glblCmpyCd String
     * @param loginUser String
     */
    public static Map<String, Object> getParams(NYEL8810CMsg bizMsg, String glblCmpyCd, String loginUser) {

        String usrId = bizMsg.usrId.getValue();
        String usrIdForDisp = usrId;
        String usrNm = "";

        // Search Parameter
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        boolean isAdmin = NYXC880002.isAdministrator(loginUser);

        if (isAdmin) {
            if (loginUser.equals(usrId)) {
                //usrId = NYXC880001constant.ADMIN_OPERATOR_NAME;
            } else {
                params.put("OtherUser", ZYPConstant.FLG_ON_1);
            }
        } else {
            if (!loginUser.equals(usrId)) {
                usrNm = getUserNmFromS21Psn(bizMsg, usrId, loginUser);

                if (S21StringUtil.isEmpty(usrNm)) {
                    return null;
                }
            }
        }

        if (S21StringUtil.isEmpty(usrNm)) {
            usrNm = getUserNmFromS21PsnForAdmin(bizMsg, usrIdForDisp);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.usrNm, usrNm);

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfAprChkFlg.getValue())) {
            params.put("xxWfAprChkFlg", bizMsg.xxWfAprChkFlg.getValue());
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfFyiChkFlg.getValue())) {
            params.put("xxWfFyiChkFlg", bizMsg.xxWfFyiChkFlg.getValue());
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfConfFlg.getValue())) {
            params.put("xxWfConfFlg", bizMsg.xxWfConfFlg.getValue());
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfSubChkFlg.getValue())) {
            params.put("xxWfSubChkFlg", bizMsg.xxWfSubChkFlg.getValue());
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxWfVisChkFlg.getValue())) {
            params.put("xxWfVisChkFlg", bizMsg.xxWfVisChkFlg.getValue());
        }

        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("usrId", usrId);
        }

        if (!bizMsg.wfProcPk.isClear()){
            params.put("wfProcPk", bizMsg.wfProcPk.getValue());
        }

        if (WF_PROC_COND_STS.ALL.equals(bizMsg.procStsCd.getValue())) {
            //params.put("PROC_STS_ALL", bizMsg.procStsCd.getValue());
            params.put("PROC_STS_ACTIVE", bizMsg.procStsCd.getValue());
            params.put("PROC_STS_COMPLETE", bizMsg.procStsCd.getValue());
        } else if (WF_PROC_COND_STS.ACTIVE.equals(bizMsg.procStsCd.getValue())) {
            params.put("PROC_STS_ACTIVE", bizMsg.procStsCd.getValue());
        } else if (WF_PROC_COND_STS.COMPLETED.equals(bizMsg.procStsCd.getValue())) {
            params.put("PROC_STS_COMPLETE", bizMsg.procStsCd.getValue());
        }

        if (!PROC_ID_ALL.equals(bizMsg.wfProcNm.getValue())) {
            params.put("wfProcNm", bizMsg.wfProcNm.getValue());
        }

        if (!TASK_ID_ALL.equals(bizMsg.wfWrkItemNm.getValue())) {
            params.put("wfWrkItemNm", bizMsg.wfWrkItemNm.getValue() + "%");
        }

        if (S21StringUtil.isNotEmpty(bizMsg.wfProcDocId.getValue())){
            params.put("wfProcDocId", bizMsg.wfProcDocId.getValue());
        }

        if (S21StringUtil.isNotEmpty(bizMsg.wfBizAttrbTxt.getValue())) {
            params.put("wfBizAttrbTxt", "%" + bizMsg.wfBizAttrbTxt.getValue() + "%");
        }

        //Start Date Start
        if (S21StringUtil.isNotEmpty(bizMsg.xxFromDt_SS.getValue())){
            params.put("xxFromDt_SS", bizMsg.xxFromDt_SS.getValue() + NYEL8810Constant.MIN_HHMMssSSSS);
        }

        //Start Date End
        if (S21StringUtil.isNotEmpty(bizMsg.xxThruDt_SE.getValue())){
            params.put("xxThruDt_SE", bizMsg.xxThruDt_SE.getValue() + NYEL8810Constant.MAX_HHMMssSSSS);
        } else {
            //params.put("xxThruDt_SE", ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        }

        //Due Date Start
        if (S21StringUtil.isNotEmpty(bizMsg.xxFromDt_DS.getValue())){
            params.put("xxFromDt_DS", bizMsg.xxFromDt_DS.getValue() + NYEL8810Constant.MIN_HHMMssSSSS);
        }

        //Due Date End
        if (S21StringUtil.isNotEmpty(bizMsg.xxThruDt_DE.getValue())){
            params.put("xxThruDt_DE", bizMsg.xxThruDt_DE.getValue() + NYEL8810Constant.MAX_HHMMssSSSS);
        }

        //From
        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm_F.getValue())){
            if ("0".equals(bizMsg.xxGrpFlg_F.getValue())){
                //ALL
                params.put("xxGrpFlg_F1", bizMsg.wfUsrGrpNm_F.getValue());
                
            } else if ("1".equals(bizMsg.xxGrpFlg_F.getValue())){
                //GROUP
                params.put("xxGrpFlg_F2", bizMsg.wfUsrGrpNm_F.getValue());
                
            } else if ("2".equals(bizMsg.xxGrpFlg_F.getValue())){
                //USER
                params.put("xxGrpFlg_F3", bizMsg.wfUsrGrpNm_F.getValue());
            }
        }

        //To
        if (S21StringUtil.isNotEmpty(bizMsg.wfUsrGrpNm_T.getValue())){
            if ("0".equals(bizMsg.xxGrpFlg_T.getValue())){
                //ALL
                params.put("xxGrpFlg_T", true);
                params.put("xxGrpFlg_T1", bizMsg.wfUsrGrpNm_T.getValue());
            } else if ("1".equals(bizMsg.xxGrpFlg_T.getValue())){
                //GROUP
                params.put("xxGrpFlg_T", true);
                params.put("xxGrpFlg_T2", bizMsg.wfUsrGrpNm_T.getValue());
            } else if ("2".equals(bizMsg.xxGrpFlg_T.getValue())){
                //USER
                params.put("xxGrpFlg_T", true);
                params.put("xxGrpFlg_T3", bizMsg.wfUsrGrpNm_T.getValue());
            }
        }

        if (isAdmin){
            params.put("Administrator", ZYPConstant.FLG_ON_1);
        }

        params.put("wfNtfyEventTpCdList", parameterJoin(new String[]{
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_2.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_3.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_4.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_5.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_6.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_7.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_8.getCode(),
                S21NwfNotifyEvent.NTF_EVENT_TYPE.NOTIFY_9.getCode()}));
        params.put("wfNtfyActTpCd", S21NwfNotifyAction.NTF_ACT_TYPE.FYI.getCode());

        return params;

    }
    // QC#21387 ADD END 2018/08/23
}

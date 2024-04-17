package com.canon.cusa.s21.common.NYX.NYXC880001;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.TEXTAREA_NEWLINE;
import java.util.Map;
import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.nwf.core.history.impl.S21NwfHistUserAction;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_TASK_STS;

/**
 *<pre>
 * NYXC880001
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 * 2016/09/08   Fujitsu         Q10627          Update          N/A
 *</pre>
 */
public class NYXC880001 {

    /**
     * getAssigneeNmFromS21Psn
     * @param String assigneeCd
     * @return String for Assignee Name
     */
    @SuppressWarnings("unchecked")
    public static String getAssigneeNmFromS21Psn(String assigneeCd) {

        S21SsmEZDResult ssmResult = NYXC880001Query.getInstance().getFullPsnNm(assigneeCd);

        if (ssmResult.isCodeNotFound()) {
            return "";
        }

        Map<String, String> result = (Map<String, String>) ssmResult.getResultObject();
        String ret = (String) result.get("FULL_PSN_NM");

        if (ret == null) {
            ret = "";
        }

        return ret;
    }

    /**
     * Convert to Process Status Name
     * @param cd
     * @return Process Status Name
     */
    public static String toProcStatusNm(String cd) {
        String ret = "";
        String globalCompanyCode = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String val = ZYPCodeDataUtil.getName(WF_PROC_STS.class.getSimpleName(), globalCompanyCode, cd);

        if (S21StringUtil.isNotEmpty(val)){
            ret = val;
        }

        return ret;
    }
    
    /**
     * Convert to Worklist Process Status Code
     * @param cd
     * @return Process Status Code
     */
    public static String toWorklistProcStatusCd(String cd) {
        String ret = WF_PROC_COND_STS.ACTIVE;
        
        if (S21NwfProcess.STATUS.ABORT.getCode().equals(cd) || S21NwfProcess.STATUS.CANCEL.getCode().equals(cd) || S21NwfProcess.STATUS.CLOSE.getCode().equals(cd)){
            ret = WF_PROC_COND_STS.COMPLETED;
        }

        return ret;
    }
    //

    /**
     * Convert to Process Status Code
     * @param val
     * @return Process Status Code
     */
    public static String toProcStatusCode(S21NwfProcess.STATUS val) {
        return (val.getCode());
    }

    /**
     * Convert To Task Status Name
     * @param cd
     * @return Task Status Name
     */
    public static String toTaskStatusNm(String cd, String eventType) {
        String ret = "";
        String code = cd;

        if (S21StringUtil.isEmpty(cd)) {
            return ret;
        }

        if (S21NwfWorkItem.STATUS.CLOSE.getCode().equals(cd)){
            if ((S21NwfHistUserAction.EVENT.LEV_APPROVE.getCode().equals(eventType)) ||
                    (S21NwfHistUserAction.EVENT.ENT_APPROVE.getCode().equals(eventType))){
                code = WF_TASK_STS.CLOSED_APVL;
            } else if ((S21NwfHistUserAction.EVENT.LEV_REJECT.getCode().equals(eventType)) ||
                    (S21NwfHistUserAction.EVENT.ENT_REJECT.getCode().equals(eventType))){
                code = WF_TASK_STS.CLOSED_REJ;
            } else if ((S21NwfHistUserAction.EVENT.LEV_SUBMIT.getCode().equals(eventType)) ||
                    (S21NwfHistUserAction.EVENT.ENT_SUBMIT.getCode().equals(eventType))){
                code = WF_TASK_STS.CLOSED_SUBMITTED;
            }
        }

        String globalCompanyCode = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        EZDTMsg  tmsg = ZYPCodeDataUtil.findByCode(WF_TASK_STS.class, globalCompanyCode, code);

        if (tmsg == null) {
            return "";
        } else {
            String lowerTableNm = getLowerCaseTableNm(WF_TASK_STS.class.getSimpleName());
            String nmCol = lowerTableNm + "DescTxt";
            return tmsg.getValueString(nmCol, -1);
        }
    }

    /**
     * Convert To Task Status Code
     * @param val
     * @return Task Status Code
     */
    public static String toTaskStatusCode(S21NwfWorkItem.STATUS val) {
        return val.getCode();
    }

    /**
     * Convert To Action Name
     * @param eventCd
     * @return Action Name
     */
    public static String toActNm(String eventCd) {
        String ret = "";

        S21NwfHistUserAction.EVENT event = S21NwfHistUserAction.EVENT.toEnum(eventCd);
        ret = event.toString();

        ret = ret.replaceFirst("ENT_", "");
        ret = ret.replaceFirst("LEV_", "");

        return ret;

    }

    /**
     * Join Parameter
     * @param parameter
     * @return
     */
    public static String parameterJoin(String[] parameter) {
        StringBuilder sb = new StringBuilder();

        if (parameter != null) {

            for (String param : parameter) {
                if (sb.length() <= 0) {
                    sb.append(param);
                } else {
                    sb.append(" , " + param);
                }
            }
        }
        return sb.toString();
    }

    /**
     * toAttr
     * @param attribute
     * @param attributeLbl
     * @return
     */
    public static String toAttr(String attribute, String attributeLbl){
        String attr = "";
        String lbl = "";

        if (S21StringUtil.isNotEmpty(attribute)){
            attr = attribute;
        }

        if (S21StringUtil.isNotEmpty(attributeLbl)){
            lbl = attributeLbl;
        }

        return lbl + TEXTAREA_NEWLINE + attr;
    }

    /**
     * Gets the lower case table nm.
     * @param tableNm the table nm
     * @return the lower case table nm
     */
    private static String getLowerCaseTableNm(String tableNm) {
        tableNm = tableNm.toLowerCase();
        StringBuilder sb = new StringBuilder();
        int _Index = 0;
        while ((_Index = tableNm.indexOf("_")) > -1) {
            sb.append(tableNm.substring(0, _Index));
            tableNm = tableNm.substring(_Index + 1);
            if (tableNm.length() > 0) {
                sb.append(tableNm.substring(0, 1).toUpperCase());
                tableNm = tableNm.substring(1);
            }
        }
        return sb.append(tableNm).toString();
    }

    /**
     * getAssigners
     * @param String assigneeCd
     * @return String for Assigners Name
     */
    public static S21SsmEZDResult getAssigners(String wfBizAppId,
            String myUser, boolean isAdmin, String usrNm, String lastNm, String firstNm, int rowNum) {

        //TODO: process by wfBizAppId

        S21SsmEZDResult ssmResult = NYXC880001Query.getInstance().getAssigners(myUser, isAdmin, usrNm, lastNm, firstNm, rowNum);
        return ssmResult;
    }
}

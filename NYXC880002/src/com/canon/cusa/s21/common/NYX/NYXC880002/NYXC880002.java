package com.canon.cusa.s21.common.NYX.NYXC880002;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.WF_BTN_CTRLTMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NYXC880002
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/19   Fujitsu         Q10627          Update          QC#21139
 *</pre>
 */
public class NYXC880002 {

    /**
     * Administrator Check
     * @param usrId
     * @return
     */
    public static boolean isAdministrator(String usrId) {
        boolean ret = false;

        S21SsmEZDResult userList = NYXC880002Query.getInstance().isAdministrator(usrId);

        if (userList.isCodeNormal()) {
            List userltList = (List) userList.getResultObject();

            if (userltList.size() > 0) {
                Map userMap = (Map) userltList.get(0);
                String isAdmin = (String) userMap.get("ISADMIN");

                if (ZYPConstant.FLG_ON_1.equals(isAdmin)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

// 2018/09/19 ADD START QC#21139
    /**
     * Get General Users
     * @param usrIdList
     * @return generalUsers
     */
    public static List<String> getGeneralUsers(List<String> usrIdList) {
        List<String> adminList = new ArrayList<String>();
        List<String> generalList = new ArrayList<String>();

        // Create Admin User List

        S21SsmEZDResult userList = NYXC880002Query.getInstance().getAdministrators(usrIdList);
        if (userList.isCodeNormal()) {
            List<Map> userltList = (List<Map>) userList.getResultObject();
            for (Map userlt: userltList) {
                String adminUser = (String) userlt.get("USR_ID");
                if (adminUser != null && !adminUser.isEmpty()){
                    adminList.add(adminUser);
                }
            }
        }

        // Create General User List

        for (String usrId: usrIdList) {
           boolean isAdmin = false;
            for (String admin: adminList) {
                if (usrId != null && usrId.equalsIgnoreCase(admin)) {
                    isAdmin = true;
                }
            }
            if (!isAdmin && usrId != null && !usrId.isEmpty()) {
                generalList.add(usrId);
            }
        }

        return generalList;
    }
// 2018/09/19 ADD END   QC#21139

    /**
     * isRecvReassignable
     * @param taskID
     * @param usrId
     * @return
     */
    public static boolean isRecvReassignable(BigDecimal taskID, String usrId) {
        boolean ret = false;

        S21SsmEZDResult userList = NYXC880002Query.getInstance().isRecvReassignable(taskID, usrId);

        if (userList.isCodeNormal()) {
            List userltList = (List) userList.getResultObject();

            if (userltList.size() > 0) {
                Map userMap = (Map) userltList.get(0);
                String isRecvRasg = (String) userMap.get("ISRECVRASG");

                if (ZYPConstant.FLG_ON_1.equals(isRecvRasg)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    /**
     * isPossibleBtn
     * @param procName
     * @param btnName
     * @param glblCmpyCd
     * @return
     */
    public static boolean isPossibleBtn(String procName, String btnName, String glblCmpyCd) {
        boolean ret = true;

        WF_BTN_CTRLTMsg condMsg = new WF_BTN_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condMsg.wfProcNm, procName);
        ZYPEZDItemValueSetter.setValue(condMsg.wfBtnNm, btnName);

        condMsg = (WF_BTN_CTRLTMsg) EZDTBLAccessor.findByKey(condMsg);

        if (condMsg != null) {
            if (ZYPCommonFunc.hasValue(condMsg.wfBtnStsCd)) {
                if (ZYPConstant.FLG_OFF_0.equals(condMsg.wfBtnStsCd.getValue())) {
                    ret = false;
                }
            }
        }

        return ret;
    }
}

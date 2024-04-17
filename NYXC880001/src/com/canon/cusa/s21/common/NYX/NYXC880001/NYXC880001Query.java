package com.canon.cusa.s21.common.NYX.NYXC880001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class NYXC880001Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final NYXC880001Query MY_INSTANCE = new NYXC880001Query();

    /**
     * Private constructor
     */
    private NYXC880001Query() {
        super();
    }

    /**
     * Get the NYEL8820Query instance.
     * @return NYEL8820Query instance
     */
    public static NYXC880001Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getFullPsnNm
     * @param assigneeCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFullPsnNm(String assigneeCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnCd", assigneeCd);

        return getSsmEZDClient().queryObject("getFullPsnNm", params);
    }

    /**
     * Get the upper tab name returns null
     * @param bizAppId Business Application ID
     * @return String Upper Tab Name
     */
    @SuppressWarnings("unchecked")
    public String getUpTabName(String bizAppId, String glblCmpyCd) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("BIZ_APP_ID", bizAppId);
        param.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("findUpTabName", param);

        String tabName = "";
        List list = (List) result.getResultObject();
        if (list == null || list.size() == 0) {
            return tabName;
        }
        tabName = ((HashMap<String, String>) list.get(0)).get("UP_TAB_NM");
        if (tabName == null) {
            tabName = "";
        }
        return tabName;
    }

    /**
     * Method name: getProcList <dd>The method explanation: Get
     * ProcessID from UP_TAB,MENU_PROC <dd>Remarks:
     * @param bizAppId
     * @param menuProcGrpCd
     * @param glblCmpyCd
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getMenuProcId(String bizAppId, String menuProcGrpCd, String glblCmpyCd) {
        String menuProcId = "";

        HashMap<String, String> param = new HashMap<String, String>();

        param.put("GLBL_CMPY_CD", glblCmpyCd);
        param.put("MENU_PROC_GRP_CD", menuProcGrpCd);
        param.put("BIZ_APP_ID", bizAppId);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getProcList", param);

        // No Data
        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();
            for (int j = 0; j < resultList.size(); j++) {
                Map map = (Map) resultList.get(j);
                if ((String) map.get("MENU_PROC_ID") != null) {
                    menuProcId = (String) map.get("MENU_PROC_ID");
                }
            }
        }

        return menuProcId;

    }
    
    /**
     * Method name: getProcList <dd>The method explanation: Get
     * ProcessID from UP_TAB,MENU_PROC <dd>Remarks:
     * @param bizAppId
     * @param menuProcGrpCd
     * @param glblCmpyCd
     * @return
     */
    public S21SsmEZDResult getAssigners(String myUser, boolean isAdmin, String usrNm, String lastNm, String firstNm, int rowNum) {

        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("maxRowNum", rowNum);
        param.put("myUser", myUser);
        if (ZYPCommonFunc.hasValue(usrNm)) {
            param.put("usrNm", usrNm);
        }
        if (ZYPCommonFunc.hasValue(lastNm)) {
            param.put("lastNm", lastNm);
        }
        if (ZYPCommonFunc.hasValue(firstNm)) {
            param.put("firstNm", firstNm);
        }

        S21SsmEZDResult ssmResult = null;
        if (isAdmin) {
            ssmResult = getSsmEZDClient().queryObjectList("getAssignersForAdmin", param);
        } else {
            ssmResult = getSsmEZDClient().queryObjectList("getAssigners", param);
        }
        return ssmResult;
    }

}

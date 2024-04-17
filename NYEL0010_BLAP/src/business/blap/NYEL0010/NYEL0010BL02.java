/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved. Date Company
 * Name Create/Update Defect No
 * ----------------------------------------------------------------------
 * 08/03/2009 Fujitsu T.Nakamatsu Create N/A 
 * 09/07/2010 Fujitsu A.Miyamoto Update Performance Tuning
 */
package business.blap.NYEL0010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDFastTBLAccessor;
import business.blap.NYEL0010.constant.NYEL0010Constant;
import business.db.MENU_PROCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfoConstant;
import com.canon.cusa.s21.framework.security.S21AuthorizationException;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.workflow.core.context.S21WfHumanTaskExecutionContext;
import com.canon.cusa.s21.framework.workflow.ezd.business.S21WfBusinessHandlerBL02Support;

public class NYEL0010BL02 extends S21WfBusinessHandlerBL02Support {

    private static S21LRUMap<String, List> submenuMap = new S21LRUMap<String, List>(98);

    private static S21LRUMap<String, Long> keyMapforSubmenu = new S21LRUMap<String, Long>(98);

    private static int lazytimeForsubmenu = 180000;

    private static S21LRUMap<String, List> appIdMap = new S21LRUMap<String, List>(120);

    private static S21LRUMap<String, Long> keyMapforAppId = new S21LRUMap<String, Long>(120);

    private static int lazytimeForAppId = 60000;

    private static S21LRUMap<String, String> wfprocMap = new S21LRUMap<String, String>(120);

    private static S21LRUMap<String, Long> keyMapforwfproc = new S21LRUMap<String, Long>(120);

    private static int lazytimeForwfproc = 300000;

    private static long latestinfotime = 0L;

    private static Map<String, String> msgMap = new HashMap<String, String>();

    private static final int lazytimeinterval = 180000;

    private static final int infoTextMaxLength = 2000;

    private static S21LRUMap<String, String> sysUrlMap = new S21LRUMap<String, String>(25);

    private static S21LRUMap<String, Long> keyMapforSysUrl = new S21LRUMap<String, Long>(25);

    private static int lazytimeForSysUrl = 240000;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg, S21WfHumanTaskExecutionContext s21WfHumanTaskExecutionContext) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NYEL0010_INIT".equals(screenAplID)) {
                doProcess_NYEL0010_INIT((NYEL0010CMsg) cMsg);
            } else if ("NYEL0010_NYEL0020".equals(screenAplID)) {
                doProcess_NYEL0010_NYEL0020((NYEL0010CMsg) cMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NYEL0010_INIT(NYEL0010CMsg cMsg) {

        final S21UserProfileService profileService = getUserProfileService();
        final String userId = profileService.getContextUserInfo().getUserId();

        final String glblCd = getGlobalCompanyCode();
        final String userCompanyCd = profileService.getContextUserInfo().getUserCompanyCode();

        // Order Process
        cMsg.xxWfProcCd_A0.setValue(getWfProcCd(cMsg.menuProcGrpCd_A0.getValue(), glblCd));
        cMsg.bizAppId_A1.setValue(getAppId(userId, cMsg.menuProcId_A1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_A2.setValue(getAppId(userId, cMsg.menuProcId_A2.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_A3.setValue(getAppId(userId, cMsg.menuProcId_A3.getValue(), glblCd, userCompanyCd));

        // SCE
        cMsg.xxWfProcCd_B0.setValue(getWfProcCd(cMsg.menuProcGrpCd_B0.getValue(), glblCd));
        cMsg.bizAppId_B1.setValue(getAppId(userId, cMsg.menuProcId_B1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_B2.setValue(getAppId(userId, cMsg.menuProcId_B2.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_B3.setValue(getAppId(userId, cMsg.menuProcId_B3.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_B4.setValue(getAppId(userId, cMsg.menuProcId_B4.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_B5.setValue(getAppId(userId, cMsg.menuProcId_B5.getValue(), glblCd, userCompanyCd));

        // Invoicing
        cMsg.xxWfProcCd_C0.setValue(getWfProcCd(cMsg.menuProcGrpCd_C0.getValue(), glblCd));
        cMsg.bizAppId_C1.setValue(getAppId(userId, cMsg.menuProcId_C1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_C2.setValue(getAppId(userId, cMsg.menuProcId_C2.getValue(), glblCd, userCompanyCd));

        // AR
        cMsg.xxWfProcCd_D0.setValue(getWfProcCd(cMsg.menuProcGrpCd_D0.getValue(), glblCd));
        cMsg.bizAppId_D1.setValue(getAppId(userId, cMsg.menuProcId_D1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_D2.setValue(getAppId(userId, cMsg.menuProcId_D2.getValue(), glblCd, userCompanyCd));

        // Post-Sales
        cMsg.xxWfProcCd_H1.setValue(getWfProcCd(cMsg.menuProcGrpCd_H1.getValue(), glblCd));
        cMsg.bizAppId_H1.setValue(getAppId(userId, cMsg.menuProcId_H1.getValue(), glblCd, userCompanyCd));
        cMsg.xxWfProcCd_H0.setValue(getWfProcCd(cMsg.menuProcGrpCd_H0.getValue(), glblCd));
        cMsg.bizAppId_H3.setValue(getAppId(userId, cMsg.menuProcId_H3.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_H4.setValue(getAppId(userId, cMsg.menuProcId_H4.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_H5.setValue(getAppId(userId, cMsg.menuProcId_H5.getValue(), glblCd, userCompanyCd));
        cMsg.xxWfProcCd_H2.setValue(getWfProcCd(cMsg.menuProcGrpCd_H2.getValue(), glblCd));
        cMsg.bizAppId_H2.setValue(getAppId(userId, cMsg.menuProcId_H2.getValue(), glblCd, userCompanyCd));

        // Financial Link
        cMsg.xxWfProcCd_J0.setValue(getWfProcCd(cMsg.menuProcGrpCd_J0.getValue(), glblCd));
        cMsg.bizAppId_J1.setValue(getAppId(userId, cMsg.menuProcId_J1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_J2.setValue(getAppId(userId, cMsg.menuProcId_J2.getValue(), glblCd, userCompanyCd));

        // My Process
        cMsg.bizAppId_K1.setValue(getAppId(userId, cMsg.menuProcId_K1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_K2.setValue(getAppId(userId, cMsg.menuProcId_K2.getValue(), glblCd, userCompanyCd));

        // Online Inquiry
        cMsg.bizAppId_L1.setValue(getAppId(userId, cMsg.menuProcId_L1.getValue(), glblCd, userCompanyCd));

        cMsg.bizAppId_M1.setValue(getAppId(userId, cMsg.menuProcId_M1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_M2.setValue(getAppId(userId, cMsg.menuProcId_M2.getValue(), glblCd, userCompanyCd));

        cMsg.othSysUrl_M1.setValue(getSysUrl(cMsg.menuProcId_M1.getValue(), glblCd));
        cMsg.othSysUrl_M2.setValue(getSysUrl(cMsg.menuProcId_M2.getValue(), glblCd));

        // IDS
        cMsg.bizAppId_P1.setValue(getAppId(userId, cMsg.menuProcId_P1.getValue(), glblCd, userCompanyCd));
        cMsg.othSysUrl_P1.setValue(getSysUrl(cMsg.menuProcId_P1.getValue(), glblCd));

        // Master
        cMsg.xxWfProcCd_N0.setValue(getWfProcCd(cMsg.menuProcGrpCd_N0.getValue(), glblCd));
        cMsg.bizAppId_N1.setValue(getAppId(userId, cMsg.menuProcId_N1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_N2.setValue(getAppId(userId, cMsg.menuProcId_N2.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_N3.setValue(getAppId(userId, cMsg.menuProcId_N3.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_N4.setValue(getAppId(userId, cMsg.menuProcId_N4.getValue(), glblCd, userCompanyCd));
        // CodeTable Sub Menu
        cMsg.bizAppId_N5.setValue(checkCdSubMenu(userId, cMsg.menuProcId_N5.getValue(), glblCd, userCompanyCd));
        
        // Admin menu
        cMsg.xxWfProcCd_O0.setValue(getWfProcCd(cMsg.menuProcGrpCd_O0.getValue(), glblCd));
        cMsg.bizAppId_O1.setValue(getAppId(userId, cMsg.menuProcId_O1.getValue(), glblCd, userCompanyCd));

        // Workflow
        cMsg.xxWfProcCd_Z0.setValue(getWfProcCd(cMsg.menuProcGrpCd_Z0.getValue(), glblCd));
        cMsg.bizAppId_Z1.setValue(getAppId(userId, cMsg.menuProcId_Z1.getValue(), glblCd, userCompanyCd));

        /** 
         * START
         * [ADD] ASCC Security
         * C.Kim
         */
        // ASCC (CustomApp)
        cMsg.xxWfProcCd_Q0.setValue(getWfProcCd(cMsg.menuProcGrpCd_Q0.getValue(), glblCd));
        String bizAppId = getAppId(userId, cMsg.menuProcId_Q1.getValue(), glblCd, userCompanyCd);
        cMsg.bizAppId_Q1.setValue(bizAppId);
//        cMsg.othSysUrl_Q1.setValue(getCustomAppUrl(bizAppId)); // <------- Need to get URL from SYS URL or get it by functionID
        cMsg.othSysUrl_Q1.setValue(getSysUrl(cMsg.menuProcId_Q1.getValue(), glblCd));
        /** 
         * END
         * [ADD] ASCC Security
         * C.Kim
         */
        
        // information
        cMsg.menuInfoTxt.setValue(getInfo(glblCd));

    }

    private void doProcess_NYEL0010_NYEL0020(NYEL0010CMsg cMsg) {

        S21UserProfileService profileService = getUserProfileService();
        String userId = profileService.getContextUserInfo().getUserId();
        final String glblCd = getGlobalCompanyCode();
        final String userCompanyCd = profileService.getContextUserInfo().getUserCompanyCode();

        // My Process
        cMsg.bizAppId_K1.setValue(getAppId(userId, cMsg.menuProcId_K1.getValue(), glblCd, userCompanyCd));
        cMsg.bizAppId_K2.setValue(getAppId(userId, cMsg.menuProcId_K2.getValue(), glblCd, userCompanyCd));
    }

    /**
     * Method name: checkCdSubMenu
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param userId String
     * @param processId String
     * @return String
     */
    private String checkCdSubMenu(String userId, String processId, String glblCd, String userCompanyCd) {

        if (processId.equals("")) {
            return "";
        }

        String key = new StringBuilder().append(processId).append(glblCd).append(userCompanyCd).toString();
        List obj = submenuMap.get(key);
        Long latestAccessTime = keyMapforSubmenu.get(key);

        synchronized (this) {
            if (latestAccessTime == null || obj == null || ((System.currentTimeMillis() - latestAccessTime.longValue()) > lazytimeForsubmenu)) {

                keyMapforSubmenu.put(key, System.currentTimeMillis());
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("GLBL_CMPY_CD", glblCd);
                param.put("USR_CMPY_CD", userCompanyCd);
                param.put("NOW_TM", ZYPDateUtil.getCurrentSystemTime("HHmmss"));
                S21SsmEZDResult ssmResult = NYEL0010Query.getInstance().getCDMenuBizAppList(param);

                if (!ssmResult.isCodeNormal()) { // No Data
                    submenuMap.put(key, new ArrayList());
                    return "";
                } else {
                    obj = (List) ssmResult.getResultObject();
                    submenuMap.put(key, obj);
                }
            }
        }

        if (!obj.isEmpty()) {

            S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
            for (Iterator iterator = obj.iterator(); iterator.hasNext();) {
                Map resultMap = (Map) iterator.next();
                String bizAppId = nvl((String) resultMap.get("BIZ_APP_ID"));
                if (profileService.isBusinessAppGranted(userId, bizAppId)) {
                    return NYEL0010Constant.CD_TBL_SUB_MENU;
                }
            }
        }
        return "";

    }

    /**
     * Method name: getAppId
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param userId String
     * @param processId String
     * @return String
     */
    private String getAppId(String userId, String processId, String glblCd, String userCompanyCd) {

        if (processId.equals("")) {
            return "";
        }

        if (processId.equals(S21BusinessProcessInfoConstant.PROCNAME_MYPROCESS)) {
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("GLBL_CMPY_CD", glblCd);
            param.put("USR_CMPY_CD", userCompanyCd);
            param.put("USR_NM", userId);
            param.put("NOW_TM", ZYPDateUtil.getCurrentSystemTime("HHmmss"));
            S21SsmEZDResult ssmResult = NYEL0010Query.getInstance().getMyProcessInfoList(param);
            if (!ssmResult.isCodeNormal()) { // No Data

                // START 2016/12/26 C.Ogaki Add Function Shared My Process
//                return "";
                // Use Shared My Process Setting
                ssmResult = NYEL0010Query.getInstance().getSharedMyProcessInfoList(param);
                if (!ssmResult.isCodeNormal()) { // No Data
                    return "";
                }
                // END   2016/12/26 C.Ogaki Add Function Shared My Process
            }
            // Normal
            List resultList = (List) ssmResult.getResultObject();
            S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
            // Pick a biz app id to transit
            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                Map resultMap = (Map) iterator.next();
                String bizAppId = nvl((String) resultMap.get("BIZ_APP_ID"));
                if (profileService.isBusinessAppGranted(userId, bizAppId)) {

                    return bizAppId;
                }
            }
            return "";
        } else {

            String key = new StringBuilder().append(glblCd).append(processId).append(userCompanyCd).toString();
            List obj = appIdMap.get(key);
            Long latestAccessTime = keyMapforAppId.get(key);

            synchronized (this) {
                if (latestAccessTime == null || obj == null || ((System.currentTimeMillis() - latestAccessTime.longValue()) > lazytimeForAppId)) {
                    keyMapforAppId.put(key, System.currentTimeMillis());
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("GLBL_CMPY_CD", glblCd);
                    param.put("MENU_PROC_ID", processId);
                    param.put("USR_CMPY_CD", userCompanyCd);
                    param.put("NOW_TM", ZYPDateUtil.getCurrentSystemTime("HHmmss"));
                    S21SsmEZDResult ssmResult = NYEL0010Query.getInstance().getTabInfoListForProc(param);
                    if (!ssmResult.isCodeNormal()) { // No Data
                        appIdMap.put(key, new ArrayList());
                        return "";
                    } else {
                        obj = (List) ssmResult.getResultObject();
                        appIdMap.put(key, obj);
                    }
                }
            }

            if (!obj.isEmpty()) {

                S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
                for (Iterator iterator = obj.iterator(); iterator.hasNext();) {
                    Map resultMap = (Map) iterator.next();
                    String bizAppId = nvl((String) resultMap.get("BIZ_APP_ID"));
                    if (profileService.isBusinessAppGranted(userId, bizAppId)) {
                        return bizAppId;
                    }
                }
            }
            return "";
        }

    }
    
    /**
     * Method name: getSysUrl
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param processId String
     * @return String
     */
    private String getSysUrl(String processId, String glblCd) {

        if (processId.equals("")) {
            return "";
        }

        String key = new StringBuilder().append(glblCd).append(processId).toString();
        String obj = sysUrlMap.get(key);
        Long latestAccessTime = keyMapforSysUrl.get(key);

        if (latestAccessTime == null || obj == null || ((System.currentTimeMillis() - latestAccessTime.longValue()) > lazytimeForSysUrl)) {

            keyMapforSysUrl.put(key, System.currentTimeMillis());
            MENU_PROCTMsg tMsg = new MENU_PROCTMsg();
            tMsg.glblCmpyCd.setValue(glblCd);
            tMsg.menuProcId.setValue(processId);
            tMsg = (MENU_PROCTMsg) EZDFastTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                sysUrlMap.put(key, "");
                return "";
            } else {
                String sSysUrl = nvl(tMsg.othSysUrl.getValue());
                sysUrlMap.put(key, sSysUrl);
                return sSysUrl;
            }
        } else {
            return obj;
        }
    }

    /**
     * Method name: getCustomAppUrl
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param bizAppId String
     * @return String
     */
    private String getCustomAppUrl(String bizAppId) {
    	S21InfoLogOutput.println("[START] getCustomAppUrl");
    	
        if (!ZYPCommonFunc.hasValue(bizAppId)) {
            return "";
        }
        
    	String customAppUrl = "";
		try {
			S21InfoLogOutput.println("[getCustomAppUrl] call S21SecurityContextHolder");
			customAppUrl = S21SecurityContextHolder.getContext().getAuthentication().getAuthorizationAction(bizAppId).getResourceAlternativeName();
		} catch (S21AuthorizationException e) {
			S21InfoLogOutput.println("It failed to get URL of CustomApp (XTRNL_NM in RESRC_OBJ), RESRC_OBJ_ID: " + bizAppId);
			return "";
		} catch (Exception e1) {
			S21InfoLogOutput.println("[getCustomAppUrl] got Exception!!!!");
			System.out.println(e1);
		}
        if (!ZYPCommonFunc.hasValue(customAppUrl)) {
        	S21InfoLogOutput.println("There is no URL of CustomApp (XTRNL_NM in RESRC_OBJ), RESRC_OBJ_ID: " + bizAppId);
        	return "";
        }
        S21InfoLogOutput.println("[END] getCustomAppUrl: customAppUrl-" + customAppUrl);
        return customAppUrl;
    }
    
    /**
     * Method name: getWfProcCd
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param userId String
     * @param processId String
     * @return String
     */
    private String getWfProcCd(String processGroupId, String glblCd) {

        if (processGroupId.equals("")) {
            return "";
        }

        String procCodeStrList = "";
        String key = new StringBuilder().append(processGroupId).append(glblCd).toString();
        String obj = wfprocMap.get(key);
        Long latestAccessTime = keyMapforwfproc.get(key);

        if (latestAccessTime == null || obj == null || ((System.currentTimeMillis() - latestAccessTime.longValue()) > lazytimeForwfproc)) {
            keyMapforwfproc.put(key, System.currentTimeMillis());
            List<String> result = new ArrayList<String>();
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("GLBL_CMPY_CD", glblCd);
            param.put("MENU_PROC_GRP_CD", processGroupId);
            S21SsmEZDResult ssmResult = NYEL0010Query.getInstance().getWfBizAppList(param);
            if (!ssmResult.isCodeNormal()) { // No Data
                wfprocMap.put(key, "");
                return "";
            }
            List resultList = (List) ssmResult.getResultObject();
            int i = 0;
            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                Map resultMap = (Map) iterator.next();
                String sBizAppId = nvl((String) resultMap.get("BIZ_APP_ID"));
                result.add(sBizAppId);
                i++;
            }
            String[] bizAppIds = (String[]) result.toArray(new String[0]);
            procCodeStrList = getWfProcessCodesByBizAppIds(bizAppIds);
            wfprocMap.put(key, procCodeStrList);
            return procCodeStrList;
        } else {
            return obj;
        }

    }

    /**
     * Method name: getInfo
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param userId String
     * @param processId String
     * @return String
     */
    private synchronized String getInfo(String glblCd) {

        if ((System.currentTimeMillis() - latestinfotime) > lazytimeinterval || !msgMap.containsKey(glblCd)) {
            // Get BusinessID from UP_TAB,MENU_PROC (ProcessID)
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("GLBL_CMPY_CD", glblCd);
            param.put("NOW_DT", ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
            param.put("NOW_TM", ZYPDateUtil.getCurrentSystemTime("HHmmss"));
            S21SsmEZDResult ssmResult = NYEL0010Query.getInstance().getInformationList(param);
            if (!ssmResult.isCodeNormal()) { // No Data
                return "";
            }
            List resultList = (List) ssmResult.getResultObject();
            StringBuilder infoTxt = new StringBuilder(infoTextMaxLength);
            infoTxt.append("");
            for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
                Map resultMap = (Map) iterator.next();
                infoTxt.append(resultMap.get("MENU_INFO_TXT")).append("\n");
            }
            String msg = infoTxt.toString();
            if (msg.length() >= infoTextMaxLength) {
                msgMap.put(glblCd, msg.substring(0, infoTextMaxLength));
            } else if (msg.length() <= infoTextMaxLength) {
                msgMap.put(glblCd, msg);
            } else {
                msgMap.put(glblCd, "");
            }
            latestinfotime = System.currentTimeMillis();
        }

        return msgMap.get(glblCd);
    }

    /**
     * Method name: nvl
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    private static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}

/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.NYEL0020.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NYEL0020.NYEL0020CMsg;
import business.blap.NYEL0020.NYEL0020Query;
import business.blap.NYEL0020.NYEL0020SMsg;
import business.blap.NYEL0020.constant.NYEL0020Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;


public class NYEL0020CommonLogic implements NYEL0020Constant {

    /**
     * Method name: doInit
     * <dd>The method explanation: Scrn00 Set
     * <dd>Remarks:
     * @param sGblCpyCd String
     * @param cMsg cMsg
     * @param sMsg sMsg
     */
    public static void doInit(String sGblCpyCd, NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        doBTableSet(sGblCpyCd, cMsg);

        doATableSet(sGblCpyCd, cMsg , sMsg);
    }

    /**
     * Method name: doATableSet
     * <dd>The method explanation: set to [A]Table of sMsg 
     * <dd>Remarks:
     * @param sGblCpyCd String
     * @param cMsg cMsg
     * @param sMsg sMsg
     * @return boolean true or false
     */
    public static boolean doATableSet(String sGblCpyCd, NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        if (getGlbCmpNm(sGblCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return false;
        }

        // Get User's usable BusinessID from UserProfile  
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        String sUserId = profileService.getContextUserInfo().getUserId();

        /* To improve performance, it's better to use SSM instead of S21UserProfile, especially on server*/
        //List<String> canAppIDList = profileService.getBizAppIdList();
        List<String> canAppIDList = getCanBizApp(sUserId);

        // No Data
        if (canAppIDList == null || canAppIDList.size() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        // Get BusinessID from UP_TAB,MENU_PROC
        EZDDebugOutput.println(1, "-----------Get BusinessID" , null);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", sGblCpyCd);

        S21SsmEZDResult ssmResult = NYEL0020Query.getInstance().getAppList(param);

        // No Data
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        // Normal
        List resultList = (List) ssmResult.getResultObject();

        // Matching
        int i = 0;

        for (int j = 0; j < resultList.size(); j++) {
            Map map = (Map) resultList.get(j);
            String strMenuProcNm    = "";
            String strUpTabCd       = "";
            String strUpTabNm       = "";
            String strBizAppId      = "";
            String strBizAppNm      = "";
            if ((String) map.get("MENU_PROC_NM") != null) {
                strMenuProcNm    = (String) map.get("MENU_PROC_NM");
            }
            if ((String) map.get("UP_TAB_CD") != null) {
                strUpTabCd       = (String) map.get("UP_TAB_CD");
            }
            if ((String) map.get("UP_TAB_NM") != null) {
                strUpTabNm       = (String) map.get("UP_TAB_NM");
            }
            if ((String) map.get("BIZ_APP_ID") != null) {
                strBizAppId      = (String) map.get("BIZ_APP_ID");
            }
            if ((String) map.get("BIZ_APP_NM") != null) {
                strBizAppNm      = (String) map.get("BIZ_APP_NM");
            }
            // Maching of canApplist 
            boolean canAppFlg = false;
            for (int k = 0; k < canAppIDList.size(); k++) {
                String strCanAppId = canAppIDList.get(k).toString();
                // Can App
                if (strCanAppId.equals(strBizAppId)) {
                    canAppFlg = true;
                    break;
                }
            }

            if (canAppFlg == true) {

                // Maching of My Process 
                boolean myAppFlg = false;

                for (int k = 0; k < cMsg.B.getValidCount(); k++) {
                    String xxUpTabCd = cMsg.B.no(k).upTabCd_B1.getValue();
                    if (xxUpTabCd.equals(strUpTabCd)) {
                        myAppFlg = true;
                        break;
                    }
                }

                if (myAppFlg == false) {
                    sMsg.A.no(i).menuProcNm_A1.setValue(strMenuProcNm);
                    sMsg.A.no(i).upTabCd_A1.setValue(strUpTabCd);
                    sMsg.A.no(i).upTabNm_A1.setValue(strUpTabNm);
                    sMsg.A.no(i).bizAppNm_A1.setValue(strBizAppNm);
                    i++;
                    sMsg.A.setValidCount(i);
                }
            }
        }
        // No Match Data
        if (i == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        // SMsg -> CMsg(1Page)
        for (i = 0; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);

        }
        cMsg.A.setValidCount(i);
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        return true;
    }

    /**
     * Method name: doBTableSet
     * <dd>The method explanation: set to [B]Table of cMsg 
     * <dd>Remarks:
     * @param sGblCpyCd String
     * @param cMsg cMsg
     * @return boolean true or false
     */
    public static boolean doBTableSet(String sGblCpyCd, NYEL0020CMsg cMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        // Get User's usable BusinessID from UserProfile  
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = profileService.getContextUserInfo();
        String sUserId = userInfo.getUserId();

        /* To improve performance, it's better to use SSM instead of S21UserProfile, especially on server*/
        //List<String> canAppIDList = profileService.getBizAppIdList();
        List<String> canAppIDList = getCanBizApp(sUserId);
        
        // No Data
        if (canAppIDList == null || canAppIDList.size() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        // Get My BusinessID from UP_TAB,MY_PROC
        HashMap<String, String> param1 = new HashMap<String, String>();
        param1.put("GLBL_CMPY_CD", sGblCpyCd);
        param1.put("USR_CMPY_CD", userInfo.getUserCompanyCode());
        param1.put("USR_NM", sUserId);
        S21SsmEZDResult ssmResult = NYEL0020Query.getInstance().getMyAppList(param1);

        // Normal
        if (ssmResult.isCodeNormal()) {

            List resultMyList = (List) ssmResult.getResultObject();

            // Matching 
            for (int i = 0; i < resultMyList.size(); i++) {
                Map map = (Map) resultMyList.get(i);
                String strMenuProcNm     = "";
                String strUpTabCd        = "";
                String strUpTabNm        = "";
                String strBizAppId       = "";
                String strBizAppNm       = "";
                String strMyProcUsbleFlg = "";
                if ((String) map.get("MENU_PROC_NM") != null) {
                    strMenuProcNm     = (String) map.get("MENU_PROC_NM");
                }
                if ((String) map.get("UP_TAB_CD") != null) {
                    strUpTabCd        = (String) map.get("UP_TAB_CD");
                }
                if ((String) map.get("UP_TAB_NM") != null) {
                    strUpTabNm        = (String) map.get("UP_TAB_NM");
                }
                if ((String) map.get("BIZ_APP_ID") != null) {
                    strBizAppId       = (String) map.get("BIZ_APP_ID");
                }
                if ((String) map.get("BIZ_APP_NM") != null) {
                    strBizAppNm       = (String) map.get("BIZ_APP_NM");
                }
                if ((String) map.get("MY_PROC_USBLE_FLG") != null) {
                    strMyProcUsbleFlg = (String) map.get("MY_PROC_USBLE_FLG");
                }

                // check
                cMsg.B.no(i).xxErrFlg_B1.setValue("0");
                boolean fCanFlg = false;
                for (int j = 0; j < canAppIDList.size(); j++) {
                    String strCanAppId = canAppIDList.get(j).toString();
                    if (strCanAppId.equals(strBizAppId)) {
                        fCanFlg = true;
                        break;
                    }
                }
                if (fCanFlg == false) {
                    cMsg.B.no(i).xxErrFlg_B1.setValue("1");
                }
                if (!strMyProcUsbleFlg.equals("Y")) {
                    cMsg.B.no(i).xxErrFlg_B1.setValue("1");
                }
                cMsg.B.no(i).menuProcNm_B1.setValue(strMenuProcNm);
                cMsg.B.no(i).upTabCd_B1.setValue(strUpTabCd);
                cMsg.B.no(i).upTabNm_B1.setValue(strUpTabNm);
                cMsg.B.no(i).bizAppNm_B1.setValue(strBizAppNm);
                cMsg.B.setValidCount(i + 1);
            }
        }

        return true;
    }

    /**
     * Method name: getGlbCmpNm
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param sGblCpyCd String
     * @param cMsg NYEL0020CMsg
     * @return boolean true or false
     */
    public static boolean getGlbCmpNm(String sGblCpyCd, NYEL0020CMsg cMsg) {

        // Init
        cMsg.glblCmpyNm.setValue("");

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(sGblCpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return false;
        } else {
            cMsg.glblCmpyNm.setValue(tMsg.glblCmpyNm.getValue());
            return true;
        }
    }
    
    /**
     * Method name: getCanBizApp
     * <dd>The method explanation: Search Security Info.
     * @param ss Stringss
     * @param cMsg NYEL0020CMsg
     * @return boolean true or false
     */
    public static List<String> getCanBizApp(String usrNm) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("USR_NM", usrNm);
        S21SsmEZDResult ssmResult = NYEL0020Query.getInstance().getCanAppList(param);

        List<String> bizAppIDList = new ArrayList();

        if (ssmResult.isCodeNormal()) {
            List resultMyList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultMyList.size(); i++) {
                Map map = (Map) resultMyList.get(i);
                String canBizAppID = "";
                if (map.get("RESRC_OBJ_NM") != null) {
                    canBizAppID = (String) map.get("RESRC_OBJ_NM");
                    bizAppIDList.add(canBizAppID);
                }
            }
        }
        return bizAppIDList;
    }
}

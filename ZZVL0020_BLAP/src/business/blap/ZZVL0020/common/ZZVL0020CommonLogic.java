package business.blap.ZZVL0020.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.ZZVL0020.ZZVL0020CMsg;
import business.blap.ZZVL0020.ZZVL0020Query;
import business.blap.ZZVL0020.ZZVL0020SMsg;
import business.blap.ZZVL0020.constant.ZZVL0020Constant;
import business.db.GLBL_CMPYTMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020CommonLogic {

    /**
     * Method name: doInit
     * <dd>The method explanation: Scrn00 Set
     * <dd>Remarks:
     * @param sGblCpyCd String
     * @param cMsg cMsg
     * @param sMsg sMsg
     */
    public static void doInit(String sGblCpyCd, ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

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
    public static boolean doATableSet(String sGblCpyCd, ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.xxPageShowFromNum_A1.clear();
        cMsg.xxPageShowToNum_A1.clear();
        cMsg.xxPageShowOfNum_A1.clear();

        if (getGlbCmpNm(sGblCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {ZZVL0020Constant.GLOBAL_COMPANY_CODE});
            return false;
        }

        String roleNm = cMsg.C.no(cMsg.xxNum_1.getValueInt()).roleNm_C.getValue();

        List<String> canAppIDList = getCanBizApp(roleNm, sGblCpyCd);

        // No Data
        if (canAppIDList == null || canAppIDList.size() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        // Get BusinessID from UP_TAB,MENU_PROC
        EZDDebugOutput.println(1, "-----------Get BusinessID" , null);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", sGblCpyCd);

        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getAppList(param);

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
                String strCanAppId = canAppIDList.get(k);
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
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).menuProcNm_A1, strMenuProcNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).upTabCd_A1, strUpTabCd);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).upTabNm_A1, strUpTabNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).bizAppNm_A1, strBizAppNm);
                    i++;
                    sMsg.A.setValidCount(i);
                }
            }
        }
        // No Match Data
        if ((i == 0) && (cMsg.B.getValidCount() == 0)) {
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
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A1, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A1, BigDecimal.valueOf(cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A1, BigDecimal.valueOf(sMsg.A.getValidCount()));

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
    public static boolean doBTableSet(String sGblCpyCd, ZZVL0020CMsg cMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        String roleNm = cMsg.C.no(cMsg.xxNum_1.getValueInt()).roleNm_C.getValue();

        List<String> canAppIDList = getCanBizApp(roleNm, sGblCpyCd);

        // No Data
        if (canAppIDList == null || canAppIDList.size() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        // Get My BusinessID from UP_TAB,MY_PROC
        HashMap<String, String> param1 = new HashMap<String, String>();
        param1.put("GLBL_CMPY_CD", sGblCpyCd);
        param1.put("USR_CMPY_CD", sGblCpyCd);
        param1.put("ROLE_NM", roleNm);
        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getSharedMyAppList(param1);

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
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxErrFlg_B1, "0");
                boolean fCanFlg = false;
                for (int j = 0; j < canAppIDList.size(); j++) {
                    String strCanAppId = canAppIDList.get(j).toString();
                    if (strCanAppId.equals(strBizAppId)) {
                        fCanFlg = true;
                        break;
                    }
                }
                if (fCanFlg == false) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxErrFlg_B1, "1");
                }
                if (!strMyProcUsbleFlg.equals("Y")) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxErrFlg_B1, "1");
                }
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).menuProcNm_B1, strMenuProcNm);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).upTabCd_B1, strUpTabCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).upTabNm_B1, strUpTabNm);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bizAppNm_B1, strBizAppNm);
                cMsg.B.setValidCount(i + 1);
            }
        }

        return true;
    }

    /**
     * Method name: getGlbCmpNm
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param sGblCpyCd String
     * @param cMsg ZZVL0020CMsg
     * @return boolean true or false
     */
    public static boolean getGlbCmpNm(String sGblCpyCd, ZZVL0020CMsg cMsg) {

        // Init
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyNm, "");

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, sGblCpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyNm, tMsg.glblCmpyNm.getValue());
            return true;
        }
    }

    /**
     * Method name: getCanBizApp
     * <dd>The method explanation: Search Security Info.
     * @param roleNm String
     * @param sGblCpyCd String
     * @return boolean true or false
     */
    public static List<String> getCanBizApp(String roleNm, String sGblCpyCd) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("ROLE_NM", roleNm);
        param.put("GLBL_CMPY_CD", sGblCpyCd);
        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getCanAppList(param);

        List<String> bizAppIDList = new ArrayList<String>();

        if (ssmResult.isCodeNormal()) {
            List resultMyList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultMyList.size(); i++) {
                Map map = (Map) resultMyList.get(i);
                String canBizAppID = (String) map.get("RESRC_OBJ_NM");
                if (canBizAppID != null) {
                    bizAppIDList.add(canBizAppID);
                }
            }
        }
        return bizAppIDList;
    }

}

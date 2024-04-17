/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0120.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.blap.ZZOL0120.ZZOL0120Query;
import business.blap.ZZOL0120.constant.ZZOL0120Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


public class ZZOL0120CommonLogic implements ZZOL0120Constant {
    /**
     * Method name: doClear00
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param cMsg ZZOL0120CMsg
     */
    public static void doClear00(String sGlblCmpyCd, ZZOL0120CMsg cMsg) {

        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        cMsg.glblCmpyCd.setValue(sGlblCmpyCd);
        cMsg.glblCmpyCd_BK.setValue(sGlblCmpyCd);

    }

    /**
     * Method name: doClear01
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param cMsg ZZOL0120CMsg
     */
    public static void doClear01(ZZOL0120CMsg cMsg) {

        cMsg.menuProcId_B1.clear();
        cMsg.menuProcNm_B1.clear();
        cMsg.xxChkBox_B1.clear();
        cMsg.othSysUrl_B1.clear();

    }

    /**
     * Method name: doClear02
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param cMsg ZZOL0120CMsg
     */
    public static void doClear02(ZZOL0120CMsg cMsg) {

        cMsg.upTabCd_C1.clear();
        cMsg.upTabNm_C1.clear();
        cMsg.upTabSortNum_C1.clear();
        cMsg.bizAppId_C1.clear();
        cMsg.bizAppNm_C1.clear();
        cMsg.myProcUsbleFlg_C1.clear();
        cMsg.upTabUsbleFlg_C1.clear();

    }
    /**
     * Method name: getProcGroupList
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param cMsg ZZOL0120CMsg
     */
    public static void getProcGroupList(String sGlblCmpyCd, ZZOL0120CMsg cMsg) {

        // Get ProcGroupList from MENU_PROC_GRP
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", sGlblCmpyCd);
        S21SsmEZDResult ssmResult = ZZOL0120Query.getInstance().getProcGroupList(param);
        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                String sMenuProcGrpCd       = "";
                String sMenuProcGrpNm       = "";
                BigDecimal bMenuProcGrpsortNum = new BigDecimal(0);
                String sMenuProcGrpDescTxt  = "";
                String sWfAppNm             = "";
                String sUpTime              = "";
                String sUpTimeZone          = "";

                sMenuProcGrpCd        = nvl((String) map.get("MENU_PROC_GRP_CD"));
                sMenuProcGrpNm        = nvl((String) map.get("MENU_PROC_GRP_NM"));
                bMenuProcGrpsortNum   = (BigDecimal) map.get("MENU_PROC_GRP_SORT_NUM");
                sMenuProcGrpDescTxt   = nvl((String) map.get("MENU_PROC_GRP_DESC_TXT"));
                sWfAppNm              = nvl((String) map.get("WF_APP_NM"));
                sUpTime               = nvl((String) map.get("EZUPTIME"));
                sUpTimeZone           = nvl((String) map.get("EZUPTIMEZONE"));
                if (i < cMsg.A.length()) {
                    cMsg.A.no(i).xxChkBox_A2.setValue("N");
                    cMsg.A.no(i).menuProcGrpCd_A2.setValue(sMenuProcGrpCd);
                    cMsg.A.no(i).menuProcGrpNm_A2.setValue(sMenuProcGrpNm);
                    cMsg.A.no(i).menuProcGrpSortNum_A2.setValue(bMenuProcGrpsortNum);
                    cMsg.A.no(i).menuProcGrpDescTxt_A2.setValue(sMenuProcGrpDescTxt);
                    cMsg.A.no(i).wfAppNm_A2.setValue(sWfAppNm);
                    cMsg.A.no(i).ezUpTime_A2.setValue(sUpTime);
                    cMsg.A.no(i).ezUpTimeZone_A2.setValue(sUpTimeZone);
                    cMsg.A.setValidCount(i + 1);
                }
            }
        }
        // No Data
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return;
        }
    }

    /**
     * Method name: getProcList
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param menuProcGrpCd String
     * @param cMsg ZZOL0120CMsg 
     */
    public static void getProcList(String glblCmpyCd, String menuProcGrpCd, ZZOL0120CMsg cMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        // Get getProcList from MENU_PROC
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD"    , glblCmpyCd);
        param.put("MENU_PROC_GRP_CD", menuProcGrpCd);
        S21SsmEZDResult ssmResult = ZZOL0120Query.getInstance().getProcList(param);
        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                String sMenuProcId = "";
                String sMenuProcNm = "";
                String sOthSysFlg  = "";
                String sOthSysUrl  = "";
                String sUpTime     = "";
                String sUpTimeZone = "";

                sMenuProcId  = nvl((String) map.get("MENU_PROC_ID"));
                sMenuProcNm  = nvl((String) map.get("MENU_PROC_NM"));
                sOthSysFlg   = nvl((String) map.get("OTH_SYS_FLG"));
                sOthSysUrl   = nvl((String) map.get("OTH_SYS_URL"));
                sUpTime      = nvl((String) map.get("EZUPTIME"));
                sUpTimeZone  = nvl((String) map.get("EZUPTIMEZONE"));
                if (i < cMsg.B.length()) {
                    cMsg.B.no(i).xxChkBox_B2.setValue("N");
                    cMsg.B.no(i).menuProcId_B2.setValue(sMenuProcId);
                    cMsg.B.no(i).menuProcNm_B2.setValue(sMenuProcNm);
                    cMsg.B.no(i).othSysFlg_B2.setValue(sOthSysFlg);
                    cMsg.B.no(i).othSysUrl_B2.setValue(sOthSysUrl);
                    cMsg.B.no(i).ezUpTime_B2.setValue(sUpTime);
                    cMsg.B.no(i).ezUpTimeZone_B2.setValue(sUpTimeZone);
                    cMsg.B.setValidCount(i + 1);
                }
            }
        }
        // No Data
        if (cMsg.B.getValidCount() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return;
        }
    }

    /**
     * Method name: getUpTabList
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param glblCmpyCd String
     * @param menuProcId String
     * @param cMsg ZZOL0120CMsg
     */
    public static void getUpTabList(String glblCmpyCd, String menuProcId, ZZOL0120CMsg cMsg) {

        cMsg.C.clear();
        cMsg.C.setValidCount(0);

        // Get getUpTabList from UP_TAB
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD"    , glblCmpyCd);
        param.put("MENU_PROC_ID"    , menuProcId);
        S21SsmEZDResult ssmResult = ZZOL0120Query.getInstance().getUpTabList(param);
        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                String sUpTabCd        = "";
                String sUpTabNm        = "";
                BigDecimal bUpTabSortNum = new BigDecimal(0);
                String sBizAppId       = "";
                String sBizAppNm       = "";
                String sMyProcUsbleFlg = "";
                String sUpTabUsbleFlg  = "";
                String sUpTime         = "";
                String sUpTimeZone     = "";

                sUpTabCd        = nvl((String) map.get("UP_TAB_CD"));
                sUpTabNm        = nvl((String) map.get("UP_TAB_NM"));
                bUpTabSortNum   = (BigDecimal) map.get("UP_TAB_SORT_NUM");
                sBizAppId       = nvl((String) map.get("BIZ_APP_ID"));
                sBizAppNm       = nvl((String) map.get("BIZ_APP_NM"));
                sMyProcUsbleFlg = nvl((String) map.get("MY_PROC_USBLE_FLG"));
                sUpTabUsbleFlg  = nvl((String) map.get("UP_TAB_USBLE_FLG"));
                sUpTime         = nvl((String) map.get("EZUPTIME"));
                sUpTimeZone     = nvl((String) map.get("EZUPTIMEZONE"));
                if (i < cMsg.C.length()) {
                    cMsg.C.no(i).xxChkBox_C2.setValue("N");
                    cMsg.C.no(i).upTabCd_C2.setValue(sUpTabCd);
                    cMsg.C.no(i).upTabNm_C2.setValue(sUpTabNm);
                    cMsg.C.no(i).upTabSortNum_C2.setValue(bUpTabSortNum);
                    cMsg.C.no(i).bizAppId_C2.setValue(sBizAppId);
                    cMsg.C.no(i).bizAppNm_C2.setValue(sBizAppNm);
                    cMsg.C.no(i).myProcUsbleFlg_C2.setValue(sMyProcUsbleFlg);
                    cMsg.C.no(i).upTabUsbleFlg_C2.setValue(sUpTabUsbleFlg);
                    cMsg.C.no(i).ezUpTime_C2.setValue(sUpTime);
                    cMsg.C.no(i).ezUpTimeZone_C2.setValue(sUpTimeZone);
                    cMsg.C.setValidCount(i + 1);
                }
            }
        }
        // No Data
        if (cMsg.C.getValidCount() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return;
        }
    }

    /**
     * Method name: getProcGroupMax
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @return String
     */
    public static String getProcGroupMax(String sGlblCmpyCd) {

        String sMenuProcGrpCd = "";
        BigDecimal bMenuProcGrpCd = new BigDecimal(0);
        // Get MAX(MENU_PROC_GRP_CD) from MENU_PROC_GRP
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", sGlblCmpyCd);
        S21SsmEZDResult ssmResult = ZZOL0120Query.getInstance().getProcGroupMax(param);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                sMenuProcGrpCd = nvl((String) map.get("MENU_PROC_GRP_CD"));
            }
        }
        if (sMenuProcGrpCd.equals("")) {
            bMenuProcGrpCd = new BigDecimal(0);
        } else {
            bMenuProcGrpCd = new BigDecimal(sMenuProcGrpCd);
        }
        bMenuProcGrpCd = bMenuProcGrpCd.add(new BigDecimal(1));
        sMenuProcGrpCd = "" + bMenuProcGrpCd;
        sMenuProcGrpCd = ZYPCommonFunc.leftPad(sMenuProcGrpCd, 10, "0");
        return sMenuProcGrpCd;

    }

    /**
     * Method name: getProcMax
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @return String
     */
    public static String getProcMax(String sGlblCmpyCd, String procGrpId) {

        String sMenuProcId = "";
        BigDecimal bMenuProcId = new BigDecimal(0);
        // Get MAX(MENU_PROC_ID) from MENU_PROC
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("MENU_PROC_GRP_CD"    , procGrpId);
        param.put("GLBL_CMPY_CD"    , sGlblCmpyCd);
        S21SsmEZDResult ssmResult = ZZOL0120Query.getInstance().getProcMax(param);
        if (ssmResult.isCodeNormal()) {
            Map map = (Map) ssmResult.getResultObject();
            sMenuProcId = nvl((String) map.get("MENU_PROC_ID"));
            if (sMenuProcId.equals("")) {
                sMenuProcId = procGrpId + "00000001";
            } else {
                bMenuProcId = new BigDecimal(sMenuProcId);
                bMenuProcId = bMenuProcId.add(new BigDecimal(1));
                sMenuProcId = bMenuProcId.toString();
                sMenuProcId = ZYPCommonFunc.leftPad(sMenuProcId, 18, "0");
            }
        }
        return sMenuProcId;
    }

    /**
     * Method name: getUpTabMax
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @return String
     */
    public static String getUpTabMax(String sGlblCmpyCd) {

        String sUpTabCd = "";
        BigDecimal bUpTabCd = new BigDecimal(0);
        // Get MAX(UP_TAB_CD) from UP_TAB
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD"    , sGlblCmpyCd);
        S21SsmEZDResult ssmResult = ZZOL0120Query.getInstance().getUpTabMax(param);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                sUpTabCd = nvl((String) map.get("UP_TAB_CD"));
            }
        }
        if (sUpTabCd.equals("")) {
            bUpTabCd = new BigDecimal(0);
        } else {
            bUpTabCd = new BigDecimal(sUpTabCd);
        }
        bUpTabCd = bUpTabCd.add(new BigDecimal(1));
        sUpTabCd = "" + bUpTabCd;
        sUpTabCd = ZYPCommonFunc.leftPad(sUpTabCd, 10, "0");
        return sUpTabCd;

    }

    /**
     * Method name: getGlbCmpNm
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param sGblCpyCd String
     * @param cMsg ZZOL0120CMsg
     * @return boolean true or false
     */
    public static boolean getGlbCmpNm(String sGblCpyCd, ZZOL0120CMsg cMsg) {

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

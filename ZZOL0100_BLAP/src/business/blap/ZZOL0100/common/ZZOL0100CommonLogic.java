/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0100.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0100.ZZOL0100CMsg;
import business.blap.ZZOL0100.ZZOL0100Query;
import business.blap.ZZOL0100.ZZOL0100SMsg;
import business.blap.ZZOL0100.constant.ZZOL0100Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


public class ZZOL0100CommonLogic implements ZZOL0100Constant {
    /**
     * Method name: doClear
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param cMsg ZZOL0100CMsg
     * @param sMsg ZZOL0100SMsg
     */
    public static void doClear(String sGlblCmpyCd, ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        
        cMsg.glblCmpyCd.setValue(sGlblCmpyCd);
        cMsg.glblCmpyCd_BK.setValue(sGlblCmpyCd);
        cMsg.xxChkBox.setValue("N");

        // pull-down
        for (int i = 0; i < 24; i++) {
            cMsg.xxHrsMn_F2.no(i).setValue(HHMM[i]);
            cMsg.menuEffFromTm_F1.no(i).setValue(HHMM[i].substring(0, 2) + "0000");
        }
        for (int i = 0; i < 24; i++) {
            cMsg.xxHrsMn_T2.no(i).setValue(HHMM[i]);
            //cMsg.menuEffThruTm_T1.no(i).setValue(HHMM[i].substring(0, 2) + "5959");
            cMsg.menuEffThruTm_T1.no(i).setValue(HHMM[i].substring(0, 2) + "0000");
        }
        for (int i = 0; i < 24; i++) {
            cMsg.xxHrsMn_P2.no(i).setValue(HHMM[i]);
            cMsg.menuEffFromTm_P1.no(i).setValue(HHMM[i].substring(0, 2) + "0000");
        }
    }

    /**
     * Method name: getInformationList
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param cMsg ZZOL0100CMsg
     * @param sMsg ZZOL0100SMsg
     * @return String
     */
    public static void getInformationList(String sGlblCmpyCd, ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        
        // Get BusinessID from UP_TAB,MENU_PROC (ProcessID)
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", sGlblCmpyCd);
        S21SsmEZDResult ssmResult = ZZOL0100Query.getInstance().getInformationList(param);
        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();
            
            int i = 0;

            for (int j = 0; j < resultList.size(); j++) {
                Map map = (Map) resultList.get(j);
                BigDecimal bMenuInfoPk = new BigDecimal(0);
                String sMenuInfoTxt    = "";
                String sMenuVwbleFlg   = "";
                String sMenuEffFromDt  = "";
                String sMenuEffFromTm  = "";
                String sMenuEffThruDt  = "";
                String sMenuEffThruTm  = "";
                String sUpTime         = "";
                String sUpTimeZone     = "";
                BigDecimal bMenuInfoSortNum  = new BigDecimal(0);

                bMenuInfoPk      = (BigDecimal) map.get("MENU_INFO_PK");
                sMenuInfoTxt     = nvl((String) map.get("MENU_INFO_TXT"));
                sMenuVwbleFlg    = nvl((String) map.get("MENU_INFO_VWBLE_FLG"));
                sMenuEffFromDt   = nvl((String) map.get("MENU_EFF_FROM_DT"));
                sMenuEffFromTm   = nvl((String) map.get("MENU_EFF_FROM_TM"));
                sMenuEffThruDt   = nvl((String) map.get("MENU_EFF_THRU_DT"));
                sMenuEffThruTm   = nvl((String) map.get("MENU_EFF_THRU_TM"));
                bMenuInfoSortNum = (BigDecimal) map.get("MENU_INFO_SORT_NUM");
                sUpTime          = nvl((String) map.get("EZUPTIME"));
                sUpTimeZone      = nvl((String) map.get("EZUPTIMEZONE"));

                sMsg.A.no(i).xxChkBox_A1.setValue("N");
                sMsg.A.no(i).menuInfoPk_A1.setValue(bMenuInfoPk);
                sMsg.A.no(i).menuInfoTxt_A1.setValue(sMenuInfoTxt);
                sMsg.A.no(i).menuInfoVwbleFlg_A1.setValue(sMenuVwbleFlg);
                sMsg.A.no(i).xxFromDt_A1.setValue(sMenuEffFromDt);
                sMsg.A.no(i).xxHrsMn_A1.setValue(editTime(sMenuEffFromTm));
                sMsg.A.no(i).menuEffFromTm_A1.setValue(sMenuEffFromTm);
                sMsg.A.no(i).xxThruDt_A2.setValue(sMenuEffThruDt);
                sMsg.A.no(i).xxHrsMn_A2.setValue(editTime(sMenuEffThruTm));
                sMsg.A.no(i).menuEffThruTm_A2.setValue(sMenuEffThruTm);
                sMsg.A.no(i).menuInfoSortNum_A1.setValue(bMenuInfoSortNum);
                sMsg.A.no(i).ezUpTime_A1.setValue(sUpTime);
                sMsg.A.no(i).ezUpTimeZone_A1.setValue(sUpTimeZone);
                i++;
                sMsg.A.setValidCount(i);
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
        }
    }

    /**
     * Method name: getInformationPreviewList
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param sYmd String
     * @param sHms String
     * @return String
     */
    public static String getInformationPreviewList(String sGlblCmpyCd, String sYmd, String sHms) {

        // Get BusinessID from UP_TAB,MENU_PROC (ProcessID)
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", sGlblCmpyCd);
        param.put("NOW_DT", sYmd);
        param.put("NOW_TM", sHms);
        S21SsmEZDResult ssmResult = ZZOL0100Query.getInstance().getInformationPreviewList(param);
        if (!ssmResult.isCodeNormal()) { // No Data
            return "";
        }
        // Normal
        List resultList = (List) ssmResult.getResultObject();

        String infoTxt = "";
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Map resultMap = (Map) iterator.next();
            infoTxt = infoTxt + nvl((String) resultMap.get("MENU_INFO_TXT")) + "\n";
        }
        if (infoTxt.length() > 2000) {
            infoTxt = infoTxt.substring(0, 2000);
        }
        return infoTxt;
    }

    /**
     * Method name: getGlbCmpNm
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param sGblCpyCd String
     * @param cMsg ZZOL0100CMsg
     * @return boolean true or false
     */
    public static boolean getGlbCmpNm(String sGblCpyCd, ZZOL0100CMsg cMsg) {

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
     * Method name: editTime
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param hms String
     * @return String
     */
    public static String editTime(String hms) {

        if (hms.length() > 4) {
            hms = hms.substring(0, 2) + ':' + "00";
        } else {
            hms = "";
        }
        return hms;
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

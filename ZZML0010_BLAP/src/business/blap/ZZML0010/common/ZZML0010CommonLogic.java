package business.blap.ZZML0010.common;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZML0010.ZZML0010CMsg;
import business.blap.ZZML0010.ZZML0010Query;
import business.blap.ZZML0010.constant.ZZML0010Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * @author q02673
 */
public class ZZML0010CommonLogic implements ZZML0010Constant {

    /**
     * @param cMsg EZDCMsg
     */
    public static void search(ZZML0010CMsg cMsg) {

        S21SsmEZDResult ssmResult = ZZML0010Query.getInstance().getMailSystemConfig(cMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W", null);
            return;
        }
    }

    /**
     * Method name: chkGlbCmpCd
     * <dd>The method explanation: Search GLBL_CMPY table by Primary Key
     * @param cMsg ZZML0100CMsg
     * @return boolean true or false
     */
    public static boolean chkGlbCmpCd(ZZML0010CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd_S.getValue();

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            cMsg.glblCmpyCd_S.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return false;
        }
        return true;
    }
    
    public static void setLanguageList(ZZML0010CMsg cMsg) {
        int j = 0;
        for (ZZML0010Constant.Language langCd : ZZML0010Constant.Language.values()) {
            // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            // cMsg.langCd_L1.no(j).setValue(langCd.toString());
            cMsg.mlLocId_L1.no(j).setValue(langCd.toString());
            // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
            cMsg.langNm_L1.no(j).setValue(langCd.getLangName());
            j++;
        }
    }
}

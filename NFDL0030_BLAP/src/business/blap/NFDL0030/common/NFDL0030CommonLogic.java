package business.blap.NFDL0030.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.blap.NFDL0030.NFDL0030Query;
import business.blap.NFDL0030.constant.NFDL0030Constant;
import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 05/18/2018   Hitachi         Y.Takeno        Update          QC#24882
 * 06/06/2018   Hitachi         Y.Takeno        Update          QC#24882-1
 *</pre>
 */
public class NFDL0030CommonLogic implements NFDL0030Constant {

    /**
     * Get AR Account Date
     * @param glblCmpyCd String
     * @return String
     */
    public static String getArAcctDt(String glblCmpyCd) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY, glblCmpyCd);
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.subSysCd.setValue(subSysCd);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return null;
        }
        return outMsg.acctDt.getValue();
    }

    /**
     * Get AR Account Year Month
     * @param glblCmpyCd String
     * @return String
     */
    public static String getArAcctYrMth(String glblCmpyCd) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY, glblCmpyCd);
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.subSysCd.setValue(subSysCd);
        inMsg.onlBatTpCd.setValue("1");

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return null;
        }
        return outMsg.acctYrMth.getValue();
    }

    /**
     * Check GL Date
     * @param glblCmpyCd String
     * @param glDt String
     * @return boolean
     */
    public static boolean checkGlDate(String glblCmpyCd, String glDt) {
        String batProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        String arAcctYrMth = NFDL0030CommonLogic.getArAcctYrMth(glblCmpyCd);

        String batProcThisYrMth = batProcDt.substring(0, 6);
        String glYtMth = glDt.substring(0, 6);

        if (arAcctYrMth.equals(batProcThisYrMth)) {
            if (!glYtMth.equals(batProcThisYrMth)) {
                return false;
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            int batProcYear = Integer.parseInt(batProcDt.substring(0, 4));
            int batProcMonth = Integer.parseInt(batProcDt.substring(4, 6));
            calendar.set(batProcYear, batProcMonth, 1);
            calendar.add(Calendar.MONTH, -2);
            String batProcLastYrMth = new SimpleDateFormat("yyyyMM").format(calendar.getTime());

            if (glYtMth.equals(batProcThisYrMth)) {
                return false;
            } else if (glYtMth.equals(batProcLastYrMth)) {

                return false;
            }
        }
        return true;
    }

    // START 2018/05/23 [QC#24882, ADD]
    // START 2018/06/06 [QC#24882-1, DEL]
    //public static boolean checkCatcPsn(String glblCmpyCd,  NFDL0030CMsg bizMsg) {
    //    if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
    //        if (!ZYPCommonFunc.hasValue((String) NFDL0030Query.getInstance().getCtacPsnMailAddrOnAcct(
    //                glblCmpyCd, bizMsg.dsAcctNum_H.getValue()).getResultObject())) {
    //            bizMsg.setMessageInfo(NFDM0048E, new String[]{ MSG_PRM_CONF_LTR, MSG_PRM_CTAC_PSN });
    //            return false;
    //        }
    //        return true;
    //    }
    //    boolean result = true;
    //    for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
    //        if (!ZYPCommonFunc.hasValue((String) NFDL0030Query.getInstance().getCtacPsnMailAddr(
    //                glblCmpyCd, bizMsg.A.no(i).arTrxNum_A1.getValue()).getResultObject())) {
    //            bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, NFDM0048E, new String[]{ MSG_PRM_CONF_LTR, MSG_PRM_CTAC_PSN });
    //            result = false;
    //        }
    //    }
    //    return result;
    //}
    // END   2018/06/06 [QC#24882-1, DEL]

    public static boolean checkCollector(String glblCmpyCd, S21UserProfileService profile, NFDL0030CMsg bizMsg) {
        if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            String cltPsnCd = (String) NFDL0030Query.getInstance().getCltPsnCdOnAcct(glblCmpyCd, bizMsg.dsAcctNum_H.getValue()).getResultObject();
            if (!ZYPCommonFunc.hasValue(cltPsnCd)) {
                bizMsg.setMessageInfo(NFDM0048E, new String[]{ MSG_PRM_CONF_LTR, MSG_PRM_CLTR });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(getCollectorMailAddress(glblCmpyCd, profile, cltPsnCd))) {
                bizMsg.setMessageInfo(NFDM0048E, new String[]{ MSG_PRM_CONF_LTR, MSG_PRM_CLTR });
                return false;
            }
            return true;
        }

        boolean result = true;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//            String cltPsnCd = (String) NFDL0030Query.getInstance().getCltPsnCd(glblCmpyCd, bizMsg.A.no(i).arTrxNum_A1.getValue()).getResultObject();
            String cltPsnCd;
            if (INV_NUM_ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxNum_A1.getValue())) {
                cltPsnCd = (String) NFDL0030Query.getInstance().getCltPsnCdOnAcct(glblCmpyCd, bizMsg.dsAcctNum_H.getValue()).getResultObject();
            } else {
                cltPsnCd = (String) NFDL0030Query.getInstance().getCltPsnCd(glblCmpyCd, bizMsg.A.no(i).arTrxNum_A1.getValue()).getResultObject();
            }
            // END 2023/03/10 S.Nakatani [QC#55645,MOD]
            if (!ZYPCommonFunc.hasValue(cltPsnCd)) {
                bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, NFDM0048E, new String[]{ MSG_PRM_CONF_LTR, MSG_PRM_CLTR });
                result = false;
                continue;
            }
            if (!ZYPCommonFunc.hasValue(getCollectorMailAddress(glblCmpyCd, profile, cltPsnCd))) {
                bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, NFDM0048E, new String[]{ MSG_PRM_CONF_LTR, MSG_PRM_CLTR });
                result = false;
            }
        }
        return result;
    }

    /**
     * getCollectorMailAddress
     * @param glblCmpyCd String
     * @param profile S21UserProfileService
     * @param cltPsnCd String
     * @return String
     */
    public static String getCollectorMailAddress(String glblCmpyCd, S21UserProfileService profile, String cltPsnCd) {
        String defMlAddr = ZYPCodeDataUtil.getVarCharConstValue(AR_CLT_DEF_EML_ADDR, glblCmpyCd);

        // START 2018/05/29 [QC#24882, MOD]
        if (!hasValue(cltPsnCd)) {
            return defMlAddr;
        }
        // END   2018/05/29 [QC#24882, MOD]

        S21UserInfo userInfo = profile.getUserInfoFor(cltPsnCd);
        if (userInfo == null) {
            return defMlAddr;
        }
        if (hasValue(userInfo.getEmailAddress())) {
            return userInfo.getEmailAddress();
        }

        S21UserInfo superVisorInfo = profile.getUserInfoFor(userInfo.getUserDetails().getManagerId());
        if (superVisorInfo == null) {
            return defMlAddr;
        }
        if (hasValue(superVisorInfo.getEmailAddress())) {
            return superVisorInfo.getEmailAddress();
        }

        S21UserInfo managerInfo = profile.getUserInfoFor(superVisorInfo.getUserDetails().getManagerId());
        if (managerInfo == null) {
            return defMlAddr;
        }
        if (hasValue(managerInfo.getEmailAddress())) {
            return managerInfo.getEmailAddress();
        }

        return defMlAddr;
    }
    // END   2018/05/23 [QC#24882, ADD]
}

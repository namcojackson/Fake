/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0910.common;

import static business.blap.NSAL0910.constant.NSAL0910Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0910.NSAL0910CMsg;
import business.blap.NSAL0910.NSAL0910Query;
import business.blap.NSAL0910.NSAL0910SMsg;
import business.blap.NSAL0910.NSAL0910_ASMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

/**
 *<pre>
 * CFS Invoice Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0910CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0910CMsg
     * @param sMsg NSAL0910SMsg
     */
    public static void clearMsg(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0910CMsg
     */
    public static void createPullDown(NSAL0910CMsg cMsg) {
        String dealerCode = ZYPCodeDataUtil.getVarCharConstValue(NSAL0910_DEALER_CODE, cMsg.glblCmpyCd.getValue());
        if (dealerCode != null) {
            String[] dealerCodeArray = dealerCode.split(",", 0);
            for (int i = 0; i < dealerCodeArray.length; i++) {
                setValue(cMsg.dsAcctDlrCd_PC.no(i), dealerCodeArray[i]);
                setValue(cMsg.dsAcctDlrCd_PD.no(i), dealerCodeArray[i]);
            }
        }
        ZYPCodeDataUtil.createPulldownList(CFS_INV_PROC_STS.class, cMsg.cfsInvProcStsCd_PC, cMsg.cfsInvProcStsDescTxt_PD);
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0910CMsg
     * @param sMsg NSAL0910SMsg
     * @param isSubmit boolean
     */
    public static void getSearchData(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg, boolean isSubmit) {
        S21SsmEZDResult ssmResult = NSAL0910Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (!isSubmit) {
            if (ssmResult.isCodeNormal()) {
                // Result > 1000
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                } else {
                    //cMsg.setMessageInfo(NZZM0002I);
                }
            } else {
                // No result
                cMsg.setMessageInfo(ZZZM9001E);
            }
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0910CMsg
     * @param sMsg NSAL0910SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * Get Service Machine Data
     * @param cMsg NSAL0910CMsg
     * @param asMsg NSAL0910_ASMsg
     * @return boolean
     */
    public static boolean getSvcMachData(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();

        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("serNum01", asMsg.cfsSerNum_A.getValue());
        SVC_MACH_MSTRTMsgArray outMsg = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Get Invoice Data
     * @param cMsg NSAL0910CMsg
     * @param asMsg NSAL0910_ASMsg
     * @return String
     */
    public static String getInvData(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg) {
        S21SsmEZDResult ssmResult = NSAL0910Query.getInstance().getInvData(cMsg, asMsg);

        if (ssmResult != null && ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() > 0) {
            Map<String, Object> invInfo = (Map<String, Object>) ssmResult.getResultObject();
            return invInfo.get("DS_CONTR_NUM").toString();
        }
        // No result
        return null;
    }

    /**
     * Get Contract Data
     * @param cMsg NSAL0910CMsg
     * @param asMsg NSAL0910_ASMsg
     * @return String
     */
    public static String getContrData(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg) {
        S21SsmEZDResult ssmResult = NSAL0910Query.getInstance().getContrData(cMsg, asMsg);

        if (ssmResult != null && ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() > 0) {
            Map<String, Object> invInfo = (Map<String, Object>) ssmResult.getResultObject();
            return invInfo.get("DS_CONTR_NUM").toString();
        }
        // No result
        return null;
    }

}

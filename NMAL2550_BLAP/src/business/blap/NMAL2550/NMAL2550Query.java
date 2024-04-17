/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2550;

import static business.blap.NMAL2550.constant.NMAL2550Constant.*;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2550.constant.NMAL2550Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/06/20   Hitachi         K.Kojima        Update          QC#10316
 *</pre>
 */
public final class NMAL2550Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2550Query INSTANCE = new NMAL2550Query();

    /**
     * Constructor.
     */
    private NMAL2550Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2550Query
     */
    public static NMAL2550Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param cMsg NMAL2550CMsg
     * @param sMsg NMAL2550SMsg
     * @param selectColumn int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCodeAndName(NMAL2550CMsg cMsg, NMAL2550SMsg sMsg, int selectColumn) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);

        if (NMAL2550Constant.COMPANY == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_CMPY);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getCmpyWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.AFFILIATE == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_AFFL);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getAfflWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.BRANCH == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_BR);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getBrWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.COST_CENTER == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_CC);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getCcWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.ACCOUNT == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_ACCT);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getAcctWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.PRODUCT == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_PROD);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getProdWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.CHANNEL == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_CH);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getChWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.PROJECT == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_PROJ);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getProjWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.EXTENSION == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_EXTN);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getExtnWithCoaGlSegNotAllwSec", ssmParam, sMsg.A);
        }
        return null;
    }

    /**
     * @param cMsg NMAL2550CMsg
     * @param sMsg NMAL2550SMsg
     * @param selectColumn int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCodeAndNameWithFilter(NMAL2550CMsg cMsg, NMAL2550SMsg sMsg, int selectColumn) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);

        if (NMAL2550Constant.COMPANY == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_CMPY);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getCmpyWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.AFFILIATE == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_AFFL);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getAfflWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.BRANCH == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_BR);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getBrWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.COST_CENTER == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_CC);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getCcWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.ACCOUNT == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_ACCT);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getAcctWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.PRODUCT == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_PROD);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getProdWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.CHANNEL == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_CH);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getChWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.PROJECT == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_PROJ);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getProjWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        if (NMAL2550Constant.EXTENSION == selectColumn) {
            // START 2016/06/20 K.Kojima [QC#10316,ADD]
            ssmParam.put("coaSegNm", COA_SEG_NM_EXTN);
            // END 2016/06/20 K.Kojima [QC#10316,ADD]
            return getSsmEZDClient().queryEZDMsgArray("getExtnWithCoaGlSegNotAllwSecAndFilter", ssmParam, sMsg.A);
        }
        return null;
    }

    /**
     * @param cMsg NMAL2550CMsg
     * @param selectColumn int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getName(NMAL2550CMsg cMsg, int selectColumn) {

        S21UserProfileService profileAccessor = getUserProfileService();
        String globalCmpyCd = profileAccessor.getGlobalCompanyCode();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", 1);
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("globalCmpyCd", globalCmpyCd);

        if (NMAL2550Constant.COMPANY == selectColumn) {
            return getSsmEZDClient().queryObject("getCmpy", ssmParam);
        }
        if (NMAL2550Constant.AFFILIATE == selectColumn) {
            return getSsmEZDClient().queryObject("getAffl", ssmParam);
        }
        if (NMAL2550Constant.BRANCH == selectColumn) {
            return getSsmEZDClient().queryObject("getBr", ssmParam);
        }
        if (NMAL2550Constant.COST_CENTER == selectColumn) {
            return getSsmEZDClient().queryObject("getCc", ssmParam);
        }
        if (NMAL2550Constant.ACCOUNT == selectColumn) {
            return getSsmEZDClient().queryObject("getAcct", ssmParam);
        }
        if (NMAL2550Constant.PRODUCT == selectColumn) {
            return getSsmEZDClient().queryObject("getProd", ssmParam);
        }
        if (NMAL2550Constant.CHANNEL == selectColumn) {
            return getSsmEZDClient().queryObject("getCh", ssmParam);
        }
        if (NMAL2550Constant.PROJECT == selectColumn) {
            return getSsmEZDClient().queryObject("getProj", ssmParam);
        }
        if (NMAL2550Constant.EXTENSION == selectColumn) {
            return getSsmEZDClient().queryObject("getExtn", ssmParam);
        }
        return null;
    }
}

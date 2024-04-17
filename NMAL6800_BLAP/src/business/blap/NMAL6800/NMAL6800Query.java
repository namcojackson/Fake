/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6800;

import static business.blap.NMAL6800.constant.NMAL6800Constant.BUSINESS_ID;

import java.util.HashMap;
import java.util.Map;
import business.blap.NMAL6800.common.NMAL6800CommonLogic;
import business.blap.NMAL6800.constant.NMAL6800Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6800Query myInstance = new NMAL6800Query();

    /**
     * Constructor.
     */
    private NMAL6800Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6800Query
     */
    public static NMAL6800Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getItemStatusList(NMAL6800CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getItemStatusList", ssmParam);
    }

    public S21SsmEZDResult getPlanningGroupList(NMAL6800CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getPlanningGroupList", ssmParam);
    }

    public S21SsmEZDResult getProductLevelName(NMAL6800CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getProductLevelName", ssmParam);
    }

    /**
     * Search
     * @param cMsg NMAL6800CMsg
     * @param sMsg NMAL6800SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL6800CMsg cMsg, NMAL6800SMsg sMsg, String glblCmpyCd, int rownum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        NMAL6800CommonLogic.createSsmPrm(cMsg, glblCmpyCd, rownum, ssmParam);
        if (NMAL6800Constant.XX_MODE_CD_ALL.equals(cMsg.xxModeCd_H1.getValue())) {
	        return getSsmEZDClient().queryEZDMsgArray("searchAllDigits", ssmParam, sMsg.A);
        } else {
            return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
        }
    }
    
    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BUSINESS_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
    
    public S21SsmEZDResult getThirdProdHrch(NMAL6800CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("thirdProdCtrlCd", cMsg.thirdProdCtrlCd_H1.getValue());
        ssmParam.put("mdseStruElmntTpCdPL3", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
        return getSsmEZDClient().queryObjectList("getThirdProdHrch", ssmParam);
    }
}

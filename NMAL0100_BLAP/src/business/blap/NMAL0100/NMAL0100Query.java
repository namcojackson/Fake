/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0100;

import static business.blap.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;

import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0100Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL0100Query myInstance = new NMAL0100Query();

    /**
     * Constructor.
     */
    private NMAL0100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL0100Query
     */
    public static NMAL0100Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getItemStatusList(NMAL0100CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getItemStatusList", ssmParam);
    }

    public S21SsmEZDResult getPlanningGroupList(NMAL0100CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getPlanningGroupList", ssmParam);
    }

    public S21SsmEZDResult getProductLevelName(NMAL0100CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getProductLevelName", ssmParam);
    }

    /**
     * Search
     * @param cMsg NMAL0100CMsg
     * @param sMsg NMAL0100SMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL0100CMsg cMsg, NMAL0100SMsg sMsg, String glblCmpyCd, int rownum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        NMAL0100CommonLogic.createSsmPrm(cMsg, glblCmpyCd, rownum, ssmParam);

        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
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
    public S21SsmEZDResult getThirdProdHrch(NMAL0100CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("thirdProdCtrlCd", cMsg.thirdProdCtrlCd_H1.getValue());
        ssmParam.put("mdseStruElmntTpCdPL3", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
        ssmParam.put("mdseStruElmntTpCdPL2", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
        return getSsmEZDClient().queryObjectList("getThirdProdHrch", ssmParam);
    }
    
}

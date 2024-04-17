/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0310;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ORG_UNITTMsg;
import static business.blap.NSBL0310.constant.NSBL0310Constant.*;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 * 2016/10/18   Hitachi         A.Kohinata      Update          CSA QC#11030
 *</pre>
 */
public final class NSBL0310Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0310Query INSTANCE = new NSBL0310Query();

    /**
     * Constructor.
     */
    private NSBL0310Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL0010Query
     */
    public static NSBL0310Query getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * getOpenSvcTaskSmry.
     * </pre>
     * @param bizMsg NSBL0310CMsg  
     * @param shareMsg NSBL0310SMsg
     * @param orgLayerNum BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOpenSvcTaskSmry(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg, BigDecimal orgLayerNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("svcMgrTpCd", bizMsg.svcMgrTpCd_H.getValue());
        params.put("orgCd", bizMsg.orgCd_HT.getValue());
        if (ORG_LAYER_NUM_1.equals(orgLayerNum)) {
            params.put("firstOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_2.equals(orgLayerNum)) {
            params.put("scdOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_3.equals(orgLayerNum)) {
            params.put("thirdOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_4.equals(orgLayerNum)) {
            params.put("frthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_5.equals(orgLayerNum)) {
            params.put("fifthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_6.equals(orgLayerNum)) {
            params.put("sixthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_7.equals(orgLayerNum)) {
            params.put("svnthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_8.equals(orgLayerNum)) {
            params.put("eightOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_9.equals(orgLayerNum)) {
            params.put("ninthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_10.equals(orgLayerNum)) {
            params.put("tenthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_11.equals(orgLayerNum)) {
            params.put("elvthOrgCd", NOT_NULL);
        }
        // add start 2016/10/18 CSA Defect#11030
        params.put("orgLayerNum", orgLayerNum);
        // add end 2016/10/18 CSA Defect#11030
        params.put("rowNum", shareMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getOpenSvcTaskSmry", params, shareMsg.A);
    }

    /**
     * <pre>
     * getOpenSvcTaskSmry.
     * </pre>
     * @param bizMsg NSBL0310CMsg
     * @param shareMsg NSBL0310SMsg
     * @param orgLayerNum BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOpenSvcTask(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg, BigDecimal orgLayerNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("svcMgrTpCd", bizMsg.svcMgrTpCd_H.getValue());
        params.put("orgCd", bizMsg.orgCd_HT.getValue());
        if (ORG_LAYER_NUM_1.equals(orgLayerNum)) {
            params.put("firstOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_2.equals(orgLayerNum)) {
            params.put("scdOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_3.equals(orgLayerNum)) {
            params.put("thirdOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_4.equals(orgLayerNum)) {
            params.put("frthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_5.equals(orgLayerNum)) {
            params.put("fifthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_6.equals(orgLayerNum)) {
            params.put("sixthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_7.equals(orgLayerNum)) {
            params.put("svnthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_8.equals(orgLayerNum)) {
            params.put("eightOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_9.equals(orgLayerNum)) {
            params.put("ninthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_10.equals(orgLayerNum)) {
            params.put("tenthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_11.equals(orgLayerNum)) {
            params.put("elvthOrgCd", NOT_NULL);
        }
        // add start 2016/10/18 CSA Defect#11030
        params.put("orgLayerNum", orgLayerNum);
        // add end 2016/10/18 CSA Defect#11030
        params.put("rowNum", shareMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getOpenSvcTask", params, shareMsg.A);
    }

    /**
     * getLayerNum
     * @param bizMsg NSBL0310CMsg
     * @return S21SsmEZDResult
     */
    public BigDecimal getLayerNum(NSBL0310CMsg bizMsg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("orgCd", bizMsg.orgCd_HT.getValue());

        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getLayerNum", paramMap);
        return (BigDecimal) rs.getResultObject();
    }

    /**
     * get Direct Sales Organization Unit
     * @param glblCmpyCd String
     * @param orgCd String
     * @return DS_ORG_UNITTMsg
     */
    public DS_ORG_UNITTMsg getDsOrgUnit(String glblCmpyCd, String orgCd) {
        DS_ORG_UNITTMsg tMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, orgCd);
        DS_ORG_UNITTMsg result = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(tMsg);
        return result;
    }
}

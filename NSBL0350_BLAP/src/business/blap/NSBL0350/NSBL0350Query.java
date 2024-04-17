/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0350;

import static business.blap.NSBL0350.constant.NSBL0350Constant.NOT_NULL;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_1;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_2;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_3;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_4;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_5;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_6;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_7;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_8;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_9;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_10;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_LAYER_NUM_11;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_ORG_UNITTMsg;
import business.db.ORG_STRUTMsg;
import business.db.ORG_STRUTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/30   Hitachi         K.Yamada        Update          CSA QC#6081
 *</pre>
 */
public final class NSBL0350Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0350Query INSTANCE = new NSBL0350Query();

    /**
     * Constructor.
     */
    private NSBL0350Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0350Query
     */
    public static NSBL0350Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Init Item Information
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSBL0350_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitItem(Map<String, Object> ssmParam, NSBL0350_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getInitItem", ssmParam, aSMsgArray);
    }

    /**
     * get Init Item Information History
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSBL0350_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInitItemHist(Map<String, Object> ssmParam, NSBL0350_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getInitItemHist", ssmParam, aSMsgArray);
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

    /**
     * get Search Data
     * @param cMsg NSBL0350CMsg
     * @param sMsg NSBL0350SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        // mod start 2016/03/30 CSA Defect#6081
        S21SsmEZDResult srchResult;
        // search SVC_TASK_DLY_RPT
        srchResult = getSsmEZDClient().queryEZDMsgArray("getSrchDtl", getSsmParamDtl(cMsg, sMsg, false), sMsg.A);
        if (srchResult.isCodeNormal()) {
            return srchResult;
        }
        // search SVC_TASK_HIST_RPT
        return getSsmEZDClient().queryEZDMsgArray("getSrchDtl", getSsmParamDtl(cMsg, sMsg, true), sMsg.A);
        // mod end 2016/03/30 CSA Defect#6081
    }

    /**
     * get SsmParam for Layer Name
     * @param cMsg NSBL0350CMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> getSsmParamLayerNum(NSBL0350CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        if (cMsg.xxFromDt_H.getValue().equals(ZYPDateUtil.getSalesDate())) {
            params.put("dlyRpt", NOT_NULL);
        } else {
            params.put("histRpt", NOT_NULL);
        }
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("cratDt", cMsg.xxFromDt_H.getValue());
        params.put("orgCd", cMsg.orgCd_HT.getValue());
        params.put("svcTaskLtstFlg", ZYPConstant.FLG_ON_Y);

        params.put("layerNum1", ORG_LAYER_NUM_1);
        params.put("layerNum2", ORG_LAYER_NUM_2);
        params.put("layerNum3", ORG_LAYER_NUM_3);
        params.put("layerNum4", ORG_LAYER_NUM_4);
        params.put("layerNum5", ORG_LAYER_NUM_5);
        params.put("layerNum6", ORG_LAYER_NUM_6);
        params.put("layerNum7", ORG_LAYER_NUM_7);
        params.put("layerNum8", ORG_LAYER_NUM_8);
        params.put("layerNum9", ORG_LAYER_NUM_9);
        params.put("layerNum10", ORG_LAYER_NUM_10);
        params.put("layerNum11", ORG_LAYER_NUM_11);
        return params;
    }

    /**
     * get SsmParamOther
     * @param cMsg NSBL0350CMsg
     * @param sMsg NSBL0350SMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getSsmParamDtl(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg, boolean searchHistFlg) {
        Map<String, Object> params = new HashMap<String, Object>();

        // mod start 2016/03/30 CSA Defect#6081
        BigDecimal orgLayerNum = null;
        String orgCd = null;

        ORG_STRUTMsg orgStru = getOrgStru(cMsg);
        if (orgStru != null) {
            orgLayerNum = orgStru.orgLayerNum.getValue();
            orgCd = orgStru.orgCd.getValue();
        }

        if (!searchHistFlg) {
            params.put("dlyRpt", NOT_NULL);
        } else {
            params.put("histRpt", NOT_NULL);
        }

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
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("cratDt", cMsg.xxFromDt_H.getValue());
        params.put("orgCd", orgCd);
        params.put("svcTaskLtstFlg", ZYPConstant.FLG_ON_Y);
        params.put("limitCnt", sMsg.A.length() + 1);
        // mod end 2016/03/30 CSA Defect#6081
        params.put("layerNum1", ORG_LAYER_NUM_1);
        params.put("layerNum2", ORG_LAYER_NUM_2);
        params.put("layerNum3", ORG_LAYER_NUM_3);
        params.put("layerNum4", ORG_LAYER_NUM_4);
        params.put("layerNum5", ORG_LAYER_NUM_5);
        params.put("layerNum6", ORG_LAYER_NUM_6);
        params.put("layerNum7", ORG_LAYER_NUM_7);
        params.put("layerNum8", ORG_LAYER_NUM_8);
        params.put("layerNum9", ORG_LAYER_NUM_9);
        params.put("layerNum10", ORG_LAYER_NUM_10);
        params.put("layerNum11", ORG_LAYER_NUM_11);
        return params;
    }

    // add start 2016/03/30 CSA Defect#6081
    private ORG_STRUTMsg getOrgStru(NSBL0350CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.orgCd_HT)) {
            return null;
        }
        ORG_STRUTMsg tMsg = new ORG_STRUTMsg();
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("orgCd01", cMsg.orgCd_HT.getValue());
        tMsg.setSQLID("003");

        ORG_STRUTMsgArray tMsgArray = (ORG_STRUTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    public String getLtstCratDt(NSBL0350CMsg cMsg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        params.put("dlyRpt", NOT_NULL);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getLtstCratDt", params);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        }

        params.remove("dlyRpt");
        params.put("histRpt", NOT_NULL);
        ssmResult  = getSsmEZDClient().queryObject("getLtstCratDt", params);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        }
        return null;
    }
    // add end 2016/03/30 CSA Defect#6081

}

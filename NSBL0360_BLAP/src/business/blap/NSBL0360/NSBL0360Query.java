/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0360;


import static business.blap.NSBL0360.constant.NSBL0360Constant.NOT_NULL;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_10;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_11;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_1;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_2;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_3;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_4;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_5;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_6;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_7;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_8;
import static business.blap.NSBL0360.constant.NSBL0360Constant.ORG_LAYER_NUM_9;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NSBL0360.common.NSBL0360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Sub Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 * 2016/03/30   Hitachi         K.Yamada        Update          CSA QC#6081
 *</pre>
 */
public final class NSBL0360Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0360Query INSTANCE = new NSBL0360Query();

    /**
     * Constructor.
     */
    private NSBL0360Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0360Query
     */
    public static NSBL0360Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSBL0360CMsg
     * @param sMsg NSBL0360SMsg
     * @param cnt int
     */
    public void getSearchData(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg, int cnt) {

        // mod start 2016/03/30 CSA Defect#6081
        // Search SVC_TASK_DLY_RPT
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, false), sMsg.A);
        if (ssmResult.isCodeNormal()) {
            sMsg = NSBL0360CommonLogic.removeMsg(sMsg, cMsg);
            S21SsmEZDResult ssmResult2 = getSsmEZDClient().queryEZDMsgArray("getSearchDataOther", getSsmParamOther(cMsg, sMsg, (sMsg.A.length()) + 1, false), sMsg.A);
            if (ssmResult2.isCodeNormal()) {
                // Result > 1000
                int queryResCnt = ssmResult2.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo("NZZM0001W");

                }
            }
            return;
        }

        // Search SVC_TASK_HIST_RPT
        ssmResult = getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, true), sMsg.A);
        if (ssmResult.isCodeNormal()) {
            sMsg = NSBL0360CommonLogic.removeMsg(sMsg, cMsg);
            S21SsmEZDResult ssmResult2 = getSsmEZDClient().queryEZDMsgArray("getSearchDataOther", getSsmParamOther(cMsg, sMsg, (sMsg.A.length()) + 1, true), sMsg.A);
            if (ssmResult2.isCodeNormal()) {
                // Result > 1000
                int queryResCnt = ssmResult2.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo("NZZM0001W");

                }
            }
            return;
        }

        // No result
        cMsg.setMessageInfo("NZZM0000E");
        // mod end 2016/03/30 CSA Defect#6081
    }

    /**
     * getSsmParam
     * @param cMsg NSBL0360CMsg
     * @param sMsg NSBL0360SMsg
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSsmParam(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg, boolean searchHistFlg) {
        Map<String, Object> params = new HashMap<String, Object>();

        if (!searchHistFlg) {
            params.put("dlyRpt", NOT_NULL);
        } else {
            params.put("histRpt", NOT_NULL);
        }
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("cratDt", cMsg.cratDt_H.getValue());
        params.put("orgCd", cMsg.orgCd_HL.getValue());
        params.put("ltstFlg", ZYPConstant.FLG_ON_Y);

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
     * getSsmParamOther
     * @param cMsg NSBL0360CMsg
     * @param sMsg NSBL0360SMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSsmParamOther(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg, int cnt, boolean searchHistFlg) {
        Map<String, Object> params = new HashMap<String, Object>();

        if (!searchHistFlg) {
            params.put("dlyRpt", NOT_NULL);
        } else {
            params.put("histRpt", NOT_NULL);
        }
        BigDecimal orgLayerNum = null;
        orgLayerNum = sMsg.B.no(0).orgLayerNum_B.getValue();
        params.put("orgLayerNum", orgLayerNum);

        if (ORG_LAYER_NUM_2.equals(orgLayerNum)) {
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
        params.put("cratDt", cMsg.cratDt_H.getValue());
        params.put("orgCd", cMsg.orgCd_HL.getValue());
        params.put("ltstFlg", ZYPConstant.FLG_ON_Y);
        params.put("rowNum", cnt);
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
}

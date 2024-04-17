/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0050;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 * Class name: NFAL0050Query
 * <dd>The class explanation: SSM
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public final class NFAL0050Query extends S21SsmEZDQuerySupport implements NFAL0050Constant {

    /**
     * Singleton instance.
     */
    private static final NFAL0050Query INSTANCE = new NFAL0050Query();

    /**
     * Constructor.
     */
    private NFAL0050Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NFAL0050Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param bizMsg getAllIndTpCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAllIndTpCd(NFAL0050CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getAllIndTpCd", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getActlCdByIndTpCd
     * @param ajePtrnIndTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActlCdByIndTpCd(NFAL0050CMsg bizMsg, String ajePtrnIndTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("ajePtrnIndTpCd", ajePtrnIndTpCd);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getActlCdByIndTpCd", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg NFAL0050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjeIdsGroupByIndTp(NFAL0050CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        // In case paste mode, if the indicator values are null, set default value.
        if (bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            
            if (!ZYPCommonFunc.hasValue(bizMsg.ajePtrnIndTpCd_1V.getValue())) {
                bizMsg.ajePtrnIndTpCd_1V.setValue(DEFAULT_VAL_CD_3);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.ajePtrnIndTpCd_2V.getValue())) {
                bizMsg.ajePtrnIndTpCd_2V.setValue(DEFAULT_VAL_CD_3);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.ajePtrnIndTpCd_3V.getValue())) {
                bizMsg.ajePtrnIndTpCd_3V.setValue(DEFAULT_VAL_CD_3);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.ajePtrnActlCd_1V.getValue())) {
                bizMsg.ajePtrnActlCd_1V.setValue(DEFAULT_VAL_CD_2);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.ajePtrnActlCd_2V.getValue())) {
                bizMsg.ajePtrnActlCd_2V.setValue(DEFAULT_VAL_CD_2);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.ajePtrnActlCd_3V.getValue())) {
                bizMsg.ajePtrnActlCd_3V.setValue(DEFAULT_VAL_CD_2);
            }
        }
        
        ssmParam.put("bizMsg", bizMsg);
        
        // If the values are default value, set null to parameters.
        if (DEFAULT_VAL_CD_3.equals(bizMsg.ajePtrnIndTpCd_1V.getValue())) {
            ssmParam.put("indTpCd_1", null);
        } else {
            ssmParam.put("indTpCd_1", bizMsg.ajePtrnIndTpCd_1V.getValue());
        }
        if (DEFAULT_VAL_CD_3.equals(bizMsg.ajePtrnIndTpCd_2V.getValue())) {
            ssmParam.put("indTpCd_2", null);
        } else {
            ssmParam.put("indTpCd_2", bizMsg.ajePtrnIndTpCd_2V.getValue());
        }
        if (DEFAULT_VAL_CD_3.equals(bizMsg.ajePtrnIndTpCd_3V.getValue())) {
            ssmParam.put("indTpCd_3", null);
        } else {
            ssmParam.put("indTpCd_3", bizMsg.ajePtrnIndTpCd_3V.getValue());
        }
        
        if (DEFAULT_VAL_CD_2.equals(bizMsg.ajePtrnActlCd_1V.getValue())) {
            ssmParam.put("actlCd_1", null);
        } else {
            ssmParam.put("actlCd_1", bizMsg.ajePtrnActlCd_1V.getValue());
        }
        if (DEFAULT_VAL_CD_2.equals(bizMsg.ajePtrnActlCd_2V.getValue())) {
            ssmParam.put("actlCd_2", null);
        } else {
            ssmParam.put("actlCd_2", bizMsg.ajePtrnActlCd_2V.getValue());
        }
        if (DEFAULT_VAL_CD_2.equals(bizMsg.ajePtrnActlCd_3V.getValue())) {
            ssmParam.put("actlCd_3", null);
        } else {
            ssmParam.put("actlCd_3", bizMsg.ajePtrnActlCd_3V.getValue());
        }
        
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getAjeIdsGroupByIndTp", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg NFAL0050CMsg
     * @param hMap HashMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjeIdsGroupByIndTpIfExists(NFAL0050CMsg bizMsg, HashMap<String, String> hMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        ssmParam.put("type1", hMap.get(TYPE_1));
        ssmParam.put("code1", hMap.get(CODE_1));
        ssmParam.put("type2", hMap.get(TYPE_2));
        ssmParam.put("code2", hMap.get(CODE_2));
        ssmParam.put("type3", hMap.get(TYPE_3));
        ssmParam.put("code3", hMap.get(CODE_3));

        return getSsmEZDClient().queryObjectList("getAjeIdsGroupByIndTpIfExists", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg NFAL0050CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResultCountNonDefault(NFAL0050CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("ajeId", bizMsg.ajeId.getValue());
        ssmParam.put("defaultIndTpCd", DEFAULT_VAL_CD_3);
        ssmParam.put("defaultActlCd", DEFAULT_VAL_CD_2);

        return getSsmEZDClient().queryEZDMsg("getResultCountNonDefault", ssmParam, bizMsg);
    }

    /**
     * @param bizMsg NFAL0050CMsg
     * @param segPtrn String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkAjePtrnWithSegPtrn(NFAL0050CMsg bizMsg, String segPtrn) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("segPtrn", segPtrn);

        return getSsmEZDClient().queryObjectList("checkAjePtrnWithSegPtrn", ssmParam, -1, -1);
    }
}

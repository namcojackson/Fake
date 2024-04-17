/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0060;

import java.util.HashMap;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import business.blap.NFAL0060.constant.NFAL0060Constant;
import business.db.AJE_PTRNTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Class name: NFAL0060Query
 * <dd>The class explanation: SSM
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public final class NFAL0060Query extends S21SsmEZDQuerySupport implements NFAL0060Constant {

    /**
     * Singleton instance.
     */
    private static final NFAL0060Query INSTANCE = new NFAL0060Query();

    /**
     * Constructor.
     */
    private NFAL0060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NFAL0060Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param bizMsg getExistAjeIntfcTpCdList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExistAjeIntfcTpCdList(NFAL0060CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getExistAjeIntfcTpCdList", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getExistAjePtrnIndTpCdList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExistAjePtrnIndTpCdList(NFAL0060CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getExistAjePtrnIndTpCdList", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getActlCdByIndTpCd
     * @param ajePtrnIndTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getActlCdByIndTpCd(NFAL0060CMsg bizMsg, String ajePtrnIndTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("ajePtrnIndTpCd", ajePtrnIndTpCd);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getActlCdByIndTpCd", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getActlCdByIndTpCd
     * @param ajeIntfcTblNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getColumnNameByIntfcNm(NFAL0060CMsg bizMsg, String ajeIntfcTblNm) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("ajeIntfcTblNm", ajeIntfcTblNm);

        return getSsmEZDClient().queryObjectList("getColumnNameByIntfcNm", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getCdTblListCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCdTblListCd(NFAL0060CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        ssmParam.put("xxTblNm", bizMsg.xxTblNm.getValue());
        ssmParam.put("xxTblCdColNm", bizMsg.xxTblCdColNm.getValue());
        ssmParam.put("xxTblNmColNm", bizMsg.xxTblNmColNm.getValue());
        ssmParam.put("xxTblSortColNm", bizMsg.xxTblSortColNm.getValue());

        ssmParam.put("rowNum", bizMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getCdTblListCd", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getAjePtrnIndTpByKey
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjePtrnIndTpByKey(NFAL0060CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        
        //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
        ssmParam.put("ajeIntfcTpCd", INTFC_TP_CD);
        //---- end 2016/03/24
        ssmParam.put("ajePtrnIndTpCd", bizMsg.ajePtrnIndTpCd_3.getValue());
        ssmParam.put("ajePtrnActlCd", bizMsg.ajePtrnActlCd_3.getValue());
        ssmParam.put("defaultIndTpCd", DEFAULT_IND_TP_CD);

        ssmParam.put("rowNum", bizMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getAjePtrnIndTpByKey", ssmParam, -1, -1);
    }

    /**
     * @param bizMsg getAjePtrnIndTpByKeyForExclusiveCheck
     * @param ajeIntfcTpCd String
     * @param ajePtrnIndTpCd String
     * @param ajePtrnActlCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjePtrnIndTpByKeyForExclusiveCheck(NFAL0060CMsg bizMsg, String ajeIntfcTpCd, String ajePtrnIndTpCd, String ajePtrnActlCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bizMsg", bizMsg);
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        ssmParam.put("ajeIntfcTpCd", ajeIntfcTpCd);
        ssmParam.put("ajePtrnIndTpCd", ajePtrnIndTpCd);
        ssmParam.put("ajePtrnActlCd", ajePtrnActlCd);
        ssmParam.put("defaultIndTpCd", DEFAULT_IND_TP_CD);

        ssmParam.put("rowNum", bizMsg.A.length() + 1);

        return getSsmEZDClient().queryObjectList("getAjePtrnIndTpByKeyForExclusiveCheck", ssmParam, -1, -1);
    }
    
    //---- start add 2016/03/24
    public S21SsmEZDResult getPtrnToBeDeleted(NFAL0060CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());

        if (hasValue(bizMsg.ajePtrnIndTpCd_3)) {
            ssmParam.put("ptrnIndTp", bizMsg.ajePtrnIndTpCd_3.getValue());
        } else {
            ssmParam.put("ptrnIndTp", bizMsg.ajePtrnIndTpCd_NW.getValue());
        }
        ssmParam.put("intfcTpCd", INTFC_TP_CD);

        return getSsmEZDClient().queryObjectList("getPtrnToBeDeleted", ssmParam);
    }
    //---- end 2016/03/24
}

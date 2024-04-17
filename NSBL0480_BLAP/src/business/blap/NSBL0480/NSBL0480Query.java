/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0480;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.S21_PSNTMsg;
import business.db.SVC_ACCS_PMIT_TECH_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Access Permits Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public final class NSBL0480Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0480Query INSTANCE = new NSBL0480Query();

    /**
     * Constructor.
     */
    private NSBL0480Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0480Query
     */
    public static NSBL0480Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchResourceData
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchResourceData(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchResourceData", getResourceParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getSearchAccessPermitsData
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchAccessPermitsData(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchAccessPermitsData", getSearchAccessPermitsParam(cMsg, cnt), sMsg.B);
    }

    /**
     * get21Pns
     * @param cMsg NSBL0480CMsg
     * @param psnCd String
     * @return S21_PSNTMsg
     */
    public S21_PSNTMsg get21Pns(NSBL0480CMsg cMsg, String psnCd) {

        S21_PSNTMsg psnTMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(psnTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(psnTMsg.psnCd, psnCd);
        return (S21_PSNTMsg) EZDTBLAccessor.findByKey(psnTMsg);
    }

    /**
     * getResourceParam
     * @param cMsg NSBL0480CMsg
     * @param limitCount int
     * @return Map<String, Object>
     */
    private Map<String, Object> getResourceParam(NSBL0480CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("techCd", cMsg.techCd_A1.getValue());
        params.put("effDt", cMsg.slsDt.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    // START 2017/02/01 K.Kitachi [QC#16629, MOD]
    /**
     * getSearchAccessPermitsParam
     * @param cMsg NSBL0480CMsg
     * @param limitCount int
     * @return Map<String, Object>
     */
    private Map<String, Object> getSearchAccessPermitsParam(NSBL0480CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcAccsPmitNum", cMsg.svcAccsPmitNum.getValue());
        params.put("svcPmitLvlTpCd", cMsg.svcPmitLvlTpCd.getValue());
        params.put("svcPmitLvlValTxt", cMsg.xxScrItem30Txt.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("effDt", cMsg.slsDt.getValue());
        params.put("limitCount", limitCount);
        return params;
    }
    // END 2017/02/01 K.Kitachi [QC#16629, MOD]

    /**
     * getSvcAccsPmitTechRelnInfo
     * @param glblCmpyCd String
     * @param svcAccsPmitTechRelnPk BigDecimal
     * @return SVC_ACCS_PMIT_TECH_RELNTMsg
     */
    public SVC_ACCS_PMIT_TECH_RELNTMsg getSvcAccsPmitTechRelnInfo(String glblCmpyCd, BigDecimal svcAccsPmitTechRelnPk) {

        SVC_ACCS_PMIT_TECH_RELNTMsg inTMsg = new SVC_ACCS_PMIT_TECH_RELNTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.svcAccsPmitTechRelnPk, svcAccsPmitTechRelnPk);
        return (SVC_ACCS_PMIT_TECH_RELNTMsg) EZDTBLAccessor.findByKey(inTMsg);
    }

    // START 2017/02/01 K.Kitachi [QC#16629, MOD]
    /**
     * getSvcAccsPmitPk
     * @param cMsg NSBL0480CMsg
     * @param svcAccsPmitNum String
     * @param svcPmitLvlTpCd String
     * @param svcPmitLvlValTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcAccsPmitPk(NSBL0480CMsg cMsg, String svcAccsPmitNum, String svcPmitLvlTpCd, String svcPmitLvlValTxt) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcAccsPmitNum", svcAccsPmitNum);
        params.put("svcPmitLvlTpCd", svcPmitLvlTpCd);
        params.put("svcPmitLvlValTxt", svcPmitLvlValTxt);
        params.put("effDt", cMsg.slsDt.getValue());
        return getSsmEZDClient().queryObject("getSvcAccsPmitPk", params);
    }
    // END 2017/02/01 K.Kitachi [QC#16629, MOD]

    /**
     * getSearchPsn
     * @param cMsg NSBL0480CMsg
     * @param psnCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getSearchPsn(NSBL0480CMsg cMsg, String psnCd) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("psnCd", psnCd);
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("effDt", cMsg.slsDt.getValue());
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        Map<String, Object> psnInfo = (Map<String, Object>) getSsmEZDClient().queryObject("getSearchPsn", params).getResultObject();
        return psnInfo;
    }

    // START 2017/02/01 K.Kitachi [QC#16629, MOD]
    /**
     * getSearchSvcAccsPmitList
     * @param cMsg NSBL0480CMsg
     * @param svcAccsPmitNum String
     * @param svcPmitLvlTpCd String
     * @param svcPmitLvlValTxt String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSearchSvcAccsPmitList(NSBL0480CMsg cMsg, String svcAccsPmitNum, String svcPmitLvlTpCd, String svcPmitLvlValTxt) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcAccsPmitNum", svcAccsPmitNum);
        params.put("svcPmitLvlTpCd", svcPmitLvlTpCd);
        params.put("svcPmitLvlValTxt", svcPmitLvlValTxt);
        params.put("effDt", cMsg.slsDt.getValue());
        List<Map<String, Object>> svcAccsPmitInfo = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSearchSvcAccsPmit", params).getResultObject();
        return svcAccsPmitInfo;
    }
    // END 2017/02/01 K.Kitachi [QC#16629, MOD]

    // START 2017/02/01 K.Kitachi [QC#16629, ADD]
    /**
     * getDtPerOvlpCount
     * @param cMsg NSBL0480CMsg
     * @param svcAccsPmitNum String
     * @param svcPmitLvlTpCd String
     * @param svcPmitLvlValTxt String
     * @param fromDt String
     * @param thruDt String
     * @param svcAccsPmitTechRelnPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getDtPerOvlpCount(NSBL0480CMsg cMsg, String svcAccsPmitNum, String svcPmitLvlTpCd, String svcPmitLvlValTxt, String fromDt, String thruDt, BigDecimal svcAccsPmitTechRelnPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("effDt", cMsg.slsDt.getValue());
        params.put("svcAccsPmitNum", svcAccsPmitNum);
        params.put("svcPmitLvlTpCd", svcPmitLvlTpCd);
        params.put("svcPmitLvlValTxt", svcPmitLvlValTxt);
        params.put("fromDt", fromDt);
        if (ZYPCommonFunc.hasValue(thruDt)) {
            params.put("thruDt", thruDt);
        } else {
            params.put("thruDt", "99991231");
        }
        if (ZYPCommonFunc.hasValue(svcAccsPmitTechRelnPk)) {
            params.put("svcAccsPmitTechRelnPk", svcAccsPmitTechRelnPk);
        }
        return (BigDecimal) getSsmEZDClient().queryObject("getDtPerOvlpCount", params).getResultObject();
    }
    // END 2017/02/01 K.Kitachi [QC#16629, ADD]
}

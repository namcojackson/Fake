/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0280;

import static business.blap.NSBL0280.constant.NSBL0280Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.S21_PSNTMsg;
import business.db.SVC_SKILLTMsg;
import business.db.SVC_SKILLTMsgArray;
import business.db.SVC_SKILL_LVLTMsg;
import business.db.SVC_SKILL_LVLTMsgArray;
import business.db.SVC_SKILL_TPTMsg;
import business.db.SVC_SKILL_TPTMsgArray;
import business.db.TECH_TNG_HISTTMsg;
import business.db.TECH_TNG_HISTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Resource Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 * 2016/12/15   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 *</pre>
 */
public final class NSBL0280Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0280Query INSTANCE = new NSBL0280Query();

    /**
     * Constructor.
     */
    private NSBL0280Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0280Query
     */
    public static NSBL0280Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchDataSvcSkillTp
     * @param cMsg NSBL0290CMsg
     * @return SVC_SKILL_TPTMsg
     */
    public SVC_SKILL_TPTMsg getSearchDataSvcSkillTp(NSBL0280CMsg cMsg) {

        SVC_SKILL_TPTMsg svcSkillTp = new SVC_SKILL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(svcSkillTp.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcSkillTp.svcSkillTpCd, cMsg.svcSkillTpCd_S);
        return (SVC_SKILL_TPTMsg) EZDTBLAccessor.findByKey(svcSkillTp);
    }

    /**
     * getSearchDataSvcSkillTp
     * @param cMsg NSBL0280CMsg
     * @param psnCd String
     * @return SVC_SKILL_TPTMsg
     */
    public S21_PSNTMsg get21Pns(NSBL0280CMsg cMsg, String psnCd) {

        S21_PSNTMsg psnTMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(psnTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(psnTMsg.psnCd, psnCd);
        return (S21_PSNTMsg) EZDTBLAccessor.findByKey(psnTMsg);
    }

    /**
     * getSearchSvcSkillTp
     * @param glblCmpyCd String
     * @return SVC_SKILL_TPTMsgArray
     */
    public SVC_SKILL_TPTMsgArray getSearchSvcSkillTp(String glblCmpyCd) {

        SVC_SKILL_TPTMsg svcSkillTpTMsg = new SVC_SKILL_TPTMsg();
        svcSkillTpTMsg.setSQLID("001");
        svcSkillTpTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_SKILL_TPTMsgArray) EZDTBLAccessor.findByCondition(svcSkillTpTMsg);
    }

    /**
     * getSearchData
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchResourceData(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchResourceData", getResourceParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getSearchData
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchSkillData(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchSkillData", getSkillParam(cMsg, cnt), sMsg.B);
    }

    /**
     * getSkillParam
     * @param cMsg NSBL0280CMsg
     * @param limitCount int
     * @return Map<String, Object>
     */
    private Map<String, Object> getSkillParam(NSBL0280CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcSkillDescTxt", cMsg.svcSkillDescTxt.getValue());
        params.put("svcSkillTpCd", cMsg.svcSkillTpCd_S.getValue());
        params.put("delFlg", ZYPConstant.FLG_OFF_N);
        params.put("effDt", cMsg.slsDt.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    /**
     * getResourceParam
     * @param cMsg NSBL0280CMsg
     * @param limitCount int
     * @return Map<String, Object>
     */
    private Map<String, Object> getResourceParam(NSBL0280CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcSkillResrcTpCd", cMsg.svcSkillResrcTpCd_S.getValue());
        params.put("techCd", cMsg.psnCd.getValue());
        params.put("effDt", cMsg.slsDt.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    /**
     * getTechTngHistInfo
     * @param glblCmpyCd String
     * @param techTngHistPk BigDecimal
     * @return TECH_TNG_HISTTMsg
     */
    public TECH_TNG_HISTTMsg getTechTngHistInfo(String glblCmpyCd, BigDecimal techTngHistPk) {

        TECH_TNG_HISTTMsg inTMsg = new TECH_TNG_HISTTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.techTngHistPk, techTngHistPk);
        return (TECH_TNG_HISTTMsg) EZDTBLAccessor.findByKey(inTMsg);
    }

    /**
     * getSvcSkillLvlInfo
     * @param glblCmpyCd String
     * @param svcSkillLvlGrpCd String
     * @return BigDecimal
     */
    public BigDecimal getSvcSkillLvlInfo(String glblCmpyCd, String svcSkillLvlGrpCd) {

        SVC_SKILL_LVLTMsg inTMsg = new SVC_SKILL_LVLTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("svcSkillLvlGrpCd01", svcSkillLvlGrpCd);
        SVC_SKILL_LVLTMsgArray svcSkillLvlTMsgList = (SVC_SKILL_LVLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        BigDecimal svcSkillLvlPk = null;
        if (svcSkillLvlTMsgList.getValidCount() > 0) {
            SVC_SKILL_LVLTMsg svcSkillLvlTMsg = (SVC_SKILL_LVLTMsg) svcSkillLvlTMsgList.get(0);
            svcSkillLvlPk = svcSkillLvlTMsg.svcSkillLvlPk.getValue();
        }
        return svcSkillLvlPk;
    }

    /**
     * getSvcSkillLvlInfo
     * @param cMsg NSBL0280CMsg
     * @return SVC_SKILL_LVLTMsgArray
     */
    public SVC_SKILL_LVLTMsgArray getSvcSkillLvlInfo(NSBL0280CMsg cMsg) {

        SVC_SKILL_LVLTMsg inTMsg = new SVC_SKILL_LVLTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        return (SVC_SKILL_LVLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    /**
     * getConditionTechTngHistInfo
     * @param cMsg NSBL0280CMsg
     * @return TECH_TNG_HISTTMsgArray
     */
    public TECH_TNG_HISTTMsgArray getConditionTechTngHistInfo(NSBL0280CMsg cMsg) {

        TECH_TNG_HISTTMsg inTMsg = new TECH_TNG_HISTTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("techCd01", cMsg.psnCd.getValue());
        return (TECH_TNG_HISTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    /**
     * getConditionSvcSkillInfo
     * @param cMsg NSBL0280CMsg
     * @param skillName String
     * @return SVC_SKILLTMsgArray
     */
    public SVC_SKILLTMsgArray getConditionSvcSkillInfo(NSBL0280CMsg cMsg, String skillName) {

        SVC_SKILLTMsg inTMsg = new SVC_SKILLTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("svcSkillDescTxt01", skillName);
        return (SVC_SKILLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    /**
     * getSvcSkillLvlInfo
     * @param glblCmpyCd String
     * @param svcSkillLvlPk BigDecimal
     * @return SVC_SKILL_LVLTMsg
     */
    public SVC_SKILL_LVLTMsg getSvcSkillLvlInfo(String glblCmpyCd, BigDecimal svcSkillLvlPk) {

        SVC_SKILL_LVLTMsg inTMsg = new SVC_SKILL_LVLTMsg();
        inTMsg.setSQLID("003");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("svcSkillLvlPk01", svcSkillLvlPk);
        SVC_SKILL_LVLTMsgArray svcSkillLvlTMsgList = (SVC_SKILL_LVLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        SVC_SKILL_LVLTMsg svcSkillLvlTMsg = null;
        if (svcSkillLvlTMsgList.getValidCount() > 0) {
            svcSkillLvlTMsg = (SVC_SKILL_LVLTMsg) svcSkillLvlTMsgList.get(0);
        }
        return svcSkillLvlTMsg;
    }

    /**
     * getSvcSkillInfo
     * @param cMsg NSBL0280CMsg
     * @param svcSkillTpCd String
     * @return SVC_SKILLTMsg
     */
    public SVC_SKILLTMsg getSvcSkillInfo(NSBL0280CMsg cMsg, String svcSkillTpCd) {

        SVC_SKILLTMsg svcSkillTMsg = new SVC_SKILLTMsg();
        setValue(svcSkillTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(svcSkillTMsg.svcSkillTpCd, svcSkillTpCd);
        return (SVC_SKILLTMsg) EZDTBLAccessor.findByKey(svcSkillTMsg);
    }

    // START 2016/12/15 K.Kojima [QC#15653,DEL]
    // /**
    //  * techMstrInfo
    //  * @param glblCmpyCd String
    //  * @param techTocCd String
    //  * @return TECH_MSTRTMsg
    //  */
    // public static TECH_MSTRTMsg techMstrInfo(String glblCmpyCd, String techTocCd) {
    // 
    //     TECH_MSTRTMsg techMstrTMsg = new TECH_MSTRTMsg();
    //     setValue(techMstrTMsg.glblCmpyCd, glblCmpyCd);
    //     setValue(techMstrTMsg.techTocCd, techTocCd);
    //     TECH_MSTRTMsg rtTechMstr = (TECH_MSTRTMsg) EZDTBLAccessor.findByKey(techMstrTMsg);
    //     if (rtTechMstr == null) {
    //         return null;
    //     }
    //     return rtTechMstr;
    // }
    // END 2016/12/15 K.Kojima [QC#15653,DEL]

    // START 2016/12/15 K.Kojima [QC#15653,ADD]
    /**
     * techMstrInfo
     * @param glblCmpyCd String
     * @param techTocCd String
     * @return TECH_MSTRTMsg
     */
    public String techMstrInfo(String glblCmpyCd, String techTocCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("techTocCd", techTocCd);
        ssmParam.put("maxDt", MAX_DT_VAL);
        ssmParam.put("slsDt", slsDt);
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getTechMstr", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return null;
        }
        return (String) res.getResultObject();
    }
    // END 2016/12/15 K.Kojima [QC#15653,ADD]

    /**
     * getSearchSvcSkill
     * @param cMsg NSBL0280CMsg
     * @param skillName String
     * @return S21SsmEZDResult
     */
    public Map<String, Object> getSearchSvcSkill(NSBL0280CMsg cMsg, String skillName) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcSkillTxt", skillName);
        params.put("effDt", cMsg.slsDt.getValue());
        Map<String, Object> svcAccsPmitInfo = (Map<String, Object>) getSsmEZDClient().queryObject("getSearchSvcSkill", params).getResultObject();
        return svcAccsPmitInfo;
    }
}

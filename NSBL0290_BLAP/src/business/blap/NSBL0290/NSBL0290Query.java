/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0290;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_SKILLTMsg;
import business.db.SVC_SKILL_TPTMsg;
import business.db.SVC_SKILL_TPTMsgArray;
import business.db.TECH_TNG_HISTTMsg;
import business.db.TECH_TNG_HISTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 * 2016/10/21   Hitachi         K.Kojima        Update          QC#15137
 *</pre>
 */
public final class NSBL0290Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0290Query INSTANCE = new NSBL0290Query();

    /**
     * Constructor.
     */
    private NSBL0290Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0290Query
     */
    public static NSBL0290Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchDataSvcSkillTp
     * @param cMsg NSBL0290CMsg
     * @return SVC_SKILL_TPTMsg
     */
    public SVC_SKILL_TPTMsg getSearchDataSvcSkillTp(NSBL0290CMsg cMsg) {

        SVC_SKILL_TPTMsg svcSkillTp = new SVC_SKILL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(svcSkillTp.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcSkillTp.svcSkillTpCd, cMsg.svcSkillTpCd_S);
        return (SVC_SKILL_TPTMsg) EZDTBLAccessor.findByKey(svcSkillTp);
    }

    /**
     * getSearchDataSvcSkill
     * @param glblCmpyCd String
     * @param asMsg NSBL0290_ASMsg
     * @return SVC_SKILL_TPTMsg
     */
    public SVC_SKILLTMsg getSearchDataSvcSkill(String glblCmpyCd, NSBL0290_ASMsg asMsg) {

        SVC_SKILLTMsg svcSkill = new SVC_SKILLTMsg();
        ZYPEZDItemValueSetter.setValue(svcSkill.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcSkill.svcSkillNum, asMsg.svcSkillNum);
        return (SVC_SKILLTMsg) EZDTBLAccessor.findByKey(svcSkill);
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
     * getSearchDataTechTngHist
     * @param glblCmpyCd String
     * @param asMsg NSBL0290_ASMsg
     * @return TECH_TNG_HISTTMsgArray
     */
    public TECH_TNG_HISTTMsgArray getSearchDataTechTngHist(String glblCmpyCd, NSBL0290_ASMsg asMsg) {

        TECH_TNG_HISTTMsg techTngHistTMsg = new TECH_TNG_HISTTMsg();
        techTngHistTMsg.setSQLID("002");
        techTngHistTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        techTngHistTMsg.setConditionValue("svcSkillNum01", asMsg.svcSkillNum.getValue());
        techTngHistTMsg.setConditionValue("effThruDt01", asMsg.effThruDt.getValue());
        return (TECH_TNG_HISTTMsgArray) EZDTBLAccessor.findByCondition(techTngHistTMsg);
    }

    /**
     * getSearchData
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, cnt), sMsg.A);
    }

    /**
     * getSsmParam
     * @param cMsg NSBL0290CMsg
     * @param limitCount int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(NSBL0290CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcSkillTpCd", cMsg.svcSkillTpCd_S.getValue());
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        params.put("svcSkillNm", cMsg.svcSkillNm_H.getValue());
        params.put("svcSkillDescTxt", cMsg.svcSkillDescTxt_H.getValue());
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        params.put("limitCount", limitCount);
        return params;
    }
}

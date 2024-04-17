/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0300.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0300.NSBL0300CMsg;
import business.db.SVC_SKILL_LVLTMsg;
import business.db.SVC_SKILL_LVLTMsgArray;
import business.db.SVC_SKILL_LVL_GRPTMsg;
import business.db.SVC_SKILL_LVL_GRPTMsgArray;

/**
 *<pre>
 * Skill Level Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 * 2016/12/06   Hitachi         N.Arai          Create          QC#14204
 *</pre>
 */
public class NSBL0300CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0300CMsg
     */
    public static void clearMsg(NSBL0300CMsg cMsg) {
        cMsg.clear();
        cMsg.A.setValidCount(0);
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0300CMsg
     */
    public static void createPullDown(NSBL0300CMsg cMsg) {
        SVC_SKILL_LVL_GRPTMsgArray outTMsgArray = getAllSvcSkillLvlGrpInfo(cMsg);
        for (int i = 0; i < outTMsgArray.getValidCount(); i++) {
            setValue(cMsg.svcSkillLvlGrpCd_H1.no(i), outTMsgArray.no(i).svcSkillLvlGrpCd);
            setValue(cMsg.svcSkillLvlGrpNm_H2.no(i), outTMsgArray.no(i).svcSkillLvlGrpNm);
        }
    }

    /**
     * getSvcSkillLvlGrpInfo
     * @param cMsg NSBL0300CMsg
     * @return SVC_SKILL_LVL_GRPTMsg
     */
    public static SVC_SKILL_LVL_GRPTMsg getSvcSkillLvlGrpInfo(NSBL0300CMsg cMsg) {

        SVC_SKILL_LVL_GRPTMsg inTMsg = new SVC_SKILL_LVL_GRPTMsg();
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.svcSkillLvlGrpCd, cMsg.svcSkillLvlGrpCd_H3);
        return (SVC_SKILL_LVL_GRPTMsg) EZDTBLAccessor.findByKey(inTMsg);
    }

    /**
     * getSvcSkillLvlInfo
     * @param cMsg NSBL0300CMsg
     * @return SVC_SKILL_LVLTMsgArray
     */
    public static SVC_SKILL_LVLTMsgArray getSvcSkillLvlInfo(NSBL0300CMsg cMsg) {

        SVC_SKILL_LVLTMsg inTMsg = new SVC_SKILL_LVLTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("svcSkillLvlGrpCd01", cMsg.svcSkillLvlGrpCd_H3.getValue());
        return (SVC_SKILL_LVLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    private static SVC_SKILL_LVL_GRPTMsgArray getAllSvcSkillLvlGrpInfo(NSBL0300CMsg cMsg) {

        SVC_SKILL_LVL_GRPTMsg inTMsg = new SVC_SKILL_LVL_GRPTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        return (SVC_SKILL_LVL_GRPTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    }

    /**
     * setHeaderInfo
     * @param cMsg NSBL0300CMsg
     * @param outTMsg SVC_SKILL_LVL_GRPTMsg
     */
    public static void setHeaderInfo(NSBL0300CMsg cMsg, SVC_SKILL_LVL_GRPTMsg outTMsg) {

        EZDMsg.copy(outTMsg, null, cMsg, null);
        setValue(cMsg.ezUpTime, outTMsg.ezUpTime);
        setValue(cMsg.ezUpTimeZone, outTMsg.ezUpTimeZone);
    }

    /**
     * setDetailInfo
     * @param cMsg NSBL0300CMsg
     * @param outTMsgArray SVC_SKILL_LVLTMsgArray
     */
    public static void setDetailInfo(NSBL0300CMsg cMsg, SVC_SKILL_LVLTMsgArray outTMsgArray) {

        EZDMsg.copy(outTMsgArray, "", cMsg.A, "A");
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).ezUpTime_A, outTMsgArray.no(i).ezUpTime);
            setValue(cMsg.A.no(i).ezUpTimeZone_A, outTMsgArray.no(i).ezUpTimeZone);
// START 2016/12/06 N.Arai [QC#14204, MOD]
            setValue(cMsg.A.no(i).xxRecHistCratTs_A, outTMsgArray.no(i).ezInTime);
            setValue(cMsg.A.no(i).xxRecHistCratByNm_A, outTMsgArray.no(i).ezInAplID);;
            setValue(cMsg.A.no(i).xxRecHistUpdTs_A, outTMsgArray.no(i).ezUpTime);
            setValue(cMsg.A.no(i).xxRecHistUpdByNm_A, outTMsgArray.no(i).ezUpUserID);
            setValue(cMsg.A.no(i).xxRecHistTblNm_A, outTMsgArray.no(i).ezTableID);
// END 2016/12/06 N.Arai [QC#14204, MOD]
        }
        cMsg.A.setValidCount(outTMsgArray.getValidCount());
    }
}

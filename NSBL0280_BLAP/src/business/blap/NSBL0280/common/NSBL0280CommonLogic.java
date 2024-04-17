/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0280.common;

import static business.blap.NSBL0280.constant.NSBL0280Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;
import business.blap.NSBL0280.NSBL0280CMsg;
import business.blap.NSBL0280.NSBL0280Query;
import business.blap.NSBL0280.NSBL0280SMsg;
import business.blap.NSBL0280.NSBL0280_ACMsg;
import business.blap.NSBL0280.NSBL0280_ASMsg;
import business.blap.NSBL0280.NSBL0280_BCMsg;
import business.blap.NSBL0280.NSBL0280_BSMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_SKILLTMsg;
import business.db.SVC_SKILLTMsgArray;
import business.db.SVC_SKILL_LVLTMsg;
import business.db.SVC_SKILL_LVLTMsgArray;
import business.db.TECH_TNG_HISTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SKILL_RESRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SKILL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Resource Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 * 2016/11/07   Hitachi         Y.Takeno        Update          QC#15804
 * 2016/11/15   Hitachi         K.Ochiai        Update          QC#15700
 * 2016/12/15   Hitachi         K.Kojima        Update          QC#15653
 *</pre>
 */
public class NSBL0280CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     */
    public static void clearMsg(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0280CMsg
     */
    public static void createPullDown(NSBL0280CMsg cMsg) {

        // Resource Type
        ZYPCodeDataUtil.createPulldownList(SVC_SKILL_RESRC_TP.class, cMsg.svcSkillResrcTpCd_C, cMsg.svcSkillResrcTpDescTxt_D);
        // Skill Type
        ZYPCodeDataUtil.createPulldownList(SVC_SKILL_TP.class, cMsg.svcSkillTpCd_C, cMsg.svcSkillTpDescTxt_D);
    }

    /**
     * isErrorSearchCondition
     * @param cMsg NSBL0280CMsg
     * @return boolean
     */
    public static boolean isErrorSearchCondition(NSBL0280CMsg cMsg) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
// START 2016/11/07 [QC#15804,DEL]
//            TECH_TNG_HISTTMsgArray techTngHistList = NSBL0280Query.getInstance().getConditionTechTngHistInfo(cMsg);
//            if (techTngHistList == null || techTngHistList.getValidCount() == 0) {
//                cMsg.psnCd.setErrorInfo(1, NSBM0084E, new String[] {cMsg.psnCd.getValue(), "Tech Training History" });
//                return false;
//            }
// END 2016/11/07 [QC#15804,DEL]
            ZYPEZDItemValueSetter.setValue(cMsg.psnCd_A, cMsg.psnCd);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillResrcTpCd_B, cMsg.svcSkillResrcTpCd_S);

        } else {
            String skillName = cMsg.svcSkillDescTxt.getValue();
            SVC_SKILLTMsgArray svcSkillList = NSBL0280Query.getInstance().getConditionSvcSkillInfo(cMsg, skillName);
            if (svcSkillList == null || svcSkillList.getValidCount() == 0) {
                cMsg.svcSkillDescTxt.setErrorInfo(1, NSBM0084E, new String[] {skillName, "Service Skill" });
                return false;
            }
        }
        return true;
    }

    /**
     * isErrorSubmitCondition
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     * @return boolean
     */
    public static boolean isErrorSubmitCondition(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        sMsg.xxNum.clear();
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {

            if (!checkEffFromDtResourceType(cMsg)) {
                return false;
            }

            if (!checkSkillNameResourceType(sMsg)) {
                return false;
            }
        } else {

            if (!checkEffFromDtSkillType(cMsg)) {
                return false;
            }

            // START 2016/12/15 K.Kojima [QC#15653,MOD]
            // if (!checkSkillNameSkillType(glblCmpyCd, sMsg)) {
            if (!checkSkillNameSkillType(glblCmpyCd, cMsg, sMsg)) {
            // END 2016/12/15 K.Kojima [QC#15653,MOD]
                return false;
            }
        }

        if (!checkSvcSkillLvlGrpCd(cMsg, sMsg)) {
            return false;
        }

        return true;
    }

    private static boolean checkSkillNameResourceType(NSBL0280SMsg sMsg) {

        for (int row = 0; row < sMsg.A.getValidCount(); row++) {
            String selectedSkillNm = sMsg.A.no(row).svcSkillDescTxt_A.getValue();
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                String skillNm = sMsg.A.no(index).svcSkillDescTxt_A.getValue();
                if ((row != index) && selectedSkillNm.equals(skillNm)) {
                    sMsg.xxNum.setValue(index);
                    // START 2016/11/15 K.Ochiai [QC#15700, MOD]
                    sMsg.A.no(row).svcSkillDescTxt_A.setErrorInfo(1, NSBM0184E);
                    sMsg.A.no(index).svcSkillDescTxt_A.setErrorInfo(1, NSBM0184E);
                    // END 2016/11/15 K.Ochiai [QC#15700, MOD]
                    return false;
                }
            }
        }
        return true;
    }

    // START 2016/12/15 K.Kojima [QC#15653,MOD]
    // private static boolean checkSkillNameSkillType(String glblCmpyCd, NSBL0280SMsg sMsg) {
    private static boolean checkSkillNameSkillType(String glblCmpyCd, NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {
    // END 2016/12/15 K.Kojima [QC#15653,MOD]

        for (int index = 0; index < sMsg.B.getValidCount(); index++) {
            String techCd = sMsg.B.no(index).techCd_B.getValue();

            // START 2016/12/15 K.Kojima [QC#15653,ADD]
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(index).xxSelFlg_B.getValue())) {
                continue;
            }
            // END 2016/12/15 K.Kojima [QC#15653,ADD]

            // START 2016/12/15 K.Kojima [QC#15653,MOD]
            // TECH_MSTRTMsg techMstrTMsg = NSBL0280Query.techMstrInfo(glblCmpyCd, techCd);
            // if (techMstrTMsg == null) {
            String techMstrInfo = NSBL0280Query.getInstance().techMstrInfo(glblCmpyCd, techCd, cMsg.slsDt.getValue());
            if (techMstrInfo == null) {
            // END 2016/12/15 K.Kojima [QC#15653,MOD]
                sMsg.xxNum.setValue(index);
                sMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0084E, new String[] {techCd, "Tech Master" });
                return false;
            }
        }
        return true;
    }

    /**
     * checkEffFromDtResourceType
     * @param cMsg NSBL0280CMsg
     * @return boolean
     */
    public static boolean checkEffFromDtResourceType(NSBL0280CMsg cMsg) {

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(index).effFromDt_A) && ZYPCommonFunc.hasValue(cMsg.A.no(index).effThruDt_A)) {
                String effFromDt = cMsg.A.no(index).effFromDt_A.getValue();
                String effThruDt = cMsg.A.no(index).effThruDt_A.getValue();
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                    cMsg.A.no(index).effFromDt_A.setErrorInfo(1, NSBM0083E);
                    cMsg.A.no(index).effThruDt_A.setErrorInfo(1, NSBM0083E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checkEffFromDtSkillType
     * @param cMsg NSBL0280CMsg
     * @return boolean
     */
    public static boolean checkEffFromDtSkillType(NSBL0280CMsg cMsg) {

        for (int index = 0; index < cMsg.B.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(cMsg.B.no(index).effFromDt_B) && ZYPCommonFunc.hasValue(cMsg.B.no(index).effThruDt_B)) {
                String effFromDt = cMsg.B.no(index).effFromDt_B.getValue();
                String effThruDt = cMsg.B.no(index).effThruDt_B.getValue();
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                    cMsg.B.no(index).effFromDt_B.setErrorInfo(1, NSBM0083E);
                    cMsg.B.no(index).effThruDt_B.setErrorInfo(1, NSBM0083E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * deleteRow
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     * @return boolean
     */
    public static boolean deleteRow(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        int sLineNum = 0;
        int selectLineNum = 0;
        EZDMsgArray asMsg = null;
        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            asMsg = sMsg.A;
            selectLineNum = cMsg.xxRadioBtn_A.getValueInt();
            sLineNum = sMsg.xxRadioBtn_A.getValueInt();
            if (ZYPCommonFunc.hasValue(cMsg.A.no(selectLineNum).techTngHistPk_A)) {
                cMsg.A.no(selectLineNum).svcSkillTpDescTxt_A.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).svcSkillDescTxt_A.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).svcSkillLvlPk_AS.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).effFromDt_A.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).effThruDt_A.setErrorInfo(1, NSBM0085E);
                return false;
            }
        } else {
            asMsg = sMsg.B;
            selectLineNum = cMsg.xxRadioBtn_B.getValueInt();
            sLineNum = sMsg.xxRadioBtn_B.getValueInt();
            if (ZYPCommonFunc.hasValue(cMsg.B.no(selectLineNum).techTngHistPk_B)) {
                cMsg.B.no(selectLineNum).techCd_B.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).fullPsnNm_B.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).svcSkillResrcTpCd_BS.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).svcSkillLvlPk_BS.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).effFromDt_B.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).effThruDt_B.setErrorInfo(1, NSBM0085E);
                return false;
            }
        }

        List<Integer> selectedRow = new ArrayList<Integer>();
        selectedRow.add(sLineNum);
        ZYPTableUtil.deleteRows(asMsg, selectedRow);

        return true;
    }

    /**
     * updateProcess
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     */
    public static void updateProcess(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            doProcessResource(cMsg, sMsg);
        } else {
            if (!ZYPCommonFunc.hasValue(cMsg.svcSkillNum)) {
                String skillName = cMsg.svcSkillDescTxt.getValue();
                SVC_SKILLTMsgArray svcSkillList = NSBL0280Query.getInstance().getConditionSvcSkillInfo(cMsg, skillName);
                if (svcSkillList == null || svcSkillList.getValidCount() == 0) {
                    cMsg.svcSkillDescTxt.setErrorInfo(1, NSBM0084E, new String[] {skillName, "Service Skill" });
                    return;
                }
                SVC_SKILLTMsg svcSkillTMsg = (SVC_SKILLTMsg) svcSkillList.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.svcSkillNum, svcSkillTMsg.svcSkillNum);
            }

            doProcessSkill(cMsg, sMsg);
        }
    }

    private static boolean checkSvcSkillLvlGrpCd(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                NSBL0280_ASMsg asMsg = sMsg.A.no(index);
                String glblCmpyCd = cMsg.glblCmpyCd.getValue();

                String skillName = asMsg.svcSkillDescTxt_A.getValue();
                Map<String, Object> svcSkillList = NSBL0280Query.getInstance().getSearchSvcSkill(cMsg, skillName);
                if (svcSkillList == null) {
                    sMsg.xxNum.setValue(index);
                    sMsg.A.no(index).svcSkillDescTxt_A.setErrorInfo(1, NSBM0084E, new String[] {skillName, "Service Skill" });
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(asMsg.svcSkillNum_A, (String) svcSkillList.get("SVC_SKILL_NUM"));
                ZYPEZDItemValueSetter.setValue(asMsg.svcSkillTpCd_A, (String) svcSkillList.get("SVC_SKILL_TP_CD"));
                ZYPEZDItemValueSetter.setValue(asMsg.svcSkillLvlGrpCd_A, (String) svcSkillList.get("SVC_SKILL_LVL_GRP_CD"));

                BigDecimal svcSkillLvlPk = asMsg.svcSkillLvlPk_AS.getValue();
                String svcSkillLvlGrpCd = asMsg.svcSkillLvlGrpCd_A.getValue();
                SVC_SKILL_LVLTMsg svcSkillLvlTMsg = (SVC_SKILL_LVLTMsg) NSBL0280Query.getInstance().getSvcSkillLvlInfo(glblCmpyCd, svcSkillLvlPk);
                if (svcSkillLvlTMsg != null) {
                    String svcSkillLvlGrpCdTMsg = svcSkillLvlTMsg.svcSkillLvlGrpCd.getValue();
                    if (!svcSkillLvlGrpCd.equals(svcSkillLvlGrpCdTMsg)) {
                        sMsg.xxNum.setValue(index);
                        sMsg.A.no(index).svcSkillLvlPk_AS.setErrorInfo(1, NSBM0088E, new String[] {svcSkillLvlGrpCd, svcSkillLvlGrpCdTMsg });
                        return false;
                    }
                    ZYPEZDItemValueSetter.setValue(asMsg.svcSkillLvlGrpCd_A, svcSkillLvlGrpCdTMsg);
                }
            }
        } else {
            String svcSkillLvlGrpCdBk = cMsg.svcSkillLvlGrpCd_BK.getValue();
            for (int index = 0; index < sMsg.B.getValidCount(); index++) {
                NSBL0280_BSMsg asMsg = sMsg.B.no(index);
                String glblCmpyCd = cMsg.glblCmpyCd.getValue();
                BigDecimal svcSkillLvlPk = asMsg.svcSkillLvlPk_BS.getValue();
                SVC_SKILL_LVLTMsg svcSkillLvlTMsg = (SVC_SKILL_LVLTMsg) NSBL0280Query.getInstance().getSvcSkillLvlInfo(glblCmpyCd, svcSkillLvlPk);
                if (svcSkillLvlTMsg != null) {
                    String svcSkillLvlGrpCdTMsg = svcSkillLvlTMsg.svcSkillLvlGrpCd.getValue();
                    if (!svcSkillLvlGrpCdBk.equals(svcSkillLvlGrpCdTMsg)) {
                        sMsg.xxNum.setValue(index);
                        sMsg.B.no(index).svcSkillLvlPk_BS.setErrorInfo(1, NSBM0088E, new String[] {svcSkillLvlGrpCdBk, svcSkillLvlGrpCdTMsg });
                        return false;
                    }
                    ZYPEZDItemValueSetter.setValue(asMsg.svcSkillLvlGrpCd_B, svcSkillLvlGrpCdTMsg);
                }
            }
        }
        return true;
    }

    private static boolean doProcessResource(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            NSBL0280_ASMsg asMsg = sMsg.A.no(index);
            String glblCmpyCd = cMsg.glblCmpyCd.getValue();
            String svcSkillResrcTpCd = cMsg.svcSkillResrcTpCd_S.getValue();
            if (ZYPCommonFunc.hasValue(asMsg.techTngHistPk_A)) {

                if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxSelFlg_A.getValue()) && svcSkillResrcTpCd.equals(cMsg.svcSkillResrcTpCd_B.getValue())) {
                    continue;
                }

                // Update
                BigDecimal techTngHistPk = asMsg.techTngHistPk_A.getValue();
                String sMsgEzUpTime = asMsg.ezUpTime_A.getValue();
                String sMsgEzUpTimeZone = asMsg.ezUpTimeZone_A.getValue();
                if (!isSameTimeTechTngHist(glblCmpyCd, techTngHistPk, sMsgEzUpTime, sMsgEzUpTimeZone)) {
                    sMsg.xxNum.setValue(index);
                    sMsg.A.no(index).svcSkillTpDescTxt_A.setErrorInfo(1, ZZZM9004E);
                    return false;
                }

                if (!updateTechTngHistResource(cMsg, asMsg)) {
                    sMsg.xxNum.setValue(index);
                    sMsg.A.no(index).svcSkillTpDescTxt_A.setErrorInfo(1, NSBM0073E, new String[] {"TECH_TNG_HIST", "TECH_TNG_HIST_PK", asMsg.techTngHistPk_A.getValue().toString() });
                    return false;
                }

            } else {
                // Insert
                if (!insertTechTngHistResource(cMsg, asMsg)) {
                    sMsg.xxNum.setValue(index);
                    sMsg.A.no(index).svcSkillTpDescTxt_A.setErrorInfo(1, NSBM0081E, new String[] {"TECH_TNG_HIST", "TECH_TNG_HIST_PK" });
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean doProcessSkill(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        for (int index = 0; index < sMsg.B.getValidCount(); index++) {

            NSBL0280_BSMsg bsMsg = sMsg.B.no(index);
            String glblCmpyCd = cMsg.glblCmpyCd.getValue();
            String techCd = bsMsg.techCd_B.getValue();
            S21_PSNTMsg psnTMsg = NSBL0280Query.getInstance().get21Pns(cMsg, techCd);
            if (psnTMsg == null) {
                sMsg.xxNum.setValue(index);
                cMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0084E, new String[] {techCd, "Tech Master" });
                return false;
            }
            String firstNm = psnTMsg.psnFirstNm.getValue();
            String lastNm = psnTMsg.psnLastNm.getValue();
            ZYPEZDItemValueSetter.setValue(bsMsg.fullPsnNm_B, lastNm + "," + firstNm);

            if (ZYPCommonFunc.hasValue(bsMsg.techTngHistPk_B)) {

                if (ZYPConstant.FLG_ON_Y.equals(bsMsg.xxSelFlg_B.getValue())) {
                    continue;
                }

                // Update
                BigDecimal techTngHistPk = bsMsg.techTngHistPk_B.getValue();
                String sMsgEzUpTime = bsMsg.ezUpTime_B.getValue();
                String sMsgEzUpTimeZone = bsMsg.ezUpTimeZone_B.getValue();
                if (!isSameTimeTechTngHist(glblCmpyCd, techTngHistPk, sMsgEzUpTime, sMsgEzUpTimeZone)) {
                    sMsg.xxNum.setValue(index);
                    cMsg.xxRadioBtn_B.setValue(index);
                    cMsg.xxRadioBtn_B.setErrorInfo(1, ZZZM9004E);
                    return false;
                }

                if (!updateTechTngHistSkill(cMsg, bsMsg)) {
                    sMsg.xxNum.setValue(index);
                    cMsg.xxRadioBtn_B.setValue(index);
                    cMsg.xxRadioBtn_B.setErrorInfo(1, NSBM0073E, new String[] {"TECH_TNG_HIST", "TECH_TNG_HIST_PK", bsMsg.techTngHistPk_B.getValue().toString() });
                    return false;
                }
            } else {
                // Insert
                if (!insertTechTngHistSkill(cMsg, bsMsg)) {
                    sMsg.xxNum.setValue(index);
                    cMsg.xxRadioBtn_B.setValue(index);
                    cMsg.xxRadioBtn_B.setErrorInfo(1, NSBM0081E, new String[] {"TECH_TNG_HIST", "TECH_TNG_HIST_PK", bsMsg.techTngHistPk_B.getValue().toString() });
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean insertTechTngHistResource(NSBL0280CMsg cMsg, NSBL0280_ASMsg asMsg) {

        TECH_TNG_HISTTMsg techTngHist = new TECH_TNG_HISTTMsg();
        BigDecimal techTngHistPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_TNG_HIST_SQ);
        ZYPEZDItemValueSetter.setValue(techTngHist.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistPk, techTngHistPk);
        ZYPEZDItemValueSetter.setValue(techTngHist.techCd, cMsg.psnCd);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistActvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(techTngHist.svcSkillNum, asMsg.svcSkillNum_A);
        ZYPEZDItemValueSetter.setValue(techTngHist.svcSkillLvlPk, asMsg.svcSkillLvlPk_AS);
        ZYPEZDItemValueSetter.setValue(techTngHist.effFromDt, asMsg.effFromDt_A);
        ZYPEZDItemValueSetter.setValue(techTngHist.effThruDt, asMsg.effThruDt_A);
        ZYPEZDItemValueSetter.setValue(techTngHist.svcSkillResrcTpCd, cMsg.svcSkillResrcTpCd_S);
        S21FastTBLAccessor.insert(techTngHist);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(techTngHist.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(asMsg.techTngHistPk_A, techTngHistPk);
            return false;
        }
        return true;
    }

    private static boolean updateTechTngHistResource(NSBL0280CMsg cMsg, NSBL0280_ASMsg asMsg) {

        TECH_TNG_HISTTMsg techTngHist = new TECH_TNG_HISTTMsg();
        ZYPEZDItemValueSetter.setValue(techTngHist.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistPk, asMsg.techTngHistPk_A);
        TECH_TNG_HISTTMsg outTechTngHist = (TECH_TNG_HISTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(techTngHist);

        if (outTechTngHist != null) {
            ZYPEZDItemValueSetter.setValue(outTechTngHist.techCd, cMsg.psnCd);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.svcSkillNum, asMsg.svcSkillNum_A);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.svcSkillLvlPk, asMsg.svcSkillLvlPk_AS);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.effFromDt, asMsg.effFromDt_A);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.effThruDt, asMsg.effThruDt_A);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.svcSkillResrcTpCd, cMsg.svcSkillResrcTpCd_S);
            S21FastTBLAccessor.update(outTechTngHist);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outTechTngHist.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private static boolean insertTechTngHistSkill(NSBL0280CMsg cMsg, NSBL0280_BSMsg bsMsg) {

        TECH_TNG_HISTTMsg techTngHist = new TECH_TNG_HISTTMsg();
        BigDecimal techTngHistPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_TNG_HIST_SQ);
        ZYPEZDItemValueSetter.setValue(techTngHist.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistPk, techTngHistPk);
        ZYPEZDItemValueSetter.setValue(techTngHist.techCd, bsMsg.techCd_B);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistActvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(techTngHist.svcSkillNum, cMsg.svcSkillNum);
        ZYPEZDItemValueSetter.setValue(techTngHist.svcSkillLvlPk, bsMsg.svcSkillLvlPk_BS);
        ZYPEZDItemValueSetter.setValue(techTngHist.effFromDt, bsMsg.effFromDt_B);
        ZYPEZDItemValueSetter.setValue(techTngHist.effThruDt, bsMsg.effThruDt_B);
        ZYPEZDItemValueSetter.setValue(techTngHist.svcSkillResrcTpCd, bsMsg.svcSkillResrcTpCd_BS);
        S21FastTBLAccessor.insert(techTngHist);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(techTngHist.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(bsMsg.techTngHistPk_B, techTngHistPk);
            return false;
        }
        return true;
    }

    private static boolean updateTechTngHistSkill(NSBL0280CMsg cMsg, NSBL0280_BSMsg bsMsg) {

        TECH_TNG_HISTTMsg techTngHist = new TECH_TNG_HISTTMsg();
        ZYPEZDItemValueSetter.setValue(techTngHist.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistPk, bsMsg.techTngHistPk_B);
        TECH_TNG_HISTTMsg outTechTngHist = (TECH_TNG_HISTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(techTngHist);

        if (outTechTngHist != null) {
            ZYPEZDItemValueSetter.setValue(outTechTngHist.techCd, bsMsg.techCd_B);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.svcSkillNum, cMsg.svcSkillNum);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.svcSkillLvlPk, bsMsg.svcSkillLvlPk_BS);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.effFromDt, bsMsg.effFromDt_B);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.effThruDt, bsMsg.effThruDt_B);
            ZYPEZDItemValueSetter.setValue(outTechTngHist.svcSkillResrcTpCd, bsMsg.svcSkillResrcTpCd_BS);
            S21FastTBLAccessor.update(outTechTngHist);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outTechTngHist.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSameTimeTechTngHist(String glblCmpyCd, BigDecimal techTngHistPk, String sMsgEzUpTime, String sMsgEzUpTimeZone) {

        TECH_TNG_HISTTMsg techTngHistTMsg = NSBL0280Query.getInstance().getTechTngHistInfo(glblCmpyCd, techTngHistPk);
        if (techTngHistTMsg == null) {
            return false;
        }

        String bfMsgEzUpTime = techTngHistTMsg.ezUpTime.getValue();
        String bfsgEzUpTimeZone = techTngHistTMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bfMsgEzUpTime, bfsgEzUpTimeZone, sMsgEzUpTime, sMsgEzUpTimeZone)) {
            return false;
        }
        return true;
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg, int pagenationFrom) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            int cnt = pagenationFrom;
            for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
                if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                    EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
                } else {
                    break;
                }
            }
            sMsg.xxRadioBtn_A.setValue(cMsg.xxRadioBtn_A.getValueInt() + pagenationFrom);
        } else {
            int cnt = pagenationFrom;
            for (; cnt < pagenationFrom + cMsg.B.length(); cnt++) {
                if (cnt < pagenationFrom + cMsg.B.getValidCount()) {
                    EZDMsg.copy(cMsg.B.get(cnt - pagenationFrom), null, sMsg.B.get(cnt), null);
                } else {
                    break;
                }
            }
            sMsg.xxRadioBtn_B.setValue(cMsg.xxRadioBtn_B.getValueInt() + pagenationFrom);
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        cMsg.xxErrNum.clear();
        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            for (; i < pageFrom + cMsg.A.length(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
                } else {
                    break;
                }
            }

            cMsg.A.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
            cMsg.xxRadioBtn_A.setValue(0);
            if (sMsg.A.length() == sMsg.A.getValidCount()) {
                cMsg.xxErrNum.setValue(BigDecimal.ONE);
            }
        } else {

            for (; i < pageFrom + cMsg.B.length(); i++) {
                if (i < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(i - pageFrom), null);
                } else {
                    break;
                }
            }

            cMsg.B.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
            cMsg.xxRadioBtn_B.setValue(0);
            if (sMsg.B.length() == sMsg.B.getValidCount()) {
                cMsg.xxErrNum.setValue(BigDecimal.ONE);
            }
        }
    }

    /**
     * getSearchSkillData
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     */
    public static void getSearchSkillData(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        // Resource Type
        ZYPCodeDataUtil.createPulldownList(SVC_SKILL_RESRC_TP.class, cMsg.svcSkillResrcTpCd_C, cMsg.svcSkillResrcTpDescTxt_D);

        S21SsmEZDResult ssmResult = NSBL0280Query.getInstance().getSearchSkillData(cMsg, sMsg, sMsg.B.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 5000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_B, BigDecimal.ZERO);
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9005W);
        }
    }

    /**
     * getSearchResourceData
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     */
    public static void getSearchResourceData(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        // Skill Level
        createPulldownList(cMsg);

        S21_PSNTMsg psnTMsg = NSBL0280Query.getInstance().get21Pns(cMsg, cMsg.psnCd.getValue());
        if (psnTMsg != null) {
            String firstNm = psnTMsg.psnFirstNm.getValue();
            String lastNm = psnTMsg.psnLastNm.getValue();
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, lastNm + "," + firstNm);
        }

        S21SsmEZDResult ssmResult = NSBL0280Query.getInstance().getSearchResourceData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 5000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9005W);
        }
    }

    private static void createPulldownList(NSBL0280CMsg cMsg) {

        SVC_SKILL_LVLTMsgArray svcSkillLvlTMsgList = NSBL0280Query.getInstance().getSvcSkillLvlInfo(cMsg);
        for (int index = 0; index < svcSkillLvlTMsgList.getValidCount(); index++) {
            SVC_SKILL_LVLTMsg svcSkillLvlTMsg = (SVC_SKILL_LVLTMsg) svcSkillLvlTMsgList.get(index);
            String svcSkillLvlPk = svcSkillLvlTMsg.svcSkillLvlPk.getValue().toString();
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillLvlGrpCd_BC.no(index), svcSkillLvlPk);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillLvlDescTxt_BD.no(index), svcSkillLvlTMsg.svcSkillLvlDescTxt);
        }
    }

    /**
     * setUpdateFlg
     * @param cMsg NSBL0280CMsg
     * @param sMsg NSBL0280SMsg
     */
    public static void setUpdateFlg(NSBL0280CMsg cMsg, NSBL0280SMsg sMsg) {

        if (RESOURCE_TYPE.equals(cMsg.xxScrDply.getValue())) {
            for (int index = 0; index < cMsg.A.getValidCount(); index++) {
                NSBL0280_ACMsg acMsg = cMsg.A.no(index);
                if (ZYPCommonFunc.hasValue(acMsg.techTngHistPk_A)) {
                    BigDecimal acTechTngHistPk = acMsg.techTngHistPk_A.getValue();
                    for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
                        NSBL0280_ASMsg asMsg = sMsg.A.no(cnt);
                        BigDecimal asTechTngHistPk = asMsg.techTngHistPk_A.getValue();
                        if (acTechTngHistPk.equals(asTechTngHistPk) && isSameDataResource(acMsg, asMsg)) {
                            ZYPEZDItemValueSetter.setValue(acMsg.xxSelFlg_A, ZYPConstant.FLG_ON_Y);
                            break;
                        }
                    }
                }
            }
        } else {
            for (int index = 0; index < cMsg.B.getValidCount(); index++) {
                NSBL0280_BCMsg bcMsg = cMsg.B.no(index);
                if (ZYPCommonFunc.hasValue(bcMsg.techTngHistPk_B)) {
                    BigDecimal bcTechTngHistPk = bcMsg.techTngHistPk_B.getValue();
                    for (int cnt = 0; cnt < sMsg.B.getValidCount(); cnt++) {
                        NSBL0280_BSMsg bsMsg = sMsg.B.no(cnt);
                        BigDecimal bsTechTngHistPk = bsMsg.techTngHistPk_B.getValue();
                        if (bcTechTngHistPk.equals(bsTechTngHistPk) && isSameDataSkill(bcMsg, bsMsg)) {
                            ZYPEZDItemValueSetter.setValue(bcMsg.xxSelFlg_B, ZYPConstant.FLG_ON_Y);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean isSameDataResource(NSBL0280_ACMsg acMsg, NSBL0280_ASMsg asMsg) {

        String svcSkillTpCd = acMsg.svcSkillTpDescTxt_A.getValue();
        String acMsgSvcSkillDescTxt = acMsg.svcSkillDescTxt_A.getValue();
        BigDecimal acMsgSvcSkillLvlPk = acMsg.svcSkillLvlPk_AS.getValue();
        String acMsgEffFromDt = checkNull(acMsg.effFromDt_A);
        String acMsgEffThruDt = checkNull(acMsg.effThruDt_A);

        String asMsgSvcSkillTpCd = asMsg.svcSkillTpDescTxt_A.getValue();
        String asMsgSvcSkillDescTxt = checkNull(asMsg.svcSkillDescTxt_A);
        BigDecimal asMsgSvcSkillLvlPk = asMsg.svcSkillLvlPk_AS.getValue();
        String asMsgEffFromDt = checkNull(asMsg.effFromDt_A);
        String asMsgEffThruDt = checkNull(asMsg.effThruDt_A);

        if (svcSkillTpCd.equals(asMsgSvcSkillTpCd) && acMsgSvcSkillDescTxt.equals(asMsgSvcSkillDescTxt) && acMsgSvcSkillLvlPk.compareTo(asMsgSvcSkillLvlPk) == 0 && acMsgEffFromDt.equals(asMsgEffFromDt)
                && acMsgEffThruDt.equals(asMsgEffThruDt)) {
            return true;
        }
        return false;
    }

    private static boolean isSameDataSkill(NSBL0280_BCMsg bcMsg, NSBL0280_BSMsg bsMsg) {

        String bcMsgTechCd = bcMsg.techCd_B.getValue();
        String bcMsgFfullPsnNm = bcMsg.fullPsnNm_B.getValue();
        String bcSvcSkillResrcTpCd = bcMsg.svcSkillResrcTpCd_BS.getValue();
        BigDecimal bcMsgsvcSkillLvlPk = bcMsg.svcSkillLvlPk_BS.getValue();
        String bcMsgEffFromDt = checkNull(bcMsg.effFromDt_B);
        String bcMsgEffThruDt = checkNull(bcMsg.effThruDt_B);

        String bsMsgTechCd = bsMsg.techCd_B.getValue();
        String bsMsgFfullPsnNm = bsMsg.fullPsnNm_B.getValue();
        String bsMsgSvcSkillResrcTpCd = bsMsg.svcSkillResrcTpCd_BS.getValue();
        BigDecimal bsMsgSvcSkillLvlPk = bsMsg.svcSkillLvlPk_BS.getValue();
        String bsMsgEffFromDt = checkNull(bsMsg.effFromDt_B);
        String bsMsgEffThruDt = checkNull(bsMsg.effThruDt_B);

        if (bcMsgTechCd.equals(bsMsgTechCd) && bcMsgFfullPsnNm.equals(bsMsgFfullPsnNm) && bcSvcSkillResrcTpCd.equals(bsMsgSvcSkillResrcTpCd) && bcMsgsvcSkillLvlPk.compareTo(bsMsgSvcSkillLvlPk) == 0 && bcMsgEffFromDt.equals(bsMsgEffFromDt)
                && bcMsgEffThruDt.equals(bsMsgEffThruDt)) {
            return true;
        }
        return false;
    }

    private static String checkNull(EZDSItem value) {
        String rt = "";
        if (value instanceof EZDSDateItem) {
            EZDSDateItem item = (EZDSDateItem) value;
            if (ZYPCommonFunc.hasValue(item)) {
                return item.getValue();
            }
        } else if (value instanceof EZDSStringItem) {
            EZDSStringItem item = (EZDSStringItem) value;
            if (ZYPCommonFunc.hasValue(item)) {
                return item.getValue();
            }
        }
        return rt;
    }

    private static String checkNull(EZDCItem value) {
        String rt = "";
        if (value instanceof EZDCDateItem) {
            EZDCDateItem item = (EZDCDateItem) value;
            if (ZYPCommonFunc.hasValue(item)) {
                return item.getValue();
            }
        }
        return rt;
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param sMsgCount int
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int sMsgCount) {

        int insertPage = (sMsgCount / rowsPerPage) * rowsPerPage;
        return insertPage;
    }

    /**
     * @param maxDispalyRows int
     * @param rowNum int
     * @return int
     */
    public static int getPageNum(int maxDispalyRows, int rowNum) {
        return ((rowNum - 1) / maxDispalyRows + 1);
    }
}

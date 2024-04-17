/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0290.common;

import static business.blap.NSBL0290.constant.NSBL0290Constant.*;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SKILL_LVL_GRP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;

import business.blap.NSBL0290.NSBL0290CMsg;
import business.blap.NSBL0290.NSBL0290Query;
import business.blap.NSBL0290.NSBL0290SMsg;
import business.blap.NSBL0290.NSBL0290_ACMsg;
import business.blap.NSBL0290.NSBL0290_ASMsg;
import business.db.SVC_SKILLTMsg;
import business.db.SVC_SKILL_TPTMsg;
import business.db.SVC_SKILL_TPTMsgArray;
import business.db.TECH_TNG_HISTTMsg;
import business.db.TECH_TNG_HISTTMsgArray;

/**
 *<pre>
 * Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 * 2016/06/02   Hitachi         K.Kasai         Update          QC#9379
 *</pre>
 */
public class NSBL0290CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     */
    public static void clearMsg(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     */
    public static void createPullDown(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        // Skill Type
        cMsg.svcSkillTpCd_C.clear();
        cMsg.svcSkillTpNm_D.clear();
        SVC_SKILL_TPTMsgArray svcSkillTpList = NSBL0290Query.getInstance().getSearchSvcSkillTp(cMsg.glblCmpyCd.getValue());
        for (int i = 0; i < svcSkillTpList.getValidCount(); i++) {
            SVC_SKILL_TPTMsg svcSkillTpTMsg = (SVC_SKILL_TPTMsg) svcSkillTpList.get(i);
            cMsg.svcSkillTpCd_C.no(i).setValue(svcSkillTpTMsg.svcSkillTpCd.getValue());
            cMsg.svcSkillTpNm_D.no(i).setValue(svcSkillTpTMsg.svcSkillTpNm.getValue());
        }

        // Use Scale
        ZYPCodeDataUtil.createPulldownList(SVC_SKILL_LVL_GRP.class, cMsg.svcSkillLvlGrpCd_C, cMsg.svcSkillLvlGrpDescTxt_D);
    }

    /**
     * checkSelect
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     * @return boolean
     */
    public static boolean checkSelect(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NZZM0011E);
            rtnVal = false;
        } else {
            ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        }
        return rtnVal;
    }

    /**
     * @param maxDispalyRows int
     * @param rowNum int
     * @return int
     */
    public static int getPageNum(int maxDispalyRows, int rowNum) {
        return ((rowNum - 1) / maxDispalyRows + 1);
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        cMsg.xxNum.clear();
        for (int row = 0; row < sMsg.A.getValidCount(); row++) {
            String selectedSvcSkillNm = sMsg.A.no(row).svcSkillNm.getValue();
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                String svcSkillNm = sMsg.A.no(index).svcSkillNm.getValue();
                if ((row != index) && selectedSvcSkillNm.equals(svcSkillNm)) {
                    sMsg.xxNum.setValue(index);
                    setErrorMsgList(sMsg.A.no(row));
                    setErrorMsgList(sMsg.A.no(index));
                    return false;
                }
            }
        }

        if (!checkEffFromDt(cMsg)) {
            return false;
        }
        return true;
    }

    /**
     * checkEffFromDt
     * @param cMsg NSBL0290CMsg
     * @return boolean
     */
    public static boolean checkEffFromDt(NSBL0290CMsg cMsg) {

        cMsg.A.clearErrorInfo();
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt_H) && ZYPCommonFunc.hasValue(cMsg.effThruDt_H)) {
            String effFromDtH = cMsg.effFromDt_H.getValue();
            String effThruDtH = cMsg.effThruDt_H.getValue();
            if (ZYPDateUtil.compare(effFromDtH, effThruDtH) > 0) {
                cMsg.effFromDt_H.setErrorInfo(1, NSBM0083E);
                cMsg.effThruDt_H.setErrorInfo(1, NSBM0083E);
                return false;
            }
        }

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(index).effFromDt) && ZYPCommonFunc.hasValue(cMsg.A.no(index).effThruDt)) {
                String effFromDt = cMsg.A.no(index).effFromDt.getValue();
                String effThruDt = cMsg.A.no(index).effThruDt.getValue();
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                    setErrorDateList(cMsg.A.no(index));
                    return false;
                }
            }
        }
        return true;
    }

    private static void setErrorMsgList(NSBL0290_ASMsg aSMsg) {

        aSMsg.xxChkBox.setErrorInfo(1, NSBM0082E, new String[] {aSMsg.svcSkillNm.getValue(), "Service Skill" });
        aSMsg.svcSkillNm.setErrorInfo(1, NSBM0082E, new String[] {aSMsg.svcSkillNm.getValue(), "Service Skill" });
    }

    private static void setErrorDateList(NSBL0290_ACMsg aCMsg) {

        //del start 2016/06/02 CSA Defect#9379
        //aCMsg.xxChkBox.setErrorInfo(1, NSBM0083E);
        //del end 2016/06/02 CSA Defect#9379
        aCMsg.effFromDt.setErrorInfo(1, NSBM0083E);
        aCMsg.effThruDt.setErrorInfo(1, NSBM0083E);
    }

    /**
     * updateProcess
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     */
    public static void updateProcess(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        sMsg.xxNum.clear();
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // SVC_SKILL_TP
        if (!isSameTimeSvcSkillTp(cMsg)) {
            return;
        }

        if (!updateServiecSkillTp(cMsg)) {
            return;
        }

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            NSBL0290_ASMsg asMsg = sMsg.A.no(index);
            // Update SVC_SKILLTMsg
            if (ZYPCommonFunc.hasValue(asMsg.svcSkillNum)) {
                if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxSelFlg.getValue())) {
                    continue;
                }

                SVC_SKILLTMsg svcSkill = NSBL0290Query.getInstance().getSearchDataSvcSkill(glblCmpyCd, asMsg);
                if (!isSameTimeSvcSkill(cMsg, asMsg, svcSkill)) {
                    sMsg.xxNum.setValue(index);
                    return;
                }

                if (!updateSvcSkill(cMsg, asMsg)) {
                    sMsg.xxNum.setValue(index);
                    return;
                }
            } else {
                if (!insertSvcSkill(cMsg, asMsg)) {
                    sMsg.xxNum.setValue(index);
                    return;
                }
            }

            // Update TECH_TNG_HIST
            if (ZYPCommonFunc.hasValue(asMsg.effThruDt)) {
                TECH_TNG_HISTTMsgArray techTngHistList = NSBL0290Query.getInstance().getSearchDataTechTngHist(glblCmpyCd, asMsg);
                for (int dx = 0; dx < techTngHistList.getValidCount(); dx++) {
                    TECH_TNG_HISTTMsg techTngHist = (TECH_TNG_HISTTMsg) techTngHistList.get(dx);
                    BigDecimal techTngHistPk = techTngHist.techTngHistPk.getValue();
                    if (!updateTechTngHist(glblCmpyCd, techTngHistPk, asMsg)) {
                        sMsg.xxNum.setValue(index);
                        return;
                    }
                }
            }
        }
    }

    /**
     * setUpdateFlg
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     */
    public static void setUpdateFlg(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            NSBL0290_ACMsg acMsg = cMsg.A.no(index);
            String acMsgSvcSikkNum = acMsg.svcSkillNum.getValue();
            if (ZYPCommonFunc.hasValue(acMsg.svcSkillNum)) {
                for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
                    NSBL0290_ASMsg asMsg = sMsg.A.no(cnt);
                    String asMsgSvcSikkNum = asMsg.svcSkillNum.getValue();
                    if (acMsgSvcSikkNum.equals(asMsgSvcSikkNum) && isSameData(acMsg, asMsg)) {
                        ZYPEZDItemValueSetter.setValue(acMsg.xxSelFlg, ZYPConstant.FLG_ON_Y);
                        break;
                    }
                }
            }
        }
    }

    private static boolean isSameTimeSvcSkillTp(NSBL0290CMsg cMsg) {

        SVC_SKILL_TPTMsg svcSkillTp = NSBL0290Query.getInstance().getSearchDataSvcSkillTp(cMsg);
        String bfMsgezUpTime = svcSkillTp.ezUpTime.getValue();
        String bfMsgezUpTimeZone = svcSkillTp.ezUpTimeZone.getValue();
        String cMsgezUpTime = cMsg.ezUpTime_A.getValue();
        String cMsgezUpTimeZone = cMsg.ezUpTimeZone_A.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bfMsgezUpTime, bfMsgezUpTimeZone, cMsgezUpTime, cMsgezUpTimeZone)) {
            cMsg.svcSkillTpDescTxt.setErrorInfo(1, ZZZM9004E);
            return false;
        }
        return true;
    }

    private static boolean isSameTimeSvcSkill(NSBL0290CMsg cMsg, NSBL0290_ASMsg asMsg, SVC_SKILLTMsg svcSkillTMsg) {

        if (svcSkillTMsg == null) {
            return false;
        }
        String bfMsgEzUpTime = svcSkillTMsg.ezUpTime.getValue();
        String bfsgEzUpTimeZone = svcSkillTMsg.ezUpTimeZone.getValue();
        String sMsgEzUpTime = asMsg.ezUpTime_B.getValue();
        String sMsgEzUpTimeZone = asMsg.ezUpTimeZone_B.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bfMsgEzUpTime, bfsgEzUpTimeZone, sMsgEzUpTime, sMsgEzUpTimeZone)) {
            asMsg.xxChkBox.setErrorInfo(1, ZZZM9004E);
            return false;
        }
        return true;
    }

    private static boolean isSameData(NSBL0290_ACMsg acMsg, NSBL0290_ASMsg asMsg) {

        BigDecimal acMsgSvcAliasRate = BigDecimal.ZERO;
        String acMsgSvcSillNm = acMsg.svcSkillNm.getValue();
        String acMsgSvcSkillDescTxt = acMsg.svcSkillDescTxt.getValue();
        String acMsgEffFromDt = acMsg.effFromDt.getValue();
        String acMsgEffThruDt = checkNull(acMsg.effThruDt);
        if (ZYPCommonFunc.hasValue(acMsg.svcAliasRate)) {
            acMsgSvcAliasRate = acMsg.svcAliasRate.getValue();
        }

        BigDecimal asMsgSvcAliasRate = BigDecimal.ZERO;
        String asMsgSvcSillNm = asMsg.svcSkillNm.getValue();
        String asMsgSvcSkillDescTxt = checkNull(asMsg.svcSkillDescTxt);
        String asMsgEffFromDt = checkNull(asMsg.effFromDt);
        String asMsgEffThruDt = checkNull(asMsg.effThruDt);
        if (ZYPCommonFunc.hasValue(asMsg.svcAliasRate)) {
            asMsgSvcAliasRate = asMsg.svcAliasRate.getValue();
        }
        if (acMsgSvcSillNm.equals(asMsgSvcSillNm) && acMsgSvcSkillDescTxt.equals(asMsgSvcSkillDescTxt) && acMsgSvcAliasRate.compareTo(asMsgSvcAliasRate) == 0 && acMsgEffFromDt.equals(asMsgEffFromDt) && acMsgEffThruDt.equals(asMsgEffThruDt)) {
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

    private static boolean insertSvcSkill(NSBL0290CMsg cMsg, NSBL0290_ASMsg asMsg) {

        SVC_SKILLTMsg svcSkillTMsg = new SVC_SKILLTMsg();
        BigDecimal svcSkillNumSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SKILL_NUM_SQ);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillNum, svcSkillNumSq.toString());
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillNm, asMsg.svcSkillNm);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillTpCd, cMsg.svcSkillTpCd_S);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillDescTxt, asMsg.svcSkillDescTxt);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcAliasRate, asMsg.svcAliasRate);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.effFromDt, asMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.effThruDt, asMsg.effThruDt);
        S21FastTBLAccessor.insert(svcSkillTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcSkillTMsg.getReturnCode())) {
            asMsg.xxChkBox.setErrorInfo(1, NSBM0081E, new String[] {"SVC_SKILL", "SVC_SKILL_NUM", asMsg.svcSkillNum.getValue() });
            return false;
        }
        return true;
    }

    private static boolean updateSvcSkill(NSBL0290CMsg cMsg, NSBL0290_ASMsg asMsg) {

        SVC_SKILLTMsg svcSkillTMsg = new SVC_SKILLTMsg();
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillNum, asMsg.svcSkillNum);
        SVC_SKILLTMsg outSvcSkillTMsg = (SVC_SKILLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(svcSkillTMsg);
        if (outSvcSkillTMsg != null) {
            ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillNm, asMsg.svcSkillNm);
            ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillTpCd, cMsg.svcSkillTpCd_S);
            ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcSkillDescTxt, asMsg.svcSkillDescTxt);
            ZYPEZDItemValueSetter.setValue(svcSkillTMsg.svcAliasRate, asMsg.svcAliasRate);
            ZYPEZDItemValueSetter.setValue(svcSkillTMsg.effFromDt, asMsg.effFromDt);
            ZYPEZDItemValueSetter.setValue(svcSkillTMsg.effThruDt, asMsg.effThruDt);
            S21FastTBLAccessor.update(svcSkillTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcSkillTMsg.getReturnCode())) {
                asMsg.xxChkBox.setErrorInfo(1, NSBM0073E, new String[] {"SVC_SKILL", "SVC_SKILL_NUM", asMsg.svcSkillNum.getValue() });
                return false;
            }
        }
        return true;
    }

    private static boolean updateServiecSkillTp(NSBL0290CMsg cMsg) {

        SVC_SKILL_TPTMsg svcSkillTpTMsg = new SVC_SKILL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(svcSkillTpTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcSkillTpTMsg.svcSkillTpCd, cMsg.svcSkillTpCd_S);
        SVC_SKILL_TPTMsg outSvcSkillTpTMsg = (SVC_SKILL_TPTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(svcSkillTpTMsg);
        if (outSvcSkillTpTMsg != null) {
            ZYPEZDItemValueSetter.setValue(outSvcSkillTpTMsg.svcSkillTpDescTxt, cMsg.svcSkillTpDescTxt);
            if (ZYPCommonFunc.hasValue(cMsg.svcSkillLvlGrpCd_S)) {
                ZYPEZDItemValueSetter.setValue(outSvcSkillTpTMsg.svcSkillLvlGrpCd, cMsg.svcSkillLvlGrpCd_S);
            }
            ZYPEZDItemValueSetter.setValue(outSvcSkillTpTMsg.effFromDt, cMsg.effFromDt_H);
            ZYPEZDItemValueSetter.setValue(outSvcSkillTpTMsg.effThruDt, cMsg.effThruDt_H);
            S21FastTBLAccessor.update(outSvcSkillTpTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcSkillTpTMsg.getReturnCode())) {
                cMsg.svcSkillTpCd_S.setErrorInfo(1, NSBM0073E, new String[] {"SVC_SKILL_TP", "SVC_SKILL_TP_CD", cMsg.svcSkillTpCd_S.getValue() });
                return false;
            }
        }
        return true;
    }

    private static boolean updateTechTngHist(String glblCmpyCd, BigDecimal techTngHistPk, NSBL0290_ASMsg asMsg) {

        TECH_TNG_HISTTMsg techTngHist = new TECH_TNG_HISTTMsg();
        ZYPEZDItemValueSetter.setValue(techTngHist.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(techTngHist.techTngHistPk, techTngHistPk);
        TECH_TNG_HISTTMsg outTechTngHist = (TECH_TNG_HISTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(techTngHist);

        if (outTechTngHist != null) {
            ZYPEZDItemValueSetter.setValue(outTechTngHist.effThruDt, asMsg.effThruDt);
            S21FastTBLAccessor.update(outTechTngHist);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outTechTngHist.getReturnCode())) {
                asMsg.xxChkBox.setErrorInfo(1, NSBM0073E, new String[] {"TECH_TNG_HIST", "TECH_TNG_HIST_PK", techTngHistPk.toString() });
                return false;
            }
        }
        return true;
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     */
    public static void getSearchData(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg) {

        setSvcSkillTp(cMsg);

        S21SsmEZDResult ssmResult = NSBL0290Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9005W);
        }
    }

    private static void setSvcSkillTp(NSBL0290CMsg cMsg) {

        SVC_SKILL_TPTMsg svcSkillTp = NSBL0290Query.getInstance().getSearchDataSvcSkillTp(cMsg);
        if (svcSkillTp != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillTpCd_S, svcSkillTp.svcSkillTpCd);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillTpDescTxt, svcSkillTp.svcSkillTpDescTxt);
            ZYPEZDItemValueSetter.setValue(cMsg.svcSkillLvlGrpCd_S, svcSkillTp.svcSkillLvlGrpCd);
            ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_H, svcSkillTp.effFromDt);
            ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_H, svcSkillTp.effThruDt);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_A, svcSkillTp.ezUpTime);
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_A, svcSkillTp.ezUpTimeZone);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0290CMsg
     * @param sMsg NSBL0290SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0290CMsg cMsg, NSBL0290SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        if (sMsg.A.length() == sMsg.A.getValidCount()) {
            cMsg.xxBtnFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
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
}

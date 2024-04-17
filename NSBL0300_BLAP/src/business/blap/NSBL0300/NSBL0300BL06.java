/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0300;

import static business.blap.NSBL0300.constant.NSBL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0300.common.NSBL0300CommonLogic;
import business.db.SVC_SKILL_LVLTMsg;
import business.db.SVC_SKILL_LVLTMsgArray;
import business.db.SVC_SKILL_LVL_GRPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Skill Level Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 * 2016/06/02   Hitachi         K.Kasai         Update          QC#9379
 *</pre>
 */
public class NSBL0300BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0300CMsg cMsg = (NSBL0300CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0300Scrn00_CMN_Submit(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSBL0300Scrn00_CMN_Submit(NSBL0300CMsg cMsg) {

        //check DB uploaded by another person
        SVC_SKILL_LVLTMsgArray svcSkillLvlArray = NSBL0300CommonLogic.getSvcSkillLvlInfo(cMsg);
        if (svcSkillLvlArray.getValidCount() != getRegisteredCnt(cMsg)) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        //validation check
        if (!checkDataValid(cMsg)) {
            return;
        }

        //update SVC_SKILL_LVL_GRP info/insert or update SVC_SKILL_LVL info
        doSubmit(cMsg);

    }

    private boolean checkDataValid(NSBL0300CMsg cMsg) {
        boolean checkDataValid = true;
        BigDecimal orgSvcSkillSortNum;
        String orgSvcSkillLvlNm = "";
        //check data validation
        if (hasValue(cMsg.effFromDt) && hasValue(cMsg.effThruDt)) {
            if (cMsg.effFromDt.getValue().compareTo(cMsg.effThruDt.getValue()) >= 0) {
                //mod start 2016/06/02 CSA Defect#9379
                cMsg.effFromDt.setErrorInfo(1, NSBM0083E);
                cMsg.effThruDt.setErrorInfo(1, NSBM0083E);
                //mod end 2016/06/02 CSA Defect#9379
                checkDataValid = false;
            }
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            orgSvcSkillSortNum = cMsg.A.no(i).svcSkillLvlSortNum_A.getValue();
            orgSvcSkillLvlNm = cMsg.A.no(i).svcSkillLvlNm_A.getValue();
            //check duplication data
            for (int j = i + 1; j < cMsg.A.getValidCount(); j++) {
                if (orgSvcSkillSortNum.compareTo(cMsg.A.no(j).svcSkillLvlSortNum_A.getValue()) == 0) {
                    cMsg.A.no(i).svcSkillLvlSortNum_A.setErrorInfo(1, NSAM0472E, new String[]{"Order:".concat(cMsg.A.no(i).svcSkillLvlSortNum_A.getValue().toString()), "Service Skill"});
                    cMsg.A.no(j).svcSkillLvlSortNum_A.setErrorInfo(1, NSAM0472E, new String[]{"Order:".concat(cMsg.A.no(j).svcSkillLvlSortNum_A.getValue().toString()), "Service Skill"});
                    checkDataValid = false;
                }
                if (orgSvcSkillLvlNm.equals(cMsg.A.no(j).svcSkillLvlNm_A.getValue())) {
                    cMsg.A.no(i).svcSkillLvlNm_A.setErrorInfo(1, NSAM0472E, new String[]{"Skill Level Name:".concat(cMsg.A.no(i).svcSkillLvlNm_A.getValue()), "Service Skill"});
                    cMsg.A.no(j).svcSkillLvlNm_A.setErrorInfo(1, NSAM0472E, new String[]{"Skill Level Name:".concat(cMsg.A.no(j).svcSkillLvlNm_A.getValue()), "Service Skill"});
                    checkDataValid = false;
                }
            }
            //check data validation
            if (hasValue(cMsg.A.no(i).effFromDt_A) && hasValue(cMsg.A.no(i).effThruDt_A)) {
                if (cMsg.A.no(i).effFromDt_A.getValue().compareTo(cMsg.A.no(i).effThruDt_A.getValue()) >= 0) {
                    //mod start 2016/06/02 CSA Defect#9379
                    cMsg.A.no(i).effFromDt_A.setErrorInfo(1, NSBM0083E);
                    cMsg.A.no(i).effThruDt_A.setErrorInfo(1, NSBM0083E);
                    //mod end 2016/06/02 CSA Defect#9379
                    checkDataValid = false;
                }
            }
        }
        return checkDataValid;
    }

    private int getRegisteredCnt(NSBL0300CMsg cMsg) {
        int registeredCnt = 0;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (hasValue(cMsg.A.no(i).svcSkillLvlPk_A)) {
                registeredCnt++;
            }
        }
        return registeredCnt;
    }

    private void doSubmit(NSBL0300CMsg cMsg) {

        if (!updateSvcSkillLvlGrp(cMsg)) {
            return;
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSBL0300_ACMsg acMsg = cMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).submtFlg_A.getValue())) {
                //update data
                if (hasValue(cMsg.A.no(i).svcSkillLvlPk_A)) {
                    if (!updateSvcSkillLvl(cMsg, acMsg)) {
                        return;
                    }
                //insert data
                } else {
                    if (!insertSvcSkillLvl(cMsg, acMsg)) {
                        return;
                    }
                }
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private boolean updateSvcSkillLvlGrp(NSBL0300CMsg cMsg) {

        SVC_SKILL_LVL_GRPTMsg inMsg  = new SVC_SKILL_LVL_GRPTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.svcSkillLvlGrpCd, cMsg.svcSkillLvlGrpCd_H3);

        SVC_SKILL_LVL_GRPTMsg updTMsg = (SVC_SKILL_LVL_GRPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setParamSvcSkillLvlGrp(cMsg, updTMsg);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0031E, new String[] {TBL_NM_SVC_SKILL_LVL_GRP});
            return false;
        }
        return true;
    }

    private void setParamSvcSkillLvlGrp(NSBL0300CMsg cMsg, SVC_SKILL_LVL_GRPTMsg tMsg) {

        setValue(tMsg.svcSkillLvlGrpDescTxt, cMsg.svcSkillLvlGrpDescTxt);
        setValue(tMsg.effFromDt, cMsg.effFromDt);
        setValue(tMsg.effThruDt, cMsg.effThruDt);
    }

    private boolean insertSvcSkillLvl(NSBL0300CMsg cMsg, NSBL0300_ACMsg acMsg) {

        SVC_SKILL_LVLTMsg insMsg  = new SVC_SKILL_LVLTMsg();

        setValue(insMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insMsg.svcSkillLvlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SKILL_LVL_SQ));
        setValue(insMsg.svcSkillLvlGrpCd, cMsg.svcSkillLvlGrpCd_H3);
        setParamSvcSkillLvl(cMsg, acMsg, insMsg);
        S21FastTBLAccessor.insert(insMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0032E, new String[] {TBL_NM_SVC_SKILL_LVL});
            return false;
        }
        return true;
    }

    private boolean updateSvcSkillLvl(NSBL0300CMsg cMsg, NSBL0300_ACMsg acMsg) {

        SVC_SKILL_LVLTMsg inMsg  = new SVC_SKILL_LVLTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.svcSkillLvlPk, acMsg.svcSkillLvlPk_A);
        setValue(inMsg.svcSkillLvlGrpCd, cMsg.svcSkillLvlGrpCd_H3);

        SVC_SKILL_LVLTMsg updTMsg = (SVC_SKILL_LVLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setParamSvcSkillLvl(cMsg, acMsg, updTMsg);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSBM0031E, new String[] {TBL_NM_SVC_SKILL_LVL});
            return false;
        }
        return true;
    }

    private void setParamSvcSkillLvl(NSBL0300CMsg cMsg, NSBL0300_ACMsg acMsg, SVC_SKILL_LVLTMsg tMsg) {

        setValue(tMsg.svcSkillLvlNm, acMsg.svcSkillLvlNm_A);
        setValue(tMsg.svcSkillLvlSortNum, acMsg.svcSkillLvlSortNum_A);
        setValue(tMsg.svcSkillLvlDescTxt, acMsg.svcSkillLvlDescTxt_A);
        setValue(tMsg.effFromDt, acMsg.effFromDt_A);
        setValue(tMsg.effThruDt, acMsg.effThruDt_A);
    }

}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0960.common;

import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0031E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0032E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NZZM0003E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.TABLE_NAME_DS_CONTR_VLD_LIST;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0960.NSAL0960CMsg;
import business.blap.NSAL0960.NSAL0960_ACMsg;
import business.db.DS_CONTR_VLD_LISTTMsg;
import business.db.DS_CONTR_VLD_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Contract Validation List Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0960CommonLogic {

    /**
     * submitDsContrVldList
     * @param cMsg NSAL0960CMsg
     */
    public static void submitDsContrVldList(NSAL0960CMsg cMsg) {
        if (cMsg == null) {
            return;
        }
        if (hasValue(cMsg.dsContrVldListPk_H)) {
            DS_CONTR_VLD_LISTTMsg tMsg = getDsContrVldListForUpdate(cMsg.glblCmpyCd_H.getValue(), cMsg.dsContrVldListPk_H.getValue());
            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H.getValue(), cMsg.ezUpTimeZone_H.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }
            // Update
            setDsContrVldListForSubmit(tMsg, cMsg, false);
            S21FastTBLAccessor.update(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {TABLE_NAME_DS_CONTR_VLD_LIST });
            }
        } else {
            DS_CONTR_VLD_LISTTMsg tMsg = new DS_CONTR_VLD_LISTTMsg();
            // Insert
            setDsContrVldListForSubmit(tMsg, cMsg, true);
            S21FastTBLAccessor.insert(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(NSAM0032E, new String[] {TABLE_NAME_DS_CONTR_VLD_LIST });
            }
        }
    }

    /**
     * submitDsContrVldRln
     * @param cMsg NSAL0960CMsg
     */
    public static void submitDsContrVldReln(NSAL0960CMsg cMsg) {
        if (cMsg == null) {
            return;
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0960_ACMsg target = (NSAL0960_ACMsg) cMsg.A.get(i);
            if (ZYPConstant.FLG_ON_Y.equals(target.xxExstFlg_A.getValue())) {
                DS_CONTR_VLD_RELNTMsg tMsg = getDsContrVldRelnForUpdate(target.glblCmpyCd_A.getValue(), cMsg.dsContrVldListPk_H.getValue(), target.dsContrVldPk_A.getValue());
                if (!ZYPDateUtil.isSameTimeStamp(target.ezUpTime_A.getValue(), target.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }
                // Update
                setDsContrVldRelnForSubmit(tMsg, cMsg, target, false);
                S21FastTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {TABLE_NAME_DS_CONTR_VLD_LIST });
                }
            } else {
                DS_CONTR_VLD_RELNTMsg tMsg = new DS_CONTR_VLD_RELNTMsg();
                // Insert
                setDsContrVldRelnForSubmit(tMsg, cMsg, target, true);
                S21FastTBLAccessor.insert(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {TABLE_NAME_DS_CONTR_VLD_LIST });
                }
            }
        }
    }

    private static void setDsContrVldListForSubmit(DS_CONTR_VLD_LISTTMsg tMsg, NSAL0960CMsg cMsg, boolean newFlag) {
        if (newFlag) {
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd_H);
            BigDecimal dsContrVldListPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_VLD_LIST_SQ);
            setValue(cMsg.dsContrVldListPk_H, dsContrVldListPk);
            setValue(tMsg.dsContrVldListPk, dsContrVldListPk);
        }
        setValue(tMsg.dsContrVldListNm, cMsg.dsContrVldListNm_H);
        setValue(tMsg.dsContrVldListDescTxt, cMsg.dsContrVldListDescTxt_H);
        setValue(tMsg.effFromDt, cMsg.effFromDt_H);
        setValue(tMsg.effToDt, cMsg.effToDt_H);
    }

    private static DS_CONTR_VLD_LISTTMsg getDsContrVldListForUpdate(String glblCmpyCd, BigDecimal dsContrVldListPk) {
        DS_CONTR_VLD_LISTTMsg prmTMsg = new DS_CONTR_VLD_LISTTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsContrVldListPk, dsContrVldListPk);
        return (DS_CONTR_VLD_LISTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static DS_CONTR_VLD_RELNTMsg getDsContrVldRelnForUpdate(String glblCmpyCd, BigDecimal dsContrVldListPk, BigDecimal dsContrVldPk) {
        DS_CONTR_VLD_RELNTMsg prmTMsg = new DS_CONTR_VLD_RELNTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsContrVldListPk, dsContrVldListPk);
        setValue(prmTMsg.dsContrVldPk, dsContrVldPk);
        return (DS_CONTR_VLD_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static void setDsContrVldRelnForSubmit(DS_CONTR_VLD_RELNTMsg tMsg, NSAL0960CMsg cMsg, NSAL0960_ACMsg target, boolean newFlag) {
        if (newFlag) {
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd_H);
        }
        setValue(tMsg.dsContrVldListPk, cMsg.dsContrVldListPk_H);
        setValue(tMsg.dsContrVldPk, target.dsContrVldPk_A);
        setValue(tMsg.vldSortNum, target.vldSortNum_A);
        setValue(tMsg.vldActCd, target.vldActCd_AS);
        setValue(tMsg.effFromDt, target.effFromDt_A);
        setValue(tMsg.effToDt, target.effToDt_A);
    }

    /**
     * setSearchCriteria
     * @param cMsg NSAL0960CMsg
     * @param dsContrVldListPk BigDecimal
     * @param limitNum int
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSearchCriteriaMap(NSAL0960CMsg cMsg, BigDecimal dsContrVldListPk, int limitNum) {
        if (cMsg == null) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_H.getValue());
        params.put("dsContrVldListPk", dsContrVldListPk);
        params.put("limitNum", limitNum);

        return params;
    }
}

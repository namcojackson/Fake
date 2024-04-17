/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0950.common;

import static business.blap.NSAL0950.constant.NSAL0950Constant.MAX_THRU_DATE;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0031E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0032E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NZZM0003E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.TABLE_NAME_DS_CONTR_VLD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0950.NSAL0950CMsg;
import business.blap.NSAL0950.NSAL0950Query;
import business.blap.NSAL0950.NSAL0950SMsg;
import business.blap.NSAL0950.NSAL0950_ACMsg;
import business.db.DS_CONTR_VLDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/06/28   Hitachi         Y.Tsuchimoto    Update          QC#9691
 * 2016/07/07   Hitachi         O.Okuma         Update          QC#10991
 *</pre>
 */
public class NSAL0950CommonLogic {

    /**
     * submitDsContrVld
     * @param cMsg NSAL0950CMsg
     */
    public static void submitDsContrVld(NSAL0950CMsg cMsg) {
        int submitRow = cMsg.xxRadioBtn_A.getValueInt();
        NSAL0950_ACMsg target = (NSAL0950_ACMsg) cMsg.A.get(submitRow);

        if (ZYPCommonFunc.hasValue(target.dsContrVldPk_A)) {
            DS_CONTR_VLDTMsg tMsg = getDsContrVldForUpdate(target.glblCmpyCd_A.getValue(), target.dsContrVldPk_A.getValue());
            if (!ZYPDateUtil.isSameTimeStamp(target.ezUpTime_A.getValue(), target.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }
            // Update
            setDsContrVldForSubmit(tMsg, cMsg, false);
            S21FastTBLAccessor.update(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {TABLE_NAME_DS_CONTR_VLD });
            }
        } else {
            DS_CONTR_VLDTMsg tMsg = new DS_CONTR_VLDTMsg();
            // Insert
            setDsContrVldForSubmit(tMsg, cMsg, true);
            S21FastTBLAccessor.insert(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(NSAM0032E, new String[] {TABLE_NAME_DS_CONTR_VLD });
            }
        }
    }

    private static void setDsContrVldForSubmit(DS_CONTR_VLDTMsg tMsg, NSAL0950CMsg cMsg, boolean newFlag) {
        // START 2016/06/28 [QC#9691, MOD]
        if (newFlag) {
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd_D);
            BigDecimal dsContrVldPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_VLD_SQ);
            setValue(tMsg.dsContrVldPk, dsContrVldPk);
            setValue(tMsg.dsContrVldCatgCd, cMsg.dsContrVldCatgCd_DS);
            setValue(tMsg.dsContrVldNm, cMsg.dsContrVldNm_D);
        }
        setValue(tMsg.vldCmptTxt, cMsg.vldCmptTxt_D);
        // END   2016/06/28 [QC#9691, MOD]
        setValue(tMsg.dsContrVldDescTxt, cMsg.dsContrVldDescTxt_D);
        if (ZYPCommonFunc.hasValue(cMsg.vldLvlContrFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldLvlContrFlg_D.getValue())) {
            setValue(tMsg.vldLvlContrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.vldLvlContrFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldLvlMachFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldLvlMachFlg_D.getValue())) {
            setValue(tMsg.vldLvlMachFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.vldLvlMachFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldRegFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldRegFlg_D.getValue())) {
            setValue(tMsg.vldRegFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.vldRegFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldFleetFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldFleetFlg_D.getValue())) {
            setValue(tMsg.vldFleetFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.vldFleetFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldAggrFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldAggrFlg_D.getValue())) {
            setValue(tMsg.vldAggrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.vldAggrFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldWtyFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldWtyFlg_D.getValue())) {
            setValue(tMsg.vldWtyFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.vldWtyFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.primVldFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.primVldFlg_D.getValue())) {
            setValue(tMsg.primVldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.primVldFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.ovrdVldRsltAvalFlg_D) && ZYPConstant.FLG_ON_Y.equals(cMsg.ovrdVldRsltAvalFlg_D.getValue())) {
            setValue(tMsg.ovrdVldRsltAvalFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.ovrdVldRsltAvalFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(tMsg.effFromDt, cMsg.effFromDt_D);
        setValue(tMsg.effToDt, cMsg.effToDt_D);
    }

    private static DS_CONTR_VLDTMsg getDsContrVldForUpdate(String glblCmpyCd, BigDecimal dsContrVldPk) {
        DS_CONTR_VLDTMsg prmTMsg = new DS_CONTR_VLDTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrVldPk, dsContrVldPk);
        return (DS_CONTR_VLDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * clearInputArea
     * @param cMsg NSAL0950CMsg
     */
    public static void clearInputArea(NSAL0950CMsg cMsg) {
        setValue(cMsg.glblCmpyCd_D, (String) null);
        setValue(cMsg.dsContrVldPk_D, (BigDecimal) null);
        setValue(cMsg.dsContrVldCatgCd_DS, (String) null);
        setValue(cMsg.dsContrVldNm_D, (String) null);
        setValue(cMsg.dsContrVldDescTxt_D, (String) null);
        setValue(cMsg.vldLvlContrFlg_D, (String) null);
        setValue(cMsg.vldLvlMachFlg_D, (String) null);
        setValue(cMsg.effFromDt_D, (String) null);
        setValue(cMsg.effToDt_D, (String) null);
        setValue(cMsg.vldCmptTxt_D, (String) null);
        setValue(cMsg.primVldFlg_D, (String) null);
        setValue(cMsg.ovrdVldRsltAvalFlg_D, (String) null);
        setValue(cMsg.vldRegFlg_D, (String) null);
        setValue(cMsg.vldFleetFlg_D, (String) null);
        setValue(cMsg.vldAggrFlg_D, (String) null);
        setValue(cMsg.vldWtyFlg_D, (String) null);
    }

    /**
     * setSearchCriteria
     * @param cMsg NSAL0950CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSearchCriteriaMap(NSAL0950CMsg cMsg) {
        if (cMsg == null) {
            return new HashMap<String, Object>();
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd_S.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsContrVldNm_S)) {
            params.put("dsContrVldNm", cMsg.dsContrVldNm_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrVldDescTxt_S)) {
            params.put("dsContrVldDescTxt", cMsg.dsContrVldDescTxt_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsContrVldCatgCd_SS)) {
            params.put("dsContrVldCatgCd", cMsg.dsContrVldCatgCd_SS.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldLvlContrFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldLvlContrFlg_S.getValue())) {
            params.put("vldLvlContrFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldLvlMachFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldLvlMachFlg_S.getValue())) {
            params.put("vldLvlMachFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldRegFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldRegFlg_S.getValue())) {
            params.put("vldRegFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldFleetFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldFleetFlg_S.getValue())) {
            params.put("vldFleetFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldAggrFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldAggrFlg_S.getValue())) {
            params.put("vldAggrFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldWtyFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.vldWtyFlg_S.getValue())) {
            params.put("vldWtyFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.primVldFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.primVldFlg_S.getValue())) {
            params.put("primVldFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.ovrdVldRsltAvalFlg_S) && ZYPConstant.FLG_ON_Y.equals(cMsg.ovrdVldRsltAvalFlg_S.getValue())) {
            params.put("ovrdVldRsltAvalFlg", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(cMsg.vldCmptTxt_S)) {
            params.put("vldCmptTxt", cMsg.vldCmptTxt_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt_S)) {
            params.put("effFromDt", cMsg.effFromDt_S.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effToDt_S)) {
            params.put("effToDt", cMsg.effToDt_S.getValue());
            params.put("maxThruDate", MAX_THRU_DATE);
        }
        params.put("limitNum", cMsg.A.length() + 1);

        return params;
    }

    /**
     * setDetailDsContrVld
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     * @param detailDisplayRow int
     */
    public static void setDetailDsContrVld(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg, int detailDisplayRow) {
        if (detailDisplayRow < 0) {
            return;
        }
        NSAL0950_ACMsg target = (NSAL0950_ACMsg) cMsg.A.get(detailDisplayRow);
        if (target != null) {
            setValue(cMsg.glblCmpyCd_D, target.glblCmpyCd_A);
            setValue(cMsg.dsContrVldPk_D, target.dsContrVldPk_A);
            setValue(cMsg.dsContrVldCatgCd_DS, target.dsContrVldCatgCd_AS);
            setValue(cMsg.dsContrVldNm_D, target.dsContrVldNm_A);
            setValue(cMsg.dsContrVldDescTxt_D, target.dsContrVldDescTxt_A);
            setValue(cMsg.vldLvlContrFlg_D, target.vldLvlContrFlg_A);
            setValue(cMsg.vldLvlMachFlg_D, target.vldLvlMachFlg_A);
            setValue(cMsg.effFromDt_D, target.effFromDt_A);
            setValue(cMsg.effToDt_D, target.effToDt_A);
            setValue(cMsg.vldCmptTxt_D, target.vldCmptTxt_A);
            setValue(cMsg.primVldFlg_D, target.primVldFlg_A);
            setValue(cMsg.ovrdVldRsltAvalFlg_D, target.ovrdVldRsltAvalFlg_A);
            setValue(cMsg.vldRegFlg_D, target.vldRegFlg_A);
            setValue(cMsg.vldFleetFlg_D, target.vldFleetFlg_A);
            setValue(cMsg.vldAggrFlg_D, target.vldAggrFlg_A);
            setValue(cMsg.vldWtyFlg_D, target.vldWtyFlg_A);
        }
    }

    // START 2016/07/07 [QC#10991, ADD]
    /**
     * cheakDsContrVldNm
     * @param cMsg NSAL0950CMsg
     * @param dsContrVldPk BigDecimal
     * @return boolean
     */
    public static boolean cheakDsContrVldNm(NSAL0950CMsg cMsg, BigDecimal dsContrVldPk) {

        S21SsmEZDResult ssmResult = NSAL0950Query.getInstance().cheakDsContrVldNm(cMsg, dsContrVldPk);
        if (ssmResult.getQueryResultCount() > 0) {
            return false;
        }
        return true;
    }
    // END 2016/07/07 [QC#10991, ADD]
}

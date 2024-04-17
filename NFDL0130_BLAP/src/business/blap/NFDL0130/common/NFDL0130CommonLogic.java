/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0130.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFDL0130.NFDL0130_ACMsg;
import business.db.CLT_WRK_ITEMTMsg;
import business.db.CLT_WRK_ITEMTMsgArray;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#12997
 * 2022/06/03   CITS            K.Suzuki        Update          QC#60145
 *</pre>
 */
public class NFDL0130CommonLogic {

    /**
     * getSearchData
     * @param glblCmpyCd String
     * @return CLT_WRK_ITEMTMsgArray
     */
    public static CLT_WRK_ITEMTMsgArray getSearchData(String glblCmpyCd) {
        CLT_WRK_ITEMTMsg tMsg = new CLT_WRK_ITEMTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        CLT_WRK_ITEMTMsgArray tMsgArr = (CLT_WRK_ITEMTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        return tMsgArr;
    }

    /**
     * get CLT_WRK_ITEM By Primary Key For Update No Wait
     * @param glblCmpyCd String
     * @param cltWrkItemCd String
     * @return CLT_WRK_ITEMTMsg
     */
    public static CLT_WRK_ITEMTMsg getCltWrkItemForUpdateNoWait(String glblCmpyCd, String cltWrkItemCd) {
        CLT_WRK_ITEMTMsg prmTMsg = new CLT_WRK_ITEMTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.cltWrkItemCd, cltWrkItemCd);
        return (CLT_WRK_ITEMTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * setTmsgValue
     * @param tMsg CLT_WRK_ITEMTMsg
     * @param acMsg NFDL0130_ACMsg
     * @param glblCmpyCd String
     */
    public static void setTmsgValue(CLT_WRK_ITEMTMsg tMsg, NFDL0130_ACMsg acMsg, String glblCmpyCd) {
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.cltWrkItemCd, acMsg.cltWrkItemCd_A);
        setValue(tMsg.cltWrkItemNm, acMsg.cltWrkItemNm_A);
        setValue(tMsg.cltWrkTpCd, acMsg.cltWrkTpCd_SV);
        setValue(tMsg.cltWrkCatgCd, acMsg.cltWrkCatgCd_A);
        setValue(tMsg.cltWrkCatgNm, acMsg.cltWrkCatgNm_A);
        setValue(tMsg.cltWrkWaitDaysAot, acMsg.cltWrkWaitDaysAot_A);
        // START 2016/09/12 K.Kojima [QC#12997,MOD]
        // setValue(tMsg.cltWrkPrtyCd, acMsg.cltWrkPrtyCd_A);
        // setValue(tMsg.cltWrkPrtyNm, acMsg.cltWrkPrtyNm_A);
        setValue(tMsg.cltWrkPrtyCd, (String) null);
        setValue(tMsg.cltWrkPrtyNm, (String) null);
        // END 2016/09/12 K.Kojima [QC#12997,MOD]
        setValue(tMsg.cltWrkDescTxt, acMsg.cltWrkDescTxt_A);
        setValue(tMsg.cltWrkOptItemFlg, acMsg.cltWrkOptItemFlg_A);
        setValue(tMsg.cltWrkEsclFlg, acMsg.cltWrkEsclFlg_A);
        setValue(tMsg.cltWrkNtfyFlg, acMsg.cltWrkNtfyFlg_A);
        setValue(tMsg.cltWrkEsclWaitDaysAot, acMsg.cltWrkEsclWaitDaysAot_A);
        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.cltWrkOptItemFlg.getValue())) {
            setValue(tMsg.cltWrkOptItemFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.cltWrkEsclFlg.getValue())) {
            setValue(tMsg.cltWrkEsclFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.cltWrkNtfyFlg.getValue())) {
            setValue(tMsg.cltWrkNtfyFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(tMsg.cltWrkNextWaitDaysAot)) {
            BigDecimal nextWaitDaysAot = ZYPCodeDataUtil.getNumConstValue("NFDL0130_NEXT_WAIT_DAYS_AOT", glblCmpyCd);
            setValue(tMsg.cltWrkNextWaitDaysAot, nextWaitDaysAot);
        }
    }
}

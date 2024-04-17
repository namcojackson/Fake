/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1380;

import static business.blap.NSAL1380.constant.NSAL1380Constant.MTH_12;
import static business.blap.NSAL1380.constant.NSAL1380Constant.NMAM0177E;
import static business.blap.NSAL1380.constant.NSAL1380Constant.NZZM0003E;
import static business.blap.NSAL1380.constant.NSAL1380Constant.SCHD_CRAT_TMPL_LINE_TABLE_NAME;
import static business.blap.NSAL1380.constant.NSAL1380Constant.SCHD_CRAT_TMPL_TABLE_NAME;
import static business.blap.NSAL1380.constant.NSAL1380Constant.SVC_TERM_COND_TABLE_NAME;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1380.common.NSAL1380CommonLogic;
import business.db.SCHD_CRAT_TMPLTMsg;
import business.db.SCHD_CRAT_TMPL_LINETMsg;
import business.db.SHPG_INTVLTMsg;
import business.db.SVC_TERM_CONDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Agreement Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 * 2017/10/16   Hitachi         Y.Takeno        Update          QC#20001
 * 2018/09/10   Fujitsu         K.Ishizuka      Update          QC#28104
 * 2019/02/26   Hitachi         K.Fujimoto      Update          QC#30525
 *</pre>
 */
public class NSAL1380BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1380CMsg bizMsg = (NSAL1380CMsg) cMsg;

            if ("NSAL1380Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL1380Scrn00_CMN_Save(bizMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL1380Scrn00_CMN_Save(NSAL1380CMsg bizMsg) {

        if (!submitCheck(bizMsg)) {
            return;
        }

        // START 2017/10/16 [QC#20001, MOD]
        // if (!updatePlan(bizMsg)) {
        //     return;
        // }
        //
        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //     if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).schdCratTmplPk_A)) {
        //         if (!updatePlnDtl(bizMsg, i)) {
        //             return;
        //         }
        //     }
        // }
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrDtlGrpNum)) {
            if(!updatePlanByModel(bizMsg)) {
                return;
            }
        } else {
            if(!updatePlanByConfig(bizMsg)) {
                return;
            }
        }

        // search
        // NSAL1380CommonLogic.search(bizMsg);
        NSAL1380CommonLogic.search(getGlobalCompanyCode(), bizMsg);
        // END   2017/10/16 [QC#20001, MOD]
    }

    // START 2017/10/16 [QC#20001, MOD]
    /**
     * updatePlan
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean updatePlan(NSAL1380CMsg bizMsg) {
        return updatePlan(bizMsg, bizMsg.schdCratTmplPk.getValue());
    }

    /**
     * updatePlan
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param schdCratTmplPk BigDecimal
     */
    // private Boolean updatePlan(NSAL1380CMsg bizMsg) {
    private Boolean updatePlan(NSAL1380CMsg bizMsg, BigDecimal schdCratTmplPk) {

        if (!NSAL1380CommonLogic.checkChangedHeaderValueExist(bizMsg)) {
            return true;
        }

        SCHD_CRAT_TMPLTMsg tMsg = new SCHD_CRAT_TMPLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        // ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplPk, bizMsg.schdCratTmplPk);
        ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplPk, schdCratTmplPk);

        tMsg = (SCHD_CRAT_TMPLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // Check time stamp
        if (schdCratTmplPk.compareTo(bizMsg.schdCratTmplPk.getValue()) == 0) {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                // anyone update
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }
        }
        ZYPEZDItemValueSetter.setValue(tMsg.splyBaseAmt, bizMsg.splyBaseAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.qtyContrCapQty, bizMsg.qtyContrCapQty);

        EZDTBLAccessor.update(tMsg);
        String returnCode = tMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {SCHD_CRAT_TMPL_TABLE_NAME });
            return false;
        }

        return true;
    }

    /**
     * update Price Group Detail
     * @param bizMsg Business Msg
     * @param index Global Msg index
     */
    private Boolean updatePlnDtl(NSAL1380CMsg bizMsg, int index) {
        return updatePlnDtl(bizMsg, bizMsg.schdCratTmplPk.getValue(), index);
    }

    /**
     * update Price Group Detail
     * @param bizMsg Business Msg
     * @param schdCratTmplPk BigDecimal
     * @param index Global Msg index
     */
    // private Boolean updatePlnDtl(NSAL1380CMsg bizMsg, int index) {
    private Boolean updatePlnDtl(NSAL1380CMsg bizMsg, BigDecimal schdCratTmplPk, int index) {
        if (!NSAL1380CommonLogic.checkChangedDetailValueExist(bizMsg.A.no(index))) {
            return true;
        }

        SCHD_CRAT_TMPL_LINETMsg tMsg = new SCHD_CRAT_TMPL_LINETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        // ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplPk, bizMsg.A.no(index).schdCratTmplPk_A);
        ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplPk, schdCratTmplPk);
        ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplLineNum, bizMsg.A.no(index).schdCratTmplLineNum_A);

        tMsg = (SCHD_CRAT_TMPL_LINETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // Check time stamp
        if (schdCratTmplPk.compareTo(bizMsg.A.no(index).schdCratTmplPk_A.getValue()) == 0) {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(index).ezUpTime_A.getValue(), bizMsg.A.no(index).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                // anyone update
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }
        }
        BigDecimal termMthAot = bizMsg.termMthAot.getValue();
        BigDecimal termPerMonths = null;
        if (termMthAot != null) {
            // START 2019/02/26 K.Fujimoto [QC#30525,MOD]
            // termPerMonths = termMthAot.divide(MTH_12).setScale(0, BigDecimal.ROUND_DOWN);
            termPerMonths = termMthAot.divide(MTH_12, 0, BigDecimal.ROUND_DOWN);
            // END   2019/02/26 K.Fujimoto [QC#30525,MOD]
        }
        BigDecimal shpgIntvlMthNum = getShpgIntvlMthNum(getGlobalCompanyCode(), bizMsg.A.no(index).shpgIntvlCd_A.getValue());
        BigDecimal monthsPerIntvl = null;
        if (shpgIntvlMthNum != null) {
            if (BigDecimal.ZERO.compareTo(shpgIntvlMthNum) == 0) {
                monthsPerIntvl = BigDecimal.ZERO;
            } else {
                // START 2019/02/26 K.Fujimoto [QC#30525,MOD]
                // monthsPerIntvl = MTH_12.divide(shpgIntvlMthNum).setScale(0, BigDecimal.ROUND_DOWN);
                monthsPerIntvl = MTH_12.divide(shpgIntvlMthNum, 0, BigDecimal.ROUND_DOWN);
                // END   2019/02/26 K.Fujimoto [QC#30525,MOD]
            }
        }

        BigDecimal otmShipQty = bizMsg.A.no(index).otmShipQty_A.getValue();
        BigDecimal schdAllwQty = null;
        if (otmShipQty != null && termMthAot != null && shpgIntvlMthNum != null) {
            schdAllwQty = otmShipQty.multiply(monthsPerIntvl).multiply(termPerMonths);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.shpgIntvlCd, bizMsg.A.no(index).shpgIntvlCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.otmShipQty, bizMsg.A.no(index).otmShipQty_A);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAllwQty, schdAllwQty);
        ZYPEZDItemValueSetter.setValue(tMsg.firstShipQty, bizMsg.A.no(index).firstShipQty_A);
        EZDTBLAccessor.update(tMsg);
        String returnCode = tMsg.getReturnCode();

        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {SCHD_CRAT_TMPL_LINE_TABLE_NAME });
            return false;
        }

        return true;
    }
    // END   2017/10/16 [QC#20001, MOD]

    private Boolean submitCheck(NSAL1380CMsg bizMsg) {
        // checkDocTp
        return NSAL1380CommonLogic.checkDocTp(getGlobalCompanyCode(), bizMsg);
    }

    private BigDecimal getShpgIntvlMthNum(String glblCmpyCd, String shpgIntvlCd) {
        SHPG_INTVLTMsg tMsg = new SHPG_INTVLTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.shpgIntvlCd.setValue(shpgIntvlCd);
        tMsg = (SHPG_INTVLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.shpgIntvlMthNum.getValue();
        }
        return null;
    }

    // START 2017/10/16 [QC#20001, ADD]
    private boolean updatePlanByConfig(NSAL1380CMsg bizMsg) {
        if (!updatePlan(bizMsg)) {
            return false;
        }
        // 2018/09/10 S21_NA#28104 Add Start
        if (!updateQtyForSvcTermCond(bizMsg)) {
            return false;
        }
        // 2018/09/10 S21_NA#28104 Add End
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).schdCratTmplPk_A)) {
                if (!updatePlnDtl(bizMsg, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean updatePlanByModel(NSAL1380CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NSAL1380Query.getInstance().getSchdCratTmplPkByDsContrDtlGrpNum(bizMsg);
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>)ssmResult.getResultObject();
            for (Map<String, Object> resultMap : resultList) {
                BigDecimal schdCratTmplPk = (BigDecimal)resultMap.get("SCHD_CRAT_TMPL_PK");
                BigDecimal dsContrDtlPk = (BigDecimal)resultMap.get("DS_CONTR_DTL_PK"); // 2018/09/10 S21_NA#28104 Add
                if (!updatePlan(bizMsg, schdCratTmplPk)) {
                    return false;
                }
                // 2018/09/10 S21_NA#28104 Add Start
                if (!updateQtyForSvcTermCond(bizMsg, dsContrDtlPk)) {
                    return false;
                }
                // 2018/09/10 S21_NA#28104 Add End
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    if (!updatePlnDtl(bizMsg, schdCratTmplPk, i)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    // END   2017/10/16 [QC#20001, ADD]
    
    // 2018/09/10 S21_NA#28104 Add Start
    /**
     * updateQtyForSvcTermCond
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean updateQtyForSvcTermCond(NSAL1380CMsg bizMsg) {
        return updateQtyForSvcTermCond(bizMsg, bizMsg.dsContrDtlPk.getValue());
    }

    /**
     * updateQtyForSvcTermCond
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param schdCratTmplPk BigDecimal
     */
    @SuppressWarnings("unchecked")
    private Boolean updateQtyForSvcTermCond(NSAL1380CMsg bizMsg, BigDecimal dsContrDtlPk) {

        if (!NSAL1380CommonLogic.checkChangeValue(bizMsg.qtyContrCapQty.getValue(), bizMsg.qtyContrCapQty_H.getValue())) {
            return true;
        }

        S21SsmEZDResult ssmResult = NSAL1380Query.getInstance().getSvcTermCondAttrbPk(bizMsg, dsContrDtlPk);

        if (ssmResult.isCodeNotFound() || ssmResult == null) {
            return true;
        }

        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return true;
        }

        SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondPk, (BigDecimal) resList.get(0).get("SVC_TERM_COND_PK"));

        tMsg = (SVC_TERM_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        BigDecimal capQty = bizMsg.qtyContrCapQty.getValue();
        if (ZYPCommonFunc.hasValue(capQty)) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcTermAttrbMapValCd, capQty.toPlainString());
            S21ApiTBLAccessor.update(tMsg);
            String returnCode = tMsg.getReturnCode();
            if (!RTNCD_NORMAL.equals(returnCode)) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {SVC_TERM_COND_TABLE_NAME });
                return false;
            }
        }

        return true;
    }
    // 2018/09/10 S21_NA#28104 Add End
}

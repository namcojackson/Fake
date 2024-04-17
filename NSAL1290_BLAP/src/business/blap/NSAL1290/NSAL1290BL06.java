/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1290;

import static business.blap.NSAL1290.constant.NSAL1290Constant.MAX_DT_VAL;
import static business.blap.NSAL1290.constant.NSAL1290Constant.MIN_DT_VAL;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0031E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0032E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0035E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0045E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0062E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0374E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NSAM0520E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.NZZM0002I;
import static business.blap.NSAL1290.constant.NSAL1290Constant.STR_PERIOD;
import static business.blap.NSAL1290.constant.NSAL1290Constant.STR_SLS_DATE;
import static business.blap.NSAL1290.constant.NSAL1290Constant.TBL_NM_BLLG_MTR_MAP;
import static business.blap.NSAL1290.constant.NSAL1290Constant.ZZZM9001E;
import static business.blap.NSAL1290.constant.NSAL1290Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1290.common.NSAL1290CommonLogic;
import business.db.BLLG_MTR_MAPTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 * 2016/08/22   Hitachi         A.Kohinata      Update          QC#12910
 * 2016/10/18   Hitachi         T.Tomita        Update          QC#15133
 *</pre>
 */
public class NSAL1290BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1290CMsg cMsg = (NSAL1290CMsg) arg0;
        NSAL1290SMsg sMsg = (NSAL1290SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1290Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NSAL1290Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL1290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1290Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1290Scrn00_CMN_Submit(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg) {

        cMsg.setCommitSMsg(true);

        NSAL1290CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        List<NSAL1290_ASMsg> submitList = getSubmitData(sMsg);

        if (submitList == null || submitList.isEmpty()) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }

        if (!checkValidation(cMsg, sMsg, submitList)) {
            NSAL1290CommonLogic.copySMsgToCMsg(cMsg, sMsg);
            return;
        }

        doSubmit(cMsg, submitList);
    }

    private List<NSAL1290_ASMsg> getSubmitData(NSAL1290SMsg sMsg) {

        List<NSAL1290_ASMsg> submitList = new ArrayList<NSAL1290_ASMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (i >= sMsg.B.getValidCount()) {
                submitList.add(sMsg.A.no(i));
                continue;
            }

            for (int j = 0; j < sMsg.B.getValidCount(); j++) {

                if (sMsg.A.no(i).xxRowNum_A.getValueInt() == sMsg.B.no(j).xxRowNum_A.getValueInt()) {
                    if (isChangeData(sMsg.A.no(i), sMsg.B.no(j))) {
                        submitList.add(sMsg.A.no(i));
                    }
                }
            }
        }
        return submitList;
    }

    private boolean isChangeData(NSAL1290_ASMsg asMsg, NSAL1290_BSMsg bsMsg) {

        if (!bsMsg.mtrLbNm_BC.getValue().equals(asMsg.mtrLbNm_BC.getValue())) {
            return true;
        }
        if (!bsMsg.bllgMtrMapLvlNum_A.getValue().equals(asMsg.bllgMtrMapLvlNum_A.getValue())) {
            return true;
        }
        if (!bsMsg.actvFlg_A.getValue().equals(asMsg.actvFlg_A.getValue())) {
            return true;
        }
        if (!bsMsg.effFromDt_A.getValue().equals(asMsg.effFromDt_A.getValue())) {
            return true;
        }
        if (!bsMsg.effThruDt_A.getValue().equals(asMsg.effThruDt_A.getValue())) {
            return true;
        }

        return false;
    }

    private boolean checkValidation(NSAL1290CMsg cMsg, NSAL1290SMsg sMsg, List<NSAL1290_ASMsg> submitList) {
        boolean errFlg = true;
        for (int i = 0; i < submitList.size(); i++) {
            NSAL1290_ASMsg asMsg = submitList.get(i);
            if (!checkBillingCounter(cMsg, asMsg)) {
                errFlg = false;
            }
            if (!checkDate(asMsg)) {
                errFlg = false;
            }
            if (!checkActvFlg(cMsg, asMsg)) {
                errFlg = false;
            }
            if (!checkDuplicated(cMsg, asMsg, submitList)) {
                errFlg = false;
            }
        }
        return errFlg;
    }

    private boolean checkBillingCounter(NSAL1290CMsg cMsg, NSAL1290_ASMsg asMsg) {

        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("mtrLbNm01", asMsg.mtrLbNm_BC.getValue());
        inMsg.setConditionValue("mtrLbTpCd01", MTR_LB_TP.BILLING_METER);

        MTR_LBTMsgArray tMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            asMsg.mtrLbNm_BC.setErrorInfo(1, NSAM0045E, new String[] {"Billing Counter" });
            asMsg.bllgMtrLbCd_BC.clear();
            asMsg.mtrLbDescTxt_BC.clear();
            return false;
        }
        if (!cMsg.mtrIdxCd_H.getValue().equals(tMsgArray.no(0).mtrIdxCd.getValue().toString())) {
            asMsg.mtrLbNm_BC.setErrorInfo(1, NSAM0520E);
            asMsg.bllgMtrLbCd_BC.clear();
            asMsg.mtrLbDescTxt_BC.clear();
            return false;
        }
        setValue(asMsg.bllgMtrLbCd_BC, tMsgArray.no(0).mtrLbCd);
        setValue(asMsg.mtrLbNm_BC, tMsgArray.no(0).mtrLbNm);
        setValue(asMsg.mtrLbDescTxt_BC, tMsgArray.no(0).mtrLbDescTxt);
        return true;

    }

    private boolean checkDate(NSAL1290_ASMsg asMsg) {

        if (!hasValue(asMsg.effFromDt_A) || !hasValue(asMsg.effThruDt_A)) {
            return true;
        }
        if (ZYPDateUtil.compare(asMsg.effThruDt_A.getValue(), asMsg.effFromDt_A.getValue()) < 0) {
            asMsg.effFromDt_A.setErrorInfo(1, NSAM0062E);
            asMsg.effThruDt_A.setErrorInfo(1, NSAM0062E);
            return false;
        }
        return true;
    }

    private boolean checkActvFlg(NSAL1290CMsg cMsg, NSAL1290_ASMsg asMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(asMsg.actvFlg_A.getValue())) {
            return true;
        }
        if (!hasValue(asMsg.effThruDt_A)) {
            return true;
        }
        if (ZYPDateUtil.compare(asMsg.effThruDt_A.getValue(), cMsg.slsDt.getValue()) < 0) {
            asMsg.effThruDt_A.setErrorInfo(1, NSAM0374E, new String[] {STR_SLS_DATE });
            return false;
        }
        return true;
    }

    private boolean checkDuplicated(NSAL1290CMsg cMsg, NSAL1290_ASMsg asMsg, List<NSAL1290_ASMsg> submitList) {

        if (!ZYPConstant.FLG_ON_Y.equals(asMsg.actvFlg_A.getValue())) {
            return true;
        }

        String chkEffFromDt = asMsg.effFromDt_A.getValue();
        if (!hasValue(asMsg.effFromDt_A)) {
            chkEffFromDt = MIN_DT_VAL;
        }
        String chkEffThruDt = asMsg.effThruDt_A.getValue();
        if (!hasValue(asMsg.effThruDt_A)) {
            chkEffThruDt = MAX_DT_VAL;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).actvFlg_A.getValue())) {
                continue;
            }
            if (asMsg.xxRowNum_A.getValueInt() == cMsg.A.no(i).xxRowNum_A.getValueInt()) {
                continue;
            }
            String effFromDt = cMsg.A.no(i).effFromDt_A.getValue();
            if (!hasValue(cMsg.A.no(i).effFromDt_A)) {
                effFromDt = MIN_DT_VAL;
            }
            String effThruDt = cMsg.A.no(i).effThruDt_A.getValue();
            if (!hasValue(cMsg.A.no(i).effThruDt_A)) {
                effThruDt = MAX_DT_VAL;
            }
            // del start 2016/10/18 CSA Defect#15133
//            // add start 2016/08/22 CSA Defect#12910
//            if (asMsg.bllgMtrMapLvlNum_A.getValue().equals(cMsg.A.no(i).bllgMtrMapLvlNum_A.getValue())) {
//                if ((ZYPDateUtil.compare(effThruDt, chkEffFromDt) >= 0)
//                        && (ZYPDateUtil.compare(chkEffThruDt, effFromDt) >= 0)) {
//                    asMsg.bllgMtrMapLvlNum_A.setErrorInfo(1, NSAM0035E, new String[] {STR_LEVEL});
//                    return false;
//                }
//            }
//            // add end 2016/08/22 CSA Defect#12910
            // del end 2016/10/18 CSA Defect#15133
            if (!asMsg.bllgMtrLbCd_BC.getValue().equals(cMsg.A.no(i).bllgMtrLbCd_BC.getValue())) {
                continue;
            }
            if ((ZYPDateUtil.compare(effThruDt, chkEffFromDt) >= 0)
                    && (ZYPDateUtil.compare(chkEffThruDt, effFromDt) >= 0)) {
                asMsg.effFromDt_A.setErrorInfo(1, NSAM0035E, new String[] {STR_PERIOD });
                asMsg.effThruDt_A.setErrorInfo(1, NSAM0035E, new String[] {STR_PERIOD });
                return false;
            }
        }

        S21SsmEZDResult ssmResult = NSAL1290Query.getInstance().getBllgMtrMap(cMsg.glblCmpyCd.getValue(), cMsg.mdlMtrLbCd_H.getValue());
        if (ssmResult.getQueryResultCount() != 0) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int j = 0; j < ssmResult.getQueryResultCount(); j++) {
                BigDecimal bllgmtrMapPk = (BigDecimal) list.get(j).get("BLLG_MTR_MAP_PK");
                String acvtFlg = (String) list.get(j).get("ACTV_FLG");
                String effFromDt = (String) list.get(j).get("EFF_FROM_DT");
                String effThruDt = (String) list.get(j).get("EFF_THRU_DT");
                String bllgMtrLbCd = (String) list.get(j).get("BLLG_MTR_LB_CD");
                // add start 2016/08/22 CSA Defect#12910
                String bllgMtrMapLvlNum = (String) list.get(j).get("BLLG_MTR_MAP_LVL_NUM");
                // add end 2016/08/22 CSA Defect#12910
                if (!ZYPConstant.FLG_ON_Y.equals(acvtFlg)) {
                    continue;
                }
                boolean chkFlg = false;
                for (int cnt = 0; cnt < submitList.size(); cnt++) {
                    if (bllgmtrMapPk.equals(submitList.get(cnt).bllgMtrMapPk_A.getValue())) {
                        chkFlg = true;
                        break;
                    }
                }
                if (chkFlg) {
                    continue;
                }
                if (bllgmtrMapPk.equals(asMsg.bllgMtrMapPk_A.getValue())) {
                    continue;
                }
                if (!hasValue(effFromDt)) {
                    effFromDt = MIN_DT_VAL;
                }
                if (!hasValue(effThruDt)) {
                    effThruDt = MAX_DT_VAL;
                }
                // del start 2016/10/18 CSA Defect#15133
//                // add start 2016/08/22 CSA Defect#12910
//                if (asMsg.bllgMtrMapLvlNum_A.getValue().equals(bllgMtrMapLvlNum)) {
//                    if ((ZYPDateUtil.compare(effThruDt, chkEffFromDt) >= 0)
//                            && (ZYPDateUtil.compare(chkEffThruDt, effFromDt) >= 0)) {
//                        asMsg.bllgMtrMapLvlNum_A.setErrorInfo(1, NSAM0035E, new String[] {STR_LEVEL});
//                        return false;
//                    }
//                }
//                // add end 2016/08/22 CSA Defect#12910
                // del end 2016/10/18 CSA Defect#15133
                if (!asMsg.bllgMtrLbCd_BC.getValue().equals(bllgMtrLbCd)) {
                    continue;
                }
                if ((ZYPDateUtil.compare(effThruDt, chkEffFromDt) >= 0)
                        && (ZYPDateUtil.compare(chkEffThruDt, effFromDt) >= 0)) {
                    asMsg.effFromDt_A.setErrorInfo(1, NSAM0035E, new String[] {STR_PERIOD });
                    asMsg.effThruDt_A.setErrorInfo(1, NSAM0035E, new String[] {STR_PERIOD });
                    return false;
                }
            }
        }

        return true;
    }

    private void doSubmit(NSAL1290CMsg cMsg, List<NSAL1290_ASMsg> submitList) {

        for (int i = 0; i < submitList.size(); i++) {
            NSAL1290_ASMsg asMsg = submitList.get(i);
            if (hasValue(asMsg.bllgMtrMapPk_A)) {
                if (!updateForBllgMtrMap(cMsg, asMsg)) {
                    return;
                }
            } else {
                if (!insertForBllgMtrMap(cMsg, asMsg)) {
                    return;
                }
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private boolean insertForBllgMtrMap(NSAL1290CMsg cMsg, NSAL1290_ASMsg asMsg) {

        BLLG_MTR_MAPTMsg insMsg  = new BLLG_MTR_MAPTMsg();
        setValue(insMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insMsg.bllgMtrMapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.BLLG_MTR_MAP_SQ));
        setValue(insMsg.bllgMtrLbCd, asMsg.bllgMtrLbCd_BC);
        setValue(insMsg.bllgMtrMapLvlNum, asMsg.bllgMtrMapLvlNum_A);
        setValue(insMsg.actvFlg, asMsg.actvFlg_A);
        setValue(insMsg.effFromDt, asMsg.effFromDt_A);
        setValue(insMsg.effThruDt, asMsg.effThruDt_A);
        setValue(insMsg.mdlMtrLbCd, cMsg.mdlMtrLbCd_H);

        S21FastTBLAccessor.insert(insMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_BLLG_MTR_MAP });
            return false;
        }

        setValue(asMsg.bllgMtrMapPk_A, insMsg.bllgMtrMapPk);

        boolean isExistsModel = false;
        NSAL1290CommonLogic.invokeMasterDataMessaging(isExistsModel, insMsg.ezUpTime.getValue(), asMsg.bllgMtrLbCd_BC.getValue(), asMsg.mtrLbNm_BC.getValue());

        return true;
    }

    private boolean updateForBllgMtrMap(NSAL1290CMsg cMsg, NSAL1290_ASMsg asMsg) {

        BLLG_MTR_MAPTMsg inMsg  = new BLLG_MTR_MAPTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.bllgMtrMapPk, asMsg.bllgMtrMapPk_A);

        BLLG_MTR_MAPTMsg updTMsg = (BLLG_MTR_MAPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setValue(updTMsg.bllgMtrLbCd, asMsg.bllgMtrLbCd_BC);
        setValue(updTMsg.bllgMtrMapLvlNum, asMsg.bllgMtrMapLvlNum_A);
        setValue(updTMsg.actvFlg, asMsg.actvFlg_A);
        setValue(updTMsg.effFromDt, asMsg.effFromDt_A);
        setValue(updTMsg.effThruDt, asMsg.effThruDt_A);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_BLLG_MTR_MAP });
            return false;
        }

        boolean isExistsModel = true;
        NSAL1290CommonLogic.invokeMasterDataMessaging(isExistsModel, updTMsg.ezUpTime.getValue(), asMsg.bllgMtrLbCd_BC.getValue(), asMsg.mtrLbNm_BC.getValue());

        return true;
    }
}

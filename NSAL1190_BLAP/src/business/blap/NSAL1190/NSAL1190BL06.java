/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1190;

import static business.blap.NSAL1190.constant.NSAL1190Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1190.common.NSAL1190CommonLogic;
import business.db.MDSETMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 * 2016/03/29   Hitachi         O.Okuma         Update          QC5713
 * 2016/05/26   Hitachi         T.Tomita        Update          QC#8896
 * 2016/06/27   Hitachi         M.Gotou         Update          QC7886
 * 2017/08/02   Hitachi         T.Kanasaka      Update          QC#18193
 * 2018/02/05   CITS            M.Naito         Update          QC#21184
 *</pre>
 */
public class NSAL1190BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1190CMsg cMsg = (NSAL1190CMsg) arg0;
        NSAL1190SMsg sMsg = (NSAL1190SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1190Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL1190Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1190Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1190Scrn00_CMN_Submit(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg) {

        cMsg.setCommitSMsg(true);

        int rowIndex = 0;
        if (cMsg.xxPageShowFromNum.getValueInt() > 0) {
            rowIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        }

        NSAL1190CommonLogic.setPagenation(cMsg, sMsg, rowIndex);

        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        List<NSAL1190_ASMsg> submitList = getSubmitData(sMsg);

        if (submitList == null || submitList.isEmpty()) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }

        if (!checkValidation(cMsg, sMsg, submitList)) {
            int errPageFromNum = NSAL1190CommonLogic.getErrPageFromNum(cMsg, sMsg);
            NSAL1190CommonLogic.pagenation(cMsg, sMsg, errPageFromNum);
            return;
        }

        doSubmit(cMsg, submitList);
    }

    private List<NSAL1190_ASMsg> getSubmitData(NSAL1190SMsg sMsg) {

        List<NSAL1190_ASMsg> submitList = new ArrayList<NSAL1190_ASMsg>();

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

    private boolean isChangeData(NSAL1190_ASMsg asMsg, NSAL1190_BSMsg bsMsg) {

        if (!bsMsg.mtrLbNm_A.getValue().equals(asMsg.mtrLbNm_A.getValue())) {
            return true;
        }
        if (!bsMsg.mtrLbTpCd_A.getValue().equals(asMsg.mtrLbTpCd_A.getValue())) {
            return true;
        }
        if (!bsMsg.mtrIdxCd_A.getValue().equals(asMsg.mtrIdxCd_A.getValue())) {
            return true;
        }
        if (!bsMsg.bllgMtrLvlNum_A.getValue().equals(asMsg.bllgMtrLvlNum_A.getValue())) {
            return true;
        }
        if (!bsMsg.mtrLbDescTxt_A.getValue().equals(asMsg.mtrLbDescTxt_A.getValue())) {
            return true;
        }
        if (!bsMsg.mtrLbNm_AF.getValue().equals(asMsg.mtrLbNm_AF.getValue())) {
            return true;
        }
        if (!bsMsg.mtrLbNm_AG.getValue().equals(asMsg.mtrLbNm_AG.getValue())) {
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
        if (!bsMsg.bwMtrFlg_A.getValue().equals(asMsg.bwMtrFlg_A.getValue())) {
            return true;
        }
        if (!bsMsg.colorMtrFlg_A.getValue().equals(asMsg.colorMtrFlg_A.getValue())) {
            return true;
        }
        if (!bsMsg.totMtrFlg_A.getValue().equals(asMsg.totMtrFlg_A.getValue())) {
            return true;
        }
        if (!bsMsg.corpAdvtgFlg_A.getValue().equals(asMsg.corpAdvtgFlg_A.getValue())) {
            return true;
        }
        if (!bsMsg.mtrCntrId_A.getValue().equals(asMsg.mtrCntrId_A.getValue())) {
            return true;
        }
        if (!bsMsg.intgMdseCd_A.getValue().equals(asMsg.intgMdseCd_A.getValue())) {
            return true;
        }
        // START 2018/02/05 M.Naito [QC#21184,ADD]
        if (!bsMsg.invPrintMtrLbDescTxt_A.getValue().equals(asMsg.invPrintMtrLbDescTxt_A.getValue())) {
            return true;
        }
        // END 2018/02/05 M.Naito [QC#21184,ADD]
        return false;
    }

    private boolean checkValidation(NSAL1190CMsg cMsg, NSAL1190SMsg sMsg, List<NSAL1190_ASMsg> submitList) {
        boolean errFlg = true;
        for (int i = 0; i < submitList.size(); i++) {
            NSAL1190_ASMsg asMsg = submitList.get(i);
            // START 2017/08/02 T.Kanasaka [QC#18193,DEL]
//            if (!cheakMandatoryForMtrIdxCd(cMsg, asMsg)) {
//                errFlg = false;
//            }
            // END 2017/08/02 T.Kanasaka [QC#18193,DEL]
            if (!cheakMandatoryForMtrLbTpCd(cMsg, asMsg)) {
                errFlg = false;
            }
            if (!checkDate(asMsg)) {
                errFlg = false;
            }
            if (!checkActvFlg(cMsg, asMsg)) {
                errFlg = false;
            }
            if (!checkMtrLbNm(cMsg, asMsg, submitList)) {
                errFlg = false;
            }
            if (!checkMtrLbDescTxt(cMsg, asMsg, submitList)) {
                errFlg = false;
            }
        }
        return errFlg;
    }

    // START 2017/08/02 T.Kanasaka [QC#18193,DEL]
//    private boolean cheakMandatoryForMtrIdxCd(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg) {
//
//        boolean errFlg = true;
//        if (MTR_IDX.NON_FLEET.equals(asMsg.mtrIdxCd_A.getValue())) {
//            // START 2016/05/26 T.Tomita [QC#8896, MOD]
//            if (!hasValue(asMsg.mtrLbNm_AF)) {
//                if (MTR_LB_TP.REGULAR_METER.equals(asMsg.mtrLbTpCd_A.getValue())) {
//                    asMsg.mtrLbNm_AF.setErrorInfo(1, NSAM0442E, new String[] {FIELD_NM_FLT_COUNTER, FIELD_NM_IND, MTR_IDX.NON_FLEET });
//                    errFlg = false;
//                }
//            } else if (!checkExistForCounter(cMsg, asMsg.mtrLbNm_AF.getValue(), MTR_IDX.FLEET)) {
//                asMsg.mtrLbNm_AF.setErrorInfo(1, NSAM0040E, new String[] {FIELD_NM_FLT_COUNTER });
//                errFlg = false;
//            }
//            if (!hasValue(asMsg.mtrLbNm_AG)) {
//                if (MTR_LB_TP.REGULAR_METER.equals(asMsg.mtrLbTpCd_A.getValue())) {
//                    asMsg.mtrLbNm_AG.setErrorInfo(1, NSAM0442E, new String[] {FIELD_NM_AGG_COUNTER, FIELD_NM_IND, MTR_IDX.NON_FLEET });
//                    errFlg = false;
//                }
//            } else if (!checkExistForCounter(cMsg, asMsg.mtrLbNm_AG.getValue(), MTR_IDX.AGGREGATE)) {
//                asMsg.mtrLbNm_AG.setErrorInfo(1, NSAM0040E, new String[] {FIELD_NM_AGG_COUNTER });
//                errFlg = false;
//            }
//            // END 2016/05/26 T.Tomita [QC#8896, MOD]
//        }
//        return errFlg;
//    }
    // END 2017/08/02 T.Kanasaka [QC#18193,DEL]

    private boolean cheakMandatoryForMtrLbTpCd(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg) {

        boolean errFlg = true;
        if (MTR_LB_TP.BILLING_METER.equals(asMsg.mtrLbTpCd_A.getValue())) {
            if (BLLG_MTR_LVL_NUM_0.equals(asMsg.bllgMtrLvlNum_A.getValue())) {
                asMsg.bllgMtrLvlNum_A.setErrorInfo(1, NSAM0442E, new String[] {FIELD_NM_LEVEL, FIELD_NM_TYPE, MTR_LB_TP.BILLING_METER });
                errFlg = false;
            }
            if (!hasValue(asMsg.intgMdseCd_A)) {
                asMsg.intgMdseCd_A.setErrorInfo(1, NSAM0442E, new String[] {FIELD_NM_COUNTER_ITEM, FIELD_NM_TYPE, MTR_LB_TP.BILLING_METER });
                errFlg = false;
            }
            errFlg = checkIntgMdseCd(cMsg, asMsg);
        } else {
            if (hasValue(asMsg.intgMdseCd_A)) {
                errFlg = checkIntgMdseCd(cMsg, asMsg);
            }
        }
        return errFlg;
    }

    private boolean checkExistForCounter(NSAL1190CMsg cMsg, String mtrLbCdNm, String mtrIdxCd) {

        MTR_LBTMsg tMsg = getMtrLb(cMsg.glblCmpyCd.getValue(), mtrLbCdNm);

        if (tMsg == null) {
            return false;
        }
        if (!tMsg.mtrIdxCd.getValue().equals(mtrIdxCd)) {
            return false;
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(tMsg.actvFlg.getValue())
                || ZYPDateUtil.compare(tMsg.effThruDt.getValue(), cMsg.slsDt.getValue()) >= 0) {
            return true;
        }
        return false;
    }

    private boolean checkDate(NSAL1190_ASMsg asMsg) {

        if (!hasValue(asMsg.effFromDt_A) || !hasValue(asMsg.effThruDt_A)) {
            return true;
        }
        if (ZYPDateUtil.compare(asMsg.effThruDt_A.getValue(), asMsg.effFromDt_A.getValue()) < 0) {
            asMsg.effFromDt_A.setErrorInfo(1, NSAM0374E, new String[] {FIELD_NM_START_DATE });
            asMsg.effThruDt_A.setErrorInfo(1, NSAM0374E, new String[] {FIELD_NM_START_DATE });
            return false;
        }
        return true;
    }

    private boolean checkActvFlg(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg) {

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

    private boolean checkIntgMdseCd(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg) {

        MDSETMsg tMsg = getMdse(cMsg.glblCmpyCd.getValue(), asMsg.intgMdseCd_A.getValue());

        if (tMsg == null) {
            asMsg.intgMdseCd_A.setErrorInfo(1, NSAM0040E, new String[] {FIELD_NM_COUNTER_ITEM });
            return false;
        }
        if (hasValue(tMsg.mdseItemTpCd)) {
            String mdseItemTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSAL1190_INTG_MDSE_ITEM_TP_CD, cMsg.glblCmpyCd.getValue());
            if (!tMsg.mdseItemTpCd.getValue().equals(mdseItemTpCd)) {
                asMsg.intgMdseCd_A.setErrorInfo(1, NSAM0040E, new String[] {FIELD_NM_COUNTER_ITEM });
                return false;
            }
            if (ZYPDateUtil.compare(tMsg.mdseItemActvDt.getValue(), cMsg.slsDt.getValue()) > 0) {
                asMsg.intgMdseCd_A.setErrorInfo(1, NSAM0040E, new String[] {FIELD_NM_COUNTER_ITEM });
                return false;
            }
        }
        return true;
    }

    private boolean checkMtrLbNm(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg, List<NSAL1190_ASMsg> submitList) {

        MTR_LBTMsg tMsg = getMtrLb(cMsg.glblCmpyCd.getValue(), asMsg.mtrLbNm_A.getValue());

        for (int i = 0; i < submitList.size(); i++) {

            if (asMsg.xxRowNum_A.getValueInt() == submitList.get(i).xxRowNum_A.getValueInt()) {
                continue;
            }
            if (asMsg.mtrLbNm_A.getValue().equals(submitList.get(i).mtrLbNm_A.getValue())) {
                asMsg.mtrLbNm_A.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_COUNTER });
                return false;
            }
        }

        if (tMsg != null) {

            for (int i = 0; i < submitList.size(); i++) {
                if (tMsg.mtrLbCd.getValue().equals(asMsg.mtrLbCd_A.getValue())) {
                    continue;
                }
                if (tMsg.mtrLbCd.getValue().equals(submitList.get(i).mtrLbCd_A.getValue())
                        && !tMsg.mtrLbNm.getValue().equals(submitList.get(i).mtrLbNm_A.getValue())) {
                    return true;
                } else {
                    asMsg.mtrLbNm_A.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_COUNTER });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkMtrLbDescTxt(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg, List<NSAL1190_ASMsg> submitList) {

        MTR_LBTMsg tMsg = getmtrLbDescTxt(cMsg.glblCmpyCd.getValue(), asMsg.mtrLbDescTxt_A.getValue());

        for (int i = 0; i < submitList.size(); i++) {

            if (asMsg.xxRowNum_A.getValueInt() == submitList.get(i).xxRowNum_A.getValueInt()) {
                continue;
            }
            if (asMsg.mtrLbDescTxt_A.getValue().equals(submitList.get(i).mtrLbDescTxt_A.getValue())) {
                asMsg.mtrLbDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_DESCRIPTION });
                return false;
            }
        }

        if (tMsg != null) {

            for (int i = 0; i < submitList.size(); i++) {
                if (tMsg.mtrLbCd.getValue().equals(asMsg.mtrLbCd_A.getValue())) {
                    continue;
                }
                if (tMsg.mtrLbCd.getValue().equals(submitList.get(i).mtrLbCd_A.getValue())
                        && !tMsg.mtrLbDescTxt.getValue().equals(submitList.get(i).mtrLbDescTxt_A.getValue())) {
                    return true;
                } else {
                    asMsg.mtrLbDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_DESCRIPTION });
                    return false;
                }
            }
        }
        return true;
    }

    private MTR_LBTMsg getMtrLb(String glblCmpyCd, String mtrLbTpNm) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbNm01", mtrLbTpNm);

        MTR_LBTMsgArray tMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return (MTR_LBTMsg) tMsgArray.get(0);
    }

    private MTR_LBTMsg getmtrLbDescTxt(String glblCmpyCd, String mtrLbDescTxt) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbDescTxt01", mtrLbDescTxt);

        MTR_LBTMsgArray tMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return (MTR_LBTMsg) tMsgArray.get(0);

    }

    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mdseCd, mdseCd);

        return (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private void doSubmit(NSAL1190CMsg cMsg, List<NSAL1190_ASMsg> submitList) {

        for (int i = 0; i < submitList.size(); i++) {
            NSAL1190_ASMsg asMsg = submitList.get(i);
            if (hasValue(asMsg.mtrLbCd_A)) {
                if (!updateForMtrLb(cMsg, asMsg)) {
                    return;
                }
            } else {
                if (!insertForMtrLb(cMsg, asMsg)) {
                    return;
                }
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private boolean insertForMtrLb(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg) {

        MTR_LBTMsg insMsg  = new MTR_LBTMsg();
        String mtrLbCd = ZYPNumbering.getUniqueID(cMsg.glblCmpyCd.getValue(), AUTO_SEQ_MTR_LB_CD);
        int mtrLbCdLen = ZYPCodeDataUtil.getNumConstValue(NSAL1190_MTR_LB_CD_LEN, cMsg.glblCmpyCd.getValue()).intValue();
        setValue(insMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insMsg.mtrLbCd, mtrLbCd.substring(mtrLbCd.length() - mtrLbCdLen));

        S21SsmEZDResult ssmResult = NSAL1190Query.getInstance().getMaxMtrLbSortNum(cMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            BigDecimal mtrLbSortNum = (BigDecimal) list.get(0).get("MTR_LB_SORT_NUM");
            setValue(insMsg.mtrLbSortNum, new BigDecimal(mtrLbSortNum.intValue() + 1));
        } else {
            return false;
        }
        setParam(cMsg, asMsg, insMsg);

        S21FastTBLAccessor.insert(insMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_NM_MTR_LB });
            return false;
        }

        // add start 2016/06/27 CSA Defect#7886
        boolean isExistsModel = false;
        NSAL1190CommonLogic.invokeMasterDataMessaging(isExistsModel, insMsg.ezUpTime.getValue(), asMsg.mtrLbCd_A.getValue(), asMsg.mtrLbNm_A.getValue());
        // add end 2016/06/27 CSA Defect#7886

        return true;
    }

    private boolean updateForMtrLb(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg) {

        MTR_LBTMsg inMsg  = new MTR_LBTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.mtrLbCd, asMsg.mtrLbCd_A);

        MTR_LBTMsg updTMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setParam(cMsg, asMsg, updTMsg);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_MTR_LB });
            return false;
        }

        // add start 2016/06/27 CSA Defect#7886
        boolean isExistsModel = true;
        NSAL1190CommonLogic.invokeMasterDataMessaging(isExistsModel, updTMsg.ezUpTime.getValue(), asMsg.mtrLbCd_A.getValue(), asMsg.mtrLbNm_A.getValue());
        // add end 2016/06/27 CSA Defect#7886

        return true;
    }

    // START 2017/08/02 T.Kanasaka [QC#18193,MOD]
    private void setParam(NSAL1190CMsg cMsg, NSAL1190_ASMsg asMsg, MTR_LBTMsg tMsg) {

//        MTR_LBTMsg fltMsg = getMtrLb(cMsg.glblCmpyCd.getValue(), asMsg.mtrLbNm_AF.getValue());
//        MTR_LBTMsg aggMsg = getMtrLb(cMsg.glblCmpyCd.getValue(), asMsg.mtrLbNm_AG.getValue());

        setValue(tMsg.mtrLbNm, asMsg.mtrLbNm_A);
        setValue(tMsg.mtrLbTpCd, asMsg.mtrLbTpCd_A);
        setValue(tMsg.mtrIdxCd, asMsg.mtrIdxCd_A);
        setValue(tMsg.bllgMtrLvlNum, asMsg.bllgMtrLvlNum_A);
        setValue(tMsg.mtrLbDescTxt, asMsg.mtrLbDescTxt_A);

//        if (fltMsg != null) {
//            setValue(tMsg.fleetMtrLbCd, fltMsg.mtrLbCd);
//        }
//        if (aggMsg != null) {
//            setValue(tMsg.aggrMtrLbCd, aggMsg.mtrLbCd);
//        }
        setValue(tMsg.actvFlg, asMsg.actvFlg_A);
        setValue(tMsg.effFromDt, asMsg.effFromDt_A);
        setValue(tMsg.effThruDt, asMsg.effThruDt_A);
        setValue(tMsg.bwMtrFlg, asMsg.bwMtrFlg_A);
        setValue(tMsg.colorMtrFlg, asMsg.colorMtrFlg_A);
        setValue(tMsg.totMtrFlg, asMsg.totMtrFlg_A);
        setValue(tMsg.corpAdvtgFlg, asMsg.corpAdvtgFlg_A);
        setValue(tMsg.mtrCntrId, asMsg.mtrCntrId_A);
        setValue(tMsg.intgMdseCd, asMsg.intgMdseCd_A);
        // START 2018/02/05 M.Naito [QC#21184,ADD]
        setValue(tMsg.invPrintMtrLbDescTxt, asMsg.invPrintMtrLbDescTxt_A);
        // END 2018/02/05 M.Naito [QC#21184,ADD]
    }
    // END 2017/08/02 T.Kanasaka [QC#18193,MOD]
}

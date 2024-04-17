/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1050;

import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_DELETE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_SAVE;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0010E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0103E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0170E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0202E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0204E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0207E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NLCM0209E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NZZM0003E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.TBL_ABC_ANLS_CLS;
import static business.blap.NLCL1050.constant.NLCL1050Constant.VAL_ASG_PCT_HUNDRED;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ABC_ANLS_CLSTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL1050 ABC Class Setup
 * Function Name : update business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   CITS            N.Akaishi       Create          N/A
 * </pre>
 */
public class NLCL1050BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1050_SAVE.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_Save((NLCL1050CMsg) cMsg);
            } else if (EVENT_NM_NLCL1050_DELETE.equals(screenAplID)) {
                doProcess_NLCL1050Scrn00_Delete((NLCL1050CMsg) cMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLCL1050Scrn00_Save
     * @param cMsg NLCL1050CMsg
     */
    private void doProcess_NLCL1050Scrn00_Save(NLCL1050CMsg cMsg) {

        // input check
        if (!inputCheck(cMsg)) {
            return;
        }

        // physical remove ABC Class Info
        if (ZYPCommonFunc.hasValue(cMsg.abcAnlsClsNum_SV)) {
            if (!deleteAbcInfoForSave(cMsg)) {
                return;
            }
        }

        // insert ABC Class Info
        BigDecimal currentValAsgPct = BigDecimal.ZERO;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ABC_ANLS_CLSTMsg insertData  = new ABC_ANLS_CLSTMsg();

            if (!ZYPCommonFunc.hasValue(cMsg.abcAnlsClsNum_SV)) {
                ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_SV, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_CLS_NUM_SQ).toString());
            }

            ZYPEZDItemValueSetter.setValue(insertData.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertData.abcAnlsClsNum, cMsg.abcAnlsClsNum_SV);
            ZYPEZDItemValueSetter.setValue(insertData.abcAnlsClsCratDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
            ZYPEZDItemValueSetter.setValue(insertData.abcAnlsClsNm, cMsg.abcAnlsClsNm);
            ZYPEZDItemValueSetter.setValue(insertData.abcAnlsClsPrtyNum, cMsg.A.no(i).abcAnlsClsPrtyNum);
            ZYPEZDItemValueSetter.setValue(insertData.abcAnlsClsTagCd, cMsg.A.no(i).abcAnlsClsTagCd);
            ZYPEZDItemValueSetter.setValue(insertData.abcAnlsClsTagDescTxt, cMsg.A.no(i).abcAnlsClsTagDescTxt);
            ZYPEZDItemValueSetter.setValue(insertData.cycleCntFreqDaysAot, cMsg.A.no(i).cycleCntFreqDaysAot);
            ZYPEZDItemValueSetter.setValue(insertData.valAsgPct, cMsg.A.no(i).valAsgPct);

            // cumValAsgFromPct
            ZYPEZDItemValueSetter.setValue(insertData.cumValAsgFromPct, currentValAsgPct);
            currentValAsgPct = currentValAsgPct.add(cMsg.A.no(i).valAsgPct.getValue());
            // cumValAsgToPct
            ZYPEZDItemValueSetter.setValue(insertData.cumValAsgToPct, currentValAsgPct);
            EZDTBLAccessor.insert(insertData);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertData.getReturnCode())) {
                cMsg.setMessageInfo(NLCM0103E, new String[] {TBL_ABC_ANLS_CLS, cMsg.abcAnlsClsNum_SV.getValue()});
                return;
            }
        }
    }

    /**
     * doProcess_NLCL1050Scrn00_Delete
     * @param cMsg NLCL1050CMsg
     */
    private void doProcess_NLCL1050Scrn00_Delete(NLCL1050CMsg cMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(cMsg.xxSupdFlg.getValue())) {
            return;
        }

        Map<String, Object> ssmParamAsg = new HashMap<String, Object>();
        ssmParamAsg.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParamAsg.put("abcAnlsClsNum", cMsg.abcAnlsClsNum_SV.getValue());

        S21SsmEZDResult resultAsg = NLCL1050Query.getInstance().checkAbcAnlsAsg(ssmParamAsg);

        if (resultAsg.isCodeNormal()) {
            BigDecimal count = (BigDecimal) resultAsg.getResultObject();

            if (BigDecimal.ZERO.compareTo(count) < 0) {
                cMsg.abcAnlsClsNm.setErrorInfo(1, NLCM0209E);
                return;
            }
        }

        Map<String, Object> ssmParamRslt = new HashMap<String, Object>();
        ssmParamRslt.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParamRslt.put("abcAnlsClsNum", cMsg.abcAnlsClsNum_SV.getValue());

        S21SsmEZDResult resultRslt = NLCL1050Query.getInstance().checkAbcAnlsAsg(ssmParamRslt);

        if (resultRslt.isCodeNormal()) {
            BigDecimal count = (BigDecimal) resultAsg.getResultObject();

            if (BigDecimal.ZERO.compareTo(count) < 0) {
                cMsg.abcAnlsClsNm.setErrorInfo(1, NLCM0209E);
                return;
            }
        }

        // processing registration
        if (!delete(cMsg)) {
            return;
        }

    }

    /**
     * 
     * inputCheck
     * 
     * @param cMsg NLCL1050CMsg
     * @return boolean
     */
    private static boolean inputCheck(NLCL1050CMsg cMsg) {

        boolean ret = true;

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NLCM0010E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.abcAnlsClsNum_SV)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
            ssmParam.put("abcAnlsClsNm", cMsg.abcAnlsClsNm.getValue());

            S21SsmEZDResult result = NLCL1050Query.getInstance().getAbcAnlsClsList(ssmParam);

            if (result.isCodeNormal()) {
                BigDecimal count = (BigDecimal) result.getResultObject();

                if (BigDecimal.ZERO.compareTo(count) < 0) {
                    cMsg.abcAnlsClsNm.setErrorInfo(1, NLCM0207E);
                    return false;
                }
            }
        }

        // check for duplication (Class Tag Name, Priority#)
        Map<String, String> abcAnlsClsTagMap = new HashMap<String, String>();
        Map<String, BigDecimal> abcAnlsClsPrtyNumMap = new HashMap<String, BigDecimal>();
        BigDecimal valAsgPctCnt = BigDecimal.ZERO;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            String currrentAbcAnlsClsTag = cMsg.A.no(i).abcAnlsClsTagCd.getValue();
            BigDecimal currrentAbcAnlsClsPrtyNum = cMsg.A.no(i).abcAnlsClsPrtyNum.getValue();

            if (abcAnlsClsTagMap.containsValue(currrentAbcAnlsClsTag)) {
                cMsg.A.no(i).abcAnlsClsTagCd.setErrorInfo(1, NLCM0202E, new String[] {"ABC Class Tag Name"});
                ret = false;
            } else {
                abcAnlsClsTagMap.put(String.valueOf(i), currrentAbcAnlsClsTag);
            }
            if (abcAnlsClsPrtyNumMap.containsValue(currrentAbcAnlsClsPrtyNum)) {
                cMsg.A.no(i).abcAnlsClsPrtyNum.setErrorInfo(1, NLCM0202E, new String[] {"Priority#"});
                ret = false;
            } else {
                abcAnlsClsPrtyNumMap.put(String.valueOf(i), currrentAbcAnlsClsPrtyNum);
            }

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).valAsgPct)) {
                valAsgPctCnt = valAsgPctCnt.add(cMsg.A.no(i).valAsgPct.getValue());
            }
        }

        // Value Assignment ' s total count
        if (valAsgPctCnt.compareTo(new BigDecimal(VAL_ASG_PCT_HUNDRED)) != 0) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).valAsgPct.setErrorInfo(1, NLCM0204E);
            }
            ret = false;
        }

        return ret;
    }

    private static List<Map<String, Object>> lockAbcAnlsCls(NLCL1050CMsg cMsg) {

        S21SsmEZDResult abcAnlsClsList = NLCL1050Query.getInstance().getAbcAnlsClsList(cMsg.glblCmpyCd.getValue(), cMsg.abcAnlsClsNum_SV.getValue(), EVENT_NM_NLCL1050_SAVE);
        List<Map<String, Object>> delTarget = (List<Map<String, Object>>) abcAnlsClsList.getResultObject();

        // column lock for deletion
        for (Map<String, Object> obj : delTarget) {

            String abcAnlsClsNum = (String) obj.get("ABC_ANLS_CLS_NUM");
            BigDecimal abcAnlsClsPrtyNum = (BigDecimal) obj.get("ABC_ANLS_CLS_PRTY_NUM");
            ABC_ANLS_CLSTMsg delParam = new ABC_ANLS_CLSTMsg();
            ZYPEZDItemValueSetter.setValue(delParam.abcAnlsClsNum, abcAnlsClsNum);
            ZYPEZDItemValueSetter.setValue(delParam.abcAnlsClsPrtyNum, abcAnlsClsPrtyNum);
            ZYPEZDItemValueSetter.setValue(delParam.glblCmpyCd, cMsg.glblCmpyCd);
            ABC_ANLS_CLSTMsg delTMsg = (ABC_ANLS_CLSTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(delParam);
            if (delTMsg == null) {
                cMsg.setMessageInfo(NZZM0003E);
                return null;
            }

        }

        // get current time stamp for exclusion control
        int idx = 0;
        for (idx = 0; idx < cMsg.A.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(idx).abcAnlsClsNum_HD)) {

                ABC_ANLS_CLSTMsg updateParam = new ABC_ANLS_CLSTMsg();
                ZYPEZDItemValueSetter.setValue(updateParam.abcAnlsClsNum, cMsg.A.no(idx).abcAnlsClsNum_HD.getValue().toString());
                ZYPEZDItemValueSetter.setValue(updateParam.abcAnlsClsPrtyNum, cMsg.A.no(idx).abcAnlsClsPrtyNum.getValue());
                ZYPEZDItemValueSetter.setValue(updateParam.glblCmpyCd, cMsg.glblCmpyCd);
                ABC_ANLS_CLSTMsg updateTMsg = (ABC_ANLS_CLSTMsg) EZDTBLAccessor.findByKey(updateParam);

                if (updateTMsg != null) {
                    if (!ZYPDateUtil.isSameTimeStamp(
                            updateTMsg.ezUpTime.getValue(),
                            updateTMsg.ezUpTimeZone.getValue(),
                            cMsg.A.no(idx).ezUpTime_HD.getValue(),
                            cMsg.A.no(idx).ezUpTimeZone_HD.getValue())) {

                        cMsg.setMessageInfo(NZZM0003E);
                        return null;
                    }
                } else {
                    cMsg.setMessageInfo(NLCM0170E, new String[] {TBL_ABC_ANLS_CLS});
                    return null;
                }
            }

        }

        return delTarget;
    }

    /**
     * 
     * delete ABC Class Info
     * 
     * @param cMsg NLCL1050CMsg
     * @return boolean
     */
    private static boolean delete(NLCL1050CMsg cMsg) {

        List<Map<String, Object>> delTarget = lockAbcAnlsCls(cMsg);

        // delete ABC Class Info By ABC Class Name
        for (Map<String, Object> obj : delTarget) {
            ABC_ANLS_CLSTMsg delTMsg = new ABC_ANLS_CLSTMsg();
            ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(delTMsg.abcAnlsClsNum, (String) obj.get("ABC_ANLS_CLS_NUM"));
            ZYPEZDItemValueSetter.setValue(delTMsg.abcAnlsClsPrtyNum, (BigDecimal) obj.get("ABC_ANLS_CLS_PRTY_NUM"));
            EZDTBLAccessor.logicalRemove(delTMsg);
        }

        return true;
    }

    /**
     * 
     * Physical Remove ABC Class Info(for Save)
     * 
     * @param cMsg NLCL1050CMsg
     * @return boolean
     */
    private static boolean deleteAbcInfoForSave(NLCL1050CMsg cMsg) {

        List<Map<String, Object>> delTarget = lockAbcAnlsCls(cMsg);

        // physical remove ABC Class Info By ABC Class Name
        for (Map<String, Object> obj : delTarget) {
            ABC_ANLS_CLSTMsg delTMsg = new ABC_ANLS_CLSTMsg();
            ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(delTMsg.abcAnlsClsNum, (String) obj.get("ABC_ANLS_CLS_NUM"));
            ZYPEZDItemValueSetter.setValue(delTMsg.abcAnlsClsPrtyNum, (BigDecimal) obj.get("ABC_ANLS_CLS_PRTY_NUM"));
            EZDTBLAccessor.remove(delTMsg);
        }

        return true;
    }
}

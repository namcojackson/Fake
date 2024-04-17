/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0380;

import static business.blap.NSBL0380.constant.NSBL0380Constant.BEGININDEX_MONTH;
import static business.blap.NSBL0380.constant.NSBL0380Constant.BEGININDEX_YEAR;
import static business.blap.NSBL0380.constant.NSBL0380Constant.CHECK_DIGIT_2;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ENDINDEX_MONTH;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ENDINDEX_YEAR;
import static business.blap.NSBL0380.constant.NSBL0380Constant.NOT_NULL;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_1;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_10;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_11;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_2;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_3;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_4;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_5;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_6;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_7;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_8;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_LAYER_NUM_9;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ZERO_PADDING;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ORG_UNITTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * History Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public final class NSBL0380Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0380Query INSTANCE = new NSBL0380Query();

    /**
     * Constructor.
     */
    private NSBL0380Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0380Query
     */
    public static NSBL0380Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        S21SsmEZDResult srchResult;
        srchResult = getSsmEZDClient().queryEZDMsgArray("getSrchLayerNum", getSsmParamLayerNum(cMsg), sMsg.A);
        if (!srchResult.isCodeNotFound()) {
            srchResult = getSsmEZDClient().queryEZDMsgArray("getSrchDtl", getSsmParamDtl(cMsg, sMsg), sMsg.A);
        }
        return srchResult;
    }

    /**
     * get SsmParam for Layer Name
     * @param cMsg NSBL0380CMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParamLayerNum(NSBL0380CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("xxSumYrMthStartDay", getFirstDayOfMonth(cMsg.xxSumYrMth_H.getValue()));
        params.put("xxSumYrMthEndDay", getEndDayOfMonth(cMsg.xxSumYrMth_H.getValue()));
        params.put("orgCd", cMsg.orgCd_HT.getValue());
        params.put("svcTaskLtstFlg", ZYPConstant.FLG_ON_Y);

        params.put("layerNum1", ORG_LAYER_NUM_1);
        params.put("layerNum2", ORG_LAYER_NUM_2);
        params.put("layerNum3", ORG_LAYER_NUM_3);
        params.put("layerNum4", ORG_LAYER_NUM_4);
        params.put("layerNum5", ORG_LAYER_NUM_5);
        params.put("layerNum6", ORG_LAYER_NUM_6);
        params.put("layerNum7", ORG_LAYER_NUM_7);
        params.put("layerNum8", ORG_LAYER_NUM_8);
        params.put("layerNum9", ORG_LAYER_NUM_9);
        params.put("layerNum10", ORG_LAYER_NUM_10);
        params.put("layerNum11", ORG_LAYER_NUM_11);
        return params;
    }

    /**
     * get First day of Month
     * @param yyyyMm String
     * @return String
     */
    public static String getFirstDayOfMonth(String yyyyMm) {
        String yearStr = yyyyMm.substring(BEGININDEX_YEAR, ENDINDEX_YEAR);
        String monthStr = yyyyMm.substring(BEGININDEX_MONTH, ENDINDEX_MONTH);
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int startDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, startDay);

        String outYear = String.valueOf(calendar.get(Calendar.YEAR));
        String outMonth = String.valueOf(calendar.get(Calendar.MONTH));
        if (Integer.parseInt(outMonth) < CHECK_DIGIT_2) {
            outMonth = ZERO_PADDING + String.valueOf((Integer.parseInt(outMonth) + 1));
        } else {
            outMonth = String.valueOf((Integer.parseInt(outMonth) + 1));
        }
        String outDay = String.valueOf(calendar.get(Calendar.DATE));
        if (Integer.parseInt(outDay) < CHECK_DIGIT_2) {
            outDay = ZERO_PADDING + outDay;
        }
        String startDate = outYear + outMonth + outDay;
        return startDate;
    }

    /**
     * get End day of Month
     * @param yyyyMm String
     * @return String
     */
    public static String getEndDayOfMonth(String yyyyMm) {
        String yearStr = yyyyMm.substring(BEGININDEX_YEAR, ENDINDEX_YEAR);
        String monthStr = yyyyMm.substring(BEGININDEX_MONTH, ENDINDEX_MONTH);
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, endDay);

        String outYear = String.valueOf(calendar.get(Calendar.YEAR));
        String outMonth = String.valueOf(calendar.get(Calendar.MONTH));
        if (Integer.parseInt(outMonth) < CHECK_DIGIT_2) {
            outMonth = ZERO_PADDING + String.valueOf((Integer.parseInt(outMonth) + 1));
        } else {
            outMonth = String.valueOf((Integer.parseInt(outMonth) + 1));
        }
        String outDay = String.valueOf(calendar.get(Calendar.DATE));
        if (Integer.parseInt(outDay) < CHECK_DIGIT_2) {
            outDay = ZERO_PADDING + outDay;
        }
        String endDate = outYear + outMonth + outDay;
        return endDate;
    }

    /**
     * get SsmParamOther
     * @param cMsg NSBL0380CMsg
     * @param sMsg NSBL0380SMsg
     * @return Map<String, Object>
     */
    private static Map<String, Object> getSsmParamDtl(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        BigDecimal orgLayerNum = null;
        orgLayerNum = sMsg.A.no(0).orgLayerNum_A.getValue();
        params.put("orgLayerNum", orgLayerNum);

        if (ORG_LAYER_NUM_1.equals(orgLayerNum)) {
            params.put("firstOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_2.equals(orgLayerNum)) {
            params.put("scdOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_3.equals(orgLayerNum)) {
            params.put("thirdOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_4.equals(orgLayerNum)) {
            params.put("frthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_5.equals(orgLayerNum)) {
            params.put("fifthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_6.equals(orgLayerNum)) {
            params.put("sixthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_7.equals(orgLayerNum)) {
            params.put("svnthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_8.equals(orgLayerNum)) {
            params.put("eightOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_9.equals(orgLayerNum)) {
            params.put("ninthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_10.equals(orgLayerNum)) {
            params.put("tenthOrgCd", NOT_NULL);
        } else if (ORG_LAYER_NUM_11.equals(orgLayerNum)) {
            params.put("elvthOrgCd", NOT_NULL);
        }
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("xxSumYrMthStartDay", getFirstDayOfMonth(cMsg.xxSumYrMth_H.getValue()));
        params.put("xxSumYrMthEndDay", getEndDayOfMonth(cMsg.xxSumYrMth_H.getValue()));
        params.put("orgCd", cMsg.orgCd_HT.getValue());
        params.put("svcTaskLtstFlg", ZYPConstant.FLG_ON_Y);
        params.put("limitCnt", sMsg.A.length() + 1);

        params.put("layerNum1", ORG_LAYER_NUM_1);
        params.put("layerNum2", ORG_LAYER_NUM_2);
        params.put("layerNum3", ORG_LAYER_NUM_3);
        params.put("layerNum4", ORG_LAYER_NUM_4);
        params.put("layerNum5", ORG_LAYER_NUM_5);
        params.put("layerNum6", ORG_LAYER_NUM_6);
        params.put("layerNum7", ORG_LAYER_NUM_7);
        params.put("layerNum8", ORG_LAYER_NUM_8);
        params.put("layerNum9", ORG_LAYER_NUM_9);
        params.put("layerNum10", ORG_LAYER_NUM_10);
        params.put("layerNum11", ORG_LAYER_NUM_11);
        return params;
    }

    /**
     * get Direct Sales Organization Unit
     * @param glblCmpyCd String
     * @param orgCd String
     * @return DS_ORG_UNITTMsg
     */
    public DS_ORG_UNITTMsg getDsOrgUnit(String glblCmpyCd, String orgCd) {
        DS_ORG_UNITTMsg tMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, orgCd);
        DS_ORG_UNITTMsg result = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKey(tMsg);
        return result;
    }
}

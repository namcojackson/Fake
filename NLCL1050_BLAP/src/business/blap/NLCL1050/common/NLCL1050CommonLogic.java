/*
 * Copyright(c)2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NLCL1050.common;

import static business.blap.NLCL1050.constant.NLCL1050Constant.EVENT_NM_NLCL1050_SEARCH;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NZZM0000E;
import static business.blap.NLCL1050.constant.NLCL1050Constant.NZZM0001W;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NLCL1050.NLCL1050CMsg;
import business.blap.NLCL1050.NLCL1050Query;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NLCL1050 ABC Class Setup
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   CITS            N Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050CommonLogic {

    /**
     * 
     * getAbcAnlsClsList
     * 
     * @param cMsg NLCL1050CMsg
     */
    public static void getAbcAnlsClsList(NLCL1050CMsg cMsg) {

        cMsg.abcAnlsClsNum_SV.clear();

        S21SsmEZDResult abcAnlsClsList = NLCL1050Query.getInstance().getAbcAnlsClsList(cMsg.glblCmpyCd.getValue(), cMsg.abcAnlsClsNm.getValue(), EVENT_NM_NLCL1050_SEARCH);

        List<Map<String, Object>> ret = (List<Map<String, Object>>) abcAnlsClsList.getResultObject();

        if (abcAnlsClsList.isCodeNormal()) {

            int queryResCnt = abcAnlsClsList.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            int i = 0;
            for (Map<String, Object> obj : ret) {
                if (i == cMsg.A.length()) {
                    break;
                }

                if (!ZYPCommonFunc.hasValue(cMsg.abcAnlsClsNum_SV)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNum_SV, (String) obj.get("ABC_ANLS_CLS_NUM"));
                }

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).abcAnlsClsPrtyNum, (BigDecimal) obj.get("ABC_ANLS_CLS_PRTY_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).abcAnlsClsTagCd, (String) obj.get("ABC_ANLS_CLS_TAG_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).abcAnlsClsTagDescTxt, (String) obj.get("ABC_ANLS_CLS_TAG_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).cycleCntFreqDaysAot, (BigDecimal) obj.get("CYCLE_CNT_FREQ_DAYS_AOT"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).valAsgPct, (BigDecimal) obj.get("VAL_ASG_PCT"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).abcAnlsClsNm_A1, (String) obj.get("ABC_ANLS_CLS_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).abcAnlsClsNum_HD, (String) obj.get("ABC_ANLS_CLS_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ezUpTime_HD, (String) obj.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).ezUpTimeZone_HD, (String) obj.get("EZUPTIMEZONE"));

                i++;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.abcAnlsClsNm, (String) ret.get(0).get("ABC_ANLS_CLS_NM"));
            cMsg.A.setValidCount(i);

        } else {
            cMsg.setMessageInfo(NZZM0000E);
        }

    }

}

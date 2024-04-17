package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import business.db.CCYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Hitachi         T.Kanasaka      Create          N/A
 * 2018/06/29   Hitachi         K.Kojima        Update          QC#23685-1
 *</pre>
 */
public class NSXC003001Exchange implements ZYPConstant {

    // START 2018/06/29 K.Kojima [QC#23685-1,ADD]
    private Map<String, Map<String, Object>> exchRateData = new HashMap<String, Map<String, Object>>();

    private Integer stdCcyDigit = null;

    // END 2018/06/29 K.Kojima [QC#23685-1,ADD]

    /**
     * It is a computational method that shows multiplication.
     */
    private static final String MULTIPLICATION = "*";

    /**
     * It is a computational method that shows division.
     */
    private static final String DIVISION = "/";

    /**
     * S21SsmBatchClient
     */
    private static final S21SsmBatchClient SSMCLIENT = S21SsmBatchClient.getClient(NSXC003001Exchange.class);

    /**
     * calcDealFromFunc
     * @param glblCmpyCd Global Company Code
     * @param ccyCd Currency Code
     * @param slsDt Sales Date
     * @param funcAmt Function Amount
     * @return BigDecimal Deal Amount
     */
    public static BigDecimal calcDealFromFunc(String glblCmpyCd, String ccyCd, String slsDt, BigDecimal funcAmt) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ccyCd", ccyCd);
        ssmParam.put("slsDt", slsDt);
        Map<String, Object> resultMap = (Map<String, Object>) SSMCLIENT.queryObject("getExchRateData", ssmParam);
        if (resultMap == null) {
            return null;
        }

        String acctArthTpCd = (String) resultMap.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) resultMap.get("ACTL_EXCH_RATE");

        if (actlExchRate.compareTo(ZERO) <= 0) {
            return null;
        }

        final Integer scale = getDealCcyDigit(glblCmpyCd, ccyCd);
        if (scale == null) {
            return null;
        }

        // Because it is conversion from the function to dealings, the
        // conversion sign from dealings to the function is reversed.
        if (MULTIPLICATION.equals(acctArthTpCd)) {
            acctArthTpCd = DIVISION;
        } else {
            acctArthTpCd = MULTIPLICATION;
        }

        final BigDecimal dealAmt = calcAmt(funcAmt, acctArthTpCd, actlExchRate, scale, HALF_UP);
        return dealAmt;
    }

    /**
     * calcFuncFromDeal
     * @param glblCmpyCd Global Company Code
     * @param ccyCd Currency Code
     * @param slsDt Sales Date
     * @param dealAmt Deal Amount
     * @return BigDecimal Function Amount
     */
    public static BigDecimal calcFuncFromDeal(String glblCmpyCd, String ccyCd, String slsDt, BigDecimal dealAmt) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ccyCd", ccyCd);
        ssmParam.put("slsDt", slsDt);
        Map<String, Object> resultMap = (Map<String, Object>) SSMCLIENT.queryObject("getExchRateData", ssmParam);
        if (resultMap == null) {
            return null;
        }

        String acctArthTpCd = (String) resultMap.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) resultMap.get("ACTL_EXCH_RATE");

        if (actlExchRate.compareTo(ZERO) <= 0) {
            return null;
        }

        final Integer scale = getStdCcyDigit(glblCmpyCd);
        if (scale == null) {
            return null;
        }

        final BigDecimal funcAmt = calcAmt(dealAmt, acctArthTpCd, actlExchRate, scale, HALF_UP);
        return funcAmt;
    }

    // START 2018/06/29 K.Kojima [QC#23685-1,ADD]
    /**
     * calcFuncFromDeal
     * @param glblCmpyCd Global Company Code
     * @param ccyCd Currency Code
     * @param slsDt Sales Date
     * @param dealAmt Deal Amount
     * @return BigDecimal Function Amount
     */
    public BigDecimal calcFuncFromDealNoStatic(String glblCmpyCd, String ccyCd, String slsDt, BigDecimal dealAmt) {

        Map<String, Object> resultMap = null;
        if (this.exchRateData.containsKey(ccyCd)) {
            resultMap = this.exchRateData.get(ccyCd);
        } else {
            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("ccyCd", ccyCd);
            ssmParam.put("slsDt", slsDt);
            resultMap = (Map<String, Object>) SSMCLIENT.queryObject("getExchRateData", ssmParam);
            if (resultMap != null) {
                this.exchRateData.put(ccyCd, resultMap);
            }
        }
        if (resultMap == null) {
            return null;
        }

        String acctArthTpCd = (String) resultMap.get("ACCT_ARTH_TP_CD");
        BigDecimal actlExchRate = (BigDecimal) resultMap.get("ACTL_EXCH_RATE");

        if (actlExchRate.compareTo(ZERO) <= 0) {
            return null;
        }

        Integer scale = null;
        if (this.stdCcyDigit != null) {
            scale = this.stdCcyDigit;
        } else {
            scale = getStdCcyDigit(glblCmpyCd);
            this.stdCcyDigit = scale;
        }
        if (scale == null) {
            return null;
        }

        final BigDecimal funcAmt = calcAmt(dealAmt, acctArthTpCd, actlExchRate, scale, HALF_UP);
        return funcAmt;
    }

    // END 2018/06/29 K.Kojima [QC#23685-1,ADD]

    /**
     * getDealCcyDigit
     * @param glblCmpyCd Global Company Code
     * @param ccyCd Currency Code
     * @return Integer After Decimal Point
     */
    public static Integer getDealCcyDigit(String glblCmpyCd, String ccyCd) {

        CCYTMsg ccyTMsg = new CCYTMsg();
        setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(ccyTMsg.ccyCd, ccyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg == null) {
            return null;
        } else {
            // AFT_DECL_PNT_DIGIT_NUM
            final BigDecimal aftDeclPntDigitNum = ccyTMsg.aftDeclPntDigitNum.getValue();
            if (aftDeclPntDigitNum == null) {
                return null;
            } else {
                return aftDeclPntDigitNum.intValue();
            }
        }
    }

    /**
     * getStdCcyDigit
     * @param glblCmpyCd Global Company Code
     * @return Integer After Decimal Point
     */
    public static Integer getStdCcyDigit(String glblCmpyCd) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return (Integer) SSMCLIENT.queryObject("getStdCcyDigit", ssmParam);
    }

    private static BigDecimal calcAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale, RoundingMode rMode) {

        BigDecimal retAmt = null;

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            if (amt != null) {
                retAmt = amt.multiply(actlExchRate).setScale(scale, HALF_UP);
            }
        } else {
            if (amt != null) {
                retAmt = amt.divide(actlExchRate, scale, HALF_UP);
            }
        }

        return retAmt;
    }
}

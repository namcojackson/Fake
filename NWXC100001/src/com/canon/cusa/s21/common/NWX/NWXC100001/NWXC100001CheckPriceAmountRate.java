/**
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.db.PRC_COND_TPTMsg;
import business.db.PRC_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_UNIT;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Check Price Amount Rate
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/29/2012   Fujitsu         T.Tozuka        Create          N/A
 * 01/30/2013   Fujitsu         D.Yanagisawa    Update          Defect#307
 * 04/19/2013   Fujitsu         D.Yanagisawa    Update          R-OM003-001
 * 04/24/2014   Fujitsu         M.Fuji          Update          CMEX Defect3545
 * </pre>
 */
public class NWXC100001CheckPriceAmountRate {

    //04/19/2013 DEL START R-OM003-001
    /**
     * Value is missing in the parameter's required field.
     */
//    private static final String NWZM0382E = "NWZM0382E";
    //04/19/2013 DEL E N D R-OM003-001

    /**
     * The target item is specified for Amount. Negative number cannot
     * be entered on AmountRate.
     */
    private static final String NWCM0082E = "NWCM0082E";

    /**
     * The target item is specified for Amount. Cannot enter amount
     * less than one cent.
     */
    private static final String NWCM0083E = "NWCM0083E";

    /**
     * The target item is specified for Amount. Cannot enter amount
     * more than 100,000,000,000.
     */
    private static final String NWCM0084E = "NWCM0084E";

    /**
     * The target item is specified for Amount. Negative number cannot
     * be entered on AmountRate.
     */
    private static final String NWCM0085E = "NWCM0085E";

    /**
     * The target item is specified for Amount. Cannot enter amount
     * more than 100%.
     */
    private static final String NWCM0086E = "NWCM0086E";


    //04/19/2013 DEL START R-OM003-001
    //01/30/2013 Add START Defect#307
//    /**
//     * The target item is specified for Amount. Positive number cannot
//     * be entered on AmountRate.
//     */
//    private static final String NWCM0087E = "NWCM0087E";
//
//    /**
//     * The target item is specified for Amount. Cannot enter amount
//     * less than -100%.
//     */
//    private static final String NWCM0088E = "NWCM0088E";
    //01/30/2013 Add E N D Defect#307
    //04/19/2013 DEL E N D R-OM003-001

    /**
     * "SELECT" yielded "0" results. [Table Name: @, Search Key: @]
     */
    private static final String NWCM0007E = "NWCM0007E";

    //04/19/2013 ADD START R-OM003-001
    /**
     * An input parameter, [@],  has not been set.
     */
    private static final String NWCM0089E = "NWCM0089E";
    //04/19/2013 ADD E N D R-OM003-001
    //WDS 2014/2/14 Mod Start Defect#3545
    /**
     * @ is mandatory value.
     */
    private static final String NWAM0298E = "NWAM0298E";
    /**
     *The target item is specified for Amount.  The AmountRate, you can enter 
     *only up to 5 orders of magnitude less than few.
     */
    private static final String NWCM0091E = "NWCM0091E";
    //WDS 2014/2/14 Mod Start Defect#3545

    /** The Constant MAX_AMOUNT_DIGITS. */
    private static final int MAX_AMOUNT_DIGITS = 11;

    /** The max amount. */
    private final BigDecimal maxAmount;

    /** The Constant MAX_PERCENT_DIGITS. */
    private static final int MAX_PERCENT_DIGITS = 0;

    /** The max percent. */
    private final BigDecimal maxPercent;


    //04/19/2013 DEL START R-OM003-001
    //01/30/2013 Upd START Defect#307
//    /** The min percent. */
//    private final BigDecimal minPercent;
    //01/30/2013 Upd START Defect#307
    //04/19/2013 DEL E N D R-OM003-001

    /** this Class class **/
    private static Class<NWXC100001CheckPriceAmountRate> clazz = NWXC100001CheckPriceAmountRate.class;

    /** ssm batch client */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(clazz);

    /**
     * Instantiates a new this class.
     */
    public NWXC100001CheckPriceAmountRate() {
        maxAmount = BigDecimal.TEN.pow(MAX_AMOUNT_DIGITS);
        maxPercent = BigDecimal.TEN.pow(MAX_PERCENT_DIGITS);
        //04/19/2013 DEL START R-OM003-001
//        minPercent = BigDecimal.TEN.pow(MAX_PERCENT_DIGITS).negate();
        //04/19/2013 DEL E N D R-OM003-001
    }

    /** The error message code. */
    private String errorMessageCd = null;

    /**
     * Check price amount rate.
     * @param glblCmpyCd String
     * @param prcCondTpCd String
     * @param prcAmtRate BigDecimal
     * @return true, if successful
     */
    public boolean checkPriceAmountRate(String glblCmpyCd, String prcCondTpCd, BigDecimal prcAmtRate) {
        return checkPriceAmountRate(glblCmpyCd, prcCondTpCd, prcAmtRate, null);
    }

    /**
     * Check price amount rate.
     * @param glblCmpyCd String
     * @param prcCondTpCd String
     * @param prcAmtRate BigDecimal
     * @param ccyCd String
     * @return true, if successful
     */
    public boolean checkPriceAmountRate(String glblCmpyCd, String prcCondTpCd, BigDecimal prcAmtRate, String ccyCd) {

        // check mandatory (glblCmpyCd)
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            //WDS 2014/4/24 Mod Start CMEX Defect#3545
//            throw new S21AbendException(NWCM0089E, new String[] {"glblCmpyCd"});
            throw new S21AbendException(NWAM0298E, new String[] {"glblCmpyCd"});
            //WDS 2014/4/24 Mod End CMEX Defect#3545
        }
        // check mandatory (prcCondTpCd)
        if (!ZYPCommonFunc.hasValue(prcCondTpCd)) {
            //WDS 2014/4/24 Mod Start CMEX Defect#3545
//            throw new S21AbendException(NWCM0089E, new String[] {"prcCondTpCd"});
            throw new S21AbendException(NWAM0298E, new String[] {"prcCondTpCd"});
            //WDS 2014/4/24 Mod End CMEX Defect#3545
        }
        // check mandatory (prcAmtRate)
        if (!ZYPCommonFunc.hasValue(prcAmtRate)) {
            //WDS 2014/4/24 Mod Start CMEX Defect#3545
//            throw new S21AbendException(NWCM0089E, new String[] {"prcAmtRate"});
            throw new S21AbendException(NWAM0298E, new String[] {"prcAmtRate"});
            //WDS 2014/4/24 Mod End CMEX Defect#3545
        }
        // check mandatory (ccyCd)
        if (!ZYPCommonFunc.hasValue(ccyCd)) {
            //WDS 2014/4/24 Mod Start CMEX Defect#3545
//            throw new S21AbendException(NWCM0089E, new String[] {"ccyCd"});
            throw new S21AbendException(NWAM0298E, new String[] {"ccyCd"});
            //WDS 2014/4/24 Mod End CMEX Defect#3545
        }

        PRC_COND_TPTMsg prcCondTpValue = findPrcCondTp(glblCmpyCd, prcCondTpCd);
        if (prcCondTpValue == null) {
            // master check error
            throw new S21AbendException(NWCM0007E, new String[] {"PRC_COND_TP", "GLBL_CMPY_CD : " + glblCmpyCd + " PRC_COND_TP_CD : " + prcCondTpCd });
        }

        // get aftDeclPntDigitNum
        BigDecimal aftDeclPntDigitNum;
        if (ZYPCommonFunc.hasValue(ccyCd)) {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", glblCmpyCd);
            mapParam.put("ccyCd", ccyCd);
            aftDeclPntDigitNum = (BigDecimal) ssmBatchClient.queryObject("getAftDeclPntDigitNumByCcyCd", mapParam);
            if(aftDeclPntDigitNum == null) {
                // master check error
                throw new S21AbendException(NWCM0007E, new String[] {"CCY", "GLBL_CMPY_CD : " + glblCmpyCd + " CCY_CD : " + ccyCd });
            }
        } else {
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", glblCmpyCd);
            aftDeclPntDigitNum = (BigDecimal) ssmBatchClient.queryObject("getAftDeclPntDigitNumByGlblCmpy", mapParam);
            if(aftDeclPntDigitNum == null) {
                // master check error
                throw new S21AbendException(NWCM0007E, new String[] {"GLBL_CMPY", "GLBL_CMPY_CD : " + glblCmpyCd });                
            }
        }

        BigDecimal minAmt = new BigDecimal("0.1").pow(aftDeclPntDigitNum.intValue());

        if (PRC_COND_UNIT.AMT.equals(prcCondTpValue.prcCondUnitCd.getValue())) {
            if (prcAmtRate.compareTo(BigDecimal.ZERO) < 0) {
                setErrorMessageCd(NWCM0082E);
                return false;
            }

            if (!checkFraction(prcAmtRate, minAmt)) {
                setErrorMessageCd(NWCM0083E);
                return false;
            }

            if (maxAmount.compareTo(prcAmtRate) <= 0) {
                setErrorMessageCd(NWCM0084E);
                return false;
            }
        } else {
            //04/19/2013 DEL START R-OM003-001
            // 01/30/2013 Upd START Defect#307
            // if
            // (PRC_DTL_GRP.RESTOCKING_FEE.equals(prcCondTpValue.prcDtlGrpCd.getValue()))
            // {
            // if (prcAmtRate.compareTo(BigDecimal.ZERO) > 0) {
            // setErrorMessageCd(NWCM0087E);
            // return false;
            // }
            // if (minPercent.compareTo(prcAmtRate) > 0) {
            // setErrorMessageCd(NWCM0088E);
            // return false;
            // }
            // } else {
            //04/19/2013 DEL E N D R-OM003-001
          //WDS 2014/4/24 Add Start CMEX Defect#3545
            minAmt = new BigDecimal("0.1").pow(new PRC_DTLTMsg().getAttr("dealSlsPctNum").getFracDigit());
          //WDS 2014/4/24 Add Start CMEX Defect#3545
            
            if (prcAmtRate.compareTo(BigDecimal.ZERO) < 0) {
                setErrorMessageCd(NWCM0085E);
                return false;
            }
            //WDS 2014/2/14 Add Start Defect#3545
            if (!checkFraction(prcAmtRate, minAmt)) {
                setErrorMessageCd(NWCM0091E);
                return false;
            }
            //WDS 2014/2/14 Add End Defect#3545
            if (maxPercent.compareTo(prcAmtRate) < 0) {
                setErrorMessageCd(NWCM0086E);
                return false;
            }
            //04/19/2013 DEL START R-OM003-001
            // }
            // 01/30/2013 Upd E N D Defect#307
            //04/19/2013 DEL E N D R-OM003-001
        }

        return true;
    }

      //04/19/2013 DEL START R-OM003-001
//    private CCYTMsg findCcy(String glblCmpyCd, String ccyCd) {
//        CCYTMsg cond = new CCYTMsg();
//        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cond.ccyCd, ccyCd);
//        return (CCYTMsg) S21CacheTBLAccessor.findByKey(cond);
//    }

//    private GLBL_CMPYTMsg findGlblCmpy(String glblCmpyCd) {
//        GLBL_CMPYTMsg cond = new GLBL_CMPYTMsg();
//        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
//        return (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(cond);
//    }
      //04/19/2013 DEL E N D R-OM003-001

    /**
     * Find PRC_COND_TP.
     * @param glblCmpyCd String
     * @param prcCondTpCd String
     * @return PRC_COND_TPTMsg
     */
    private PRC_COND_TPTMsg findPrcCondTp(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg cond = new PRC_COND_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.prcCondTpCd, prcCondTpCd);

        return (PRC_COND_TPTMsg) S21FastTBLAccessor.findByKey(cond);
    }

    /**
     * Check fraction.
     * @param target BigDecimal
     * @param divisor BigDecimal
     * @return return false if failed
     */
    private boolean checkFraction(BigDecimal target, BigDecimal divisor) {
        return target.remainder(divisor).compareTo(BigDecimal.ZERO.setScale(target.scale())) == 0;
    }

    /**
     * Sets error message code.
     * @param errorMessageCd error message cd
     */
    private void setErrorMessageCd(String errorMessageCd) {
        this.errorMessageCd = errorMessageCd;
    }

    /**
     * Gets error message code.
     * @return error message code
     */
    public String getErrorMessageCd() {
        return errorMessageCd;
    }

}

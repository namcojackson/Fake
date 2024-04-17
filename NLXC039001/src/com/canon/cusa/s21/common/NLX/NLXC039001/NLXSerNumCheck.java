package com.canon.cusa.s21.common.NLX.NLXC039001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SER_NUM_RNGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <Pre>
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * </Pre>
 */
/**
 * <pre>
 * NLXC039001 : Serial Number Check
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/22/2010   Fujitsu         S.Yoshida       Create          N/A
 * 03/29/2011   CSAI            D.Fukaya        Update          1851
 * 02/23/2012   CSAI            N.Sasaki        Update          ITG#382732
 *</pre>
 */
public final class NLXSerNumCheck {

    /**
     * MDSE_SER_NUM_RNGTMsgArray Map
     * Key:MDSE_CD, Value:MDSE_SER_NUM_RNGTMsgArray
     */
    private Map<String, MDSE_SER_NUM_RNGTMsgArray> mdseSerNumRngTAryMap = null;
    /**
     * From Serial Number Prefix Map
     * Key:MDSE_CD, Value:From Serial Number Prefix List
     */
    private Map<String, List<String>> fromSerNumPfxMap = null;
    /**
     * From Serial Number Numeric Map
     * Key:MDSE_CD, Value:From Serial Number Numeric List
     */
    private Map<String, List<String>> fromSerNumNmrcMap = null;
    /**
     * Thru Serial Number Prefix Map
     * Key : MDSE_CD, value:Thru Serial Number Prefix List
     */
    private Map<String, List<String>> thruSerNumPfxMap = null;
    /**
     * Thru Serial Number Numeric Map
     * Key : MDSE_CD, Value:Thru Serial Number Numeric List
     */
    private Map<String, List<String>> thruSerNumNmrcMap = null;
    /**
     * Length of Serial Number Map
     * Key : MDSE_CD, Value:Length of Serial Number List
     */
    private Map<String, List<BigDecimal>> lgSerNumMap = null;
    /**
     * Serial Number Duplication Map
     * Key : MDSE_CD, Value:Serial Number Duplication List
     */
    private Map<String, List<String>> duplicationMap = null;

    /**
     * MDSE_SER_NUM_RNGTMsgArray
     */
    private MDSE_SER_NUM_RNGTMsgArray mdseSerNumRngTAry = null;
    /**
     * From Serial Number Prefix List
     */
    private List<String> fromSerNumPfxList = null;
    /**
     * From Serial Number Numeric List
     */
    private List<String> fromSerNumNmrcList  = null;
    /**
     * Thru Serial Number Prefix List
     */
    private List<String> thruSerNumPfxList = null;
    /**
     * Thru Serial Number Numeric List
     */
    private List<String> thruSerNumNmrcList  = null;
    /**
     * Length of Serial Number List
     */
    private List<BigDecimal> lgSerNumList = null;
    /**
     * Serial Number Duplication List
     */
    private List<String> duplicationList = null;

    /**
     * S21SsmLowLevelCodingClient
     */
    private S21SsmLowLevelCodingClient ssmLLClient = null;


    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NLXSerNumCheck() {

        mdseSerNumRngTAryMap = new HashMap<String, MDSE_SER_NUM_RNGTMsgArray>();
        mdseSerNumRngTAry    = null;

        fromSerNumPfxMap  = new HashMap<String, List<String>>();
        fromSerNumNmrcMap = new HashMap<String, List<String>>();
        thruSerNumPfxMap  = new HashMap<String, List<String>>();
        thruSerNumNmrcMap = new HashMap<String, List<String>>();
        lgSerNumMap       = new HashMap<String, List<BigDecimal>>();
        duplicationMap    = new HashMap<String, List<String>>();

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Check Serial Number for input.
     * </pre>
     * @param  glblCmpyCd Global Company Code
     * @param  mdseCd     Merchandise Code
     * @param  serNum     Serial Number
     * @return Error Messsage
     */
    public String chkSerNumForInput(String glblCmpyCd, String mdseCd, String serNum) {

        init(glblCmpyCd, mdseCd);

        String fromSerNumFixChar = null;
        String thruSerNumFixChar = null;

        // Check Input
        if (!ZYPCommonFunc.hasValue(serNum)) {
            return "ZZM9000E";
        }

        // Check Duplication
        if (duplicationList.indexOf(serNum) < 0) {
            duplicationList.add(serNum);
            duplicationMap.put(mdseCd, duplicationList);
        } else {
            return "NLXM0001E";
        }

        // When MDSE_SER_NUM_RNG is not regist, not check.
        if (mdseSerNumRngTAry.length() == 0) {
            return null;

        } else {

            boolean rangeCheckFlag = false;
            boolean lengthCheckFlag = false;

            String[] outSplitSerialNumber = splitSerialNumber(serNum);
            for (int j = 0; j < fromSerNumPfxList.size(); j++) {
                fromSerNumFixChar = fromSerNumPfxList.get(j);
                thruSerNumFixChar = thruSerNumPfxList.get(j);

                // Both From and Thru alphabetic parts must be corresponding. or All Blanks.
                if ((outSplitSerialNumber[0].equals(fromSerNumFixChar)
                        && outSplitSerialNumber[0].equals(thruSerNumFixChar))
                        || (!ZYPCommonFunc.hasValue(fromSerNumPfxList.get(j))
                                && !ZYPCommonFunc.hasValue(fromSerNumNmrcList.get(j))
                                && !ZYPCommonFunc.hasValue(thruSerNumPfxList.get(j))
                                && !ZYPCommonFunc.hasValue(thruSerNumNmrcList.get(j)))) {

                    // Check Length
                    BigDecimal lgSerNum = lgSerNumList.get(j);
                    if (new BigDecimal(serNum.length()).compareTo(lgSerNum) == 0) {
                        lengthCheckFlag = true;
                        break;
                    }
                }
            }

            // Length check error
            if (lengthCheckFlag == false) {
                return "NLXM0002E";
            }


            for (int j = 0; j < fromSerNumPfxList.size(); j++) {
                fromSerNumFixChar = fromSerNumPfxList.get(j);
                thruSerNumFixChar = thruSerNumPfxList.get(j);

                // Both From and Thru alphabetic parts must be corresponding. or All Blanks.
                if ((outSplitSerialNumber[0].equals(fromSerNumFixChar)
                        && outSplitSerialNumber[0].equals(thruSerNumFixChar))
                        || (!ZYPCommonFunc.hasValue(fromSerNumPfxList.get(j))
                                && !ZYPCommonFunc.hasValue(fromSerNumNmrcList.get(j))
                                && !ZYPCommonFunc.hasValue(thruSerNumPfxList.get(j))
                                && !ZYPCommonFunc.hasValue(thruSerNumNmrcList.get(j)))) {

                    // At the unsetting
                    if ("".equals(outSplitSerialNumber[1])
                            || "".equals(fromSerNumNmrcList.get(j))
                            || "".equals(thruSerNumNmrcList.get(j))) {

                        if ("".equals(outSplitSerialNumber[1])
                                && "".equals(fromSerNumNmrcList.get(j))
                                && "".equals(thruSerNumNmrcList.get(j))) {

                            rangeCheckFlag = true;
                            break;

                        } else if (!"".equals(outSplitSerialNumber[1])
                                && "".equals(fromSerNumNmrcList.get(j))
                                && "".equals(thruSerNumNmrcList.get(j))) {

                            rangeCheckFlag = true;
                            break;

                        } else {
                            continue;
                        }

                    // Check range
                    } else {

                        BigDecimal fromSerNumA1Number = new BigDecimal(outSplitSerialNumber[1]);
                        BigDecimal fromSerNumNumber   = new BigDecimal(fromSerNumNmrcList.get(j));
                        BigDecimal thruSerNumNumber   = new BigDecimal(thruSerNumNmrcList.get(j));

                        if (fromSerNumNumber.compareTo(fromSerNumA1Number) <= 0
                                && fromSerNumA1Number.compareTo(thruSerNumNumber) <= 0) {
                            rangeCheckFlag = true;
                            break;
                        }
                    }
                }
            }

            // Range check error
            if (rangeCheckFlag == false) {
                return "NLXM0002E";
            }
        }

        return null;
    }

    /**
     * ITG#382732 TODO
     * <pre>
     * Check Serial Number for Exist DB.
     * </pre>
     * @param  glblCmpyCd Global Company Code
     * @param  mdseCd     Merchandise Code
     * @param  serNum     Serial Number
     * @return Error Messsage
     */
    public String chkSerNumForExstDB(String glblCmpyCd, BigDecimal serEventStsPk, String mdseCd, String serNum) {

        // Set search parameters.
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mdseCd", mdseCd);
        paramMap.put("serNum", serNum);
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("serEventStsPk", serEventStsPk);

        S21SsmEZDResult serEventResult = NLXC039001Query.getInstance().checkSerialEvent(paramMap);
        if (!serEventResult.isCodeNormal()) {
            // Shipping Event Code Does NOT exist
            // i.e. Not yet shipped (OK)
            return null;
        } else {
            List list = (List) serEventResult.getResultObject();
            if (list != null && list.size() == 1) {
                Map map = (Map) list.get(0);
                String serOwnrId = checkNull((String) map.get("SER_OWNR_ID"));
                if (serOwnrId.equals("05")) {
                    // Last Serial Event is "Return"
                    // i.e. Item has been returned (OK)
                    return null;
                } else {
                    // Last Serial Event is NOT "Return"
                    // i.e. Item has been shipped (NG)
                    return "NLXM0001E";
                }
            }
            return "NLXM0003E";
        }
    }

    private String checkNull(String val) {
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * <pre>
     * Initialization
     * </pre>
     * 
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise Code
     */
    private void init(String glblCmpyCd, String mdseCd) {

        // Find MDSE_SER_NUM_RNG
        if (mdseSerNumRngTAryMap.containsKey(mdseCd)) {
            mdseSerNumRngTAry  = mdseSerNumRngTAryMap.get(mdseCd);
            fromSerNumPfxList  = fromSerNumPfxMap.get(mdseCd);
            fromSerNumNmrcList = fromSerNumNmrcMap.get(mdseCd);
            thruSerNumPfxList  = thruSerNumPfxMap.get(mdseCd);
            thruSerNumNmrcList = thruSerNumNmrcMap.get(mdseCd);
            lgSerNumList       = lgSerNumMap.get(mdseCd);
            duplicationList    = duplicationMap.get(mdseCd);
            return;
        } else {
            fromSerNumPfxList  = new ArrayList<String>();
            fromSerNumNmrcList = new ArrayList<String>();
            thruSerNumPfxList  = new ArrayList<String>();
            thruSerNumNmrcList = new ArrayList<String>();
            lgSerNumList       = new ArrayList<BigDecimal>();
            duplicationList    = new ArrayList<String>();
        }

        // Find MDSE_SER_NUM_RNG
        MDSE_SER_NUM_RNGTMsg mdseSerNumRngT = new MDSE_SER_NUM_RNGTMsg();
        mdseSerNumRngT.setConditionValue("glblCmpyCd01", glblCmpyCd);
        mdseSerNumRngT.setConditionValue("mdseCd01",     mdseCd);
        mdseSerNumRngT.setMaxCount(0);
        mdseSerNumRngT.setSQLID("001");

        mdseSerNumRngTAry = (MDSE_SER_NUM_RNGTMsgArray) EZDTBLAccessor.findByCondition(mdseSerNumRngT);

        if (mdseSerNumRngTAry.length() > 0) {

            String[] splitSerNumFrom = null;
            String[] splitSerNumTo   = null;

            for (int i = 0; i < mdseSerNumRngTAry.length(); i++) {
                String xxFromSerNum = mdseSerNumRngTAry.no(i).fromSerNum.getValue();
                String xxThruSerNum = mdseSerNumRngTAry.no(i).thruSerNum.getValue();
                BigDecimal xxlgSerNum = ZYPCommonFunc.getBigDecimal(mdseSerNumRngTAry.no(i).lgSerNum, "0");

                // 000    -> [0]:null,   [1]:000  -> OK
                // AAA    -> [0]:AAA,    [1]:null -> NG
                // AAA000 -> [0]:AAA,    [1]:000  -> OK
                // 000AAA -> [0]:000AAA, [1]:null -> NG
                splitSerNumFrom = splitSerialNumber(xxFromSerNum);
                fromSerNumPfxList.add(splitSerNumFrom[0]);
                fromSerNumNmrcList.add(splitSerNumFrom[1]);

                splitSerNumTo = splitSerialNumber(xxThruSerNum);
                thruSerNumPfxList.add(splitSerNumTo[0]);
                thruSerNumNmrcList.add(splitSerNumTo[1]);

                lgSerNumList.add(xxlgSerNum);

                // From:Number, Thru:Alphabet -> All blank
                if (!ZYPCommonFunc.hasValue(fromSerNumPfxList.get(i))
                        && ZYPCommonFunc.hasValue(fromSerNumNmrcList.get(i))
                        && ZYPCommonFunc.hasValue(thruSerNumPfxList.get(i))
                        && !ZYPCommonFunc.hasValue(thruSerNumNmrcList.get(i))) {
                    fromSerNumPfxList.set(i,  "");
                    fromSerNumNmrcList.set(i, "");
                    thruSerNumPfxList.set(i,  "");
                    thruSerNumNmrcList.set(i, "");
                }
            }
        }

        mdseSerNumRngTAryMap.put(mdseCd, mdseSerNumRngTAry);
        fromSerNumPfxMap.put(mdseCd,     fromSerNumPfxList);
        fromSerNumNmrcMap.put(mdseCd,    fromSerNumNmrcList);
        thruSerNumPfxMap.put(mdseCd,     thruSerNumPfxList);
        thruSerNumNmrcMap.put(mdseCd,    thruSerNumNmrcList);
        lgSerNumMap.put(mdseCd,          lgSerNumList);
    }

    /**
     * <pre>
     * Split Serial Number
     * </pre>
     * @param  String Serial Number
     * @return Split Serial Number
     */
    private String[] splitSerialNumber(String serNum) {

        String[] splitSerNum = new String[2];

        int i = serNum.length();
        for ( ; 0 < i; i--) {
            try {
                new BigDecimal(String.valueOf(serNum.charAt(i - 1)));
            } catch (NumberFormatException e) {
                break;
            }
        }

        splitSerNum[0] = serNum.substring(0, i);
        splitSerNum[1] = serNum.substring(i);

        return splitSerNum;
    }
}
